package com.facebook.appevents.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.facebook.FacebookSdk;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
class InAppPurchaseEventManager {
    private static final String AS_INTERFACE = "asInterface";
    private static final int CACHE_CLEAR_TIME_LIMIT_SEC = 604800;
    private static final String DETAILS_LIST = "DETAILS_LIST";
    private static final String GET_PURCHASES = "getPurchases";
    private static final String GET_PURCHASE_HISTORY = "getPurchaseHistory";
    private static final String GET_SKU_DETAILS = "getSkuDetails";
    private static final String INAPP = "inapp";
    private static final String INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
    private static final String INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
    private static final String IN_APP_BILLING_SERVICE = "com.android.vending.billing.IInAppBillingService";
    private static final String IN_APP_BILLING_SERVICE_STUB = "com.android.vending.billing.IInAppBillingService$Stub";
    private static final String IS_BILLING_SUPPORTED = "isBillingSupported";
    private static final String ITEM_ID_LIST = "ITEM_ID_LIST";
    private static final String LAST_CLEARED_TIME = "LAST_CLEARED_TIME";
    private static final String LAST_LOGGED_TIME_SEC = "LAST_LOGGED_TIME_SEC";
    private static final int MAX_QUERY_PURCHASE_NUM = 30;
    private static final int PURCHASE_EXPIRE_TIME_SEC = 43200;
    private static final int PURCHASE_STOP_QUERY_TIME_SEC = 1200;
    private static final String RESPONSE_CODE = "RESPONSE_CODE";
    private static final int SKU_DETAIL_EXPIRE_TIME_SEC = 43200;
    private static final String SUBSCRIPTION = "subs";
    private static final long SUBSCRIPTION_HARTBEAT_INTERVAL = 86400;
    private static final String TAG = "com.facebook.appevents.internal.InAppPurchaseEventManager";
    private static final HashMap<String, Method> methodMap = new HashMap<>();
    private static final HashMap<String, Class<?>> classMap = new HashMap<>();
    private static final String PACKAGE_NAME = FacebookSdk.getApplicationContext().getPackageName();
    private static final String SKU_DETAILS_STORE = "com.facebook.internal.SKU_DETAILS";
    private static final SharedPreferences skuDetailSharedPrefs = FacebookSdk.getApplicationContext().getSharedPreferences(SKU_DETAILS_STORE, 0);
    private static final String PURCHASE_INAPP_STORE = "com.facebook.internal.PURCHASE";
    private static final SharedPreferences purchaseInappSharedPrefs = FacebookSdk.getApplicationContext().getSharedPreferences(PURCHASE_INAPP_STORE, 0);
    private static final String PURCHASE_SUBS_STORE = "com.facebook.internal.PURCHASE_SUBS";
    private static final SharedPreferences purchaseSubsSharedPrefs = FacebookSdk.getApplicationContext().getSharedPreferences(PURCHASE_SUBS_STORE, 0);

    InAppPurchaseEventManager() {
    }

    public static Object asInterface(Context context, IBinder iBinder) {
        return invokeMethod(context, IN_APP_BILLING_SERVICE_STUB, AS_INTERFACE, null, new Object[]{iBinder});
    }

