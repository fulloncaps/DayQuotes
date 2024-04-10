package com.example.dayquotes

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET(value = "api/random")
    fun getData(): Call<List<MyDataItem>>
}