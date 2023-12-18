package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgf  reason: invalid package */
/* loaded from: classes.dex */
final class zzgf extends zzbn {
    private final String zza;
    private final zzoy zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgf(String str, zzoy zzoyVar, zzge zzgeVar) {
        this.zza = str;
        this.zzb = zzoyVar;
    }

    public final String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = this.zza;
        zzoy zzoyVar = this.zzb;
        zznr zznrVar = zznr.UNKNOWN_KEYMATERIAL;
        zzoy zzoyVar2 = zzoy.UNKNOWN_PREFIX;
        int ordinal = zzoyVar.ordinal();
        objArr[1] = ordinal != 1 ? ordinal != 2 ? ordinal != 3 ? ordinal != 4 ? "UNKNOWN" : "CRUNCHY" : "RAW" : "LEGACY" : "TINK";
        return String.format("(typeUrl=%s, outputPrefixType=%s)", objArr);
    }
}
