package com.egeetouch.egeetouch_manager;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
/* loaded from: classes.dex */
public class activity_change_pad_password extends AppCompatActivity {
    public static int Pass_code_mode = 0;
    public static int old_pass_code_mode = 0;
    public static final int old_pass_code_success = 1;
    public static final int pass_code_failed = 2;
    public static final int pass_code_success = 1;
    ImageButton btn_down_arrow;
    ImageButton btn_left_arrow;
    ImageButton btn_rigth_arrow;
    ImageButton btn_up_arrow;
    Button clear_all;
    TextView label;
    Handler mHandler;
    Handler old_passHandler;
    Button submit;
    TextView tx_old_passcode;
    SweetAlertDialog update_fail;
    SweetAlertDialog update_progress;
    SweetAlertDialog update_success;
    int i = 2;
    public final Runnable task_update = new Runnable() { // from class: com.egeetouch.egeetouch_manager.activity_change_pad_password.7
        @Override // java.lang.Runnable
        public void run() {
            System.out.println("Hello Pass_mode:" + activity_change_pad_password.Pass_code_mode);
            int i = activity_change_pad_password.Pass_code_mode;
            if (i == 0) {
                activity_change_pad_password.this.mHandler.postDelayed(this, 10L);
            } else if (i == 1) {
                activity_change_pad_password.this.update_progress.dismissWithAnimation();
                activity_change_pad_password.this.update_progress.show();
                activity_change_pad_password.this.i = 2;
                for (int i2 = 0; i2 < 16; i2++) {
                    BLEService.new_pass_code_byte[i2] = 0;
                }
                activity_change_pad_password.this.label.setText("");
                activity_change_pad_password.this.update_success = new SweetAlertDialog(activity_change_pad_password.this, 2);
                activity_change_pad_password.this.update_success.setTitleText(activity_change_pad_password.this.getString(R.string.update_successful));
                activity_change_pad_password.this.update_success.setConfirmText(" ");
                activity_change_pad_password.this.update_success.setConfirmText(activity_change_pad_password.this.getString(R.string.ok));
                activity_change_pad_password.this.update_success.show();
                activity_change_pad_password.this.tx_old_passcode.setText("");
                BLEService.Response_old_passcode_boolean = true;
                BLEService.Ble_Mode = BLEService.Request_old_passcode;
                activity_change_pad_password.this.old_passHandler.postDelayed(activity_change_pad_password.this.task_old_passCode, 1000L);
                activity_change_pad_password.Pass_code_mode = 3;
                return;
            } else if (i == 2) {
                activity_change_pad_password.this.update_progress.dismissWithAnimation();
                activity_change_pad_password.this.update_progress.show();
                return;
            } else if (i != 3) {
                return;
            }
            System.out.println("Case 3!!");
        }
    };
    public final Runnable task_old_passCode = new Runnable() { // from class: com.egeetouch.egeetouch_manager.activity_change_pad_password.8
        @Override // java.lang.Runnable
        public void run() {
            int i = activity_change_pad_password.old_pass_code_mode;
            if (i == 0) {
                activity_change_pad_password.this.old_passHandler.postDelayed(this, 10L);
            } else if (i == 1) {
                for (int i2 = 0; i2 < BLEService.old_pass_code.length; i2++) {
                    if (BLEService.old_pass_code[i2] == 1) {
                        activity_change_pad_password.this.tx_old_passcode.setText(((Object) activity_change_pad_password.this.tx_old_passcode.getText()) + "➡");
                    } else if (BLEService.old_pass_code[i2] == 2) {
                        activity_change_pad_password.this.tx_old_passcode.setText(((Object) activity_change_pad_password.this.tx_old_passcode.getText()) + "⬆");
                    } else if (BLEService.old_pass_code[i2] == 4) {
                        activity_change_pad_password.this.tx_old_passcode.setText(((Object) activity_change_pad_password.this.tx_old_passcode.getText()) + "⬅");
                    } else if (BLEService.old_pass_code[i2] == 8) {
                        activity_change_pad_password.this.tx_old_passcode.setText(((Object) activity_change_pad_password.this.tx_old_passcode.getText()) + "⬇");
                    }
                }
            } else {
                Log.i("old pass code", "default mode");
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_change_pad_password);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setTitle("Change Pad Passcode");
        BLEService.Response_old_passcode_boolean = true;
        BLEService.Ble_Mode = BLEService.Request_old_passcode;
        this.mHandler = new Handler();
        this.old_passHandler = new Handler();
        for (int i = 0; i < 16; i++) {
            BLEService.new_pass_code_byte[i] = 0;
        }
        initialize();
    }

    public void initialize() {
        this.btn_up_arrow = (ImageButton) findViewById(R.id.btn_up_arrow);
        this.btn_down_arrow = (ImageButton) findViewById(R.id.btn_down_arrow);
        this.btn_left_arrow = (ImageButton) findViewById(R.id.btn_left_arrow);
        this.btn_rigth_arrow = (ImageButton) findViewById(R.id.btn_right_arrow);
        this.clear_all = (Button) findViewById(R.id.btn_clear_all);
        this.submit = (Button) findViewById(R.id.btn_submit_pad_pass);
        this.label = (TextView) findViewById(R.id.text_pad_pass);
        TextView textView = (TextView) findViewById(R.id.tx_old_pass);
        this.tx_old_passcode = textView;
        textView.setText("");
        this.old_passHandler.post(this.task_old_passCode);
        this.label.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        this.btn_up_arrow.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.activity_change_pad_password.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (activity_change_pad_password.this.i < 10) {
                    activity_change_pad_password.this.label.setText(((Object) activity_change_pad_password.this.label.getText()) + "⬆");
                    BLEService.new_pass_code_byte[activity_change_pad_password.this.i] = 2;
                    activity_change_pad_password.this.i++;
                }
            }
        });
        this.btn_down_arrow.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.activity_change_pad_password.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (activity_change_pad_password.this.i < 10) {
                    activity_change_pad_password.this.label.setText(((Object) activity_change_pad_password.this.label.getText()) + "⬇");
                    BLEService.new_pass_code_byte[activity_change_pad_password.this.i] = 8;
                    activity_change_pad_password.this.i++;
                }
            }
        });
        this.btn_rigth_arrow.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.activity_change_pad_password.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (activity_change_pad_password.this.i < 10) {
                    activity_change_pad_password.this.label.setText(((Object) activity_change_pad_password.this.label.getText()) + "➡");
                    BLEService.new_pass_code_byte[activity_change_pad_password.this.i] = 1;
                    activity_change_pad_password.this.i++;
                }
            }
        });
        this.btn_left_arrow.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.activity_change_pad_password.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (activity_change_pad_password.this.i < 10) {
                    activity_change_pad_password.this.label.setText(((Object) activity_change_pad_password.this.label.getText()) + "⬅");
                    BLEService.new_pass_code_byte[activity_change_pad_password.this.i] = 4;
                    activity_change_pad_password.this.i++;
                }
            }
        });
        this.clear_all.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.activity_change_pad_password.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                activity_change_pad_password.this.i = 2;
                for (int i = 0; i < 16; i++) {
                    BLEService.new_pass_code_byte[i] = 0;
                }
                activity_change_pad_password.this.label.setText("");
                activity_change_pad_password.this.label.setError(null);
            }
        });
        this.submit.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.activity_change_pad_password.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (activity_change_pad_password.this.i >= 6 && activity_change_pad_password.this.i <= 10) {
                    BLEService.new_pass_code_byte[0] = 89;
                    BLEService.new_pass_code_byte[1] = (byte) (activity_change_pad_password.this.i - 2);
                    activity_change_pad_password.this.update_progress = new SweetAlertDialog(activity_change_pad_password.this, 5);
                    activity_change_pad_password.this.update_progress.getProgressHelper().setBarColor(Color.parseColor("#389ae0"));
                    activity_change_pad_password.this.update_progress.setTitleText("Change Passcode");
                    activity_change_pad_password.this.update_progress.setContentText(activity_change_pad_password.this.getString(R.string.updating_data_to_lock));
                    activity_change_pad_password.this.update_progress.setCancelable(true);
                    activity_change_pad_password.this.update_progress.show();
                    activity_change_pad_password.Pass_code_mode = 0;
                    activity_change_pad_password.this.mHandler.post(activity_change_pad_password.this.task_update);
                    BLEService.change_passcode = true;
                    BLEService.Ble_Mode = 97;
                    activity_change_pad_password.this.label.setError(null);
                } else if (activity_change_pad_password.this.i < 6) {
                    activity_change_pad_password.this.label.setError("INVALID");
                    Toast.makeText(activity_change_pad_password.this, "Minimun Length of passcode should be 4", 1).show();
                }
            }
        });
    }
}
