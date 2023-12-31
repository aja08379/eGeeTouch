package com.google.firebase.storage;

import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes2.dex */
public class StorageException extends FirebaseException {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ERROR_BUCKET_NOT_FOUND = -13011;
    public static final int ERROR_CANCELED = -13040;
    public static final int ERROR_INVALID_CHECKSUM = -13031;
    public static final int ERROR_NOT_AUTHENTICATED = -13020;
    public static final int ERROR_NOT_AUTHORIZED = -13021;
    public static final int ERROR_OBJECT_NOT_FOUND = -13010;
    public static final int ERROR_PROJECT_NOT_FOUND = -13012;
    public static final int ERROR_QUOTA_EXCEEDED = -13013;
    public static final int ERROR_RETRY_LIMIT_EXCEEDED = -13030;
    public static final int ERROR_UNKNOWN = -13000;
    private static final int NETWORK_UNAVAILABLE = -2;
    private static final String TAG = "StorageException";
    private Throwable cause;
    private final int errorCode;
    private final int httpResultCode;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ErrorCode {
    }

    static String getErrorMessageForCode(int i) {
        if (i != -13040) {
            if (i != -13031) {
                if (i != -13030) {
                    if (i != -13021) {
                        if (i != -13020) {
                            switch (i) {
                                case ERROR_QUOTA_EXCEEDED /* -13013 */:
                                    return "Quota for bucket exceeded, please view quota on www.firebase.google.com/storage.";
                                case ERROR_PROJECT_NOT_FOUND /* -13012 */:
                                    return "Project does not exist.";
                                case ERROR_BUCKET_NOT_FOUND /* -13011 */:
                                    return "Bucket does not exist.";
                                case ERROR_OBJECT_NOT_FOUND /* -13010 */:
                                    return "Object does not exist at location.";
                                default:
                                    return "An unknown error occurred, please check the HTTP result code and inner exception for server response.";
                            }
                        }
                        return "User is not authenticated, please authenticate using Firebase Authentication and try again.";
                    }
                    return "User does not have permission to access this object.";
                }
                return "The operation retry limit has been exceeded.";
            }
            return "Object has a checksum which does not match. Please retry the operation.";
        }
        return "The operation was cancelled.";
    }

    private static boolean isResultSuccess(int i) {
        return i == 0 || (i >= 200 && i < 300);
    }

    StorageException(int i, Throwable th, int i2) {
        super(getErrorMessageForCode(i));
        this.cause = th;
        this.errorCode = i;
        this.httpResultCode = i2;
        Log.e(TAG, "StorageException has occurred.\n" + getErrorMessageForCode(i) + "\n Code: " + i + " HttpResult: " + i2);
        Throwable th2 = this.cause;
        if (th2 != null) {
            Log.e(TAG, th2.getMessage(), this.cause);
        }
    }

    private static int calculateErrorCode(Status status) {
        return status.isCanceled() ? ERROR_CANCELED : status.equals(Status.RESULT_TIMEOUT) ? ERROR_RETRY_LIMIT_EXCEEDED : ERROR_UNKNOWN;
    }

    private static int calculateErrorCode(Throwable th, int i) {
        return th instanceof CancelException ? ERROR_CANCELED : i != -2 ? i != 401 ? i != 409 ? i != 403 ? i != 404 ? ERROR_UNKNOWN : ERROR_OBJECT_NOT_FOUND : ERROR_NOT_AUTHORIZED : ERROR_INVALID_CHECKSUM : ERROR_NOT_AUTHENTICATED : ERROR_RETRY_LIMIT_EXCEEDED;
    }

    public static StorageException fromErrorStatus(Status status) {
        Preconditions.checkNotNull(status);
        Preconditions.checkArgument(!status.isSuccess());
        return new StorageException(calculateErrorCode(status), null, 0);
    }

    public static StorageException fromExceptionAndHttpCode(Throwable th, int i) {
        if (th instanceof StorageException) {
            return (StorageException) th;
        }
        if (isResultSuccess(i) && th == null) {
            return null;
        }
        return new StorageException(calculateErrorCode(th, i), th, i);
    }

    public static StorageException fromException(Throwable th) {
        return fromExceptionAndHttpCode(th, 0);
    }

    @Override // java.lang.Throwable
    public synchronized Throwable getCause() {
        Throwable th = this.cause;
        if (th == this) {
            return null;
        }
        return th;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public int getHttpResultCode() {
        return this.httpResultCode;
    }

    public boolean getIsRecoverableException() {
        return getErrorCode() == -13030;
    }
}
