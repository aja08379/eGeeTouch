package com.google.firebase.functions;

import android.util.SparseArray;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.FirebaseException;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class FirebaseFunctionsException extends FirebaseException {
    private final Code code;
    private final Object details;

    /* loaded from: classes2.dex */
    public enum Code {
        OK(0),
        CANCELLED(1),
        UNKNOWN(2),
        INVALID_ARGUMENT(3),
        DEADLINE_EXCEEDED(4),
        NOT_FOUND(5),
        ALREADY_EXISTS(6),
        PERMISSION_DENIED(7),
        RESOURCE_EXHAUSTED(8),
        FAILED_PRECONDITION(9),
        ABORTED(10),
        OUT_OF_RANGE(11),
        UNIMPLEMENTED(12),
        INTERNAL(13),
        UNAVAILABLE(14),
        DATA_LOSS(15),
        UNAUTHENTICATED(16);
        
        private static final SparseArray<Code> STATUS_LIST = buildStatusList();
        private final int value;

        Code(int i) {
            this.value = i;
        }

        private static SparseArray<Code> buildStatusList() {
            Code[] values;
            SparseArray<Code> sparseArray = new SparseArray<>();
            for (Code code : values()) {
                Code code2 = sparseArray.get(code.ordinal());
                if (code2 != null) {
                    throw new IllegalStateException("Code value duplication between " + code2 + "&" + code.name());
                }
                sparseArray.put(code.ordinal(), code);
            }
            return sparseArray;
        }

        static Code fromValue(int i) {
            return STATUS_LIST.get(i, UNKNOWN);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static Code fromHttpStatus(int i) {
            if (i != 200) {
                if (i != 409) {
                    if (i != 429) {
                        if (i != 400) {
                            if (i != 401) {
                                if (i != 403) {
                                    if (i != 404) {
                                        if (i != 503) {
                                            if (i != 504) {
                                                switch (i) {
                                                    case 499:
                                                        return CANCELLED;
                                                    case 500:
                                                        return INTERNAL;
                                                    case 501:
                                                        return UNIMPLEMENTED;
                                                    default:
                                                        return UNKNOWN;
                                                }
                                            }
                                            return DEADLINE_EXCEEDED;
                                        }
                                        return UNAVAILABLE;
                                    }
                                    return NOT_FOUND;
                                }
                                return PERMISSION_DENIED;
                            }
                            return UNAUTHENTICATED;
                        }
                        return INVALID_ARGUMENT;
                    }
                    return RESOURCE_EXHAUSTED;
                }
                return ABORTED;
            }
            return OK;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FirebaseFunctionsException fromResponse(Code code, String str, Serializer serializer) {
        Object obj;
        String name = code.name();
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("error");
            if (jSONObject.opt("status") instanceof String) {
                code = Code.valueOf(jSONObject.getString("status"));
                name = code.name();
            }
            if ((jSONObject.opt(ShareConstants.WEB_DIALOG_PARAM_MESSAGE) instanceof String) && !jSONObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE).isEmpty()) {
                name = jSONObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
            }
            obj = jSONObject.opt("details");
            if (obj != null) {
                try {
                    obj = serializer.decode(obj);
                } catch (IllegalArgumentException unused) {
                    code = Code.INTERNAL;
                    name = code.name();
                } catch (JSONException unused2) {
                }
            }
        } catch (IllegalArgumentException unused3) {
            obj = null;
        } catch (JSONException unused4) {
            obj = null;
        }
        if (code == Code.OK) {
            return null;
        }
        return new FirebaseFunctionsException(name, code, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirebaseFunctionsException(String str, Code code, Object obj) {
        super(str);
        this.code = code;
        this.details = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirebaseFunctionsException(String str, Code code, Object obj, Throwable th) {
        super(str, th);
        this.code = code;
        this.details = obj;
    }

    public Code getCode() {
        return this.code;
    }

    public Object getDetails() {
        return this.details;
    }
}
