package com.google.firebase.database.core;
/* loaded from: classes2.dex */
public interface EventTarget {
    void postEvent(Runnable runnable);

    void restart();

    void shutdown();
}
