package com.egeetouch.egeetouch_manager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/* loaded from: classes.dex */
public interface MailgunAPI {
    @FormUrlEncoded
    @POST("messages")
    Call<ResponseBody> sendEmail(@Field("from") String str, @Field("to") String str2, @Field("subject") String str3, @Field("text") String str4, @Field("template") String str5, @Field("h:X-Mailgun-Variables") String str6);
}
