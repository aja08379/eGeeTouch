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
public class RetrofitClient {
    private static final String API_PASSWORD = "0bdc90894b4617ebb227e34438c71eed-7caa9475-f24987de";
    private static final String API_USERNAME = "api";
    private static final String AUTH = "Basic " + Base64.encodeToString("api:0bdc90894b4617ebb227e34438c71eed-7caa9475-f24987de".getBytes(), 2);
    private static final String BASE_URL = "https://api.mailgun.net/v3/mail.egeetouch.com/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new Interceptor() { // from class: com.egeetouch.egeetouch_manager.RetrofitClient.1
        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request.newBuilder().header("Authorization", RetrofitClient.AUTH).method(request.method(), request.body()).build());
        }
    }).build()).build();

    RetrofitClient() {
    }

    public static synchronized RetrofitClient getInstance() {
        RetrofitClient retrofitClient;
        synchronized (RetrofitClient.class) {
            if (mInstance == null) {
                mInstance = new RetrofitClient();
            }
            retrofitClient = mInstance;
        }
        return retrofitClient;
    }

    public Retrofit getClient() {
        return this.retrofit;
    }

    public MailgunAPI getApi() {
        return (MailgunAPI) this.retrofit.create(MailgunAPI.class);
    }
}
