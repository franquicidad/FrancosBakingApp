package com.example.mac.francosbakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mac.francosbakingapp.Adapters.ProcessAdapter;
import com.example.mac.francosbakingapp.Model.Process;
import com.example.mac.francosbakingapp.Model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class ProcessFragment extends Fragment implements ProcessAdapter.onProcessAdapterClickHandler{

    private static final String TAG ="logtag" ;
    ProcessAdapter mProcessAdapter;
    private Recipe mRecipe;
    public static final String RECIPE_EXTRA="recipe_extra";
    public static final String PROCESS_EXTRA="process_extra";
    public static final String PROCESS_POSITION="process_position";
    RecyclerView processRecyclerview;
    Context mContext;

    private List<Process> mArrayListProcess;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.process_fragment_layout,container,false);

        processRecyclerview=view.findViewById(R.id.rv_process);

        mRecipe=getArguments().getParcelable("ingredientBundle");

        mArrayListProcess=mRecipe.getSteps();

        mArrayListProcess=new ArrayList<Process>(mArrayListProcess);

        Bundle bundle1=new Bundle();


// Get an ArrayList from a List
        ArrayList<Process> processes = new ArrayList<>(mArrayListProcess);

// Put the ArrayList in the Bundle
        bundle1.putParcelableArrayList("stepsList", processes);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        mProcessAdapter=new ProcessAdapter(mArrayListProcess,this);
        processRecyclerview.setAdapter(mProcessAdapter);
        processRecyclerview.setLayoutManager(linearLayoutManager);
        processRecyclerview.setHasFixedSize(true);

        return view;
    }

    /**
     * Here you set the click to go to the other layout the recipe details steps
     * @param process
     */
    @Override
    public void onProcessClicked(Process process, int position) {

        Intent stepActivityInt=new Intent(getContext(),StepsActivity.class);
        stepActivityInt.putExtra(PROCESS_EXTRA,process);
        startActivity(stepActivityInt);

        Bundle positionBund=new Bundle();
        positionBund.putInt(PROCESS_POSITION,position);

    }


}
