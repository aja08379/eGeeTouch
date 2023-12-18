package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.android.gms.common.ConnectionResult;
/* loaded from: classes.dex */
public class Fragment_pairing extends Fragment {
    public static Handler handler_pairing = new Handler();
    public static boolean has_shown = false;
    ImageView imageAnim;
    TextView instruction;
    TextView instruction_name;
    LinearLayout more_function;
    LinearLayout pair_again;
    View rootView;
    TextView title_lock_name;
    int refresh_rate = 100;
    boolean transmission_error_show = false;
    public Runnable runnable_pairing = new Runnable() { // from class: com.egeetouch.egeetouch_manager.Fragment_pairing.1
        @Override // java.lang.Runnable
        public void run() {
            if (MainActivity.transmission_error) {
                if (!Fragment_pairing.has_shown) {
                    Fragment_pairing.has_shown = true;
                    new SweetAlertDialog(MainActivity.context, 1).setTitleText(MainActivity.context.getResources().getString(R.string.pair_and_sync)).setContentText(MainActivity.context.getResources().getString(R.string.transmission_error_pls_try_again)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                    ((Vibrator) Fragment_pairing.this.getActivity().getSystemService("vibrator")).vibrate(1500L);
                }
            } else {
                Fragment_pairing.this.transmission_error_show = false;
                if (TaskManagement.password_varified) {
                    if (TaskManagement.password_varified_correct) {
                        if (MainActivity.animation.isRunning()) {
                            if (BLEService.selected_lock_model.equals("GT2000")) {
                                if (TaskManagement.password_varified_correct && Fragment_pairing.has_shown) {
                                    MainActivity.animation.addFrame(Fragment_pairing.this.getResources().getDrawable(R.drawable.unlocked), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
                                }
                            } else if (BLEService.selected_lock_model.equals("GT3000") && TaskManagement.password_varified_correct && Fragment_pairing.has_shown) {
                                MainActivity.animation.addFrame(Fragment_pairing.this.getResources().getDrawable(R.drawable.unlocked_luggage), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
                            }
                            MainActivity.animation.setOneShot(false);
                            Fragment_pairing fragment_pairing = Fragment_pairing.this;
                            fragment_pairing.imageAnim = (ImageView) fragment_pairing.rootView.findViewById(R.id.imageView_pairing);
                            Fragment_pairing.this.imageAnim.setImageDrawable(MainActivity.animation);
                            MainActivity.animation.start();
                        }
                        if (!Fragment_pairing.has_shown) {
                            Fragment_pairing.has_shown = true;
                            new SweetAlertDialog(MainActivity.context, 2).setTitleText(MainActivity.context.getResources().getString(R.string.pair_and_sync)).setContentText(MainActivity.context.getResources().getString(R.string.congratulation_successful_pairing_and_unlocking)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                            ((Vibrator) Fragment_pairing.this.getActivity().getSystemService("vibrator")).vibrate(new long[]{1, 500, 500, 500, 500, 1500}, -1);
                            MediaPlayer.create(Fragment_pairing.this.getActivity(), (int) R.raw.mission_completed).start();
                        }
                        Fragment_pairing fragment_pairing2 = Fragment_pairing.this;
                        fragment_pairing2.pair_again = (LinearLayout) fragment_pairing2.rootView.findViewById(R.id.LinearLayout_pair_again);
                        Fragment_pairing.this.pair_again.setVisibility(8);
                        Fragment_pairing fragment_pairing3 = Fragment_pairing.this;
                        fragment_pairing3.more_function = (LinearLayout) fragment_pairing3.rootView.findViewById(R.id.LinearLayout_more_function);
                        Fragment_pairing.this.more_function.setVisibility(0);
                        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(Fragment_pairing.this.getActivity());
                        if (Fragment_pairing.this.instruction_name != null && !defaultSharedPreferences.getString("username", "").equals("")) {
                            ((TextView) Fragment_pairing.this.rootView.findViewById(R.id.TextView_username)).setText(Fragment_pairing.this.getString(R.string.hi) + " " + defaultSharedPreferences.getString("username", "") + ",");
                        }
                        if (Fragment_pairing.this.isAdded()) {
                            Fragment_pairing.this.instruction.setText(Fragment_pairing.this.getString(R.string.pair_done));
                        }
                    } else if (!Fragment_pairing.has_shown) {
                        Fragment_pairing.has_shown = true;
                        new SweetAlertDialog(MainActivity.context, 1).setTitleText(MainActivity.context.getResources().getString(R.string.pair_and_sync)).setContentText(MainActivity.context.getResources().getString(R.string.password_is_incorrect)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                        ((Vibrator) Fragment_pairing.this.getActivity().getSystemService("vibrator")).vibrate(1500L);
                        Fragment_pairing fragment_pairing4 = Fragment_pairing.this;
                        fragment_pairing4.pair_again = (LinearLayout) fragment_pairing4.rootView.findViewById(R.id.LinearLayout_pair_again);
                        Fragment_pairing.this.pair_again.setVisibility(0);
                        Fragment_pairing fragment_pairing5 = Fragment_pairing.this;
                        fragment_pairing5.more_function = (LinearLayout) fragment_pairing5.rootView.findViewById(R.id.LinearLayout_more_function);
                        Fragment_pairing.this.more_function.setVisibility(8);
                        SharedPreferences defaultSharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(Fragment_pairing.this.getActivity());
                        if (Fragment_pairing.this.instruction_name != null && !defaultSharedPreferences2.getString("username", "").equals("")) {
                            ((TextView) Fragment_pairing.this.rootView.findViewById(R.id.TextView_username)).setText(Fragment_pairing.this.getString(R.string.hi) + " " + defaultSharedPreferences2.getString("username", "") + ",");
                        }
                        if (Fragment_pairing.this.isAdded()) {
                            Fragment_pairing.this.instruction.setText(Fragment_pairing.this.getString(R.string.pair_incorrect));
                        }
                    }
                }
            }
            Fragment_pairing.handler_pairing.postDelayed(this, Fragment_pairing.this.refresh_rate);
        }
    };

    public static Fragment_pairing newInstance() {
        return new Fragment_pairing();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.pairing);
        update_UI();
        this.title_lock_name = (TextView) this.rootView.findViewById(R.id.textView_pairing_lock_name);
        this.instruction_name = (TextView) this.rootView.findViewById(R.id.TextView_username);
        this.instruction = (TextView) this.rootView.findViewById(R.id.textView_instruction);
        this.pair_again = (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_pair_again);
        this.more_function = (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_more_function);
        Handler handler = new Handler();
        handler_pairing = handler;
        handler.postDelayed(this.runnable_pairing, this.refresh_rate);
    }

    @Override // android.app.Fragment
    public void onPause() {
        handler_pairing.removeCallbacks(this.runnable_pairing);
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onStop() {
        handler_pairing.removeCallbacks(this.runnable_pairing);
        super.onStop();
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.findItem(R.id.action_button_tutorial).setVisible(false);
        menuInflater.inflate(R.menu.nfc, menu);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.fragment_pairing, viewGroup, false);
        setHasOptionsMenu(false);
        update_UI();
        if (MainActivity.fab != null) {
            MainActivity.fab.setVisibility(4);
        }
        if (MainActivity.fab_share != null) {
            MainActivity.fab_share.setVisibility(4);
        }
        if (MainActivity.fab_admin_access_locks != null) {
            MainActivity.fab_admin_access_locks.setVisibility(8);
        }
        if (MainActivity.dashboard_listview != null) {
            MainActivity.dashboard_listview.setVisibility(4);
        }
        MainActivity.pullToRefresh.setEnabled(false);
        this.title_lock_name = (TextView) this.rootView.findViewById(R.id.textView_pairing_lock_name);
        this.instruction_name = (TextView) this.rootView.findViewById(R.id.TextView_username);
        this.instruction = (TextView) this.rootView.findViewById(R.id.textView_instruction);
        this.pair_again = (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_pair_again);
        this.more_function = (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_more_function);
        TextView textView = this.title_lock_name;
        if (textView != null) {
            textView.setText(BLEService.selected_lock_name);
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (this.instruction_name != null && !defaultSharedPreferences.getString("username", "").equals("")) {
            this.instruction_name.setText(getString(R.string.hi) + " " + defaultSharedPreferences.getString("username", "") + ",");
        }
        if (TaskManagement.password_varified) {
            LinearLayout linearLayout = (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_pair_again);
            this.pair_again = linearLayout;
            linearLayout.setVisibility(8);
            LinearLayout linearLayout2 = (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_more_function);
            this.more_function = linearLayout2;
            linearLayout2.setVisibility(0);
            if (TaskManagement.password_varified_correct) {
                LinearLayout linearLayout3 = (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_pair_again);
                this.pair_again = linearLayout3;
                linearLayout3.setVisibility(8);
                LinearLayout linearLayout4 = (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_more_function);
                this.more_function = linearLayout4;
                linearLayout4.setVisibility(0);
                if (isAdded()) {
                    this.instruction.setText(getString(R.string.pair_done));
                }
            } else {
                LinearLayout linearLayout5 = (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_pair_again);
                this.pair_again = linearLayout5;
                linearLayout5.setVisibility(0);
                LinearLayout linearLayout6 = (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_more_function);
                this.more_function = linearLayout6;
                linearLayout6.setVisibility(8);
                this.instruction = (TextView) this.rootView.findViewById(R.id.textView_instruction);
                if (isAdded()) {
                    this.instruction.setText(getString(R.string.pair_incorrect));
                }
            }
        } else {
            if (isAdded()) {
                this.instruction.setText(getString(R.string.pair));
            }
            this.pair_again.setVisibility(8);
            this.more_function.setVisibility(8);
        }
        MainActivity.ready_2_pair = true;
        Handler handler = new Handler();
        handler_pairing = handler;
        handler.postDelayed(this.runnable_pairing, this.refresh_rate);
        return this.rootView;
    }

    private void update_UI() {
        MainActivity.animation = new AnimationDrawable();
        if (BLEService.selected_lock_model.equals("GT2000")) {
            if (TaskManagement.password_varified_correct) {
                MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.unlocked), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
            } else {
                MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair1), 300);
                MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair2), 300);
                MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair3), 300);
                MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair4), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
            }
        } else if (BLEService.selected_lock_model.equals("GT3000")) {
            if (TaskManagement.password_varified_correct) {
                MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.unlocked_luggage), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
            } else {
                MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair1_luggage), 300);
                MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair2_luggage), 300);
                MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair3_luggage), 300);
                MainActivity.animation.addFrame(getResources().getDrawable(R.drawable.pair4_luggage), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
            }
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
