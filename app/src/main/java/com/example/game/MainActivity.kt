package com.example.game

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.ContentView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import org.hamcrest.comparator.ComparatorMatcherBuilder


class MainActivity : AppCompatActivity(),DataListener {

    private lateinit var menu1: MenuItem
    private lateinit var menu2: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){

            if(HomeFragment.gameToShowDetails == null)
             HomeFragment.gameToShowDetails = GameData.getAll()[0]
            navController.popBackStack()

        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {

            val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
            navView.setupWithNavController(navController)
            menu1 = navView.menu.findItem(R.id.homeItem)
            menu2 = navView.menu.findItem(R.id.gameDetailsItem)
            menu2.isEnabled = false
            menu1.isEnabled = false

            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.homeItem) {
                    menu1.isEnabled = false
                    if (HomeFragment.gameToShowDetails != null)
                        menu2.isEnabled = true
                } else if (destination.id == R.id.gameDetailsItem) {
                    menu1.isEnabled = true
                    menu2.isEnabled = false

                    menu1.setOnMenuItemClickListener {
                        navHostFragment.findNavController().navigate(R.id.homeItem)
                        true
                    }

                }
            }

        }
    }

    override fun refreshDetails(item: Game) {
        val details = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_land) as NavHostFragment
        val destination = GameDetailsFragmentDirections.test()
        details.navController.navigate(destination)
    }

    override fun showDetails() {
        val details = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val destination = HomeFragmentDirections.homeToDetails()
        details.navController.navigate(destination)
    }

}
