package com.eftimoff.bakingapp.app.injection;

import android.content.Context;

import com.eftimoff.bakingapp.app.injection.qualifiers.ForApplication;
import com.eftimoff.bakingapp.app.injection.scopes.PerApplication;
import com.eftimoff.bakingapp.app.repositories.RecipeRepository;
import com.eftimoff.bakingapp.app.repositories.RecipeRepositoryImpl;
import com.eftimoff.bakingapp.app.storage.remote.RemoteApi;
import com.eftimoff.bakingapp.app.storage.remote.RemoteStorage;
import com.eftimoff.bakingapp.app.storage.remote.RemoteStorageImpl;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private final long CACHE_SIZE = 52428800L;

    @Provides
    @PerApplication
    public Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Provides
    @PerApplication
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient().newBuilder();
    }

    @Provides
    @PerApplication
    public Cache provideCache(@ForApplication Context context) {
        return new Cache(context.getCacheDir(), this.CACHE_SIZE);
    }

    @Provides
    @PerApplication
    public RecipeRepository provideRecipeRepository(RecipeRepositoryImpl recipeRepository) {
        return recipeRepository;
    }

    @Provides
    @PerApplication
    public RemoteStorage provideRemoteStorage(RemoteStorageImpl remoteStorage) {
        return remoteStorage;
    }

    @Provides
    @PerApplication
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @PerApplication
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor();
    }

    @Provides
    @PerApplication
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder okHttpBuilder, Cache cache, HttpLoggingInterceptor httpLoggingInterceptor) {
        okHttpBuilder.cache(cache);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpBuilder.addNetworkInterceptor(httpLoggingInterceptor);
        return okHttpBuilder.build();
    }

    @Provides
    @PerApplication
    public RemoteApi provideRemoteApi(OkHttpClient okHttpClient, Retrofit.Builder retrofitBuilder, Gson gson) {
        return retrofitBuilder.client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://d17h27t6h515a5.cloudfront.net")
                .build()
                .create(RemoteApi.class);
    }

}
