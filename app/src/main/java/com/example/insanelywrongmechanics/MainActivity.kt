package com.example.insanelywrongmechanics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.insanelywrongmechanics.gameslist.GamesListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Fonction de rafraîchissement
        swipeContainer.setOnRefreshListener {
            refreshGames()
        }
    }

    fun refreshGames() {

        //On détache le fragment puis on le rattache à nouveau
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val gameList = GamesListFragment()
        transaction.add(R.id.swipeContainer, gameList)
        transaction.commit()

        //Arrêt du chargement lorsque le fragment est chargé
        swipeContainer.isRefreshing = false
    }
}
