package edu.osu.recipe_app.ui.MyAccount;

import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import edu.osu.recipe_app.R;

public class AccountNewActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        android.app.FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Configuration configInfo = this.getResources().getConfiguration();

        Bundle args = new Bundle();
        if (configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            AccountNewFragmentLandscape fragmentLandscape = new AccountNewFragmentLandscape();
            fragmentLandscape.setArguments(args);
            fragmentTransaction.replace(android.R.id.content, (Fragment) fragmentLandscape);
        } else {
            AccountNewFragment fragmentPortrait = new AccountNewFragment();
            fragmentPortrait.setArguments(args);
            fragmentTransaction.replace(android.R.id.content, (Fragment) fragmentPortrait);
        }
        fragmentTransaction.commit();
    }
}
