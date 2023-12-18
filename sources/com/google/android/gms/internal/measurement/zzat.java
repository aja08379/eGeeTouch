package com.google.android.gms.internal.measurement;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Iterator;
/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes.dex */
public final class zzat implements Iterable, zzap {
    private final String zza;

    public zzat(String str) {
        if (str == null) {
            throw new IllegalArgumentException("StringValue cannot be null.");
        }
        this.zza = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzat) {
            return this.zza.equals(((zzat) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new zzas(this);
    }

    public final String toString() {
        String str = this.zza;
        return "\"" + str + "\"";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x03d3  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x041f  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x04af  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x04ff  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x055c  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x05fd  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0638  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x016f  */
    @Override // com.google.android.gms.internal.measurement.zzap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.measurement.zzap zzbR(java.lang.String r22, com.google.android.gms.internal.measurement.zzg r23, java.util.List r24) {
        java.lang.String r14;
        java.lang.String r5;
        java.lang.String r17;
        java.lang.String r6;
        char r1;
        java.lang.String r3;
        java.lang.String r3;
        java.lang.String r4;
        java.lang.String r4;
        com.google.android.gms.internal.measurement.zzat r0;
        int r1;
        com.google.android.gms.internal.measurement.zzap r3;
        double r1;
        java.lang.String r17;
        java.util.regex.Matcher r1;
        double r4;
        double r5;
        int r3;
        int r14;
        com.google.android.gms.internal.measurement.zzg r3;
        int r4;
        int r1;
        java.lang.String r4;
        java.lang.String r4;
        if ("charAt".equals(r22) || "concat".equals(r22) || "hasOwnProperty".equals(r22) || "indexOf".equals(r22) || "lastIndexOf".equals(r22) || "match".equals(r22) || "replace".equals(r22) || com.google.firebase.analytics.FirebaseAnalytics.Event.SEARCH.equals(r22) || "slice".equals(r22) || "split".equals(r22) || "substring".equals(r22) || "toLowerCase".equals(r22) || "toLocaleLowerCase".equals(r22) || "toString".equals(r22)) {
            r14 = "toLocaleUpperCase";
            r5 = "toUpperCase";
        } else {
            r5 = "toUpperCase";
            r14 = "toLocaleUpperCase";
            if (!r5.equals(r22) && !r14.equals(r22)) {
                r17 = "hasOwnProperty";
                if (!"trim".equals(r22)) {
                    throw new java.lang.IllegalArgumentException(java.lang.String.format("%s is not a String function", r22));
                }
                switch (r22.hashCode()) {
                    case -1789698943:
                        java.lang.String r4 = "charAt";
                        java.lang.String r3 = r17;
                        r6 = "toString";
                        boolean r1 = r22.equals(r3);
                        r3 = r3;
                        r4 = r4;
                        if (r1) {
                            r1 = 2;
                            r3 = r3;
                            r4 = r4;
                            break;
                        }
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case -1776922004:
                        java.lang.String r4 = "charAt";
                        r6 = "toString";
                        if (r22.equals(r6)) {
                            r1 = 14;
                            r3 = r17;
                            r4 = r4;
                            break;
                        } else {
                            r3 = r17;
                            r4 = r4;
                            r1 = 65535;
                            r3 = r3;
                            r4 = r4;
                            break;
                        }
                    case -1464939364:
                        java.lang.String r4 = "charAt";
                        r4 = r4;
                        if (r22.equals("toLocaleLowerCase")) {
                            r1 = '\f';
                            r4 = r4;
                            r3 = r17;
                            r6 = "toString";
                            r4 = r4;
                            break;
                        }
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case -1361633751:
                        java.lang.String r4 = "charAt";
                        boolean r1 = r22.equals(r4);
                        r4 = r4;
                        if (r1) {
                            r3 = r17;
                            r6 = "toString";
                            r1 = 0;
                            r4 = r4;
                            break;
                        }
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case -1354795244:
                        if (r22.equals("concat")) {
                            r4 = "charAt";
                            r3 = r17;
                            r6 = "toString";
                            r1 = 1;
                            break;
                        }
                        r4 = "charAt";
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case -1137582698:
                        if (r22.equals("toLowerCase")) {
                            r1 = '\r';
                            r4 = "charAt";
                            r3 = r17;
                            r6 = "toString";
                            r4 = r4;
                            break;
                        }
                        r4 = "charAt";
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case -906336856:
                        if (r22.equals(com.google.firebase.analytics.FirebaseAnalytics.Event.SEARCH)) {
                            r1 = 7;
                            r4 = "charAt";
                            r3 = r17;
                            r6 = "toString";
                            r4 = r4;
                            break;
                        }
                        r4 = "charAt";
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case -726908483:
                        if (r22.equals(r14)) {
                            r1 = 11;
                            r4 = "charAt";
                            r3 = r17;
                            r6 = "toString";
                            r4 = r4;
                            break;
                        }
                        r4 = "charAt";
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case -467511597:
                        if (r22.equals("lastIndexOf")) {
                            r1 = 4;
                            r4 = "charAt";
                            r3 = r17;
                            r6 = "toString";
                            r4 = r4;
                            break;
                        }
                        r4 = "charAt";
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case -399551817:
                        if (r22.equals(r5)) {
                            r1 = 15;
                            r4 = "charAt";
                            r3 = r17;
                            r6 = "toString";
                            r4 = r4;
                            break;
                        }
                        r4 = "charAt";
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case 3568674:
                        if (r22.equals("trim")) {
                            r1 = 16;
                            r4 = "charAt";
                            r3 = r17;
                            r6 = "toString";
                            r4 = r4;
                            break;
                        }
                        r4 = "charAt";
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case 103668165:
                        if (r22.equals("match")) {
                            r1 = 5;
                            r4 = "charAt";
                            r3 = r17;
                            r6 = "toString";
                            r4 = r4;
                            break;
                        }
                        r4 = "charAt";
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case 109526418:
                        if (r22.equals("slice")) {
                            r1 = '\b';
                            r4 = "charAt";
                            r3 = r17;
                            r6 = "toString";
                            r4 = r4;
                            break;
                        }
                        r4 = "charAt";
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case 109648666:
                        if (r22.equals("split")) {
                            r1 = '\t';
                            r4 = "charAt";
                            r3 = r17;
                            r6 = "toString";
                            r4 = r4;
                            break;
                        }
                        r4 = "charAt";
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case 530542161:
                        if (r22.equals("substring")) {
                            r1 = '\n';
                            r4 = "charAt";
                            r3 = r17;
                            r6 = "toString";
                            r4 = r4;
                            break;
                        }
                        r4 = "charAt";
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case 1094496948:
                        if (r22.equals("replace")) {
                            r1 = 6;
                            r4 = "charAt";
                            r3 = r17;
                            r6 = "toString";
                            r4 = r4;
                            break;
                        }
                        r4 = "charAt";
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    case 1943291465:
                        if (r22.equals("indexOf")) {
                            r1 = 3;
                            r4 = "charAt";
                            r3 = r17;
                            r6 = "toString";
                            r4 = r4;
                            break;
                        }
                        r4 = "charAt";
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                    default:
                        r4 = "charAt";
                        r3 = r17;
                        r6 = "toString";
                        r4 = r4;
                        r1 = 65535;
                        r3 = r3;
                        r4 = r4;
                        break;
                }
                java.lang.String r20 = r3;
                java.lang.String r19 = r4;
                switch (r1) {
                    case 0:
                        com.google.android.gms.internal.measurement.zzh.zzj(r19, 1, r24);
                        int r14 = !r24.isEmpty() ? (int) com.google.android.gms.internal.measurement.zzh.zza(r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(0)).zzh().doubleValue()) : 0;
                        java.lang.String r1 = r21.zza;
                        if (r14 < 0 || r14 >= r1.length()) {
                            return com.google.android.gms.internal.measurement.zzap.zzm;
                        }
                        return new com.google.android.gms.internal.measurement.zzat(java.lang.String.valueOf(r1.charAt(r14)));
                    case 1:
                        r0 = r21;
                        if (!r24.isEmpty()) {
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder(r0.zza);
                            for (int r14 = 0; r14 < r24.size(); r14++) {
                                r2.append(r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(r14)).zzi());
                            }
                            return new com.google.android.gms.internal.measurement.zzat(r2.toString());
                        }
                        return r0;
                    case 2:
                        com.google.android.gms.internal.measurement.zzh.zzh(r20, 1, r24);
                        java.lang.String r2 = r21.zza;
                        com.google.android.gms.internal.measurement.zzap r1 = r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(0));
                        if ("length".equals(r1.zzi())) {
                            return com.google.android.gms.internal.measurement.zzaf.zzk;
                        }
                        double r3 = r1.zzh().doubleValue();
                        return (r3 != java.lang.Math.floor(r3) || (r1 = (int) r3) < 0 || r1 >= r2.length()) ? com.google.android.gms.internal.measurement.zzaf.zzl : com.google.android.gms.internal.measurement.zzaf.zzk;
                    case 3:
                        com.google.android.gms.internal.measurement.zzh.zzj("indexOf", 2, r24);
                        r3 = new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(r21.zza.indexOf(r24.size() > 0 ? r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(0)).zzi() : "undefined", (int) com.google.android.gms.internal.measurement.zzh.zza(r24.size() < 2 ? 0.0d : r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(1)).zzh().doubleValue()))));
                        return r3;
                    case 4:
                        com.google.android.gms.internal.measurement.zzh.zzj("lastIndexOf", 2, r24);
                        java.lang.String r4 = r21.zza;
                        java.lang.String r5 = r24.size() > 0 ? r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(0)).zzi() : "undefined";
                        r3 = new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(r4.lastIndexOf(r5, (int) (java.lang.Double.isNaN(r24.size() < 2 ? Double.NaN : r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(1)).zzh().doubleValue()) ? Double.POSITIVE_INFINITY : com.google.android.gms.internal.measurement.zzh.zza(r1)))));
                        return r3;
                    case 5:
                        com.google.android.gms.internal.measurement.zzh.zzj("match", 1, r24);
                        java.util.regex.Matcher r1 = java.util.regex.Pattern.compile(r24.size() <= 0 ? "" : r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(0)).zzi()).matcher(r21.zza);
                        return r1.find() ? new com.google.android.gms.internal.measurement.zzae(java.util.Arrays.asList(new com.google.android.gms.internal.measurement.zzat(r1.group()))) : com.google.android.gms.internal.measurement.zzap.zzg;
                    case 6:
                        r0 = r21;
                        com.google.android.gms.internal.measurement.zzh.zzj("replace", 2, r24);
                        com.google.android.gms.internal.measurement.zzap r2 = com.google.android.gms.internal.measurement.zzap.zzf;
                        if (!r24.isEmpty()) {
                            r17 = r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(0)).zzi();
                            if (r24.size() > 1) {
                                r2 = r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(1));
                            }
                        }
                        java.lang.String r1 = r17;
                        java.lang.String r4 = r0.zza;
                        int r5 = r4.indexOf(r1);
                        if (r5 >= 0) {
                            if (r2 instanceof com.google.android.gms.internal.measurement.zzai) {
                                r2 = ((com.google.android.gms.internal.measurement.zzai) r2).zza(r23, java.util.Arrays.asList(new com.google.android.gms.internal.measurement.zzat(r1), new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(r5)), r0));
                            }
                            r3 = new com.google.android.gms.internal.measurement.zzat(r4.substring(0, r5) + r2.zzi() + r4.substring(r5 + r1.length()));
                            return r3;
                        }
                        return r0;
                    case 7:
                        com.google.android.gms.internal.measurement.zzh.zzj(com.google.firebase.analytics.FirebaseAnalytics.Event.SEARCH, 1, r24);
                        if (java.util.regex.Pattern.compile(r24.isEmpty() ? "undefined" : r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(0)).zzi()).matcher(r21.zza).find()) {
                            return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(r1.start()));
                        }
                        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(-1.0d));
                    case '\b':
                        com.google.android.gms.internal.measurement.zzh.zzj("slice", 2, r24);
                        java.lang.String r2 = r21.zza;
                        double r4 = com.google.android.gms.internal.measurement.zzh.zza(!r24.isEmpty() ? r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(0)).zzh().doubleValue() : com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        if (r4 < com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            r4 = java.lang.Math.max(r2.length() + r4, (double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        } else {
                            r4 = java.lang.Math.min(r4, r2.length());
                        }
                        int r4 = (int) r4;
                        double r5 = com.google.android.gms.internal.measurement.zzh.zza(r24.size() > 1 ? r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(1)).zzh().doubleValue() : r2.length());
                        if (r5 < com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            r5 = java.lang.Math.max(r2.length() + r5, (double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        } else {
                            r5 = java.lang.Math.min(r5, r2.length());
                        }
                        r3 = new com.google.android.gms.internal.measurement.zzat(r2.substring(r4, java.lang.Math.max(0, ((int) r5) - r4) + r4));
                        return r3;
                    case '\t':
                        com.google.android.gms.internal.measurement.zzh.zzj("split", 2, r24);
                        java.lang.String r2 = r21.zza;
                        if (r2.length() == 0) {
                            return new com.google.android.gms.internal.measurement.zzae(java.util.Arrays.asList(r21));
                        }
                        java.util.ArrayList r5 = new java.util.ArrayList();
                        if (r24.isEmpty()) {
                            r5.add(r21);
                        } else {
                            java.lang.String r4 = r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(0)).zzi();
                            long r6 = r24.size() > 1 ? com.google.android.gms.internal.measurement.zzh.zzd(r23.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(1)).zzh().doubleValue()) : 2147483647L;
                            if (r6 == 0) {
                                return new com.google.android.gms.internal.measurement.zzae();
                            }
                            java.lang.String[] r1 = r2.split(java.util.regex.Pattern.quote(r4), ((int) r6) + 1);
                            int r2 = r1.length;
                            if (!r4.isEmpty() || r2 <= 0) {
                                r3 = r2;
                                r14 = 0;
                            } else {
                                boolean r14 = r1[0].isEmpty();
                                r3 = r2 - 1;
                                r14 = r14;
                                if (!r1[r3].isEmpty()) {
                                    r3 = r2;
                                    r14 = r14;
                                }
                            }
                            if (r2 > r6) {
                                r3--;
                            }
                            while (r14 < r3) {
                                r5.add(new com.google.android.gms.internal.measurement.zzat(r1[r14]));
                                r14++;
                            }
                        }
                        return new com.google.android.gms.internal.measurement.zzae(r5);
                    case '\n':
                        com.google.android.gms.internal.measurement.zzh.zzj("substring", 2, r24);
                        java.lang.String r2 = r21.zza;
                        if (r24.isEmpty()) {
                            r3 = r23;
                            r4 = 0;
                        } else {
                            r3 = r23;
                            r4 = (int) com.google.android.gms.internal.measurement.zzh.zza(r3.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(0)).zzh().doubleValue());
                        }
                        if (r24.size() > 1) {
                            r1 = (int) com.google.android.gms.internal.measurement.zzh.zza(r3.zzb((com.google.android.gms.internal.measurement.zzap) r24.get(1)).zzh().doubleValue());
                        } else {
                            r1 = r2.length();
                        }
                        int r4 = java.lang.Math.min(java.lang.Math.max(r4, 0), r2.length());
                        int r1 = java.lang.Math.min(java.lang.Math.max(r1, 0), r2.length());
                        r3 = new com.google.android.gms.internal.measurement.zzat(r2.substring(java.lang.Math.min(r4, r1), java.lang.Math.max(r4, r1)));
                        return r3;
                    case 11:
                        com.google.android.gms.internal.measurement.zzh.zzh(r14, 0, r24);
                        return new com.google.android.gms.internal.measurement.zzat(r21.zza.toUpperCase());
                    case '\f':
                        com.google.android.gms.internal.measurement.zzh.zzh("toLocaleLowerCase", 0, r24);
                        return new com.google.android.gms.internal.measurement.zzat(r21.zza.toLowerCase());
                    case '\r':
                        com.google.android.gms.internal.measurement.zzh.zzh("toLowerCase", 0, r24);
                        return new com.google.android.gms.internal.measurement.zzat(r21.zza.toLowerCase(java.util.Locale.ENGLISH));
                    case 14:
                        r0 = r21;
                        com.google.android.gms.internal.measurement.zzh.zzh(r6, 0, r24);
                        return r0;
                    case 15:
                        com.google.android.gms.internal.measurement.zzh.zzh(r5, 0, r24);
                        return new com.google.android.gms.internal.measurement.zzat(r21.zza.toUpperCase(java.util.Locale.ENGLISH));
                    case 16:
                        com.google.android.gms.internal.measurement.zzh.zzh(r5, 0, r24);
                        return new com.google.android.gms.internal.measurement.zzat(r21.zza.trim());
                    default:
                        throw new java.lang.IllegalArgumentException("Command not supported");
                }
            }
        }
        r17 = "hasOwnProperty";
        switch (r22.hashCode()) {
            case -1789698943:
                break;
            case -1776922004:
                break;
            case -1464939364:
                break;
            case -1361633751:
                break;
            case -1354795244:
                break;
            case -1137582698:
                break;
            case -906336856:
                break;
            case -726908483:
                break;
            case -467511597:
                break;
            case -399551817:
                break;
            case 3568674:
                break;
            case 103668165:
                break;
            case 109526418:
                break;
            case 109648666:
                break;
            case 530542161:
                break;
            case 1094496948:
                break;
            case 1943291465:
                break;
        }
        java.lang.String r20 = r3;
        java.lang.String r19 = r4;
        switch (r1) {
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return new zzat(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return Boolean.valueOf(!this.zza.isEmpty());
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        if (this.zza.isEmpty()) {
            return Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
        try {
            return Double.valueOf(this.zza);
        } catch (NumberFormatException unused) {
            return Double.valueOf(Double.NaN);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return new zzar(this);
    }
}
