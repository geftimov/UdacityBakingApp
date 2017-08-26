package com.eftimoff.bakingapp.app.images

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.signature.ObjectKey
import java.io.InputStream


class VideoThumbnailLoader : ModelLoader<VideoThumbnailUrl, InputStream> {
    override fun handles(url: VideoThumbnailUrl): Boolean = true

    override fun buildLoadData(videoThumbnailUrl: VideoThumbnailUrl, width: Int, height: Int, options: Options?): ModelLoader.LoadData<InputStream> {
        return ModelLoader.LoadData(ObjectKey(videoThumbnailUrl), VideoThumbnailFetcher(videoThumbnailUrl))
    }
}