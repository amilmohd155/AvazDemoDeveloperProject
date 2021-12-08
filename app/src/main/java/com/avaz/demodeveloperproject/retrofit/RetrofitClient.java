package com.avaz.demodeveloperproject.retrofit;

import com.avaz.demodeveloperproject.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitClient {

    @GET("{term}?limit_to_public_domain=1&limit=6&auth={authToken}")
    Call<ResponseModel> getIcons(@Path("term") String term, @Path("authToken") String authToken);

}
