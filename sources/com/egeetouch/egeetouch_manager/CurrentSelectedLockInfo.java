package com.egeetouch.egeetouch_manager;
/* loaded from: classes.dex */
public class CurrentSelectedLockInfo {
    private static final CurrentSelectedLockInfo lockInstance = new CurrentSelectedLockInfo();
    int batteryLevel;
    int connectionState;
    int currentBroadcastTime;
    boolean isLockStateOpen;
    boolean isOfflineLock;
    boolean isSharedLock;
    String lockModel;
    String lockModelName;
    String lockName;
    String lockPassword;
    int lockRole;
    String lockSerialNumber;
    String lockUid;
    String lockVersion;
    String ownedByUid;
    String shareAccessToken;
    String shareLockMode;
    String sharedByEmail;
    int totalTagCount;
    int sharePermissionType = 0;
    int totalTagAuditTrailCount = 0;
    boolean isBatteryLevelReceived = false;
    boolean showBatteryAlert = true;
    public int lastUpdatedPowerMode = 0;

    public static CurrentSelectedLockInfo getInstance() {
        return lockInstance;
    }

    public String getLockName() {
        return this.lockName;
    }

    public void setLockName(String str) {
        this.lockName = str;
    }

    public String getLockPassword() {
        return this.lockPassword;
    }

    public void setLockPassword(String str) {
        this.lockPassword = str;
    }

    public String getLockSerialNumber() {
        return this.lockSerialNumber;
    }

    public void setLockSerialNumber(String str) {
        this.lockSerialNumber = str;
    }

    public String getLockModel() {
        return this.lockModel;
    }

    public void setLockModel(String str) {
        this.lockModel = str;
    }

    public String getShareLockMode() {
        return this.shareLockMode;
    }

    public void setShareLockMode(String str) {
        this.shareLockMode = str;
    }

    public String getLockVersion() {
        return this.lockVersion;
    }

    public void setLockVersion(String str) {
        this.lockVersion = str;
    }

    public String getLockModelName() {
        return this.lockModelName;
    }

    public void setLockModelName(String str) {
        this.lockModelName = str;
    }

    public boolean isSharedLock() {
        return this.isSharedLock;
    }

    public void setSharedLock(boolean z) {
        this.isSharedLock = z;
    }

    public int getLockRole() {
        return this.lockRole;
    }

    public void setLockRole(int i) {
        this.lockRole = i;
    }

    public int getTotalTagAuditTrailCount() {
        return this.totalTagAuditTrailCount;
    }

    public void setTotalTagAuditTrailCount(int i) {
        this.totalTagAuditTrailCount = i;
    }

    public int getTotalTagCount() {
        return this.totalTagCount;
    }

    public void setTotalTagCount(int i) {
        this.totalTagCount = i;
    }

    public int getCurrentBroadcastTime() {
        return this.currentBroadcastTime;
    }

    public void setCurrentBroadcastTime(int i) {
        this.currentBroadcastTime = i;
    }

    public String getLockUid() {
        return this.lockUid;
    }

    public void setLockUid(String str) {
        this.lockUid = str;
    }

    public String getOwnedByUid() {
        return this.ownedByUid;
    }

    public void setOwnedByUid(String str) {
        this.ownedByUid = str;
    }

    public int getSharePermissionType() {
        return this.sharePermissionType;
    }

    public void setSharePermissionType(int i) {
        this.sharePermissionType = i;
    }

    public String getSharedByEmail() {
        return this.sharedByEmail;
    }

    public void setSharedByEmail(String str) {
        this.sharedByEmail = str;
    }

    public String getShareAccessToken() {
        return this.shareAccessToken;
    }

    public void setShareAccessToken(String str) {
        this.shareAccessToken = str;
    }

    public boolean isOfflineLock() {
        return this.isOfflineLock;
    }

    public void setOfflineLock(boolean z) {
        this.isOfflineLock = z;
    }

    public int getConnectionState() {
        return this.connectionState;
    }

    public void setConnectionState(int i) {
        this.connectionState = i;
    }

    public boolean isBatteryLevelReceived() {
        return this.isBatteryLevelReceived;
    }

    public void setBatteryLevelReceived(boolean z) {
        this.isBatteryLevelReceived = z;
    }

    public boolean isShowBatteryAlert() {
        return this.showBatteryAlert;
    }

    public void setShowBatteryAlert(boolean z) {
        this.showBatteryAlert = z;
    }

    public int getLastUpdatedPowerMode() {
        return this.lastUpdatedPowerMode;
    }

    public void setLastUpdatedPowerMode(int i) {
        if (i == 0) {
            this.showBatteryAlert = false;
        } else if (this.lastUpdatedPowerMode == i) {
            this.showBatteryAlert = false;
        } else {
            this.showBatteryAlert = true;
        }
        this.lastUpdatedPowerMode = i;
    }
}
