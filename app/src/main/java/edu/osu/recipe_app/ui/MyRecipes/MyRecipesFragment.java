package edu.osu.recipe_app.ui.MyRecipes;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.MyRecipes.JSONParser.RecipeObj;
import edu.osu.recipe_app.ui.MyRecipes.JSONParser.RecipeParser;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.Recipe;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.RecipeRepository;
import edu.osu.recipe_app.ui.MyRecipes.RecyclerView.ILoadMore;
import edu.osu.recipe_app.ui.MyRecipes.RecyclerView.Item;
import edu.osu.recipe_app.ui.MyRecipes.RecyclerView.MyAdapter;

public class MyRecipesFragment extends Fragment {

    //create variables
    private final String TAG = "RecipeActivity";

    private ProgressDialog mProgressDialog;
    private RecipeParser mRecipeParser;
    private Button mDatabaseButton;
    private Button mListRecipesDebug;

    private ArrayList<RecipeObj> mRecipeList = new ArrayList <>();
    private RecipeRepository mRecipeRepository;

    List<Item> items = new ArrayList <>();
    MyAdapter adapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(this.TAG, "OnCreateView");
        super.onCreateView(inflater, parent, savedInstanceState);
        View v = inflater.inflate(R.layout.my_recipes_recyclerview, parent, false);

        mRecipeRepository = new RecipeRepository(this.getContext());

        randomData(10); //generate random placeholder data for the recyclerview

        RecyclerView recycler = (RecyclerView) v.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new MyAdapter(recycler, this.getActivity(), items);
        recycler.setAdapter(adapter);

        //set load more event
        adapter.setLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                if(items.size() <= 20){
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
                            for(int i = index; i < end; i++){
                                String name = UUID.randomUUID().toString();
                                Item item = new Item(name, name.length());
                                items.add(item);
                            }
                            adapter.notifyDataSetChanged();
                            adapter.setLoaded();
                        }
                        }, 5000);
                } else {
                    Toast.makeText(getActivity(), "Load data completed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    private void randomData(int length){
        //create random data
        for(int i = 0; i < length; i++){
            String name = UUID.randomUUID().toString();
            Item item = new Item(name, name.length());
            items.add(item);
        }

    }

}
