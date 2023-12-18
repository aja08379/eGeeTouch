package com.google.android.gms.internal.clearcut;

import java.util.List;
import java.util.Map;
import kotlin.text.Typography;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdr {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(zzdo zzdoVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(str);
        zza(zzdoVar, sb, 0);
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:79:0x01f2, code lost:
        if (((java.lang.Boolean) r11).booleanValue() == false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01f4, code lost:
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0204, code lost:
        if (((java.lang.Integer) r11).intValue() == 0) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0215, code lost:
        if (((java.lang.Float) r11).floatValue() == 0.0f) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0227, code lost:
        if (((java.lang.Double) r11).doubleValue() == com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) goto L79;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void zza(com.google.android.gms.internal.clearcut.zzdo r18, java.lang.StringBuilder r19, int r20) {
        java.lang.reflect.Method[] r6;
        boolean r7;
        java.util.HashMap r3 = new java.util.HashMap();
        java.util.HashMap r4 = new java.util.HashMap();
        java.util.TreeSet<java.lang.String> r5 = new java.util.TreeSet();
        for (java.lang.reflect.Method r11 : r18.getClass().getDeclaredMethods()) {
            r4.put(r11.getName(), r11);
            if (r11.getParameterTypes().length == 0) {
                r3.put(r11.getName(), r11);
                if (r11.getName().startsWith("get")) {
                    r5.add(r11.getName());
                }
            }
        }
        for (java.lang.String r6 : r5) {
            java.lang.Object r7 = "";
            java.lang.String r9 = r6.replaceFirst("get", "");
            boolean r13 = true;
            if (r9.endsWith("List") && !r9.endsWith("OrBuilderList") && !r9.equals("List")) {
                java.lang.String r11 = java.lang.String.valueOf(r9.substring(0, 1).toLowerCase());
                java.lang.String r12 = java.lang.String.valueOf(r9.substring(1, r9.length() - 4));
                java.lang.String r11 = r12.length() != 0 ? r11.concat(r12) : new java.lang.String(r11);
                java.lang.reflect.Method r12 = (java.lang.reflect.Method) r3.get(r6);
                if (r12 != null && r12.getReturnType().equals(java.util.List.class)) {
                    zza(r19, r20, zzj(r11), com.google.android.gms.internal.clearcut.zzcg.zza(r12, r18, new java.lang.Object[0]));
                }
            }
            if (r9.endsWith("Map") && !r9.equals("Map")) {
                java.lang.String r11 = java.lang.String.valueOf(r9.substring(0, 1).toLowerCase());
                java.lang.String r12 = java.lang.String.valueOf(r9.substring(1, r9.length() - 3));
                java.lang.String r11 = r12.length() != 0 ? r11.concat(r12) : new java.lang.String(r11);
                java.lang.reflect.Method r6 = (java.lang.reflect.Method) r3.get(r6);
                if (r6 != null && r6.getReturnType().equals(java.util.Map.class) && !r6.isAnnotationPresent(java.lang.Deprecated.class) && java.lang.reflect.Modifier.isPublic(r6.getModifiers())) {
                    zza(r19, r20, zzj(r11), com.google.android.gms.internal.clearcut.zzcg.zza(r6, r18, new java.lang.Object[0]));
                }
            }
            java.lang.String r11 = java.lang.String.valueOf(r9);
            if (((java.lang.reflect.Method) r4.get(r11.length() != 0 ? "set".concat(r11) : new java.lang.String("set"))) != null) {
                if (r9.endsWith("Bytes")) {
                    java.lang.String r6 = java.lang.String.valueOf(r9.substring(0, r9.length() - 5));
                    if (!r3.containsKey(r6.length() != 0 ? "get".concat(r6) : new java.lang.String("get"))) {
                    }
                }
                java.lang.String r6 = java.lang.String.valueOf(r9.substring(0, 1).toLowerCase());
                java.lang.String r11 = java.lang.String.valueOf(r9.substring(1));
                java.lang.String r6 = r11.length() != 0 ? r6.concat(r11) : new java.lang.String(r6);
                java.lang.String r11 = java.lang.String.valueOf(r9);
                java.lang.reflect.Method r11 = (java.lang.reflect.Method) r3.get(r11.length() != 0 ? "get".concat(r11) : new java.lang.String("get"));
                java.lang.String r9 = java.lang.String.valueOf(r9);
                java.lang.reflect.Method r9 = (java.lang.reflect.Method) r3.get(r9.length() != 0 ? "has".concat(r9) : new java.lang.String("has"));
                if (r11 != null) {
                    java.lang.Object r11 = com.google.android.gms.internal.clearcut.zzcg.zza(r11, r18, new java.lang.Object[0]);
                    if (r9 == null) {
                        if (!(r11 instanceof java.lang.Boolean)) {
                            if (!(r11 instanceof java.lang.Integer)) {
                                if (!(r11 instanceof java.lang.Float)) {
                                    if (!(r11 instanceof java.lang.Double)) {
                                        if (!(r11 instanceof java.lang.String)) {
                                            if (r11 instanceof com.google.android.gms.internal.clearcut.zzbb) {
                                                r7 = com.google.android.gms.internal.clearcut.zzbb.zzfi;
                                            } else {
                                                r7 = !(r11 instanceof com.google.android.gms.internal.clearcut.zzdo) ? false : false;
                                            }
                                        }
                                        r7 = r11.equals(r7);
                                    }
                                }
                            }
                        }
                        if (r7) {
                            r13 = false;
                        }
                    } else {
                        r13 = ((java.lang.Boolean) com.google.android.gms.internal.clearcut.zzcg.zza(r9, r18, new java.lang.Object[0])).booleanValue();
                    }
                    if (r13) {
                        zza(r19, r20, zzj(r6), r11);
                    }
                }
            }
        }
        if (r18 instanceof com.google.android.gms.internal.clearcut.zzcg.zzd) {
            java.util.Iterator<java.util.Map.Entry<com.google.android.gms.internal.clearcut.zzcg.zze, java.lang.Object>> r3 = ((com.google.android.gms.internal.clearcut.zzcg.zzd) r18).zzjv.iterator();
            while (r3.hasNext()) {
                java.util.Map.Entry<com.google.android.gms.internal.clearcut.zzcg.zze, java.lang.Object> r4 = r3.next();
                zza(r19, r20, new java.lang.StringBuilder(13).append("[").append(r4.getKey().number).append("]").toString(), r4.getValue());
            }
        }
        com.google.android.gms.internal.clearcut.zzcg r0 = (com.google.android.gms.internal.clearcut.zzcg) r18;
        if (r0.zzjp != null) {
            r0.zzjp.zza(r19, r20);
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
                sb.append(": \"").append(zzet.zzc(zzbb.zzf((String) obj))).append(Typography.quote);
            } else if (obj instanceof zzbb) {
                sb.append(": \"").append(zzet.zzc((zzbb) obj)).append(Typography.quote);
            } else if (obj instanceof zzcg) {
                sb.append(" {");
                zza((zzcg) obj, sb, i + 2);
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else if (!(obj instanceof Map.Entry)) {
                sb.append(": ").append(obj.toString());
            } else {
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
            }
        }
    }

    private static final String zzj(String str) {
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
