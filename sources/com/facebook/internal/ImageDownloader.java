package com.facebook.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.facebook.internal.ImageRequest;
import com.facebook.internal.WorkQueue;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class ImageDownloader {
    private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static Handler handler;
    private static WorkQueue downloadQueue = new WorkQueue(8);
    private static WorkQueue cacheReadQueue = new WorkQueue(2);
    private static final Map<RequestKey, DownloaderContext> pendingRequests = new HashMap();

    public static void downloadAsync(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return;
        }
        RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        Map<RequestKey, DownloaderContext> map = pendingRequests;
        synchronized (map) {
            DownloaderContext downloaderContext = map.get(requestKey);
            if (downloaderContext != null) {
                downloaderContext.request = imageRequest;
                downloaderContext.isCancelled = false;
                downloaderContext.workItem.moveToFront();
            } else {
                enqueueCacheRead(imageRequest, requestKey, imageRequest.isCachedRedirectAllowed());
            }
        }
    }

    public static boolean cancelRequest(ImageRequest imageRequest) {
        boolean z;
        RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        Map<RequestKey, DownloaderContext> map = pendingRequests;
        synchronized (map) {
            DownloaderContext downloaderContext = map.get(requestKey);
            z = true;
            if (downloaderContext == null) {
                z = false;
            } else if (downloaderContext.workItem.cancel()) {
                map.remove(requestKey);
            } else {
                downloaderContext.isCancelled = true;
            }
        }
        return z;
    }

    public static void prioritizeRequest(ImageRequest imageRequest) {
        RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        Map<RequestKey, DownloaderContext> map = pendingRequests;
        synchronized (map) {
            DownloaderContext downloaderContext = map.get(requestKey);
            if (downloaderContext != null) {
                downloaderContext.workItem.moveToFront();
            }
        }
    }

    public static void clearCache(Context context) {
        ImageResponseCache.clearCache(context);
        UrlRedirectCache.clearCache();
    }

    private static void enqueueCacheRead(ImageRequest imageRequest, RequestKey requestKey, boolean z) {
        enqueueRequest(imageRequest, requestKey, cacheReadQueue, new CacheReadWorkItem(imageRequest.getContext(), requestKey, z));
    }

    private static void enqueueDownload(ImageRequest imageRequest, RequestKey requestKey) {
        enqueueRequest(imageRequest, requestKey, downloadQueue, new DownloadImageWorkItem(imageRequest.getContext(), requestKey));
    }

    private static void enqueueRequest(ImageRequest imageRequest, RequestKey requestKey, WorkQueue workQueue, Runnable runnable) {
        Map<RequestKey, DownloaderContext> map = pendingRequests;
        synchronized (map) {
            DownloaderContext downloaderContext = new DownloaderContext();
            downloaderContext.request = imageRequest;
            map.put(requestKey, downloaderContext);
            downloaderContext.workItem = workQueue.addActiveWorkItem(runnable);
        }
    }

    private static void issueResponse(RequestKey requestKey, final Exception exc, final Bitmap bitmap, final boolean z) {
        final ImageRequest imageRequest;
        final ImageRequest.Callback callback;
        DownloaderContext removePendingRequest = removePendingRequest(requestKey);
        if (removePendingRequest == null || removePendingRequest.isCancelled || (callback = (imageRequest = removePendingRequest.request).getCallback()) == null) {
            return;
        }
        getHandler().post(new Runnable() { // from class: com.facebook.internal.ImageDownloader.1
            @Override // java.lang.Runnable
            public void run() {
                callback.onCompleted(new ImageResponse(ImageRequest.this, exc, z, bitmap));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readFromCache(RequestKey requestKey, Context context, boolean z) {
        InputStream inputStream;
        Uri redirectedUri;
        boolean z2 = false;
        if (!z || (redirectedUri = UrlRedirectCache.getRedirectedUri(requestKey.uri)) == null) {
            inputStream = null;
        } else {
            inputStream = ImageResponseCache.getCachedImageStream(redirectedUri, context);
            if (inputStream != null) {
                z2 = true;
            }
        }
        if (!z2) {
            inputStream = ImageResponseCache.getCachedImageStream(requestKey.uri, context);
        }
        if (inputStream != null) {
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
            Utility.closeQuietly(inputStream);
            issueResponse(requestKey, null, decodeStream, z2);
            return;
        }
        DownloaderContext removePendingRequest = removePendingRequest(requestKey);
        if (removePendingRequest == null || removePendingRequest.isCancelled) {
            return;
        }
        enqueueDownload(removePendingRequest.request, requestKey);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void download(com.facebook.internal.ImageDownloader.RequestKey r9, android.content.Context r10) {
        java.net.HttpURLConnection r3;
        com.facebook.FacebookException r5;
        int r4;
        java.io.InputStream r10;
        android.graphics.Bitmap r4;
        com.facebook.FacebookException r5;
        com.facebook.FacebookException r5;
        java.io.Closeable r0 = null;
        java.io.InputStream r0 = null;
        r0 = null;
        android.graphics.Bitmap r0 = null;
        boolean r2 = true;
        try {
            r3 = (java.net.HttpURLConnection) new java.net.URL(r9.uri.toString()).openConnection();
            try {
                try {
                    r3.setInstanceFollowRedirects(false);
                    r4 = r3.getResponseCode();
                } catch (java.lang.Throwable th) {
                    r9 = th;
                }
                try {
                } catch (java.io.IOException e) {
                    r4 = e;
                } catch (java.lang.Throwable th) {
                    r9 = th;
                    r0 = r10;
                    com.facebook.internal.Utility.closeQuietly(r0);
                    com.facebook.internal.Utility.disconnectQuietly(r3);
                    throw r9;
                }
            } catch (java.io.IOException e) {
                r4 = e;
                r10 = 0;
            }
        } catch (java.io.IOException e) {
            r4 = e;
            r10 = 0;
            r3 = null;
        } catch (java.lang.Throwable th) {
            r9 = th;
            r3 = null;
        }
        if (r4 == 200) {
            r10 = com.facebook.internal.ImageResponseCache.interceptAndCacheImageStream(r10, r3);
            r4 = android.graphics.BitmapFactory.decodeStream(r10);
            r5 = null;
        } else if (r4 == 301 || r4 == 302) {
            try {
                java.lang.String r10 = r3.getHeaderField("location");
                if (!com.facebook.internal.Utility.isNullOrEmpty(r10)) {
                    android.net.Uri r10 = android.net.Uri.parse(r10);
                    com.facebook.internal.UrlRedirectCache.cacheUriRedirect(r9.uri, r10);
                    com.facebook.internal.ImageDownloader.DownloaderContext r2 = removePendingRequest(r9);
                    if (r2 != null && !r2.isCancelled) {
                        enqueueCacheRead(r2.request, new com.facebook.internal.ImageDownloader.RequestKey(r10, r9.tag), false);
                    }
                }
                r4 = null;
                r5 = null;
                r2 = false;
                com.facebook.internal.Utility.closeQuietly(r0);
                com.facebook.internal.Utility.disconnectQuietly(r3);
                r0 = r4;
                r5 = r5;
            } catch (java.io.IOException e) {
                r4 = e;
                r10 = 0;
                r2 = false;
                com.facebook.internal.Utility.closeQuietly(r10);
                com.facebook.internal.Utility.disconnectQuietly(r3);
                r5 = r4;
                if (r2) {
                }
            }
            if (r2) {
                issueResponse(r9, r5, r0, false);
                return;
            }
            return;
        } else {
            r10 = r3.getErrorStream();
            java.lang.StringBuilder r4 = new java.lang.StringBuilder();
            if (r10 != null) {
                java.io.InputStreamReader r5 = new java.io.InputStreamReader(r10);
                char[] r7 = new char[128];
                while (true) {
                    int r8 = r5.read(r7, 0, 128);
                    if (r8 <= 0) {
                        break;
                    }
                    r4.append(r7, 0, r8);
                }
                com.facebook.internal.Utility.closeQuietly(r5);
            } else {
                r4.append("Unexpected error while downloading an image.");
            }
            com.facebook.FacebookException r5 = new com.facebook.FacebookException(r4.toString());
            r4 = null;
            r5 = r5;
        }
        r0 = r10;
        r5 = r5;
        com.facebook.internal.Utility.closeQuietly(r0);
        com.facebook.internal.Utility.disconnectQuietly(r3);
        r0 = r4;
        r5 = r5;
        if (r2) {
        }
    }

    private static synchronized Handler getHandler() {
        Handler handler2;
        synchronized (ImageDownloader.class) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler2 = handler;
        }
        return handler2;
    }

    private static DownloaderContext removePendingRequest(RequestKey requestKey) {
        DownloaderContext remove;
        Map<RequestKey, DownloaderContext> map = pendingRequests;
        synchronized (map) {
            remove = map.remove(requestKey);
        }
        return remove;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class RequestKey {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        Object tag;
        Uri uri;

        RequestKey(Uri uri, Object obj) {
            this.uri = uri;
            this.tag = obj;
        }

        public int hashCode() {
            return ((1073 + this.uri.hashCode()) * 37) + this.tag.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof RequestKey)) {
                return false;
            }
            RequestKey requestKey = (RequestKey) obj;
            return requestKey.uri == this.uri && requestKey.tag == this.tag;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class DownloaderContext {
        boolean isCancelled;
        ImageRequest request;
        WorkQueue.WorkItem workItem;

        private DownloaderContext() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CacheReadWorkItem implements Runnable {
        private boolean allowCachedRedirects;
        private Context context;
        private RequestKey key;

        CacheReadWorkItem(Context context, RequestKey requestKey, boolean z) {
            this.context = context;
            this.key = requestKey;
            this.allowCachedRedirects = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageDownloader.readFromCache(this.key, this.context, this.allowCachedRedirects);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class DownloadImageWorkItem implements Runnable {
        private Context context;
        private RequestKey key;

        DownloadImageWorkItem(Context context, RequestKey requestKey) {
            this.context = context;
            this.key = requestKey;
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageDownloader.download(this.key, this.context);
        }
    }
}
