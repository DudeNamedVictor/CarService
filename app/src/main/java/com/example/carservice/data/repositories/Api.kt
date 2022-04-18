package com.example.carservice.data.repositories

import com.example.carservice.data.models.CatModel
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("v1/images/search")
    fun getCats(): Call<List<CatModel>>
}