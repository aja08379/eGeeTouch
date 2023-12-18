package com.samsung.android.sdk.accessory;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Log;
import com.facebook.internal.NativeProtocol;
import com.samsung.android.sdk.accessory.SAAgentV2;
import java.util.List;
/* loaded from: classes2.dex */
public class SAJobService extends JobService implements com.samsung.android.sdk.accessory.a {
    private static volatile int a;

    /* loaded from: classes2.dex */
    static class a implements SAAgentV2.RequestAgentCallback {
        private int a;
        private JobParameters b;
        private SAJobService c;

        public a(int i, JobParameters jobParameters, SAJobService sAJobService) {
            this.a = i;
            this.b = jobParameters;
            this.c = sAJobService;
        }

        @Override // com.samsung.android.sdk.accessory.SAAgentV2.RequestAgentCallback
        public final void onAgentAvailable(SAAgentV2 sAAgentV2) {
            SAJobService.a(this.c, this.a, sAAgentV2, this.b);
        }

        @Override // com.samsung.android.sdk.accessory.SAAgentV2.RequestAgentCallback
        public final void onError(int i, String str) {
            Log.e("[SA_SDK]SAJobService", "Request failed. Type = " + this.a + ". ErrorCode : " + i + ". ErrorMsg: " + str);
        }
    }

    private static void a(Context context, String str, String str2, long j, String str3, SAPeerAgent sAPeerAgent) {
        ComponentName componentName = new ComponentName(context, SAJobService.class);
        int i = a;
        a = i + 1;
        JobInfo.Builder builder = new JobInfo.Builder(i, componentName);
        builder.setOverrideDeadline(3000L);
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString(NativeProtocol.WEB_DIALOG_ACTION, str);
        persistableBundle.putString("agentImplclass", str2);
        persistableBundle.putLong("transactionId", j);
        persistableBundle.putString("agentId", str3);
        if (sAPeerAgent == null) {
            persistableBundle.putStringArray("peerAgent", null);
        } else {
            List<String> d = sAPeerAgent.d();
            persistableBundle.putStringArray("peerAgent", (String[]) d.toArray(new String[d.size()]));
        }
        builder.setExtras(persistableBundle);
        ((JobScheduler) context.getSystemService("jobscheduler")).schedule(builder.build());
    }

    static /* synthetic */ void a(SAJobService sAJobService, int i, SAAgentV2 sAAgentV2, JobParameters jobParameters) {
        if (i == 1) {
            sAAgentV2.a(jobParameters, sAJobService);
        } else if (i == 2) {
            sAAgentV2.a();
        }
    }

    private void a(String str, a aVar) {
        SAAgentV2.requestAgent(getApplicationContext(), str, aVar);
    }

    public static void scheduleMessageJob(Context context, String str, long j, String str2, SAPeerAgent sAPeerAgent) {
        Log.d("[SA_SDK]SAJobService", "Schedule Message indication Job for class: ".concat(String.valueOf(str)));
        a(context, SAMessage.ACTION_ACCESSORY_MESSAGE_RECEIVED, str, j, str2, sAPeerAgent);
    }

    public static void scheduleSCJob(Context context, String str, long j, String str2, SAPeerAgent sAPeerAgent) {
        Log.d("[SA_SDK]SAJobService", "Schedule SC indication Job for class: ".concat(String.valueOf(str)));
        a(context, "com.samsung.accessory.action.SERVICE_CONNECTION_REQUESTED", str, j, str2, sAPeerAgent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.samsung.android.sdk.accessory.a
    public void onJobFinished(JobParameters jobParameters) {
        jobFinished(jobParameters, false);
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        String string;
        String string2;
        a aVar;
        PersistableBundle extras = jobParameters.getExtras();
        if (extras != null && (string = extras.getString(NativeProtocol.WEB_DIALOG_ACTION)) != null) {
            if ("com.samsung.accessory.action.SERVICE_CONNECTION_REQUESTED".equalsIgnoreCase(string)) {
                Log.d("[SA_SDK]SAJobService", "Received incoming connection indication");
                string2 = jobParameters.getExtras().getString("agentImplclass");
                aVar = new a(1, jobParameters, this);
            } else if (SAMessage.ACTION_ACCESSORY_MESSAGE_RECEIVED.equalsIgnoreCase(string)) {
                Log.d("[SA_SDK]SAJobService", "Received message received indication");
                string2 = jobParameters.getExtras().getString("agentImplclass");
                aVar = new a(2, jobParameters, this);
            }
            a(string2, aVar);
        }
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
