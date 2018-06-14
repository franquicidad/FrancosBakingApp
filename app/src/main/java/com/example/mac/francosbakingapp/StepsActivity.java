package com.example.mac.francosbakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mac.francosbakingapp.Model.Process;
import com.example.mac.francosbakingapp.Model.Recipe;

public class StepsActivity extends AppCompatActivity {

    Process mProcess;
    TextView stepName,stepDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick_steps);

        mProcess= getIntent().getExtras().getParcelable(ProcessFragment.PROCESS_EXTRA);

        int id =mProcess.getId();
        String shortDescription=mProcess.getShortDescription();
        String Description=mProcess.getDescription();
        String video=mProcess.getVideoURL();

        stepName=findViewById(R.id.step_name);
        stepDescription=findViewById(R.id.step_description);


        stepName.setText(shortDescription);
        stepDescription.setText(Description);





        }
}
