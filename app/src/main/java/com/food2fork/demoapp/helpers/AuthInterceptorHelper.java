package com.food2fork.demoapp.helpers;

import com.food2fork.demoapp.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptorHelper implements Interceptor {
    public AuthInterceptorHelper() {}

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalReq = chain.request();
        HttpUrl originalUrl = originalReq.url();
        HttpUrl newUrl = originalUrl.newBuilder().addQueryParameter("key", BuildConfig.API_KEY).build();
        Request.Builder builder = originalReq.newBuilder();
        Request newRequest = builder.url(newUrl).build();
        return chain.proceed(newRequest);
    }
}