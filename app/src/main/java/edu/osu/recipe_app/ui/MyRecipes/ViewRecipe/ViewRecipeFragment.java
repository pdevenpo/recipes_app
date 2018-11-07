package edu.osu.recipe_app.ui.MyRecipes.ViewRecipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.osu.recipe_app.MainActivity;
import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.MyAccount.UserData.User;
import edu.osu.recipe_app.ui.MyAccount.UserData.UserRepository;
import edu.osu.recipe_app.ui.MyRecipes.MyRecipesActivity;
import edu.osu.recipe_app.ui.MyRecipes.MyRecipesFragment;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.Recipe;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.RecipeRepository;
import edu.osu.recipe_app.ui.MyRecipes.RecyclerView.MyAdapter;

public class ViewRecipeFragment extends Fragment {

    private TextView mRecipeTitle;
    private TextView mRecipeSource;
    //private TextView mRecipeTimerStartTxt;
    private TextView mTimerTime;
    private Button mPrepTime;
    private Button mWaitTime;
    private Button mCookTime;
    private TextView mCalories;
    private TextView mFat;
    private TextView mSatFat;
    private TextView mCarbs;
    private TextView mFiber;
    private TextView mSugar;
    private TextView mProtein;
    private TextView mInstructions;
    private TextView mIngredients;

    int mRecipePosition;
    private RecipeRepository mRecipeRepository;

    public ViewRecipeFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mRecipeRepository = new RecipeRepository(this.getContext());
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        super.onCreateView(inflater, parent, savedInstanceState);
        View v = inflater.inflate(R.layout.view_recipe_fragment, parent, false);

        int args = getArguments().getInt("Recipe");
        if(args >= 0){
            mRecipePosition = args;
        }

        mRecipeTitle = v.findViewById(R.id.recipeTitle);
        mRecipeSource = v.findViewById(R.id.recipeSource);
        mTimerTime = v.findViewById(R.id.timerTime);
        //buttons
        mPrepTime = v.findViewById(R.id.prepTime);
        mWaitTime = v.findViewById(R.id.waitTime);
        mCookTime = v.findViewById(R.id.cookTime);
        //
        mCalories = v.findViewById(R.id.calories);
        mFat = v.findViewById(R.id.fat);
        mSatFat = v.findViewById(R.id.satFat);
        mCarbs = v.findViewById(R.id.carbs);
        mFiber = v.findViewById(R.id.fiber);
        mSugar = v.findViewById(R.id.sugar);
        mProtein = v.findViewById(R.id.protein);
        mInstructions = v.findViewById(R.id.instructions);
        mIngredients = v.findViewById(R.id.ingredients);

        List<Recipe> recipeList = mRecipeRepository.listRecipes();
        Recipe recipe = recipeList.get(mRecipePosition);

        mRecipeTitle.setText(recipe.getRecipeName());
        mRecipeSource.setText("Source: " + recipe.getRecipeSource());
        mTimerTime.setText("Prep Time: " + recipe.getRecipePrepTime() + " min, " + "Wait Time: " + recipe.getRecipeWaitTime() + " min, " + "Cook Time: " + recipe.getRecipeCookTime() + " min" );
        mCalories.setText("Calories: " + recipe.getRecipeCalories());
        mFat.setText("Fat: " +recipe.getRecipeFat());
        mSatFat.setText("Saturated Fat: " +recipe.getRecipeSatFat());
        mCarbs.setText("Carbs: " +recipe.getRecipeCarbs());
        mFiber.setText("Fiber: " +recipe.getRecipeFiber());
        mSugar.setText("Sugar: " +recipe.getRecipeSugar());
        mProtein.setText("Protein: " + recipe.getRecipeProtein());
        mInstructions.setText(recipe.getRecipeDirections());
        mIngredients.setText(getTagsAsString(recipe.getRecipeIngredients(), ", "));


        return v;
    }

    private String getTagsAsString(List<String> list, String delim){
        StringBuilder sb = new StringBuilder();
        String loopDelim = "";
        for(String s : list) {
            sb.append(loopDelim);
            sb.append(s);
            loopDelim = delim;
        }
        return sb.toString();
    }
}
