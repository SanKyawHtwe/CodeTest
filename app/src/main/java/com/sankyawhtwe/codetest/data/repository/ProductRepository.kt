package com.sankyawhtwe.codetest.data.repository

import com.sankyawhtwe.codetest.data.model.ProductRequest
import com.sankyawhtwe.codetest.domain.model.ProductModel

interface ProductRepository {
    suspend fun getProductList(): Result<List<ProductModel>>

    suspend fun getCategories(): Result<List<String>>

    suspend fun getProductsByCategory(category: String): Result<List<ProductModel>>

    suspend fun getProductDetails(id: Int): Result<ProductModel>

    suspend fun createProduct(newProduct:ProductRequest) :Result<Unit>
}