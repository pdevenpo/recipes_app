package edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RecipeDao {
    @Query("SELECT * FROM recipe")
    List<Recipe> getAll();

    @Query("DELETE FROM recipe")
    void deleteRecipeTable();

    @Query("SELECT * FROM recipe WHERE name LIKE :recipeName")
    Recipe findByRecipeId(String recipeName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Recipe recipe);

}
