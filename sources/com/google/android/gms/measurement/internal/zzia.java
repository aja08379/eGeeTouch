package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzia implements Runnable {
    final /* synthetic */ zzib zza;
    private final URL zzb;
    private final String zzc;
    private final zzfp zzd;

    public zzia(zzib zzibVar, String str, URL url, byte[] bArr, Map map, zzfp zzfpVar, byte[] bArr2) {
        this.zza = zzibVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzfpVar);
        this.zzb = url;
        this.zzd = zzfpVar;
        this.zzc = str;
    }

    private final void zzb(final int i, final Exception exc, final byte[] bArr, final Map map) {
        this.zza.zzt.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzhz
            @Override // java.lang.Runnable
            public final void run() {
                zzia.this.zza(i, exc, bArr, map);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009f  */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.google.android.gms.measurement.internal.zzia] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        java.net.HttpURLConnection r3;
        ?? r4;
        ?? r4;
        int r2;
        java.lang.Throwable r0;
        java.io.IOException r0;
        java.io.InputStream r6;
        r10.zza.zzax();
        try {
            com.google.android.gms.measurement.internal.zzib r2 = r10.zza;
            java.net.URLConnection r3 = r10.zzb.openConnection();
            if (!(r3 instanceof java.net.HttpURLConnection)) {
                throw new java.io.IOException("Failed to obtain HTTP connection");
            }
            r3 = (java.net.HttpURLConnection) r3;
            r3.setDefaultUseCaches(false);
            r2.zzt.zzf();
            r4 = 60000;
            r4 = 60000;
            r3.setConnectTimeout(60000);
            r2.zzt.zzf();
            r3.setReadTimeout(61000);
            r3.setInstanceFollowRedirects(false);
            r3.setDoInput(true);
            try {
                r2 = r3.getResponseCode();
                try {
                    try {
                        java.util.Map<java.lang.String, java.util.List<java.lang.String>> r4 = r3.getHeaderFields();
                        try {
                            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream();
                            r6 = r3.getInputStream();
                            try {
                                byte[] r7 = new byte[1024];
                                while (true) {
                                    int r8 = r6.read(r7);
                                    if (r8 <= 0) {
                                        break;
                                    }
                                    r5.write(r7, 0, r8);
                                }
                                byte[] r0 = r5.toByteArray();
                                if (r6 != null) {
                                    r6.close();
                                }
                                if (r3 != null) {
                                    r3.disconnect();
                                }
                                zzb(r2, null, r0, r4);
                            } catch (java.lang.Throwable th) {
                                r0 = th;
                                if (r6 != null) {
                                    r6.close();
                                }
                                throw r0;
                            }
                        } catch (java.lang.Throwable th) {
                            r0 = th;
                            r6 = null;
                        }
                    } catch (java.io.IOException e) {
                        r0 = e;
                        if (r3 != null) {
                            r3.disconnect();
                        }
                        zzb(r2, r0, null, r4);
                    } catch (java.lang.Throwable th) {
                        r0 = th;
                        if (r3 != null) {
                            r3.disconnect();
                        }
                        zzb(r2, null, null, r4);
                        throw r0;
                    }
                } catch (java.io.IOException e) {
                    r0 = e;
                    r4 = 0;
                    if (r3 != null) {
                    }
                    zzb(r2, r0, null, r4);
                } catch (java.lang.Throwable th) {
                    r0 = th;
                    r4 = 0;
                    if (r3 != null) {
                    }
                    zzb(r2, null, null, r4);
                    throw r0;
                }
            } catch (java.io.IOException e) {
                r2 = e;
                r4 = 0;
                java.io.IOException r9 = r2;
                r2 = 0;
                r0 = r9;
                if (r3 != null) {
                }
                zzb(r2, r0, null, r4);
            } catch (java.lang.Throwable th) {
                r2 = th;
                r4 = 0;
                java.lang.Throwable r9 = r2;
                r2 = 0;
                r0 = r9;
                if (r3 != null) {
                }
                zzb(r2, null, null, r4);
                throw r0;
            }
        } catch (java.io.IOException e) {
            r2 = e;
            r3 = null;
            r4 = 0;
        } catch (java.lang.Throwable th) {
            r2 = th;
            r3 = null;
            r4 = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, Exception exc, byte[] bArr, Map map) {
        zzfp zzfpVar = this.zzd;
        zzfpVar.zza.zzC(this.zzc, i, exc, bArr, map);
    }
}
