package com.example.pokemon3.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.pokemon3.R
import com.example.pokemon3.adapter.PokemonListAdapter
import com.example.pokemon3.data.network.Api
import com.example.pokemon3.data.models.PokemonResponse
import com.example.pokemon3.ui.viewmodel.PokemonViewModel
import com.example.pokemon3.databinding.FragmentPokemonBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonFragment : Fragment() {

    private lateinit var binding: FragmentPokemonBinding

    private val pokemonViewModel : PokemonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init { result ->
            val adapter = PokemonListAdapter { pokemon ->
                Log.e("URL", pokemon.url)
                pokemonViewModel.randomPokemon(pokemon)
                findNavController().navigate(R.id.action_pokemonFragment_to_detailPokemonFragment2)
            }
            binding.etFilter.addTextChangedListener { userFilter ->
                val filteredList = result?.results?.filter { pokemon -> pokemon.name.contains(userFilter.toString(),ignoreCase = true) }
                adapter.submitList(filteredList)
                }
            adapter.submitList(result?.results)
            binding.rvPokemon.adapter = adapter
            result?.results?.let { pokemonList ->
                (binding.rvPokemon.adapter as? PokemonListAdapter)?.submitList(pokemonList)
            }
            pokemonViewModel.isLoading.observe(viewLifecycleOwner, Observer {
                binding.progressCircular.isVisible = it
            })
        }
    }

    private fun init(result: (PokemonResponse?) -> Unit) {
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