package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadn  reason: invalid package */
/* loaded from: classes.dex */
public class zzadn extends IOException {
    private zzaek zza;

    public zzadn(IOException iOException) {
        super(iOException.getMessage(), iOException);
        this.zza = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzadm zza() {
        return new zzadm("Protocol message tag had invalid wire type.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzadn zzb() {
        return new zzadn("Protocol message end-group tag did not match expected tag.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzadn zzc() {
        return new zzadn("Protocol message contained an invalid tag (zero).");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzadn zzd() {
        return new zzadn("Protocol message had invalid UTF-8.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzadn zze() {
        return new zzadn("CodedInputStream encountered a malformed varint.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzadn zzf() {
        return new zzadn("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzadn zzg() {
        return new zzadn("Failed to parse the message.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzadn zzi() {
        return new zzadn("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzadn zzh(zzaek zzaekVar) {
        this.zza = zzaekVar;
        return this;
    }

    public zzadn(String str) {
        super(str);
        this.zza = null;
    }
}
