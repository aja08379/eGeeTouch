package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/* loaded from: classes.dex */
public class Helper_Network {
    private static String url = "http://www.egeetouch.com/process.php";

    public static boolean haveNetworkConnection(Context context) {
        boolean z;
        boolean z2;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.getType() == 1) {
                z2 = false;
                z = true;
            } else if (activeNetworkInfo.getType() == 0) {
                z = false;
                z2 = true;
            } else {
                z = false;
                z2 = false;
            }
            return z || z2;
        }
        return false;
    }
}
