package no.nordicsemi.android.dfu.internal.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Build;
import java.util.ArrayList;
import java.util.List;
import no.nordicsemi.android.dfu.DfuDeviceSelector;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class BootloaderScannerLollipop extends ScanCallback implements BootloaderScanner {
    private String mBootloaderAddress;
    private final String mDeviceAddress;
    private final String mDeviceAddressIncremented;
    private boolean mFound;
    private final Object mLock = new Object();
    private DfuDeviceSelector mSelector;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BootloaderScannerLollipop(String str, String str2) {
        this.mDeviceAddress = str;
        this.mDeviceAddressIncremented = str2;
    }

    @Override // no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner
    public String searchUsing(DfuDeviceSelector dfuDeviceSelector, final long j) {
        BluetoothLeScanner bluetoothLeScanner;
        this.mSelector = dfuDeviceSelector;
        this.mBootloaderAddress = null;
        this.mFound = false;
        new Thread(new Runnable() { // from class: no.nordicsemi.android.dfu.internal.scanner.-$$Lambda$BootloaderScannerLollipop$U55QDRYw_ffeZ7sHE7TD9rIzj9A
            @Override // java.lang.Runnable
            public final void run() {
                BootloaderScannerLollipop.this.lambda$searchUsing$0$BootloaderScannerLollipop(j);
            }
        }, "Scanner timer").start();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || defaultAdapter.getState() != 12 || (bluetoothLeScanner = defaultAdapter.getBluetoothLeScanner()) == null) {
            return null;
        }
        ScanSettings build = new ScanSettings.Builder().setScanMode(2).build();
        if (defaultAdapter.isOffloadedFilteringSupported() && Build.VERSION.SDK_INT >= 27) {
            bluetoothLeScanner.startScan(new ArrayList(), build, this);
        } else {
            bluetoothLeScanner.startScan((List<ScanFilter>) null, build, this);
        }
        try {
            synchronized (this.mLock) {
                while (!this.mFound) {
                    this.mLock.wait();
                }
            }
        } catch (InterruptedException unused) {
        }
        bluetoothLeScanner.stopScan(this);
        return this.mBootloaderAddress;
    }

    public /* synthetic */ void lambda$searchUsing$0$BootloaderScannerLollipop(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
        }
        if (this.mFound) {
            return;
        }
        this.mBootloaderAddress = null;
        this.mFound = true;
        synchronized (this.mLock) {
            this.mLock.notifyAll();
        }
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanResult(int i, ScanResult scanResult) {
        String address = scanResult.getDevice().getAddress();
        if (this.mFound || !this.mSelector.matches(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes(), this.mDeviceAddress, this.mDeviceAddressIncremented)) {
            return;
        }
        this.mBootloaderAddress = address;
        this.mFound = true;
        synchronized (this.mLock) {
            this.mLock.notifyAll();
        }
    }
}
