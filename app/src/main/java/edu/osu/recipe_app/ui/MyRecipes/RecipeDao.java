package edu.osu.recipe_app.ui.MyRecipes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.osu.recipe_app.ui.UserData.User;

@Dao
public interface RecipeDao {
    @Query("SELECT * FROM recipe")
    List<Recipe> getAll();

    @Query("SELECT * FROM recipe WHERE name LIKE :recipeName")
    Recipe findByRecipeId(String recipeName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Recipe recipe);

}
