package com.navigation.navigationcomponent.data.model

import com.navigation.navigationcomponent.data.model.Data

data class GetList(
    val `data`: List<Data>,
    val responseCode: Int
)