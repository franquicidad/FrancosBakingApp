package com.example.mac.francosbakingapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mac.francosbakingapp.Adapters.MainActAdapter;
import com.example.mac.francosbakingapp.Model.Recipe;
import com.example.mac.francosbakingapp.R;

public class RecipesMainFragment extends android.support.v4.app.Fragment implements MainActAdapter.RecipesAdapterOnClickHandler{

    RecyclerView main_rv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.process_fragment_layout,container,false);

        main_rv=view.findViewById(R.id.rv_process);

//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
//        MainActAdapter mainActAdapter=new MainActAdapter(this);
//        main_rv.setAdapter(mainActAdapter);
//        main_rv.setLayoutManager(linearLayoutManager);
//        main_rv.setHasFixedSize(true);



        return view;
    }

    @Override
    public void onRecipeClick(Recipe recipe, int position) {

    }
}
