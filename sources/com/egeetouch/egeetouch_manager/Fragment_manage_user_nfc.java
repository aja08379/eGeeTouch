package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class Fragment_manage_user_nfc extends Fragment {
    private static int clickFlag;
    public static ListView listview;
    ArrayAdapter_ManageUser adapter_user;
    SweetAlertDialog pd_pls_wait_user = null;
    View rootView;

    public static Fragment_manage_user_nfc newInstance() {
        return new Fragment_manage_user_nfc();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.manage_users);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
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
        menuInflater.inflate(R.menu.nfc, menu);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.fragment_manage_users_nfc, viewGroup, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.manage_users);
        update_UI_listview();
        return this.rootView;
    }

    private void update_UI_listview() {
        final ArrayList arrayList = new ArrayList();
        arrayList.add(getString(R.string.admin_you));
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
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_user_nfc.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                if (Fragment_manage_user_nfc.clickFlag != 2) {
                    int unused = Fragment_manage_user_nfc.clickFlag = 0;
                }
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_user_nfc.2
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i2, long j) {
                int unused = Fragment_manage_user_nfc.clickFlag = 2;
                if (arrayList.isEmpty()) {
                    Cursor rawQuery2 = DatabaseVariable.db_user.rawQuery(DatabaseVariable.userdb_Query_user_name_in_lock(BLEService.selected_lock_name), null);
                    while (rawQuery2.moveToNext()) {
                        arrayList.add(rawQuery2.getString(rawQuery2.getColumnIndex(DatabaseVariable.D1_user)));
                    }
                    rawQuery2.close();
                }
                final String str = (String) arrayList.get(i2);
                new DialogInterface.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_user_nfc.2.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i3) {
                        if (i3 != -2) {
                            if (i3 != -1) {
                                return;
                            }
                            int unused2 = Fragment_manage_user_nfc.clickFlag = 0;
                            return;
                        }
                        DatabaseVariable.db_user.execSQL(DatabaseVariable.userdb_delete_value(str));
                        arrayList.remove((String) adapterView.getItemAtPosition(i2));
                        ArrayAdapter_ManageUser arrayAdapter_ManageUser2 = new ArrayAdapter_ManageUser(Fragment_manage_user_nfc.this.getActivity(), arrayList);
                        Fragment_manage_user_nfc.listview.setAdapter((ListAdapter) arrayAdapter_ManageUser2);
                        arrayAdapter_ManageUser2.notifyDataSetChanged();
                        int unused3 = Fragment_manage_user_nfc.clickFlag = 0;
                    }
                };
                new SweetAlertDialog(MainActivity.context).setTitleText(MainActivity.context.getResources().getString(R.string.delete_user)).setContentText(MainActivity.context.getResources().getString(R.string.are_you_sure_you_want_to_delete_jp) + "?").setConfirmText(MainActivity.context.getResources().getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_user_nfc.2.2
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        DatabaseVariable.db_user.execSQL(DatabaseVariable.userdb_delete_value(str));
                        arrayList.remove((String) adapterView.getItemAtPosition(i2));
                        ArrayAdapter_ManageUser arrayAdapter_ManageUser2 = new ArrayAdapter_ManageUser(Fragment_manage_user_nfc.this.getActivity(), arrayList);
                        Fragment_manage_user_nfc.listview.setAdapter((ListAdapter) arrayAdapter_ManageUser2);
                        arrayAdapter_ManageUser2.notifyDataSetChanged();
                        int unused2 = Fragment_manage_user_nfc.clickFlag = 0;
                    }
                }).setCancelText(MainActivity.context.getResources().getString(R.string.no)).show();
                return false;
            }
        });
    }
}
