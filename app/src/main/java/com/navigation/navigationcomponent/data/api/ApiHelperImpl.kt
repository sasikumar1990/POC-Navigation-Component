package com.navigation.navigationcomponent.data.api

import com.navigation.navigationcomponent.data.model.GetList
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
):ApiHelper{

    override suspend fun getList(): Response<GetList>  = apiService.getList()


}