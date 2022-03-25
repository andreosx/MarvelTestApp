package com.test.marveltestapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.marveltestapp.data.dto.comics.ComicsDto;
import com.test.marveltestapp.data.dto.comics.Result;
import com.test.marveltestapp.util.Constants;
import com.test.marveltestapp.util.retrofit.ApiRequest;
import com.test.marveltestapp.util.retrofit.RetrofitRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComicsViewModel extends ViewModel {

    private MutableLiveData<List<Result>> comicsData = new MutableLiveData<>();
    private MutableLiveData<Throwable> requestError = new MutableLiveData<>();

    public void getComics(String characterId){
        ApiRequest api = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);

        api.getComics(characterId, Constants.TS, Constants.PUBLIC_KEY, Constants.getHash())
                .enqueue(new Callback<ComicsDto>() {
                    @Override
                    public void onResponse(Call<ComicsDto> call, Response<ComicsDto> response) {
                        comicsData.setValue(response.body().getData().getResults());
                    }
                    @Override
                    public void onFailure(Call<ComicsDto> call, Throwable t) {
                        requestError.setValue(t);
                    }
                });
    }

    public LiveData<Throwable> getOnError() {return requestError; }
    public LiveData<List<Result>> getComicsLiveData() { return comicsData; }

}
