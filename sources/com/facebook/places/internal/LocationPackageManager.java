package com.facebook.places.internal;

import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import com.facebook.places.internal.ScannerException;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
/* loaded from: classes.dex */
public class LocationPackageManager {
    private static final String TAG = "LocationPackageManager";

    /* loaded from: classes.dex */
    public interface Listener {
        void onLocationPackage(LocationPackage locationPackage);
    }

    public static void requestLocationPackage(final LocationPackageRequestParams locationPackageRequestParams, final Listener listener) {
        FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.places.internal.LocationPackageManager.1
            @Override // java.lang.Runnable
            public void run() {
                FutureTask futureTask;
                FutureTask futureTask2;
                LocationPackage locationPackage = new LocationPackage();
                try {
                    FutureTask futureTask3 = null;
                    if (LocationPackageRequestParams.this.isLocationScanEnabled()) {
                        LocationScanner newLocationScanner = ScannerFactory.newLocationScanner(FacebookSdk.getApplicationContext(), LocationPackageRequestParams.this);
                        newLocationScanner.initAndCheckEligibility();
                        futureTask = LocationPackageManager.newLocationScanFuture(newLocationScanner, LocationPackageRequestParams.this);
                        FacebookSdk.getExecutor().execute(futureTask);
                    } else {
                        futureTask = null;
                    }
                    if (LocationPackageRequestParams.this.isWifiScanEnabled()) {
                        futureTask2 = LocationPackageManager.newWifiScanFuture(LocationPackageRequestParams.this);
                        FacebookSdk.getExecutor().execute(futureTask2);
                    } else {
                        futureTask2 = null;
                    }
                    if (LocationPackageRequestParams.this.isBluetoothScanEnabled()) {
                        futureTask3 = LocationPackageManager.newBluetoothScanFuture(LocationPackageRequestParams.this);
                        FacebookSdk.getExecutor().execute(futureTask3);
                    }
                    if (futureTask3 != null) {
                        try {
                            LocationPackage locationPackage2 = (LocationPackage) futureTask3.get();
                            locationPackage.ambientBluetoothLe = locationPackage2.ambientBluetoothLe;
                            locationPackage.isBluetoothScanningEnabled = locationPackage2.isBluetoothScanningEnabled;
                        } catch (Exception e) {
                            LocationPackageManager.logException("Exception scanning for bluetooth beacons", e);
                        }
                    }
                    if (futureTask2 != null) {
                        try {
                            LocationPackage locationPackage3 = (LocationPackage) futureTask2.get();
                            locationPackage.isWifiScanningEnabled = locationPackage3.isWifiScanningEnabled;
                            locationPackage.connectedWifi = locationPackage3.connectedWifi;
                            locationPackage.ambientWifi = locationPackage3.ambientWifi;
                        } catch (Exception e2) {
                            LocationPackageManager.logException("Exception scanning for wifi access points", e2);
                        }
                    }
                    if (futureTask != null) {
                        try {
                            LocationPackage locationPackage4 = (LocationPackage) futureTask.get();
                            locationPackage.locationError = locationPackage4.locationError;
                            locationPackage.location = locationPackage4.location;
                        } catch (Exception e3) {
                            LocationPackageManager.logException("Exception getting location", e3);
                        }
                    }
                } catch (ScannerException e4) {
                    LocationPackageManager.logException("Exception scanning for locations", e4);
                    locationPackage.locationError = e4.type;
                } catch (Exception e5) {
                    LocationPackageManager.logException("Exception requesting a location package", e5);
                }
                listener.onLocationPackage(locationPackage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static FutureTask<LocationPackage> newLocationScanFuture(final LocationScanner locationScanner, LocationPackageRequestParams locationPackageRequestParams) {
        return new FutureTask<>(new Callable<LocationPackage>() { // from class: com.facebook.places.internal.LocationPackageManager.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public LocationPackage call() throws Exception {
                LocationPackage locationPackage = new LocationPackage();
                try {
                    locationPackage.location = LocationScanner.this.getLocation();
                } catch (ScannerException e) {
                    locationPackage.locationError = e.type;
                    LocationPackageManager.logException("Exception while getting location", e);
                } catch (Exception unused) {
                    locationPackage.locationError = ScannerException.Type.UNKNOWN_ERROR;
                }
                return locationPackage;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static FutureTask<LocationPackage> newBluetoothScanFuture(final LocationPackageRequestParams locationPackageRequestParams) {
        return new FutureTask<>(new Callable<LocationPackage>() { // from class: com.facebook.places.internal.LocationPackageManager.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public LocationPackage call() throws Exception {
                LocationPackage locationPackage = new LocationPackage();
                try {
                    BleScanner newBleScanner = ScannerFactory.newBleScanner(FacebookSdk.getApplicationContext(), LocationPackageRequestParams.this);
                    newBleScanner.initAndCheckEligibility();
                    newBleScanner.startScanning();
                    try {
                        Thread.sleep(LocationPackageRequestParams.this.getBluetoothScanDurationMs());
                    } catch (Exception unused) {
                    }
                    newBleScanner.stopScanning();
                    int errorCode = newBleScanner.getErrorCode();
                    if (errorCode == 0) {
                        locationPackage.ambientBluetoothLe = newBleScanner.getScanResults();
                        locationPackage.isBluetoothScanningEnabled = true;
                    } else {
                        if (FacebookSdk.isDebugEnabled()) {
                            Utility.logd(LocationPackageManager.TAG, String.format(Locale.getDefault(), "Bluetooth LE scan failed with error: %d", Integer.valueOf(errorCode)));
                        }
                        locationPackage.isBluetoothScanningEnabled = false;
                    }
                } catch (Exception e) {
                    LocationPackageManager.logException("Exception scanning for bluetooth beacons", e);
                    locationPackage.isBluetoothScanningEnabled = false;
                }
                return locationPackage;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static FutureTask<LocationPackage> newWifiScanFuture(final LocationPackageRequestParams locationPackageRequestParams) {
        return new FutureTask<>(new Callable<LocationPackage>() { // from class: com.facebook.places.internal.LocationPackageManager.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public LocationPackage call() throws Exception {
                LocationPackage locationPackage = new LocationPackage();
                try {
                    WifiScanner newWifiScanner = ScannerFactory.newWifiScanner(FacebookSdk.getApplicationContext(), LocationPackageRequestParams.this);
                    newWifiScanner.initAndCheckEligibility();
                    locationPackage.connectedWifi = newWifiScanner.getConnectedWifi();
                    locationPackage.isWifiScanningEnabled = newWifiScanner.isWifiScanningEnabled();
                    if (locationPackage.isWifiScanningEnabled) {
                        locationPackage.ambientWifi = newWifiScanner.getWifiScans();
                    }
                } catch (Exception e) {
                    LocationPackageManager.logException("Exception scanning for wifi access points", e);
                    locationPackage.isWifiScanningEnabled = false;
                }
                return locationPackage;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logException(String str, Throwable th) {
        if (FacebookSdk.isDebugEnabled()) {
            Log.e(TAG, str, th);
        }
    }
}
