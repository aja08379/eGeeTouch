package com.samsung.android.sdk.accessoryfiletransfer;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import com.samsung.accessory.safiletransfer.a.d;
import com.samsung.accessory.safiletransfer.a.e;
import com.samsung.accessory.safiletransfer.a.f;
import com.samsung.accessory.safiletransfer.a.g;
import com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer;
import org.json.JSONException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SAFileTransferCallbackReceiver extends ResultReceiver {
    private int a;
    private int[] b;
    private String c;
    private String d;
    private SAFileTransfer.c e;

    public SAFileTransferCallbackReceiver(Handler handler, SAFileTransfer.c cVar) {
        super(handler);
        this.e = cVar;
    }

    @Override // android.os.ResultReceiver
    protected void onReceiveResult(int i, Bundle bundle) {
        SAFileTransfer.c cVar;
        int i2;
        String str;
        String string = bundle.getString("CallBackJson");
        if (string == null) {
            return;
        }
        switch (i) {
            case 100:
                g gVar = new g();
                try {
                    gVar.a(string);
                    this.a = gVar.a();
                    this.e.a(this.a, (int) gVar.b());
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            case 101:
                f fVar = new f();
                try {
                    fVar.a(string);
                    Log.i("[SA_SDK]SAFileTransferCallbackReceiver", "Transfer Complete");
                    this.a = fVar.a();
                    this.c = fVar.b();
                    String c = fVar.c();
                    this.d = c;
                    if (c.length() == 0) {
                        cVar = this.e;
                        i2 = this.a;
                        str = this.c;
                    } else {
                        cVar = this.e;
                        i2 = this.a;
                        str = this.d;
                    }
                    cVar.a(i2, str, 0);
                    this.a = -1;
                    return;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    return;
                }
            case 102:
                Log.e("[SA_SDK]SAFileTransferCallbackReceiver", "FT Error");
                d dVar = new d();
                try {
                    dVar.a(string);
                    this.a = dVar.a();
                    this.e.a(this.a, null, dVar.b());
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
                this.a = -1;
                this.c = null;
                this.d = null;
                return;
            case 103:
                Log.e("[SA_SDK]SAFileTransferCallbackReceiver", "FT Error");
                e eVar = new e();
                try {
                    eVar.a(string);
                    this.b = eVar.a();
                    this.e.a(this.b, eVar.b());
                } catch (JSONException e4) {
                    e4.printStackTrace();
                }
                this.a = -1;
                this.c = null;
                this.d = null;
                return;
            default:
                Log.e("[SA_SDK]SAFileTransferCallbackReceiver", "Wrong resultCode");
                return;
        }
    }
}
