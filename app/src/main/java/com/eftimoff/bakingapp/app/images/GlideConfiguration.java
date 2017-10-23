package com.eftimoff.bakingapp.app.images;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.eftimoff.bakingapp.app.BakingApplication;

import java.io.InputStream;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

@GlideModule
public class GlideConfiguration extends AppGlideModule {

    @Inject
    OkHttpClient okHttpClient;

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);
        BakingApplication.component().inject(this);

        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(okHttpClient));
        registry.replace(VideoThumbnailUrl.class, InputStream.class, new VideoThumbnailFactory());
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
