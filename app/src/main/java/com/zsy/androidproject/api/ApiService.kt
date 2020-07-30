package com.zsy.androidproject.api

import com.zsy.androidproject.models.UserObject
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("placeholder/user/{userId}")
    suspend fun getUser(@Path("userId") userId: String): UserObject
}