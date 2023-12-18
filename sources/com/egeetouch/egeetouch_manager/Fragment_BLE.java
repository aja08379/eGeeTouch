package com.egeetouch.egeetouch_manager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.facebook.appevents.codeless.internal.Constants;
import com.google.android.material.timepicker.TimeModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class Fragment_BLE extends Fragment {
    public static int Ble_Mode = 0;
    static Button Bt_switchToNfcMode = null;
    static Button TV_autoShutdownCounter = null;
    static RotateAnimation aAnim = null;
    static RotateAnimation aAnim3 = null;
    private static int acc_time = 0;
    public static final int admin = 2;
    static TranslateAnimation animation = null;
    private static int[] average_battery = new int[10];
    private static boolean battery_abnormal_msg_show = false;
    public static int battery_counter = 0;
    public static int battery_counter_init = 0;
    private static int battery_sampling_count = 1;
    public static boolean count_down_started = false;
    public static int current_zone = 1;
    private static boolean dialog_battery_show = false;
    private static boolean dialog_battery_show_3mins = false;
    private static boolean dialog_disconnected_show = false;
    public static boolean disconnected_trigger = false;
    private static int error_counter = 0;
    public static int get_battery_level_counter = 0;
    public static boolean get_init_battery_level_done = false;
    private static boolean hide_icon_low_power = false;
    public static CountDownTimer idle_count_down = null;
    static ImageView imageView_in_sync = null;
    private static float last_position = 0.0f;
    static LinearLayout ll_audit_notif = null;
    static LinearLayout ll_autoshutdown_timer = null;
    static ImageView lock_button = null;
    static ImageView lock_round_button = null;
    private static int low_batt_count = 0;
    public static int mapped_battery_level = 0;
    private static int mapped_battery_value = 0;
    private static int move_counter = 0;
    public static int next_target_zone = 1;
    private static boolean previous_selected_lock_state = true;
    static ProgressBar progressBarCounter = null;
    public static int proximity_animation_buffer_count = 21;
    private static boolean proximity_msg_show = false;
    private static SweetAlertDialog reconnect_msg = null;
    public static boolean reconnect_msg_show = false;
    static View rootView = null;
    private static float screensize = 2.0f;
    public static boolean sensitivity_low = true;
    public static int shutdown_time = 15000;
    public static boolean shutdown_trigger = false;
    private static boolean toggle = false;
    static TextView tv_audit_notif = null;
    static TextView tv_autoShutdown = null;
    static TextView tv_displayMin = null;
    static TextView tv_displaySec = null;
    static TextView tv_min = null;
    static TextView tv_sec = null;
    static ImageView unlock_button = null;
    static ImageView unlock_round_button = null;
    public static final int user = 1;
    private static boolean user_msg_show = false;
    ImageView img_animation;
    private Menu menu;
    MediaPlayer mp;
    Handler handler = null;
    private int scale = 0;
    private int previous_mapped_battery_level = 0;
    final Runnable r = new Runnable() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.3
        @Override // java.lang.Runnable
        public void run() {
            Fragment_BLE.this.UI_battery_level();
            if (!BLEService.vicinity_on) {
                Fragment_BLE.this.getActivity().setTitle(Fragment_BLE.rootView.getResources().getString(R.string.lock_access));
                RelativeLayout relativeLayout = (RelativeLayout) Fragment_BLE.rootView.findViewById(R.id.RelativeLayout_buttons);
                if (relativeLayout != null) {
                    relativeLayout.setVisibility(0);
                }
                LinearLayout linearLayout = (LinearLayout) Fragment_BLE.rootView.findViewById(R.id.LinearLayout_vicinity);
                if (linearLayout != null) {
                    linearLayout.setVisibility(8);
                }
            } else {
                Fragment_BLE.this.getActivity().setTitle(Fragment_BLE.rootView.getResources().getString(R.string.vicinity_tracking));
                RelativeLayout relativeLayout2 = (RelativeLayout) Fragment_BLE.rootView.findViewById(R.id.RelativeLayout_buttons);
                if (relativeLayout2 != null) {
                    relativeLayout2.setVisibility(8);
                }
                LinearLayout linearLayout2 = (LinearLayout) Fragment_BLE.rootView.findViewById(R.id.LinearLayout_vicinity);
                if (linearLayout2 != null) {
                    linearLayout2.setVisibility(0);
                }
            }
            if (BLEService.mConnectionState == 0) {
                Fragment_BLE.this.icon_display(false);
                Fragment_BLE.imageView_in_sync = (ImageView) Fragment_BLE.rootView.findViewById(R.id.imageView_in_sync);
                Fragment_BLE.imageView_in_sync.setImageResource(R.drawable.bluetooth_disconnect);
                Fragment_BLE.stop_count_down_timer();
                if (!Fragment_BLE.reconnect_msg_show && !Fragment_BLE.disconnected_trigger && !Fragment_BLE.shutdown_trigger) {
                    Fragment_BLE.reconnect_msg_show = true;
                    SweetAlertDialog unused = Fragment_BLE.reconnect_msg = new SweetAlertDialog(MainActivity.context, 5);
                    Fragment_BLE.reconnect_msg.setTitleText(MainActivity.context.getResources().getString(R.string.lost_connection));
                    Fragment_BLE.reconnect_msg.setContentText(MainActivity.context.getResources().getString(R.string.move_nearer));
                    Fragment_BLE.reconnect_msg.setCancelText(MainActivity.context.getResources().getString(R.string.cancel));
                    Fragment_BLE.reconnect_msg.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.3.1
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            BLEService.cancel_scaning_triggered = true;
                            BLEService.Ble_Mode = BLEService.disconnect;
                        }
                    });
                    Fragment_BLE.reconnect_msg.setCancelable(false);
                    Fragment_BLE.reconnect_msg.show();
                    Log.i("BLE", "reconnect_msg is shown");
                }
            } else {
                if (Fragment_BLE.reconnect_msg_show && Fragment_BLE.reconnect_msg != null && Fragment_BLE.reconnect_msg.isShowing()) {
                    Fragment_BLE.reconnect_msg.dismiss();
                    Fragment_BLE.reconnect_msg_show = false;
                }
                Fragment_BLE.this.icon_display(true);
                Fragment_BLE.imageView_in_sync = (ImageView) Fragment_BLE.rootView.findViewById(R.id.imageView_in_sync);
                Fragment_BLE.imageView_in_sync.setImageResource(R.drawable.bluetooth_connected);
                if (BLEService.selected_lock_role == 1 && !Fragment_BLE.user_msg_show) {
                    boolean unused2 = Fragment_BLE.user_msg_show = true;
                    new SweetAlertDialog(MainActivity.context, 3).setTitleText(MainActivity.context.getResources().getString(R.string.user_logged_in)).setContentText(MainActivity.context.getResources().getString(R.string.non_admin_user_detected)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                }
                Fragment_BLE.this.battery_display_algorithm_with_reset();
                if (!BLEService.vicinity_on || (BLEService.selected_lock_model.equals("GT2002") && BLEService.selected_lock_model.equals("GT2100"))) {
                    Fragment_BLE.this.UI_lock_status_display();
                    if (!Fragment_BLE.count_down_started) {
                        if (Fragment_BLE.idle_count_down != null) {
                            Fragment_BLE.idle_count_down.cancel();
                        }
                        if (Fragment_BLE.shutdown_time != 150000) {
                            Fragment_BLE.count_down_routine();
                        }
                    }
                } else {
                    Fragment_BLE.this.UI_vicinity();
                }
            }
            if (BLEService.AvailableAuditCount > 0) {
                if (Fragment_BLE.ll_audit_notif.getVisibility() == 8) {
                    Fragment_BLE.ll_audit_notif.setVisibility(0);
                }
                SpannableString spannableString = new SpannableString(Fragment_BLE.this.getString(R.string.headsup_text, String.valueOf(BLEService.AvailableAuditCount)));
                spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
                Fragment_BLE.tv_audit_notif.setText(spannableString);
            } else {
                Fragment_BLE.ll_audit_notif.setVisibility(8);
            }
            Fragment_BLE.this.handler.postDelayed(this, 100L);
        }
    };

    private void battery_display_algorithm_timing() {
    }

    public static Fragment_BLE newInstance() {
        return new Fragment_BLE();
    }

    public static String getReverseGeoLocationString(double d, double d2) throws IOException {
        List<Address> fromLocation = new Geocoder(MainActivity.context, Locale.getDefault()).getFromLocation(d, d2, 1);
        String addressLine = fromLocation.get(0).getAddressLine(0);
        fromLocation.get(0).getLocality();
        fromLocation.get(0).getAdminArea();
        fromLocation.get(0).getCountryName();
        String postalCode = fromLocation.get(0).getPostalCode();
        fromLocation.get(0).getFeatureName();
        return addressLine + " " + postalCode;
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (BLEService.vicinity_on) {
            supportActionBar.setTitle(R.string.vicinity_tracking);
        } else {
            supportActionBar.setTitle(R.string.lock_access);
        }
        if (MainActivity.current_icon == 1) {
            count_down_started = false;
        }
        this.handler.postDelayed(this.r, 100L);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        Log.i("BLE", "onPause");
        if (MainActivity.current_icon != 1) {
            stop_count_down_timer();
        }
        this.handler.removeCallbacks(this.r);
        ImageView imageView = this.img_animation;
        if (imageView == null || !imageView.isShown()) {
            return;
        }
        this.img_animation.clearAnimation();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        if (MainActivity.current_icon != 1) {
            stop_count_down_timer();
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (MainActivity.current_icon != 1) {
            stop_count_down_timer();
        }
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.global, menu);
        MainActivity.shutdown_status = 1;
    }

    @Override // android.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_button_disconnect) {
            new SweetAlertDialog(getActivity()).setTitleText(getString(R.string.power_off)).setContentText(getString(R.string.are_you_sure_power_off_lock)).setConfirmText(getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.1
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                    Fragment_BLE.disconnected_trigger = true;
                    BLEService.cancel_scaning_triggered = true;
                    BLEService.Ble_Mode = BLEService.Send_timeStamp;
                    BLEService.disconnect_triggered = true;
                    MainActivity.isUserClickedShutdown = true;
                    MainActivity.shutdown_dialog = new SweetAlertDialog(MainActivity.context, 5);
                    MainActivity.shutdown_dialog.setTitleText(Fragment_BLE.this.getString(R.string.power_off));
                    MainActivity.shutdown_dialog.setCancelable(false);
                    MainActivity.shutdown_dialog.setCanceledOnTouchOutside(false);
                    MainActivity.shutdown_dialog.show();
                    MainActivity.stopBackStack = false;
                    ((AppCompatActivity) Fragment_BLE.this.getActivity()).getSupportActionBar().setTitle(R.string.dashboard);
                    MediaPlayer create = MediaPlayer.create(Fragment_BLE.this.getActivity(), (int) R.raw.disconnectinapp);
                    create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.1.1
                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });
                    create.start();
                    Fragment_BLE.stop_count_down_timer();
                }
            }).setCancelText(getString(R.string.no)).show();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x018d  */
    @Override // android.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View onCreateView(android.view.LayoutInflater r6, android.view.ViewGroup r7, android.os.Bundle r8) {
        com.egeetouch.egeetouch_manager.Fragment_BLE.rootView = r6.inflate(com.egeetouch.egeetouch_manager.R.layout.testing_layout_ble2, r7, false);
        com.egeetouch.egeetouch_manager.MainActivity.lock_settings_status = false;
        setHasOptionsMenu(true);
        com.egeetouch.egeetouch_manager.MainActivity.fab.setVisibility(4);
        com.egeetouch.egeetouch_manager.MainActivity.fab_share.setVisibility(4);
        com.egeetouch.egeetouch_manager.MainActivity.fab_admin_access_locks.setVisibility(8);
        if (com.egeetouch.egeetouch_manager.MainActivity.dashboard_listview != null) {
            com.egeetouch.egeetouch_manager.MainActivity.dashboard_listview.setVisibility(4);
        }
        com.egeetouch.egeetouch_manager.MainActivity.pullToRefresh.setEnabled(false);
        com.egeetouch.egeetouch_manager.MainActivity.stopBackStack = true;
        androidx.appcompat.app.ActionBar r7 = ((androidx.appcompat.app.AppCompatActivity) getActivity()).getSupportActionBar();
        com.egeetouch.egeetouch_manager.Fragment_BLE.ll_autoshutdown_timer = (android.widget.LinearLayout) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.ll_autoShutdownTimer);
        com.egeetouch.egeetouch_manager.Fragment_BLE.ll_audit_notif = (android.widget.LinearLayout) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.ll_audit_notif);
        com.egeetouch.egeetouch_manager.Fragment_BLE.tv_displayMin = (android.widget.TextView) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.tv_displayMin);
        com.egeetouch.egeetouch_manager.Fragment_BLE.tv_displaySec = (android.widget.TextView) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.tv_displaySec);
        com.egeetouch.egeetouch_manager.Fragment_BLE.tv_min = (android.widget.TextView) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.tv_textMin);
        com.egeetouch.egeetouch_manager.Fragment_BLE.tv_sec = (android.widget.TextView) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.tv_textSec);
        com.egeetouch.egeetouch_manager.Fragment_BLE.tv_autoShutdown = (android.widget.TextView) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.tv_autoShutDown);
        com.egeetouch.egeetouch_manager.Fragment_BLE.tv_audit_notif = (android.widget.TextView) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.tv_audit_notif);
        com.egeetouch.egeetouch_manager.Fragment_BLE.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_masterTag_only;
        com.egeetouch.egeetouch_manager.BLEService.first_battery_check = true;
        if (com.egeetouch.egeetouch_manager.BLEService.vicinity_on) {
            r7.setTitle(com.egeetouch.egeetouch_manager.R.string.vicinity_tracking);
        } else {
            r7.setTitle(com.egeetouch.egeetouch_manager.R.string.lock_access);
        }
        com.egeetouch.egeetouch_manager.MainActivity.current_icon = 1;
        com.egeetouch.egeetouch_manager.Fragment_BLE.screensize = getResources().getDisplayMetrics().density;
        UI_lock_name();
        UI_battery_level();
        UI_button_alpha();
        UI_count_down_timer();
        com.egeetouch.egeetouch_manager.Fragment_BLE.disconnected_trigger = false;
        com.egeetouch.egeetouch_manager.Fragment_BLE.shutdown_trigger = false;
        android.os.Handler r7 = new android.os.Handler();
        r5.handler = r7;
        r7.post(r5.r);
        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_role == 1) {
            ((android.widget.ImageView) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.imageView_proximity)).setAlpha(75);
            ((android.widget.ImageView) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.imageView_audit)).setAlpha(75);
        }
        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2002") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2003") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100")) {
            ((android.widget.ImageView) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.imageView_proximity)).setVisibility(8);
        } else {
            if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT1000") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT3100") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT3002")) {
                ((android.widget.ImageView) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.imageView_proximity)).setVisibility(0);
            }
            if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2002")) {
                com.egeetouch.egeetouch_manager.Fragment_BLE.lock_button.setImageResource(com.egeetouch.egeetouch_manager.R.drawable.unlock_green_gt2002);
            } else {
                com.egeetouch.egeetouch_manager.Fragment_BLE.lock_button.setImageResource(com.egeetouch.egeetouch_manager.R.drawable.unlock_green);
            }
            if (com.egeetouch.egeetouch_manager.MainActivity.current_icon == 1) {
                com.egeetouch.egeetouch_manager.Fragment_BLE.count_down_started = false;
            }
            android.widget.ImageView r7 = (android.widget.ImageView) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.imageView_banner);
            if (!com.egeetouch.egeetouch_manager.MainActivity.selected_lock_is_shared) {
                r7.setVisibility(0);
            } else {
                r7.setVisibility(8);
            }
            if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550") || (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d)) {
                android.widget.Button r6 = (android.widget.Button) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.bt_nfcMode);
                com.egeetouch.egeetouch_manager.Fragment_BLE.Bt_switchToNfcMode = r6;
                r6.setVisibility(0);
                com.egeetouch.egeetouch_manager.Fragment_BLE.Bt_switchToNfcMode.setOnClickListener(new android.view.View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.2
                    {
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(android.view.View r3) {
                        try {
                            new cn.pedant.SweetAlert.SweetAlertDialog(com.egeetouch.egeetouch_manager.MainActivity.context).setTitleText(com.egeetouch.egeetouch_manager.Fragment_BLE.this.getString(com.egeetouch.egeetouch_manager.R.string.nfc_mode)).setContentText(com.egeetouch.egeetouch_manager.Fragment_BLE.this.getString(com.egeetouch.egeetouch_manager.R.string.nfc_mode_desc)).setConfirmText(com.egeetouch.egeetouch_manager.Fragment_BLE.this.getString(com.egeetouch.egeetouch_manager.R.string.ok)).setConfirmClickListener(new cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.2.1
                                {
                                }

                                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                public void onClick(cn.pedant.SweetAlert.SweetAlertDialog r3) {
                                    try {
                                        r3.dismissWithAnimation();
                                        com.egeetouch.egeetouch_manager.MainActivity.isNFC_modeOn = true;
                                        com.egeetouch.egeetouch_manager.Fragment_BLE.disconnected_trigger = true;
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Send_timeStamp;
                                        com.egeetouch.egeetouch_manager.BLEService.disconnect_triggered = true;
                                        com.egeetouch.egeetouch_manager.MainActivity.turnedOnNFCState = true;
                                        com.egeetouch.egeetouch_manager.MainActivity.nfc_mode_dialog = new cn.pedant.SweetAlert.SweetAlertDialog(com.egeetouch.egeetouch_manager.MainActivity.context, 5);
                                        com.egeetouch.egeetouch_manager.MainActivity.nfc_mode_dialog.setTitleText(com.egeetouch.egeetouch_manager.Fragment_BLE.this.getString(com.egeetouch.egeetouch_manager.R.string.disconnect));
                                        com.egeetouch.egeetouch_manager.MainActivity.nfc_mode_dialog.setCancelable(false);
                                        com.egeetouch.egeetouch_manager.MainActivity.nfc_mode_dialog.setCanceledOnTouchOutside(false);
                                        com.egeetouch.egeetouch_manager.MainActivity.nfc_mode_dialog.show();
                                        com.egeetouch.egeetouch_manager.MainActivity.stopBackStack = false;
                                        com.egeetouch.egeetouch_manager.BLEService.selectedLockIP45Serial = "";
                                        android.media.MediaPlayer r3 = android.media.MediaPlayer.create(com.egeetouch.egeetouch_manager.MainActivity.context, (int) com.egeetouch.egeetouch_manager.R.raw.disconnectinapp);
                                        r3.setOnCompletionListener(new android.media.MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.2.1.1
                                            {
                                            }

                                            @Override // android.media.MediaPlayer.OnCompletionListener
                                            public void onCompletion(android.media.MediaPlayer r1) {
                                                r1.stop();
                                                r1.reset();
                                                r1.release();
                                            }
                                        });
                                        r3.start();
                                    } catch (java.lang.Exception r3) {
                                        r3.printStackTrace();
                                    }
                                }

                            }).setCancelText(com.egeetouch.egeetouch_manager.Fragment_BLE.this.getString(com.egeetouch.egeetouch_manager.R.string.no)).show();
                        } catch (java.lang.Exception r3) {
                            r3.printStackTrace();
                        }
                    }

                });
            }
            return com.egeetouch.egeetouch_manager.Fragment_BLE.rootView;
        }
        if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2002")) {
        }
        if (com.egeetouch.egeetouch_manager.MainActivity.current_icon == 1) {
        }
        android.widget.ImageView r7 = (android.widget.ImageView) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.imageView_banner);
        if (!com.egeetouch.egeetouch_manager.MainActivity.selected_lock_is_shared) {
        }
        if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500")) {
        }
        android.widget.Button r6 = (android.widget.Button) com.egeetouch.egeetouch_manager.Fragment_BLE.rootView.findViewById(com.egeetouch.egeetouch_manager.R.id.bt_nfcMode);
        com.egeetouch.egeetouch_manager.Fragment_BLE.Bt_switchToNfcMode = r6;
        r6.setVisibility(0);
        com.egeetouch.egeetouch_manager.Fragment_BLE.Bt_switchToNfcMode.setOnClickListener(new android.view.View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.2
            {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(android.view.View r3) {
                try {
                    new cn.pedant.SweetAlert.SweetAlertDialog(com.egeetouch.egeetouch_manager.MainActivity.context).setTitleText(com.egeetouch.egeetouch_manager.Fragment_BLE.this.getString(com.egeetouch.egeetouch_manager.R.string.nfc_mode)).setContentText(com.egeetouch.egeetouch_manager.Fragment_BLE.this.getString(com.egeetouch.egeetouch_manager.R.string.nfc_mode_desc)).setConfirmText(com.egeetouch.egeetouch_manager.Fragment_BLE.this.getString(com.egeetouch.egeetouch_manager.R.string.ok)).setConfirmClickListener(new cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.2.1
                        {
                        }

                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(cn.pedant.SweetAlert.SweetAlertDialog r3) {
                            try {
                                r3.dismissWithAnimation();
                                com.egeetouch.egeetouch_manager.MainActivity.isNFC_modeOn = true;
                                com.egeetouch.egeetouch_manager.Fragment_BLE.disconnected_trigger = true;
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Send_timeStamp;
                                com.egeetouch.egeetouch_manager.BLEService.disconnect_triggered = true;
                                com.egeetouch.egeetouch_manager.MainActivity.turnedOnNFCState = true;
                                com.egeetouch.egeetouch_manager.MainActivity.nfc_mode_dialog = new cn.pedant.SweetAlert.SweetAlertDialog(com.egeetouch.egeetouch_manager.MainActivity.context, 5);
                                com.egeetouch.egeetouch_manager.MainActivity.nfc_mode_dialog.setTitleText(com.egeetouch.egeetouch_manager.Fragment_BLE.this.getString(com.egeetouch.egeetouch_manager.R.string.disconnect));
                                com.egeetouch.egeetouch_manager.MainActivity.nfc_mode_dialog.setCancelable(false);
                                com.egeetouch.egeetouch_manager.MainActivity.nfc_mode_dialog.setCanceledOnTouchOutside(false);
                                com.egeetouch.egeetouch_manager.MainActivity.nfc_mode_dialog.show();
                                com.egeetouch.egeetouch_manager.MainActivity.stopBackStack = false;
                                com.egeetouch.egeetouch_manager.BLEService.selectedLockIP45Serial = "";
                                android.media.MediaPlayer r3 = android.media.MediaPlayer.create(com.egeetouch.egeetouch_manager.MainActivity.context, (int) com.egeetouch.egeetouch_manager.R.raw.disconnectinapp);
                                r3.setOnCompletionListener(new android.media.MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.2.1.1
                                    {
                                    }

                                    @Override // android.media.MediaPlayer.OnCompletionListener
                                    public void onCompletion(android.media.MediaPlayer r1) {
                                        r1.stop();
                                        r1.reset();
                                        r1.release();
                                    }
                                });
                                r3.start();
                            } catch (java.lang.Exception r3) {
                                r3.printStackTrace();
                            }
                        }

                    }).setCancelText(com.egeetouch.egeetouch_manager.Fragment_BLE.this.getString(com.egeetouch.egeetouch_manager.R.string.no)).show();
                } catch (java.lang.Exception r3) {
                    r3.printStackTrace();
                }
            }

        });
        return com.egeetouch.egeetouch_manager.Fragment_BLE.rootView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void UI_vicinity() {
        stop_count_down_timer();
        update_seek_bar();
        if (PreferenceManager.getDefaultSharedPreferences(getActivity()).getBoolean("proximity_msg_box_boolean", true) && !proximity_msg_show) {
            proximity_msg_show = true;
            new SweetAlertDialog(MainActivity.context, 0).setTitleText(MainActivity.context.getResources().getString(R.string.vicinity_tacking_activated)).setContentText(MainActivity.context.getResources().getString(R.string.egeetouch_stays_connected)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.5
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                    SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(Fragment_BLE.this.getActivity()).edit();
                    edit.putBoolean("proximity_msg_box_boolean", false);
                    edit.commit();
                }
            }).setCancelText(MainActivity.context.getResources().getString(R.string.learn_more)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.4
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                    View inflate = LayoutInflater.from(Fragment_BLE.this.getActivity()).inflate(R.layout.vicinity_learn_more, (ViewGroup) null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Fragment_BLE.this.getActivity());
                    builder.setView(inflate);
                    builder.setTitle(Fragment_BLE.rootView.getResources().getString(R.string.vicinity_tacking_activated));
                    builder.setMessage(Fragment_BLE.rootView.getResources().getString(R.string.to_optimize_tracking_distance)).setPositiveButton(Fragment_BLE.rootView.getResources().getString(R.string.ok), (DialogInterface.OnClickListener) null);
                    AlertDialog create = builder.create();
                    create.show();
                    Button button = create.getButton(-1);
                    if (button != null) {
                        button.setTextColor(Fragment_BLE.rootView.getResources().getColor(R.color.cyanblue));
                    }
                    TextView textView = (TextView) create.findViewById(16908299);
                    if (textView != null) {
                        textView.setTextSize(14.0f);
                        textView.setGravity(17);
                    }
                    TextView textView2 = (TextView) create.findViewById(Fragment_BLE.rootView.getResources().getIdentifier("alertTitle", "id", Constants.PLATFORM));
                    if (textView2 != null) {
                        textView2.setGravity(17);
                    }
                }
            }).show();
        }
        TextView textView = (TextView) rootView.findViewById(R.id.textView_Unmute);
        if (Helper_PhoneDetails.phonelangauge().equals("ja")) {
            textView.setTextSize(12.0f);
        }
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView_vibration_icon);
        if (BLEService.proximity_sound == 1) {
            textView.setText(getString(R.string.unmute));
            imageView.setImageResource(R.drawable.sound);
        } else if (BLEService.proximity_sound == 2) {
            textView.setText(getString(R.string.vibrate));
            imageView.setImageResource(R.drawable.vibrate);
        } else if (BLEService.proximity_sound == 3) {
            textView.setText(getString(R.string.mute));
            imageView.setImageResource(R.drawable.mute);
        }
        int i = proximity_animation_buffer_count;
        if (i > 10) {
            proximity_animation_buffer_count = 1;
            algorithm_rssi();
            float f = screensize;
            if (f >= 1.5d && f < 2.0f) {
                screen_hdpi();
                return;
            } else if (f >= 2.0f && f < 2.625d) {
                screen_xhdpi();
                return;
            } else if (f >= 2.625d && f < 3.5d) {
                screen_xxhdpi();
                return;
            } else if (f >= 4.0f) {
                screen_xhdpi();
                return;
            } else if (f >= 3.5d) {
                screen_xxxhdpi();
                return;
            } else {
                screen_xhdpi();
                return;
            }
        }
        proximity_animation_buffer_count = i + 1;
    }

    public static void shootLockStatusToFirebase(String str) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        if (!BLEService.parsedIp45SerialNumber.equals("")) {
            MainActivity.currentTimestampDouble = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() * 1000);
            DatabaseReference push = firebaseDatabase.getReference("AuditTrail").child(BLEService.parsedIp45SerialNumber).push();
            push.child("time").setValue(Double.valueOf(MainActivity.currentTimestampDouble));
            push.child("UIDOfUser").setValue(MainActivity.user_uid);
            push.child("userEmail").setValue(MainActivity.email);
            push.child("Latitude").setValue(Float.valueOf(BLEService.selected_lock_address_latitude));
            push.child("Longitude").setValue(Float.valueOf(BLEService.selected_lock_address_longitude));
            push.child("lockedStatus").setValue(str);
            String string = rootView.getResources().getString(R.string.location_not_found);
            try {
                string = getReverseGeoLocationString(BLEService.selected_lock_address_latitude, BLEService.selected_lock_address_longitude);
                push.child("Placemark").setValue(string);
                return;
            } catch (IOException e) {
                push.child("Placemark").setValue(string);
                e.printStackTrace();
                return;
            }
        }
        Log.i("BLE", "HEY no serial number so am not shooting");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void UI_lock_status_display() {
        if (BLEService.selected_lock_state != previous_selected_lock_state || BLEService.update_UI_new_lock_status) {
            previous_selected_lock_state = BLEService.selected_lock_state;
            if (BLEService.selected_lock_state) {
                try {
                    if (MainActivity.watch_connected) {
                        BLEService.mConsumerService.sendData("Locking completed");
                    }
                    if (!BLEService.update_UI_new_lock_status && !BLEService.parsedIp45SerialNumber.equals("")) {
                        shootLockStatusToFirebase("LOCKED");
                    }
                    BLEService.update_UI_new_lock_status = false;
                    ImageView imageView = (ImageView) ((Activity) MainActivity.context).findViewById(R.id.imageView_ble_unlock_button);
                    ImageView imageView2 = (ImageView) ((Activity) MainActivity.context).findViewById(R.id.imageView_round_unlock);
                    ImageView imageView3 = (ImageView) ((Activity) MainActivity.context).findViewById(R.id.imageView_round_lock);
                    imageView2.clearAnimation();
                    imageView3.clearAnimation();
                    ((ImageView) ((Activity) MainActivity.context).findViewById(R.id.imageView_ble_lock_button)).setVisibility(4);
                    imageView3.setVisibility(4);
                    imageView.setVisibility(0);
                    imageView2.setVisibility(0);
                    imageView.setEnabled(true);
                    imageView2.setEnabled(true);
                    imageView.setAlpha(255);
                    imageView2.setAlpha(255);
                    RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                    rotateAnimation.setRepeatMode(1);
                    rotateAnimation.setDuration(2500L);
                    rotateAnimation.setInterpolator(new LinearInterpolator());
                    rotateAnimation.setRepeatCount(-1);
                    imageView2.startAnimation(rotateAnimation);
                } catch (Exception e) {
                    Log.i("BLE", "update_locking_picture: " + e.toString());
                }
            } else if (BLEService.selected_lock_state) {
            } else {
                try {
                    if (MainActivity.watch_connected) {
                        BLEService.mConsumerService.sendData("Unlocking completed");
                    }
                    if (!BLEService.update_UI_new_lock_status && !BLEService.parsedIp45SerialNumber.equals("")) {
                        shootLockStatusToFirebase("UNLOCKED");
                    }
                    BLEService.update_UI_new_lock_status = false;
                    ImageView imageView4 = (ImageView) ((Activity) MainActivity.context).findViewById(R.id.imageView_ble_unlock_button);
                    ImageView imageView5 = (ImageView) ((Activity) MainActivity.context).findViewById(R.id.imageView_round_unlock);
                    ImageView imageView6 = (ImageView) ((Activity) MainActivity.context).findViewById(R.id.imageView_ble_lock_button);
                    ImageView imageView7 = (ImageView) ((Activity) MainActivity.context).findViewById(R.id.imageView_round_lock);
                    imageView5.clearAnimation();
                    imageView7.clearAnimation();
                    if (BLEService.selected_lock_model.equals("GT2002")) {
                        imageView6.setImageResource(R.drawable.unlock_green_gt2002);
                    } else {
                        imageView6.setImageResource(R.drawable.unlock_green);
                    }
                    imageView4.setVisibility(4);
                    imageView5.setVisibility(4);
                    imageView6.setVisibility(0);
                    imageView7.setVisibility(0);
                    imageView6.setEnabled(true);
                    imageView7.setEnabled(true);
                    imageView6.setAlpha(255);
                    imageView7.setAlpha(255);
                    RotateAnimation rotateAnimation2 = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                    rotateAnimation2.setRepeatMode(1);
                    rotateAnimation2.setDuration(2500L);
                    rotateAnimation2.setInterpolator(new LinearInterpolator());
                    rotateAnimation2.setRepeatCount(-1);
                    imageView7.startAnimation(rotateAnimation2);
                } catch (Exception e2) {
                    Log.i("BLE", "update_locking_picture: " + e2.toString());
                }
            }
        }
    }

    private void UI_lock_name() {
        TextView textView = (TextView) rootView.findViewById(R.id.textView_serialNumberMain);
        ((TextView) rootView.findViewById(R.id.textView_lock_name)).setText(BLEService.selected_lock_name);
        System.out.println("HEY from UI_lock_name" + BLEService.selected_lock_name + BLEService.selected_lock_model + BLEService.parsedIp45SerialNumber);
        if ((BLEService.selected_lock_model.equals("GT2100") || BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5300") || BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) && !BLEService.parsedIp45SerialNumber.equals("")) {
            textView.setText(rootView.getResources().getString(R.string.serial_number) + BLEService.parsedIp45SerialNumber);
        } else {
            textView.setText("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void UI_battery_level() {
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView_battery1);
        imageView.setVisibility(0);
        this.previous_mapped_battery_level = BLEService.battery_level;
        if (BLEService.isLockInChargingState) {
            imageView.setImageResource(R.drawable.battery_charging);
            return;
        }
        int i = this.previous_mapped_battery_level;
        if (i == 100) {
            imageView.setImageResource(R.drawable.battery1);
        } else if (i >= 94 && i < 100) {
            imageView.setImageResource(R.drawable.battery2);
        } else if (i >= 84 && i < 94) {
            imageView.setImageResource(R.drawable.battery3);
        } else if (i >= 74 && i < 84) {
            imageView.setImageResource(R.drawable.battery4);
        } else if (i >= 54 && i < 74) {
            imageView.setImageResource(R.drawable.battery5);
        } else if (i >= 44 && i < 54) {
            imageView.setImageResource(R.drawable.battery6);
        } else if (i >= 24 && i < 44) {
            imageView.setImageResource(R.drawable.battery7);
        } else if (i < 25) {
            imageView.setImageResource(R.drawable.battery8);
        }
    }

    private void UI_button_alpha() {
        imageView_in_sync = (ImageView) rootView.findViewById(R.id.imageView_in_sync);
        unlock_button = (ImageView) rootView.findViewById(R.id.imageView_ble_unlock_button);
        unlock_round_button = (ImageView) rootView.findViewById(R.id.imageView_round_unlock);
        lock_button = (ImageView) rootView.findViewById(R.id.imageView_ble_lock_button);
        lock_round_button = (ImageView) rootView.findViewById(R.id.imageView_round_lock);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.3f);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setDuration(1000L);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setRepeatCount(-1);
        imageView_in_sync.startAnimation(alphaAnimation);
        if (BLEService.selected_lock_state) {
            unlock_round_button.setVisibility(0);
            unlock_round_button.setVisibility(0);
            lock_button.setVisibility(4);
            lock_round_button.setVisibility(4);
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            aAnim = rotateAnimation;
            rotateAnimation.setRepeatMode(1);
            aAnim.setDuration(2500L);
            aAnim.setInterpolator(new LinearInterpolator());
            aAnim.setRepeatCount(-1);
            unlock_round_button.startAnimation(aAnim);
            return;
        }
        unlock_round_button.setVisibility(4);
        unlock_round_button.setVisibility(4);
        lock_button.setVisibility(0);
        lock_round_button.setVisibility(0);
        RotateAnimation rotateAnimation2 = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        aAnim3 = rotateAnimation2;
        rotateAnimation2.setRepeatMode(1);
        aAnim3.setDuration(2500L);
        aAnim3.setInterpolator(new LinearInterpolator());
        aAnim3.setRepeatCount(-1);
        lock_round_button.startAnimation(aAnim3);
    }

    public static void UI_disable_buttons() {
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView_locking);
        ImageView imageView2 = (ImageView) rootView.findViewById(R.id.imageView_proximity);
        ImageView imageView3 = (ImageView) rootView.findViewById(R.id.imageView_watch);
        ImageView imageView4 = (ImageView) rootView.findViewById(R.id.imageView_audit);
        ImageView imageView5 = (ImageView) rootView.findViewById(R.id.imageView_setting);
        ImageView imageView6 = (ImageView) rootView.findViewById(R.id.imageView_ble_unlock_button);
        ImageView imageView7 = (ImageView) rootView.findViewById(R.id.imageView_round_unlock);
        ImageView imageView8 = (ImageView) rootView.findViewById(R.id.imageView_ble_lock_button);
        ImageView imageView9 = (ImageView) rootView.findViewById(R.id.imageView_round_lock);
        imageView.setAlpha(75);
        imageView2.setAlpha(75);
        imageView3.setAlpha(75);
        imageView4.setAlpha(75);
        imageView5.setAlpha(75);
        if (!BLEService.selected_lock_state) {
            imageView6.setVisibility(4);
            imageView7.setVisibility(4);
            imageView9.setAlpha(75);
            imageView8.setAlpha(75);
        } else {
            imageView8.setVisibility(4);
            imageView9.setVisibility(4);
            imageView7.setAlpha(75);
            imageView6.setAlpha(75);
        }
        imageView.setClickable(false);
        imageView2.setClickable(false);
        imageView3.setClickable(false);
        imageView4.setClickable(false);
        imageView5.setClickable(false);
        imageView6.setClickable(false);
        imageView7.setClickable(false);
        imageView8.setClickable(false);
        imageView9.setClickable(false);
    }

    private float different_screen(int i) {
        return (i * getResources().getDisplayMetrics().density) / 4.0f;
    }

    private float different_screen_invert(int i) {
        return i * (4.0f / getResources().getDisplayMetrics().density);
    }

    private void UI_count_down_timer() {
        if (count_down_started) {
            return;
        }
        CountDownTimer countDownTimer = idle_count_down;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (shutdown_time != 150000) {
            shutdown_time = PreferenceManager.getDefaultSharedPreferences(getActivity()).getInt("shutdown_time", 180000);
            count_down_routine();
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.egeetouch.egeetouch_manager.Fragment_BLE$6] */
    public static void count_down_routine() {
        ll_autoshutdown_timer.setVisibility(0);
        tv_autoShutdown.setVisibility(0);
        count_down_started = true;
        idle_count_down = new CountDownTimer(shutdown_time, 1000L) { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.6
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                if (BLEService.vicinity_on) {
                    return;
                }
                int i = Fragment_BLE.shutdown_time;
                int i2 = ((int) j) / 60000;
                int i3 = ((int) (j - (60000 * i2))) / 1000;
                if (i2 == 0) {
                    Fragment_BLE.tv_displayMin.setVisibility(8);
                    Fragment_BLE.tv_min.setVisibility(8);
                    Fragment_BLE.tv_displaySec.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3)));
                } else {
                    Fragment_BLE.tv_displayMin.setVisibility(0);
                    Fragment_BLE.tv_min.setVisibility(0);
                    Fragment_BLE.tv_displayMin.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2)));
                    Fragment_BLE.tv_displaySec.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3)));
                }
                Log.i("BLE", Fragment_BLE.rootView.getResources().getString(R.string.auto_shut_down_in) + " " + String.valueOf(i2) + ":" + String.valueOf(i3) + " " + Fragment_BLE.rootView.getResources().getString(R.string.min));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (BLEService.vicinity_on) {
                    return;
                }
                Log.i("BLE", "shutdown_trigger=true");
                Fragment_BLE.stop_count_down_timer();
                Fragment_BLE.shutdown_trigger = true;
                BLEService.shutdown_triggered = true;
                BLEService.Ble_Mode = 112;
                Fragment_BLE.imageView_in_sync = (ImageView) Fragment_BLE.rootView.findViewById(R.id.imageView_in_sync);
                Fragment_BLE.imageView_in_sync.setImageResource(R.drawable.bluetooth_disconnect);
                Fragment_BLE.UI_disable_buttons();
            }
        }.start();
    }

    public static void stop_count_down_timer() {
        if (idle_count_down == null || !count_down_started) {
            return;
        }
        count_down_started = false;
        ll_autoshutdown_timer.setVisibility(4);
        tv_autoShutdown.setVisibility(4);
        idle_count_down.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void icon_display(Boolean bool) {
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView_locking);
        ImageView imageView2 = (ImageView) rootView.findViewById(R.id.imageView_proximity);
        ImageView imageView3 = (ImageView) rootView.findViewById(R.id.imageView_audit);
        ImageView imageView4 = (ImageView) rootView.findViewById(R.id.imageView_watch);
        ImageView imageView5 = (ImageView) rootView.findViewById(R.id.imageView_setting);
        if (BLEService.vicinity_on) {
            if (imageView != null) {
                imageView.setImageResource(R.drawable.function_locking_icon);
            }
            if (imageView2 != null) {
                imageView2.setImageResource(R.drawable.function_proximity_icon_invert);
            }
        } else {
            if (imageView != null) {
                imageView.setImageResource(R.drawable.function_locking_icon_invert);
            }
            if (imageView2 != null) {
                imageView2.setImageResource(R.drawable.function_proximity_icon);
            }
        }
        if (!bool.booleanValue()) {
            if (!BLEService.vicinity_on) {
                ImageView imageView6 = (ImageView) rootView.findViewById(R.id.imageView_ble_unlock_button);
                ImageView imageView7 = (ImageView) rootView.findViewById(R.id.imageView_round_unlock);
                ImageView imageView8 = (ImageView) rootView.findViewById(R.id.imageView_ble_lock_button);
                ImageView imageView9 = (ImageView) rootView.findViewById(R.id.imageView_round_lock);
                if (!BLEService.selected_lock_state) {
                    imageView6.setVisibility(4);
                    imageView7.setVisibility(4);
                    imageView9.setAlpha(75);
                    imageView8.setAlpha(75);
                } else {
                    imageView8.setVisibility(4);
                    imageView9.setVisibility(4);
                    imageView7.setAlpha(75);
                    imageView6.setAlpha(75);
                }
                imageView6.setClickable(false);
                imageView7.setClickable(false);
                imageView8.setClickable(false);
                imageView9.setClickable(false);
            }
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
        if (!BLEService.vicinity_on) {
            ImageView imageView10 = (ImageView) rootView.findViewById(R.id.imageView_ble_unlock_button);
            ImageView imageView11 = (ImageView) rootView.findViewById(R.id.imageView_round_unlock);
            ImageView imageView12 = (ImageView) rootView.findViewById(R.id.imageView_ble_lock_button);
            ImageView imageView13 = (ImageView) rootView.findViewById(R.id.imageView_round_lock);
            if (BLEService.selected_lock_state) {
                imageView10.setVisibility(0);
                imageView11.setVisibility(0);
                imageView13.setAlpha(255);
                imageView12.setAlpha(255);
            } else {
                imageView12.setVisibility(0);
                imageView13.setVisibility(0);
                imageView11.setAlpha(255);
                imageView10.setAlpha(255);
            }
            imageView10.setClickable(true);
            imageView11.setClickable(true);
            imageView12.setClickable(true);
            imageView13.setClickable(true);
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

    private void update_seek_bar() {
        System.out.println("Hello checking the proximity: update_seek_bar()");
        final SeekBar seekBar = (SeekBar) rootView.findViewById(R.id.seekBar_sensitivity);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.7
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i, boolean z) {
                System.out.println("Hello checking the proximity: seeBar progress:" + i);
                if (i <= 50) {
                    BLEService.var_zone_1 = 0;
                    BLEService.var_zone_2 = 0;
                    Fragment_BLE.sensitivity_low = true;
                    seekBar.setProgress(0);
                    return;
                }
                BLEService.var_zone_1 = -4;
                BLEService.var_zone_2 = -3;
                Fragment_BLE.sensitivity_low = false;
                seekBar.setProgress(100);
            }
        });
        final SeekBar seekBar2 = (SeekBar) rootView.findViewById(R.id.seekBar_range);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.8
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar3) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar3) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar3, int i, boolean z) {
                if (i <= 50) {
                    BLEService.range_far = false;
                    seekBar2.setProgress(0);
                    ((TextView) Fragment_BLE.rootView.findViewById(R.id.textView_2band)).setBackgroundColor(Fragment_BLE.this.getResources().getColor(R.color.pink));
                    return;
                }
                BLEService.range_far = true;
                seekBar2.setProgress(100);
                ((TextView) Fragment_BLE.rootView.findViewById(R.id.textView_2band)).setBackgroundColor(Fragment_BLE.this.getResources().getColor(R.color.green));
            }
        });
        new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.9
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                Fragment_BLE.this.mp.stop();
                Fragment_BLE.this.mp.reset();
                Fragment_BLE.this.mp.release();
            }
        };
    }

    private void algorithm_rssi() {
        System.out.println("Hello checking the proximity: algorithm_rssi: avg_rssi:" + BLEService.avg_rssi + " zone_2_max:" + BLEService.zone_2_max + " var_zone_2:" + BLEService.var_zone_2 + " (zone_2_max - var_zone_2):" + (BLEService.zone_2_max - BLEService.var_zone_2));
        TextView textView = (TextView) rootView.findViewById(R.id.textView_near);
        TextView textView2 = (TextView) rootView.findViewById(R.id.textView_far);
        if (current_zone == 1 && BLEService.avg_rssi < BLEService.zone_1_max - BLEService.var_zone_1) {
            next_target_zone = 2;
        }
        if (current_zone == 2 && BLEService.avg_rssi < BLEService.zone_2_max - BLEService.var_zone_2) {
            next_target_zone = 3;
        } else if (current_zone == 2 && BLEService.avg_rssi >= BLEService.zone_1_min - BLEService.var_zone_1) {
            next_target_zone = 1;
        }
        if (current_zone != 3 || BLEService.avg_rssi <= BLEService.zone_2_min - BLEService.var_zone_2) {
            return;
        }
        next_target_zone = 2;
    }

    private void move_picture(float f, float f2) {
        this.img_animation = (ImageView) rootView.findViewById(R.id.imageView_proximity_ppl);
        System.out.println("Hello checking the proximity :origin:" + f + " destination:" + f2);
        if (f < f2) {
            if (toggle) {
                toggle = false;
                this.img_animation.setImageResource(R.drawable.proximity_right1);
            } else {
                toggle = true;
                this.img_animation.setImageResource(R.drawable.proximity_right2);
            }
        } else if (toggle) {
            toggle = false;
            this.img_animation.setImageResource(R.drawable.proximity_left1);
        } else {
            toggle = true;
            this.img_animation.setImageResource(R.drawable.proximity_left2);
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(f, f2, 0.0f, 0.0f);
        animation = translateAnimation;
        if (sensitivity_low) {
            translateAnimation.setDuration(800L);
        } else {
            translateAnimation.setDuration(250L);
        }
        animation.setRepeatCount(0);
        animation.setFillAfter(true);
        this.img_animation.startAnimation(animation);
        last_position = f2;
    }

    private void battery_display_algorithm() {
        if (BLEService.get_battery_level_done) {
            BLEService.get_battery_level_done = false;
            Log.i("BLE", "Battery Level_inside_BLE: " + String.valueOf(BLEService.battery_level));
            mapped_battery_value = averaging_battery_value(map_battery_value(BLEService.battery_level));
            if (get_init_battery_level_done) {
                Cursor rawQuery = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(BLEService.selected_lock_name), null);
                while (rawQuery.moveToNext()) {
                    try {
                        this.previous_mapped_battery_level = Integer.parseInt(rawQuery.getString(4));
                    } catch (Exception unused) {
                        this.previous_mapped_battery_level = 0;
                    }
                }
                rawQuery.close();
                int i = this.previous_mapped_battery_level;
                int i2 = mapped_battery_value;
                if (i > i2 || i == 0) {
                    DatabaseVariable.db_lock.execSQL(DatabaseVariable.lockdb_update_new_battery_level(BLEService.selected_lock_name, String.valueOf(mapped_battery_value)));
                    mapped_battery_level = mapped_battery_value;
                } else if (Math.abs(i - i2) > 90 || mapped_battery_value == 100) {
                    mapped_battery_value = 100;
                    DatabaseVariable.db_lock.execSQL(DatabaseVariable.lockdb_update_new_battery_level(BLEService.selected_lock_name, String.valueOf(mapped_battery_value)));
                    mapped_battery_level = mapped_battery_value;
                } else {
                    mapped_battery_level = this.previous_mapped_battery_level;
                }
                int i3 = mapped_battery_level;
                if (i3 <= 1) {
                    if (!dialog_battery_show) {
                        dialog_battery_show = true;
                        new SweetAlertDialog(MainActivity.context, 3).setTitleText(MainActivity.context.getResources().getString(R.string.low_batt_title_3)).setContentText(MainActivity.context.getResources().getString(R.string.low_batt_content_3)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.10
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        }).show();
                    }
                    ((ImageView) rootView.findViewById(R.id.imageView_battery1)).setImageResource(R.drawable.battery8);
                } else if (i3 < 15 && !dialog_battery_show) {
                    dialog_battery_show = true;
                    new SweetAlertDialog(MainActivity.context, 3).setTitleText(MainActivity.context.getResources().getString(R.string.low_batt_title_15)).setContentText(MainActivity.context.getResources().getString(R.string.low_batt_content_15)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                }
                ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView_battery1);
                imageView.setVisibility(0);
                int i4 = mapped_battery_level;
                if (i4 >= 90 && i4 <= 100) {
                    imageView.setImageResource(R.drawable.battery1);
                    return;
                } else if (i4 >= 80 && i4 < 90) {
                    imageView.setImageResource(R.drawable.battery2);
                    return;
                } else if (i4 >= 70 && i4 < 80) {
                    imageView.setImageResource(R.drawable.battery3);
                    return;
                } else if (i4 >= 60 && i4 < 70) {
                    imageView.setImageResource(R.drawable.battery4);
                    return;
                } else if (i4 >= 45 && i4 < 60) {
                    imageView.setImageResource(R.drawable.battery5);
                    return;
                } else if (i4 >= 25 && i4 < 45) {
                    imageView.setImageResource(R.drawable.battery6);
                    return;
                } else if (i4 > 1 && i4 < 25) {
                    imageView.setImageResource(R.drawable.battery7);
                    return;
                } else if (i4 <= 1) {
                    if (low_batt_count > 100) {
                        low_batt_count = 0;
                        imageView.setImageResource(R.drawable.battery8);
                        return;
                    }
                    imageView.setVisibility(4);
                    low_batt_count++;
                    return;
                } else {
                    return;
                }
            }
            int i5 = get_battery_level_counter + 1;
            get_battery_level_counter = i5;
            if (i5 > 2) {
                get_battery_level_counter = 0;
                get_init_battery_level_done = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void battery_display_algorithm_with_reset() {
        if (BLEService.get_battery_level_done) {
            BLEService.get_battery_level_done = false;
            if (get_init_battery_level_done) {
                int map_battery_value = (mapped_battery_value + map_battery_value(BLEService.battery_level)) / 2;
                mapped_battery_value = map_battery_value;
                mapped_battery_level = map_battery_value;
                Cursor rawQuery = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(BLEService.selected_lock_name), null);
                while (rawQuery.moveToNext()) {
                    try {
                        this.previous_mapped_battery_level = Integer.parseInt(rawQuery.getString(4));
                    } catch (Exception unused) {
                        this.previous_mapped_battery_level = 0;
                    }
                }
                rawQuery.close();
                Log.i("BLE", "battery_level: " + String.valueOf(BLEService.battery_level));
                Log.i("BLE", "map_battery_value(battery_level): " + String.valueOf(map_battery_value(BLEService.battery_level)));
                Log.i("BLE", "mapped_battery_level0: " + String.valueOf(mapped_battery_level));
                Log.i("BLE", "previous_mapped_battery_level0: " + String.valueOf(this.previous_mapped_battery_level));
                if (BLEService.battery_level >= 100) {
                    DatabaseVariable.db_lock.execSQL(DatabaseVariable.lockdb_update_new_battery_level(BLEService.selected_lock_name, String.valueOf(map_battery_value(BLEService.battery_level))));
                    mapped_battery_level = 100;
                } else {
                    int i = mapped_battery_level;
                    int i2 = this.previous_mapped_battery_level;
                    if (i <= i2 || i2 == 0) {
                        DatabaseVariable.db_lock.execSQL(DatabaseVariable.lockdb_update_new_battery_level(BLEService.selected_lock_name, String.valueOf(mapped_battery_value)));
                        mapped_battery_level = mapped_battery_value;
                        Log.i("BLE", "mapped_battery_level1: " + String.valueOf(mapped_battery_level));
                    } else {
                        if (i - i2 >= 90 && BLEService.battery_level < 100) {
                            DatabaseVariable.db_lock.execSQL(DatabaseVariable.lockdb_update_new_battery_level(BLEService.selected_lock_name, String.valueOf(mapped_battery_value)));
                            this.previous_mapped_battery_level = mapped_battery_value;
                            Log.i("BLE", "previous_mapped_battery_level1: " + String.valueOf(mapped_battery_level));
                        }
                        mapped_battery_level = this.previous_mapped_battery_level;
                        Log.i("BLE", "mapped_battery_level2: " + String.valueOf(mapped_battery_level));
                    }
                }
                int i3 = mapped_battery_level;
                if (i3 <= 1) {
                    if (!dialog_battery_show) {
                        dialog_battery_show = true;
                        new SweetAlertDialog(MainActivity.context, 3).setTitleText(MainActivity.context.getResources().getString(R.string.low_batt_title_3)).setContentText(MainActivity.context.getResources().getString(R.string.low_batt_content_3)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_BLE.11
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        }).show();
                    }
                    ((ImageView) rootView.findViewById(R.id.imageView_battery1)).setImageResource(R.drawable.battery8);
                    return;
                } else if (i3 >= 15 || dialog_battery_show) {
                    return;
                } else {
                    dialog_battery_show = true;
                    new SweetAlertDialog(MainActivity.context, 3).setTitleText(MainActivity.context.getResources().getString(R.string.low_batt_title_15)).setContentText(MainActivity.context.getResources().getString(R.string.low_batt_content_15)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                    return;
                }
            }
            if (get_battery_level_counter == 0) {
                mapped_battery_value = map_battery_value(BLEService.battery_level);
            } else {
                mapped_battery_value = (mapped_battery_value + map_battery_value(BLEService.battery_level)) / 2;
            }
            int i4 = get_battery_level_counter + 1;
            get_battery_level_counter = i4;
            if (i4 > 2) {
                get_battery_level_counter = 0;
                get_init_battery_level_done = true;
            }
        }
    }

    private int averaging_battery_value(int i) {
        int[] iArr = average_battery;
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = iArr[4];
        iArr[4] = iArr[5];
        iArr[5] = iArr[6];
        iArr[6] = iArr[7];
        iArr[7] = i;
        return (((((((iArr[0] + iArr[1]) + iArr[2]) + iArr[3]) + iArr[4]) + iArr[5]) + iArr[6]) + iArr[7]) / 8;
    }

    private int map_battery_value(int i) {
        int i2;
        double d;
        double d2;
        int i3;
        if (BLEService.selected_lock_model.equals("GT1000") || BLEService.selected_lock_model.equals("GT2002")) {
            if (i >= 90 && i <= 100) {
                i3 = i / 2;
                return i3 + 50;
            }
            if (i < 80 || i >= 90) {
                if (i >= 70 && i < 80) {
                    d = (i - 70) * 2.5d;
                    d2 = 60.0d;
                    return (int) (d + d2);
                } else if (i >= 60 && i < 70) {
                    i2 = (i - 60) * 4;
                    return i2 + 20;
                } else if (i < 50 || i >= 60) {
                    if (i < 50) {
                        return 1;
                    }
                    return 100;
                } else {
                    return i - 40;
                }
            }
            return i + 5;
        }
        if (BLEService.selected_lock_model.equals("GT3100") || BLEService.selected_lock_model.equals("GT2003")) {
            if (i >= 90 && i <= 100) {
                i3 = i / 2;
                return i3 + 50;
            }
            if ((i < 80 || i >= 90) && (i < 70 || i >= 80)) {
                if (i < 60 || i >= 70) {
                    if (i >= 50 && i < 60) {
                        d = (BLEService.battery_level - 50) * 1.5d;
                        d2 = 40.0d;
                    } else if (i >= 40 && i < 50) {
                        i2 = (i - 40) * 2;
                        return i2 + 20;
                    } else if (i >= 30 && i < 40) {
                        return i - 20;
                    } else {
                        if (i >= 20 && i < 30) {
                            d = (i - 20) * 0.5d;
                            d2 = 5.0d;
                        } else if (i < 10 || i >= 20) {
                            return 1;
                        } else {
                            d = (i - 10) * 0.4d;
                            d2 = 1.0d;
                        }
                    }
                    return (int) (d + d2);
                }
                return ((i - 60) * 2) + 55;
            }
            return i + 5;
        }
        return 100;
    }

    private int map_battery_value_with_reset(int i) {
        int i2;
        double d;
        double d2;
        if (BLEService.selected_lock_model.equals("GT1000") || BLEService.selected_lock_model.equals("GT2002")) {
            if (i >= 56) {
                if (i < 56 || i > 61) {
                    if (i < 61 || i > 71) {
                        if (i >= 71 && i <= 81) {
                            i2 = (i - 71) * 4;
                            return i2 + 20;
                        } else if (i < 81 || i > 91) {
                            if (i < 91 || i > 101) {
                                if (i >= 101 && i <= 103) {
                                    return 99;
                                }
                                return 100;
                            }
                            return i - 6;
                        } else {
                            return ((int) ((i - 81) * 2.5d)) + 60;
                        }
                    }
                    return i - 51;
                }
                return (i - 56) * 2;
            }
            return 1;
        }
        if (BLEService.selected_lock_model.equals("GT3100") || BLEService.selected_lock_model.equals("GT2003")) {
            if (i < 90 || i > 100) {
                if ((i < 80 || i >= 90) && (i < 70 || i >= 80)) {
                    if (i < 60 || i >= 70) {
                        if (i >= 50 && i < 60) {
                            d = (i - 50) * 1.5d;
                            d2 = 40.0d;
                        } else if (i >= 40 && i < 50) {
                            i2 = (i - 40) * 2;
                            return i2 + 20;
                        } else if (i >= 30 && i < 40) {
                            return i - 20;
                        } else {
                            if (i >= 20 && i < 30) {
                                d = (i - 20) * 0.5d;
                                d2 = 5.0d;
                            } else if (i < 10 || i >= 20) {
                                return 1;
                            } else {
                                d = (i - 10) * 0.4d;
                                d2 = 1.0d;
                            }
                        }
                        return (int) (d + d2);
                    }
                    return ((i - 60) * 2) + 55;
                }
                return i + 5;
            }
            return (i / 2) + 50;
        }
        return 100;
    }

    private void screen_hdpi() {
        int i = move_counter;
        if (i >= 1 || !sensitivity_low) {
            move_counter = 0;
            System.out.println("Hello checking the proximity:screen_hdpi current_zone:" + current_zone + " next_target_zone:" + next_target_zone);
            int i2 = current_zone;
            if (i2 == 1 && next_target_zone == 2) {
                move_picture(last_position, screensize * 80.0f);
                current_zone = 2;
            } else if (i2 == 1 && next_target_zone == 3) {
                move_picture(last_position, screensize * 160.0f);
                current_zone = 3;
            } else if (i2 == 1 && next_target_zone == 1) {
                if (toggle) {
                    move_picture(last_position, 0.0f);
                } else {
                    move_picture(last_position, screensize * 50.0f);
                }
            }
            int i3 = current_zone;
            if (i3 == 2 && next_target_zone == 1) {
                move_picture(last_position, (screensize * 50.0f) / 2.0f);
                current_zone = 1;
            } else if (i3 == 2 && next_target_zone == 3) {
                move_picture(last_position, screensize * 160.0f);
                current_zone = 3;
            } else if (i3 == 2 && next_target_zone == 2) {
                if (toggle) {
                    move_picture(last_position, screensize * 80.0f);
                } else {
                    move_picture(last_position, screensize * 130.0f);
                }
            }
            int i4 = current_zone;
            if (i4 == 3 && next_target_zone == 2) {
                move_picture(last_position, screensize * 130.0f);
                current_zone = 2;
                return;
            } else if (i4 == 3 && next_target_zone == 1) {
                move_picture(last_position, screensize * 50.0f);
                current_zone = 1;
                return;
            } else if (i4 == 3 && next_target_zone == 3) {
                if (toggle) {
                    move_picture(last_position, screensize * 160.0f);
                    return;
                } else {
                    move_picture(last_position, screensize * 220.0f);
                    return;
                }
            } else {
                return;
            }
        }
        move_counter = i + 1;
    }

    private void screen_xhdpi() {
        System.out.println("Hello checking the proximity:screen_xhdpi current_zone:" + current_zone + " next_target_zone:" + next_target_zone);
        int i = move_counter;
        if (i >= 1 || !sensitivity_low) {
            move_counter = 0;
            int i2 = current_zone;
            if (i2 == 1 && next_target_zone == 2) {
                move_picture(last_position, screensize * 120.0f);
                current_zone = 2;
            } else if (i2 == 1 && next_target_zone == 3) {
                move_picture(last_position, screensize * 200.0f);
                current_zone = 3;
            } else if (i2 == 1 && next_target_zone == 1) {
                if (toggle) {
                    move_picture(last_position, 0.0f);
                } else {
                    move_picture(last_position, screensize * 90.0f);
                }
            }
            int i3 = current_zone;
            if (i3 == 2 && next_target_zone == 1) {
                move_picture(last_position, screensize * 90.0f);
                current_zone = 1;
            } else if (i3 == 2 && next_target_zone == 3) {
                move_picture(last_position, screensize * 200.0f);
                current_zone = 3;
            } else if (i3 == 2 && next_target_zone == 2) {
                if (toggle) {
                    move_picture(last_position, screensize * 120.0f);
                } else {
                    move_picture(last_position, screensize * 170.0f);
                }
            }
            int i4 = current_zone;
            if (i4 == 3 && next_target_zone == 2) {
                move_picture(last_position, screensize * 170.0f);
                current_zone = 2;
                return;
            } else if (i4 == 3 && next_target_zone == 1) {
                move_picture(last_position, screensize * 90.0f);
                current_zone = 1;
                return;
            } else if (i4 == 3 && next_target_zone == 3) {
                if (toggle) {
                    move_picture(last_position, screensize * 200.0f);
                    return;
                } else {
                    move_picture(last_position, screensize * 240.0f);
                    return;
                }
            } else {
                return;
            }
        }
        move_counter = i + 1;
    }

    private void screen_xxhdpi() {
        System.out.println("Hello checking the proximity:screen_xxhdpi current_zone:" + current_zone + " next_target_zone:" + next_target_zone);
        int i = move_counter;
        if (i >= 1 || !sensitivity_low) {
            move_counter = 0;
            int i2 = current_zone;
            if (i2 == 1 && next_target_zone == 2) {
                move_picture(last_position, screensize * 170.0f);
                current_zone = 2;
            } else if (i2 == 1 && next_target_zone == 3) {
                move_picture(last_position, screensize * 250.0f);
                current_zone = 3;
            } else if (i2 == 1 && next_target_zone == 1) {
                if (toggle) {
                    move_picture(last_position, 0.0f);
                } else {
                    move_picture(last_position, screensize * 140.0f);
                }
            }
            int i3 = current_zone;
            if (i3 == 2 && next_target_zone == 1) {
                move_picture(last_position, screensize * 140.0f);
                current_zone = 1;
            } else if (i3 == 2 && next_target_zone == 3) {
                move_picture(last_position, screensize * 250.0f);
                current_zone = 3;
            } else if (i3 == 2 && next_target_zone == 2) {
                if (toggle) {
                    move_picture(last_position, screensize * 170.0f);
                } else {
                    move_picture(last_position, screensize * 220.0f);
                }
            }
            int i4 = current_zone;
            if (i4 == 3 && next_target_zone == 2) {
                move_picture(last_position, screensize * 220.0f);
                current_zone = 2;
                return;
            } else if (i4 == 3 && next_target_zone == 1) {
                move_picture(last_position, screensize * 140.0f);
                current_zone = 1;
                return;
            } else if (i4 == 3 && next_target_zone == 3) {
                if (toggle) {
                    move_picture(last_position, screensize * 250.0f);
                    return;
                } else {
                    move_picture(last_position, screensize * 290.0f);
                    return;
                }
            } else {
                return;
            }
        }
        move_counter = i + 1;
    }

    private void screen_xxxhdpi() {
        System.out.println("Hello checking the proximity:screen_xxxhdpi current_zone:" + current_zone + " next_target_zone:" + next_target_zone);
        int i = move_counter;
        if (i >= 1 || !sensitivity_low) {
            move_counter = 0;
            int i2 = current_zone;
            if (i2 == 1 && next_target_zone == 2) {
                move_picture(last_position, screensize * 360.0f);
                current_zone = 2;
            } else if (i2 == 1 && next_target_zone == 3) {
                move_picture(last_position, screensize * 440.0f);
                current_zone = 3;
            } else if (i2 == 1 && next_target_zone == 1) {
                if (toggle) {
                    move_picture(last_position, 0.0f);
                } else {
                    move_picture(last_position, screensize * 320.0f);
                }
            }
            int i3 = current_zone;
            if (i3 == 2 && next_target_zone == 1) {
                move_picture(last_position, screensize * 320.0f);
                current_zone = 1;
            } else if (i3 == 2 && next_target_zone == 3) {
                move_picture(last_position, screensize * 440.0f);
                current_zone = 3;
            } else if (i3 == 2 && next_target_zone == 2) {
                if (toggle) {
                    move_picture(last_position, screensize * 360.0f);
                } else {
                    move_picture(last_position, screensize * 410.0f);
                }
            }
            int i4 = current_zone;
            if (i4 == 3 && next_target_zone == 2) {
                move_picture(last_position, screensize * 410.0f);
                current_zone = 2;
                return;
            } else if (i4 == 3 && next_target_zone == 1) {
                move_picture(last_position, screensize * 320.0f);
                current_zone = 1;
                return;
            } else if (i4 == 3 && next_target_zone == 3) {
                if (toggle) {
                    move_picture(last_position, screensize * 440.0f);
                    return;
                } else {
                    move_picture(last_position, screensize * 480.0f);
                    return;
                }
            } else {
                return;
            }
        }
        move_counter = i + 1;
    }
}
