package com.samsung.android.sdk.accessory;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
/* loaded from: classes2.dex */
public class c {
    private static final String a = "[SA_SDK]" + c.class.getSimpleName();
    private static final String[] b = {"ANY", "ONE_ACCESSORY", "ONE_PEERAGENT"};
    private static c c;
    private final Context d;
    private String e = null;
    private HashMap<String, n> f;

    private c(Context context) {
        this.d = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized c a(Context context) {
        synchronized (c.class) {
            c cVar = c;
            if (cVar == null) {
                c cVar2 = new c(context);
                c = cVar2;
                return cVar2;
            }
            return cVar;
        }
    }

    public static String a(String str, String str2) {
        if (!"user".equals(Build.TYPE) || str == null || str2 == null || !str.contains(str2)) {
            return str;
        }
        int indexOf = str.indexOf(str2);
        return str.substring(0, indexOf) + "##:##:##:##:" + str.substring(indexOf + 12);
    }

    private synchronized boolean a() {
        int eventType;
        String attributeValue;
        String str;
        ArrayList arrayList = new ArrayList();
        Log.i(a, "Parse Accssory Service profile xml file");
        byte[] a2 = o.a(this.d).a();
        String str2 = new String(a2, 0, a2.length);
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            XmlPullParser newPullParser = newInstance.newPullParser();
            if (newPullParser != null) {
                newPullParser.setInput(new StringReader(str2));
            }
            if (newPullParser != null) {
                try {
                    eventType = newPullParser.getEventType();
                } catch (XmlPullParserException unused) {
                    throw new RuntimeException("Wrong Schema of xml file. Please check");
                }
            } else {
                eventType = 0;
            }
            String str3 = null;
            String str4 = null;
            String str5 = null;
            while (eventType != 1) {
                if (eventType == 0) {
                    Log.v(a, "Start document");
                } else if (eventType == 2 && newPullParser != null) {
                    String name = newPullParser.getName();
                    if (name.equals("application")) {
                        String attributeValue2 = newPullParser.getAttributeValue(null, "name");
                        this.e = attributeValue2;
                        b("application", attributeValue2);
                    } else if (name.equals("serviceProfile")) {
                        str5 = newPullParser.getAttributeValue(null, "serviceImpl");
                        b("serviceImpl", str5);
                        str4 = newPullParser.getAttributeValue(null, "name");
                        b("name", str4);
                        b("role", newPullParser.getAttributeValue(null, "role"));
                        str3 = newPullParser.getAttributeValue(null, "id");
                        b("id", str3);
                        b(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, newPullParser.getAttributeValue(null, ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION));
                        String attributeValue3 = newPullParser.getAttributeValue(null, "serviceLimit");
                        if (attributeValue3 != null) {
                            String[] strArr = b;
                            if (!attributeValue3.equalsIgnoreCase(strArr[0]) && !attributeValue3.equalsIgnoreCase(strArr[1])) {
                                attributeValue3.equalsIgnoreCase(strArr[2]);
                            }
                        }
                        String attributeValue4 = newPullParser.getAttributeValue(null, "serviceTimeout");
                        if (attributeValue4 != null) {
                            try {
                                Integer.parseInt(attributeValue4);
                            } catch (NumberFormatException unused2) {
                            }
                        }
                    } else {
                        if (name.equals(NotificationCompat.CATEGORY_TRANSPORT)) {
                            attributeValue = newPullParser.getAttributeValue(null, "type");
                            b("Transport", attributeValue);
                            if (!attributeValue.equalsIgnoreCase("TRANSPORT_BT") && !attributeValue.equalsIgnoreCase("TRANSPORT_WIFI") && !attributeValue.equalsIgnoreCase("TRANSPORT_BLE")) {
                                str = "TRANSPORT_USB";
                            }
                        } else if (name.equals("serviceChannel")) {
                            String attributeValue5 = newPullParser.getAttributeValue(null, "id");
                            b("serviceChannel/id", attributeValue5);
                            String attributeValue6 = newPullParser.getAttributeValue(null, "dataRate");
                            if (attributeValue6 == null) {
                                Log.i(a, "Parsing new attribute failed.Trying to access old attribute");
                                attributeValue6 = newPullParser.getAttributeValue(null, "qosDataRate");
                            }
                            b("serviceChannel/qoSDataRate", attributeValue6);
                            String attributeValue7 = newPullParser.getAttributeValue(null, "priority");
                            if (attributeValue7 == null) {
                                attributeValue7 = newPullParser.getAttributeValue(null, "qosPriority");
                            }
                            b("serviceChannel/qoSPriority", attributeValue7);
                            String attributeValue8 = newPullParser.getAttributeValue(null, "reliability");
                            if (attributeValue8 == null) {
                                attributeValue8 = newPullParser.getAttributeValue(null, "qosType");
                            }
                            b("serviceChannel/qoSType", attributeValue8);
                            if (!attributeValue6.equalsIgnoreCase("Low")) {
                                attributeValue6.equalsIgnoreCase("High");
                            }
                            if (!attributeValue7.equalsIgnoreCase("Low") && !attributeValue7.equalsIgnoreCase("Medium")) {
                                attributeValue7.equalsIgnoreCase("High");
                            }
                            attributeValue8.equalsIgnoreCase("enable");
                            arrayList.add(new l(Integer.parseInt(attributeValue5)));
                        } else if (name.equals("feature")) {
                            attributeValue = newPullParser.getAttributeValue(null, "type");
                            b("Feature", attributeValue);
                            str = ShareConstants.WEB_DIALOG_PARAM_MESSAGE;
                        }
                        attributeValue.equalsIgnoreCase(str);
                    }
                } else if (eventType != 3 || newPullParser == null) {
                    if (eventType == 4 && newPullParser != null) {
                        newPullParser.getText();
                    }
                } else if (newPullParser.getName().equals("serviceProfile")) {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.addAll(arrayList);
                    n nVar = new n(str3, str4, str5, arrayList2);
                    if (this.f == null) {
                        this.f = new HashMap<>();
                    }
                    this.f.put(str5, nVar);
                    arrayList.clear();
                    str3 = null;
                    str4 = null;
                    str5 = null;
                }
                if (newPullParser != null) {
                    try {
                        try {
                            eventType = newPullParser.next();
                        } catch (XmlPullParserException unused3) {
                            throw new RuntimeException("Unable to parse the accessory services configuration file");
                        }
                    } catch (Exception unused4) {
                        throw new RuntimeException("Unable to parse the accessory services configuration file");
                    }
                }
            }
            Log.i(a, "End document");
            if (this.f == null) {
                throw new RuntimeException("Unable to parse the accessory services configuration file");
            }
        } catch (XmlPullParserException unused5) {
            throw new RuntimeException("XmlPullParserFactory Exception for Accssory Service profile XML file");
        }
        return true;
    }

    private static void b(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            throw new RuntimeException("Unable to parse the accessory services configuration file :".concat(String.valueOf(str)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized n a(String str) {
        if (this.f == null) {
            a();
        }
        if (this.f.get(str) != null) {
            return this.f.get(str);
        }
        Log.e(a, "fetchServicesDescription: Class not found in registered list".concat(String.valueOf(str)));
        return null;
    }
}
