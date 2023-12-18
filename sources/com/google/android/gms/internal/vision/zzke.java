package com.google.android.gms.internal.vision;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
public final class zzke<K, V> extends LinkedHashMap<K, V> {
    private static final zzke zzb;
    private boolean zza;

    private zzke() {
        this.zza = true;
    }

    private zzke(Map<K, V> map) {
        super(map);
        this.zza = true;
    }

    public static <K, V> zzke<K, V> zza() {
        return zzb;
    }

    public final void zza(zzke<K, V> zzkeVar) {
        zze();
        if (zzkeVar.isEmpty()) {
            return;
        }
        putAll(zzkeVar);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zze();
        super.clear();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        zze();
        zzjf.zza(k);
        zzjf.zza(v);
        return (V) super.put(k, v);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        zze();
        for (K k : map.keySet()) {
            zzjf.zza(k);
            zzjf.zza(map.get(k));
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        zze();
        return (V) super.remove(obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005c A[RETURN] */
    @Override // java.util.AbstractMap, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean equals(java.lang.Object r7) {
        boolean r7;
        boolean r3;
        if (r7 instanceof java.util.Map) {
            java.util.Map r7 = (java.util.Map) r7;
            if (r6 != r7) {
                if (size() == r7.size()) {
                    for (java.util.Map.Entry<K, V> r3 : entrySet()) {
                        if (r7.containsKey(r3.getKey())) {
                            V r4 = r3.getValue();
                            java.lang.Object r3 = r7.get(r3.getKey());
                            if ((r4 instanceof byte[]) && (r3 instanceof byte[])) {
                                r3 = java.util.Arrays.equals((byte[]) r4, (byte[]) r3);
                                continue;
                            } else {
                                r3 = r4.equals(r3);
                                continue;
                            }
                            if (!r3) {
                            }
                        }
                    }
                }
                r7 = false;
                if (!r7) {
                    return true;
                }
            }
            r7 = true;
            if (!r7) {
            }
        }
        return false;
    }

    private static int zza(Object obj) {
        if (obj instanceof byte[]) {
            return zzjf.zzc((byte[]) obj);
        }
        if (obj instanceof zzje) {
            throw new UnsupportedOperationException();
        }
        return obj.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int i = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            i += zza(entry.getValue()) ^ zza(entry.getKey());
        }
        return i;
    }

    public final zzke<K, V> zzb() {
        return isEmpty() ? new zzke<>() : new zzke<>(this);
    }

    public final void zzc() {
        this.zza = false;
    }

    public final boolean zzd() {
        return this.zza;
    }

    private final void zze() {
        if (!this.zza) {
            throw new UnsupportedOperationException();
        }
    }

    static {
        zzke zzkeVar = new zzke();
        zzb = zzkeVar;
        zzkeVar.zza = false;
    }
}
