package edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.osu.recipe_app.AppDatabase;


public class RecipeRepository {
    private AppDatabase db;

    public RecipeRepository(Context context){
        db = Room.databaseBuilder(context, AppDatabase.class, "AppDatabaseCheckpoint5").allowMainThreadQueries().build();
    }

    public void insertRecipe(String recipeId, String recipeName, String recipeSource,
                             int recipePrepTime, int recipeWaitTime, int recipeCookTime,
                             int recipeServings, String recipeComments, int recipeCalories,
                             int recipeFat, int recipeSatFat, int recipeCarbs,
                             int recipeFiber, int recipeSugar, int recipeProtein,
                             String recipeDirections, ArrayList<String> recipeIngredients,
                             ArrayList<String> recipeTags, String recipeCategory){

        Recipe r = new Recipe();

        r.setRecipeId(recipeId);
        r.setRecipeName(recipeName);
        r.setRecipeSource(recipeSource);
        r.setRecipePrepTime(recipePrepTime);
        r.setRecipeWaitTime(recipeWaitTime);
        r.setRecipeCookTime(recipeCookTime);
        r.setRecipeServings(recipeServings);
        r.setRecipeComments(recipeComments);
        r.setRecipeCalories(recipeCalories);
        r.setRecipeFat(recipeFat);
        r.setRecipeSatFat(recipeSatFat);
        r.setRecipeCarbs(recipeCarbs);
        r.setRecipeFiber(recipeFiber);
        r.setRecipeSugar(recipeSugar);
        r.setRecipeProtein(recipeProtein);
        r.setRecipeDirections(recipeDirections);
        r.setRecipeIngredients(recipeIngredients);
        r.setRecipeTags(recipeTags);
        r.setRecipeCategory(recipeCategory);

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

    public void deleteRecipe(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.recipeDao().deleteRecipeTable();
                return null;
            }
        }.execute();
        db.recipeDao().deleteRecipeTable();
    }

    public Recipe getRecipe(final String recipeId){
        return db.recipeDao().findByRecipeId(recipeId);
    }

    public List<Recipe> listRecipes(){
        return db.recipeDao().getAll();
    }

}
