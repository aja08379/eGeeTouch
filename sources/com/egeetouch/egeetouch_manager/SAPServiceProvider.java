package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.firebase.database.FirebaseDatabase;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.accessory.SA;
import com.samsung.android.sdk.accessory.SAAgentV2;
import com.samsung.android.sdk.accessory.SAAuthenticationToken;
import com.samsung.android.sdk.accessory.SAPeerAgent;
import com.samsung.android.sdk.accessory.SASocket;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;
import javax.security.cert.X509Certificate;
/* loaded from: classes.dex */
public class SAPServiceProvider extends SAAgentV2 {
    private static final Class<ServiceConnection> SASOCKET_CLASS = ServiceConnection.class;
    private static final String TAG = "AccessoryProvider(P)";
    String auditKey;
    FirebaseDatabase database;
    String listKey;
    private ServiceConnection mConnectionHandler;
    private Context mContext;
    Handler mHandler;
    boolean sending;
    SharedPreferences sharedPreferences;

    /* JADX INFO: Access modifiers changed from: private */
    public void addMessage(String str, String str2) {
    }

    public SAPServiceProvider(Context context) {
        super(TAG, context, SASOCKET_CLASS);
        this.mConnectionHandler = null;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.sending = false;
        this.auditKey = "WATCH_AUDIT_TRAIL";
        this.listKey = "WATCH_LIST";
        this.mContext = context;
        this.sharedPreferences = context.getSharedPreferences("WATCH_AUDIT", 0);
        try {
            new SA().initialize(this.mContext);
        } catch (SsdkUnsupportedException e) {
            if (processUnsupportedException(e)) {
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            releaseAgent();
        }
    }

    @Override // com.samsung.android.sdk.accessory.SAAgentV2
    protected void onFindPeerAgentsResponse(SAPeerAgent[] sAPeerAgentArr, int i) {
        Log.d(TAG, "onFindPeerAgentResponse : result =" + i);
    }

    @Override // com.samsung.android.sdk.accessory.SAAgentV2
    protected void onServiceConnectionRequested(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent != null) {
            addMessage("Received: ", "ConnectionAcceptedMsg1");
            acceptServiceConnectionRequest(sAPeerAgent);
        }
    }

