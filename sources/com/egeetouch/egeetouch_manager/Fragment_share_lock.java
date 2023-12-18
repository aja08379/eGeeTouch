package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Date;
/* loaded from: classes.dex */
public class Fragment_share_lock extends Fragment {
    private Menu menu;
    View rootView = null;
    private Spinner spinner_lock;
    private Spinner spinner_mode;
    private Spinner spinner_user;

    public static Fragment_share_lock newInstance() {
        return new Fragment_share_lock();
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.share_lock);
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
        this.rootView = layoutInflater.inflate(R.layout.fragment_share_lock, viewGroup, false);
        setHasOptionsMenu(true);
        getActivity().setTitle(this.rootView.getResources().getString(R.string.share_lock));
        Switch r14 = (Switch) this.rootView.findViewById(R.id.choose_duration_switch);
        Boolean.valueOf(r14.isChecked());
        final RelativeLayout relativeLayout = (RelativeLayout) this.rootView.findViewById(R.id.choose_duration_relative_view);
        final Button button = (Button) this.rootView.findViewById(R.id.btn_start_date);
        Button button2 = (Button) this.rootView.findViewById(R.id.btn_end_date);
        final EditText editText = (EditText) this.rootView.findViewById(R.id.start_date_textfield);
        final EditText editText2 = (EditText) this.rootView.findViewById(R.id.end_date_textfield);
        MainActivity.selected_share_lock_startTime = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        MainActivity.selected_share_lock_endTime = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        r14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_lock.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    System.out.println("HEY the switch is checked");
                    button.setVisibility(0);
                    relativeLayout.setVisibility(0);
                    return;
                }
                System.out.println("HEY the switch is NOT checked");
                button.setVisibility(8);
                relativeLayout.setVisibility(8);
                MainActivity.selected_share_lock_startTime = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                MainActivity.selected_share_lock_endTime = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                editText.setText("");
                editText2.setText("");
            }
        });
        System.out.println("Hello Retractable access status before clicking:" + MainActivity.selected_share_lock_retractable);
        MainActivity.selected_share_lock_retractable = false;
        ((Switch) this.rootView.findViewById(R.id.retractable_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_lock.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    MainActivity.selected_share_lock_retractable = true;
                    Toast.makeText(Fragment_share_lock.this.getActivity(), "Retractable Access ON", 0).show();
                } else {
                    MainActivity.selected_share_lock_retractable = false;
                    Toast.makeText(Fragment_share_lock.this.getActivity(), "Retractable Access OFF", 0).show();
                }
                System.out.println("Hello Retractable access status after clicking:" + MainActivity.selected_share_lock_retractable);
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_lock.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new SingleDateAndTimePickerDialog.Builder(MainActivity.context).minutesStep(15).mainColor(Color.rgb(56, 154, 224)).displayListener(new SingleDateAndTimePickerDialog.DisplayListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_lock.3.2
                    @Override // com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog.DisplayListener
                    public void onDisplayed(SingleDateAndTimePicker singleDateAndTimePicker) {
                    }
                }).title("Start Time").listener(new SingleDateAndTimePickerDialog.Listener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_lock.3.1
                    @Override // com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog.Listener
                    public void onDateSelected(Date date) {
                        System.out.println("HEY selected start date: " + date.getDate() + " gettime:" + date.getTime() + " " + date.toLocaleString());
                        editText.setText(date.toString());
                        MainActivity.selected_share_lock_startTime = date.getTime() / 1000;
                        System.out.println("HEY MainActivity.selected_share_lock_startTime :" + MainActivity.selected_share_lock_startTime);
                    }
                }).display();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_lock.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new SingleDateAndTimePickerDialog.Builder(MainActivity.context).minutesStep(15).mainColor(Color.rgb(56, 154, 224)).displayListener(new SingleDateAndTimePickerDialog.DisplayListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_lock.4.2
                    @Override // com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog.DisplayListener
                    public void onDisplayed(SingleDateAndTimePicker singleDateAndTimePicker) {
                    }
                }).title("Start Time").listener(new SingleDateAndTimePickerDialog.Listener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_lock.4.1
                    @Override // com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog.Listener
                    public void onDateSelected(Date date) {
                        System.out.println("HEY selected end date: " + date);
                        editText2.setText(date.toString());
                        MainActivity.selected_share_lock_endTime = date.getTime() / 1000;
                        System.out.println("HEY MainActivity.selected_share_lock_endTime :" + MainActivity.selected_share_lock_endTime);
                    }
                }).display();
            }
        });
        MainActivity.fab.setVisibility(4);
        MainActivity.fab_share.setVisibility(4);
        MainActivity.fab_admin_access_locks.setVisibility(4);
        if (MainActivity.dashboard_listview != null) {
            MainActivity.dashboard_listview.setVisibility(4);
        }
        MainActivity.pullToRefresh.setEnabled(false);
        this.spinner_lock = (Spinner) this.rootView.findViewById(R.id.spinner_lock);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), 17367048, MainActivity.list_admin_lock);
        arrayAdapter.setDropDownViewResource(17367049);
        this.spinner_lock.setAdapter((android.widget.SpinnerAdapter) arrayAdapter);
        this.spinner_lock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_lock.5
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                MainActivity.selected_share_lock = Fragment_share_lock.this.spinner_lock.getSelectedItem().toString();
                Cursor rawQuery = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(MainActivity.selected_share_lock), null);
                while (rawQuery.moveToNext()) {
                    MainActivity.selected_share_lock_password = rawQuery.getString(1);
                    MainActivity.selected_share_lock_model = rawQuery.getString(2);
                    MainActivity.selected_share_lock_version = rawQuery.getString(3);
                    MainActivity.selected_share_lock_lastBatt = rawQuery.getString(4);
                    MainActivity.selected_share_lock_serial = rawQuery.getString(5);
                    MainActivity.selected_share_lock_MACAddress = rawQuery.getString(6);
                }
                rawQuery.close();
            }
        });
        this.spinner_user = (Spinner) this.rootView.findViewById(R.id.spinner_user);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getActivity(), 17367048, MainActivity.firebase_user);
        arrayAdapter2.setDropDownViewResource(17367049);
        this.spinner_user.setAdapter((android.widget.SpinnerAdapter) arrayAdapter2);
        this.spinner_user.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_lock.6
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                MainActivity.selected_share_email = MainActivity.firebase_user_email[i];
            }
        });
        String[] strArr = {this.rootView.getResources().getString(R.string.permenant), this.rootView.getResources().getString(R.string.one_time_access)};
        this.spinner_mode = (Spinner) this.rootView.findViewById(R.id.spinner_mode);
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(getActivity(), 17367048, strArr);
        arrayAdapter3.setDropDownViewResource(17367049);
        this.spinner_mode.setAdapter((android.widget.SpinnerAdapter) arrayAdapter3);
        this.spinner_mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_share_lock.7
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        return this.rootView;
    }
}
