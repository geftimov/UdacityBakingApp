package com.eftimoff.bakingapp.recipestep.view

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.injection.AppComponent
import com.eftimoff.bakingapp.app.models.Step
import com.eftimoff.bakingapp.app.view.BaseFragment
import com.eftimoff.bakingapp.app.view.EXTRA_STEP
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.fragment_recipe_step.*

class RecipeStepFragment : BaseFragment() {

    private var exoPlayer: SimpleExoPlayer? = null

    companion object {
        fun newInstance(step: Step): RecipeStepFragment {
            val recipeStepFragment = RecipeStepFragment()
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_STEP, step)
            recipeStepFragment.arguments = bundle
            return recipeStepFragment
        }
    }

    override fun inject(appComponent: AppComponent) {

    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_recipe_step
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = getStep().shortDescription

        recipeStepDescription.text = getStep().description

        if (getStep().videoURL.isEmpty()) {
            recipeStepVideo.visibility = View.GONE
        } else {
            initializePlayer(Uri.parse(getStep().videoURL))
        }
    }

    /**
     * Initialize ExoPlayer.
     *
     * @param mediaUri The URI of the sample to play.
     */
    private fun initializePlayer(mediaUri: Uri) {
        // Create an instance of the ExoPlayer.
        val trackSelector = DefaultTrackSelector()
        val loadControl = DefaultLoadControl()
        exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl)
        recipeStepVideo.player = exoPlayer

        // Prepare the MediaSource.
        val userAgent = Util.getUserAgent(context, "Recipe")
        val mediaSource = ExtractorMediaSource(mediaUri, DefaultDataSourceFactory(
                context, userAgent), DefaultExtractorsFactory(), null, null)
        exoPlayer?.prepare(mediaSource)
        exoPlayer?.playWhenReady = true
    }


    private fun getStep(): Step {
        return arguments.getParcelable(EXTRA_STEP)
    }

    override fun onDestroyView() {
        releasePlayer()
        super.onDestroyView()
    }

    /**
     * Release ExoPlayer.
     */
    private fun releasePlayer() {
        exoPlayer.apply {
            exoPlayer?.stop()
            exoPlayer?.release()
        }
    }


}