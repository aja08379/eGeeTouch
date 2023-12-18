package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
/* loaded from: classes.dex */
public class Fragment_Passcode extends Fragment {
    Button bt_cancel;
    Button bt_eight;
    Button bt_five;
    Button bt_four;
    Button bt_nine;
    Button bt_one;
    Button bt_seven;
    Button bt_six;
    Button bt_three;
    Button bt_two;
    Button bt_zero;
    Button btn_clear;
    Button btn_submit;
    TextView display_passcode;
    EditText ed_name;
    ImageButton img_downArrow_btn;
    ImageButton img_leftArrow_btn;
    ImageButton img_rightArrow_btn;
    ImageButton img_upArrow_btn;
    LinearLayout ll_editPasscode;
    LinearLayout ll_overlay;
    LinearLayout ll_passcodeName;
    LotoInfo lotoInfo;
    private Menu menu;
    byte[] newPassCode_byte;
    int passCode_count;
    private String serial;
    TextView tv_cancelName;
    TextView tv_error_msg;
    TextView tv_submitName;
    UI_BLE ui_ble;
    View rootView = null;
    String displayDirectional = "";
    int index = 0;
    int[] slots = {R.id.passcode1, R.id.passcode2, R.id.passcode3, R.id.passcode4, R.id.passcode5, R.id.passcode6};

