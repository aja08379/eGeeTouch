package com.egeetouch.egeetouch_manager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.FacebookSdk;
/* loaded from: classes.dex */
public class AppRater {
    private static final String APP_PNAME = "com.egeetouch.egeetouch_manager";
    private static final String APP_TITLE = "eGeeTouch Manager";
    private static final int DAYS_UNTIL_PROMPT = 3;
    private static int LAUNCHES_UNTIL_PROMPT = 7;

    public static void app_launched(Context context, int i) {
        PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_rater", 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (sharedPreferences.getBoolean("dontshowagain", false)) {
            return;
        }
        edit.putLong("launch_count", sharedPreferences.getLong("launch_count", 0L) + 1);
        if (Long.valueOf(sharedPreferences.getLong("date_firstlaunch", 0L)).longValue() == 0) {
            edit.putLong("date_firstlaunch", Long.valueOf(System.currentTimeMillis()).longValue());
        }
        edit.commit();
    }

    public static void showRateDialog(final Context context, final SharedPreferences.Editor editor, final int i) {
        final Dialog dialog = new Dialog(context);
        new Dialog(context);
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
        dialog.setContentView(R.layout.app_rating_pop_up);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.show();
        final LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_review);
        Button button = (Button) dialog.findViewById(R.id.button_submit);
        final EditText editText = (EditText) dialog.findViewById(R.id.ed_review);
        final RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
        ((TextView) dialog.findViewById(R.id.titleTv)).setText("Thanks for your feedback.");
        ((Button) dialog.findViewById(R.id.button_review)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.AppRater.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                linearLayout.setVisibility(8);
                editText.setVisibility(0);
            }
        });
        ((Button) dialog.findViewById(R.id.button_notNow)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.AppRater.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                editor.putLong("launch_count", 0L);
                editor.putBoolean("dontshowagain", false);
                editor.commit();
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(R.id.button_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.AppRater.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharedPreferences.Editor editor2 = editor;
                if (editor2 != null) {
                    editor2.putBoolean("dontshowagain", false);
                    editor.putLong("launch_count", 3L);
                    editor.commit();
                }
                dialog.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.AppRater.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str = "Rating " + ratingBar.getRating();
                if (ratingBar.getRating() > 0.0f) {
                    String str2 = "";
                    if (ratingBar.getRating() >= 3.0f) {
                        EditText editText2 = editText;
                        if (editText2 != null && !editText2.getText().toString().equals("")) {
                            str2 = editText.getText().toString();
                        }
                        Firebase_Data_management.sendAppRatingReview(i, Float.valueOf(ratingBar.getRating()), str2, MainActivity.email, MainActivity.user_uid, defaultSharedPreferences.getString("currentVersionName", "N.A"));
                        Toast.makeText(context, "Please Rate us in Google Play Store", 1).show();
                        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.egeetouch.egeetouch_manager")));
                        dialog.dismiss();
                        SharedPreferences.Editor editor2 = editor;
                        if (editor2 != null) {
                            editor2.putBoolean("dontshowagain", true);
                            editor.commit();
                            return;
                        }
                        return;
                    }
                    SharedPreferences.Editor editor3 = editor;
                    if (editor3 != null) {
                        editor3.putBoolean("dontshowagain", true);
                        editor.commit();
                    }
                    EditText editText3 = editText;
                    if (editText3 != null && !editText3.getText().toString().equals("")) {
                        String obj = editText.getText().toString();
                        dialog.dismiss();
                        Toast.makeText(context, str, 0).show();
                        Firebase_Data_management.sendAppRatingReview(i, Float.valueOf(ratingBar.getRating()), obj, MainActivity.email, MainActivity.user_uid, defaultSharedPreferences.getString("currentVersionName", "Not Found"));
                        return;
                    }
                    linearLayout.setVisibility(8);
                    editText.setVisibility(0);
                    editText.setError("Please mention the issue!");
                    return;
                }
                Toast.makeText(context, "Please Rate us !", 1).show();
            }
        });
    }
}
