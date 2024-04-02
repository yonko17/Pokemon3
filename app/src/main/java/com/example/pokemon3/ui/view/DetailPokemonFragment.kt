package com.example.pokemon3.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.pokemon3.data.network.Api
import com.example.pokemon3.data.models.PokemonDetailsModel
import com.example.pokemon3.domain.viewmodel.PokemonViewModel
import com.example.pokemon3.databinding.CharacteristicsPokemonBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPokemonFragment : Fragment() {
    private lateinit var binding: CharacteristicsPokemonBinding

    private val pokemonViewModel: PokemonViewModel by activityViewModels()

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

        pokemonViewModel.getDetailPokemon(pokemonViewModel.getObjetPokemon()?.url.orEmpty()) { detail ->
            binding.tvHeight.text = "Altura: ${detail?.height.toString()} m."
            binding.tvWeight.text = "Peso: ${detail?.weight} kg."
            binding.tvTypes.text = "Tipo: ${detail?.types?.first()?.type?.name}."
            Glide.with(this).load(detail?.sprites?.front_default).into(binding.ivPickPokemon)
            // por agregar sonido pokemon
//            binding.vvPokemonSound.playSoundEffect(detail?.cries.latest)

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
}