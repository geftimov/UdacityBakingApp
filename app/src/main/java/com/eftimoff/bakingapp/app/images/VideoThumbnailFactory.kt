package com.eftimoff.bakingapp.app.images

import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import java.io.InputStream

class VideoThumbnailFactory : ModelLoaderFactory<VideoThumbnailUrl, InputStream> {
    override fun teardown() {

    }

    override fun build(modelLoaderFactory: MultiModelLoaderFactory): ModelLoader<VideoThumbnailUrl, InputStream> {
        return VideoThumbnailLoader()
    }

}