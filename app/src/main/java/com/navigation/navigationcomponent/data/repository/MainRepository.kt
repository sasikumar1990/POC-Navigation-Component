package com.navigation.navigationcomponent.data.repository

import com.navigation.navigationcomponent.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor (private val apiHelper: ApiHelper) {

    suspend fun getList() = apiHelper.getList()
}