package edu.osu.recipe_app.ui.TodaysPick;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import edu.osu.recipe_app.R;

public class TodaysPickActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todays_pick_activity);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.TodaysPickContainer);

        if(fragment == null){
            fragment = new TodaysPickFragment();
            fm.beginTransaction()
                    .add(R.id.TodaysPickContainer, fragment)
                    .commit();

        }
    }
}
