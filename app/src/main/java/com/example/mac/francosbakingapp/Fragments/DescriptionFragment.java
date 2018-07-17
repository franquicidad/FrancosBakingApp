package com.example.mac.francosbakingapp.Fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DescriptionFragment extends Fragment {

    Process mProcess;
    TextView descriptionTextview, nameStep;
    Bundle positionBund;
    TextView videoIsNull;
    int position;
    Button previousB, nextB;
    private Context context;
    private SimpleExoPlayerView mExoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private PlayerView playerView;
    private boolean playWhenReady;
    private int startWindow;
    private long playbackPosition;
    private SimpleExoPlayer player;
    private ArrayList<Process> mProcessList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frame_layout, container, false);

        descriptionTextview = view.findViewById(R.id.description_textview);
        previousB = view.findViewById(R.id.previous_button);
        nextB = view.findViewById(R.id.next_button);
        nameStep = view.findViewById(R.id.step_name);
        playerView = view.findViewById(R.id.exoplayer_view);
        videoIsNull = view.findViewById(R.id.exoplayer_null);


        mProcessList = getArguments().getParcelableArrayList("ArrayList");

        position = getArguments().getInt("process_position");

        mProcess = mProcessList.get(position);
        final String Description = mProcess.getDescription();
        final String nameDes = mProcess.getShortDescription();

        getVideo(mProcess.getVideoURL());

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

                    getVideo(mProcess.getVideoURL());

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
                    getVideo(mProcess.getVideoURL().toString());

                }

            }
        });

        return view;
    }

    private void getVideo(String mVideoUrl) {
        Log.d("Carga video " ,mVideoUrl);
        if (mVideoUrl != "" ) {
            initializePlayer(Uri.parse(mVideoUrl));
            videoIsNull.setVisibility(View.GONE);
        } else {
            videoIsNull.setVisibility(View.VISIBLE);
            playerView.setVisibility(View.GONE);

        }

    }


    private void initializePlayer(Uri mediaUri) {
        player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(),
                new DefaultLoadControl());
        playerView.setPlayer(player);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(startWindow, playbackPosition);
        String userAgent = Util.getUserAgent(getContext(), "bakingapp-exoplayer");
        MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(getContext(), userAgent),
                new DefaultExtractorsFactory(), null, null);

        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void releasePlayer() {
        player.stop();
        player.release();
        player = null;
    }
}
