package com.samsung.accessory.safiletransfer.a;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public final class c {
    private int a;
    private JSONObject b;

    public c() {
    }

    public c(int i, JSONObject jSONObject) {
        this.a = i;
        this.b = jSONObject;
    }

    public final JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("OpCode", this.a);
        jSONObject.put("Parameters", this.b);
        return jSONObject;
    }
}
