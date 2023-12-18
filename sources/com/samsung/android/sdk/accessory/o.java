package com.samsung.android.sdk.accessory;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
/* loaded from: classes2.dex */
final class o {
    private static o a;
    private static byte[] b;
    private Context c;

    private o(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Invalid context:" + ((Object) null));
        }
        this.c = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized o a(Context context) {
        o oVar;
        synchronized (o.class) {
            if (a == null) {
                b = null;
                a = new o(context);
            }
            oVar = a;
        }
        return oVar;
    }

    private synchronized byte[] a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        StringBuffer stringBuffer;
        String trim;
        stringBuffer = new StringBuffer();
        int eventType = xmlPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 0) {
                Log.v("[SA_SDK]ServiceXmlReader", "Start document");
            } else {
                if (eventType == 2) {
                    stringBuffer.append(String.format("<%s ", xmlPullParser.getName().trim()));
                    int attributeCount = xmlPullParser.getAttributeCount();
                    if (attributeCount > 0) {
                        for (int i = 0; i < attributeCount; i++) {
                            stringBuffer.append(String.format("%s=\"%s\"", xmlPullParser.getAttributeName(i).trim(), xmlPullParser.getAttributeValue(i).trim()));
                        }
                    }
                    trim = ">";
                } else if (eventType == 3) {
                    trim = String.format("</%s>", xmlPullParser.getName());
                } else if (eventType == 4) {
                    trim = xmlPullParser.getText().trim();
                }
                stringBuffer.append(trim);
            }
            if (stringBuffer.length() >= 65529) {
                throw new RuntimeException("Accessory Service XML is too long! Services XML cannot be more than 64k in size");
            }
            eventType = xmlPullParser.next();
        }
        return stringBuffer.toString().getBytes(k.h());
    }

    private String b() {
        try {
            Bundle bundle = this.c.getApplicationContext().getPackageManager().getApplicationInfo(this.c.getApplicationContext().getPackageName(), 128).metaData;
            if (bundle == null) {
                Log.e("[SA_SDK]ServiceXmlReader", "No meta data present in the manifest");
                throw new RuntimeException("No meta data present in the manifest");
            }
            String string = bundle.getString("AccessoryServicesLocation", null);
            if (string != null) {
                Log.i("[SA_SDK]ServiceXmlReader", "Service description(s) file Location:".concat(String.valueOf(string)));
                return string;
            }
            Log.e("[SA_SDK]ServiceXmlReader", "No meta data found with key:AccessoryServicesLocation");
            throw new RuntimeException("No meta data found with key:AccessoryServicesLocation");
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("[SA_SDK]ServiceXmlReader", "Unable to fetch metadata from teh manifest" + e.getMessage());
            throw new RuntimeException("Unable to fetch metadata from teh manifest", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized byte[] a() {
        String b2 = b();
        if (b == null) {
            if (b2.startsWith("/res/")) {
                Log.d("[SA_SDK]ServiceXmlReader", "Fetching xml from /res/xml");
                try {
                    try {
                        XmlResourceParser xml = this.c.getResources().getXml(this.c.getResources().getIdentifier(b2.substring(b2.lastIndexOf(File.separator) + 1, b2.lastIndexOf(".")), "xml", this.c.getPackageName()));
                        if (xml == null) {
                            throw new RuntimeException("Unable to read the service XML file from:" + b2 + " resource parser is null");
                        }
                        b = a(xml);
                        if (xml != null) {
                            xml.close();
                        }
                    } catch (XmlPullParserException e) {
                        throw new RuntimeException("Parsing Accessory service configuration failed from:".concat(String.valueOf(b2)), e);
                    }
                } catch (Resources.NotFoundException e2) {
                    throw new RuntimeException("Accessory services configuration XML file not found at:".concat(String.valueOf(b2)), e2);
                } catch (IOException e3) {
                    throw new RuntimeException("Parsing Accessory service configuration failed from:".concat(String.valueOf(b2)), e3);
                }
            } else if (!b2.startsWith("/assets/")) {
                throw new RuntimeException("Accssory Service profile xml must be in /res or /assets directory.");
            } else {
                Log.d("[SA_SDK]ServiceXmlReader", "Fetching xml from /assets");
                try {
                    InputStream open = this.c.getAssets().open(b2.substring(8));
                    try {
                        XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
                        newInstance.setNamespaceAware(true);
                        XmlPullParser newPullParser = newInstance.newPullParser();
                        newPullParser.setInput(open, k.h());
                        b = a(newPullParser);
                    } catch (IOException e4) {
                        throw new RuntimeException("Parsing Accessory service configuration failed from:".concat(String.valueOf(b2)), e4);
                    } catch (XmlPullParserException e5) {
                        throw new RuntimeException("Parsing Accessory service configuration failed from:".concat(String.valueOf(b2)), e5);
                    }
                } catch (IOException e6) {
                    throw new RuntimeException("Unable to read the service XML file from:".concat(String.valueOf(b2)), e6);
                }
            }
            Context context = this.c;
            g.a(context, "SACP", context.getPackageName() + "#2.6.4#" + k.f() + "#" + Build.VERSION.SDK_INT);
        }
        return b;
    }
}
