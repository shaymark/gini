package com.markoapps.gini.di

import com.markoapps.gini.GiniApplication
import com.markoapps.gini.MainActivity
import com.markoapps.gini.network.IntApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideIntApi(

    ): IntApi {
        return  Retrofit.Builder()
            .baseUrl("https://pastebin.com/raw/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IntApi::class.java)
    }
}