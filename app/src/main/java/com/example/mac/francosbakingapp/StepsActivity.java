package com.example.mac.francosbakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.mac.francosbakingapp.Model.Process;
import com.example.mac.francosbakingapp.Model.Recipe;

public class StepsActivity extends AppCompatActivity {

    private static final String DESCRIPTION_FRAGMENT="description_fragment";

    Process mProcess;
    TextView stepName,stepDescription;
    Button next,previous;
    private int idCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick_steps);



        stepName=findViewById(R.id.step_name);
        stepDescription=findViewById(R.id.step_description);
        next=findViewById(R.id.next_button);
        previous=findViewById(R.id.previous_button);

        Bundle steps=new Bundle();





        DescriptionFragment descriptionFragment=new DescriptionFragment();
        descriptionFragment.setArguments(steps);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.description_container,descriptionFragment,DESCRIPTION_FRAGMENT).commit();






        }
}
