package com.google.android.gms.internal.vision;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zzi uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
public class zzml {
    public static final zzml zza;
    public static final zzml zzb;
    public static final zzml zzc;
    public static final zzml zzd;
    public static final zzml zze;
    public static final zzml zzf;
    public static final zzml zzg;
    public static final zzml zzh;
    public static final zzml zzi;
    public static final zzml zzj;
    public static final zzml zzk;
    public static final zzml zzl;
    public static final zzml zzm;
    public static final zzml zzn;
    public static final zzml zzo;
    public static final zzml zzp;
    public static final zzml zzq;
    public static final zzml zzr;
    private static final /* synthetic */ zzml[] zzu;
    private final zzmo zzs;
    private final int zzt;

    public static zzml[] values() {
        return (zzml[]) zzu.clone();
    }

    private zzml(String str, int i, zzmo zzmoVar, int i2) {
        this.zzs = zzmoVar;
        this.zzt = i2;
    }

    public final zzmo zza() {
        return this.zzs;
    }

    public final int zzb() {
        return this.zzt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzml(String str, int i, zzmo zzmoVar, int i2, zzmi zzmiVar) {
        this(str, i, zzmoVar, i2);
    }

    static {
        zzml zzmlVar = new zzml("DOUBLE", 0, zzmo.DOUBLE, 1);
        zza = zzmlVar;
        zzml zzmlVar2 = new zzml("FLOAT", 1, zzmo.FLOAT, 5);
        zzb = zzmlVar2;
        zzml zzmlVar3 = new zzml("INT64", 2, zzmo.LONG, 0);
        zzc = zzmlVar3;
        zzml zzmlVar4 = new zzml("UINT64", 3, zzmo.LONG, 0);
        zzd = zzmlVar4;
        zzml zzmlVar5 = new zzml("INT32", 4, zzmo.INT, 0);
        zze = zzmlVar5;
        zzml zzmlVar6 = new zzml("FIXED64", 5, zzmo.LONG, 1);
        zzf = zzmlVar6;
        zzml zzmlVar7 = new zzml("FIXED32", 6, zzmo.INT, 5);
        zzg = zzmlVar7;
        zzml zzmlVar8 = new zzml("BOOL", 7, zzmo.BOOLEAN, 0);
        zzh = zzmlVar8;
        final zzmo zzmoVar = zzmo.STRING;
        zzml zzmlVar9 = new zzml("STRING", 8, zzmoVar, 2) { // from class: com.google.android.gms.internal.vision.zzmk
        };
        zzi = zzmlVar9;
        final zzmo zzmoVar2 = zzmo.MESSAGE;
        zzml zzmlVar10 = new zzml("GROUP", 9, zzmoVar2, 3) { // from class: com.google.android.gms.internal.vision.zzmn
        };
        zzj = zzmlVar10;
        final zzmo zzmoVar3 = zzmo.MESSAGE;
        zzml zzmlVar11 = new zzml("MESSAGE", 10, zzmoVar3, 2) { // from class: com.google.android.gms.internal.vision.zzmm
        };
        zzk = zzmlVar11;
        final zzmo zzmoVar4 = zzmo.BYTE_STRING;
        zzml zzmlVar12 = new zzml("BYTES", 11, zzmoVar4, 2) { // from class: com.google.android.gms.internal.vision.zzmp
        };
        zzl = zzmlVar12;
        zzml zzmlVar13 = new zzml("UINT32", 12, zzmo.INT, 0);
        zzm = zzmlVar13;
        zzml zzmlVar14 = new zzml("ENUM", 13, zzmo.ENUM, 0);
        zzn = zzmlVar14;
        zzml zzmlVar15 = new zzml("SFIXED32", 14, zzmo.INT, 5);
        zzo = zzmlVar15;
        zzml zzmlVar16 = new zzml("SFIXED64", 15, zzmo.LONG, 1);
        zzp = zzmlVar16;
        zzml zzmlVar17 = new zzml("SINT32", 16, zzmo.INT, 0);
        zzq = zzmlVar17;
        zzml zzmlVar18 = new zzml("SINT64", 17, zzmo.LONG, 0);
        zzr = zzmlVar18;
        zzu = new zzml[]{zzmlVar, zzmlVar2, zzmlVar3, zzmlVar4, zzmlVar5, zzmlVar6, zzmlVar7, zzmlVar8, zzmlVar9, zzmlVar10, zzmlVar11, zzmlVar12, zzmlVar13, zzmlVar14, zzmlVar15, zzmlVar16, zzmlVar17, zzmlVar18};
    }
}
