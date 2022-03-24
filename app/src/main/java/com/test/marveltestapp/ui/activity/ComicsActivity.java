package com.test.marveltestapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.test.marveltestapp.R;
import com.test.marveltestapp.ui.viewmodel.CharacterViewModel;
import com.test.marveltestapp.ui.viewmodel.ComicsViewModel;

public class ComicsActivity extends AppCompatActivity {

    ComicsViewModel comicsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        comicsViewModel = new ViewModelProvider(this).get(ComicsViewModel.class);
        observable();

        Bundle intentExtras = getIntent().getExtras();
        String characterId = intentExtras.getString("characterId");
        comicsViewModel.setCharacterId(characterId);
    }

    private void observable() {
        comicsViewModel.getComicsLiveData().observe(this, characters -> {
            Log.d("oi","");
        });
    }
}