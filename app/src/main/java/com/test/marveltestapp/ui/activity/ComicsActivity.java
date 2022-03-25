package com.test.marveltestapp.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.marveltestapp.R;
import com.test.marveltestapp.data.dto.comics.Result;
import com.test.marveltestapp.ui.adapter.ComicsAdapter;
import com.test.marveltestapp.ui.viewmodel.ComicsViewModel;
import com.test.marveltestapp.util.Constants;

import java.util.List;

public class ComicsActivity extends AppCompatActivity {

    private String characterId, characterName;
    private ComicsViewModel comicsViewModel;
    private ComicsAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comicsViewModel = new ViewModelProvider(this).get(ComicsViewModel.class);
        setContentView(R.layout.activity_character);
        recyclerView = findViewById(R.id.rvComics);
        mProgressBar = findViewById(R.id.progressBar);

        getExtras();
        setToolbar();
        observable();

        showLoading(true);
        comicsViewModel.getComics(characterId);
    }

    private void showLoading(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void setToolbar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(characterName);
    }

    private void getExtras(){
        Bundle intentExtras = getIntent().getExtras();
        characterId = intentExtras.getString("characterId");
        characterName = intentExtras.getString("characterName");
    }

    private void observable() {
        comicsViewModel.getComicsLiveData().observe(this, comics -> {
            showLoading(false);
            setAdapter(comics);
        });

        comicsViewModel.getOnError().observe(this,error->{
            showLoading(false);
            Toast.makeText(this,error.getMessage(),Toast.LENGTH_LONG).show();
        });
    }

    private void setAdapter(List<Result> comics) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, Constants.COLUMNS_VIEW_COMICS));
        adapter = new ComicsAdapter(this, comics);
        recyclerView.setAdapter(adapter);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}