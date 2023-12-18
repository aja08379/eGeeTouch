package com.google.firebase.database.tubesock;

import com.google.firebase.database.tubesock.MessageBuilderFactory;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
/* loaded from: classes2.dex */
class WebSocketReceiver {
    private MessageBuilderFactory.Builder pendingBuilder;
    private WebSocket websocket;
    private DataInputStream input = null;
    private WebSocketEventHandler eventHandler = null;
    private byte[] inputHeader = new byte[112];
    private volatile boolean stop = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSocketReceiver(WebSocket webSocket) {
        this.websocket = null;
        this.websocket = webSocket;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setInput(DataInputStream dataInputStream) {
        this.input = dataInputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void run() {
        int read;
        byte[] bArr;
        boolean z;
        this.eventHandler = this.websocket.getEventHandler();
        while (!this.stop) {
            try {
                read = read(this.inputHeader, 0, 1) + 0;
                bArr = this.inputHeader;
                z = (bArr[0] & ByteCompanionObject.MIN_VALUE) != 0;
            } catch (WebSocketException e) {
                handleError(e);
            } catch (SocketTimeoutException unused) {
            } catch (IOException e2) {
                handleError(new WebSocketException("IO Error", e2));
            }
            if ((bArr[0] & 112) != 0) {
                throw new WebSocketException("Invalid frame received");
            }
            byte b = (byte) (bArr[0] & 15);
            int read2 = read + read(bArr, read, 1);
            byte[] bArr2 = this.inputHeader;
            byte b2 = bArr2[1];
            long j = 0;
            if (b2 < 126) {
                j = b2;
            } else if (b2 == 126) {
                read(bArr2, read2, 2);
                byte[] bArr3 = this.inputHeader;
                j = ((bArr3[2] & UByte.MAX_VALUE) << 8) | (bArr3[3] & UByte.MAX_VALUE);
            } else if (b2 == Byte.MAX_VALUE) {
                j = parseLong(this.inputHeader, (read2 + read(bArr2, read2, 8)) - 8);
            }
            int i = (int) j;
            byte[] bArr4 = new byte[i];
            read(bArr4, 0, i);
            if (b == 8) {
                this.websocket.onCloseOpReceived();
            } else if (b != 10) {
                if (b != 1 && b != 2 && b != 9 && b != 0) {
                    throw new WebSocketException("Unsupported opcode: " + ((int) b));
                }
                appendBytes(z, b, bArr4);
            }
        }
    }

    private void appendBytes(boolean z, byte b, byte[] bArr) {
        if (b == 9) {
            if (z) {
                handlePing(bArr);
                return;
            }
            throw new WebSocketException("PING must not fragment across frames");
        }
        MessageBuilderFactory.Builder builder = this.pendingBuilder;
        if (builder != null && b != 0) {
            throw new WebSocketException("Failed to continue outstanding frame");
        }
        if (builder == null && b == 0) {
            throw new WebSocketException("Received continuing frame, but there's nothing to continue");
        }
        if (builder == null) {
            this.pendingBuilder = MessageBuilderFactory.builder(b);
        }
        if (!this.pendingBuilder.appendBytes(bArr)) {
            throw new WebSocketException("Failed to decode frame");
        }
        if (z) {
            WebSocketMessage message = this.pendingBuilder.toMessage();
            this.pendingBuilder = null;
            if (message == null) {
                throw new WebSocketException("Failed to decode whole message");
            }
            this.eventHandler.onMessage(message);
        }
    }

    private void handlePing(byte[] bArr) {
        if (bArr.length <= 125) {
            this.websocket.pong(bArr);
            return;
        }
        throw new WebSocketException("PING frame too long");
    }

    private long parseLong(byte[] bArr, int i) {
        return (bArr[i + 0] << 56) + ((bArr[i + 1] & UByte.MAX_VALUE) << 48) + ((bArr[i + 2] & UByte.MAX_VALUE) << 40) + ((bArr[i + 3] & UByte.MAX_VALUE) << 32) + ((bArr[i + 4] & UByte.MAX_VALUE) << 24) + ((bArr[i + 5] & UByte.MAX_VALUE) << 16) + ((bArr[i + 6] & UByte.MAX_VALUE) << 8) + ((bArr[i + 7] & UByte.MAX_VALUE) << 0);
    }

    private int read(byte[] bArr, int i, int i2) throws IOException {
        this.input.readFully(bArr, i, i2);
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopit() {
        this.stop = true;
    }

    boolean isRunning() {
        return !this.stop;
    }

    private void handleError(WebSocketException webSocketException) {
        stopit();
        this.websocket.handleReceiverError(webSocketException);
    }
}
