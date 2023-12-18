package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/* loaded from: classes.dex */
public class NewsAdapter extends BaseAdapter {
    Context context;
    String[] desc;
    LayoutInflater inflater;
    String[] news;
    Integer[] newsPic;

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public NewsAdapter(Context context, String[] strArr, String[] strArr2, Integer[] numArr) {
        this.context = context;
        this.news = strArr;
        this.desc = strArr2;
        this.newsPic = numArr;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.news.length;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = this.inflater.inflate(R.layout.listview_news, (ViewGroup) null);
        ((ImageView) inflate.findViewById(R.id.imgView)).setImageResource(this.newsPic[i].intValue());
        ((TextView) inflate.findViewById(R.id.judul)).setText(this.news[i]);
        ((TextView) inflate.findViewById(R.id.description)).setText(this.desc[i]);
        return inflate;
    }
}
