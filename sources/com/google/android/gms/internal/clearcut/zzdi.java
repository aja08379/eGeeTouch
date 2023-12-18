package com.google.android.gms.internal.clearcut;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class zzdi<K, V> extends LinkedHashMap<K, V> {
    private static final zzdi zzme;
    private boolean zzfa;

    static {
        zzdi zzdiVar = new zzdi();
        zzme = zzdiVar;
        zzdiVar.zzfa = false;
    }

    private zzdi() {
        this.zzfa = true;
    }

    private zzdi(Map<K, V> map) {
        super(map);
        this.zzfa = true;
    }

    public static <K, V> zzdi<K, V> zzbz() {
        return zzme;
    }

    private final void zzcb() {
        if (!this.zzfa) {
            throw new UnsupportedOperationException();
        }
    }

    private static int zzf(Object obj) {
        if (obj instanceof byte[]) {
            return zzci.hashCode((byte[]) obj);
        }
        if (obj instanceof zzcj) {
            throw new UnsupportedOperationException();
        }
        return obj.hashCode();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzcb();
        super.clear();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
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

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int i = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            i += zzf(entry.getValue()) ^ zzf(entry.getKey());
        }
        return i;
    }

    public final boolean isMutable() {
        return this.zzfa;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        zzcb();
        zzci.checkNotNull(k);
        zzci.checkNotNull(v);
        return (V) super.put(k, v);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        zzcb();
        for (K k : map.keySet()) {
            zzci.checkNotNull(k);
            zzci.checkNotNull(map.get(k));
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        zzcb();
        return (V) super.remove(obj);
    }

    public final void zza(zzdi<K, V> zzdiVar) {
        zzcb();
        if (zzdiVar.isEmpty()) {
            return;
        }
        putAll(zzdiVar);
    }

    public final zzdi<K, V> zzca() {
        return isEmpty() ? new zzdi<>() : new zzdi<>(this);
    }

    public final void zzv() {
        this.zzfa = false;
    }
}
