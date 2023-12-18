package com.google.firebase.database.tubesock;

import android.util.Base64;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes2.dex */
class WebSocketHandshake {
    private static final String WEBSOCKET_VERSION = "13";
    private Map<String, String> extraHeaders;
    private String nonce;
    private String protocol;
    private URI url;

    public WebSocketHandshake(URI uri, String str, Map<String, String> map) {
        this.url = null;
        this.protocol = null;
        this.nonce = null;
        this.extraHeaders = null;
        this.url = uri;
        this.protocol = str;
        this.extraHeaders = map;
        this.nonce = createNonce();
    }

    public byte[] getHandshake() {
        String query;
        String str = this.url.getPath() + (this.url.getQuery() == null ? "" : "?" + query);
        String host = this.url.getHost();
        if (this.url.getPort() != -1) {
            host = host + ":" + this.url.getPort();
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("Host", host);
        linkedHashMap.put("Upgrade", "websocket");
        linkedHashMap.put("Connection", "Upgrade");
        linkedHashMap.put("Sec-WebSocket-Version", WEBSOCKET_VERSION);
        linkedHashMap.put("Sec-WebSocket-Key", this.nonce);
        String str2 = this.protocol;
        if (str2 != null) {
            linkedHashMap.put("Sec-WebSocket-Protocol", str2);
        }
        Map<String, String> map = this.extraHeaders;
        if (map != null) {
            for (String str3 : map.keySet()) {
                if (!linkedHashMap.containsKey(str3)) {
                    linkedHashMap.put(str3, this.extraHeaders.get(str3));
                }
            }
        }
        byte[] bytes = ((("GET " + str + " HTTP/1.1\r\n") + generateHeader(linkedHashMap)) + "\r\n").getBytes(Charset.defaultCharset());
        byte[] bArr = new byte[bytes.length];
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        return bArr;
    }

    private String generateHeader(LinkedHashMap<String, String> linkedHashMap) {
        String str = new String();
        for (String str2 : linkedHashMap.keySet()) {
            str = str + str2 + ": " + linkedHashMap.get(str2) + "\r\n";
        }
        return str;
    }

    private String createNonce() {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) rand(0, 255);
        }
        return Base64.encodeToString(bArr, 2);
    }

    public void verifyServerStatusLine(String str) {
        int parseInt = Integer.parseInt(str.substring(9, 12));
        if (parseInt == 407) {
            throw new WebSocketException("connection failed: proxy authentication not supported");
        }
        if (parseInt == 404) {
            throw new WebSocketException("connection failed: 404 not found");
        }
        if (parseInt != 101) {
            throw new WebSocketException("connection failed: unknown status code " + parseInt);
        }
    }

    public void verifyServerHandshakeHeaders(HashMap<String, String> hashMap) {
        if (!"websocket".equals(hashMap.get("upgrade"))) {
            throw new WebSocketException("connection failed: missing header field in server handshake: Upgrade");
        }
        if (!"upgrade".equals(hashMap.get("connection"))) {
            throw new WebSocketException("connection failed: missing header field in server handshake: Connection");
        }
    }

    private int rand(int i, int i2) {
        return (int) ((Math.random() * i2) + i);
    }
}
