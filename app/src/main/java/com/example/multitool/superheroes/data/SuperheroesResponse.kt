package com.example.multitool.superheroes.data

import com.google.gson.annotations.SerializedName

class SuperheroesResponse (
    @SerializedName("response") val response:String,
    @SerializedName("results-for") val resultFor:String,
    @SerializedName("results") val results:List<Superhero>

){

}

class Superhero (
    @SerializedName("id") val id:String,
    @SerializedName("name") val name:String,
) {

}