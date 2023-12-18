package no.nordicsemi.android.dfu.internal.scanner;

import android.os.Build;
import java.util.Locale;
/* loaded from: classes2.dex */
public final class BootloaderScannerFactory {
    private static final int ADDRESS_DIFF = 1;

    private BootloaderScannerFactory() {
    }

    public static String getIncrementedAddress(String str) {
        return str.substring(0, 15) + String.format(Locale.US, "%02X", Integer.valueOf((Integer.valueOf(str.substring(15), 16).intValue() + 1) & 255));
    }

    public static BootloaderScanner getScanner(String str) {
        String incrementedAddress = getIncrementedAddress(str);
        if (Build.VERSION.SDK_INT >= 21) {
            return new BootloaderScannerLollipop(str, incrementedAddress);
        }
        return new BootloaderScannerJB(str, incrementedAddress);
    }
}
