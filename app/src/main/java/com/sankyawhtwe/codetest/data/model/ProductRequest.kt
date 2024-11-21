package com.sankyawhtwe.codetest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductRequest(
    val title:String,
    val price:Double,
    val category:String,
    val description:String,
    val image: String
)
