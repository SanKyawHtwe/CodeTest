package com.sankyawhtwe.codetest.data.repository

interface AuthRepository {
    suspend fun logIn(email:String,password:String): Result<String>

    suspend fun signUp(email:String,password:String): Result<String>

    suspend fun logOut()
}