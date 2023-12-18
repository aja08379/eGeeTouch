package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class TestingLayoutSettingsBinding implements ViewBinding {
    public final Button Button01;
    public final Button Button02;
    public final Button ButtonSaveNewuser;
    public final LinearLayout LinearLayoutBroadcasting;
    public final LinearLayout LinearLayoutDeviceInfo;
    public final LinearLayout LinearLayoutIcons;
    public final LinearLayout LinearLayoutLockInfo;
    public final LinearLayout LinearLayoutNewName;
    public final LinearLayout LinearLayoutNewPassword;
    public final LinearLayout LinearLayoutNotification;
    public final LinearLayout LinearLayoutSettingDefocusEditbox;
    public final Switch SwitchAutoLocking;
    public final Switch SwitchAutoUnlocking;
    public final Switch SwitchPowerSavingMode;
    public final Switch SwitchShackleByPass;
    public final Switch SwitchUnlocking;
    public final TextView TextView0002;
    public final TextView TextView002;
    public final TextView TextView01;
    public final TextView TextView011;
    public final TextView TextView03;
    public final TextView TextView04;
    public final TextView TextViewNotification;
    public final Button btCheckMasterTag;
    public final Button buttonDfu;
    public final Button buttonManagePasscode;
    public final Button buttonManageTag;
    public final Button buttonManageUser;
    public final Button buttonScanTagId;
    public final Button changePadPassword;
    public final EditText editTextNewName;
    public final EditText editTextNewPrimaryPassword;
    public final EditText editTextNewPrimaryPassword2;
    public final ImageView imageViewAudit;
    public final ImageView imageViewLocking;
    public final ImageView imageViewProximity;
    public final ImageView imageViewSetting;
    public final ImageView imageViewWatch;
    public final LinearLayout llAdvTime;
    public final LinearLayout llAutoLocking;
    public final LinearLayout llShackleBypass;
    private final LinearLayout rootView;
    public final ScrollView scrollView1;
    public final Switch switchProximity;
    public final TextView textView0003;
    public final TextView textView003;
    public final TextView textView1;
    public final TextView textView2;
    public final TextView textViewAdvertising;
    public final TextView textViewAdvertisingTitle;
    public final TextView textViewAdvertisingTitle001;
    public final TextView textViewAuditCount;
    public final TextView textViewFirmwareV;
    public final TextView textViewModel;
    public final TextView textViewNewPrimaryPasswordBigTitle;
    public final TextView textViewNewPrimaryPasswordTitle;
    public final TextView textViewStandby;
    public final TextView tvAuditCount;
    public final TextView tvAuditCount1;
    public final TextView tvAutoLocking;
    public final TextView tvBroadCastTime;
    public final TextView tvLockReconnectionTime;
    public final TextView tvShackleByPass;
    public final TextView tvTagCount;
    public final TextView tvTagCount1;

    private TestingLayoutSettingsBinding(LinearLayout linearLayout, Button button, Button button2, Button button3, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, LinearLayout linearLayout7, LinearLayout linearLayout8, LinearLayout linearLayout9, Switch r15, Switch r16, Switch r17, Switch r18, Switch r19, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, Button button4, Button button5, Button button6, Button button7, Button button8, Button button9, Button button10, EditText editText, EditText editText2, EditText editText3, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout10, LinearLayout linearLayout11, LinearLayout linearLayout12, ScrollView scrollView, Switch r46, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, TextView textView24, TextView textView25, TextView textView26, TextView textView27, TextView textView28) {
        this.rootView = linearLayout;
        this.Button01 = button;
        this.Button02 = button2;
        this.ButtonSaveNewuser = button3;
        this.LinearLayoutBroadcasting = linearLayout2;
        this.LinearLayoutDeviceInfo = linearLayout3;
        this.LinearLayoutIcons = linearLayout4;
        this.LinearLayoutLockInfo = linearLayout5;
        this.LinearLayoutNewName = linearLayout6;
        this.LinearLayoutNewPassword = linearLayout7;
        this.LinearLayoutNotification = linearLayout8;
        this.LinearLayoutSettingDefocusEditbox = linearLayout9;
        this.SwitchAutoLocking = r15;
        this.SwitchAutoUnlocking = r16;
        this.SwitchPowerSavingMode = r17;
        this.SwitchShackleByPass = r18;
        this.SwitchUnlocking = r19;
        this.TextView0002 = textView;
        this.TextView002 = textView2;
        this.TextView01 = textView3;
        this.TextView011 = textView4;
        this.TextView03 = textView5;
        this.TextView04 = textView6;
        this.TextViewNotification = textView7;
        this.btCheckMasterTag = button4;
        this.buttonDfu = button5;
        this.buttonManagePasscode = button6;
        this.buttonManageTag = button7;
        this.buttonManageUser = button8;
        this.buttonScanTagId = button9;
        this.changePadPassword = button10;
        this.editTextNewName = editText;
        this.editTextNewPrimaryPassword = editText2;
        this.editTextNewPrimaryPassword2 = editText3;
        this.imageViewAudit = imageView;
        this.imageViewLocking = imageView2;
        this.imageViewProximity = imageView3;
        this.imageViewSetting = imageView4;
        this.imageViewWatch = imageView5;
        this.llAdvTime = linearLayout10;
        this.llAutoLocking = linearLayout11;
        this.llShackleBypass = linearLayout12;
        this.scrollView1 = scrollView;
        this.switchProximity = r46;
        this.textView0003 = textView8;
        this.textView003 = textView9;
        this.textView1 = textView10;
        this.textView2 = textView11;
        this.textViewAdvertising = textView12;
        this.textViewAdvertisingTitle = textView13;
        this.textViewAdvertisingTitle001 = textView14;
        this.textViewAuditCount = textView15;
        this.textViewFirmwareV = textView16;
        this.textViewModel = textView17;
        this.textViewNewPrimaryPasswordBigTitle = textView18;
        this.textViewNewPrimaryPasswordTitle = textView19;
        this.textViewStandby = textView20;
        this.tvAuditCount = textView21;
        this.tvAuditCount1 = textView22;
        this.tvAutoLocking = textView23;
        this.tvBroadCastTime = textView24;
        this.tvLockReconnectionTime = textView25;
        this.tvShackleByPass = textView26;
        this.tvTagCount = textView27;
        this.tvTagCount1 = textView28;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static TestingLayoutSettingsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TestingLayoutSettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.testing_layout_settings, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TestingLayoutSettingsBinding bind(View view) {
        int i = R.id.Button01;
        Button button = (Button) view.findViewById(R.id.Button01);
        if (button != null) {
            i = R.id.Button02;
            Button button2 = (Button) view.findViewById(R.id.Button02);
            if (button2 != null) {
                i = R.id.Button_save_newuser;
                Button button3 = (Button) view.findViewById(R.id.Button_save_newuser);
                if (button3 != null) {
                    i = R.id.LinearLayout_broadcasting;
                    LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_broadcasting);
                    if (linearLayout != null) {
                        i = R.id.LinearLayout_device_info;
                        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.LinearLayout_device_info);
                        if (linearLayout2 != null) {
                            i = R.id.LinearLayout_icons;
                            LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.LinearLayout_icons);
                            if (linearLayout3 != null) {
                                i = R.id.LinearLayout_lock_info;
                                LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.LinearLayout_lock_info);
                                if (linearLayout4 != null) {
                                    i = R.id.LinearLayout_new_name;
                                    LinearLayout linearLayout5 = (LinearLayout) view.findViewById(R.id.LinearLayout_new_name);
                                    if (linearLayout5 != null) {
                                        i = R.id.LinearLayout_new_password;
                                        LinearLayout linearLayout6 = (LinearLayout) view.findViewById(R.id.LinearLayout_new_password);
                                        if (linearLayout6 != null) {
                                            i = R.id.LinearLayout_notification;
                                            LinearLayout linearLayout7 = (LinearLayout) view.findViewById(R.id.LinearLayout_notification);
                                            if (linearLayout7 != null) {
                                                i = R.id.LinearLayout_setting_defocus_editbox;
                                                LinearLayout linearLayout8 = (LinearLayout) view.findViewById(R.id.LinearLayout_setting_defocus_editbox);
                                                if (linearLayout8 != null) {
                                                    i = R.id.Switch_auto_locking;
                                                    Switch r16 = (Switch) view.findViewById(R.id.Switch_auto_locking);
                                                    if (r16 != null) {
                                                        i = R.id.Switch_auto_unlocking;
                                                        Switch r17 = (Switch) view.findViewById(R.id.Switch_auto_unlocking);
                                                        if (r17 != null) {
                                                            i = R.id.Switch_powerSaving_mode;
                                                            Switch r18 = (Switch) view.findViewById(R.id.Switch_powerSaving_mode);
                                                            if (r18 != null) {
                                                                i = R.id.Switch_shackleByPass;
                                                                Switch r19 = (Switch) view.findViewById(R.id.Switch_shackleByPass);
                                                                if (r19 != null) {
                                                                    i = R.id.Switch_unlocking;
                                                                    Switch r20 = (Switch) view.findViewById(R.id.Switch_unlocking);
                                                                    if (r20 != null) {
                                                                        i = R.id.TextView0002;
                                                                        TextView textView = (TextView) view.findViewById(R.id.TextView0002);
                                                                        if (textView != null) {
                                                                            i = R.id.TextView002;
                                                                            TextView textView2 = (TextView) view.findViewById(R.id.TextView002);
                                                                            if (textView2 != null) {
                                                                                i = R.id.TextView_01;
                                                                                TextView textView3 = (TextView) view.findViewById(R.id.TextView_01);
                                                                                if (textView3 != null) {
                                                                                    i = R.id.TextView01;
                                                                                    TextView textView4 = (TextView) view.findViewById(R.id.TextView01);
                                                                                    if (textView4 != null) {
                                                                                        i = R.id.TextView03;
                                                                                        TextView textView5 = (TextView) view.findViewById(R.id.TextView03);
                                                                                        if (textView5 != null) {
                                                                                            i = R.id.TextView04;
                                                                                            TextView textView6 = (TextView) view.findViewById(R.id.TextView04);
                                                                                            if (textView6 != null) {
                                                                                                i = R.id.TextView_notification;
                                                                                                TextView textView7 = (TextView) view.findViewById(R.id.TextView_notification);
                                                                                                if (textView7 != null) {
                                                                                                    i = R.id.bt_checkMasterTag;
                                                                                                    Button button4 = (Button) view.findViewById(R.id.bt_checkMasterTag);
                                                                                                    if (button4 != null) {
                                                                                                        i = R.id.button_dfu;
                                                                                                        Button button5 = (Button) view.findViewById(R.id.button_dfu);
                                                                                                        if (button5 != null) {
                                                                                                            i = R.id.button_manage_passcode;
                                                                                                            Button button6 = (Button) view.findViewById(R.id.button_manage_passcode);
                                                                                                            if (button6 != null) {
                                                                                                                i = R.id.button_manage_tag;
                                                                                                                Button button7 = (Button) view.findViewById(R.id.button_manage_tag);
                                                                                                                if (button7 != null) {
                                                                                                                    i = R.id.button_manage_user;
                                                                                                                    Button button8 = (Button) view.findViewById(R.id.button_manage_user);
                                                                                                                    if (button8 != null) {
                                                                                                                        i = R.id.button_scanTagId;
                                                                                                                        Button button9 = (Button) view.findViewById(R.id.button_scanTagId);
                                                                                                                        if (button9 != null) {
                                                                                                                            i = R.id.change_pad_password;
                                                                                                                            Button button10 = (Button) view.findViewById(R.id.change_pad_password);
                                                                                                                            if (button10 != null) {
                                                                                                                                i = R.id.editText_new_name;
                                                                                                                                EditText editText = (EditText) view.findViewById(R.id.editText_new_name);
                                                                                                                                if (editText != null) {
                                                                                                                                    i = R.id.editText_new_primary_password;
                                                                                                                                    EditText editText2 = (EditText) view.findViewById(R.id.editText_new_primary_password);
                                                                                                                                    if (editText2 != null) {
                                                                                                                                        i = R.id.editText_new_primary_password2;
                                                                                                                                        EditText editText3 = (EditText) view.findViewById(R.id.editText_new_primary_password2);
                                                                                                                                        if (editText3 != null) {
                                                                                                                                            i = R.id.imageView_audit;
                                                                                                                                            ImageView imageView = (ImageView) view.findViewById(R.id.imageView_audit);
                                                                                                                                            if (imageView != null) {
                                                                                                                                                i = R.id.imageView_locking;
                                                                                                                                                ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView_locking);
                                                                                                                                                if (imageView2 != null) {
                                                                                                                                                    i = R.id.imageView_proximity;
                                                                                                                                                    ImageView imageView3 = (ImageView) view.findViewById(R.id.imageView_proximity);
                                                                                                                                                    if (imageView3 != null) {
                                                                                                                                                        i = R.id.imageView_setting;
                                                                                                                                                        ImageView imageView4 = (ImageView) view.findViewById(R.id.imageView_setting);
                                                                                                                                                        if (imageView4 != null) {
                                                                                                                                                            i = R.id.imageView_watch;
                                                                                                                                                            ImageView imageView5 = (ImageView) view.findViewById(R.id.imageView_watch);
                                                                                                                                                            if (imageView5 != null) {
                                                                                                                                                                i = R.id.ll_adv_time;
                                                                                                                                                                LinearLayout linearLayout9 = (LinearLayout) view.findViewById(R.id.ll_adv_time);
                                                                                                                                                                if (linearLayout9 != null) {
                                                                                                                                                                    i = R.id.ll_auto_locking;
                                                                                                                                                                    LinearLayout linearLayout10 = (LinearLayout) view.findViewById(R.id.ll_auto_locking);
                                                                                                                                                                    if (linearLayout10 != null) {
                                                                                                                                                                        i = R.id.ll_shackle_bypass;
                                                                                                                                                                        LinearLayout linearLayout11 = (LinearLayout) view.findViewById(R.id.ll_shackle_bypass);
                                                                                                                                                                        if (linearLayout11 != null) {
                                                                                                                                                                            i = R.id.scrollView1;
                                                                                                                                                                            ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollView1);
                                                                                                                                                                            if (scrollView != null) {
                                                                                                                                                                                i = R.id.switch_proximity;
                                                                                                                                                                                Switch r47 = (Switch) view.findViewById(R.id.switch_proximity);
                                                                                                                                                                                if (r47 != null) {
                                                                                                                                                                                    i = R.id.textView0003;
                                                                                                                                                                                    TextView textView8 = (TextView) view.findViewById(R.id.textView0003);
                                                                                                                                                                                    if (textView8 != null) {
                                                                                                                                                                                        i = R.id.textView003;
                                                                                                                                                                                        TextView textView9 = (TextView) view.findViewById(R.id.textView003);
                                                                                                                                                                                        if (textView9 != null) {
                                                                                                                                                                                            i = R.id.textView1;
                                                                                                                                                                                            TextView textView10 = (TextView) view.findViewById(R.id.textView1);
                                                                                                                                                                                            if (textView10 != null) {
                                                                                                                                                                                                i = R.id.textView2;
                                                                                                                                                                                                TextView textView11 = (TextView) view.findViewById(R.id.textView2);
                                                                                                                                                                                                if (textView11 != null) {
                                                                                                                                                                                                    i = R.id.textView_advertising;
                                                                                                                                                                                                    TextView textView12 = (TextView) view.findViewById(R.id.textView_advertising);
                                                                                                                                                                                                    if (textView12 != null) {
                                                                                                                                                                                                        i = R.id.textView_advertising_title;
                                                                                                                                                                                                        TextView textView13 = (TextView) view.findViewById(R.id.textView_advertising_title);
                                                                                                                                                                                                        if (textView13 != null) {
                                                                                                                                                                                                            i = R.id.textView_advertising_title001;
                                                                                                                                                                                                            TextView textView14 = (TextView) view.findViewById(R.id.textView_advertising_title001);
                                                                                                                                                                                                            if (textView14 != null) {
                                                                                                                                                                                                                i = R.id.textView_auditCount;
                                                                                                                                                                                                                TextView textView15 = (TextView) view.findViewById(R.id.textView_auditCount);
                                                                                                                                                                                                                if (textView15 != null) {
                                                                                                                                                                                                                    i = R.id.textView_firmware_v;
                                                                                                                                                                                                                    TextView textView16 = (TextView) view.findViewById(R.id.textView_firmware_v);
                                                                                                                                                                                                                    if (textView16 != null) {
                                                                                                                                                                                                                        i = R.id.textView_model;
                                                                                                                                                                                                                        TextView textView17 = (TextView) view.findViewById(R.id.textView_model);
                                                                                                                                                                                                                        if (textView17 != null) {
                                                                                                                                                                                                                            i = R.id.textView_new_primary_password_big_title;
                                                                                                                                                                                                                            TextView textView18 = (TextView) view.findViewById(R.id.textView_new_primary_password_big_title);
                                                                                                                                                                                                                            if (textView18 != null) {
                                                                                                                                                                                                                                i = R.id.textView_new_primary_password_title;
                                                                                                                                                                                                                                TextView textView19 = (TextView) view.findViewById(R.id.textView_new_primary_password_title);
                                                                                                                                                                                                                                if (textView19 != null) {
                                                                                                                                                                                                                                    i = R.id.textView_standby;
                                                                                                                                                                                                                                    TextView textView20 = (TextView) view.findViewById(R.id.textView_standby);
                                                                                                                                                                                                                                    if (textView20 != null) {
                                                                                                                                                                                                                                        i = R.id.tv_auditCount;
                                                                                                                                                                                                                                        TextView textView21 = (TextView) view.findViewById(R.id.tv_auditCount);
                                                                                                                                                                                                                                        if (textView21 != null) {
                                                                                                                                                                                                                                            i = R.id.tv_audit_count1;
                                                                                                                                                                                                                                            TextView textView22 = (TextView) view.findViewById(R.id.tv_audit_count1);
                                                                                                                                                                                                                                            if (textView22 != null) {
                                                                                                                                                                                                                                                i = R.id.tv_autoLocking;
                                                                                                                                                                                                                                                TextView textView23 = (TextView) view.findViewById(R.id.tv_autoLocking);
                                                                                                                                                                                                                                                if (textView23 != null) {
                                                                                                                                                                                                                                                    i = R.id.tv_broadCastTime;
                                                                                                                                                                                                                                                    TextView textView24 = (TextView) view.findViewById(R.id.tv_broadCastTime);
                                                                                                                                                                                                                                                    if (textView24 != null) {
                                                                                                                                                                                                                                                        i = R.id.tv_lockReconnectionTime;
                                                                                                                                                                                                                                                        TextView textView25 = (TextView) view.findViewById(R.id.tv_lockReconnectionTime);
                                                                                                                                                                                                                                                        if (textView25 != null) {
                                                                                                                                                                                                                                                            i = R.id.tv_shackleByPass;
                                                                                                                                                                                                                                                            TextView textView26 = (TextView) view.findViewById(R.id.tv_shackleByPass);
                                                                                                                                                                                                                                                            if (textView26 != null) {
                                                                                                                                                                                                                                                                i = R.id.tv_tagCount;
                                                                                                                                                                                                                                                                TextView textView27 = (TextView) view.findViewById(R.id.tv_tagCount);
                                                                                                                                                                                                                                                                if (textView27 != null) {
                                                                                                                                                                                                                                                                    i = R.id.tv_tagCount1;
                                                                                                                                                                                                                                                                    TextView textView28 = (TextView) view.findViewById(R.id.tv_tagCount1);
                                                                                                                                                                                                                                                                    if (textView28 != null) {
                                                                                                                                                                                                                                                                        return new TestingLayoutSettingsBinding((LinearLayout) view, button, button2, button3, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, r16, r17, r18, r19, r20, textView, textView2, textView3, textView4, textView5, textView6, textView7, button4, button5, button6, button7, button8, button9, button10, editText, editText2, editText3, imageView, imageView2, imageView3, imageView4, imageView5, linearLayout9, linearLayout10, linearLayout11, scrollView, r47, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21, textView22, textView23, textView24, textView25, textView26, textView27, textView28);
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
