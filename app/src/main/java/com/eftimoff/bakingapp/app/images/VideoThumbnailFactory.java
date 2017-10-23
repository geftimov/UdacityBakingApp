package com.eftimoff.bakingapp.app.images;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;

public class VideoThumbnailFactory implements ModelLoaderFactory<VideoThumbnailUrl, InputStream> {

    @Override
    public ModelLoader<VideoThumbnailUrl, InputStream> build(MultiModelLoaderFactory multiFactory) {
        return new VideoThumbnailLoader();
    }

    @Override
    public void teardown() {

    }
}
