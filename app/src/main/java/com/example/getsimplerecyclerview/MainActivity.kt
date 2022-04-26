package com.example.getsimplerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.getsimplerecyclerview.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var queue: RequestQueue
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemones: Array<JSONObject>

        binding.btnUpdatePokemon.setOnClickListener{
            var num = binding.etPokemonAmount.text

            if(num.isEmpty()){
                Toast.makeText(this, "Ingrese primero un numero", Toast.LENGTH_LONG).show()
            } else {
                queue = Volley.newRequestQueue(this)
                getPokemonList(Integer.parseInt(num.toString()))
            }
        }
    }

    fun getPokemonList(ListAmount: Int){
        val url = "https://pokeapi.co/api/v2/pokemon/?limit=${ListAmount}"

        //val pokemones: Array<JSONObject>

        val jsonRequest = JsonObjectRequest(url, Response.Listener<JSONObject>{ response ->
            //TODO
            var pokemones:JSONArray = response.getJSONArray("results")


            binding.rvPokemonEntries.adapter = MainAdapter(pokemones)
        },
            Response.ErrorListener { error ->
                //TODO
                Toast.makeText(this, "no haya ningun pokemon", Toast.LENGTH_LONG)
            }
        )
        queue.add(jsonRequest)


    }

    override fun onStop(){
        super.onStop()

        queue.cancelAll("Stopped")
    }


}