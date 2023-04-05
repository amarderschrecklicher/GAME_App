package com.example.game

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.game.HomeActivity.Companion.gameToShowDetails

class GameDetailsActivity : AppCompatActivity() {
    private lateinit var game : Game
    private lateinit var home_button : Button
    private lateinit var details_button : Button
    private lateinit var gameTitle : TextView
    private lateinit var gameRating : TextView
    private lateinit var gameGenre : TextView
    private lateinit var gamePlatform : TextView
    private lateinit var gameDate : TextView
    private lateinit var gameDeveloper : TextView
    private lateinit var gamePublisher : TextView
    private lateinit var gameDescription : TextView
    private lateinit var gameLogo : ImageView
    private lateinit var reviewsList : RecyclerView
    private lateinit var reviewsAdapter : CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)
        home_button = findViewById(R.id.home_button)
        home_button.setOnClickListener(){
            home()
        }
        details_button = findViewById(R.id.details_button)
        details_button.setEnabled(false)

        reviewsList = findViewById(R.id.recyclerView)
        reviewsList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        gameTitle = findViewById(R.id.game_title_textview)
        gameRating = findViewById(R.id.esrb_rating_textview)
        gameGenre = findViewById(R.id.genre_textview)
        gameDeveloper = findViewById(R.id.developer_textview)
        gameDate = findViewById(R.id.release_date)
        gamePlatform = findViewById(R.id.platform_textview)
        gameDescription = findViewById(R.id.description_textview)
        gamePublisher = findViewById(R.id.publisher_textview)
        gameLogo = findViewById(R.id.cover_imageview)

        val extras = intent.extras
        if (extras != null) {
            game = GameData().getDetails(extras.getString("game_title",""))!!
            populateDetails()
            reviewsAdapter = CommentsAdapter(arrayListOf())
            reviewsList.adapter = reviewsAdapter

            reviewsAdapter.updateImpression(GameData().getDetails(game.title).userImpressions.sortedByDescending { it.timestamp } )

            gameToShowDetails = game
        } else {
            finish()
        }

    }

    private fun populateDetails() {
        gameTitle.text=game.title
        gameDate.text=game.releaseDate
        gameGenre.text=game.genre
        gameRating.text=game.esrbRating
        gamePlatform.text = game.platform
        gameDeveloper.text = game.developer
        gamePublisher.text = game.publisher
        gameDescription.text = game.description
        val context: Context = gameLogo.context
        var id: Int = context.resources
            .getIdentifier(game.title.split(" ")[0].lowercase(), "drawable", context.packageName)
        if (id===0) id=context.resources
            .getIdentifier("picture1", "drawable", context.packageName)
        gameLogo.setImageResource(id)
    }

    private fun home() {
        val intent = Intent(this, HomeActivity::class.java).apply {
        }
        startActivity(intent)
    }
}