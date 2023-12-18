package com.facebook.devicerequests.internal;

import android.graphics.Bitmap;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Build;
import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.SmartLoginOption;
import com.facebook.internal.Utility;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.EnumMap;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class DeviceRequestsHelper {
    static final String DEVICE_INFO_DEVICE = "device";
    static final String DEVICE_INFO_MODEL = "model";
    public static final String DEVICE_INFO_PARAM = "device_info";
    public static final String DEVICE_TARGET_USER_ID = "target_user_id";
    static final String SDK_FLAVOR = "android";
    static final String SDK_HEADER = "fbsdk";
    static final String SERVICE_TYPE = "_fb._tcp.";
    private static final String TAG = "com.facebook.devicerequests.internal.DeviceRequestsHelper";
    private static HashMap<String, NsdManager.RegistrationListener> deviceRequestsListeners = new HashMap<>();

    public static String getDeviceInfo() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(DEVICE_INFO_DEVICE, Build.DEVICE);
            jSONObject.put(DEVICE_INFO_MODEL, Build.MODEL);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public static boolean startAdvertisementService(String str) {
        if (isAvailable()) {
            return startAdvertisementServiceImpl(str);
        }
        return false;
    }

    public static boolean isAvailable() {
        return Build.VERSION.SDK_INT >= 16 && FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId()).getSmartLoginOptions().contains(SmartLoginOption.Enabled);
    }

    public static Bitmap generateQRCode(String str) {
        EnumMap enumMap = new EnumMap(EncodeHintType.class);
        enumMap.put((EnumMap) EncodeHintType.MARGIN, (EncodeHintType) 2);
        Bitmap bitmap = null;
        try {
            BitMatrix encode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 200, 200, enumMap);
            int height = encode.getHeight();
            int width = encode.getWidth();
            int[] iArr = new int[height * width];
            for (int i = 0; i < height; i++) {
                int i2 = i * width;
                for (int i3 = 0; i3 < width; i3++) {
                    iArr[i2 + i3] = encode.get(i3, i) ? -16777216 : -1;
                }
            }
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException unused) {
            return bitmap;
        }
    }

    public static void cleanUpAdvertisementService(String str) {
        cleanUpAdvertisementServiceImpl(str);
    }

    private static boolean startAdvertisementServiceImpl(final String str) {
        if (deviceRequestsListeners.containsKey(str)) {
            return true;
        }
        final String format = String.format("%s_%s_%s", SDK_HEADER, String.format("%s-%s", "android", FacebookSdk.getSdkVersion().replace('.', '|')), str);
        NsdServiceInfo nsdServiceInfo = new NsdServiceInfo();
        nsdServiceInfo.setServiceType(SERVICE_TYPE);
        nsdServiceInfo.setServiceName(format);
        nsdServiceInfo.setPort(80);
        NsdManager.RegistrationListener registrationListener = new NsdManager.RegistrationListener() { // from class: com.facebook.devicerequests.internal.DeviceRequestsHelper.1
            @Override // android.net.nsd.NsdManager.RegistrationListener
            public void onServiceUnregistered(NsdServiceInfo nsdServiceInfo2) {
            }

            @Override // android.net.nsd.NsdManager.RegistrationListener
            public void onUnregistrationFailed(NsdServiceInfo nsdServiceInfo2, int i) {
            }

            @Override // android.net.nsd.NsdManager.RegistrationListener
            public void onServiceRegistered(NsdServiceInfo nsdServiceInfo2) {
                if (format.equals(nsdServiceInfo2.getServiceName())) {
                    return;
                }
                DeviceRequestsHelper.cleanUpAdvertisementService(str);
            }

            @Override // android.net.nsd.NsdManager.RegistrationListener
            public void onRegistrationFailed(NsdServiceInfo nsdServiceInfo2, int i) {
                DeviceRequestsHelper.cleanUpAdvertisementService(str);
            }
        };
        deviceRequestsListeners.put(str, registrationListener);
        ((NsdManager) FacebookSdk.getApplicationContext().getSystemService("servicediscovery")).registerService(nsdServiceInfo, 1, registrationListener);
        return true;
    }

    private static void cleanUpAdvertisementServiceImpl(String str) {
        NsdManager.RegistrationListener registrationListener = deviceRequestsListeners.get(str);
        if (registrationListener != null) {
            try {
                ((NsdManager) FacebookSdk.getApplicationContext().getSystemService("servicediscovery")).unregisterService(registrationListener);
            } catch (IllegalArgumentException e) {
                Utility.logd(TAG, e);
            }
            deviceRequestsListeners.remove(str);
        }
    }
}