    public static Map<String, String> getSkuDetails(Context context, ArrayList<String> arrayList, Object obj, boolean z) {
        Map<String, String> readSkuDetailsFromCache = readSkuDetailsFromCache(arrayList);
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!readSkuDetailsFromCache.containsKey(next)) {
                arrayList2.add(next);
            }
        }
        readSkuDetailsFromCache.putAll(getSkuDetailsFromGoogle(context, arrayList2, obj, z));
        return readSkuDetailsFromCache;
    }

    private static Map<String, String> getSkuDetailsFromGoogle(Context context, ArrayList<String> arrayList, Object obj, boolean z) {
        HashMap hashMap = new HashMap();
        if (obj != null && !arrayList.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(ITEM_ID_LIST, arrayList);
            Object[] objArr = new Object[4];
            objArr[0] = 3;
            objArr[1] = PACKAGE_NAME;
            objArr[2] = z ? SUBSCRIPTION : INAPP;
            objArr[3] = bundle;
            Object invokeMethod = invokeMethod(context, IN_APP_BILLING_SERVICE, GET_SKU_DETAILS, obj, objArr);
            if (invokeMethod != null) {
                Bundle bundle2 = (Bundle) invokeMethod;
                if (bundle2.getInt(RESPONSE_CODE) == 0) {
                    ArrayList<String> stringArrayList = bundle2.getStringArrayList(DETAILS_LIST);
                    if (stringArrayList != null && arrayList.size() == stringArrayList.size()) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            hashMap.put(arrayList.get(i), stringArrayList.get(i));
                        }
                    }
                    writeSkuDetailsToCache(hashMap);
                }
            }
        }
        return hashMap;
    }

    private static Map<String, String> readSkuDetailsFromCache(ArrayList<String> arrayList) {
        HashMap hashMap = new HashMap();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            String string = skuDetailSharedPrefs.getString(next, null);
            if (string != null) {
                String[] split = string.split(";", 2);
                if (currentTimeMillis - Long.parseLong(split[0]) < 43200) {
                    hashMap.put(next, split[1]);
                }
            }
        }
        return hashMap;
    }

    private static void writeSkuDetailsToCache(Map<String, String> map) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        SharedPreferences.Editor edit = skuDetailSharedPrefs.edit();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            edit.putString(entry.getKey(), currentTimeMillis + ";" + entry.getValue());
        }
        edit.apply();
    }

    private static Boolean isBillingSupported(Context context, Object obj, String str) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        Object invokeMethod = invokeMethod(context, IN_APP_BILLING_SERVICE, IS_BILLING_SUPPORTED, obj, new Object[]{3, PACKAGE_NAME, str});
        if (invokeMethod != null && ((Integer) invokeMethod).intValue() == 0) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public static ArrayList<String> getPurchasesInapp(Context context, Object obj) {
        return filterPurchasesInapp(getPurchases(context, obj, INAPP));
    }

    public static ArrayList<String> getPurchasesSubsExpire(Context context, Object obj) {
        ArrayList<String> arrayList = new ArrayList<>();
        Map<String, ?> all = purchaseSubsSharedPrefs.getAll();
        if (all.isEmpty()) {
            return arrayList;
        }
        ArrayList<String> purchases = getPurchases(context, obj, SUBSCRIPTION);
        HashSet hashSet = new HashSet();
        Iterator<String> it = purchases.iterator();
        while (it.hasNext()) {
            try {
                hashSet.add(new JSONObject(it.next()).getString("productId"));
            } catch (JSONException e) {
                Log.e(TAG, "Error parsing purchase json", e);
            }
        }
        HashSet<String> hashSet2 = new HashSet();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            if (!hashSet.contains(key)) {
                hashSet2.add(key);
            }
        }
        SharedPreferences.Editor edit = purchaseSubsSharedPrefs.edit();
        for (String str : hashSet2) {
            SharedPreferences sharedPreferences = purchaseSubsSharedPrefs;
            String string = sharedPreferences.getString(str, "");
            edit.remove(str);
            if (!string.isEmpty()) {
                arrayList.add(sharedPreferences.getString(str, ""));
            }
        }
        edit.apply();
        return arrayList;
    }

    public static Map<String, SubscriptionType> getPurchasesSubs(Context context, Object obj) {
        HashMap hashMap = new HashMap();
        Iterator<String> it = getPurchases(context, obj, SUBSCRIPTION).iterator();
        while (it.hasNext()) {
            String next = it.next();
            SubscriptionType subsType = getSubsType(next);
            if (subsType != SubscriptionType.DUPLICATED && subsType != SubscriptionType.UNKNOWN) {
                hashMap.put(next, subsType);
            }
        }
        return hashMap;
    }

    private static SubscriptionType getSubsType(String str) {
        SubscriptionType subscriptionType = null;
        try {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("productId");
            SharedPreferences sharedPreferences = purchaseSubsSharedPrefs;
            String string2 = sharedPreferences.getString(string, "");
            JSONObject jSONObject2 = string2.isEmpty() ? new JSONObject() : new JSONObject(string2);
            if (!jSONObject2.optString("purchaseToken").equals(jSONObject.get("purchaseToken"))) {
                subscriptionType = currentTimeMillis - (jSONObject.getLong("purchaseTime") / 1000) < 43200 ? SubscriptionType.NEW : SubscriptionType.HEARTBEAT;
            }
            if (subscriptionType == null && !string2.isEmpty()) {
                boolean z = jSONObject2.getBoolean("autoRenewing");
                boolean z2 = jSONObject.getBoolean("autoRenewing");
                if (!z2 && z) {
                    subscriptionType = SubscriptionType.CANCEL;
                } else if (!z && z2) {
                    subscriptionType = SubscriptionType.RESTORE;
                }
            }
            if (subscriptionType == null && !string2.isEmpty()) {
                if (currentTimeMillis - jSONObject2.getLong(LAST_LOGGED_TIME_SEC) > SUBSCRIPTION_HARTBEAT_INTERVAL) {
                    subscriptionType = SubscriptionType.HEARTBEAT;
                } else {
                    subscriptionType = SubscriptionType.DUPLICATED;
                }
            }
            if (subscriptionType != SubscriptionType.DUPLICATED) {
                jSONObject.put(LAST_LOGGED_TIME_SEC, currentTimeMillis);
                sharedPreferences.edit().putString(string, jSONObject.toString()).apply();
            }
            return subscriptionType;
        } catch (JSONException e) {
            Log.e(TAG, "parsing purchase failure: ", e);
            return SubscriptionType.UNKNOWN;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005c A[EDGE_INSN: B:21:0x005c->B:19:0x005c ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.ArrayList<java.lang.String> getPurchases(android.content.Context r9, java.lang.Object r10, java.lang.String r11) {
        java.util.ArrayList<java.lang.String> r0 = new java.util.ArrayList<>();
        if (r10 != null && isBillingSupported(r9, r10, r11).booleanValue()) {
            int r4 = 0;
            java.lang.String r3 = null;
            do {
                java.lang.Object r3 = invokeMethod(r9, com.facebook.appevents.internal.InAppPurchaseEventManager.IN_APP_BILLING_SERVICE, com.facebook.appevents.internal.InAppPurchaseEventManager.GET_PURCHASES, r10, new java.lang.Object[]{3, com.facebook.appevents.internal.InAppPurchaseEventManager.PACKAGE_NAME, r11, r3});
                if (r3 != null) {
                    android.os.Bundle r3 = (android.os.Bundle) r3;
                    if (r3.getInt(com.facebook.appevents.internal.InAppPurchaseEventManager.RESPONSE_CODE) == 0) {
                        java.util.ArrayList<java.lang.String> r5 = r3.getStringArrayList(com.facebook.appevents.internal.InAppPurchaseEventManager.INAPP_PURCHASE_DATA_LIST);
                        if (r5 == null) {
                            break;
                        }
                        r4 += r5.size();
                        r0.addAll(r5);
                        r3 = r3.getString(com.facebook.appevents.internal.InAppPurchaseEventManager.INAPP_CONTINUATION_TOKEN);
                        if (r4 >= 30) {
                            break;
                        }
                    }
                }
                r3 = null;
                if (r4 >= 30) {
                    break;
                    break;
                }
            } while (r3 != null);
        }
        return r0;
    }

    public static ArrayList<String> getPurchaseHistoryInapp(Context context, Object obj) {
        Class<?> cls;
        ArrayList<String> arrayList = new ArrayList<>();
        return (obj == null || (cls = getClass(context, IN_APP_BILLING_SERVICE)) == null || getMethod(cls, GET_PURCHASE_HISTORY) == null) ? arrayList : filterPurchasesInapp(getPurchaseHistory(context, obj, INAPP));
    }

    private static ArrayList<String> getPurchaseHistory(Context context, Object obj, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (isBillingSupported(context, obj, str).booleanValue()) {
            char c = 0;
            Boolean bool = false;
            int i = 0;
            String str2 = null;
            while (true) {
                Object[] objArr = new Object[5];
                objArr[c] = 6;
                objArr[1] = PACKAGE_NAME;
                objArr[2] = str;
                objArr[3] = str2;
                objArr[4] = new Bundle();
                Object invokeMethod = invokeMethod(context, IN_APP_BILLING_SERVICE, GET_PURCHASE_HISTORY, obj, objArr);
                if (invokeMethod != null) {
                    long currentTimeMillis = System.currentTimeMillis() / 1000;
                    Bundle bundle = (Bundle) invokeMethod;
                    if (bundle.getInt(RESPONSE_CODE) == 0) {
                        Iterator<String> it = bundle.getStringArrayList(INAPP_PURCHASE_DATA_LIST).iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            String next = it.next();
                            try {
                            } catch (JSONException e) {
                                Log.e(TAG, "parsing purchase failure: ", e);
                            }
                            if (currentTimeMillis - (new JSONObject(next).getLong("purchaseTime") / 1000) > 1200) {
                                bool = true;
                                break;
                            }
                            arrayList.add(next);
                            i++;
                        }
                        str2 = bundle.getString(INAPP_CONTINUATION_TOKEN);
                        if (i < 30 || str2 == null || bool.booleanValue()) {
                            break;
                            break;
                        }
                        c = 0;
                    }
                }
                str2 = null;
                if (i < 30) {
                    break;
                }
                c = 0;
            }
        }
        return arrayList;
    }

    private static ArrayList<String> filterPurchasesInapp(ArrayList<String> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        SharedPreferences.Editor edit = purchaseInappSharedPrefs.edit();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            try {
                JSONObject jSONObject = new JSONObject(next);
                String string = jSONObject.getString("productId");
                long j = jSONObject.getLong("purchaseTime");
                String string2 = jSONObject.getString("purchaseToken");
                if (currentTimeMillis - (j / 1000) <= 43200 && !purchaseInappSharedPrefs.getString(string, "").equals(string2)) {
                    edit.putString(string, string2);
                    arrayList2.add(next);
                }
            } catch (JSONException e) {
                Log.e(TAG, "parsing purchase failure: ", e);
            }
        }
        edit.apply();
        return arrayList2;
    }

    private static Method getMethod(Class<?> cls, String str) {
        HashMap<String, Method> hashMap = methodMap;
        Method method = hashMap.get(str);
        if (method != null) {
            return method;
        }
        Class<?>[] clsArr = null;
        char c = 65535;
        try {
            switch (str.hashCode()) {
                case -1801122596:
                    if (str.equals(GET_PURCHASES)) {
                        c = 3;
                        break;
                    }
                    break;
                case -1450694211:
                    if (str.equals(IS_BILLING_SUPPORTED)) {
                        c = 2;
                        break;
                    }
                    break;
                case -1123215065:
                    if (str.equals(AS_INTERFACE)) {
                        c = 0;
                        break;
                    }
                    break;
                case -594356707:
                    if (str.equals(GET_PURCHASE_HISTORY)) {
                        c = 4;
                        break;
                    }
                    break;
                case -573310373:
                    if (str.equals(GET_SKU_DETAILS)) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                clsArr = new Class[]{IBinder.class};
            } else if (c == 1) {
                clsArr = new Class[]{Integer.TYPE, String.class, String.class, Bundle.class};
            } else if (c == 2) {
                clsArr = new Class[]{Integer.TYPE, String.class, String.class};
            } else if (c == 3) {
                clsArr = new Class[]{Integer.TYPE, String.class, String.class, String.class};
            } else if (c == 4) {
                clsArr = new Class[]{Integer.TYPE, String.class, String.class, String.class, Bundle.class};
            }
            method = cls.getDeclaredMethod(str, clsArr);
            hashMap.put(str, method);
            return method;
        } catch (NoSuchMethodException e) {
            Log.e(TAG, cls.getName() + "." + str + " method not found", e);
            return method;
        }
    }

    private static Class<?> getClass(Context context, String str) {
        HashMap<String, Class<?>> hashMap = classMap;
        Class<?> cls = hashMap.get(str);
        if (cls != null) {
            return cls;
        }
        try {
            cls = context.getClassLoader().loadClass(str);
            hashMap.put(str, cls);
            return cls;
        } catch (ClassNotFoundException e) {
            Log.e(TAG, str + " is not available, please add " + str + " to the project.", e);
            return cls;
        }
    }

    private static Object invokeMethod(Context context, String str, String str2, Object obj, Object[] objArr) {
        Method method;
        Class<?> cls = getClass(context, str);
        if (cls == null || (method = getMethod(cls, str2)) == null) {
            return null;
        }
        if (obj != null) {
            obj = cls.cast(obj);
        }
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Illegal access to method " + cls.getName() + "." + method.getName(), e);
            return null;
        } catch (InvocationTargetException e2) {
            Log.e(TAG, "Invocation target exception in " + cls.getName() + "." + method.getName(), e2);
            return null;
        }
    }

    public static void clearSkuDetailsCache() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        SharedPreferences sharedPreferences = skuDetailSharedPrefs;
        long j = sharedPreferences.getLong(LAST_CLEARED_TIME, 0L);
        if (j == 0) {
            sharedPreferences.edit().putLong(LAST_CLEARED_TIME, currentTimeMillis).apply();
        } else if (currentTimeMillis - j > 604800) {
            sharedPreferences.edit().clear().putLong(LAST_CLEARED_TIME, currentTimeMillis).apply();
        }
    }
}
