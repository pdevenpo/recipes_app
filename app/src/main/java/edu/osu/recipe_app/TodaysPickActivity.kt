package edu.osu.recipe_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import edu.osu.recipe_app.ui.todayspick.TodaysPickFragment

class TodaysPickActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todays_pick_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TodaysPickFragment.newInstance())
                    .commitNow()
        }
    }

}
