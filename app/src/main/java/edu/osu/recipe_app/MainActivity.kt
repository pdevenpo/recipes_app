package edu.osu.recipe_app

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View



class MainActivity : AppCompatActivity() {

    private val s = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        Log.d(s, "Activity Created")

        val fragmentManager = getFragmentManager()
        val fragmentTransaction = fragmentManager.beginTransaction()
        val configInfo = getResources().getConfiguration()
        if (configInfo.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            val fragmentLandscape = FragmentLandscape()
            fragmentTransaction.replace(android.R.id.content, fragmentLandscape)
        } else {
            val fragmentPortrait = FragmentPortrait()
            fragmentTransaction.replace(android.R.id.content, fragmentPortrait)
        }

        fragmentTransaction.commit()
    }

    fun onClick_Button(v: View) {
        when (v.id) {

            R.id.MyRecipesButton -> {
                val myRecipesIntent = Intent(this, MyRecipeActivity::class.java)
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
                val myAccountIntent = Intent(this, AccountActivity::class.java)
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

    override fun onStart() {
        super.onStart()
        Log.d(s, "Activity Started")
    }

    override fun onResume() {
        super.onResume()
        Log.d(s, "Activity Resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d(s, "Activity Paused")
    }

    override fun onStop() {
        super.onStop()
        Log.d(s, "Activity Stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(s, "Activity Destroyed")
    }
}
