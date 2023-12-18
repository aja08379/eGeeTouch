package com.egeetouch.egeetouch_manager;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.work.WorkRequest;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import java.io.File;
import java.io.FileOutputStream;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.dfu.DfuProgressListener;
import no.nordicsemi.android.dfu.DfuProgressListenerAdapter;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import no.nordicsemi.android.dfu.DfuServiceListenerHelper;
/* loaded from: classes.dex */
public class DfuActivity extends AppCompatActivity {
    public static String affectedLockAddress = "";
    public static String affectedLockName = "";
    public static boolean connectAffected = false;
    public static boolean foundAffected = false;
    TextView additional_msg;
    ScrollView before_update;
    Button btn_scan;
    Button btn_update;
    String database_version;
    String dfu_filename;
    ProgressBar file_progress;
    TextView file_text;
    LinearLayout ll_tips;
    BLEService mService;
    ProgressBar overall_progress;
    TextView overall_text;
    LinearLayout repair_mode;
    LinearLayout repair_progress;
    TextView scan_text;
    SweetAlertDialog swdialog_to;
    TextView tv_current_version;
    TextView tv_database_version;
    TextView tv_tips;
    ScrollView uploading;
    TextView warning_text;
    boolean isUpdating = false;
    Handler handler = new Handler();
    Handler handlerTips = new Handler();
    Handler handlerTimeout = new Handler();
    int tips_index = 0;
    int[] tips_arr = {R.string.before_update_msg2_2, R.string.before_update_msg3, R.string.before_update_msg4};
    private final DfuProgressListener mDfuProgressListener = new DfuProgressListenerAdapter() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.3
        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onDeviceConnecting(String str) {
            DfuActivity.this.overall_progress.setProgress(50);
            DfuActivity.this.overall_text.setText(DfuActivity.this.getString(R.string.preparing_upload));
            DfuActivity.this.handlerTimeout.postDelayed(DfuActivity.this.runTimeout, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onDfuProcessStarting(String str) {
            System.out.println("CMDCMD onDfuProcessStarting");
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onEnablingDfuMode(String str) {
            System.out.println("CMDCMD onEnablingDfuMode");
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onDfuProcessStarted(String str) {
            DfuActivity.this.overall_progress.setProgress(75);
            DfuActivity.this.overall_text.setText(DfuActivity.this.getString(R.string.uploading_files));
            try {
                DfuActivity.this.handlerTimeout.removeCallbacks(DfuActivity.this.runTimeout);
                if (!DfuActivity.this.swdialog_to.isShowing() || DfuActivity.this.swdialog_to == null) {
                    return;
                }
                DfuActivity.this.swdialog_to.dismissWithAnimation();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onProgressChanged(String str, int i, float f, float f2, int i2, int i3) {
            super.onProgressChanged(str, i, f, f2, i2, i3);
            DfuActivity.this.file_progress.setProgress(i);
            DfuActivity.this.file_text.setText(DfuActivity.this.getString(R.string.upload_percent) + " " + i + "%");
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onDfuCompleted(String str) {
            DfuActivity.this.overall_progress.setProgress(100);
            new Handler().postDelayed(new Runnable() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.3.1
                @Override // java.lang.Runnable
                public void run() {
                    System.out.println("CMDCMD onDfuCompleted");
                    DfuActivity.this.overall_text.setText(R.string.update_completed);
                    DfuActivity.this.showSuccessDialog();
                }
            }, 200L);
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
        public void onError(String str, final int i, final int i2, final String str2) {
            new Handler().postDelayed(new Runnable() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.3.2
                @Override // java.lang.Runnable
                public void run() {
                    System.out.println("CMDCMD onError: " + i + " " + i2 + " " + str2);
                    DfuActivity.this.overall_text.setText(DfuActivity.this.getString(R.string.update_failed) + "\n" + str2);
                    DfuActivity.this.overall_text.setTextColor(DfuActivity.this.getResources().getColor(R.color.red));
                    DfuActivity.this.showFailedDialog(2);
                }
            }, 200L);
        }
    };
    Runnable runRepair = new Runnable() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.12
        @Override // java.lang.Runnable
        public void run() {
            if (DfuActivity.foundAffected) {
                DfuActivity.foundAffected = false;
                DfuActivity.this.scan_text.setText(DfuActivity.this.getString(R.string.found_affected_lock));
                DfuActivity.this.isUpdating = true;
            } else if (DfuActivity.connectAffected) {
                DfuActivity.connectAffected = false;
                DfuActivity.this.btn_scan.setVisibility(8);
                DfuActivity.this.btn_update.setVisibility(0);
                DfuActivity.this.btn_update.setText(R.string.repair_firmware);
                DfuActivity.this.repair_progress.setVisibility(8);
                DfuActivity.this.btn_update.setClickable(true);
                DfuActivity.this.btn_update.setAlpha(1.0f);
                DfuActivity.this.isUpdating = false;
                DfuActivity.this.handler.removeCallbacks(this);
                return;
            }
            DfuActivity.this.handler.postDelayed(this, 100L);
        }
    };
    Runnable runTips = new AnonymousClass13();
    Runnable runTimeout = new Runnable() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.14
        @Override // java.lang.Runnable
        public void run() {
            DfuActivity.this.showFailedDialog(1);
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(DfuActivity.this);
            Intent intent = new Intent(DfuBaseService.BROADCAST_ACTION);
            intent.putExtra(DfuBaseService.EXTRA_ACTION, 2);
            localBroadcastManager.sendBroadcast(intent);
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dfu);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            MainActivity.lock_dfu_repair = extras.getBoolean("isRepair");
        }
        this.mService = new BLEService();
        this.btn_update = (Button) findViewById(R.id.button_update);
        this.btn_scan = (Button) findViewById(R.id.button_scan_dfutarg);
        this.before_update = (ScrollView) findViewById(R.id.before_update);
        this.tv_current_version = (TextView) findViewById(R.id.current_version);
        this.tv_database_version = (TextView) findViewById(R.id.database_version);
        this.uploading = (ScrollView) findViewById(R.id.uploading);
        this.overall_progress = (ProgressBar) findViewById(R.id.overall_progress);
        this.overall_text = (TextView) findViewById(R.id.overall_text);
        this.file_progress = (ProgressBar) findViewById(R.id.file_progress);
        this.file_text = (TextView) findViewById(R.id.file_text);
        this.repair_mode = (LinearLayout) findViewById(R.id.ll_repair_mode);
        this.repair_progress = (LinearLayout) findViewById(R.id.repair_scan_progress);
        this.scan_text = (TextView) findViewById(R.id.scan_text);
        this.warning_text = (TextView) findViewById(R.id.tv_warning);
        this.additional_msg = (TextView) findViewById(R.id.tv_repair_additional_msg);
        this.tv_tips = (TextView) findViewById(R.id.tv_tips);
        this.ll_tips = (LinearLayout) findViewById(R.id.ll_tips);
        this.swdialog_to = new SweetAlertDialog(this, 1);
        reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dfuUpdate() {
        if (this.isUpdating) {
            return;
        }
        this.isUpdating = true;
        this.handlerTips.post(this.runTips);
        this.before_update.setVisibility(8);
        this.uploading.setVisibility(0);
        FirebaseStorage.getInstance().getReference().child(DfuBaseService.NOTIFICATION_CHANNEL_DFU).child(this.dfu_filename).getBytes(PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED).addOnSuccessListener(new OnSuccessListener<byte[]>() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.2
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(byte[] bArr) {
                File file = new File(DfuActivity.this.getFilesDir(), "gt2500dfu.zip");
                file.setWritable(true);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(bArr);
                    fileOutputStream.close();
                    Uri fromFile = Uri.fromFile(file);
                    System.out.println("CMDCMD uri: " + fromFile.toString());
                    System.out.println("CMDCMD filesize: " + Integer.parseInt(String.valueOf(file.length())));
                    DfuServiceInitiator keepBond = new DfuServiceInitiator("").setKeepBond(true);
                    if (MainActivity.lock_dfu_repair) {
                        if (!DfuActivity.affectedLockAddress.isEmpty()) {
                            keepBond = new DfuServiceInitiator(DfuActivity.affectedLockAddress).setKeepBond(true);
                        }
                    } else {
                        keepBond = new DfuServiceInitiator(BLEService.selected_lock_address).setDeviceName(BLEService.fullNameOfLock).setKeepBond(true);
                    }
                    keepBond.setPrepareDataObjectDelay(300L);
                    keepBond.setZip(fromFile, file.getPath());
                    keepBond.start(DfuActivity.this, DfuService.class);
                    if (Build.VERSION.SDK_INT >= 26) {
                        DfuServiceInitiator.createDfuNotificationChannel(DfuActivity.this);
                    }
                    DfuActivity.this.overall_progress.setProgress(20);
                    DfuActivity.this.overall_text.setText(DfuActivity.this.getString(R.string.enabling_dfu));
                } catch (Exception e) {
                    System.out.println("CMDCMD FileOutputStream exception");
                    e.printStackTrace();
                    DfuActivity.this.showFailedDialog(2);
                }
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                System.out.println("CMDCMD download file failed");
            }
        });
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
            return true;
        }
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        DfuServiceListenerHelper.registerProgressListener(this, this.mDfuProgressListener);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        DfuServiceListenerHelper.unregisterProgressListener(this, this.mDfuProgressListener);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.isUpdating) {
            return;
        }
        super.onBackPressed();
    }

    public void btn_update_firmware(View view) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 3);
        if (MainActivity.lock_dfu_repair) {
            sweetAlertDialog.setTitleText(getString(R.string.repair_firmware));
        } else {
            sweetAlertDialog.setTitleText(getString(R.string.firmware_update));
        }
        sweetAlertDialog.setContentText(getString(R.string.firmware_update_msg));
        sweetAlertDialog.setCancelText(getString(R.string.cancel));
        sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.4
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                sweetAlertDialog2.dismissWithAnimation();
            }
        });
        sweetAlertDialog.setConfirmText(getString(R.string.ok));
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.5
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                DfuActivity.this.dfuUpdate();
                sweetAlertDialog2.dismissWithAnimation();
            }
        });
        sweetAlertDialog.show();
    }

    public void btn_scan_dfutarg(View view) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 3);
        sweetAlertDialog.setTitleText(getString(R.string.firmware_repair));
        sweetAlertDialog.setContentText(getString(R.string.before_repair_msg));
        sweetAlertDialog.setCancelText(getString(R.string.cancel));
        sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.6
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                sweetAlertDialog2.dismissWithAnimation();
            }
        });
        sweetAlertDialog.setConfirmText(getString(R.string.ok));
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.7
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                DfuActivity.this.btn_scan.setClickable(false);
                DfuActivity.this.btn_scan.setAlpha(0.5f);
                DfuActivity.this.repair_progress.setVisibility(0);
                DfuActivity.this.mService.startScanDfuRepair();
                DfuActivity.this.handler.postDelayed(DfuActivity.this.runRepair, 100L);
                sweetAlertDialog2.dismissWithAnimation();
            }
        });
        sweetAlertDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSuccessDialog() {
        BLEService.selected_lock_version = this.database_version;
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 2);
        sweetAlertDialog.setTitleText(getString(R.string.success));
        if (MainActivity.lock_dfu_repair) {
            sweetAlertDialog.setContentText(getString(R.string.repair_completed));
        } else {
            sweetAlertDialog.setContentText(getString(R.string.update_completed));
        }
        sweetAlertDialog.setConfirmText(getString(R.string.ok));
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.8
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                DfuActivity.this.reset();
                sweetAlertDialog2.dismissWithAnimation();
            }
        });
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFailedDialog(int i) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 1);
        sweetAlertDialog.setTitleText(getString(R.string.update_failed));
        if (i == 1) {
            this.swdialog_to.setTitleText(getString(R.string.update_failed));
            this.swdialog_to.setContentText("No device found, make sure that your lock is in charging state before starting the update.");
            this.swdialog_to.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.9
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog2) {
                    sweetAlertDialog2.dismissWithAnimation();
                    DfuActivity.this.finish();
                }
            });
            this.swdialog_to.setCancelable(false);
            this.swdialog_to.setCanceledOnTouchOutside(false);
            this.swdialog_to.show();
        } else if (i == 2) {
            sweetAlertDialog.setContentText("If your lock becomes unresponsive, you can use the Firmware Repair feature in the menu at top left of the screen in lock selection page.");
            sweetAlertDialog.setConfirmText(getString(R.string.ok));
            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.10
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog2) {
                    sweetAlertDialog2.dismissWithAnimation();
                    DfuActivity.this.reset();
                }
            });
            sweetAlertDialog.setCancelable(false);
            sweetAlertDialog.setCanceledOnTouchOutside(false);
            sweetAlertDialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        this.before_update.setVisibility(0);
        this.uploading.setVisibility(8);
        this.overall_progress.setProgress(0);
        this.overall_text.setText(getString(R.string.downloading_files));
        this.overall_text.setTextColor(getResources().getColor(R.color.cmd_text));
        this.file_progress.setProgress(0);
        this.file_text.setText(getString(R.string.upload_percent) + " 0%");
        this.isUpdating = false;
        this.btn_update.setClickable(false);
        this.btn_update.setAlpha(0.5f);
        this.btn_update.setText(R.string.update_firmware);
        this.btn_scan.setClickable(false);
        this.btn_scan.setAlpha(0.5f);
        this.repair_progress.setVisibility(8);
        this.scan_text.setText(getString(R.string.scanning));
        connectAffected = false;
        foundAffected = false;
        affectedLockName = "";
        affectedLockAddress = "";
        this.tips_index = 0;
        this.handlerTips.removeCallbacks(this.runTips);
        if (MainActivity.lock_dfu_repair) {
            this.tv_current_version.setVisibility(8);
            this.btn_scan.setVisibility(0);
            this.btn_update.setVisibility(8);
            this.repair_mode.setVisibility(0);
            this.warning_text.setText(getString(R.string.warning_repair));
            this.additional_msg.setVisibility(0);
        } else {
            this.tv_current_version.setVisibility(0);
            this.tv_current_version.setText(getString(R.string.current_version) + " " + BLEService.selected_lock_version);
            this.btn_scan.setVisibility(8);
            this.btn_update.setVisibility(0);
            this.repair_mode.setVisibility(8);
            this.warning_text.setText(getString(R.string.warning_update));
            this.additional_msg.setVisibility(8);
        }
        this.tv_database_version.setText(getString(R.string.database_version));
        findViewById(R.id.dbv_progress).setVisibility(0);
        FirebaseDatabase.getInstance().getReference("firmwareUpdate").addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.11
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                DfuActivity.this.dfu_filename = dataSnapshot.child("filename").getValue().toString();
                DfuActivity.this.database_version = dataSnapshot.child(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION).getValue().toString();
                DfuActivity.this.tv_database_version.setText(DfuActivity.this.getString(R.string.database_version) + " " + DfuActivity.this.database_version);
                DfuActivity.this.findViewById(R.id.dbv_progress).setVisibility(8);
                if (MainActivity.lock_dfu_repair) {
                    DfuActivity.this.btn_scan.setClickable(true);
                    DfuActivity.this.btn_scan.setAlpha(1.0f);
                } else if (Float.parseFloat(DfuActivity.this.database_version) > Float.parseFloat(BLEService.selected_lock_version)) {
                    DfuActivity.this.btn_update.setClickable(true);
                    DfuActivity.this.btn_update.setAlpha(1.0f);
                }
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
                DfuActivity.this.reset();
            }
        });
    }

    /* renamed from: com.egeetouch.egeetouch_manager.DfuActivity$13  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass13 implements Runnable {
        AnonymousClass13() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DfuActivity.this.tips_index <= 0) {
                TextView textView = DfuActivity.this.tv_tips;
                DfuActivity dfuActivity = DfuActivity.this;
                textView.setText(dfuActivity.getString(dfuActivity.tips_arr[DfuActivity.this.tips_index % DfuActivity.this.tips_arr.length]));
                DfuActivity.this.tips_index++;
                DfuActivity.this.handlerTips.postDelayed(DfuActivity.this.runTips, DfuServiceInitiator.DEFAULT_SCAN_TIMEOUT);
                return;
            }
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(500L);
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.13.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    DfuActivity.this.tv_tips.setText(DfuActivity.this.getString(DfuActivity.this.tips_arr[DfuActivity.this.tips_index % DfuActivity.this.tips_arr.length]));
                    DfuActivity.this.tips_index++;
                    AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
                    alphaAnimation2.setDuration(500L);
                    alphaAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.egeetouch.egeetouch_manager.DfuActivity.13.1.1
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation2) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation2) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation2) {
                            if (DfuActivity.this.isUpdating) {
                                DfuActivity.this.handlerTips.postDelayed(DfuActivity.this.runTips, DfuServiceInitiator.DEFAULT_SCAN_TIMEOUT);
                            }
                        }
                    });
                    DfuActivity.this.ll_tips.startAnimation(alphaAnimation2);
                }
            });
            DfuActivity.this.ll_tips.startAnimation(alphaAnimation);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (MainActivity.lock_dfu_repair) {
            try {
                this.mService.disconnectRepair();
            } catch (Exception unused) {
            }
        }
        foundAffected = false;
        connectAffected = false;
        affectedLockName = "";
        affectedLockAddress = "";
        this.tips_index = 0;
        this.handlerTips.removeCallbacks(this.runTips);
        MainActivity.lock_dfu_repair = false;
    }
}
