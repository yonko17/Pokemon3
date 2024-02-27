package com.example.pokemon3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon3.data.Api
import com.example.pokemon3.data.PokemonResponse
import com.example.pokemon3.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

    private fun init(){

//      val tvResult : TextView = findViewById(R.id.tv_Result)
        val request = Api.build().loadPokemon(151)
        request.enqueue(object : Callback<PokemonResponse>{
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                val pokemonResponse = response.body()

                pokemonResponse?.results?.let {
                    it.forEach{
                        binding.tvResult.append("\n${it.name}")
                    }
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                println(t.message)
            }


        }

        )
    }

}