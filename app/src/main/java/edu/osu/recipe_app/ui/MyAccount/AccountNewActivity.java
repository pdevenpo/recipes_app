package edu.osu.recipe_app.ui.MyAccount;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import edu.osu.recipe_app.R;

public class AccountNewActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account_new_activity);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.AccountContainer);

        if(fragment == null){
            fragment = new AccountNewFragment();
            fm.beginTransaction()
                    .add(R.id.AccountContainer, fragment)
                    .commit();
        //changes to test
        }
    }
}
