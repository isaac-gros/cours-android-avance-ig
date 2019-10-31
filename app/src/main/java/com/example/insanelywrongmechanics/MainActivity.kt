package com.example.insanelywrongmechanics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation de la liste
        main_listItems.layoutManager = LinearLayoutManager(this)
        main_listItems.adapter = MainAdapter(fetchGames())
    }

    fun fetchGames(): ArrayList<Game> {

        // Tableau vide des jeux
        var listOfGames: ArrayList<Game> = ArrayList()

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://my-json-server.typicode.com/bgdom/cours-android/games"

        // Request a string response from the provided URL.
        val request = JsonArrayRequest(Request.Method.GET, url, null,
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
                    main_listItems.adapter?.notifyDataSetChanged()
                }

            },
            Response.ErrorListener { error ->
                listOfGames.add(Game(
                    0,
                    "Aucun jeu trouvé",
                    "",
                    "",
                    ""
                ))
                println("Erreur lors de la récupération des jeux. Vérifier la requête")
            }
        )
        queue.add(request)

        return listOfGames
    }
}
