package com.google.android.gms.measurement.internal;

import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaa extends zzkh {
    private String zza;
    private Set zzb;
    private Map zzc;
    private Long zzd;
    private Long zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(zzkt zzktVar) {
        super(zzktVar);
    }

    private final zzu zzd(Integer num) {
        if (this.zzc.containsKey(num)) {
            return (zzu) this.zzc.get(num);
        }
        zzu zzuVar = new zzu(this, this.zza, null);
        this.zzc.put(num, zzuVar);
        return zzuVar;
    }

    private final boolean zzf(int i, int i2) {
        zzu zzuVar = (zzu) this.zzc.get(Integer.valueOf(i));
        if (zzuVar == null) {
            return false;
        }
        return zzu.zzb(zzuVar).get(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(16:(6:19|20|21|22|23|(21:(7:25|26|27|28|(1:30)(3:517|(1:519)(1:521)|520)|31|(1:34)(1:33))|(1:36)|37|38|39|40|41|42|(3:44|(1:46)|47)(4:475|(6:476|477|478|479|480|(1:483)(1:482))|(1:485)|486)|48|(1:50)(6:284|(6:286|287|288|289|290|(2:(3:292|(1:294)|295)|297)(1:460))(1:474)|304|(10:307|(3:311|(4:314|(5:316|317|(1:319)(1:323)|320|321)(1:324)|322|312)|325)|326|(3:330|(4:333|(3:338|339|340)|341|331)|344)|345|(3:347|(6:350|(2:352|(3:354|355|356))(1:359)|357|358|356|348)|360)|361|(3:370|(8:373|(1:375)|376|(1:378)|379|(3:381|382|383)(1:385)|384|371)|386)|387|305)|393|394)|51|(3:182|(4:185|(10:187|188|(1:190)(1:281)|191|(10:193|194|195|196|197|198|199|200|(4:202|(11:203|204|205|206|207|208|209|(3:211|212|213)(1:256)|214|215|(1:218)(1:217))|(1:220)|221)(2:262|263)|222)(1:280)|223|(4:226|(3:244|245|246)(4:228|229|(2:230|(2:232|(1:234)(2:235|236))(1:243))|(3:238|239|240)(1:242))|241|224)|247|248|249)(1:282)|250|183)|283)|53|54|(3:81|(6:84|(7:86|87|88|89|90|(3:(9:92|93|94|95|96|(1:98)(1:157)|99|100|(1:103)(1:102))|(1:105)|106)(2:164|165)|107)(1:180)|108|(2:109|(2:111|(3:147|148|149)(8:113|(2:114|(4:116|(3:118|(1:120)(1:143)|121)(1:144)|122|(1:1)(2:126|(1:128)(2:129|130)))(2:145|146))|137|(1:139)(1:141)|140|132|133|134))(0))|150|82)|181)|56|57|(9:60|61|62|63|64|65|(2:67|68)(1:70)|69|58)|78|79)(1:525))|41|42|(0)(0)|48|(0)(0)|51|(0)|53|54|(0)|56|57|(1:58)|78|79) */
    /* JADX WARN: Can't wrap try/catch for region: R(26:1|(2:2|(2:4|(2:6|7))(2:541|542))|8|(3:10|11|12)|16|(6:19|20|21|22|23|(21:(7:25|26|27|28|(1:30)(3:517|(1:519)(1:521)|520)|31|(1:34)(1:33))|(1:36)|37|38|39|40|41|42|(3:44|(1:46)|47)(4:475|(6:476|477|478|479|480|(1:483)(1:482))|(1:485)|486)|48|(1:50)(6:284|(6:286|287|288|289|290|(2:(3:292|(1:294)|295)|297)(1:460))(1:474)|304|(10:307|(3:311|(4:314|(5:316|317|(1:319)(1:323)|320|321)(1:324)|322|312)|325)|326|(3:330|(4:333|(3:338|339|340)|341|331)|344)|345|(3:347|(6:350|(2:352|(3:354|355|356))(1:359)|357|358|356|348)|360)|361|(3:370|(8:373|(1:375)|376|(1:378)|379|(3:381|382|383)(1:385)|384|371)|386)|387|305)|393|394)|51|(3:182|(4:185|(10:187|188|(1:190)(1:281)|191|(10:193|194|195|196|197|198|199|200|(4:202|(11:203|204|205|206|207|208|209|(3:211|212|213)(1:256)|214|215|(1:218)(1:217))|(1:220)|221)(2:262|263)|222)(1:280)|223|(4:226|(3:244|245|246)(4:228|229|(2:230|(2:232|(1:234)(2:235|236))(1:243))|(3:238|239|240)(1:242))|241|224)|247|248|249)(1:282)|250|183)|283)|53|54|(3:81|(6:84|(7:86|87|88|89|90|(3:(9:92|93|94|95|96|(1:98)(1:157)|99|100|(1:103)(1:102))|(1:105)|106)(2:164|165)|107)(1:180)|108|(2:109|(2:111|(3:147|148|149)(8:113|(2:114|(4:116|(3:118|(1:120)(1:143)|121)(1:144)|122|(1:1)(2:126|(1:128)(2:129|130)))(2:145|146))|137|(1:139)(1:141)|140|132|133|134))(0))|150|82)|181)|56|57|(9:60|61|62|63|64|65|(2:67|68)(1:70)|69|58)|78|79)(1:525))|540|38|39|40|41|42|(0)(0)|48|(0)(0)|51|(0)|53|54|(0)|56|57|(1:58)|78|79|(5:(0)|(0)|(0)|(0)|(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x02cc, code lost:
        if (r5 != null) goto L299;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x02ce, code lost:
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x02d6, code lost:
        if (r5 != null) goto L299;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x02fb, code lost:
        if (r5 == null) goto L300;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x02fe, code lost:
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r1);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r13);
        r1 = new androidx.collection.ArrayMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x030d, code lost:
        if (r13.isEmpty() == false) goto L395;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x030f, code lost:
        r21 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0313, code lost:
        r3 = r13.keySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x031f, code lost:
        if (r3.hasNext() == false) goto L459;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0321, code lost:
        r4 = ((java.lang.Integer) r3.next()).intValue();
        r5 = java.lang.Integer.valueOf(r4);
        r6 = (com.google.android.gms.internal.measurement.zzgi) r13.get(r5);
        r7 = (java.util.List) r0.get(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x033b, code lost:
        if (r7 == null) goto L458;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0341, code lost:
        if (r7.isEmpty() == false) goto L402;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x0345, code lost:
        r17 = r0;
        r0 = r64.zzf.zzu().zzq(r6.zzk(), r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0359, code lost:
        if (r0.isEmpty() != false) goto L404;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x035b, code lost:
        r5 = (com.google.android.gms.internal.measurement.zzgh) r6.zzby();
        r5.zzf();
        r5.zzb(r0);
        r20 = r3;
        r0 = r64.zzf.zzu().zzq(r6.zzn(), r7);
        r5.zzh();
        r5.zzd(r0);
        com.google.android.gms.internal.measurement.zzoc.zzc();
        r21 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x038f, code lost:
        if (r64.zzt.zzf().zzs(null, com.google.android.gms.measurement.internal.zzdu.zzas) == false) goto L436;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0391, code lost:
        r0 = new java.util.ArrayList();
        r3 = r6.zzj().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x03a2, code lost:
        if (r3.hasNext() == false) goto L420;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x03a4, code lost:
        r8 = (com.google.android.gms.internal.measurement.zzfr) r3.next();
        r23 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x03ba, code lost:
        if (r7.contains(java.lang.Integer.valueOf(r8.zza())) != false) goto L419;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x03bc, code lost:
        r0.add(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x03bf, code lost:
        r3 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x03c3, code lost:
        r5.zze();
        r5.zza(r0);
        r0 = new java.util.ArrayList();
        r3 = r6.zzm().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x03da, code lost:
        if (r3.hasNext() == false) goto L431;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x03dc, code lost:
        r6 = (com.google.android.gms.internal.measurement.zzgk) r3.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x03ee, code lost:
        if (r7.contains(java.lang.Integer.valueOf(r6.zzb())) != false) goto L430;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x03f0, code lost:
        r0.add(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x03f4, code lost:
        r5.zzg();
        r5.zzc(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x03fb, code lost:
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0400, code lost:
        if (r0 >= r6.zza()) goto L445;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0412, code lost:
        if (r7.contains(java.lang.Integer.valueOf(r6.zze(r0).zza())) == false) goto L444;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0414, code lost:
        r5.zzi(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x0417, code lost:
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x041a, code lost:
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x041f, code lost:
        if (r0 >= r6.zzc()) goto L455;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0431, code lost:
        if (r7.contains(java.lang.Integer.valueOf(r6.zzi(r0).zzb())) == false) goto L454;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x0433, code lost:
        r5.zzj(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x0436, code lost:
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x0439, code lost:
        r1.put(java.lang.Integer.valueOf(r4), (com.google.android.gms.internal.measurement.zzgi) r5.zzaC());
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x0447, code lost:
        r0 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x044b, code lost:
        r17 = r0;
        r20 = r3;
        r21 = r8;
        r1.put(r5, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x0454, code lost:
        r0 = r17;
        r3 = r20;
        r8 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x045c, code lost:
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:291:0x0794, code lost:
        if (r5 != null) goto L255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:292:0x0796, code lost:
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:307:0x07c6, code lost:
        if (r5 != null) goto L255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:362:0x0945, code lost:
        if (r13 != null) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:363:0x0947, code lost:
        r13.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x096d, code lost:
        if (r13 == null) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:414:0x0a99, code lost:
        if (r7 != false) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0151, code lost:
        if (r5 != null) goto L527;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0153, code lost:
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0175, code lost:
        if (r5 == null) goto L540;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0228, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0229, code lost:
        r18 = "audience_id";
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x022e, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x022f, code lost:
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0232, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0233, code lost:
        r18 = "audience_id";
        r19 = com.facebook.share.internal.ShareConstants.WEB_DIALOG_PARAM_DATA;
        r4 = null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0617  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x087c  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0aca  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01b4 A[Catch: SQLiteException -> 0x0228, all -> 0x0b5a, TRY_LEAVE, TryCatch #12 {SQLiteException -> 0x0228, blocks: (B:61:0x01ae, B:63:0x01b4, B:67:0x01c4, B:68:0x01c9, B:69:0x01d3, B:70:0x01e3, B:72:0x01f2), top: B:454:0x01ae }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01c4 A[Catch: SQLiteException -> 0x0228, all -> 0x0b5a, TRY_ENTER, TryCatch #12 {SQLiteException -> 0x0228, blocks: (B:61:0x01ae, B:63:0x01b4, B:67:0x01c4, B:68:0x01c9, B:69:0x01d3, B:70:0x01e3, B:72:0x01f2), top: B:454:0x01ae }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0251  */
    /* JADX WARN: Type inference failed for: r5v5, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v59, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v60 */
    /* JADX WARN: Type inference failed for: r5v61, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r5v8, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List zza(java.lang.String r65, java.util.List r66, java.util.List r67, java.lang.Long r68, java.lang.Long r69) {
        int r12;
        int r13;
        boolean r1;
        android.database.Cursor r5;
        androidx.collection.ArrayMap r12;
        java.lang.String r18;
        java.lang.String r19;
        android.database.Cursor r4;
        androidx.collection.ArrayMap r13;
        java.lang.String r21;
        androidx.collection.ArrayMap r0;
        java.lang.String r13;
        java.lang.String r28;
        java.lang.String r29;
        java.lang.String r12;
        java.util.List<com.google.android.gms.internal.measurement.zzek> r0;
        java.lang.String r22;
        android.database.Cursor r5;
        com.google.android.gms.measurement.internal.zzas r7;
        com.google.android.gms.measurement.internal.zzw r65;
        java.util.Iterator r66;
        com.google.android.gms.measurement.internal.zzas r22;
        java.lang.String r24;
        android.database.Cursor r5;
        java.util.List r7;
        java.util.Iterator r65;
        java.lang.String r11;
        java.lang.String r9;
        java.util.Map r66;
        com.google.android.gms.internal.measurement.zzet r7;
        android.database.Cursor r5;
        android.database.Cursor r13;
        java.util.List r15;
        androidx.collection.ArrayMap r17;
        android.database.Cursor r5;
        java.util.List r12;
        java.lang.String r11 = "current_results";
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r65);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r66);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r67);
        r64.zza = r65;
        r64.zzb = new java.util.HashSet();
        r64.zzc = new androidx.collection.ArrayMap();
        r64.zzd = r68;
        r64.zze = r69;
        java.util.Iterator r0 = r66.iterator();
        while (true) {
            r12 = 0;
            r13 = 1;
            if (r0.hasNext()) {
                if ("_s".equals(((com.google.android.gms.internal.measurement.zzft) r0.next()).zzh())) {
                    r1 = true;
                    break;
                }
            } else {
                r1 = false;
                break;
            }
        }
        com.google.android.gms.internal.measurement.zznz.zzc();
        boolean r14 = r64.zzt.zzf().zzs(r64.zza, com.google.android.gms.measurement.internal.zzdu.zzW);
        com.google.android.gms.internal.measurement.zznz.zzc();
        boolean r15 = r64.zzt.zzf().zzs(r64.zza, com.google.android.gms.measurement.internal.zzdu.zzV);
        if (r1) {
            com.google.android.gms.measurement.internal.zzam r2 = r64.zzf.zzi();
            java.lang.String r3 = r64.zza;
            r2.zzW();
            r2.zzg();
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3);
            android.content.ContentValues r0 = new android.content.ContentValues();
            ?? r5 = "current_session_count";
            r0.put("current_session_count", (java.lang.Integer) 0);
            try {
                r5 = new java.lang.String[]{r3};
                r2.zzh().update("events", r0, "app_id = ?", r5);
                r5 = r5;
            } catch (android.database.sqlite.SQLiteException r0) {
                r2.zzt.zzay().zzd().zzc("Error resetting session-scoped event counts. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r3), r0);
                r5 = r5;
            }
        }
        java.util.Map r0 = java.util.Collections.emptyMap();
        java.lang.String r9 = "Failed to merge filter. appId";
        java.lang.String r8 = "Database error querying filters. appId";
        java.lang.String r7 = com.facebook.share.internal.ShareConstants.WEB_DIALOG_PARAM_DATA;
        java.lang.String r6 = "audience_id";
        try {
            if (r15 && r14) {
                com.google.android.gms.measurement.internal.zzam r2 = r64.zzf.zzi();
                java.lang.String r3 = r64.zza;
                com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3);
                androidx.collection.ArrayMap r4 = new androidx.collection.ArrayMap();
                try {
                    try {
                        r5 = r2.zzh().query("event_filters", new java.lang.String[]{"audience_id", com.facebook.share.internal.ShareConstants.WEB_DIALOG_PARAM_DATA}, "app_id=?", new java.lang.String[]{r3}, null, null, null);
                        try {
                        } catch (android.database.sqlite.SQLiteException e) {
                            r0 = e;
                            r2.zzt.zzay().zzd().zzc("Database error querying filters. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r3), r0);
                            r0 = java.util.Collections.emptyMap();
                        }
                    } catch (java.lang.Throwable th) {
                        r0 = th;
                        if (r5 != null) {
                            r5.close();
                        }
                        throw r0;
                    }
                } catch (android.database.sqlite.SQLiteException e) {
                    r0 = e;
                    r5 = null;
                } catch (java.lang.Throwable th) {
                    r0 = th;
                    r5 = null;
                    if (r5 != null) {
                    }
                    throw r0;
                }
                if (r5.moveToFirst()) {
                    while (true) {
                        try {
                            com.google.android.gms.internal.measurement.zzek r0 = (com.google.android.gms.internal.measurement.zzek) ((com.google.android.gms.internal.measurement.zzej) com.google.android.gms.measurement.internal.zzkv.zzl(com.google.android.gms.internal.measurement.zzek.zzc(), r5.getBlob(r13))).zzaC();
                            if (r0.zzo()) {
                                java.lang.Integer r13 = java.lang.Integer.valueOf(r5.getInt(r12));
                                java.util.List r16 = (java.util.List) r4.get(r13);
                                if (r16 == null) {
                                    r12 = new java.util.ArrayList();
                                    r4.put(r13, r12);
                                } else {
                                    r12 = r16;
                                }
                                r12.add(r0);
                            }
                        } catch (java.io.IOException r0) {
                            r2.zzt.zzay().zzd().zzc("Failed to merge filter. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r3), r0);
                        }
                        if (!r5.moveToNext()) {
                            break;
                        }
                        r12 = 0;
                        r13 = 1;
                    }
                    if (r5 != null) {
                        r5.close();
                    }
                    r12 = r4;
                    com.google.android.gms.measurement.internal.zzam r2 = r64.zzf.zzi();
                    java.lang.String r3 = r64.zza;
                    r2.zzW();
                    r2.zzg();
                    com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3);
                    r4 = r2.zzh().query("audience_filter_values", new java.lang.String[]{"audience_id", "current_results"}, "app_id=?", new java.lang.String[]{r3}, null, null, null);
                    if (r4.moveToFirst()) {
                        java.util.Map r0 = java.util.Collections.emptyMap();
                        if (r4 != null) {
                            r4.close();
                        }
                        r13 = r0;
                        r18 = "audience_id";
                        r19 = com.facebook.share.internal.ShareConstants.WEB_DIALOG_PARAM_DATA;
                    } else {
                        androidx.collection.ArrayMap r5 = new androidx.collection.ArrayMap();
                        while (true) {
                            int r16 = r4.getInt(0);
                            try {
                                r5.put(java.lang.Integer.valueOf(r16), (com.google.android.gms.internal.measurement.zzgi) ((com.google.android.gms.internal.measurement.zzgh) com.google.android.gms.measurement.internal.zzkv.zzl(com.google.android.gms.internal.measurement.zzgi.zzf(), r4.getBlob(1))).zzaC());
                                r17 = r5;
                                r18 = r6;
                                r19 = r7;
                            } catch (java.io.IOException r0) {
                                r17 = r5;
                                r18 = r6;
                                try {
                                    r19 = r7;
                                    try {
                                        r2.zzt.zzay().zzd().zzd("Failed to merge filter results. appId, audienceId, error", com.google.android.gms.measurement.internal.zzeh.zzn(r3), java.lang.Integer.valueOf(r16), r0);
                                    } catch (android.database.sqlite.SQLiteException e) {
                                        r0 = e;
                                        r2.zzt.zzay().zzd().zzc("Database error querying filter results. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r3), r0);
                                        java.util.Map r0 = java.util.Collections.emptyMap();
                                        if (r4 != null) {
                                            r4.close();
                                        }
                                        r13 = r0;
                                        if (r13.isEmpty()) {
                                        }
                                        if (!r66.isEmpty()) {
                                        }
                                        java.lang.String r24 = r11;
                                        if (!r67.isEmpty()) {
                                        }
                                        java.lang.String r11 = r28;
                                        java.util.ArrayList r1 = new java.util.ArrayList();
                                        java.util.Set<java.lang.Integer> r0 = r64.zzc.keySet();
                                        r0.removeAll(r64.zzb);
                                        while (r2.hasNext()) {
                                        }
                                        return r1;
                                    }
                                } catch (android.database.sqlite.SQLiteException e) {
                                    r0 = e;
                                    r19 = r7;
                                    r2.zzt.zzay().zzd().zzc("Database error querying filter results. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r3), r0);
                                    java.util.Map r0 = java.util.Collections.emptyMap();
                                    if (r4 != null) {
                                    }
                                    r13 = r0;
                                    if (r13.isEmpty()) {
                                    }
                                    if (!r66.isEmpty()) {
                                    }
                                    java.lang.String r24 = r11;
                                    if (!r67.isEmpty()) {
                                    }
                                    java.lang.String r11 = r28;
                                    java.util.ArrayList r1 = new java.util.ArrayList();
                                    java.util.Set<java.lang.Integer> r0 = r64.zzc.keySet();
                                    r0.removeAll(r64.zzb);
                                    while (r2.hasNext()) {
                                    }
                                    return r1;
                                }
                            }
                            if (!r4.moveToNext()) {
                                break;
                            }
                            r5 = r17;
                            r6 = r18;
                            r7 = r19;
                        }
                        if (r4 != null) {
                            r4.close();
                        }
                        r13 = r17;
                    }
                    if (r13.isEmpty()) {
                        java.util.HashSet<java.lang.Integer> r2 = new java.util.HashSet(r13.keySet());
                        if (r1) {
                            java.lang.String r1 = r64.zza;
                            com.google.android.gms.measurement.internal.zzam r3 = r64.zzf.zzi();
                            java.lang.String r4 = r64.zza;
                            r3.zzW();
                            r3.zzg();
                            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4);
                            java.util.Map r0 = new androidx.collection.ArrayMap();
                            ?? r5 = r3.zzh();
                            try {
                                try {
                                    r5 = r5.rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new java.lang.String[]{r4, r4});
                                    try {
                                        if (r5.moveToFirst()) {
                                            do {
                                                java.lang.Integer r6 = java.lang.Integer.valueOf(r5.getInt(0));
                                                java.util.List r7 = (java.util.List) r0.get(r6);
                                                if (r7 == null) {
                                                    r7 = new java.util.ArrayList();
                                                    r0.put(r6, r7);
                                                }
                                                r7.add(java.lang.Integer.valueOf(r5.getInt(1)));
                                            } while (r5.moveToNext());
                                        } else {
                                            r0 = java.util.Collections.emptyMap();
                                        }
                                    } catch (android.database.sqlite.SQLiteException e) {
                                        r0 = e;
                                        r3.zzt.zzay().zzd().zzc("Database error querying scoped filters. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r4), r0);
                                        r0 = java.util.Collections.emptyMap();
                                    }
                                } catch (java.lang.Throwable th) {
                                    r0 = th;
                                    if (r5 != 0) {
                                        r5.close();
                                    }
                                    throw r0;
                                }
                            } catch (android.database.sqlite.SQLiteException e) {
                                r0 = e;
                                r5 = null;
                            } catch (java.lang.Throwable th) {
                                r0 = th;
                                r5 = 0;
                                if (r5 != 0) {
                                }
                                throw r0;
                            }
                        } else {
                            r21 = "Database error querying filters. appId";
                            r0 = r13;
                        }
                        for (java.lang.Integer r1 : r2) {
                            int r20 = r1.intValue();
                            com.google.android.gms.internal.measurement.zzgi r1 = (com.google.android.gms.internal.measurement.zzgi) r0.get(java.lang.Integer.valueOf(r20));
                            java.util.BitSet r5 = new java.util.BitSet();
                            java.util.BitSet r6 = new java.util.BitSet();
                            androidx.collection.ArrayMap r7 = new androidx.collection.ArrayMap();
                            if (r1 != null && r1.zza() != 0) {
                                for (com.google.android.gms.internal.measurement.zzfr r3 : r1.zzj()) {
                                    if (r3.zzh()) {
                                        r7.put(java.lang.Integer.valueOf(r3.zza()), r3.zzg() ? java.lang.Long.valueOf(r3.zzb()) : null);
                                    }
                                }
                            }
                            androidx.collection.ArrayMap r8 = new androidx.collection.ArrayMap();
                            if (r1 != null && r1.zzc() != 0) {
                                for (com.google.android.gms.internal.measurement.zzgk r3 : r1.zzm()) {
                                    if (r3.zzi() && r3.zza() > 0) {
                                        r8.put(java.lang.Integer.valueOf(r3.zzb()), java.lang.Long.valueOf(r3.zzc(r3.zza() - 1)));
                                        r0 = r0;
                                    }
                                }
                            }
                            java.util.Map r23 = r0;
                            if (r1 != null) {
                                int r0 = 0;
                                while (r0 < r1.zzd() * 64) {
                                    if (com.google.android.gms.measurement.internal.zzkv.zzv(r1.zzn(), r0)) {
                                        r22 = r9;
                                        r64.zzt.zzay().zzj().zzc("Filter already evaluated. audience ID, filter ID", java.lang.Integer.valueOf(r20), java.lang.Integer.valueOf(r0));
                                        r6.set(r0);
                                        if (com.google.android.gms.measurement.internal.zzkv.zzv(r1.zzk(), r0)) {
                                            r5.set(r0);
                                            r0++;
                                            r9 = r22;
                                        }
                                    } else {
                                        r22 = r9;
                                    }
                                    r7.remove(java.lang.Integer.valueOf(r0));
                                    r0++;
                                    r9 = r22;
                                }
                            }
                            java.lang.String r22 = r9;
                            java.lang.Integer r0 = java.lang.Integer.valueOf(r20);
                            com.google.android.gms.internal.measurement.zzgi r4 = (com.google.android.gms.internal.measurement.zzgi) r13.get(r0);
                            if (r15 && r14 && (r0 = (java.util.List) r12.get(r0)) != null && r64.zze != null && r64.zzd != null) {
                                for (com.google.android.gms.internal.measurement.zzek r1 : r0) {
                                    int r2 = r1.zzb();
                                    long r24 = r64.zze.longValue() / 1000;
                                    if (r1.zzm()) {
                                        r24 = r64.zzd.longValue() / 1000;
                                    }
                                    java.lang.Integer r1 = java.lang.Integer.valueOf(r2);
                                    if (r7.containsKey(r1)) {
                                        r7.put(r1, java.lang.Long.valueOf(r24));
                                    }
                                    if (r8.containsKey(r1)) {
                                        r8.put(r1, java.lang.Long.valueOf(r24));
                                    }
                                }
                            }
                            r64.zzc.put(java.lang.Integer.valueOf(r20), new com.google.android.gms.measurement.internal.zzu(r64, r64.zza, r4, r5, r6, r7, r8, null));
                            r9 = r22;
                            r12 = r12;
                            r0 = r23;
                            r13 = r13;
                        }
                        r13 = r9;
                        r28 = r18;
                        r29 = r19;
                        r12 = r21;
                    } else {
                        r12 = "Database error querying filters. appId";
                        r13 = "Failed to merge filter. appId";
                        r28 = r18;
                        r29 = r19;
                    }
                    if (!r66.isEmpty()) {
                        com.google.android.gms.measurement.internal.zzw r2 = new com.google.android.gms.measurement.internal.zzw(r64, null);
                        androidx.collection.ArrayMap r4 = new androidx.collection.ArrayMap();
                        java.util.Iterator r5 = r66.iterator();
                        while (r5.hasNext()) {
                            com.google.android.gms.internal.measurement.zzft r0 = (com.google.android.gms.internal.measurement.zzft) r5.next();
                            com.google.android.gms.internal.measurement.zzft r6 = r2.zza(r64.zza, r0);
                            if (r6 != null) {
                                com.google.android.gms.measurement.internal.zzam r7 = r64.zzf.zzi();
                                java.lang.String r8 = r64.zza;
                                java.lang.String r9 = r6.zzh();
                                com.google.android.gms.measurement.internal.zzas r14 = r7.zzn(r8, r0.zzh());
                                if (r14 == null) {
                                    r7.zzt.zzay().zzk().zzc("Event aggregate wasn't created during raw event logging. appId, event", com.google.android.gms.measurement.internal.zzeh.zzn(r8), r7.zzt.zzj().zzd(r9));
                                    r7 = new com.google.android.gms.measurement.internal.zzas(r8, r0.zzh(), 1L, 1L, 1L, r0.zzd(), 0L, null, null, null, null);
                                } else {
                                    r7 = new com.google.android.gms.measurement.internal.zzas(r14.zza, r14.zzb, r14.zzc + 1, r14.zzd + 1, r14.zze + 1, r14.zzf, r14.zzg, r14.zzh, r14.zzi, r14.zzj, r14.zzk);
                                }
                                r64.zzf.zzi().zzE(r7);
                                long r8 = r7.zzc;
                                java.lang.String r14 = r6.zzh();
                                java.util.Map r0 = (java.util.Map) r4.get(r14);
                                if (r0 == null) {
                                    com.google.android.gms.measurement.internal.zzam r15 = r64.zzf.zzi();
                                    java.lang.String r3 = r64.zza;
                                    r15.zzW();
                                    r15.zzg();
                                    com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3);
                                    com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14);
                                    r65 = r2;
                                    androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap();
                                    r66 = r5;
                                    r24 = r11;
                                    java.lang.String r11 = r28;
                                    java.lang.String r5 = r29;
                                    try {
                                        try {
                                            r29 = r5;
                                        } catch (java.lang.Throwable th) {
                                            r0 = th;
                                            r5 = null;
                                        }
                                    } catch (android.database.sqlite.SQLiteException e) {
                                        r0 = e;
                                        r29 = r5;
                                    }
                                    try {
                                        r5 = r15.zzh().query("event_filters", new java.lang.String[]{r11, r5}, "app_id=? AND event_name=?", new java.lang.String[]{r3, r14}, null, null, null);
                                    } catch (android.database.sqlite.SQLiteException e) {
                                        r0 = e;
                                        r22 = r7;
                                        r28 = r11;
                                        r5 = null;
                                        r15.zzt.zzay().zzd().zzc(r12, com.google.android.gms.measurement.internal.zzeh.zzn(r3), r0);
                                        r0 = java.util.Collections.emptyMap();
                                    }
                                    try {
                                        try {
                                            if (r5.moveToFirst()) {
                                                r28 = r11;
                                                while (true) {
                                                    try {
                                                        try {
                                                            com.google.android.gms.internal.measurement.zzek r0 = (com.google.android.gms.internal.measurement.zzek) ((com.google.android.gms.internal.measurement.zzej) com.google.android.gms.measurement.internal.zzkv.zzl(com.google.android.gms.internal.measurement.zzek.zzc(), r5.getBlob(1))).zzaC();
                                                            java.lang.Integer r11 = java.lang.Integer.valueOf(r5.getInt(0));
                                                            java.util.List r16 = (java.util.List) r2.get(r11);
                                                            if (r16 == null) {
                                                                r22 = r7;
                                                                try {
                                                                    r7 = new java.util.ArrayList();
                                                                    r2.put(r11, r7);
                                                                } catch (android.database.sqlite.SQLiteException e) {
                                                                    r0 = e;
                                                                    r15.zzt.zzay().zzd().zzc(r12, com.google.android.gms.measurement.internal.zzeh.zzn(r3), r0);
                                                                    r0 = java.util.Collections.emptyMap();
                                                                }
                                                            } else {
                                                                r22 = r7;
                                                                r7 = r16;
                                                            }
                                                            r7.add(r0);
                                                        } catch (java.io.IOException r0) {
                                                            r22 = r7;
                                                            r15.zzt.zzay().zzd().zzc(r13, com.google.android.gms.measurement.internal.zzeh.zzn(r3), r0);
                                                        }
                                                        if (!r5.moveToNext()) {
                                                            break;
                                                        }
                                                        r7 = r22;
                                                    } catch (android.database.sqlite.SQLiteException e) {
                                                        r0 = e;
                                                        r22 = r7;
                                                    }
                                                }
                                                if (r5 != null) {
                                                    r5.close();
                                                }
                                                r0 = r2;
                                            } else {
                                                r22 = r7;
                                                r28 = r11;
                                                r0 = java.util.Collections.emptyMap();
                                            }
                                        } catch (android.database.sqlite.SQLiteException e) {
                                            r0 = e;
                                            r22 = r7;
                                            r28 = r11;
                                        }
                                        r4.put(r14, r0);
                                    } catch (java.lang.Throwable th) {
                                        r0 = th;
                                        if (r5 != null) {
                                            r5.close();
                                        }
                                        throw r0;
                                    }
                                } else {
                                    r65 = r2;
                                    r66 = r5;
                                    r22 = r7;
                                    r24 = r11;
                                }
                                for (java.lang.Integer r3 : r0.keySet()) {
                                    int r3 = r3.intValue();
                                    java.util.Set r5 = r64.zzb;
                                    java.lang.Integer r7 = java.lang.Integer.valueOf(r3);
                                    if (r5.contains(r7)) {
                                        r64.zzt.zzay().zzj().zzb("Skipping failed audience ID", r7);
                                    } else {
                                        java.util.Iterator r5 = ((java.util.List) r0.get(r7)).iterator();
                                        boolean r7 = true;
                                        while (true) {
                                            if (!r5.hasNext()) {
                                                break;
                                            }
                                            com.google.android.gms.internal.measurement.zzek r7 = (com.google.android.gms.internal.measurement.zzek) r5.next();
                                            com.google.android.gms.measurement.internal.zzx r11 = new com.google.android.gms.measurement.internal.zzx(r64, r64.zza, r3, r7);
                                            r7 = r11.zzd(r64.zzd, r64.zze, r6, r8, r22, zzf(r3, r7.zzb()));
                                            if (!r7) {
                                                r64.zzb.add(java.lang.Integer.valueOf(r3));
                                                break;
                                            }
                                            zzd(java.lang.Integer.valueOf(r3)).zzc(r11);
                                        }
                                        if (!r7) {
                                            r64.zzb.add(java.lang.Integer.valueOf(r3));
                                        }
                                    }
                                }
                                r2 = r65;
                                r5 = r66;
                                r11 = r24;
                            }
                        }
                    }
                    java.lang.String r24 = r11;
                    if (!r67.isEmpty()) {
                        androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap();
                        java.util.Iterator r3 = r67.iterator();
                        while (r3.hasNext()) {
                            com.google.android.gms.internal.measurement.zzgm r4 = (com.google.android.gms.internal.measurement.zzgm) r3.next();
                            java.lang.String r5 = r4.zzf();
                            java.util.Map r0 = (java.util.Map) r2.get(r5);
                            if (r0 == null) {
                                com.google.android.gms.measurement.internal.zzam r6 = r64.zzf.zzi();
                                java.lang.String r7 = r64.zza;
                                r6.zzW();
                                r6.zzg();
                                com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7);
                                com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5);
                                androidx.collection.ArrayMap r8 = new androidx.collection.ArrayMap();
                                r11 = r28;
                                r9 = r29;
                                try {
                                    r13 = r6.zzh().query("property_filters", new java.lang.String[]{r11, r9}, "app_id=? AND property_name=?", new java.lang.String[]{r7, r5}, null, null, null);
                                    try {
                                        try {
                                            if (r13.moveToFirst()) {
                                                while (true) {
                                                    try {
                                                        com.google.android.gms.internal.measurement.zzet r0 = (com.google.android.gms.internal.measurement.zzet) ((com.google.android.gms.internal.measurement.zzes) com.google.android.gms.measurement.internal.zzkv.zzl(com.google.android.gms.internal.measurement.zzet.zzc(), r13.getBlob(1))).zzaC();
                                                        java.lang.Integer r14 = java.lang.Integer.valueOf(r13.getInt(0));
                                                        java.util.List r16 = (java.util.List) r8.get(r14);
                                                        if (r16 == null) {
                                                            r15 = new java.util.ArrayList();
                                                            r8.put(r14, r15);
                                                        } else {
                                                            r15 = r16;
                                                        }
                                                        r15.add(r0);
                                                        r65 = r3;
                                                    } catch (java.io.IOException r0) {
                                                        r65 = r3;
                                                        try {
                                                            r6.zzt.zzay().zzd().zzc("Failed to merge filter", com.google.android.gms.measurement.internal.zzeh.zzn(r7), r0);
                                                        } catch (android.database.sqlite.SQLiteException e) {
                                                            r0 = e;
                                                            r6.zzt.zzay().zzd().zzc(r12, com.google.android.gms.measurement.internal.zzeh.zzn(r7), r0);
                                                            r0 = java.util.Collections.emptyMap();
                                                        }
                                                    }
                                                    if (!r13.moveToNext()) {
                                                        break;
                                                    }
                                                    r3 = r65;
                                                }
                                                if (r13 != null) {
                                                    r13.close();
                                                }
                                                r0 = r8;
                                            } else {
                                                r65 = r3;
                                                r0 = java.util.Collections.emptyMap();
                                            }
                                        } catch (java.lang.Throwable th) {
                                            r0 = th;
                                            r5 = r13;
                                            if (r5 != null) {
                                                r5.close();
                                            }
                                            throw r0;
                                        }
                                    } catch (android.database.sqlite.SQLiteException e) {
                                        r0 = e;
                                        r65 = r3;
                                    }
                                } catch (android.database.sqlite.SQLiteException e) {
                                    r0 = e;
                                    r65 = r3;
                                    r13 = null;
                                } catch (java.lang.Throwable th) {
                                    r0 = th;
                                    r5 = null;
                                }
                                r2.put(r5, r0);
                            } else {
                                r65 = r3;
                                r11 = r28;
                                r9 = r29;
                            }
                            java.util.Iterator r3 = r0.keySet().iterator();
                            while (true) {
                                if (r3.hasNext()) {
                                    int r5 = ((java.lang.Integer) r3.next()).intValue();
                                    java.util.Set r6 = r64.zzb;
                                    java.lang.Integer r7 = java.lang.Integer.valueOf(r5);
                                    if (r6.contains(r7)) {
                                        r64.zzt.zzay().zzj().zzb("Skipping failed audience ID", r7);
                                        break;
                                    }
                                    java.util.Iterator r6 = ((java.util.List) r0.get(r7)).iterator();
                                    boolean r7 = true;
                                    while (true) {
                                        if (!r6.hasNext()) {
                                            r66 = r0;
                                            break;
                                        }
                                        r7 = (com.google.android.gms.internal.measurement.zzet) r6.next();
                                        if (android.util.Log.isLoggable(r64.zzt.zzay().zzq(), 2)) {
                                            r66 = r0;
                                            r64.zzt.zzay().zzj().zzd("Evaluating filter. audience, filter, property", java.lang.Integer.valueOf(r5), r7.zzj() ? java.lang.Integer.valueOf(r7.zza()) : null, r64.zzt.zzj().zzf(r7.zze()));
                                            r64.zzt.zzay().zzj().zzb("Filter definition", r64.zzf.zzu().zzp(r7));
                                        } else {
                                            r66 = r0;
                                        }
                                        if (!r7.zzj() || r7.zza() > 256) {
                                            break;
                                        }
                                        com.google.android.gms.measurement.internal.zzz r0 = new com.google.android.gms.measurement.internal.zzz(r64, r64.zza, r5, r7);
                                        r7 = r0.zzd(r64.zzd, r64.zze, r4, zzf(r5, r7.zza()));
                                        if (!r7) {
                                            r64.zzb.add(java.lang.Integer.valueOf(r5));
                                            break;
                                        }
                                        zzd(java.lang.Integer.valueOf(r5)).zzc(r0);
                                        r0 = r66;
                                    }
                                    r64.zzt.zzay().zzk().zzc("Invalid property filter ID. appId, id", com.google.android.gms.measurement.internal.zzeh.zzn(r64.zza), java.lang.String.valueOf(r7.zzj() ? java.lang.Integer.valueOf(r7.zza()) : null));
                                    r64.zzb.add(java.lang.Integer.valueOf(r5));
                                    r0 = r66;
                                }
                            }
                            r3 = r65;
                            r29 = r9;
                            r28 = r11;
                        }
                    }
                    java.lang.String r11 = r28;
                    java.util.ArrayList r1 = new java.util.ArrayList();
                    java.util.Set<java.lang.Integer> r0 = r64.zzc.keySet();
                    r0.removeAll(r64.zzb);
                    for (java.lang.Integer r0 : r0) {
                        int r0 = r0.intValue();
                        java.util.Map r3 = r64.zzc;
                        java.lang.Integer r4 = java.lang.Integer.valueOf(r0);
                        com.google.android.gms.measurement.internal.zzu r3 = (com.google.android.gms.measurement.internal.zzu) r3.get(r4);
                        com.google.android.gms.common.internal.Preconditions.checkNotNull(r3);
                        com.google.android.gms.internal.measurement.zzfp r0 = r3.zza(r0);
                        r1.add(r0);
                        com.google.android.gms.measurement.internal.zzam r3 = r64.zzf.zzi();
                        java.lang.String r5 = r64.zza;
                        com.google.android.gms.internal.measurement.zzgi r0 = r0.zzd();
                        r3.zzW();
                        r3.zzg();
                        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5);
                        com.google.android.gms.common.internal.Preconditions.checkNotNull(r0);
                        byte[] r0 = r0.zzbu();
                        android.content.ContentValues r6 = new android.content.ContentValues();
                        r6.put("app_id", r5);
                        r6.put(r11, r4);
                        java.lang.String r4 = r24;
                        r6.put(r4, r0);
                        try {
                        } catch (android.database.sqlite.SQLiteException e) {
                            r0 = e;
                        }
                        try {
                            if (r3.zzh().insertWithOnConflict("audience_filter_values", null, r6, 5) == -1) {
                                r3.zzt.zzay().zzd().zzb("Failed to insert filter results (got -1). appId", com.google.android.gms.measurement.internal.zzeh.zzn(r5));
                            }
                        } catch (android.database.sqlite.SQLiteException e) {
                            r0 = e;
                            r3.zzt.zzay().zzd().zzc("Error storing filter results. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r5), r0);
                            r24 = r4;
                        }
                        r24 = r4;
                    }
                    return r1;
                }
                r0 = java.util.Collections.emptyMap();
            }
            if (r4.moveToFirst()) {
            }
            if (r13.isEmpty()) {
            }
            if (!r66.isEmpty()) {
            }
            java.lang.String r24 = r11;
            if (!r67.isEmpty()) {
            }
            java.lang.String r11 = r28;
            java.util.ArrayList r1 = new java.util.ArrayList();
            java.util.Set<java.lang.Integer> r0 = r64.zzc.keySet();
            r0.removeAll(r64.zzb);
            while (r2.hasNext()) {
            }
            return r1;
        } catch (java.lang.Throwable th) {
            r0 = th;
            android.database.Cursor r5 = r4;
            if (r5 != null) {
                r5.close();
            }
            throw r0;
        }
        r12 = r0;
        com.google.android.gms.measurement.internal.zzam r2 = r64.zzf.zzi();
        java.lang.String r3 = r64.zza;
        r2.zzW();
        r2.zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3);
        r4 = r2.zzh().query("audience_filter_values", new java.lang.String[]{"audience_id", "current_results"}, "app_id=?", new java.lang.String[]{r3}, null, null, null);
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    protected final boolean zzb() {
        return false;
    }
}
