package com.egeetouch.egeetouch_manager;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.egeetouch.egeetouch_manager.SublimePickerFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import es.dmoral.toasty.Toasty;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.crypto.NoSuchPaddingException;
/* loaded from: classes.dex */
public class Activity_share_lock extends AppCompatActivity {
    Switch chooseDurationSwitch;
    public FirebaseDatabase database;
    EditText endDateTextField;
    public SweetAlertDialog loading_share_history;
    SelectedDate mSelectedDateEnd;
    SelectedDate mSelectedDateStart;
    Switch retractableSwitch;
    Spinner spinner_lock;
    Spinner spinner_mode;
    Spinner spinner_user;
    EditText startDateTextField;
    TextView tv_shareModeInfo;
    long unixDateEnd;
    long unixDateStart;
    public static List<Integer> selected_user_index = new ArrayList();
    private static long numberOfShareHistory = 0;
    private static int currentShareHistoryCounterNumber = 0;
    public static String selected_share_lock = "";
    public static String selected_share_email = "";
    public static String selected_share_role = "";
    public static String selected_share_user_name = "";
    public static String selected_share_lock_model = "";
    public static String selected_share_lock_password = "";
    public static String selected_share_lock_version = "";
    public static String selected_share_lock_lastBatt = "";
    public static String selected_share_lock_serial = "";
    public static String selected_share_lock_MACAddress = "";
    public static long share_friend_number_of_lock = 1;
    public static boolean selected_share_lock_retractable = true;
    public static String share_access_token_from_admin = "";
    public static int selected_share_lock_Type = 0;
    public static double selected_share_lock_startTime = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    public static double selected_share_lock_endTime = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    public static String selected_share_lock_ip45SerialNumber = "";
    public static String selected_share_lock_sharedByEmail = "";
    public static boolean selected_lock_is_shared = false;
    public static List<String> fivelistRecipName = new ArrayList();
    public static ArrayList<String> shareHistoryLockName = new ArrayList<>();
    public static ArrayList<String> shareHistorySharedOnDate = new ArrayList<>();
    public static ArrayList<String> shareHistorySharedTo = new ArrayList<>();
    public static ArrayList<String> shareHistoryStartDate = new ArrayList<>();
    public static ArrayList<String> shareHistoryEndDate = new ArrayList<>();
    public static ArrayList<String> fiveRecipientList = new ArrayList<>();
    SublimePickerFragment.Callback mFragmentCallbackStart = new SublimePickerFragment.Callback() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.1
        @Override // com.egeetouch.egeetouch_manager.SublimePickerFragment.Callback
        public void onCancelled() {
        }

