package com.google.android.gms.internal.vision;

import java.util.Map;
import kotlin.UByte;
import kotlin.UShort;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
public final class zzes<K, V> extends zzef<K, V> {
    static final zzef<Object, Object> zza = new zzes(null, new Object[0], 0);
    private final transient Object zzb;
    private final transient Object[] zzc;
    private final transient int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0056, code lost:
        r2[r6] = (byte) r3;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0097, code lost:
        r2[r6] = (short) r3;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00cc, code lost:
        r2[r7] = r3;
        r1 = r1 + 1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [int[]] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r8v0, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <K, V> com.google.android.gms.internal.vision.zzes<K, V> zza(int r10, java.lang.Object[] r11) {
        byte[] r2;
        if (r10 == 0) {
            return (com.google.android.gms.internal.vision.zzes) com.google.android.gms.internal.vision.zzes.zza;
        }
        java.lang.Object r0 = null;
        int r1 = 0;
        if (r10 == 1) {
            com.google.android.gms.internal.vision.zzdq.zza(r11[0], r11[1]);
            return new com.google.android.gms.internal.vision.zzes<>(null, r11, 1);
        }
        com.google.android.gms.internal.vision.zzde.zzb(r10, r11.length >> 1);
        int r3 = com.google.android.gms.internal.vision.zzej.zza(r10);
        if (r10 == 1) {
            com.google.android.gms.internal.vision.zzdq.zza(r11[0], r11[1]);
        } else {
            int r0 = r3 - 1;
            if (r3 <= 128) {
                r2 = new byte[r3];
                java.util.Arrays.fill(r2, (byte) -1);
                while (r1 < r10) {
                    int r3 = r1 * 2;
                    java.lang.Object r4 = r11[r3];
                    java.lang.Object r5 = r11[r3 ^ 1];
                    com.google.android.gms.internal.vision.zzdq.zza(r4, r5);
                    int r6 = com.google.android.gms.internal.vision.zzec.zza(r4.hashCode());
                    while (true) {
                        int r6 = r6 & r0;
                        int r7 = r2[r6] & 255;
                        if (r7 == 255) {
                            break;
                        } else if (r11[r7].equals(r4)) {
                            throw zza(r4, r5, r11, r7);
                        } else {
                            r6 = r6 + 1;
                        }
                    }
                }
            } else if (r3 <= 32768) {
                r2 = new short[r3];
                java.util.Arrays.fill(r2, (short) -1);
                while (r1 < r10) {
                    int r3 = r1 * 2;
                    java.lang.Object r4 = r11[r3];
                    java.lang.Object r5 = r11[r3 ^ 1];
                    com.google.android.gms.internal.vision.zzdq.zza(r4, r5);
                    int r6 = com.google.android.gms.internal.vision.zzec.zza(r4.hashCode());
                    while (true) {
                        int r6 = r6 & r0;
                        int r7 = r2[r6] & kotlin.UShort.MAX_VALUE;
                        if (r7 == 65535) {
                            break;
                        } else if (r11[r7].equals(r4)) {
                            throw zza(r4, r5, r11, r7);
                        } else {
                            r6 = r6 + 1;
                        }
                    }
                }
            } else {
                r2 = new int[r3];
                java.util.Arrays.fill((int[]) r2, -1);
                while (r1 < r10) {
                    int r3 = r1 * 2;
                    java.lang.Object r5 = r11[r3];
                    java.lang.Object r6 = r11[r3 ^ 1];
                    com.google.android.gms.internal.vision.zzdq.zza(r5, r6);
                    int r7 = com.google.android.gms.internal.vision.zzec.zza(r5.hashCode());
                    while (true) {
                        int r7 = r7 & r0;
                        ?? r8 = r2[r7];
                        if (r8 == -1) {
                            break;
                        } else if (r11[r8].equals(r5)) {
                            throw zza(r5, r6, r11, r8);
                        } else {
                            r7 = r7 + 1;
                        }
                    }
                }
            }
            r0 = r2;
        }
        return new com.google.android.gms.internal.vision.zzes<>(r0, r11, r10);
    }

    private static IllegalArgumentException zza(Object obj, Object obj2, Object[] objArr, int i) {
        String valueOf = String.valueOf(obj);
        String valueOf2 = String.valueOf(obj2);
        String valueOf3 = String.valueOf(objArr[i]);
        String valueOf4 = String.valueOf(objArr[i ^ 1]);
        return new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 39 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length()).append("Multiple entries with same key: ").append(valueOf).append("=").append(valueOf2).append(" and ").append(valueOf3).append("=").append(valueOf4).toString());
    }

    private zzes(Object obj, Object[] objArr, int i) {
        this.zzb = obj;
        this.zzc = objArr;
        this.zzd = i;
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.vision.zzef, java.util.Map
    @NullableDecl
    public final V get(@NullableDecl Object obj) {
        Object obj2 = this.zzb;
        Object[] objArr = this.zzc;
        int i = this.zzd;
        if (obj == null) {
            return null;
        }
        if (i == 1) {
            if (objArr[0].equals(obj)) {
                return (V) objArr[1];
            }
            return null;
        } else if (obj2 == null) {
            return null;
        } else {
            if (obj2 instanceof byte[]) {
                byte[] bArr = (byte[]) obj2;
                int length = bArr.length - 1;
                int zza2 = zzec.zza(obj.hashCode());
                while (true) {
                    int i2 = zza2 & length;
                    int i3 = bArr[i2] & UByte.MAX_VALUE;
                    if (i3 == 255) {
                        return null;
                    }
                    if (objArr[i3].equals(obj)) {
                        return (V) objArr[i3 ^ 1];
                    }
                    zza2 = i2 + 1;
                }
            } else if (obj2 instanceof short[]) {
                short[] sArr = (short[]) obj2;
                int length2 = sArr.length - 1;
                int zza3 = zzec.zza(obj.hashCode());
                while (true) {
                    int i4 = zza3 & length2;
                    int i5 = sArr[i4] & UShort.MAX_VALUE;
                    if (i5 == 65535) {
                        return null;
                    }
                    if (objArr[i5].equals(obj)) {
                        return (V) objArr[i5 ^ 1];
                    }
                    zza3 = i4 + 1;
                }
            } else {
                int[] iArr = (int[]) obj2;
                int length3 = iArr.length - 1;
                int zza4 = zzec.zza(obj.hashCode());
                while (true) {
                    int i6 = zza4 & length3;
                    int i7 = iArr[i6];
                    if (i7 == -1) {
                        return null;
                    }
                    if (objArr[i7].equals(obj)) {
                        return (V) objArr[i7 ^ 1];
                    }
                    zza4 = i6 + 1;
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.vision.zzef
    final zzej<Map.Entry<K, V>> zza() {
        return new zzer(this, this.zzc, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.vision.zzef
    final zzej<K> zzb() {
        return new zzet(this, new zzew(this.zzc, 0, this.zzd));
    }

    @Override // com.google.android.gms.internal.vision.zzef
    final zzeb<V> zzc() {
        return new zzew(this.zzc, 1, this.zzd);
    }
}
