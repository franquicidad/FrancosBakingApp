package com.example.mac.francosbakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.francosbakingapp.Model.Recipe;

public class IngredientActivity extends AppCompatActivity {

    private Recipe detailRecipe;
    Recipe selectedRecipe;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_layout);

        detailRecipe= getIntent().getExtras().getParcelable(MainActivity.RECIPE_KEY);

        ImageView recipe_image_ing_act=findViewById(R.id.ingredient_detail_imageView);
        TextView recipe_textView_ing_act=findViewById(R.id.textView_recipe);


        selectedRecipe=detailRecipe;







    }
}
