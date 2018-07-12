package com.example.mac.francosbakingapp.ActivitiesUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mac.francosbakingapp.Adapters.MainActAdapter;
import com.example.mac.francosbakingapp.Model.Recipe;
import com.example.mac.francosbakingapp.R;
import com.example.mac.francosbakingapp.RetrofitJSON.RetrofitBuilder;
import com.example.mac.francosbakingapp.Widget.BankingAppWidgetProvider;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainActAdapter.RecipesAdapterOnClickHandler{

    private ArrayList<Recipe> mRecipeList;
    private MainActAdapter mainActAdapter;
    private Recipe mRecipe;
    public static String RECIPE_KEY="recipe_key";
    public static String POSITION_KEY= "position_key";
    boolean mHasTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(findViewById(R.id.layout_distinguish_tablet)!=null) {
            mHasTwoPane = true;
        }
        else {
            mHasTwoPane = false;

            RecyclerView recyclerViewMainRecipe = findViewById(R.id.recyclerView_recipies);
            mainActAdapter = new MainActAdapter(this);
            recyclerViewMainRecipe.setAdapter(mainActAdapter);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerViewMainRecipe.setLayoutManager(linearLayoutManager);

//        RetrofitBuilder.RecipesInterface recipesInterface=RetrofitBuilder.getRecipes();
//        Call <ArrayList<Recipe>> call = RetrofitBuilder.getRecipes();

            Call<ArrayList<Recipe>> arrayListCall = RetrofitBuilder.getRecipes();

            arrayListCall.enqueue(new Callback<ArrayList<Recipe>>() {
                @Override
                public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                    mRecipeList = response.body();
                    mainActAdapter.setRecipesData(mRecipeList);

                }

                @Override
                public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {

                }
            });

        }

    }

    @Override
    public void onRecipeClick(Recipe recipe,int position) {

        mRecipe=recipe;
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(MainActivity.POSITION_KEY,position);
        editor.apply();
        BankingAppWidgetProvider.sendUpdateBroadcast(this);

        Intent IngredientsIntent =new Intent(this,IngredientActivity.class);

        IngredientsIntent.putExtra(RECIPE_KEY,recipe);
        startActivity(IngredientsIntent);
    }
}
