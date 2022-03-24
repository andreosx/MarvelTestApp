package com.test.marveltestapp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.test.marveltestapp.data.dto.Character;
import com.test.marveltestapp.data.dto.Comics;
import com.test.marveltestapp.data.dto.Example;
import com.test.marveltestapp.data.implementation.IMarvelRepository;
import com.test.marveltestapp.util.Constants;
import com.test.marveltestapp.util.retrofit.ApiRequest;
import com.test.marveltestapp.util.retrofit.RetrofitRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarvelRepository implements IMarvelRepository {

    private final ApiRequest api;

    public MarvelRepository(){
        api = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<List<Character>> getCharacters(){
        final MutableLiveData<List<Character>> charactersData = new MutableLiveData<>();

        api.getCharacters(Constants.TS, Constants.PUBLIC_KEY, Constants.getHash())
           .enqueue(new Callback<Example>() {
               @Override
               public void onResponse(Call<Example> call, Response<Example> response) {
                   List<Character> characterList = new ArrayList<>();;
                   characterList.addAll(response.body().getData().getCharacters());
                   charactersData.setValue(characterList);
               }
               @Override
               public void onFailure(Call<Example> call, Throwable t) {
                   charactersData.setValue(null);
               }
           });

        return charactersData;
    }

    public LiveData<List<Comics>> getComics(String characterId){
        final MutableLiveData<List<Comics>> charactersData = new MutableLiveData<>();

        api.getComics(characterId,Constants.TS, Constants.PUBLIC_KEY, Constants.getHash())
                .enqueue(new Callback<Comics>() {
                    @Override
                    public void onResponse(Call<Comics> call, Response<Comics> response) {
                        List<Character> characterList = new ArrayList<>();;
                    }
                    @Override
                    public void onFailure(Call<Comics> call, Throwable t) {
                        charactersData.setValue(null);
                    }
                });

        return charactersData;
    }

}
