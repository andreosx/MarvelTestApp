package com.test.marveltestapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.marveltestapp.data.dto.character.Characters;
import com.test.marveltestapp.data.dto.character.Result;
import com.test.marveltestapp.util.Constants;
import com.test.marveltestapp.data.api.ApiRequest;
import com.test.marveltestapp.util.retrofit.RetrofitRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterViewModel extends ViewModel {

    private MutableLiveData<List<Result>> charactersDTO = new MutableLiveData<>();
    private MutableLiveData<Throwable> requestError = new MutableLiveData<>();

    public void getCharacters(){
        ApiRequest api = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);

        api.getCharacters(Constants.TS, Constants.PUBLIC_KEY, Constants.getHash())
                .enqueue(new Callback<Characters>() {
                    @Override
                    public void onResponse(Call<Characters> call, Response<Characters> response) {
                        charactersDTO.setValue(response.body().getData().getResults());
                    }
                    @Override
                    public void onFailure(Call<Characters> call, Throwable t) {
                        requestError.postValue(t);
                    }
                });
    }

    public LiveData<Throwable> getOnError() {return requestError; }
    public LiveData<List<Result>> getCharactersLiveData() { return charactersDTO; }

}
