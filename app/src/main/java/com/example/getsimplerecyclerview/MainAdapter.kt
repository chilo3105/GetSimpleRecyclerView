package com.example.getsimplerecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.getsimplerecyclerview.databinding.ItemPokemonBinding
import org.json.JSONArray
import org.json.JSONObject

class MainAdapter(private val pokemones: JSONArray): RecyclerView.Adapter<MainAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainHolder, position: Int) {
        holder.render(pokemones.getJSONObject(position), position)
    }

    override fun getItemCount(): Int {
        return pokemones.length()
    }

    class MainHolder(val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root) {
        fun render(pokemon: JSONObject, position: Int){
            binding.tvPokemon.setText(""+ (position + 1) +".- "+ pokemon.getString("name"))
        }
    }
}