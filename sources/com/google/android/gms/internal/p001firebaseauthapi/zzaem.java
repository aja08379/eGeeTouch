package com.google.android.gms.internal.p001firebaseauthapi;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kotlin.text.Typography;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaem  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaem {
    private static final char[] zza;

    static {
        char[] cArr = new char[80];
        zza = cArr;
        Arrays.fill(cArr, ' ');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(zzaek zzaekVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzaekVar, sb, 0);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                zzb(sb, i, str, obj2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                zzb(sb, i, str, entry);
            }
        } else {
            sb.append('\n');
            zzc(i, sb);
            if (!str.isEmpty()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Character.toLowerCase(str.charAt(0)));
                for (int i2 = 1; i2 < str.length(); i2++) {
                    char charAt = str.charAt(i2);
                    if (Character.isUpperCase(charAt)) {
                        sb2.append("_");
                    }
                    sb2.append(Character.toLowerCase(charAt));
                }
                str = sb2.toString();
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzafl.zza(zzacc.zzp((String) obj)));
                sb.append(Typography.quote);
            } else if (obj instanceof zzacc) {
                sb.append(": \"");
                sb.append(zzafl.zza((zzacc) obj));
                sb.append(Typography.quote);
            } else if (obj instanceof zzadf) {
                sb.append(" {");
                zzd((zzadf) obj, sb, i + 2);
                sb.append("\n");
                zzc(i, sb);
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry2 = (Map.Entry) obj;
                int i3 = i + 2;
                zzb(sb, i3, "key", entry2.getKey());
                zzb(sb, i3, "value", entry2.getValue());
                sb.append("\n");
                zzc(i, sb);
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj);
            }
        }
    }

    private static void zzc(int i, StringBuilder sb) {
        while (i > 0) {
            int i2 = 80;
            if (i <= 80) {
                i2 = i;
            }
            sb.append(zza, 0, i2);
            i -= i2;
        }
    }

    private static void zzd(zzaek zzaekVar, StringBuilder sb, int i) {
        int i2;
        boolean equals;
        Method method;
        Method method2;
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        Method[] declaredMethods = zzaekVar.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i3 = 0;
        while (true) {
            i2 = 3;
            if (i3 >= length) {
                break;
            }
            Method method3 = declaredMethods[i3];
            if (!Modifier.isStatic(method3.getModifiers()) && method3.getName().length() >= 3) {
                if (method3.getName().startsWith("set")) {
                    hashSet.add(method3.getName());
                } else if (Modifier.isPublic(method3.getModifiers()) && method3.getParameterTypes().length == 0) {
                    if (method3.getName().startsWith("has")) {
                        hashMap.put(method3.getName(), method3);
                    } else if (method3.getName().startsWith("get")) {
                        treeMap.put(method3.getName(), method3);
                    }
                }
            }
            i3++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String substring = ((String) entry.getKey()).substring(i2);
            if (!substring.endsWith("List") || substring.endsWith("OrBuilderList") || substring.equals("List") || (method2 = (Method) entry.getValue()) == null || !method2.getReturnType().equals(List.class)) {
                if (!substring.endsWith("Map") || substring.equals("Map") || (method = (Method) entry.getValue()) == null || !method.getReturnType().equals(Map.class) || method.isAnnotationPresent(Deprecated.class) || !Modifier.isPublic(method.getModifiers())) {
                    if (hashSet.contains("set".concat(String.valueOf(substring))) && (!substring.endsWith("Bytes") || !treeMap.containsKey("get".concat(String.valueOf(substring.substring(0, substring.length() - 5)))))) {
                        Method method4 = (Method) entry.getValue();
                        Method method5 = (Method) hashMap.get("has".concat(String.valueOf(substring)));
                        if (method4 != null) {
                            Object zzC = zzadf.zzC(method4, zzaekVar, new Object[0]);
                            if (method5 == null) {
                                if (zzC instanceof Boolean) {
                                    if (!((Boolean) zzC).booleanValue()) {
                                    }
                                    zzb(sb, i, substring, zzC);
                                } else if (zzC instanceof Integer) {
                                    if (((Integer) zzC).intValue() == 0) {
                                    }
                                    zzb(sb, i, substring, zzC);
                                } else if (zzC instanceof Float) {
                                    if (Float.floatToRawIntBits(((Float) zzC).floatValue()) == 0) {
                                    }
                                    zzb(sb, i, substring, zzC);
                                } else if (zzC instanceof Double) {
                                    if (Double.doubleToRawLongBits(((Double) zzC).doubleValue()) == 0) {
                                    }
                                    zzb(sb, i, substring, zzC);
                                } else {
                                    if (zzC instanceof String) {
                                        equals = zzC.equals("");
                                    } else if (zzC instanceof zzacc) {
                                        equals = zzC.equals(zzacc.zzb);
                                    } else if (zzC instanceof zzaek) {
                                        if (zzC == ((zzaek) zzC).zzL()) {
                                        }
                                        zzb(sb, i, substring, zzC);
                                    } else {
                                        if ((zzC instanceof Enum) && ((Enum) zzC).ordinal() == 0) {
                                        }
                                        zzb(sb, i, substring, zzC);
                                    }
                                    if (equals) {
                                    }
                                    zzb(sb, i, substring, zzC);
                                }
                            } else {
                                if (!((Boolean) zzadf.zzC(method5, zzaekVar, new Object[0])).booleanValue()) {
                                }
                                zzb(sb, i, substring, zzC);
                            }
                        }
                    }
                } else {
                    zzb(sb, i, substring.substring(0, substring.length() - 3), zzadf.zzC(method, zzaekVar, new Object[0]));
                }
            } else {
                zzb(sb, i, substring.substring(0, substring.length() - 4), zzadf.zzC(method2, zzaekVar, new Object[0]));
            }
            i2 = 3;
        }
        if (!(zzaekVar instanceof zzadc)) {
            zzafo zzafoVar = ((zzadf) zzaekVar).zzc;
            if (zzafoVar != null) {
                zzafoVar.zzi(sb, i);
                return;
            }
            return;
        }
        zzacx zzacxVar = ((zzadc) zzaekVar).zzb;
        throw null;
    }
}
