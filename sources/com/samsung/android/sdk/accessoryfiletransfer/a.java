package com.samsung.android.sdk.accessoryfiletransfer;

import android.os.Handler;
import android.os.HandlerThread;
import com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public final class a {
    private SAFileTransfer.EventListener a;
    private SAFileTransfer.c b;
    private HandlerThread c;
    private Handler d;
    private ConcurrentHashMap<Integer, C0083a> e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.samsung.android.sdk.accessoryfiletransfer.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0083a {
        int a;
        String b;
        String c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(SAFileTransfer.EventListener eventListener, HandlerThread handlerThread, Handler handler, SAFileTransfer.c cVar, ConcurrentHashMap<Integer, C0083a> concurrentHashMap) {
        this.a = eventListener;
        this.c = handlerThread;
        this.d = handler;
        this.e = concurrentHashMap;
        this.b = cVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SAFileTransfer.EventListener a() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(SAFileTransfer.EventListener eventListener) {
        this.a = eventListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(SAFileTransfer.c cVar) {
        this.b = cVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SAFileTransfer.c b() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final HandlerThread c() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Handler d() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ConcurrentHashMap<Integer, C0083a> e() {
        return this.e;
    }
}
