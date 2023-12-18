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
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import es.dmoral.toasty.Toasty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes.dex */
public class Fragment_Manage_Passcode extends Fragment {
    static ArrayAdapter_ManagePasscode adapter;
    static ListView listview;
    static View rootView;
    private static String serial;
    static UI_BLE ui_ble;
    private Menu menu;
    static List<String> directionalEmojiList = new ArrayList();
    static List<String> directionalCodeList = new ArrayList();
    static List<Long> timeList = new ArrayList();
    static List<String> nameList = new ArrayList();
    static List<Integer> indexList = new ArrayList();
    public static ArrayList<String> directionalEmoji = new ArrayList<>();
    public static ArrayList<String> directionalCode = new ArrayList<>();
    public static ArrayList<String> directionalName = new ArrayList<>();
    public static ArrayList<Integer> directionalIndex = new ArrayList<>();
    public static long number_of_Code = 0;
    private static int currentCodeCountNumber = 0;
    static LotoInfo lotoInfo = LotoInfo.getInstance();

    private void updateArrayAdapterView() {
    }

    public static Fragment_Manage_Passcode newInstance() {
        return new Fragment_Manage_Passcode();
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ui_ble = new UI_BLE(MainActivity.context);
        serial = BLEService.parsedIp45SerialNumber;
        ListView listView = (ListView) rootView.findViewById(R.id.listview_passcodelist);
        listview = listView;
        listView.setScrollingCacheEnabled(false);
        if (Helper_Network.haveNetworkConnection(MainActivity.context)) {
            InitializerFields();
        } else {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.context, 0);
            sweetAlertDialog.setTitleText(getString(R.string.pls_note));
            sweetAlertDialog.setContentText(getString(R.string.you_are_not_connected_access_lock));
            sweetAlertDialog.show();
            Toasty.normal(MainActivity.context, (int) R.string.you_are_not_connected_access_lock, getResources().getDrawable(R.drawable.ic_cloud_off_black_24dp)).show();
        }
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Manage_Passcode.1
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long j) {
                System.out.println("Hello checking the lock Passcode selected for deletion :" + i + " Passcode: " + Fragment_Manage_Passcode.directionalCodeList.get(i));
                new SweetAlertDialog(MainActivity.context, 0).setTitleText(Fragment_Manage_Passcode.this.getString(R.string.delete_passcode)).setContentText(Fragment_Manage_Passcode.this.getString(R.string.are_you_sure_you_want_to_delete)).setConfirmText(Fragment_Manage_Passcode.this.getString(R.string.yes)).setCancelText(Fragment_Manage_Passcode.this.getString(R.string.no)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Manage_Passcode.1.2
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog2) {
                        sweetAlertDialog2.dismiss();
                        UI_BLE.pls_wait_content = Fragment_Manage_Passcode.this.getString(R.string.updating_data_to_lock);
                        UI_BLE.BLE_UI = 22;
                        Fragment_Manage_Passcode.ui_ble.update();
                        Fragment_Manage_Passcode.lotoInfo.deleteIndex = Fragment_Manage_Passcode.indexList.get(i).intValue();
                        Fragment_Manage_Passcode.lotoInfo.deletePasscodeValues = Fragment_Manage_Passcode.directionalCodeList.get(i);
                        Fragment_Manage_Passcode.lotoInfo.setUpdateMode(Fragment_Manage_Passcode.lotoInfo.DELETE_PASSCODE);
                        Fragment_Manage_Passcode.lotoInfo.setDeleteAdd_mode(false);
                        BLEService.Ble_Mode = BLEService.Request_ChangePasscode;
                    }
                }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Manage_Passcode.1.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog2) {
                        sweetAlertDialog2.dismiss();
                    }
                }).show();
                return true;
            }
        });
    }

    public static void InitializerFields() {
        customProgressBar.ShowProgressBar(MainActivity.context, "pleaseWait");
        directionalEmojiList.clear();
        directionalCodeList.clear();
        timeList.clear();
        nameList.clear();
        indexList.clear();
        directionalEmoji.clear();
        directionalCode.clear();
        directionalIndex.clear();
        directionalName.clear();
        lotoInfo.clearBackupPasscodeList();
        try {
            FirebaseApp.initializeApp(MainActivity.context, new FirebaseOptions.Builder().setApplicationId("1:793477089265:android:2c6bbd1d22610f6144e650").setDatabaseUrl("https://egeetouchindustrial.firebaseio.com/").setApiKey("AIzaSyACfHrrc0ARWI_iyx9XRcFE-1Gk6AHtmc8").build(), "industrial");
        } catch (Exception e) {
            e.printStackTrace();
        }
        FirebaseDatabase.getInstance(FirebaseApp.getInstance("industrial")).getReference("directionalPassCodeDirectory").child(serial).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_Manage_Passcode.2
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        HashMap hashMap = (HashMap) dataSnapshot2.getValue();
                        String obj = hashMap.containsKey("directionalEmoji") ? hashMap.get("directionalEmoji").toString() : "";
                        String obj2 = hashMap.containsKey("directionalPasscode") ? hashMap.get("directionalPasscode").toString() : "";
                        String obj3 = hashMap.containsKey("name") ? hashMap.get("name").toString() : "N.A";
                        int intValue = hashMap.containsKey(FirebaseAnalytics.Param.INDEX) ? Integer.valueOf(hashMap.get(FirebaseAnalytics.Param.INDEX).toString()).intValue() : 0;
                        Fragment_Manage_Passcode.directionalEmoji.add(obj);
                        Fragment_Manage_Passcode.directionalCode.add(obj2);
                        Fragment_Manage_Passcode.directionalName.add(obj3);
                        Fragment_Manage_Passcode.directionalIndex.add(Integer.valueOf(intValue));
                        Fragment_Manage_Passcode.lotoInfo.addStoredPasscodeToList(intValue, Fragment_Manage_Passcode.convertPasscodeToDecimal(obj2));
                    }
                    for (int size = Fragment_Manage_Passcode.directionalEmoji.size() - 1; size >= 0; size--) {
                        Fragment_Manage_Passcode.directionalEmojiList.add(Fragment_Manage_Passcode.directionalEmoji.get(size));
                    }
                    for (int size2 = Fragment_Manage_Passcode.directionalCode.size() - 1; size2 >= 0; size2--) {
                        Fragment_Manage_Passcode.directionalCodeList.add(Fragment_Manage_Passcode.directionalCode.get(size2));
                    }
                    for (int size3 = Fragment_Manage_Passcode.directionalName.size() - 1; size3 >= 0; size3--) {
                        Fragment_Manage_Passcode.nameList.add(Fragment_Manage_Passcode.directionalName.get(size3));
                    }
                    for (int size4 = Fragment_Manage_Passcode.directionalIndex.size() - 1; size4 >= 0; size4--) {
                        Fragment_Manage_Passcode.indexList.add(Fragment_Manage_Passcode.directionalIndex.get(size4));
                    }
                    Fragment_Manage_Passcode.lotoInfo.setUpdatedIndexList(Fragment_Manage_Passcode.indexList);
                    Fragment_Manage_Passcode.lotoInfo.setTotalAvailablePasscode(Fragment_Manage_Passcode.directionalCode.size());
                    System.out.println("Hello checking the lock Passcode next available slot :" + Fragment_Manage_Passcode.lotoInfo.nextAvailabeIndex);
                    System.out.println("Passcode list :" + Fragment_Manage_Passcode.lotoInfo.getBackupPasscodeList());
                    Fragment_Manage_Passcode.adapter = new ArrayAdapter_ManagePasscode(MainActivity.context, Fragment_Manage_Passcode.directionalEmojiList, Fragment_Manage_Passcode.directionalCodeList, Fragment_Manage_Passcode.directionalIndex, Fragment_Manage_Passcode.nameList);
                    Fragment_Manage_Passcode.listview.setAdapter((ListAdapter) Fragment_Manage_Passcode.adapter);
                    Fragment_Manage_Passcode.adapter.notifyDataSetChanged();
                    customProgressBar.closeDialog(0L);
                    return;
                }
                Fragment_Manage_Passcode.adapter = new ArrayAdapter_ManagePasscode(MainActivity.context, Fragment_Manage_Passcode.directionalEmojiList, Fragment_Manage_Passcode.directionalCodeList, Fragment_Manage_Passcode.directionalIndex, Fragment_Manage_Passcode.nameList);
                Fragment_Manage_Passcode.listview.setAdapter((ListAdapter) Fragment_Manage_Passcode.adapter);
                Fragment_Manage_Passcode.adapter.notifyDataSetChanged();
                customProgressBar.closeDialog(0L);
            }
        });
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.manage_passcode);
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
        rootView = layoutInflater.inflate(R.layout.fragment_manage_passcode, viewGroup, false);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.manage_passcode);
        return rootView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int convertPasscodeToDecimal(String str) {
        String str2 = "";
        for (int length = str.length() - 1; length >= 0; length--) {
            str2 = str2 + "" + (Integer.parseInt("" + str.charAt(length)) - 1);
        }
        String str3 = "";
        for (int i = 0; i < str2.length(); i++) {
            str3 = str3 + String.format("%2s", Integer.toBinaryString(Integer.parseInt("" + str2.charAt(i)))).replace(' ', '0');
        }
        return Integer.parseInt(str3, 2) + 1;
    }
}
