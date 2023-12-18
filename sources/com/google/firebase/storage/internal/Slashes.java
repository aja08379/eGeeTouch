package com.google.firebase.storage.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes2.dex */
public class Slashes {
    public static String preserveSlashEncode(String str) {
        return TextUtils.isEmpty(str) ? "" : slashize(Uri.encode(str));
    }

    public static String slashize(String str) {
        Preconditions.checkNotNull(str);
        return str.replace("%2F", "/");
    }

    public static String normalizeSlashes(String str) {
        String[] split;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.startsWith("/") || str.endsWith("/") || str.contains("//")) {
            StringBuilder sb = new StringBuilder();
            for (String str2 : str.split("/", -1)) {
                if (!TextUtils.isEmpty(str2)) {
                    if (sb.length() > 0) {
                        sb.append("/").append(str2);
                    } else {
                        sb.append(str2);
                    }
                }
            }
            return sb.toString();
        }
        return str;
    }
}
