package com.avaz.demodeveloperproject.retrofit;

import android.text.TextUtils;

import com.avaz.demodeveloperproject.BuildConfig;
import com.avaz.demodeveloperproject.utility.AuthenticationInterceptor;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String BASE_URL = BuildConfig.NOUN_PROJECT_BASE_API_URL;

    private static String authToken;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(
            Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, String clientID, String clientSecret) {

        if (!TextUtils.isEmpty(clientID)
                && !TextUtils.isEmpty(clientSecret)) {
            authToken = Credentials.basic(clientID, clientSecret);
            return createService(serviceClass, authToken);
        }

        return createService(serviceClass, null, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if(!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return  retrofit.create(serviceClass);

    }

    public static String getAuthToken() {
        return authToken;
    }
}
