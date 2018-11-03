package edu.osu.recipe_app;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.Recipe;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.RecipeDao;
import edu.osu.recipe_app.ui.MyAccount.UserData.User;
import edu.osu.recipe_app.ui.MyAccount.UserData.UserDao;

@Database(entities = {User.class, Recipe.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RecipeDao recipeDao();
}