package edu.osu.recipe_app.ui.MyRecipes;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import edu.osu.recipe_app.AppDatabase;


public class RecipeRepository {
    private AppDatabase db;

    public RecipeRepository(Context context){
        db = Room.databaseBuilder(context, AppDatabase.class, "AppDatabaseCheckpoint4").allowMainThreadQueries().build();
    }

    public void insertRecipe(String recipeId, String recipeName, String recipeDirections, String recipePrepTime, String recipeCookTime, String recipeIngredients, String recipeCategory){
        Recipe r = new Recipe();

        r.setRecipeId(recipeId);
        r.setRecipeCategory(recipeCategory);
        r.setRecipeCookTime(recipeCookTime);
        r.setRecipeDirections(recipeDirections);
        r.setRecipeIngredients(recipeIngredients);
        r.setRecipeName(recipeName);
        r.setRecipePrepTime(recipePrepTime);

        insertRecipeHelper(r);

    }

    public void insertRecipeHelper(final Recipe recipe){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                Log.d("AsyncInsert", "recipe: " + recipe.getRecipeId() + " added");
                db.recipeDao().insert(recipe);
                return null;
            }
        }.execute();
    }

    public Recipe getRecipe(final String recipeId){
        return db.recipeDao().findByRecipeId(recipeId);
    }

    public List<Recipe> listRecipes(){
        return db.recipeDao().getAll();
    }


}
