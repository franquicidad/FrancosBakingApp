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
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class DescriptionFragment extends Fragment {

    Process mProcess;
    TextView descriptionTextview, nameStep;
    Bundle positionBund;
    ImageView imageView_thumb;
    TextView videoIsNull;
    int position;
    Button previousB, nextB;
    private Context context;
    private SimpleExoPlayerView mExoPlayerView;
    private PlayerView playerView;
    private boolean playWhenReady;
    //private static MediaSessionCompat mMediaSession;// problems

    private int startWindow;
    private long playbackPosition;
    private SimpleExoPlayer mExoPlayer;
    int exoPosition;
    private ArrayList<Process> mProcessList;
    private Long mPosition;
    private int windowIndexExoPlayer;
    private boolean isPlayWhenReadyExoPlayer;
    private static final String PLAYER_POSITION= "player_position";
    private static final String PLAYER_CURRENT_WINDOW="player_current_window";
    private static final String PLAYER_PLAY_STATE="player_play_state";

    private Long positionExoPlayer;





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
        imageView_thumb=view.findViewById(R.id.thumbnail_imageview);


        mProcessList = getArguments().getParcelableArrayList("ArrayList");

        position = getArguments().getInt("process_position");

        mProcess = mProcessList.get(position);
        final String Description = mProcess.getDescription();
        final String nameDes = mProcess.getShortDescription();

        if (savedInstanceState != null) {
            playbackPosition = savedInstanceState.getLong(PLAYER_POSITION);
            windowIndexExoPlayer = savedInstanceState.getInt(PLAYER_CURRENT_WINDOW);
            playWhenReady = savedInstanceState.getBoolean(PLAYER_PLAY_STATE);

        }


        String thumbnail=mProcess.getThumbnailURL();
        if(thumbnail.isEmpty()) {
            imageView_thumb.setVisibility(View.GONE);

        }else{
            Picasso.with(getContext()).load(thumbnail).into(imageView_thumb);
        }

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

                        releasePlayer();
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
                    releasePlayer();
                    String Description = mProcess.getDescription();
                    String shortDesAdd = mProcess.getShortDescription();
                    nameStep.setText(shortDesAdd);
                    descriptionTextview.setText(Description);
                    if(!TextUtils.isEmpty(mProcess.getVideoURL())) {
                        if(mExoPlayer!=null){

                            releasePlayer();
                            initializePlayer(Uri.parse(mProcess.getVideoURL()));
                            videoIsNull.setVisibility(View.GONE);
                            mExoPlayerView.setVisibility(View.VISIBLE);

                        }


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
        saveState();
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }

    }


    private void saveState() {
        if (mExoPlayer != null) {
            positionExoPlayer = mExoPlayer.getCurrentPosition();
            windowIndexExoPlayer = mExoPlayer.getCurrentWindowIndex();
            playWhenReady = mExoPlayer.getPlayWhenReady();
        }

    }

    /**
     * @param outState
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        outState.putLong(PLAYER_POSITION, positionExoPlayer);
        outState.putInt(PLAYER_CURRENT_WINDOW, windowIndexExoPlayer);
        outState.putBoolean(PLAYER_PLAY_STATE, playWhenReady);

    }




    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onPause() {
        super.onPause();

        releasePlayer();


    }
    @Override
    public void onStart() {
        super.onStart();

           initializePlayer(Uri.parse(mProcess.getVideoURL()));


    }

    @Override
    public void onResume() {
        super.onResume();

        initializePlayer(Uri.parse(mProcess.getVideoURL()));

    }
}
