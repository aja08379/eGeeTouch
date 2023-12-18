package com.google.android.gms.safetynet;

import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/* loaded from: classes2.dex */
public class SafeBrowsingData extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SafeBrowsingData> CREATOR = new zzj();
    private static final String TAG = "SafeBrowsingData";
    private String zzm;
    private DataHolder zzn;
    private ParcelFileDescriptor zzo;
    private long zzp;
    private byte[] zzq;
    private byte[] zzr;
    private File zzs;

    public SafeBrowsingData() {
        this(null, null, null, 0L, null);
    }

    public SafeBrowsingData(long j, byte[] bArr) {
        this(null, null, null, j, bArr);
    }

    public SafeBrowsingData(String str) {
        this(str, null, null, 0L, null);
    }

    public SafeBrowsingData(String str, DataHolder dataHolder) {
        this(str, dataHolder, null, 0L, null);
    }

    public SafeBrowsingData(String str, DataHolder dataHolder, ParcelFileDescriptor parcelFileDescriptor, long j, byte[] bArr) {
        this.zzm = str;
        this.zzn = dataHolder;
        this.zzo = parcelFileDescriptor;
        this.zzp = j;
        this.zzq = bArr;
    }

    private final FileOutputStream zza() {
        Throwable th;
        File file;
        File file2 = this.zzs;
        if (file2 == null) {
            return null;
        }
        try {
            file = File.createTempFile("xlb", ".tmp", file2);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                this.zzo = ParcelFileDescriptor.open(file, 268435456);
                if (file != null) {
                    file.delete();
                }
                return fileOutputStream;
            } catch (IOException unused) {
                if (file != null) {
                    file.delete();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (file != null) {
                    file.delete();
                }
                throw th;
            }
        } catch (IOException unused2) {
            file = null;
        } catch (Throwable th3) {
            th = th3;
            file = null;
        }
    }

    private static void zza(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    public byte[] getBlacklists() {
        if (this.zzo == null) {
            return null;
        }
        DataInputStream dataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(this.zzo));
        try {
            byte[] bArr = new byte[dataInputStream.readInt()];
            dataInputStream.read(bArr);
            return bArr;
        } catch (IOException unused) {
            return null;
        } finally {
            zza(dataInputStream);
            this.zzo = null;
        }
    }

    public DataHolder getBlacklistsDataHolder() {
        return this.zzn;
    }

    public ParcelFileDescriptor getFileDescriptor() {
        return this.zzo;
    }

    public long getLastUpdateTimeMs() {
        return this.zzp;
    }

    public String getMetadata() {
        return this.zzm;
    }

    public byte[] getState() {
        return this.zzq;
    }

    public void setBlacklists(byte[] bArr) {
        this.zzr = bArr;
    }

    public void setTempDir(File file) {
        if (file != null) {
            this.zzs = file;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0035  */
    @Override // android.os.Parcelable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void writeToParcel(android.os.Parcel r4, int r5) {
        boolean r0;
        java.io.FileOutputStream r0;
        if (r3.zzo == null && r3.zzr != null && (r0 = zza()) != null) {
            java.io.DataOutputStream r0 = new java.io.DataOutputStream(new java.io.BufferedOutputStream(r0));
            try {
                r0.writeInt(r3.zzr.length);
                r0.write(r3.zzr);
                zza(r0);
                r0 = true;
            } catch (java.io.IOException unused) {
                zza(r0);
            } catch (java.lang.Throwable r4) {
                zza(r0);
                throw r4;
            }
            if (r0) {
                r5 |= 1;
            }
            com.google.android.gms.safetynet.zzj.zza(r3, r4, r5);
            r3.zzo = null;
        }
        r0 = false;
        if (r0) {
        }
        com.google.android.gms.safetynet.zzj.zza(r3, r4, r5);
        r3.zzo = null;
    }
}
