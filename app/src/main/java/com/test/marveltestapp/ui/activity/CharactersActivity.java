package com.test.marveltestapp.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.marveltestapp.R;
import com.test.marveltestapp.data.dto.character.Result;
import com.test.marveltestapp.ui.adapter.CharactersAdapter;
import com.test.marveltestapp.ui.viewmodel.CharacterViewModel;
import com.test.marveltestapp.util.Constants;

import java.util.List;

public class CharactersActivity extends AppCompatActivity implements CharactersAdapter.ItemClickListener {

    private CharacterViewModel characterViewModel;
    private CharactersAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvCharacters);
        mProgressBar = findViewById(R.id.progressBar);

        characterViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        observable();

        showLoading(true);
        characterViewModel.getCharacters();
    }

    private void showLoading(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void observable() {
        characterViewModel.getCharactersLiveData().observe(this, characters -> {
            showLoading(false);
            setAdapter(characters);
        });

        characterViewModel.getOnError().observe(this,error->{
            showLoading(false);
            Toast.makeText(this,error.getMessage(),Toast.LENGTH_LONG).show();
        });
    }

    private void setAdapter(List<Result> chracters) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, Constants.COLUMNS_VIEW_CHARACTERS));
        adapter = new CharactersAdapter(this, chracters);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, ComicsActivity.class);
        Bundle extras = new Bundle();
        extras.putString("characterId", adapter.getItem(position).getId()+"");
        extras.putString("characterName", adapter.getItem(position).getName());
        intent.putExtras(extras);
        this.startActivity(intent);
    }
}