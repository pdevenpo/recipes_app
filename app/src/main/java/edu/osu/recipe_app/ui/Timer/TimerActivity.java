package edu.osu.recipe_app.ui.Timer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.Timer.TimerFragment;
import edu.osu.recipe_app.ui.Timer.TimerFragmentLandscape;

public class TimerActivity extends AppCompatActivity {

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.timer_activity);
//
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.TimerContainer);
//
//        if(fragment == null){
//            fragment = new TimerFragment();
//
//            fm.beginTransaction()
//                    .add(R.id.TimerContainer, fragment)
//                    .commit();
//
//        }
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.app.FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Configuration configInfo = this.getResources().getConfiguration();

        Bundle args = new Bundle();
        if (configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            TimerFragmentLandscape fragmentLandscape = new TimerFragmentLandscape();
            fragmentLandscape.setArguments(args);
            fragmentTransaction.replace(android.R.id.content, (Fragment)fragmentLandscape);
        } else {
            TimerFragment fragmentPortrait = new TimerFragment();
            fragmentPortrait.setArguments(args);
            fragmentTransaction.replace(android.R.id.content, (Fragment)fragmentPortrait);
        }
        fragmentTransaction.commit();
    }
}
