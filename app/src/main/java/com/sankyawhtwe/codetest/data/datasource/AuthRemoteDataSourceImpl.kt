package com.sankyawhtwe.codetest.data.datasource

import io.ktor.client.HttpClient

class AuthRemoteDataSourceImpl() : AuthRemoteDataSource {
    override suspend fun logIn(email: String, password: String): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(email: String, password: String): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun logOut() {
        TODO("Not yet implemented")
    }
}