package com.google.android.gms.internal.p001firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbp  reason: invalid package */
/* loaded from: classes.dex */
public final class zzbp {
    private final Class zza;
    private zzbq zzc;
    private ConcurrentMap zzb = new ConcurrentHashMap();
    private zzjc zzd = zzjc.zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbp(Class cls, zzbo zzboVar) {
        this.zza = cls;
    }

    private final zzbp zze(Object obj, zzoe zzoeVar, boolean z) throws GeneralSecurityException {
        byte[] array;
        if (this.zzb == null) {
            throw new IllegalStateException("addPrimitive cannot be called after build");
        }
        if (zzoeVar.zzk() == 3) {
            ConcurrentMap concurrentMap = this.zzb;
            Integer valueOf = Integer.valueOf(zzoeVar.zza());
            if (zzoeVar.zze() == zzoy.RAW) {
                valueOf = null;
            }
            zzaw zza = zzgn.zzb().zza(zzgy.zza(zzoeVar.zzb().zzf(), zzoeVar.zzb().zze(), zzoeVar.zzb().zzb(), zzoeVar.zze(), valueOf), zzca.zza());
            int ordinal = zzoeVar.zze().ordinal();
            if (ordinal == 1) {
                array = ByteBuffer.allocate(5).put((byte) 1).putInt(zzoeVar.zza()).array();
            } else {
                if (ordinal != 2) {
                    if (ordinal == 3) {
                        array = zzas.zza;
                    } else if (ordinal != 4) {
                        throw new GeneralSecurityException("unknown output prefix type");
                    }
                }
                array = ByteBuffer.allocate(5).put((byte) 0).putInt(zzoeVar.zza()).array();
            }
            zzbq zzbqVar = new zzbq(obj, array, zzoeVar.zzk(), zzoeVar.zze(), zzoeVar.zza(), zza);
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzbqVar);
            zzbs zzbsVar = new zzbs(zzbqVar.zzf(), null);
            List list = (List) concurrentMap.put(zzbsVar, Collections.unmodifiableList(arrayList));
            if (list != null) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(list);
                arrayList2.add(zzbqVar);
                concurrentMap.put(zzbsVar, Collections.unmodifiableList(arrayList2));
            }
            if (z) {
                if (this.zzc == null) {
                    this.zzc = zzbqVar;
                } else {
                    throw new IllegalStateException("you cannot set two primary primitives");
                }
            }
            return this;
        }
        throw new GeneralSecurityException("only ENABLED key is allowed");
    }

    public final zzbp zza(Object obj, zzoe zzoeVar) throws GeneralSecurityException {
        zze(obj, zzoeVar, true);
        return this;
    }

    public final zzbp zzb(Object obj, zzoe zzoeVar) throws GeneralSecurityException {
        zze(obj, zzoeVar, false);
        return this;
    }

    public final zzbp zzc(zzjc zzjcVar) {
        if (this.zzb != null) {
            this.zzd = zzjcVar;
            return this;
        }
        throw new IllegalStateException("setAnnotations cannot be called after build");
    }

    public final zzbu zzd() throws GeneralSecurityException {
        ConcurrentMap concurrentMap = this.zzb;
        if (concurrentMap != null) {
            zzbu zzbuVar = new zzbu(concurrentMap, this.zzc, this.zzd, this.zza, null);
            this.zzb = null;
            return zzbuVar;
        }
        throw new IllegalStateException("build cannot be called twice");
    }
}
