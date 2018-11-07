package edu.osu.recipe_app;

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

import edu.osu.recipe_app.ui.MyRecipes.JSONParser.RecipeObj;
import edu.osu.recipe_app.ui.MyRecipes.JSONParser.RecipeParser;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.Recipe;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.RecipeRepository;
import edu.osu.recipe_app.ui.Orientation.FragmentLandscape;
import edu.osu.recipe_app.ui.Orientation.FragmentPortrait;


public final class MainActivity extends AppCompatActivity {
    private final String s = "MainActivity";
    private String mCurrentUser = "User";


    //create variables
    private final String TAG = "RecipeActivity";

    private ProgressDialog mProgressDialog;
    private RecipeParser mRecipeParser;
    private Button mDatabaseButton;
    private Button mListRecipesDebug;

    private ArrayList<RecipeObj> mRecipeList = new ArrayList <>();
    private RecipeRepository mRecipeRepository;








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

        mRecipeRepository = new RecipeRepository(this);
        mRecipeParser = new RecipeParser();

        String recipesString = ReadRecipesFile();

        try{
            mRecipeList = mRecipeParser.Parse(recipesString);
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
        populateDatabase(mRecipeList);
        

        createNotificationChannel();
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
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Parsing Recipes");
        //mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();
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

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Timer";
            String description = "Built-in timer notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

