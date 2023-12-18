package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes.dex */
public class Fragment_add_tag extends Fragment {
    private static int clickFlag = 0;
    private static boolean disconnected_dialog_show = false;
    private static boolean disconnected_trigger = false;
    public static ListView listview = null;
    public static boolean msg_update_done = false;
    private static boolean reconnect_msg_show = false;
    public static boolean refresh_listview_tag = false;
    public static String tag_id_remove = "";
    ArrayAdapter_ManageTag adapter;
    private Menu menu;
    SweetAlertDialog reconnect_msg;
    View rootView;
    UI_BLE uiBle;
    public static List<String> list_UID = new ArrayList();
    public static List<String> list_tagUID_check = new ArrayList();
    public static boolean add_tag_clickable = false;
    public static List<String> list = new ArrayList();
    public static List<String> list_id = new ArrayList();
    public static List<String> list_origin = new ArrayList();
    public static List<String> list_page_number = new ArrayList();
    private Handler manage_tag_handler = new Handler();
    SweetAlertDialog pd_pls_wait_tag = null;
    int Tag_id_page = 0;
    public String tag_id = "";
    int local_max_index = 0;
    final Runnable r = new Runnable() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag.3
        @Override // java.lang.Runnable
        public void run() {
            if (Fragment_add_tag.refresh_listview_tag) {
                Fragment_add_tag.this.update_UI_listview();
                if (Fragment_add_tag.this.pd_pls_wait_tag != null && Fragment_add_tag.this.pd_pls_wait_tag.isShowing()) {
                    Fragment_add_tag.this.pd_pls_wait_tag.dismiss();
                }
                Fragment_add_tag.refresh_listview_tag = false;
                Fragment_add_tag.add_tag_clickable = true;
                if (Fragment_add_tag.msg_update_done) {
                    if (UI_BLE.pd_pls_wait_extract != null && UI_BLE.pd_pls_wait_extract.isShowing()) {
                        UI_BLE.pd_pls_wait_extract.dismiss();
                    }
                    if (!BLEService.tag_updateSuccess) {
                        BLEService.tag_updateSuccess = true;
                        new SweetAlertDialog(MainActivity.context, 3).setTitleText(MainActivity.context.getResources().getString(R.string.update_failed)).setContentText("").setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                        Fragment_add_tag.msg_update_done = false;
                    } else {
                        new SweetAlertDialog(MainActivity.context, 2).setTitleText(MainActivity.context.getResources().getString(R.string.update_successful)).setContentText("").setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                        Fragment_add_tag.msg_update_done = false;
                    }
                }
            }
            if (BLEService.mConnectionState == 0) {
                Fragment_add_tag.this.icon_display(false);
                if (Fragment_add_tag.reconnect_msg_show || Fragment_add_tag.disconnected_trigger) {
                    if (Fragment_add_tag.reconnect_msg_show && Fragment_add_tag.disconnected_trigger && Fragment_add_tag.this.reconnect_msg != null && Fragment_add_tag.this.reconnect_msg.isShowing()) {
                        Fragment_add_tag.this.reconnect_msg.dismissWithAnimation();
                    }
                } else {
                    boolean unused = Fragment_add_tag.reconnect_msg_show = true;
                    Fragment_add_tag.this.reconnect_msg = new SweetAlertDialog(MainActivity.context, 5);
                    Fragment_add_tag.this.reconnect_msg.setTitleText(MainActivity.context.getResources().getString(R.string.lost_connection));
                    Fragment_add_tag.this.reconnect_msg.setContentText(MainActivity.context.getResources().getString(R.string.move_nearer));
                    Fragment_add_tag.this.reconnect_msg.setCancelText(MainActivity.context.getResources().getString(R.string.cancel));
                    Fragment_add_tag.this.reconnect_msg.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag.3.1
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            BLEService.cancel_scaning_triggered = true;
                            BLEService.Ble_Mode = BLEService.disconnect;
                        }
                    });
                    Fragment_add_tag.this.reconnect_msg.setCancelable(false);
                    Fragment_add_tag.this.reconnect_msg.show();
                    Log.i("TAG", "reconnect_msg is shown");
                }
            } else {
                Fragment_add_tag.this.icon_display(true);
                if (Fragment_add_tag.reconnect_msg_show && Fragment_add_tag.this.reconnect_msg != null && Fragment_add_tag.this.reconnect_msg.isShowing()) {
                    Fragment_add_tag.this.reconnect_msg.dismiss();
                    boolean unused2 = Fragment_add_tag.reconnect_msg_show = false;
                }
            }
            Fragment_add_tag.this.manage_tag_handler.postDelayed(this, 300L);
        }
    };

    public static Fragment_add_tag newInstance() {
        return new Fragment_add_tag();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        disconnected_dialog_show = false;
        this.manage_tag_handler.postDelayed(this.r, 300L);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.manage_tags);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        this.manage_tag_handler.removeCallbacks(this.r);
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
            new SweetAlertDialog(getActivity()).setTitleText(getString(R.string.power_off)).setContentText(getString(R.string.are_you_sure_power_off_lock)).setConfirmText(getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag.1
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                    boolean unused = Fragment_add_tag.disconnected_trigger = true;
                    BLEService.cancel_scaning_triggered = true;
                    BLEService.Ble_Mode = BLEService.Send_timeStamp;
                    BLEService.disconnect_triggered = true;
                    MainActivity.isUserClickedShutdown = true;
                    MainActivity.shutdown_dialog = new SweetAlertDialog(MainActivity.context, 5);
                    MainActivity.shutdown_dialog.setTitleText(Fragment_add_tag.this.getString(R.string.power_off));
                    MainActivity.shutdown_dialog.setCancelable(false);
                    MainActivity.shutdown_dialog.setCanceledOnTouchOutside(false);
                    MainActivity.shutdown_dialog.show();
                    MainActivity.stopBackStack = false;
                    ((AppCompatActivity) Fragment_add_tag.this.getActivity()).getSupportActionBar().setTitle(R.string.dashboard);
                    MediaPlayer create = MediaPlayer.create(Fragment_add_tag.this.getActivity(), (int) R.raw.disconnectinapp);
                    create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag.1.1
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
        this.rootView = layoutInflater.inflate(R.layout.fragment_manage_tags, viewGroup, false);
        setHasOptionsMenu(true);
        disconnected_trigger = false;
        Handler handler = new Handler();
        this.manage_tag_handler = handler;
        handler.post(this.r);
        MainActivity.current_icon = 6;
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.manage_tags);
        System.out.println("Hello checking the fragment Manage tag!");
        if (BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2003") || BLEService.selected_lock_model.equals("GT2100") || BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
            ((ImageView) this.rootView.findViewById(R.id.imageView_proximity)).setVisibility(8);
        } else if (BLEService.selected_lock_model.equals("GT1000") || BLEService.selected_lock_model.equals("GT3100")) {
            ((ImageView) this.rootView.findViewById(R.id.imageView_proximity)).setVisibility(0);
        }
        this.uiBle = new UI_BLE(MainActivity.context);
        ImageView imageView = (ImageView) this.rootView.findViewById(R.id.imageView_synch_tag);
        try {
        } catch (Exception unused) {
            imageView.setVisibility(8);
        }
        if (!BLEService.selected_lock_model.equals("GT2500") && !BLEService.selected_lock_model.equals("GT2550") && (!BLEService.selected_lock_model.equals("GT2100") || (!BLEService.selected_lock_version.equals("1.80") && Float.valueOf(BLEService.selected_lock_version).floatValue() <= 1.8d))) {
            imageView.setVisibility(8);
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (Helper_Network.haveNetworkConnection(MainActivity.context)) {
                        UI_BLE.pls_wait_content = MainActivity.context.getResources().getString(R.string.checking_tag);
                        UI_BLE.BLE_UI = 22;
                        Fragment_add_tag.this.uiBle.update();
                        BLEService.Request_list_page = 0;
                        BLEService.Request_list_batch = 0;
                        BLEService.rewrite_page_boolean = false;
                        BLEService.sync_list.clear();
                        BLEService.sync_list_page.clear();
                        if (BLEService.selected_lock_model.equals("GT2100") && (BLEService.selected_lock_version.equals("1.80") || Float.valueOf(BLEService.selected_lock_version).floatValue() > 1.8d)) {
                            BLEService.Ble_Mode = 100;
                        } else if (BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                            BLEService.Ble_Mode = 503;
                        }
                        BLEService.check_page_graph.clear();
                        for (int i = 0; i < 15; i++) {
                            BLEService.check_page_graph.add(new ArrayList<>());
                        }
                        return;
                    }
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.context, 0);
                    sweetAlertDialog.setTitleText(Fragment_add_tag.this.getString(R.string.pls_note));
                    sweetAlertDialog.setContentText(Fragment_add_tag.this.getString(R.string.you_are_not_connected_access_lock));
                    sweetAlertDialog.show();
                }
            });
            update_UI_listview();
            if (BLEService.selected_lock_model.equals("GT2100") && Float.valueOf(BLEService.selected_lock_version).floatValue() >= 1.8d) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.context, 3);
                sweetAlertDialog.setTitleText(getString(R.string.warning));
                sweetAlertDialog.setContentText(getString(R.string.warning_sync));
                sweetAlertDialog.show();
            }
            return this.rootView;
        }
        imageView.setVisibility(0);
        SweetAlertDialog sweetAlertDialog2 = this.pd_pls_wait_tag;
        if (sweetAlertDialog2 != null && sweetAlertDialog2.isShowing()) {
            this.pd_pls_wait_tag.dismiss();
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Helper_Network.haveNetworkConnection(MainActivity.context)) {
                    UI_BLE.pls_wait_content = MainActivity.context.getResources().getString(R.string.checking_tag);
                    UI_BLE.BLE_UI = 22;
                    Fragment_add_tag.this.uiBle.update();
                    BLEService.Request_list_page = 0;
                    BLEService.Request_list_batch = 0;
                    BLEService.rewrite_page_boolean = false;
                    BLEService.sync_list.clear();
                    BLEService.sync_list_page.clear();
                    if (BLEService.selected_lock_model.equals("GT2100") && (BLEService.selected_lock_version.equals("1.80") || Float.valueOf(BLEService.selected_lock_version).floatValue() > 1.8d)) {
                        BLEService.Ble_Mode = 100;
                    } else if (BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                        BLEService.Ble_Mode = 503;
                    }
                    BLEService.check_page_graph.clear();
                    for (int i = 0; i < 15; i++) {
                        BLEService.check_page_graph.add(new ArrayList<>());
                    }
                    return;
                }
                SweetAlertDialog sweetAlertDialog3 = new SweetAlertDialog(MainActivity.context, 0);
                sweetAlertDialog3.setTitleText(Fragment_add_tag.this.getString(R.string.pls_note));
                sweetAlertDialog3.setContentText(Fragment_add_tag.this.getString(R.string.you_are_not_connected_access_lock));
                sweetAlertDialog3.show();
            }
        });
        update_UI_listview();
        if (BLEService.selected_lock_model.equals("GT2100")) {
            SweetAlertDialog sweetAlertDialog3 = new SweetAlertDialog(MainActivity.context, 3);
            sweetAlertDialog3.setTitleText(getString(R.string.warning));
            sweetAlertDialog3.setContentText(getString(R.string.warning_sync));
            sweetAlertDialog3.show();
        }
        return this.rootView;
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void update_UI_listview() {
        System.out.println("Hello checking the fragment Manage tag update_UI_listview()");
        list.clear();
        list_id.clear();
        list_origin.clear();
        list_page_number.clear();
        list_UID.clear();
        list_tagUID_check.clear();
        add_tag_clickable = false;
        Cursor rawQuery = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_Query_tag_name_in_lock(BLEService.selected_lock_name), null);
        while (rawQuery.moveToNext()) {
            list.add(rawQuery.getString(0));
            list_id.add("null");
            list_origin.add("cloud");
            list_page_number.add("null");
        }
        rawQuery.close();
        Cursor rawQuery2 = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_Query_tag_Id_in_lock(BLEService.selected_lock_name), null);
        while (rawQuery2.moveToNext()) {
            list_tagUID_check.add(rawQuery2.getString(0));
        }
        rawQuery2.close();
        for (int i = 0; i < list.size(); i++) {
            Cursor rawQuery3 = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_tag_UID_retriving(list.get(i)), null);
            while (rawQuery3.moveToNext()) {
                System.out.println("Hello tag UID:" + rawQuery3.getString(1) + " list:" + list.get(i));
                list_UID.add(rawQuery3.getString(1));
            }
            rawQuery3.close();
        }
        this.local_max_index = list.size() - 1;
        if (BLEService.selected_lock_model.equals("GT2100") && (BLEService.selected_lock_version.equals("1.80") || Float.valueOf(BLEService.selected_lock_version).floatValue() > 1.8d)) {
            FirebaseDatabase.getInstance(FirebaseApp.getInstance("industrial")).getReference("tagDirectory").child(BLEService.parsedIp45SerialNumber).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag.4
                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        HashMap hashMap = (HashMap) dataSnapshot2.getValue();
                        Fragment_add_tag.list.add(hashMap.containsKey("tagName") ? hashMap.get("tagName").toString() : " N.A ");
                        Fragment_add_tag.list_id.add(hashMap.containsKey("tagId") ? hashMap.get("tagId").toString() : " N.A ");
                        Fragment_add_tag.list_origin.add("industrial");
                        Fragment_add_tag.list_page_number.add(hashMap.containsKey("PageNumber") ? hashMap.get("PageNumber").toString() : AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    }
                    Fragment_add_tag.this.update_UI_listview2(Fragment_add_tag.list, Fragment_add_tag.list_id, Fragment_add_tag.list_origin, Fragment_add_tag.list_page_number);
                }
            });
        } else {
            update_UI_listview2(list, list_id, list_origin, list_page_number);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update_UI_listview2(List<String> list2, List<String> list3, List<String> list4, List<String> list5) {
        listview = (ListView) this.rootView.findViewById(R.id.listview_taglist);
        ArrayAdapter_ManageTag arrayAdapter_ManageTag = new ArrayAdapter_ManageTag(getActivity(), list2, list3, list4);
        this.adapter = arrayAdapter_ManageTag;
        listview.setAdapter((ListAdapter) arrayAdapter_ManageTag);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (Fragment_add_tag.clickFlag != 2) {
                    int unused = Fragment_add_tag.clickFlag = 0;
                }
            }
        });
        listview.setOnItemLongClickListener(new AnonymousClass6(list2, list3, list5, list4));
        if (list2.size() > 0) {
            delete0000();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.egeetouch.egeetouch_manager.Fragment_add_tag$6  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass6 implements AdapterView.OnItemLongClickListener {
        final /* synthetic */ List val$list;
        final /* synthetic */ List val$list_id;
        final /* synthetic */ List val$list_origin;
        final /* synthetic */ List val$list_page_number;

        AnonymousClass6(List list, List list2, List list3, List list4) {
            this.val$list = list;
            this.val$list_id = list2;
            this.val$list_page_number = list3;
            this.val$list_origin = list4;
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long j) {
            int unused = Fragment_add_tag.clickFlag = 2;
            if (this.val$list.isEmpty()) {
                Cursor rawQuery = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_Query_tag_name_in_lock(BLEService.selected_lock_name), null);
                while (rawQuery.moveToNext()) {
                    this.val$list.add(rawQuery.getString(0));
                }
                rawQuery.close();
            }
            final String str = (String) this.val$list.get(i);
            if (i > Fragment_add_tag.this.local_max_index) {
                Fragment_add_tag.tag_id_remove = (String) this.val$list_id.get(i);
            } else {
                Fragment_add_tag.tag_id_remove = Fragment_add_tag.list_tagUID_check.get(i);
            }
            if (BLEService.selected_lock_model.equals("GT2100") && ((BLEService.selected_lock_version.equals("1.80") || Float.valueOf(BLEService.selected_lock_version).floatValue() > 1.8d) && i <= Fragment_add_tag.this.local_max_index)) {
                String str2 = Fragment_add_tag.list_tagUID_check.get(i);
                Fragment_add_tag.tag_id_remove = Fragment_add_tag.list_tagUID_check.get(i);
                System.out.println("Hello list_UID_check2:" + Fragment_add_tag.list_tagUID_check);
                System.out.println("Hello delete page number:" + MainActivity.tag_page_number.get(i));
                System.out.println("Hello selected Tag:" + str2 + " Position:" + i + " Tag Name:" + str);
                System.out.println("Hello selected Tag:list" + this.val$list);
                System.out.println("Hello selected Tag: listid size:" + Fragment_add_tag.list_tagUID_check.size() + "listID" + Fragment_add_tag.list_tagUID_check);
                int i2 = 0;
                while (true) {
                    if (i2 < MainActivity.tag_page_number.size()) {
                        if (MainActivity.tag_id_graph.get(Integer.valueOf(MainActivity.tag_page_number.get(i2)).intValue()).size() != 0 && MainActivity.tag_id_graph.get(Integer.valueOf(MainActivity.tag_page_number.get(i2)).intValue()).contains(str2)) {
                            System.out.println("Hello check deletion i:" + i2);
                            Fragment_add_tag.this.Tag_id_page = Integer.valueOf(MainActivity.tag_page_number.get(i)).intValue();
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
            }
            new SweetAlertDialog(MainActivity.context).setTitleText(MainActivity.context.getResources().getString(R.string.delete_tag)).setContentText(MainActivity.context.getResources().getString(R.string.are_you_sure_you_want_to_delete_jp) + "?").setConfirmText(MainActivity.context.getResources().getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag.6.1
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                    if (BLEService.selected_lock_model.equals("GT2100") && (BLEService.selected_lock_version.equals("1.80") || Float.valueOf(BLEService.selected_lock_version).floatValue() > 1.8d)) {
                        if (Helper_Network.haveNetworkConnection(MainActivity.context)) {
                            if (i > Fragment_add_tag.this.local_max_index) {
                                MainActivity.Tag_deletion = true;
                                if (Fragment_add_tag.list_tagUID_check.contains(Fragment_add_tag.tag_id_remove)) {
                                    int indexOf = Fragment_add_tag.list_tagUID_check.indexOf(Fragment_add_tag.tag_id_remove);
                                    Fragment_add_tag.list_tagUID_check.remove(indexOf);
                                    AnonymousClass6.this.val$list.remove(indexOf);
                                    DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value((String) AnonymousClass6.this.val$list.get(indexOf), BLEService.selected_lock_name));
                                    MainActivity.tag_id_graph.get(Fragment_add_tag.this.Tag_id_page).remove(Fragment_add_tag.tag_id_remove);
                                }
                                MainActivity.list_empty_pages.clear();
                                MainActivity.list_empty_pages.add(Integer.valueOf((String) AnonymousClass6.this.val$list_page_number.get(i)));
                                MainActivity.last_page_number = Integer.valueOf((String) AnonymousClass6.this.val$list_page_number.get(i)).intValue();
                                BLEService.page_count = 0;
                                BLEService.tag_list_count = 0;
                                BLEService.tag_batch_number = 0;
                                BLEService.Ble_Mode = BLEService.Add_tag_version2;
                                UI_BLE.pls_wait_content = MainActivity.context.getResources().getString(R.string.updating_data_to_lock);
                                UI_BLE.BLE_UI = 22;
                                Fragment_add_tag.this.uiBle.update();
                                return;
                            }
                            DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value(str, BLEService.selected_lock_name));
                            MainActivity.Tag_deletion = true;
                            AnonymousClass6.this.val$list.remove(str);
                            Fragment_add_tag.list_tagUID_check.remove(Fragment_add_tag.tag_id_remove);
                            MainActivity.list_empty_pages.clear();
                            MainActivity.list_empty_pages.add(Integer.valueOf(MainActivity.tag_page_number.get(i)));
                            MainActivity.last_page_number = Integer.valueOf(MainActivity.tag_page_number.get(i)).intValue();
                            MainActivity.tag_id_graph.get(Fragment_add_tag.this.Tag_id_page).remove(Fragment_add_tag.tag_id_remove);
                            System.out.println("Hello Deletion tag_id:" + Fragment_add_tag.tag_id_remove + "list:" + MainActivity.tag_id_graph.get(Fragment_add_tag.this.Tag_id_page));
                            BLEService.page_count = 0;
                            BLEService.tag_list_count = 0;
                            BLEService.tag_batch_number = 0;
                            BLEService.Ble_Mode = BLEService.Add_tag_version2;
                            System.out.println("Hello graph after deletion:" + MainActivity.tag_id_graph.get(Fragment_add_tag.this.Tag_id_page));
                            UI_BLE.pls_wait_content = MainActivity.context.getResources().getString(R.string.updating_data_to_lock);
                            UI_BLE.BLE_UI = 22;
                            Fragment_add_tag.this.uiBle.update();
                            return;
                        }
                        SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(MainActivity.context, 1);
                        sweetAlertDialog2.setTitleText(Fragment_add_tag.this.getString(R.string.pls_note));
                        sweetAlertDialog2.setContentText(Fragment_add_tag.this.getString(R.string.you_are_not_connected_access_lock));
                        sweetAlertDialog2.show();
                    } else if (BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                        if (Helper_Network.haveNetworkConnection(MainActivity.context)) {
                            DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value(str, BLEService.selected_lock_name));
                            AnonymousClass6.this.val$list.remove(str);
                            Fragment_add_tag.list_tagUID_check.remove(Fragment_add_tag.tag_id_remove);
                            BLEService.deleteTagID_IP66 = Fragment_add_tag.tag_id_remove;
                            BLEService.Ble_Mode = 501;
                            UI_BLE.pls_wait_content = Fragment_add_tag.this.getResources().getString(R.string.updating_data_to_lock);
                            UI_BLE.BLE_UI = 22;
                            Fragment_add_tag.this.uiBle.update();
                            return;
                        }
                        SweetAlertDialog sweetAlertDialog3 = new SweetAlertDialog(MainActivity.context, 1);
                        sweetAlertDialog3.setTitleText(Fragment_add_tag.this.getString(R.string.pls_note));
                        sweetAlertDialog3.setContentText(Fragment_add_tag.this.getString(R.string.you_are_not_connected_access_lock));
                        sweetAlertDialog3.show();
                    } else {
                        DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value(str, BLEService.selected_lock_name));
                        AnonymousClass6.this.val$list.remove((String) adapterView.getItemAtPosition(i));
                        ArrayAdapter_ManageTag arrayAdapter_ManageTag = new ArrayAdapter_ManageTag(Fragment_add_tag.this.getActivity(), AnonymousClass6.this.val$list, AnonymousClass6.this.val$list_id, AnonymousClass6.this.val$list_origin);
                        Fragment_add_tag.listview.setAdapter((ListAdapter) arrayAdapter_ManageTag);
                        arrayAdapter_ManageTag.notifyDataSetChanged();
                        int unused2 = Fragment_add_tag.clickFlag = 0;
                        BLEService.update_progress = 0.0f;
                        BLEService.retrieved_Tag_number = 0;
                        BLEService.retrieve_tag_current_number = 1;
                        BLEService.retrieved_tag_done = false;
                        BLEService.Ble_Mode = 80;
                        Fragment_add_tag.this.pd_pls_wait_tag = new SweetAlertDialog(MainActivity.context, 5);
                        Fragment_add_tag.this.pd_pls_wait_tag.getProgressHelper().setBarColor(Color.parseColor("#389ae0"));
                        Fragment_add_tag.this.pd_pls_wait_tag.setTitleText(MainActivity.context.getResources().getString(R.string.manage_tags));
                        Fragment_add_tag.this.pd_pls_wait_tag.setContentText(MainActivity.context.getResources().getString(R.string.updating_data_to_lock));
                        Fragment_add_tag.this.pd_pls_wait_tag.setCancelable(false);
                        Fragment_add_tag.this.pd_pls_wait_tag.setCancelText(MainActivity.context.getResources().getString(R.string.cancel));
                        Fragment_add_tag.this.pd_pls_wait_tag.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag.6.1.1
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog4) {
                                sweetAlertDialog4.dismissWithAnimation();
                                BLEService.Ble_Mode = 0;
                                MainActivity.current_icon = 0;
                                Fragment_add_tag.this.pd_pls_wait_tag.show();
                            }
                        });
                        Fragment_add_tag.this.pd_pls_wait_tag.show();
                        Fragment_add_tag.msg_update_done = true;
                    }
                }
            }).setCancelText(MainActivity.context.getResources().getString(R.string.no)).show();
            return false;
        }
    }

    private void delete0000() {
        ((MainActivity) MainActivity.context).runOnUiThread(new Runnable() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag.7
            @Override // java.lang.Runnable
            public void run() {
                Cursor rawQuery = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_number_lock_exist(BLEService.selected_lock_name), null);
                while (rawQuery.moveToNext()) {
                    System.out.println("CMDCMD got " + rawQuery.getString(0));
                    if (rawQuery.getString(1).equals(" 00 00 ")) {
                        DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value(rawQuery.getString(0), BLEService.selected_lock_name));
                        Fragment_add_tag.list.remove(rawQuery.getString(0));
                        System.out.println("CMDCMD removed " + rawQuery.getString(0));
                        ArrayAdapter_ManageTag arrayAdapter_ManageTag = new ArrayAdapter_ManageTag(Fragment_add_tag.this.getActivity(), Fragment_add_tag.list, Fragment_add_tag.list_id, Fragment_add_tag.list_origin);
                        Fragment_add_tag.listview.setAdapter((ListAdapter) arrayAdapter_ManageTag);
                        arrayAdapter_ManageTag.notifyDataSetChanged();
                        int unused = Fragment_add_tag.clickFlag = 0;
                        BLEService.update_progress = 0.0f;
                        BLEService.retrieved_Tag_number = 0;
                        BLEService.retrieve_tag_current_number = 1;
                        BLEService.retrieved_tag_done = false;
                        BLEService.Ble_Mode = 80;
                        Fragment_add_tag.this.pd_pls_wait_tag = new SweetAlertDialog(MainActivity.context, 5);
                        Fragment_add_tag.this.pd_pls_wait_tag.getProgressHelper().setBarColor(Color.parseColor("#389ae0"));
                        Fragment_add_tag.this.pd_pls_wait_tag.setTitleText(MainActivity.context.getResources().getString(R.string.manage_tags));
                        Fragment_add_tag.this.pd_pls_wait_tag.setContentText(MainActivity.context.getResources().getString(R.string.updating_data_to_lock));
                        Fragment_add_tag.this.pd_pls_wait_tag.setCancelable(false);
                        Fragment_add_tag.this.pd_pls_wait_tag.setCancelText(MainActivity.context.getResources().getString(R.string.cancel));
                        Fragment_add_tag.this.pd_pls_wait_tag.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag.7.1
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                BLEService.Ble_Mode = 0;
                                MainActivity.current_icon = 0;
                                Fragment_add_tag.this.pd_pls_wait_tag.show();
                            }
                        });
                        Fragment_add_tag.this.pd_pls_wait_tag.show();
                        Fragment_add_tag.msg_update_done = true;
                        return;
                    }
                }
            }
        });
    }
}
