package com.sankyawhtwe.codetest.data.repository

import com.sankyawhtwe.codetest.data.datasource.AuthRemoteDataSource

class AuthRepositoryImpl(private val authRemoteDataSource: AuthRemoteDataSource) : AuthRepository {
    override suspend fun logIn(email: String, password: String): Result<String> {
        return authRemoteDataSource.logIn(email = email, password = password)
    }

    override suspend fun signUp(email: String, password: String): Result<String> {
        return authRemoteDataSource.signUp(email = email, password = password)
    }

    override suspend fun logOut() {
        authRemoteDataSource.logOut()
    }
}