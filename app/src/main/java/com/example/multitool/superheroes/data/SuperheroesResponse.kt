package com.example.multitool.superheroes.data

import com.google.gson.annotations.SerializedName

class SuperheroesResponse (
    @SerializedName("response") val response:String,
    @SerializedName("results-for") val resultFor:String,
    @SerializedName("results") val results:List<Superhero>,
){

}

class Superhero (
    @SerializedName("id") val id:String,
    @SerializedName("name") val name:String,
    @SerializedName("image") val image: Image,
    @SerializedName("powerstats") val powerstats:PowerStats
) {

}

class Image(
    @SerializedName("url") val url:String
){

}

class PowerStats(
    @SerializedName("intelligence") val intelligence:String,
    @SerializedName("strength") val strength:String,
    @SerializedName("speed") val speed:String,
    @SerializedName("durability") val durability:String,
    @SerializedName("power") val power:String,
    @SerializedName("combat") val combat:String,
){
}
