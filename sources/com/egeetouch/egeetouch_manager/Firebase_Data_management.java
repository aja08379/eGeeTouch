package com.egeetouch.egeetouch_manager;

import android.os.Build;
import android.util.Log;
import com.facebook.appevents.codeless.internal.Constants;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.ServerValues;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class Firebase_Data_management {
    public static List<String> AuditTarilTagID;
    public static List<Integer> AuditTrailDeciValueList;
    public static List<Long> AuditTrailLockBackTimeList;
    public static List<String> AuditTrailPasscodeList;
    public static List<Long> AuditTrailTimeList;
    public static List<Integer> AuditTrailType;
    private static FirebaseDatabase commercialDB;
    public static FirebaseDatabase database;
    public static List<String> listRecipientEmail = new ArrayList();
    public static List<String> listRecipientName = new ArrayList();
    public static List<Boolean> listRecipientExists = new ArrayList();
    public static long number_of_user = 0;
    public static List<String> fivelistRecipientName = new ArrayList();
    public static int RecipientCount = 0;
    private static double SystemTimestamp = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    private static double CurrentTimeStamp = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    public static long TotalAuditTrail = 0;
    public static long AuditTrailNameCounter = 0;
    private static String UpdateFromUID = "";
    private static String UpdateFromEmail = "";
    static HashMap<Integer, Object> passcodeNameMap = new HashMap<>();
    static HashMap<Integer, Object> passcodeUpdateTime = new HashMap<>();
    static HashMap<Integer, Object> passcodeValueMap = new HashMap<>();
    static HashMap<Integer, Object> tagNameMap = new HashMap<>();
    static HashMap<Integer, Object> tagUpdateTime = new HashMap<>();
    private static long AuditTrailPasscodeCounter = 0;

    static /* synthetic */ long access$108() {
        long j = AuditTrailPasscodeCounter;
        AuditTrailPasscodeCounter = 1 + j;
        return j;
    }

    public static void fetch_Recipient(String str) {
        System.out.println("Hello test new class!!!" + str);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        database = firebaseDatabase;
        firebaseDatabase.getReference("userFriendList").child(str).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Firebase_Data_management.1
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Firebase_Data_management.number_of_user = 0L;
                    Firebase_Data_management.RecipientCount = 0;
                    Firebase_Data_management.listRecipientEmail.clear();
                    Firebase_Data_management.listRecipientName.clear();
                    Firebase_Data_management.listRecipientExists.clear();
                    Firebase_Data_management.fivelistRecipientName.clear();
                    Firebase_Data_management.number_of_user = dataSnapshot.getChildrenCount();
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        System.out.println("Hello test new class:" + dataSnapshot2.getValue().toString());
                        Firebase_Data_management.listRecipientEmail.add(dataSnapshot2.getValue().toString());
                        Firebase_Data_management.listRecipientName.add(dataSnapshot2.getKey().toString());
                        Firebase_Data_management.listRecipientExists.add(true);
                        if (Firebase_Data_management.RecipientCount < 5) {
                            Firebase_Data_management.fivelistRecipientName.add(dataSnapshot2.getKey().toString());
                            Firebase_Data_management.RecipientCount++;
                        }
                    }
                } else {
                    Firebase_Data_management.fivelistRecipientName.clear();
                    Firebase_Data_management.listRecipientEmail.clear();
                    Firebase_Data_management.listRecipientName.clear();
                    Firebase_Data_management.listRecipientExists.clear();
                    System.out.println("Hello no recipient Found");
                }
                if (Firebase_Data_management.listRecipientName.size() > 0) {
                    for (final int i = 0; i < Firebase_Data_management.listRecipientEmail.size(); i++) {
                        FirebaseDatabase.getInstance().getReference("registeredUsersOnPlatform").orderByValue().equalTo(Firebase_Data_management.listRecipientEmail.get(i)).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Firebase_Data_management.1.1
                            @Override // com.google.firebase.database.ValueEventListener
                            public void onCancelled(DatabaseError databaseError) {
                            }

                            @Override // com.google.firebase.database.ValueEventListener
                            public void onDataChange(DataSnapshot dataSnapshot3) {
                                if (dataSnapshot3.exists()) {
                                    return;
                                }
                                Firebase_Data_management.listRecipientExists.add(i, false);
                            }
                        });
                    }
                }
            }
        });
    }

    public static void sendAppRatingReview(int i, Float f, String str, String str2, String str3, String str4) {
        database = FirebaseDatabase.getInstance();
        DatabaseReference push = database.getReference("appRatingAndReviews").child("androidApp").child("appVersionCode" + i).push();
        HashMap hashMap = new HashMap();
        hashMap.put("rating", f);
        hashMap.put("review", str);
        hashMap.put("userEmail", str2);
        hashMap.put("userUid", str3);
        hashMap.put("userDeviceInfo", "OS: " + Build.VERSION.RELEASE + ", name: " + getDeviceName());
        hashMap.put(RemoteConfigConstants.RequestFieldKey.APP_VERSION, str4);
        hashMap.put("updateTime", MainActivity.currentTimestamp);
        push.setValue(hashMap);
    }

    public static void sendDirectionCodeToServer(String str, String str2, String str3, String str4, int i) {
        FirebaseDatabase commercialDBInstance = getCommercialDBInstance();
        CurrentTimeStamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() * 1000);
        DatabaseReference child = commercialDBInstance.getReference("directionalPassCodeDirectory").child(str);
        DatabaseReference push = child.push();
        HashMap hashMap = new HashMap();
        hashMap.put("directionalEmoji", str2);
        hashMap.put("directionalPasscode", str3);
        hashMap.put(FirebaseAnalytics.Param.INDEX, Integer.valueOf(i));
        hashMap.put("name", str4);
        hashMap.put(ServerValues.NAME_OP_TIMESTAMP, Double.valueOf(CurrentTimeStamp));
        hashMap.put("id", push.getKey());
        child.child(push.getKey()).setValue(hashMap);
    }

    public static void sendingTagAuditTrailToFirebase(List<String> list, List<Long> list2, String str, String str2, String str3, List<Integer> list3) {
        database = FirebaseDatabase.getInstance();
        SystemTimestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() * 1000);
        AuditTrailNameCounter = 0L;
        TotalAuditTrail = 0L;
        tagNameMap.clear();
        tagUpdateTime.clear();
        TotalAuditTrail = list.size();
        AuditTarilTagID = list;
        AuditTrailTimeList = list2;
        AuditTrailType = list3;
        UpdateFromEmail = str2;
        UpdateFromUID = str;
        for (int size = list.size() - 1; size >= 0; size--) {
            MainActivity.TagAuditTrailBackup_done = true;
            SystemTimestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() * 1000);
            String upperCase = list.get(size).toUpperCase();
            FirebaseDatabase firebaseDatabase = database;
            if (BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                firebaseDatabase = FirebaseDatabase.getInstance(FirebaseApp.getInstance("industrial"));
            }
            firebaseDatabase.getReference("tagDirectory").child(str3).orderByChild("tagId").equalTo(upperCase).addListenerForSingleValueEvent(new AnonymousClass2(firebaseDatabase, str3, size, upperCase));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.egeetouch.egeetouch_manager.Firebase_Data_management$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static class AnonymousClass2 implements ValueEventListener {
        final /* synthetic */ String val$LockSerial;
        final /* synthetic */ FirebaseDatabase val$finalTagDatabase;
        final /* synthetic */ int val$position;
        final /* synthetic */ String val$tag_id;

        @Override // com.google.firebase.database.ValueEventListener
        public void onCancelled(DatabaseError databaseError) {
        }

        AnonymousClass2(FirebaseDatabase firebaseDatabase, String str, int i, String str2) {
            this.val$finalTagDatabase = firebaseDatabase;
            this.val$LockSerial = str;
            this.val$position = i;
            this.val$tag_id = str2;
        }

        @Override // com.google.firebase.database.ValueEventListener
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                    this.val$finalTagDatabase.getReference("tagDirectory").child(this.val$LockSerial).child(dataSnapshot2.getKey()).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Firebase_Data_management.2.1
                        @Override // com.google.firebase.database.ValueEventListener
                        public void onCancelled(DatabaseError databaseError) {
                        }

                        @Override // com.google.firebase.database.ValueEventListener
                        public void onDataChange(DataSnapshot dataSnapshot3) {
                            if (dataSnapshot3.exists()) {
                                for (DataSnapshot dataSnapshot4 : dataSnapshot3.getChildren()) {
                                    if (dataSnapshot4.getKey().equals("tagName")) {
                                        Firebase_Data_management.AuditTrailNameCounter++;
                                        Firebase_Data_management.tagNameMap.put(Integer.valueOf(AnonymousClass2.this.val$position), dataSnapshot4.getValue().toString());
                                    } else if (dataSnapshot4.getKey().equals("timeAdded")) {
                                        Firebase_Data_management.tagUpdateTime.put(Integer.valueOf(AnonymousClass2.this.val$position), dataSnapshot4.getValue().toString());
                                    }
                                }
                                if (Firebase_Data_management.AuditTrailNameCounter == Firebase_Data_management.TotalAuditTrail) {
                                    Firebase_Data_management.updateTagAuditToServer(AnonymousClass2.this.val$LockSerial);
                                }
                            }
                        }
                    });
                }
                return;
            }
            BLEService.commercialDatabase.getReference("tagDirectory").child(this.val$LockSerial).orderByChild("tagId").equalTo(this.val$tag_id).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Firebase_Data_management.2.2
                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot dataSnapshot3) {
                    if (dataSnapshot3.exists()) {
                        for (DataSnapshot dataSnapshot4 : dataSnapshot3.getChildren()) {
                            BLEService.commercialDatabase.getReference("tagDirectory").child(AnonymousClass2.this.val$LockSerial).child(dataSnapshot4.getKey()).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Firebase_Data_management.2.2.1
                                @Override // com.google.firebase.database.ValueEventListener
                                public void onCancelled(DatabaseError databaseError) {
                                }

                                @Override // com.google.firebase.database.ValueEventListener
                                public void onDataChange(DataSnapshot dataSnapshot5) {
                                    if (dataSnapshot5.exists()) {
                                        for (DataSnapshot dataSnapshot6 : dataSnapshot5.getChildren()) {
                                            if (dataSnapshot6.getKey().equals("tagName")) {
                                                Firebase_Data_management.AuditTrailNameCounter++;
                                                Firebase_Data_management.tagNameMap.put(Integer.valueOf(AnonymousClass2.this.val$position), dataSnapshot6.getValue().toString());
                                            } else if (dataSnapshot6.getKey().equals("timeAdded")) {
                                                Firebase_Data_management.tagUpdateTime.put(Integer.valueOf(AnonymousClass2.this.val$position), dataSnapshot6.getValue().toString());
                                            }
                                        }
                                        if (Firebase_Data_management.AuditTrailNameCounter == Firebase_Data_management.TotalAuditTrail) {
                                            Firebase_Data_management.updateTagAuditToServer(AnonymousClass2.this.val$LockSerial);
                                        }
                                    }
                                }
                            });
                        }
                        return;
                    }
                    Firebase_Data_management.AuditTrailNameCounter++;
                    Firebase_Data_management.tagNameMap.put(Integer.valueOf(AnonymousClass2.this.val$position), "N.A");
                    Firebase_Data_management.tagUpdateTime.put(Integer.valueOf(AnonymousClass2.this.val$position), "N.A");
                    if (Firebase_Data_management.AuditTrailNameCounter == Firebase_Data_management.TotalAuditTrail) {
                        Firebase_Data_management.updateTagAuditToServer(AnonymousClass2.this.val$LockSerial);
                    }
                }
            });
        }
    }

    public static void updateTagAuditToServer(String str) {
        for (int size = AuditTarilTagID.size() - 1; size >= 0; size--) {
            SystemTimestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() * 1000);
            if (!tagUpdateTime.get(Integer.valueOf(size)).toString().contains("N.A")) {
                if (Long.valueOf(tagUpdateTime.get(Integer.valueOf(size)).toString()).longValue() < AuditTrailTimeList.get(size).longValue() * 1000) {
                    System.out.println("Hello checking the time of the Tag updateTime:" + tagUpdateTime.get(Integer.valueOf(size)) + " A.T:" + (AuditTrailTimeList.get(size).longValue() * 1000) + " i:" + size);
                    DatabaseReference push = database.getReference("tagAuditTrail").child(str).push();
                    HashMap hashMap = new HashMap();
                    hashMap.put("tagId", AuditTarilTagID.get(size).toUpperCase());
                    hashMap.put("auditTrailTime", Long.valueOf(AuditTrailTimeList.get(size).longValue() * 1000));
                    hashMap.put("UIDOfUser", UpdateFromUID);
                    hashMap.put("userEmail", UpdateFromEmail);
                    hashMap.put("updateTime", Double.valueOf(SystemTimestamp));
                    hashMap.put("tagName", tagNameMap.get(Integer.valueOf(size)));
                    hashMap.put("auditDeciValue", AuditTrailType.get(size));
                    push.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.egeetouch.egeetouch_manager.Firebase_Data_management.3
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public void onSuccess(Void r3) {
                            customProgressBar.closeDialog(0L);
                            UI_BLE.BLE_UI = 30;
                        }
                    });
                } else {
                    System.out.println("Hello checking the time of the Tag updateTime else:" + tagUpdateTime.get(Integer.valueOf(size)) + " A.T:" + (AuditTrailTimeList.get(size).longValue() * 1000) + " i:" + size);
                    DatabaseReference push2 = database.getReference("tagAuditTrail").child(str).push();
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("tagId", AuditTarilTagID.get(size).toUpperCase());
                    hashMap2.put("auditTrailTime", Long.valueOf(AuditTrailTimeList.get(size).longValue() * 1000));
                    hashMap2.put("UIDOfUser", UpdateFromUID);
                    hashMap2.put("userEmail", UpdateFromEmail);
                    hashMap2.put("updateTime", Double.valueOf(SystemTimestamp));
                    hashMap2.put("tagName", "N.A");
                    hashMap2.put("auditDeciValue", AuditTrailType.get(size));
                    push2.setValue(hashMap2).addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.egeetouch.egeetouch_manager.Firebase_Data_management.4
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public void onSuccess(Void r3) {
                            customProgressBar.closeDialog(0L);
                            UI_BLE.BLE_UI = 30;
                        }
                    });
                }
            } else {
                DatabaseReference push3 = database.getReference("tagAuditTrail").child(str).push();
                HashMap hashMap3 = new HashMap();
                hashMap3.put("tagId", AuditTarilTagID.get(size).toUpperCase());
                hashMap3.put("auditTrailTime", Long.valueOf(AuditTrailTimeList.get(size).longValue() * 1000));
                hashMap3.put("UIDOfUser", UpdateFromUID);
                hashMap3.put("userEmail", UpdateFromEmail);
                hashMap3.put("updateTime", Double.valueOf(SystemTimestamp));
                hashMap3.put("tagName", "N.A");
                hashMap3.put("auditDeciValue", AuditTrailType.get(size));
                push3.setValue(hashMap3).addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.egeetouch.egeetouch_manager.Firebase_Data_management.5
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public void onSuccess(Void r3) {
                        customProgressBar.closeDialog(0L);
                        UI_BLE.BLE_UI = 30;
                    }
                });
            }
        }
    }

    public static void deletePasscode(String str, int i) {
        DatabaseReference child = getCommercialDBInstance().getReference("directionalPassCodeDirectory").child(str);
        child.push();
        child.orderByChild(FirebaseAnalytics.Param.INDEX).equalTo(i).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Firebase_Data_management.6
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        dataSnapshot2.getRef().removeValue();
                        UI_BLE.BLE_UI = 33;
                    }
                }
            }
        });
    }

    public static void sendingPasscodeAuditTrailToFirebase(List<Integer> list, List<String> list2, List<Long> list3, List<Integer> list4, List<Long> list5, String str, String str2, final String str3) {
        commercialDB = getCommercialDBInstance();
        SystemTimestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() * 1000);
        AuditTrailPasscodeCounter = 0L;
        TotalAuditTrail = 0L;
        passcodeNameMap.clear();
        passcodeUpdateTime.clear();
        TotalAuditTrail = list2.size();
        AuditTrailPasscodeList = list2;
        AuditTrailTimeList = list3;
        AuditTrailDeciValueList = list4;
        AuditTrailLockBackTimeList = list5;
        UpdateFromEmail = str2;
        UpdateFromUID = str;
        for (final int size = list2.size() - 1; size >= 0; size--) {
            SystemTimestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() * 1000);
            final String str4 = list2.get(size);
            passcodeValueMap.put(Integer.valueOf(size), str4);
            commercialDB.getReference("directionalPassCodeDirectory").child(str3).orderByChild("directionalPasscode").equalTo(str4).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Firebase_Data_management.7
                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.exists()) {
                        Integer.valueOf((int) Firebase_Data_management.AuditTrailPasscodeCounter).intValue();
                        Firebase_Data_management.access$108();
                        Firebase_Data_management.passcodeNameMap.put(Integer.valueOf(size), "N.A");
                        Firebase_Data_management.passcodeUpdateTime.put(Integer.valueOf(size), "N.A");
                        System.out.println("Hello checking the Passcode hash: N.A passcode: " + str4 + " pos:" + size + " time: " + Firebase_Data_management.passcodeUpdateTime.get(Integer.valueOf(size)));
                        if (Firebase_Data_management.AuditTrailPasscodeCounter == Firebase_Data_management.TotalAuditTrail) {
                            Firebase_Data_management.findPasscodeAuditCount(str3);
                            return;
                        }
                        return;
                    }
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        Firebase_Data_management.commercialDB.getReference("directionalPassCodeDirectory").child(str3).child(dataSnapshot2.getKey()).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Firebase_Data_management.7.1
                            @Override // com.google.firebase.database.ValueEventListener
                            public void onCancelled(DatabaseError databaseError) {
                            }

                            @Override // com.google.firebase.database.ValueEventListener
                            public void onDataChange(DataSnapshot dataSnapshot3) {
                                System.out.println("Hello checking the position values- : " + size + " Passcode:" + str4);
                                if (dataSnapshot3.exists()) {
                                    System.out.println("Hello checking the position values-- : " + size + " Passcode:Above");
                                    String str5 = " ";
                                    for (DataSnapshot dataSnapshot4 : dataSnapshot3.getChildren()) {
                                        if (dataSnapshot4.getKey().equals("name")) {
                                            str5 = dataSnapshot4.getValue().toString();
                                            Firebase_Data_management.passcodeNameMap.put(Integer.valueOf(size), str5);
                                        } else if (dataSnapshot4.getKey().equals(ServerValues.NAME_OP_TIMESTAMP)) {
                                            Firebase_Data_management.passcodeUpdateTime.put(Integer.valueOf(size), Long.valueOf((long) Double.valueOf(dataSnapshot4.getValue().toString()).doubleValue()));
                                            System.out.println("Hello checking the Passcode hash:   passcode: " + str5 + " pos:" + size + " time: " + Firebase_Data_management.passcodeUpdateTime.get(Integer.valueOf(size)));
                                            Firebase_Data_management.access$108();
                                        }
                                    }
                                    System.out.println("Hello checking the Passcode updateTime :" + Firebase_Data_management.passcodeUpdateTime.get(Integer.valueOf(size)) + " Postion:" + size);
                                    if (Firebase_Data_management.AuditTrailPasscodeCounter == Firebase_Data_management.TotalAuditTrail) {
                                        Firebase_Data_management.findPasscodeAuditCount(str3);
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void findPasscodeAuditCount(final String str) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        database = firebaseDatabase;
        firebaseDatabase.getReference("FirmwareAuditTrailCount").child("directionalPassCodeAuditTrailCount").child(str).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Firebase_Data_management.8
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    Firebase_Data_management.updatePasscodeAuditToServer(0, str);
                    return;
                }
                HashMap hashMap = (HashMap) dataSnapshot.getValue();
                int intValue = (hashMap.containsKey("lastUpdatedCount") ? Integer.valueOf(hashMap.get("lastUpdatedCount").toString()).intValue() : 0) + 1;
                Firebase_Data_management.updatePasscodeAuditToServer(intValue <= 119 ? intValue : 0, str);
            }
        });
    }

    public static void claimRedeemptionUpdateFB(String str, String str2) {
        CurrentTimeStamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() * 1000);
        DatabaseReference reference = database.getReference("claimedFreeUpgrade");
        HashMap hashMap = new HashMap();
        hashMap.put("email", str);
        hashMap.put("redeemed", true);
        hashMap.put(ServerValues.NAME_OP_TIMESTAMP, Double.valueOf(CurrentTimeStamp));
        hashMap.put("platform", Constants.PLATFORM);
        reference.child(str2).updateChildren(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updatePasscodeAuditToServer(int i, String str) {
        int i2;
        String str2;
        String str3 = str;
        database = FirebaseDatabase.getInstance();
        int size = AuditTrailPasscodeList.size() - 1;
        int i3 = i;
        while (size >= 0) {
            SystemTimestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() * 1000);
            String valueOf = String.valueOf(i3);
            System.out.println("Hello checking the Passcode updateTimeSize : " + passcodeUpdateTime.size() + " i: " + size + " Passcode:" + passcodeValueMap.get(Integer.valueOf(size)) + " UpdateTime: " + passcodeUpdateTime.get(Integer.valueOf(size)));
            if (passcodeUpdateTime.get(Integer.valueOf(size)) != null) {
                i2 = i3;
                if (!passcodeUpdateTime.get(Integer.valueOf(size)).toString().contains("N.A")) {
                    if (Long.valueOf(passcodeUpdateTime.get(Integer.valueOf(size)).toString()).longValue() < AuditTrailTimeList.get(size).longValue() * 1000) {
                        DatabaseReference child = database.getReference("directionalPassCodeAuditTrail").child(str3);
                        HashMap hashMap = new HashMap();
                        hashMap.put("directionalPasscode", passcodeValueMap.get(Integer.valueOf(size)));
                        hashMap.put("auditTrailTime", Long.valueOf(AuditTrailTimeList.get(size).longValue() * 1000));
                        hashMap.put("UIDOfUser", UpdateFromUID);
                        hashMap.put("userEmail", UpdateFromEmail);
                        hashMap.put("updateTime", Double.valueOf(SystemTimestamp));
                        hashMap.put("passcodeName", passcodeNameMap.get(Integer.valueOf(size)));
                        hashMap.put("auditDeciValue", AuditTrailDeciValueList.get(size));
                        hashMap.put("lockBackTime", AuditTrailLockBackTimeList.get(size));
                        child.child(valueOf).setValue(hashMap);
                        System.out.println("Hello checking the Passcode 1 name: " + hashMap);
                    } else {
                        DatabaseReference child2 = database.getReference("directionalPassCodeAuditTrail").child(str3);
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("directionalPasscode", passcodeValueMap.get(Integer.valueOf(size)));
                        hashMap2.put("auditTrailTime", Long.valueOf(AuditTrailTimeList.get(size).longValue() * 1000));
                        hashMap2.put("UIDOfUser", UpdateFromUID);
                        hashMap2.put("userEmail", UpdateFromEmail);
                        hashMap2.put("updateTime", Double.valueOf(SystemTimestamp));
                        hashMap2.put("auditDeciValue", AuditTrailDeciValueList.get(size));
                        hashMap2.put("lockBackTime", AuditTrailLockBackTimeList.get(size));
                        hashMap2.put("passcodeName", "N.A");
                        child2.child(valueOf).setValue(hashMap2);
                        System.out.println("Hello checking the Passcode 2 name: " + hashMap2);
                    }
                } else {
                    DatabaseReference child3 = database.getReference("directionalPassCodeAuditTrail").child(str);
                    HashMap hashMap3 = new HashMap();
                    hashMap3.put("directionalPasscode", passcodeValueMap.get(Integer.valueOf(size)));
                    hashMap3.put("auditTrailTime", Long.valueOf(AuditTrailTimeList.get(size).longValue() * 1000));
                    hashMap3.put("UIDOfUser", UpdateFromUID);
                    hashMap3.put("userEmail", UpdateFromEmail);
                    hashMap3.put("updateTime", Double.valueOf(SystemTimestamp));
                    hashMap3.put("auditDeciValue", AuditTrailDeciValueList.get(size));
                    hashMap3.put("lockBackTime", AuditTrailLockBackTimeList.get(size));
                    hashMap3.put("passcodeName", "N.A");
                    child3.child(valueOf).setValue(hashMap3);
                    System.out.println("Hello checking the Passcode 2 name: " + hashMap3);
                }
            } else {
                i2 = i3;
                DatabaseReference child4 = database.getReference("directionalPassCodeAuditTrail").child(str);
                HashMap hashMap4 = new HashMap();
                hashMap4.put("directionalPasscode", passcodeValueMap.get(Integer.valueOf(size)));
                hashMap4.put("auditTrailTime", Long.valueOf(AuditTrailTimeList.get(size).longValue() * 1000));
                hashMap4.put("UIDOfUser", UpdateFromUID);
                hashMap4.put("userEmail", UpdateFromEmail);
                hashMap4.put("updateTime", Double.valueOf(SystemTimestamp));
                hashMap4.put("auditDeciValue", AuditTrailDeciValueList.get(size));
                hashMap4.put("lockBackTime", AuditTrailLockBackTimeList.get(size));
                hashMap4.put("passcodeName", "N.A");
                child4.child(valueOf).setValue(hashMap4);
                System.out.println("Hello checking the Passcode 2 name: " + hashMap4);
            }
            if (size == 0) {
                str2 = str;
                DatabaseReference child5 = database.getReference("FirmwareAuditTrailCount").child("directionalPassCodeAuditTrailCount").child(str2);
                HashMap hashMap5 = new HashMap();
                hashMap5.put("UIDOfUser", UpdateFromUID);
                hashMap5.put("userEmail", UpdateFromEmail);
                hashMap5.put("updateTime", Double.valueOf(SystemTimestamp));
                hashMap5.put("lastUpdatedCount", Integer.valueOf(i2));
                child5.setValue(hashMap5);
            } else {
                str2 = str;
            }
            int i4 = i2;
            System.out.println("Hello checking the counter value : " + i4);
            int i5 = i4 + 1;
            if (i5 > 119) {
                i5 = 0;
            }
            size--;
            String str4 = str2;
            i3 = i5;
            str3 = str4;
        }
    }

    public static FirebaseDatabase getCommercialDBInstance() {
        return commercialDB;
    }

    public static void DeclareCommercialDBInstance() {
        FirebaseOptions build = new FirebaseOptions.Builder().setApplicationId("1:793477089265:android:2c6bbd1d22610f6144e650").setDatabaseUrl("https://egeetouchindustrial.firebaseio.com/").setApiKey("AIzaSyACfHrrc0ARWI_iyx9XRcFE-1Gk6AHtmc8").build();
        try {
            System.out.println("what is inside options");
            commercialDB = FirebaseDatabase.getInstance(FirebaseApp.initializeApp(MainActivity.context, build, "commercial"));
        } catch (Exception e) {
            Log.e("Firebase_DB", e.toString());
        }
    }

    public static void DeleteLockSerialFromAdminLocks(String str, String str2) {
        database = FirebaseDatabase.getInstance();
    }

    public static String getDeviceName() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.toLowerCase().startsWith(str.toLowerCase())) {
            return capitalize(str2);
        }
        return capitalize(str) + " " + str2;
    }

    private static String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char charAt = str.charAt(0);
        return Character.isUpperCase(charAt) ? str : Character.toUpperCase(charAt) + str.substring(1);
    }
}
