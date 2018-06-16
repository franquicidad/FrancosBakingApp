package com.example.mac.francosbakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mac.francosbakingapp.Model.Process;

import java.util.List;

public class DescriptionFragment extends Fragment {

    private Context context;
    Process mProcess;
    TextView descriptionTextview;

    private List<Process> mProcessList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.frame_layout,container,false);

        descriptionTextview=view.findViewById(R.id.description_textview);


        int id =mProcess.getId();
        String shortDescription=mProcess.getShortDescription();
        String Description=mProcess.getDescription();
        String video=mProcess.getVideoURL();

        descriptionTextview.setText(Description);




        return view;
    }
}
