package com.samsung.android.sdk.accessory;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class SAPeerAgent implements Parcelable {
    public static final Parcelable.Creator<SAPeerAgent> CREATOR = new Parcelable.Creator<SAPeerAgent>() { // from class: com.samsung.android.sdk.accessory.SAPeerAgent.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ SAPeerAgent createFromParcel(Parcel parcel) {
            return new SAPeerAgent(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ SAPeerAgent[] newArray(int i) {
            return new SAPeerAgent[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private String d;
    private SAPeerAccessory e;
    private long f;
    private int g;
    private int h;

    public SAPeerAgent(Parcel parcel) {
        this.g = 0;
        this.h = 1;
        int readInt = parcel.readInt();
        Log.v("[SA_SDK]SAPeerAgent", "Peeragent:Framework version:".concat(String.valueOf(readInt)));
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = (SAPeerAccessory) parcel.readParcelable(SAPeerAccessory.class.getClassLoader());
        if (readInt >= 9) {
            this.g = parcel.readInt();
            this.h = parcel.readInt();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SAPeerAgent(List<String> list) {
        this.g = 0;
        this.h = 1;
        this.a = list.get(0);
        this.b = list.get(1);
        this.c = list.get(2);
        this.d = list.get(3);
        this.g = Integer.parseInt(list.get(4));
        this.h = Integer.parseInt(list.get(5));
        this.e = new SAPeerAccessory(list.subList(6, list.size()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        int i = this.g;
        if (i == 1) {
            return 1792;
        }
        if (i == 2) {
            return SAMessage.ERROR_PEER_SERVICE_NOT_SUPPORTED;
        }
        if (i == 0) {
            return SAMessage.ERROR_PEER_AGENT_NOT_SUPPORTED;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(long j) {
        this.f = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b() {
        SAPeerAccessory sAPeerAccessory = this.e;
        if (sAPeerAccessory != null) {
            return sAPeerAccessory.b();
        }
        return 65530;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long c() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<String> d() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.a);
        arrayList.add(this.b);
        arrayList.add(this.c);
        arrayList.add(this.d);
        arrayList.add(Integer.toString(this.g));
        arrayList.add(Integer.toString(this.h));
        arrayList.addAll(this.e.e());
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00cc A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r9) {
        java.lang.StringBuilder r1;
        java.lang.String r9;
        if (r9 != null && (r9 instanceof com.samsung.android.sdk.accessory.SAPeerAgent)) {
            com.samsung.android.sdk.accessory.SAPeerAgent r9 = (com.samsung.android.sdk.accessory.SAPeerAgent) r9;
            if (getPeerId() == null) {
                r1 = new java.lang.StringBuilder("Invalid peerAgent instance.Peer ID - this:null PeerAgent:");
            } else if (r8.a.equals(r9.getPeerId())) {
                if (getContainerId() == null) {
                    if (r9.getContainerId() != null) {
                        r1 = new java.lang.StringBuilder("Invalid peerAgent instance.Container ID - this:null PeerAgent:");
                        r9 = r9.getContainerId();
                    }
                    if (r9.getAccessory().getId() != getAccessory().getId()) {
                        return true;
                    }
                    r1 = new java.lang.StringBuilder("Invalid peerAgent instance.Accessory ID - this:").append(com.samsung.android.sdk.accessory.c.a(getAccessory().getAccessoryId(), getAccessory().getAddress())).append(" PeerAgent:");
                    r9 = com.samsung.android.sdk.accessory.c.a(r9.getAccessory().getAccessoryId(), r9.getAccessory().getAddress());
                } else {
                    if (!r8.b.equals(r9.getContainerId())) {
                        r1 = new java.lang.StringBuilder("Invalid peerAgent instance.Container ID - this:").append(r8.b).append(" PeerAgent:");
                        r9 = r9.getContainerId();
                    }
                    if (r9.getAccessory().getId() != getAccessory().getId()) {
                    }
                }
                android.util.Log.w("[SA_SDK]SAPeerAgent", r1.append(r9).toString());
                return false;
            } else {
                r1 = new java.lang.StringBuilder("Invalid peerAgent instance.Peer ID - this:").append(r8.a).append(" PeerAgent:");
            }
            r9 = r9.getPeerId();
            android.util.Log.w("[SA_SDK]SAPeerAgent", r1.append(r9).toString());
            return false;
        }
        return false;
    }

    public SAPeerAccessory getAccessory() {
        return this.e;
    }

    public long getAccessoryId() {
        return this.e.getId();
    }

    public String getAppName() {
        return this.c;
    }

    public String getContainerId() {
        return this.b;
    }

    public int getMaxAllowedDataSize() {
        SAPeerAccessory sAPeerAccessory = this.e;
        if (sAPeerAccessory != null) {
            return sAPeerAccessory.a();
        }
        return 1048576;
    }

    public int getMaxAllowedMessageSize() {
        SAPeerAccessory sAPeerAccessory = this.e;
        if (sAPeerAccessory != null) {
            return sAPeerAccessory.a();
        }
        return 1048576;
    }

    public String getPeerId() {
        return this.a;
    }

    public String getProfileVersion() {
        return this.d;
    }

    public int hashCode() {
        int hashCode = (this.a.hashCode() + 527) * 31;
        String str = this.b;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        SAPeerAccessory sAPeerAccessory = this.e;
        return hashCode2 + (sAPeerAccessory != null ? (int) (sAPeerAccessory.getId() ^ (this.e.getId() >>> 32)) : 0);
    }

    public boolean isFeatureEnabled(int i) {
        if (i != 0) {
            return i != 2 ? i == 3 && this.h == 1 : this.g == 1;
        }
        return true;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("PeerAgent - id:" + this.a + " ");
        stringBuffer.append("containerId:" + this.b + " ");
        stringBuffer.append("FriendlyName:" + this.c + " ");
        stringBuffer.append("Profile Version:" + this.d + " ");
        stringBuffer.append(this.e.toString() + " ");
        stringBuffer.append("MexSupport:" + this.g + " ");
        stringBuffer.append("SocketSupport:" + this.h);
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(9);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeParcelable(this.e, i);
        Log.v("[SA_SDK]SAPeerAgent", "mCompatibilityVersion = 0");
        if ((k.g() == 1 && k.i()) || k.j()) {
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
        }
    }
}
