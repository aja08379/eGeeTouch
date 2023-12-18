package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.NetworkInfo;
import android.os.Build;
import com.squareup.picasso.NetworkRequestHandler;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class BitmapHunter implements Runnable {
    Action action;
    List<Action> actions;
    final Cache cache;
    final Request data;
    final Dispatcher dispatcher;
    Exception exception;
    int exifOrientation;
    Future<?> future;
    final String key;
    Picasso.LoadedFrom loadedFrom;
    final int memoryPolicy;
    int networkPolicy;
    final Picasso picasso;
    Picasso.Priority priority;
    final RequestHandler requestHandler;
    Bitmap result;
    int retryCount;
    final int sequence = SEQUENCE_GENERATOR.incrementAndGet();
    final Stats stats;
    private static final Object DECODE_LOCK = new Object();
    private static final ThreadLocal<StringBuilder> NAME_BUILDER = new ThreadLocal<StringBuilder>() { // from class: com.squareup.picasso.BitmapHunter.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public StringBuilder initialValue() {
            return new StringBuilder("Picasso-");
        }
    };
    private static final AtomicInteger SEQUENCE_GENERATOR = new AtomicInteger();
    private static final RequestHandler ERRORING_HANDLER = new RequestHandler() { // from class: com.squareup.picasso.BitmapHunter.2
        @Override // com.squareup.picasso.RequestHandler
        public boolean canHandleRequest(Request request) {
            return true;
        }

        @Override // com.squareup.picasso.RequestHandler
        public RequestHandler.Result load(Request request, int i) throws IOException {
            throw new IllegalStateException("Unrecognized type of request: " + request);
        }
    };

    static int getExifRotation(int i) {
        switch (i) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    static int getExifTranslation(int i) {
        return (i == 2 || i == 7 || i == 4 || i == 5) ? -1 : 1;
    }

    private static boolean shouldResize(boolean z, int i, int i2, int i3, int i4) {
        return !z || (i3 != 0 && i > i3) || (i4 != 0 && i2 > i4);
    }

    BitmapHunter(Picasso picasso, Dispatcher dispatcher, Cache cache, Stats stats, Action action, RequestHandler requestHandler) {
        this.picasso = picasso;
        this.dispatcher = dispatcher;
        this.cache = cache;
        this.stats = stats;
        this.action = action;
        this.key = action.getKey();
        this.data = action.getRequest();
        this.priority = action.getPriority();
        this.memoryPolicy = action.getMemoryPolicy();
        this.networkPolicy = action.getNetworkPolicy();
        this.requestHandler = requestHandler;
        this.retryCount = requestHandler.getRetryCount();
    }

    static Bitmap decodeStream(Source source, Request request) throws IOException {
        BufferedSource buffer = Okio.buffer(source);
        boolean isWebPFile = Utils.isWebPFile(buffer);
        boolean z = request.purgeable && Build.VERSION.SDK_INT < 21;
        BitmapFactory.Options createBitmapOptions = RequestHandler.createBitmapOptions(request);
        boolean requiresInSampleSize = RequestHandler.requiresInSampleSize(createBitmapOptions);
        if (isWebPFile || z) {
            byte[] readByteArray = buffer.readByteArray();
            if (requiresInSampleSize) {
                BitmapFactory.decodeByteArray(readByteArray, 0, readByteArray.length, createBitmapOptions);
                RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, createBitmapOptions, request);
            }
            return BitmapFactory.decodeByteArray(readByteArray, 0, readByteArray.length, createBitmapOptions);
        }
        InputStream inputStream = buffer.inputStream();
        if (requiresInSampleSize) {
            MarkableInputStream markableInputStream = new MarkableInputStream(inputStream);
            markableInputStream.allowMarksToExpire(false);
            long savePosition = markableInputStream.savePosition(1024);
            BitmapFactory.decodeStream(markableInputStream, null, createBitmapOptions);
            RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, createBitmapOptions, request);
            markableInputStream.reset(savePosition);
            markableInputStream.allowMarksToExpire(true);
            inputStream = markableInputStream;
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, createBitmapOptions);
        if (decodeStream != null) {
            return decodeStream;
        }
        throw new IOException("Failed to decode stream.");
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                try {
                    updateThreadName(this.data);
                    if (this.picasso.loggingEnabled) {
                        Utils.log("Hunter", "executing", Utils.getLogIdsForHunter(this));
                    }
                    Bitmap hunt = hunt();
                    this.result = hunt;
                    if (hunt == null) {
                        this.dispatcher.dispatchFailed(this);
                    } else {
                        this.dispatcher.dispatchComplete(this);
                    }
                } catch (Exception e) {
                    this.exception = e;
                    this.dispatcher.dispatchFailed(this);
                } catch (OutOfMemoryError e2) {
                    StringWriter stringWriter = new StringWriter();
                    this.stats.createSnapshot().dump(new PrintWriter(stringWriter));
                    this.exception = new RuntimeException(stringWriter.toString(), e2);
                    this.dispatcher.dispatchFailed(this);
                }
            } catch (NetworkRequestHandler.ResponseException e3) {
                if (!NetworkPolicy.isOfflineOnly(e3.networkPolicy) || e3.code != 504) {
                    this.exception = e3;
                }
                this.dispatcher.dispatchFailed(this);
            } catch (IOException e4) {
                this.exception = e4;
                this.dispatcher.dispatchRetry(this);
            }
        } finally {
            Thread.currentThread().setName("Picasso-Idle");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bitmap hunt() throws IOException {
        Bitmap bitmap;
        if (MemoryPolicy.shouldReadFromMemoryCache(this.memoryPolicy)) {
            bitmap = this.cache.get(this.key);
            if (bitmap != null) {
                this.stats.dispatchCacheHit();
                this.loadedFrom = Picasso.LoadedFrom.MEMORY;
                if (this.picasso.loggingEnabled) {
                    Utils.log("Hunter", "decoded", this.data.logId(), "from cache");
                }
                return bitmap;
            }
        } else {
            bitmap = null;
        }
        int i = this.retryCount == 0 ? NetworkPolicy.OFFLINE.index : this.networkPolicy;
        this.networkPolicy = i;
        RequestHandler.Result load = this.requestHandler.load(this.data, i);
        if (load != null) {
            this.loadedFrom = load.getLoadedFrom();
            this.exifOrientation = load.getExifOrientation();
            bitmap = load.getBitmap();
            if (bitmap == null) {
                Source source = load.getSource();
                try {
                    bitmap = decodeStream(source, this.data);
                } finally {
                    try {
                        source.close();
                    } catch (IOException unused) {
                    }
                }
            }
        }
        if (bitmap != null) {
            if (this.picasso.loggingEnabled) {
                Utils.log("Hunter", "decoded", this.data.logId());
            }
            this.stats.dispatchBitmapDecoded(bitmap);
            if (this.data.needsTransformation() || this.exifOrientation != 0) {
                synchronized (DECODE_LOCK) {
                    if (this.data.needsMatrixTransform() || this.exifOrientation != 0) {
                        bitmap = transformResult(this.data, bitmap, this.exifOrientation);
                        if (this.picasso.loggingEnabled) {
                            Utils.log("Hunter", "transformed", this.data.logId());
                        }
                    }
                    if (this.data.hasCustomTransformations()) {
                        bitmap = applyCustomTransformations(this.data.transformations, bitmap);
                        if (this.picasso.loggingEnabled) {
                            Utils.log("Hunter", "transformed", this.data.logId(), "from custom transformations");
                        }
                    }
                }
                if (bitmap != null) {
                    this.stats.dispatchBitmapTransformed(bitmap);
                }
            }
        }
        return bitmap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void attach(Action action) {
        boolean z = this.picasso.loggingEnabled;
        Request request = action.request;
        if (this.action == null) {
            this.action = action;
            if (z) {
                List<Action> list = this.actions;
                if (list == null || list.isEmpty()) {
                    Utils.log("Hunter", "joined", request.logId(), "to empty hunter");
                    return;
                } else {
                    Utils.log("Hunter", "joined", request.logId(), Utils.getLogIdsForHunter(this, "to "));
                    return;
                }
            }
            return;
        }
        if (this.actions == null) {
            this.actions = new ArrayList(3);
        }
        this.actions.add(action);
        if (z) {
            Utils.log("Hunter", "joined", request.logId(), Utils.getLogIdsForHunter(this, "to "));
        }
        Picasso.Priority priority = action.getPriority();
        if (priority.ordinal() > this.priority.ordinal()) {
            this.priority = priority;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void detach(Action action) {
        boolean remove;
        if (this.action == action) {
            this.action = null;
            remove = true;
        } else {
            List<Action> list = this.actions;
            remove = list != null ? list.remove(action) : false;
        }
        if (remove && action.getPriority() == this.priority) {
            this.priority = computeNewPriority();
        }
        if (this.picasso.loggingEnabled) {
            Utils.log("Hunter", "removed", action.request.logId(), Utils.getLogIdsForHunter(this, "from "));
        }
    }

    private Picasso.Priority computeNewPriority() {
        Picasso.Priority priority = Picasso.Priority.LOW;
        List<Action> list = this.actions;
        boolean z = true;
        boolean z2 = (list == null || list.isEmpty()) ? false : true;
        Action action = this.action;
        if (action == null && !z2) {
            z = false;
        }
        if (z) {
            if (action != null) {
                priority = action.getPriority();
            }
            if (z2) {
                int size = this.actions.size();
                for (int i = 0; i < size; i++) {
                    Picasso.Priority priority2 = this.actions.get(i).getPriority();
                    if (priority2.ordinal() > priority.ordinal()) {
                        priority = priority2;
                    }
                }
            }
            return priority;
        }
        return priority;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean cancel() {
        Future<?> future;
        if (this.action == null) {
            List<Action> list = this.actions;
            return (list == null || list.isEmpty()) && (future = this.future) != null && future.cancel(false);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isCancelled() {
        Future<?> future = this.future;
        return future != null && future.isCancelled();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldRetry(boolean z, NetworkInfo networkInfo) {
        int i = this.retryCount;
        if (i > 0) {
            this.retryCount = i - 1;
            return this.requestHandler.shouldRetry(z, networkInfo);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean supportsReplay() {
        return this.requestHandler.supportsReplay();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bitmap getResult() {
        return this.result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getKey() {
        return this.key;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMemoryPolicy() {
        return this.memoryPolicy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request getData() {
        return this.data;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Action getAction() {
        return this.action;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Picasso getPicasso() {
        return this.picasso;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Action> getActions() {
        return this.actions;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Exception getException() {
        return this.exception;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Picasso.LoadedFrom getLoadedFrom() {
        return this.loadedFrom;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Picasso.Priority getPriority() {
        return this.priority;
    }

    static void updateThreadName(Request request) {
        String name = request.getName();
        StringBuilder sb = NAME_BUILDER.get();
        sb.ensureCapacity(name.length() + 8);
        sb.replace(8, sb.length(), name);
        Thread.currentThread().setName(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BitmapHunter forRequest(Picasso picasso, Dispatcher dispatcher, Cache cache, Stats stats, Action action) {
        Request request = action.getRequest();
        List<RequestHandler> requestHandlers = picasso.getRequestHandlers();
        int size = requestHandlers.size();
        for (int i = 0; i < size; i++) {
            RequestHandler requestHandler = requestHandlers.get(i);
            if (requestHandler.canHandleRequest(request)) {
                return new BitmapHunter(picasso, dispatcher, cache, stats, action, requestHandler);
            }
        }
        return new BitmapHunter(picasso, dispatcher, cache, stats, action, ERRORING_HANDLER);
    }

    static Bitmap applyCustomTransformations(List<Transformation> list, Bitmap bitmap) {
        int size = list.size();
        int i = 0;
        while (i < size) {
            final Transformation transformation = list.get(i);
            try {
                Bitmap transform = transformation.transform(bitmap);
                if (transform == null) {
                    final StringBuilder append = new StringBuilder().append("Transformation ").append(transformation.key()).append(" returned null after ").append(i).append(" previous transformation(s).\n\nTransformation list:\n");
                    for (Transformation transformation2 : list) {
                        append.append(transformation2.key()).append('\n');
                    }
                    Picasso.HANDLER.post(new Runnable() { // from class: com.squareup.picasso.BitmapHunter.4
                        @Override // java.lang.Runnable
                        public void run() {
                            throw new NullPointerException(append.toString());
                        }
                    });
                    return null;
                } else if (transform == bitmap && bitmap.isRecycled()) {
                    Picasso.HANDLER.post(new Runnable() { // from class: com.squareup.picasso.BitmapHunter.5
                        @Override // java.lang.Runnable
                        public void run() {
                            throw new IllegalStateException("Transformation " + Transformation.this.key() + " returned input Bitmap but recycled it.");
                        }
                    });
                    return null;
                } else if (transform != bitmap && !bitmap.isRecycled()) {
                    Picasso.HANDLER.post(new Runnable() { // from class: com.squareup.picasso.BitmapHunter.6
                        @Override // java.lang.Runnable
                        public void run() {
                            throw new IllegalStateException("Transformation " + Transformation.this.key() + " mutated input Bitmap but failed to recycle the original.");
                        }
                    });
                    return null;
                } else {
                    i++;
                    bitmap = transform;
                }
            } catch (RuntimeException e) {
                Picasso.HANDLER.post(new Runnable() { // from class: com.squareup.picasso.BitmapHunter.3
                    @Override // java.lang.Runnable
                    public void run() {
                        throw new RuntimeException("Transformation " + Transformation.this.key() + " crashed with exception.", e);
                    }
                });
                return null;
            }
        }
        return bitmap;
    }

    /* JADX WARN: Removed duplicated region for block: B:93:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x026f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static android.graphics.Bitmap transformResult(com.squareup.picasso.Request r28, android.graphics.Bitmap r29, int r30) {
        int r20;
        int r17;
        boolean r18;
        android.graphics.Matrix r19;
        android.graphics.Matrix r3;
        int r5;
        int r2;
        float r0;
        float r1;
        float r1;
        float r7;
        float r0;
        float r1;
        float r1;
        float r7;
        int r2;
        int r5;
        float r1;
        float r7;
        float r8;
        int r0;
        int r9;
        float r8;
        boolean r10;
        int r7;
        int r16;
        int r0;
        int r6;
        int r8;
        int r5;
        int r7;
        int r0;
        android.graphics.Bitmap r0;
        int r1 = r29.getWidth();
        int r2 = r29.getHeight();
        boolean r3 = r28.onlyScaleDown;
        android.graphics.Matrix r9 = new android.graphics.Matrix();
        if (r28.needsMatrixTransform() || r30 != 0) {
            int r4 = r28.targetWidth;
            int r6 = r28.targetHeight;
            float r7 = r28.rotationDegrees;
            if (r7 != 0.0f) {
                double r10 = r7;
                double r12 = java.lang.Math.cos(java.lang.Math.toRadians(r10));
                double r10 = java.lang.Math.sin(java.lang.Math.toRadians(r10));
                if (r28.hasRotationPivot) {
                    r9.setRotate(r7, r28.rotationPivotX, r28.rotationPivotY);
                    double r14 = 1.0d - r12;
                    double r6 = (r28.rotationPivotX * r14) + (r28.rotationPivotY * r10);
                    double r4 = (r28.rotationPivotY * r14) - (r28.rotationPivotX * r10);
                    double r14 = (r28.targetWidth * r12) + r6;
                    r17 = r2;
                    r18 = r3;
                    double r2 = (r28.targetWidth * r10) + r4;
                    r19 = r9;
                    r20 = r1;
                    double r8 = ((r28.targetWidth * r12) + r6) - (r28.targetHeight * r10);
                    double r1 = (r28.targetWidth * r10) + r4 + (r28.targetHeight * r12);
                    double r8 = r6 - (r28.targetHeight * r10);
                    double r10 = (r28.targetHeight * r12) + r4;
                    double r12 = java.lang.Math.max(r8, java.lang.Math.max(r8, java.lang.Math.max(r6, r14)));
                    double r6 = java.lang.Math.min(r8, java.lang.Math.min(r8, java.lang.Math.min(r6, r14)));
                    double r10 = java.lang.Math.max(r10, java.lang.Math.max(r1, java.lang.Math.max(r4, r2)));
                    double r1 = java.lang.Math.min(r10, java.lang.Math.min(r1, java.lang.Math.min(r4, r2)));
                    r4 = (int) java.lang.Math.floor(r12 - r6);
                    r6 = (int) java.lang.Math.floor(r10 - r1);
                } else {
                    r20 = r1;
                    r17 = r2;
                    r18 = r3;
                    r9.setRotate(r7);
                    double r2 = r28.targetWidth * r12;
                    double r4 = r28.targetWidth * r10;
                    double r6 = (r28.targetWidth * r12) - (r28.targetHeight * r10);
                    double r8 = (r28.targetWidth * r10) + (r28.targetHeight * r12);
                    double r10 = -(r28.targetHeight * r10);
                    double r14 = r28.targetHeight * r12;
                    r19 = r9;
                    double r0 = java.lang.Math.max(r10, java.lang.Math.max(r6, java.lang.Math.max((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, r2)));
                    double r2 = java.lang.Math.min(r10, java.lang.Math.min(r6, java.lang.Math.min((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, r2)));
                    r6 = (int) java.lang.Math.floor(java.lang.Math.max(r14, java.lang.Math.max(r8, java.lang.Math.max((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, r4))) - java.lang.Math.min(r14, java.lang.Math.min(r8, java.lang.Math.min((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, r4))));
                    r4 = (int) java.lang.Math.floor(r0 - r2);
                }
            } else {
                r20 = r1;
                r17 = r2;
                r18 = r3;
                r19 = r9;
            }
            if (r30 != 0) {
                int r0 = getExifRotation(r30);
                int r1 = getExifTranslation(r30);
                if (r0 != 0) {
                    r3 = r19;
                    r3.preRotate(r0);
                    if (r0 == 90 || r0 == 270) {
                        int r27 = r6;
                        r6 = r4;
                        r4 = r27;
                    }
                } else {
                    r3 = r19;
                }
                if (r1 != 1) {
                    r3.postScale(r1, 1.0f);
                }
            } else {
                r3 = r19;
            }
            if (r28.centerCrop) {
                if (r4 != 0) {
                    r2 = r20;
                    r1 = r4 / r2;
                    r5 = r17;
                } else {
                    r2 = r20;
                    r5 = r17;
                    r1 = r6 / r5;
                }
                if (r6 != 0) {
                    r7 = r6;
                    r8 = r5;
                } else {
                    r7 = r4;
                    r8 = r2;
                }
                float r7 = r7 / r8;
                if (r1 > r7) {
                    int r7 = (int) java.lang.Math.ceil(r5 * (r7 / r1));
                    if ((r28.centerCropGravity & 48) == 48) {
                        r0 = 0;
                    } else {
                        r0 = (r28.centerCropGravity & 80) == 80 ? r5 - r7 : (r5 - r7) / 2;
                    }
                    r8 = r6 / r7;
                    r9 = r7;
                    r10 = r18;
                    r16 = 0;
                    r7 = r0;
                    r0 = r2;
                } else if (r1 < r7) {
                    int r1 = (int) java.lang.Math.ceil(r2 * (r1 / r7));
                    if ((r28.centerCropGravity & 3) == 3) {
                        r0 = 0;
                    } else {
                        r0 = (r28.centerCropGravity & 5) == 5 ? r2 - r1 : (r2 - r1) / 2;
                    }
                    r16 = r0;
                    r0 = r1;
                    r9 = r5;
                    r1 = r4 / r1;
                    r10 = r18;
                    r8 = r7;
                    r7 = 0;
                } else {
                    r0 = r2;
                    r9 = r5;
                    r1 = r7;
                    r8 = r1;
                    r10 = r18;
                    r7 = 0;
                    r16 = 0;
                }
                if (shouldResize(r10, r2, r5, r4, r6)) {
                    r3.preScale(r1, r8);
                }
                r6 = r7;
                r8 = r9;
                r5 = r16;
                r7 = r0;
                r0 = android.graphics.Bitmap.createBitmap(r29, r5, r6, r7, r8, r3, true);
                if (r0 == r29) {
                    r29.recycle();
                    return r0;
                }
                return r29;
            }
            r5 = r17;
            boolean r10 = r18;
            r2 = r20;
            if (r28.centerInside) {
                if (r4 != 0) {
                    r0 = r4;
                    r1 = r2;
                } else {
                    r0 = r6;
                    r1 = r5;
                }
                float r0 = r0 / r1;
                if (r6 != 0) {
                    r1 = r6;
                    r7 = r5;
                } else {
                    r1 = r4;
                    r7 = r2;
                }
                float r1 = r1 / r7;
                if (r0 >= r1) {
                    r0 = r1;
                }
                if (shouldResize(r10, r2, r5, r4, r6)) {
                    r3.preScale(r0, r0);
                }
            } else if ((r4 != 0 || r6 != 0) && (r4 != r2 || r6 != r5)) {
                if (r4 != 0) {
                    r0 = r4;
                    r1 = r2;
                } else {
                    r0 = r6;
                    r1 = r5;
                }
                float r0 = r0 / r1;
                if (r6 != 0) {
                    r1 = r6;
                    r7 = r5;
                } else {
                    r1 = r4;
                    r7 = r2;
                }
                float r1 = r1 / r7;
                if (shouldResize(r10, r2, r5, r4, r6)) {
                    r3.preScale(r0, r1);
                }
            }
        } else {
            r5 = r2;
            r3 = r9;
            r2 = r1;
        }
        r7 = r2;
        r8 = r5;
        r5 = 0;
        r6 = 0;
        r0 = android.graphics.Bitmap.createBitmap(r29, r5, r6, r7, r8, r3, true);
        if (r0 == r29) {
        }
    }
}
