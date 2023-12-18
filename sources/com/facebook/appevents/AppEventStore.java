package com.facebook.appevents;

import android.content.Context;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AccessTokenAppIdPair;
import com.facebook.appevents.AppEvent;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.Utility;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AppEventStore {
    private static final String PERSISTED_EVENTS_FILENAME = "AppEventsLogger.persistedevents";
    private static final String TAG = "com.facebook.appevents.AppEventStore";

    AppEventStore() {
    }

    public static synchronized void persistEvents(AccessTokenAppIdPair accessTokenAppIdPair, SessionEventsState sessionEventsState) {
        synchronized (AppEventStore.class) {
            AppEventUtility.assertIsNotMainThread();
            PersistedEvents readAndClearStore = readAndClearStore();
            if (readAndClearStore.containsKey(accessTokenAppIdPair)) {
                readAndClearStore.get(accessTokenAppIdPair).addAll(sessionEventsState.getEventsToPersist());
            } else {
                readAndClearStore.addEvents(accessTokenAppIdPair, sessionEventsState.getEventsToPersist());
            }
            saveEventsToDisk(readAndClearStore);
        }
    }

    public static synchronized void persistEvents(AppEventCollection appEventCollection) {
        synchronized (AppEventStore.class) {
            AppEventUtility.assertIsNotMainThread();
            PersistedEvents readAndClearStore = readAndClearStore();
            for (AccessTokenAppIdPair accessTokenAppIdPair : appEventCollection.keySet()) {
                readAndClearStore.addEvents(accessTokenAppIdPair, appEventCollection.get(accessTokenAppIdPair).getEventsToPersist());
            }
            saveEventsToDisk(readAndClearStore);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x008a A[Catch: all -> 0x0091, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:8:0x0021, B:9:0x0024, B:41:0x008a, B:12:0x002f, B:21:0x0047, B:22:0x004a, B:25:0x0055, B:26:0x0059, B:28:0x005e, B:29:0x0061, B:33:0x0073, B:32:0x006c, B:35:0x0075, B:36:0x0078, B:39:0x0083), top: B:48:0x0003, inners: #1, #4, #8, #10 }] */
    /* JADX WARN: Type inference failed for: r1v12, types: [java.lang.Throwable, java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r4v13, types: [java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized com.facebook.appevents.PersistedEvents readAndClearStore() {
        com.facebook.appevents.PersistedEvents r2;
        com.facebook.appevents.AppEventStore.MovedClassObjectInputStream r4;
        java.lang.Throwable r2;
        com.facebook.appevents.AppEventStore.MovedClassObjectInputStream r4;
        java.lang.String r3;
        java.lang.String r4;
        com.facebook.appevents.AppEventStore.MovedClassObjectInputStream r4;
        java.io.File r1;
        synchronized (com.facebook.appevents.AppEventStore.class) {
            com.facebook.appevents.internal.AppEventUtility.assertIsNotMainThread();
            android.content.Context r1 = com.facebook.FacebookSdk.getApplicationContext();
            r2 = null;
            try {
                try {
                    r4 = new com.facebook.appevents.AppEventStore.MovedClassObjectInputStream(new java.io.BufferedInputStream(r1.openFileInput(com.facebook.appevents.AppEventStore.PERSISTED_EVENTS_FILENAME)));
                    try {
                        com.facebook.appevents.PersistedEvents r3 = (com.facebook.appevents.PersistedEvents) r4.readObject();
                        com.facebook.internal.Utility.closeQuietly(r4);
                        try {
                            java.io.File r1 = r1.getFileStreamPath(com.facebook.appevents.AppEventStore.PERSISTED_EVENTS_FILENAME);
                            r1.delete();
                            r1 = r1;
                            r4 = r4;
                        } catch (java.lang.Exception r1) {
                            android.util.Log.w(com.facebook.appevents.AppEventStore.TAG, "Got unexpected exception when removing events file: ", r1);
                            r1 = r1;
                            r4 = "Got unexpected exception when removing events file: ";
                        }
                        r2 = r3;
                        r1 = r1;
                        r4 = r4;
                    } catch (java.io.FileNotFoundException unused) {
                        com.facebook.internal.Utility.closeQuietly(r4);
                        try {
                            r1.getFileStreamPath(com.facebook.appevents.AppEventStore.PERSISTED_EVENTS_FILENAME).delete();
                        } catch (java.lang.Exception e) {
                            r1 = e;
                            r3 = com.facebook.appevents.AppEventStore.TAG;
                            r4 = "Got unexpected exception when removing events file: ";
                            android.util.Log.w(r3, r4, r1);
                            if (r2 == null) {
                            }
                            return r2;
                        }
                        if (r2 == null) {
                        }
                        return r2;
                    } catch (java.lang.Exception e) {
                        r3 = e;
                        android.util.Log.w(com.facebook.appevents.AppEventStore.TAG, "Got unexpected exception while reading events: ", r3);
                        com.facebook.internal.Utility.closeQuietly(r4);
                        try {
                            java.io.File r1 = r1.getFileStreamPath(com.facebook.appevents.AppEventStore.PERSISTED_EVENTS_FILENAME);
                            r1.delete();
                            r1 = r1;
                            r4 = r4;
                        } catch (java.lang.Exception e) {
                            r1 = e;
                            r3 = com.facebook.appevents.AppEventStore.TAG;
                            r4 = "Got unexpected exception when removing events file: ";
                            android.util.Log.w(r3, r4, r1);
                            if (r2 == null) {
                            }
                            return r2;
                        }
                        if (r2 == null) {
                        }
                        return r2;
                    }
                } catch (java.lang.Throwable th) {
                    r2 = th;
                    com.facebook.internal.Utility.closeQuietly(r4);
                    try {
                        r1.getFileStreamPath(com.facebook.appevents.AppEventStore.PERSISTED_EVENTS_FILENAME).delete();
                    } catch (java.lang.Exception r1) {
                        android.util.Log.w(com.facebook.appevents.AppEventStore.TAG, "Got unexpected exception when removing events file: ", r1);
                    }
                    throw r2;
                }
            } catch (java.io.FileNotFoundException unused) {
                r4 = null;
            } catch (java.lang.Exception e) {
                r3 = e;
                r4 = null;
            } catch (java.lang.Throwable r3) {
                r4 = null;
                r2 = r3;
                com.facebook.internal.Utility.closeQuietly(r4);
                r1.getFileStreamPath(com.facebook.appevents.AppEventStore.PERSISTED_EVENTS_FILENAME).delete();
                throw r2;
            }
            if (r2 == null) {
                r2 = new com.facebook.appevents.PersistedEvents();
            }
        }
        return r2;
    }

    private static void saveEventsToDisk(PersistedEvents persistedEvents) {
        ObjectOutputStream objectOutputStream;
        Context applicationContext = FacebookSdk.getApplicationContext();
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(applicationContext.openFileOutput(PERSISTED_EVENTS_FILENAME, 0)));
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            objectOutputStream.writeObject(persistedEvents);
            Utility.closeQuietly(objectOutputStream);
        } catch (Exception e2) {
            e = e2;
            objectOutputStream2 = objectOutputStream;
            Log.w(TAG, "Got unexpected exception while persisting events: ", e);
            try {
                applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
            } catch (Exception unused) {
            }
            Utility.closeQuietly(objectOutputStream2);
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            Utility.closeQuietly(objectOutputStream2);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class MovedClassObjectInputStream extends ObjectInputStream {
        private static final String ACCESS_TOKEN_APP_ID_PAIR_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1";
        private static final String APP_EVENT_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AppEvent$SerializationProxyV1";

        public MovedClassObjectInputStream(InputStream inputStream) throws IOException {
            super(inputStream);
        }

        @Override // java.io.ObjectInputStream
        protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
            ObjectStreamClass readClassDescriptor = super.readClassDescriptor();
            if (readClassDescriptor.getName().equals(ACCESS_TOKEN_APP_ID_PAIR_SERIALIZATION_PROXY_V1_CLASS_NAME)) {
                return ObjectStreamClass.lookup(AccessTokenAppIdPair.SerializationProxyV1.class);
            }
            return readClassDescriptor.getName().equals(APP_EVENT_SERIALIZATION_PROXY_V1_CLASS_NAME) ? ObjectStreamClass.lookup(AppEvent.SerializationProxyV1.class) : readClassDescriptor;
        }
    }
}
