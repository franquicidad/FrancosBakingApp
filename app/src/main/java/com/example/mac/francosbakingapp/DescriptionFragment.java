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
import android.widget.Toast;

import com.example.mac.francosbakingapp.Model.Process;

import java.util.ArrayList;
import java.util.List;

public class DescriptionFragment extends Fragment {

    private Context context;
    Process mProcess;
    TextView descriptionTextview, nameStep;
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
        nameStep=view.findViewById(R.id.step_name);


        mProcessList=getArguments().getParcelableArrayList("ArrayList");

        position=getArguments().getInt("process_position");

        mProcess=mProcessList.get(position);
        final String Description =mProcess.getDescription();
        final String nameDes=mProcess.getShortDescription();
        nameStep.setText(nameDes);
        descriptionTextview.setText(Description);

        previousB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if(position == 0) {
                    Toast.makeText(getContext(),"You are in the first step of this recipe",Toast.LENGTH_LONG).show();
                }else{
                    mProcess = mProcessList.get(position);
                    String Description=mProcess.getDescription();
                    descriptionTextview.setText(Description);
                }

            }
        });

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                position++;

                if(position >= mProcessList.size()) {
                    Toast.makeText(getContext(),"You are in the Last step of this recipe",Toast.LENGTH_LONG).show();
                }else{
                    mProcess=mProcessList.get(position);
                        String Description = mProcess.getDescription();
                        descriptionTextview.setText(Description);

                }


            }
        });




        return view;
    }
}
