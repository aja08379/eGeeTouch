package com.google.android.gms.cloudmessaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
/* compiled from: com.google.android.gms:play-services-cloud-messaging@@16.0.0 */
/* loaded from: classes.dex */
final class zzf implements ServiceConnection {
    int zza;
    final Messenger zzb;
    zzo zzc;
    final Queue<zzq<?>> zzd;
    final SparseArray<zzq<?>> zze;
    final /* synthetic */ zze zzf;

    private zzf(zze zzeVar) {
        this.zzf = zzeVar;
        this.zza = 0;
        this.zzb = new Messenger(new com.google.android.gms.internal.cloudmessaging.zze(Looper.getMainLooper(), new Handler.Callback(this) { // from class: com.google.android.gms.cloudmessaging.zzi
            private final zzf zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.zza.zza(message);
            }
        }));
        this.zzd = new ArrayDeque();
        this.zze = new SparseArray<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized boolean zza(zzq<?> zzqVar) {
        int i = this.zza;
        if (i != 0) {
            if (i == 1) {
                this.zzd.add(zzqVar);
                return true;
            } else if (i == 2) {
                this.zzd.add(zzqVar);
                zza();
                return true;
            } else {
                if (i != 3 && i != 4) {
                    throw new IllegalStateException(new StringBuilder(26).append("Unknown state: ").append(this.zza).toString());
                }
                return false;
            }
        }
        this.zzd.add(zzqVar);
        Preconditions.checkState(this.zza == 0);
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Starting bind to GmsCore");
        }
        this.zza = 1;
        Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gms");
        if (!ConnectionTracker.getInstance().bindService(zze.zza(this.zzf), intent, this, 1)) {
            zza(0, "Unable to bind to service");
        } else {
            zze.zzb(this.zzf).schedule(new Runnable(this) { // from class: com.google.android.gms.cloudmessaging.zzh
                private final zzf zza;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.zza = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zzc();
                }
            }, 30L, TimeUnit.SECONDS);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            Log.d("MessengerIpcClient", new StringBuilder(41).append("Received response to request: ").append(i).toString());
        }
        synchronized (this) {
            zzq<?> zzqVar = this.zze.get(i);
            if (zzqVar == null) {
                Log.w("MessengerIpcClient", new StringBuilder(50).append("Received response for unknown request: ").append(i).toString());
                return true;
            }
            this.zze.remove(i);
            zzb();
            Bundle data = message.getData();
            if (data.getBoolean("unsupported", false)) {
                zzqVar.zza(new zzp(4, "Not supported by GmsCore"));
            } else {
                zzqVar.zza(data);
            }
            return true;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        zze.zzb(this.zzf).execute(new Runnable(this, iBinder) { // from class: com.google.android.gms.cloudmessaging.zzk
            private final zzf zza;
            private final IBinder zzb;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
                this.zzb = iBinder;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzf zzfVar = this.zza;
                IBinder iBinder2 = this.zzb;
                synchronized (zzfVar) {
                    try {
                        if (iBinder2 == null) {
                            zzfVar.zza(0, "Null service connection");
                            return;
                        }
                        try {
                            zzfVar.zzc = new zzo(iBinder2);
                            zzfVar.zza = 2;
                            zzfVar.zza();
                        } catch (RemoteException e) {
                            zzfVar.zza(0, e.getMessage());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza() {
        zze.zzb(this.zzf).execute(new Runnable(this) { // from class: com.google.android.gms.cloudmessaging.zzj
            private final zzf zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                final zzq<?> poll;
                final zzf zzfVar = this.zza;
                while (true) {
                    synchronized (zzfVar) {
                        if (zzfVar.zza != 2) {
                            return;
                        }
                        if (zzfVar.zzd.isEmpty()) {
                            zzfVar.zzb();
                            return;
                        }
                        poll = zzfVar.zzd.poll();
                        zzfVar.zze.put(poll.zza, poll);
                        zze.zzb(zzfVar.zzf).schedule(new Runnable(zzfVar, poll) { // from class: com.google.android.gms.cloudmessaging.zzl
                            private final zzf zza;
                            private final zzq zzb;

                            /* JADX INFO: Access modifiers changed from: package-private */
                            {
                                this.zza = zzfVar;
                                this.zzb = poll;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.zza.zza(this.zzb.zza);
                            }
                        }, 30L, TimeUnit.SECONDS);
                    }
                    if (Log.isLoggable("MessengerIpcClient", 3)) {
                        String valueOf = String.valueOf(poll);
                        Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(valueOf).length() + 8).append("Sending ").append(valueOf).toString());
                    }
                    Context zza = zze.zza(zzfVar.zzf);
                    Messenger messenger = zzfVar.zzb;
                    Message obtain = Message.obtain();
                    obtain.what = poll.zzc;
                    obtain.arg1 = poll.zza;
                    obtain.replyTo = messenger;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("oneWay", poll.zza());
                    bundle.putString("pkg", zza.getPackageName());
                    bundle.putBundle(ShareConstants.WEB_DIALOG_PARAM_DATA, poll.zzd);
                    obtain.setData(bundle);
                    try {
                        zzfVar.zzc.zza(obtain);
                    } catch (RemoteException e) {
                        zzfVar.zza(2, e.getMessage());
                    }
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        zze.zzb(this.zzf).execute(new Runnable(this) { // from class: com.google.android.gms.cloudmessaging.zzm
            private final zzf zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(2, "Service disconnected");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zza(int i, String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(str);
            Log.d("MessengerIpcClient", valueOf.length() != 0 ? "Disconnected: ".concat(valueOf) : new String("Disconnected: "));
        }
        int i2 = this.zza;
        if (i2 == 0) {
            throw new IllegalStateException();
        }
        if (i2 != 1 && i2 != 2) {
            if (i2 == 3) {
                this.zza = 4;
                return;
            } else if (i2 != 4) {
                throw new IllegalStateException(new StringBuilder(26).append("Unknown state: ").append(this.zza).toString());
            } else {
                return;
            }
        }
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Unbinding service");
        }
        this.zza = 4;
        ConnectionTracker.getInstance().unbindService(zze.zza(this.zzf), this);
        zzp zzpVar = new zzp(i, str);
        for (zzq<?> zzqVar : this.zzd) {
            zzqVar.zza(zzpVar);
        }
        this.zzd.clear();
        for (int i3 = 0; i3 < this.zze.size(); i3++) {
            this.zze.valueAt(i3).zza(zzpVar);
        }
        this.zze.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzb() {
        if (this.zza == 2 && this.zzd.isEmpty() && this.zze.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.zza = 3;
            ConnectionTracker.getInstance().unbindService(zze.zza(this.zzf), this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzc() {
        if (this.zza == 1) {
            zza(1, "Timed out while binding");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zza(int i) {
        zzq<?> zzqVar = this.zze.get(i);
        if (zzqVar != null) {
            Log.w("MessengerIpcClient", new StringBuilder(31).append("Timing out request: ").append(i).toString());
            this.zze.remove(i);
            zzqVar.zza(new zzp(3, "Timed out waiting for response"));
            zzb();
        }
    }
}
