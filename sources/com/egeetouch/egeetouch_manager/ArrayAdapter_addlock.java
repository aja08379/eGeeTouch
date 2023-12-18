package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import java.util.List;
/* loaded from: classes.dex */
public class ArrayAdapter_addlock extends ArrayAdapter<String> {
    private final Context context;
    private List<String> data;
    private String[] mStringArray;

    public ArrayAdapter_addlock(Context context, List<String> list) {
        super(context, (int) R.layout.listview_adapter_addlock, list);
        this.context = context;
        this.data = list;
        String[] strArr = new String[list.size()];
        this.mStringArray = strArr;
        this.mStringArray = (String[]) this.data.toArray(strArr);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        int[] iArr = {Color.parseColor("#FFFFFF"), Color.parseColor("#C9E1FC")};
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_adapter_addlock, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.ImageView_lock_model);
        TextView textView = (TextView) inflate.findViewById(R.id.TextView_locktype);
        inflate.setBackgroundColor(iArr[i % 2]);
        if (i == 0) {
            imageView.setImageResource(R.drawable.anticut);
        } else if (i == 1) {
            imageView.setImageResource(R.drawable.gen5);
        } else if (i == 2) {
            imageView.setImageResource(R.drawable.loto);
        } else if (i == 3) {
            imageView.setImageResource(R.drawable.ip45_4th_gen_locks);
        } else if (i == 4) {
            imageView.setImageResource(R.drawable.tsa);
        } else if (i == 5) {
            imageView.setImageResource(R.drawable.gen2_2);
        } else if (i == 6) {
            imageView.setImageResource(R.drawable.lug1);
        } else if (i == 7) {
            imageView.setImageResource(R.drawable.lug2);
        } else if (i == 8) {
            imageView.setImageResource(R.drawable.gen2);
        }
        textView.setText(this.mStringArray[i]);
        return inflate;
    }

    /* loaded from: classes.dex */
    public static class FirebaseInstanceidService extends FirebaseInstanceIdService {
        private static final String TAG = "MyAndroidFCMIIDService";

        private void sendRegistrationToServer(String str) {
        }

        @Override // com.google.firebase.iid.FirebaseInstanceIdService
        public void onTokenRefresh() {
            Log.d(TAG, "Refreshed token: " + FirebaseInstanceId.getInstance().getToken());
        }
    }
}
