package com.example.mac.francosbakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mac.francosbakingapp.Model.Process;

import java.util.ArrayList;
import java.util.List;

public class DescriptionFragment extends Fragment {

    private Context context;
    Process mProcess;
    TextView descriptionTextview;
    Bundle positionBund;

    int position;
    Button previousB, nextB;


    private ArrayList<Process> mProcessList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.frame_layout,container,false);

        descriptionTextview=view.findViewById(R.id.description_textview);
        previousB=view.findViewById(R.id.previous_button);
        nextB=view.findViewById(R.id.next_button);



        mProcessList=getArguments().getParcelableArrayList("ArrayList");

        position=getArguments().getInt("process_position");




        //Process selectedProcess=mProcessList.get(position)


        int id =mProcess.getId();
        String shortDescription=mProcess.getShortDescription();
        String Description=mProcess.getDescription();
        String video=mProcess.getVideoURL();

        descriptionTextview.setText(Description);




        return view;
    }
}
