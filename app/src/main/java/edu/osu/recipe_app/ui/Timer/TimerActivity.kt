package edu.osu.recipe_app.ui.Timer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import edu.osu.recipe_app.R

class TimerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TimerFragment.newInstance())
                    .commitNow()
        }
    }

}
