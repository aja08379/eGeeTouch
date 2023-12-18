package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
/* loaded from: classes.dex */
public class ArrayAdapter_addshareUser extends ArrayAdapter<String> {
    private List<String> User_email;
    private List<String> User_name;
    private final Context context;
    private Boolean[] mBooleanArray_exists;
    private String[] mStringArray_email;
    private String[] mStringArray_user;

    public ArrayAdapter_addshareUser(Context context, List<String> list, List<String> list2, List<Boolean> list3) {
        super(context, (int) R.layout.listview_adapter_addshareuser, list);
        this.context = context;
        this.User_name = list;
        this.User_email = list2;
        this.mStringArray_user = new String[list.size()];
        this.mStringArray_email = new String[list2.size()];
        this.mBooleanArray_exists = new Boolean[list3.size()];
        this.mStringArray_user = (String[]) list.toArray(this.mStringArray_user);
        this.mStringArray_email = (String[]) list2.toArray(this.mStringArray_email);
        this.mBooleanArray_exists = (Boolean[]) list3.toArray(this.mBooleanArray_exists);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        int[] iArr = {Color.parseColor("#FFFFFF"), Color.parseColor("#C9E1FC")};
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_adapter_addshareuser, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.TextView_user);
        TextView textView2 = (TextView) inflate.findViewById(R.id.TextView_user_email);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.doesnt_exist);
        inflate.setBackgroundColor(iArr[i % 2]);
        String[] strArr = this.mStringArray_user;
        if (strArr[i] != null) {
            textView.setText(strArr[i]);
        }
        String[] strArr2 = this.mStringArray_email;
        if (strArr2[i] != null) {
            textView2.setText(strArr2[i]);
        }
        if (!this.mBooleanArray_exists[i].booleanValue()) {
            imageView.setVisibility(0);
        }
        return inflate;
    }
}
