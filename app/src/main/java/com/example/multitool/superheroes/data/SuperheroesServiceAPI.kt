package com.example.multitool.superheroes.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroesServiceAPI {

    @GET("search/{name}")
    suspend fun searchByName(
        @Path("name") query:String
    ):Response<SuperheroesResponse>

    @GET("{id}")
    suspend fun searchById(
        @Path("id") identifier:String
    ):Response<Superhero>

}