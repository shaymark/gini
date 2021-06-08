package com.markoapps.gini.network

import com.markoapps.gini.models.NumberResponse
import retrofit2.http.GET

interface IntApi {

    @GET("8wJzytQX")
    suspend fun getInts(): NumberResponse

}