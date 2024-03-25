package com.example.pokemon3.data.models

import com.google.gson.annotations.SerializedName

data class PokemonDetailsModel(
    @SerializedName("abilities")    val abilities: List<Ability>,
    @SerializedName ("experience")  val base_experience: Int,
    @SerializedName ("cries")       val cries: Cries,
    @SerializedName ("forms")       val forms: List<Form>,
    @SerializedName ("indices")     val game_indices: List<GameIndice>,
    @SerializedName ("height")      val height: Int,
    @SerializedName ("items")       val held_items: List<Any>,
    @SerializedName ("id")          val id: Int,
    @SerializedName ("default")     val is_default: Boolean,
    @SerializedName ("location")    val location_area_encounters: String,
    @SerializedName ("moves")       val moves: List<Move>,
    @SerializedName ("name")        val name: String,
    @SerializedName ("order")       val order: Int,
    @SerializedName ("pastAbilities")val past_abilities: List<Any>,
    @SerializedName ("pastTypes")   val past_types: List<Any>,
    @SerializedName ("species")     val species: Species,
    @SerializedName ("sprites")     val sprites: Sprites,
    @SerializedName ("stats")       val stats: List<Stat>,
    @SerializedName ("types")       val types: List<Types>,
    @SerializedName ("weight")      val weight: Int
)
