package edu.osu.recipe_app.ui.MyRecipes.ViewRecipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.Recipe;

public class ViewRecipeActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recipe_activity);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.ViewRecipeContainer);

        int mRecipeListNumber = 0;
        //pass integer of which recipe was passed
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            mRecipeListNumber = extras.getInt("Recipe");
        }

        if(fragment == null){
            fragment = new ViewRecipeFragment();
            Bundle args = new Bundle();
            args.putInt("Recipe", mRecipeListNumber);
            fragment.setArguments(args);
            fm.beginTransaction()
                    .add(R.id.ViewRecipeContainer, fragment)
                    .commit();
        }
    }

}
