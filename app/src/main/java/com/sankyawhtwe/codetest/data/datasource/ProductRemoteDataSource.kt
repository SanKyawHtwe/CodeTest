package com.sankyawhtwe.codetest.data.datasource

import com.sankyawhtwe.codetest.domain.model.ProductModel

interface ProductRemoteDataSource {

    suspend fun getProductList() :Result<List<ProductModel>>
}