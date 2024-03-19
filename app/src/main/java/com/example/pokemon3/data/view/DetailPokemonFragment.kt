package com.example.pokemon3.data.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.pokemon3.data.Api
import com.example.pokemon3.data.models.PokemonDetailsModel
import com.example.pokemon3.data.viewmodel.PokemonViewModel
import com.example.pokemon3.databinding.CharacteristicsPokemonBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPokemonFragment : Fragment() {
    private lateinit var binding: CharacteristicsPokemonBinding

    private val pokemonViewModel: PokemonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacteristicsPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvName.text = pokemonViewModel.getObjetPokemon()?.name

        init(pokemonViewModel.getObjetPokemon()?.url.orEmpty()) { detail ->
                //5.- Pintar los datos que obtenga de la funcion init (lambda)
                Log.e("LOG", "$detail")
            }
//        pokemonViewModel.vmModel.observe(viewLifecycleOwner, Observer { pokemon ->
//            binding.tvName.text = pokemon.name
//
//            //4.- Mandar llamar la funcion init
//            init(pokemon.url) { detail ->
//                //5.- Pintar los datos que obtenga de la funcion init (lambda)
//                Log.e("LOG", "$detail")
//            }
//        })
    }

    private fun init(
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
}