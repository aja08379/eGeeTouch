package com.google.android.gms.internal.clearcut;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zzqk uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes.dex */
public class zzfl {
    public static final zzfl zzqc;
    public static final zzfl zzqd;
    public static final zzfl zzqe;
    public static final zzfl zzqf;
    public static final zzfl zzqg;
    public static final zzfl zzqh;
    public static final zzfl zzqi;
    public static final zzfl zzqj;
    public static final zzfl zzqk;
    public static final zzfl zzql;
    public static final zzfl zzqm;
    public static final zzfl zzqn;
    public static final zzfl zzqo;
    public static final zzfl zzqp;
    public static final zzfl zzqq;
    public static final zzfl zzqr;
    public static final zzfl zzqs;
    public static final zzfl zzqt;
    private static final /* synthetic */ zzfl[] zzqw;
    private final zzfq zzqu;
    private final int zzqv;

    static {
        zzfl zzflVar = new zzfl("DOUBLE", 0, zzfq.DOUBLE, 1);
        zzqc = zzflVar;
        zzfl zzflVar2 = new zzfl("FLOAT", 1, zzfq.FLOAT, 5);
        zzqd = zzflVar2;
        zzfl zzflVar3 = new zzfl("INT64", 2, zzfq.LONG, 0);
        zzqe = zzflVar3;
        zzfl zzflVar4 = new zzfl("UINT64", 3, zzfq.LONG, 0);
        zzqf = zzflVar4;
        zzfl zzflVar5 = new zzfl("INT32", 4, zzfq.INT, 0);
        zzqg = zzflVar5;
        zzfl zzflVar6 = new zzfl("FIXED64", 5, zzfq.LONG, 1);
        zzqh = zzflVar6;
        zzfl zzflVar7 = new zzfl("FIXED32", 6, zzfq.INT, 5);
        zzqi = zzflVar7;
        zzfl zzflVar8 = new zzfl("BOOL", 7, zzfq.BOOLEAN, 0);
        zzqj = zzflVar8;
        final zzfq zzfqVar = zzfq.STRING;
        zzfl zzflVar9 = new zzfl("STRING", 8, zzfqVar, 2) { // from class: com.google.android.gms.internal.clearcut.zzfm
        };
        zzqk = zzflVar9;
        final zzfq zzfqVar2 = zzfq.MESSAGE;
        zzfl zzflVar10 = new zzfl("GROUP", 9, zzfqVar2, 3) { // from class: com.google.android.gms.internal.clearcut.zzfn
        };
        zzql = zzflVar10;
        final zzfq zzfqVar3 = zzfq.MESSAGE;
        zzfl zzflVar11 = new zzfl("MESSAGE", 10, zzfqVar3, 2) { // from class: com.google.android.gms.internal.clearcut.zzfo
        };
        zzqm = zzflVar11;
        final zzfq zzfqVar4 = zzfq.BYTE_STRING;
        zzfl zzflVar12 = new zzfl("BYTES", 11, zzfqVar4, 2) { // from class: com.google.android.gms.internal.clearcut.zzfp
        };
        zzqn = zzflVar12;
        zzfl zzflVar13 = new zzfl("UINT32", 12, zzfq.INT, 0);
        zzqo = zzflVar13;
        zzfl zzflVar14 = new zzfl("ENUM", 13, zzfq.ENUM, 0);
        zzqp = zzflVar14;
        zzfl zzflVar15 = new zzfl("SFIXED32", 14, zzfq.INT, 5);
        zzqq = zzflVar15;
        zzfl zzflVar16 = new zzfl("SFIXED64", 15, zzfq.LONG, 1);
        zzqr = zzflVar16;
        zzfl zzflVar17 = new zzfl("SINT32", 16, zzfq.INT, 0);
        zzqs = zzflVar17;
        zzfl zzflVar18 = new zzfl("SINT64", 17, zzfq.LONG, 0);
        zzqt = zzflVar18;
        zzqw = new zzfl[]{zzflVar, zzflVar2, zzflVar3, zzflVar4, zzflVar5, zzflVar6, zzflVar7, zzflVar8, zzflVar9, zzflVar10, zzflVar11, zzflVar12, zzflVar13, zzflVar14, zzflVar15, zzflVar16, zzflVar17, zzflVar18};
    }

    private zzfl(String str, int i, zzfq zzfqVar, int i2) {
        this.zzqu = zzfqVar;
        this.zzqv = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfl(String str, int i, zzfq zzfqVar, int i2, zzfk zzfkVar) {
        this(str, i, zzfqVar, i2);
    }

    public static zzfl[] values() {
        return (zzfl[]) zzqw.clone();
    }

    public final zzfq zzek() {
        return this.zzqu;
    }

    public final int zzel() {
        return this.zzqv;
    }
}
