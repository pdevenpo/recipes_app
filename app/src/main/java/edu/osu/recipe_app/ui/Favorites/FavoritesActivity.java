package edu.osu.recipe_app.ui.Favorites;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.TodaysPick.TodaysPickFragment;

public class FavoritesActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_activity);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.FavoritesContainer);

        if(fragment == null){
            fragment = new FavoritesFragment();
            fm.beginTransaction()
                    .add(R.id.FavoritesContainer, fragment)
                    .commit();

        }
    }
}
