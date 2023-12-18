package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzach  reason: invalid package */
/* loaded from: classes.dex */
public final class zzach implements zzaev {
    private final zzacg zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    private zzach(zzacg zzacgVar) {
        zzadl.zzf(zzacgVar, "input");
        this.zza = zzacgVar;
        zzacgVar.zzc = this;
    }

    private final void zzP(Object obj, zzaew zzaewVar, zzacs zzacsVar) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            zzaewVar.zzh(obj, this, zzacsVar);
            if (this.zzb == this.zzc) {
                return;
            }
            throw zzadn.zzg();
        } finally {
            this.zzc = i;
        }
    }

    private final void zzQ(Object obj, zzaew zzaewVar, zzacs zzacsVar) throws IOException {
        zzacg zzacgVar;
        int zze = ((zzace) this.zza).zze();
        zzacg zzacgVar2 = this.zza;
        if (zzacgVar2.zza >= zzacgVar2.zzb) {
            throw new zzadn("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int zzc = zzacgVar2.zzc(zze);
        this.zza.zza++;
        zzaewVar.zzh(obj, this, zzacsVar);
        this.zza.zzm(0);
        zzacgVar.zza--;
        this.zza.zzn(zzc);
    }

    private final void zzR(int i) throws IOException {
        if (this.zza.zzb() != i) {
            throw zzadn.zzi();
        }
    }

    private final void zzS(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzadn.zza();
        }
    }

    private static final void zzT(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzadn.zzg();
        }
    }

    private static final void zzU(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzadn.zzg();
        }
    }

    public static zzach zzq(zzacg zzacgVar) {
        zzach zzachVar = zzacgVar.zzc;
        return zzachVar != null ? zzachVar : new zzach(zzacgVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzA(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadz) {
            zzadz zzadzVar = (zzadz) list;
            int i = this.zzb & 7;
            if (i != 1) {
                if (i == 2) {
                    int zze = ((zzace) this.zza).zze();
                    zzU(zze);
                    int zzb = this.zza.zzb() + zze;
                    do {
                        zzadzVar.zzf(((zzace) this.zza).zzg());
                    } while (this.zza.zzb() < zzb);
                    return;
                }
                throw zzadn.zza();
            }
            do {
                zzadzVar.zzf(((zzace) this.zza).zzg());
                zzacg zzacgVar = this.zza;
                if (zzacgVar.zzp()) {
                    return;
                }
                zzf2 = zzacgVar.zzf();
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 1) {
            if (i2 == 2) {
                int zze2 = ((zzace) this.zza).zze();
                zzU(zze2);
                int zzb2 = this.zza.zzb() + zze2;
                do {
                    list.add(Long.valueOf(((zzace) this.zza).zzg()));
                } while (this.zza.zzb() < zzb2);
                return;
            }
            throw zzadn.zza();
        }
        do {
            list.add(Long.valueOf(((zzace) this.zza).zzg()));
            zzacg zzacgVar2 = this.zza;
            if (zzacgVar2.zzp()) {
                return;
            }
            zzf = zzacgVar2.zzf();
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzB(List list) throws IOException {
        int zzf;
        int zzf2;
        if (!(list instanceof zzacz)) {
            int i = this.zzb & 7;
            if (i == 2) {
                int zze = ((zzace) this.zza).zze();
                zzT(zze);
                int zzb = this.zza.zzb() + zze;
                do {
                    list.add(Float.valueOf(Float.intBitsToFloat(((zzace) this.zza).zzd())));
                } while (this.zza.zzb() < zzb);
                return;
            } else if (i != 5) {
                throw zzadn.zza();
            } else {
                do {
                    list.add(Float.valueOf(Float.intBitsToFloat(((zzace) this.zza).zzd())));
                    zzacg zzacgVar = this.zza;
                    if (zzacgVar.zzp()) {
                        return;
                    }
                    zzf = zzacgVar.zzf();
                } while (zzf == this.zzb);
                this.zzd = zzf;
                return;
            }
        }
        zzacz zzaczVar = (zzacz) list;
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int zze2 = ((zzace) this.zza).zze();
            zzT(zze2);
            int zzb2 = this.zza.zzb() + zze2;
            do {
                zzaczVar.zze(Float.intBitsToFloat(((zzace) this.zza).zzd()));
            } while (this.zza.zzb() < zzb2);
        } else if (i2 != 5) {
            throw zzadn.zza();
        } else {
            do {
                zzaczVar.zze(Float.intBitsToFloat(((zzace) this.zza).zzd()));
                zzacg zzacgVar2 = this.zza;
                if (zzacgVar2.zzp()) {
                    return;
                }
                zzf2 = zzacgVar2.zzf();
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    @Deprecated
    public final void zzC(List list, zzaew zzaewVar, zzacs zzacsVar) throws IOException {
        int zzf;
        int i = this.zzb;
        if ((i & 7) != 3) {
            throw zzadn.zza();
        }
        do {
            Object zze = zzaewVar.zze();
            zzP(zze, zzaewVar, zzacsVar);
            zzaewVar.zzf(zze);
            list.add(zze);
            zzacg zzacgVar = this.zza;
            if (zzacgVar.zzp() || this.zzd != 0) {
                return;
            }
            zzf = zzacgVar.zzf();
        } while (zzf == i);
        this.zzd = zzf;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzD(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadg) {
            zzadg zzadgVar = (zzadg) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzb = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzadgVar.zzf(((zzace) this.zza).zze());
                    } while (this.zza.zzb() < zzb);
                    zzR(zzb);
                    return;
                }
                throw zzadn.zza();
            }
            do {
                zzadgVar.zzf(((zzace) this.zza).zze());
                zzacg zzacgVar = this.zza;
                if (zzacgVar.zzp()) {
                    return;
                }
                zzf2 = zzacgVar.zzf();
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Integer.valueOf(((zzace) this.zza).zze()));
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
                return;
            }
            throw zzadn.zza();
        }
        do {
            list.add(Integer.valueOf(((zzace) this.zza).zze()));
            zzacg zzacgVar2 = this.zza;
            if (zzacgVar2.zzp()) {
                return;
            }
            zzf = zzacgVar2.zzf();
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzE(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadz) {
            zzadz zzadzVar = (zzadz) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzb = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzadzVar.zzf(((zzace) this.zza).zzh());
                    } while (this.zza.zzb() < zzb);
                    zzR(zzb);
                    return;
                }
                throw zzadn.zza();
            }
            do {
                zzadzVar.zzf(((zzace) this.zza).zzh());
                zzacg zzacgVar = this.zza;
                if (zzacgVar.zzp()) {
                    return;
                }
                zzf2 = zzacgVar.zzf();
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Long.valueOf(((zzace) this.zza).zzh()));
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
                return;
            }
            throw zzadn.zza();
        }
        do {
            list.add(Long.valueOf(((zzace) this.zza).zzh()));
            zzacg zzacgVar2 = this.zza;
            if (zzacgVar2.zzp()) {
                return;
            }
            zzf = zzacgVar2.zzf();
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzF(List list, zzaew zzaewVar, zzacs zzacsVar) throws IOException {
        int zzf;
        int i = this.zzb;
        if ((i & 7) != 2) {
            throw zzadn.zza();
        }
        do {
            Object zze = zzaewVar.zze();
            zzQ(zze, zzaewVar, zzacsVar);
            zzaewVar.zzf(zze);
            list.add(zze);
            zzacg zzacgVar = this.zza;
            if (zzacgVar.zzp() || this.zzd != 0) {
                return;
            }
            zzf = zzacgVar.zzf();
        } while (zzf == i);
        this.zzd = zzf;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzG(List list) throws IOException {
        int zzf;
        int zzf2;
        if (!(list instanceof zzadg)) {
            int i = this.zzb & 7;
            if (i == 2) {
                int zze = ((zzace) this.zza).zze();
                zzT(zze);
                int zzb = this.zza.zzb() + zze;
                do {
                    list.add(Integer.valueOf(((zzace) this.zza).zzd()));
                } while (this.zza.zzb() < zzb);
                return;
            } else if (i != 5) {
                throw zzadn.zza();
            } else {
                do {
                    list.add(Integer.valueOf(((zzace) this.zza).zzd()));
                    zzacg zzacgVar = this.zza;
                    if (zzacgVar.zzp()) {
                        return;
                    }
                    zzf = zzacgVar.zzf();
                } while (zzf == this.zzb);
                this.zzd = zzf;
                return;
            }
        }
        zzadg zzadgVar = (zzadg) list;
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int zze2 = ((zzace) this.zza).zze();
            zzT(zze2);
            int zzb2 = this.zza.zzb() + zze2;
            do {
                zzadgVar.zzf(((zzace) this.zza).zzd());
            } while (this.zza.zzb() < zzb2);
        } else if (i2 != 5) {
            throw zzadn.zza();
        } else {
            do {
                zzadgVar.zzf(((zzace) this.zza).zzd());
                zzacg zzacgVar2 = this.zza;
                if (zzacgVar2.zzp()) {
                    return;
                }
                zzf2 = zzacgVar2.zzf();
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzH(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadz) {
            zzadz zzadzVar = (zzadz) list;
            int i = this.zzb & 7;
            if (i != 1) {
                if (i == 2) {
                    int zze = ((zzace) this.zza).zze();
                    zzU(zze);
                    int zzb = this.zza.zzb() + zze;
                    do {
                        zzadzVar.zzf(((zzace) this.zza).zzg());
                    } while (this.zza.zzb() < zzb);
                    return;
                }
                throw zzadn.zza();
            }
            do {
                zzadzVar.zzf(((zzace) this.zza).zzg());
                zzacg zzacgVar = this.zza;
                if (zzacgVar.zzp()) {
                    return;
                }
                zzf2 = zzacgVar.zzf();
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 1) {
            if (i2 == 2) {
                int zze2 = ((zzace) this.zza).zze();
                zzU(zze2);
                int zzb2 = this.zza.zzb() + zze2;
                do {
                    list.add(Long.valueOf(((zzace) this.zza).zzg()));
                } while (this.zza.zzb() < zzb2);
                return;
            }
            throw zzadn.zza();
        }
        do {
            list.add(Long.valueOf(((zzace) this.zza).zzg()));
            zzacg zzacgVar2 = this.zza;
            if (zzacgVar2.zzp()) {
                return;
            }
            zzf = zzacgVar2.zzf();
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzI(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadg) {
            zzadg zzadgVar = (zzadg) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzb = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzadgVar.zzf(zzace.zzs(((zzace) this.zza).zze()));
                    } while (this.zza.zzb() < zzb);
                    zzR(zzb);
                    return;
                }
                throw zzadn.zza();
            }
            do {
                zzadgVar.zzf(zzace.zzs(((zzace) this.zza).zze()));
                zzacg zzacgVar = this.zza;
                if (zzacgVar.zzp()) {
                    return;
                }
                zzf2 = zzacgVar.zzf();
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Integer.valueOf(zzace.zzs(((zzace) this.zza).zze())));
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
                return;
            }
            throw zzadn.zza();
        }
        do {
            list.add(Integer.valueOf(zzace.zzs(((zzace) this.zza).zze())));
            zzacg zzacgVar2 = this.zza;
            if (zzacgVar2.zzp()) {
                return;
            }
            zzf = zzacgVar2.zzf();
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzJ(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadz) {
            zzadz zzadzVar = (zzadz) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzb = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzadzVar.zzf(zzace.zzt(((zzace) this.zza).zzh()));
                    } while (this.zza.zzb() < zzb);
                    zzR(zzb);
                    return;
                }
                throw zzadn.zza();
            }
            do {
                zzadzVar.zzf(zzace.zzt(((zzace) this.zza).zzh()));
                zzacg zzacgVar = this.zza;
                if (zzacgVar.zzp()) {
                    return;
                }
                zzf2 = zzacgVar.zzf();
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Long.valueOf(zzace.zzt(((zzace) this.zza).zzh())));
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
                return;
            }
            throw zzadn.zza();
        }
        do {
            list.add(Long.valueOf(zzace.zzt(((zzace) this.zza).zzh())));
            zzacg zzacgVar2 = this.zza;
            if (zzacgVar2.zzp()) {
                return;
            }
            zzf = zzacgVar2.zzf();
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzK(List list, boolean z) throws IOException {
        int zzf;
        int zzf2;
        if ((this.zzb & 7) != 2) {
            throw zzadn.zza();
        }
        if (!(list instanceof zzads) || z) {
            do {
                list.add(z ? zzs() : zzr());
                zzacg zzacgVar = this.zza;
                if (zzacgVar.zzp()) {
                    return;
                }
                zzf = zzacgVar.zzf();
            } while (zzf == this.zzb);
            this.zzd = zzf;
            return;
        }
        zzads zzadsVar = (zzads) list;
        do {
            zzadsVar.zzi(zzp());
            zzacg zzacgVar2 = this.zza;
            if (zzacgVar2.zzp()) {
                return;
            }
            zzf2 = zzacgVar2.zzf();
        } while (zzf2 == this.zzb);
        this.zzd = zzf2;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzL(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadg) {
            zzadg zzadgVar = (zzadg) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzb = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzadgVar.zzf(((zzace) this.zza).zze());
                    } while (this.zza.zzb() < zzb);
                    zzR(zzb);
                    return;
                }
                throw zzadn.zza();
            }
            do {
                zzadgVar.zzf(((zzace) this.zza).zze());
                zzacg zzacgVar = this.zza;
                if (zzacgVar.zzp()) {
                    return;
                }
                zzf2 = zzacgVar.zzf();
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Integer.valueOf(((zzace) this.zza).zze()));
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
                return;
            }
            throw zzadn.zza();
        }
        do {
            list.add(Integer.valueOf(((zzace) this.zza).zze()));
            zzacg zzacgVar2 = this.zza;
            if (zzacgVar2.zzp()) {
                return;
            }
            zzf = zzacgVar2.zzf();
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzM(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadz) {
            zzadz zzadzVar = (zzadz) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzb = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzadzVar.zzf(((zzace) this.zza).zzh());
                    } while (this.zza.zzb() < zzb);
                    zzR(zzb);
                    return;
                }
                throw zzadn.zza();
            }
            do {
                zzadzVar.zzf(((zzace) this.zza).zzh());
                zzacg zzacgVar = this.zza;
                if (zzacgVar.zzp()) {
                    return;
                }
                zzf2 = zzacgVar.zzf();
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Long.valueOf(((zzace) this.zza).zzh()));
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
                return;
            }
            throw zzadn.zza();
        }
        do {
            list.add(Long.valueOf(((zzace) this.zza).zzh()));
            zzacg zzacgVar2 = this.zza;
            if (zzacgVar2.zzp()) {
                return;
            }
            zzf = zzacgVar2.zzf();
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final boolean zzN() throws IOException {
        zzS(0);
        return this.zza.zzq();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final boolean zzO() throws IOException {
        int i;
        zzacg zzacgVar = this.zza;
        if (zzacgVar.zzp() || (i = this.zzb) == this.zzc) {
            return false;
        }
        return zzacgVar.zzr(i);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final double zza() throws IOException {
        zzS(1);
        return Double.longBitsToDouble(((zzace) this.zza).zzg());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final float zzb() throws IOException {
        zzS(5);
        return Float.intBitsToFloat(((zzace) this.zza).zzd());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final int zzc() throws IOException {
        int i = this.zzd;
        if (i != 0) {
            this.zzb = i;
            this.zzd = 0;
        } else {
            i = this.zza.zzf();
            this.zzb = i;
        }
        if (i == 0 || i == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return i >>> 3;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final int zzd() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final int zze() throws IOException {
        zzS(0);
        return ((zzace) this.zza).zze();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final int zzf() throws IOException {
        zzS(5);
        return ((zzace) this.zza).zzd();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final int zzg() throws IOException {
        zzS(0);
        return ((zzace) this.zza).zze();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final int zzh() throws IOException {
        zzS(5);
        return ((zzace) this.zza).zzd();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final int zzi() throws IOException {
        zzS(0);
        return zzace.zzs(((zzace) this.zza).zze());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final int zzj() throws IOException {
        zzS(0);
        return ((zzace) this.zza).zze();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final long zzk() throws IOException {
        zzS(1);
        return ((zzace) this.zza).zzg();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final long zzl() throws IOException {
        zzS(0);
        return ((zzace) this.zza).zzh();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final long zzm() throws IOException {
        zzS(1);
        return ((zzace) this.zza).zzg();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final long zzn() throws IOException {
        zzS(0);
        return zzace.zzt(((zzace) this.zza).zzh());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final long zzo() throws IOException {
        zzS(0);
        return ((zzace) this.zza).zzh();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final zzacc zzp() throws IOException {
        zzS(2);
        return this.zza.zzj();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final String zzr() throws IOException {
        zzS(2);
        return this.zza.zzk();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final String zzs() throws IOException {
        zzS(2);
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzt(Object obj, zzaew zzaewVar, zzacs zzacsVar) throws IOException {
        zzS(3);
        zzP(obj, zzaewVar, zzacsVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzu(Object obj, zzaew zzaewVar, zzacs zzacsVar) throws IOException {
        zzS(2);
        zzQ(obj, zzaewVar, zzacsVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzv(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzabr) {
            zzabr zzabrVar = (zzabr) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzb = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzabrVar.zze(this.zza.zzq());
                    } while (this.zza.zzb() < zzb);
                    zzR(zzb);
                    return;
                }
                throw zzadn.zza();
            }
            do {
                zzabrVar.zze(this.zza.zzq());
                zzacg zzacgVar = this.zza;
                if (zzacgVar.zzp()) {
                    return;
                }
                zzf2 = zzacgVar.zzf();
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Boolean.valueOf(this.zza.zzq()));
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
                return;
            }
            throw zzadn.zza();
        }
        do {
            list.add(Boolean.valueOf(this.zza.zzq()));
            zzacg zzacgVar2 = this.zza;
            if (zzacgVar2.zzp()) {
                return;
            }
            zzf = zzacgVar2.zzf();
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzw(List list) throws IOException {
        int zzf;
        if ((this.zzb & 7) != 2) {
            throw zzadn.zza();
        }
        do {
            list.add(zzp());
            zzacg zzacgVar = this.zza;
            if (zzacgVar.zzp()) {
                return;
            }
            zzf = zzacgVar.zzf();
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzx(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzacp) {
            zzacp zzacpVar = (zzacp) list;
            int i = this.zzb & 7;
            if (i != 1) {
                if (i == 2) {
                    int zze = ((zzace) this.zza).zze();
                    zzU(zze);
                    int zzb = this.zza.zzb() + zze;
                    do {
                        zzacpVar.zze(Double.longBitsToDouble(((zzace) this.zza).zzg()));
                    } while (this.zza.zzb() < zzb);
                    return;
                }
                throw zzadn.zza();
            }
            do {
                zzacpVar.zze(Double.longBitsToDouble(((zzace) this.zza).zzg()));
                zzacg zzacgVar = this.zza;
                if (zzacgVar.zzp()) {
                    return;
                }
                zzf2 = zzacgVar.zzf();
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 1) {
            if (i2 == 2) {
                int zze2 = ((zzace) this.zza).zze();
                zzU(zze2);
                int zzb2 = this.zza.zzb() + zze2;
                do {
                    list.add(Double.valueOf(Double.longBitsToDouble(((zzace) this.zza).zzg())));
                } while (this.zza.zzb() < zzb2);
                return;
            }
            throw zzadn.zza();
        }
        do {
            list.add(Double.valueOf(Double.longBitsToDouble(((zzace) this.zza).zzg())));
            zzacg zzacgVar2 = this.zza;
            if (zzacgVar2.zzp()) {
                return;
            }
            zzf = zzacgVar2.zzf();
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzy(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadg) {
            zzadg zzadgVar = (zzadg) list;
            int i = this.zzb & 7;
            if (i != 0) {
                if (i == 2) {
                    int zzb = this.zza.zzb() + ((zzace) this.zza).zze();
                    do {
                        zzadgVar.zzf(((zzace) this.zza).zze());
                    } while (this.zza.zzb() < zzb);
                    zzR(zzb);
                    return;
                }
                throw zzadn.zza();
            }
            do {
                zzadgVar.zzf(((zzace) this.zza).zze());
                zzacg zzacgVar = this.zza;
                if (zzacgVar.zzp()) {
                    return;
                }
                zzf2 = zzacgVar.zzf();
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        int i2 = this.zzb & 7;
        if (i2 != 0) {
            if (i2 == 2) {
                int zzb2 = this.zza.zzb() + ((zzace) this.zza).zze();
                do {
                    list.add(Integer.valueOf(((zzace) this.zza).zze()));
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
                return;
            }
            throw zzadn.zza();
        }
        do {
            list.add(Integer.valueOf(((zzace) this.zza).zze()));
            zzacg zzacgVar2 = this.zza;
            if (zzacgVar2.zzp()) {
                return;
            }
            zzf = zzacgVar2.zzf();
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaev
    public final void zzz(List list) throws IOException {
        int zzf;
        int zzf2;
        if (!(list instanceof zzadg)) {
            int i = this.zzb & 7;
            if (i == 2) {
                int zze = ((zzace) this.zza).zze();
                zzT(zze);
                int zzb = this.zza.zzb() + zze;
                do {
                    list.add(Integer.valueOf(((zzace) this.zza).zzd()));
                } while (this.zza.zzb() < zzb);
                return;
            } else if (i != 5) {
                throw zzadn.zza();
            } else {
                do {
                    list.add(Integer.valueOf(((zzace) this.zza).zzd()));
                    zzacg zzacgVar = this.zza;
                    if (zzacgVar.zzp()) {
                        return;
                    }
                    zzf = zzacgVar.zzf();
                } while (zzf == this.zzb);
                this.zzd = zzf;
                return;
            }
        }
        zzadg zzadgVar = (zzadg) list;
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int zze2 = ((zzace) this.zza).zze();
            zzT(zze2);
            int zzb2 = this.zza.zzb() + zze2;
            do {
                zzadgVar.zzf(((zzace) this.zza).zzd());
            } while (this.zza.zzb() < zzb2);
        } else if (i2 != 5) {
            throw zzadn.zza();
        } else {
            do {
                zzadgVar.zzf(((zzace) this.zza).zzd());
                zzacg zzacgVar2 = this.zza;
                if (zzacgVar2.zzp()) {
                    return;
                }
                zzf2 = zzacgVar2.zzf();
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
        }
    }
}
