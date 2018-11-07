package edu.osu.recipe_app.ui.TodaysPick;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.Recipe;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.RecipeRepository;
import edu.osu.recipe_app.ui.TodaysPick.RecyclerView.ItemToday;
import edu.osu.recipe_app.ui.TodaysPick.RecyclerView.MyAdapterToday;

public class TodaysPickFragment extends Fragment {
    //create variables
    private final String TAG = "RecipeActivity";

    List<ItemToday> items = new ArrayList<>();
    MyAdapterToday adapter = null;
    Button mRecipeButton;

    private RecipeRepository mRecipeRepository;
    private List<Recipe> mRecipesList;

    int counter = 0; //counter for which recipe to pull from the database
    int mInitialLoadNumber = 10; //sets how many you want to load at a time

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(this.TAG, "OnCreateView");
        super.onCreateView(inflater, parent, savedInstanceState);
        View v = inflater.inflate(R.layout.todays_pick_recyclerview, parent, false);


        return v;
    }




}
