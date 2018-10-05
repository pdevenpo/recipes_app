package edu.osu.recipe_app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import edu.osu.recipe_app.ui.Favorites.FavoritesFragment
import edu.osu.recipe_app.ui.Timer.TimerFragment

class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorites_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, FavoritesFragment.newInstance())
                    .commitNow()
        }
    }

}
