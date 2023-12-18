package com.google.firebase.database.tubesock;
/* loaded from: classes2.dex */
public interface WebSocketEventHandler {
    void onClose();

    void onError(WebSocketException webSocketException);

    void onLogMessage(String str);

    void onMessage(WebSocketMessage webSocketMessage);

    void onOpen();
}
