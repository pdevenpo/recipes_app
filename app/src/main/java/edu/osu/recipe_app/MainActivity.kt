package edu.osu.recipe_app

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick_Button(v: View) {
        when (v.id) {

            R.id.MyRecipesButton -> {
                val myRecipesIntent = Intent(this, MyRecipesActivity::class.java)
                startActivity(myRecipesIntent)
            }

            R.id.FindStoreButton -> {
                val findStoreIntent = Intent(this, FindStoreActivity::class.java)
                startActivity(findStoreIntent)
            }

            R.id.TimerButton -> {
                val timerIntent = Intent(this, TimerActivity::class.java)
                startActivity(timerIntent)
            }

            R.id.TodaysPickButton -> {
                val todaysPickIntent = Intent(this, TodaysPickActivity::class.java)
                startActivity(todaysPickIntent)
            }

            R.id.MyAccountButton -> {
                val myAccountIntent = Intent(this, MyAccountActivity::class.java)
                startActivity(myAccountIntent)
            }

            R.id.FavoritesButton -> {
                val favoritesIntent = Intent(this, FavoritesActivity::class.java)
                startActivity(favoritesIntent)
            }

            else -> {
            }
        }
    }
}
