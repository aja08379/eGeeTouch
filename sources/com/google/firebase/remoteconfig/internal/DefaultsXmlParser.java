package com.google.firebase.remoteconfig.internal;
/* loaded from: classes2.dex */
public class DefaultsXmlParser {
    private static final String XML_TAG_ENTRY = "entry";
    private static final String XML_TAG_KEY = "key";
    private static final String XML_TAG_VALUE = "value";

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0074, code lost:
        if (r10 == 1) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0076, code lost:
        android.util.Log.w(com.google.firebase.remoteconfig.FirebaseRemoteConfig.TAG, "Encountered an unexpected tag while parsing the defaults XML.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x007c, code lost:
        r5 = r9.getText();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Map<java.lang.String, java.lang.String> getDefaultsFromXml(android.content.Context r9, int r10) {
        android.content.res.Resources r9;
        java.util.HashMap r1 = new java.util.HashMap();
        try {
            r9 = r9.getResources();
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException r9) {
            android.util.Log.e(com.google.firebase.remoteconfig.FirebaseRemoteConfig.TAG, "Encountered an error while parsing the defaults XML file.", r9);
        }
        if (r9 == null) {
            android.util.Log.e(com.google.firebase.remoteconfig.FirebaseRemoteConfig.TAG, "Could not find the resources of the current context while trying to set defaults from an XML.");
            return r1;
        }
        android.content.res.XmlResourceParser r9 = r9.getXml(r10);
        java.lang.String r3 = null;
        java.lang.String r4 = null;
        java.lang.String r5 = null;
        for (int r10 = r9.getEventType(); r10 != 1; r10 = r9.next()) {
            if (r10 == 2) {
                r3 = r9.getName();
            } else if (r10 == 3) {
                if (r9.getName().equals(com.google.firebase.remoteconfig.internal.DefaultsXmlParser.XML_TAG_ENTRY)) {
                    if (r4 != null && r5 != null) {
                        r1.put(r4, r5);
                    } else {
                        android.util.Log.w(com.google.firebase.remoteconfig.FirebaseRemoteConfig.TAG, "An entry in the defaults XML has an invalid key and/or value tag.");
                    }
                    r4 = null;
                    r5 = null;
                }
                r3 = null;
            } else if (r10 == 4 && r3 != null) {
                char r10 = 65535;
                int r7 = r3.hashCode();
                if (r7 != 106079) {
                    if (r7 == 111972721 && r3.equals("value")) {
                        r10 = 1;
                    }
                } else if (r3.equals(com.google.firebase.remoteconfig.internal.DefaultsXmlParser.XML_TAG_KEY)) {
                    r10 = 0;
                }
                r4 = r9.getText();
            }
        }
        return r1;
    }
}
