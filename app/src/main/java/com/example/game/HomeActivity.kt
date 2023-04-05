package com.example.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.search.SearchBar

open class HomeActivity : AppCompatActivity() {

    companion object{
      var gameToShowDetails: Game? =  null
    }
    private lateinit var searchText : TextView
    private lateinit var home_button : Button
    private lateinit var details_button : Button
    private lateinit var allGames: RecyclerView
    private lateinit var gamesAdapter: GameListAdapter
    private lateinit var searchBar : SearchBar
    private var gamesList = GameData.getAll()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        allGames = findViewById(R.id.game_list)
        allGames.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        gamesAdapter = GameListAdapter(arrayListOf()) { game -> showGameDetails(game) }
        allGames.adapter = gamesAdapter
        gamesAdapter.updateGames(gamesList)

        home_button = findViewById(R.id.home_button)
        home_button.setEnabled(false)
        details_button = findViewById(R.id.details_button)

        if(gameToShowDetails!=null){
            details_button.setEnabled(true)
        details_button.setOnClickListener(){

            showGameDetails(gameToShowDetails!!)
        }
        }else details_button.setEnabled(false)


        if(intent?.action == Intent.ACTION_SEND && intent?.type == "text/plain")
            handleSendText(intent)

    }

    private fun showGameDetails(game: Game) {
        val intent = Intent(this, GameDetailsActivity::class.java).apply {
            putExtra("game_title", game.title)
        }
        startActivity(intent)
    }
    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            searchText.setText(it)
        }
    }


}