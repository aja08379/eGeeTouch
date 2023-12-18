package com.egeetouch.egeetouch_manager;

import java.util.ArrayList;
import java.util.Collections;
/* loaded from: classes.dex */
public class SortAuditLogs2 {
    private ArrayList<String> address;
    private ArrayList<Integer> auditDeciValue;
    private ArrayList<String> email;
    private ArrayList<Double> latitude;
    private ArrayList<Long> lockBacktime;
    private ArrayList<String> lockStatus;
    private ArrayList<Double> longitude;
    private String[] mAddress;
    private Integer[] mAduditDeciValue;
    private Long[] mArrTime;
    private Double[] mAuditLatitude;
    private Double[] mAuditLongitude;
    private String[] mEmail;
    private Long[] mLockBackTime;
    private String[] mLockStatus;
    private ArrayList<Long> time;

    public SortAuditLogs2(ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4, ArrayList arrayList5, ArrayList arrayList6, ArrayList arrayList7, ArrayList arrayList8) {
        this.email = new ArrayList<>();
        this.time = new ArrayList<>();
        this.address = new ArrayList<>();
        this.lockStatus = new ArrayList<>();
        this.auditDeciValue = new ArrayList<>();
        this.lockBacktime = new ArrayList<>();
        this.longitude = new ArrayList<>();
        this.latitude = new ArrayList<>();
        this.email = arrayList3;
        this.address = arrayList2;
        this.time = arrayList;
        this.lockStatus = arrayList4;
        this.auditDeciValue = arrayList5;
        this.lockBacktime = arrayList6;
        this.longitude = arrayList7;
        this.latitude = arrayList8;
        this.mArrTime = new Long[arrayList.size()];
        this.mEmail = new String[arrayList3.size()];
        this.mAddress = new String[arrayList2.size()];
        this.mLockStatus = new String[arrayList4.size()];
        this.mAduditDeciValue = new Integer[arrayList5.size()];
        this.mLockBackTime = new Long[arrayList6.size()];
        this.mAuditLongitude = new Double[arrayList7.size()];
        this.mAuditLatitude = new Double[arrayList7.size()];
        this.mArrTime = (Long[]) arrayList.toArray(this.mArrTime);
        this.mEmail = (String[]) arrayList3.toArray(this.mEmail);
        this.mAddress = (String[]) arrayList2.toArray(this.mAddress);
        this.mLockStatus = (String[]) arrayList4.toArray(this.mLockStatus);
        this.mAduditDeciValue = (Integer[]) arrayList5.toArray(this.mAduditDeciValue);
        this.mLockBackTime = (Long[]) arrayList6.toArray(this.mLockBackTime);
        this.mAuditLongitude = (Double[]) arrayList7.toArray(this.mAuditLongitude);
        this.mAuditLatitude = (Double[]) arrayList8.toArray(this.mAuditLatitude);
        sort(this.mArrTime, 0, arrayList.size() - 1, this.mEmail, this.mAddress, this.mLockStatus, this.mAduditDeciValue, this.mLockBackTime, this.mAuditLongitude, this.mAuditLatitude);
    }

    public ArrayList<String> getLockStatus() {
        return this.lockStatus;
    }

    public ArrayList<String> getEmail() {
        return this.email;
    }

    public ArrayList<Long> getTime() {
        return this.time;
    }

    public ArrayList<String> getAddress() {
        return this.address;
    }

    public ArrayList<Integer> getAuditDeciValue() {
        return this.auditDeciValue;
    }

    public ArrayList<Long> getLockBacktime() {
        return this.lockBacktime;
    }

    public ArrayList<Double> getLongitude() {
        return this.longitude;
    }

    public ArrayList<Double> getLatitude() {
        return this.latitude;
    }

    int partition(Long[] lArr, int i, int i2, String[] strArr, String[] strArr2, String[] strArr3, Integer[] numArr, Long[] lArr2, Double[] dArr, Double[] dArr2) {
        long longValue = lArr[i2].longValue();
        int i3 = i - 1;
        for (int i4 = i; i4 < i2; i4++) {
            if (lArr[i4].longValue() < longValue) {
                i3++;
                long longValue2 = lArr[i3].longValue();
                lArr[i3] = lArr[i4];
                lArr[i4] = Long.valueOf(longValue2);
                String str = strArr[i3];
                strArr[i3] = strArr[i4];
                strArr[i4] = str;
                String str2 = strArr2[i3];
                strArr2[i3] = strArr2[i4];
                strArr2[i4] = str2;
                String str3 = strArr3[i3];
                strArr3[i3] = strArr3[i4];
                strArr3[i4] = str3;
                Integer num = numArr[i3];
                numArr[i3] = numArr[i4];
                numArr[i4] = num;
                long longValue3 = lArr2[i3].longValue();
                lArr2[i3] = lArr2[i4];
                lArr2[i4] = Long.valueOf(longValue3);
                Double d = dArr[i3];
                dArr[i3] = dArr[i4];
                dArr[i4] = d;
                Double d2 = dArr2[i3];
                dArr2[i3] = dArr2[i4];
                dArr2[i4] = d2;
            }
        }
        int i5 = i3 + 1;
        long longValue4 = lArr[i5].longValue();
        lArr[i5] = lArr[i2];
        lArr[i2] = Long.valueOf(longValue4);
        String str4 = strArr[i5];
        strArr[i5] = strArr[i2];
        strArr[i2] = str4;
        String str5 = strArr2[i5];
        strArr2[i5] = strArr2[i2];
        strArr2[i2] = str5;
        String str6 = strArr3[i5];
        strArr3[i5] = strArr3[i2];
        strArr3[i2] = str6;
        Integer num2 = numArr[i5];
        numArr[i5] = numArr[i2];
        numArr[i2] = num2;
        long longValue5 = lArr2[i5].longValue();
        lArr2[i5] = lArr2[i2];
        lArr2[i2] = Long.valueOf(longValue5);
        Double d3 = dArr[i5];
        dArr[i5] = dArr[i2];
        dArr[i2] = d3;
        Double d4 = dArr2[i5];
        dArr2[i5] = dArr2[i2];
        dArr2[i2] = d4;
        return i5;
    }

    void sort(Long[] lArr, int i, int i2, String[] strArr, String[] strArr2, String[] strArr3, Integer[] numArr, Long[] lArr2, Double[] dArr, Double[] dArr2) {
        if (i < i2) {
            int partition = partition(lArr, i, i2, strArr, strArr2, strArr3, numArr, lArr2, dArr, dArr2);
            sort(lArr, i, partition - 1, strArr, strArr2, strArr3, numArr, lArr2, dArr, dArr2);
            sort(lArr, partition + 1, i2, strArr, strArr2, strArr3, numArr, lArr2, dArr, dArr2);
            return;
        }
        this.time.clear();
        this.address.clear();
        this.email.clear();
        this.lockStatus.clear();
        this.auditDeciValue.clear();
        this.lockBacktime.clear();
        this.longitude.clear();
        this.latitude.clear();
        Collections.addAll(this.lockStatus, strArr3);
        Collections.addAll(this.email, strArr);
        Collections.addAll(this.address, strArr2);
        Collections.addAll(this.time, lArr);
        Collections.addAll(this.auditDeciValue, numArr);
        Collections.addAll(this.lockBacktime, lArr2);
        Collections.addAll(this.longitude, dArr);
        Collections.addAll(this.latitude, dArr2);
        System.out.println("Hello checking the array list size:" + this.time.size());
    }
}
