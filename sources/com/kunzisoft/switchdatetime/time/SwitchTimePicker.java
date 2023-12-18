package com.kunzisoft.switchdatetime.time;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.timepicker.TimeModel;
import com.kunzisoft.switchdatetime.R;
import com.kunzisoft.switchdatetime.Utils;
import com.kunzisoft.switchdatetime.time.RadialPickerLayout;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
/* loaded from: classes2.dex */
public class SwitchTimePicker implements RadialPickerLayout.OnValueSelectedListener {
    public static final int AM = 0;
    public static final int AMPM_INDEX = 2;
    public static final int ENABLE_PICKER_INDEX = 3;
    public static final int HOUR_INDEX = 0;
    private static final String KEY_CURRENT_ITEM_SHOWING = "current_item_showing";
    private static final String KEY_HIGHLIGHT_SELECTED_AM_PM_VIEW = "highlight_selected_AM_PM_view";
    private static final String KEY_HOUR_OF_DAY = "hour_of_day";
    private static final String KEY_IN_KB_MODE = "in_kb_mode";
    private static final String KEY_IS_24_HOUR_VIEW = "is_24_hour_view";
    private static final String KEY_MINUTE = "minute";
    private static final String KEY_TYPED_TIMES = "typed_times";
    private static final String KEY_VIBRATE = "vibrate";
    public static final int MINUTE_INDEX = 1;
    public static final int PM = 1;
    private static final int PULSE_ANIMATOR_DELAY = 300;
    private static final String TAG = "TimePickerDialog";
    private int hourOfDay;
    private boolean isSelected;
    private boolean mAllowAutoAdvance;
    private int mAmKeyCode;
    private View mAmPmHitspace;
    private TextView mAmPmTextView;
    private String mAmText;
    private Context mContext;
    private int mCurrentViewShow;
    private String mDeletedKeyFormat;
    private String mDoublePlaceholderText;
    private boolean mHighlightAMPMSelection;
    private String mHourPickerDescription;
    private TextView mHourView;
    private boolean mInKbMode;
    private boolean mIs24HourMode;
    private Node mLegalTimesTree;
    private String mMinutePickerDescription;
    private TextView mMinuteView;
    private char mPlaceholderText;
    private int mPmKeyCode;
    private String mPmText;
    private String mSelectHours;
    private String mSelectMinutes;
    private RadialPickerLayout mTimePicker;
    private ArrayList<Integer> mTypedTimes;
    private boolean mVibrate;
    private int minute;
    private View.OnClickListener onClickTimeListener;
    private OnTimeSelectedListener onTimeSelectedListener;

    /* loaded from: classes2.dex */
    public interface OnTimeSelectedListener {
        void onTimeSelected(int i, int i2);
    }

    private int getValFromKeyCode(int i) {
        switch (i) {
            case 7:
                return 0;
            case 8:
                return 1;
            case 9:
                return 2;
            case 10:
                return 3;
            case 11:
                return 4;
            case 12:
                return 5;
            case 13:
                return 6;
            case 14:
                return 7;
            case 15:
                return 8;
            case 16:
                return 9;
            default:
                return -1;
        }
    }

    public SwitchTimePicker(Context context, OnTimeSelectedListener onTimeSelectedListener) {
        this.mVibrate = true;
        this.isSelected = false;
        this.mContext = context;
        this.onTimeSelectedListener = onTimeSelectedListener;
        this.mIs24HourMode = false;
        this.mHighlightAMPMSelection = false;
        this.mInKbMode = false;
        this.mVibrate = false;
    }

