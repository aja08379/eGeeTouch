package com.google.firebase.storage.network;

import com.android.volley.toolbox.HttpClientStack;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class UpdateMetadataNetworkRequest extends NetworkRequest {
    private final JSONObject metadata;

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return "PUT";
    }

    public UpdateMetadataNetworkRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp, JSONObject jSONObject) {
        super(storageReferenceUri, firebaseApp);
        this.metadata = jSONObject;
        setCustomHeader("X-HTTP-Method-Override", HttpClientStack.HttpPatch.METHOD_NAME);
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected JSONObject getOutputJSON() {
        return this.metadata;
    }
}
