package com.example.multitool.zodiapp.data

import com.example.multitool.R

sealed class Zodiac (val image:Int, val name:Int, val date:Int) {

    object Aries : Zodiac(R.drawable.aries_svg, R.string.zodiacNameAries, R.string.zodiacDateAries)
    object Taurus : Zodiac(R.drawable.taurus_svg, R.string.zodiacNameTaurus, R.string.zodiacDateTaurus)
    object Gemini : Zodiac(R.drawable.gemini_svg, R.string.zodiacNameGemini, R.string.zodiacDateGemini)
    object Cancer : Zodiac(R.drawable.cancer_svg, R.string.zodiacNameCancer, R.string.zodiacDateCancer)
    object Leo : Zodiac(R.drawable.leo_svg, R.string.zodiacNameLeo, R.string.zodiacDateLeo)
    object Virgo : Zodiac(R.drawable.virgo_svg, R.string.zodiacNameVirgo, R.string.zodiacDateVirgo)
    object Libra : Zodiac(R.drawable.libra_svg, R.string.zodiacNameLibra, R.string.zodiacDateLibra)
    object Scorpio : Zodiac(R.drawable.scorpion_svg, R.string.zodiacNameScorpio, R.string.zodiacDateScorpio)
    object Sagittarius : Zodiac(R.drawable.sagittarius_svg, R.string.zodiacNameSagittarius, R.string.zodiacDateSagittarius)
    object Capricorn : Zodiac(R.drawable.capricorn_svg, R.string.zodiacNameCapricorn, R.string.zodiacDateCapricorn)
    object Aquarius : Zodiac(R.drawable.aquarius_svg, R.string.zodiacNameAquarius, R.string.zodiacDateAquarius)
    object Pisces : Zodiac(R.drawable.pisces_svg, R.string.zodiacNamePisces, R.string.zodiacDatePisces)

}