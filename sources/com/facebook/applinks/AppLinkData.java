package com.facebook.applinks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class AppLinkData {
    private static final String APPLINK_BRIDGE_ARGS_KEY = "bridge_args";
    private static final String APPLINK_METHOD_ARGS_KEY = "method_args";
    private static final String APPLINK_VERSION_KEY = "version";
    public static final String ARGUMENTS_EXTRAS_KEY = "extras";
    public static final String ARGUMENTS_NATIVE_CLASS_KEY = "com.facebook.platform.APPLINK_NATIVE_CLASS";
    public static final String ARGUMENTS_NATIVE_URL = "com.facebook.platform.APPLINK_NATIVE_URL";
    public static final String ARGUMENTS_REFERER_DATA_KEY = "referer_data";
    public static final String ARGUMENTS_TAPTIME_KEY = "com.facebook.platform.APPLINK_TAP_TIME_UTC";
    private static final String BRIDGE_ARGS_METHOD_KEY = "method";
    private static final String BUNDLE_AL_APPLINK_DATA_KEY = "al_applink_data";
    static final String BUNDLE_APPLINK_ARGS_KEY = "com.facebook.platform.APPLINK_ARGS";
    private static final String DEFERRED_APP_LINK_ARGS_FIELD = "applink_args";
    private static final String DEFERRED_APP_LINK_CLASS_FIELD = "applink_class";
    private static final String DEFERRED_APP_LINK_CLICK_TIME_FIELD = "click_time";
    private static final String DEFERRED_APP_LINK_EVENT = "DEFERRED_APP_LINK";
    private static final String DEFERRED_APP_LINK_PATH = "%s/activities";
    private static final String DEFERRED_APP_LINK_URL_FIELD = "applink_url";
    private static final String EXTRAS_DEEPLINK_CONTEXT_KEY = "deeplink_context";
    private static final String METHOD_ARGS_REF_KEY = "ref";
    private static final String METHOD_ARGS_TARGET_URL_KEY = "target_url";
    private static final String PROMOTION_CODE_KEY = "promo_code";
    private static final String REFERER_DATA_REF_KEY = "fb_ref";
    private static final String TAG = "com.facebook.applinks.AppLinkData";
    private Bundle argumentBundle;
    private JSONObject arguments;
    private String promotionCode;
    private String ref;
    private Uri targetUri;

    /* loaded from: classes.dex */
    public interface CompletionHandler {
        void onDeferredAppLinkDataFetched(AppLinkData appLinkData);
    }

    public static void fetchDeferredAppLinkData(Context context, CompletionHandler completionHandler) {
        fetchDeferredAppLinkData(context, null, completionHandler);
    }

    public static void fetchDeferredAppLinkData(Context context, final String str, final CompletionHandler completionHandler) {
        Validate.notNull(context, "context");
        Validate.notNull(completionHandler, "completionHandler");
        if (str == null) {
            str = Utility.getMetadataApplicationId(context);
        }
        Validate.notNull(str, "applicationId");
        final Context applicationContext = context.getApplicationContext();
        FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.applinks.AppLinkData.1
            @Override // java.lang.Runnable
            public void run() {
                AppLinkData.fetchDeferredAppLinkFromServer(applicationContext, str, completionHandler);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fetchDeferredAppLinkFromServer(Context context, String str, CompletionHandler completionHandler) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(NotificationCompat.CATEGORY_EVENT, DEFERRED_APP_LINK_EVENT);
            Utility.setAppEventAttributionParameters(jSONObject, AttributionIdentifiers.getAttributionIdentifiers(context), AppEventsLogger.getAnonymousAppDeviceGUID(context), FacebookSdk.getLimitEventAndDataUsage(context));
            Utility.setAppEventExtendedDeviceInfoParameters(jSONObject, FacebookSdk.getApplicationContext());
            jSONObject.put("application_package_name", context.getPackageName());
            Object[] objArr = {str};
            AppLinkData appLinkData = null;
            try {
                JSONObject jSONObject2 = GraphRequest.newPostRequest(null, String.format(DEFERRED_APP_LINK_PATH, objArr), jSONObject, null).executeAndWait().getJSONObject();
                if (jSONObject2 != null) {
                    String optString = jSONObject2.optString(DEFERRED_APP_LINK_ARGS_FIELD);
                    long optLong = jSONObject2.optLong(DEFERRED_APP_LINK_CLICK_TIME_FIELD, -1L);
                    String optString2 = jSONObject2.optString(DEFERRED_APP_LINK_CLASS_FIELD);
                    String optString3 = jSONObject2.optString(DEFERRED_APP_LINK_URL_FIELD);
                    if (!TextUtils.isEmpty(optString)) {
                        appLinkData = createFromJson(optString);
                        if (optLong != -1) {
                            try {
                                JSONObject jSONObject3 = appLinkData.arguments;
                                if (jSONObject3 != null) {
                                    jSONObject3.put(ARGUMENTS_TAPTIME_KEY, optLong);
                                }
                                Bundle bundle = appLinkData.argumentBundle;
                                if (bundle != null) {
                                    bundle.putString(ARGUMENTS_TAPTIME_KEY, Long.toString(optLong));
                                }
                            } catch (JSONException unused) {
                                Utility.logd(TAG, "Unable to put tap time in AppLinkData.arguments");
                            }
                        }
                        if (optString2 != null) {
                            try {
                                JSONObject jSONObject4 = appLinkData.arguments;
                                if (jSONObject4 != null) {
                                    jSONObject4.put(ARGUMENTS_NATIVE_CLASS_KEY, optString2);
                                }
                                Bundle bundle2 = appLinkData.argumentBundle;
                                if (bundle2 != null) {
                                    bundle2.putString(ARGUMENTS_NATIVE_CLASS_KEY, optString2);
                                }
                            } catch (JSONException unused2) {
                                Utility.logd(TAG, "Unable to put tap time in AppLinkData.arguments");
                            }
                        }
                        if (optString3 != null) {
                            try {
                                JSONObject jSONObject5 = appLinkData.arguments;
                                if (jSONObject5 != null) {
                                    jSONObject5.put(ARGUMENTS_NATIVE_URL, optString3);
                                }
                                Bundle bundle3 = appLinkData.argumentBundle;
                                if (bundle3 != null) {
                                    bundle3.putString(ARGUMENTS_NATIVE_URL, optString3);
                                }
                            } catch (JSONException unused3) {
                                Utility.logd(TAG, "Unable to put tap time in AppLinkData.arguments");
                            }
                        }
                    }
                }
            } catch (Exception unused4) {
                Utility.logd(TAG, "Unable to fetch deferred applink from server");
            }
            completionHandler.onDeferredAppLinkDataFetched(appLinkData);
        } catch (JSONException e) {
            throw new FacebookException("An error occurred while preparing deferred app link", e);
        }
    }

    public static AppLinkData createFromActivity(Activity activity) {
        Validate.notNull(activity, "activity");
        Intent intent = activity.getIntent();
        if (intent == null) {
            return null;
        }
        AppLinkData createFromAlApplinkData = createFromAlApplinkData(intent);
        if (createFromAlApplinkData == null) {
            createFromAlApplinkData = createFromJson(intent.getStringExtra(BUNDLE_APPLINK_ARGS_KEY));
        }
        return createFromAlApplinkData == null ? createFromUri(intent.getData()) : createFromAlApplinkData;
    }

    public static AppLinkData createFromAlApplinkData(Intent intent) {
        Bundle bundleExtra;
        String string;
        String string2;
        if (intent == null || (bundleExtra = intent.getBundleExtra(BUNDLE_AL_APPLINK_DATA_KEY)) == null) {
            return null;
        }
        AppLinkData appLinkData = new AppLinkData();
        Uri data = intent.getData();
        appLinkData.targetUri = data;
        if (data == null && (string2 = bundleExtra.getString(METHOD_ARGS_TARGET_URL_KEY)) != null) {
            appLinkData.targetUri = Uri.parse(string2);
        }
        appLinkData.argumentBundle = bundleExtra;
        appLinkData.arguments = null;
        Bundle bundle = bundleExtra.getBundle(ARGUMENTS_REFERER_DATA_KEY);
        if (bundle != null) {
            appLinkData.ref = bundle.getString(REFERER_DATA_REF_KEY);
        }
        Bundle bundle2 = bundleExtra.getBundle(ARGUMENTS_EXTRAS_KEY);
        if (bundle2 != null && (string = bundle2.getString("deeplink_context")) != null) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                if (jSONObject.has("promo_code")) {
                    appLinkData.promotionCode = jSONObject.getString("promo_code");
                }
            } catch (JSONException e) {
                Utility.logd(TAG, "Unable to parse deeplink_context JSON", e);
            }
        }
        return appLinkData;
    }

    private static AppLinkData createFromJson(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("version");
            if (jSONObject.getJSONObject("bridge_args").getString("method").equals("applink") && string.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                AppLinkData appLinkData = new AppLinkData();
                JSONObject jSONObject2 = jSONObject.getJSONObject("method_args");
                appLinkData.arguments = jSONObject2;
                if (jSONObject2.has(METHOD_ARGS_REF_KEY)) {
                    appLinkData.ref = appLinkData.arguments.getString(METHOD_ARGS_REF_KEY);
                } else if (appLinkData.arguments.has(ARGUMENTS_REFERER_DATA_KEY)) {
                    JSONObject jSONObject3 = appLinkData.arguments.getJSONObject(ARGUMENTS_REFERER_DATA_KEY);
                    if (jSONObject3.has(REFERER_DATA_REF_KEY)) {
                        appLinkData.ref = jSONObject3.getString(REFERER_DATA_REF_KEY);
                    }
                }
                if (appLinkData.arguments.has(METHOD_ARGS_TARGET_URL_KEY)) {
                    appLinkData.targetUri = Uri.parse(appLinkData.arguments.getString(METHOD_ARGS_TARGET_URL_KEY));
                }
                if (appLinkData.arguments.has(ARGUMENTS_EXTRAS_KEY)) {
                    JSONObject jSONObject4 = appLinkData.arguments.getJSONObject(ARGUMENTS_EXTRAS_KEY);
                    if (jSONObject4.has("deeplink_context")) {
                        JSONObject jSONObject5 = jSONObject4.getJSONObject("deeplink_context");
                        if (jSONObject5.has("promo_code")) {
                            appLinkData.promotionCode = jSONObject5.getString("promo_code");
                        }
                    }
                }
                appLinkData.argumentBundle = toBundle(appLinkData.arguments);
                return appLinkData;
            }
        } catch (FacebookException e) {
            Utility.logd(TAG, "Unable to parse AppLink JSON", e);
        } catch (JSONException e2) {
            Utility.logd(TAG, "Unable to parse AppLink JSON", e2);
        }
        return null;
    }

    private static AppLinkData createFromUri(Uri uri) {
        if (uri == null) {
            return null;
        }
        AppLinkData appLinkData = new AppLinkData();
        appLinkData.targetUri = uri;
        return appLinkData;
    }

    private static Bundle toBundle(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONObject) {
                bundle.putBundle(next, toBundle((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj;
                int i = 0;
                if (jSONArray.length() == 0) {
                    bundle.putStringArray(next, new String[0]);
                } else {
                    Object obj2 = jSONArray.get(0);
                    if (obj2 instanceof JSONObject) {
                        Bundle[] bundleArr = new Bundle[jSONArray.length()];
                        while (i < jSONArray.length()) {
                            bundleArr[i] = toBundle(jSONArray.getJSONObject(i));
                            i++;
                        }
                        bundle.putParcelableArray(next, bundleArr);
                    } else if (obj2 instanceof JSONArray) {
                        throw new FacebookException("Nested arrays are not supported.");
                    } else {
                        String[] strArr = new String[jSONArray.length()];
                        while (i < jSONArray.length()) {
                            strArr[i] = jSONArray.get(i).toString();
                            i++;
                        }
                        bundle.putStringArray(next, strArr);
                    }
                }
            } else {
                bundle.putString(next, obj.toString());
            }
        }
        return bundle;
    }

    private AppLinkData() {
    }

    public Uri getTargetUri() {
        return this.targetUri;
    }

    public String getRef() {
        return this.ref;
    }

    public String getPromotionCode() {
        return this.promotionCode;
    }

    public Bundle getArgumentBundle() {
        return this.argumentBundle;
    }

    public Bundle getRefererData() {
        Bundle bundle = this.argumentBundle;
        if (bundle != null) {
            return bundle.getBundle(ARGUMENTS_REFERER_DATA_KEY);
        }
        return null;
    }
}