package edu.osu.recipe_app.ui.TodaysPick;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.Recipe;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.RecipeRepository;
import edu.osu.recipe_app.ui.TodaysPick.RecyclerView.ILoadMoreToday;
import edu.osu.recipe_app.ui.TodaysPick.RecyclerView.ItemToday;
import edu.osu.recipe_app.ui.TodaysPick.RecyclerView.MyAdapterToday;

public class TodaysPickActivity extends AppCompatActivity {

    List<ItemToday> items = new ArrayList<>();
    MyAdapterToday adapter = null;
    //Button mRecipeButton;

    private RecipeRepository mRecipeRepository;
    private List<Recipe> mRecipesList;

    int counter = 0; //counter for which recipe to pull from the database
    int mInitialLoadNumber = 10; //sets how many you want to load at a time


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.todays_pick_activity);
//
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.TodaysPickContainer);
//
//        if(fragment == null){
//            fragment = new TodaysPickFragment();
//            fm.beginTransaction()
//                    .add(R.id.TodaysPickContainer, fragment)
//                    .commit();
//
//        }
        mRecipeRepository = new RecipeRepository(this);
        mRecipesList = mRecipeRepository.listRecipes();

        setContentView(R.layout.todays_pick_recyclerview);
        getRecipesToDisplay(0, mInitialLoadNumber);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler_todays_pick);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapterToday(recycler, this, items);
        recycler.setAdapter(adapter);

        //mRecipeButton = findViewById(R.id.recipeButton);

        //set load more event
        adapter.setLoadMore(new ILoadMoreToday() {
            @Override
            public void onLoadMore() {
                //if(items.size() <= 20){ //load 20 more items
                if(items.size() <= (mRecipesList.size() - mInitialLoadNumber)){ //load all recipes
                    items.add(null);
                    adapter.notifyItemInserted(items.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size() - 1);
                            adapter.notifyItemRemoved(items.size());

                            //random more data
                            int index = items.size();
                            int end = index + 10;
                            //randomData(index, end);
                            getRecipesToDisplay(index, end);

                            adapter.notifyDataSetChanged();
                            adapter.setLoaded();
                        }
                    }, 5000);
                } else {
                    Toast.makeText(TodaysPickActivity.this, "End of recipes list", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

//        savedInstanceState.putBoolean("MyBoolean", true);
//        savedInstanceState.putDouble("myDouble", 1.9);
//        savedInstanceState.putInt("MyInt", 1);
          //savedInstanceState.putParcelableArrayList("items",items);




    }

    private void getRecipesToDisplay(int index, int end){
        int num = 0;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd");
        String strDate =mdformat.format(calendar.getTime());
        String daysSubString = strDate.substring(strDate.length()-2,strDate.length());
        int result = Integer.parseInt(daysSubString);

        if(result > 0 && result <= 10){
            num = 3;
        }else if(result>10 && result<=20){
            num = 5;
        }else{
            num = 8;
        }


        //String name = UUID.randomUUID().toString();
        Recipe recipe = mRecipesList.get(num);

        String name = recipe.getRecipeName();
        List<String> tags = recipe.getRecipeTags();
        String tagsAsString = getTagsAsString(tags, ", ");

        ItemToday item = new ItemToday(counter + ". " + name, "tags: " + tagsAsString);
        items.add(item);

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
