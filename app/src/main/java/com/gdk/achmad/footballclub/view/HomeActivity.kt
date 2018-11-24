package com.gdk.achmad.footballclub.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gdk.achmad.footballclub.R
import com.gdk.achmad.footballclub.R.id.favorites
import com.gdk.achmad.footballclub.R.id.teams
import com.gdk.achmad.footballclub.R.layout.activity_home
import com.gdk.achmad.footballclub.view.fragment.FavoriteTeamsFragment
import com.gdk.achmad.footballclub.view.fragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId){
                teams -> {
                    loadTeamsFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true

        }
        bottom_navigation.selectedItemId = teams
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteTeamsFragment(), FavoriteTeamsFragment::class.java.simpleName)
                    .commit()
        }
    }
}
