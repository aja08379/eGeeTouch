package com.egeetouch.egeetouch_manager;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class LotoInfo {
    private static final LotoInfo lotoInsatance = new LotoInfo();
    public int deleteIndex;
    public String deletePasscodeValues;
    boolean isDeleteAdd_mode;
    boolean isNewLock;
    String newPasscode;
    String newPasscodeName;
    public int nextAvailabeIndex;
    int totalAvailablePasscode;
    int updateIndex;
    int updateMode;
    public int ADD_PASSCODE = BLEService.Request_ChangePasscode;
    public int DELETE_PASSCODE = BLEService.Request_tag_number_version2;
    public int CHANGE_PASSCODE = 206;
    List<Integer> updatedIndexList = new ArrayList();
    List<Integer> backupPasscodeList = new ArrayList();
    List<Integer> lockPasscodeList = new ArrayList();
    public List<Integer> syncPasscodeList = new ArrayList();
    public List<Integer> syncPasscodeIndexList = new ArrayList();

    public static LotoInfo getInstance() {
        return lotoInsatance;
    }

    public boolean isNewLock() {
        return this.isNewLock;
    }

    public void setNewLock(boolean z) {
        this.isNewLock = z;
    }

    public int getUpdateIndex() {
        return this.updateIndex;
    }

    public void setUpdateIndex(int i) {
        this.updateIndex = i;
    }

    public int getUpdateMode() {
        return this.updateMode;
    }

    public void setUpdateMode(int i) {
        this.updateMode = i;
    }

    public int getTotalAvailablePasscode() {
        return this.totalAvailablePasscode;
    }

    public void setTotalAvailablePasscode(int i) {
        this.totalAvailablePasscode = i;
    }

    public String getNewPasscode() {
        return this.newPasscode;
    }

    public void setNewPasscode(String str) {
        this.newPasscode = str;
    }

    public String getNewPasscodeName() {
        return this.newPasscodeName;
    }

    public void setNewPasscodeName(String str) {
        this.newPasscodeName = str;
    }

    public boolean isDeleteAdd_mode() {
        return this.isDeleteAdd_mode;
    }

    public void setDeleteAdd_mode(boolean z) {
        this.isDeleteAdd_mode = z;
    }

    public int getNextAvailabeIndex() {
        return this.nextAvailabeIndex;
    }

    public void setNextAvailabeIndex(int i) {
        this.nextAvailabeIndex = i;
    }

    public List<Integer> getUpdatedIndexList() {
        return this.updatedIndexList;
    }

    public void setUpdatedIndexList(List<Integer> list) {
        this.updatedIndexList = list;
        if (list == null) {
            this.nextAvailabeIndex = 0;
            return;
        }
        for (int i = 0; i < 80; i++) {
            if (!list.contains(Integer.valueOf(i))) {
                this.nextAvailabeIndex = i;
                return;
            }
        }
    }

    public void clearBackupPasscodeList() {
        this.backupPasscodeList.clear();
        for (int i = 0; i < 100; i++) {
            this.backupPasscodeList.add(i, 0);
        }
    }

    public void addStoredPasscodeToList(int i, int i2) {
        this.backupPasscodeList.set(i, Integer.valueOf(i2));
    }

    public List<Integer> getBackupPasscodeList() {
        return this.backupPasscodeList;
    }

    public void clearLockPasscodeList() {
        this.lockPasscodeList.clear();
        for (int i = 0; i < 100; i++) {
            this.lockPasscodeList.add(i, 0);
        }
    }

    public void addLockPasscodeToList(int i, int i2) {
        this.lockPasscodeList.set(i, Integer.valueOf(i2));
    }

    public List<Integer> getLockPasscodeList() {
        return this.lockPasscodeList;
    }

    public void clearSyncPasscode() {
        this.syncPasscodeIndexList.clear();
        this.syncPasscodeList.clear();
    }

    public void addSyncPasscode(int i, int i2) {
        this.syncPasscodeIndexList.add(Integer.valueOf(i));
        this.syncPasscodeList.add(Integer.valueOf(i2));
    }

    public List<Integer> getSyncPasscodeList() {
        return this.syncPasscodeList;
    }

    public List<Integer> getSyncPasscodeIndexList() {
        return this.syncPasscodeIndexList;
    }
}
