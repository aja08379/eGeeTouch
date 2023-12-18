package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.AppCheckTokenListener;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.inject.Provider;
import com.google.firebase.storage.internal.Util;
import java.io.UnsupportedEncodingException;
/* loaded from: classes2.dex */
public class FirebaseStorage {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String STORAGE_BUCKET_WITH_PATH_EXCEPTION = "The storage Uri cannot contain a path element.";
    private static final String STORAGE_URI_PARSE_EXCEPTION = "The storage Uri could not be parsed.";
    private static final String TAG = "FirebaseStorage";
    private EmulatedServiceSettings emulatorSettings;
    private final FirebaseApp mApp;
    private final Provider<InternalAppCheckTokenProvider> mAppCheckProvider;
    private final Provider<InternalAuthProvider> mAuthProvider;
    private final String mBucketName;
    private long sMaxUploadRetry = 600000;
    private long sMaxChunkUploadRetry = 60000;
    private long sMaxDownloadRetry = 600000;
    private long sMaxQueryRetry = 120000;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirebaseStorage(String str, FirebaseApp firebaseApp, Provider<InternalAuthProvider> provider, Provider<InternalAppCheckTokenProvider> provider2) {
        this.mBucketName = str;
        this.mApp = firebaseApp;
        this.mAuthProvider = provider;
        this.mAppCheckProvider = provider2;
        if (provider2 == null || provider2.get() == null) {
            return;
        }
        provider2.get().addAppCheckTokenListener(new AppCheckTokenListener() { // from class: com.google.firebase.storage.FirebaseStorage.1
            @Override // com.google.firebase.appcheck.interop.AppCheckTokenListener
            public void onAppCheckTokenChanged(AppCheckTokenResult appCheckTokenResult) {
            }
        });
    }

    private static FirebaseStorage getInstanceImpl(FirebaseApp firebaseApp, Uri uri) {
        String host = uri != null ? uri.getHost() : null;
        if (uri != null && !TextUtils.isEmpty(uri.getPath())) {
            throw new IllegalArgumentException(STORAGE_BUCKET_WITH_PATH_EXCEPTION);
        }
        Preconditions.checkNotNull(firebaseApp, "Provided FirebaseApp must not be null.");
        FirebaseStorageComponent firebaseStorageComponent = (FirebaseStorageComponent) firebaseApp.get(FirebaseStorageComponent.class);
        Preconditions.checkNotNull(firebaseStorageComponent, "Firebase Storage component is not present.");
        return firebaseStorageComponent.get(host);
    }

    public static FirebaseStorage getInstance() {
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        Preconditions.checkArgument(firebaseApp != null, "You must call FirebaseApp.initialize() first.");
        return getInstance(firebaseApp);
    }

    public static FirebaseStorage getInstance(String str) {
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        Preconditions.checkArgument(firebaseApp != null, "You must call FirebaseApp.initialize() first.");
        return getInstance(firebaseApp, str);
    }

    public static FirebaseStorage getInstance(FirebaseApp firebaseApp) {
        Preconditions.checkArgument(firebaseApp != null, "Null is not a valid value for the FirebaseApp.");
        String storageBucket = firebaseApp.getOptions().getStorageBucket();
        if (storageBucket == null) {
            return getInstanceImpl(firebaseApp, null);
        }
        try {
            return getInstanceImpl(firebaseApp, Util.normalize(firebaseApp, "gs://" + firebaseApp.getOptions().getStorageBucket()));
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Unable to parse bucket:" + storageBucket, e);
            throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
        }
    }

    public static FirebaseStorage getInstance(FirebaseApp firebaseApp, String str) {
        Preconditions.checkArgument(firebaseApp != null, "Null is not a valid value for the FirebaseApp.");
        Preconditions.checkArgument(str != null, "Null is not a valid value for the Firebase Storage URL.");
        if (!str.toLowerCase().startsWith("gs://")) {
            throw new IllegalArgumentException("Please use a gs:// URL for your Firebase Storage bucket.");
        }
        try {
            return getInstanceImpl(firebaseApp, Util.normalize(firebaseApp, str));
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Unable to parse url:" + str, e);
            throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
        }
    }

    public void useEmulator(String str, int i) {
        this.emulatorSettings = new EmulatedServiceSettings(str, i);
    }

    public long getMaxDownloadRetryTimeMillis() {
        return this.sMaxDownloadRetry;
    }

    public void setMaxDownloadRetryTimeMillis(long j) {
        this.sMaxDownloadRetry = j;
    }

    public long getMaxUploadRetryTimeMillis() {
        return this.sMaxUploadRetry;
    }

    public void setMaxUploadRetryTimeMillis(long j) {
        this.sMaxUploadRetry = j;
    }

    public long getMaxChunkUploadRetry() {
        return this.sMaxChunkUploadRetry;
    }

    public void setMaxChunkUploadRetry(long j) {
        this.sMaxChunkUploadRetry = j;
    }

    public long getMaxOperationRetryTimeMillis() {
        return this.sMaxQueryRetry;
    }

    public void setMaxOperationRetryTimeMillis(long j) {
        this.sMaxQueryRetry = j;
    }

    private String getBucketName() {
        return this.mBucketName;
    }

    public StorageReference getReference() {
        if (TextUtils.isEmpty(getBucketName())) {
            throw new IllegalStateException("FirebaseApp was not initialized with a bucket name.");
        }
        return getReference(new Uri.Builder().scheme("gs").authority(getBucketName()).path("/").build());
    }

    public StorageReference getReferenceFromUrl(String str) {
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "location must not be null or empty");
        String lowerCase = str.toLowerCase();
        if (lowerCase.startsWith("gs://") || lowerCase.startsWith("https://") || lowerCase.startsWith("http://")) {
            try {
                Uri normalize = Util.normalize(this.mApp, str);
                if (normalize == null) {
                    throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
                }
                return getReference(normalize);
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, "Unable to parse location:" + str, e);
                throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
            }
        }
        throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
    }

    public StorageReference getReference(String str) {
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "location must not be null or empty");
        String lowerCase = str.toLowerCase();
        if (lowerCase.startsWith("gs://") || lowerCase.startsWith("https://") || lowerCase.startsWith("http://")) {
            throw new IllegalArgumentException("location should not be a full URL.");
        }
        return getReference().child(str);
    }

    private StorageReference getReference(Uri uri) {
        Preconditions.checkNotNull(uri, "uri must not be null");
        String bucketName = getBucketName();
        Preconditions.checkArgument(TextUtils.isEmpty(bucketName) || uri.getAuthority().equalsIgnoreCase(bucketName), "The supplied bucketname does not match the storage bucket of the current instance.");
        return new StorageReference(uri, this);
    }

    public FirebaseApp getApp() {
        return this.mApp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalAuthProvider getAuthProvider() {
        Provider<InternalAuthProvider> provider = this.mAuthProvider;
        if (provider != null) {
            return provider.get();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalAppCheckTokenProvider getAppCheckProvider() {
        Provider<InternalAppCheckTokenProvider> provider = this.mAppCheckProvider;
        if (provider != null) {
            return provider.get();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EmulatedServiceSettings getEmulatorSettings() {
        return this.emulatorSettings;
    }
}
