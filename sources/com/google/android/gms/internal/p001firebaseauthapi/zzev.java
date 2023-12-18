package com.google.android.gms.internal.p001firebaseauthapi;

import com.egeetouch.egeetouch_manager.TaskManagement;
import java.security.GeneralSecurityException;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzev  reason: invalid package */
/* loaded from: classes.dex */
final class zzev implements zzau {
    private static final byte[] zza = new byte[0];
    private final zzey zzb;
    private final zzex zzc;
    private final zzet zzd;
    private final int zze;
    private final zzes zzf;

    private zzev(zzey zzeyVar, zzex zzexVar, zzes zzesVar, zzet zzetVar, int i, byte[] bArr) {
        this.zzb = zzeyVar;
        this.zzc = zzexVar;
        this.zzf = zzesVar;
        this.zzd = zzetVar;
        this.zze = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzev zzb(zznk zznkVar) throws GeneralSecurityException {
        int i;
        zzey zzc;
        if (!zznkVar.zzk()) {
            throw new IllegalArgumentException("HpkePrivateKey is missing public_key field.");
        }
        if (!zznkVar.zze().zzl()) {
            throw new IllegalArgumentException("HpkePrivateKey.public_key is missing params field.");
        }
        if (zznkVar.zzf().zzs()) {
            throw new IllegalArgumentException("HpkePrivateKey.private_key is empty.");
        }
        zznh zzb = zznkVar.zze().zzb();
        zzex zzb2 = zzez.zzb(zzb);
        zzes zzc2 = zzez.zzc(zzb);
        zzet zza2 = zzez.zza(zzb);
        int zzf = zzb.zzf();
        int i2 = zzf - 2;
        if (i2 == 1) {
            i = 32;
        } else if (i2 == 2) {
            i = 65;
        } else if (i2 == 3) {
            i = 97;
        } else if (i2 != 4) {
            throw new IllegalArgumentException("Unable to determine KEM-encoding length for ".concat(zznb.zza(zzf)));
        } else {
            i = TaskManagement.restore_user1;
        }
        int zzf2 = zznkVar.zze().zzb().zzf() - 2;
        if (zzf2 == 1) {
            zzc = zzfj.zzc(zznkVar.zzf().zzt());
        } else if (zzf2 == 2 || zzf2 == 3 || zzf2 == 4) {
            zzc = zzfh.zzc(zznkVar.zzf().zzt(), zznkVar.zze().zzg().zzt(), zzff.zzg(zznkVar.zze().zzb().zzf()));
        } else {
            throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
        }
        return new zzev(zzc, zzb2, zzc2, zza2, i, null);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzau
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.zze;
        if (length < i) {
            throw new GeneralSecurityException("Ciphertext is too short.");
        }
        byte[] copyOf = Arrays.copyOf(bArr, i);
        byte[] copyOfRange = Arrays.copyOfRange(bArr, this.zze, length);
        zzey zzeyVar = this.zzb;
        zzex zzexVar = this.zzc;
        zzes zzesVar = this.zzf;
        zzet zzetVar = this.zzd;
        return zzeu.zzb(copyOf, zzexVar.zza(copyOf, zzeyVar), zzexVar, zzesVar, zzetVar, new byte[0]).zza(copyOfRange, zza);
    }
}
