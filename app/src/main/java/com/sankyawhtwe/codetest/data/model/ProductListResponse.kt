package com.sankyawhtwe.codetest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponse(
    val products : List<ProductResponse>
)

@Serializable
data class ProductResponse(
    val id: Int,
    val title:String?,
    val price:Double?,
    val category:String?,
    val description:String?,
    val image: String?
)
