package com.egeetouch.egeetouch_manager;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.firebase.auth.FirebaseAuth;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
/* loaded from: classes.dex */
public class login_page extends Activity {
    public static String current_logon_email = "";
    public static String current_logon_name = "";
    public static int langauges_change = 1;
    private static MyPagerAdapter myPagerAdapter;
    private static ViewPager viewPager;
    private AlphaAnimation animation1;
    private ImageView iv_banner;
    public FirebaseAuth.AuthStateListener mAuthListener;
    int photo_position = 0;
    private Handler handler = new Handler();
    private Runnable runnable_slide_photo = new Runnable() { // from class: com.egeetouch.egeetouch_manager.login_page.1
        @Override // java.lang.Runnable
        public void run() {
            if (login_page.this.photo_position >= 2) {
                login_page.this.photo_position = 0;
            } else {
                login_page.this.photo_position++;
            }
            login_page.viewPager.setCurrentItem(login_page.this.photo_position, true);
            login_page.this.handler.postDelayed(login_page.this.runnable_slide_photo, DfuServiceInitiator.DEFAULT_SCAN_TIMEOUT);
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("keep_access", false)) {
            startActivityForResult(new Intent(this, MainActivity.class), 3);
        }
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.login_page2);
        try {
            ((TextView) findViewById(R.id.textView_version2)).setText(ExifInterface.GPS_MEASUREMENT_INTERRUPTED + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        banner_amination();
        setup_photo_slider();
        this.handler.postDelayed(this.runnable_slide_photo, DfuServiceInitiator.DEFAULT_SCAN_TIMEOUT);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        banner_amination();
        setup_photo_slider();
        this.handler.postDelayed(this.runnable_slide_photo, DfuServiceInitiator.DEFAULT_SCAN_TIMEOUT);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.iv_banner.clearAnimation();
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable_slide_photo);
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.iv_banner.clearAnimation();
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable_slide_photo);
        }
        if (this.mAuthListener != null) {
            login_page_gmail.mAuth.removeAuthStateListener(this.mAuthListener);
        }
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        login_page_gmail.mAuth.addAuthStateListener(this.mAuthListener);
    }

    public void btn_gmail(View view) {
        startActivityForResult(new Intent(this, Login_gmail.class), 4);
    }

    public void btn_login(View view) {
        startActivityForResult(new Intent(this, Login.class), 1);
    }

    public void btn_signup(View view) {
        startActivityForResult(new Intent(this, Signup.class), 2);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            if (i2 == 16) {
                startActivityForResult(new Intent(this, Signup.class), 2);
            } else if (i2 == 32) {
                startActivityForResult(new Intent(this, MainActivity.class), 3);
            }
        } else if (i == 2) {
            if (i2 == -1) {
                startActivityForResult(new Intent(this, Login.class), 1);
            }
        } else if (i == 3 && i2 == -1) {
            finish();
        }
    }

    private void banner_amination() {
        this.iv_banner = (ImageView) findViewById(R.id.imageView_banner);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.75f, 1.0f);
        this.animation1 = alphaAnimation;
        alphaAnimation.setDuration(1000L);
        this.animation1.setRepeatMode(2);
        this.animation1.setRepeatCount(-1);
        this.iv_banner.startAnimation(this.animation1);
    }

    private void setup_photo_slider() {
        myPagerAdapter = new MyPagerAdapter();
        ViewPager viewPager2 = (ViewPager) findViewById(R.id.myviewpager);
        viewPager = viewPager2;
        viewPager2.setAdapter(myPagerAdapter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class MyPagerAdapter extends PagerAdapter {
        int NumberOfPages = 3;
        int[] res = {R.drawable.ss3, R.drawable.ss2, R.drawable.ss1};
        int[] backgroundcolor = {-1, -1, -1, -1};

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        MyPagerAdapter() {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.NumberOfPages;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            ImageView imageView = new ImageView(login_page.this);
            imageView.setImageResource(this.res[i]);
            imageView.setLayoutParams(new ActionBar.LayoutParams(-2, -2));
            LinearLayout linearLayout = new LinearLayout(login_page.this);
            linearLayout.setOrientation(1);
            ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(-1, -1);
            linearLayout.setBackgroundColor(this.backgroundcolor[i]);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.addView(imageView);
            viewGroup.addView(linearLayout);
            return linearLayout;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((LinearLayout) obj);
        }
    }
}
