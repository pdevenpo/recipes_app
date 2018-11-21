package edu.osu.recipe_app.ui.MyAccount;

import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import edu.osu.recipe_app.R;

public class AccountLoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        android.app.FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Configuration configInfo = this.getResources().getConfiguration();

        Bundle args = new Bundle();
        if (configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            AccountLoginFragmentLandscape fragmentLandscape = new AccountLoginFragmentLandscape();
            fragmentLandscape.setArguments(args);
            fragmentTransaction.replace(android.R.id.content, (android.app.Fragment) fragmentLandscape);
        } else {
            AccountLoginFragment fragmentPortrait = new AccountLoginFragment();
            fragmentPortrait.setArguments(args);
            fragmentTransaction.replace(android.R.id.content, (android.app.Fragment) fragmentPortrait);
        }
        fragmentTransaction.commit();


    }
}