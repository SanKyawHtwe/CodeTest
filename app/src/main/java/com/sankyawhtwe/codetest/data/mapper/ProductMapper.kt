package com.sankyawhtwe.codetest.data.mapper

import com.sankyawhtwe.codetest.data.model.ProductResponse
import com.sankyawhtwe.codetest.domain.model.ProductModel

fun ProductResponse.toProductModel(): ProductModel =
    ProductModel(
        id = this.id,
        title = this.title.orEmpty(),
        price = this.price ?: 0.00,
        category = this.category.orEmpty(),
        description = this.description.orEmpty(),
        image = this.image.orEmpty()
    )
