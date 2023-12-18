package com.samsung.android.sdk.accessory;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class SAPeerAccessory implements Parcelable {
    public static final Parcelable.Creator<SAPeerAccessory> CREATOR = new Parcelable.Creator<SAPeerAccessory>() { // from class: com.samsung.android.sdk.accessory.SAPeerAccessory.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ SAPeerAccessory createFromParcel(Parcel parcel) {
            return new SAPeerAccessory(parcel, (byte) 0);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ SAPeerAccessory[] newArray(int i) {
            return new SAPeerAccessory[i];
        }
    };
    public static final int TRANSPORT_BLE = 4;
    public static final int TRANSPORT_BT = 2;
    public static final int TRANSPORT_MOBILE = 16;
    public static final int TRANSPORT_USB = 8;
    public static final int TRANSPORT_WIFI = 1;
    private long a;
    private String b;
    private String c;
    private int d;
    private String e;
    private String f;
    private int g;
    private int h;
    private int i;
    private int j;
    private String k;

    private SAPeerAccessory(Parcel parcel) {
        int readInt = parcel.readInt();
        this.a = parcel.readLong();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.h = parcel.readInt();
        this.k = parcel.readString();
        if (readInt >= 8) {
            this.i = parcel.readInt();
        }
        this.g = parcel.readInt();
        this.j = parcel.readInt();
    }

    /* synthetic */ SAPeerAccessory(Parcel parcel, byte b) {
        this(parcel);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SAPeerAccessory(List<String> list) {
        this.a = Integer.parseInt(list.get(0));
        this.b = list.get(1);
        this.c = list.get(2);
        this.d = Integer.parseInt(list.get(3));
        this.e = list.get(4);
        this.f = list.get(5);
        this.h = Integer.parseInt(list.get(6));
        this.k = list.get(7);
        this.i = Integer.parseInt(list.get(8));
        this.g = Integer.parseInt(list.get(9));
        this.j = Integer.parseInt(list.get(10));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int c() {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int d() {
        return this.j;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<String> e() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Long.toString(this.a));
        arrayList.add(this.b);
        arrayList.add(this.c);
        arrayList.add(Integer.toString(this.d));
        arrayList.add(this.e);
        arrayList.add(this.f);
        arrayList.add(Integer.toString(this.h));
        arrayList.add(this.k);
        arrayList.add(Integer.toString(this.i));
        arrayList.add(Integer.toString(this.g));
        arrayList.add(Integer.toString(this.j));
        return arrayList;
    }

    public String getAccessoryId() {
        return this.k;
    }

    public String getAddress() {
        return this.b;
    }

    public long getId() {
        return this.a;
    }

    public String getName() {
        return this.c;
    }

    public String getProductId() {
        return this.e;
    }

    public int getTransportType() {
        return this.d;
    }

    public String getVendorId() {
        return this.f;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("PeerAccessory - ");
        stringBuffer.append("Id:" + this.a);
        stringBuffer.append(" Name:" + this.c);
        StringBuilder sb = new StringBuilder(" Address:");
        String str = this.b;
        stringBuffer.append(sb.append(c.a(str, str)).append(" ").toString());
        stringBuffer.append(" TransportType:" + this.d);
        stringBuffer.append(" ProductId:" + this.e);
        stringBuffer.append(" VendorId:" + this.f);
        stringBuffer.append(" APDU:" + this.g);
        stringBuffer.append(" SSDU:" + this.h);
        stringBuffer.append(" Accessory ID:" + c.a(this.k, this.b));
        stringBuffer.append(" MXDU:" + this.i);
        stringBuffer.append(" Encryption padding:" + this.j);
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(9);
        parcel.writeLong(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeInt(this.h);
        parcel.writeString(this.k);
        Log.v("[SA_SDK]SAPeerAccesssosry", "mCompatibilityVersion = 0");
        if (k.k()) {
            parcel.writeInt(this.i);
        }
        parcel.writeInt(this.g);
        parcel.writeInt(this.j);
    }
}
