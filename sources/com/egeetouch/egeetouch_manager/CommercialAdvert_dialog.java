package com.egeetouch.egeetouch_manager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Locale;
/* loaded from: classes.dex */
public class CommercialAdvert_dialog {
    public static Button btnMoreDetails;
    public static Dialog egeetouchCommercialDialog;
    public static ImageView imgCloseButton;
    public static TextView tv_default;
    public static TextView tv_default2;
    public static TextView tv_pop_up_subTitle;
    public static TextView tv_pop_up_title;

    public static void ShowDialog(final Context context, final String str) {
        Dialog dialog = new Dialog(context);
        egeetouchCommercialDialog = dialog;
        dialog.setContentView(R.layout.egeetouch_commercial_adv_pop_up);
        Dialog dialog2 = egeetouchCommercialDialog;
        if (dialog2 != null && dialog2.isShowing()) {
            egeetouchCommercialDialog.dismiss();
        }
        btnMoreDetails = (Button) egeetouchCommercialDialog.findViewById(R.id.button_moreDetails);
        tv_pop_up_title = (TextView) egeetouchCommercialDialog.findViewById(R.id.pop_up_titleTV);
        tv_pop_up_subTitle = (TextView) egeetouchCommercialDialog.findViewById(R.id.pop_up_sub_titleTV);
        imgCloseButton = (ImageView) egeetouchCommercialDialog.findViewById(R.id.imgage_closePopUp);
        tv_default = (TextView) egeetouchCommercialDialog.findViewById(R.id.default2);
        tv_default2 = (TextView) egeetouchCommercialDialog.findViewById(R.id.defaultTV);
        egeetouchCommercialDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -947840688:
                if (str.equals("lockLimit")) {
                    c = 0;
                    break;
                }
                break;
            case -781693887:
                if (str.equals("tagLimit")) {
                    c = 1;
                    break;
                }
                break;
            case -348171998:
                if (str.equals("recipientLimit")) {
                    c = 2;
                    break;
                }
                break;
            case 110119261:
                if (str.equals("tagAd")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                tv_pop_up_title.setText(R.string.egee_pop_up_lock_title);
                tv_pop_up_subTitle.setText(R.string.egee_pop_up_lock_sub_title);
                break;
            case 1:
                tv_pop_up_title.setText(R.string.egee_pop_up_tag_title);
                tv_pop_up_subTitle.setText(R.string.egee_pop_up_tag_sub_title);
                break;
            case 2:
                tv_pop_up_title.setText(R.string.egee_pop_up_recipient_title);
                tv_pop_up_subTitle.setText(R.string.egee_pop_up_recipient_sub_title);
                break;
            case 3:
                tv_pop_up_title.setVisibility(8);
                tv_pop_up_subTitle.setText(R.string.egee_pop_up_tag_sub_title);
                break;
        }
        if (Locale.getDefault().getLanguage().equals("ja")) {
            tv_default2.setVisibility(8);
            tv_default.setVisibility(8);
        } else {
            tv_default2.setVisibility(0);
            tv_default.setVisibility(0);
        }
        imgCloseButton.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.CommercialAdvert_dialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommercialAdvert_dialog.egeetouchCommercialDialog.dismiss();
            }
        });
        btnMoreDetails.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.CommercialAdvert_dialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                if (Locale.getDefault().getLanguage().equals("ja")) {
                    intent.setData(Uri.parse("https://www.egeetouch.com/jp/products/IAM-software"));
                } else {
                    intent.setData(Uri.parse("https://iam.egeetouch.com/package"));
                }
                context.startActivity(intent);
            }
        });
        egeetouchCommercialDialog.show();
    }
}
