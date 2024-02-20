package com.example.multitool.zodiapp.data

import com.example.multitool.R

sealed class Zodiac (val id:String, val image:Int, val name:Int, val date:Int) {

    object Aries : Zodiac("aries", R.drawable.aries_svg, R.string.zodiacNameAries, R.string.zodiacDateAries)
    object Taurus : Zodiac("taurus", R.drawable.taurus_svg, R.string.zodiacNameTaurus, R.string.zodiacDateTaurus)
    object Gemini : Zodiac("gemini", R.drawable.gemini_svg, R.string.zodiacNameGemini, R.string.zodiacDateGemini)
    object Cancer : Zodiac("cancer", R.drawable.cancer_svg, R.string.zodiacNameCancer, R.string.zodiacDateCancer)
    object Leo : Zodiac("leo", R.drawable.leo_svg, R.string.zodiacNameLeo, R.string.zodiacDateLeo)
    object Virgo : Zodiac("virgo", R.drawable.virgo_svg, R.string.zodiacNameVirgo, R.string.zodiacDateVirgo)
    object Libra : Zodiac("libra", R.drawable.libra_svg, R.string.zodiacNameLibra, R.string.zodiacDateLibra)
    object Scorpio : Zodiac("scorpio", R.drawable.scorpion_svg, R.string.zodiacNameScorpio, R.string.zodiacDateScorpio)
    object Sagittarius : Zodiac("sagittarius", R.drawable.sagittarius_svg, R.string.zodiacNameSagittarius, R.string.zodiacDateSagittarius)
    object Capricorn : Zodiac("capricorn", R.drawable.capricorn_svg, R.string.zodiacNameCapricorn, R.string.zodiacDateCapricorn)
    object Aquarius : Zodiac("aquarius", R.drawable.aquarius_svg, R.string.zodiacNameAquarius, R.string.zodiacDateAquarius)
    object Pisces : Zodiac("pisces", R.drawable.pisces_svg, R.string.zodiacNamePisces, R.string.zodiacDatePisces)

}