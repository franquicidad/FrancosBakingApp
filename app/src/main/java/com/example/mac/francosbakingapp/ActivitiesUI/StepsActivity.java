package com.example.mac.francosbakingapp.ActivitiesUI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.mac.francosbakingapp.Fragments.DescriptionFragment;
import com.example.mac.francosbakingapp.Fragments.ProcessFragment;
import com.example.mac.francosbakingapp.Model.Process;
import com.example.mac.francosbakingapp.R;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.ArrayList;

public class StepsActivity extends AppCompatActivity {

    private static final String DESCRIPTION_FRAGMENT="description_fragment";

    Process mProcess;

    ArrayList<Process> mArraylistProcess;
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



        getSupportFragmentManager().beginTransaction()
                .add(R.id.description_container,descriptionFragment,DESCRIPTION_FRAGMENT).commit();






        }
}
