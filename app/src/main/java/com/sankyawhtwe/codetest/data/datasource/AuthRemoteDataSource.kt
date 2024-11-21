package com.sankyawhtwe.codetest.data.datasource

interface AuthRemoteDataSource {

    suspend fun logIn(email:String,password:String): Result<String>

    suspend fun signUp(email:String,password:String): Result<String>

    suspend fun logOut()
}