package com.example.mac.francosbakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.mac.francosbakingapp.Model.Process;
import com.example.mac.francosbakingapp.Model.Recipe;

import java.util.ArrayList;

public class StepsActivity extends AppCompatActivity {

    private static final String DESCRIPTION_FRAGMENT="description_fragment";

    Process mProcess;

    ArrayList<Process> mArraylistProcess;
    TextView stepName,stepDescription;
    Button next,previous;
    private int idCount;
    private int processPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick_steps);

        mArraylistProcess=getIntent().getExtras().getParcelableArrayList(ProcessFragment.PROCESS_EXTRA);

        processPosition=getIntent().getExtras().getInt(ProcessFragment.PROCESS_POSITION);

        Bundle stepsBundle=new Bundle();
        stepsBundle.putParcelableArrayList("ArrayList",mArraylistProcess);
        stepsBundle.putInt("process_position",processPosition);

        DescriptionFragment descriptionFragment=new DescriptionFragment();
        descriptionFragment.setArguments(stepsBundle);



        stepName=findViewById(R.id.step_name);
        stepDescription=findViewById(R.id.step_description);
        next=findViewById(R.id.next_button);
        previous=findViewById(R.id.previous_button);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.description_container,descriptionFragment,DESCRIPTION_FRAGMENT).commit();






        }
}