    public static Fragment_Passcode newInstance() {
        return new Fragment_Passcode();
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.add_passcode_jp);
    }

    @Override // android.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_button_tutorial) {
            String str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video" : "http://www.egeetouch.com/support/video";
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.fragment_change_passcode, viewGroup, false);
        setHasOptionsMenu(true);
        getActivity().setTitle("Passcode");
        this.lotoInfo = LotoInfo.getInstance();
        this.ll_passcodeName = (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_add_passcodeName);
        this.ll_overlay = (LinearLayout) this.rootView.findViewById(R.id.linearLayout_overlay);
        this.ll_editPasscode = (LinearLayout) this.rootView.findViewById(R.id.ll_editPasscode);
        this.img_downArrow_btn = (ImageButton) this.rootView.findViewById(R.id.btn_down_arrow);
        this.img_upArrow_btn = (ImageButton) this.rootView.findViewById(R.id.btn_up_arrow);
        this.img_leftArrow_btn = (ImageButton) this.rootView.findViewById(R.id.btn_left_arrow);
        this.img_rightArrow_btn = (ImageButton) this.rootView.findViewById(R.id.btn_right_arrow);
        this.btn_clear = (Button) this.rootView.findViewById(R.id.btn_clear_all);
        this.btn_submit = (Button) this.rootView.findViewById(R.id.btn_submit_pad_pass);
        this.bt_cancel = (Button) this.rootView.findViewById(R.id.bt_cancel);
        this.bt_one = (Button) this.rootView.findViewById(R.id.bt_one);
        this.bt_two = (Button) this.rootView.findViewById(R.id.bt_two);
        this.bt_three = (Button) this.rootView.findViewById(R.id.bt_three);
        this.bt_four = (Button) this.rootView.findViewById(R.id.bt_four);
        this.bt_five = (Button) this.rootView.findViewById(R.id.bt_five);
        this.bt_six = (Button) this.rootView.findViewById(R.id.bt_six);
        this.bt_seven = (Button) this.rootView.findViewById(R.id.bt_seven);
        this.bt_eight = (Button) this.rootView.findViewById(R.id.bt_eight);
        this.bt_nine = (Button) this.rootView.findViewById(R.id.bt_nine);
        this.bt_zero = (Button) this.rootView.findViewById(R.id.bt_zero);
        this.display_passcode = (TextView) this.rootView.findViewById(R.id.text_pad_pass);
        this.tv_error_msg = (TextView) this.rootView.findViewById(R.id.tv_error);
        this.tv_cancelName = (TextView) this.rootView.findViewById(R.id.textView_cancel_name);
        this.tv_submitName = (TextView) this.rootView.findViewById(R.id.textView_submitName);
        this.ed_name = (EditText) this.rootView.findViewById(R.id.editText_passcode_name);
        this.ui_ble = new UI_BLE(MainActivity.context);
        this.display_passcode.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        this.newPassCode_byte = new byte[6];
        BLEService.new_passcode = "";
        this.btn_submit.setAlpha(0.3f);
        this.btn_submit.setEnabled(false);
        this.img_rightArrow_btn.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.rightArrow(view);
            }
        });
        this.img_leftArrow_btn.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.leftArrow(view);
            }
        });
        this.img_upArrow_btn.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.upArrow(view);
            }
        });
        this.img_downArrow_btn.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.downArrow(view);
            }
        });
        this.bt_one.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.upArrow(view);
            }
        });
        this.bt_two.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.upArrow(view);
            }
        });
        this.bt_three.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.upArrow(view);
            }
        });
        this.bt_four.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.rightArrow(view);
            }
        });
        this.bt_five.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.rightArrow(view);
            }
        });
        this.bt_six.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.downArrow(view);
            }
        });
        this.bt_seven.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.downArrow(view);
            }
        });
        this.bt_eight.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.downArrow(view);
            }
        });
        this.bt_nine.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.leftArrow(view);
            }
        });
        this.bt_zero.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.leftArrow(view);
            }
        });
        this.btn_clear.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.passCode_count = 0;
                BLEService.new_passcode = "";
                Fragment_Passcode.this.displayDirectional = "";
                Fragment_Passcode.this.display_passcode.setText("");
                Fragment_Passcode.this.tv_error_msg.setText("");
                for (int i = 0; i < Fragment_Passcode.this.slots.length; i++) {
                    ((ImageView) Fragment_Passcode.this.rootView.findViewById(Fragment_Passcode.this.slots[i])).setImageResource(0);
                }
                Fragment_Passcode.this.index = 0;
                Fragment_Passcode.this.checkSaveButtonEnabled();
            }
        });
        this.btn_submit.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.tv_error_msg.setText("");
                if (BLEService.new_passcode.length() == 6) {
                    Fragment_Passcode.this.ll_passcodeName.setVisibility(0);
                    Fragment_Passcode.this.ll_overlay.setVisibility(0);
                    Fragment_Passcode.this.ll_editPasscode.setAlpha(0.2f);
                    Fragment_Passcode.this.ll_editPasscode.setClickable(false);
                    return;
                }
                Toast.makeText(MainActivity.context, "Length of the passcode must be 6", 1).show();
            }
        });
        this.tv_submitName.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!Fragment_Manage_Passcode.directionalCode.contains(BLEService.new_passcode)) {
                    String obj = Fragment_Passcode.this.ed_name.getText().toString();
                    if (obj.length() > 0) {
                        if (!Fragment_Manage_Passcode.directionalName.contains(obj)) {
                            Fragment_Passcode.this.ll_passcodeName.setVisibility(8);
                            Fragment_Passcode.this.ll_overlay.setVisibility(8);
                            Fragment_Passcode.this.ll_editPasscode.setAlpha(1.0f);
                            Fragment_Passcode.this.ll_editPasscode.setClickable(true);
                            BLEService.new_passcodeName = obj;
                            Fragment_Passcode.this.updatePasscodeToLock();
                            return;
                        }
                        Fragment_Passcode.this.ed_name.setError("Name Already exist !");
                        return;
                    }
                    Fragment_Passcode.this.ed_name.setError("Please enter the name !");
                    return;
                }
                Fragment_Passcode.this.ll_passcodeName.setVisibility(8);
                Fragment_Passcode.this.ll_overlay.setVisibility(8);
                Fragment_Passcode.this.ll_editPasscode.setAlpha(1.0f);
                Fragment_Passcode.this.ll_editPasscode.setClickable(true);
                new SweetAlertDialog(MainActivity.context, 3).setTitleText(MainActivity.context.getResources().getString(R.string.update_error)).setContentText(Fragment_Passcode.this.displayDirectional + " " + MainActivity.context.getResources().getString(R.string.already_exists)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
            }
        });
        this.tv_cancelName.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.ll_passcodeName.setVisibility(8);
                Fragment_Passcode.this.ll_overlay.setVisibility(8);
                Fragment_Passcode.this.ll_editPasscode.setAlpha(1.0f);
                Fragment_Passcode.this.ll_editPasscode.setClickable(true);
            }
        });
        this.bt_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Passcode.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_Passcode.this.getActivity().getFragmentManager().popBackStack();
            }
        });
        return this.rootView;
    }

    private String getEmojiByUnicode(int i) {
        return new String(Character.toChars(i));
    }

    public void upArrow(View view) {
        if (BLEService.new_passcode.length() < 6) {
            this.displayDirectional += getEmojiByUnicode(11014);
            BLEService.new_passcode += 1;
            BLEService.new_passcodeBytes[this.passCode_count] = 1;
            this.display_passcode.setText(this.displayDirectional);
            this.passCode_count++;
            updatePassDisplay("up");
        } else {
            dispalyError();
        }
        checkSaveButtonEnabled();
    }

    public void downArrow(View view) {
        if (BLEService.new_passcode.length() < 6) {
            this.displayDirectional += getEmojiByUnicode(11015);
            BLEService.new_passcode += 3;
            BLEService.new_passcodeBytes[this.passCode_count] = 3;
            this.display_passcode.setText(this.displayDirectional);
            this.passCode_count++;
            updatePassDisplay("down");
        } else {
            dispalyError();
        }
        checkSaveButtonEnabled();
    }

    public void rightArrow(View view) {
        if (BLEService.new_passcode.length() < 6) {
            this.displayDirectional += getEmojiByUnicode(10145);
            BLEService.new_passcode += 4;
            BLEService.new_passcodeBytes[this.passCode_count] = 4;
            this.display_passcode.setText(this.displayDirectional);
            this.passCode_count++;
            updatePassDisplay("right");
        } else {
            dispalyError();
        }
        checkSaveButtonEnabled();
    }

    public void leftArrow(View view) {
        if (BLEService.new_passcode.length() < 6) {
            this.displayDirectional += getEmojiByUnicode(11013);
            BLEService.new_passcode += 2;
            BLEService.new_passcodeBytes[this.passCode_count] = 2;
            this.display_passcode.setText(this.displayDirectional);
            this.passCode_count++;
            updatePassDisplay("left");
        } else {
            dispalyError();
        }
        checkSaveButtonEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePasscodeToLock() {
        LotoInfo lotoInfo = this.lotoInfo;
        lotoInfo.setUpdateMode(lotoInfo.ADD_PASSCODE);
        UI_BLE.pls_wait_content = getString(R.string.updating_data_to_lock);
        UI_BLE.BLE_UI = 22;
        this.ui_ble.update();
        this.passCode_count = 0;
        BLEService.Ble_Mode = BLEService.Request_ChangePasscode;
        BLEService.StrPasscodeEmoji = this.displayDirectional;
    }

    private void dispalyError() {
        this.tv_error_msg.setText(MainActivity.context.getResources().getString(R.string.reached_maximum_length));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkSaveButtonEnabled() {
        if (BLEService.new_passcode.length() == 6) {
            this.btn_submit.setEnabled(true);
            this.btn_submit.setAlpha(1.0f);
            return;
        }
        this.btn_submit.setEnabled(false);
        this.btn_submit.setAlpha(0.3f);
    }

    private void updatePassDisplay(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 3739:
                if (str.equals("up")) {
                    c = 0;
                    break;
                }
                break;
            case 3089570:
                if (str.equals("down")) {
                    c = 1;
                    break;
                }
                break;
            case 3317767:
                if (str.equals("left")) {
                    c = 2;
                    break;
                }
                break;
            case 108511772:
                if (str.equals("right")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                int i = this.index;
                if (i < 6) {
                    ((ImageView) this.rootView.findViewById(this.slots[i])).setImageResource(R.drawable.arrow_up);
                }
                this.index++;
                return;
            case 1:
                int i2 = this.index;
                if (i2 < 6) {
                    ((ImageView) this.rootView.findViewById(this.slots[i2])).setImageResource(R.drawable.arrow_down);
                }
                this.index++;
                return;
            case 2:
                int i3 = this.index;
                if (i3 < 6) {
                    ((ImageView) this.rootView.findViewById(this.slots[i3])).setImageResource(R.drawable.arrow_left);
                }
                this.index++;
                return;
            case 3:
                int i4 = this.index;
                if (i4 < 6) {
                    ((ImageView) this.rootView.findViewById(this.slots[i4])).setImageResource(R.drawable.arrow_right);
                }
                this.index++;
                return;
            default:
                return;
        }
    }
}