    @Override // com.samsung.android.sdk.accessory.SAAgentV2
    protected void onServiceConnectionResponse(SAPeerAgent sAPeerAgent, SASocket sASocket, int i) {
        if (i == 0) {
            if (sASocket != null) {
                this.mConnectionHandler = (ServiceConnection) sASocket;
            }
        } else if (i == 1029) {
            Log.e(TAG, "onServiceConnectionResponse, CONNECTION_ALREADY_EXIST");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.accessory.SAAgentV2
    public void onError(SAPeerAgent sAPeerAgent, String str, int i) {
        super.onError(sAPeerAgent, str, i);
    }

    @Override // com.samsung.android.sdk.accessory.SAAgentV2
    protected void onAuthenticationResponse(SAPeerAgent sAPeerAgent, SAAuthenticationToken sAAuthenticationToken, int i) {
        if (sAAuthenticationToken.getAuthenticationType() == 1548) {
            Context applicationContext = getApplicationContext();
            this.mContext = applicationContext;
            byte[] applicationCertificate = getApplicationCertificate(applicationContext);
            if (sAAuthenticationToken.getKey() != null) {
                boolean z = false;
                if (sAAuthenticationToken.getKey().length == applicationCertificate.length) {
                    boolean z2 = true;
                    for (int i2 = 0; i2 < sAAuthenticationToken.getKey().length; i2++) {
                        if (sAAuthenticationToken.getKey()[i2] != applicationCertificate[i2]) {
                            z2 = false;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    Log.d(TAG, "onAuthenticationResponse : authentication is matched");
                    acceptServiceConnectionRequest(sAPeerAgent);
                    addMessage("Received: ", "Aunthenticated");
                }
            }
        } else if (sAAuthenticationToken.getAuthenticationType() == 1547) {
            Log.e(TAG, "onAuthenticationResponse : CERT_TYPE(NONE)");
        }
    }

    public void sendData(final String str) {
        if (this.mConnectionHandler != null) {
            new Thread(new Runnable() { // from class: com.egeetouch.egeetouch_manager.SAPServiceProvider.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        SAPServiceProvider.this.mConnectionHandler.send(SAPServiceProvider.this.getServiceChannelId(0), str.getBytes());
                        SAPServiceProvider.this.addMessage("Sent: ", str);
                    } catch (IOException e) {
                        e.printStackTrace();
                        SAPServiceProvider.this.addMessage("Exception: ", e.getMessage());
                    }
                }
            }).start();
        }
    }

    private static byte[] getApplicationCertificate(Context context) {
        Signature[] signatureArr;
        if (context == null) {
            return null;
        }
        String packageName = context.getPackageName();
        if (context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 64);
                if (packageInfo != null && (signatureArr = packageInfo.signatures) != null) {
                    CertificateFactory.getInstance("X.509");
                    return X509Certificate.getInstance(new ByteArrayInputStream(signatureArr[0].toByteArray())).getPublicKey().getEncoded();
                }
                return null;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (CertificateException e2) {
                e2.printStackTrace();
                return null;
            } catch (javax.security.cert.CertificateException e3) {
                e3.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private boolean processUnsupportedException(SsdkUnsupportedException ssdkUnsupportedException) {
        ssdkUnsupportedException.printStackTrace();
        int type = ssdkUnsupportedException.getType();
        if (type == 0 || type == 1) {
            releaseAgent();
        } else if (type == 2) {
            Log.e(TAG, "You need to install Samsung Accessory SDK to use this application.");
        } else if (type == 3) {
            Log.e(TAG, "You need to update Samsung Accessory SDK to use this application.");
        } else if (type == 4) {
            Log.e(TAG, "We recommend that you update your Samsung Accessory SDK before using this application.");
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> watchData() {
        ArrayList arrayList = new ArrayList();
        try {
            List<String> list = MainActivity.list;
            for (String str : (String[]) list.toArray(new String[list.size()])) {
                Cursor rawQuery = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(str), null);
                if (rawQuery.moveToNext() && rawQuery.getString(10).length() <= 0) {
                    arrayList.add(rawQuery.getString(0));
                    arrayList.add(rawQuery.getString(1));
                    arrayList.add(rawQuery.getString(2));
                    arrayList.add(rawQuery.getString(3));
                    arrayList.add(rawQuery.getString(5));
                }
                rawQuery.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /* loaded from: classes.dex */
    public class ServiceConnection extends SASocket {
        @Override // com.samsung.android.sdk.accessory.SASocket
        public void onError(int i, String str, int i2) {
        }

        public ServiceConnection() {
            super(ServiceConnection.class.getName());
        }

        @Override // com.samsung.android.sdk.accessory.SASocket
        public void onReceive(int i, byte[] bArr) {
            SAPServiceProvider sAPServiceProvider = SAPServiceProvider.this;
            int i2 = 0;
            sAPServiceProvider.sharedPreferences = sAPServiceProvider.getApplicationContext().getSharedPreferences("WATCH_AUDIT", 0);
            String str = new String(bArr);
            if (SAPServiceProvider.this.mConnectionHandler == null) {
                return;
            }
            String str2 = "";
            if (str.equals("[IMPORT]")) {
                if (MainActivity.list.size() <= 0) {
                    return;
                }
                List watchData = SAPServiceProvider.this.watchData();
                String[] strArr = (String[]) watchData.toArray(new String[watchData.size()]);
                String str3 = "";
                while (i2 < strArr.length) {
                    str3 = str3.concat(strArr[i2].replaceAll("`", ""));
                    if (i2 < strArr.length - 1) {
                        str3 = str3.concat("`");
                    }
                    i2++;
                }
                SAPServiceProvider.this.sendData(str3);
            } else if (str.contains("[ONLINE]")) {
                int i3 = -1;
                String[] split = str.replace("[ONLINE]", "").split("`");
                String[] split2 = SAPServiceProvider.this.sharedPreferences.getString(SAPServiceProvider.this.listKey, "").split("`");
                int length = split2.length / 3;
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                for (int i4 = 0; i4 < length; i4++) {
                    int i5 = i4 * 3;
                    arrayList.add(split2[i5]);
                    arrayList2.add(split2[i5 + 1]);
                    arrayList3.add(split2[i5 + 2]);
                }
                for (int i6 = 0; i6 < arrayList2.size(); i6++) {
                    if (((String) arrayList2.get(i6)).equals(split[1])) {
                        i3 = i6;
                    }
                }
                if (i3 >= 0) {
                    arrayList3.set(i3, String.valueOf(System.currentTimeMillis()));
                } else {
                    arrayList.add(split[0]);
                    arrayList2.add(split[1]);
                    arrayList3.add(String.valueOf(System.currentTimeMillis()));
                }
                while (i2 < arrayList.size()) {
                    String str4 = ((((str2 + ((String) arrayList.get(i2))) + "`") + ((String) arrayList2.get(i2))) + "`") + ((String) arrayList3.get(i2));
                    if (i2 != arrayList.size() - 1) {
                        str4 = str4 + "`";
                    }
                    str2 = str4;
                    i2++;
                }
                SharedPreferences.Editor edit = SAPServiceProvider.this.sharedPreferences.edit();
                edit.putString(SAPServiceProvider.this.listKey, str2);
                edit.apply();
            } else {
                String string = SAPServiceProvider.this.sharedPreferences.getString(SAPServiceProvider.this.auditKey, "");
                SharedPreferences.Editor edit2 = SAPServiceProvider.this.sharedPreferences.edit();
                if (string.length() > 0) {
                    edit2.putString(SAPServiceProvider.this.auditKey, string + "`" + str);
                } else {
                    edit2.putString(SAPServiceProvider.this.auditKey, str);
                }
                edit2.apply();
                SAPServiceProvider.this.sendData("AUDIT_RECEIVED");
            }
        }

        @Override // com.samsung.android.sdk.accessory.SASocket
        protected void onServiceConnectionLost(int i) {
            SAPServiceProvider.this.mConnectionHandler = null;
            SAPServiceProvider.this.addMessage("Received: ", "ConnectionTerminatedMsg");
        }
    }
}
