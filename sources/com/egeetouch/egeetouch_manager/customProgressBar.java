package com.egeetouch.egeetouch_manager;

import android.app.Dialog;
import android.os.Handler;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes.dex */
public class customProgressBar {
    public static Handler customHandler;
    public static TextView custom_DiaContentText;
    public static TextView custom_DiaTitleDots;
    public static TextView custom_DiaTitleText;
    public static Dialog custom_progressDialog;
    public static Runnable runText = new Runnable() { // from class: com.egeetouch.egeetouch_manager.customProgressBar.1
        int count = 0;

        @Override // java.lang.Runnable
        public void run() {
            int i = this.count + 1;
            this.count = i;
            if (i == 1) {
                customProgressBar.custom_DiaTitleDots.setText(" .");
            } else if (i == 2) {
                customProgressBar.custom_DiaTitleDots.setText(" ..");
            } else if (i == 3) {
                customProgressBar.custom_DiaTitleDots.setText(" ...");
            }
            if (this.count == 3) {
                this.count = 0;
            }
            customProgressBar.customHandler.postDelayed(this, 500L);
        }
    };

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0088, code lost:
        if (r6.equals("permission") == false) goto L3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void ShowProgressBar(android.content.Context r5, java.lang.String r6) {
        android.app.Dialog r0 = new android.app.Dialog(r5);
        com.egeetouch.egeetouch_manager.customProgressBar.custom_progressDialog = r0;
        r0.setContentView(com.egeetouch.egeetouch_manager.R.layout.progress_bar_dialog);
        char r2 = 0;
        com.egeetouch.egeetouch_manager.customProgressBar.custom_progressDialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(0));
        com.egeetouch.egeetouch_manager.customProgressBar.custom_progressDialog.show();
        com.egeetouch.egeetouch_manager.customProgressBar.custom_DiaTitleText = (android.widget.TextView) com.egeetouch.egeetouch_manager.customProgressBar.custom_progressDialog.findViewById(com.egeetouch.egeetouch_manager.R.id.title_tv);
        com.egeetouch.egeetouch_manager.customProgressBar.custom_DiaTitleDots = (android.widget.TextView) com.egeetouch.egeetouch_manager.customProgressBar.custom_progressDialog.findViewById(com.egeetouch.egeetouch_manager.R.id.title_dots_tv);
        android.os.Handler r0 = new android.os.Handler();
        com.egeetouch.egeetouch_manager.customProgressBar.customHandler = r0;
        r0.postDelayed(com.egeetouch.egeetouch_manager.customProgressBar.runText, 500L);
        r6.hashCode();
        switch (r6.hashCode()) {
            case -517618225:
                break;
            case 336650556:
                if (r6.equals("loading")) {
                    r2 = 1;
                    break;
                }
                r2 = 65535;
                break;
            case 628886935:
                if (r6.equals("onlineAccess")) {
                    r2 = 2;
                    break;
                }
                r2 = 65535;
                break;
            case 1040782463:
                if (r6.equals("pleaseWait")) {
                    r2 = 3;
                    break;
                }
                r2 = 65535;
                break;
            case 1816497766:
                if (r6.equals("syncLock")) {
                    r2 = 4;
                    break;
                }
                r2 = 65535;
                break;
            default:
                r2 = 65535;
                break;
        }
        switch (r2) {
            case 0:
                com.egeetouch.egeetouch_manager.customProgressBar.custom_DiaTitleText.setText("Checking Permission");
                return;
            case 1:
                com.egeetouch.egeetouch_manager.customProgressBar.custom_DiaTitleText.setText(r5.getResources().getString(com.egeetouch.egeetouch_manager.R.string.loading2));
                return;
            case 2:
                com.egeetouch.egeetouch_manager.customProgressBar.custom_DiaTitleText.setText("Progressing ");
                return;
            case 3:
                com.egeetouch.egeetouch_manager.customProgressBar.custom_DiaTitleText.setText(r5.getResources().getString(com.egeetouch.egeetouch_manager.R.string.please_wait2));
                break;
            case 4:
                com.egeetouch.egeetouch_manager.customProgressBar.custom_DiaTitleText.setText(r5.getResources().getString(com.egeetouch.egeetouch_manager.R.string.data_sync));
                return;
        }
        com.egeetouch.egeetouch_manager.customProgressBar.custom_DiaTitleText.setText(r5.getResources().getString(com.egeetouch.egeetouch_manager.R.string.please_wait2));
    }

    public static synchronized void closeDialog(long j) {
        synchronized (customProgressBar.class) {
            new Timer().schedule(new TimerTask() { // from class: com.egeetouch.egeetouch_manager.customProgressBar.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    customProgressBar.customHandler.removeCallbacks(customProgressBar.runText);
                    if (customProgressBar.custom_progressDialog == null || !customProgressBar.custom_progressDialog.isShowing()) {
                        return;
                    }
                    customProgressBar.custom_progressDialog.dismiss();
                }
            }, j);
        }
    }
}
