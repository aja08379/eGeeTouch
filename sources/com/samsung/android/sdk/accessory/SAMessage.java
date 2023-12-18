package com.samsung.android.sdk.accessory;

import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.accessory.api.ISAMexCallback;
import java.io.IOException;
import java.lang.ref.WeakReference;
/* loaded from: classes2.dex */
public abstract class SAMessage {
    public static final String ACTION_ACCESSORY_MESSAGE_DISABLED = "com.samsung.accessory.action.MESSAGE_DISABLED";
    public static final String ACTION_ACCESSORY_MESSAGE_ENABLED = "com.samsung.accessory.action.MESSAGE_ENABLED";
    public static final String ACTION_ACCESSORY_MESSAGE_RECEIVED = "com.samsung.accessory.action.MESSAGE_RECEIVED";
    public static final int ERROR_PEER_AGENT_NOT_SUPPORTED = 1795;
    public static final int ERROR_PEER_AGENT_NO_RESPONSE = 1794;
    public static final int ERROR_PEER_AGENT_UNREACHABLE = 1793;
    public static final int ERROR_PEER_SERVICE_NOT_SUPPORTED = 1796;
    public static final int ERROR_SERVICE_NOT_SUPPORTED = 1797;
    public static final int ERROR_UNKNOWN = 1798;
    public static final String EXTRA_PEER_ACCESSORY = "com.samsung.accessory.device.extra.SAPeerAccessory";
    public static final int MESSAGE_PRIORITY_HIGH = 3;
    public static final int MESSAGE_PRIORITY_LOW = 1;
    public static final int MESSAGE_PRIORITY_MEDIUM = 2;
    private static final String a = "[SA_SDK]" + SAMessage.class.getSimpleName();
    private static final int[] b = {2, 3, 6, 13, 15};
    private SAAdapter c;
    private MexCallback d;
    private Handler e;
    private String f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class MexCallback extends ISAMexCallback.Stub {
        private WeakReference<SAMessage> mMessageRef;

        MexCallback(SAMessage sAMessage) {
            this.mMessageRef = new WeakReference<>(sAMessage);
        }

        @Override // com.samsung.accessory.api.ISAMexCallback
        public void onReceived(Bundle bundle) throws RemoteException {
            SAMessage sAMessage = this.mMessageRef.get();
            if (sAMessage == null) {
                Log.e(SAMessage.a, "onMessageReceived(): SAMessage referecnce is null!");
            } else {
                sAMessage.a(bundle);
            }
        }

        @Override // com.samsung.accessory.api.ISAMexCallback
        public void onSent(Bundle bundle) throws RemoteException {
            SAMessage sAMessage = this.mMessageRef.get();
            if (sAMessage == null) {
                Log.e(SAMessage.a, "onMessageReceived(): SAMessage referecnce is null!");
            } else {
                SAMessage.c(sAMessage, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class a implements Runnable {
        private WeakReference<SAMessage> a;
        private Bundle b;
        private boolean c;

        a(SAMessage sAMessage, Bundle bundle, boolean z) {
            this.a = new WeakReference<>(sAMessage);
            this.b = bundle;
            this.c = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            SAMessage sAMessage = this.a.get();
            if (sAMessage == null) {
                Log.e(SAMessage.a, "run(): SAMessage referecnce is null!");
            } else if (this.c) {
                SAMessage.a(sAMessage, this.b);
            } else {
                SAMessage.b(sAMessage, this.b);
            }
        }
    }

    protected SAMessage(SAAgent sAAgent) {
        if (sAAgent == null) {
            Log.e(a, "SAMessage() - empty agent instance!");
            throw new IllegalArgumentException("Message creation failed! - invalid agent instance supplied");
        }
        this.c = SAAdapter.a(sAAgent.getApplicationContext());
        this.d = new MexCallback(this);
        this.e = sAAgent.a;
        String a2 = sAAgent.a(this);
        if (a2 != null) {
            b(a2);
        }
    }

    protected SAMessage(SAAgentV2 sAAgentV2) {
        if (sAAgentV2 == null) {
            Log.e(a, "SAMessage() - empty agent instance!");
            throw new IllegalArgumentException("Message creation failed! - invalid agent instance supplied");
        }
        this.c = SAAdapter.a(sAAgentV2.getApplicationContext());
        this.d = new MexCallback(this);
        this.e = sAAgentV2.a;
        String a2 = sAAgentV2.a(this);
        if (a2 != null) {
            b(a2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0129, code lost:
        if (r2 == (-1800)) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x012d, code lost:
        if (r2 == (-1799)) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x012f, code lost:
        r0 = "Send Message Failed - internal error!";
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0132, code lost:
        r0 = "Send Message Failed - Message timed out!";
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0135, code lost:
        r0 = "Send Message Failed - Peer Agent is invalid!";
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x013c, code lost:
        throw new java.io.IOException(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int a(com.samsung.android.sdk.accessory.SAPeerAgent r20, byte[] r21, boolean r22, int r23) throws java.io.IOException {
        int r14;
        com.samsung.android.sdk.accessory.f r18;
        if (r20 == null) {
            android.util.Log.e(com.samsung.android.sdk.accessory.SAMessage.a, "Send: peerAgent null");
            throw new java.lang.IllegalArgumentException("Send Message Failed! - Peer Agent is invalid!");
        } else if (r21 == null) {
            android.util.Log.e(com.samsung.android.sdk.accessory.SAMessage.a, "Send: data null");
            throw new java.lang.IllegalArgumentException("Invalid data to send!");
        } else if (r21.length == 0) {
            android.util.Log.e(com.samsung.android.sdk.accessory.SAMessage.a, "Send: invalid data length 0");
            throw new java.lang.IllegalArgumentException("Invalid data length 0");
        } else if (r21.length > r20.getMaxAllowedDataSize()) {
            android.util.Log.e(com.samsung.android.sdk.accessory.SAMessage.a, "Send: Data too big:" + r21.length);
            throw new java.lang.IllegalArgumentException("Data Too long..! Data size:" + r21.length + "Max allowed Size:" + r20.getMaxAllowedDataSize() + " .Please check SAPeerAgent.getMaxAllowedDataSize()");
        } else if (r19.f == null) {
            android.util.Log.e(com.samsung.android.sdk.accessory.SAMessage.a, "Send: agentId not retrieved!");
            throw new java.io.IOException("Failed to send message - Agent info empty!");
        } else {
            int r2 = !com.samsung.android.sdk.accessory.k.j() ? com.samsung.android.sdk.accessory.SAMessage.ERROR_SERVICE_NOT_SUPPORTED : r20.a();
            if (r2 != 1792) {
                onError(r20, -1, r2);
                return -1;
            }
            if (a(r20)) {
                android.util.Log.d(com.samsung.android.sdk.accessory.SAMessage.a, "MEX Priority : High (" + r20.getPeerId() + ")");
                r14 = 3;
            } else {
                r14 = r23;
            }
            int r8 = -1801;
            java.lang.String r15 = a(r19.f, r20.getAccessory().getId(), r20.getPeerId());
            int r6 = r22 ? r20.getAccessory().d() : 0;
            android.util.Log.d(com.samsung.android.sdk.accessory.SAMessage.a, "Sending data:" + r21.length + " bytes");
            com.samsung.android.sdk.accessory.f r13 = new com.samsung.android.sdk.accessory.f(2, r15);
            try {
                try {
                    r13.a(com.samsung.android.sdk.accessory.k.c(), com.samsung.android.sdk.accessory.k.d(), r20.getAccessory().c(), r6, r21);
                    int r2 = -1801;
                    while (true) {
                        try {
                            com.samsung.android.sdk.accessory.e r16 = r13.a();
                            if (r16 == null) {
                                r13.d();
                                return r2;
                            }
                            try {
                                r18 = r13;
                                try {
                                    try {
                                        r2 = r19.c.a(r19.f, r20.getPeerId(), r20.getAccessory().getId(), r16.d(), r22, r14, r16.e(), r16.f(), r16.g());
                                        if (r2 <= 0) {
                                            break;
                                        }
                                        android.util.Log.d(com.samsung.android.sdk.accessory.SAMessage.a, r15 + "-> msg<" + r2 + ">[" + r18.b().e() + "] sent: " + r18.c());
                                        try {
                                            try {
                                                r16.h();
                                                r13 = r18;
                                            } catch (java.io.IOException e) {
                                                r0 = e;
                                                r8 = r2;
                                                android.util.Log.e(com.samsung.android.sdk.accessory.SAMessage.a, "Send Message Failed! <" + r8 + " " + r0.getLocalizedMessage());
                                                throw r0;
                                            }
                                        } catch (java.lang.Throwable th) {
                                            r0 = th;
                                            r18.d();
                                            throw r0;
                                        }
                                    } catch (com.samsung.android.sdk.accessory.d e) {
                                        r0 = e;
                                        throw new java.io.IOException("Send Message Failed", r0);
                                    }
                                } catch (java.lang.Throwable th) {
                                    r0 = th;
                                    r8 = r2;
                                    try {
                                        r16.h();
                                        throw r0;
                                    } catch (java.io.IOException e) {
                                        r0 = e;
                                        android.util.Log.e(com.samsung.android.sdk.accessory.SAMessage.a, "Send Message Failed! <" + r8 + " " + r0.getLocalizedMessage());
                                        throw r0;
                                    }
                                }
                            } catch (com.samsung.android.sdk.accessory.d e) {
                                r0 = e;
                                r18 = r13;
                            } catch (java.lang.Throwable th) {
                                r0 = th;
                                r18 = r13;
                                r8 = r2;
                                r16.h();
                                throw r0;
                            }
                        } catch (java.io.IOException e) {
                            r0 = e;
                            r18 = r13;
                        }
                    }
                } catch (java.lang.Throwable th) {
                    r0 = th;
                    r18 = r13;
                    r18.d();
                    throw r0;
                }
            } catch (java.io.IOException e) {
                r0 = e;
                r18 = r13;
            }
        }
    }

    static /* synthetic */ String a(SAMessage sAMessage) {
        sAMessage.f = null;
        return null;
    }

    private static String a(String str, long j, String str2) {
        return new StringBuilder(40).append(str).append("_").append(j).append("_").append(str2).toString();
    }

    private void a(long j, int i, int i2) throws IOException {
        try {
            this.c.a(j, i, i2);
        } catch (d e) {
            Log.e(a, "Ack failed! ".concat(String.valueOf(e)));
            throw new IOException("Send Failed", e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0091 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ void a(com.samsung.android.sdk.accessory.SAMessage r16, android.os.Bundle r17) {
        java.lang.String r0;
        java.lang.String r1;
        int r15;
        int r4;
        int r0;
        if (r16.f == null) {
            r0 = com.samsung.android.sdk.accessory.SAMessage.a;
            r1 = "onMessageReceived(): Agent info empty!";
        } else {
            r17.setClassLoader(com.samsung.android.sdk.accessory.SAPeerAgent.class.getClassLoader());
            byte[] r3 = r17.getByteArray("com.samsung.accessory.adapter.extra.READ_BYTES");
            int r9 = r17.getInt("com.samsung.accessory.adapter.extra.READ_LENGHT");
            int r8 = r17.getInt("com.samsung.accessory.adapter.extra.READ_OFFSET");
            int r6 = r17.getInt("fragmentIndex");
            com.samsung.android.sdk.accessory.SAPeerAgent r10 = (com.samsung.android.sdk.accessory.SAPeerAgent) r17.getParcelable("peerAgent");
            int r11 = r17.getInt("transactionId");
            if (r10 == null || r10.getAccessory() == null) {
                r0 = com.samsung.android.sdk.accessory.SAMessage.a;
                r1 = "onMessageReceived(): PeerAgent is null!";
            } else {
                long r12 = r10.getAccessory().getId();
                java.lang.String r0 = r10.getPeerId();
                ?? r4 = r16.f;
                java.lang.String r14 = a(r0, r12, (java.lang.String) r4);
                try {
                    try {
                        r15 = 2;
                    } catch (java.lang.Throwable th) {
                        r0 = th;
                        r16.c.b(r3);
                        if (r4 != r15) {
                            com.samsung.android.sdk.accessory.i.b(r14);
                        }
                        throw r0;
                    }
                } catch (java.io.IOException e) {
                    r0 = e;
                    r15 = 2;
                } catch (java.lang.Throwable th) {
                    r0 = th;
                    r15 = 2;
                }
                try {
                    r4 = com.samsung.android.sdk.accessory.i.b(r14, r10.getAccessory().a(), r6, r3, r8, r9);
                    if (r4 == 1) {
                        try {
                            byte[] r0 = com.samsung.android.sdk.accessory.i.a(r14);
                            if (r0 == null) {
                                r0 = com.samsung.android.sdk.accessory.SAMessage.ERROR_UNKNOWN;
                                r16.c.b(r3);
                                if (r0 > 0) {
                                    try {
                                        try {
                                            r16.a(r12, r11, r0);
                                        } catch (java.io.IOException r0) {
                                            android.util.Log.e(com.samsung.android.sdk.accessory.SAMessage.a, "Failed to send message status! " + r0.getLocalizedMessage());
                                            if (r4 != 2) {
                                                com.samsung.android.sdk.accessory.i.b(r14);
                                                return;
                                            }
                                            return;
                                        }
                                    } catch (java.lang.Throwable r0) {
                                        if (r4 != 2) {
                                            com.samsung.android.sdk.accessory.i.b(r14);
                                        }
                                        throw r0;
                                    }
                                }
                                if (r4 == 2) {
                                    com.samsung.android.sdk.accessory.i.b(r14);
                                    return;
                                }
                                return;
                            }
                            r16.onReceive(r10, r0);
                            r0 = 1792;
                            r16.c.b(r3);
                            if (r0 > 0) {
                            }
                            if (r4 == 2) {
                            }
                        } catch (java.io.IOException e) {
                            r0 = e;
                            android.util.Log.e(com.samsung.android.sdk.accessory.SAMessage.a, "Error in onRead! ".concat(java.lang.String.valueOf(r0)));
                            r16.c.b(r3);
                            try {
                                try {
                                    r16.a(r12, r11, com.samsung.android.sdk.accessory.SAMessage.ERROR_UNKNOWN);
                                    if (r4 == r15) {
                                        com.samsung.android.sdk.accessory.i.b(r14);
                                        return;
                                    }
                                    return;
                                } catch (java.io.IOException r0) {
                                    android.util.Log.e(com.samsung.android.sdk.accessory.SAMessage.a, "Failed to send message status! " + r0.getLocalizedMessage());
                                    if (r4 != r15) {
                                        com.samsung.android.sdk.accessory.i.b(r14);
                                        return;
                                    }
                                    return;
                                }
                            } catch (java.lang.Throwable r0) {
                                if (r4 != r15) {
                                    com.samsung.android.sdk.accessory.i.b(r14);
                                }
                                throw r0;
                            }
                        }
                    } else {
                        if (r4 != 3) {
                            r0 = -1;
                            r16.c.b(r3);
                            if (r0 > 0) {
                            }
                            if (r4 == 2) {
                            }
                        }
                        r0 = com.samsung.android.sdk.accessory.SAMessage.ERROR_UNKNOWN;
                        r16.c.b(r3);
                        if (r0 > 0) {
                        }
                        if (r4 == 2) {
                        }
                    }
                } catch (java.io.IOException e) {
                    r0 = e;
                    r4 = -1;
                    android.util.Log.e(com.samsung.android.sdk.accessory.SAMessage.a, "Error in onRead! ".concat(java.lang.String.valueOf(r0)));
                    r16.c.b(r3);
                    r16.a(r12, r11, com.samsung.android.sdk.accessory.SAMessage.ERROR_UNKNOWN);
                    if (r4 == r15) {
                    }
                } catch (java.lang.Throwable th) {
                    r0 = th;
                    r4 = -1;
                    r16.c.b(r3);
                    if (r4 != r15) {
                    }
                    throw r0;
                }
            }
        }
        android.util.Log.e(r0, r1);
    }

    private static boolean a(SAPeerAgent sAPeerAgent) {
        int parseInt = Integer.parseInt(sAPeerAgent.getPeerId());
        int[] iArr = b;
        for (int i = 0; i < 5; i++) {
            if (parseInt == iArr[i]) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ void b(SAMessage sAMessage, Bundle bundle) {
        bundle.setClassLoader(SAPeerAgent.class.getClassLoader());
        SAPeerAgent sAPeerAgent = (SAPeerAgent) bundle.getParcelable("peerAgent");
        int i = bundle.getInt("transactionId");
        int i2 = bundle.getInt("errorcode");
        if (i2 == 1792) {
            sAMessage.onSent(sAPeerAgent, i);
        } else {
            sAMessage.onError(sAPeerAgent, i, i2);
        }
    }

    private void b(final String str) {
        this.e.post(new Runnable() { // from class: com.samsung.android.sdk.accessory.SAMessage.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    SAMessage.this.a(str);
                } catch (d e) {
                    SAMessage.a(SAMessage.this);
                    e.printStackTrace();
                }
            }
        });
    }

    static /* synthetic */ void c(SAMessage sAMessage, Bundle bundle) {
        Handler handler = sAMessage.e;
        if (handler != null) {
            handler.post(new a(sAMessage, bundle, false));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        try {
            String str = this.f;
            if (str != null) {
                this.c.d(str);
            }
        } catch (d e) {
            Log.e(a, "Failed to un-register Mex callback! " + e.getLocalizedMessage());
        }
    }

    final void a(Bundle bundle) {
        Handler handler = this.e;
        if (handler != null) {
            handler.post(new a(this, bundle, true));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str) throws d {
        String str2 = this.f;
        if (str2 != null && !str.equalsIgnoreCase(str2)) {
            this.c.d(this.f);
        }
        this.f = str;
        this.c.a(str, this.d);
    }

    protected abstract void onError(SAPeerAgent sAPeerAgent, int i, int i2);

    protected abstract void onReceive(SAPeerAgent sAPeerAgent, byte[] bArr);

    protected abstract void onSent(SAPeerAgent sAPeerAgent, int i);

    public int secureSend(SAPeerAgent sAPeerAgent, byte[] bArr) throws IOException {
        return a(sAPeerAgent, bArr, true, 1);
    }

    public int send(SAPeerAgent sAPeerAgent, byte[] bArr) throws IOException {
        return a(sAPeerAgent, bArr, false, 1);
    }
}
