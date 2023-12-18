package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public class AttributionIdentifiers {
    private static final String ANDROID_ID_COLUMN_NAME = "androidid";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER = "com.facebook.katana.provider.AttributionIdProvider";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI = "com.facebook.wakizashi.provider.AttributionIdProvider";
    private static final int CONNECTION_RESULT_SUCCESS = 0;
    private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000;
    private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
    private static final String TAG = "com.facebook.internal.AttributionIdentifiers";
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String androidInstallerPackage;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;

    private static AttributionIdentifiers getAndroidId(Context context) {
        AttributionIdentifiers androidIdViaReflection = getAndroidIdViaReflection(context);
        if (androidIdViaReflection == null) {
            AttributionIdentifiers androidIdViaService = getAndroidIdViaService(context);
            return androidIdViaService == null ? new AttributionIdentifiers() : androidIdViaService;
        }
        return androidIdViaReflection;
    }

    private static AttributionIdentifiers getAndroidIdViaReflection(Context context) {
        Method methodQuietly;
        Object invokeMethodQuietly;
        try {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                Method methodQuietly2 = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
                if (methodQuietly2 == null) {
                    return null;
                }
                Object invokeMethodQuietly2 = Utility.invokeMethodQuietly(null, methodQuietly2, context);
                if (!(invokeMethodQuietly2 instanceof Integer) || ((Integer) invokeMethodQuietly2).intValue() != 0 || (methodQuietly = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class)) == null || (invokeMethodQuietly = Utility.invokeMethodQuietly(null, methodQuietly, context)) == null) {
                    return null;
                }
                Method methodQuietly3 = Utility.getMethodQuietly(invokeMethodQuietly.getClass(), "getId", new Class[0]);
                Method methodQuietly4 = Utility.getMethodQuietly(invokeMethodQuietly.getClass(), "isLimitAdTrackingEnabled", new Class[0]);
                if (methodQuietly3 != null && methodQuietly4 != null) {
                    AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                    attributionIdentifiers.androidAdvertiserId = (String) Utility.invokeMethodQuietly(invokeMethodQuietly, methodQuietly3, new Object[0]);
                    attributionIdentifiers.limitTracking = ((Boolean) Utility.invokeMethodQuietly(invokeMethodQuietly, methodQuietly4, new Object[0])).booleanValue();
                    return attributionIdentifiers;
                }
                return null;
            }
            throw new FacebookException("getAndroidId cannot be called on the main thread.");
        } catch (Exception e) {
            Utility.logd("android_id", e);
            return null;
        }
    }

    private static AttributionIdentifiers getAndroidIdViaService(Context context) {
        GoogleAdServiceConnection googleAdServiceConnection = new GoogleAdServiceConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, googleAdServiceConnection, 1)) {
            try {
                GoogleAdInfo googleAdInfo = new GoogleAdInfo(googleAdServiceConnection.getBinder());
                AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                attributionIdentifiers.androidAdvertiserId = googleAdInfo.getAdvertiserId();
                attributionIdentifiers.limitTracking = googleAdInfo.isTrackingLimited();
                return attributionIdentifiers;
            } catch (Exception e) {
                Utility.logd("android_id", e);
            } finally {
                context.unbindService(googleAdServiceConnection);
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0064 A[Catch: all -> 0x00ca, Exception -> 0x00cc, TryCatch #4 {Exception -> 0x00cc, all -> 0x00ca, blocks: (B:11:0x0031, B:13:0x0042, B:19:0x005e, B:21:0x0064, B:23:0x0068, B:25:0x006d, B:15:0x004a, B:17:0x0056), top: B:64:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0068 A[Catch: all -> 0x00ca, Exception -> 0x00cc, TryCatch #4 {Exception -> 0x00cc, all -> 0x00ca, blocks: (B:11:0x0031, B:13:0x0042, B:19:0x005e, B:21:0x0064, B:23:0x0068, B:25:0x006d, B:15:0x004a, B:17:0x0056), top: B:64:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006d A[Catch: all -> 0x00ca, Exception -> 0x00cc, TRY_LEAVE, TryCatch #4 {Exception -> 0x00cc, all -> 0x00ca, blocks: (B:11:0x0031, B:13:0x0042, B:19:0x005e, B:21:0x0064, B:23:0x0068, B:25:0x006d, B:15:0x004a, B:17:0x0056), top: B:64:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00f4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.facebook.internal.AttributionIdentifiers getAttributionIdentifiers(android.content.Context r12) {
        android.database.Cursor r0;
        android.net.Uri r6;
        android.net.Uri r5;
        java.lang.String r5;
        if (android.os.Looper.myLooper() == android.os.Looper.getMainLooper()) {
            android.util.Log.e(com.facebook.internal.AttributionIdentifiers.TAG, "getAttributionIdentifiers should not be called from the main thread");
        }
        if (com.facebook.internal.AttributionIdentifiers.recentlyFetchedIdentifiers != null) {
            long r3 = java.lang.System.currentTimeMillis();
            com.facebook.internal.AttributionIdentifiers r5 = com.facebook.internal.AttributionIdentifiers.recentlyFetchedIdentifiers;
            if (r3 - r5.fetchTime < com.facebook.internal.AttributionIdentifiers.IDENTIFIER_REFRESH_INTERVAL_MILLIS) {
                return r5;
            }
        }
        com.facebook.internal.AttributionIdentifiers r3 = getAndroidId(r12);
        android.database.Cursor r4 = null;
        try {
            java.lang.String[] r7 = {com.facebook.internal.AttributionIdentifiers.ATTRIBUTION_ID_COLUMN_NAME, com.facebook.internal.AttributionIdentifiers.ANDROID_ID_COLUMN_NAME, com.facebook.internal.AttributionIdentifiers.LIMIT_TRACKING_COLUMN_NAME};
            if (r12.getPackageManager().resolveContentProvider(com.facebook.internal.AttributionIdentifiers.ATTRIBUTION_ID_CONTENT_PROVIDER, 0) != null) {
                r5 = android.net.Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
            } else if (r12.getPackageManager().resolveContentProvider(com.facebook.internal.AttributionIdentifiers.ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI, 0) != null) {
                r5 = android.net.Uri.parse("content://com.facebook.wakizashi.provider.AttributionIdProvider");
            } else {
                r6 = null;
                r5 = getInstallerPackageName(r12);
                if (r5 != null) {
                    r3.androidInstallerPackage = r5;
                }
                if (r6 != null) {
                    return cacheAndReturnIdentifiers(r3);
                }
                android.database.Cursor r12 = r12.getContentResolver().query(r6, r7, null, null, null);
                if (r12 != null) {
                    try {
                        if (r12.moveToFirst()) {
                            int r2 = r12.getColumnIndex(com.facebook.internal.AttributionIdentifiers.ATTRIBUTION_ID_COLUMN_NAME);
                            int r1 = r12.getColumnIndex(com.facebook.internal.AttributionIdentifiers.ANDROID_ID_COLUMN_NAME);
                            int r0 = r12.getColumnIndex(com.facebook.internal.AttributionIdentifiers.LIMIT_TRACKING_COLUMN_NAME);
                            r3.attributionId = r12.getString(r2);
                            if (r1 > 0 && r0 > 0 && r3.getAndroidAdvertiserId() == null) {
                                r3.androidAdvertiserId = r12.getString(r1);
                                r3.limitTracking = java.lang.Boolean.parseBoolean(r12.getString(r0));
                            }
                            if (r12 != null) {
                                r12.close();
                            }
                            return cacheAndReturnIdentifiers(r3);
                        }
                    } catch (java.lang.Exception r0) {
                        r0 = r12;
                        r12 = r0;
                        try {
                            com.facebook.internal.Utility.logd(com.facebook.internal.AttributionIdentifiers.TAG, "Caught unexpected exception in getAttributionId(): " + r12.toString());
                            if (r0 != null) {
                                r0.close();
                            }
                            return null;
                        } catch (java.lang.Throwable th) {
                            r12 = th;
                            r4 = r0;
                            if (r4 != null) {
                                r4.close();
                            }
                            throw r12;
                        }
                    } catch (java.lang.Throwable r0) {
                        r4 = r12;
                        r12 = r0;
                        if (r4 != null) {
                        }
                        throw r12;
                    }
                }
                com.facebook.internal.AttributionIdentifiers r0 = cacheAndReturnIdentifiers(r3);
                if (r12 != null) {
                    r12.close();
                }
                return r0;
            }
            r6 = r5;
            r5 = getInstallerPackageName(r12);
            if (r5 != null) {
            }
            if (r6 != null) {
            }
        } catch (java.lang.Exception e) {
            r12 = e;
            r0 = null;
        } catch (java.lang.Throwable th) {
            r12 = th;
        }
    }

    public static AttributionIdentifiers getCachedIdentifiers() {
        return recentlyFetchedIdentifiers;
    }

    private static AttributionIdentifiers cacheAndReturnIdentifiers(AttributionIdentifiers attributionIdentifiers) {
        attributionIdentifiers.fetchTime = System.currentTimeMillis();
        recentlyFetchedIdentifiers = attributionIdentifiers;
        return attributionIdentifiers;
    }

    public String getAttributionId() {
        return this.attributionId;
    }

    public String getAndroidAdvertiserId() {
        if (FacebookSdk.isInitialized() && FacebookSdk.getAdvertiserIDCollectionEnabled()) {
            return this.androidAdvertiserId;
        }
        return null;
    }

    public String getAndroidInstallerPackage() {
        return this.androidInstallerPackage;
    }

    public boolean isTrackingLimited() {
        return this.limitTracking;
    }

    private static String getInstallerPackageName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getInstallerPackageName(context.getPackageName());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class GoogleAdServiceConnection implements ServiceConnection {
        private AtomicBoolean consumed;
        private final BlockingQueue<IBinder> queue;

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        private GoogleAdServiceConnection() {
            this.consumed = new AtomicBoolean(false);
            this.queue = new LinkedBlockingDeque();
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                try {
                    this.queue.put(iBinder);
                } catch (InterruptedException unused) {
                }
            }
        }

        public IBinder getBinder() throws InterruptedException {
            if (this.consumed.compareAndSet(true, true)) {
                throw new IllegalStateException("Binder already consumed");
            }
            return this.queue.take();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class GoogleAdInfo implements IInterface {
        private static final int FIRST_TRANSACTION_CODE = 1;
        private static final int SECOND_TRANSACTION_CODE = 2;
        private IBinder binder;

        GoogleAdInfo(IBinder iBinder) {
            this.binder = iBinder;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.binder;
        }

        public String getAdvertiserId() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean isTrackingLimited() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.binder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readInt() != 0;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
