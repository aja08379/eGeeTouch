package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.AppEventsConstants;
import java.util.List;
/* loaded from: classes.dex */
public class ArrayAdapter_ManagePasscode extends ArrayAdapter<String> {
    private final Context context;
    public List<String> data_code;
    private SparseBooleanArray mSelectedItemsIds;
    private String[] mStringArray_code;
    private String[] mStringArray_emoji;
    private Integer[] mStringArray_index;
    private String[] mStringArray_name;
    int[] slots;

    public ArrayAdapter_ManagePasscode(Context context, List<String> list, List<String> list2, List<Integer> list3, List<String> list4) {
        super(context, (int) R.layout.fragment_manage_passcode, list2);
        this.slots = new int[]{R.id.passcode1, R.id.passcode2, R.id.passcode3, R.id.passcode4, R.id.passcode5, R.id.passcode6};
        this.context = context;
        this.data_code = list2;
        this.mStringArray_code = new String[list2.size()];
        this.mStringArray_emoji = new String[list.size()];
        this.mStringArray_name = new String[list4.size()];
        this.mStringArray_index = new Integer[list3.size()];
        this.mStringArray_code = (String[]) this.data_code.toArray(this.mStringArray_code);
        this.mStringArray_emoji = (String[]) list.toArray(this.mStringArray_emoji);
        this.mStringArray_name = (String[]) list4.toArray(this.mStringArray_name);
        this.mStringArray_index = (Integer[]) list3.toArray(this.mStringArray_index);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        int[] iArr = {Color.parseColor("#FFFFFF"), Color.parseColor("#C9E1FC")};
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_adapter_managepasscode, viewGroup, false);
        String.valueOf(i + 1);
        inflate.setBackgroundColor(iArr[i % 2]);
        ((TextView) inflate.findViewById(R.id.textView_passcodeName)).setText(this.mStringArray_name[i]);
        ((TextView) inflate.findViewById(R.id.textView_passcode)).setText(convertToEmoji(this.mStringArray_code[i]));
        loadArrows(this.mStringArray_code[i], inflate);
        return inflate;
    }

    private String convertToEmoji(String str) {
        String str2 = "";
        String str3 = "";
        for (int intValue = Integer.valueOf(str).intValue(); intValue != 0; intValue /= 10) {
            str3 = str3 + getEmojiByUnicode(intValue % 10);
        }
        for (int length = str3.length() - 1; length >= 0; length--) {
            str2 = str2 + str3.charAt(length);
        }
        return str2;
    }

    private String getEmojiByUnicode(int i) {
        return new String(Character.toChars(i != 1 ? i != 2 ? i != 3 ? i != 4 ? 0 : 10145 : 11015 : 11013 : 11014));
    }

    private void loadArrows(String str, View view) {
        for (int i = 0; i < str.length(); i++) {
            try {
                String ch = Character.toString(str.charAt(i));
                char c = 65535;
                switch (ch.hashCode()) {
                    case 49:
                        if (ch.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 50:
                        if (ch.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 51:
                        if (ch.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                            c = 2;
                            break;
                        }
                        break;
                    case 52:
                        if (ch.equals("4")) {
                            c = 3;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    ((ImageView) view.findViewById(this.slots[i])).setImageResource(R.drawable.arrow_up);
                } else if (c == 1) {
                    ((ImageView) view.findViewById(this.slots[i])).setImageResource(R.drawable.arrow_left);
                } else if (c == 2) {
                    ((ImageView) view.findViewById(this.slots[i])).setImageResource(R.drawable.arrow_down);
                } else if (c == 3) {
                    ((ImageView) view.findViewById(this.slots[i])).setImageResource(R.drawable.arrow_right);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ((LinearLayout) view.findViewById(R.id.slots)).setVisibility(0);
        ((TextView) view.findViewById(R.id.textView_passcode)).setVisibility(8);
    }
}
