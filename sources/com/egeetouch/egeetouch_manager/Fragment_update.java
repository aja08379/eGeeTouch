package com.egeetouch.egeetouch_manager;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.android.gms.common.ConnectionResult;
/* loaded from: classes.dex */
public class Fragment_update extends Fragment {
    private static AlertDialog.Builder alertDialogBuilder;
    public static Handler handler_update = new Handler();
    ImageView imageAnim;
    TextView instruction;
    TextView instruction_name;
    LinearLayout more_function;
    LinearLayout pair_again;
    View rootView;
    TextView title_lock_name;
    int refresh_rate = 100;
    boolean transmission_error_show = false;
    public Runnable runnable_update = new Runnable() { // from class: com.egeetouch.egeetouch_manager.Fragment_update.1
        @Override // java.lang.Runnable
        public void run() {
            if (MainActivity.transmission_error) {
                if (!Fragment_pairing.has_shown) {
                    Fragment_pairing.has_shown = true;
                    new SweetAlertDialog(MainActivity.context, 1).setTitleText(MainActivity.context.getResources().getString(R.string.pair_and_sync)).setContentText(MainActivity.context.getResources().getString(R.string.transmission_error_pls_try_again)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                    ((Vibrator) Fragment_update.this.getActivity().getSystemService("vibrator")).vibrate(1500L);
                }
            } else {
                Fragment_update.this.transmission_error_show = false;
                if (TaskManagement.current_function == 48) {
                    if (TaskManagement.password_varified) {
                        if (TaskManagement.password_varified_correct) {
                            if (MainActivity.animation.isRunning()) {
                                if (BLEService.selected_lock_model.equals("GT2000")) {
                                    if (TaskManagement.updated_flag) {
                                        MainActivity.animation.addFrame(Fragment_update.this.getResources().getDrawable(R.drawable.updated), 300);
                                    } else {
                                        MainActivity.animation.addFrame(Fragment_update.this.getResources().getDrawable(R.drawable.unlocked), 300);
                                    }
                                } else if (BLEService.selected_lock_model.equals("GT3000")) {
                                    if (TaskManagement.updated_flag) {
                                        MainActivity.animation.addFrame(Fragment_update.this.getResources().getDrawable(R.drawable.updated_luggage), 300);
                                    } else {
                                        MainActivity.animation.addFrame(Fragment_update.this.getResources().getDrawable(R.drawable.unlocked_luggage), 300);
                                    }
                                }
                                MainActivity.animation.setOneShot(false);
                                Fragment_update fragment_update = Fragment_update.this;
                                fragment_update.imageAnim = (ImageView) fragment_update.rootView.findViewById(R.id.imageView_pairing);
                                Fragment_update.this.imageAnim.setImageDrawable(MainActivity.animation);
                                MainActivity.animation.start();
                            }
                            if (!Fragment_pairing.has_shown) {
                                Fragment_pairing.has_shown = true;
                                new SweetAlertDialog(MainActivity.context, 2).setTitleText(MainActivity.context.getResources().getString(R.string.pair_and_sync)).setContentText(MainActivity.context.getResources().getString(R.string.congratulation_successful_pairing_and_unlocking)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                                try {
                                    ((Vibrator) Fragment_update.this.getActivity().getSystemService("vibrator")).vibrate(new long[]{1, 500, 500, 500, 500, 1500}, -1);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                MediaPlayer.create(Fragment_update.this.getActivity(), (int) R.raw.mission_completed).start();
                                Fragment_update fragment_update2 = Fragment_update.this;
                                fragment_update2.pair_again = (LinearLayout) fragment_update2.rootView.findViewById(R.id.LinearLayout_pair_again);
                                Fragment_update.this.pair_again.setVisibility(8);
                                Fragment_update fragment_update3 = Fragment_update.this;
                                fragment_update3.more_function = (LinearLayout) fragment_update3.rootView.findViewById(R.id.LinearLayout_more_function);
                                Fragment_update.this.more_function.setVisibility(0);
                                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(Fragment_update.this.getActivity());
                                if (Fragment_update.this.instruction_name != null && !defaultSharedPreferences.getString("username", "").equals("")) {
                                    ((TextView) Fragment_update.this.rootView.findViewById(R.id.TextView_username)).setText(Fragment_update.this.getString(R.string.hi) + " " + defaultSharedPreferences.getString("username", "") + ",");
                                }
                                if (Fragment_update.this.isAdded()) {
                                    Fragment_update.this.instruction.setText(Fragment_update.this.getString(R.string.pair_done));
                                }
                            }
                        } else if (!Fragment_pairing.has_shown) {
                            Fragment_pairing.has_shown = true;
                            new SweetAlertDialog(MainActivity.context, 1).setTitleText(MainActivity.context.getResources().getString(R.string.pair_and_sync)).setContentText(MainActivity.context.getResources().getString(R.string.password_is_incorrect)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                            ((Vibrator) Fragment_update.this.getActivity().getSystemService("vibrator")).vibrate(1500L);
                            Fragment_update fragment_update4 = Fragment_update.this;
                            fragment_update4.pair_again = (LinearLayout) fragment_update4.rootView.findViewById(R.id.LinearLayout_pair_again);
                            Fragment_update.this.pair_again.setVisibility(0);
                            Fragment_update fragment_update5 = Fragment_update.this;
                            fragment_update5.more_function = (LinearLayout) fragment_update5.rootView.findViewById(R.id.LinearLayout_more_function);
                            Fragment_update.this.more_function.setVisibility(8);
                            SharedPreferences defaultSharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(Fragment_update.this.getActivity());
                            if (Fragment_update.this.instruction_name != null && !defaultSharedPreferences2.getString("username", "").equals("")) {
                                ((TextView) Fragment_update.this.rootView.findViewById(R.id.TextView_username)).setText(Fragment_update.this.getString(R.string.hi) + " " + defaultSharedPreferences2.getString("username", "") + ",");
                            }
                            if (Fragment_update.this.isAdded()) {
                                Fragment_update.this.instruction.setText(Fragment_update.this.getString(R.string.pair_incorrect));
                            }
                        }
                    }
                } else if (TaskManagement.current_function == 131) {
                    if (Fragment_update.this.isAdded()) {
                        try {
                            Fragment_update.this.instruction.setText(Fragment_update.this.getString(R.string.pair_update4));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (TaskManagement.log_extract10_done) {
                        TaskManagement.current_function = TaskManagement.log_extract20;
                        TaskManagement.log_extract10_done = false;
                        TaskManagement.log_extract20_done = false;
                        if (Fragment_update.this.isAdded()) {
                            try {
                                Fragment_update.this.instruction.setText(Fragment_update.this.getString(R.string.pair_update5));
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        DatabaseVariable.lockdb_getversion(BLEService.selected_lock_name);
                    }
                } else if (TaskManagement.current_function == 132) {
                    if (TaskManagement.log_extract20_done) {
                        TaskManagement.log_extract10_done = false;
                        TaskManagement.log_extract20_done = false;
                        if (Fragment_update.this.isAdded()) {
                            try {
                                Fragment_update.this.instruction.setText(Fragment_update.this.getString(R.string.pair_update6));
                            } catch (Exception e4) {
                                e4.printStackTrace();
                            }
                        }
                        TaskManagement.current_function = 48;
                        ((LinearLayout) Fragment_update.this.rootView.findViewById(R.id.LinearLayout_pair_again)).setVisibility(8);
                        ((LinearLayout) Fragment_update.this.rootView.findViewById(R.id.LinearLayout_more_function)).setVisibility(0);
                        MainActivity.fragmentManager.popBackStack();
                        Fragment_manage_audit_trail_nfc.is_new_retrieve_data = true;
                        Fragment_manage_audit_trail_nfc.start_convert_address = true;
                    }
                } else if (TaskManagement.current_function == 16) {
                    if (TaskManagement.tag_update_done) {
                        TaskManagement.tag_update_done = false;
                        TaskManagement.current_function = 48;
                        ((LinearLayout) Fragment_update.this.rootView.findViewById(R.id.LinearLayout_pair_again)).setVisibility(8);
                        ((LinearLayout) Fragment_update.this.rootView.findViewById(R.id.LinearLayout_more_function)).setVisibility(0);
                        SharedPreferences defaultSharedPreferences3 = PreferenceManager.getDefaultSharedPreferences(Fragment_update.this.getActivity());
                        if (Fragment_update.this.instruction_name != null && !defaultSharedPreferences3.getString("username", "").equals("")) {
                            ((TextView) Fragment_update.this.rootView.findViewById(R.id.TextView_username)).setText(Fragment_update.this.getString(R.string.hi) + " " + defaultSharedPreferences3.getString("username", "") + ",");
                        }
                        if (!TaskManagement.tag_update_done_wrong_lock) {
                            Fragment_update.this.instruction.setText(Fragment_update.this.getString(R.string.pair_update_done));
                            new SweetAlertDialog(MainActivity.context, 2).setTitleText(MainActivity.context.getResources().getString(R.string.pair_and_sync)).setContentText(MainActivity.context.getResources().getString(R.string.update_successfully)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                            MainActivity.fragmentManager.popBackStack();
                        } else {
                            Fragment_update.this.instruction.setText(Fragment_update.this.getString(R.string.pair_update_incorrectlock_done));
                        }
                    }
                } else if (TaskManagement.current_function == 128) {
                    if (TaskManagement.tag_restore_done) {
                        TaskManagement.tag_restore_done = false;
                        ((LinearLayout) Fragment_update.this.rootView.findViewById(R.id.LinearLayout_pair_again)).setVisibility(8);
                        ((LinearLayout) Fragment_update.this.rootView.findViewById(R.id.LinearLayout_more_function)).setVisibility(0);
                        SharedPreferences defaultSharedPreferences4 = PreferenceManager.getDefaultSharedPreferences(Fragment_update.this.getActivity());
                        if (Fragment_update.this.instruction_name != null && !defaultSharedPreferences4.getString("username", "").equals("")) {
                            ((TextView) Fragment_update.this.rootView.findViewById(R.id.TextView_username)).setText(Fragment_update.this.getString(R.string.hi) + " " + defaultSharedPreferences4.getString("username", "") + ",");
                        }
                        MainActivity.fragmentManager.popBackStack();
                    }
                } else if (TaskManagement.current_function == 81 || TaskManagement.current_function == 33) {
                    try {
                        Fragment_update.this.instruction.setText(Fragment_update.this.getString(R.string.pair_update1));
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                    if (TaskManagement.admin_update_done) {
                        if (!TaskManagement.admin_update_done_wrong_lock) {
                            TaskManagement.admin_update_done = false;
                            TaskManagement.user_update_done = false;
                            TaskManagement.current_function = 48;
                            new SweetAlertDialog(MainActivity.context, 2).setTitleText(MainActivity.context.getResources().getString(R.string.pair_and_sync)).setContentText(MainActivity.context.getResources().getString(R.string.update_successfully)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                            MainActivity.fragmentManager.popBackStack();
                        } else {
                            Fragment_update.this.instruction.setText(Fragment_update.this.getString(R.string.pair_update_incorrectlock_done));
                        }
                    }
                } else if (TaskManagement.current_function == 82) {
                    try {
                        Fragment_update.this.instruction.setText(Fragment_update.this.getString(R.string.pair_update2));
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                    if (TaskManagement.admin_update2_done && TaskManagement.admin_update_done_wrong_lock) {
                        Fragment_update.this.instruction.setText(Fragment_update.this.getString(R.string.pair_update_incorrectlock_done));
                    }
                } else if (TaskManagement.current_function == 83) {
                    try {
                        Fragment_update.this.instruction.setText(Fragment_update.this.getString(R.string.pair_update3));
                    } catch (Exception e7) {
                        e7.printStackTrace();
                    }
                    if (TaskManagement.admin_update3_done) {
                        if (!TaskManagement.admin_update_done_wrong_lock) {
                            TaskManagement.admin_update_done = false;
                            TaskManagement.admin_update2_done = false;
                            TaskManagement.admin_update3_done = false;
                            TaskManagement.current_function = 48;
                            ((LinearLayout) Fragment_update.this.rootView.findViewById(R.id.LinearLayout_pair_again)).setVisibility(8);
                            ((LinearLayout) Fragment_update.this.rootView.findViewById(R.id.LinearLayout_more_function)).setVisibility(0);
                            Fragment_update.this.instruction.setText(Fragment_update.this.getString(R.string.pair_update_done));
                            new SweetAlertDialog(MainActivity.context, 2).setTitleText(MainActivity.context.getResources().getString(R.string.pair_and_sync)).setContentText(MainActivity.context.getResources().getString(R.string.update_successfully)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                            MainActivity.fragmentManager.popBackStack();
                        } else {
                            Fragment_update.this.instruction.setText(Fragment_update.this.getString(R.string.pair_update_incorrectlock_done));
                        }
                    }
                }
            }
            Fragment_update.handler_update.postDelayed(this, Fragment_update.this.refresh_rate);
        }
    };

    public static Fragment_update newInstance() {
        return new Fragment_update();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.pairing_update);
        Handler handler = new Handler();
        handler_update = handler;
        handler.postDelayed(this.runnable_update, this.refresh_rate);
    }

    @Override // android.app.Fragment
    public void onPause() {
        handler_update.removeCallbacks(this.runnable_update);
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onStop() {
        handler_update.removeCallbacks(this.runnable_update);
        super.onStop();
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.fragment_pairing_update, viewGroup, false);
        alertDialogBuilder = new AlertDialog.Builder(getActivity());
        update_UI();
        this.title_lock_name = (TextView) this.rootView.findViewById(R.id.textView_pairing_lock_name);
        this.instruction_name = (TextView) this.rootView.findViewById(R.id.TextView_username);
        this.instruction = (TextView) this.rootView.findViewById(R.id.textView_instruction);
        this.pair_again = (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_pair_again);
        this.more_function = (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_more_function);
        this.title_lock_name.setText(BLEService.selected_lock_name);
        this.instruction_name = (TextView) this.rootView.findViewById(R.id.TextView_username);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (this.instruction_name != null && !defaultSharedPreferences.getString("username", "").equals("")) {
            ((TextView) this.rootView.findViewById(R.id.TextView_username)).setText(getString(R.string.hi) + " " + defaultSharedPreferences.getString("username", "") + ",");
        }
        if (isAdded()) {
            try {
                this.instruction.setText(getString(R.string.pair_update));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.pair_again.setVisibility(8);
        this.more_function.setVisibility(8);
        MainActivity.ready_2_pair = true;
        Handler handler = new Handler();
        handler_update = handler;
        handler.postDelayed(this.runnable_update, this.refresh_rate);
        return this.rootView;
    }

    private void update_UI() {
        MainActivity.animation = new AnimationDrawable();
        if (BLEService.selected_lock_model.equals("GT2000")) {
            MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair1), 300);
            MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair2), 300);
            MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair3), 300);
            MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair4), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        } else if (BLEService.selected_lock_model.equals("GT3000")) {
            MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair1_luggage), 300);
            MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair2_luggage), 300);
            MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair3_luggage), 300);
            MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair4_luggage), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        }
        MainActivity.animation.setOneShot(false);
        ImageView imageView = (ImageView) this.rootView.findViewById(R.id.imageView_pairing);
        this.imageAnim = imageView;
        imageView.setImageDrawable(MainActivity.animation);
        if (MainActivity.ready_2_pair) {
            MainActivity.animation.start();
        }
    }
}
