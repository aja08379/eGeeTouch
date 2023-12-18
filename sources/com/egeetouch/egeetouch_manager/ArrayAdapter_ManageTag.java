package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
/* loaded from: classes.dex */
public class ArrayAdapter_ManageTag extends ArrayAdapter<String> {
    private final Context context;
    private List<String> data;
    private List<String> data_id;
    private List<String> data_origin;
    private String[] mStringArray;

    public ArrayAdapter_ManageTag(Context context, List<String> list, List<String> list2, List<String> list3) {
        super(context, (int) R.layout.fragment_manage_tags, list);
        this.context = context;
        this.data = list;
        this.data_id = list2;
        this.data_origin = list3;
        String[] strArr = new String[list.size()];
        this.mStringArray = strArr;
        this.mStringArray = (String[]) this.data.toArray(strArr);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2;
        int[] iArr = {Color.parseColor("#FFFFFF"), Color.parseColor("#C9E1FC")};
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_adapter_managetag, viewGroup, false);
        inflate.setBackgroundColor(iArr[i % 2]);
        String.valueOf(i + 1);
        TextView textView = (TextView) inflate.findViewById(R.id.textView_tagname_new);
        TextView textView2 = (TextView) inflate.findViewById(R.id.textView_tag_uid);
        TextView textView3 = (TextView) inflate.findViewById(R.id.textView_tag_index1);
        ((TextView) inflate.findViewById(R.id.textView_tag_index)).setText(this.context.getString(R.string.tag) + " " + String.valueOf(i2) + " : ");
        try {
            textView.setText(this.mStringArray[i]);
        } catch (Exception unused) {
            textView.setText(this.context.getResources().getString(R.string.error));
        }
        try {
            if (this.data_origin.get(i).equals("cloud")) {
                Cursor rawQuery = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_tag_UID_retriving(this.mStringArray[i]), null);
                while (rawQuery.moveToNext()) {
                    textView2.setText(rawQuery.getString(1));
                }
                rawQuery.close();
            } else if (this.data_origin.get(i).equals("industrial")) {
                textView2.setText(this.data_id.get(i));
                inflate.setBackgroundColor(Color.parseColor("#FFE8C2"));
            }
        } catch (Exception unused2) {
            textView2.setText(this.context.getResources().getString(R.string.error));
        }
        return inflate;
    }
}
