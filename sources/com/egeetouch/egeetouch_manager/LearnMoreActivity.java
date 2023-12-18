package com.egeetouch.egeetouch_manager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.IOException;
import java.util.Locale;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes.dex */
public class LearnMoreActivity extends AppCompatActivity {
    Dialog successDialog;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_learn_more);
        this.successDialog = new Dialog(this);
        FirebaseDatabase.getInstance().getReference("claimedFreeUpgrade").child(MainActivity.user_uid).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.LearnMoreActivity.1
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ((Button) LearnMoreActivity.this.findViewById(R.id.claim2)).setVisibility(8);
                } else {
                    System.out.println("Hello checking the displayDialog1 values : Doesn't exist ");
                }
            }
        });
        System.out.println("Hey im at lang " + Locale.getDefault().getLanguage());
        if (Locale.getDefault().getLanguage().equals("ja")) {
            ((ImageView) findViewById(R.id.youtubeImageView)).setTag("https://www.youtube.com/watch?v=yinljbdlvRQ");
        } else {
            ((ImageView) findViewById(R.id.youtubeImageView)).setTag("https://www.youtube.com/watch?v=YglMAgv5no0");
        }
        this.successDialog.setContentView(R.layout.dialog_success_pop_up);
        ((Button) this.successDialog.findViewById(R.id.done)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.LearnMoreActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LearnMoreActivity.this.successDialog.dismiss();
                LearnMoreActivity.this.startActivity(new Intent(LearnMoreActivity.this, MainActivity.class));
            }
        });
        this.successDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    public void btn_claim(View view) {
        if (Helper_Network.haveNetworkConnection(this)) {
            this.successDialog.show();
            String string = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("rmbMe_email", "");
            System.out.println("Hi Get user email " + string);
            sendViaMailGun("Beloved User", string);
            Firebase_Data_management.claimRedeemptionUpdateFB(string, MainActivity.user_uid);
            return;
        }
        Toast.makeText(this, "No internet Connection", 0).show();
    }

    public void openLink(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse((String) view.getTag()));
        startActivity(intent);
    }

    public void btn_back(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }

    private void sendViaMailGun(String str, String str2) {
        String str3;
        if (Locale.getDefault().getLanguage().equals("ja")) {
            System.out.println("Hey im at lang " + Locale.getDefault().getLanguage());
            str3 = "email_blast_success_jp";
        } else {
            str3 = "email_blast_success";
        }
        System.out.println("Hello checking the mailGun variables:{}");
        RetrofitClient.getInstance().getApi().sendEmail("The eGeeTouch Team <noreply@egeetouch.com>", str2, "Here's Your eGeeTouch FREE Redemption Code", "", str3, "{}").enqueue(new Callback<ResponseBody>() { // from class: com.egeetouch.egeetouch_manager.LearnMoreActivity.3
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("Hello checking the mailGun Response:" + response);
                if (response.code() == 200) {
                    try {
                        System.out.println("Hello checking the mailGun Response 1:" + response);
                        new JSONObject(response.body().string());
                        System.out.println("Redemption Code Sent");
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                System.out.println("Redemption Code Sent");
            }
        });
    }
}
