package com.egeetouch.egeetouch_manager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class Activity_share_history extends AppCompatActivity {
    ListView listview;
    ArrayAdapter_shareHistory shareHistoryAdapter;
    List<String> bigTextList = new ArrayList();
    List<String> smallTextList = new ArrayList();
    List<String> sharedLockNameList = new ArrayList();
    List<String> sharedOnDateList = new ArrayList();
    List<String> shareToEmailList = new ArrayList();
    List<String> sharedStartTimeList = new ArrayList();
    List<String> sharedEndTimeList = new ArrayList();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_share_history);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setTitle(getResources().getString(R.string.share_history));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.listview = (ListView) findViewById(R.id.listview_sharehistory);
        for (int size = Activity_share_lock.shareHistoryLockName.size() - 1; size >= 0; size--) {
            this.sharedLockNameList.add(Activity_share_lock.shareHistoryLockName.get(size));
        }
        for (int size2 = Activity_share_lock.shareHistorySharedOnDate.size() - 1; size2 >= 0; size2--) {
            this.sharedOnDateList.add(Activity_share_lock.shareHistorySharedOnDate.get(size2));
        }
        for (int size3 = Activity_share_lock.shareHistorySharedTo.size() - 1; size3 >= 0; size3--) {
            this.shareToEmailList.add(Activity_share_lock.shareHistorySharedTo.get(size3));
        }
        for (int size4 = Activity_share_lock.shareHistoryStartDate.size() - 1; size4 >= 0; size4--) {
            this.sharedStartTimeList.add(Activity_share_lock.shareHistoryStartDate.get(size4));
        }
        for (int size5 = Activity_share_lock.shareHistoryEndDate.size() - 1; size5 >= 0; size5--) {
            this.sharedEndTimeList.add(Activity_share_lock.shareHistoryEndDate.get(size5));
        }
        ArrayAdapter_shareHistory arrayAdapter_shareHistory = new ArrayAdapter_shareHistory(this, this.sharedLockNameList, this.sharedOnDateList, this.shareToEmailList, this.sharedStartTimeList, this.sharedEndTimeList);
        this.shareHistoryAdapter = arrayAdapter_shareHistory;
        this.listview.setAdapter((ListAdapter) arrayAdapter_shareHistory);
        this.shareHistoryAdapter.notifyDataSetChanged();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return true;
    }
}
