package com.example.marvelapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelapp.R
import com.example.marvelapp.databinding.CardviewCharacterBinding
import com.example.marvelapp.entity.Character

interface CharacterListener {
    fun setOnClickListener(characterId: String)
}

class CharacterAdapter(
    private val characters: List<Character>,
    private val listener: CharacterListener
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_character, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position], listener)
    }

    override fun getItemCount(): Int = characters.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardviewCharacterBinding.bind(itemView)
        fun bind(character: Character, listener: CharacterListener) {
            binding.apply {
                this.cardviewName.text = character.name
                Glide
                    .with(itemView.context)
                    .load(character.img)
                    .into(this.cardviewImage)
                binding.cardview.setOnClickListener {
                    listener.setOnClickListener(character.id)
                }
            }
        }
    }
}
