package com.egeetouch.egeetouch_manager;
/* loaded from: classes.dex */
public class Helper_Firebase {
    public static String EncodeString(String str) {
        return str.replace(".", ",");
    }

    public static String DecodeString(String str) {
        return str.replace(",", ".");
    }
}
