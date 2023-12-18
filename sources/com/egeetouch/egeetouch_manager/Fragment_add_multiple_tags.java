package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.facebook.appevents.AppEventsConstants;
import es.dmoral.toasty.Toasty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class Fragment_add_multiple_tags extends Fragment {
    UI_BLE uiBle;
    public static int vertexCount = 15;
    public static ArrayList<ArrayList<String>> graph2 = new ArrayList<>(vertexCount);
    public static List<String> list_tag_error = new ArrayList();
    public static List<String> new_tag_id = new ArrayList();
    public static List<String> new_tag_name = new ArrayList();
    public static List<Integer> list_empty_pages = new ArrayList();
    public static List<Integer> list_empty_page_size = new ArrayList();
    public static int last_page_number = 0;
    View rootView = null;
    ArrayList<ArrayList<Integer>> graph1 = new ArrayList<>(vertexCount);
    ArrayList<ArrayList<Integer>> graph_empty1 = new ArrayList<>(vertexCount);
    ArrayList<ArrayList<ArrayList<String>>> space = new ArrayList<>(vertexCount);
    ArrayList<ArrayList<ArrayList<String>>> graph_empty = new ArrayList<>(vertexCount);
    ArrayList<ArrayList<ArrayList<String>>> graph = new ArrayList<>(vertexCount);
    List<String> tag_id = new ArrayList();
    int index = 0;
    int empty_page_count = 0;
    int count_plusTag = 1;
    long total_plusTag = 0;
    public boolean add_tag_error = false;

    public static Fragment_add_multiple_tags newInstance() {
        return new Fragment_add_multiple_tags();
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.global, menu);
    }

    @Override // android.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_button_disconnect) {
            new SweetAlertDialog(getActivity()).setTitleText(getString(R.string.power_off)).setContentText(getString(R.string.are_you_sure_power_off_lock)).setConfirmText(getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_multiple_tags.1
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                    Fragment_BLE.disconnected_trigger = true;
                    BLEService.cancel_scaning_triggered = true;
                    BLEService.Ble_Mode = BLEService.Send_timeStamp;
                    BLEService.disconnect_triggered = true;
                    MainActivity.isUserClickedShutdown = true;
                    MainActivity.shutdown_dialog = new SweetAlertDialog(MainActivity.context, 5);
                    MainActivity.shutdown_dialog.setTitleText(Fragment_add_multiple_tags.this.getString(R.string.power_off));
                    MainActivity.shutdown_dialog.setCancelable(false);
                    MainActivity.shutdown_dialog.setCanceledOnTouchOutside(false);
                    MainActivity.shutdown_dialog.show();
                    MainActivity.stopBackStack = false;
                    ((AppCompatActivity) Fragment_add_multiple_tags.this.getActivity()).getSupportActionBar().setTitle(R.string.dashboard);
                    MediaPlayer create = MediaPlayer.create(Fragment_add_multiple_tags.this.getActivity(), (int) R.raw.disconnectinapp);
                    create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_multiple_tags.1.1
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

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.fragment_multiple_tag_adding, viewGroup, false);
        setHasOptionsMenu(true);
        getActivity().setTitle(this.rootView.getResources().getString(R.string.add_tag));
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        this.uiBle = new UI_BLE(MainActivity.context);
        Cursor rawQuery = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_number_lock_exist(BLEService.selected_lock_name), null);
        while (rawQuery.moveToNext()) {
            arrayList.add(rawQuery.getString(0));
            arrayList2.add(rawQuery.getString(1));
        }
        for (int i = 0; i < vertexCount; i++) {
            this.graph1.add(new ArrayList<>());
            this.graph_empty1.add(new ArrayList<>());
            graph2.add(new ArrayList<>());
        }
        for (int i2 = 0; i2 < vertexCount; i2++) {
            this.space.add(new ArrayList<>(vertexCount));
            this.graph.add(new ArrayList<>(vertexCount));
            this.graph_empty.add(new ArrayList<>(vertexCount));
            for (int i3 = 0; i3 < vertexCount; i3++) {
                this.space.get(i2).add(new ArrayList<>(vertexCount));
                this.graph.get(i2).add(new ArrayList<>(vertexCount));
                this.graph_empty.get(i2).add(new ArrayList<>(vertexCount));
            }
        }
        for (int i4 = 0; i4 < vertexCount; i4++) {
            for (int i5 = 0; i5 < vertexCount; i5++) {
                this.graph_empty1.get(i4).add(Integer.valueOf(i5));
            }
        }
        for (int i6 = 0; i6 < 15; i6++) {
            list_empty_pages.add(Integer.valueOf(i6));
            list_empty_page_size.add(0);
        }
        Button button = (Button) this.rootView.findViewById(R.id.button5);
        Button button2 = (Button) this.rootView.findViewById(R.id.button4);
        final Button button3 = (Button) this.rootView.findViewById(R.id.btn_plus_add_tag);
        final Button button4 = (Button) this.rootView.findViewById(R.id.btn_remove_tag);
        button4.setEnabled(false);
        button4.setAlpha(0.3f);
        final TextView[] textViewArr = {(TextView) this.rootView.findViewById(R.id.textView_add_tag_title), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title1), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title2), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title3), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title4), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title5), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title6), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title7), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title8), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title9), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title10), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title11), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title12), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title13), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title14), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title15), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title16), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title17), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title18), (TextView) this.rootView.findViewById(R.id.textView_add_tag_title19)};
        TextView[] textViewArr2 = new TextView[20];
        TextView textView = (TextView) this.rootView.findViewById(R.id.textView_tag_name);
        TextView textView2 = (TextView) this.rootView.findViewById(R.id.textView_tag_name1);
        TextView textView3 = (TextView) this.rootView.findViewById(R.id.textView_tag_name2);
        TextView textView4 = (TextView) this.rootView.findViewById(R.id.textView_tag_name3);
        TextView textView5 = (TextView) this.rootView.findViewById(R.id.textView_tag_name4);
        TextView textView6 = (TextView) this.rootView.findViewById(R.id.textView_tag_name5);
        TextView textView7 = (TextView) this.rootView.findViewById(R.id.textView_tag_name6);
        TextView textView8 = (TextView) this.rootView.findViewById(R.id.textView_tag_name7);
        TextView textView9 = (TextView) this.rootView.findViewById(R.id.textView_tag_name8);
        TextView textView10 = (TextView) this.rootView.findViewById(R.id.textView_tag_name9);
        TextView textView11 = (TextView) this.rootView.findViewById(R.id.textView_tag_name10);
        TextView textView12 = (TextView) this.rootView.findViewById(R.id.textView_tag_name11);
        TextView textView13 = (TextView) this.rootView.findViewById(R.id.textView_tag_name12);
        TextView textView14 = (TextView) this.rootView.findViewById(R.id.textView_tag_name13);
        TextView textView15 = (TextView) this.rootView.findViewById(R.id.textView_tag_name14);
        TextView textView16 = (TextView) this.rootView.findViewById(R.id.textView_tag_name15);
        TextView textView17 = (TextView) this.rootView.findViewById(R.id.textView_tag_name16);
        TextView textView18 = (TextView) this.rootView.findViewById(R.id.textView_tag_name17);
        TextView textView19 = (TextView) this.rootView.findViewById(R.id.textView_tag_name18);
        TextView textView20 = (TextView) this.rootView.findViewById(R.id.textView_tag_name19);
        TextView[] textViewArr3 = new TextView[20];
        TextView textView21 = (TextView) this.rootView.findViewById(R.id.textView_tag_id);
        TextView textView22 = (TextView) this.rootView.findViewById(R.id.textView_tag_id1);
        TextView textView23 = (TextView) this.rootView.findViewById(R.id.textView_tag_id2);
        TextView textView24 = (TextView) this.rootView.findViewById(R.id.textView_tag_id3);
        TextView textView25 = (TextView) this.rootView.findViewById(R.id.textView_tag_id4);
        TextView textView26 = (TextView) this.rootView.findViewById(R.id.textView_tag_id5);
        TextView textView27 = (TextView) this.rootView.findViewById(R.id.textView_tag_id6);
        TextView textView28 = (TextView) this.rootView.findViewById(R.id.textView_tag_id7);
        TextView textView29 = (TextView) this.rootView.findViewById(R.id.textView_tag_id8);
        TextView textView30 = (TextView) this.rootView.findViewById(R.id.textView_tag_id9);
        TextView textView31 = (TextView) this.rootView.findViewById(R.id.textView_tag_id10);
        TextView textView32 = (TextView) this.rootView.findViewById(R.id.textView_tag_id11);
        TextView textView33 = (TextView) this.rootView.findViewById(R.id.textView_tag_id12);
        TextView textView34 = (TextView) this.rootView.findViewById(R.id.textView_tag_id13);
        TextView textView35 = (TextView) this.rootView.findViewById(R.id.textView_tag_id14);
        TextView textView36 = (TextView) this.rootView.findViewById(R.id.textView_tag_id15);
        TextView textView37 = (TextView) this.rootView.findViewById(R.id.textView_tag_id16);
        TextView textView38 = (TextView) this.rootView.findViewById(R.id.textView_tag_id17);
        TextView textView39 = (TextView) this.rootView.findViewById(R.id.textView_tag_id18);
        TextView textView40 = (TextView) this.rootView.findViewById(R.id.textView_tag_id19);
        final EditText[] editTextArr = {(EditText) this.rootView.findViewById(R.id.editText_tag_name), (EditText) this.rootView.findViewById(R.id.editText_tag_name1), (EditText) this.rootView.findViewById(R.id.editText_tag_name2), (EditText) this.rootView.findViewById(R.id.editText_tag_name3), (EditText) this.rootView.findViewById(R.id.editText_tag_name4), (EditText) this.rootView.findViewById(R.id.editText_tag_name5), (EditText) this.rootView.findViewById(R.id.editText_tag_name6), (EditText) this.rootView.findViewById(R.id.editText_tag_name7), (EditText) this.rootView.findViewById(R.id.editText_tag_name8), (EditText) this.rootView.findViewById(R.id.editText_tag_name9), (EditText) this.rootView.findViewById(R.id.editText_tag_name10), (EditText) this.rootView.findViewById(R.id.editText_tag_name11), (EditText) this.rootView.findViewById(R.id.editText_tag_name12), (EditText) this.rootView.findViewById(R.id.editText_tag_name13), (EditText) this.rootView.findViewById(R.id.editText_tag_name14), (EditText) this.rootView.findViewById(R.id.editText_tag_name15), (EditText) this.rootView.findViewById(R.id.editText_tag_name16), (EditText) this.rootView.findViewById(R.id.editText_tag_name17), (EditText) this.rootView.findViewById(R.id.editText_tag_name18), (EditText) this.rootView.findViewById(R.id.editText_tag_name19)};
        final EditText[] editTextArr2 = {(EditText) this.rootView.findViewById(R.id.editText_tag_id), (EditText) this.rootView.findViewById(R.id.editText_tag_id1), (EditText) this.rootView.findViewById(R.id.editText_tag_id2), (EditText) this.rootView.findViewById(R.id.editText_tag_id3), (EditText) this.rootView.findViewById(R.id.editText_tag_id4), (EditText) this.rootView.findViewById(R.id.editText_tag_id5), (EditText) this.rootView.findViewById(R.id.editText_tag_id6), (EditText) this.rootView.findViewById(R.id.editText_tag_id7), (EditText) this.rootView.findViewById(R.id.editText_tag_id8), (EditText) this.rootView.findViewById(R.id.editText_tag_id9), (EditText) this.rootView.findViewById(R.id.editText_tag_id10), (EditText) this.rootView.findViewById(R.id.editText_tag_id11), (EditText) this.rootView.findViewById(R.id.editText_tag_id12), (EditText) this.rootView.findViewById(R.id.editText_tag_id13), (EditText) this.rootView.findViewById(R.id.editText_tag_id14), (EditText) this.rootView.findViewById(R.id.editText_tag_id15), (EditText) this.rootView.findViewById(R.id.editText_tag_id16), (EditText) this.rootView.findViewById(R.id.editText_tag_id17), (EditText) this.rootView.findViewById(R.id.editText_tag_id18), (EditText) this.rootView.findViewById(R.id.editText_tag_id19)};
        final LinearLayout[] linearLayoutArr = {(LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag0), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag1), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag2), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag3), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag4), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag5), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag6), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag7), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag8), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag9), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag10), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag11), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag12), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag13), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag14), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag15), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag16), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag17), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag18), (LinearLayout) this.rootView.findViewById(R.id.LinearLayout_tag19)};
        button3.setVisibility(0);
        for (int i7 = 1; i7 <= 19; i7++) {
            textViewArr[i7].setVisibility(8);
            linearLayoutArr[i7].setVisibility(8);
        }
        String string = getString(R.string.tag);
        int i8 = 0;
        while (i8 < 20) {
            int i9 = i8 + 1;
            textViewArr[i8].setText(string + " " + i9);
            i8 = i9;
        }
        long j = 20 - MainActivity.FirebaseTotalTag;
        this.total_plusTag = j;
        if (j < 2) {
            button3.setAlpha(0.3f);
            button3.setEnabled(false);
        }
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_multiple_tags.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Helper_Network.haveNetworkConnection(MainActivity.context)) {
                    if (Fragment_add_multiple_tags.this.count_plusTag < Fragment_add_multiple_tags.this.total_plusTag) {
                        button4.setEnabled(true);
                        button4.setAlpha(1.0f);
                        textViewArr[Fragment_add_multiple_tags.this.count_plusTag].setVisibility(0);
                        linearLayoutArr[Fragment_add_multiple_tags.this.count_plusTag].setVisibility(0);
                        Fragment_add_multiple_tags.this.count_plusTag++;
                        if (Fragment_add_multiple_tags.this.count_plusTag == Fragment_add_multiple_tags.this.total_plusTag) {
                            button3.setAlpha(0.3f);
                            button3.setEnabled(false);
                            return;
                        }
                        return;
                    }
                    button3.setAlpha(0.3f);
                    button3.setEnabled(false);
                    return;
                }
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.context, 0);
                sweetAlertDialog.setTitleText(Fragment_add_multiple_tags.this.getString(R.string.pls_note));
                sweetAlertDialog.setContentText(Fragment_add_multiple_tags.this.getString(R.string.you_are_not_connected_access_lock));
                sweetAlertDialog.show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_multiple_tags.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.i("Multiple_Tag", "Hello remove tag:" + Fragment_add_multiple_tags.this.count_plusTag);
                if (Fragment_add_multiple_tags.this.count_plusTag > 1) {
                    button3.setAlpha(1.0f);
                    button3.setEnabled(true);
                    textViewArr[Fragment_add_multiple_tags.this.count_plusTag - 1].setVisibility(8);
                    linearLayoutArr[Fragment_add_multiple_tags.this.count_plusTag - 1].setVisibility(8);
                    Fragment_add_multiple_tags.this.count_plusTag--;
                    if (Fragment_add_multiple_tags.this.count_plusTag == 1) {
                        button4.setAlpha(0.3f);
                        button4.setEnabled(false);
                    }
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_multiple_tags.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Fragment_add_tag.refresh_listview_tag = true;
                Fragment_add_multiple_tags.this.getActivity().getFragmentManager().popBackStack();
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_multiple_tags.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                char[] cArr;
                Fragment_add_tag.refresh_listview_tag = true;
                if (Helper_Network.haveNetworkConnection(MainActivity.context)) {
                    MainActivity.currentTimestampDouble = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() * 1000);
                    Fragment_add_multiple_tags.new_tag_name.clear();
                    Fragment_add_multiple_tags.new_tag_id.clear();
                    BLEService.list_Add_tag_id_IP66.clear();
                    BLEService.list_Add_tag_name_IP66.clear();
                    Fragment_add_multiple_tags.this.add_tag_error = false;
                    BLEService.rewrite_page_boolean = false;
                    BLEService.totalNewTagsAdded = Fragment_add_multiple_tags.this.count_plusTag;
                    for (int i10 = 0; i10 < Fragment_add_multiple_tags.this.count_plusTag; i10++) {
                        if (editTextArr[i10].getText().toString().equals("")) {
                            editTextArr[i10].setError(Fragment_add_multiple_tags.this.getString(R.string.please_enter_tag_name));
                            Fragment_add_multiple_tags.this.add_tag_error = true;
                        } else if (editTextArr2[i10].getText().toString().equals("") || editTextArr2[i10].getText().toString().length() != 4) {
                            editTextArr2[i10].setError(Fragment_add_multiple_tags.this.getString(R.string.please_enter_tag_id));
                            Fragment_add_multiple_tags.this.add_tag_error = true;
                        } else if (!editTextArr[i10].getText().toString().equals("") && !editTextArr2[i10].getText().toString().equals("")) {
                            String obj = editTextArr[i10].getText().toString();
                            String upperCase = editTextArr2[i10].getText().toString().toUpperCase();
                            Log.i("Multiple_Tag", "Hello print tag ID:" + upperCase + " length:" + editTextArr2[i10].getText().toString().length());
                            upperCase.getChars(0, 4, new char[4], 0);
                            String str = " " + cArr[0] + cArr[1] + " " + cArr[2] + cArr[3] + " ";
                            if (upperCase.length() != 4) {
                                editTextArr2[i10].setError(Fragment_add_multiple_tags.this.getString(R.string.please_enter_correct_tag_id));
                                Fragment_add_multiple_tags.list_tag_error.add(editTextArr[i10].getText().toString());
                                Toast.makeText(MainActivity.context, "Invalid Tag ID of " + Fragment_add_multiple_tags.list_tag_error, 1).show();
                            } else if (arrayList.contains(obj) || Fragment_add_multiple_tags.new_tag_name.contains(obj)) {
                                Fragment_add_multiple_tags.this.add_tag_error = true;
                                if (Fragment_add_multiple_tags.new_tag_name.contains(obj)) {
                                    editTextArr[i10].setError("Can not add with same name");
                                } else {
                                    editTextArr[i10].setError("Name already Exist");
                                }
                                if (arrayList2.contains(str)) {
                                    editTextArr2[i10].setError("ID already Exist");
                                } else if (Fragment_add_multiple_tags.new_tag_id.contains(str)) {
                                    editTextArr2[i10].setError("Can not same Tag ID");
                                }
                            } else if (!arrayList2.contains(str) && !Fragment_add_multiple_tags.new_tag_id.contains(str)) {
                                if (Fragment_add_multiple_tags.this.checkTagValid(upperCase)) {
                                    Fragment_add_multiple_tags.new_tag_id.add(str);
                                    Fragment_add_multiple_tags.new_tag_name.add(obj);
                                    MainActivity.Tag_deletion = false;
                                    BLEService.page_count = 0;
                                    BLEService.tag_list_count = 0;
                                    BLEService.tag_batch_number = 0;
                                    BLEService.name_count = 0;
                                    BLEService.new_tag_name_count = 0;
                                } else {
                                    Fragment_add_multiple_tags.this.add_tag_error = true;
                                    editTextArr2[i10].setError(Fragment_add_multiple_tags.this.getString(R.string.please_enter_correct_tag_id));
                                }
                            } else {
                                Fragment_add_multiple_tags.this.add_tag_error = true;
                                if (Fragment_add_multiple_tags.new_tag_id.contains(str)) {
                                    editTextArr2[i10].setError("Can not same Tag ID");
                                } else {
                                    editTextArr2[i10].setError("ID already Exist");
                                }
                            }
                        }
                    }
                    if (!Fragment_add_multiple_tags.this.add_tag_error) {
                        if (BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                            BLEService.list_Add_tag_name_IP66 = Fragment_add_multiple_tags.new_tag_name;
                            BLEService.list_Add_tag_id_IP66 = Fragment_add_multiple_tags.new_tag_id;
                            UI_BLE.pls_wait_content = Fragment_add_multiple_tags.this.getString(R.string.updating_data_to_lock);
                            UI_BLE.BLE_UI = 22;
                            Fragment_add_multiple_tags.this.uiBle.update();
                            BLEService.add_tag_count_IP66 = 0;
                            BLEService.Ble_Mode = 500;
                            MainActivity.fragmentManager.popBackStack();
                            return;
                        }
                        Fragment_add_multiple_tags.this.Add_tag();
                        return;
                    }
                    Toasty.custom(MainActivity.context, (CharSequence) Fragment_add_multiple_tags.this.rootView.getResources().getString(R.string.please_check_the_errors), Fragment_add_multiple_tags.this.getResources().getDrawable(R.drawable.ic_warning_black_24dp), Fragment_add_multiple_tags.this.getResources().getColor(R.color.red_btn_bg_pressed_color), Fragment_add_multiple_tags.this.getResources().getColor(R.color.white), 0, true, true).show();
                    return;
                }
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.context, 0);
                sweetAlertDialog.setTitleText(Fragment_add_multiple_tags.this.getString(R.string.pls_note));
                sweetAlertDialog.setContentText(Fragment_add_multiple_tags.this.getString(R.string.you_are_not_connected_access_lock));
                sweetAlertDialog.show();
            }
        });
        return this.rootView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkTagValid(String str) {
        ArrayList arrayList = new ArrayList(Arrays.asList(AppEventsConstants.EVENT_PARAM_VALUE_YES, ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9", AppEventsConstants.EVENT_PARAM_VALUE_NO, "a", "b", "c", "d", "e", "f"));
        boolean z = true;
        for (int i = 0; i < str.length(); i++) {
            if (!arrayList.contains(String.valueOf(str.charAt(i)).toLowerCase())) {
                z = false;
            }
        }
        return z;
    }

    public void Add_tag() {
        UI_BLE.pls_wait_content = getString(R.string.updating_data_to_lock);
        UI_BLE.BLE_UI = 22;
        this.uiBle.update();
        Log.i("Multiple_Tag", "Hello tag_array empty pages:" + MainActivity.list_empty_pages + "Size:" + MainActivity.list_empty_page_size);
        for (int i = 0; i < new_tag_id.size(); i++) {
            if (MainActivity.tag_id_graph.get(MainActivity.list_empty_pages.get(this.empty_page_count).intValue()).size() == 15) {
                this.empty_page_count++;
            }
            MainActivity.tag_id_graph.get(MainActivity.list_empty_pages.get(this.empty_page_count).intValue()).add(new_tag_id.get(i));
            MainActivity.last_page_number = MainActivity.list_empty_pages.get(this.empty_page_count).intValue();
        }
        BLEService.Ble_Mode = BLEService.Add_tag_version2;
        getActivity().getFragmentManager().popBackStack();
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        getActivity().setTitle(this.rootView.getResources().getString(R.string.add_tag));
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_multiple_tags.6
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == 4) {
                    Fragment_add_multiple_tags.this.getActivity().getFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });
    }
}
