package com.test.marveltestapp.data.implementation;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface IMarvelRepository {
    LiveData<List<Character>> getCharacters();
}
