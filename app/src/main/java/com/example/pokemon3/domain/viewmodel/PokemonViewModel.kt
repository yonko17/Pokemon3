package com.example.pokemon3.domain.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon3.data.models.Pokemon
import com.example.pokemon3.data.models.PokemonDetailsModel
import com.example.pokemon3.data.models.PokemonResponse
import com.example.pokemon3.data.network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel : ViewModel(){
    val vmModel = MutableLiveData<Pokemon>()
    val isLoading = MutableLiveData<Boolean>()


    fun randomPokemon(pokemon: Pokemon){
        vmModel.value = pokemon
    }

    fun getObjetPokemon() = vmModel.value

    fun getDetailPokemon(
        url: String,
        result: (PokemonDetailsModel?) -> Unit
    ) {//3.- reemplazar pokemon response por el nombre de la nueva clase y reemplazar la url que va a consumir
        val request = Api.build()
            .getPokemonInfo(url)//2.- Mandar llamar la nueva funcion que voy a crear en la clase API
        request.enqueue(object : Callback<PokemonDetailsModel> {
            override fun onResponse(
                call: Call<PokemonDetailsModel>,
                response: Response<PokemonDetailsModel>
            ) {
                val pokemonDetail = response.body()
                result.invoke(pokemonDetail)
            }

            override fun onFailure(call: Call<PokemonDetailsModel>, t: Throwable) {
                Log.e("LOG", t.message ?: "Error")
            }
        })
    }

     // Pasar de Pokemon Fragment a una funcion lo de init
     fun loadPokemon(result: (PokemonResponse?) -> Unit){
         val request = Api.build().loadPokemon(151)
         request.enqueue(object : Callback<PokemonResponse> {
             override fun onResponse(
                 call: Call<PokemonResponse>,
                 response: Response<PokemonResponse>
             ) {
                 val pokemonResponse = response.body()
                 result.invoke(pokemonResponse)
             }

             override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                 println(t.message)
             }
         })
     }


}