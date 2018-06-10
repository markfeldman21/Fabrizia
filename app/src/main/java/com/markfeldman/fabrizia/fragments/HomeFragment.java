package com.markfeldman.fabrizia.fragments;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.markfeldman.fabrizia.R;
import com.markfeldman.fabrizia.utilities.YouTubeConfig;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements YouTubePlayer.OnInitializedListener {
    private final String TAG = "HOME FRAGMENT";
    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        youTubePlayerSupportFragment = new YouTubePlayerSupportFragment();
        youTubePlayerSupportFragment.initialize(YouTubeConfig.getApiKey(),this);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_play_button, youTubePlayerSupportFragment);
        fragmentTransaction.commit();

        return view;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Log.d("TAG","Success initialize" );
        youTubePlayer.loadVideo("aql_JEABmd0");
        //youTubePlayer.pause();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
      Log.d("TAG","Failed to initialize: " + result.toString());
    }
}
