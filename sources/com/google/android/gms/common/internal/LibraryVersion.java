package com.google.android.gms.common.internal;

import java.util.concurrent.ConcurrentHashMap;
/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
@Deprecated
/* loaded from: classes.dex */
public class LibraryVersion {
    private static final GmsLogger zza = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzb = new LibraryVersion();
    private ConcurrentHashMap zzc = new ConcurrentHashMap();

    protected LibraryVersion() {
    }

    public static LibraryVersion getInstance() {
        return zzb;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009a  */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v9 */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getVersion(java.lang.String r9) {
        java.lang.Object r3;
        java.io.InputStream r3;
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9, "Please provide a valid libraryName");
        if (r8.zzc.containsKey(r9)) {
            return (java.lang.String) r8.zzc.get(r9);
        }
        java.util.Properties r2 = new java.util.Properties();
        java.lang.Object r4 = null;
        r4 = 0;
        r4 = 0;
        java.io.InputStream r4 = null;
        try {
            try {
                r3 = com.google.android.gms.common.internal.LibraryVersion.class.getResourceAsStream(java.lang.String.format("/%s.properties", r9));
            } catch (java.lang.Throwable th) {
                r9 = th;
            }
        } catch (java.io.IOException e) {
            r2 = e;
            r3 = null;
        }
        try {
            if (r3 != null) {
                r2.load(r3);
                java.lang.String r4 = r2.getProperty(com.facebook.internal.ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, null);
                com.google.android.gms.common.internal.LibraryVersion.zza.v("LibraryVersion", r9 + " version is " + r4);
                r4 = r4;
            } else {
                com.google.android.gms.common.internal.LibraryVersion.zza.w("LibraryVersion", "Failed to get app version for libraryName: " + r9);
            }
            if (r3 != null) {
                com.google.android.gms.common.util.IOUtils.closeQuietly(r3);
            }
        } catch (java.io.IOException e) {
            r2 = e;
            java.lang.Object r7 = r4;
            r4 = r3;
            r3 = r7;
            com.google.android.gms.common.internal.LibraryVersion.zza.e("LibraryVersion", "Failed to get app version for libraryName: " + r9, r2);
            if (r4 != null) {
                com.google.android.gms.common.util.IOUtils.closeQuietly(r4);
            }
            r4 = r3;
            if (r4 == 0) {
            }
            r8.zzc.put(r9, r4);
            return r4;
        } catch (java.lang.Throwable th) {
            r9 = th;
            r4 = r3;
            if (r4 != null) {
                com.google.android.gms.common.util.IOUtils.closeQuietly(r4);
            }
            throw r9;
        }
        if (r4 == 0) {
            com.google.android.gms.common.internal.LibraryVersion.zza.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used");
            r4 = "UNKNOWN";
        }
        r8.zzc.put(r9, r4);
        return r4;
    }
}
