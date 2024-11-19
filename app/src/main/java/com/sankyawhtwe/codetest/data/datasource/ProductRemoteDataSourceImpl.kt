package com.sankyawhtwe.codetest.data.datasource

import com.sankyawhtwe.codetest.data.mapper.toProductModel
import com.sankyawhtwe.codetest.data.model.ProductResponse
import com.sankyawhtwe.codetest.domain.model.ProductModel
import com.sci.coffeeandroid.feature.menudetails.data.utils.handle
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ProductRemoteDataSourceImpl(private val httpClient: HttpClient) : ProductRemoteDataSource {
    override suspend fun getProductList(): Result<List<ProductModel>> {
        return handle<List<ProductResponse>> {
            httpClient.get("https://fakestoreapi.com/products")
        }.map {
            it.map { productResponse ->
                productResponse.toProductModel()
            }
        }
    }
}