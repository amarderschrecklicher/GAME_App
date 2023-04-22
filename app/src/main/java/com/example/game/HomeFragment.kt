package com.example.game

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.search.SearchBar

open class HomeFragment : Fragment() {

    companion object{
        var gameToShowDetails: Game? =  null
    }
    private lateinit var searchText : TextView
    private lateinit var allGames: RecyclerView
    private lateinit var gamesAdapter: GameListAdapter
    private var gamesList = GameData.getAll()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_main, container, false)
        allGames = view.findViewById(R.id.game_list)
        allGames.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        gamesAdapter = GameListAdapter(arrayListOf()) { game -> showGameDetails(game) }
        allGames.adapter=gamesAdapter
        gamesAdapter.updateGames(gamesList)


        return view;
    }


    private fun showGameDetails(game: Game) {
        gameToShowDetails = game
        val listener = context as DataListener

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            listener.showDetails()
         else listener.refreshDetails(game)
    }


    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            searchText.setText(it)
        }
    }

}