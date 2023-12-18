package com.google.firebase.storage;

import android.app.Activity;
import android.os.Build;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.StorageTask.ProvideError;
import com.google.firebase.storage.internal.ActivityLifecycleListener;
import com.google.firebase.storage.internal.SmartHandler;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class TaskListenerImpl<ListenerTypeT, ResultT extends StorageTask.ProvideError> {
    private OnRaise<ListenerTypeT, ResultT> onRaise;
    private int targetStates;
    private StorageTask<ResultT> task;
    private final Queue<ListenerTypeT> listenerQueue = new ConcurrentLinkedQueue();
    private final HashMap<ListenerTypeT, SmartHandler> handlerMap = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface OnRaise<ListenerTypeT, ResultT> {
        void raise(ListenerTypeT listenertypet, ResultT resultt);
    }

    public TaskListenerImpl(StorageTask<ResultT> storageTask, int i, OnRaise<ListenerTypeT, ResultT> onRaise) {
        this.task = storageTask;
        this.targetStates = i;
        this.onRaise = onRaise;
    }

    public int getListenerCount() {
        return Math.max(this.listenerQueue.size(), this.handlerMap.size());
    }

    public void addListener(Activity activity, Executor executor, final ListenerTypeT listenertypet) {
        boolean z;
        SmartHandler smartHandler;
        Preconditions.checkNotNull(listenertypet);
        synchronized (this.task.getSyncObject()) {
            boolean z2 = true;
            z = (this.task.getInternalState() & this.targetStates) != 0;
            this.listenerQueue.add(listenertypet);
            smartHandler = new SmartHandler(executor);
            this.handlerMap.put(listenertypet, smartHandler);
            if (activity != null) {
                if (Build.VERSION.SDK_INT >= 17) {
                    if (activity.isDestroyed()) {
                        z2 = false;
                    }
                    Preconditions.checkArgument(z2, "Activity is already destroyed!");
                }
                ActivityLifecycleListener.getInstance().runOnActivityStopped(activity, listenertypet, new Runnable() { // from class: com.google.firebase.storage.-$$Lambda$TaskListenerImpl$9S735gSTLEjyjiLA9feZHcjz9nc
                    @Override // java.lang.Runnable
                    public final void run() {
                        TaskListenerImpl.this.lambda$addListener$0$TaskListenerImpl(listenertypet);
                    }
                });
            }
        }
        if (z) {
            final ResultT snapState = this.task.snapState();
            smartHandler.callBack(new Runnable() { // from class: com.google.firebase.storage.-$$Lambda$TaskListenerImpl$jo6z-BgrthwDXSAznMNohfdE2VU
                @Override // java.lang.Runnable
                public final void run() {
                    TaskListenerImpl.this.lambda$addListener$1$TaskListenerImpl(listenertypet, snapState);
                }
            });
        }
    }

    public /* synthetic */ void lambda$addListener$1$TaskListenerImpl(Object obj, StorageTask.ProvideError provideError) {
        this.onRaise.raise(obj, provideError);
    }

    public void onInternalStateChanged() {
        if ((this.task.getInternalState() & this.targetStates) != 0) {
            final ResultT snapState = this.task.snapState();
            for (final ListenerTypeT listenertypet : this.listenerQueue) {
                SmartHandler smartHandler = this.handlerMap.get(listenertypet);
                if (smartHandler != null) {
                    smartHandler.callBack(new Runnable() { // from class: com.google.firebase.storage.-$$Lambda$TaskListenerImpl$S8elBxPWPDCBbbHv0Z6yA1jvX68
                        @Override // java.lang.Runnable
                        public final void run() {
                            TaskListenerImpl.this.lambda$onInternalStateChanged$2$TaskListenerImpl(listenertypet, snapState);
                        }
                    });
                }
            }
        }
    }

    public /* synthetic */ void lambda$onInternalStateChanged$2$TaskListenerImpl(Object obj, StorageTask.ProvideError provideError) {
        this.onRaise.raise(obj, provideError);
    }

    /* renamed from: removeListener */
    public void lambda$addListener$0$TaskListenerImpl(ListenerTypeT listenertypet) {
        Preconditions.checkNotNull(listenertypet);
        synchronized (this.task.getSyncObject()) {
            this.handlerMap.remove(listenertypet);
            this.listenerQueue.remove(listenertypet);
            ActivityLifecycleListener.getInstance().removeCookie(listenertypet);
        }
    }
}
