package com.google.firebase.installations;

import android.util.Log;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
/* loaded from: classes2.dex */
class CrossProcessLock {
    private static final String TAG = "CrossProcessLock";
    private final FileChannel channel;
    private final FileLock lock;

    private CrossProcessLock(FileChannel fileChannel, FileLock fileLock) {
        this.channel = fileChannel;
        this.lock = fileLock;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0041 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x003c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.firebase.installations.CrossProcessLock acquire(android.content.Context r4, java.lang.String r5) {
        java.nio.channels.FileChannel r4;
        java.nio.channels.FileLock r5;
        try {
            r4 = new java.io.RandomAccessFile(new java.io.File(r4.getFilesDir(), r5), "rw").getChannel();
            try {
                r5 = r4.lock();
                try {
                    return new com.google.firebase.installations.CrossProcessLock(r4, r5);
                } catch (java.io.IOException e) {
                    r1 = e;
                    android.util.Log.e(com.google.firebase.installations.CrossProcessLock.TAG, "encountered error while creating and acquiring the lock, ignoring", r1);
                    if (r5 != null) {
                        try {
                            r5.release();
                        } catch (java.io.IOException unused) {
                        }
                    }
                    if (r4 != null) {
                        try {
                            r4.close();
                        } catch (java.io.IOException unused) {
                        }
                    }
                    return null;
                } catch (java.lang.Error e) {
                    r1 = e;
                    android.util.Log.e(com.google.firebase.installations.CrossProcessLock.TAG, "encountered error while creating and acquiring the lock, ignoring", r1);
                    if (r5 != null) {
                    }
                    if (r4 != null) {
                    }
                    return null;
                } catch (java.nio.channels.OverlappingFileLockException e) {
                    r1 = e;
                    android.util.Log.e(com.google.firebase.installations.CrossProcessLock.TAG, "encountered error while creating and acquiring the lock, ignoring", r1);
                    if (r5 != null) {
                    }
                    if (r4 != null) {
                    }
                    return null;
                }
            } catch (java.io.IOException | java.lang.Error | java.nio.channels.OverlappingFileLockException e) {
                r1 = e;
                r5 = null;
            }
        } catch (java.io.IOException | java.lang.Error | java.nio.channels.OverlappingFileLockException e) {
            r1 = e;
            r4 = null;
            r5 = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseAndClose() {
        try {
            this.lock.release();
            this.channel.close();
        } catch (IOException e) {
            Log.e(TAG, "encountered error while releasing, ignoring", e);
        }
    }
}
