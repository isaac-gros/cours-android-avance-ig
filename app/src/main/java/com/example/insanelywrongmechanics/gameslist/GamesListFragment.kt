package com.example.insanelywrongmechanics.gameslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

import com.example.insanelywrongmechanics.R
import kotlinx.android.synthetic.main.fragment_games_list.*
import org.json.JSONObject

class GamesListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialisation de la liste
        gamesList.layoutManager = LinearLayoutManager(activity)
        gamesList.adapter = MainAdapter(fetchGames())
    }

    private fun fetchGames(): ArrayList<Game> {

        // Tableau vide des jeux
        var listOfGames: ArrayList<Game> = ArrayList()

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(activity)
        val url = "https://my-json-server.typicode.com/bgdom/cours-android/games"

        // Request a string response from the provided URL.
        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->

                for(i in 0 until response.length()) {
                    val singleItem = response.get(i).toString()
                    val singleObject = JSONObject(singleItem)

                    val gameObject = Game(
                        singleObject.getInt("id"),
                        singleObject.getString("name"),
                        singleObject.getString("description"),
                        singleObject.getString("link"),
                        singleObject.getString("img")
                    )

                    listOfGames.add(gameObject)
                    gamesList.adapter?.notifyDataSetChanged()
                }

                showStatus("Jeux récupérés avec succès.")

            },
            Response.ErrorListener { error ->
                listOfGames.add(
                    Game(
                        0,
                        "Aucun jeu trouvé",
                        "",
                        "",
                        ""
                    )
                )

                showStatus("Erreur lors de la récupération des jeux...")
            }
        )
        queue.add(request)

        return listOfGames
    }

    private fun showStatus(Message: String): Unit {
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(activity, Message, duration)
        toast.show()
    }
}
