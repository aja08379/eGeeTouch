package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzem implements Runnable {
    final /* synthetic */ zzen zza;
    private final URL zzb;
    private final byte[] zzc;
    private final zzej zzd;
    private final String zze;
    private final Map zzf;

    public zzem(zzen zzenVar, String str, URL url, byte[] bArr, Map map, zzej zzejVar) {
        this.zza = zzenVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzejVar);
        this.zzb = url;
        this.zzc = bArr;
        this.zzd = zzejVar;
        this.zze = str;
        this.zzf = map;
    }

    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0100: MOVE  (r12 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY]), block:B:45:0x00fe */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0106: MOVE  (r12 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY]), block:B:47:0x0103 */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x016c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x012c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        java.lang.Throwable r5;
        int r9;
        java.net.HttpURLConnection r4;
        java.util.Map r12;
        java.io.IOException r5;
        int r9;
        java.util.Map r12;
        com.google.android.gms.measurement.internal.zzel r1;
        com.google.android.gms.measurement.internal.zzfo r0;
        java.io.IOException r10;
        com.google.android.gms.measurement.internal.zzen r3;
        java.net.URLConnection r4;
        int r8;
        java.util.Map r11;
        java.util.Map r11;
        java.io.InputStream r5;
        r14.zza.zzax();
        java.io.OutputStream r2 = null;
        try {
            r3 = r14.zza;
            r4 = r14.zzb.openConnection();
        } catch (java.io.IOException r3) {
            r5 = r3;
            r9 = 0;
            r4 = null;
            r12 = null;
        } catch (java.lang.Throwable r3) {
            r5 = r3;
            r9 = 0;
            r4 = null;
            r12 = null;
        }
        if (!(r4 instanceof java.net.HttpURLConnection)) {
            throw new java.io.IOException("Failed to obtain HTTP connection");
        }
        r4 = (java.net.HttpURLConnection) r4;
        r4.setDefaultUseCaches(false);
        r3.zzt.zzf();
        r4.setConnectTimeout(60000);
        r3.zzt.zzf();
        r4.setReadTimeout(61000);
        r4.setInstanceFollowRedirects(false);
        r4.setDoInput(true);
        try {
            java.util.Map r5 = r14.zzf;
            if (r5 != null) {
                for (java.util.Map.Entry r6 : r5.entrySet()) {
                    r4.addRequestProperty((java.lang.String) r6.getKey(), (java.lang.String) r6.getValue());
                }
            }
            if (r14.zzc != null) {
                byte[] r5 = r14.zza.zzf.zzu().zzy(r14.zzc);
                com.google.android.gms.measurement.internal.zzef r6 = r14.zza.zzt.zzay().zzj();
                int r7 = r5.length;
                r6.zzb("Uploading data. size", java.lang.Integer.valueOf(r7));
                r4.setDoOutput(true);
                r4.addRequestProperty("Content-Encoding", "gzip");
                r4.setFixedLengthStreamingMode(r7);
                r4.connect();
                java.io.OutputStream r3 = r4.getOutputStream();
                try {
                    r3.write(r5);
                    r3.close();
                } catch (java.io.IOException e) {
                    r5 = e;
                    r9 = 0;
                    r12 = null;
                    r2 = r3;
                    r10 = r5;
                    if (r2 != null) {
                        try {
                            r2.close();
                        } catch (java.io.IOException r1) {
                            r14.zza.zzt.zzay().zzd().zzc("Error closing HTTP compressed POST connection output stream. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r14.zze), r1);
                        }
                    }
                    if (r4 != null) {
                        r4.disconnect();
                    }
                    r0 = r14.zza.zzt.zzaz();
                    r1 = new com.google.android.gms.measurement.internal.zzel(r14.zze, r14.zzd, r9, r10, null, r12, null);
                    r0.zzp(r1);
                } catch (java.lang.Throwable th) {
                    r5 = th;
                    r9 = 0;
                    r12 = null;
                    r2 = r3;
                    if (r2 != null) {
                        try {
                            r2.close();
                        } catch (java.io.IOException r1) {
                            r14.zza.zzt.zzay().zzd().zzc("Error closing HTTP compressed POST connection output stream. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r14.zze), r1);
                        }
                    }
                    if (r4 != null) {
                        r4.disconnect();
                    }
                    r14.zza.zzt.zzaz().zzp(new com.google.android.gms.measurement.internal.zzel(r14.zze, r14.zzd, r9, null, null, r12, null));
                    throw r5;
                }
            }
            r8 = r4.getResponseCode();
        } catch (java.io.IOException r3) {
            r9 = 0;
            r12 = null;
            r10 = r3;
        } catch (java.lang.Throwable r3) {
            r9 = 0;
            r12 = null;
            r5 = r3;
        }
        try {
            try {
                java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11 = r4.getHeaderFields();
                try {
                    java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream();
                    r5 = r4.getInputStream();
                    try {
                        byte[] r6 = new byte[1024];
                        while (true) {
                            int r7 = r5.read(r6);
                            if (r7 <= 0) {
                                break;
                            }
                            r3.write(r6, 0, r7);
                        }
                        byte[] r10 = r3.toByteArray();
                        if (r5 != null) {
                            r5.close();
                        }
                        if (r4 != null) {
                            r4.disconnect();
                        }
                        r0 = r14.zza.zzt.zzaz();
                        r1 = new com.google.android.gms.measurement.internal.zzel(r14.zze, r14.zzd, r8, null, r10, r11, null);
                    } catch (java.lang.Throwable th) {
                        r1 = th;
                        if (r5 != null) {
                            r5.close();
                        }
                        throw r1;
                    }
                } catch (java.lang.Throwable th) {
                    r1 = th;
                    r5 = null;
                }
            } catch (java.io.IOException r5) {
                r12 = null;
                r10 = r5;
                r9 = r8;
                if (r2 != null) {
                }
                if (r4 != null) {
                }
                r0 = r14.zza.zzt.zzaz();
                r1 = new com.google.android.gms.measurement.internal.zzel(r14.zze, r14.zzd, r9, r10, null, r12, null);
                r0.zzp(r1);
            } catch (java.lang.Throwable th) {
                r5 = th;
                r12 = null;
                r9 = r8;
                if (r2 != null) {
                }
                if (r4 != null) {
                }
                r14.zza.zzt.zzaz().zzp(new com.google.android.gms.measurement.internal.zzel(r14.zze, r14.zzd, r9, null, null, r12, null));
                throw r5;
            }
        } catch (java.io.IOException r1) {
            r10 = r1;
            r9 = r8;
            r12 = r11;
            if (r2 != null) {
            }
            if (r4 != null) {
            }
            r0 = r14.zza.zzt.zzaz();
            r1 = new com.google.android.gms.measurement.internal.zzel(r14.zze, r14.zzd, r9, r10, null, r12, null);
            r0.zzp(r1);
        } catch (java.lang.Throwable r1) {
            r5 = r1;
            r9 = r8;
            r12 = r11;
            if (r2 != null) {
            }
            if (r4 != null) {
            }
            r14.zza.zzt.zzaz().zzp(new com.google.android.gms.measurement.internal.zzel(r14.zze, r14.zzd, r9, null, null, r12, null));
            throw r5;
        }
        r0.zzp(r1);
    }
}
