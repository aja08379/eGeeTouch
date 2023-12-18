package com.egeetouch.egeetouch_manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import com.appeaser.sublimepickerlibrary.SublimePicker;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeListenerAdapter;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes.dex */
public class SublimePickerFragment extends DialogFragment {
    Callback mCallback;
    SublimePicker mSublimePicker;
    DateFormat mTimeFormatter;
    SublimeListenerAdapter mListener = new SublimeListenerAdapter() { // from class: com.egeetouch.egeetouch_manager.SublimePickerFragment.1
        @Override // com.appeaser.sublimepickerlibrary.helpers.SublimeListenerAdapter
        public void onCancelled() {
            if (SublimePickerFragment.this.mCallback != null) {
                SublimePickerFragment.this.mCallback.onCancelled();
            }
            SublimePickerFragment.this.dismiss();
        }

        @Override // com.appeaser.sublimepickerlibrary.helpers.SublimeListenerAdapter
        public void onDateTimeRecurrenceSet(SublimePicker sublimePicker, SelectedDate selectedDate, int i, int i2, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String str) {
            if (SublimePickerFragment.this.mCallback != null) {
                SublimePickerFragment.this.mCallback.onDateTimeRecurrenceSet(selectedDate, i, i2, recurrenceOption, str);
            }
            SublimePickerFragment.this.dismiss();
        }
    };
    DateFormat mDateFormatter = DateFormat.getDateInstance(2, Locale.getDefault());

    /* loaded from: classes.dex */
    public interface Callback {
        void onCancelled();

        void onDateTimeRecurrenceSet(SelectedDate selectedDate, int i, int i2, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String str);
    }

    public SublimePickerFragment() {
        DateFormat timeInstance = DateFormat.getTimeInstance(3, Locale.getDefault());
        this.mTimeFormatter = timeInstance;
        timeInstance.setTimeZone(TimeZone.getTimeZone("GMT+0"));
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mSublimePicker = (SublimePicker) getActivity().getLayoutInflater().inflate(R.layout.sublime_picker, viewGroup);
        Bundle arguments = getArguments();
        this.mSublimePicker.initializePicker(arguments != null ? (SublimeOptions) arguments.getParcelable("SUBLIME_OPTIONS") : null, this.mListener);
        return this.mSublimePicker;
    }
}
