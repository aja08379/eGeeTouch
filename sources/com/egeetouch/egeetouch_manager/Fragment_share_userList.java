package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class Fragment_share_userList extends Fragment {
    ArrayAdapter_addshareUser adapter;
    ListView listview;
    private Menu menu;
    private Spinner spinner_lock;
    private Spinner spinner_mode;
    private Spinner spinner_user;
    View rootView = null;
    int clickFlag = 0;
    List<String> list_user = new ArrayList();
    List<String> list_email = new ArrayList();
    List<Boolean> list_exists = new ArrayList();

    public static Fragment_share_userList newInstance() {
        return new Fragment_share_userList();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.share_user_list);
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
        this.rootView = layoutInflater.inflate(R.layout.fragment_share_userlist, viewGroup, false);
        setHasOptionsMenu(true);
        getActivity().setTitle(this.rootView.getResources().getString(R.string.share_user_list));
        MainActivity.fab.setVisibility(4);
        MainActivity.fab_share.setVisibility(4);
        MainActivity.fab_admin_access_locks.setVisibility(8);
        if (MainActivity.dashboard_listview != null) {
            MainActivity.dashboard_listview.setVisibility(4);
        }
        MainActivity.pullToRefresh.setEnabled(false);
        this.listview = (ListView) this.rootView.findViewById(R.id.listview_userlist);
        if (!MainActivity.firebase_user[0].equals(this.rootView.getResources().getString(R.string.nothing_user))) {
            for (int i = 0; i < MainActivity.firebase_user.length; i++) {
                this.list_user.add(MainActivity.firebase_user[i]);
            }
            for (int i2 = 0; i2 < MainActivity.firebase_user_email.length; i2++) {
                this.list_email.add(MainActivity.firebase_user_email[i2]);
            }
            for (int i3 = 0; i3 < this.list_email.size(); i3++) {
                this.list_exists.add(true);
            }
            ArrayAdapter_addshareUser arrayAdapter_addshareUser = new ArrayAdapter_addshareUser(getActivity(), this.list_user, this.list_email, this.list_exists);
            this.adapter = arrayAdapter_addshareUser;
            this.listview.setAdapter((ListAdapter) arrayAdapter_addshareUser);
            this.adapter.notifyDataSetChanged();
            this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_userList.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i4, long j) {
                    int i5 = Fragment_share_userList.this.clickFlag;
                }
            });
            this.listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_userList.2
                @Override // android.widget.AdapterView.OnItemLongClickListener
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i4, long j) {
                    Fragment_share_userList.this.clickFlag = 2;
                    new SweetAlertDialog(Fragment_share_userList.this.getActivity()).setTitleText(Fragment_share_userList.this.getActivity().getResources().getString(R.string.delete_user)).setContentText(Fragment_share_userList.this.getActivity().getResources().getString(R.string.are_you_sure_you_want_to_delete_jp) + " " + MainActivity.firebase_user[i4] + "?").setConfirmText(Fragment_share_userList.this.getActivity().getResources().getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_userList.2.2
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            FirebaseDatabase.getInstance().getReference("userFriendList").child(MainActivity.user_uid).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_userList.2.2.1
                                @Override // com.google.firebase.database.ValueEventListener
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                                        if (dataSnapshot2.getKey().equals(MainActivity.firebase_user[i4])) {
                                            Log.i("Tag", "Remove: " + dataSnapshot2.getKey());
                                            dataSnapshot2.getRef().removeValue();
                                        }
                                    }
                                }

                                @Override // com.google.firebase.database.ValueEventListener
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.e("TAG", "onCancelled", databaseError.toException());
                                }
                            });
                            Log.i("Tag", "Position: " + i4);
                            Fragment_share_userList.this.list_user.remove(i4);
                            Fragment_share_userList.this.list_email.remove(i4);
                            Fragment_share_userList.this.adapter.notifyDataSetChanged();
                        }
                    }).setCancelText(Fragment_share_userList.this.getActivity().getResources().getString(R.string.no)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_userList.2.1
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    }).show();
                    return false;
                }
            });
        }
        return this.rootView;
    }
}