    public SwitchTimePicker(Context context, OnTimeSelectedListener onTimeSelectedListener, Bundle bundle) {
        this(context, onTimeSelectedListener);
        if (bundle != null) {
            if (bundle.containsKey(KEY_HOUR_OF_DAY)) {
                this.hourOfDay = bundle.getInt(KEY_HOUR_OF_DAY);
            }
            if (bundle.containsKey(KEY_MINUTE)) {
                this.minute = bundle.getInt(KEY_MINUTE);
            }
            if (bundle.containsKey(KEY_IS_24_HOUR_VIEW)) {
                this.mIs24HourMode = bundle.getBoolean(KEY_IS_24_HOUR_VIEW);
            }
            if (bundle.containsKey(KEY_HIGHLIGHT_SELECTED_AM_PM_VIEW)) {
                this.mHighlightAMPMSelection = bundle.getBoolean(KEY_HIGHLIGHT_SELECTED_AM_PM_VIEW);
            }
            if (bundle.containsKey(KEY_CURRENT_ITEM_SHOWING)) {
                this.mCurrentViewShow = bundle.getInt(KEY_CURRENT_ITEM_SHOWING);
            }
            if (bundle.containsKey(KEY_IN_KB_MODE)) {
                this.mInKbMode = bundle.getBoolean(KEY_IN_KB_MODE);
            }
            if (bundle.containsKey(KEY_VIBRATE)) {
                this.mVibrate = bundle.getBoolean(KEY_VIBRATE);
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        RadialPickerLayout radialPickerLayout = this.mTimePicker;
        if (radialPickerLayout != null) {
            bundle.putInt(KEY_HOUR_OF_DAY, radialPickerLayout.getHours());
            bundle.putInt(KEY_MINUTE, this.mTimePicker.getMinutes());
            bundle.putBoolean(KEY_IS_24_HOUR_VIEW, this.mIs24HourMode);
            bundle.putBoolean(KEY_HIGHLIGHT_SELECTED_AM_PM_VIEW, this.mHighlightAMPMSelection);
            bundle.putInt(KEY_CURRENT_ITEM_SHOWING, this.mTimePicker.getCurrentItemShowing());
            bundle.putBoolean(KEY_IN_KB_MODE, this.mInKbMode);
            if (this.mInKbMode) {
                bundle.putIntegerArrayList(KEY_TYPED_TIMES, this.mTypedTimes);
            }
            bundle.putBoolean(KEY_VIBRATE, this.mVibrate);
        }
    }

    public void setOnTimeSetListener(OnTimeSelectedListener onTimeSelectedListener) {
        this.onTimeSelectedListener = onTimeSelectedListener;
    }

    public void setStartTime(int i, int i2) {
        this.hourOfDay = i;
        this.minute = i2;
        this.mInKbMode = false;
    }

    public void setVibrate(boolean z) {
        this.mVibrate = z;
        RadialPickerLayout radialPickerLayout = this.mTimePicker;
        if (radialPickerLayout != null) {
            radialPickerLayout.setVibrate(z);
        }
    }

    public View onCreateView(View view, Bundle bundle) {
        KeyboardListener keyboardListener = new KeyboardListener();
        view.setOnKeyListener(keyboardListener);
        Resources resources = this.mContext.getResources();
        this.mHourPickerDescription = resources.getString(R.string.hour_picker_description);
        this.mSelectHours = resources.getString(R.string.select_hours);
        this.mMinutePickerDescription = resources.getString(R.string.minute_picker_description);
        this.mSelectMinutes = resources.getString(R.string.select_minutes);
        TextView textView = (TextView) view.findViewById(R.id.hours);
        this.mHourView = textView;
        textView.setOnKeyListener(keyboardListener);
        TextView textView2 = (TextView) view.findViewById(R.id.minutes);
        this.mMinuteView = textView2;
        textView2.setOnKeyListener(keyboardListener);
        TextView textView3 = (TextView) view.findViewById(R.id.ampm_label);
        this.mAmPmTextView = textView3;
        textView3.setOnKeyListener(keyboardListener);
        if (Build.VERSION.SDK_INT <= 14) {
            this.mAmPmTextView.setTransformationMethod(new TransformationMethod(resources) { // from class: com.kunzisoft.switchdatetime.time.SwitchTimePicker.1
                private final Locale locale;
                final /* synthetic */ Resources val$resources;

                @Override // android.text.method.TransformationMethod
                public void onFocusChanged(View view2, CharSequence charSequence, boolean z, int i, Rect rect) {
                }

                {
                    this.val$resources = resources;
                    this.locale = resources.getConfiguration().locale;
                }

                @Override // android.text.method.TransformationMethod
                public CharSequence getTransformation(CharSequence charSequence, View view2) {
                    if (charSequence != null) {
                        return charSequence.toString().toUpperCase(this.locale);
                    }
                    return null;
                }
            });
        }
        String[] amPmStrings = new DateFormatSymbols().getAmPmStrings();
        this.mAmText = amPmStrings[0];
        this.mPmText = amPmStrings[1];
        RadialPickerLayout radialPickerLayout = (RadialPickerLayout) view.findViewById(R.id.time_picker);
        this.mTimePicker = radialPickerLayout;
        radialPickerLayout.setOnValueSelectedListener(this);
        this.mTimePicker.setOnKeyListener(keyboardListener);
        this.mTimePicker.initialize(this.mContext, this.hourOfDay, this.minute, this.mIs24HourMode, this.mHighlightAMPMSelection, this.mVibrate);
        this.mCurrentViewShow = 0;
        if (bundle != null && bundle.containsKey(KEY_CURRENT_ITEM_SHOWING)) {
            this.mCurrentViewShow = bundle.getInt(KEY_CURRENT_ITEM_SHOWING);
        }
        setCurrentItemShowing(this.mCurrentViewShow, false, true, true);
        this.mTimePicker.invalidate();
        this.mHourView.setOnClickListener(new View.OnClickListener() { // from class: com.kunzisoft.switchdatetime.time.SwitchTimePicker.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SwitchTimePicker.this.setCurrentItemShowing(0, true, false, true);
                SwitchTimePicker.this.mTimePicker.tryVibrate();
                if (SwitchTimePicker.this.onClickTimeListener != null) {
                    SwitchTimePicker.this.onClickTimeListener.onClick(SwitchTimePicker.this.mHourView);
                }
            }
        });
        this.mMinuteView.setOnClickListener(new View.OnClickListener() { // from class: com.kunzisoft.switchdatetime.time.SwitchTimePicker.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SwitchTimePicker.this.isSelected) {
                    SwitchTimePicker.this.setCurrentItemShowing(1, true, false, true);
                    SwitchTimePicker.this.mTimePicker.tryVibrate();
                    if (SwitchTimePicker.this.onClickTimeListener != null) {
                        SwitchTimePicker.this.onClickTimeListener.onClick(SwitchTimePicker.this.mMinuteView);
                        return;
                    }
                    return;
                }
                SwitchTimePicker.this.clickHour();
            }
        });
        this.mAmPmHitspace = view.findViewById(R.id.ampm_hitspace);
        if (this.mIs24HourMode) {
            this.mAmPmTextView.setVisibility(8);
        } else {
            this.mAmPmTextView.setVisibility(0);
            updateAmPmDisplay(this.hourOfDay < 12 ? 0 : 1);
            this.mAmPmHitspace.setOnClickListener(new View.OnClickListener() { // from class: com.kunzisoft.switchdatetime.time.SwitchTimePicker.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (SwitchTimePicker.this.onClickTimeListener != null) {
                        SwitchTimePicker.this.onClickTimeListener.onClick(view2);
                    }
                    SwitchTimePicker.this.mTimePicker.tryVibrate();
                    int isCurrentlyAmOrPm = SwitchTimePicker.this.mTimePicker.getIsCurrentlyAmOrPm();
                    if (isCurrentlyAmOrPm == 0) {
                        isCurrentlyAmOrPm = 1;
                    } else if (isCurrentlyAmOrPm == 1) {
                        isCurrentlyAmOrPm = 0;
                    }
                    SwitchTimePicker.this.updateAmPmDisplay(isCurrentlyAmOrPm);
                    SwitchTimePicker.this.mTimePicker.setAmOrPm(isCurrentlyAmOrPm);
                }
            });
        }
        this.mAllowAutoAdvance = true;
        attributeHour(this.hourOfDay, true);
        attributeMinute(this.minute);
        this.mDoublePlaceholderText = resources.getString(R.string.time_placeholder);
        this.mDeletedKeyFormat = resources.getString(R.string.deleted_key);
        this.mPlaceholderText = this.mDoublePlaceholderText.charAt(0);
        this.mPmKeyCode = -1;
        this.mAmKeyCode = -1;
        generateLegalTimesTree();
        if (this.mInKbMode) {
            if (bundle != null) {
                this.mTypedTimes = bundle.getIntegerArrayList(KEY_TYPED_TIMES);
            }
            tryStartingKbMode(-1);
            this.mHourView.invalidate();
        } else if (this.mTypedTimes == null) {
            this.mTypedTimes = new ArrayList<>();
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAmPmDisplay(int i) {
        this.hourOfDay %= 12;
        if (i == 0) {
            this.mAmPmTextView.setText(this.mAmText);
            Utils.tryAccessibilityAnnounce(this.mTimePicker, this.mAmText);
            this.mAmPmHitspace.setContentDescription(this.mAmText);
        } else if (i == 1) {
            this.mAmPmTextView.setText(this.mPmText);
            Utils.tryAccessibilityAnnounce(this.mTimePicker, this.mPmText);
            this.mAmPmHitspace.setContentDescription(this.mPmText);
            this.hourOfDay += 12;
        } else {
            this.mAmPmTextView.setText(this.mDoublePlaceholderText);
        }
        OnTimeSelectedListener onTimeSelectedListener = this.onTimeSelectedListener;
        if (onTimeSelectedListener != null) {
            onTimeSelectedListener.onTimeSelected(this.hourOfDay, this.minute);
        }
    }

    @Override // com.kunzisoft.switchdatetime.time.RadialPickerLayout.OnValueSelectedListener
    public void onValueSelected(int i, int i2, boolean z) {
        if (i == 0) {
            attributeHour(i2, false);
            String format = String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Integer.valueOf(i2));
            if (this.mAllowAutoAdvance && z) {
                setCurrentItemShowing(1, true, true, false);
                format = format + ". " + this.mSelectMinutes;
            }
            Utils.tryAccessibilityAnnounce(this.mTimePicker, format);
        } else if (i == 1) {
            attributeMinute(i2);
        } else if (i == 2) {
            updateAmPmDisplay(i2);
        } else if (i == 3) {
            if (!isTypedTimeFullyLegal()) {
                this.mTypedTimes.clear();
            }
            finishKbMode(true);
        }
        OnTimeSelectedListener onTimeSelectedListener = this.onTimeSelectedListener;
        if (onTimeSelectedListener != null) {
            onTimeSelectedListener.onTimeSelected(this.hourOfDay, this.minute);
        }
    }

    private void attributeHour(int i, boolean z) {
        this.hourOfDay = i;
        boolean z2 = this.mIs24HourMode;
        String str = TimeModel.NUMBER_FORMAT;
        if (z2) {
            str = TimeModel.ZERO_LEADING_NUMBER_FORMAT;
        } else {
            i %= 12;
            if (i == 0) {
                i = 12;
            }
        }
        String format = String.format(str, Integer.valueOf(i));
        this.mHourView.setText(format);
        if (z) {
            Utils.tryAccessibilityAnnounce(this.mTimePicker, format);
        }
    }

    private void attributeMinute(int i) {
        this.minute = i;
        if (i == 60) {
            i = 0;
        }
        String format = String.format(Locale.getDefault(), TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i));
        Utils.tryAccessibilityAnnounce(this.mTimePicker, format);
        this.mMinuteView.setText(format);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentItemShowing(int i, boolean z, boolean z2, boolean z3) {
        TextView textView;
        this.mTimePicker.setCurrentItemShowing(i, z);
        if (i == 0) {
            int hours = this.mTimePicker.getHours();
            if (!this.mIs24HourMode) {
                hours %= 12;
            }
            this.mTimePicker.setContentDescription(this.mHourPickerDescription + ": " + hours);
            if (z3) {
                Utils.tryAccessibilityAnnounce(this.mTimePicker, this.mSelectHours);
            }
            textView = this.mHourView;
        } else {
            this.mTimePicker.setContentDescription(this.mMinutePickerDescription + ": " + this.mTimePicker.getMinutes());
            if (z3) {
                Utils.tryAccessibilityAnnounce(this.mTimePicker, this.mSelectMinutes);
            }
            textView = this.mMinuteView;
        }
        ObjectAnimator pulseAnimator = Utils.getPulseAnimator(textView, 0.85f, 1.1f);
        if (z2) {
            pulseAnimator.setStartDelay(300L);
        }
        pulseAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean processKeyUp(int i) {
        String format;
        if (i != 111 && i != 4) {
            if (i == 61) {
                if (this.mInKbMode) {
                    if (isTypedTimeFullyLegal()) {
                        finishKbMode(true);
                    }
                    return true;
                }
            } else if (i == 66) {
                if (this.mInKbMode) {
                    if (!isTypedTimeFullyLegal()) {
                        return true;
                    }
                    finishKbMode(false);
                }
                OnTimeSelectedListener onTimeSelectedListener = this.onTimeSelectedListener;
                if (onTimeSelectedListener != null) {
                    onTimeSelectedListener.onTimeSelected(this.mTimePicker.getHours(), this.mTimePicker.getMinutes());
                }
                return true;
            } else if (i == 67) {
                if (this.mInKbMode && !this.mTypedTimes.isEmpty()) {
                    int deleteLastTypedKey = deleteLastTypedKey();
                    if (deleteLastTypedKey == getAmOrPmKeyCode(0)) {
                        format = this.mAmText;
                    } else if (deleteLastTypedKey == getAmOrPmKeyCode(1)) {
                        format = this.mPmText;
                    } else {
                        format = String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Integer.valueOf(getValFromKeyCode(deleteLastTypedKey)));
                    }
                    Utils.tryAccessibilityAnnounce(this.mTimePicker, String.format(this.mDeletedKeyFormat, format));
                    updateDisplay(true);
                }
            } else if (i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || (!this.mIs24HourMode && (i == getAmOrPmKeyCode(0) || i == getAmOrPmKeyCode(1)))) {
                if (!this.mInKbMode) {
                    if (this.mTimePicker == null) {
                        Log.e(TAG, "Unable to initiate keyboard mode, TimePicker was null.");
                        return true;
                    }
                    this.mTypedTimes.clear();
                    tryStartingKbMode(i);
                    return true;
                } else if (addKeyIfLegal(i)) {
                    updateDisplay(false);
                }
            }
            return false;
        }
        return true;
    }

    private void tryStartingKbMode(int i) {
        if (this.mTimePicker.trySettingInputEnabled(false)) {
            if (i == -1 || addKeyIfLegal(i)) {
                this.mInKbMode = true;
                updateDisplay(false);
            }
        }
    }

    private boolean addKeyIfLegal(int i) {
        if (!(this.mIs24HourMode && this.mTypedTimes.size() == 4) && (this.mIs24HourMode || !isTypedTimeFullyLegal())) {
            this.mTypedTimes.add(Integer.valueOf(i));
            if (!isTypedTimeLegalSoFar()) {
                deleteLastTypedKey();
                return false;
            }
            Utils.tryAccessibilityAnnounce(this.mTimePicker, String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Integer.valueOf(getValFromKeyCode(i))));
            if (isTypedTimeFullyLegal() && !this.mIs24HourMode && this.mTypedTimes.size() <= 3) {
                ArrayList<Integer> arrayList = this.mTypedTimes;
                arrayList.add(arrayList.size() - 1, 7);
                ArrayList<Integer> arrayList2 = this.mTypedTimes;
                arrayList2.add(arrayList2.size() - 1, 7);
            }
            return true;
        }
        return false;
    }

    private boolean isTypedTimeLegalSoFar() {
        Node node = this.mLegalTimesTree;
        Iterator<Integer> it = this.mTypedTimes.iterator();
        while (it.hasNext()) {
            node = node.canReach(it.next().intValue());
            if (node == null) {
                return false;
            }
        }
        return true;
    }

    private boolean isTypedTimeFullyLegal() {
        if (!this.mIs24HourMode) {
            return this.mTypedTimes.contains(Integer.valueOf(getAmOrPmKeyCode(0))) || this.mTypedTimes.contains(Integer.valueOf(getAmOrPmKeyCode(1)));
        }
        int[] enteredTime = getEnteredTime(null);
        return enteredTime[0] >= 0 && enteredTime[1] >= 0 && enteredTime[1] < 60;
    }

    private int deleteLastTypedKey() {
        ArrayList<Integer> arrayList = this.mTypedTimes;
        int intValue = arrayList.remove(arrayList.size() - 1).intValue();
        isTypedTimeFullyLegal();
        return intValue;
    }

    private void finishKbMode(boolean z) {
        this.mInKbMode = false;
        if (!this.mTypedTimes.isEmpty()) {
            int[] enteredTime = getEnteredTime(null);
            this.mTimePicker.setTime(enteredTime[0], enteredTime[1]);
            if (!this.mIs24HourMode) {
                this.mTimePicker.setAmOrPm(enteredTime[2]);
            }
            this.mTypedTimes.clear();
        }
        if (z) {
            updateDisplay(false);
            this.mTimePicker.trySettingInputEnabled(true);
        }
    }

    private void updateDisplay(boolean z) {
        if (!z && this.mTypedTimes.isEmpty()) {
            int hours = this.mTimePicker.getHours();
            int minutes = this.mTimePicker.getMinutes();
            attributeHour(hours, true);
            attributeMinute(minutes);
            if (!this.mIs24HourMode) {
                updateAmPmDisplay(hours >= 12 ? 1 : 0);
            }
            setCurrentItemShowing(this.mTimePicker.getCurrentItemShowing(), true, true, true);
            return;
        }
        Boolean[] boolArr = {false, false};
        int[] enteredTime = getEnteredTime(boolArr);
        boolean booleanValue = boolArr[0].booleanValue();
        String str = TimeModel.ZERO_LEADING_NUMBER_FORMAT;
        String str2 = booleanValue ? TimeModel.ZERO_LEADING_NUMBER_FORMAT : "%2d";
        if (!boolArr[1].booleanValue()) {
            str = "%2d";
        }
        String replace = enteredTime[0] == -1 ? this.mDoublePlaceholderText : String.format(str2, Integer.valueOf(enteredTime[0])).replace(' ', this.mPlaceholderText);
        String replace2 = enteredTime[1] == -1 ? this.mDoublePlaceholderText : String.format(str, Integer.valueOf(enteredTime[1])).replace(' ', this.mPlaceholderText);
        this.mHourView.setText(replace);
        this.mMinuteView.setText(replace2);
        if (this.mIs24HourMode) {
            return;
        }
        updateAmPmDisplay(enteredTime[2]);
    }

    private int[] getEnteredTime(Boolean[] boolArr) {
        int i;
        int i2;
        int i3 = -1;
        if (this.mIs24HourMode || !isTypedTimeFullyLegal()) {
            i = -1;
            i2 = 1;
        } else {
            ArrayList<Integer> arrayList = this.mTypedTimes;
            int intValue = arrayList.get(arrayList.size() - 1).intValue();
            if (intValue == getAmOrPmKeyCode(0)) {
                i = 0;
            } else {
                i = intValue == getAmOrPmKeyCode(1) ? 1 : -1;
            }
            i2 = 2;
        }
        int i4 = -1;
        for (int i5 = i2; i5 <= this.mTypedTimes.size(); i5++) {
            ArrayList<Integer> arrayList2 = this.mTypedTimes;
            int valFromKeyCode = getValFromKeyCode(arrayList2.get(arrayList2.size() - i5).intValue());
            if (i5 == i2) {
                i4 = valFromKeyCode;
            } else if (i5 == i2 + 1) {
                i4 += valFromKeyCode * 10;
                if (boolArr != null && valFromKeyCode == 0) {
                    boolArr[1] = true;
                }
            } else if (i5 == i2 + 2) {
                i3 = valFromKeyCode;
            } else if (i5 == i2 + 3) {
                i3 += valFromKeyCode * 10;
                if (boolArr != null && valFromKeyCode == 0) {
                    boolArr[0] = true;
                }
            }
        }
        return new int[]{i3, i4, i};
    }

    private int getAmOrPmKeyCode(int i) {
        if (this.mAmKeyCode == -1 || this.mPmKeyCode == -1) {
            KeyCharacterMap load = KeyCharacterMap.load(-1);
            int i2 = 0;
            while (true) {
                if (i2 >= Math.max(this.mAmText.length(), this.mPmText.length())) {
                    break;
                }
                char charAt = this.mAmText.toLowerCase(Locale.getDefault()).charAt(i2);
                char charAt2 = this.mPmText.toLowerCase(Locale.getDefault()).charAt(i2);
                if (charAt != charAt2) {
                    KeyEvent[] events = load.getEvents(new char[]{charAt, charAt2});
                    if (events != null && events.length == 4) {
                        this.mAmKeyCode = events[0].getKeyCode();
                        this.mPmKeyCode = events[2].getKeyCode();
                    } else {
                        Log.e(TAG, "Unable to find keycodes for AM and PM.");
                    }
                } else {
                    i2++;
                }
            }
        }
        if (i == 0) {
            return this.mAmKeyCode;
        }
        if (i == 1) {
            return this.mPmKeyCode;
        }
        return -1;
    }

    private void generateLegalTimesTree() {
        this.mLegalTimesTree = new Node(new int[0]);
        if (this.mIs24HourMode) {
            Node node = new Node(7, 8, 9, 10, 11, 12);
            Node node2 = new Node(7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
            node.addChild(node2);
            Node node3 = new Node(7, 8);
            this.mLegalTimesTree.addChild(node3);
            Node node4 = new Node(7, 8, 9, 10, 11, 12);
            node3.addChild(node4);
            node4.addChild(node);
            node4.addChild(new Node(13, 14, 15, 16));
            Node node5 = new Node(13, 14, 15, 16);
            node3.addChild(node5);
            node5.addChild(node);
            Node node6 = new Node(9);
            this.mLegalTimesTree.addChild(node6);
            Node node7 = new Node(7, 8, 9, 10);
            node6.addChild(node7);
            node7.addChild(node);
            Node node8 = new Node(11, 12);
            node6.addChild(node8);
            node8.addChild(node2);
            Node node9 = new Node(10, 11, 12, 13, 14, 15, 16);
            this.mLegalTimesTree.addChild(node9);
            node9.addChild(node);
            return;
        }
        Node node10 = new Node(getAmOrPmKeyCode(0), getAmOrPmKeyCode(1));
        Node node11 = new Node(8);
        this.mLegalTimesTree.addChild(node11);
        node11.addChild(node10);
        Node node12 = new Node(7, 8, 9);
        node11.addChild(node12);
        node12.addChild(node10);
        Node node13 = new Node(7, 8, 9, 10, 11, 12);
        node12.addChild(node13);
        node13.addChild(node10);
        Node node14 = new Node(7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        node13.addChild(node14);
        node14.addChild(node10);
        Node node15 = new Node(13, 14, 15, 16);
        node12.addChild(node15);
        node15.addChild(node10);
        Node node16 = new Node(10, 11, 12);
        node11.addChild(node16);
        Node node17 = new Node(7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        node16.addChild(node17);
        node17.addChild(node10);
        Node node18 = new Node(9, 10, 11, 12, 13, 14, 15, 16);
        this.mLegalTimesTree.addChild(node18);
        node18.addChild(node10);
        Node node19 = new Node(7, 8, 9, 10, 11, 12);
        node18.addChild(node19);
        Node node20 = new Node(7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        node19.addChild(node20);
        node20.addChild(node10);
    }

    public void setOnClickTimeListener(View.OnClickListener onClickListener) {
        this.onClickTimeListener = onClickListener;
    }

    public int getHourOfDay() {
        return this.mTimePicker.getHours();
    }

    public void setHourOfDay(int i) {
        this.hourOfDay = i;
    }

    public int getMinute() {
        return this.mTimePicker.getMinutes();
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public boolean is24HourMode() {
        return this.mIs24HourMode;
    }

    public void setIs24HourMode(boolean z) {
        this.mIs24HourMode = z;
    }

    public void setHighlightAMPMSelection(boolean z) {
        this.mHighlightAMPMSelection = z;
    }

    public void setFirstViewShow(int i) {
        this.mCurrentViewShow = i;
    }

    public void clickHour() {
        this.isSelected = true;
        setCurrentItemShowing(0, true, false, true);
        this.mTimePicker.tryVibrate();
        View.OnClickListener onClickListener = this.onClickTimeListener;
        if (onClickListener != null) {
            onClickListener.onClick(this.mHourView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class Node {
        private ArrayList<Node> mChildren = new ArrayList<>();
        private int[] mLegalKeys;

        Node(int... iArr) {
            this.mLegalKeys = iArr;
        }

        void addChild(Node node) {
            this.mChildren.add(node);
        }

        boolean containsKey(int i) {
            for (int i2 : this.mLegalKeys) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }

        Node canReach(int i) {
            ArrayList<Node> arrayList = this.mChildren;
            if (arrayList == null) {
                return null;
            }
            Iterator<Node> it = arrayList.iterator();
            while (it.hasNext()) {
                Node next = it.next();
                if (next.containsKey(i)) {
                    return next;
                }
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    private class KeyboardListener implements View.OnKeyListener {
        private KeyboardListener() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            return keyEvent.getAction() == 1 && SwitchTimePicker.this.processKeyUp(i);
        }
    }
}
