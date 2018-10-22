package edu.osu.recipe_app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import edu.osu.recipe_app.ui.MyAccount.MyAccountFragment

class MyAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.my_account_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MyAccountFragment.newInstance())
                    .commitNow()
        }
    }
}
