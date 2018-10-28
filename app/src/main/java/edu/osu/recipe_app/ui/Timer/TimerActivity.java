package edu.osu.recipe_app.ui.Timer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import edu.osu.recipe_app.R;

public class TimerActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_activity);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.TimerContainer);

        if(fragment == null){
            fragment = new TimerFragment();
            fm.beginTransaction()
                    .add(R.id.TimerContainer, fragment)
                    .commit();

        }
    }
}
