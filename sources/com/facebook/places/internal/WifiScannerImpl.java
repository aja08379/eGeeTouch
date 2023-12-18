package com.facebook.places.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.internal.Validate;
import com.facebook.places.internal.ScannerException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class WifiScannerImpl implements WifiScanner {
    private static final String SSID_NOMAP = "_nomap";
    private static final String SSID_OPTOUT = "_optout";
    private ScanResultBroadcastReceiver broadcastReceiver;
    private Context context;
    private final LocationPackageRequestParams params;
    private final Object scanLock = new Object();
    private WifiManager wifiManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WifiScannerImpl(Context context, LocationPackageRequestParams locationPackageRequestParams) {
        this.context = context;
        this.params = locationPackageRequestParams;
    }

    @Override // com.facebook.places.internal.WifiScanner
    public void initAndCheckEligibility() throws ScannerException {
        if (!this.context.getPackageManager().hasSystemFeature("android.hardware.wifi")) {
            throw new ScannerException(ScannerException.Type.NOT_SUPPORTED);
        }
        if (!Validate.hasWiFiPermission(this.context)) {
            throw new ScannerException(ScannerException.Type.PERMISSION_DENIED);
        }
        if (this.wifiManager == null) {
            this.wifiManager = (WifiManager) this.context.getSystemService("wifi");
        }
        if (!isWifiScanningAlwaysOn() && !this.wifiManager.isWifiEnabled()) {
            throw new ScannerException(ScannerException.Type.DISABLED);
        }
    }

    @Override // com.facebook.places.internal.WifiScanner
    public WifiScanResult getConnectedWifi() throws ScannerException {
        try {
            WifiInfo connectionInfo = this.wifiManager.getConnectionInfo();
            if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getBSSID()) && connectionInfo.getSupplicantState() == SupplicantState.COMPLETED && !isWifiSsidBlacklisted(connectionInfo.getSSID())) {
                WifiScanResult wifiScanResult = new WifiScanResult();
                wifiScanResult.bssid = connectionInfo.getBSSID();
                wifiScanResult.ssid = connectionInfo.getSSID();
                wifiScanResult.rssi = connectionInfo.getRssi();
                wifiScanResult.timestampMs = SystemClock.elapsedRealtime();
                if (Build.VERSION.SDK_INT >= 21) {
                    wifiScanResult.frequency = connectionInfo.getFrequency();
                }
                return wifiScanResult;
            }
            return null;
        } catch (Exception e) {
            throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR, e);
        }
    }

    @Override // com.facebook.places.internal.WifiScanner
    public boolean isWifiScanningEnabled() {
        try {
            initAndCheckEligibility();
            return Validate.hasLocationPermission(this.context);
        } catch (ScannerException unused) {
            return false;
        }
    }

    private boolean isWifiScanningAlwaysOn() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.wifiManager.isScanAlwaysAvailable();
        }
        return false;
    }

    private List<WifiScanResult> getCachedScanResults() throws ScannerException {
        try {
            List<ScanResult> filterWifiScanResultsByMaxAge = filterWifiScanResultsByMaxAge(this.wifiManager.getScanResults(), this.params.getWifiScanMaxAgeMs());
            filterResults(filterWifiScanResultsByMaxAge, this.params.getWifiMaxScanResults());
            ArrayList arrayList = new ArrayList(filterWifiScanResultsByMaxAge.size());
            for (ScanResult scanResult : filterWifiScanResultsByMaxAge) {
                if (!isWifiSsidBlacklisted(scanResult.SSID)) {
                    WifiScanResult wifiScanResult = new WifiScanResult();
                    wifiScanResult.bssid = scanResult.BSSID;
                    wifiScanResult.ssid = scanResult.SSID;
                    wifiScanResult.rssi = scanResult.level;
                    wifiScanResult.frequency = scanResult.frequency;
                    if (Build.VERSION.SDK_INT >= 17) {
                        wifiScanResult.timestampMs = TimeUnit.MICROSECONDS.toMillis(scanResult.timestamp);
                    } else {
                        wifiScanResult.timestampMs = SystemClock.elapsedRealtime();
                    }
                    arrayList.add(wifiScanResult);
                }
            }
            return arrayList;
        } catch (Exception e) {
            throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR, e);
        }
    }

    private static boolean isWifiSsidBlacklisted(String str) {
        if (str != null) {
            return str.endsWith(SSID_NOMAP) || str.contains(SSID_OPTOUT);
        }
        return false;
    }

    private static void filterResults(List<ScanResult> list, int i) {
        if (list.size() > i) {
            Collections.sort(list, new Comparator<ScanResult>() { // from class: com.facebook.places.internal.WifiScannerImpl.1
                @Override // java.util.Comparator
                public int compare(ScanResult scanResult, ScanResult scanResult2) {
                    return scanResult2.level - scanResult.level;
                }
            });
            list.subList(i, list.size()).clear();
        }
    }

    private static List<ScanResult> filterWifiScanResultsByMaxAge(List<ScanResult> list, long j) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            if (Build.VERSION.SDK_INT < 17) {
                arrayList.addAll(list);
            } else {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                for (ScanResult scanResult : list) {
                    long j2 = elapsedRealtime - (scanResult.timestamp / 1000);
                    if (j2 < 0) {
                        j2 = System.currentTimeMillis() - scanResult.timestamp;
                    }
                    if (j2 < j) {
                        arrayList.add(scanResult);
                    }
                }
            }
        }
        return arrayList;
    }

    @Override // com.facebook.places.internal.WifiScanner
    public synchronized List<WifiScanResult> getWifiScans() throws ScannerException {
        List<WifiScanResult> cachedScanResults;
        boolean z;
        cachedScanResults = this.params.isWifiActiveScanForced() ? null : getCachedScanResults();
        if (cachedScanResults != null && !cachedScanResults.isEmpty()) {
            z = false;
            if (!this.params.isWifiActiveScanForced() || (this.params.isWifiActiveScanAllowed() && z)) {
                cachedScanResults = getActiveScanResults();
            }
        }
        z = true;
        if (!this.params.isWifiActiveScanForced()) {
        }
        cachedScanResults = getActiveScanResults();
        return cachedScanResults;
    }

    private List<WifiScanResult> getActiveScanResults() throws ScannerException {
        List<WifiScanResult> list = null;
        try {
            if (Validate.hasChangeWifiStatePermission(this.context)) {
                registerBroadcastReceiver();
                if (this.wifiManager.startScan()) {
                    try {
                        synchronized (this.scanLock) {
                            this.scanLock.wait(this.params.getWifiScanTimeoutMs());
                        }
                    } catch (InterruptedException unused) {
                    }
                    list = getCachedScanResults();
                }
            }
        } catch (Exception unused2) {
        } catch (Throwable th) {
            unregisterBroadcastReceiver();
            throw th;
        }
        unregisterBroadcastReceiver();
        return list;
    }

    private void registerBroadcastReceiver() {
        if (this.broadcastReceiver != null) {
            unregisterBroadcastReceiver();
        }
        this.broadcastReceiver = new ScanResultBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        this.context.registerReceiver(this.broadcastReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterBroadcastReceiver() {
        ScanResultBroadcastReceiver scanResultBroadcastReceiver = this.broadcastReceiver;
        if (scanResultBroadcastReceiver != null) {
            try {
                this.context.unregisterReceiver(scanResultBroadcastReceiver);
            } catch (Exception unused) {
            }
            this.broadcastReceiver = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ScanResultBroadcastReceiver extends BroadcastReceiver {
        private ScanResultBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !"android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                return;
            }
            synchronized (WifiScannerImpl.this.scanLock) {
                WifiScannerImpl.this.scanLock.notify();
            }
            WifiScannerImpl.this.unregisterBroadcastReceiver();
        }
    }
}
