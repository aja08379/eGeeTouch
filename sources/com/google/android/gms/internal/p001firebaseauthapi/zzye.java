package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.ExecutorService;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzye  reason: invalid package */
/* loaded from: classes.dex */
public class zzye {
    zzxb zza;
    ExecutorService zzb;

    public final Task zzP(final zzyd zzydVar) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzb.execute(new Runnable() { // from class: com.google.android.gms.internal.firebase-auth-api.zzyc
            @Override // java.lang.Runnable
            public final void run() {
                zzydVar.zzc(taskCompletionSource, zzye.this.zza);
            }
        });
        return taskCompletionSource.getTask();
    }
}
