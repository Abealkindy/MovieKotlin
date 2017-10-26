package com.rosinante24.firstinthisversion.Utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Rosinante24 on 26/10/17.
 */
class InitRetrofit {

    fun getInitRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(EndPoints.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getInitInstance(): ApiService {
        return getInitRetrofit().create(ApiService::class.java)
    }
}