package com.test.marveltestapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.marveltestapp.data.dto.Character;
import com.test.marveltestapp.data.repository.MarvelRepository;

import java.util.List;

public class CharacterViewModel extends ViewModel {

    MarvelRepository marvelRepository;
    LiveData<List<Character>> charactersDTO = new MutableLiveData<>();

    public CharacterViewModel() {
        marvelRepository = new MarvelRepository();
        this.charactersDTO = marvelRepository.getCharacters();
    }

    public LiveData<List<Character>> getCharactersLiveData() { return charactersDTO;  }

}
