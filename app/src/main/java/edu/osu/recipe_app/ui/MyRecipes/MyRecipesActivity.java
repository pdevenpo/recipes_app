package edu.osu.recipe_app.ui.MyRecipes;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.UUID;

        import edu.osu.recipe_app.R;
        import edu.osu.recipe_app.ui.MyRecipes.RecyclerView.ILoadMore;
        import edu.osu.recipe_app.ui.MyRecipes.RecyclerView.Item;
        import edu.osu.recipe_app.ui.MyRecipes.RecyclerView.MyAdapter;

public class MyRecipesActivity extends AppCompatActivity {

    List<Item> items = new ArrayList <>();
    MyAdapter adapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
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


        setContentView(R.layout.my_recipes_recyclerview);
        randomData(10); //generate random placeholder data for the recyclerview

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(recycler, this, items);
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
                    Toast.makeText(MyRecipesActivity.this, "Load data completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
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