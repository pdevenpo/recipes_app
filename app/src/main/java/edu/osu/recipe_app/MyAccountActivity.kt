package edu.osu.recipe_app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import edu.osu.recipe_app.ui.MyAccount.MyAccountFragment

class MyAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userRepo = UserRepository(applicationContext)

        val email = "test1@gmail.com"
        val name = "steve"
        val password = "password"

        userRepo.insertUser(email, password, name)

        setContentView(R.layout.my_account_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MyAccountFragment.newInstance())
                    .commitNow()
        }
    }
}
