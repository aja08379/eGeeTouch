package androidx.work.impl.utils;

import android.text.TextUtils;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import java.util.List;
/* loaded from: classes.dex */
public class EnqueueRunnable implements Runnable {
    private static final String TAG = Logger.tagWithPrefix("EnqueueRunnable");
    private final OperationImpl mOperation = new OperationImpl();
    private final WorkContinuationImpl mWorkContinuation;

    public EnqueueRunnable(WorkContinuationImpl workContinuation) {
        this.mWorkContinuation = workContinuation;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.mWorkContinuation.hasCycles()) {
                throw new IllegalStateException(String.format("WorkContinuation has cycles (%s)", this.mWorkContinuation));
            }
            if (addToDatabase()) {
                PackageManagerHelper.setComponentEnabled(this.mWorkContinuation.getWorkManagerImpl().getApplicationContext(), RescheduleReceiver.class, true);
                scheduleWorkInBackground();
            }
            this.mOperation.setState(Operation.SUCCESS);
        } catch (Throwable th) {
            this.mOperation.setState(new Operation.State.FAILURE(th));
        }
    }

    public Operation getOperation() {
        return this.mOperation;
    }

    public boolean addToDatabase() {
        WorkDatabase workDatabase = this.mWorkContinuation.getWorkManagerImpl().getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            boolean processContinuation = processContinuation(this.mWorkContinuation);
            workDatabase.setTransactionSuccessful();
            return processContinuation;
        } finally {
            workDatabase.endTransaction();
        }
    }

    public void scheduleWorkInBackground() {
        WorkManagerImpl workManagerImpl = this.mWorkContinuation.getWorkManagerImpl();
        Schedulers.schedule(workManagerImpl.getConfiguration(), workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }

    private static boolean processContinuation(WorkContinuationImpl workContinuation) {
        List<WorkContinuationImpl> parents = workContinuation.getParents();
        boolean z = false;
        if (parents != null) {
            boolean z2 = false;
            for (WorkContinuationImpl workContinuationImpl : parents) {
                if (!workContinuationImpl.isEnqueued()) {
                    z2 |= processContinuation(workContinuationImpl);
                } else {
                    Logger.get().warning(TAG, String.format("Already enqueued work ids (%s).", TextUtils.join(", ", workContinuationImpl.getIds())), new Throwable[0]);
                }
            }
            z = z2;
        }
        return enqueueContinuation(workContinuation) | z;
    }

    private static boolean enqueueContinuation(WorkContinuationImpl workContinuation) {
        boolean enqueueWorkWithPrerequisites = enqueueWorkWithPrerequisites(workContinuation.getWorkManagerImpl(), workContinuation.getWork(), (String[]) WorkContinuationImpl.prerequisitesFor(workContinuation).toArray(new String[0]), workContinuation.getName(), workContinuation.getExistingWorkPolicy());
        workContinuation.markEnqueued();
        return enqueueWorkWithPrerequisites;
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01eb A[LOOP:5: B:127:0x01e5->B:129:0x01eb, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0214 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x015f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean enqueueWorkWithPrerequisites(androidx.work.impl.WorkManagerImpl r18, java.util.List<? extends androidx.work.WorkRequest> r19, java.lang.String[] r20, java.lang.String r21, androidx.work.ExistingWorkPolicy r22) {
        boolean r12;
        boolean r13;
        boolean r14;
        boolean r3;
        long r16;
        boolean r3;
        androidx.work.impl.model.DependencyDao r16;
        androidx.work.impl.WorkManagerImpl r0 = r18;
        java.lang.String[] r1 = r20;
        long r4 = java.lang.System.currentTimeMillis();
        androidx.work.impl.WorkDatabase r6 = r18.getWorkDatabase();
        boolean r9 = r1 != null && r1.length > 0;
        if (r9) {
            r12 = true;
            r13 = false;
            r14 = false;
            for (java.lang.String r15 : r1) {
                androidx.work.impl.model.WorkSpec r8 = r6.workSpecDao().getWorkSpec(r15);
                if (r8 == null) {
                    androidx.work.Logger.get().error(androidx.work.impl.utils.EnqueueRunnable.TAG, java.lang.String.format("Prerequisite %s doesn't exist; not enqueuing", r15), new java.lang.Throwable[0]);
                    return false;
                }
                androidx.work.WorkInfo.State r8 = r8.state;
                r12 &= r8 == androidx.work.WorkInfo.State.SUCCEEDED;
                if (r8 == androidx.work.WorkInfo.State.FAILED) {
                    r14 = true;
                } else if (r8 == androidx.work.WorkInfo.State.CANCELLED) {
                    r13 = true;
                }
            }
        } else {
            r12 = true;
            r13 = false;
            r14 = false;
        }
        boolean r8 = !android.text.TextUtils.isEmpty(r21);
        if (r8 && !r9) {
            java.util.List<androidx.work.impl.model.WorkSpec.IdAndState> r10 = r6.workSpecDao().getWorkSpecIdAndStatesForName(r21);
            if (!r10.isEmpty()) {
                if (r22 == androidx.work.ExistingWorkPolicy.APPEND || r22 == androidx.work.ExistingWorkPolicy.APPEND_OR_REPLACE) {
                    androidx.work.impl.model.DependencyDao r9 = r6.dependencyDao();
                    java.util.List r11 = new java.util.ArrayList();
                    for (androidx.work.impl.model.WorkSpec.IdAndState r15 : r10) {
                        if (r9.hasDependents(r15.id)) {
                            r16 = r9;
                        } else {
                            r16 = r9;
                            boolean r7 = (r15.state == androidx.work.WorkInfo.State.SUCCEEDED) & r12;
                            if (r15.state == androidx.work.WorkInfo.State.FAILED) {
                                r14 = true;
                            } else if (r15.state == androidx.work.WorkInfo.State.CANCELLED) {
                                r13 = true;
                            }
                            r11.add(r15.id);
                            r12 = r7;
                        }
                        r9 = r16;
                    }
                    if (r22 == androidx.work.ExistingWorkPolicy.APPEND_OR_REPLACE && (r13 || r14)) {
                        androidx.work.impl.model.WorkSpecDao r3 = r6.workSpecDao();
                        for (androidx.work.impl.model.WorkSpec.IdAndState r9 : r3.getWorkSpecIdAndStatesForName(r21)) {
                            r3.delete(r9.id);
                        }
                        r11 = java.util.Collections.emptyList();
                        r3 = false;
                        r13 = false;
                    } else {
                        r3 = r14;
                    }
                    r1 = (java.lang.String[]) r11.toArray(r1);
                    r9 = r1.length > 0;
                    r14 = r3;
                } else {
                    if (r22 == androidx.work.ExistingWorkPolicy.KEEP) {
                        for (androidx.work.impl.model.WorkSpec.IdAndState r11 : r10) {
                            if (r11.state == androidx.work.WorkInfo.State.ENQUEUED || r11.state == androidx.work.WorkInfo.State.RUNNING) {
                                return false;
                            }
                        }
                    }
                    androidx.work.impl.utils.CancelWorkRunnable.forName(r21, r0, false).run();
                    androidx.work.impl.model.WorkSpecDao r3 = r6.workSpecDao();
                    for (androidx.work.impl.model.WorkSpec.IdAndState r11 : r10) {
                        r3.delete(r11.id);
                    }
                    r3 = true;
                    for (androidx.work.WorkRequest r10 : r19) {
                        androidx.work.impl.model.WorkSpec r11 = r10.getWorkSpec();
                        if (!r9 || r12) {
                            if (!r11.isPeriodic()) {
                                r11.periodStartTime = r4;
                            } else {
                                r16 = r4;
                                r11.periodStartTime = 0L;
                                if (android.os.Build.VERSION.SDK_INT < 23 && android.os.Build.VERSION.SDK_INT <= 25) {
                                    tryDelegateConstrainedWorkSpec(r11);
                                } else if (android.os.Build.VERSION.SDK_INT <= 22 && usesScheduler(r0, androidx.work.impl.Schedulers.GCM_SCHEDULER)) {
                                    tryDelegateConstrainedWorkSpec(r11);
                                }
                                if (r11.state == androidx.work.WorkInfo.State.ENQUEUED) {
                                    r3 = true;
                                }
                                r6.workSpecDao().insertWorkSpec(r11);
                                if (r9) {
                                    for (java.lang.String r11 : r1) {
                                        r6.dependencyDao().insertDependency(new androidx.work.impl.model.Dependency(r10.getStringId(), r11));
                                    }
                                }
                                for (java.lang.String r4 : r10.getTags()) {
                                    r6.workTagDao().insert(new androidx.work.impl.model.WorkTag(r4, r10.getStringId()));
                                }
                                if (!r8) {
                                    r6.workNameDao().insert(new androidx.work.impl.model.WorkName(r21, r10.getStringId()));
                                }
                                r0 = r18;
                                r4 = r16;
                            }
                        } else if (r14) {
                            r11.state = androidx.work.WorkInfo.State.FAILED;
                        } else if (r13) {
                            r11.state = androidx.work.WorkInfo.State.CANCELLED;
                        } else {
                            r11.state = androidx.work.WorkInfo.State.BLOCKED;
                        }
                        r16 = r4;
                        if (android.os.Build.VERSION.SDK_INT < 23) {
                        }
                        if (android.os.Build.VERSION.SDK_INT <= 22) {
                            tryDelegateConstrainedWorkSpec(r11);
                        }
                        if (r11.state == androidx.work.WorkInfo.State.ENQUEUED) {
                        }
                        r6.workSpecDao().insertWorkSpec(r11);
                        if (r9) {
                        }
                        while (r0.hasNext()) {
                        }
                        if (!r8) {
                        }
                        r0 = r18;
                        r4 = r16;
                    }
                    return r3;
                }
            }
        }
        r3 = false;
        while (r7.hasNext()) {
        }
        return r3;
    }

    private static void tryDelegateConstrainedWorkSpec(WorkSpec workSpec) {
        Constraints constraints = workSpec.constraints;
        String str = workSpec.workerClassName;
        if (str.equals(ConstraintTrackingWorker.class.getName())) {
            return;
        }
        if (constraints.requiresBatteryNotLow() || constraints.requiresStorageNotLow()) {
            Data.Builder builder = new Data.Builder();
            builder.putAll(workSpec.input).putString(ConstraintTrackingWorker.ARGUMENT_CLASS_NAME, str);
            workSpec.workerClassName = ConstraintTrackingWorker.class.getName();
            workSpec.input = builder.build();
        }
    }

    private static boolean usesScheduler(WorkManagerImpl workManager, String className) {
        try {
            Class<?> cls = Class.forName(className);
            for (Scheduler scheduler : workManager.getSchedulers()) {
                if (cls.isAssignableFrom(scheduler.getClass())) {
                    return true;
                }
            }
        } catch (ClassNotFoundException unused) {
        }
        return false;
    }
}
