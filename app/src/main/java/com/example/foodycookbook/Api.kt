package com.example.foodycookbook

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @POST("random.php")
    fun random(): Call<JsonObject>
}