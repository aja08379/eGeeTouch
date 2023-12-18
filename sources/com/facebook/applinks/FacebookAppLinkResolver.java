package com.facebook.applinks;

import android.net.Uri;
import android.os.Bundle;
import bolts.AppLink;
import bolts.AppLinkResolver;
import bolts.Continuation;
import bolts.Task;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class FacebookAppLinkResolver implements AppLinkResolver {
    private static final String APP_LINK_ANDROID_TARGET_KEY = "android";
    private static final String APP_LINK_KEY = "app_links";
    private static final String APP_LINK_TARGET_APP_NAME_KEY = "app_name";
    private static final String APP_LINK_TARGET_CLASS_KEY = "class";
    private static final String APP_LINK_TARGET_PACKAGE_KEY = "package";
    private static final String APP_LINK_TARGET_SHOULD_FALLBACK_KEY = "should_fallback";
    private static final String APP_LINK_TARGET_URL_KEY = "url";
    private static final String APP_LINK_WEB_TARGET_KEY = "web";
    private final HashMap<Uri, AppLink> cachedAppLinks = new HashMap<>();

    @Override // bolts.AppLinkResolver
    public Task<AppLink> getAppLinkFromUrlInBackground(final Uri uri) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(uri);
        return getAppLinkFromUrlsInBackground(arrayList).onSuccess(new Continuation<Map<Uri, AppLink>, AppLink>() { // from class: com.facebook.applinks.FacebookAppLinkResolver.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // bolts.Continuation
            public AppLink then(Task<Map<Uri, AppLink>> task) throws Exception {
                return task.getResult().get(uri);
            }
        });
    }

    public Task<Map<Uri, AppLink>> getAppLinkFromUrlsInBackground(List<Uri> list) {
        AppLink appLink;
        final HashMap hashMap = new HashMap();
        final HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        for (Uri uri : list) {
            synchronized (this.cachedAppLinks) {
                appLink = this.cachedAppLinks.get(uri);
            }
            if (appLink != null) {
                hashMap.put(uri, appLink);
            } else {
                if (!hashSet.isEmpty()) {
                    sb.append(',');
                }
                sb.append(uri.toString());
                hashSet.add(uri);
            }
        }
        if (hashSet.isEmpty()) {
            return Task.forResult(hashMap);
        }
        final Task.TaskCompletionSource create = Task.create();
        Bundle bundle = new Bundle();
        bundle.putString("ids", sb.toString());
        bundle.putString(GraphRequest.FIELDS_PARAM, String.format("%s.fields(%s,%s)", "app_links", "android", "web"));
        new GraphRequest(AccessToken.getCurrentAccessToken(), "", bundle, null, new GraphRequest.Callback() { // from class: com.facebook.applinks.FacebookAppLinkResolver.2
            /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
            @Override // com.facebook.GraphRequest.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onCompleted(com.facebook.GraphResponse r9) {
                com.facebook.FacebookRequestError r0 = r9.getError();
                if (r0 != null) {
                    r2.setError(r0.getException());
                    return;
                }
                org.json.JSONObject r9 = r9.getJSONObject();
                if (r9 == null) {
                    r2.setResult(r3);
                    return;
                }
                java.util.Iterator r0 = r4.iterator();
                while (r0.hasNext()) {
                    android.net.Uri r1 = (android.net.Uri) r0.next();
                    if (r9.has(r1.toString())) {
                        try {
                            org.json.JSONObject r2 = r9.getJSONObject(r1.toString()).getJSONObject("app_links");
                            org.json.JSONArray r3 = r2.getJSONArray("android");
                            int r4 = r3.length();
                            java.util.ArrayList r5 = new java.util.ArrayList(r4);
                            for (int r6 = 0; r6 < r4; r6++) {
                                bolts.AppLink.Target r7 = com.facebook.applinks.FacebookAppLinkResolver.getAndroidTargetFromJson(r3.getJSONObject(r6));
                                if (r7 != null) {
                                    r5.add(r7);
                                }
                            }
                            bolts.AppLink r3 = new bolts.AppLink(r1, r5, com.facebook.applinks.FacebookAppLinkResolver.getWebFallbackUriFromJson(r1, r2));
                            r3.put(r1, r3);
                            synchronized (com.facebook.applinks.FacebookAppLinkResolver.this.cachedAppLinks) {
                                com.facebook.applinks.FacebookAppLinkResolver.this.cachedAppLinks.put(r1, r3);
                            }
                        } catch (org.json.JSONException unused) {
                        }
                    }
                    while (r0.hasNext()) {
                    }
                }
                r2.setResult(r3);
            }
        }).executeAsync();
        return create.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AppLink.Target getAndroidTargetFromJson(JSONObject jSONObject) {
        String tryGetStringFromJson = tryGetStringFromJson(jSONObject, APP_LINK_TARGET_PACKAGE_KEY, null);
        if (tryGetStringFromJson == null) {
            return null;
        }
        String tryGetStringFromJson2 = tryGetStringFromJson(jSONObject, APP_LINK_TARGET_CLASS_KEY, null);
        String tryGetStringFromJson3 = tryGetStringFromJson(jSONObject, "app_name", null);
        String tryGetStringFromJson4 = tryGetStringFromJson(jSONObject, "url", null);
        return new AppLink.Target(tryGetStringFromJson, tryGetStringFromJson2, tryGetStringFromJson4 != null ? Uri.parse(tryGetStringFromJson4) : null, tryGetStringFromJson3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Uri getWebFallbackUriFromJson(Uri uri, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("web");
            if (tryGetBooleanFromJson(jSONObject2, APP_LINK_TARGET_SHOULD_FALLBACK_KEY, true)) {
                String tryGetStringFromJson = tryGetStringFromJson(jSONObject2, "url", null);
                Uri parse = tryGetStringFromJson != null ? Uri.parse(tryGetStringFromJson) : null;
                return parse != null ? parse : uri;
            }
            return null;
        } catch (JSONException unused) {
            return uri;
        }
    }

    private static String tryGetStringFromJson(JSONObject jSONObject, String str, String str2) {
        try {
            return jSONObject.getString(str);
        } catch (JSONException unused) {
            return str2;
        }
    }

    private static boolean tryGetBooleanFromJson(JSONObject jSONObject, String str, boolean z) {
        try {
            return jSONObject.getBoolean(str);
        } catch (JSONException unused) {
            return z;
        }
    }
}
