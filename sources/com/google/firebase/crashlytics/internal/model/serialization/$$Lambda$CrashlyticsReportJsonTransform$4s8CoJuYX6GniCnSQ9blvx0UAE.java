package com.google.firebase.crashlytics.internal.model.serialization;

import android.util.JsonReader;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform;
/* compiled from: lambda */
/* renamed from: com.google.firebase.crashlytics.internal.model.serialization.-$$Lambda$CrashlyticsReportJsonTransform$4s8CoJuYX6GniCnSQ9blv-x0UAE  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$CrashlyticsReportJsonTransform$4s8CoJuYX6GniCnSQ9blvx0UAE implements CrashlyticsReportJsonTransform.ObjectParser {
    public static final /* synthetic */ $$Lambda$CrashlyticsReportJsonTransform$4s8CoJuYX6GniCnSQ9blvx0UAE INSTANCE = new $$Lambda$CrashlyticsReportJsonTransform$4s8CoJuYX6GniCnSQ9blvx0UAE();

    private /* synthetic */ $$Lambda$CrashlyticsReportJsonTransform$4s8CoJuYX6GniCnSQ9blvx0UAE() {
    }

    @Override // com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform.ObjectParser
    public final Object parse(JsonReader jsonReader) {
        CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame parseEventFrame;
        parseEventFrame = CrashlyticsReportJsonTransform.parseEventFrame(jsonReader);
        return parseEventFrame;
    }
}
