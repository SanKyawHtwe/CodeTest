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

    override suspend fun getCategories(): Result<List<String>> {
        return handle<List<String>> {
            httpClient.get("https://fakestoreapi.com/products/categories")
        }
    }

    override suspend fun getProductsByCategory(category: String): Result<List<ProductModel>> {
        return handle<List<ProductResponse>> {
            httpClient.get("https://fakestoreapi.com/products/category/$category")
        }.map {
            it.map { productResponse ->
                productResponse.toProductModel()
            }
        }
    }

    override suspend fun getProductDetails(id: Int): Result<ProductModel> {
        return handle<ProductResponse> {
            httpClient.get("https://fakestoreapi.com/products/$id")
        }.map {
            it.toProductModel()
        }
    }
}