        @Override // com.egeetouch.egeetouch_manager.SublimePickerFragment.Callback
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int i, int i2, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String str) {
            Activity_share_lock.this.mSelectedDateStart = selectedDate;
            Activity_share_lock activity_share_lock = Activity_share_lock.this;
            activity_share_lock.unixDateStart = (activity_share_lock.mSelectedDateStart.getFirstDate().getTimeInMillis() / 1000) - ((Activity_share_lock.this.mSelectedDateStart.getFirstDate().getTimeInMillis() / 1000) % 86400);
            TimeZone timeZone = Calendar.getInstance().getTimeZone();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ssZ");
            simpleDateFormat.setTimeZone(timeZone);
            Date date = new Date(Activity_share_lock.this.unixDateStart * 1000);
            String substring = simpleDateFormat.format(date).substring(simpleDateFormat.format(date).length() - 4);
            Activity_share_lock.this.unixDateStart += ((i - Integer.parseInt(substring.substring(0, 2))) * 3600) + ((i2 - Integer.parseInt(substring.substring(2, 4))) * 60);
            if (Activity_share_lock.selected_share_lock_endTime > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && Activity_share_lock.this.unixDateStart >= Activity_share_lock.selected_share_lock_endTime) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(Activity_share_lock.this, 1);
                sweetAlertDialog.setTitleText(Activity_share_lock.this.getString(R.string.invalid_time));
                sweetAlertDialog.setContentText(Activity_share_lock.this.getString(R.string.error_start_time));
                sweetAlertDialog.show();
                return;
            }
            Activity_share_lock.selected_share_lock_startTime = Activity_share_lock.this.unixDateStart;
            if (Locale.getDefault().getLanguage().equals("ja")) {
                Activity_share_lock.this.startDateTextField.setText(new SimpleDateFormat("E, yyyy年MM月dd日 HH時mm分").format(date) + " (GMT" + new SimpleDateFormat("Z").format(date) + ")");
            } else {
                Activity_share_lock.this.startDateTextField.setText(new SimpleDateFormat("E, MMM dd yyyy hh:mm a").format(date) + " (GMT" + new SimpleDateFormat("Z").format(date) + ")");
            }
        }
    };
    SublimePickerFragment.Callback mFragmentCallbackEnd = new SublimePickerFragment.Callback() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.2
        @Override // com.egeetouch.egeetouch_manager.SublimePickerFragment.Callback
        public void onCancelled() {
        }

        @Override // com.egeetouch.egeetouch_manager.SublimePickerFragment.Callback
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int i, int i2, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String str) {
            Activity_share_lock.this.mSelectedDateEnd = selectedDate;
            Activity_share_lock activity_share_lock = Activity_share_lock.this;
            activity_share_lock.unixDateEnd = (activity_share_lock.mSelectedDateEnd.getFirstDate().getTimeInMillis() / 1000) - ((Activity_share_lock.this.mSelectedDateEnd.getFirstDate().getTimeInMillis() / 1000) % 86400);
            TimeZone timeZone = Calendar.getInstance().getTimeZone();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ssZ");
            simpleDateFormat.setTimeZone(timeZone);
            Date date = new Date(Activity_share_lock.this.unixDateEnd * 1000);
            String substring = simpleDateFormat.format(date).substring(simpleDateFormat.format(date).length() - 4);
            Activity_share_lock.this.unixDateEnd += ((i - Integer.parseInt(substring.substring(0, 2))) * 3600) + ((i2 - Integer.parseInt(substring.substring(2, 4))) * 60);
            if (Activity_share_lock.selected_share_lock_startTime > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && Activity_share_lock.this.unixDateEnd <= Activity_share_lock.selected_share_lock_startTime) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(Activity_share_lock.this, 1);
                sweetAlertDialog.setTitleText(Activity_share_lock.this.getString(R.string.invalid_time));
                sweetAlertDialog.setContentText(Activity_share_lock.this.getString(R.string.error_end_time));
                sweetAlertDialog.show();
                return;
            }
            Activity_share_lock.selected_share_lock_endTime = Activity_share_lock.this.unixDateEnd;
            Date date2 = new Date(Activity_share_lock.this.unixDateEnd * 1000);
            if (Locale.getDefault().getLanguage().equals("ja")) {
                Activity_share_lock.this.endDateTextField.setText(new SimpleDateFormat("E, yyyy年MM月dd日 HH時mm分").format(date2) + " (GMT" + new SimpleDateFormat("Z").format(date2) + ")");
            } else {
                Activity_share_lock.this.endDateTextField.setText(new SimpleDateFormat("E, MMM dd yyyy hh:mm a").format(date2) + " (GMT" + new SimpleDateFormat("Z").format(date2) + ")");
            }
        }
    };

    static /* synthetic */ int access$108() {
        int i = currentShareHistoryCounterNumber;
        currentShareHistoryCounterNumber = i + 1;
        return i;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_share_lock);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setTitle(getResources().getString(R.string.share_lock));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.chooseDurationSwitch = (Switch) findViewById(R.id.choose_duration_switch);
        this.retractableSwitch = (Switch) findViewById(R.id.retractable_switch);
        this.startDateTextField = (EditText) findViewById(R.id.start_date_textfield);
        this.endDateTextField = (EditText) findViewById(R.id.end_date_textfield);
        this.spinner_lock = (Spinner) findViewById(R.id.spinner_lock);
        this.spinner_user = (Spinner) findViewById(R.id.spinner_user);
        this.spinner_mode = (Spinner) findViewById(R.id.spinner_mode);
        this.tv_shareModeInfo = (TextView) findViewById(R.id.tv_mode_info);
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.choose_duration_relative_view);
        final Button button = (Button) findViewById(R.id.btn_start_date);
        selected_share_lock_retractable = true;
        selected_share_lock_startTime = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        selected_share_lock_endTime = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        fivelistRecipName = Firebase_Data_management.fivelistRecipientName;
        System.out.println("Hello check email Recipient : list : " + fivelistRecipName);
        this.chooseDurationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.3
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
                Activity_share_lock.selected_share_lock_startTime = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                Activity_share_lock.selected_share_lock_endTime = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                Activity_share_lock.this.startDateTextField.setText("");
                Activity_share_lock.this.endDateTextField.setText("");
            }
        });
        this.retractableSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    Activity_share_lock.selected_share_lock_retractable = true;
                    Activity_share_lock.this.tv_shareModeInfo.setText(R.string.recipient_has_to_have_internet_connectivity_to_access_lock);
                    Toast.makeText(Activity_share_lock.this, "Retractable Access ON", 0).show();
                    return;
                }
                Activity_share_lock.selected_share_lock_retractable = false;
                Activity_share_lock.this.tv_shareModeInfo.setText(R.string.locks_are_accessible_offline_but_cannot_be_retracted);
                Toast.makeText(Activity_share_lock.this, "Retractable Access OFF", 0).show();
            }
        });
        selected_share_lock_startTime = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        selected_share_lock_endTime = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SublimePickerFragment sublimePickerFragment = new SublimePickerFragment();
                sublimePickerFragment.setCallback(Activity_share_lock.this.mFragmentCallbackStart);
                SublimeOptions sublimeOptions = new SublimeOptions();
                sublimeOptions.setDisplayOptions(3);
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("SUBLIME_OPTIONS", sublimeOptions);
                sublimePickerFragment.setArguments(bundle2);
                sublimePickerFragment.setStyle(0, 0);
                sublimePickerFragment.show(Activity_share_lock.this.getSupportFragmentManager(), "SUBLIME_PICKER");
            }
        });
        ((Button) findViewById(R.id.btn_end_date)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SublimePickerFragment sublimePickerFragment = new SublimePickerFragment();
                sublimePickerFragment.setCallback(Activity_share_lock.this.mFragmentCallbackEnd);
                SublimeOptions sublimeOptions = new SublimeOptions();
                sublimeOptions.setDisplayOptions(3);
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("SUBLIME_OPTIONS", sublimeOptions);
                sublimePickerFragment.setArguments(bundle2);
                sublimePickerFragment.setStyle(1, 0);
                sublimePickerFragment.show(Activity_share_lock.this.getSupportFragmentManager(), "SUBLIME_PICKER");
            }
        });
        this.spinner_lock.setAdapter((android.widget.SpinnerAdapter) new SpinnerAdapter(this, MainActivity.list));
        this.spinner_lock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.7
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Activity_share_lock.selected_share_lock = Activity_share_lock.this.spinner_lock.getSelectedItem().toString();
                Cursor rawQuery = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(Activity_share_lock.selected_share_lock), null);
                while (rawQuery.moveToNext()) {
                    Activity_share_lock.selected_share_lock_password = rawQuery.getString(1);
                    Activity_share_lock.selected_share_lock_model = rawQuery.getString(2);
                    Activity_share_lock.selected_share_lock_version = rawQuery.getString(3);
                    Activity_share_lock.selected_share_lock_lastBatt = rawQuery.getString(4);
                    Activity_share_lock.selected_share_lock_serial = rawQuery.getString(5);
                    Activity_share_lock.selected_share_lock_MACAddress = rawQuery.getString(6);
                }
                rawQuery.close();
            }
        });
        ArrayList arrayList = new ArrayList(Collections.singletonList(getString(R.string.select_a_recipient)));
        arrayList.addAll(fivelistRecipName);
        System.out.println("Hello check temp email Recipient : list : " + arrayList);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367048, arrayList);
        arrayAdapter.setDropDownViewResource(17367049);
        this.spinner_user.setAdapter((android.widget.SpinnerAdapter) arrayAdapter);
        this.spinner_user.setOnItemSelectedListener(new AnonymousClass8());
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, 17367048, new String[]{getResources().getString(R.string.permenant), getResources().getString(R.string.one_time_access)});
        arrayAdapter2.setDropDownViewResource(17367049);
        this.spinner_mode.setAdapter((android.widget.SpinnerAdapter) arrayAdapter2);
        this.spinner_mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.9
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        FirebaseDatabase.getInstance().getReference("userFriendList").child(MainActivity.user_uid).addValueEventListener(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.10
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                Activity_share_lock.this.fetch_Recipient_local(MainActivity.user_uid);
            }
        });
    }

    /* renamed from: com.egeetouch.egeetouch_manager.Activity_share_lock$8  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass8 implements AdapterView.OnItemSelectedListener {
        AnonymousClass8() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (i > 0) {
                int i2 = i - 1;
                Activity_share_lock.selected_share_email = Firebase_Data_management.listRecipientEmail.get(i2);
                Activity_share_lock.selected_share_user_name = Firebase_Data_management.listRecipientName.get(i2);
                System.out.println("Hello check email Recipient :" + Activity_share_lock.selected_share_email + " name:" + Activity_share_lock.selected_share_user_name);
                Activity_share_lock.this.database = FirebaseDatabase.getInstance();
                Activity_share_lock.this.database.getReference("registeredUsersOnPlatform").orderByValue().equalTo(Activity_share_lock.selected_share_email).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.8.1
                    @Override // com.google.firebase.database.ValueEventListener
                    public void onCancelled(DatabaseError databaseError) {
                    }

                    @Override // com.google.firebase.database.ValueEventListener
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            return;
                        }
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(Activity_share_lock.this, 3);
                        sweetAlertDialog.setTitleText("No Account Registered");
                        sweetAlertDialog.setContentText("Please make sure the user " + Activity_share_lock.selected_share_email + " has been signed up for an eGeeTouch Manager account before sharing your lock with this user.");
                        sweetAlertDialog.setConfirmText("Help");
                        sweetAlertDialog.setCancelText(Activity_share_lock.this.getString(R.string.cancel));
                        sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.8.1.1
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                                sweetAlertDialog2.dismissWithAnimation();
                            }
                        });
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.8.1.2
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                                Intent intent = new Intent("android.intent.action.VIEW");
                                intent.setData(Uri.parse("https://www.youtube.com/watch?v=Qt3wxltkDLc"));
                                Activity_share_lock.this.startActivity(intent);
                                sweetAlertDialog2.dismiss();
                            }
                        });
                        sweetAlertDialog.show();
                        Toasty.normal(Activity_share_lock.this, Activity_share_lock.this.getString(R.string.email_not_found) + " " + Activity_share_lock.selected_share_email, Activity_share_lock.this.getResources().getDrawable(R.drawable.ic_cancel_black_24dp)).show();
                    }
                });
                return;
            }
            Activity_share_lock.selected_share_email = Activity_share_lock.this.getResources().getString(R.string.select_a_recipient);
            Activity_share_lock.selected_share_user_name = Activity_share_lock.this.getResources().getString(R.string.select_a_recipient);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            System.out.println("Hello check email Recipient : onNothingSelected");
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        System.out.println("Hello onResume Activity share lock()");
        fetch_Recipient_local(MainActivity.user_uid);
    }

    public void fetch_Recipient_local(String str) {
        final long[] jArr = {0};
        final int[] iArr = {0};
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        final ArrayList arrayList4 = new ArrayList();
        FirebaseDatabase.getInstance().getReference("userFriendList").child(str).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.11
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    jArr[0] = dataSnapshot.getChildrenCount();
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        System.out.println("Hello test temp class:" + dataSnapshot2.getValue().toString());
                        arrayList.add(dataSnapshot2.getValue().toString());
                        arrayList2.add(dataSnapshot2.getKey().toString());
                        arrayList3.add(true);
                        if (iArr[0] < 5) {
                            arrayList4.add(dataSnapshot2.getKey().toString());
                            int[] iArr2 = iArr;
                            iArr2[0] = iArr2[0] + 1;
                        }
                    }
                } else {
                    arrayList4.clear();
                    arrayList.clear();
                    arrayList2.clear();
                    arrayList3.clear();
                    System.out.println("Hello no recipient Found");
                }
                if (arrayList2.size() == 0) {
                    Firebase_Data_management.listRecipientEmail = arrayList;
                    Firebase_Data_management.listRecipientName = arrayList2;
                    Firebase_Data_management.listRecipientExists = arrayList3;
                    Firebase_Data_management.fivelistRecipientName = arrayList4;
                    Firebase_Data_management.number_of_user = jArr[0];
                    Firebase_Data_management.RecipientCount = iArr[0];
                    Activity_share_lock.this.UpdateSpinner();
                    return;
                }
                final int[] iArr3 = {0};
                for (final int i = 0; i < arrayList.size(); i++) {
                    FirebaseDatabase.getInstance().getReference("registeredUsersOnPlatform").orderByValue().equalTo((String) arrayList.get(i)).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.11.1
                        @Override // com.google.firebase.database.ValueEventListener
                        public void onCancelled(DatabaseError databaseError) {
                        }

                        @Override // com.google.firebase.database.ValueEventListener
                        public void onDataChange(DataSnapshot dataSnapshot3) {
                            if (!dataSnapshot3.exists()) {
                                arrayList3.add(i, false);
                            }
                            int[] iArr4 = iArr3;
                            iArr4[0] = iArr4[0] + 1;
                            if (iArr4[0] == arrayList.size()) {
                                Firebase_Data_management.listRecipientEmail = arrayList;
                                Firebase_Data_management.listRecipientName = arrayList2;
                                Firebase_Data_management.listRecipientExists = arrayList3;
                                Firebase_Data_management.fivelistRecipientName = arrayList4;
                                Firebase_Data_management.number_of_user = jArr[0];
                                Firebase_Data_management.RecipientCount = iArr[0];
                                Activity_share_lock.this.UpdateSpinner();
                            }
                        }
                    });
                }
            }
        });
    }

    public void UpdateSpinner() {
        System.out.println("Hello checking the Firebase update in spinner!");
        fivelistRecipName = Firebase_Data_management.fivelistRecipientName;
        ArrayList arrayList = new ArrayList(Collections.singletonList(getString(R.string.select_a_recipient)));
        arrayList.addAll(fivelistRecipName);
        System.out.println("Hello check temp email Recipient : list : " + arrayList);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367048, arrayList);
        arrayAdapter.setDropDownViewResource(17367049);
        this.spinner_user.setAdapter((android.widget.SpinnerAdapter) arrayAdapter);
    }

    public void btn_choose_duration(View view) {
        System.out.println("HEY btn_choose_duration is getting clicked");
        startActivity(new Intent(this, ChooseDurationActivity.class));
    }

    public void btn_retract_access(View view) {
        if (Helper_Network.haveNetworkConnection(this)) {
            startActivity(new Intent(view.getContext(), Activity_retract_access.class));
            return;
        }
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 0);
        sweetAlertDialog.setTitleText(getString(R.string.pls_note));
        sweetAlertDialog.setContentText(getString(R.string.you_are_not_connected_access_lock));
        sweetAlertDialog.show();
    }

    public void btn_share_add_user(View view) {
        if (Helper_Network.haveNetworkConnection(this)) {
            fetch_Recipient_local(MainActivity.user_uid);
            startActivity(new Intent(this, Activity_manage_recipients.class));
            return;
        }
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 0);
        sweetAlertDialog.setTitleText(getString(R.string.pls_note));
        sweetAlertDialog.setContentText(getString(R.string.you_are_not_connected_access_lock));
        sweetAlertDialog.show();
    }

    public void btn_share_history(View view) {
        System.out.println("HEY btn_share_history button clicked");
        if (Helper_Network.haveNetworkConnection(this)) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 5);
            this.loading_share_history = sweetAlertDialog;
            sweetAlertDialog.setTitleText(getString(R.string.loading));
            this.loading_share_history.setCancelable(false);
            customProgressBar.ShowProgressBar(this, getString(R.string.loading));
            shareHistoryLockName.clear();
            shareHistorySharedTo.clear();
            shareHistorySharedOnDate.clear();
            shareHistoryStartDate.clear();
            shareHistoryEndDate.clear();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            this.database = firebaseDatabase;
            firebaseDatabase.getReference("MyShareHistory").child(MainActivity.user_uid).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.12
                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        long unused = Activity_share_lock.numberOfShareHistory = dataSnapshot.getChildrenCount();
                        System.out.println("HEY numberOfShareHistory " + Activity_share_lock.numberOfShareHistory);
                        int unused2 = Activity_share_lock.currentShareHistoryCounterNumber = 0;
                        System.out.println("HEY currentShareHistoryCounterNumber is starting at  " + Activity_share_lock.currentShareHistoryCounterNumber);
                        System.out.println("HEY currentShareHistoryCounterNumber is at  " + Activity_share_lock.currentShareHistoryCounterNumber);
                        for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                            HashMap hashMap = (HashMap) dataSnapshot2.getValue();
                            String obj = hashMap.containsKey("lockName") ? hashMap.get("lockName").toString() : " N.A ";
                            String obj2 = hashMap.containsKey("sharedOn") ? hashMap.get("sharedOn").toString() : " N.A ";
                            String obj3 = hashMap.containsKey("sharedTo") ? hashMap.get("sharedTo").toString() : " N.A ";
                            String str = "";
                            String obj4 = hashMap.containsKey("startDate") ? hashMap.get("startDate").toString() : "";
                            if (hashMap.containsKey("endDate")) {
                                str = hashMap.get("endDate").toString();
                            }
                            Activity_share_lock.shareHistoryLockName.add(Activity_share_lock.currentShareHistoryCounterNumber, obj);
                            Activity_share_lock.shareHistorySharedOnDate.add(Activity_share_lock.currentShareHistoryCounterNumber, obj2);
                            Activity_share_lock.shareHistorySharedTo.add(Activity_share_lock.currentShareHistoryCounterNumber, obj3);
                            Activity_share_lock.shareHistoryStartDate.add(Activity_share_lock.currentShareHistoryCounterNumber, obj4);
                            Activity_share_lock.shareHistoryEndDate.add(Activity_share_lock.currentShareHistoryCounterNumber, str);
                            Activity_share_lock.access$108();
                        }
                        if (Activity_share_lock.currentShareHistoryCounterNumber == Activity_share_lock.numberOfShareHistory) {
                            Activity_share_lock.this.startActivity(new Intent(Activity_share_lock.this, Activity_share_history.class));
                            customProgressBar.closeDialog(10L);
                            return;
                        }
                        return;
                    }
                    customProgressBar.closeDialog(0L);
                    SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(Activity_share_lock.this, 0);
                    sweetAlertDialog2.setTitleText(Activity_share_lock.this.getString(R.string.pls_note));
                    sweetAlertDialog2.setContentText(Activity_share_lock.this.getString(R.string.no_audittrial_history));
                    sweetAlertDialog2.show();
                }
            });
            return;
        }
        SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(this, 0);
        sweetAlertDialog2.setTitleText(getString(R.string.pls_note));
        sweetAlertDialog2.setContentText(getString(R.string.you_are_not_connected_access_lock));
        sweetAlertDialog2.show();
    }

    public void btn_sharelock_submit(View view) {
        this.database = FirebaseDatabase.getInstance();
        if (Helper_Network.haveNetworkConnection(this)) {
            if (selected_share_lock_model.equals("GT1000") || selected_share_lock_model.equals("GT2002") || selected_share_lock_model.equals("GT2100") || selected_share_lock_model.equals("GT3000") || selected_share_lock_model.equals("GT5107") || selected_share_lock_model.equals("GT5109") || selected_share_lock_model.equals("GT5300") || selected_share_lock_model.equals("GT2500") || selected_share_lock_model.equals("GT2550")) {
                if (selected_share_email.equals("")) {
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 1);
                    sweetAlertDialog.setTitleText(getString(R.string.no_friend_is_added));
                    sweetAlertDialog.setContentText(getString(R.string.pls_add_a_friend));
                    sweetAlertDialog.show();
                    return;
                } else if (selected_share_email.equals(getResources().getString(R.string.select_a_recipient))) {
                    SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(this, 1);
                    sweetAlertDialog2.setTitleText(getString(R.string.invalid_recipient));
                    sweetAlertDialog2.setContentText(getString(R.string.please_select_a_recipient));
                    sweetAlertDialog2.show();
                    return;
                } else {
                    Cursor rawQuery = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(selected_share_lock), null);
                    while (rawQuery.moveToNext()) {
                        selected_share_lock_password = rawQuery.getString(1);
                        selected_share_lock_model = rawQuery.getString(2);
                        selected_share_lock_version = rawQuery.getString(3);
                        selected_share_lock_lastBatt = rawQuery.getString(4);
                        selected_share_lock_serial = rawQuery.getString(5);
                        selected_share_lock_MACAddress = rawQuery.getString(6);
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        selected_share_lock_sharedByEmail = currentUser != null ? currentUser.getEmail() : "Unknown Email Address";
                        selected_share_lock_ip45SerialNumber = rawQuery.getString(5);
                        if (selected_share_lock_model.equals("GT2100")) {
                            selected_share_lock_Type = 4;
                        } else if (selected_share_lock_model.equals("GT2002")) {
                            selected_share_lock_Type = 3;
                        } else if (selected_share_lock_model.equals("GT1000")) {
                            selected_share_lock_Type = 2;
                        } else if (selected_share_lock_model.equals("GT3100")) {
                            selected_share_lock_Type = 1;
                        } else if (selected_share_lock_model.equals("GT5300")) {
                            selected_share_lock_Type = 7;
                        } else if (selected_share_lock_model.equals("GT5107")) {
                            selected_share_lock_Type = 8;
                        } else if (selected_share_lock_model.equals("GT5109")) {
                            selected_share_lock_Type = 9;
                        } else if (selected_share_lock_model.equals("GT2500")) {
                            selected_share_lock_Type = 10;
                        } else if (selected_share_lock_model.equals("GT2550")) {
                            selected_share_lock_Type = 11;
                        } else {
                            selected_share_lock_Type = 0;
                        }
                        Log.i("TAG", "selected_share_lock_password: " + selected_share_lock_password + " selected_share_lock_model: " + selected_share_lock_model + " selected_share_lock_version: " + selected_share_lock_version + " selected_share_lock_lastBatt: " + selected_share_lock_lastBatt + " selected_share_lock_MACAddress: " + selected_share_lock_MACAddress + "selected_share_lock_Type: " + selected_share_lock_Type);
                    }
                    rawQuery.close();
                    FirebaseUser currentUser2 = FirebaseAuth.getInstance().getCurrentUser();
                    currentUser2.getEmail();
                    if (selected_share_email.equals(currentUser2.getEmail())) {
                        Toasty.normal(this, getString(R.string.you_already_have_this_lock), getResources().getDrawable(R.drawable.ic_error_outline_black_24dp)).show();
                        return;
                    }
                    String string = getString(!selected_share_lock_retractable ? R.string.once_shared_cannot_retract : R.string.user_has_to_be_online_to_access);
                    SweetAlertDialog sweetAlertDialog3 = new SweetAlertDialog(this, 0);
                    sweetAlertDialog3.setTitleText(getString(R.string.pls_confirm));
                    sweetAlertDialog3.setContentText(getString(R.string.are_you_sure_you_want_to_share) + ":\n" + selected_share_lock + "\n" + getString(R.string.with) + "\n" + selected_share_email + string);
                    sweetAlertDialog3.setConfirmText(getString(R.string.yes));
                    sweetAlertDialog3.setConfirmClickListener(new AnonymousClass13());
                    sweetAlertDialog3.setCancelText(getString(R.string.cancel));
                    sweetAlertDialog3.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.14
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog4) {
                            sweetAlertDialog4.dismissWithAnimation();
                        }
                    });
                    sweetAlertDialog3.show();
                    return;
                }
            }
            SweetAlertDialog sweetAlertDialog4 = new SweetAlertDialog(this, 0);
            sweetAlertDialog4.setTitleText(getString(R.string.pls_note));
            sweetAlertDialog4.setContentText(getString(R.string.lock_model_not_supported));
            sweetAlertDialog4.show();
            return;
        }
        SweetAlertDialog sweetAlertDialog5 = new SweetAlertDialog(this, 0);
        sweetAlertDialog5.setTitleText(getString(R.string.pls_note));
        sweetAlertDialog5.setContentText(getString(R.string.you_are_not_connected_access_lock));
        sweetAlertDialog5.show();
    }

    /* renamed from: com.egeetouch.egeetouch_manager.Activity_share_lock$13  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass13 implements SweetAlertDialog.OnSweetClickListener {
        AnonymousClass13() {
        }

        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
        public void onClick(SweetAlertDialog sweetAlertDialog) {
            sweetAlertDialog.dismissWithAnimation();
            if (MainActivity.list_admin_lock.contains(Activity_share_lock.selected_share_lock)) {
                final SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(Activity_share_lock.this, 5);
                sweetAlertDialog2.setTitleText("");
                sweetAlertDialog2.setContentText(Activity_share_lock.this.getString(R.string.pls_wait_sending_data_to_ur_friend));
                sweetAlertDialog2.setCancelable(false);
                sweetAlertDialog2.show();
                Activity_share_lock.this.database.getReference("registeredUsersOnPlatform").orderByValue().equalTo(Activity_share_lock.selected_share_email).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.13.1
                    @Override // com.google.firebase.database.ValueEventListener
                    public void onCancelled(DatabaseError databaseError) {
                    }

                    @Override // com.google.firebase.database.ValueEventListener
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final String str;
                        final String charSequence;
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                                if (dataSnapshot2.getValue().equals(Activity_share_lock.selected_share_email)) {
                                    final String key = dataSnapshot2.getKey();
                                    System.out.println("Hello ID getKey()" + key + " EX:" + dataSnapshot.getValue());
                                    final boolean[] zArr = {false};
                                    if (Activity_share_lock.selected_share_lock_startTime == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && Activity_share_lock.selected_share_lock_endTime == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                                        str = "";
                                        charSequence = str;
                                    } else {
                                        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
                                        calendar.setTimeInMillis(((long) Activity_share_lock.selected_share_lock_startTime) * 1000);
                                        String charSequence2 = DateFormat.format("MMM dd yyyy, HH:mm", calendar).toString();
                                        Calendar calendar2 = Calendar.getInstance(Locale.ENGLISH);
                                        calendar2.setTimeInMillis(((long) Activity_share_lock.selected_share_lock_endTime) * 1000);
                                        str = charSequence2;
                                        charSequence = DateFormat.format("MMM dd yyyy, HH:mm", calendar2).toString();
                                    }
                                    Activity_share_lock.this.database.getReference("MyShareHistory").child(MainActivity.user_uid).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.13.1.1
                                        @Override // com.google.firebase.database.ValueEventListener
                                        public void onCancelled(DatabaseError databaseError) {
                                        }

                                        @Override // com.google.firebase.database.ValueEventListener
                                        public void onDataChange(DataSnapshot dataSnapshot3) {
                                            if (dataSnapshot3.exists()) {
                                                for (DataSnapshot dataSnapshot4 : dataSnapshot3.getChildren()) {
                                                    if (dataSnapshot4.child("lockName").getValue().equals(Activity_share_lock.selected_share_lock) && dataSnapshot4.child("sharedTo").getValue().equals(Activity_share_lock.selected_share_email) && dataSnapshot4.child("startDate").getValue().equals(str) && dataSnapshot4.child("endDate").getValue().equals(charSequence)) {
                                                        zArr[0] = true;
                                                    }
                                                }
                                            } else {
                                                zArr[0] = false;
                                            }
                                            if (!Activity_share_lock.selected_share_lock_retractable) {
                                                zArr[0] = false;
                                            }
                                            if (!zArr[0]) {
                                                long parseLong = Long.parseLong(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())));
                                                Calendar calendar3 = Calendar.getInstance(Locale.ENGLISH);
                                                calendar3.setTimeInMillis(parseLong * 1000);
                                                DateFormat.format("MMM dd, HH:mm", calendar3).toString();
                                                DatabaseReference push = Activity_share_lock.this.database.getReference("MyShareHistory").child(MainActivity.user_uid).push();
                                                HashMap hashMap = new HashMap();
                                                hashMap.put("lockName", Activity_share_lock.selected_share_lock);
                                                hashMap.put("sharedTo", Activity_share_lock.selected_share_email);
                                                hashMap.put("sharedOn", Long.valueOf(parseLong));
                                                Activity_share_lock.share_access_token_from_admin = String.valueOf(push.child("lockName").push().getKey());
                                                if (Activity_share_lock.selected_share_lock_startTime != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || Activity_share_lock.selected_share_lock_endTime != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                                                    Calendar calendar4 = Calendar.getInstance(Locale.ENGLISH);
                                                    calendar4.setTimeInMillis(((long) Activity_share_lock.selected_share_lock_startTime) * 1000);
                                                    DateFormat.format("MMM dd yyyy, HH:mm", calendar4).toString();
                                                    hashMap.put("startDate", Double.valueOf(Activity_share_lock.selected_share_lock_startTime));
                                                    Calendar calendar5 = Calendar.getInstance(Locale.ENGLISH);
                                                    calendar5.setTimeInMillis(((long) Activity_share_lock.selected_share_lock_endTime) * 1000);
                                                    DateFormat.format("MMM dd yyyy, HH:mm", calendar5).toString();
                                                    hashMap.put("endDate", Double.valueOf(Activity_share_lock.selected_share_lock_endTime));
                                                    push.setValue(hashMap);
                                                } else {
                                                    hashMap.put("startDate", "");
                                                    hashMap.put("endDate", "");
                                                    push.setValue(hashMap);
                                                }
                                                if (Activity_share_lock.selected_share_lock_retractable) {
                                                    DatabaseReference push2 = Activity_share_lock.this.database.getReference("retractableSharingRegistry").child(MainActivity.user_uid).push();
                                                    HashMap hashMap2 = new HashMap();
                                                    hashMap2.put("allowAccess", true);
                                                    hashMap2.put("id", push2.getKey());
                                                    hashMap2.put("lockSerial", Activity_share_lock.selected_share_lock_ip45SerialNumber);
                                                    hashMap2.put("senderUID", MainActivity.user_uid);
                                                    hashMap2.put("shareAccessToken", Activity_share_lock.share_access_token_from_admin);
                                                    hashMap2.put("sharedLockName", Activity_share_lock.selected_share_lock);
                                                    hashMap2.put("sharedOn", Long.valueOf(parseLong));
                                                    hashMap2.put("sharedToEmail", Activity_share_lock.selected_share_email);
                                                    hashMap2.put("sharedToUID", key);
                                                    hashMap2.put("shareEndTime", Double.valueOf(Activity_share_lock.selected_share_lock_endTime));
                                                    hashMap2.put("shareStartTime", Double.valueOf(Activity_share_lock.selected_share_lock_startTime));
                                                    push2.setValue(hashMap2);
                                                }
                                                DatabaseReference push3 = Activity_share_lock.this.database.getReference("userLocks").child(key).push();
                                                HashMap hashMap3 = new HashMap();
                                                hashMap3.put("Name", Activity_share_lock.selected_share_lock);
                                                hashMap3.put("id", push3.getKey());
                                                hashMap3.put("Type", Integer.valueOf(Activity_share_lock.selected_share_lock_Type));
                                                hashMap3.put("Password", Activity_share_lock.this.encryptPassword(Activity_share_lock.selected_share_lock_password));
                                                hashMap3.put("ip45SerialNumber", Activity_share_lock.selected_share_lock_ip45SerialNumber);
                                                hashMap3.put("shareStartTime", Double.valueOf(Activity_share_lock.selected_share_lock_startTime));
                                                hashMap3.put("shareEndTime", Double.valueOf(Activity_share_lock.selected_share_lock_endTime));
                                                hashMap3.put("sharedBy", Activity_share_lock.selected_share_lock_sharedByEmail);
                                                hashMap3.put("ownedBy", MainActivity.user_uid);
                                                hashMap3.put("isShared", true);
                                                hashMap3.put("shareCount", 1);
                                                hashMap3.put("shareAccessToken", Activity_share_lock.share_access_token_from_admin);
                                                if (Activity_share_lock.selected_share_lock_retractable) {
                                                    hashMap3.put("isOfflineLock", false);
                                                } else {
                                                    hashMap3.put("isOfflineLock", true);
                                                }
                                                push3.setValue(hashMap3);
                                                sweetAlertDialog2.dismissWithAnimation();
                                                SweetAlertDialog sweetAlertDialog3 = new SweetAlertDialog(Activity_share_lock.this, 2);
                                                sweetAlertDialog3.setTitleText(Activity_share_lock.this.getString(R.string.done));
                                                sweetAlertDialog3.setContentText(Activity_share_lock.this.getString(R.string.successfully_share_to_ur_friend));
                                                sweetAlertDialog3.setConfirmText(Activity_share_lock.this.getString(R.string.dismiss));
                                                sweetAlertDialog3.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.13.1.1.1
                                                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                                    public void onClick(SweetAlertDialog sweetAlertDialog4) {
                                                        sweetAlertDialog4.dismissWithAnimation();
                                                    }
                                                });
                                                sweetAlertDialog3.show();
                                                return;
                                            }
                                            sweetAlertDialog2.dismissWithAnimation();
                                            SweetAlertDialog sweetAlertDialog4 = new SweetAlertDialog(Activity_share_lock.this, 1);
                                            sweetAlertDialog4.setTitleText("Duplicate Share Access");
                                            sweetAlertDialog4.setContentText("You have previously shared the same lock access.");
                                            sweetAlertDialog4.setConfirmText(Activity_share_lock.this.getString(R.string.ok));
                                            sweetAlertDialog4.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.13.1.1.2
                                                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                                public void onClick(SweetAlertDialog sweetAlertDialog5) {
                                                    sweetAlertDialog5.dismissWithAnimation();
                                                }
                                            });
                                            sweetAlertDialog4.show();
                                        }
                                    });
                                }
                            }
                            return;
                        }
                        sweetAlertDialog2.dismissWithAnimation();
                        SweetAlertDialog sweetAlertDialog3 = new SweetAlertDialog(Activity_share_lock.this, 3);
                        sweetAlertDialog3.setTitleText("No Account Registered");
                        sweetAlertDialog3.setContentText("Please make sure the user " + Activity_share_lock.selected_share_email + " has been signed up for an eGeeTouch Manager account before sharing your lock with this user.");
                        sweetAlertDialog3.setConfirmText("Help");
                        sweetAlertDialog3.setCancelText(Activity_share_lock.this.getString(R.string.cancel));
                        sweetAlertDialog3.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.13.1.2
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog4) {
                                sweetAlertDialog4.dismissWithAnimation();
                            }
                        });
                        sweetAlertDialog3.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_share_lock.13.1.3
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog4) {
                                Intent intent = new Intent("android.intent.action.VIEW");
                                intent.setData(Uri.parse("https://www.youtube.com/watch?v=Qt3wxltkDLc"));
                                Activity_share_lock.this.startActivity(intent);
                                sweetAlertDialog4.dismiss();
                            }
                        });
                        sweetAlertDialog3.show();
                        Toasty.normal(Activity_share_lock.this, Activity_share_lock.this.getString(R.string.email_not_found) + " " + Activity_share_lock.selected_share_email, Activity_share_lock.this.getResources().getDrawable(R.drawable.ic_cancel_black_24dp)).show();
                    }
                });
                return;
            }
            Toasty.normal(Activity_share_lock.this, "You can share only your own lock").show();
        }
    }

    public String encryptPassword(String str) {
        try {
            CryptLib cryptLib = new CryptLib();
            try {
                System.out.println("HEY " + cryptLib.encryptPlainTextWithRandomIV(str, "ThisIsMyKey"));
                return cryptLib.encryptPlainTextWithRandomIV(str, "ThisIsMyKey");
            } catch (Exception unused) {
                System.out.println("HEY error in encrypting");
                return "";
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            System.out.println("HEY some issue in encrypting/decrypting");
            return "";
        }
    }
}
