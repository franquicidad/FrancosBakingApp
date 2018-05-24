package com.example.mac.francosbakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mac.francosbakingapp.Adapters.MainActAdapter;
import com.example.mac.francosbakingapp.Model.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainActAdapter.RecipesAdapterOnClickHandler{

    private ArrayList<Recipe> mRecipeList;
    private MainActAdapter mainActAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewMainRecipe=findViewById(R.id.recyclerView_recipies);
        mainActAdapter=new MainActAdapter(this);
        recyclerViewMainRecipe.setAdapter(mainActAdapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewMainRecipe.setLayoutManager(linearLayoutManager);

        RetrofitBuilder.RecipesInterface recipesInterface=RetrofitBuilder.getRecipes();
        Call<ArrayList<Recipe>> arrayListCall=recipesInterface.getRecipesListTask();

        arrayListCall.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                mRecipeList=response.body();
                mainActAdapter.setRecipesData(mRecipeList);

            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {

            }
        });






    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        Intent IngredientsIntent =new Intent(this,)
    }
}
