package edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.MyRecipes.MyRecipesFragment;

public class PopulateDatabaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_recipes_fragment_container);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.MyRecipesFragmentContainer);

        if(fragment == null){
            fragment = new MyRecipesFragment();
            fm.beginTransaction()
                    .add(R.id.MyRecipesFragmentContainer, fragment)
                    .commit();
        }
    }

}