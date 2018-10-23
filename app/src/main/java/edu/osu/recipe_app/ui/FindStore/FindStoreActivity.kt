package edu.osu.recipe_app.ui.FindStore

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import edu.osu.recipe_app.R

class FindStoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.find_store_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, FindStoreFragment.newInstance())
                    .commitNow()
        }
    }

}
