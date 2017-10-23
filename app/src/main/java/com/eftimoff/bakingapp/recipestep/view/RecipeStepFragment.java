package com.eftimoff.bakingapp.recipestep.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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

    @BindView(R.id.recipeStepDescription)
    TextView recipeStepDescription;
    @BindView(R.id.recipeStepVideo)
    SimpleExoPlayerView recipeStepVideo;

    private SimpleExoPlayer exoPlayer;

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

        if (getStep().getVideoURL() == null || getStep().getVideoURL().isEmpty()) {
            recipeStepVideo.setVisibility(View.GONE);
        } else {
            initializePlayer(Uri.parse(getStep().getVideoURL()));
        }

    }

    private Step getStep() {
        return getArguments().getParcelable(IntentExtras.EXTRA_STEP);
    }


    private void initializePlayer(Uri uri) {
        DefaultTrackSelector trackSelector = new DefaultTrackSelector();
        DefaultLoadControl loadControl = new DefaultLoadControl();

        exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
        recipeStepVideo.setPlayer(exoPlayer);

        String userAgent = Util.getUserAgent(getContext(), "Recipe");
        ExtractorMediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);
    }

    @Override
    public void onDestroyView() {
        releasePlayer();
        super.onDestroyView();
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
        }
    }

}
