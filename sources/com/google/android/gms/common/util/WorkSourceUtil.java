package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.WorkSource;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes.dex */
public class WorkSourceUtil {
    private static final int zza = Process.myUid();
    private static final Method zzb;
    private static final Method zzc;
    private static final Method zzd;
    private static final Method zze;
    private static final Method zzf;
    private static final Method zzg;
    private static final Method zzh;
    private static final Method zzi;

    /* JADX WARN: Can't wrap try/catch for region: R(25:1|2|3|4|(21:49|50|7|8|9|10|11|12|13|(12:41|42|16|(9:36|37|19|(6:31|32|22|(2:27|28)|24|25)|21|22|(0)|24|25)|18|19|(0)|21|22|(0)|24|25)|15|16|(0)|18|19|(0)|21|22|(0)|24|25)|6|7|8|9|10|11|12|13|(0)|15|16|(0)|18|19|(0)|21|22|(0)|24|25) */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0041, code lost:
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0053, code lost:
        r0 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0090 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        java.lang.reflect.Method r4;
        java.lang.reflect.Method r0;
        java.lang.reflect.Method r0;
        java.lang.reflect.Method r0;
        java.lang.reflect.Method r0;
        java.lang.reflect.Method r3 = null;
        try {
            r4 = android.os.WorkSource.class.getMethod("add", java.lang.Integer.TYPE);
        } catch (java.lang.Exception unused) {
            r4 = null;
        }
        com.google.android.gms.common.util.WorkSourceUtil.zzb = r4;
        if (com.google.android.gms.common.util.PlatformVersion.isAtLeastJellyBeanMR2()) {
            try {
                r0 = android.os.WorkSource.class.getMethod("add", java.lang.Integer.TYPE, java.lang.String.class);
            } catch (java.lang.Exception unused) {
            }
            com.google.android.gms.common.util.WorkSourceUtil.zzc = r0;
            java.lang.reflect.Method r0 = android.os.WorkSource.class.getMethod("size", new java.lang.Class[0]);
            com.google.android.gms.common.util.WorkSourceUtil.zzd = r0;
            java.lang.reflect.Method r0 = android.os.WorkSource.class.getMethod("get", java.lang.Integer.TYPE);
            com.google.android.gms.common.util.WorkSourceUtil.zze = r0;
            if (com.google.android.gms.common.util.PlatformVersion.isAtLeastJellyBeanMR2()) {
                try {
                    r0 = android.os.WorkSource.class.getMethod("getName", java.lang.Integer.TYPE);
                } catch (java.lang.Exception unused) {
                }
                com.google.android.gms.common.util.WorkSourceUtil.zzf = r0;
                if (com.google.android.gms.common.util.PlatformVersion.isAtLeastP()) {
                    try {
                        r0 = android.os.WorkSource.class.getMethod("createWorkChain", new java.lang.Class[0]);
                    } catch (java.lang.Exception r0) {
                        android.util.Log.w("WorkSourceUtil", "Missing WorkChain API createWorkChain", r0);
                    }
                    com.google.android.gms.common.util.WorkSourceUtil.zzg = r0;
                    if (com.google.android.gms.common.util.PlatformVersion.isAtLeastP()) {
                        try {
                            r0 = java.lang.Class.forName("android.os.WorkSource$WorkChain").getMethod("addNode", java.lang.Integer.TYPE, java.lang.String.class);
                        } catch (java.lang.Exception r0) {
                            android.util.Log.w("WorkSourceUtil", "Missing WorkChain class", r0);
                        }
                        com.google.android.gms.common.util.WorkSourceUtil.zzh = r0;
                        if (com.google.android.gms.common.util.PlatformVersion.isAtLeastP()) {
                            try {
                                r3 = android.os.WorkSource.class.getMethod("isEmpty", new java.lang.Class[0]);
                                r3.setAccessible(true);
                            } catch (java.lang.Exception unused) {
                            }
                        }
                        com.google.android.gms.common.util.WorkSourceUtil.zzi = r3;
                    }
                    r0 = null;
                    com.google.android.gms.common.util.WorkSourceUtil.zzh = r0;
                    if (com.google.android.gms.common.util.PlatformVersion.isAtLeastP()) {
                    }
                    com.google.android.gms.common.util.WorkSourceUtil.zzi = r3;
                }
                r0 = null;
                com.google.android.gms.common.util.WorkSourceUtil.zzg = r0;
                if (com.google.android.gms.common.util.PlatformVersion.isAtLeastP()) {
                }
                r0 = null;
                com.google.android.gms.common.util.WorkSourceUtil.zzh = r0;
                if (com.google.android.gms.common.util.PlatformVersion.isAtLeastP()) {
                }
                com.google.android.gms.common.util.WorkSourceUtil.zzi = r3;
            }
            r0 = null;
            com.google.android.gms.common.util.WorkSourceUtil.zzf = r0;
            if (com.google.android.gms.common.util.PlatformVersion.isAtLeastP()) {
            }
            r0 = null;
            com.google.android.gms.common.util.WorkSourceUtil.zzg = r0;
            if (com.google.android.gms.common.util.PlatformVersion.isAtLeastP()) {
            }
            r0 = null;
            com.google.android.gms.common.util.WorkSourceUtil.zzh = r0;
            if (com.google.android.gms.common.util.PlatformVersion.isAtLeastP()) {
            }
            com.google.android.gms.common.util.WorkSourceUtil.zzi = r3;
        }
        r0 = null;
        com.google.android.gms.common.util.WorkSourceUtil.zzc = r0;
        java.lang.reflect.Method r0 = android.os.WorkSource.class.getMethod("size", new java.lang.Class[0]);
        com.google.android.gms.common.util.WorkSourceUtil.zzd = r0;
        java.lang.reflect.Method r0 = android.os.WorkSource.class.getMethod("get", java.lang.Integer.TYPE);
        com.google.android.gms.common.util.WorkSourceUtil.zze = r0;
        if (com.google.android.gms.common.util.PlatformVersion.isAtLeastJellyBeanMR2()) {
        }
        r0 = null;
        com.google.android.gms.common.util.WorkSourceUtil.zzf = r0;
        if (com.google.android.gms.common.util.PlatformVersion.isAtLeastP()) {
        }
        r0 = null;
        com.google.android.gms.common.util.WorkSourceUtil.zzg = r0;
        if (com.google.android.gms.common.util.PlatformVersion.isAtLeastP()) {
        }
        r0 = null;
        com.google.android.gms.common.util.WorkSourceUtil.zzh = r0;
        if (com.google.android.gms.common.util.PlatformVersion.isAtLeastP()) {
        }
        com.google.android.gms.common.util.WorkSourceUtil.zzi = r3;
    }

    private WorkSourceUtil() {
    }

    public static void add(WorkSource workSource, int i, String str) {
        Method method = zzc;
        if (method == null) {
            Method method2 = zzb;
            if (method2 != null) {
                try {
                    method2.invoke(workSource, Integer.valueOf(i));
                    return;
                } catch (Exception e) {
                    Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
                    return;
                }
            }
            return;
        }
        if (str == null) {
            str = "";
        }
        try {
            method.invoke(workSource, Integer.valueOf(i), str);
        } catch (Exception e2) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
        }
    }

    public static WorkSource fromPackage(Context context, String str) {
        if (context != null && context.getPackageManager() != null && str != null) {
            try {
                ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
                if (applicationInfo == null) {
                    Log.e("WorkSourceUtil", "Could not get applicationInfo from package: ".concat(str));
                    return null;
                }
                int i = applicationInfo.uid;
                WorkSource workSource = new WorkSource();
                add(workSource, i, str);
                return workSource;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("WorkSourceUtil", "Could not find package: ".concat(str));
            }
        }
        return null;
    }

    public static WorkSource fromPackageAndModuleExperimentalPi(Context context, String str, String str2) {
        Method method;
        if (context == null || context.getPackageManager() == null || str2 == null || str == null) {
            Log.w("WorkSourceUtil", "Unexpected null arguments");
            return null;
        }
        int i = -1;
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
            if (applicationInfo == null) {
                Log.e("WorkSourceUtil", "Could not get applicationInfo from package: ".concat(str));
            } else {
                i = applicationInfo.uid;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("WorkSourceUtil", "Could not find package: ".concat(str));
        }
        if (i < 0) {
            return null;
        }
        WorkSource workSource = new WorkSource();
        Method method2 = zzg;
        if (method2 == null || (method = zzh) == null) {
            add(workSource, i, str);
        } else {
            try {
                Object invoke = method2.invoke(workSource, new Object[0]);
                int i2 = zza;
                if (i != i2) {
                    method.invoke(invoke, Integer.valueOf(i), str);
                }
                method.invoke(invoke, Integer.valueOf(i2), str2);
            } catch (Exception e) {
                Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", e);
            }
        }
        return workSource;
    }

    public static int get(WorkSource workSource, int i) {
        Method method = zze;
        if (method != null) {
            try {
                Object invoke = method.invoke(workSource, Integer.valueOf(i));
                Preconditions.checkNotNull(invoke);
                return ((Integer) invoke).intValue();
            } catch (Exception e) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        }
        return 0;
    }

    public static String getName(WorkSource workSource, int i) {
        Method method = zzf;
        if (method != null) {
            try {
                return (String) method.invoke(workSource, Integer.valueOf(i));
            } catch (Exception e) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
                return null;
            }
        }
        return null;
    }

    public static List<String> getNames(WorkSource workSource) {
        ArrayList arrayList = new ArrayList();
        int size = workSource == null ? 0 : size(workSource);
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                String name = getName(workSource, i);
                if (!Strings.isEmptyOrWhitespace(name)) {
                    Preconditions.checkNotNull(name);
                    arrayList.add(name);
                }
            }
        }
        return arrayList;
    }

    public static boolean hasWorkSourcePermission(Context context) {
        return (context == null || context.getPackageManager() == null || Wrappers.packageManager(context).checkPermission("android.permission.UPDATE_DEVICE_STATS", context.getPackageName()) != 0) ? false : true;
    }

    public static boolean isEmpty(WorkSource workSource) {
        Method method = zzi;
        if (method != null) {
            try {
                Object invoke = method.invoke(workSource, new Object[0]);
                Preconditions.checkNotNull(invoke);
                return ((Boolean) invoke).booleanValue();
            } catch (Exception e) {
                Log.e("WorkSourceUtil", "Unable to check WorkSource emptiness", e);
            }
        }
        return size(workSource) == 0;
    }

    public static int size(WorkSource workSource) {
        Method method = zzd;
        if (method != null) {
            try {
                Object invoke = method.invoke(workSource, new Object[0]);
                Preconditions.checkNotNull(invoke);
                return ((Integer) invoke).intValue();
            } catch (Exception e) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        }
        return 0;
    }
}
