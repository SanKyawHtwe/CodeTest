package com.sankyawhtwe.codetest.data.datasource

import com.sankyawhtwe.codetest.domain.model.ProductModel

class FakeProductDataSource :ProductRemoteDataSource{
    override suspend fun getProductList(): Result<List<ProductModel>> {
        return Result.success(
            listOf(
                ProductModel(
                    id = 1,
                    title = "Apple",
                    price = 10.01,
                    category = "Fruit",
                    description = "this is apple",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
                ),
                ProductModel(
                    id = 2,
                    title = "Orange",
                    price = 10.01,
                    category = "Fruit",
                    description = "this is apple",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
                ),
                ProductModel(
                    id = 3,
                    title = "Banana",
                    price = 10.01,
                    category = "Fruit",
                    description = "this is apple",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
                ),
                ProductModel(
                    id = 4,
                    title = "Apple",
                    price = 10.01,
                    category = "Fruit",
                    description = "this is apple",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
                ),
                ProductModel(
                    id = 5,
                    title = "Apple",
                    price = 10.01,
                    category = "Fruit",
                    description = "this is apple",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
                ),
            )
        )
    }

    override suspend fun getCategories(): Result<List<String>> {
        return  Result.success(listOf(
            "electronics",
            "jewelery",
            "men's clothing",
            "women's clothing"
        ))
    }

    override suspend fun getProductsByCategory(category: String): Result<List<ProductModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductDetails(id: Int): Result<ProductModel> {
        TODO("Not yet implemented")
    }

    override suspend fun createProduct(): Result<Unit> {
        TODO("Not yet implemented")
    }

}