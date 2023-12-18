package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zze  reason: invalid package */
/* loaded from: classes.dex */
final class zze implements zzc {
    private zze() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zze(zzd zzdVar) {
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzc
    public final ExecutorService zza(int i) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Executors.defaultThreadFactory());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return Executors.unconfigurableExecutorService(threadPoolExecutor);
    }
}
