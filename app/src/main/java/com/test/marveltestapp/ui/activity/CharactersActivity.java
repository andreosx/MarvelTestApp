package com.test.marveltestapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.marveltestapp.R;
import com.test.marveltestapp.data.dto.Character;
import com.test.marveltestapp.ui.adapter.CharactersAdapter;
import com.test.marveltestapp.ui.viewmodel.CharacterViewModel;
import com.test.marveltestapp.util.Constants;

import java.util.List;

public class CharactersActivity extends AppCompatActivity implements CharactersAdapter.ItemClickListener {

    CharacterViewModel characterViewModel;
    CharactersAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvCharacters);

        characterViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        observable();
    }

    private void observable() {
        characterViewModel.getCharactersLiveData().observe(this, characters -> {
            setAdapter(characters);
        });
    }

    private void setAdapter(List<Character> characters) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, Constants.COLUMNS_VIEW_CHARACTERS));
        adapter = new CharactersAdapter(this, characters);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, ComicsActivity.class);
        Bundle extras = new Bundle();
        extras.putString("characterId", adapter.getItem(position).getId()+"");
        intent.putExtras(extras);
        this.startActivity(intent);
    }
}