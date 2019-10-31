package com.example.insanelywrongmechanics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.list_item.view.*

class MainAdapter(var arrayGames: ArrayList<Game>): RecyclerView.Adapter<MainViewHolder>() {

    override fun getItemCount(): Int = arrayGames.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.list_item, parent, false)
        return MainViewHolder(cell)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val game = arrayGames.get(position)
        holder.view.itemTitle.text = game.name
        Picasso.get().load(game.img).into(holder.view.itemImage)
    }

    //Notify recycler view when view change
}