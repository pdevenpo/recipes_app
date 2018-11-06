package edu.osu.recipe_app.ui.MyRecipes.RecyclerView;

import android.view.View;

public interface ItemClickListener {
    void onClick(View view, int position, boolean isLongClick);
}
