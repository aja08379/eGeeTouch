package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadu  reason: invalid package */
/* loaded from: classes.dex */
final class zzadu extends zzady {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzadu() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzadu(zzadt zzadtVar) {
        super(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static List zzf(Object obj, long j, int i) {
        zzadr zzadrVar;
        List arrayList;
        List list = (List) zzafx.zzf(obj, j);
        if (list.isEmpty()) {
            if (list instanceof zzads) {
                arrayList = new zzadr(i);
            } else if (!(list instanceof zzaer) || !(list instanceof zzadk)) {
                arrayList = new ArrayList(i);
            } else {
                arrayList = ((zzadk) list).zzd(i);
            }
            zzafx.zzs(obj, j, arrayList);
            return arrayList;
        }
        if (zza.isAssignableFrom(list.getClass())) {
            ArrayList arrayList2 = new ArrayList(list.size() + i);
            arrayList2.addAll(list);
            zzafx.zzs(obj, j, arrayList2);
            zzadrVar = arrayList2;
        } else if (list instanceof zzafs) {
            zzadr zzadrVar2 = new zzadr(list.size() + i);
            zzadrVar2.addAll(zzadrVar2.size(), (zzafs) list);
            zzafx.zzs(obj, j, zzadrVar2);
            zzadrVar = zzadrVar2;
        } else if ((list instanceof zzaer) && (list instanceof zzadk)) {
            zzadk zzadkVar = (zzadk) list;
            if (zzadkVar.zzc()) {
                return list;
            }
            zzadk zzd = zzadkVar.zzd(list.size() + i);
            zzafx.zzs(obj, j, zzd);
            return zzd;
        } else {
            return list;
        }
        return zzadrVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzady
    public final List zza(Object obj, long j) {
        return zzf(obj, j, 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzady
    public final void zzb(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzafx.zzf(obj, j);
        if (list instanceof zzads) {
            unmodifiableList = ((zzads) list).zze();
        } else if (zza.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if (!(list instanceof zzaer) || !(list instanceof zzadk)) {
                unmodifiableList = Collections.unmodifiableList(list);
            } else {
                zzadk zzadkVar = (zzadk) list;
                if (zzadkVar.zzc()) {
                    zzadkVar.zzb();
                    return;
                }
                return;
            }
        }
        zzafx.zzs(obj, j, unmodifiableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzady
    public final void zzc(Object obj, Object obj2, long j) {
        List list = (List) zzafx.zzf(obj2, j);
        List zzf = zzf(obj, j, list.size());
        int size = zzf.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            zzf.addAll(list);
        }
        if (size > 0) {
            list = zzf;
        }
        zzafx.zzs(obj, j, list);
    }
}
