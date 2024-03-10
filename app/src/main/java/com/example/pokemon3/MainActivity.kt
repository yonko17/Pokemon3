package com.example.pokemon3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemon3.adapter.PokemonAdapter
import com.example.pokemon3.data.Api
import com.example.pokemon3.data.models.PokemonResponse
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

        init() { result ->
            binding.rvPokemon.adapter = PokemonAdapter(result?.results ?: emptyList()){
                it.url
                Log.e("URL", it.url)
            }
        }

    }

    private fun init(result: (PokemonResponse?) -> Unit) {

//      val tvResult : TextView = findViewById(R.id.tv_Result)
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


        }

        )
    }

}