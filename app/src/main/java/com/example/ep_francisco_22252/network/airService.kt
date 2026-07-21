package com.example.ep_francisco_22252.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import retrofit2.http.Path
import retrofit2.http.GET
import com.example.ep_francisco_22252.data.*

interface AirService{
    @GET("v1/air-quality?latitude={lat}&longitude={long}&current=european_aqi,pm10,pm2_5,nitrogen_dioxide")
    suspend fun getAir(@Path("lat")latitude: Double, @Path("long")longitude: Double):List<Air>
}

object RetrofitClient {
    private const val BASE_URL = "https://air-quality-api.open-meteo.com/"

    val service: AirService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AirService::class.java)
    }
}