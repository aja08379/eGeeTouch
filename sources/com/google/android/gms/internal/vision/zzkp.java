package com.google.android.gms.internal.vision;

import java.util.List;
import java.util.Map;
import kotlin.text.Typography;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
public final class zzkp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(zzkk zzkkVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(str);
        zza(zzkkVar, sb, 0);
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:83:0x01f1, code lost:
        if (((java.lang.Boolean) r6).booleanValue() == false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01f3, code lost:
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0204, code lost:
        if (((java.lang.Integer) r6).intValue() == 0) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0215, code lost:
        if (((java.lang.Float) r6).floatValue() == 0.0f) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0227, code lost:
        if (((java.lang.Double) r6).doubleValue() == com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) goto L82;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void zza(com.google.android.gms.internal.vision.zzkk r13, java.lang.StringBuilder r14, int r15) {
        java.lang.reflect.Method[] r3;
        boolean r4;
        java.util.HashMap r0 = new java.util.HashMap();
        java.util.HashMap r1 = new java.util.HashMap();
        java.util.TreeSet<java.lang.String> r2 = new java.util.TreeSet();
        for (java.lang.reflect.Method r8 : r13.getClass().getDeclaredMethods()) {
            r1.put(r8.getName(), r8);
            if (r8.getParameterTypes().length == 0) {
                r0.put(r8.getName(), r8);
                if (r8.getName().startsWith("get")) {
                    r2.add(r8.getName());
                }
            }
        }
        for (java.lang.String r3 : r2) {
            java.lang.String r4 = r3.startsWith("get") ? r3.substring(3) : r3;
            boolean r10 = true;
            if (r4.endsWith("List") && !r4.endsWith("OrBuilderList") && !r4.equals("List")) {
                java.lang.String r8 = java.lang.String.valueOf(r4.substring(0, 1).toLowerCase());
                java.lang.String r9 = java.lang.String.valueOf(r4.substring(1, r4.length() - 4));
                java.lang.String r8 = r9.length() != 0 ? r8.concat(r9) : new java.lang.String(r8);
                java.lang.reflect.Method r9 = (java.lang.reflect.Method) r0.get(r3);
                if (r9 != null && r9.getReturnType().equals(java.util.List.class)) {
                    zza(r14, r15, zza(r8), com.google.android.gms.internal.vision.zzjb.zza(r9, r13, new java.lang.Object[0]));
                }
            }
            if (r4.endsWith("Map") && !r4.equals("Map")) {
                java.lang.String r8 = java.lang.String.valueOf(r4.substring(0, 1).toLowerCase());
                java.lang.String r6 = java.lang.String.valueOf(r4.substring(1, r4.length() - 3));
                java.lang.String r6 = r6.length() != 0 ? r8.concat(r6) : new java.lang.String(r8);
                java.lang.reflect.Method r3 = (java.lang.reflect.Method) r0.get(r3);
                if (r3 != null && r3.getReturnType().equals(java.util.Map.class) && !r3.isAnnotationPresent(java.lang.Deprecated.class) && java.lang.reflect.Modifier.isPublic(r3.getModifiers())) {
                    zza(r14, r15, zza(r6), com.google.android.gms.internal.vision.zzjb.zza(r3, r13, new java.lang.Object[0]));
                }
            }
            java.lang.String r6 = java.lang.String.valueOf(r4);
            if (((java.lang.reflect.Method) r1.get(r6.length() != 0 ? "set".concat(r6) : new java.lang.String("set"))) != null) {
                if (r4.endsWith("Bytes")) {
                    java.lang.String r3 = java.lang.String.valueOf(r4.substring(0, r4.length() - 5));
                    if (!r0.containsKey(r3.length() != 0 ? "get".concat(r3) : new java.lang.String("get"))) {
                    }
                }
                java.lang.String r3 = java.lang.String.valueOf(r4.substring(0, 1).toLowerCase());
                java.lang.String r6 = java.lang.String.valueOf(r4.substring(1));
                java.lang.String r3 = r6.length() != 0 ? r3.concat(r6) : new java.lang.String(r3);
                java.lang.String r6 = java.lang.String.valueOf(r4);
                java.lang.reflect.Method r6 = (java.lang.reflect.Method) r0.get(r6.length() != 0 ? "get".concat(r6) : new java.lang.String("get"));
                java.lang.String r4 = java.lang.String.valueOf(r4);
                java.lang.reflect.Method r4 = (java.lang.reflect.Method) r0.get(r4.length() != 0 ? "has".concat(r4) : new java.lang.String("has"));
                if (r6 != null) {
                    java.lang.Object r6 = com.google.android.gms.internal.vision.zzjb.zza(r6, r13, new java.lang.Object[0]);
                    if (r4 == null) {
                        if (!(r6 instanceof java.lang.Boolean)) {
                            if (!(r6 instanceof java.lang.Integer)) {
                                if (!(r6 instanceof java.lang.Float)) {
                                    if (!(r6 instanceof java.lang.Double)) {
                                        if (r6 instanceof java.lang.String) {
                                            r4 = r6.equals("");
                                        } else if (r6 instanceof com.google.android.gms.internal.vision.zzht) {
                                            r4 = r6.equals(com.google.android.gms.internal.vision.zzht.zza);
                                        } else {
                                            r4 = !(r6 instanceof com.google.android.gms.internal.vision.zzkk) ? false : false;
                                        }
                                    }
                                }
                            }
                        }
                        if (r4) {
                            r10 = false;
                        }
                    } else {
                        r10 = ((java.lang.Boolean) com.google.android.gms.internal.vision.zzjb.zza(r4, r13, new java.lang.Object[0])).booleanValue();
                    }
                    if (r10) {
                        zza(r14, r15, zza(r3), r6);
                    }
                }
            }
        }
        if (r13 instanceof com.google.android.gms.internal.vision.zzjb.zzc) {
            java.util.Iterator<java.util.Map.Entry<com.google.android.gms.internal.vision.zzjb.zzf, java.lang.Object>> r0 = ((com.google.android.gms.internal.vision.zzjb.zzc) r13).zzc.zzd();
            while (r0.hasNext()) {
                java.util.Map.Entry<com.google.android.gms.internal.vision.zzjb.zzf, java.lang.Object> r1 = r0.next();
                zza(r14, r15, new java.lang.StringBuilder(13).append("[").append(r1.getKey().zzb).append("]").toString(), r1.getValue());
            }
        }
        com.google.android.gms.internal.vision.zzjb r13 = (com.google.android.gms.internal.vision.zzjb) r13;
        if (r13.zzb != null) {
            r13.zzb.zza(r14, r15);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                zza(sb, i, str, obj2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                zza(sb, i, str, entry);
            }
        } else {
            sb.append('\n');
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"").append(zzlq.zza(zzht.zza((String) obj))).append(Typography.quote);
            } else if (obj instanceof zzht) {
                sb.append(": \"").append(zzlq.zza((zzht) obj)).append(Typography.quote);
            } else if (obj instanceof zzjb) {
                sb.append(" {");
                zza((zzjb) obj, sb, i + 2);
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry2 = (Map.Entry) obj;
                int i4 = i + 2;
                zza(sb, i4, "key", entry2.getKey());
                zza(sb, i4, "value", entry2.getValue());
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else {
                sb.append(": ").append(obj.toString());
            }
        }
    }

    private static final String zza(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}
