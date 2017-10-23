package com.eftimoff.bakingapp.app.images;

import android.support.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

import java.io.InputStream;

public class VideoThumbnailLoader implements ModelLoader<VideoThumbnailUrl, InputStream> {

    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(VideoThumbnailUrl videoThumbnailUrl, int width, int height, Options options) {
        return new LoadData<>(new ObjectKey(videoThumbnailUrl), new VideoThumbnailFetcher(videoThumbnailUrl));
    }

    @Override
    public boolean handles(VideoThumbnailUrl videoThumbnailUrl) {
        return true;
    }
}
