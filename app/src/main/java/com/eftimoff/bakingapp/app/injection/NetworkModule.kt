package com.eftimoff.bakingapp.app.injection

import android.content.Context
import com.eftimoff.bakingapp.app.repositories.RecipeRepository
import com.eftimoff.bakingapp.app.repositories.RecipeRepositoryImpl
import com.eftimoff.bakingapp.app.storage.remote.RemoteApi
import com.eftimoff.bakingapp.app.storage.remote.RemoteStorage
import com.eftimoff.bakingapp.app.storage.remote.RemoteStorageImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    private val CACHE_SIZE: Long = 50 * 1024 * 1024

    @Provides
    @PerApplication
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }

    @Provides
    @PerApplication
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient().newBuilder()
    }

    @Provides
    @PerApplication
    fun provideCache(@ForApplication context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE)
    }

    @Provides
    @PerApplication
    fun provideRecipeRepository(recipeRepository: RecipeRepositoryImpl): RecipeRepository = recipeRepository

    @Provides
    @PerApplication
    fun provideRemoteStorage(remoteStorage: RemoteStorageImpl): RemoteStorage = remoteStorage

    @Provides
    @PerApplication
    fun provideGson(): Gson = Gson()

    @Provides
    @PerApplication
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    @PerApplication
    fun provideOkHttpClient(okHttpBuilder: OkHttpClient.Builder, cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        okHttpBuilder.cache(cache)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addNetworkInterceptor(httpLoggingInterceptor)

        return okHttpBuilder.build()
    }

    @Provides
    @PerApplication
    fun provideRemoteApi(okHttpClient: OkHttpClient, retrofitBuilder: Retrofit.Builder, gson: Gson): RemoteApi {
        return retrofitBuilder.client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://d17h27t6h515a5.cloudfront.net")
                .build()
                .create(RemoteApi::class.java)
    }
}