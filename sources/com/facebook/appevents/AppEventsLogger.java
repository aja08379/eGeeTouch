package com.facebook.appevents;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import bolts.AppLinks;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.appevents.internal.Constants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.BundleJSONConverter;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.share.internal.ShareConstants;
import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class AppEventsLogger {
    public static final String ACTION_APP_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_FLUSHED";
    public static final String APP_EVENTS_EXTRA_FLUSH_RESULT = "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT";
    public static final String APP_EVENTS_EXTRA_NUM_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED";
    private static final String APP_EVENT_NAME_PUSH_OPENED = "fb_mobile_push_opened";
    public static final String APP_EVENT_PREFERENCES = "com.facebook.sdk.appEventPreferences";
    private static final String APP_EVENT_PUSH_PARAMETER_ACTION = "fb_push_action";
    private static final String APP_EVENT_PUSH_PARAMETER_CAMPAIGN = "fb_push_campaign";
    private static final int APP_SUPPORTS_ATTRIBUTION_ID_RECHECK_PERIOD_IN_SECONDS = 86400;
    private static final int FLUSH_APP_SESSION_INFO_IN_SECONDS = 30;
    private static final String PUSH_PAYLOAD_CAMPAIGN_KEY = "campaign";
    private static final String PUSH_PAYLOAD_KEY = "fb_push_payload";
    private static final String SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT = "_fbSourceApplicationHasBeenSet";
    private static final String TAG = "com.facebook.appevents.AppEventsLogger";
    private static String anonymousAppDeviceGUID;
    private static ScheduledThreadPoolExecutor backgroundExecutor;
    private static boolean isActivateAppEventRequested;
    private static boolean isOpenedByAppLink;
    private static String pushNotificationsRegistrationId;
    private static String sourceApplication;
    private final AccessTokenAppIdPair accessTokenAppId;
    private final String contextName;
    private static FlushBehavior flushBehavior = FlushBehavior.AUTO;
    private static Object staticLock = new Object();

    /* loaded from: classes.dex */
    public enum FlushBehavior {
        AUTO,
        EXPLICIT_ONLY
    }

    /* loaded from: classes.dex */
    public enum ProductAvailability {
        IN_STOCK,
        OUT_OF_STOCK,
        PREORDER,
        AVALIABLE_FOR_ORDER,
        DISCONTINUED
    }

    /* loaded from: classes.dex */
    public enum ProductCondition {
        NEW,
        REFURBISHED,
        USED
    }

    public static void activateApp(Application application) {
        activateApp(application, (String) null);
    }

    public static void activateApp(Application application, String str) {
        if (!FacebookSdk.isInitialized()) {
            throw new FacebookException("The Facebook sdk must be initialized before calling activateApp");
        }
        AnalyticsUserIDStore.initStore();
        UserDataStore.initStore();
        if (str == null) {
            str = FacebookSdk.getApplicationId();
        }
        FacebookSdk.publishInstallAsync(application, str);
        ActivityLifecycleTracker.startTracking(application, str);
    }

    @Deprecated
    public static void activateApp(Context context) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "activateApp events are being logged automatically. There's no need to call activateApp explicitly, this is safe to remove.");
            return;
        }
        FacebookSdk.sdkInitialize(context);
        activateApp(context, Utility.getMetadataApplicationId(context));
    }

    @Deprecated
    public static void activateApp(Context context, String str) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "activateApp events are being logged automatically. There's no need to call activateApp explicitly, this is safe to remove.");
        } else if (context == null || str == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        } else {
            AnalyticsUserIDStore.initStore();
            UserDataStore.initStore();
            if (context instanceof Activity) {
                setSourceApplication((Activity) context);
            } else {
                resetSourceApplication();
                Utility.logd(AppEventsLogger.class.getName(), "To set source application the context of activateApp must be an instance of Activity");
            }
            FacebookSdk.publishInstallAsync(context, str);
            AppEventsLogger appEventsLogger = new AppEventsLogger(context, str, (AccessToken) null);
            final long currentTimeMillis = System.currentTimeMillis();
            final String sourceApplication2 = getSourceApplication();
            backgroundExecutor.execute(new Runnable() { // from class: com.facebook.appevents.AppEventsLogger.1
                @Override // java.lang.Runnable
                public void run() {
                    AppEventsLogger.this.logAppSessionResumeEvent(currentTimeMillis, sourceApplication2);
                }
            });
        }
    }

    @Deprecated
    public static void deactivateApp(Context context) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "deactivateApp events are being logged automatically. There's no need to call deactivateApp, this is safe to remove.");
        } else {
            deactivateApp(context, Utility.getMetadataApplicationId(context));
        }
    }

    @Deprecated
    public static void deactivateApp(Context context, String str) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "deactivateApp events are being logged automatically. There's no need to call deactivateApp, this is safe to remove.");
        } else if (context == null || str == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        } else {
            resetSourceApplication();
            AppEventsLogger appEventsLogger = new AppEventsLogger(context, str, (AccessToken) null);
            final long currentTimeMillis = System.currentTimeMillis();
            backgroundExecutor.execute(new Runnable() { // from class: com.facebook.appevents.AppEventsLogger.2
                @Override // java.lang.Runnable
                public void run() {
                    AppEventsLogger.this.logAppSessionSuspendEvent(currentTimeMillis);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logAppSessionResumeEvent(long j, String str) {
        PersistedAppSessionInfo.onResume(FacebookSdk.getApplicationContext(), this.accessTokenAppId, this, j, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logAppSessionSuspendEvent(long j) {
        PersistedAppSessionInfo.onSuspend(FacebookSdk.getApplicationContext(), this.accessTokenAppId, this, j);
    }

    public static void initializeLib(Context context, String str) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            backgroundExecutor.execute(new Runnable() { // from class: com.facebook.appevents.AppEventsLogger.3
                @Override // java.lang.Runnable
                public void run() {
                    Bundle bundle = new Bundle();
                    try {
                        Class.forName("com.facebook.core.Core");
                        bundle.putInt("core_lib_included", 1);
                    } catch (ClassNotFoundException unused) {
                    }
                    try {
                        Class.forName("com.facebook.login.Login");
                        bundle.putInt("login_lib_included", 1);
                    } catch (ClassNotFoundException unused2) {
                    }
                    try {
                        Class.forName("com.facebook.share.Share");
                        bundle.putInt("share_lib_included", 1);
                    } catch (ClassNotFoundException unused3) {
                    }
                    try {
                        Class.forName("com.facebook.places.Places");
                        bundle.putInt("places_lib_included", 1);
                    } catch (ClassNotFoundException unused4) {
                    }
                    try {
                        Class.forName("com.facebook.messenger.Messenger");
                        bundle.putInt("messenger_lib_included", 1);
                    } catch (ClassNotFoundException unused5) {
                    }
                    try {
                        Class.forName("com.facebook.applinks.AppLinks");
                        bundle.putInt("applinks_lib_included", 1);
                    } catch (ClassNotFoundException unused6) {
                    }
                    try {
                        Class.forName("com.facebook.marketing.Marketing");
                        bundle.putInt("marketing_lib_included", 1);
                    } catch (ClassNotFoundException unused7) {
                    }
                    try {
                        Class.forName("com.facebook.all.All");
                        bundle.putInt("all_lib_included", 1);
                    } catch (ClassNotFoundException unused8) {
                    }
                    try {
                        Class.forName("com.android.billingclient.api.BillingClient");
                        bundle.putInt("billing_client_lib_included", 1);
                    } catch (ClassNotFoundException unused9) {
                    }
                    try {
                        Class.forName("com.android.vending.billing.IInAppBillingService");
                        bundle.putInt("billing_service_lib_included", 1);
                    } catch (ClassNotFoundException unused10) {
                    }
                    AppEventsLogger.this.logSdkEvent(AnalyticsEvents.EVENT_SDK_INITIALIZE, null, bundle);
                }
            });
        }
    }

    public static AppEventsLogger newLogger(Context context) {
        return new AppEventsLogger(context, (String) null, (AccessToken) null);
    }

    public static AppEventsLogger newLogger(Context context, AccessToken accessToken) {
        return new AppEventsLogger(context, (String) null, accessToken);
    }

    public static AppEventsLogger newLogger(Context context, String str, AccessToken accessToken) {
        return new AppEventsLogger(context, str, accessToken);
    }

    public static AppEventsLogger newLogger(Context context, String str) {
        return new AppEventsLogger(context, str, (AccessToken) null);
    }

    public static FlushBehavior getFlushBehavior() {
        FlushBehavior flushBehavior2;
        synchronized (staticLock) {
            flushBehavior2 = flushBehavior;
        }
        return flushBehavior2;
    }

    public static void setFlushBehavior(FlushBehavior flushBehavior2) {
        synchronized (staticLock) {
            flushBehavior = flushBehavior2;
        }
    }

    public void logEvent(String str) {
        logEvent(str, (Bundle) null);
    }

    public void logEvent(String str, double d) {
        logEvent(str, d, null);
    }

    public void logEvent(String str, Bundle bundle) {
        logEvent(str, null, bundle, false, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public void logEvent(String str, double d, Bundle bundle) {
        logEvent(str, Double.valueOf(d), bundle, false, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public void logPurchase(BigDecimal bigDecimal, Currency currency) {
        if (AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
            Log.w(TAG, "You are logging purchase events while auto-logging of in-app purchase is enabled in the SDK. Make sure you don't log duplicate events");
        }
        logPurchase(bigDecimal, currency, null, false);
    }

    public void logPurchase(BigDecimal bigDecimal, Currency currency, Bundle bundle) {
        if (AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
            Log.w(TAG, "You are logging purchase events while auto-logging of in-app purchase is enabled in the SDK. Make sure you don't log duplicate events");
        }
        logPurchase(bigDecimal, currency, bundle, false);
    }

    @Deprecated
    public void logPurchaseImplicitly(BigDecimal bigDecimal, Currency currency, Bundle bundle) {
        String str;
        if (AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
            str = "Function logPurchaseImplicitly() is deprecated and your purchase events cannot be logged with this function. Auto-logging of in-app purchase has been enabled in the SDK, so you don't have to manually log purchases";
        } else {
            str = "Function logPurchaseImplicitly() is deprecated and your purchase events cannot be logged with this function. Please use logPurchase() function instead.";
        }
        Log.e(TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logPurchaseImplicitlyInternal(BigDecimal bigDecimal, Currency currency, Bundle bundle) {
        logPurchase(bigDecimal, currency, bundle, true);
    }

    private void logPurchase(BigDecimal bigDecimal, Currency currency, Bundle bundle, boolean z) {
        if (bigDecimal == null) {
            notifyDeveloperError("purchaseAmount cannot be null");
        } else if (currency == null) {
            notifyDeveloperError("currency cannot be null");
        } else {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Bundle bundle2 = bundle;
            bundle2.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, currency.getCurrencyCode());
            logEvent(AppEventsConstants.EVENT_NAME_PURCHASED, Double.valueOf(bigDecimal.doubleValue()), bundle2, z, ActivityLifecycleTracker.getCurrentSessionGuid());
            eagerFlush();
        }
    }

    public void logPushNotificationOpen(Bundle bundle) {
        logPushNotificationOpen(bundle, null);
    }

    public void logPushNotificationOpen(Bundle bundle, String str) {
        String str2;
        String string;
        try {
            string = bundle.getString(PUSH_PAYLOAD_KEY);
        } catch (JSONException unused) {
            str2 = null;
        }
        if (Utility.isNullOrEmpty(string)) {
            return;
        }
        str2 = new JSONObject(string).getString("campaign");
        if (str2 == null) {
            Logger.log(LoggingBehavior.DEVELOPER_ERRORS, TAG, "Malformed payload specified for logging a push notification open.");
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString(APP_EVENT_PUSH_PARAMETER_CAMPAIGN, str2);
        if (str != null) {
            bundle2.putString(APP_EVENT_PUSH_PARAMETER_ACTION, str);
        }
        logEvent(APP_EVENT_NAME_PUSH_OPENED, bundle2);
    }

    public void logProductItem(String str, ProductAvailability productAvailability, ProductCondition productCondition, String str2, String str3, String str4, String str5, BigDecimal bigDecimal, Currency currency, String str6, String str7, String str8, Bundle bundle) {
        if (str == null) {
            notifyDeveloperError("itemID cannot be null");
        } else if (productAvailability == null) {
            notifyDeveloperError("availability cannot be null");
        } else if (productCondition == null) {
            notifyDeveloperError("condition cannot be null");
        } else if (str2 == null) {
            notifyDeveloperError("description cannot be null");
        } else if (str3 == null) {
            notifyDeveloperError("imageLink cannot be null");
        } else if (str4 == null) {
            notifyDeveloperError("link cannot be null");
        } else if (str5 == null) {
            notifyDeveloperError("title cannot be null");
        } else if (bigDecimal == null) {
            notifyDeveloperError("priceAmount cannot be null");
        } else if (currency == null) {
            notifyDeveloperError("currency cannot be null");
        } else if (str6 == null && str7 == null && str8 == null) {
            notifyDeveloperError("Either gtin, mpn or brand is required");
        } else {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(Constants.EVENT_PARAM_PRODUCT_ITEM_ID, str);
            bundle.putString(Constants.EVENT_PARAM_PRODUCT_AVAILABILITY, productAvailability.name());
            bundle.putString(Constants.EVENT_PARAM_PRODUCT_CONDITION, productCondition.name());
            bundle.putString(Constants.EVENT_PARAM_PRODUCT_DESCRIPTION, str2);
            bundle.putString(Constants.EVENT_PARAM_PRODUCT_IMAGE_LINK, str3);
            bundle.putString(Constants.EVENT_PARAM_PRODUCT_LINK, str4);
            bundle.putString(Constants.EVENT_PARAM_PRODUCT_TITLE, str5);
            bundle.putString(Constants.EVENT_PARAM_PRODUCT_PRICE_AMOUNT, bigDecimal.setScale(3, 4).toString());
            bundle.putString(Constants.EVENT_PARAM_PRODUCT_PRICE_CURRENCY, currency.getCurrencyCode());
            if (str6 != null) {
                bundle.putString(Constants.EVENT_PARAM_PRODUCT_GTIN, str6);
            }
            if (str7 != null) {
                bundle.putString(Constants.EVENT_PARAM_PRODUCT_MPN, str7);
            }
            if (str8 != null) {
                bundle.putString(Constants.EVENT_PARAM_PRODUCT_BRAND, str8);
            }
            logEvent(AppEventsConstants.EVENT_NAME_PRODUCT_CATALOG_UPDATE, bundle);
            eagerFlush();
        }
    }

    public void flush() {
        AppEventQueue.flush(FlushReason.EXPLICIT);
    }

    public static void onContextStop() {
        AppEventQueue.persistToDisk();
    }

    public boolean isValidForAccessToken(AccessToken accessToken) {
        return this.accessTokenAppId.equals(new AccessTokenAppIdPair(accessToken));
    }

    public static void setPushNotificationsRegistrationId(String str) {
        synchronized (staticLock) {
            if (!Utility.stringsEqualOrEmpty(pushNotificationsRegistrationId, str)) {
                pushNotificationsRegistrationId = str;
                AppEventsLogger newLogger = newLogger(FacebookSdk.getApplicationContext());
                newLogger.logEvent(AppEventsConstants.EVENT_NAME_PUSH_TOKEN_OBTAINED);
                if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
                    newLogger.flush();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getPushNotificationsRegistrationId() {
        String str;
        synchronized (staticLock) {
            str = pushNotificationsRegistrationId;
        }
        return str;
    }

    public static void augmentWebView(WebView webView, Context context) {
        String[] split = Build.VERSION.RELEASE.split("\\.");
        int parseInt = split.length > 0 ? Integer.parseInt(split[0]) : 0;
        int parseInt2 = split.length > 1 ? Integer.parseInt(split[1]) : 0;
        if (Build.VERSION.SDK_INT < 17 || parseInt < 4 || (parseInt == 4 && parseInt2 <= 1)) {
            Logger.log(LoggingBehavior.DEVELOPER_ERRORS, TAG, "augmentWebView is only available for Android SDK version >= 17 on devices running Android >= 4.2");
        } else {
            webView.addJavascriptInterface(new FacebookSDKJSInterface(context), "fbmq_" + FacebookSdk.getApplicationId());
        }
    }

    public static void setUserID(String str) {
        AnalyticsUserIDStore.setUserID(str);
    }

    public static String getUserID() {
        return AnalyticsUserIDStore.getUserID();
    }

    public static void clearUserID() {
        AnalyticsUserIDStore.setUserID(null);
    }

    @Deprecated
    public static void setUserData(Bundle bundle) {
        UserDataStore.setUserDataAndHash(bundle);
    }

    public static void setUserData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        UserDataStore.setUserDataAndHash(str, str2, str3, str4, str5, str6, str7, str8, str9, str10);
    }

    public static String getUserData() {
        return UserDataStore.getHashedUserData();
    }

    public static void clearUserData() {
        UserDataStore.clear();
    }

    public static void updateUserProperties(Bundle bundle, GraphRequest.Callback callback) {
        updateUserProperties(bundle, FacebookSdk.getApplicationId(), callback);
    }

    public static void updateUserProperties(final Bundle bundle, final String str, final GraphRequest.Callback callback) {
        getAnalyticsExecutor().execute(new Runnable() { // from class: com.facebook.appevents.AppEventsLogger.4
            @Override // java.lang.Runnable
            public void run() {
                String userID = AppEventsLogger.getUserID();
                if (userID == null || userID.isEmpty()) {
                    Logger.log(LoggingBehavior.APP_EVENTS, AppEventsLogger.TAG, "AppEventsLogger userID cannot be null or empty");
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("user_unique_id", userID);
                bundle2.putBundle("custom_data", bundle);
                AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
                if (attributionIdentifiers != null && attributionIdentifiers.getAndroidAdvertiserId() != null) {
                    bundle2.putString("advertiser_id", attributionIdentifiers.getAndroidAdvertiserId());
                }
                Bundle bundle3 = new Bundle();
                try {
                    JSONObject convertToJSON = BundleJSONConverter.convertToJSON(bundle2);
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(convertToJSON);
                    bundle3.putString(ShareConstants.WEB_DIALOG_PARAM_DATA, jSONArray.toString());
                    GraphRequest graphRequest = new GraphRequest(AccessToken.getCurrentAccessToken(), String.format(Locale.US, "%s/user_properties", str), bundle3, HttpMethod.POST, callback);
                    graphRequest.setSkipClientToken(true);
                    graphRequest.executeAsync();
                } catch (JSONException e) {
                    throw new FacebookException("Failed to construct request", e);
                }
            }
        });
    }

    public void logSdkEvent(String str, Double d, Bundle bundle) {
        logEvent(str, d, bundle, true, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public String getApplicationId() {
        return this.accessTokenAppId.getApplicationId();
    }

    private AppEventsLogger(Context context, String str, AccessToken accessToken) {
        this(Utility.getActivityName(context), str, accessToken);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AppEventsLogger(String str, String str2, AccessToken accessToken) {
        Validate.sdkInitialized();
        this.contextName = str;
        accessToken = accessToken == null ? AccessToken.getCurrentAccessToken() : accessToken;
        if (AccessToken.isCurrentAccessTokenActive() && (str2 == null || str2.equals(accessToken.getApplicationId()))) {
            this.accessTokenAppId = new AccessTokenAppIdPair(accessToken);
        } else {
            this.accessTokenAppId = new AccessTokenAppIdPair(null, str2 == null ? Utility.getMetadataApplicationId(FacebookSdk.getApplicationContext()) : str2);
        }
        initializeTimersIfNeeded();
    }

    private static void initializeTimersIfNeeded() {
        synchronized (staticLock) {
            if (backgroundExecutor != null) {
                return;
            }
            backgroundExecutor = new ScheduledThreadPoolExecutor(1);
            backgroundExecutor.scheduleAtFixedRate(new Runnable() { // from class: com.facebook.appevents.AppEventsLogger.5
                @Override // java.lang.Runnable
                public void run() {
                    HashSet<String> hashSet = new HashSet();
                    for (AccessTokenAppIdPair accessTokenAppIdPair : AppEventQueue.getKeySet()) {
                        hashSet.add(accessTokenAppIdPair.getApplicationId());
                    }
                    for (String str : hashSet) {
                        FetchedAppSettingsManager.queryAppSettings(str, true);
                    }
                }
            }, 0L, 86400L, TimeUnit.SECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logEventImplicitly(String str, BigDecimal bigDecimal, Currency currency, Bundle bundle) {
        logEvent(str, Double.valueOf(bigDecimal.doubleValue()), bundle, true, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    private void logEvent(String str, Double d, Bundle bundle, boolean z, UUID uuid) {
        try {
            logEvent(new AppEvent(this.contextName, str, d, bundle, z, ActivityLifecycleTracker.isInBackground(), uuid), this.accessTokenAppId);
        } catch (FacebookException e) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event: %s", e.toString());
        } catch (JSONException e2) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", e2.toString());
        }
    }

    private static void logEvent(AppEvent appEvent, AccessTokenAppIdPair accessTokenAppIdPair) {
        AppEventQueue.add(accessTokenAppIdPair, appEvent);
        if (appEvent.getIsImplicit() || isActivateAppEventRequested) {
            return;
        }
        if (appEvent.getName().equals(AppEventsConstants.EVENT_NAME_ACTIVATED_APP)) {
            isActivateAppEventRequested = true;
        } else {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
        }
    }

    static void eagerFlush() {
        if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
            AppEventQueue.flush(FlushReason.EAGER_FLUSHING_EVENT);
        }
    }

    private static void notifyDeveloperError(String str) {
        Logger.log(LoggingBehavior.DEVELOPER_ERRORS, "AppEvents", str);
    }

    private static void setSourceApplication(Activity activity) {
        ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity != null) {
            String packageName = callingActivity.getPackageName();
            if (packageName.equals(activity.getPackageName())) {
                resetSourceApplication();
                return;
            }
            sourceApplication = packageName;
        }
        Intent intent = activity.getIntent();
        if (intent == null || intent.getBooleanExtra(SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, false)) {
            resetSourceApplication();
            return;
        }
        Bundle appLinkData = AppLinks.getAppLinkData(intent);
        if (appLinkData == null) {
            resetSourceApplication();
            return;
        }
        isOpenedByAppLink = true;
        Bundle bundle = appLinkData.getBundle("referer_app_link");
        if (bundle == null) {
            sourceApplication = null;
            return;
        }
        sourceApplication = bundle.getString("package");
        intent.putExtra(SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, true);
    }

    static void setSourceApplication(String str, boolean z) {
        sourceApplication = str;
        isOpenedByAppLink = z;
    }

    static String getSourceApplication() {
        String str = isOpenedByAppLink ? "Applink" : "Unclassified";
        return sourceApplication != null ? str + "(" + sourceApplication + ")" : str;
    }

    static void resetSourceApplication() {
        sourceApplication = null;
        isOpenedByAppLink = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor getAnalyticsExecutor() {
        if (backgroundExecutor == null) {
            initializeTimersIfNeeded();
        }
        return backgroundExecutor;
    }

    public static String getAnonymousAppDeviceGUID(Context context) {
        if (anonymousAppDeviceGUID == null) {
            synchronized (staticLock) {
                if (anonymousAppDeviceGUID == null) {
                    String string = context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).getString("anonymousAppDeviceGUID", null);
                    anonymousAppDeviceGUID = string;
                    if (string == null) {
                        anonymousAppDeviceGUID = "XZ" + UUID.randomUUID().toString();
                        context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).edit().putString("anonymousAppDeviceGUID", anonymousAppDeviceGUID).apply();
                    }
                }
            }
        }
        return anonymousAppDeviceGUID;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class PersistedAppSessionInfo {
        private static final String PERSISTED_SESSION_INFO_FILENAME = "AppEventsLogger.persistedsessioninfo";
        private static Map<AccessTokenAppIdPair, FacebookTimeSpentData> appSessionInfoMap = null;
        private static boolean hasChanges = false;
        private static boolean isLoaded = false;
        private static final Object staticLock = new Object();
        private static final Runnable appSessionInfoFlushRunnable = new Runnable() { // from class: com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.1
            @Override // java.lang.Runnable
            public void run() {
                PersistedAppSessionInfo.saveAppSessionInformation(FacebookSdk.getApplicationContext());
            }
        };

        PersistedAppSessionInfo() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:30:0x008d A[Catch: all -> 0x00b1, TryCatch #3 {, blocks: (B:4:0x0003, B:9:0x0026, B:11:0x0032, B:12:0x0039, B:13:0x003b, B:23:0x006a, B:25:0x0076, B:26:0x007d, B:28:0x0081, B:30:0x008d, B:31:0x0094, B:32:0x0098, B:33:0x0099, B:35:0x00a5, B:36:0x00ac, B:37:0x00af), top: B:46:0x0003 }] */
        /* JADX WARN: Type inference failed for: r9v12, types: [java.util.Map<com.facebook.appevents.AccessTokenAppIdPair, com.facebook.appevents.FacebookTimeSpentData>] */
        /* JADX WARN: Type inference failed for: r9v14, types: [java.util.Map<com.facebook.appevents.AccessTokenAppIdPair, com.facebook.appevents.FacebookTimeSpentData>, java.util.HashMap] */
        /* JADX WARN: Type inference failed for: r9v6, types: [java.util.Map<com.facebook.appevents.AccessTokenAppIdPair, com.facebook.appevents.FacebookTimeSpentData>] */
        /* JADX WARN: Type inference failed for: r9v8, types: [java.util.Map<com.facebook.appevents.AccessTokenAppIdPair, com.facebook.appevents.FacebookTimeSpentData>, java.util.HashMap] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static void restoreAppSessionInformation(android.content.Context r9) {
            java.io.ObjectInputStream r4;
            java.lang.Throwable r3;
            java.lang.Exception r3;
            synchronized (com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.staticLock) {
                if (!com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.isLoaded) {
                    java.io.ObjectInputStream r3 = null;
                    try {
                        try {
                            r4 = new java.io.ObjectInputStream(r9.openFileInput(com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.PERSISTED_SESSION_INFO_FILENAME));
                            try {
                                com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.appSessionInfoMap = (java.util.HashMap) r4.readObject();
                                com.facebook.internal.Logger.log(com.facebook.LoggingBehavior.APP_EVENTS, "AppEvents", "App session info loaded");
                                com.facebook.internal.Utility.closeQuietly(r4);
                                r9.deleteFile(com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.PERSISTED_SESSION_INFO_FILENAME);
                                ?? r9 = com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.appSessionInfoMap;
                                android.content.Context r9 = r9;
                                if (r9 == 0) {
                                    ?? r9 = new java.util.HashMap();
                                    com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.appSessionInfoMap = r9;
                                    r9 = r9;
                                }
                                com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.isLoaded = true;
                                r9 = r9;
                            } catch (java.io.FileNotFoundException unused) {
                                r3 = r4;
                                com.facebook.internal.Utility.closeQuietly(r3);
                                r9.deleteFile(com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.PERSISTED_SESSION_INFO_FILENAME);
                                if (com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.appSessionInfoMap == null) {
                                    com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.appSessionInfoMap = new java.util.HashMap();
                                }
                                com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.isLoaded = true;
                                com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.hasChanges = false;
                            } catch (java.lang.Exception e) {
                                r3 = e;
                                android.util.Log.w(com.facebook.appevents.AppEventsLogger.TAG, "Got unexpected exception restoring app session info: " + r3.toString());
                                com.facebook.internal.Utility.closeQuietly(r4);
                                r9.deleteFile(com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.PERSISTED_SESSION_INFO_FILENAME);
                                ?? r9 = com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.appSessionInfoMap;
                                android.content.Context r9 = r9;
                                if (r9 == 0) {
                                    ?? r9 = new java.util.HashMap();
                                    com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.appSessionInfoMap = r9;
                                    r9 = r9;
                                }
                                com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.isLoaded = true;
                                r9 = r9;
                                com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.hasChanges = false;
                            }
                        } catch (java.lang.Throwable th) {
                            r3 = th;
                            com.facebook.internal.Utility.closeQuietly(r4);
                            r9.deleteFile(com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.PERSISTED_SESSION_INFO_FILENAME);
                            if (com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.appSessionInfoMap == null) {
                                com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.appSessionInfoMap = new java.util.HashMap();
                            }
                            com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.isLoaded = true;
                            com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.hasChanges = false;
                            throw r3;
                        }
                    } catch (java.io.FileNotFoundException unused) {
                    } catch (java.lang.Exception r4) {
                        r4 = null;
                        r3 = r4;
                    } catch (java.lang.Throwable r4) {
                        r4 = null;
                        r3 = r4;
                        com.facebook.internal.Utility.closeQuietly(r4);
                        r9.deleteFile(com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.PERSISTED_SESSION_INFO_FILENAME);
                        if (com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.appSessionInfoMap == null) {
                        }
                        com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.isLoaded = true;
                        com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.hasChanges = false;
                        throw r3;
                    }
                    com.facebook.appevents.AppEventsLogger.PersistedAppSessionInfo.hasChanges = false;
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.String] */
        static void saveAppSessionInformation(Context context) {
            ObjectOutputStream objectOutputStream;
            synchronized (staticLock) {
                if (hasChanges) {
                    ObjectOutputStream objectOutputStream2 = null;
                    try {
                        try {
                            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(context.openFileOutput(PERSISTED_SESSION_INFO_FILENAME, 0)));
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Exception e) {
                        e = e;
                    }
                    try {
                        objectOutputStream.writeObject(appSessionInfoMap);
                        hasChanges = false;
                        ?? r1 = "AppEvents";
                        Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "App session info saved");
                        Utility.closeQuietly(objectOutputStream);
                        objectOutputStream2 = r1;
                    } catch (Exception e2) {
                        e = e2;
                        objectOutputStream2 = objectOutputStream;
                        Log.w(AppEventsLogger.TAG, "Got unexpected exception while writing app session info: " + e.toString());
                        Utility.closeQuietly(objectOutputStream2);
                        objectOutputStream2 = objectOutputStream2;
                    } catch (Throwable th2) {
                        th = th2;
                        objectOutputStream2 = objectOutputStream;
                        Utility.closeQuietly(objectOutputStream2);
                        throw th;
                    }
                }
            }
        }

        static void onResume(Context context, AccessTokenAppIdPair accessTokenAppIdPair, AppEventsLogger appEventsLogger, long j, String str) {
            synchronized (staticLock) {
                getTimeSpentData(context, accessTokenAppIdPair).onResume(appEventsLogger, j, str);
                onTimeSpentDataUpdate();
            }
        }

        static void onSuspend(Context context, AccessTokenAppIdPair accessTokenAppIdPair, AppEventsLogger appEventsLogger, long j) {
            synchronized (staticLock) {
                getTimeSpentData(context, accessTokenAppIdPair).onSuspend(appEventsLogger, j);
                onTimeSpentDataUpdate();
            }
        }

        private static FacebookTimeSpentData getTimeSpentData(Context context, AccessTokenAppIdPair accessTokenAppIdPair) {
            restoreAppSessionInformation(context);
            FacebookTimeSpentData facebookTimeSpentData = appSessionInfoMap.get(accessTokenAppIdPair);
            if (facebookTimeSpentData == null) {
                FacebookTimeSpentData facebookTimeSpentData2 = new FacebookTimeSpentData();
                appSessionInfoMap.put(accessTokenAppIdPair, facebookTimeSpentData2);
                return facebookTimeSpentData2;
            }
            return facebookTimeSpentData;
        }

        private static void onTimeSpentDataUpdate() {
            if (hasChanges) {
                return;
            }
            hasChanges = true;
            AppEventsLogger.backgroundExecutor.schedule(appSessionInfoFlushRunnable, 30L, TimeUnit.SECONDS);
        }
    }
}
