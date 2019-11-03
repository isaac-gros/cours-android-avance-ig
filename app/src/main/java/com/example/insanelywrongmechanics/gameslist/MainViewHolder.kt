package com.example.insanelywrongmechanics.gameslist

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class MainViewHolder(val view: View, var game: Game? = null): RecyclerView.ViewHolder(view) {

    // Les éléments qui constituent un élément de liste
    companion object {
        var GAME_NAME: String = "Titre du jeu"
        var GAME_IMAGE: String = "Lien de l'image"
        var GAME_DESCRIPTION: String = "Description du jeu"
        var GAME_LINK: String = "Lien du jeu"
    }

    init {
        view.button.setOnClickListener {
            val singleGameIntent = Intent(view.context, GamePageActivity::class.java)

            singleGameIntent.putExtra(GAME_NAME, game?.name)
            singleGameIntent.putExtra(GAME_IMAGE, game?.img)
            singleGameIntent.putExtra(GAME_DESCRIPTION, game?.description)
            singleGameIntent.putExtra(GAME_LINK, game?.link)

            view.context.startActivity(singleGameIntent)
        }
    }
}