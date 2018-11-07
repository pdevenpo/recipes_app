package edu.osu.recipe_app.ui.MyRecipes;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.StringJoiner;
        import java.util.UUID;
        import android.content.Intent;

        import edu.osu.recipe_app.MainActivity;
        import edu.osu.recipe_app.R;
        import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.Recipe;
        import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.RecipeRepository;
        import edu.osu.recipe_app.ui.MyRecipes.RecyclerView.ILoadMore;
        import edu.osu.recipe_app.ui.MyRecipes.RecyclerView.Item;
        import edu.osu.recipe_app.ui.MyRecipes.RecyclerView.MyAdapter;

public class MyRecipesActivity extends AppCompatActivity {

    List<Item> items = new ArrayList <>();
    MyAdapter adapter = null;
    //Button mRecipeButton;

    private RecipeRepository mRecipeRepository;
    private List<Recipe> mRecipesList;

    int counter = 0; //counter for which recipe to pull from the database
    int mInitialLoadNumber = 10; //sets how many you want to load at a time

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //fragment code will add back later.
//        setContentView(R.layout.my_recipes_fragment_container);
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.MyRecipesFragmentContainer);
//
//        if(fragment == null){
//            fragment = new MyRecipesFragment();
//            fm.beginTransaction()
//                    .add(R.id.MyRecipesFragmentContainer, fragment)
//                    .commit();
//        }

        mRecipeRepository = new RecipeRepository(this);
        mRecipesList = mRecipeRepository.listRecipes();

        setContentView(R.layout.my_recipes_recyclerview);
        getRecipesToDisplay(0, mInitialLoadNumber);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(recycler, this, items);
        recycler.setAdapter(adapter);

        //mRecipeButton = findViewById(R.id.recipeButton);

        //set load more event
        adapter.setLoadMore(new ILoadMore() {
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
                    Toast.makeText(MyRecipesActivity.this, "End of recipes list", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getRecipesToDisplay(int index, int end){
        for(int i = index; i < end; i++){
            //String name = UUID.randomUUID().toString();
            Recipe recipe = mRecipesList.get(counter);
            counter++;
            String name = recipe.getRecipeName();
            List<String> tags = recipe.getRecipeTags();
            String tagsAsString = getTagsAsString(tags, ", ");

            Item item = new Item(counter + ". " + name, "tags: " + tagsAsString);
            items.add(item);
        }
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