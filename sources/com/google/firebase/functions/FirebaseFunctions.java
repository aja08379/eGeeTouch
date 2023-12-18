package com.google.firebase.functions;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.security.ProviderInstaller;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.functions.FirebaseFunctionsException;
import com.google.firebase.functions.dagger.assisted.Assisted;
import com.google.firebase.functions.dagger.assisted.AssistedInject;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Executor;
import javax.inject.Named;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class FirebaseFunctions {
    private final ContextProvider contextProvider;
    private final String customDomain;
    private EmulatedServiceSettings emulatorSettings;
    private final Executor executor;
    private final String projectId;
    private final String region;
    private static final TaskCompletionSource<Void> providerInstalled = new TaskCompletionSource<>();
    private static boolean providerInstallStarted = false;
    private String urlFormat = "https://%1$s-%2$s.cloudfunctions.net/%3$s";
    private final OkHttpClient client = new OkHttpClient();
    private final Serializer serializer = new Serializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    @AssistedInject
    public FirebaseFunctions(Context context, @Named("projectId") String str, @Assisted String str2, ContextProvider contextProvider, Executor executor, Executor executor2) {
        boolean z;
        this.executor = executor;
        this.contextProvider = (ContextProvider) Preconditions.checkNotNull(contextProvider);
        this.projectId = (String) Preconditions.checkNotNull(str);
        try {
            new URL(str2);
            z = false;
        } catch (MalformedURLException unused) {
            z = true;
        }
        if (z) {
            this.region = str2;
            this.customDomain = null;
        } else {
            this.region = "us-central1";
            this.customDomain = str2;
        }
        maybeInstallProviders(context, executor2);
    }

    private static void maybeInstallProviders(final Context context, Executor executor) {
        synchronized (providerInstalled) {
            if (providerInstallStarted) {
                return;
            }
            providerInstallStarted = true;
            executor.execute(new Runnable() { // from class: com.google.firebase.functions.-$$Lambda$FirebaseFunctions$uILPKcGaA6x9Xnj2JHSb1gijpek
                @Override // java.lang.Runnable
                public final void run() {
                    ProviderInstaller.installIfNeededAsync(context, new ProviderInstaller.ProviderInstallListener() { // from class: com.google.firebase.functions.FirebaseFunctions.1
                        @Override // com.google.android.gms.security.ProviderInstaller.ProviderInstallListener
                        public void onProviderInstalled() {
                            FirebaseFunctions.providerInstalled.setResult(null);
                        }

                        @Override // com.google.android.gms.security.ProviderInstaller.ProviderInstallListener
                        public void onProviderInstallFailed(int i, Intent intent) {
                            Log.d("FirebaseFunctions", "Failed to update ssl context");
                            FirebaseFunctions.providerInstalled.setResult(null);
                        }
                    });
                }
            });
        }
    }

    public static FirebaseFunctions getInstance(FirebaseApp firebaseApp, String str) {
        Preconditions.checkNotNull(firebaseApp, "You must call FirebaseApp.initializeApp first.");
        Preconditions.checkNotNull(str);
        FunctionsMultiResourceComponent functionsMultiResourceComponent = (FunctionsMultiResourceComponent) firebaseApp.get(FunctionsMultiResourceComponent.class);
        Preconditions.checkNotNull(functionsMultiResourceComponent, "Functions component does not exist.");
        return functionsMultiResourceComponent.get(str);
    }

    public static FirebaseFunctions getInstance(FirebaseApp firebaseApp) {
        return getInstance(firebaseApp, "us-central1");
    }

    public static FirebaseFunctions getInstance(String str) {
        return getInstance(FirebaseApp.getInstance(), str);
    }

    public static FirebaseFunctions getInstance() {
        return getInstance(FirebaseApp.getInstance(), "us-central1");
    }

    public HttpsCallableReference getHttpsCallable(String str) {
        return new HttpsCallableReference(this, str);
    }

    public HttpsCallableReference getHttpsCallableFromUrl(URL url) {
        return new HttpsCallableReference(this, url);
    }

    URL getURL(String str) {
        EmulatedServiceSettings emulatedServiceSettings = this.emulatorSettings;
        if (emulatedServiceSettings != null) {
            this.urlFormat = "http://" + emulatedServiceSettings.getHost() + ":" + emulatedServiceSettings.getPort() + "/%2$s/%1$s/%3$s";
        }
        String format = String.format(this.urlFormat, this.region, this.projectId, str);
        if (this.customDomain != null && emulatedServiceSettings == null) {
            format = this.customDomain + "/" + str;
        }
        try {
            return new URL(format);
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void useFunctionsEmulator(String str) {
        Preconditions.checkNotNull(str, "origin cannot be null");
        this.urlFormat = str + "/%2$s/%1$s/%3$s";
    }

    public void useEmulator(String str, int i) {
        this.emulatorSettings = new EmulatedServiceSettings(str, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<HttpsCallableResult> call(final String str, final Object obj, final HttpsCallOptions httpsCallOptions) {
        return providerInstalled.getTask().continueWithTask(this.executor, new Continuation() { // from class: com.google.firebase.functions.-$$Lambda$FirebaseFunctions$eUU6VeLxoO_RnMeuWS7NO3DqJ5w
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirebaseFunctions.this.lambda$call$1$FirebaseFunctions(task);
            }
        }).continueWithTask(this.executor, new Continuation() { // from class: com.google.firebase.functions.-$$Lambda$FirebaseFunctions$Fr5yl8p2_j0ryEigLSsAqhdWRco
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirebaseFunctions.this.lambda$call$2$FirebaseFunctions(str, obj, httpsCallOptions, task);
            }
        });
    }

    public /* synthetic */ Task lambda$call$1$FirebaseFunctions(Task task) throws Exception {
        return this.contextProvider.getContext();
    }

    public /* synthetic */ Task lambda$call$2$FirebaseFunctions(String str, Object obj, HttpsCallOptions httpsCallOptions, Task task) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException(task.getException());
        }
        return call(getURL(str), obj, (HttpsCallableContext) task.getResult(), httpsCallOptions);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<HttpsCallableResult> call(final URL url, final Object obj, final HttpsCallOptions httpsCallOptions) {
        return providerInstalled.getTask().continueWithTask(this.executor, new Continuation() { // from class: com.google.firebase.functions.-$$Lambda$FirebaseFunctions$5GSwWwRsnQIpdKNKpFZCmcPM7tU
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirebaseFunctions.this.lambda$call$3$FirebaseFunctions(task);
            }
        }).continueWithTask(this.executor, new Continuation() { // from class: com.google.firebase.functions.-$$Lambda$FirebaseFunctions$vvkiphkp9KTW-dKVFEtUVH6_QeY
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirebaseFunctions.this.lambda$call$4$FirebaseFunctions(url, obj, httpsCallOptions, task);
            }
        });
    }

    public /* synthetic */ Task lambda$call$3$FirebaseFunctions(Task task) throws Exception {
        return this.contextProvider.getContext();
    }

    public /* synthetic */ Task lambda$call$4$FirebaseFunctions(URL url, Object obj, HttpsCallOptions httpsCallOptions, Task task) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException(task.getException());
        }
        return call(url, obj, (HttpsCallableContext) task.getResult(), httpsCallOptions);
    }

    private Task<HttpsCallableResult> call(URL url, Object obj, HttpsCallableContext httpsCallableContext, HttpsCallOptions httpsCallOptions) {
        Preconditions.checkNotNull(url, "url cannot be null");
        HashMap hashMap = new HashMap();
        hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, this.serializer.encode(obj));
        Request.Builder post = new Request.Builder().url(url).post(RequestBody.create(MediaType.parse("application/json"), new JSONObject(hashMap).toString()));
        if (httpsCallableContext.getAuthToken() != null) {
            post = post.header("Authorization", "Bearer " + httpsCallableContext.getAuthToken());
        }
        if (httpsCallableContext.getInstanceIdToken() != null) {
            post = post.header("Firebase-Instance-ID-Token", httpsCallableContext.getInstanceIdToken());
        }
        if (httpsCallableContext.getAppCheckToken() != null) {
            post = post.header("X-Firebase-AppCheck", httpsCallableContext.getAppCheckToken());
        }
        Call newCall = httpsCallOptions.apply(this.client).newCall(post.build());
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        newCall.enqueue(new Callback() { // from class: com.google.firebase.functions.FirebaseFunctions.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                if (iOException instanceof InterruptedIOException) {
                    taskCompletionSource.setException(new FirebaseFunctionsException(FirebaseFunctionsException.Code.DEADLINE_EXCEEDED.name(), FirebaseFunctionsException.Code.DEADLINE_EXCEEDED, null, iOException));
                    return;
                }
                taskCompletionSource.setException(new FirebaseFunctionsException(FirebaseFunctionsException.Code.INTERNAL.name(), FirebaseFunctionsException.Code.INTERNAL, null, iOException));
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                FirebaseFunctionsException.Code fromHttpStatus = FirebaseFunctionsException.Code.fromHttpStatus(response.code());
                String string = response.body().string();
                FirebaseFunctionsException fromResponse = FirebaseFunctionsException.fromResponse(fromHttpStatus, string, FirebaseFunctions.this.serializer);
                if (fromResponse != null) {
                    taskCompletionSource.setException(fromResponse);
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    Object opt = jSONObject.opt(ShareConstants.WEB_DIALOG_PARAM_DATA);
                    if (opt == null) {
                        opt = jSONObject.opt("result");
                    }
                    if (opt == null) {
                        taskCompletionSource.setException(new FirebaseFunctionsException("Response is missing data field.", FirebaseFunctionsException.Code.INTERNAL, null));
                        return;
                    }
                    taskCompletionSource.setResult(new HttpsCallableResult(FirebaseFunctions.this.serializer.decode(opt)));
                } catch (JSONException e) {
                    taskCompletionSource.setException(new FirebaseFunctionsException("Response is not valid JSON object.", FirebaseFunctionsException.Code.INTERNAL, null, e));
                }
            }
        });
        return taskCompletionSource.getTask();
    }
}
