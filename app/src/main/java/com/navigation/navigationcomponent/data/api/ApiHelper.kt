package com.navigation.navigationcomponent.data.api

import com.navigation.navigationcomponent.data.model.GetList
import retrofit2.Response

interface ApiHelper {

    suspend fun getList():Response<GetList>

}