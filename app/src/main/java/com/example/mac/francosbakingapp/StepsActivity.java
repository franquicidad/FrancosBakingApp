package com.example.mac.francosbakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mac.francosbakingapp.Model.Recipe;

public class StepsActivity extends AppCompatActivity {

    Recipe mRecipe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick_steps);

        mRecipe=getIntent().getExtras().getParcelable(ProcessFragment.RECIPE_EXTRA);



    }
}
