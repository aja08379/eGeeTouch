package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;
/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes.dex */
public final class zzaz extends zzaw {
    /* JADX INFO: Access modifiers changed from: protected */
    public zzaz() {
        this.zza.add(zzbl.APPLY);
        this.zza.add(zzbl.BLOCK);
        this.zza.add(zzbl.BREAK);
        this.zza.add(zzbl.CASE);
        this.zza.add(zzbl.DEFAULT);
        this.zza.add(zzbl.CONTINUE);
        this.zza.add(zzbl.DEFINE_FUNCTION);
        this.zza.add(zzbl.FN);
        this.zza.add(zzbl.IF);
        this.zza.add(zzbl.QUOTE);
        this.zza.add(zzbl.RETURN);
        this.zza.add(zzbl.SWITCH);
        this.zza.add(zzbl.TERNARY);
    }

    private static zzap zzc(zzg zzgVar, List list) {
        zzh.zzi(zzbl.FN.name(), 2, list);
        zzap zzb = zzgVar.zzb((zzap) list.get(0));
        zzap zzb2 = zzgVar.zzb((zzap) list.get(1));
        if (!(zzb2 instanceof zzae)) {
            throw new IllegalArgumentException(String.format("FN requires an ArrayValue of parameter names found %s", zzb2.getClass().getCanonicalName()));
        }
        List zzm = ((zzae) zzb2).zzm();
        List arrayList = new ArrayList();
        if (list.size() > 2) {
            arrayList = list.subList(2, list.size());
        }
        return new zzao(zzb.zzi(), zzm, arrayList, zzgVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x0129, code lost:
        if (r8.equals("continue") == false) goto L67;
     */
    @Override // com.google.android.gms.internal.measurement.zzaw
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.measurement.zzap zza(java.lang.String r8, com.google.android.gms.internal.measurement.zzg r9, java.util.List r10) {
        com.google.android.gms.internal.measurement.zzap r2;
        com.google.android.gms.internal.measurement.zzbl r0 = com.google.android.gms.internal.measurement.zzbl.ADD;
        int r0 = com.google.android.gms.internal.measurement.zzh.zze(r8).ordinal();
        if (r0 == 2) {
            com.google.android.gms.internal.measurement.zzh.zzh(com.google.android.gms.internal.measurement.zzbl.APPLY.name(), 3, r10);
            com.google.android.gms.internal.measurement.zzap r8 = r9.zzb((com.google.android.gms.internal.measurement.zzap) r10.get(0));
            java.lang.String r0 = r9.zzb((com.google.android.gms.internal.measurement.zzap) r10.get(1)).zzi();
            com.google.android.gms.internal.measurement.zzap r10 = r9.zzb((com.google.android.gms.internal.measurement.zzap) r10.get(2));
            if (!(r10 instanceof com.google.android.gms.internal.measurement.zzae)) {
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Function arguments for Apply are not a list found %s", r10.getClass().getCanonicalName()));
            }
            if (r0.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Function name for apply is undefined");
            }
            return r8.zzbR(r0, r9, ((com.google.android.gms.internal.measurement.zzae) r10).zzm());
        } else if (r0 == 15) {
            com.google.android.gms.internal.measurement.zzh.zzh(com.google.android.gms.internal.measurement.zzbl.BREAK.name(), 0, r10);
            return com.google.android.gms.internal.measurement.zzap.zzh;
        } else if (r0 != 25) {
            if (r0 == 41) {
                com.google.android.gms.internal.measurement.zzh.zzi(com.google.android.gms.internal.measurement.zzbl.IF.name(), 2, r10);
                com.google.android.gms.internal.measurement.zzap r8 = r9.zzb((com.google.android.gms.internal.measurement.zzap) r10.get(0));
                com.google.android.gms.internal.measurement.zzap r0 = r9.zzb((com.google.android.gms.internal.measurement.zzap) r10.get(1));
                com.google.android.gms.internal.measurement.zzap r10 = r10.size() > 2 ? r9.zzb((com.google.android.gms.internal.measurement.zzap) r10.get(2)) : null;
                com.google.android.gms.internal.measurement.zzap r1 = com.google.android.gms.internal.measurement.zzap.zzf;
                if (r8.zzg().booleanValue()) {
                    r1 = r9.zzc((com.google.android.gms.internal.measurement.zzae) r0);
                } else if (r10 != null) {
                    r1 = r9.zzc((com.google.android.gms.internal.measurement.zzae) r10);
                }
                return r1 instanceof com.google.android.gms.internal.measurement.zzag ? r1 : com.google.android.gms.internal.measurement.zzap.zzf;
            } else if (r0 != 54) {
                if (r0 != 57) {
                    if (r0 != 19) {
                        if (r0 == 20) {
                            com.google.android.gms.internal.measurement.zzh.zzi(com.google.android.gms.internal.measurement.zzbl.DEFINE_FUNCTION.name(), 2, r10);
                            com.google.android.gms.internal.measurement.zzap r8 = zzc(r9, r10);
                            com.google.android.gms.internal.measurement.zzai r10 = (com.google.android.gms.internal.measurement.zzai) r8;
                            if (r10.zzc() == null) {
                                r9.zzg("", r8);
                            } else {
                                r9.zzg(r10.zzc(), r8);
                            }
                            return r8;
                        } else if (r0 == 60) {
                            com.google.android.gms.internal.measurement.zzh.zzh(com.google.android.gms.internal.measurement.zzbl.SWITCH.name(), 3, r10);
                            com.google.android.gms.internal.measurement.zzap r8 = r9.zzb((com.google.android.gms.internal.measurement.zzap) r10.get(0));
                            com.google.android.gms.internal.measurement.zzap r0 = r9.zzb((com.google.android.gms.internal.measurement.zzap) r10.get(1));
                            com.google.android.gms.internal.measurement.zzap r10 = r9.zzb((com.google.android.gms.internal.measurement.zzap) r10.get(2));
                            if (!(r0 instanceof com.google.android.gms.internal.measurement.zzae)) {
                                throw new java.lang.IllegalArgumentException("Malformed SWITCH statement, cases are not a list");
                            }
                            if (!(r10 instanceof com.google.android.gms.internal.measurement.zzae)) {
                                throw new java.lang.IllegalArgumentException("Malformed SWITCH statement, case statements are not a list");
                            }
                            com.google.android.gms.internal.measurement.zzae r0 = (com.google.android.gms.internal.measurement.zzae) r0;
                            com.google.android.gms.internal.measurement.zzae r10 = (com.google.android.gms.internal.measurement.zzae) r10;
                            int r1 = 0;
                            boolean r2 = false;
                            while (true) {
                                if (r1 < r0.zzc()) {
                                    if (r2 || r8.equals(r9.zzb(r0.zze(r1)))) {
                                        r2 = r9.zzb(r10.zze(r1));
                                        if (!(r2 instanceof com.google.android.gms.internal.measurement.zzag)) {
                                            r2 = true;
                                        } else if (((com.google.android.gms.internal.measurement.zzag) r2).zzc().equals("break")) {
                                            return com.google.android.gms.internal.measurement.zzap.zzf;
                                        }
                                    } else {
                                        r2 = false;
                                    }
                                    r1++;
                                } else {
                                    if (r0.zzc() + 1 == r10.zzc()) {
                                        r2 = r9.zzb(r10.zze(r0.zzc()));
                                        if (r2 instanceof com.google.android.gms.internal.measurement.zzag) {
                                            java.lang.String r8 = ((com.google.android.gms.internal.measurement.zzag) r2).zzc();
                                            if (!r8.equals("return")) {
                                            }
                                        }
                                    }
                                    return com.google.android.gms.internal.measurement.zzap.zzf;
                                }
                            }
                            return r2;
                        } else if (r0 != 61) {
                            switch (r0) {
                                case 11:
                                    return r9.zza().zzc(new com.google.android.gms.internal.measurement.zzae(r10));
                                case 12:
                                    com.google.android.gms.internal.measurement.zzh.zzh(com.google.android.gms.internal.measurement.zzbl.BREAK.name(), 0, r10);
                                    return com.google.android.gms.internal.measurement.zzap.zzi;
                                case 13:
                                    break;
                                default:
                                    return super.zzb(r8);
                            }
                        } else {
                            com.google.android.gms.internal.measurement.zzh.zzh(com.google.android.gms.internal.measurement.zzbl.TERNARY.name(), 3, r10);
                            if (r9.zzb((com.google.android.gms.internal.measurement.zzap) r10.get(0)).zzg().booleanValue()) {
                                return r9.zzb((com.google.android.gms.internal.measurement.zzap) r10.get(1));
                            }
                            return r9.zzb((com.google.android.gms.internal.measurement.zzap) r10.get(2));
                        }
                    }
                    if (r10.isEmpty()) {
                        return com.google.android.gms.internal.measurement.zzap.zzf;
                    }
                    com.google.android.gms.internal.measurement.zzap r8 = r9.zzb((com.google.android.gms.internal.measurement.zzap) r10.get(0));
                    if (r8 instanceof com.google.android.gms.internal.measurement.zzae) {
                        return r9.zzc((com.google.android.gms.internal.measurement.zzae) r8);
                    }
                    return com.google.android.gms.internal.measurement.zzap.zzf;
                } else if (r10.isEmpty()) {
                    return com.google.android.gms.internal.measurement.zzap.zzj;
                } else {
                    com.google.android.gms.internal.measurement.zzh.zzh(com.google.android.gms.internal.measurement.zzbl.RETURN.name(), 1, r10);
                    return new com.google.android.gms.internal.measurement.zzag("return", r9.zzb((com.google.android.gms.internal.measurement.zzap) r10.get(0)));
                }
            } else {
                return new com.google.android.gms.internal.measurement.zzae(r10);
            }
        } else {
            return zzc(r9, r10);
        }
    }
}
