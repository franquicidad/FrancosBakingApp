package com.example.mac.francosbakingapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
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
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.ArrayList;
import java.util.List;

public class DescriptionFragment extends Fragment {

    private Context context;
    Process mProcess;
    private SimpleExoPlayerView mExoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private PlayerView playerView;
    TextView descriptionTextview, nameStep;
    Bundle positionBund;
    String mVideoUrl;
    private boolean playWhenReady;
    private int startWindow;
    private long playbackPosition;

    private SimpleExoPlayer player;


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
        playerView=view.findViewById(R.id.exoplayer_view);


        mProcessList=getArguments().getParcelableArrayList("ArrayList");

        position=getArguments().getInt("process_position");

        mProcess=mProcessList.get(position);
        final String Description =mProcess.getDescription();
        final String nameDes=mProcess.getShortDescription();
        mVideoUrl=mProcess.getVideoURL();

        nameStep.setText(nameDes);
        descriptionTextview.setText(Description);

        previousB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position>1){
                    position--;
                }

                if(position >= 0) {
                    mProcess = mProcessList.get(position);
                    String Description=mProcess.getDescription();
                    String shortDes=mProcess.getShortDescription();
                    descriptionTextview.setText(Description);
                    nameStep.setText(shortDes);

                }else{

                    if(position==0) {

                        Toast.makeText(getContext(), "You are in the first step of this recipe", Toast.LENGTH_LONG).show();
                    }
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
                        String shortDesAdd=mProcess.getShortDescription();
                        nameStep.setText(shortDesAdd);
                        descriptionTextview.setText(Description);

                }


            }
        });




        return view;
    }


    private void initializaPlayer(){
         player= ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(),
                new DefaultLoadControl());

        playerView.setPlayer(player);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(startWindow,playbackPosition);


        Uri uri=Uri.parse(mVideoUrl);
        MediaSource mediaSource=buildMediaSource(uri);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("bakingapp-exoplayer"));
    }
}
