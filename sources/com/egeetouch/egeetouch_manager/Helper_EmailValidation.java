package com.egeetouch.egeetouch_manager;

import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class Helper_EmailValidation {
    public static boolean validate_password(String str) {
        return Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})").matcher(str).matches();
    }

    public static boolean isEmailValid(String str) {
        return Pattern.compile("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", 2).matcher(str).matches();
    }
}
