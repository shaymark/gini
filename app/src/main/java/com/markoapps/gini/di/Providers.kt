package com.markoapps.gini.di

import com.markoapps.gini.network.IntApi
import com.markoapps.gini.viewmodels.MyViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Providers {

    val intApi: IntApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pastebin.com/raw/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: IntApi = retrofit.create(IntApi::class.java)
        service
    }

    val viewModelFactory: MyViewModelFactory by lazy {
        MyViewModelFactory(intApi)
    }

}