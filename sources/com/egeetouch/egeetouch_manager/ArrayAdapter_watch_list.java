package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.work.WorkRequest;
/* loaded from: classes.dex */
public class ArrayAdapter_watch_list extends BaseAdapter {
    private final Context context;
    private String[] mWatchName;
    private long[] mWatchTime;
    private String[] mWatchUuid;

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public ArrayAdapter_watch_list(Context context, String[] strArr, String[] strArr2, long[] jArr) {
        this.context = context;
        this.mWatchName = strArr;
        this.mWatchUuid = strArr2;
        this.mWatchTime = jArr;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mWatchUuid.length;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.watch_list_item, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.watch_name);
        TextView textView2 = (TextView) inflate.findViewById(R.id.watch_uuid);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.watch);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.status_circle);
        textView.setText(this.mWatchName[i]);
        textView2.setText(this.mWatchUuid[i]);
        if (System.currentTimeMillis() < this.mWatchTime[i] + WorkRequest.MIN_BACKOFF_MILLIS) {
            imageView2.setImageResource(R.drawable.ic_circle_green);
            textView.setAlpha(1.0f);
            textView2.setAlpha(1.0f);
            imageView.setAlpha(1.0f);
        } else {
            imageView2.setImageResource(R.drawable.ic_circle_red);
            textView.setAlpha(0.4f);
            textView2.setAlpha(0.4f);
            imageView.setAlpha(0.4f);
        }
        return inflate;
    }
}
