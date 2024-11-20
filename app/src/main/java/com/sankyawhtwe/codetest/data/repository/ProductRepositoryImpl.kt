package com.sankyawhtwe.codetest.data.repository

import com.sankyawhtwe.codetest.data.datasource.ProductRemoteDataSource
import com.sankyawhtwe.codetest.domain.model.ProductModel

class ProductRepositoryImpl(
    private val productRemoteDataSource: ProductRemoteDataSource
) : ProductRepository {
    override suspend fun getProductList(): Result<List<ProductModel>> {
        return productRemoteDataSource.getProductList()
    }

    override suspend fun getCategories(): Result<List<String>> {
        return productRemoteDataSource.getCategories()
    }

    override suspend fun getProductsByCategory(category: String): Result<List<ProductModel>> {
        return productRemoteDataSource.getProductsByCategory(category = category)
    }

    override suspend fun getProductDetails(id: Int): Result<ProductModel> {
        return productRemoteDataSource.getProductDetails(id = id)
    }
}