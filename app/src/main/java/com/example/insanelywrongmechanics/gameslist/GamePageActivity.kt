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

        //Récupération des datas passées en extra
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

        //Mise à jour du texte du bouton pour ouvrir le lien
        updateButtonText()

        //Détection de la prochaine activité au clic sur le navigateur
        gameOpenLink.setOnClickListener {
            if (useWebView()) {

                //Pour passer à l'état inverse
                updateWebViewUsage()

                //On passe le lien en paramètre pour l'ouvrir dans la webview
                val nextIntent = Intent(this, WebviewActivity::class.java)
                nextIntent.putExtra(LINK, gameLinkStr)
                this.startActivity(nextIntent)

            } else {
                updateWebViewUsage()
                val loadGame = Intent(android.content.Intent.ACTION_VIEW, Uri.parse(gameLinkStr))
                startActivity(loadGame)
            }
        }

        //Bouton de partage
        gameShare.setOnClickListener {
            shareGame(gameTitleStr, gameLinkStr)
        }
    }

    override fun onResume() {
        super.onResume()
        updateButtonText()

    }

    //Texte du bouton
    private fun updateButtonText() {
        if(useWebView()) {
            gameOpenLink.text = getString(R.string.openInApp)
        } else {
            gameOpenLink.text = getString(R.string.openInBrowser)
        }
    }

    //Test si on utilise la webview ou le navigateur natif
    private fun useWebView(): Boolean {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        useWebView = sharedPreferences.getBoolean(WEBVIEW_PREFERENCE, false)
        return useWebView
    }

    //Mise à jour de l'utilisation de la webview
    private fun updateWebViewUsage() {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(WEBVIEW_PREFERENCE, !useWebView)
        editor.apply()
    }

    //Fonction de partage du jeu
    private fun shareGame(name: String?, link: String?) {

        //Contenus texte
        val title = "Connaîs-tu le jeu $name ?"
        val content = "Je trouve ce jeu super intéressant ! $link"

        //Création de l'intent et lancement
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"

        shareIntent.putExtra(Intent.EXTRA_TITLE, title.toString())
        shareIntent.putExtra(Intent.EXTRA_TEXT, content.toString())

        startActivity(Intent.createChooser(shareIntent, "Partager le jeu"))
    }

}