package com.food2fork.demoapp.network;

import com.food2fork.demoapp.BuildConfig;
import com.food2fork.demoapp.helpers.AuthInterceptorHelper;
import com.food2fork.demoapp.network.service.RecipeService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    private RecipeService service;

    public ApiClient(){
        retrofit = getClient();
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new AuthInterceptorHelper());

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(builder.build())
                    .build();
        }
        return retrofit;
    }

    public static RecipeService getRecipeService(){
        return getClient().create(RecipeService.class);
    }

}
