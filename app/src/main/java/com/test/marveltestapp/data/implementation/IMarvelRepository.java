package com.test.marveltestapp.data.implementation;

import androidx.lifecycle.LiveData;

import com.test.marveltestapp.data.dto.Character;

import java.util.List;

public interface IMarvelRepository {
    LiveData<List<Character>> getCharacters();
}
