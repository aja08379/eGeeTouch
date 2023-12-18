package com.egeetouch.egeetouch_manager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
/* loaded from: classes.dex */
public class Popup_ads extends Dialog {
    private FirebaseRemoteConfig config;
    private Context ctx;

    public Popup_ads(Context context, FirebaseRemoteConfig firebaseRemoteConfig) {
        super(context);
        this.ctx = context;
        this.config = firebaseRemoteConfig;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.pop_up_ads);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        final FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        final ImageView imageView = (ImageView) findViewById(R.id.imgpopup);
        final ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.imgLoad);
        System.out.println("TESTING IMAGE   " + firebaseRemoteConfig.getString(RemotePopUp.IMAGEPOPUP));
        SharedPreferences sharedPreferences = this.ctx.getApplicationContext().getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        TextView textView = (TextView) findViewById(R.id.dismiss_text);
        textView.setAlpha(0.5f);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Popup_ads.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                edit.putBoolean("dismiss_permission", true);
                edit.apply();
                Popup_ads.this.dismiss();
            }
        });
        ((ImageView) findViewById(R.id.close_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Popup_ads.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Popup_ads.this.dismiss();
            }
        });
        imageView.setVisibility(8);
        shimmerFrameLayout.setVisibility(0);
        Picasso.get().load(firebaseRemoteConfig.getString(RemotePopUp.IMAGEPOPUP)).into(imageView, new Callback() { // from class: com.egeetouch.egeetouch_manager.Popup_ads.3
            @Override // com.squareup.picasso.Callback
            public void onError(Exception exc) {
            }

            @Override // com.squareup.picasso.Callback
            public void onSuccess() {
                shimmerFrameLayout.setVisibility(8);
                imageView.setVisibility(0);
            }
        });
        System.out.println("time :" + System.currentTimeMillis());
        System.out.println("time :" + sharedPreferences.getLong("time", 0L));
        edit.putLong("time", System.currentTimeMillis());
        edit.apply();
        new Handler().post(new Runnable() { // from class: com.egeetouch.egeetouch_manager.Popup_ads.4
            @Override // java.lang.Runnable
            public void run() {
                Popup_ads.this.show();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Popup_ads.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String string = firebaseRemoteConfig.getString(RemotePopUp.LINKIN);
                System.out.println(string);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(string));
                Popup_ads.this.ctx.startActivity(intent);
                Popup_ads.this.dismiss();
            }
        });
    }
}
