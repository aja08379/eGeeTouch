package com.facebook.login;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentActivity;
import com.facebook.AccessTokenSource;
import com.facebook.CustomTabMainActivity;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.login.LoginClient;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class CustomTabLoginMethodHandler extends WebLoginMethodHandler {
    private static final int API_EC_DIALOG_CANCEL = 4201;
    private static final int CHALLENGE_LENGTH = 20;
    private static final String[] CHROME_PACKAGES = {"com.android.chrome", "com.chrome.beta", "com.chrome.dev"};
    public static final Parcelable.Creator<CustomTabLoginMethodHandler> CREATOR = new Parcelable.Creator() { // from class: com.facebook.login.CustomTabLoginMethodHandler.1
        @Override // android.os.Parcelable.Creator
        public CustomTabLoginMethodHandler createFromParcel(Parcel parcel) {
            return new CustomTabLoginMethodHandler(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public CustomTabLoginMethodHandler[] newArray(int i) {
            return new CustomTabLoginMethodHandler[i];
        }
    };
    private static final String CUSTOM_TABS_SERVICE_ACTION = "android.support.customtabs.action.CustomTabsService";
    private static final int CUSTOM_TAB_REQUEST_CODE = 1;
    private String currentPackage;
    private String expectedChallenge;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.facebook.login.LoginMethodHandler
    String getNameForLogging() {
        return "custom_tab";
    }

    @Override // com.facebook.login.WebLoginMethodHandler
    protected String getSSODevice() {
        return "chrome_custom_tab";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CustomTabLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
        this.expectedChallenge = Utility.generateRandomString(20);
    }

    @Override // com.facebook.login.WebLoginMethodHandler
    AccessTokenSource getTokenSource() {
        return AccessTokenSource.CHROME_CUSTOM_TAB;
    }

    @Override // com.facebook.login.LoginMethodHandler
    boolean tryAuthorize(LoginClient.Request request) {
        if (isCustomTabsAllowed()) {
            Bundle addExtraParameters = addExtraParameters(getParameters(request), request);
            Intent intent = new Intent(this.loginClient.getActivity(), CustomTabMainActivity.class);
            intent.putExtra(CustomTabMainActivity.EXTRA_PARAMS, addExtraParameters);
            intent.putExtra(CustomTabMainActivity.EXTRA_CHROME_PACKAGE, getChromePackage());
            this.loginClient.getFragment().startActivityForResult(intent, 1);
            return true;
        }
        return false;
    }

    private boolean isCustomTabsAllowed() {
        return isCustomTabsEnabled() && getChromePackage() != null && isCustomTabsCompatibleWithAutofill() && Validate.hasCustomTabRedirectActivity(FacebookSdk.getApplicationContext());
    }

    private boolean isCustomTabsEnabled() {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(Utility.getMetadataApplicationId(this.loginClient.getActivity()));
        return appSettingsWithoutQuery != null && appSettingsWithoutQuery.getCustomTabsEnabled();
    }

    private String getChromePackage() {
        String str = this.currentPackage;
        if (str != null) {
            return str;
        }
        FragmentActivity activity = this.loginClient.getActivity();
        List<ResolveInfo> queryIntentServices = activity.getPackageManager().queryIntentServices(new Intent("android.support.customtabs.action.CustomTabsService"), 0);
        if (queryIntentServices != null) {
            HashSet hashSet = new HashSet(Arrays.asList(CHROME_PACKAGES));
            for (ResolveInfo resolveInfo : queryIntentServices) {
                ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                if (serviceInfo != null && hashSet.contains(serviceInfo.packageName)) {
                    String str2 = serviceInfo.packageName;
                    this.currentPackage = str2;
                    return str2;
                }
            }
            return null;
        }
        return null;
    }

    private boolean isCustomTabsCompatibleWithAutofill() {
        return !Utility.isAutofillAvailable(this.loginClient.getActivity());
    }

    @Override // com.facebook.login.LoginMethodHandler
    boolean onActivityResult(int i, int i2, Intent intent) {
        if (i != 1) {
            return super.onActivityResult(i, i2, intent);
        }
        LoginClient.Request pendingRequest = this.loginClient.getPendingRequest();
        if (i2 == -1) {
            onCustomTabComplete(intent.getStringExtra(CustomTabMainActivity.EXTRA_URL), pendingRequest);
            return true;
        }
        super.onComplete(pendingRequest, null, new FacebookOperationCanceledException());
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void onCustomTabComplete(java.lang.String r7, com.facebook.login.LoginClient.Request r8) {
        int r3;
        if (r7 == null || !r7.startsWith(com.facebook.CustomTabMainActivity.getRedirectUrl())) {
            return;
        }
        android.net.Uri r7 = android.net.Uri.parse(r7);
        android.os.Bundle r0 = com.facebook.internal.Utility.parseUrlQueryString(r7.getQuery());
        r0.putAll(com.facebook.internal.Utility.parseUrlQueryString(r7.getFragment()));
        if (!validateChallengeParam(r0)) {
            super.onComplete(r8, null, new com.facebook.FacebookException("Invalid state parameter"));
            return;
        }
        java.lang.String r7 = r0.getString("error");
        if (r7 == null) {
            r7 = r0.getString(com.facebook.internal.NativeProtocol.BRIDGE_ARG_ERROR_TYPE);
        }
        java.lang.String r2 = r0.getString("error_msg");
        if (r2 == null) {
            r2 = r0.getString(com.facebook.internal.AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE);
        }
        if (r2 == null) {
            r2 = r0.getString(com.facebook.internal.NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
        }
        java.lang.String r3 = r0.getString(com.facebook.internal.NativeProtocol.BRIDGE_ARG_ERROR_CODE);
        if (!com.facebook.internal.Utility.isNullOrEmpty(r3)) {
            try {
                r3 = java.lang.Integer.parseInt(r3);
            } catch (java.lang.NumberFormatException unused) {
            }
            if (!com.facebook.internal.Utility.isNullOrEmpty(r7) && com.facebook.internal.Utility.isNullOrEmpty(r2) && r3 == -1) {
                super.onComplete(r8, r0, null);
                return;
            } else if (r7 == null && (r7.equals("access_denied") || r7.equals("OAuthAccessDeniedException"))) {
                super.onComplete(r8, null, new com.facebook.FacebookOperationCanceledException());
                return;
            } else if (r3 != com.facebook.login.CustomTabLoginMethodHandler.API_EC_DIALOG_CANCEL) {
                super.onComplete(r8, null, new com.facebook.FacebookOperationCanceledException());
                return;
            } else {
                super.onComplete(r8, null, new com.facebook.FacebookServiceException(new com.facebook.FacebookRequestError(r3, r7, r2), r2));
                return;
            }
        }
        r3 = -1;
        if (!com.facebook.internal.Utility.isNullOrEmpty(r7)) {
        }
        if (r7 == null) {
        }
        if (r3 != com.facebook.login.CustomTabLoginMethodHandler.API_EC_DIALOG_CANCEL) {
        }
    }

    @Override // com.facebook.login.LoginMethodHandler
    protected void putChallengeParam(JSONObject jSONObject) throws JSONException {
        jSONObject.put("7_challenge", this.expectedChallenge);
    }

    private boolean validateChallengeParam(Bundle bundle) {
        try {
            String string = bundle.getString("state");
            if (string == null) {
                return false;
            }
            return new JSONObject(string).getString("7_challenge").equals(this.expectedChallenge);
        } catch (JSONException unused) {
            return false;
        }
    }

    CustomTabLoginMethodHandler(Parcel parcel) {
        super(parcel);
        this.expectedChallenge = parcel.readString();
    }

    @Override // com.facebook.login.LoginMethodHandler, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.expectedChallenge);
    }
}
