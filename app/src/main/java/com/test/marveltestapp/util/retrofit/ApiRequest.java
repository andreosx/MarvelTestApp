package com.test.marveltestapp.util.retrofit;

import com.test.marveltestapp.data.dto.character.Characters;
import com.test.marveltestapp.data.dto.comics.ComicsDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("/v1/public/characters")
    Call<Characters> getCharacters(@Query("ts") String ts,
                                   @Query("apikey") String apikey,
                                   @Query("hash") String hash);

    @GET("/v1/public/characters/{characterId}/comics")
    Call<ComicsDto> getComics(@Path("characterId") String characterId,
                              @Query("ts") String ts,
                              @Query("apikey") String apikey,
                              @Query("hash") String hash);

}
