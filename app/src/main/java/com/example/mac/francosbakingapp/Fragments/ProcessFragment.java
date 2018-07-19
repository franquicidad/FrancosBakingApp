package com.example.mac.francosbakingapp.Fragments;

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
import com.example.mac.francosbakingapp.R;
import com.example.mac.francosbakingapp.ActivitiesUI.StepsActivity;

import java.util.ArrayList;
import java.util.List;

public class ProcessFragment extends Fragment implements ProcessAdapter.onProcessAdapterClickHandler {

    private static final String TAG = "logtag";
    ProcessAdapter mProcessAdapter;
    private Recipe mRecipe;
    public static final String RECIPE_EXTRA = "recipe_extra";
    public static final String PROCESS_EXTRA = "process_extra";
    public static final String PROCESS_POSITION = "process_position";
    private static final String DESCRIPTION_FRAGMENT="description_fragment";

    RecyclerView processRecyclerview;
    Context mContext;
    boolean mIsTablet;


    private List<Process> mArrayListProcess;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.process_fragment_layout, container, false);

        processRecyclerview = view.findViewById(R.id.rv_process);

        mRecipe = getArguments().getParcelable("ingredientBundle");

        mArrayListProcess = mRecipe.getSteps();

        mArrayListProcess = new ArrayList<Process>(mArrayListProcess);

        mIsTablet = getArguments().getString("phone_or_tablet").equals("tablet");

        Bundle bundle1 = new Bundle();


// Get an ArrayList from a List
        ArrayList<Process> processes = new ArrayList<>(mArrayListProcess);

// Put the ArrayList in the Bundle
        bundle1.putParcelableArrayList("stepsList", processes);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mProcessAdapter = new ProcessAdapter(mArrayListProcess, this);
        processRecyclerview.setAdapter(mProcessAdapter);
        processRecyclerview.setLayoutManager(linearLayoutManager);
        processRecyclerview.setHasFixedSize(true);

        return view;
    }

    /**
     * Here you set the click to go to the other layout the recipe details steps
     *
     * @param
     */
    @Override
    public void onProcessClicked(List<Process> processes, int position) {



        ArrayList<Process> processes1 = new ArrayList<>(mArrayListProcess);


        Log.e(TAG,"THE VARIABLE IS MISTABLET IS :-------------->"+mIsTablet);

        if (mIsTablet) {

            Bundle stepsBundle=new Bundle();
            stepsBundle.putParcelableArrayList("ArrayList",processes1);
            stepsBundle.putInt("process_position",position);

            DescriptionFragment descriptionFragment=new DescriptionFragment();
            descriptionFragment.setArguments(stepsBundle);


            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.description_container,descriptionFragment,DESCRIPTION_FRAGMENT).commit();



        }else{

            Intent stepActivityInt = new Intent(getContext(), StepsActivity.class);

            stepActivityInt.putParcelableArrayListExtra(PROCESS_EXTRA, processes1);
            stepActivityInt.putExtra(PROCESS_POSITION, position);
            startActivity(stepActivityInt);


        }


    }
}
