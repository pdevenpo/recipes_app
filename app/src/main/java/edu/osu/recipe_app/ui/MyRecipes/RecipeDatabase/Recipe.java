package edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity(tableName = "Recipe")
public class Recipe {
    //id number of the recipe we wish to view
    @PrimaryKey
    @android.support.annotation.NonNull
    private String recipeId;

    //recipe name
    @ColumnInfo(name = "name")
    private String recipeName;

    //recipe source
    @ColumnInfo(name = "source")
    private String recipeSource;

    //Recipe prep times
    @ColumnInfo(name = "prepTime")
    private int recipePrepTime;

//    //Recipe wait time
    @ColumnInfo(name = "waitTime")
    private int recipeWaitTime;

    //Recipe cook times
    @ColumnInfo(name = "cookTime")
    private int recipeCookTime;

    //recipe servings
    @ColumnInfo(name = "servings")
    private int recipeServings;

    //recipe comments
    @ColumnInfo(name = "comments")
    private String recipeComments;

    //recipe calories
    @ColumnInfo(name = "calories")
    private int recipeCalories;

    //recipe fat
    @ColumnInfo(name = "fat")
    private int recipeFat;

    //recipe staurated fat
    @ColumnInfo(name = "satFat")
    private int recipeSatFat;

    //recipe carbs
    @ColumnInfo(name = "carbs")
    private int recipeCarbs;

    //recipe fiber
    @ColumnInfo(name = "fiber")
    private int recipeFiber;

    //recipe sugar
    @ColumnInfo(name = "sugar")
    private int recipeSugar;

    //recipe protein
    @ColumnInfo(name = "protein")
    private int recipeProtein;



    //recipe directions(instructions)
    @ColumnInfo(name = "directions")
    private String recipeDirections;


    //Ingredients tuple //-------
    @ColumnInfo(name = "ingredients")
    @TypeConverters(ArrayTypeConverter.class)
    private ArrayList<String> recipeIngredients;

    //recipe tags
    @ColumnInfo(name = "tags")
    @TypeConverters(ArrayTypeConverter.class)
    private ArrayList<String> recipeTags;



    //Breakfast, Lunch, or Dinner
    @ColumnInfo(name = "category")
    private String recipeCategory;


    //id
    public String getRecipeId() { return recipeId; }
    public void setRecipeId(String recipeId) { this.recipeId = recipeId; }


    //name
    public String getRecipeName() {
        return recipeName;
    }
    public void setRecipeName(String name) {
        this.recipeName = name;
    }

    //source
    public String getRecipeSource() { return recipeSource; }
    public void setRecipeSource(String name) { this.recipeSource = name;}

    //prep time
    public int getRecipePrepTime(){return recipePrepTime;}
    public void setRecipePrepTime(int prepTime) { this.recipePrepTime = prepTime;}

    //wait time
    public int getRecipeWaitTime(){return recipeWaitTime;}
    public void setRecipeWaitTime(int waitTime) { this.recipeWaitTime = waitTime;}

    //cook time
    public int getRecipeCookTime(){return recipeCookTime;}
    public void setRecipeCookTime(int cookTime) { this.recipeCookTime = cookTime;}

    //servings
    public int getRecipeServings(){return recipeServings;}
    public void setRecipeServings(int servings) { this.recipeServings = servings;}

    //comments
    public String getRecipeComments(){return recipeComments;}
    public void setRecipeComments(String comments) { this.recipeComments = comments;}

    //calories
    public int getRecipeCalories(){return recipeCalories;}
    public void setRecipeCalories(int calories) { this.recipeCalories = calories;}

    //fat
    public int getRecipeFat(){return recipeFat;}
    public void setRecipeFat(int fat) { this.recipeFat = fat;}

    //saturated fat
    public int getRecipeSatFat(){return recipeSatFat;}
    public void setRecipeSatFat(int satFat) { this.recipeSatFat = satFat;}

    //carbs
    public int getRecipeCarbs(){return recipeCarbs;}
    public void setRecipeCarbs(int carbs) { this.recipeCarbs = carbs;}

    //fiber
    public int getRecipeFiber(){return recipeFiber;}
    public void setRecipeFiber(int fiber) { this.recipeFiber = fiber;}

    //sugar
    public int getRecipeSugar(){return recipeSugar;}
    public void setRecipeSugar(int sugar) { this.recipeSugar = sugar;}

    //protein
    public int getRecipeProtein(){return recipeProtein;}
    public void setRecipeProtein(int protein) { this.recipeProtein = protein;}

    //instructions
    public String getRecipeDirections() {
        return recipeDirections;
    }
    public void setRecipeDirections(String directions) {
        this.recipeDirections = directions;
    }

    //ingredients
    public ArrayList<String> getRecipeIngredients(){return recipeIngredients;}
    public void setRecipeIngredients(ArrayList<String> ingredients) { this.recipeIngredients = ingredients;}

    //Tags
    public ArrayList<String> getRecipeTags(){return recipeTags;}
    public void setRecipeTags(ArrayList<String> tags) { this.recipeTags = tags;}

    public String getRecipeCategory(){return recipeCategory;}
    public void setRecipeCategory(String category) { this.recipeCategory = category;}


}

class ArrayTypeConverter {
    private Gson gson = new Gson();

    @TypeConverter
    public ArrayList<String> stringToList(String data){
        ArrayList<String> convertedList = new ArrayList<String>();
        if(data != null){
            Type listType = new TypeToken<ArrayList<String>>() {}.getType();
            convertedList = gson.fromJson(data, listType);
        }
        return convertedList;
    }

    @TypeConverter
    public String ListToString(ArrayList<String> data){
        return gson.toJson(data);
    }


}
