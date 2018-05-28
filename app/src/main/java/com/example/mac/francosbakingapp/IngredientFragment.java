package com.example.mac.francosbakingapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mac.francosbakingapp.Adapters.IngredientAdapter;
import com.example.mac.francosbakingapp.Model.Ingredient;
import com.example.mac.francosbakingapp.Model.Recipe;

import org.w3c.dom.Text;

public class IngredientFragment extends Fragment {

    IngredientAdapter mIngredientAdapter;
    Ingredient mIngredient;
    private Recipe mRecipe;
    TextView ingredientTextView;
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.ingredient_textview,container,false);

        ingredientTextView=view.findViewById(R.id.ingredients);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        recyclerView.setAdapter(mIngredientAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        mRecipe=getArguments().getParcelable(MainActivity.RECIPE_KEY);



        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
