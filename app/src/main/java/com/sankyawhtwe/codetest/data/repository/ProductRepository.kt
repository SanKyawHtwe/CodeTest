package com.sankyawhtwe.codetest.data.repository

import com.sankyawhtwe.codetest.domain.model.ProductModel

interface ProductRepository {
    suspend fun getProductList(): Result<List<ProductModel>>
}