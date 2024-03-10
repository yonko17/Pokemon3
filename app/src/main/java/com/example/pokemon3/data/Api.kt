package com.example.pokemon3.data

import com.example.pokemon3.data.models.PokemonResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object Api {

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())

    interface RemoteService{
        @GET("pokemon?limit=151")
        fun loadPokemon(@Query("limit") limit:Int) : Call<PokemonResponse>
    }

    fun build() : RemoteService{
        return builder.build().create(RemoteService::class.java)
    }
}