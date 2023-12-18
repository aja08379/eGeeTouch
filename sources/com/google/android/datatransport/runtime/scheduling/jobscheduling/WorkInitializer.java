package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Inject;
/* loaded from: classes.dex */
public class WorkInitializer {
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final WorkScheduler scheduler;
    private final EventStore store;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public WorkInitializer(Executor executor, EventStore eventStore, WorkScheduler workScheduler, SynchronizationGuard synchronizationGuard) {
        this.executor = executor;
        this.store = eventStore;
        this.scheduler = workScheduler;
        this.guard = synchronizationGuard;
    }

    public void ensureContextsScheduled() {
        this.executor.execute(new Runnable() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.-$$Lambda$WorkInitializer$IZdbY5oNmKQf7_hQvjCC3nA27Mo
            @Override // java.lang.Runnable
            public final void run() {
                WorkInitializer.this.lambda$ensureContextsScheduled$1$WorkInitializer();
            }
        });
    }

    public /* synthetic */ void lambda$ensureContextsScheduled$1$WorkInitializer() {
        this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.-$$Lambda$WorkInitializer$9qDRtwUbk8Lu0d-4AfNJqExglzE
            @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
            public final Object execute() {
                return WorkInitializer.this.lambda$ensureContextsScheduled$0$WorkInitializer();
            }
        });
    }

    public /* synthetic */ Object lambda$ensureContextsScheduled$0$WorkInitializer() {
        for (TransportContext transportContext : this.store.loadActiveContexts()) {
            this.scheduler.schedule(transportContext, 1);
        }
        return null;
    }
}
