package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class Fragment_add_tag_nfc extends Fragment {
    public static ArrayAdapter_ManageTag adapter_tag;
    private static int clickFlag;
    public static ListView listview;
    View rootView;

    public static Fragment_add_tag_nfc newInstance() {
        return new Fragment_add_tag_nfc();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.add_tag);
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
        menuInflater.inflate(R.menu.nfc, menu);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        this.rootView = layoutInflater.inflate(R.layout.fragment_manage_tags_nfc, viewGroup, false);
        MainActivity.fab.setVisibility(4);
        MainActivity.fab_share.setVisibility(4);
        MainActivity.fab_admin_access_locks.setVisibility(8);
        if (MainActivity.dashboard_listview != null) {
            MainActivity.dashboard_listview.setVisibility(4);
        }
        Cursor rawQuery = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_Query_tag_name_in_lock(BLEService.selected_lock_name), null);
        while (rawQuery.moveToNext()) {
            arrayList.add(rawQuery.getString(0));
            arrayList2.add("null");
            arrayList3.add("cloud");
        }
        rawQuery.close();
        listview = (ListView) this.rootView.findViewById(R.id.listview_taglist);
        ArrayAdapter_ManageTag arrayAdapter_ManageTag = new ArrayAdapter_ManageTag(getActivity(), arrayList, arrayList2, arrayList3);
        adapter_tag = arrayAdapter_ManageTag;
        listview.setAdapter((ListAdapter) arrayAdapter_ManageTag);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag_nfc.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (Fragment_add_tag_nfc.clickFlag != 2) {
                    int unused = Fragment_add_tag_nfc.clickFlag = 0;
                }
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag_nfc.2
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long j) {
                int unused = Fragment_add_tag_nfc.clickFlag = 2;
                if (arrayList.isEmpty()) {
                    Cursor rawQuery2 = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_Query_tag_name_in_lock(BLEService.selected_lock_name), null);
                    while (rawQuery2.moveToNext()) {
                        arrayList.add(rawQuery2.getString(0));
                    }
                    rawQuery2.close();
                }
                final String str = (String) arrayList.get(i);
                new SweetAlertDialog(MainActivity.context).setTitleText(MainActivity.context.getResources().getString(R.string.delete_user)).setContentText(MainActivity.context.getResources().getString(R.string.are_you_sure_you_want_to_delete_jp) + "?").setConfirmText(MainActivity.context.getResources().getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_tag_nfc.2.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value(str, BLEService.selected_lock_name));
                        arrayList.remove((String) adapterView.getItemAtPosition(i));
                        ArrayAdapter_ManageTag arrayAdapter_ManageTag2 = new ArrayAdapter_ManageTag(Fragment_add_tag_nfc.this.getActivity(), arrayList, arrayList2, arrayList3);
                        Fragment_add_tag_nfc.listview.setAdapter((ListAdapter) arrayAdapter_ManageTag2);
                        arrayAdapter_ManageTag2.notifyDataSetChanged();
                        int unused2 = Fragment_add_tag_nfc.clickFlag = 0;
                    }
                }).setCancelText(MainActivity.context.getResources().getString(R.string.no)).show();
                return false;
            }
        });
        return this.rootView;
    }
}
