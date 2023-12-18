package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NfcAdapter;
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
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.facebook.appevents.AppEventsConstants;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class Fragment_addlock extends Fragment {
    public static NfcAdapter mNFCAdapter;
    private Menu menu;
    View rootView = null;
    public Boolean nfc_verification = false;

    public static Fragment_addlock newInstance() {
        return new Fragment_addlock();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.add_lock);
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
        this.rootView = layoutInflater.inflate(R.layout.fragment_choose_type, viewGroup, false);
        setHasOptionsMenu(true);
        getActivity().setTitle(this.rootView.getResources().getString(R.string.add_lock));
        UI_BLE.add_lock_mode = true;
        MainActivity.fab.setVisibility(4);
        MainActivity.fab_share.setVisibility(4);
        MainActivity.fab_admin_access_locks.setVisibility(8);
        if (MainActivity.dashboard_listview != null) {
            MainActivity.dashboard_listview.setVisibility(4);
        }
        MainActivity.pullToRefresh.setEnabled(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(getString(R.string.smart_anticut));
        arrayList.add(getString(R.string.ip66_5th_gen));
        arrayList.add(this.rootView.getResources().getString(R.string.loto_models));
        arrayList.add(this.rootView.getResources().getString(R.string.smart_ip45_weatherproof_padlock));
        arrayList.add(this.rootView.getResources().getString(R.string.smart_NFC_travel_padlock));
        arrayList.add(this.rootView.getResources().getString(R.string.smart_BT_NFC_padlock));
        arrayList.add(this.rootView.getResources().getString(R.string.smart_luggage_zipper_lock_embossed));
        arrayList.add(this.rootView.getResources().getString(R.string.smart_luggage_zipper_lock_debossed));
        arrayList.add(this.rootView.getResources().getString(R.string.smart_NFC_padlock));
        ListView listView = (ListView) this.rootView.findViewById(R.id.listview_locktype);
        listView.setAdapter((ListAdapter) new ArrayAdapter_addlock(getActivity(), arrayList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_addlock.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (Helper_Network.haveNetworkConnection(Fragment_addlock.this.rootView.getContext())) {
                    int i2 = 1;
                    while (DatabaseVariable.db_lock.rawQuery(DatabaseVariable.Query_lock_db, null).moveToNext()) {
                        i2++;
                    }
                    if (i2 <= 20) {
                        switch (i) {
                            case 0:
                                System.out.println("Hello testing the anticut Add lock");
                                BLEService.selected_lock_model = "GT2550";
                                MainActivity.scanning_new_lock = true;
                                MainActivity.start_scanning = true;
                                break;
                            case 1:
                                System.out.println("Hello testing the ip66 Add lock");
                                BLEService.selected_lock_model = "GT2500";
                                MainActivity.scanning_new_lock = true;
                                MainActivity.start_scanning = true;
                                break;
                            case 2:
                                System.out.println("HEY i selected GT5100");
                                BLEService.selected_lock_model = "GT5100";
                                MainActivity.scanning_new_lock = true;
                                MainActivity.start_scanning = true;
                                break;
                            case 3:
                                Log.i("TAG", "5");
                                System.out.println("HEY i selected GT2100");
                                BLEService.selected_lock_model = "GT2100";
                                MainActivity.scanning_new_lock = true;
                                MainActivity.start_scanning = true;
                                break;
                            case 4:
                                Log.i("TAG", AppEventsConstants.EVENT_PARAM_VALUE_NO);
                                System.out.println("HEY i selected GT1000");
                                BLEService.selected_lock_model = "GT1000";
                                MainActivity.scanning_new_lock = true;
                                MainActivity.start_scanning = true;
                                break;
                            case 5:
                                Log.i("TAG", ExifInterface.GPS_MEASUREMENT_2D);
                                BLEService.selected_lock_model = "GT2002";
                                System.out.println("HEY i selected GT2002");
                                MainActivity.scanning_new_lock = true;
                                MainActivity.start_scanning = true;
                                break;
                            case 6:
                                Log.i("TAG", ExifInterface.GPS_MEASUREMENT_3D);
                                BLEService.selected_lock_model = "GT3000";
                                System.out.println("HEY i selected GT3000");
                                break;
                            case 7:
                                Log.i("TAG", "4");
                                BLEService.selected_lock_model = "GT3002";
                                System.out.println("HEY i selected GT3002");
                                BLEService.selected_lock_model = "GT3002";
                                MainActivity.scanning_new_lock = true;
                                MainActivity.start_scanning = true;
                                break;
                            case 8:
                                Log.i("TAG", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                                System.out.println("HEY i selected GT2000");
                                BLEService.selected_lock_model = "GT2000";
                                break;
                        }
                        if (BLEService.selected_lock_model.equals("GT2000") || BLEService.selected_lock_model.equals("GT3000") || BLEService.selected_lock_model.equals("GT2003")) {
                            Fragment_addlock.mNFCAdapter = NfcAdapter.getDefaultAdapter(Fragment_addlock.this.getActivity());
                            System.out.println("Hello selected model NFC");
                            System.out.println("Hello NFC" + Fragment_addlock.mNFCAdapter);
                            if (Fragment_addlock.mNFCAdapter != null && !Fragment_addlock.mNFCAdapter.isEnabled()) {
                                Fragment_addlock.this.turn_on_nfc_dialog();
                                return;
                            }
                            Fragment_addlock.this.getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_left, R.animator.slide_out_right).replace(R.id.container, Fragment_addlock2.newInstance()).addToBackStack("AddLock2").commit();
                            return;
                        }
                        Fragment_addlock.this.getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_left, R.animator.slide_out_right).replace(R.id.container, Fragment_addlock2.newInstance()).addToBackStack("AddLock2").commit();
                        return;
                    }
                    Toast.makeText(Fragment_addlock.this.getActivity(), "Lock Limit Reached Maximum", 0).show();
                    return;
                }
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(Fragment_addlock.this.rootView.getContext(), 1);
                sweetAlertDialog.setTitleText(Fragment_addlock.this.getString(R.string.no_connection));
                sweetAlertDialog.setContentText(Fragment_addlock.this.getString(R.string.no_internet));
                sweetAlertDialog.setConfirmText(Fragment_addlock.this.getString(R.string.ok));
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_addlock.1.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog2) {
                        sweetAlertDialog2.dismissWithAnimation();
                    }
                });
                sweetAlertDialog.show();
            }
        });
        return this.rootView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void turn_on_nfc_dialog() {
        new SweetAlertDialog(getActivity()).setTitleText(getResources().getString(R.string.NFC_is_not_turned_on)).setContentText(getResources().getString(R.string.click_setting_to_turn_on_NFC)).setConfirmText(getResources().getString(R.string.setting)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_addlock.3
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
                Fragment_addlock.this.startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
            }
        }).setCancelText(getResources().getString(R.string.cancel)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_addlock.2
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        }).show();
    }
}
