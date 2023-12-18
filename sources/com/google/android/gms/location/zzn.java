package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.internal.location.zzbd;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes2.dex */
final class zzn extends RegisterListenerMethod<zzaz, LocationCallback> {
    private final /* synthetic */ zzbd zzy;
    private final /* synthetic */ ListenerHolder zzz;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzn(FusedLocationProviderClient fusedLocationProviderClient, ListenerHolder listenerHolder, zzbd zzbdVar, ListenerHolder listenerHolder2) {
        super(listenerHolder);
        this.zzy = zzbdVar;
        this.zzz = listenerHolder2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    public final /* synthetic */ void registerListener(zzaz zzazVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzazVar.zza(this.zzy, this.zzz, new FusedLocationProviderClient.zza(taskCompletionSource));
    }
}
