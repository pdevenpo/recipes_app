package edu.osu.recipe_app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import edu.osu.recipe_app.ui.Favorites.FavoritesFragment

class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userRepo = UserRepository(applicationContext)

        val list = userRepo.listUsers()

        for(item in list){
            Log.d("test", item.name)
        }

        setContentView(R.layout.favorites_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, FavoritesFragment.newInstance())
                    .commitNow()
        }
    }

    fun onClick_Button(v: View) {
        val userRepo = UserRepository(applicationContext)
        val user = userRepo.getUser("john")

        userRepo.deleteUser(user)
    }
}
