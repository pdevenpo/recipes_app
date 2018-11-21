package edu.osu.recipe_app.ui.Orientation;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.FindStore.FindStoreActivity;
import edu.osu.recipe_app.ui.MyAccount.AccountLoginActivity;
import edu.osu.recipe_app.ui.MyRecipes.JSONParser.RecipeObj;
import edu.osu.recipe_app.ui.MyRecipes.JSONParser.RecipeParser;
import edu.osu.recipe_app.ui.MyRecipes.MyRecipesActivity;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.Recipe;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.RecipeRepository;
import edu.osu.recipe_app.ui.Timer.TimerActivity;
import edu.osu.recipe_app.ui.TodaysPick.TodaysPickActivity;

public class FragmentLandscape extends Fragment {
    private final String s = "FragmentPortrait";
    private final String TAG = "FragmentPortrait";


    private TextView mWelcomeMessage;
    private String mCurrentUser = "User3";
    private final String LOGIN_USER = "User2";
    private String mHeaderWelcome = "Welcome, ";

    private RecipeParser mRecipeParser;
    private ArrayList<RecipeObj> mRecipeList = new ArrayList <>();
    private RecipeRepository mRecipeRepository;


    private Button mMyRecipes;
    private Button mFindStore;
    private Button mTimer;
    private Button mTodaysPick;
    private Button mMyAccount;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.landscape_fragment, container, false);

        if(savedInstanceState != null){
            mCurrentUser = savedInstanceState.getString(LOGIN_USER, "User");
        }

        String newUser = getArguments().getString("CurrentUser");
        if(newUser != null){
            mCurrentUser = newUser;
        }


        mWelcomeMessage = v.findViewById(R.id.userWelcome);
        mWelcomeMessage.setText(mHeaderWelcome + mCurrentUser + "!");


        mMyRecipes = v.findViewById(R.id.MyRecipesButton);
        mFindStore = v.findViewById(R.id.FindStoreButton);
        mTimer = v.findViewById(R.id.TimerButton);
        mTodaysPick = v.findViewById(R.id.TodaysPickButton);
        mMyAccount = v.findViewById(R.id.MyAccountButton);


        mRecipeRepository = new RecipeRepository(this.getContext());
        String recipesString = ReadRecipesFile();
        mRecipeParser = new RecipeParser();
        try{
            mRecipeList = mRecipeParser.Parse(recipesString);
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
        populateDatabase(mRecipeList);

        mMyRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), MyRecipesActivity.class);
                startActivity(intent);
            }
        });
        mFindStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FindStoreActivity.class);
                startActivity(intent);
            }
        });
        mTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TimerActivity.class);
                startActivity(intent);
            }
        });
        mTodaysPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TodaysPickActivity.class);
                startActivity(intent);
            }
        });
        mMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AccountLoginActivity.class);
                startActivity(intent);
            }
        });
        return v;
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


    //add recipes to the database
    public void populateDatabase(ArrayList<RecipeObj> recipes) {
        Log.d(this.TAG, "Length of recipes list: " + recipes.size());
        mRecipeRepository.deleteRecipe();

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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(LOGIN_USER, mCurrentUser);
    }
}
