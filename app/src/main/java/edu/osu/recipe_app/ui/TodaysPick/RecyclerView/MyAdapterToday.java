package edu.osu.recipe_app.ui.TodaysPick.RecyclerView;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.MyRecipes.ViewRecipe.ViewRecipeActivity;

class LoadingViewHolder extends RecyclerView.ViewHolder{
    public ProgressBar progressBar;
    public LoadingViewHolder(View itemView){
        super(itemView);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
    }

}

class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
    public TextView mName, mTags;
    //public Button mRecipeButton;

    private ItemClickListenerToday itemClickListener;

    public ItemViewHolder(View itemView){
        super(itemView);
        mName = itemView.findViewById(R.id.txtName);
        mTags = itemView.findViewById(R.id.txtLength);
        //mRecipeButton = itemView.findViewById(R.id.recipeButton);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListenerToday itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v){
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    public boolean onLongClick(View v){
        itemClickListener.onClick(v, getAdapterPosition(), true);
        return true;
//        return false;
    }
}

public class MyAdapterToday extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    ILoadMoreToday loadMore;
    boolean isLoading;
    Activity activity;
    List<ItemToday> items;
    int visibleThreshold = 5;
    int lastVisibleItem, totalItemCount;

    public MyAdapterToday(RecyclerView recyclerView, Activity activity, List<ItemToday> items){
        this.activity = activity;
        this.items = items;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)){
                    if(loadMore != null){
                        //loadMore.onLoadMore();
                        isLoading = true;
                    }
                }
            }
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
        });

    }

    @Override
    public int getItemViewType(int position){
        return items.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoadMore(ILoadMoreToday loadMore){
        this.loadMore = loadMore;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(activity).inflate(R.layout.item_layout, parent, false);

            return new ItemViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING){
            View view = LayoutInflater.from(activity).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ItemToday item = items.get(holder.getAdapterPosition());
            int adapterPos=holder.getAdapterPosition();
            if (adapterPos<0){
                adapterPos*=-1;
            }
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.mName.setText(items.get(holder.getAdapterPosition()).getName());
            viewHolder.mTags.setText(String.valueOf(items.get(holder.getAdapterPosition()).getLength()));

            viewHolder.setItemClickListener(new ItemClickListenerToday() {
                @Override
                public void onClick(View view, int adapterPos, boolean isLongClick) {
                    adapterPos = holder.getAdapterPosition();
//                    ItemToday clickedItem = items.get(position);
//                    Toast.makeText(activity, clickedItem.getName(), Toast.LENGTH_SHORT).show();
//                    if(isLongClick){
//                        Toast.makeText(activity, "Long Click: " + items.get(position), Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(activity, "Short Click: " + items.get(position), Toast.LENGTH_SHORT).show();
//                    }
                    Intent intent = new Intent(view.getContext(), ViewRecipeActivity.class);
                    intent.putExtra("Recipe", adapterPos);
                    //intent.pu
                    view.getContext().startActivity(intent);

                }
            });

//            viewHolder.mRecipeButton.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view){
//
//                    Intent intent = new Intent(view.getContext(), ViewRecipeActivity.class);
//                    //intent.putExtra("Recipe", position);
//                    view.getContext().startActivity(intent);
//                }
//            });

        } else if (holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }




    @Override
    public int getItemCount() {
        return items.size();
    }


    public void setLoaded(){
        isLoading = false;
    }
}
