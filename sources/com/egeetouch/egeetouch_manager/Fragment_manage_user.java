package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class Fragment_manage_user extends Fragment {
    private static int clickFlag = 0;
    private static boolean disconnected_dialog_show = false;
    private static boolean disconnected_trigger = false;
    public static ListView listview = null;
    private static boolean msg_update_done = false;
    private static boolean reconnect_msg_show = false;
    public static boolean refresh_listview_user = false;
    ArrayAdapter_ManageUser adapter_user;
    private Menu menu;
    SweetAlertDialog reconnect_msg;
    View rootView;
    private Handler manage_user_handler = new Handler();
    SweetAlertDialog pd_pls_wait_user = null;
    final Runnable r = new Runnable() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_user.2
        @Override // java.lang.Runnable
        public void run() {
            if (Fragment_manage_user.refresh_listview_user) {
                Fragment_manage_user.refresh_listview_user = false;
                Fragment_manage_user.this.update_UI_listview();
                if (Fragment_manage_user.this.pd_pls_wait_user != null && Fragment_manage_user.this.pd_pls_wait_user.isShowing()) {
                    Fragment_manage_user.this.pd_pls_wait_user.dismiss();
                }
                if (Fragment_manage_user.msg_update_done) {
                    boolean unused = Fragment_manage_user.msg_update_done = false;
                    new SweetAlertDialog(MainActivity.context, 2).setTitleText(MainActivity.context.getResources().getString(R.string.update_successful)).setContentText("").setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                }
            }
            if (BLEService.mConnectionState == 0) {
                Fragment_manage_user.this.icon_display(false);
                if (Fragment_manage_user.reconnect_msg_show || Fragment_manage_user.disconnected_trigger) {
                    if (Fragment_manage_user.reconnect_msg_show && Fragment_manage_user.disconnected_trigger && Fragment_manage_user.this.reconnect_msg != null && Fragment_manage_user.this.reconnect_msg.isShowing()) {
                        Fragment_manage_user.this.reconnect_msg.dismissWithAnimation();
                    }
                } else {
                    boolean unused2 = Fragment_manage_user.reconnect_msg_show = true;
                    Fragment_manage_user.this.reconnect_msg = new SweetAlertDialog(MainActivity.context, 5);
                    Fragment_manage_user.this.reconnect_msg.setTitleText(MainActivity.context.getResources().getString(R.string.lost_connection));
                    Fragment_manage_user.this.reconnect_msg.setContentText(MainActivity.context.getResources().getString(R.string.move_nearer));
                    Fragment_manage_user.this.reconnect_msg.setCancelText(MainActivity.context.getResources().getString(R.string.cancel));
                    Fragment_manage_user.this.reconnect_msg.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_user.2.1
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            BLEService.cancel_scaning_triggered = true;
                            BLEService.Ble_Mode = BLEService.disconnect;
                        }
                    });
                    Fragment_manage_user.this.reconnect_msg.setCancelable(false);
                    Fragment_manage_user.this.reconnect_msg.show();
                    Log.i("TAG", "reconnect_msg is shown");
                }
            } else {
                Fragment_manage_user.this.icon_display(true);
                if (Fragment_manage_user.reconnect_msg_show && Fragment_manage_user.this.reconnect_msg != null && Fragment_manage_user.this.reconnect_msg.isShowing()) {
                    Fragment_manage_user.this.reconnect_msg.dismiss();
                    boolean unused3 = Fragment_manage_user.reconnect_msg_show = false;
                }
            }
            Fragment_manage_user.this.manage_user_handler.postDelayed(this, 300L);
        }
    };

    public static Fragment_manage_user newInstance() {
        return new Fragment_manage_user();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.manage_users);
        disconnected_dialog_show = false;
        this.manage_user_handler.postDelayed(this.r, 300L);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        this.manage_user_handler.removeCallbacks(this.r);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MenuItem findItem = menu.findItem(R.id.action_button_tutorial);
        if (findItem != null) {
            findItem.setVisible(false);
        }
        this.menu = menu;
        menuInflater.inflate(R.menu.global, menu);
    }

    @Override // android.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_button_disconnect) {
            new SweetAlertDialog(getActivity()).setTitleText(getString(R.string.power_off)).setContentText(getString(R.string.are_you_sure_power_off_lock)).setConfirmText(getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_user.1
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                    boolean unused = Fragment_manage_user.disconnected_trigger = true;
                    BLEService.cancel_scaning_triggered = true;
                    BLEService.Ble_Mode = 112;
                    BLEService.disconnect_triggered = true;
                    MainActivity.stopBackStack = false;
                    ((AppCompatActivity) Fragment_manage_user.this.getActivity()).getSupportActionBar().setTitle(R.string.dashboard);
                    MediaPlayer create = MediaPlayer.create(Fragment_manage_user.this.getActivity(), (int) R.raw.disconnectinapp);
                    create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_user.1.1
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
        this.rootView = layoutInflater.inflate(R.layout.fragment_manage_users, viewGroup, false);
        setHasOptionsMenu(true);
        disconnected_trigger = false;
        Handler handler = new Handler();
        this.manage_user_handler = handler;
        handler.post(this.r);
        MainActivity.current_icon = 6;
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.manage_users);
        if (BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2003") || BLEService.selected_lock_model.equals("GT2100")) {
            ((ImageView) this.rootView.findViewById(R.id.imageView_proximity)).setVisibility(8);
        } else {
            if (BLEService.selected_lock_model.equals("GT1000") || BLEService.selected_lock_model.equals("GT3100")) {
                ((ImageView) this.rootView.findViewById(R.id.imageView_proximity)).setVisibility(0);
            }
            return this.rootView;
        }
        return this.rootView;
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
        ArrayList arrayList = new ArrayList();
        Log.i("TAG", "update_UI_listview");
        Cursor rawQuery = DatabaseVariable.db_user.rawQuery(DatabaseVariable.userdb_Query_user_name_in_lock(BLEService.selected_lock_name), null);
        int i = 0;
        while (rawQuery.moveToNext()) {
            i++;
            arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(DatabaseVariable.D1_user)));
            Log.i("TAG", String.valueOf(i) + " " + rawQuery.getString(rawQuery.getColumnIndex(DatabaseVariable.D1_user)));
        }
        rawQuery.close();
        listview = (ListView) this.rootView.findViewById(R.id.listview_userlist);
        ArrayAdapter_ManageUser arrayAdapter_ManageUser = new ArrayAdapter_ManageUser(getActivity(), arrayList);
        this.adapter_user = arrayAdapter_ManageUser;
        listview.setAdapter((ListAdapter) arrayAdapter_ManageUser);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_user.3
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                if (Fragment_manage_user.clickFlag != 2) {
                    int unused = Fragment_manage_user.clickFlag = 0;
                }
            }
        });
        listview.setOnItemLongClickListener(new AnonymousClass4(arrayList));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.egeetouch.egeetouch_manager.Fragment_manage_user$4  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass4 implements AdapterView.OnItemLongClickListener {
        final /* synthetic */ List val$list;

        AnonymousClass4(List list) {
            this.val$list = list;
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long j) {
            int unused = Fragment_manage_user.clickFlag = 2;
            if (this.val$list.isEmpty()) {
                Cursor rawQuery = DatabaseVariable.db_user.rawQuery(DatabaseVariable.userdb_Query_user_name_in_lock(BLEService.selected_lock_name), null);
                while (rawQuery.moveToNext()) {
                    this.val$list.add(rawQuery.getString(rawQuery.getColumnIndex(DatabaseVariable.D1_user)));
                }
                rawQuery.close();
            }
            final String str = (String) this.val$list.get(i);
            new DialogInterface.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_user.4.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    if (i2 != -2) {
                        if (i2 != -1) {
                            return;
                        }
                        int unused2 = Fragment_manage_user.clickFlag = 0;
                        return;
                    }
                    DatabaseVariable.db_user.execSQL(DatabaseVariable.userdb_delete_value(str));
                    AnonymousClass4.this.val$list.remove((String) adapterView.getItemAtPosition(i));
                    ArrayAdapter_ManageUser arrayAdapter_ManageUser = new ArrayAdapter_ManageUser(Fragment_manage_user.this.getActivity(), AnonymousClass4.this.val$list);
                    Fragment_manage_user.listview.setAdapter((ListAdapter) arrayAdapter_ManageUser);
                    arrayAdapter_ManageUser.notifyDataSetChanged();
                    int unused3 = Fragment_manage_user.clickFlag = 0;
                }
            };
            new SweetAlertDialog(MainActivity.context).setTitleText(MainActivity.context.getResources().getString(R.string.delete_user)).setContentText(MainActivity.context.getResources().getString(R.string.are_you_sure_you_want_to_delete_jp) + "?").setConfirmText(MainActivity.context.getResources().getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_user.4.2
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                    DatabaseReference child = FirebaseDatabase.getInstance().getReference("Registered_user").child(MainActivity.user_uid).child(String.valueOf(MainActivity.child_index)).child("Share_List");
                    Log.i("tag", "Selected Position: " + String.valueOf(i));
                    child.child(String.valueOf(i + 1)).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_user.4.2.1
                        @Override // com.google.firebase.database.ValueEventListener
                        public void onCancelled(DatabaseError databaseError) {
                        }

                        @Override // com.google.firebase.database.ValueEventListener
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                                dataSnapshot2.getRef().removeValue();
                            }
                        }
                    });
                    DatabaseVariable.db_user.execSQL(DatabaseVariable.userdb_delete_value(str));
                    AnonymousClass4.this.val$list.remove((String) adapterView.getItemAtPosition(i));
                    ArrayAdapter_ManageUser arrayAdapter_ManageUser = new ArrayAdapter_ManageUser(Fragment_manage_user.this.getActivity(), AnonymousClass4.this.val$list);
                    Fragment_manage_user.listview.setAdapter((ListAdapter) arrayAdapter_ManageUser);
                    arrayAdapter_ManageUser.notifyDataSetChanged();
                    int unused2 = Fragment_manage_user.clickFlag = 0;
                    BLEService.add_user_done = false;
                    BLEService.Ble_Mode = 24;
                    BLEService.User_loop_number = 1;
                    Fragment_manage_user.this.pd_pls_wait_user = new SweetAlertDialog(MainActivity.context, 5);
                    Fragment_manage_user.this.pd_pls_wait_user.getProgressHelper().setBarColor(Color.parseColor("#389ae0"));
                    Fragment_manage_user.this.pd_pls_wait_user.setTitleText(MainActivity.context.getResources().getString(R.string.manage_users));
                    Fragment_manage_user.this.pd_pls_wait_user.setContentText(MainActivity.context.getResources().getString(R.string.updating_data_to_lock));
                    Fragment_manage_user.this.pd_pls_wait_user.setCancelable(false);
                    Fragment_manage_user.this.pd_pls_wait_user.setCancelText(MainActivity.context.getResources().getString(R.string.cancel));
                    Fragment_manage_user.this.pd_pls_wait_user.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_user.4.2.2
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog2) {
                            sweetAlertDialog2.dismissWithAnimation();
                            BLEService.Ble_Mode = 0;
                            MainActivity.current_icon = 0;
                            Fragment_manage_user.this.pd_pls_wait_user.show();
                        }
                    });
                    Fragment_manage_user.this.pd_pls_wait_user.show();
                    boolean unused3 = Fragment_manage_user.msg_update_done = true;
                }
            }).setCancelText(MainActivity.context.getResources().getString(R.string.no)).show();
            return false;
        }
    }
}
