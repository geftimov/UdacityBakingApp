package com.eftimoff.bakingapp.recipestep.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.injection.AppComponent;
import com.eftimoff.bakingapp.app.models.Step;
import com.eftimoff.bakingapp.app.view.BaseFragment;
import com.eftimoff.bakingapp.app.view.IntentExtras;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;

public class RecipeStepFragment extends BaseFragment {

    private static final String CURRENT_POSITION = "current_position";

    @BindView(R.id.recipeStepDescription)
    TextView recipeStepDescription;
    @BindView(R.id.recipeStepVideo)
    SimpleExoPlayerView recipeStepVideo;
    @BindView(R.id.recipeStepImage)
    ImageView recipeStepImage;

    private SimpleExoPlayer exoPlayer;
    private long currentPosition;

    public static RecipeStepFragment newInstance(Step step) {
        RecipeStepFragment fragment = new RecipeStepFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(IntentExtras.EXTRA_STEP, step);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_recipe_step;
    }

    @Override
    protected void inject(AppComponent appComponent) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getStep().getShortDescription());
        }

        recipeStepDescription.setText(getStep().getDescription());

        loadThumbnail();

        currentPosition = getSavedCurrentPosition(savedInstanceState);
    }

    private void loadThumbnail() {
        if (getStep().getThumbnailURL() == null || getStep().getThumbnailURL().trim().isEmpty()) {
            recipeStepImage.setVisibility(View.GONE);
        } else {
            Glide.with(recipeStepImage).load(getStep().getThumbnailURL()).into(recipeStepImage);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        initializePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (exoPlayer != null) {
            currentPosition = exoPlayer.getCurrentPosition();
        }
        releasePlayer();
    }

    private void initializePlayer() {
        if (getStep().getVideoURL() == null || getStep().getVideoURL().isEmpty()) {
            recipeStepVideo.setVisibility(View.GONE);
        } else {
            initializePlayer(Uri.parse(getStep().getVideoURL()), currentPosition);
        }
    }

    private long getSavedCurrentPosition(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return 0;
        }
        return savedInstanceState.getLong(CURRENT_POSITION, 0);
    }

    private Step getStep() {
        return getArguments().getParcelable(IntentExtras.EXTRA_STEP);
    }


    private void initializePlayer(Uri uri, long currentPosition) {
        DefaultTrackSelector trackSelector = new DefaultTrackSelector();
        DefaultLoadControl loadControl = new DefaultLoadControl();

        exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
        recipeStepVideo.setPlayer(exoPlayer);

        String userAgent = Util.getUserAgent(getContext(), "Recipe");
        ExtractorMediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
        exoPlayer.prepare(mediaSource);
        exoPlayer.seekTo(currentPosition);
        exoPlayer.setPlayWhenReady(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(CURRENT_POSITION, exoPlayer.getCurrentPosition());
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
        }
    }

}
