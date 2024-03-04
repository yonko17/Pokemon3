package com.example.pokemon3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon3.R
import com.example.pokemon3.data.Pokemon
import com.example.pokemon3.MainActivity
import com.example.pokemon3.databinding.ItemPokemonBinding

class PokemonAdapter(private val pokemonList:List<Pokemon>) : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context))
        return PokemonViewHolder(binding)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = pokemonList[position]
        holder.render(item)
    }
}

//class PokemonViewHolder(view: View):RecyclerView.ViewHolder(view)
class PokemonViewHolder(private val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root)
{


    val pokemon = binding.textView
    fun render(pokemonModel: Pokemon){
        pokemon.text = pokemonModel.name
    }
}
