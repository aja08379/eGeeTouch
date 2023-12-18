package retrofit2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import okhttp3.Call;
import okhttp3.ResponseBody;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class HttpServiceMethod<ResponseT, ReturnT> extends ServiceMethod<ReturnT> {
    private final CallAdapter<ResponseT, ReturnT> callAdapter;
    private final Call.Factory callFactory;
    private final RequestFactory requestFactory;
    private final Converter<ResponseBody, ResponseT> responseConverter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <ResponseT, ReturnT> HttpServiceMethod<ResponseT, ReturnT> parseAnnotations(Retrofit retrofit, Method method, RequestFactory requestFactory) {
        CallAdapter createCallAdapter = createCallAdapter(retrofit, method);
        Type responseType = createCallAdapter.responseType();
        if (responseType == Response.class || responseType == okhttp3.Response.class) {
            throw Utils.methodError(method, "'" + Utils.getRawType(responseType).getName() + "' is not a valid response body type. Did you mean ResponseBody?", new Object[0]);
        }
        if (requestFactory.httpMethod.equals("HEAD") && !Void.class.equals(responseType)) {
            throw Utils.methodError(method, "HEAD method must use Void as response type.", new Object[0]);
        }
        return new HttpServiceMethod<>(requestFactory, retrofit.callFactory, createCallAdapter, createResponseConverter(retrofit, method, responseType));
    }

    private static <ResponseT, ReturnT> CallAdapter<ResponseT, ReturnT> createCallAdapter(Retrofit retrofit, Method method) {
        Type genericReturnType = method.getGenericReturnType();
        try {
            return (CallAdapter<ResponseT, ReturnT>) retrofit.callAdapter(genericReturnType, method.getAnnotations());
        } catch (RuntimeException e) {
            throw Utils.methodError(method, e, "Unable to create call adapter for %s", genericReturnType);
        }
    }

    private static <ResponseT> Converter<ResponseBody, ResponseT> createResponseConverter(Retrofit retrofit, Method method, Type type) {
        try {
            return retrofit.responseBodyConverter(type, method.getAnnotations());
        } catch (RuntimeException e) {
            throw Utils.methodError(method, e, "Unable to create converter for %s", type);
        }
    }

    private HttpServiceMethod(RequestFactory requestFactory, Call.Factory factory, CallAdapter<ResponseT, ReturnT> callAdapter, Converter<ResponseBody, ResponseT> converter) {
        this.requestFactory = requestFactory;
        this.callFactory = factory;
        this.callAdapter = callAdapter;
        this.responseConverter = converter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // retrofit2.ServiceMethod
    public ReturnT invoke(Object[] objArr) {
        return this.callAdapter.adapt(new OkHttpCall(this.requestFactory, objArr, this.callFactory, this.responseConverter));
    }
}
