package com.sankyawhtwe.codetest.data.datasource

import com.sankyawhtwe.codetest.domain.model.ProductModel

interface ProductRemoteDataSource {

    suspend fun getProductList() :Result<List<ProductModel>>

    suspend fun getCategories(): Result<List<String>>

    suspend fun getProductsByCategory(category: String): Result<List<ProductModel>>

    suspend fun getProductDetails(id: Int): Result<ProductModel>
}