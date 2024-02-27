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
    @SerializedName("powerstats") val powerstats:Array<PowerStats>
) {

}

class Image(
    @SerializedName("url") val url:String
){

}

class PowerStats(
    @SerializedName("intelligence") val intelligence:Int,
    @SerializedName("strength") val strength:Int,
    @SerializedName("speed") val speed:Int,
    @SerializedName("durability") val durability:Int,
    @SerializedName("power") val power:Int,
    @SerializedName("combat") val combat:Int,
){

}
