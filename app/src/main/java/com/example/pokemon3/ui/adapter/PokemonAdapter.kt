package com.example.pokemon3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon3.data.models.Pokemon
import com.example.pokemon3.databinding.ItemPokemonBinding

class PokemonListAdapter(private val itemCallback: (pokemon: Pokemon) -> Unit) : ListAdapter<Pokemon, PokemonViewHolder>(
    PokemonDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding,itemCallback)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = getItem(position)
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
class PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.name  == newItem.name
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }
}
