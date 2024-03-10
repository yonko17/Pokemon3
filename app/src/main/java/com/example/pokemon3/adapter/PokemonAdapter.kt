package com.example.pokemon3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon3.data.models.Pokemon
import com.example.pokemon3.databinding.ItemPokemonBinding

class PokemonAdapter(private val pokemonList: List<Pokemon>, private val itemCallback: (pokemon: Pokemon) -> Unit) : RecyclerView.Adapter<PokemonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context))
        return PokemonViewHolder(binding,itemCallback)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = pokemonList[position]
        holder.render(item)
    }
}

//class PokemonViewHolder(view: View):RecyclerView.ViewHolder(view)
class PokemonViewHolder(private val binding: ItemPokemonBinding, private val itemCallback: (pokemon: Pokemon) -> Unit) : RecyclerView.ViewHolder(binding.root)
{
    fun render(pokemonModel: Pokemon){
        binding.textView.text = pokemonModel.name
        binding.textView.setOnClickListener {itemCallback(pokemonModel)}
    }
}
