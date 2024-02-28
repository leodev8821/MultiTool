package com.example.multitool.superheroes.utils

import com.example.multitool.superheroes.data.SuperheroesServiceAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    companion object {
        fun getRetrofit(): SuperheroesServiceAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(SuperheroesServiceAPI::class.java)
        }
    }
}