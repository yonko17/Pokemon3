package com.example.pokemon3.data.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pokemon3.R
import com.example.pokemon3.adapter.PokemonAdapter
import com.example.pokemon3.data.Api
import com.example.pokemon3.data.models.PokemonResponse
import com.example.pokemon3.data.viewmodel.PokemonViewModel
import com.example.pokemon3.databinding.FragmentPokemonBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonFragment : Fragment() {

    private lateinit var binding: FragmentPokemonBinding

    private val pokemonViewModel : PokemonViewModel by viewModels()

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
            binding.rvPokemon.adapter = PokemonAdapter(result?.results ?: emptyList()) {
                it.url
                Log.e("URL", it.url)

                pokemonViewModel.randomPokemon(it)
                findNavController().navigate(R.id.action_pokemonFragment_to_detailPokemonFragment2)
            }
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