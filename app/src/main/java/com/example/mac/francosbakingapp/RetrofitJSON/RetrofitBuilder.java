package com.example.mac.francosbakingapp.RetrofitJSON;

import com.example.mac.francosbakingapp.Model.Ingredient;
import com.example.mac.francosbakingapp.Model.Recipe;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitBuilder {

    static RecipesInterface interfaceRecipes;
    static OkHttpClient httpClient=new OkHttpClient.Builder().build();


    public static  Call<ArrayList<Recipe>> getRecipes(){

        // Create a basic REST adapter which points to the BASE_URL
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipesInterface apiFactory = retrofit.create(RecipesInterface.class);

        return  apiFactory.getRecipesListTask();
    }

    public  interface RecipesInterface{
        @GET ("baking.json")
        Call<ArrayList<Recipe>> getRecipesListTask();
    }

    public interface ingredientsInterface{
       @GET("baking.json")
       Call<ArrayList<Ingredient>> getIngredientListTask();
    }
}
