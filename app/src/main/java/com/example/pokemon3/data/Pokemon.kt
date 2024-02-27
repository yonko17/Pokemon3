package com.example.pokemon3.data

data class PokemonResponse(
    val count:Int,
    val next:String,
    val previous:String,
    val results:List<Pokemon>
)

data class Pokemon (val name:String, val url:String)