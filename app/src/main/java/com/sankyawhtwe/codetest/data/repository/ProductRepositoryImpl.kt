package com.sankyawhtwe.codetest.data.repository

import com.sankyawhtwe.codetest.data.datasource.ProductRemoteDataSource
import com.sankyawhtwe.codetest.domain.model.ProductModel

class ProductRepositoryImpl(
    private val productRemoteDataSource: ProductRemoteDataSource
) : ProductRepository {
    override suspend fun getProductList(): Result<List<ProductModel>> {
        return productRemoteDataSource.getProductList()
    }
}