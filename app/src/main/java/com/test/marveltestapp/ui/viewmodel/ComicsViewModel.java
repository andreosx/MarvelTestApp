package com.test.marveltestapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.marveltestapp.data.dto.Character;
import com.test.marveltestapp.data.dto.Comics;
import com.test.marveltestapp.data.repository.MarvelRepository;

import java.util.List;

public class ComicsViewModel extends ViewModel {

    MarvelRepository marvelRepository;
    String characterId;
    LiveData<List<Comics>> comicsDTO = new MutableLiveData<>();

    public ComicsViewModel() {
        marvelRepository = new MarvelRepository();
        this.comicsDTO = marvelRepository.getComics(characterId);
    }

    public void setCharacterId(String characterId){
        this.characterId = characterId;
    }
    public LiveData<List<Comics>> getComicsLiveData() { return comicsDTO;  }

}
