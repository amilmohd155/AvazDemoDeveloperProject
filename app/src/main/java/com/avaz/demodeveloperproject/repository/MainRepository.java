package com.avaz.demodeveloperproject.repository;

import android.util.Log;

import com.avaz.demodeveloperproject.BuildConfig;
import com.avaz.demodeveloperproject.model.ResponseModel;
import com.avaz.demodeveloperproject.retrofit.RetrofitClient;
import com.avaz.demodeveloperproject.retrofit.ServiceGenerator;

import retrofit2.Call;

public class MainRepository {

    private final String API_KEY = BuildConfig.API_KEY;
    private final String SECRET_KEY = BuildConfig.SECRET_API_KEY;
    private final RetrofitClient client;


    private static MainRepository repository;

    private static final String TAG = "MainRepository";

    public static MainRepository getInstance() {
        if (repository == null) {
            repository = new MainRepository();
        }
        return repository;
    }

    public MainRepository() {

        client = ServiceGenerator.createService(RetrofitClient.class, API_KEY, SECRET_KEY);

    }

    public Call<ResponseModel> getIcons(String term){
        Call<ResponseModel> call = client.getIcons(term, ServiceGenerator.getAuthToken());

        Log.d(TAG, "getIcons: AuthToken " + ServiceGenerator.getAuthToken());
        return call;
    }
}
