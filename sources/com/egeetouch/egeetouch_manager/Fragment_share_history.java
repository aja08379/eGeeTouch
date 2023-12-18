package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class Fragment_share_history extends Fragment {
    ListView listview;
    private Menu menu;
    ArrayAdapter_shareHistory shareHistoryAdapter;
    private Spinner spinner_lock;
    private Spinner spinner_mode;
    private Spinner spinner_user;
    View rootView = null;
    int clickFlag = 0;
    List<String> bigTextList = new ArrayList();
    List<String> smallTextList = new ArrayList();
    List<String> sharedLockNameList = new ArrayList();
    List<String> sharedOnDateList = new ArrayList();
    List<String> shareToEmailList = new ArrayList();
    List<String> sharedStartTimeList = new ArrayList();
    List<String> sharedEndTimeList = new ArrayList();

    public static Fragment_share_history newInstance() {
        return new Fragment_share_history();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.share_history);
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
        this.rootView = layoutInflater.inflate(R.layout.fragment_share_history, viewGroup, false);
        setHasOptionsMenu(true);
        getActivity().setTitle(this.rootView.getResources().getString(R.string.share_user_list));
        MainActivity.fab.setVisibility(4);
        MainActivity.fab_share.setVisibility(4);
        MainActivity.fab_admin_access_locks.setVisibility(8);
        if (MainActivity.dashboard_listview != null) {
            MainActivity.dashboard_listview.setVisibility(4);
        }
        MainActivity.pullToRefresh.setEnabled(false);
        this.listview = (ListView) this.rootView.findViewById(R.id.listview_sharehistory);
        for (int size = MainActivity.shareHistoryLockName.size() - 1; size >= 0; size--) {
            this.sharedLockNameList.add(MainActivity.shareHistoryLockName.get(size));
        }
        for (int size2 = MainActivity.shareHistorySharedOnDate.size() - 1; size2 >= 0; size2--) {
            this.sharedOnDateList.add(MainActivity.shareHistorySharedOnDate.get(size2));
        }
        for (int size3 = MainActivity.shareHistorySharedTo.size() - 1; size3 >= 0; size3--) {
            this.shareToEmailList.add(MainActivity.shareHistorySharedTo.get(size3));
        }
        for (int size4 = MainActivity.shareHistoryStartDate.size() - 1; size4 >= 0; size4--) {
            this.sharedStartTimeList.add(MainActivity.shareHistoryStartDate.get(size4));
        }
        for (int size5 = MainActivity.shareHistoryEndDate.size() - 1; size5 >= 0; size5--) {
            this.sharedEndTimeList.add(MainActivity.shareHistoryEndDate.get(size5));
        }
        ArrayAdapter_shareHistory arrayAdapter_shareHistory = new ArrayAdapter_shareHistory(getActivity(), this.sharedLockNameList, this.sharedOnDateList, this.shareToEmailList, this.sharedStartTimeList, this.sharedEndTimeList);
        this.shareHistoryAdapter = arrayAdapter_shareHistory;
        this.listview.setAdapter((ListAdapter) arrayAdapter_shareHistory);
        this.shareHistoryAdapter.notifyDataSetChanged();
        return this.rootView;
    }
}
