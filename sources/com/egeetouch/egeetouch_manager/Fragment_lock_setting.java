package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
/* loaded from: classes.dex */
public class Fragment_lock_setting extends Fragment {
    private static boolean disconnected_trigger = false;
    public static boolean initial_setup = false;
    private static boolean reconnect_msg_show = false;
    Button btn_managePasscodes;
    Button btn_manageTags;
    LinearLayout ll_autoLocking;
    LinearLayout ll_lockInfo;
    LinearLayout ll_shackleBypass;
    private Menu menu;
    SweetAlertDialog reconnect_msg;
    Switch switch_AutoLocking;
    Switch switch_ShackleBypass;
    TextView tv_auditCount;
    TextView tv_auditCountTitle;
    TextView tv_reconnectionTime;
    TextView tv_tagCount;
    TextView tv_tagCountTitle;
    private Handler manage_setting_handler = new Handler();
    View rootView = null;
    final Runnable r = new Runnable() { // from class: com.egeetouch.egeetouch_manager.Fragment_lock_setting.7
        @Override // java.lang.Runnable
        public void run() {
            if (BLEService.mConnectionState == 0) {
                Fragment_lock_setting.this.icon_display(false);
                if (Fragment_lock_setting.reconnect_msg_show || Fragment_lock_setting.disconnected_trigger) {
                    if (Fragment_lock_setting.reconnect_msg_show && Fragment_lock_setting.disconnected_trigger && Fragment_lock_setting.this.reconnect_msg != null && Fragment_lock_setting.this.reconnect_msg.isShowing()) {
                        Fragment_lock_setting.this.reconnect_msg.dismissWithAnimation();
                    }
                } else {
                    boolean unused = Fragment_lock_setting.reconnect_msg_show = true;
                    Fragment_lock_setting.this.reconnect_msg = new SweetAlertDialog(MainActivity.context, 5);
                    Fragment_lock_setting.this.reconnect_msg.setTitleText(MainActivity.context.getResources().getString(R.string.lost_connection));
                    Fragment_lock_setting.this.reconnect_msg.setContentText(MainActivity.context.getResources().getString(R.string.move_nearer));
                    Fragment_lock_setting.this.reconnect_msg.setCancelText(MainActivity.context.getResources().getString(R.string.cancel));
                    Fragment_lock_setting.this.reconnect_msg.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_lock_setting.7.1
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            BLEService.cancel_scaning_triggered = true;
                            BLEService.Ble_Mode = BLEService.disconnect;
                        }
                    });
                    Fragment_lock_setting.this.reconnect_msg.setCancelable(false);
                    Fragment_lock_setting.this.reconnect_msg.show();
                    Log.i("TAG", "reconnect_msg is shown");
                }
            } else {
                Fragment_lock_setting.this.icon_display(true);
                if (Fragment_lock_setting.reconnect_msg_show && Fragment_lock_setting.this.reconnect_msg != null && Fragment_lock_setting.this.reconnect_msg.isShowing()) {
                    Fragment_lock_setting.this.reconnect_msg.dismiss();
                    boolean unused2 = Fragment_lock_setting.reconnect_msg_show = false;
                }
            }
            Fragment_lock_setting.this.manage_setting_handler.postDelayed(this, 300L);
        }
    };

    public static Fragment_lock_setting newInstance() {
        return new Fragment_lock_setting();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.manage_setting_handler.postDelayed(this.r, 300L);
        TextView textView = (TextView) this.rootView.findViewById(R.id.textView_firmware_v);
        if (textView != null) {
            textView.setText(String.valueOf(BLEService.selected_lock_version));
        }
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.setting);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        this.manage_setting_handler.removeCallbacks(this.r);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.global, menu);
    }

    @Override // android.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_button_disconnect) {
            new SweetAlertDialog(getActivity()).setTitleText(getString(R.string.power_off)).setContentText(getString(R.string.are_you_sure_power_off_lock)).setConfirmText(getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_lock_setting.1
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                    boolean unused = Fragment_lock_setting.disconnected_trigger = true;
                    BLEService.cancel_scaning_triggered = true;
                    BLEService.Ble_Mode = BLEService.Send_timeStamp;
                    BLEService.disconnect_triggered = true;
                    MainActivity.isUserClickedShutdown = true;
                    MainActivity.shutdown_dialog = new SweetAlertDialog(MainActivity.context, 5);
                    MainActivity.shutdown_dialog.setTitleText(Fragment_lock_setting.this.getString(R.string.power_off));
                    MainActivity.shutdown_dialog.setCancelable(false);
                    MainActivity.shutdown_dialog.setCanceledOnTouchOutside(false);
                    MainActivity.shutdown_dialog.show();
                    MainActivity.stopBackStack = false;
                    ((AppCompatActivity) Fragment_lock_setting.this.getActivity()).getSupportActionBar().setTitle(R.string.dashboard);
                    MediaPlayer create = MediaPlayer.create(Fragment_lock_setting.this.getActivity(), (int) R.raw.disconnectinapp);
                    create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_lock_setting.1.1
                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });
                    create.start();
                }
            }).setCancelText(getString(R.string.no)).show();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0456  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x047d  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0488  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x04d3  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x04d8  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x04e4  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x04e8  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x04f7  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0502  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0506  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x057f  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x058e  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x064e  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x065d  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x06cc  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0818  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x03a9  */
    @Override // android.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View onCreateView(android.view.LayoutInflater r23, android.view.ViewGroup r24, android.os.Bundle r25) {
        java.lang.Object r19;
        java.lang.Object r12;
        boolean r3;
        boolean r7;
        android.widget.ImageView r1;
        android.content.SharedPreferences r13;
        boolean r4;
        int r1;
        android.widget.TextView r1;
        android.widget.TextView r1;
        android.widget.ImageView r1;
        boolean r3;
        new java.util.ArrayList();
        android.view.View r1 = r23.inflate(com.egeetouch.egeetouch_manager.R.layout.testing_layout_settings, r24, false);
        r22.rootView = r1;
        android.widget.Button r1 = (android.widget.Button) r1.findViewById(com.egeetouch.egeetouch_manager.R.id.change_pad_password);
        r22.btn_managePasscodes = (android.widget.Button) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.button_manage_passcode);
        r22.btn_manageTags = (android.widget.Button) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.button_manage_tag);
        android.widget.Button r3 = (android.widget.Button) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.button_dfu);
        android.widget.LinearLayout r3 = (android.widget.LinearLayout) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.ll_adv_time);
        if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") && !com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550") && !com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT3100") && !com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2002") && (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") || java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() <= 1.8d)) {
            r3.setVisibility(8);
        }
        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT3002") && com.egeetouch.egeetouch_manager.BLEService.selected_lock_version.equals(com.pnikosis.materialishprogress.BuildConfig.VERSION_NAME)) {
            r1.setVisibility(0);
        } else {
            r1.setVisibility(8);
        }
        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5100") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5107") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5109") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5300")) {
            r22.btn_manageTags.setVisibility(8);
            r22.btn_managePasscodes.setVisibility(0);
        } else {
            r22.btn_manageTags.setVisibility(0);
            r22.btn_managePasscodes.setVisibility(8);
        }
        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100")) {
            r19 = "GT5100";
            if (java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d) {
                r22.ll_lockInfo = (android.widget.LinearLayout) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.LinearLayout_lock_info);
                r22.ll_autoLocking = (android.widget.LinearLayout) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.ll_auto_locking);
                r22.ll_shackleBypass = (android.widget.LinearLayout) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.ll_shackle_bypass);
                r22.switch_AutoLocking = (android.widget.Switch) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.Switch_powerSaving_mode);
                r22.switch_ShackleBypass = (android.widget.Switch) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.Switch_shackleByPass);
                r22.tv_auditCount = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.tv_auditCount);
                r22.tv_tagCount = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.tv_tagCount);
                r22.tv_reconnectionTime = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.tv_lockReconnectionTime);
                android.widget.Button r3 = (android.widget.Button) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.bt_checkMasterTag);
                r22.ll_lockInfo.setVisibility(0);
                r22.ll_autoLocking.setVisibility(0);
                ((android.widget.Button) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.button_manage_user)).setVisibility(8);
                if (com.egeetouch.egeetouch_manager.BLEService.AutoLockingMode) {
                    r22.switch_AutoLocking.setChecked(false);
                    r3 = true;
                } else {
                    r3 = true;
                    r22.switch_AutoLocking.setChecked(true);
                }
                if (com.egeetouch.egeetouch_manager.BLEService.isShackleBypass_turnedOn) {
                    r22.switch_ShackleBypass.setChecked(r3);
                } else {
                    r22.switch_ShackleBypass.setChecked(false);
                }
                r22.tv_tagCount.setText(java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.AvailableTagCount));
                r22.tv_reconnectionTime.setText(com.egeetouch.egeetouch_manager.BLEService.LockReconnectingTime + r22.rootView.getResources().getString(com.egeetouch.egeetouch_manager.R.string.shutdown_sec));
                r22.tv_auditCount.setText(java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.AvailableAuditCount));
                r12 = r19;
                com.egeetouch.egeetouch_manager.MainActivity.lock_settings_status = true;
                com.egeetouch.egeetouch_manager.UI_BLE.add_lock_mode = false;
                com.egeetouch.egeetouch_manager.MainActivity.fab.setVisibility(4);
                com.egeetouch.egeetouch_manager.MainActivity.fab_share.setVisibility(4);
                com.egeetouch.egeetouch_manager.MainActivity.fab_admin_access_locks.setVisibility(8);
                com.egeetouch.egeetouch_manager.MainActivity.pullToRefresh.setEnabled(false);
                r1.setOnClickListener(new android.view.View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_lock_setting.2
                    {
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(android.view.View r3) {
                        java.lang.System.out.println("Hello btn_change_pad_pass clicked");
                        com.egeetouch.egeetouch_manager.BLEService.Response_old_passcode_boolean = true;
                        com.egeetouch.egeetouch_manager.Fragment_lock_setting.this.startActivity(new android.content.Intent(r3.getContext(), com.egeetouch.egeetouch_manager.activity_change_pad_password.class));
                    }
                });
                setHasOptionsMenu(true);
                com.egeetouch.egeetouch_manager.MainActivity.current_icon = 5;
                ((androidx.appcompat.app.AppCompatActivity) getActivity()).getSupportActionBar().setTitle(com.egeetouch.egeetouch_manager.R.string.setting);
                if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_role == 1) {
                    ((android.widget.ImageView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.imageView_proximity)).setAlpha(75);
                    ((android.widget.ImageView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.imageView_audit)).setAlpha(75);
                }
                ((android.widget.LinearLayout) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.LinearLayout_setting_defocus_editbox)).setOnTouchListener(new android.view.View.OnTouchListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_lock_setting.3
                    {
                    }

                    @Override // android.view.View.OnTouchListener
                    public boolean onTouch(android.view.View r2, android.view.MotionEvent r3) {
                        if (r3.getAction() == 1) {
                            com.egeetouch.egeetouch_manager.Fragment_lock_setting.this.hideKeyboard(r2);
                            return true;
                        }
                        return false;
                    }
                });
                if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2002") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2003") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals(r12) || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5107") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5109") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5300") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                    r1 = (android.widget.ImageView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.imageView_proximity);
                    if (r1 != null) {
                        r1.setVisibility(8);
                    }
                } else {
                    if ((com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT1000") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT3100")) && (r1 = (android.widget.ImageView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.imageView_proximity)) != null) {
                        r1.setVisibility(0);
                    }
                    java.lang.System.out.println("HEY YOU SHOULD NOT BE SEEING THIS");
                }
                android.widget.TextView r1 = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.textView1);
                android.widget.TextView r2 = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.textView2);
                if (!com.egeetouch.egeetouch_manager.Helper_PhoneDetails.phonelangauge().equals("ja")) {
                    r1.setTextSize(13.0f);
                    r2.setTextSize(13.0f);
                } else {
                    r1.setTextSize(16.0f);
                    r2.setTextSize(16.0f);
                }
                android.widget.Switch r1 = (android.widget.Switch) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.Switch_unlocking);
                android.widget.Switch r2 = (android.widget.Switch) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.switch_proximity);
                android.widget.Switch r3 = (android.widget.Switch) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.Switch_auto_locking);
                android.widget.Switch r7 = (android.widget.Switch) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.Switch_auto_unlocking);
                r13 = android.preference.PreferenceManager.getDefaultSharedPreferences(getActivity());
                if (!r13.getBoolean("unlocking_msg_show_boolean", true)) {
                    r1.setChecked(true);
                    r4 = false;
                } else {
                    r4 = false;
                    r1.setChecked(false);
                }
                if (!r13.getBoolean("proximity_msg_box_boolean", true)) {
                    r2.setChecked(true);
                } else {
                    r2.setChecked(r4);
                }
                if (!r13.getBoolean("auto_locking_boolean", r4)) {
                    r3.setChecked(true);
                } else {
                    r3.setChecked(r4);
                }
                if (!r13.getBoolean("auto_unlocking_boolean", r4)) {
                    r7.setChecked(true);
                } else {
                    r7.setChecked(r4);
                }
                android.widget.EditText r1 = (android.widget.EditText) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.editText_new_name);
                r1.setFocusableInTouchMode(true);
                r1.requestFocus();
                com.egeetouch.egeetouch_manager.Fragment_lock_setting.disconnected_trigger = r4;
                android.os.Handler r1 = new android.os.Handler();
                r22.manage_setting_handler = r1;
                r1.post(r22.r);
                android.widget.EditText r1 = (android.widget.EditText) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.editText_new_name);
                r1.setHint(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name);
                r1.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_lock_setting.4
                    {
                    }

                    @Override // android.view.View.OnFocusChangeListener
                    public void onFocusChange(android.view.View r1, boolean r2) {
                        if (r2) {
                            return;
                        }
                        com.egeetouch.egeetouch_manager.Fragment_lock_setting.this.hideKeyboard(r1);
                    }
                });
                ((android.widget.EditText) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.editText_new_primary_password)).setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_lock_setting.5
                    {
                    }

                    @Override // android.view.View.OnFocusChangeListener
                    public void onFocusChange(android.view.View r1, boolean r2) {
                        if (r2) {
                            return;
                        }
                        com.egeetouch.egeetouch_manager.Fragment_lock_setting.this.hideKeyboard(r1);
                    }
                });
                ((android.widget.EditText) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.editText_new_primary_password2)).setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_lock_setting.6
                    {
                    }

                    @Override // android.view.View.OnFocusChangeListener
                    public void onFocusChange(android.view.View r1, boolean r2) {
                        if (r2) {
                            return;
                        }
                        com.egeetouch.egeetouch_manager.Fragment_lock_setting.this.hideKeyboard(r1);
                    }
                });
                r1 = r13.getInt("shutdown_time", 180000);
                android.widget.TextView r2 = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.textView_standby);
                if (r1 != 60000) {
                    r2.setText(getResources().getString(com.egeetouch.egeetouch_manager.R.string.advertising_time_1));
                } else if (r1 == 120000) {
                    r2.setText(getResources().getString(com.egeetouch.egeetouch_manager.R.string.advertising_time_2));
                } else if (r1 == 150000) {
                    r2.setText(getResources().getString(com.egeetouch.egeetouch_manager.R.string.never));
                } else if (r1 == 180000) {
                    r2.setText(getResources().getString(com.egeetouch.egeetouch_manager.R.string.advertising_time_3min));
                } else if (r1 == 300000) {
                    r2.setText(getResources().getString(com.egeetouch.egeetouch_manager.R.string.advertising_time_5));
                } else if (r1 == 900000) {
                    r2.setText(getResources().getString(com.egeetouch.egeetouch_manager.R.string.advertising_time_15min));
                }
                android.widget.TextView r1 = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.textView_advertising);
                if ((com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d) || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals(r12) || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5107") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5109") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5300") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                    if (com.egeetouch.egeetouch_manager.BLEService.LockReconnectingTime == 10) {
                        r1.setText(getResources().getString(com.egeetouch.egeetouch_manager.R.string.auto_reconnecting_10s));
                    } else if (com.egeetouch.egeetouch_manager.BLEService.LockReconnectingTime == 20) {
                        r1.setText(getResources().getString(com.egeetouch.egeetouch_manager.R.string.auto_reconnecting_20s));
                    } else if (com.egeetouch.egeetouch_manager.BLEService.LockReconnectingTime == 30) {
                        r1.setText(getResources().getString(com.egeetouch.egeetouch_manager.R.string.advertising_time_30));
                    } else if (com.egeetouch.egeetouch_manager.BLEService.LockReconnectingTime == 60) {
                        r1.setText(getResources().getString(com.egeetouch.egeetouch_manager.R.string.advertising_time_1));
                    } else if (com.egeetouch.egeetouch_manager.BLEService.LockReconnectingTime == 180) {
                        r1.setText(getResources().getString(com.egeetouch.egeetouch_manager.R.string.advertising_time_3));
                    } else {
                        r1.setText(getResources().getString(com.egeetouch.egeetouch_manager.R.string.auto_reconnecting_20s));
                    }
                }
                r1 = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.textView_model);
                if (r1 != null) {
                    if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT1000")) {
                        r1.setText(r22.rootView.getResources().getString(com.egeetouch.egeetouch_manager.R.string.smart_NFC_travel_padlock));
                    } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2000")) {
                        r1.setText(r22.rootView.getResources().getString(com.egeetouch.egeetouch_manager.R.string.smart_NFC_padlock));
                    } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2002")) {
                        r1.setText(r22.rootView.getResources().getString(com.egeetouch.egeetouch_manager.R.string.smart_BT_NFC_padlock));
                    } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2003")) {
                        r1.setText(r22.rootView.getResources().getString(com.egeetouch.egeetouch_manager.R.string.ip65_waterproof));
                    } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT3000")) {
                        r1.setText(r22.rootView.getResources().getString(com.egeetouch.egeetouch_manager.R.string.smart_luggage_zipper_lock_embossed));
                    } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT3100") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT3002")) {
                        r1.setText(r22.rootView.getResources().getString(com.egeetouch.egeetouch_manager.R.string.smart_luggage_zipper_lock_debossed));
                    } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100")) {
                        r1.setText(r22.rootView.getResources().getString(com.egeetouch.egeetouch_manager.R.string.smart_ip45_weatherproof_padlock));
                    } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals(r12) || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5109") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5300")) {
                        r1.setText(r22.rootView.getResources().getString(com.egeetouch.egeetouch_manager.R.string.smart_mini_padlock));
                    } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5107")) {
                        r1.setText(r22.rootView.getResources().getString(com.egeetouch.egeetouch_manager.R.string.smart_lock_out_tag_out));
                    } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500")) {
                        r1.setText(r22.rootView.getResources().getString(com.egeetouch.egeetouch_manager.R.string.ip66_5th_gen));
                    } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                        r1.setText(r22.rootView.getResources().getString(com.egeetouch.egeetouch_manager.R.string.smart_anticut));
                    }
                }
                r1 = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.textView_firmware_v);
                if (r1 != null) {
                    r1.setText(java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version));
                }
                return r22.rootView;
            }
        } else {
            r19 = "GT5100";
        }
        r12 = r19;
        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals(r12) || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5107") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5109") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5300") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
            r22.ll_lockInfo = (android.widget.LinearLayout) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.LinearLayout_lock_info);
            r22.ll_autoLocking = (android.widget.LinearLayout) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.ll_auto_locking);
            r22.ll_shackleBypass = (android.widget.LinearLayout) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.ll_shackle_bypass);
            r22.switch_AutoLocking = (android.widget.Switch) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.Switch_powerSaving_mode);
            r22.switch_ShackleBypass = (android.widget.Switch) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.Switch_shackleByPass);
            r22.tv_auditCount = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.tv_auditCount);
            r22.tv_auditCountTitle = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.tv_audit_count1);
            r22.tv_tagCount = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.tv_tagCount);
            r22.tv_tagCountTitle = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.tv_tagCount1);
            r22.tv_reconnectionTime = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.tv_lockReconnectionTime);
            android.widget.Button r3 = (android.widget.Button) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.bt_checkMasterTag);
            r22.ll_lockInfo.setVisibility(0);
            r22.ll_autoLocking.setVisibility(0);
            ((android.widget.Button) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.button_manage_user)).setVisibility(8);
            if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                r22.tv_auditCountTitle.setText(com.egeetouch.egeetouch_manager.R.string.total_no_of_tag_audit_logs_in_lock);
                r22.tv_tagCountTitle.setText(com.egeetouch.egeetouch_manager.R.string.total_no_of_tags_in_lock);
            } else {
                r22.tv_auditCountTitle.setText(com.egeetouch.egeetouch_manager.R.string.total_no_of_passcode_audit_logs_in_lock);
                r22.tv_tagCountTitle.setText(com.egeetouch.egeetouch_manager.R.string.total_no_of_passcode_in_lock);
            }
            if (com.egeetouch.egeetouch_manager.BLEService.AutoLockingMode) {
                r3 = false;
                r22.switch_AutoLocking.setChecked(false);
                r7 = true;
            } else {
                r3 = false;
                r7 = true;
                r22.switch_AutoLocking.setChecked(true);
            }
            if (com.egeetouch.egeetouch_manager.BLEService.isShackleBypass_turnedOn) {
                r22.switch_ShackleBypass.setChecked(r7);
            } else {
                r22.switch_ShackleBypass.setChecked(r3);
            }
            r22.tv_tagCount.setText(java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.AvailableTagCount));
            r22.tv_reconnectionTime.setText(com.egeetouch.egeetouch_manager.BLEService.LockReconnectingTime + r22.rootView.getResources().getString(com.egeetouch.egeetouch_manager.R.string.shutdown_sec));
            r22.tv_auditCount.setText(java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.AvailableAuditCount));
        }
        com.egeetouch.egeetouch_manager.MainActivity.lock_settings_status = true;
        com.egeetouch.egeetouch_manager.UI_BLE.add_lock_mode = false;
        com.egeetouch.egeetouch_manager.MainActivity.fab.setVisibility(4);
        com.egeetouch.egeetouch_manager.MainActivity.fab_share.setVisibility(4);
        com.egeetouch.egeetouch_manager.MainActivity.fab_admin_access_locks.setVisibility(8);
        com.egeetouch.egeetouch_manager.MainActivity.pullToRefresh.setEnabled(false);
        r1.setOnClickListener(new android.view.View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_lock_setting.2
            {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(android.view.View r3) {
                java.lang.System.out.println("Hello btn_change_pad_pass clicked");
                com.egeetouch.egeetouch_manager.BLEService.Response_old_passcode_boolean = true;
                com.egeetouch.egeetouch_manager.Fragment_lock_setting.this.startActivity(new android.content.Intent(r3.getContext(), com.egeetouch.egeetouch_manager.activity_change_pad_password.class));
            }
        });
        setHasOptionsMenu(true);
        com.egeetouch.egeetouch_manager.MainActivity.current_icon = 5;
        ((androidx.appcompat.app.AppCompatActivity) getActivity()).getSupportActionBar().setTitle(com.egeetouch.egeetouch_manager.R.string.setting);
        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_role == 1) {
        }
        ((android.widget.LinearLayout) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.LinearLayout_setting_defocus_editbox)).setOnTouchListener(new android.view.View.OnTouchListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_lock_setting.3
            {
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(android.view.View r2, android.view.MotionEvent r3) {
                if (r3.getAction() == 1) {
                    com.egeetouch.egeetouch_manager.Fragment_lock_setting.this.hideKeyboard(r2);
                    return true;
                }
                return false;
            }
        });
        if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2002")) {
        }
        r1 = (android.widget.ImageView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.imageView_proximity);
        if (r1 != null) {
        }
        android.widget.TextView r1 = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.textView1);
        android.widget.TextView r2 = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.textView2);
        if (!com.egeetouch.egeetouch_manager.Helper_PhoneDetails.phonelangauge().equals("ja")) {
        }
        android.widget.Switch r1 = (android.widget.Switch) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.Switch_unlocking);
        android.widget.Switch r2 = (android.widget.Switch) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.switch_proximity);
        android.widget.Switch r3 = (android.widget.Switch) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.Switch_auto_locking);
        android.widget.Switch r7 = (android.widget.Switch) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.Switch_auto_unlocking);
        r13 = android.preference.PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (!r13.getBoolean("unlocking_msg_show_boolean", true)) {
        }
        if (!r13.getBoolean("proximity_msg_box_boolean", true)) {
        }
        if (!r13.getBoolean("auto_locking_boolean", r4)) {
        }
        if (!r13.getBoolean("auto_unlocking_boolean", r4)) {
        }
        android.widget.EditText r1 = (android.widget.EditText) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.editText_new_name);
        r1.setFocusableInTouchMode(true);
        r1.requestFocus();
        com.egeetouch.egeetouch_manager.Fragment_lock_setting.disconnected_trigger = r4;
        android.os.Handler r1 = new android.os.Handler();
        r22.manage_setting_handler = r1;
        r1.post(r22.r);
        android.widget.EditText r1 = (android.widget.EditText) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.editText_new_name);
        r1.setHint(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name);
        r1.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_lock_setting.4
            {
            }

            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(android.view.View r1, boolean r2) {
                if (r2) {
                    return;
                }
                com.egeetouch.egeetouch_manager.Fragment_lock_setting.this.hideKeyboard(r1);
            }
        });
        ((android.widget.EditText) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.editText_new_primary_password)).setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_lock_setting.5
            {
            }

            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(android.view.View r1, boolean r2) {
                if (r2) {
                    return;
                }
                com.egeetouch.egeetouch_manager.Fragment_lock_setting.this.hideKeyboard(r1);
            }
        });
        ((android.widget.EditText) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.editText_new_primary_password2)).setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_lock_setting.6
            {
            }

            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(android.view.View r1, boolean r2) {
                if (r2) {
                    return;
                }
                com.egeetouch.egeetouch_manager.Fragment_lock_setting.this.hideKeyboard(r1);
            }
        });
        r1 = r13.getInt("shutdown_time", 180000);
        android.widget.TextView r2 = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.textView_standby);
        if (r1 != 60000) {
        }
        android.widget.TextView r1 = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.textView_advertising);
        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100")) {
            if (com.egeetouch.egeetouch_manager.BLEService.LockReconnectingTime == 10) {
            }
            r1 = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.textView_model);
            if (r1 != null) {
            }
            r1 = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.textView_firmware_v);
            if (r1 != null) {
            }
            return r22.rootView;
        }
        if (com.egeetouch.egeetouch_manager.BLEService.LockReconnectingTime == 10) {
        }
        r1 = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.textView_model);
        if (r1 != null) {
        }
        r1 = (android.widget.TextView) r22.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.textView_firmware_v);
        if (r1 != null) {
        }
        return r22.rootView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void icon_display(Boolean bool) {
        ImageView imageView = (ImageView) this.rootView.findViewById(R.id.imageView_locking);
        ImageView imageView2 = (ImageView) this.rootView.findViewById(R.id.imageView_proximity);
        ImageView imageView3 = (ImageView) this.rootView.findViewById(R.id.imageView_audit);
        ImageView imageView4 = (ImageView) this.rootView.findViewById(R.id.imageView_watch);
        ImageView imageView5 = (ImageView) this.rootView.findViewById(R.id.imageView_setting);
        if (!bool.booleanValue()) {
            if (imageView != null) {
                imageView.setAlpha(75);
                imageView.setClickable(false);
            }
            if (imageView2 != null) {
                imageView2.setAlpha(75);
                imageView2.setClickable(false);
            }
            if (imageView4 != null) {
                imageView4.setAlpha(75);
                imageView4.setClickable(false);
            }
            if (imageView3 != null) {
                imageView3.setAlpha(75);
                imageView3.setClickable(false);
            }
            if (imageView5 != null) {
                imageView5.setAlpha(75);
                imageView5.setClickable(false);
                return;
            }
            return;
        }
        if (imageView != null) {
            imageView.setAlpha(255);
            imageView.setClickable(true);
        }
        if (imageView2 != null) {
            if (BLEService.selected_lock_role == 1) {
                imageView2.setAlpha(75);
            } else {
                imageView2.setAlpha(255);
            }
            imageView2.setClickable(true);
        }
        if (imageView4 != null) {
            if (BLEService.selected_lock_role == 1) {
                imageView4.setAlpha(75);
            } else {
                imageView4.setAlpha(255);
            }
            imageView4.setClickable(true);
        }
        if (imageView3 != null) {
            if (BLEService.selected_lock_role == 1) {
                imageView3.setAlpha(75);
            } else {
                imageView3.setAlpha(255);
            }
            imageView3.setClickable(true);
        }
        if (imageView5 != null) {
            imageView5.setAlpha(255);
            imageView5.setClickable(true);
        }
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
