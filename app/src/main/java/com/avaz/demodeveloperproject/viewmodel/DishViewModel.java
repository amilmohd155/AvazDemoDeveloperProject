package com.avaz.demodeveloperproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avaz.demodeveloperproject.model.DishModel;
import com.avaz.demodeveloperproject.model.ResponseModel;
import com.avaz.demodeveloperproject.repository.MainRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DishViewModel extends ViewModel {

    private MutableLiveData<ResponseModel> model = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage =  new MutableLiveData<>();
    private MutableLiveData<DishModel> dishModelMutable = new MutableLiveData<>();
    private MainRepository repository;

    public DishViewModel() {
        repository = MainRepository.getInstance();
    }

    public LiveData<DishModel> getDishModel() {
        if (dishModelMutable == null) dishModelMutable = new MutableLiveData<>();
        return dishModelMutable;
    }

    public void setMutableDishModel(DishModel model) {
        dishModelMutable.setValue(model);
    }

    public void getIconFromTerm(String term) {

        Call<ResponseModel> response = repository.getIcons(term);
        response.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                if (response.isSuccessful()) {
                    model.postValue(response.body());
                    errorMessage.postValue(response.errorBody().toString());
                }
                else
                    errorMessage.postValue(response.errorBody().toString());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                errorMessage.postValue(t.getMessage());
            }
        });
    }

    public LiveData<ResponseModel> getModel() {
        if(model == null) model = new MutableLiveData<>();
        return model;
    }

    public LiveData<String> getError() {
        if(errorMessage == null) errorMessage = new MutableLiveData<>();
        return errorMessage;
    }

}
