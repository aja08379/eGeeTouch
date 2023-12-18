package com.google.firebase.storage;

import android.app.Activity;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.StorageTask.ProvideError;
import com.google.firebase.storage.TaskListenerImpl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public abstract class StorageTask<ResultT extends ProvideError> extends ControllableTask<ResultT> {
    static final int INTERNAL_STATE_CANCELED = 256;
    static final int INTERNAL_STATE_CANCELING = 32;
    static final int INTERNAL_STATE_FAILURE = 64;
    static final int INTERNAL_STATE_IN_PROGRESS = 4;
    static final int INTERNAL_STATE_NOT_STARTED = 1;
    static final int INTERNAL_STATE_PAUSED = 16;
    static final int INTERNAL_STATE_PAUSING = 8;
    static final int INTERNAL_STATE_QUEUED = 2;
    static final int INTERNAL_STATE_SUCCESS = 128;
    static final int STATES_CANCELED = 256;
    static final int STATES_COMPLETE = 448;
    static final int STATES_FAILURE = 64;
    static final int STATES_INPROGRESS = -465;
    static final int STATES_PAUSED = 16;
    static final int STATES_SUCCESS = 128;
    private static final String TAG = "StorageTask";
    private static final HashMap<Integer, HashSet<Integer>> ValidTaskInitiatedStateChanges;
    private static final HashMap<Integer, HashSet<Integer>> ValidUserInitiatedStateChanges;
    private ResultT finalResult;
    protected final Object syncObject = new Object();
    final TaskListenerImpl<OnSuccessListener<? super ResultT>, ResultT> successManager = new TaskListenerImpl<>(this, 128, new TaskListenerImpl.OnRaise() { // from class: com.google.firebase.storage.-$$Lambda$StorageTask$xlHsb5OfSRp-di5vg8sdDdXsCO4
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            StorageTask.this.lambda$new$0$StorageTask((OnSuccessListener) obj, (StorageTask.ProvideError) obj2);
        }
    });
    final TaskListenerImpl<OnFailureListener, ResultT> failureManager = new TaskListenerImpl<>(this, 64, new TaskListenerImpl.OnRaise() { // from class: com.google.firebase.storage.-$$Lambda$StorageTask$kGBBr9mjcn2-DxduMaHtT-Bojro
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            StorageTask.this.lambda$new$1$StorageTask((OnFailureListener) obj, (StorageTask.ProvideError) obj2);
        }
    });
    final TaskListenerImpl<OnCompleteListener<ResultT>, ResultT> completeListener = new TaskListenerImpl<>(this, STATES_COMPLETE, new TaskListenerImpl.OnRaise() { // from class: com.google.firebase.storage.-$$Lambda$StorageTask$Jkv9xcVZaYz5UBCNvMSmJTHEG18
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            StorageTask.this.lambda$new$2$StorageTask((OnCompleteListener) obj, (StorageTask.ProvideError) obj2);
        }
    });
    final TaskListenerImpl<OnCanceledListener, ResultT> cancelManager = new TaskListenerImpl<>(this, 256, new TaskListenerImpl.OnRaise() { // from class: com.google.firebase.storage.-$$Lambda$StorageTask$NSdjk49HEq4blyR-OF6mZEPTt_s
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            StorageTask.this.lambda$new$3$StorageTask((OnCanceledListener) obj, (StorageTask.ProvideError) obj2);
        }
    });
    final TaskListenerImpl<OnProgressListener<? super ResultT>, ResultT> progressManager = new TaskListenerImpl<>(this, STATES_INPROGRESS, new TaskListenerImpl.OnRaise() { // from class: com.google.firebase.storage.-$$Lambda$XYrrQpsB_p_00PPOCyqc7ym51TA
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            ((OnProgressListener) obj).onProgress((StorageTask.ProvideError) obj2);
        }
    });
    final TaskListenerImpl<OnPausedListener<? super ResultT>, ResultT> pausedManager = new TaskListenerImpl<>(this, 16, new TaskListenerImpl.OnRaise() { // from class: com.google.firebase.storage.-$$Lambda$HI9VW-fkLzW-JEKLQBX3mZC5p1s
        @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
        public final void raise(Object obj, Object obj2) {
            ((OnPausedListener) obj).onPaused((StorageTask.ProvideError) obj2);
        }
    });
    private volatile int currentState = 1;

    /* loaded from: classes2.dex */
    public interface ProvideError {
        Exception getError();
    }

    private String getStateString(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i != 8 ? i != 16 ? i != 32 ? i != 64 ? i != 128 ? i != 256 ? "Unknown Internal State!" : "INTERNAL_STATE_CANCELED" : "INTERNAL_STATE_SUCCESS" : "INTERNAL_STATE_FAILURE" : "INTERNAL_STATE_CANCELING" : "INTERNAL_STATE_PAUSED" : "INTERNAL_STATE_PAUSING" : "INTERNAL_STATE_IN_PROGRESS" : "INTERNAL_STATE_QUEUED" : "INTERNAL_STATE_NOT_STARTED";
    }

    public abstract StorageReference getStorage();

    public void onCanceled() {
    }

    protected void onFailure() {
    }

    protected void onPaused() {
    }

    protected void onProgress() {
    }

    protected void onQueued() {
    }

    protected void onSuccess() {
    }

    void resetState() {
    }

    abstract void run();

    abstract void schedule();

    abstract ResultT snapStateImpl();

    static {
        HashMap<Integer, HashSet<Integer>> hashMap = new HashMap<>();
        ValidUserInitiatedStateChanges = hashMap;
        HashMap<Integer, HashSet<Integer>> hashMap2 = new HashMap<>();
        ValidTaskInitiatedStateChanges = hashMap2;
        hashMap.put(1, new HashSet<>(Arrays.asList(16, 256)));
        hashMap.put(2, new HashSet<>(Arrays.asList(8, 32)));
        hashMap.put(4, new HashSet<>(Arrays.asList(8, 32)));
        hashMap.put(16, new HashSet<>(Arrays.asList(2, 256)));
        hashMap.put(64, new HashSet<>(Arrays.asList(2, 256)));
        hashMap2.put(1, new HashSet<>(Arrays.asList(2, 64)));
        hashMap2.put(2, new HashSet<>(Arrays.asList(4, 64, 128)));
        hashMap2.put(4, new HashSet<>(Arrays.asList(4, 64, 128)));
        hashMap2.put(8, new HashSet<>(Arrays.asList(16, 64, 128)));
        hashMap2.put(32, new HashSet<>(Arrays.asList(256, 64, 128)));
    }

    public /* synthetic */ void lambda$new$0$StorageTask(OnSuccessListener onSuccessListener, ProvideError provideError) {
        StorageTaskManager.getInstance().unRegister(this);
        onSuccessListener.onSuccess(provideError);
    }

    public /* synthetic */ void lambda$new$1$StorageTask(OnFailureListener onFailureListener, ProvideError provideError) {
        StorageTaskManager.getInstance().unRegister(this);
        onFailureListener.onFailure(provideError.getError());
    }

    public /* synthetic */ void lambda$new$2$StorageTask(OnCompleteListener onCompleteListener, ProvideError provideError) {
        StorageTaskManager.getInstance().unRegister(this);
        onCompleteListener.onComplete(this);
    }

    public /* synthetic */ void lambda$new$3$StorageTask(OnCanceledListener onCanceledListener, ProvideError provideError) {
        StorageTaskManager.getInstance().unRegister(this);
        onCanceledListener.onCanceled();
    }

    public boolean queue() {
        if (tryChangeState(2, false)) {
            schedule();
            return true;
        }
        return false;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public boolean resume() {
        if (tryChangeState(2, true)) {
            resetState();
            schedule();
            return true;
        }
        return false;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public boolean pause() {
        return tryChangeState(new int[]{16, 8}, true);
    }

    @Override // com.google.firebase.storage.CancellableTask
    public boolean cancel() {
        return tryChangeState(new int[]{256, 32}, true);
    }

    @Override // com.google.android.gms.tasks.Task
    public boolean isComplete() {
        return (getInternalState() & STATES_COMPLETE) != 0;
    }

    @Override // com.google.android.gms.tasks.Task
    public boolean isSuccessful() {
        return (getInternalState() & 128) != 0;
    }

    @Override // com.google.firebase.storage.CancellableTask, com.google.android.gms.tasks.Task
    public boolean isCanceled() {
        return getInternalState() == 256;
    }

    @Override // com.google.firebase.storage.CancellableTask
    public boolean isInProgress() {
        return (getInternalState() & STATES_INPROGRESS) != 0;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public boolean isPaused() {
        return (getInternalState() & 16) != 0;
    }

    @Override // com.google.android.gms.tasks.Task
    public ResultT getResult() {
        if (getFinalResult() == null) {
            throw new IllegalStateException();
        }
        Exception error = getFinalResult().getError();
        if (error != null) {
            throw new RuntimeExecutionException(error);
        }
        return getFinalResult();
    }

    @Override // com.google.android.gms.tasks.Task
    public <X extends Throwable> ResultT getResult(Class<X> cls) throws Throwable {
        if (getFinalResult() == null) {
            throw new IllegalStateException();
        }
        if (cls.isInstance(getFinalResult().getError())) {
            throw cls.cast(getFinalResult().getError());
        }
        Exception error = getFinalResult().getError();
        if (error != null) {
            throw new RuntimeExecutionException(error);
        }
        return getFinalResult();
    }

    @Override // com.google.android.gms.tasks.Task
    public Exception getException() {
        if (getFinalResult() == null) {
            return null;
        }
        return getFinalResult().getError();
    }

    public ResultT getSnapshot() {
        return snapState();
    }

    public int getInternalState() {
        return this.currentState;
    }

    public Object getSyncObject() {
        return this.syncObject;
    }

    public ResultT snapState() {
        ResultT snapStateImpl;
        synchronized (this.syncObject) {
            snapStateImpl = snapStateImpl();
        }
        return snapStateImpl;
    }

    boolean tryChangeState(int[] iArr, boolean z) {
        HashMap<Integer, HashSet<Integer>> hashMap = z ? ValidUserInitiatedStateChanges : ValidTaskInitiatedStateChanges;
        synchronized (this.syncObject) {
            for (int i : iArr) {
                HashSet<Integer> hashSet = hashMap.get(Integer.valueOf(getInternalState()));
                if (hashSet != null && hashSet.contains(Integer.valueOf(i))) {
                    this.currentState = i;
                    int i2 = this.currentState;
                    if (i2 == 2) {
                        StorageTaskManager.getInstance().ensureRegistered(this);
                        onQueued();
                    } else if (i2 == 4) {
                        onProgress();
                    } else if (i2 == 16) {
                        onPaused();
                    } else if (i2 == 64) {
                        onFailure();
                    } else if (i2 == 128) {
                        onSuccess();
                    } else if (i2 == 256) {
                        onCanceled();
                    }
                    this.successManager.onInternalStateChanged();
                    this.failureManager.onInternalStateChanged();
                    this.cancelManager.onInternalStateChanged();
                    this.completeListener.onInternalStateChanged();
                    this.pausedManager.onInternalStateChanged();
                    this.progressManager.onInternalStateChanged();
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "changed internal state to: " + getStateString(i) + " isUser: " + z + " from state:" + getStateString(this.currentState));
                    }
                    return true;
                }
            }
            Log.w(TAG, "unable to change internal state to: " + getStateString(iArr) + " isUser: " + z + " from state:" + getStateString(this.currentState));
            return false;
        }
    }

    public boolean tryChangeState(int i, boolean z) {
        return tryChangeState(new int[]{i}, z);
    }

    private ResultT getFinalResult() {
        ResultT resultt = this.finalResult;
        if (resultt != null) {
            return resultt;
        }
        if (isComplete()) {
            if (this.finalResult == null) {
                this.finalResult = snapState();
            }
            return this.finalResult;
        }
        return null;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public StorageTask<ResultT> addOnPausedListener(OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        this.pausedManager.addListener(null, null, onPausedListener);
        return this;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public StorageTask<ResultT> addOnPausedListener(Executor executor, OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        Preconditions.checkNotNull(executor);
        this.pausedManager.addListener(null, executor, onPausedListener);
        return this;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public StorageTask<ResultT> addOnPausedListener(Activity activity, OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        Preconditions.checkNotNull(activity);
        this.pausedManager.addListener(activity, null, onPausedListener);
        return this;
    }

    public StorageTask<ResultT> removeOnPausedListener(OnPausedListener<? super ResultT> onPausedListener) {
        Preconditions.checkNotNull(onPausedListener);
        this.pausedManager.lambda$addListener$0$TaskListenerImpl(onPausedListener);
        return this;
    }

    @Override // com.google.firebase.storage.CancellableTask
    public StorageTask<ResultT> addOnProgressListener(OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        this.progressManager.addListener(null, null, onProgressListener);
        return this;
    }

    @Override // com.google.firebase.storage.CancellableTask
    public StorageTask<ResultT> addOnProgressListener(Executor executor, OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        Preconditions.checkNotNull(executor);
        this.progressManager.addListener(null, executor, onProgressListener);
        return this;
    }

    @Override // com.google.firebase.storage.CancellableTask
    public StorageTask<ResultT> addOnProgressListener(Activity activity, OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        Preconditions.checkNotNull(activity);
        this.progressManager.addListener(activity, null, onProgressListener);
        return this;
    }

    public StorageTask<ResultT> removeOnProgressListener(OnProgressListener<? super ResultT> onProgressListener) {
        Preconditions.checkNotNull(onProgressListener);
        this.progressManager.lambda$addListener$0$TaskListenerImpl(onProgressListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnSuccessListener(OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.addListener(null, null, onSuccessListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnSuccessListener(Executor executor, OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.addListener(null, executor, onSuccessListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnSuccessListener(Activity activity, OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.addListener(activity, null, onSuccessListener);
        return this;
    }

    public StorageTask<ResultT> removeOnSuccessListener(OnSuccessListener<? super ResultT> onSuccessListener) {
        Preconditions.checkNotNull(onSuccessListener);
        this.successManager.lambda$addListener$0$TaskListenerImpl(onSuccessListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnFailureListener(OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        this.failureManager.addListener(null, null, onFailureListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        Preconditions.checkNotNull(executor);
        this.failureManager.addListener(null, executor, onFailureListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        Preconditions.checkNotNull(activity);
        this.failureManager.addListener(activity, null, onFailureListener);
        return this;
    }

    public StorageTask<ResultT> removeOnFailureListener(OnFailureListener onFailureListener) {
        Preconditions.checkNotNull(onFailureListener);
        this.failureManager.lambda$addListener$0$TaskListenerImpl(onFailureListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCompleteListener(OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        this.completeListener.addListener(null, null, onCompleteListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCompleteListener(Executor executor, OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        Preconditions.checkNotNull(executor);
        this.completeListener.addListener(null, executor, onCompleteListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCompleteListener(Activity activity, OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        Preconditions.checkNotNull(activity);
        this.completeListener.addListener(activity, null, onCompleteListener);
        return this;
    }

    public StorageTask<ResultT> removeOnCompleteListener(OnCompleteListener<ResultT> onCompleteListener) {
        Preconditions.checkNotNull(onCompleteListener);
        this.completeListener.lambda$addListener$0$TaskListenerImpl(onCompleteListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCanceledListener(OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        this.cancelManager.addListener(null, null, onCanceledListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCanceledListener(Executor executor, OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        Preconditions.checkNotNull(executor);
        this.cancelManager.addListener(null, executor, onCanceledListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCanceledListener(Activity activity, OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        Preconditions.checkNotNull(activity);
        this.cancelManager.addListener(activity, null, onCanceledListener);
        return this;
    }

    public StorageTask<ResultT> removeOnCanceledListener(OnCanceledListener onCanceledListener) {
        Preconditions.checkNotNull(onCanceledListener);
        this.cancelManager.lambda$addListener$0$TaskListenerImpl(onCanceledListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> continueWith(Continuation<ResultT, ContinuationResultT> continuation) {
        return continueWithImpl(null, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> continueWith(Executor executor, Continuation<ResultT, ContinuationResultT> continuation) {
        return continueWithImpl(executor, continuation);
    }

    private <ContinuationResultT> Task<ContinuationResultT> continueWithImpl(Executor executor, final Continuation<ResultT, ContinuationResultT> continuation) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.completeListener.addListener(null, executor, new OnCompleteListener() { // from class: com.google.firebase.storage.-$$Lambda$StorageTask$mk_kE2XoKM88mAmixvAkBmUTyro
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                StorageTask.this.lambda$continueWithImpl$4$StorageTask(continuation, taskCompletionSource, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    public /* synthetic */ void lambda$continueWithImpl$4$StorageTask(Continuation continuation, TaskCompletionSource taskCompletionSource, Task task) {
        try {
            Object then = continuation.then(this);
            if (taskCompletionSource.getTask().isComplete()) {
                return;
            }
            taskCompletionSource.setResult(then);
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                taskCompletionSource.setException((Exception) e.getCause());
            } else {
                taskCompletionSource.setException(e);
            }
        } catch (Exception e2) {
            taskCompletionSource.setException(e2);
        }
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> continueWithTask(Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        return continueWithTaskImpl(null, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> continueWithTask(Executor executor, Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        return continueWithTaskImpl(executor, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> onSuccessTask(SuccessContinuation<ResultT, ContinuationResultT> successContinuation) {
        return successTaskImpl(null, successContinuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> onSuccessTask(Executor executor, SuccessContinuation<ResultT, ContinuationResultT> successContinuation) {
        return successTaskImpl(executor, successContinuation);
    }

    private <ContinuationResultT> Task<ContinuationResultT> continueWithTaskImpl(Executor executor, final Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        final CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
        this.completeListener.addListener(null, executor, new OnCompleteListener() { // from class: com.google.firebase.storage.-$$Lambda$StorageTask$uAL5ZNP1zCuP5yLsgZAe-801mpY
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                StorageTask.this.lambda$continueWithTaskImpl$5$StorageTask(continuation, taskCompletionSource, cancellationTokenSource, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    public /* synthetic */ void lambda$continueWithTaskImpl$5$StorageTask(Continuation continuation, TaskCompletionSource taskCompletionSource, CancellationTokenSource cancellationTokenSource, Task task) {
        try {
            Task task2 = (Task) continuation.then(this);
            if (taskCompletionSource.getTask().isComplete()) {
                return;
            }
            if (task2 == null) {
                taskCompletionSource.setException(new NullPointerException("Continuation returned null"));
                return;
            }
            Objects.requireNonNull(taskCompletionSource);
            task2.addOnSuccessListener(new $$Lambda$StorageTask$JkyFbgEatWwF9u2RXdKZbbkQ0Jw(taskCompletionSource));
            Objects.requireNonNull(taskCompletionSource);
            task2.addOnFailureListener(new $$Lambda$StorageTask$g715g6W7VdV2JIn7lpH1jtTC4D0(taskCompletionSource));
            Objects.requireNonNull(cancellationTokenSource);
            task2.addOnCanceledListener(new $$Lambda$StorageTask$0ZEndH1RQEKxIilqWbB8Nk19iDo(cancellationTokenSource));
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                taskCompletionSource.setException((Exception) e.getCause());
            } else {
                taskCompletionSource.setException(e);
            }
        } catch (Exception e2) {
            taskCompletionSource.setException(e2);
        }
    }

    private <ContinuationResultT> Task<ContinuationResultT> successTaskImpl(Executor executor, final SuccessContinuation<ResultT, ContinuationResultT> successContinuation) {
        final CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
        this.successManager.addListener(null, executor, new OnSuccessListener() { // from class: com.google.firebase.storage.-$$Lambda$StorageTask$5bT26mCdNcFey2g8r5U6NeyZ95c
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                StorageTask.lambda$successTaskImpl$6(SuccessContinuation.this, taskCompletionSource, cancellationTokenSource, (StorageTask.ProvideError) obj);
            }
        });
        return taskCompletionSource.getTask();
    }

    public static /* synthetic */ void lambda$successTaskImpl$6(SuccessContinuation successContinuation, TaskCompletionSource taskCompletionSource, CancellationTokenSource cancellationTokenSource, ProvideError provideError) {
        try {
            Task then = successContinuation.then(provideError);
            Objects.requireNonNull(taskCompletionSource);
            then.addOnSuccessListener(new $$Lambda$StorageTask$JkyFbgEatWwF9u2RXdKZbbkQ0Jw(taskCompletionSource));
            Objects.requireNonNull(taskCompletionSource);
            then.addOnFailureListener(new $$Lambda$StorageTask$g715g6W7VdV2JIn7lpH1jtTC4D0(taskCompletionSource));
            Objects.requireNonNull(cancellationTokenSource);
            then.addOnCanceledListener(new $$Lambda$StorageTask$0ZEndH1RQEKxIilqWbB8Nk19iDo(cancellationTokenSource));
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                taskCompletionSource.setException((Exception) e.getCause());
            } else {
                taskCompletionSource.setException(e);
            }
        } catch (Exception e2) {
            taskCompletionSource.setException(e2);
        }
    }

    public Runnable getRunnable() {
        return new Runnable() { // from class: com.google.firebase.storage.-$$Lambda$StorageTask$q9YBoR_A8LB-JxTCx8JRQvabaZs
            @Override // java.lang.Runnable
            public final void run() {
                StorageTask.this.lambda$getRunnable$7$StorageTask();
            }
        };
    }

    public /* synthetic */ void lambda$getRunnable$7$StorageTask() {
        try {
            run();
        } finally {
            ensureFinalState();
        }
    }

    private void ensureFinalState() {
        if (isComplete() || isPaused() || getInternalState() == 2 || tryChangeState(256, false)) {
            return;
        }
        tryChangeState(64, false);
    }

    private String getStateString(int[] iArr) {
        if (iArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i : iArr) {
            sb.append(getStateString(i)).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    /* loaded from: classes2.dex */
    public class SnapshotBase implements ProvideError {
        private final Exception error;

        public SnapshotBase(Exception exc) {
            StorageTask.this = r1;
            if (exc == null) {
                if (r1.isCanceled()) {
                    this.error = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
                    return;
                } else if (r1.getInternalState() == 64) {
                    this.error = StorageException.fromErrorStatus(Status.RESULT_INTERNAL_ERROR);
                    return;
                } else {
                    this.error = null;
                    return;
                }
            }
            this.error = exc;
        }

        public StorageTask<ResultT> getTask() {
            return StorageTask.this;
        }

        public StorageReference getStorage() {
            return getTask().getStorage();
        }

        @Override // com.google.firebase.storage.StorageTask.ProvideError
        public Exception getError() {
            return this.error;
        }
    }
}
