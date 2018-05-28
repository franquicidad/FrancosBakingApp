package com.example.mac.francosbakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.example.mac.francosbakingapp.Model.Recipe;

import java.util.List;

public class IngredientActivity extends AppCompatActivity {

    private static final String INGREDIENT_FRAGMENT ="ingredient_fragment" ;
    private Recipe detailRecipe;

    Recipe selectedRecipe;
    RecyclerView rv_ingred;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_layout);

        detailRecipe= getIntent().getExtras().getParcelable(MainActivity.RECIPE_KEY);

        ImageView recipe_image_ing_act=findViewById(R.id.ingredient_detail_imageView);
        TextView recipe_textView_ing_act=findViewById(R.id.textView_recipe);


        selectedRecipe=detailRecipe;

        int id=detailRecipe.getId();
        String name=detailRecipe.getName();

        List ingredientList=detailRecipe.getIngredientList();
        List processList=detailRecipe.getProcessList();

        switch(id){
            case 1:
                recipe_image_ing_act.setImageResource(R.drawable.nutella);
                recipe_textView_ing_act.setText(name);
                break;
            case 2:
                recipe_image_ing_act.setImageResource(R.drawable.brownie);
                recipe_textView_ing_act.setText(name);
                break;

            case 3:
                recipe_image_ing_act.setImageResource(R.drawable.yellowcake);
                recipe_textView_ing_act.setText(name);
                break;

            case 4:
                recipe_image_ing_act.setImageResource(R.drawable.cheesecake);
                recipe_textView_ing_act.setText(name);
                break;
        }

        IngredientFragment ingredientFragment=new IngredientFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.ingredient_frameLayout,ingredientFragment,INGREDIENT_FRAGMENT).commit();














    }
}
