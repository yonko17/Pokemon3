package com.example.pokemon3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokemon3.databinding.DetailPokemonBinding
import com.example.pokemon3.databinding.FragmentPokemonBinding

class DetailPokemonFragment  : Fragment() {
    private lateinit var binding: DetailPokemonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding= DetailPokemonBinding.inflate(layoutInflater)
        return binding.root
    }
}