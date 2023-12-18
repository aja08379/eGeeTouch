package com.samsung.android.sdk.accessory;

import android.content.Context;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class j {
    private Context a;
    private a b;
    private FutureTask<Void> c;
    private boolean d;

    /* loaded from: classes2.dex */
    class a implements Callable<Void> {
        private a() {
        }

        /* synthetic */ a(j jVar, byte b) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            SAAdapter a = SAAdapter.a(j.this.a);
            try {
                a.a();
                try {
                    try {
                        a.a(o.a(j.this.a).a());
                        Log.i("[SA_SDK]SARegistrationTask", "Services Registered successfully!");
                        synchronized (j.this) {
                            j.b(j.this);
                        }
                        return null;
                    } catch (Throwable th) {
                        synchronized (j.this) {
                            j.b(j.this);
                            throw th;
                        }
                    }
                } catch (d e) {
                    Log.e("[SA_SDK]SARegistrationTask", "Registration failed!", e);
                    throw e;
                }
            } catch (d e2) {
                Log.e("[SA_SDK]SARegistrationTask", "Registration failed.Unable to connect to Accessory framework!", e2);
                throw e2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Invalid context:" + ((Object) null));
        }
        this.a = context;
    }

    static /* synthetic */ boolean b(j jVar) {
        jVar.d = false;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized Future<Void> a() {
        FutureTask<Void> futureTask;
        if (this.b != null || this.c != null) {
            throw new IllegalStateException("SARegistrationTask instance cannot be reused");
        }
        this.b = new a(this, (byte) 0);
        futureTask = new FutureTask<>(this.b);
        this.c = futureTask;
        return futureTask;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void b() {
        if (this.b == null || this.c == null) {
            throw new IllegalStateException("Prepare not called");
        }
        if (this.d) {
            Log.e("[SA_SDK]SARegistrationTask", "Registration task has already started");
            throw new IllegalStateException("Registration task is already running!");
        } else {
            new Thread(this.c, "RegistreationThread").start();
            this.d = true;
        }
    }
}
