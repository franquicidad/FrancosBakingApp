package com.example.mac.francosbakingapp.Fragments;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mac.francosbakingapp.Adapters.IngredientAdapter;
import com.example.mac.francosbakingapp.Model.Ingredient;
import com.example.mac.francosbakingapp.Model.Recipe;
import com.example.mac.francosbakingapp.R;
import com.example.mac.francosbakingapp.Widget.UpdateWidgetService;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class IngredientFragment extends Fragment {

    IngredientAdapter mIngredientAdapter;
    Ingredient mIngredient;
    private Recipe mRecipe=null;
    TextView ingredientTextView;
    RecyclerView recyclerView;
    private Recipe bund= null;

    private List<Ingredient> mArraylistIngredients;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_layout,container,false);

        recyclerView=view.findViewById(R.id.rv_ingredients);

        mRecipe=getArguments().getParcelable("ingredientBundle");

        mArraylistIngredients=mRecipe.getIngredients();

        ArrayList<Ingredient> Arraylist2=new ArrayList<>(mArraylistIngredients);

        UpdateWidgetService.startUpdateWidgetService(getContext(),Arraylist2);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        mIngredientAdapter=new IngredientAdapter(mArraylistIngredients);
        recyclerView.setAdapter(mIngredientAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);




        return view;
    }
}
