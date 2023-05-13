package com.example.myapplication.retrofit

import retrofit2.http.GET

interface FactApi {
    @GET("fact")
    suspend fun getFact() : Fact
}