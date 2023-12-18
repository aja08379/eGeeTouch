package com.egeetouch.egeetouch_manager;

import java.util.Locale;
/* loaded from: classes.dex */
public class Helper_PhoneDetails {
    private static final int REQUEST_ENABLE_BT = 1;
    public static boolean dialog_locationalert_isshown = false;
    public static double myLocationLatitude;
    public static double myLocationLongitude;

    public static String phonelangauge() {
        return Locale.getDefault().getLanguage();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x009a, code lost:
        if (r1.getAccuracy() >= r0.getAccuracy()) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a1, code lost:
        if (r0 != null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00a3, code lost:
        r5 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void get_location(android.content.Context r9) {
        android.location.Location r1;
        android.location.Location r0;
        android.location.LocationManager r0 = (android.location.LocationManager) r9.getSystemService("location");
        boolean r2 = r0.isProviderEnabled("gps");
        boolean r4 = r0.isProviderEnabled("network");
        android.app.Activity r5 = (android.app.Activity) r9;
        if (androidx.core.content.ContextCompat.checkSelfPermission(r9, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            androidx.core.app.ActivityCompat.requestPermissions(r5, new java.lang.String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
        }
        android.location.Location r5 = null;
        if (r2) {
            r1 = r0.getLastKnownLocation("gps");
            com.egeetouch.egeetouch_manager.Helper_PhoneDetails.dialog_locationalert_isshown = true;
        } else {
            r1 = null;
        }
        if (r4) {
            r0 = r0.getLastKnownLocation("network");
            com.egeetouch.egeetouch_manager.Helper_PhoneDetails.dialog_locationalert_isshown = true;
        } else {
            r0 = null;
        }
        if (!com.egeetouch.egeetouch_manager.Helper_PhoneDetails.dialog_locationalert_isshown && !r2 && !r4) {
            new cn.pedant.SweetAlert.SweetAlertDialog(r9).setTitleText(r9.getString(com.egeetouch.egeetouch_manager.R.string.do_you_want_to_save_your_location)).setContentText(r9.getString(com.egeetouch.egeetouch_manager.R.string.check_your_email_and_follow_the_instruction)).setConfirmText(r9.getString(com.egeetouch.egeetouch_manager.R.string.yes)).setConfirmClickListener(new cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Helper_PhoneDetails.2
                {
                }

                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(cn.pedant.SweetAlert.SweetAlertDialog r3) {
                    r3.dismissWithAnimation();
                    com.egeetouch.egeetouch_manager.MainActivity.context.startActivity(new android.content.Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                }
            }).setCancelText(r9.getString(com.egeetouch.egeetouch_manager.R.string.no)).setCancelClickListener(new cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Helper_PhoneDetails.1
                {
                }

                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(cn.pedant.SweetAlert.SweetAlertDialog r1) {
                    r1.dismissWithAnimation();
                }
            }).show();
            com.egeetouch.egeetouch_manager.Helper_PhoneDetails.dialog_locationalert_isshown = true;
        }
        if (r1 == null || r0 == null) {
            if (r1 == null) {
            }
            r5 = r1;
        }
        if (r5 != null) {
            com.egeetouch.egeetouch_manager.Helper_PhoneDetails.myLocationLatitude = r5.getLatitude();
            com.egeetouch.egeetouch_manager.Helper_PhoneDetails.myLocationLongitude = r5.getLongitude();
        }
    }
}
