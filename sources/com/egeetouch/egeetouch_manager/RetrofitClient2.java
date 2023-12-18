package com.egeetouch.egeetouch_manager;

import android.util.Base64;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/* loaded from: classes.dex */
public class RetrofitClient2 {
    private static final String API_PASSWORD = "d35bcbe40ff6ad2276655b388c6219a5-8d821f0c-a276f486";
    private static final String API_USERNAME = "api";
    private static final String AUTH = "Basic " + Base64.encodeToString("api:d35bcbe40ff6ad2276655b388c6219a5-8d821f0c-a276f486".getBytes(), 2);
    private static final String BASE_URL = "https://api.mailgun.net/v3/sandbox654a0e212c3f459bb8b8a424a9dc5cf0.mailgun.org/";
    private static RetrofitClient2 mInstance;
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new Interceptor() { // from class: com.egeetouch.egeetouch_manager.RetrofitClient2.1
        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request.newBuilder().header("Authorization", RetrofitClient2.AUTH).method(request.method(), request.body()).build());
        }
    }).build()).build();

    RetrofitClient2() {
    }

    public static synchronized RetrofitClient2 getInstance() {
        RetrofitClient2 retrofitClient2;
        synchronized (RetrofitClient2.class) {
            if (mInstance == null) {
                mInstance = new RetrofitClient2();
            }
            retrofitClient2 = mInstance;
        }
        return retrofitClient2;
    }

    public Retrofit getClient() {
        return this.retrofit;
    }

    public MailgunAPI getApi() {
        return (MailgunAPI) this.retrofit.create(MailgunAPI.class);
    }
}
