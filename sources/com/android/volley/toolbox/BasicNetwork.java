package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;
/* loaded from: classes.dex */
public class BasicNetwork implements Network {
    protected static final boolean DEBUG = VolleyLog.DEBUG;
    private static int DEFAULT_POOL_SIZE = 4096;
    private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool byteArrayPool) {
        this.mHttpStack = httpStack;
        this.mPool = byteArrayPool;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0168 A[SYNTHETIC] */
    @Override // com.android.volley.Network
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.android.volley.NetworkResponse performRequest(com.android.volley.Request<?> r25) throws com.android.volley.VolleyError {
        java.util.Map<java.lang.String, java.lang.String> r18;
        byte[] r17;
        java.util.Map<java.lang.String, java.lang.String> r23;
        long r9 = android.os.SystemClock.elapsedRealtime();
        while (true) {
            java.util.Map<java.lang.String, java.lang.String> r1 = java.util.Collections.emptyMap();
            org.apache.http.HttpResponse r2 = null;
            try {
                try {
                    java.util.HashMap r0 = new java.util.HashMap();
                    addCacheHeaders(r0, r25.getCacheEntry());
                    org.apache.http.HttpResponse r14 = r24.mHttpStack.performRequest(r25, r0);
                    try {
                        org.apache.http.StatusLine r6 = r14.getStatusLine();
                        int r0 = r6.getStatusCode();
                        java.util.Map<java.lang.String, java.lang.String> r5 = convertHeaders(r14.getAllHeaders());
                        try {
                            if (r0 == 304) {
                                com.android.volley.Cache.Entry r0 = r25.getCacheEntry();
                                if (r0 == null) {
                                    return new com.android.volley.NetworkResponse(304, null, r5, true, android.os.SystemClock.elapsedRealtime() - r9);
                                }
                                r0.responseHeaders.putAll(r5);
                                return new com.android.volley.NetworkResponse(304, r0.data, r0.responseHeaders, true, android.os.SystemClock.elapsedRealtime() - r9);
                            }
                            if (r0 == 301 || r0 == 302) {
                                try {
                                    r25.setRedirectUrl(r5.get("Location"));
                                } catch (java.io.IOException e) {
                                    r0 = e;
                                    r23 = r5;
                                    r17 = null;
                                    r2 = r14;
                                    r18 = r23;
                                    if (r2 != null) {
                                        int r1 = r2.getStatusLine().getStatusCode();
                                        if (r1 == 301 || r1 == 302) {
                                            com.android.volley.VolleyLog.e("Request at %s has been redirected to %s", r25.getOriginUrl(), r25.getUrl());
                                        } else {
                                            com.android.volley.VolleyLog.e("Unexpected response code %d for %s", java.lang.Integer.valueOf(r1), r25.getUrl());
                                        }
                                        if (r17 != null) {
                                            com.android.volley.NetworkResponse r0 = new com.android.volley.NetworkResponse(r1, r17, r18, false, android.os.SystemClock.elapsedRealtime() - r9);
                                            if (r1 == 401 || r1 == 403) {
                                                attemptRetryOnException("auth", r25, new com.android.volley.AuthFailureError(r0));
                                            } else if (r1 == 301 || r1 == 302) {
                                                attemptRetryOnException("redirect", r25, new com.android.volley.RedirectError(r0));
                                            } else {
                                                throw new com.android.volley.ServerError(r0);
                                            }
                                        } else {
                                            throw new com.android.volley.NetworkError(r0);
                                        }
                                    } else {
                                        throw new com.android.volley.NoConnectionError(r0);
                                    }
                                }
                            }
                            byte[] r22 = r14.getEntity() != null ? entityToBytes(r14.getEntity()) : new byte[0];
                            try {
                                r23 = r5;
                            } catch (java.io.IOException e) {
                                r0 = e;
                                r23 = r5;
                            }
                            try {
                                logSlowRequests(android.os.SystemClock.elapsedRealtime() - r9, r25, r22, r6);
                                if (r0 < 200 || r0 > 299) {
                                    throw new java.io.IOException();
                                }
                                return new com.android.volley.NetworkResponse(r0, r22, r23, false, android.os.SystemClock.elapsedRealtime() - r9);
                            } catch (java.io.IOException e) {
                                r0 = e;
                                r2 = r14;
                                r17 = r22;
                                r18 = r23;
                                if (r2 != null) {
                                }
                            }
                        } catch (java.io.IOException e) {
                            r0 = e;
                            r17 = null;
                            r18 = r5;
                            r2 = r14;
                            if (r2 != null) {
                            }
                        }
                    } catch (java.io.IOException e) {
                        r0 = e;
                        r18 = r1;
                        r17 = null;
                    }
                } catch (java.io.IOException e) {
                    r0 = e;
                    r18 = r1;
                    r17 = null;
                }
            } catch (java.net.MalformedURLException r0) {
                throw new java.lang.RuntimeException("Bad URL " + r25.getUrl(), r0);
            } catch (java.net.SocketTimeoutException unused) {
                attemptRetryOnException("socket", r25, new com.android.volley.TimeoutError());
            } catch (org.apache.http.conn.ConnectTimeoutException unused) {
                attemptRetryOnException("connection", r25, new com.android.volley.TimeoutError());
            }
        }
    }

    private void logSlowRequests(long j, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (DEBUG || j > SLOW_REQUEST_THRESHOLD_MS) {
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    private static void attemptRetryOnException(String str, Request<?> request, VolleyError volleyError) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", str, Integer.valueOf(timeoutMs)));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", str, Integer.valueOf(timeoutMs)));
            throw e;
        }
    }

    private void addCacheHeaders(Map<String, String> map, Cache.Entry entry) {
        if (entry == null) {
            return;
        }
        if (entry.etag != null) {
            map.put("If-None-Match", entry.etag);
        }
        if (entry.lastModified > 0) {
            map.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.lastModified)));
        }
    }

    protected void logError(String str, String str2, long j) {
        VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", str, Long.valueOf(SystemClock.elapsedRealtime() - j), str2);
    }

    private byte[] entityToBytes(HttpEntity httpEntity) throws IOException, ServerError {
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.mPool, (int) httpEntity.getContentLength());
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                throw new ServerError();
            }
            byte[] buf = this.mPool.getBuf(1024);
            while (true) {
                int read = content.read(buf);
                if (read == -1) {
                    break;
                }
                poolingByteArrayOutputStream.write(buf, 0, read);
            }
            byte[] byteArray = poolingByteArrayOutputStream.toByteArray();
            try {
                httpEntity.consumeContent();
            } catch (IOException unused) {
                VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
            }
            this.mPool.returnBuf(buf);
            poolingByteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                httpEntity.consumeContent();
            } catch (IOException unused2) {
                VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
            }
            this.mPool.returnBuf(null);
            poolingByteArrayOutputStream.close();
            throw th;
        }
    }

    protected static Map<String, String> convertHeaders(Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }
}
