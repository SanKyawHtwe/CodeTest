package com.sankyawhtwe.codetest.data.service

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorUtils {
    fun createKtor(): HttpClient{
        return HttpClient(Android){
            install(ContentNegotiation){
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}