package edu.osu.recipe_app.ui.MyRecipes;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.MyRecipes.JSONParser.RecipeObj;
import edu.osu.recipe_app.ui.MyRecipes.JSONParser.RecipeParser;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.Recipe;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.RecipeRepository;

public class MyRecipesFragment extends Fragment {

    //create variables
    private final String TAG = "RecipeActivity";

    private ProgressDialog mProgressDialog;
    private RecipeParser mRecipeParser;
    private Button mDatabaseButton;
    private Button mListRecipesDebug;

    private ArrayList<RecipeObj> mRecipeList = new ArrayList <>();
    private RecipeRepository mRecipeRepository;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(this.TAG, "OnCreateView");
        super.onCreateView(inflater, parent, savedInstanceState);
        View v = inflater.inflate(R.layout.my_recipes_activity, parent, false);

        mRecipeRepository = new RecipeRepository(this.getContext());

        // Locate the Buttons in activity_main.xml
        mDatabaseButton = (Button) v.findViewById(R.id.titlebutton);
        mListRecipesDebug = v.findViewById(R.id.displayDatabase);


        // Capture button click
        mDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.d(TAG, "ClickedButton");

                mRecipeParser = new RecipeParser();

                String recipesString = ReadRecipesFile();

                try{
                    mRecipeList = mRecipeParser.Parse(recipesString);
                } catch (org.json.JSONException e) {
                    e.printStackTrace();
                }
                populateDatabase(mRecipeList);
            }
        });

        mListRecipesDebug.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG, "Clicked show items in database");
                displayDatabase();
            }

        }));

        return v;
    }

    //add recipes to the database
    public void populateDatabase(ArrayList<RecipeObj> recipes) {
        Log.d(this.TAG, "Length of recipes list: " + recipes.size());
        //mRecipeRepository.deleteRecipe();

        List<Recipe> recipeList = mRecipeRepository.listRecipes();
        Log.d(this.TAG, "SIZE OF DATABASE TABLE " + recipeList.size());


        for (int i = 0; i < recipes.size(); i++) {
            Log.d(this.TAG, "Name of recipe " + i + ": " + recipes.get(i).getName());
            RecipeObj r = recipes.get(i);
            //set new ID string since it seems a lot of  consecutive numbers were skipped in the JSON file.
            String idNumber = "id" + i;
           mRecipeRepository.insertRecipe(idNumber, r.getName(), r.getSource(), r.getPrepTime(), r.getWaitTime(), r.getCookTime(),
                                            r.getServings(), r.getComments(), r.getCalories(), r.getFat(), r.getSatFat(), r.getCarbs(),
                                            r.getFiber(), r.getSugar(), r.getProtein(), r.getInstructions(), r.getIngredients(), r.getTags(),
                                              "");
        }
    }

    public void displayDatabase(){
        Log.d(this.TAG, "displaying recipe database data");
        List<Recipe> recipeList = mRecipeRepository.listRecipes();
        Log.d(this.TAG, "Length of recipes list: " + recipeList.size());
        for(int i = 0; i < recipeList.size(); i++){
            Log.d(TAG,"Recipe " + i + " Title: " + recipeList.get(i).getRecipeName());
        }

    }

    public String ReadRecipesFile(){
        String result = "";
        try{
            InputStream is = getResources().openRawResource(R.raw.recipes);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();


        } catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }


    public void ShowProgressDialogue(){
        mProgressDialog = new ProgressDialog(this.getContext());
        mProgressDialog.setTitle("Parsing Recipes");
        //mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();
    }
}
