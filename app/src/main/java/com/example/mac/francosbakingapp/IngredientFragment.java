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

import java.util.ArrayList;
import java.util.List;

public class IngredientFragment extends Fragment {

    IngredientAdapter mIngredientAdapter;
    Ingredient mIngredient;
    private Recipe mRecipe=null;
    TextView ingredientTextView;
    RecyclerView recyclerView;
    private String bund= null;
    private List<Ingredient> mArraylistIngredients;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle=getArguments();
        bund=bundle.getParcelable("ingredientBundle");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.ingredient_layout,container,false);

        ingredientTextView=view.findViewById(R.id.ingredients);
        recyclerView=view.findViewById(R.id.rv_ingredients);


        mArraylistIngredients=mRecipe.getIngredients();

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        mIngredientAdapter=new IngredientAdapter(mArraylistIngredients);
        recyclerView.setAdapter(mIngredientAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        mRecipe=getArguments().getParcelable(MainActivity.RECIPE_KEY);







        return view;
    }
}
