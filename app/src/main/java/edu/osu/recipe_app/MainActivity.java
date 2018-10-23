package edu.osu.recipe_app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import edu.osu.recipe_app.ui.Favorites.FavoritesActivity;
import edu.osu.recipe_app.ui.FindStore.FindStoreActivity;
import edu.osu.recipe_app.ui.MyAccount.AccountLoginActivity;
import edu.osu.recipe_app.ui.MyRecipes.MyRecipeActivity;
import edu.osu.recipe_app.ui.Orientation.FragmentLandscape;
import edu.osu.recipe_app.ui.Orientation.FragmentPortrait;
import edu.osu.recipe_app.ui.Timer.TimerActivity;
import edu.osu.recipe_app.ui.TodaysPick.TodaysPickActivity;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class MainActivity extends AppCompatActivity {
    private final String s = "MainActivity";
    private String mCurrentUser = "User";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.s, "Activity Created");

        Bundle extras = getIntent().getExtras();
        if(extras != null){
           mCurrentUser =  extras.getString("CurrentUser");
        }

        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Configuration configInfo = this.getResources().getConfiguration();

        Bundle args = new Bundle();
        if (configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentLandscape fragmentLandscape = new FragmentLandscape();
            args.putString("CurrentUser", mCurrentUser);
            fragmentLandscape.setArguments(args);
            fragmentTransaction.replace(android.R.id.content, (Fragment)fragmentLandscape);
        } else {
            FragmentPortrait fragmentPortrait = new FragmentPortrait();
            args.putString("CurrentUser", mCurrentUser);
            fragmentPortrait.setArguments(args);
            fragmentTransaction.replace(android.R.id.content, (Fragment)fragmentPortrait);
        }
        fragmentTransaction.commit();

    }


    protected void onStart() {
        super.onStart();
        Log.d(this.s, "Activity Started");
    }

    protected void onResume() {
        super.onResume();
        Log.d(this.s, "Activity Resumed");
    }

    protected void onPause() {
        super.onPause();
        Log.d(this.s, "Activity Paused");
    }

    protected void onStop() {
        super.onStop();
        Log.d(this.s, "Activity Stopped");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(this.s, "Activity Destroyed");
    }
}

