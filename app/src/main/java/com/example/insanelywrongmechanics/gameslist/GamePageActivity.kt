package com.example.insanelywrongmechanics.gameslist

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.insanelywrongmechanics.R
import com.example.insanelywrongmechanics.webview.WebviewActivity
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_game_page.*

class GamePageActivity: AppCompatActivity() {

    //SharedPreferences
    var SHARED_PREFERENCES: String = "sharedPrefs"
    var WEBVIEW_PREFERENCE: String = "useWebView"

    //État de la webview
    var useWebView: Boolean = true

    //Lien pour la webview
    companion object {
        var LINK = "Lien"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        //Définition de la vue
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)

        val gameTitleStr = intent.getStringExtra(MainViewHolder.GAME_NAME)
        val gameImageStr = intent.getStringExtra(MainViewHolder.GAME_IMAGE)
        val gameDescriptionStr = intent.getStringExtra(MainViewHolder.GAME_DESCRIPTION)
        val gameLinkStr = intent.getStringExtra(MainViewHolder.GAME_LINK)

        //Titre de navigation
        supportActionBar?.title = gameTitleStr

        //Chargement de la vue
        Picasso.get().load(gameImageStr).into(gameImage)
        gameImage.contentDescription = gameTitleStr
        gameTitle.text = gameTitleStr
        gameDescription.text = gameDescriptionStr

        gameOpenLink.setOnClickListener {

            if (useWebView()) {
                val nextIntent = Intent(this, WebviewActivity::class.java)

                //Envoi du lien en extra pour la prochaine activité
                nextIntent.putExtra(LINK, gameLinkStr)

                //Mise à jour de l'utilisation de la webview avant le commencement de l'activité
                updateWebViewUsage()

                this.startActivity(nextIntent)

            } else {
                //Mise à jour de l'utilisation de la webview
                updateWebViewUsage()

                //Ouverture du lien
                val loadGame = Intent(android.content.Intent.ACTION_VIEW, Uri.parse(gameLinkStr))
                startActivity(loadGame)
            }
        }

    }

    private fun useWebView(): Boolean {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        useWebView = sharedPreferences.getBoolean(WEBVIEW_PREFERENCE, false)
        return useWebView
    }

    private fun updateWebViewUsage() {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(WEBVIEW_PREFERENCE, !useWebView)
        editor.apply()
    }


}