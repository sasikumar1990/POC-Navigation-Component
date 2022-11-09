package com.navigation.navigationcomponent.data.api

import com.navigation.navigationcomponent.data.model.GetList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val BASE_URL = "https://mockapi-123.herokuapp.com/"

    }
    @GET("challenge-services")
    suspend fun getList(): Response<GetList>

}