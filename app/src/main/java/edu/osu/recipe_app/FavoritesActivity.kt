package edu.osu.recipe_app

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import edu.osu.recipe_app.ui.Favorites.FavoritesFragment

class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "app-database").allowMainThreadQueries().build()

        setContentView(R.layout.favorites_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, FavoritesFragment.newInstance())
                    .commitNow()
        }
    }

}
