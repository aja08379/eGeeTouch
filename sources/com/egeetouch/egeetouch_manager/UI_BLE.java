package com.egeetouch.egeetouch_manager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.net.MailTo;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.crypto.NoSuchPaddingException;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes.dex */
public class UI_BLE extends AppCompatActivity {
    public static int BLE_UI = 0;
    public static String TagID_Universal = null;
    static boolean add_lock_mode = true;
    public static SweetAlertDialog audit_address_msg = null;
    public static Dialog cmd_pls_wait = null;
    public static Dialog cmd_pls_wait_extract = null;
    public static final int diaplay_tag_id_dialog = 26;
    public static final int display_app_updateMessage = 27;
    static boolean having_trouble = false;
    public static final int old_lock = 1;
    public static final int pairing_timeout = 15;
    static int password_attempt = 0;
    public static SweetAlertDialog pd_pls_wait = null;
    public static SweetAlertDialog pd_pls_wait_extract = null;
    public static String pls_wait_content = "";
    public static Dialog primaryPassword_popUp = null;
    public static int progress_val = 0;
    public static SweetAlertDialog pw_dialog = null;
    public static Dialog shackle_dialog = null;
    public static int state = 0;
    static boolean timeout_msg_shown = false;
    public static Dialog unlock_notification_dialog = null;
    static boolean unlocking_msg_show = false;
    public static final int update_Audit_trail_UI = 21;
    public static final int update_Audit_trail_UI_addin = 8;
    public static final int update_Audit_trail_address = 25;
    public static final int update_UI_Lock_Setting_Param = 4;
    public static final int update_UI_Locking_By_App = 7;
    public static final int update_UI_Unlocking_RSSI = 2;
    public static final int update_UI_disconnect = 16;
    public static final int update_UI_extract_Tag = 10;
    public static final int update_UI_extract_user = 12;
    public static final int update_UI_lock_button_pressed = 17;
    public static final int update_UI_locking_picture = 14;
    public static final int update_UI_new_primary_password = 5;
    public static final int update_UI_one_last_step = 20;
    public static final int update_UI_please_wait_dismiss = 19;
    public static final int update_UI_please_wait_extract_dismiss = 23;
    public static final int update_UI_please_wait_extract_progress = 24;
    public static final int update_UI_please_wait_extract_show = 22;
    public static final int update_UI_please_wait_show = 18;
    public static final int update_UI_reconnecting_period = 6;
    public static final int update_UI_shutdown = 13;
    public static final int update_UI_update_tag_done = 9;
    public static final int update_UI_update_user_done = 11;
    public static final int update_UI_verify_password = 3;
    public static final int update_backupAuditTrail_UI = 30;
    public static final int update_failed = 29;
    public static final int update_passcodeDeletion_success = 33;
    public static final int update_success = 28;
    public static final int update_syncPasscode_success = 34;
    public static final int update_tagAuditTrailBackUp_Success_UI = 32;
    AlertDialog.Builder alert;
    Dialog contact_us;
    FrameLayout container;
    Context context;
    public Dialog customerServicePop;
    AlertDialog diag;
    public Dialog formDialog;
    EditText input;
    ImageView lock_button;
    ImageView lock_round_button;
    FrameLayout.LayoutParams params;
    public SweetAlertDialog progress;
    ProgressBar progress_bar;
    TextView progress_percent;
    public SweetAlertDialog reset_progress;
    Dialog resetpassword;
    Dialog resetpassword_failed;
    String tagID_input;
    ImageView unlock_button;
    ImageView unlock_round_button;
    private final int maxLength = 6;
    private Handler handler_shackle = new Handler();
    private boolean toogle_shackleImg = false;
    private int delayedSeconds = 0;
    boolean show_unlock_notification_dialog = true;
    LotoInfo lotoInfo = LotoInfo.getInstance();
    Runnable run_shackleClose = new Runnable() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.40
        @Override // java.lang.Runnable
        public void run() {
            if (UI_BLE.shackle_dialog == null || !UI_BLE.shackle_dialog.isShowing()) {
                UI_BLE.this.handler_shackle.removeCallbacks(this);
                return;
            }
            ImageView imageView = (ImageView) UI_BLE.shackle_dialog.findViewById(R.id.img_shackle);
            UI_BLE.this.delayedSeconds++;
            if (UI_BLE.this.toogle_shackleImg) {
                if (BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5300")) {
                    imageView.setImageResource(R.drawable.loto_shackle_open);
                } else {
                    imageView.setImageResource(R.drawable.shackle_open);
                }
                UI_BLE.this.toogle_shackleImg = false;
                UI_BLE.this.handler_shackle.postDelayed(this, 600L);
            } else {
                if (BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5300")) {
                    imageView.setImageResource(R.drawable.loto_shackle_close);
                } else {
                    imageView.setImageResource(R.drawable.shackle_close);
                }
                UI_BLE.this.toogle_shackleImg = true;
                UI_BLE.this.handler_shackle.postDelayed(this, 600L);
            }
            if (UI_BLE.this.delayedSeconds >= 10) {
                ((Button) UI_BLE.shackle_dialog.findViewById(R.id.btn_closeShackle)).setVisibility(0);
            }
        }
    };

    public UI_BLE(Context context) {
        this.context = context;
    }

    public String decryptPassword(String str) {
        try {
            CryptLib cryptLib = new CryptLib();
            try {
                System.out.println("HEY " + cryptLib.encryptPlainTextWithRandomIV(str, "ThisIsMyKey"));
                return cryptLib.decryptCipherTextWithRandomIV(str, "ThisIsMyKey");
            } catch (Exception unused) {
                System.out.println("HEY error in decrypting");
                return "";
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            System.out.println("HEY some issue in encrypting/decrypting");
            return "";
        }
    }

    public void shootLockCredentialsToMyAdminLocks(String str, String str2) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference push = firebaseDatabase.getReference("userLocks").child(MainActivity.user_uid).push();
        push.child("Name");
        push.child("Password").setValue(encryptPassword(BLEService.selected_lock_password));
        push.child("shareStartTime").setValue(0);
        push.child("shareEndTime").setValue(0);
        DatabaseReference child = push.child("ip45SerialNumber");
        if (!BLEService.parsedIp45SerialNumber.equals("")) {
            child.setValue(BLEService.parsedIp45SerialNumber);
            DatabaseReference push2 = firebaseDatabase.getReference("MyAdminLocks").child(MainActivity.user_uid).push();
            push2.child("ip45SerialNumber").setValue(BLEService.parsedIp45SerialNumber);
            push2.child("lockModel").setValue(4);
            return;
        }
        child.setValue("");
        BLEService.parsedIp45SerialNumber = "";
    }

    public String encryptPassword(String str) {
        try {
            CryptLib cryptLib = new CryptLib();
            try {
                System.out.println("HEY " + cryptLib.encryptPlainTextWithRandomIV(str, "ThisIsMyKey"));
                return cryptLib.encryptPlainTextWithRandomIV(str, "ThisIsMyKey");
            } catch (Exception unused) {
                System.out.println("HEY error in encrypting");
                return "";
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            System.out.println("HEY some issue in encrypting/decrypting");
            return "";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void update() {
        int i;
        boolean z;
        Dialog dialog;
        Dialog dialog2;
        Dialog dialog3;
        Dialog dialog4;
        Dialog dialog5;
        Dialog dialog6;
        Dialog dialog7;
        Dialog dialog8;
        final int i2 = 3;
        switch (BLE_UI) {
            case 1:
                System.out.println("Hello checking the lock ver 17 : " + add_lock_mode);
                System.out.println("HEY case old_lock");
                try {
                    if (add_lock_mode) {
                        add_lock_mode = false;
                        password_attempt = 1;
                        having_trouble = false;
                        BLEService.Ble_Mode = 0;
                        BLE_UI = 0;
                        System.out.println("Hello checking the lock ver 16");
                        showPopUpToKeyInPrimaryPassword(true);
                        BLEService.ready_update_parameter = true;
                    }
                } catch (Exception unused) {
                }
                BLE_UI = 0;
                return;
            case 2:
                if (BLEService.selected_lock_is_one_time_access) {
                    if (MainActivity.unlocking_pd != null && MainActivity.unlocking_pd.isShowing()) {
                        MainActivity.unlocking_pd.dismiss();
                    }
                    Log.i("Tag", "OnE Time Access Lock: " + BLEService.selected_lock_is_one_time_access);
                    new SweetAlertDialog(this.context, 2).setTitleText(this.context.getResources().getString(R.string.unlock_successful)).setContentText(this.context.getResources().getString(R.string.one_time_access_is_enabled)).setConfirmText(this.context.getResources().getString(R.string.ok)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.10
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            FirebaseDatabase.getInstance().getReference("Registered_user").child(MainActivity.user_uid).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.10.1
                                @Override // com.google.firebase.database.ValueEventListener
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot != null) {
                                        for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                                            dataSnapshot2.getRef().child("Lock").child("LockName").addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.10.1.1
                                                @Override // com.google.firebase.database.ValueEventListener
                                                public void onDataChange(DataSnapshot dataSnapshot3) {
                                                    if (dataSnapshot3.getValue() == null || !dataSnapshot3.getValue().toString().equals(BLEService.selected_lock_name)) {
                                                        return;
                                                    }
                                                    Log.i("Tag", "Delete: " + dataSnapshot3.getRef().getParent().getParent().getKey().toString());
                                                    dataSnapshot3.getRef().getParent().getParent().removeValue();
                                                }

                                                @Override // com.google.firebase.database.ValueEventListener
                                                public void onCancelled(DatabaseError databaseError) {
                                                    Log.e("TAG", "onCancelled", databaseError.toException());
                                                }
                                            });
                                        }
                                    }
                                }

                                @Override // com.google.firebase.database.ValueEventListener
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.e("TAG", "onCancelled", databaseError.toException());
                                }
                            });
                            DatabaseVariable.db_lock.execSQL(DatabaseVariable.lockdb_delete_value(BLEService.selected_lock_name));
                            DatabaseVariable.db_user.execSQL(DatabaseVariable.userdb_delete_value_bylock(BLEService.selected_lock_name));
                            DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value_bylock(BLEService.selected_lock_name));
                            DatabaseVariable.db_audittrail.execSQL(DatabaseVariable.audittrail_db_delete_value_bylock(BLEService.selected_lock_name));
                            DatabaseVariable.db_location.execSQL(DatabaseVariable.locationdb_delete_value_bylock(BLEService.selected_lock_name));
                            Fragment_BLE.disconnected_trigger = true;
                            BLEService.Ble_Mode = 112;
                            BLEService.disconnect_triggered = true;
                        }
                    }).show();
                    BLE_UI = 0;
                    return;
                }
                final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences((Activity) this.context);
                System.out.println("Hello checking the shackle :isShackleOpened: " + BLEService.isShackleOpened + " unlocking_msg_show:" + unlocking_msg_show + "(sharedPref.):" + defaultSharedPreferences.getBoolean("unlocking_msg_show_boolean", true));
                if (BLEService.isShackleOpened) {
                    if (!unlocking_msg_show) {
                        unlocking_msg_show = true;
                        Dialog dialog9 = new Dialog(this.context);
                        shackle_dialog = dialog9;
                        dialog9.setContentView(R.layout.custom_shackle_alert_dialog);
                        shackle_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                        shackle_dialog.setCancelable(false);
                        try {
                            if (BLEService.selected_lock_model.equals("GT2100") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5300") || BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                                shackle_dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.delayedSeconds = 0;
                        this.handler_shackle.post(this.run_shackleClose);
                        ((Button) shackle_dialog.findViewById(R.id.btn_closeShackle)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.11
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                UI_BLE.shackle_dialog.dismiss();
                                if (BLEService.shackleByPassOnce_isEnabled) {
                                    new SweetAlertDialog(UI_BLE.this.context).setTitleText("Auto-Lock disabled").setContentText("Use app screen to lock or press padlock power button to lock").setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.11.1
                                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            BLEService.Ble_Mode = BLEService.ShackleBypass_settings;
                                            BLEService.shackleByPassOnce_isEnabled = false;
                                            UI_BLE.this.handler_shackle.removeCallbacks(UI_BLE.this.run_shackleClose);
                                            sweetAlertDialog.dismiss();
                                        }
                                    }).show();
                                    return;
                                }
                                BLEService.shackleByPassOnce_isEnabled = true;
                                UI_BLE.shackle_dialog.dismiss();
                                UI_BLE.this.handler_shackle.removeCallbacks(UI_BLE.this.run_shackleClose);
                                UI_BLE.unlocking_msg_show = false;
                            }
                        });
                    }
                } else if (defaultSharedPreferences.getBoolean("unlocking_msg_show_boolean", true) && this.show_unlock_notification_dialog) {
                    Dialog dialog10 = new Dialog(this.context);
                    unlock_notification_dialog = dialog10;
                    dialog10.setContentView(R.layout.dialog_unlock_notificaiton);
                    unlock_notification_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    ((Button) unlock_notification_dialog.findViewById(R.id.btn_dont_remind)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.12
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            SharedPreferences.Editor edit = defaultSharedPreferences.edit();
                            edit.putBoolean("unlocking_msg_show_boolean", false);
                            edit.apply();
                            UI_BLE.unlock_notification_dialog.dismiss();
                        }
                    });
                    ((Button) unlock_notification_dialog.findViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.13
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            UI_BLE.unlock_notification_dialog.dismiss();
                        }
                    });
                    unlock_notification_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.14
                        @Override // android.content.DialogInterface.OnDismissListener
                        public void onDismiss(DialogInterface dialogInterface) {
                            UI_BLE.this.show_unlock_notification_dialog = true;
                        }
                    });
                    unlock_notification_dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.15
                        @Override // android.content.DialogInterface.OnCancelListener
                        public void onCancel(DialogInterface dialogInterface) {
                            UI_BLE.this.show_unlock_notification_dialog = true;
                        }
                    });
                    if (!unlock_notification_dialog.isShowing()) {
                        try {
                            unlock_notification_dialog.show();
                            this.show_unlock_notification_dialog = false;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                if (MainActivity.unlocking_pd != null && MainActivity.unlocking_pd.isShowing()) {
                    MainActivity.unlocking_pd.dismiss();
                }
                state = 3;
                BLE_UI = 14;
                return;
            case 3:
                System.out.println("HEY case update_UI_verify_password we are adding a NEW lock");
                System.out.println("Hello checking the lock ver 13");
                if (BLEService.verified_password_done) {
                    System.out.println("checking add old lock " + MainActivity.add_old_lock);
                    if (MainActivity.scanning_new_lock || MainActivity.add_old_lock) {
                        MainActivity.add_old_lock = false;
                        MainActivity.scanning_new_lock = false;
                        BLEService.Ble_Mode = 0;
                        BLE_UI = 0;
                        System.out.println("on verify password");
                        BLEService.selected_lock_name = "My New Lock " + String.valueOf(1);
                        int i3 = 1;
                        while (i3 != 0) {
                            if (DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_name_exist(BLEService.selected_lock_name), null).getCount() > 0) {
                                i3++;
                                BLEService.selected_lock_name = "My New Lock " + String.valueOf(i3);
                            } else {
                                i3 = 0;
                            }
                        }
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference push = firebaseDatabase.getReference("userLocks").child(MainActivity.user_uid).push();
                        HashMap hashMap = new HashMap();
                        hashMap.put("Name", BLEService.selected_lock_name);
                        hashMap.put("Password", encryptPassword(BLEService.selected_lock_password));
                        hashMap.put("shareEndTime", 0);
                        hashMap.put("shareStartTime", 0);
                        if (!BLEService.parsedIp45SerialNumber.equals("")) {
                            hashMap.put("ip45SerialNumber", BLEService.parsedIp45SerialNumber);
                        } else {
                            hashMap.put("ip45SerialNumber", BLEService.parsedIp45SerialNumber);
                        }
                        if (BLEService.selected_lock_model.contains("GT2100")) {
                            System.out.println("HEY we found a GT2100 so setting type to 4");
                            hashMap.put("Type", 4);
                            i2 = 4;
                        } else if (BLEService.selected_lock_model.contains("GT2002")) {
                            hashMap.put("Type", 3);
                        } else if (BLEService.selected_lock_model.contains("GT1000")) {
                            hashMap.put("Type", 2);
                            i2 = 2;
                        } else {
                            if (BLEService.selected_lock_model.contains("GT3100")) {
                                hashMap.put("Type", 1);
                            } else if (BLEService.selected_lock_model.contains("GT3002")) {
                                hashMap.put("Type", 1);
                            } else if (BLEService.selected_lock_model.contains("GT5100")) {
                                i2 = 7;
                                hashMap.put("Type", 7);
                            } else if (BLEService.selected_lock_model.contains("GT5107")) {
                                i2 = 8;
                                hashMap.put("Type", 8);
                            } else if (BLEService.selected_lock_model.contains("GT5109")) {
                                i2 = 9;
                                hashMap.put("Type", 9);
                            } else if (BLEService.selected_lock_model.contains("GT5300")) {
                                i2 = 7;
                                hashMap.put("Type", 7);
                            } else if (BLEService.selected_lock_model.contains("GT2500")) {
                                i2 = 10;
                                hashMap.put("Type", 10);
                                System.out.println("what is temptype 10");
                            } else if (BLEService.selected_lock_model.contains("GT2550")) {
                                i2 = 11;
                                hashMap.put("Type", 11);
                                System.out.println("what is temptype 11");
                            } else {
                                System.out.println("HEY it isn't an IP45 so being normal GTSOMETHING format");
                                hashMap.put("Type", BLEService.selected_lock_model);
                                i2 = 0;
                            }
                            i2 = 1;
                        }
                        firebaseDatabase.getReference("userLocks").child(MainActivity.user_uid).child(push.getKey()).setValue(hashMap);
                        if (!BLEService.parsedIp45SerialNumber.equals("")) {
                            final DatabaseReference child = firebaseDatabase.getReference("MyAdminLocks").child(MainActivity.user_uid);
                            child.orderByChild("ip45SerialNumber").equalTo(BLEService.parsedIp45SerialNumber).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.1
                                @Override // com.google.firebase.database.ValueEventListener
                                public void onCancelled(DatabaseError databaseError) {
                                }

                                @Override // com.google.firebase.database.ValueEventListener
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists() || BLEService.parsedIp45SerialNumber.equals("")) {
                                        return;
                                    }
                                    HashMap hashMap2 = new HashMap();
                                    hashMap2.put("ip45SerialNumber", BLEService.parsedIp45SerialNumber);
                                    hashMap2.put("lockModel", Integer.valueOf(i2));
                                    hashMap2.put("lockAlias", BLEService.selected_lock_name);
                                    child.push().setValue(hashMap2);
                                }
                            });
                        }
                        MainActivity.currentTimestamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
                        System.out.println("HEY this is currentTimestamp updated from adding lock" + MainActivity.currentTimestamp + BLEService.selected_lock_model);
                        DatabaseVariable.db_lock.execSQL(new DatabaseVariable(MainActivity.context).lockdb_insert_value(BLEService.selected_lock_name, BLEService.selected_lock_password, "model4", AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_NO, BLEService.parsedIp45SerialNumber, BLEService.selected_lock_address, AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_NO, "", "", null));
                        DatabaseReference child2 = FirebaseDatabase.getInstance().getReference("Registered_user").child(MainActivity.user_uid);
                        MainActivity.number_of_lock++;
                        MainActivity.child_index = String.valueOf(MainActivity.number_of_lock);
                        child2.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.2
                            @Override // com.google.firebase.database.ValueEventListener
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                while (dataSnapshot.hasChild(MainActivity.child_index)) {
                                    MainActivity.child_index = String.valueOf(Integer.valueOf(MainActivity.child_index).intValue() + 1);
                                }
                            }

                            @Override // com.google.firebase.database.ValueEventListener
                            public void onCancelled(DatabaseError databaseError) {
                                Log.i("TAG", "Firebase error1: " + databaseError.toString());
                            }
                        });
                        Log.i("Tag", "child_index: " + MainActivity.child_index);
                        DatabaseReference child3 = child2.child(String.valueOf(MainActivity.number_of_lock)).child("Lock");
                        child3.child("LockName").setValue(BLEService.selected_lock_name);
                        child3.child("LockModel").setValue(BLEService.selected_lock_model);
                        child3.child("LockPassword").setValue(BLEService.selected_lock_password);
                        child3.child("LockVersion").setValue(BLEService.selected_lock_version);
                        child3.child("LockLastBatt").setValue(0);
                        child3.child("UserRole").setValue("");
                        child3.child("LockIdentifier").setValue("");
                        child3.child("LockMACAddress").setValue(BLEService.selected_lock_address);
                        DatabaseReference child4 = child3.child("UserRole");
                        if (MainActivity.is_admin) {
                            child4.setValue(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                        } else {
                            child4.setValue(AppEventsConstants.EVENT_PARAM_VALUE_YES);
                        }
                    }
                    System.out.println("Hello Fragment Ble calling!!");
                    if (BLEService.selected_lock_password.equals("123456") && !MainActivity.selected_lock_is_shared && !add_lock_mode) {
                        System.out.println("HEY selected_lock_password variable is " + BLEService.selected_lock_password + " should be 123456 if u see this");
                        Fragment_lock_setting.initial_setup = true;
                        if (BLEService.selected_lock_model.equals("GT5100") || BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5300")) {
                            z = true;
                            this.lotoInfo.setNewLock(true);
                        } else {
                            z = true;
                        }
                        Fragment_lock_setting newInstance = Fragment_lock_setting.newInstance();
                        BLEService.verified_password_done = z;
                        MainActivity.fragmentManager.beginTransaction().setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left).replace(R.id.container, newInstance).addToBackStack("TopStack").commit();
                        BLE_UI = 20;
                        System.out.println("HEY admin password is 123456 so changing it");
                    } else if (!add_lock_mode) {
                        System.out.println("HEY selected_lock_password variable is " + BLEService.selected_lock_password + " should be NOT 123456 if u see this");
                        Fragment_lock_setting.initial_setup = false;
                        BLEService.verified_password_done = true;
                        if (!MainActivity.lock_settings_status) {
                            MainActivity.fragmentManager.beginTransaction().setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left).replace(R.id.container, Fragment_BLE.newInstance()).addToBackStack("TopStack").commit();
                            System.out.println("HEY admin password is NOT 123456 so logging you in");
                            if (MainActivity.unlocking_pd != null && MainActivity.unlocking_pd.isShowing()) {
                                MainActivity.unlocking_pd.dismiss();
                                BLE_UI = 14;
                            } else {
                                BLE_UI = 0;
                            }
                        }
                    } else {
                        MainActivity.addlock_model = 0;
                        BLEService.cancel_scaning_triggered = true;
                        add_lock_mode = false;
                        BLEService.Ble_Mode = 112;
                        SweetAlertDialog sweetAlertDialog = pw_dialog;
                        if (sweetAlertDialog != null) {
                            try {
                                sweetAlertDialog.dismissWithAnimation();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        new SweetAlertDialog(this.context, 2).setTitleText(this.context.getResources().getString(R.string.congratulation)).setContentText(this.context.getResources().getString(R.string.eGeeTouch_successfully_added)).setConfirmText(this.context.getResources().getString(R.string.done)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.3
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                                System.out.println("Hello checking the Adding lock !");
                                UI_BLE.BLE_UI = 0;
                                sweetAlertDialog2.dismiss();
                            }
                        }).show();
                        BLE_UI = 0;
                    }
                    SweetAlertDialog sweetAlertDialog2 = pd_pls_wait;
                    if (sweetAlertDialog2 != null && sweetAlertDialog2.isShowing()) {
                        pd_pls_wait.dismiss();
                    }
                    BLEService.ready_update_parameter = true;
                    return;
                }
                boolean z2 = add_lock_mode;
                if (z2 && (i = password_attempt) < 2) {
                    password_attempt = i + 1;
                    BLEService.Ble_Mode = 0;
                    BLE_UI = 0;
                    showPopUpToKeyInPrimaryPassword(false);
                    BLEService.ready_update_parameter = true;
                    BLE_UI = 0;
                    return;
                } else if (password_attempt == 2) {
                    password_attempt = 0;
                    add_lock_mode = false;
                    having_trouble = true;
                    new SweetAlertDialog(this.context, 0).setTitleText(this.context.getResources().getString(R.string.having_trouble)).setContentText(this.context.getResources().getString(R.string.learn_how_to)).setConfirmText(this.context.getResources().getString(R.string.ok_trouble)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.5
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog3) {
                            String str;
                            if (BLEService.selected_lock_model.equals("GT1000")) {
                                String str2 = Helper_PhoneDetails.phonelangauge().equals("ja") ? "https://youtu.be/3YRorE8YkB4" : "https://youtu.be/XMxmr5Bi1OU";
                                Intent intent = new Intent("android.intent.action.VIEW");
                                intent.setData(Uri.parse(str2));
                                UI_BLE.this.context.startActivity(intent);
                                return;
                            }
                            if (BLEService.selected_lock_model.equals("GT1001")) {
                                str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video-luagge" : "http://www.egeetouch.com/support/video";
                                Intent intent2 = new Intent("android.intent.action.VIEW");
                                intent2.setData(Uri.parse(str));
                                UI_BLE.this.context.startActivity(intent2);
                            } else if (BLEService.selected_lock_model.equals("GT2000")) {
                                String str3 = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video-padlock" : "https://youtu.be/WGyX6n8fJKk";
                                Intent intent3 = new Intent("android.intent.action.VIEW");
                                intent3.setData(Uri.parse(str3));
                                UI_BLE.this.context.startActivity(intent3);
                            } else if (BLEService.selected_lock_model.equals("GT2002")) {
                                str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video-luagge" : "http://www.egeetouch.com/support/video";
                                Intent intent4 = new Intent("android.intent.action.VIEW");
                                intent4.setData(Uri.parse(str));
                                UI_BLE.this.context.startActivity(intent4);
                            } else if (BLEService.selected_lock_model.equals("GT2003")) {
                                str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video-luagge" : "http://www.egeetouch.com/support/video";
                                Intent intent5 = new Intent("android.intent.action.VIEW");
                                intent5.setData(Uri.parse(str));
                                UI_BLE.this.context.startActivity(intent5);
                            } else if (BLEService.selected_lock_model.equals("GT3000")) {
                                str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video-luagge" : "http://www.egeetouch.com/support/video";
                                Intent intent6 = new Intent("android.intent.action.VIEW");
                                intent6.setData(Uri.parse(str));
                                UI_BLE.this.context.startActivity(intent6);
                            } else if (BLEService.selected_lock_model.equals("GT3002")) {
                                str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video-luagge" : "https://youtu.be/0ttfg_jB4yo";
                                Intent intent7 = new Intent("android.intent.action.VIEW");
                                intent7.setData(Uri.parse(str));
                                UI_BLE.this.context.startActivity(intent7);
                            } else {
                                String str4 = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video" : "http://www.egeetouch.com/support/video";
                                Intent intent8 = new Intent("android.intent.action.VIEW");
                                intent8.setData(Uri.parse(str4));
                                UI_BLE.this.context.startActivity(intent8);
                            }
                        }
                    }).setCancelText(this.context.getResources().getString(R.string.continue_search)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.4
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog3) {
                            sweetAlertDialog3.dismissWithAnimation();
                            MainActivity.scanning_new_lock = false;
                            MainActivity.add_old_lock = false;
                            MainActivity.addlock_model = 0;
                            BLEService.cancel_scaning_triggered = true;
                            BLEService.Ble_Mode = BLEService.disconnect;
                            UI_BLE.BLE_UI = 0;
                        }
                    }).show();
                    return;
                } else if (z2 || having_trouble) {
                    return;
                } else {
                    having_trouble = false;
                    MainActivity.scanning_new_lock = false;
                    MainActivity.add_old_lock = false;
                    MainActivity.addlock_model = 0;
                    BLEService.cancel_scaning_triggered = true;
                    BLEService.Ble_Mode = BLEService.disconnect;
                    BLE_UI = 0;
                    SweetAlertDialog sweetAlertDialog3 = pd_pls_wait;
                    if (sweetAlertDialog3 != null && sweetAlertDialog3.isShowing()) {
                        pd_pls_wait.dismiss();
                    }
                    if (BLEService.wrong_password) {
                        BLEService.wrong_password = false;
                        try {
                            new SweetAlertDialog(this.context, 3).setTitleText(this.context.getResources().getString(R.string.error)).setContentText(this.context.getResources().getString(R.string.password_missmatch)).setConfirmText(this.context.getResources().getString(R.string.ok)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.6
                                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                public void onClick(SweetAlertDialog sweetAlertDialog4) {
                                    sweetAlertDialog4.dismissWithAnimation();
                                    MainActivity.current_icon = 0;
                                    if (UI_BLE.pd_pls_wait == null || !UI_BLE.pd_pls_wait.isShowing()) {
                                        return;
                                    }
                                    UI_BLE.pd_pls_wait.dismiss();
                                }
                            }).show();
                            return;
                        } catch (Exception e4) {
                            e4.printStackTrace();
                            return;
                        }
                    }
                    new SweetAlertDialog(this.context, 3).setTitleText(this.context.getResources().getString(R.string.error)).setContentText(this.context.getResources().getString(R.string.transmission_error_pls_try_again)).setConfirmText(this.context.getResources().getString(R.string.ok)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.7
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog4) {
                            sweetAlertDialog4.dismissWithAnimation();
                            MainActivity.current_icon = 0;
                            if (UI_BLE.pd_pls_wait == null || !UI_BLE.pd_pls_wait.isShowing()) {
                                return;
                            }
                            UI_BLE.pd_pls_wait.dismiss();
                        }
                    }).show();
                    return;
                }
            case 4:
                BLEService.update_UI_new_lock_status = true;
                if (BLEService.isShackleOpened) {
                    BLE_UI = 2;
                    return;
                } else {
                    BLE_UI = 0;
                    return;
                }
            case 5:
                this.reset_progress = new SweetAlertDialog(this.context, 5);
                if (BLEService.add_new_password_done) {
                    if (!BLEService.selected_lock_model.equals("GT2500") && !BLEService.selected_lock_model.equals("GT2550") && !BLEService.selected_lock_model.equals("GT5100") && !BLEService.selected_lock_model.equals("GT5107") && !BLEService.selected_lock_model.equals("GT5109") && !BLEService.selected_lock_model.equals("GT5300")) {
                        BLEService.Ble_Mode = BLEService.Read_masterTag_only;
                    }
                    final DatabaseReference child5 = FirebaseDatabase.getInstance().getReference("userLocks").child(MainActivity.user_uid);
                    child5.orderByChild("Name").equalTo(BLEService.selected_lock_name).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.16
                        @Override // com.google.firebase.database.ValueEventListener
                        public void onCancelled(DatabaseError databaseError) {
                        }

                        @Override // com.google.firebase.database.ValueEventListener
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                                String key = dataSnapshot2.getKey();
                                System.out.println("HEY key of lock whose name you are searching for " + key);
                                HashMap hashMap2 = new HashMap();
                                hashMap2.put("Password", UI_BLE.this.encryptPassword(BLEService.new_primary_password));
                                child5.child(key).updateChildren(hashMap2);
                            }
                        }
                    });
                    DatabaseVariable.db_lock.execSQL(DatabaseVariable.lockdb_update_new_password(BLEService.new_primary_password, BLEService.selected_lock_name));
                    BLEService.selected_lock_password = BLEService.new_primary_password;
                    Fragment_lock_setting.initial_setup = false;
                    this.reset_progress.dismiss();
                    try {
                        MainActivity.setting_progress.dismiss();
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                    new SweetAlertDialog(this.context, 2).setTitleText(this.context.getResources().getString(R.string.update_successfully)).show();
                } else {
                    new SweetAlertDialog(this.context, 1).setTitleText(this.context.getResources().getString(R.string.error_pls_try_again)).show();
                }
                BLE_UI = 0;
                return;
            case 6:
                try {
                    MainActivity.setting_progress.dismissWithAnimation();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
                new SweetAlertDialog(this.context, 2).setTitleText(this.context.getResources().getString(R.string.update_successfully)).show();
                BLE_UI = 0;
                return;
            case 7:
                try {
                    BLEService.selected_lock_state = true;
                    if (MainActivity.unlocking_pd != null && MainActivity.unlocking_pd.isShowing()) {
                        MainActivity.unlocking_pd.dismiss();
                    }
                    if (BLEService.locking_by_lock && !BLEService.selected_lock_model.equals("GT2002") && !BLEService.selected_lock_model.equals("GT2003") && (BLEService.pending_shut_down || BLEService.pending_disconnect)) {
                        ImageView imageView = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_locking);
                        if (imageView.getVisibility() == 0) {
                            imageView.setClickable(false);
                            imageView.setAlpha(75);
                        }
                        ImageView imageView2 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_tag);
                        if (imageView2.getVisibility() == 0) {
                            imageView2.setClickable(false);
                            imageView2.setAlpha(75);
                        }
                        ImageView imageView3 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_user);
                        if (imageView3.getVisibility() == 0) {
                            imageView3.setClickable(false);
                            imageView3.setAlpha(75);
                        }
                        ImageView imageView4 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_proximity);
                        if (imageView4.getVisibility() == 0) {
                            imageView4.setClickable(false);
                            imageView4.setAlpha(75);
                        }
                        ImageView imageView5 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_audit);
                        if (imageView5.getVisibility() == 0) {
                            imageView5.setClickable(false);
                            imageView5.setAlpha(75);
                        }
                        ImageView imageView6 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_backup);
                        if (imageView6.getVisibility() == 0) {
                            imageView6.setClickable(false);
                            imageView6.setAlpha(75);
                        }
                        ImageView imageView7 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_setting);
                        if (imageView6.getVisibility() == 0) {
                            imageView6.setClickable(false);
                            imageView6.setAlpha(75);
                        }
                        ImageView imageView8 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_in_sync);
                        if (imageView8.getVisibility() == 0) {
                            imageView8.setImageResource(R.drawable.sync_red);
                        }
                        TextView textView = (TextView) ((Activity) this.context).findViewById(R.id.button_countdown_timer);
                        if (textView.getVisibility() == 0) {
                            textView.setText(((Activity) this.context).getResources().getString(R.string.locking_triggered));
                        }
                        state = 2;
                        BLE_UI = 14;
                        if (Fragment_BLE.idle_count_down != null && Fragment_BLE.count_down_started) {
                            Fragment_BLE.count_down_started = false;
                            Fragment_BLE.idle_count_down.cancel();
                        }
                    } else if (BLEService.locking_by_lock && (BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2003"))) {
                        state = 1;
                        BLE_UI = 14;
                    } else {
                        state = 1;
                        BLE_UI = 14;
                    }
                } catch (Exception e7) {
                    Log.i("", e7.toString());
                }
                if (BLEService.locking_by_lock && !BLEService.selected_lock_model.equals("GT2002") && !BLEService.selected_lock_model.equals("GT2003")) {
                    new SweetAlertDialog(this.context).setTitleText(this.context.getResources().getString(R.string.shuting_down)).setContentText(this.context.getResources().getString(R.string.locking_triggered)).setConfirmText(this.context.getResources().getString(R.string.ok)).show();
                    Fragment_BLE.Ble_Mode = 0;
                    return;
                } else if (BLEService.pending_shut_down) {
                    BLEService.pending_shut_down = false;
                    BLEService.Ble_Mode = 112;
                    return;
                } else if (BLEService.pending_disconnect) {
                    BLEService.pending_disconnect = false;
                    BLEService.Ble_Mode = BLEService.disconnect;
                    return;
                } else if (BLEService.pending_vicinity_tracking) {
                    BLEService.pending_vicinity_tracking = false;
                    BLEService.Ble_Mode = 0;
                    return;
                } else {
                    return;
                }
            case 8:
                if (MainActivity.unlocking_pd == null || !MainActivity.unlocking_pd.isShowing()) {
                    return;
                }
                MainActivity.unlocking_pd.setContentText(this.context.getString(R.string.please_wait_audit));
                return;
            case 9:
            case 10:
                if (BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                    BLEService.Ble_Mode = 504;
                } else {
                    BLEService.Ble_Mode = 0;
                }
                Fragment_add_tag.refresh_listview_tag = true;
                BLE_UI = 23;
                update();
                BLE_UI = 0;
                return;
            case 11:
            case 12:
                Log.i("TAG", "update_UI_extract_user123");
                Fragment_manage_user.refresh_listview_user = true;
                BLE_UI = 23;
                update();
                BLE_UI = 0;
                return;
            case 13:
                Dialog dialog11 = shackle_dialog;
                if (dialog11 != null && dialog11.isShowing()) {
                    shackle_dialog.dismiss();
                }
                try {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.18
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                new SweetAlertDialog(UI_BLE.this.context).setTitleText(UI_BLE.this.context.getResources().getString(R.string.timeout)).setContentText(UI_BLE.this.context.getResources().getString(R.string.auto_shut_down_triggered)).setConfirmText(UI_BLE.this.context.getResources().getString(R.string.ok)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.18.1
                                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                    public void onClick(SweetAlertDialog sweetAlertDialog4) {
                                        sweetAlertDialog4.dismissWithAnimation();
                                        BLEService.Ble_Mode = 0;
                                        MainActivity.stopBackStack = false;
                                    }
                                }).show();
                            } catch (Exception e8) {
                                e8.printStackTrace();
                            }
                        }
                    });
                } catch (Exception unused2) {
                }
                BLE_UI = 0;
                return;
            case 14:
                Log.i("", "state: " + state);
                int i4 = state;
                if (i4 == 1) {
                    try {
                        if (BLEService.selected_lock_state) {
                            AlertDialog alertDialog = this.diag;
                            if (alertDialog != null && alertDialog.isShowing()) {
                                BLEService.isShackleOpened = false;
                                unlocking_msg_show = false;
                                this.diag.dismiss();
                            } else {
                                Dialog dialog12 = shackle_dialog;
                                if (dialog12 != null && dialog12.isShowing()) {
                                    shackle_dialog.dismiss();
                                    unlocking_msg_show = false;
                                    BLEService.isShackleOpened = false;
                                }
                            }
                        }
                        this.unlock_button = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_ble_unlock_button);
                        this.unlock_round_button = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_round_unlock);
                        this.lock_button = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_ble_lock_button);
                        this.lock_round_button = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_round_lock);
                        this.unlock_round_button.clearAnimation();
                        this.lock_round_button.clearAnimation();
                        this.lock_button.setVisibility(4);
                        this.lock_round_button.setVisibility(4);
                        this.unlock_button.setVisibility(0);
                        this.unlock_round_button.setVisibility(0);
                        this.unlock_button.setEnabled(true);
                        this.unlock_round_button.setEnabled(true);
                        this.unlock_button.setAlpha(255);
                        this.unlock_round_button.setAlpha(255);
                        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                        rotateAnimation.setRepeatMode(1);
                        rotateAnimation.setDuration(2500L);
                        rotateAnimation.setInterpolator(new LinearInterpolator());
                        rotateAnimation.setRepeatCount(-1);
                        this.unlock_round_button.startAnimation(rotateAnimation);
                    } catch (Exception e8) {
                        Log.i("", "update_locking_picture: " + e8.toString());
                    }
                } else if (i4 == 2) {
                    try {
                        if (BLEService.selected_lock_state) {
                            AlertDialog alertDialog2 = this.diag;
                            if (alertDialog2 != null && alertDialog2.isShowing()) {
                                BLEService.isShackleOpened = false;
                                unlocking_msg_show = false;
                                this.diag.dismiss();
                            } else {
                                Dialog dialog13 = shackle_dialog;
                                if (dialog13 != null && dialog13.isShowing()) {
                                    shackle_dialog.dismiss();
                                    unlocking_msg_show = false;
                                    BLEService.isShackleOpened = false;
                                }
                            }
                        }
                        ImageView imageView9 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_ble_unlock_button);
                        ImageView imageView10 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_round_unlock);
                        ImageView imageView11 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_round_lock);
                        imageView10.clearAnimation();
                        imageView11.clearAnimation();
                        ((ImageView) ((Activity) this.context).findViewById(R.id.imageView_ble_lock_button)).setVisibility(4);
                        imageView11.setVisibility(4);
                        imageView9.setVisibility(0);
                        imageView10.setVisibility(0);
                        imageView9.setAlpha(75);
                        imageView10.setAlpha(75);
                        imageView9.setEnabled(false);
                        imageView10.setEnabled(false);
                    } catch (Exception e9) {
                        Log.i("", "update_locking_picture: " + e9.toString());
                    }
                } else if (i4 == 3) {
                    try {
                        ImageView imageView12 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_ble_unlock_button);
                        ImageView imageView13 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_round_unlock);
                        ImageView imageView14 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_ble_lock_button);
                        ImageView imageView15 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_round_lock);
                        imageView13.clearAnimation();
                        imageView15.clearAnimation();
                        if (BLEService.selected_lock_model.equals("GT2002")) {
                            imageView14.setImageResource(R.drawable.unlock_green_gt2002);
                        } else {
                            imageView14.setImageResource(R.drawable.unlock_green);
                        }
                        imageView12.setVisibility(4);
                        imageView13.setVisibility(4);
                        imageView14.setVisibility(0);
                        imageView15.setVisibility(0);
                        imageView14.setEnabled(true);
                        imageView15.setEnabled(true);
                        imageView14.setAlpha(255);
                        imageView15.setAlpha(255);
                        RotateAnimation rotateAnimation2 = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                        rotateAnimation2.setRepeatMode(1);
                        rotateAnimation2.setDuration(2500L);
                        rotateAnimation2.setInterpolator(new LinearInterpolator());
                        rotateAnimation2.setRepeatCount(-1);
                        imageView15.startAnimation(rotateAnimation2);
                    } catch (Exception e10) {
                        Log.i("", "update_locking_picture: " + e10.toString());
                    }
                } else if (i4 == 4) {
                    try {
                        ImageView imageView16 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_ble_unlock_button);
                        ImageView imageView17 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_round_unlock);
                        ImageView imageView18 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_ble_lock_button);
                        ImageView imageView19 = (ImageView) ((Activity) this.context).findViewById(R.id.imageView_round_lock);
                        imageView17.clearAnimation();
                        imageView19.clearAnimation();
                        if (BLEService.selected_lock_model.equals("GT2002")) {
                            imageView18.setImageResource(R.drawable.unlock_green_gt2002);
                        } else {
                            imageView18.setImageResource(R.drawable.unlock_green);
                        }
                        imageView16.setVisibility(4);
                        imageView17.setVisibility(4);
                        imageView18.setVisibility(0);
                        imageView19.setVisibility(0);
                        imageView18.setAlpha(128);
                        imageView19.setAlpha(128);
                        imageView18.setEnabled(false);
                        imageView19.setEnabled(false);
                    } catch (Exception e11) {
                        Log.i("", "update_locking_picture: " + e11.toString());
                    }
                }
                BLE_UI = 0;
                return;
            case 15:
                if (!timeout_msg_shown && BLEService.mConnectionState == 0 && (MainActivity.scanning_new_lock || MainActivity.add_old_lock)) {
                    timeout_msg_shown = true;
                    try {
                        new SweetAlertDialog(this.context, 0).setTitleText(this.context.getResources().getString(R.string.having_trouble)).setContentText(this.context.getResources().getString(R.string.learn_how_to)).setConfirmText(this.context.getResources().getString(R.string.ok_trouble)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.9
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog4) {
                                String str;
                                if (BLEService.selected_lock_model.equals("GT1000")) {
                                    String str2 = Helper_PhoneDetails.phonelangauge().equals("ja") ? "https://youtu.be/3YRorE8YkB4" : "https://youtu.be/XMxmr5Bi1OU";
                                    Intent intent = new Intent("android.intent.action.VIEW");
                                    intent.setData(Uri.parse(str2));
                                    UI_BLE.this.context.startActivity(intent);
                                    return;
                                }
                                if (BLEService.selected_lock_model.equals("GT1001")) {
                                    str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video-luagge" : "http://www.egeetouch.com/support/video";
                                    Intent intent2 = new Intent("android.intent.action.VIEW");
                                    intent2.setData(Uri.parse(str));
                                    UI_BLE.this.context.startActivity(intent2);
                                } else if (BLEService.selected_lock_model.equals("GT2000")) {
                                    String str3 = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video-padlock" : "https://youtu.be/WGyX6n8fJKk";
                                    Intent intent3 = new Intent("android.intent.action.VIEW");
                                    intent3.setData(Uri.parse(str3));
                                    UI_BLE.this.context.startActivity(intent3);
                                } else if (BLEService.selected_lock_model.equals("GT2002")) {
                                    str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video-luagge" : "http://www.egeetouch.com/support/video";
                                    Intent intent4 = new Intent("android.intent.action.VIEW");
                                    intent4.setData(Uri.parse(str));
                                    UI_BLE.this.context.startActivity(intent4);
                                } else if (BLEService.selected_lock_model.equals("GT2003")) {
                                    str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video-luagge" : "http://www.egeetouch.com/support/video";
                                    Intent intent5 = new Intent("android.intent.action.VIEW");
                                    intent5.setData(Uri.parse(str));
                                    UI_BLE.this.context.startActivity(intent5);
                                } else if (BLEService.selected_lock_model.equals("GT3000")) {
                                    str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video-luagge" : "http://www.egeetouch.com/support/video";
                                    Intent intent6 = new Intent("android.intent.action.VIEW");
                                    intent6.setData(Uri.parse(str));
                                    UI_BLE.this.context.startActivity(intent6);
                                } else if (BLEService.selected_lock_model.equals("GT3002")) {
                                    str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video-luagge" : "https://youtu.be/0ttfg_jB4yo";
                                    Intent intent7 = new Intent("android.intent.action.VIEW");
                                    intent7.setData(Uri.parse(str));
                                    UI_BLE.this.context.startActivity(intent7);
                                } else {
                                    String str4 = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video" : "http://www.egeetouch.com/support/video";
                                    Intent intent8 = new Intent("android.intent.action.VIEW");
                                    intent8.setData(Uri.parse(str4));
                                    UI_BLE.this.context.startActivity(intent8);
                                }
                            }
                        }).setCancelText(this.context.getResources().getString(R.string.continue_search)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.8
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog4) {
                                sweetAlertDialog4.dismissWithAnimation();
                                UI_BLE.timeout_msg_shown = false;
                                MainActivity.start_scanning = true;
                            }
                        }).show();
                    } catch (Exception e12) {
                        e12.printStackTrace();
                    }
                } else if (BLEService.mConnectionState == 0 && !MainActivity.scanning_new_lock) {
                    MainActivity.current_icon = 0;
                    BLE_UI = 19;
                    update();
                    return;
                }
                BLE_UI = 0;
                return;
            case 16:
                try {
                    new SweetAlertDialog(this.context).setTitleText(this.context.getResources().getString(R.string.power_off)).setContentText(this.context.getResources().getString(R.string.lock_is_power_off_soon)).setConfirmText(this.context.getResources().getString(R.string.ok)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.17
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog4) {
                            sweetAlertDialog4.dismissWithAnimation();
                            if (Helper_Network.haveNetworkConnection(UI_BLE.this.context)) {
                                boolean z3 = MainActivity.isPackageInfoFound;
                            }
                        }
                    }).show();
                } catch (Exception unused3) {
                    Context context = this.context;
                    Toast.makeText(context, context.getResources().getString(R.string.lock_is_power_off_soon), 0).show();
                }
                Fragment_BLE.shutdown_trigger = false;
                BLEService.Ble_Mode = 0;
                BLEService.selectedLockIP45Serial = "";
                BLE_UI = 0;
                return;
            case 17:
                MediaPlayer create = MediaPlayer.create(this.context, (int) R.raw.off);
                create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.19
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.release();
                    }
                });
                create.start();
                ((Vibrator) this.context.getSystemService("vibrator")).vibrate(400L);
                BLE_UI = 0;
                return;
            case 18:
                SweetAlertDialog sweetAlertDialog4 = new SweetAlertDialog(this.context, 5);
                pd_pls_wait = sweetAlertDialog4;
                sweetAlertDialog4.getProgressHelper().setBarColor(Color.parseColor("#389ae0"));
                pd_pls_wait.setTitleText("");
                pd_pls_wait.setContentText(pls_wait_content);
                pd_pls_wait.setCancelable(false);
                pd_pls_wait.setCancelText(this.context.getResources().getString(R.string.cancel));
                pd_pls_wait.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.20
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog5) {
                        sweetAlertDialog5.dismissWithAnimation();
                        MainActivity.current_icon = 0;
                        BLEService.cancel_scaning_triggered = true;
                        BLEService.Ble_Mode = BLEService.disconnect;
                    }
                });
                pd_pls_wait.show();
                BLE_UI = 0;
                return;
            case 19:
                SweetAlertDialog sweetAlertDialog5 = pd_pls_wait;
                if (sweetAlertDialog5 != null && sweetAlertDialog5.isShowing()) {
                    try {
                        pd_pls_wait.dismiss();
                    } catch (Exception e13) {
                        e13.printStackTrace();
                    }
                }
                BLE_UI = 0;
                return;
            case 20:
                new SweetAlertDialog(this.context, 3).setTitleText(this.context.getResources().getString(R.string.one_last_step)).setContentText(this.context.getResources().getString(R.string.pls_change_dafult_pw)).show();
                BLE_UI = 0;
                return;
            case 21:
                Fragment_manage_audit_trail.refresh_listview_audit = true;
                BLE_UI = 23;
                update();
                BLE_UI = 0;
                return;
            case 22:
                Dialog dialog14 = new Dialog(this.context);
                cmd_pls_wait_extract = dialog14;
                dialog14.requestWindowFeature(1);
                cmd_pls_wait_extract.setContentView(R.layout.dialog_wait);
                cmd_pls_wait_extract.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                cmd_pls_wait_extract.getWindow().setLayout((int) (this.context.getResources().getDisplayMetrics().widthPixels * 0.8d), -2);
                this.progress_percent = (TextView) cmd_pls_wait_extract.findViewById(R.id.wait_percent);
                this.progress_bar = (ProgressBar) cmd_pls_wait_extract.findViewById(R.id.wait_progress);
                progress_val = 0;
                this.progress_percent.setText(String.valueOf(progress_val) + "%");
                ((TextView) cmd_pls_wait_extract.findViewById(R.id.wait_content)).setText(pls_wait_content);
                this.progress_bar.setProgress(progress_val);
                cmd_pls_wait_extract.setCanceledOnTouchOutside(false);
                cmd_pls_wait_extract.setCancelable(false);
                ((Button) cmd_pls_wait_extract.findViewById(R.id.wait_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.21
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        BLEService.Ble_Mode = 0;
                        MainActivity.current_icon = 0;
                        MainActivity.fragmentManager.popBackStack();
                        UI_BLE.cmd_pls_wait_extract.dismiss();
                    }
                });
                cmd_pls_wait_extract.show();
                BLE_UI = 0;
                return;
            case 23:
                SweetAlertDialog sweetAlertDialog6 = pd_pls_wait_extract;
                if (sweetAlertDialog6 != null && sweetAlertDialog6.isShowing()) {
                    pd_pls_wait_extract.dismiss();
                }
                if (cmd_pls_wait_extract.isShowing() && (dialog = cmd_pls_wait_extract) != null) {
                    dialog.dismiss();
                }
                BLE_UI = 0;
                return;
            case 24:
                SweetAlertDialog sweetAlertDialog7 = pd_pls_wait_extract;
                if (sweetAlertDialog7 != null && sweetAlertDialog7.isShowing()) {
                    pd_pls_wait_extract.setContentText(pls_wait_content);
                }
                if (!cmd_pls_wait_extract.isShowing() || (dialog2 = cmd_pls_wait_extract) == null) {
                    return;
                }
                this.progress_percent = (TextView) dialog2.findViewById(R.id.wait_percent);
                ProgressBar progressBar = (ProgressBar) cmd_pls_wait_extract.findViewById(R.id.wait_progress);
                this.progress_bar = progressBar;
                progressBar.setProgress(progress_val);
                this.progress_percent.setText(String.valueOf(progress_val) + "%");
                return;
            case 25:
                SweetAlertDialog sweetAlertDialog8 = pd_pls_wait_extract;
                if (sweetAlertDialog8 != null && sweetAlertDialog8.isShowing()) {
                    pd_pls_wait_extract.setContentText(pls_wait_content);
                }
                if (!cmd_pls_wait_extract.isShowing() || (dialog3 = cmd_pls_wait_extract) == null) {
                    return;
                }
                this.progress_percent = (TextView) dialog3.findViewById(R.id.wait_percent);
                ProgressBar progressBar2 = (ProgressBar) cmd_pls_wait_extract.findViewById(R.id.wait_progress);
                this.progress_bar = progressBar2;
                progressBar2.setProgress(progress_val);
                this.progress_percent.setText(String.valueOf(progress_val) + "%");
                return;
            case 26:
                break;
            case 27:
                MainActivity.addlock_model = 0;
                BLEService.cancel_scaning_triggered = true;
                BLEService.Ble_Mode = 112;
                add_lock_mode = false;
                SweetAlertDialog sweetAlertDialog9 = new SweetAlertDialog(this.context, 3);
                sweetAlertDialog9.setTitleText("Note");
                sweetAlertDialog9.setContentText("Please update your app to the latest version from the play store inorder to support ");
                sweetAlertDialog9.setConfirmText("okay");
                sweetAlertDialog9.setCancelable(false);
                sweetAlertDialog9.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.22
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog10) {
                        System.out.println("Hello checking the Adding lock !");
                        UI_BLE.BLE_UI = 0;
                        sweetAlertDialog10.dismiss();
                    }
                }).show();
                BLE_UI = 0;
                break;
            case 28:
                System.out.println("Hello checking the Passcode Success2!!");
                SweetAlertDialog sweetAlertDialog10 = pd_pls_wait_extract;
                if (sweetAlertDialog10 != null && sweetAlertDialog10.isShowing()) {
                    pd_pls_wait_extract.dismiss();
                }
                if (cmd_pls_wait_extract.isShowing() && (dialog4 = cmd_pls_wait_extract) != null) {
                    dialog4.dismiss();
                }
                String str = BLEService.selected_lock_version;
                SweetAlertDialog sweetAlertDialog11 = new SweetAlertDialog(MainActivity.context, 2);
                sweetAlertDialog11.setTitleText(this.context.getString(R.string.added));
                sweetAlertDialog11.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.24
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog12) {
                        sweetAlertDialog12.dismiss();
                        MainActivity.fragmentManager.popBackStack();
                    }
                });
                sweetAlertDialog11.setCancelable(false);
                sweetAlertDialog11.show();
                BLE_UI = 0;
                return;
            case 29:
                SweetAlertDialog sweetAlertDialog12 = pd_pls_wait_extract;
                if (sweetAlertDialog12 != null && sweetAlertDialog12.isShowing()) {
                    pd_pls_wait_extract.dismiss();
                }
                if (cmd_pls_wait_extract.isShowing() && (dialog5 = cmd_pls_wait_extract) != null) {
                    dialog5.dismiss();
                }
                new SweetAlertDialog(MainActivity.context, 3).setTitleText(MainActivity.context.getResources().getString(R.string.update_failed)).show();
                BLE_UI = 0;
                return;
            case 30:
                SweetAlertDialog sweetAlertDialog13 = pd_pls_wait_extract;
                if (sweetAlertDialog13 != null && sweetAlertDialog13.isShowing()) {
                    pd_pls_wait_extract.dismiss();
                }
                if (cmd_pls_wait_extract.isShowing() && (dialog6 = cmd_pls_wait_extract) != null) {
                    dialog6.dismiss();
                }
                try {
                    Fragment_online_logs.showAuditTrail();
                } catch (Exception e14) {
                    Log.i("AuditTrail", "Error : " + e14);
                }
                BLE_UI = 0;
                return;
            case 31:
            case 32:
            default:
                BLE_UI = 0;
                return;
            case 33:
                SweetAlertDialog sweetAlertDialog14 = pd_pls_wait_extract;
                if (sweetAlertDialog14 != null && sweetAlertDialog14.isShowing()) {
                    pd_pls_wait_extract.dismiss();
                }
                if (cmd_pls_wait_extract.isShowing() && (dialog7 = cmd_pls_wait_extract) != null) {
                    dialog7.dismiss();
                }
                BLE_UI = 0;
                Fragment_Manage_Passcode.InitializerFields();
                return;
            case 34:
                SweetAlertDialog sweetAlertDialog15 = pd_pls_wait_extract;
                if (sweetAlertDialog15 != null && sweetAlertDialog15.isShowing()) {
                    pd_pls_wait_extract.dismiss();
                }
                if (cmd_pls_wait_extract.isShowing() && (dialog8 = cmd_pls_wait_extract) != null) {
                    dialog8.dismiss();
                }
                new SweetAlertDialog(MainActivity.context, 2).setTitleText(MainActivity.context.getResources().getString(R.string.update_successfully)).show();
                BLE_UI = 0;
                return;
        }
        final SweetAlertDialog sweetAlertDialog16 = new SweetAlertDialog(this.context);
        sweetAlertDialog16.setTitle("Tag ID");
        sweetAlertDialog16.setContentText(BLEService.Detected_tag_id);
        sweetAlertDialog16.setCancelable(false);
        sweetAlertDialog16.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.23
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog17) {
                sweetAlertDialog16.cancel();
            }
        });
        sweetAlertDialog16.show();
        BLE_UI = 0;
    }

    private void showPopUpToKeyInPrimaryPassword(final boolean z) {
        System.out.println("Hello checking the lock ver 15");
        Dialog dialog = new Dialog(this.context);
        primaryPassword_popUp = dialog;
        dialog.setContentView(R.layout.primary_password_pop_up);
        Dialog dialog2 = primaryPassword_popUp;
        if (dialog2 != null && dialog2.isShowing()) {
            primaryPassword_popUp.dismiss();
        }
        primaryPassword_popUp.setCancelable(false);
        primaryPassword_popUp.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        TextView textView = (TextView) primaryPassword_popUp.findViewById(R.id.pop_up_titleTV);
        TextView textView2 = (TextView) primaryPassword_popUp.findViewById(R.id.fgt_primary_pw);
        final EditText editText = (EditText) primaryPassword_popUp.findViewById(R.id.ed_primary_password);
        Button button = (Button) primaryPassword_popUp.findViewById(R.id.button_startPairing);
        ImageView imageView = (ImageView) primaryPassword_popUp.findViewById(R.id.img_closePopUp);
        FirebaseDatabase.getInstance().getReference("masterTagID").child(BLEService.parsedIp45SerialNumber).child(MainActivity.user_uid);
        if (z) {
            textView.setText(R.string.pls_enter_primary_usesr_pw);
        } else {
            textView.setText(R.string.wrong_pw_pls_enter_primary_usesr_pw);
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString() != "" && editText.getText().toString().length() == 6) {
                    if (z) {
                        MainActivity.add_old_lock = true;
                        UI_BLE.add_lock_mode = true;
                    }
                    BLEService.selected_lock_password = editText.getText().toString();
                    BLEService.Ble_Mode = 97;
                    UI_BLE.BLE_UI = 0;
                    UI_BLE.primaryPassword_popUp.dismiss();
                    UI_BLE.this.showAddLockWaitingForPasswordConfirmationAfterSubmittingPasswordLoadingDialog();
                    return;
                }
                editText.setError(UI_BLE.this.context.getResources().getString(R.string.password_invalid));
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.scanning_new_lock = false;
                MainActivity.add_old_lock = false;
                MainActivity.addlock_model = 0;
                BLEService.cancel_scaning_triggered = true;
                BLEService.Ble_Mode = 112;
                UI_BLE.BLE_UI = 0;
                UI_BLE.primaryPassword_popUp.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UI_BLE.primaryPassword_popUp.dismiss();
                UI_BLE.this.showAuthenticationErrorDialog();
            }
        });
        SweetAlertDialog sweetAlertDialog = pw_dialog;
        if (sweetAlertDialog != null) {
            try {
                sweetAlertDialog.dismissWithAnimation();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            primaryPassword_popUp.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAddLockWaitingForPasswordConfirmationAfterSubmittingPasswordLoadingDialog() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this.context, 5);
        pw_dialog = sweetAlertDialog;
        sweetAlertDialog.setTitleText(this.context.getResources().getString(R.string.please_wait));
        pw_dialog.setCanceledOnTouchOutside(false);
        pw_dialog.show();
    }

    public void showAuthenticationDialog() {
        Dialog dialog = new Dialog(this.context);
        this.formDialog = dialog;
        dialog.setContentView(R.layout.tagid_authentication);
        this.formDialog.setCancelable(false);
        this.formDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        final DatabaseReference child = FirebaseDatabase.getInstance().getReference("masterTagID").child(BLEService.parsedIp45SerialNumber).child(MainActivity.user_uid).child("tagID");
        final EditText editText = (EditText) this.formDialog.findViewById(R.id.tagid);
        editText.addTextChangedListener(new TextWatcher() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.28
            int prevL = 0;

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                this.prevL = editText.getText().toString().length();
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                int length = editable.length();
                if (this.prevL >= length || length != 2) {
                    return;
                }
                UI_BLE.this.tagID_input = editText.getText().toString();
                editText.setText(UI_BLE.this.tagID_input + " ");
                editText.setSelection(length + 1);
            }
        });
        ((Button) this.formDialog.findViewById(R.id.sent_mailgun)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                final String upperCase = editText.getText().toString().replace(" ", "").toUpperCase();
                System.out.println("CHECKING TAG ID INPUT :" + upperCase);
                System.out.println("CHECKING TAG ID INPUT :" + child);
                UI_BLE.this.progress = new SweetAlertDialog(UI_BLE.this.context, 5);
                UI_BLE.this.progress.setTitleText("Loading...");
                UI_BLE.this.progress.setCancelable(false);
                UI_BLE.this.progress.show();
                child.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.29.1
                    @Override // com.google.firebase.database.ValueEventListener
                    public void onCancelled(DatabaseError databaseError) {
                    }

                    @Override // com.google.firebase.database.ValueEventListener
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue().toString().equals(upperCase)) {
                            UI_BLE.this.progress.dismiss();
                            UI_BLE.this.formDialog.dismiss();
                            UI_BLE.this.showResetPrimaryPassword();
                            return;
                        }
                        UI_BLE.this.showFailedResetPassword();
                        UI_BLE.this.progress.dismiss();
                        UI_BLE.this.formDialog.dismiss();
                    }
                });
            }
        });
        ((Button) this.formDialog.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.30
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UI_BLE.this.formDialog.dismiss();
                MainActivity.scanning_new_lock = false;
                MainActivity.add_old_lock = false;
                MainActivity.addlock_model = 0;
                BLEService.cancel_scaning_triggered = true;
                BLEService.Ble_Mode = 112;
                UI_BLE.BLE_UI = 0;
            }
        });
        this.formDialog.show();
    }

    public void showFailedResetPassword() {
        Dialog dialog = new Dialog(this.context);
        this.resetpassword_failed = dialog;
        dialog.setContentView(R.layout.resetpasswordfailed);
        this.resetpassword_failed.setCancelable(false);
        this.resetpassword_failed.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.resetpassword_failed.show();
        ((Button) this.resetpassword_failed.findViewById(R.id.button_contactUs)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.31
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UI_BLE.this.resetpassword_failed.dismiss();
                UI_BLE.this.showContactUs();
            }
        });
        ((Button) this.resetpassword_failed.findViewById(R.id.button_cancell)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.32
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UI_BLE.this.resetpassword_failed.dismiss();
                MainActivity.scanning_new_lock = false;
                MainActivity.add_old_lock = false;
                MainActivity.addlock_model = 0;
                BLEService.cancel_scaning_triggered = true;
                BLEService.Ble_Mode = 112;
                UI_BLE.BLE_UI = 0;
            }
        });
    }

    public void showResetPrimaryPassword() {
        Dialog dialog = new Dialog(this.context);
        this.resetpassword = dialog;
        dialog.setContentView(R.layout.reset_primary_password);
        this.resetpassword.setCancelable(false);
        this.resetpassword.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.resetpassword.show();
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this.context, 5);
        this.reset_progress = sweetAlertDialog;
        sweetAlertDialog.setCancelable(false);
        this.reset_progress.setTitleText("Loading...");
        ((Button) this.resetpassword.findViewById(R.id.button_reset)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.33
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UI_BLE.this.btn_update_new_primary_passwordd();
                UI_BLE.this.reset_progress.show();
            }
        });
        ((Button) this.resetpassword.findViewById(R.id.button_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.34
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UI_BLE.this.resetpassword.dismiss();
                MainActivity.scanning_new_lock = false;
                MainActivity.add_old_lock = false;
                MainActivity.addlock_model = 0;
                BLEService.cancel_scaning_triggered = true;
                BLEService.Ble_Mode = 112;
                UI_BLE.BLE_UI = 0;
            }
        });
    }

    public void showContactUs() {
        Dialog dialog = new Dialog(this.context);
        this.contact_us = dialog;
        dialog.setContentView(R.layout.contact_us);
        this.contact_us.setCancelable(false);
        this.contact_us.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.contact_us.show();
        final Button button = (Button) this.contact_us.findViewById(R.id.sent_mailgunn);
        final Button button2 = (Button) this.contact_us.findViewById(R.id.btn_cancell);
        final EditText editText = (EditText) this.contact_us.findViewById(R.id.tagidd);
        ((TextView) this.contact_us.findViewById(R.id.email_customerr)).setText("Email\t\t\t\t:\t" + MainActivity.email);
        ((TextView) this.contact_us.findViewById(R.id.lockseriall)).setText("Lock Serial\t:\t" + BLEService.parsedIp45SerialNumber);
        editText.addTextChangedListener(new TextWatcher() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.35
            int prevL = 0;

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                this.prevL = editText.getText().toString().length();
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                int length = editable.length();
                if (this.prevL >= length || length != 2) {
                    return;
                }
                UI_BLE.this.tagID_input = editText.getText().toString();
                editText.setText(UI_BLE.this.tagID_input + " ");
                editText.setSelection(length + 1);
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.36
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UI_BLE.this.sendViaMailGun(BLEService.parsedIp45SerialNumber, editText.getText().toString());
                button.setEnabled(false);
                button2.setText("Done");
                button.setVisibility(8);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.37
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UI_BLE.this.contact_us.dismiss();
                MainActivity.scanning_new_lock = false;
                MainActivity.add_old_lock = false;
                MainActivity.addlock_model = 0;
                BLEService.cancel_scaning_triggered = true;
                BLEService.Ble_Mode = 112;
                UI_BLE.BLE_UI = 0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAuthenticationErrorDialog() {
        System.out.println("Hello checking the lock ver 14");
        final Dialog dialog = new Dialog(this.context);
        dialog.setContentView(R.layout.authentication_error);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        ((Button) dialog.findViewById(R.id.button_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.38
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
                MainActivity.scanning_new_lock = false;
                MainActivity.add_old_lock = false;
                MainActivity.addlock_model = 0;
                BLEService.cancel_scaning_triggered = true;
                BLEService.Ble_Mode = 112;
                UI_BLE.BLE_UI = 0;
            }
        });
        ((Button) dialog.findViewById(R.id.button_contactUs)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.39
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String[] strArr = {UI_BLE.this.context.getResources().getString(R.string.customer_support_email)};
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setData(Uri.parse(MailTo.MAILTO_SCHEME));
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.EMAIL", strArr);
                intent.putExtra("android.intent.extra.SUBJECT", UI_BLE.this.context.getResources().getString(R.string.authentcation_err_email_sub));
                intent.putExtra("android.intent.extra.TEXT", UI_BLE.this.context.getResources().getString(R.string.authentcation_err_email_content));
                try {
                    UI_BLE.this.context.startActivity(Intent.createChooser(intent, "Send mail..."));
                    UI_BLE.this.finish();
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(UI_BLE.this.context, "There is no email client installed.", 0).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void btn_update_new_primary_passwordd() {
        if (BLEService.selected_lock_role == 1) {
            new SweetAlertDialog(this.context, 3).setTitleText(this.context.getResources().getString(R.string.user_logged_in)).setContentText(this.context.getResources().getString(R.string.please_login_as_admin)).setConfirmText(this.context.getResources().getString(R.string.ok)).show();
            return;
        }
        EditText editText = (EditText) this.resetpassword.findViewById(R.id.reset_p_password);
        EditText editText2 = (EditText) this.resetpassword.findViewById(R.id.reset_p_password_confirm);
        System.out.println("PRINTKAN " + editText.getText().toString());
        if (!editText.getText().toString().equals("") && !editText2.getText().toString().equals("")) {
            if (editText.getText().toString().equals(editText2.getText().toString())) {
                if (editText.getText().length() >= 6) {
                    if (DatabaseVariable.db_user.rawQuery(DatabaseVariable.userdb_Query_password_exist_in_lock(BLEService.selected_lock_name, editText.getText().toString()), null).moveToNext()) {
                        editText.setError(this.context.getResources().getString(R.string.password_used));
                        return;
                    }
                    BLEService.new_primary_password = editText.getText().toString();
                    if (BLEService.new_primary_password.equals("123456")) {
                        editText.setError(this.context.getResources().getString(R.string.default_password_is_not_allowed));
                        return;
                    }
                    BLEService.Ble_Mode = 85;
                    backup_lock_password_firebase(BLEService.new_primary_password);
                    return;
                }
                editText.setError(this.context.getResources().getString(R.string.password_length));
                return;
            }
            editText2.setError(this.context.getResources().getString(R.string.password_is_not_identical));
            return;
        }
        editText.setError(this.context.getResources().getString(R.string.please_key_in_password));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendViaMailGun(String str, String str2) {
        String uuid;
        String str3 = "{\n    \"email\": \"" + MainActivity.email + "\",\n    \"lockserial\": \"" + BLEService.parsedIp45SerialNumber + "\",\n    \"tagid\": \"" + str2 + "\",\n    \"caseid\": \"A-" + UUID.randomUUID().toString().substring(uuid.length() - 6) + "\"\n}";
        System.out.println("Hello checking the mailGun variables:" + str3);
        RetrofitClient2.getInstance().getApi().sendEmail("The eGeeTouch Customer <" + MainActivity.email + ">", "crazyfuryy@gmail.com", "reset primary password", "", "test123", str3).enqueue(new Callback<ResponseBody>() { // from class: com.egeetouch.egeetouch_manager.UI_BLE.41
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("Hello checking the mailGun Response:" + response);
                if (response.code() == 200) {
                    try {
                        System.out.println("Hello checking the mailGun Response 1:" + response);
                        Toast.makeText(UI_BLE.this.context, new JSONObject(response.body().string()).getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE), 1).show();
                        System.out.println("Redemption Code Sent");
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        System.out.println("Redemption Code Failed Sent");
                    }
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                Toast.makeText(MainActivity.context, th.getMessage(), 1).show();
                System.out.println("Redemption Code Failed");
            }
        });
    }

    public void backup_lock_password_firebase(String str) {
        System.out.println("Hello backup_password" + str);
        if (BLEService.parsedIp45SerialNumber.equals("")) {
            return;
        }
        System.out.println("Hello shooting to Firebase LOCKED status with serial of " + BLEService.parsedIp45SerialNumber);
        MainActivity.currentTimestampDouble = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() * 1000);
        DatabaseReference push = Firebase_Data_management.database.getReference("passwordBackups").child(BLEService.parsedIp45SerialNumber).push();
        push.child("changedOn").setValue(Double.valueOf(MainActivity.currentTimestampDouble));
        push.child("password").setValue(encryptPassword(str));
    }
}
