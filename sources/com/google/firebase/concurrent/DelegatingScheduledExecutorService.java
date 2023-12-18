package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class DelegatingScheduledExecutorService implements ScheduledExecutorService {
    private final ExecutorService delegate;
    private final ScheduledExecutorService scheduler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DelegatingScheduledExecutorService(ExecutorService executorService, ScheduledExecutorService scheduledExecutorService) {
        this.delegate = executorService;
        this.scheduler = scheduledExecutorService;
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        throw new UnsupportedOperationException("Shutting down is not allowed.");
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException("Shutting down is not allowed.");
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.delegate.awaitTermination(j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        return this.delegate.submit(callable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        return this.delegate.submit(runnable, t);
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return this.delegate.submit(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.delegate.invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        return this.delegate.invokeAll(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws ExecutionException, InterruptedException {
        return (T) this.delegate.invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return (T) this.delegate.invokeAny(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.delegate.execute(runnable);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(final Runnable runnable, final long j, final TimeUnit timeUnit) {
        return new DelegatingScheduledFuture(new DelegatingScheduledFuture.Resolver() { // from class: com.google.firebase.concurrent.-$$Lambda$DelegatingScheduledExecutorService$3dD8JtK55qCkKwmMvJ9neUfVdvc
            @Override // com.google.firebase.concurrent.DelegatingScheduledFuture.Resolver
            public final ScheduledFuture addCompleter(DelegatingScheduledFuture.Completer completer) {
                return DelegatingScheduledExecutorService.this.lambda$schedule$2$DelegatingScheduledExecutorService(runnable, j, timeUnit, completer);
            }
        });
    }

    public /* synthetic */ ScheduledFuture lambda$schedule$2$DelegatingScheduledExecutorService(final Runnable runnable, long j, TimeUnit timeUnit, final DelegatingScheduledFuture.Completer completer) {
        return this.scheduler.schedule(new Runnable() { // from class: com.google.firebase.concurrent.-$$Lambda$DelegatingScheduledExecutorService$JxZtzR6jdSzRyrmZu4YPnJxj4Kk
            @Override // java.lang.Runnable
            public final void run() {
                DelegatingScheduledExecutorService.this.lambda$schedule$1$DelegatingScheduledExecutorService(runnable, completer);
            }
        }, j, timeUnit);
    }

    public /* synthetic */ void lambda$schedule$1$DelegatingScheduledExecutorService(final Runnable runnable, final DelegatingScheduledFuture.Completer completer) {
        this.delegate.execute(new Runnable() { // from class: com.google.firebase.concurrent.-$$Lambda$DelegatingScheduledExecutorService$BSUbF5Pe-wiJEvt_cHLaYWmHN78
            @Override // java.lang.Runnable
            public final void run() {
                DelegatingScheduledExecutorService.lambda$schedule$0(runnable, completer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$schedule$0(Runnable runnable, DelegatingScheduledFuture.Completer completer) {
        try {
            runnable.run();
            completer.set(null);
        } catch (Exception e) {
            completer.setException(e);
        }
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(final Callable<V> callable, final long j, final TimeUnit timeUnit) {
        return new DelegatingScheduledFuture(new DelegatingScheduledFuture.Resolver() { // from class: com.google.firebase.concurrent.-$$Lambda$DelegatingScheduledExecutorService$lPWqEIYgdSSU85cPGttTuZqk7OU
            @Override // com.google.firebase.concurrent.DelegatingScheduledFuture.Resolver
            public final ScheduledFuture addCompleter(DelegatingScheduledFuture.Completer completer) {
                return DelegatingScheduledExecutorService.this.lambda$schedule$5$DelegatingScheduledExecutorService(callable, j, timeUnit, completer);
            }
        });
    }

    public /* synthetic */ ScheduledFuture lambda$schedule$5$DelegatingScheduledExecutorService(final Callable callable, long j, TimeUnit timeUnit, final DelegatingScheduledFuture.Completer completer) {
        return this.scheduler.schedule(new Callable() { // from class: com.google.firebase.concurrent.-$$Lambda$DelegatingScheduledExecutorService$YrTqU3zU9UN04Lno_ULTPetLiEo
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DelegatingScheduledExecutorService.this.lambda$schedule$4$DelegatingScheduledExecutorService(callable, completer);
            }
        }, j, timeUnit);
    }

    public /* synthetic */ Future lambda$schedule$4$DelegatingScheduledExecutorService(final Callable callable, final DelegatingScheduledFuture.Completer completer) throws Exception {
        return this.delegate.submit(new Runnable() { // from class: com.google.firebase.concurrent.-$$Lambda$DelegatingScheduledExecutorService$zK7CKs2O-9_bdiWMJjJdn8prvL8
            @Override // java.lang.Runnable
            public final void run() {
                DelegatingScheduledExecutorService.lambda$schedule$3(callable, completer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$schedule$3(Callable callable, DelegatingScheduledFuture.Completer completer) {
        try {
            completer.set(callable.call());
        } catch (Exception e) {
            completer.setException(e);
        }
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(final Runnable runnable, final long j, final long j2, final TimeUnit timeUnit) {
        return new DelegatingScheduledFuture(new DelegatingScheduledFuture.Resolver() { // from class: com.google.firebase.concurrent.-$$Lambda$DelegatingScheduledExecutorService$d1jVxrsfYgiflF7RPJaqI3KawAk
            @Override // com.google.firebase.concurrent.DelegatingScheduledFuture.Resolver
            public final ScheduledFuture addCompleter(DelegatingScheduledFuture.Completer completer) {
                return DelegatingScheduledExecutorService.this.lambda$scheduleAtFixedRate$8$DelegatingScheduledExecutorService(runnable, j, j2, timeUnit, completer);
            }
        });
    }

    public /* synthetic */ ScheduledFuture lambda$scheduleAtFixedRate$8$DelegatingScheduledExecutorService(final Runnable runnable, long j, long j2, TimeUnit timeUnit, final DelegatingScheduledFuture.Completer completer) {
        return this.scheduler.scheduleAtFixedRate(new Runnable() { // from class: com.google.firebase.concurrent.-$$Lambda$DelegatingScheduledExecutorService$g_S2OtjJCwcTEu128h8erIljgbM
            @Override // java.lang.Runnable
            public final void run() {
                DelegatingScheduledExecutorService.this.lambda$scheduleAtFixedRate$7$DelegatingScheduledExecutorService(runnable, completer);
            }
        }, j, j2, timeUnit);
    }

    public /* synthetic */ void lambda$scheduleAtFixedRate$7$DelegatingScheduledExecutorService(final Runnable runnable, final DelegatingScheduledFuture.Completer completer) {
        this.delegate.execute(new Runnable() { // from class: com.google.firebase.concurrent.-$$Lambda$DelegatingScheduledExecutorService$rnVdP-Pp7Z5VEd3ZjLkZTqHOlZM
            @Override // java.lang.Runnable
            public final void run() {
                DelegatingScheduledExecutorService.lambda$scheduleAtFixedRate$6(runnable, completer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$scheduleAtFixedRate$6(Runnable runnable, DelegatingScheduledFuture.Completer completer) {
        try {
            runnable.run();
        } catch (Exception e) {
            completer.setException(e);
            throw e;
        }
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(final Runnable runnable, final long j, final long j2, final TimeUnit timeUnit) {
        return new DelegatingScheduledFuture(new DelegatingScheduledFuture.Resolver() { // from class: com.google.firebase.concurrent.-$$Lambda$DelegatingScheduledExecutorService$ukf75tO9Kcq5I1llXwQnLgEl2G0
            @Override // com.google.firebase.concurrent.DelegatingScheduledFuture.Resolver
            public final ScheduledFuture addCompleter(DelegatingScheduledFuture.Completer completer) {
                return DelegatingScheduledExecutorService.this.lambda$scheduleWithFixedDelay$11$DelegatingScheduledExecutorService(runnable, j, j2, timeUnit, completer);
            }
        });
    }

    public /* synthetic */ ScheduledFuture lambda$scheduleWithFixedDelay$11$DelegatingScheduledExecutorService(final Runnable runnable, long j, long j2, TimeUnit timeUnit, final DelegatingScheduledFuture.Completer completer) {
        return this.scheduler.scheduleWithFixedDelay(new Runnable() { // from class: com.google.firebase.concurrent.-$$Lambda$DelegatingScheduledExecutorService$yoljGvHUIDz4eAUXQLt0PkAWsZ4
            @Override // java.lang.Runnable
            public final void run() {
                DelegatingScheduledExecutorService.this.lambda$scheduleWithFixedDelay$10$DelegatingScheduledExecutorService(runnable, completer);
            }
        }, j, j2, timeUnit);
    }

    public /* synthetic */ void lambda$scheduleWithFixedDelay$10$DelegatingScheduledExecutorService(final Runnable runnable, final DelegatingScheduledFuture.Completer completer) {
        this.delegate.execute(new Runnable() { // from class: com.google.firebase.concurrent.-$$Lambda$DelegatingScheduledExecutorService$y2nZinuPEf08ZLSLq0vuVOIWB70
            @Override // java.lang.Runnable
            public final void run() {
                DelegatingScheduledExecutorService.lambda$scheduleWithFixedDelay$9(runnable, completer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$scheduleWithFixedDelay$9(Runnable runnable, DelegatingScheduledFuture.Completer completer) {
        try {
            runnable.run();
        } catch (Exception e) {
            completer.setException(e);
        }
    }
}
