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

    //recipe directions(instructions)
    @ColumnInfo(name = "directions")
    private String recipeDirections;

    //Recipe prep times
    @ColumnInfo(name = "prepTime")
    private String recipePrepTime;

    //Recipe cook times
    @ColumnInfo(name = "cookTime")
    private String recipeCookTime;

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

    public String getRecipePrepTime(){return recipePrepTime;}

    public void setRecipePrepTime(String prepTime) { this.recipePrepTime = prepTime;}

    public String getRecipeCookTime(){return recipeCookTime;}

    public void setRecipeCookTime(String cookTime) { this.recipeCookTime = cookTime;}

    public String getRecipeCategory(){return recipeCategory;}

    public void setRecipeCategory(String category) { this.recipeCategory = category;}

    public String getRecipeIngredients(){return recipeIngredients;}

    public void setRecipeIngredients(String ingredients) { this.recipeIngredients = ingredients;}
}
