package com.example.mac.francosbakingapp.ActivitiesUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mac.francosbakingapp.Adapters.IngredientAdapter;
import com.example.mac.francosbakingapp.Adapters.MainActAdapter;
import com.example.mac.francosbakingapp.Fragments.DescriptionFragment;
import com.example.mac.francosbakingapp.Fragments.IngredientFragment;
import com.example.mac.francosbakingapp.Fragments.ProcessFragment;
import com.example.mac.francosbakingapp.Model.Ingredient;
import com.example.mac.francosbakingapp.Model.Process;
import com.example.mac.francosbakingapp.Model.Recipe;
import com.example.mac.francosbakingapp.R;
import com.example.mac.francosbakingapp.RetrofitJSON.RetrofitBuilder;
import com.example.mac.francosbakingapp.Widget.BankingAppWidgetProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainActAdapter.RecipesAdapterOnClickHandler {

    private static final String INGREDIENT_FRAGMENT = "ingredient_fragment";
    private static final String PROCESS_FRAGMENT="process_fragment";
    private static final String EXO_DESCRIP_FRAG="exo_des_frag";

    public static String RECIPE_KEY = "recipe_key";
    public static String POSITION_KEY = "position_key";
    boolean mHasTwoPane;
    RecyclerView recyclerViewMainRecipe;
    private ArrayList<Recipe> mRecipeList;
    private MainActAdapter mainActAdapter;
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewMainRecipe = findViewById(R.id.rv_process);


        if (findViewById(R.id.layout_distinguish_tablet) != null) {
            mHasTwoPane = true;
            loadRecipe();

        } else {
            mHasTwoPane = false;
            loadRecipe();
        }


    }

    private void loadRecipe() {
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

    @Override
    public void onRecipeClick(Recipe recipe, int position) {
        if (mHasTwoPane) {
            eventAndLoadData(recipe,position);

            mRecipe = recipe;
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(MainActivity.POSITION_KEY, position);
            editor.apply();
            BankingAppWidgetProvider.sendUpdateBroadcast(this);

        } else {


            Intent IngredientsIntent = new Intent(this, IngredientActivity.class);

            IngredientsIntent.putExtra(RECIPE_KEY, recipe);
            startActivity(IngredientsIntent);
        }


    }



    private  void eventAndLoadData(Recipe recipe,int position){
        ImageView imageView = (ImageView) findViewById(R.id.tablet_imageOfRecipe);
        loadIngredientFragment(recipe);
        loadStepsTabletFragment(recipe);
        switch (position) {
            case 0:
                imageView.setImageResource(R.drawable.nutella);

                break;
            case 1:
                imageView.setImageResource(R.drawable.brownie);

                break;

            case 2:
                imageView.setImageResource(R.drawable.yellowcake);

                break;

            case 3:
                imageView.setImageResource(R.drawable.cheesecake);

                break;
        }
    }
    private void eventAndLoadDataOnStepsList(Recipe recipe,int position){

        loadExoAndDescription(recipe);

    }
    private void  loadIngredientFragment(Recipe recipe) {
        Bundle ingredientBundle=new Bundle();
        ingredientBundle.putParcelable("ingredientBundle", recipe);
        IngredientFragment ingredientFragment=new IngredientFragment();
        ingredientFragment.setArguments(ingredientBundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ingredient_frameLayout,ingredientFragment,INGREDIENT_FRAGMENT).commit();
    }

    private void loadStepsTabletFragment(Recipe recipe){

        Bundle ingredientBundle=new Bundle();
        ingredientBundle.putParcelable("ingredientBundle", recipe);
        ProcessFragment processFragment=new ProcessFragment();
        processFragment.setArguments(ingredientBundle);


        getSupportFragmentManager().beginTransaction().replace(R.id.tablet_fragment_steps,processFragment,PROCESS_FRAGMENT).commit();

    }

    private void loadExoAndDescription(Recipe recipe){

        DescriptionFragment descriptionFragment=new DescriptionFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.tablet_fragment_exo_description,descriptionFragment,EXO_DESCRIP_FRAG);

    }

    public void onProcessClicked(Process process,int position){

    }
}

