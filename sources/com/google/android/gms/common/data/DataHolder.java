package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: classes.dex */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    public static final Parcelable.Creator<DataHolder> CREATOR = new zaf();
    private static final Builder zaf = new zab(new String[0], null);
    final int zaa;
    Bundle zab;
    int[] zac;
    int zad;
    boolean zae;
    private final String[] zag;
    private final CursorWindow[] zah;
    private final int zai;
    private final Bundle zaj;
    private boolean zak;

    /* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
    /* loaded from: classes.dex */
    public static class Builder {
        private final String[] zaa;
        private final ArrayList zab = new ArrayList();
        private final HashMap zac = new HashMap();

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ Builder(String[] strArr, String str, zac zacVar) {
            this.zaa = (String[]) Preconditions.checkNotNull(strArr);
        }

        public DataHolder build(int i) {
            return new DataHolder(this, i);
        }

        public Builder withRow(ContentValues contentValues) {
            Asserts.checkNotNull(contentValues);
            HashMap hashMap = new HashMap(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            return zaa(hashMap);
        }

        public Builder zaa(HashMap hashMap) {
            Asserts.checkNotNull(hashMap);
            this.zab.add(hashMap);
            return this;
        }

        public DataHolder build(int i, Bundle bundle) {
            return new DataHolder(this, i, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.zae = false;
        this.zak = true;
        this.zaa = i;
        this.zag = strArr;
        this.zah = cursorWindowArr;
        this.zai = i2;
        this.zaj = bundle;
    }

    public static Builder builder(String[] strArr) {
        return new Builder(strArr, null, null);
    }

    public static DataHolder empty(int i) {
        return new DataHolder(zaf, i, (Bundle) null);
    }

    private final void zae(String str, int i) {
        Bundle bundle = this.zab;
        if (bundle == null || !bundle.containsKey(str)) {
            throw new IllegalArgumentException("No such column: ".concat(String.valueOf(str)));
        }
        if (!isClosed()) {
            if (i < 0 || i >= this.zad) {
                throw new CursorIndexOutOfBoundsException(i, this.zad);
            }
            return;
        }
        throw new IllegalArgumentException("Buffer is closed.");
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x0133, code lost:
        if (r5 != false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0135, code lost:
        android.util.Log.d("DataHolder", "Couldn't populate window data for row " + r4 + " - allocating new window.");
        r2.freeLastRow();
        r2 = new android.database.CursorWindow(false);
        r2.setStartPosition(r4);
        r2.setNumColumns(r13.zaa.length);
        r3.add(r2);
        r4 = r4 - 1;
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0167, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0171, code lost:
        throw new com.google.android.gms.common.data.zad("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.database.CursorWindow[] zaf(com.google.android.gms.common.data.DataHolder.Builder r13, int r14) {
        if (r13.zaa.length == 0) {
            return new android.database.CursorWindow[0];
        }
        java.util.ArrayList r14 = r13.zab;
        int r1 = r14.size();
        android.database.CursorWindow r2 = new android.database.CursorWindow(false);
        java.util.ArrayList r3 = new java.util.ArrayList();
        r3.add(r2);
        r2.setNumColumns(r13.zaa.length);
        int r4 = 0;
        boolean r5 = false;
        while (r4 < r1) {
            try {
                if (!r2.allocRow()) {
                    android.util.Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + r4 + ")");
                    r2 = new android.database.CursorWindow(false);
                    r2.setStartPosition(r4);
                    r2.setNumColumns(r13.zaa.length);
                    r3.add(r2);
                    if (!r2.allocRow()) {
                        android.util.Log.e("DataHolder", "Unable to allocate row to hold data.");
                        r3.remove(r2);
                        return (android.database.CursorWindow[]) r3.toArray(new android.database.CursorWindow[r3.size()]);
                    }
                }
                java.util.Map r6 = (java.util.Map) r14.get(r4);
                int r9 = 0;
                boolean r10 = true;
                while (true) {
                    if (r9 < r13.zaa.length) {
                        if (!r10) {
                            break;
                        }
                        java.lang.String r10 = r13.zaa[r9];
                        java.lang.Object r11 = r6.get(r10);
                        if (r11 == null) {
                            r10 = r2.putNull(r4, r9);
                        } else if (r11 instanceof java.lang.String) {
                            r10 = r2.putString((java.lang.String) r11, r4, r9);
                        } else if (r11 instanceof java.lang.Long) {
                            r10 = r2.putLong(((java.lang.Long) r11).longValue(), r4, r9);
                        } else if (r11 instanceof java.lang.Integer) {
                            r10 = r2.putLong(((java.lang.Integer) r11).intValue(), r4, r9);
                        } else if (r11 instanceof java.lang.Boolean) {
                            r10 = r2.putLong(true != ((java.lang.Boolean) r11).booleanValue() ? 0L : 1L, r4, r9);
                        } else if (r11 instanceof byte[]) {
                            r10 = r2.putBlob((byte[]) r11, r4, r9);
                        } else if (r11 instanceof java.lang.Double) {
                            r10 = r2.putDouble(((java.lang.Double) r11).doubleValue(), r4, r9);
                        } else if (r11 instanceof java.lang.Float) {
                            r10 = r2.putDouble(((java.lang.Float) r11).floatValue(), r4, r9);
                        } else {
                            throw new java.lang.IllegalArgumentException("Unsupported object for column " + r10 + ": " + r11.toString());
                        }
                        r9++;
                    } else if (r10) {
                        r5 = false;
                    }
                }
            } catch (java.lang.RuntimeException r13) {
                int r14 = r3.size();
                for (int r0 = 0; r0 < r14; r0++) {
                    ((android.database.CursorWindow) r3.get(r0)).close();
                }
                throw r13;
            }
        }
        return (android.database.CursorWindow[]) r3.toArray(new android.database.CursorWindow[r3.size()]);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            if (!this.zae) {
                this.zae = true;
                int i = 0;
                while (true) {
                    CursorWindow[] cursorWindowArr = this.zah;
                    if (i >= cursorWindowArr.length) {
                        break;
                    }
                    cursorWindowArr[i].close();
                    i++;
                }
            }
        }
    }

    protected final void finalize() throws Throwable {
        try {
            if (this.zak && this.zah.length > 0 && !isClosed()) {
                close();
                String obj = toString();
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: " + obj + ")");
            }
        } finally {
            super.finalize();
        }
    }

    public boolean getBoolean(String str, int i, int i2) {
        zae(str, i);
        return Long.valueOf(this.zah[i2].getLong(i, this.zab.getInt(str))).longValue() == 1;
    }

    public byte[] getByteArray(String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].getBlob(i, this.zab.getInt(str));
    }

    public int getCount() {
        return this.zad;
    }

    public int getInteger(String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].getInt(i, this.zab.getInt(str));
    }

    public long getLong(String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].getLong(i, this.zab.getInt(str));
    }

    public Bundle getMetadata() {
        return this.zaj;
    }

    public int getStatusCode() {
        return this.zai;
    }

    public String getString(String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].getString(i, this.zab.getInt(str));
    }

    public int getWindowIndex(int i) {
        int length;
        int i2 = 0;
        Preconditions.checkState(i >= 0 && i < this.zad);
        while (true) {
            int[] iArr = this.zac;
            length = iArr.length;
            if (i2 >= length) {
                break;
            } else if (i < iArr[i2]) {
                i2--;
                break;
            } else {
                i2++;
            }
        }
        return i2 == length ? i2 - 1 : i2;
    }

    public boolean hasColumn(String str) {
        return this.zab.containsKey(str);
    }

    public boolean hasNull(String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].isNull(i, this.zab.getInt(str));
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.zae;
        }
        return z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, this.zag, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zah, i, false);
        SafeParcelWriter.writeInt(parcel, 3, getStatusCode());
        SafeParcelWriter.writeBundle(parcel, 4, getMetadata(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zaa);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        if ((i & 1) != 0) {
            close();
        }
    }

    public final double zaa(String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].getDouble(i, this.zab.getInt(str));
    }

    public final float zab(String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].getFloat(i, this.zab.getInt(str));
    }

    public final void zac(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zae(str, i);
        this.zah[i2].copyStringToBuffer(i, this.zab.getInt(str), charArrayBuffer);
    }

    public final void zad() {
        this.zab = new Bundle();
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = this.zag;
            if (i2 >= strArr.length) {
                break;
            }
            this.zab.putInt(strArr[i2], i2);
            i2++;
        }
        this.zac = new int[this.zah.length];
        int i3 = 0;
        while (true) {
            CursorWindow[] cursorWindowArr = this.zah;
            if (i >= cursorWindowArr.length) {
                this.zad = i3;
                return;
            }
            this.zac[i] = i3;
            i3 += this.zah[i].getNumRows() - (i3 - cursorWindowArr[i].getStartPosition());
            i++;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DataHolder(android.database.Cursor r8, int r9, android.os.Bundle r10) {
        this(r8, (android.database.CursorWindow[]) r1.toArray(new android.database.CursorWindow[r1.size()]), r9, r10);
        int r3;
        com.google.android.gms.common.sqlite.CursorWrapper r0 = new com.google.android.gms.common.sqlite.CursorWrapper(r8);
        java.lang.String[] r8 = r0.getColumnNames();
        java.util.ArrayList r1 = new java.util.ArrayList();
        try {
            int r2 = r0.getCount();
            android.database.CursorWindow r3 = r0.getWindow();
            if (r3 == null || r3.getStartPosition() != 0) {
                r3 = 0;
            } else {
                r3.acquireReference();
                r0.setWindow(null);
                r1.add(r3);
                r3 = r3.getNumRows();
            }
            while (r3 < r2) {
                if (!r0.moveToPosition(r3)) {
                    break;
                }
                android.database.CursorWindow r6 = r0.getWindow();
                if (r6 != null) {
                    r6.acquireReference();
                    r0.setWindow(null);
                } else {
                    r6 = new android.database.CursorWindow(false);
                    r6.setStartPosition(r3);
                    r0.fillWindow(r3, r6);
                }
                if (r6.getNumRows() == 0) {
                    break;
                }
                r1.add(r6);
                r3 = r6.getStartPosition() + r6.getNumRows();
            }
            r0.close();
        } catch (java.lang.Throwable r8) {
            r0.close();
            throw r8;
        }
    }

    private DataHolder(Builder builder, int i, Bundle bundle) {
        this(builder.zaa, zaf(builder, -1), i, (Bundle) null);
    }

    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.zae = false;
        this.zak = true;
        this.zaa = 1;
        this.zag = (String[]) Preconditions.checkNotNull(strArr);
        this.zah = (CursorWindow[]) Preconditions.checkNotNull(cursorWindowArr);
        this.zai = i;
        this.zaj = bundle;
        zad();
    }
}
