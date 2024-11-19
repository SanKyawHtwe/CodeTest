package com.sankyawhtwe.codetest.data.mapper

import com.sankyawhtwe.codetest.data.model.ProductListResponse
import com.sankyawhtwe.codetest.domain.model.ProductModel

fun ProductListResponse.toProductModel(): List<ProductModel> = this.products.map {
    ProductModel(
        id = it.id,
        title = it.title.orEmpty(),
        price = it.price ?: 0.00,
        category = it.category.orEmpty(),
        description = it.description.orEmpty(),
        image = it.image.orEmpty()
    )
}