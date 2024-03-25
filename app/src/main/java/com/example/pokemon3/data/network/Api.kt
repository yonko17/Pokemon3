package com.example.pokemon3.data.network

import com.example.pokemon3.data.models.PokemonDetailsModel
import com.example.pokemon3.data.models.PokemonResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

object Api {

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())

    interface RemoteService{
        @GET("pokemon?limit=151")
        fun loadPokemon(@Query("limit") limit:Int) : Call<PokemonResponse>
        //La agregue para obtener el Id del pokemon
        @GET
        fun getPokemonInfo(@Url url: String): Call<PokemonDetailsModel>
    }

    // 1.- Agregar funcion que va a consumir la nueva URL

//    fun buildtwo(url: String) : RemoteService {
//        val retrofit = builder.baseUrl(url).build()
//        return retrofit.create(RemoteService::class.java)
//    }
    fun build() : RemoteService {
        return builder.build().create(RemoteService::class.java)
    }
}