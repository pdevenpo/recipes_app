package edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Recipe")
public class Recipe {
    //id number of the recipe we wish to view
    @PrimaryKey
    @android.support.annotation.NonNull
    private String recipeId;

    //recipe name
    @ColumnInfo(name = "name")
    private String recipeName;

//    //recipe source
//    @ColumnInfo(name = "source")
//    private String recipeSource;

    //Recipe prep times
    @ColumnInfo(name = "prepTime")
    private int recipePrepTime;

//    //Recipe wait time
//    @ColumnInfo(name = "waitTime")
//    private int recipeWaitTime;

    //Recipe cook times
    @ColumnInfo(name = "cookTime")
    private int recipeCookTime;

    //recipe directions(instructions)
    @ColumnInfo(name = "directions")
    private String recipeDirections;



    //Ingredients tuple
    @ColumnInfo(name = "ingredients")
    private String recipeIngredients;

    //Breakfast, Lunch, or Dinner
    @ColumnInfo(name = "category")
    private String recipeCategory;

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

//    public String getRecipeSource() {
//        return recipeSource;
//    }
//
//    public void setRecipeSource(String name) {
//        this.recipeSource = name;
//    }

    public String getRecipeDirections() {
        return recipeDirections;
    }

    public void setRecipeDirections(String directions) {
        this.recipeDirections = directions;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String name) {
        this.recipeName = name;
    }

    public int getRecipePrepTime(){return recipePrepTime;}

    public void setRecipePrepTime(int prepTime) { this.recipePrepTime = prepTime;}

    public int getRecipeCookTime(){return recipeCookTime;}

    public void setRecipeCookTime(int cookTime) { this.recipeCookTime = cookTime;}

    public String getRecipeCategory(){return recipeCategory;}

    public void setRecipeCategory(String category) { this.recipeCategory = category;}

    public String getRecipeIngredients(){return recipeIngredients;}

    public void setRecipeIngredients(String ingredients) { this.recipeIngredients = ingredients;}
}
