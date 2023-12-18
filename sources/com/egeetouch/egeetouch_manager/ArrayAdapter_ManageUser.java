package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
/* loaded from: classes.dex */
public class ArrayAdapter_ManageUser extends ArrayAdapter<String> {
    private final Context context;
    private List<String> data;
    private String[] mStringArray;

    public ArrayAdapter_ManageUser(Context context, List<String> list) {
        super(context, (int) R.layout.listview_adapter_managetag, list);
        this.context = context;
        this.data = list;
        String[] strArr = new String[list.size()];
        this.mStringArray = strArr;
        this.mStringArray = (String[]) this.data.toArray(strArr);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        int[] iArr = {Color.parseColor("#FFFFFF"), Color.parseColor("#C9E1FC")};
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_adapter_manageuser, viewGroup, false);
        inflate.setBackgroundColor(iArr[i % 2]);
        ((TextView) inflate.findViewById(R.id.TextView_username)).setText(this.mStringArray[i]);
        return inflate;
    }
}
