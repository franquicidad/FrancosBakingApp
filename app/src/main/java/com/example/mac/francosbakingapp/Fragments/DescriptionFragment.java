package com.example.mac.francosbakingapp.Fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.francosbakingapp.Model.Process;
import com.example.mac.francosbakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class DescriptionFragment extends Fragment {

    Process mProcess;
    TextView descriptionTextview, nameStep;
    Bundle positionBund;
    TextView videoIsNull;
    int position;
    Button previousB, nextB;
    private Context context;
    private SimpleExoPlayerView mExoPlayerView;
    private PlayerView playerView;
    private boolean playWhenReady;
    private static MediaSessionCompat mMediaSession;// problems

    private int startWindow;
    private long playbackPosition;
    private SimpleExoPlayer mExoPlayer;
    private ArrayList<Process> mProcessList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frame_layout, container, false);

        descriptionTextview = view.findViewById(R.id.description_textview);
        previousB = view.findViewById(R.id.previous_button);
        nextB = view.findViewById(R.id.next_button);
        nameStep = view.findViewById(R.id.step_name);
        mExoPlayerView = view.findViewById(R.id.exoplayer_view);
        videoIsNull = view.findViewById(R.id.exoplayer_null);


        mProcessList = getArguments().getParcelableArrayList("ArrayList");

        position = getArguments().getInt("process_position");

        mProcess = mProcessList.get(position);
        final String Description = mProcess.getDescription();
        final String nameDes = mProcess.getShortDescription();

        if(!TextUtils.isEmpty(mProcess.getVideoURL())) {

            initializePlayer(Uri.parse(mProcess.getVideoURL()));
            Log.e(TAG,"Error tagg------->:"+mProcess.getVideoURL());
            videoIsNull.setVisibility(View.GONE);
            mExoPlayerView.setVisibility(View.VISIBLE);


        }
        else {
            videoIsNull.setVisibility(View.VISIBLE);
            mExoPlayerView.setVisibility(View.GONE);
        }


        nameStep.setText(nameDes);
        descriptionTextview.setText(Description);


        previousB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position > 1) {
                    position--;
                }

                if (position >= 0) {
                    mProcess = mProcessList.get(position);
                    String Description = mProcess.getDescription();
                    String shortDes = mProcess.getShortDescription();

                    descriptionTextview.setText(Description);
                    nameStep.setText(shortDes);

                    if(!TextUtils.isEmpty(mProcess.getVideoURL())) {

                        initializePlayer(Uri.parse(mProcess.getVideoURL()));
                        videoIsNull.setVisibility(View.GONE);
                        mExoPlayerView.setVisibility(View.VISIBLE);


                    }
                    else {
                        videoIsNull.setVisibility(View.VISIBLE);
                        mExoPlayerView.setVisibility(View.GONE);
                    }

                } else {

                    if (position == 0) {

                        Toast.makeText(getContext(), "You are in the first step of this recipe", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                if (position == mProcessList.size()) {
                    Toast.makeText(getContext(), "You are in the Last step of this recipe", Toast.LENGTH_LONG).show();
                } else {

                    mProcess = mProcessList.get(position);
                    String Description = mProcess.getDescription();
                    String shortDesAdd = mProcess.getShortDescription();
                    nameStep.setText(shortDesAdd);
                    descriptionTextview.setText(Description);
                    if(!TextUtils.isEmpty(mProcess.getVideoURL())) {
                        initializePlayer(Uri.parse(mProcess.getVideoURL()));
                        videoIsNull.setVisibility(View.GONE);
                        mExoPlayerView.setVisibility(View.VISIBLE);

                    }
                    else {
                        videoIsNull.setVisibility(View.VISIBLE);
                        mExoPlayerView.setVisibility(View.GONE);
                    }


                }

            }
        });

        return view;
    }

    private void initializeMediaSession() {

        // Create a new MediaSession
        mMediaSession = new MediaSessionCompat(this, StepActivity.class.getSimpleName());

        // Enable callbacks from MediaButtons and TransportControls
        mMediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        // Do not let MediaButtons restart the player when the app is not visible
        mMediaSession.setMediaButtonReceiver(null);

        mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mMediaSession.setPlaybackState(mStateBuilder.build());


        // Handle callbacks from a media controller
        mMediaSession.setCallback(new StepCallback());

        // Start the Media Session since the activity is active
        mMediaSession.setActive(true);
    }

    private void initializePlayer(Uri mediaUri) {

        if(mExoPlayer ==null){

            TrackSelector trackSelector=new DefaultTrackSelector();
            LoadControl loadControl=new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(),trackSelector,loadControl);
            mExoPlayerView.setPlayer(mExoPlayer);
            //Prepare media source
            String userAgent=Util.getUserAgent(getContext(), "FrancosBakingApp");
            MediaSource mediaSource= new ExtractorMediaSource(mediaUri,new DefaultDataSourceFactory(getContext(),
                    userAgent),new DefaultExtractorsFactory(),
                    null,null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
            mExoPlayer.setPlayWhenReady(playWhenReady);
            mExoPlayer.seekTo(startWindow, playbackPosition);

        }

    }

    private void releasePlayer() {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();


    }
    @Override
    public void onStart() {
        super.onStart();

            releasePlayer();


    }


}
