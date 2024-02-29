package com.example.pokemon3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon3.R
import com.example.pokemon3.data.Pokemon

class PokemonAdapter(private val pokemonList:List<Pokemon>) : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.item_pokemon, parent, false))
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = pokemonList[position]
        holder.render(item)
    }
}

class PokemonViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val pokemon = view.findViewById<TextView>(R.id.textView)
    fun render(pokemonModel: Pokemon){
        pokemon.text = pokemonModel.name
    }
}