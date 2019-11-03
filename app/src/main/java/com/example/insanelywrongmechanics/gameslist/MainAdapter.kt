package com.example.insanelywrongmechanics.gameslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.insanelywrongmechanics.R
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.list_item.view.*

class MainAdapter(var arrayGames: ArrayList<Game>): RecyclerView.Adapter<MainViewHolder>() {

    // Le nombre d'éléments dans la liste
    override fun getItemCount(): Int = arrayGames.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.list_item, parent, false)
        return MainViewHolder(cell)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        // Une instance de jeu dans la liste
        val game = arrayGames.get(position)
        holder.game = game

        // La vue du jeu dans la liste
        holder.view.itemTitle.text = game.name
        holder.view.itemImage.contentDescription = game.description
        Picasso.get().load(game.img).into(holder.view.itemImage)
    }
}