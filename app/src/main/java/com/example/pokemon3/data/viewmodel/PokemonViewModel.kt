package com.example.pokemon3.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon3.data.models.Pokemon

class PokemonViewModel : ViewModel(){
    val vmModel = MutableLiveData<Pokemon>()


    fun randomPokemon(pokemon: Pokemon){
        vmModel.value = pokemon
    }

    fun getObjetPokemon() = vmModel.value
}