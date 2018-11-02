package edu.osu.recipe_app.ui.MyRecipes.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.*;

import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.RecipeRepository;

public class RecipeParser {

    private RecipeRepository mRecipeRepository;


    public RecipeParser(){

    }

    public ArrayList<RecipeObj> Parse() throws JSONException {
        String jsonString = readFile("db-recipes.json");
        ArrayList<RecipeObj> recipeList = new ArrayList<RecipeObj>();
        JSONObject obj = new JSONObject(jsonString.trim());
        Iterator<String> keys = obj.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            if(obj.get(key) instanceof JSONObject) {
                JSONObject value = obj.getJSONObject(key);
                recipeList.add(ParseRecipe(value));
            }
        }
        return recipeList;
    }

    public RecipeObj ParseRecipe(JSONObject recipeObj) throws JSONException {
        RecipeObj recipe = new RecipeObj();

        recipe.setId(recipeObj.getString("id"));
        recipe.setName(recipeObj.getString("name"));
        recipe.setSource(recipeObj.getString("source"));
        recipe.setPrepTime(recipeObj.getInt("preptime"));
        recipe.setWaitTime(recipeObj.getInt("waittime"));
        recipe.setCookTime(recipeObj.getInt("cooktime"));
        recipe.setServings(recipeObj.getInt("servings"));
        recipe.setComments(recipeObj.getString("comments"));
        recipe.setCalories(recipeObj.getInt("calories"));
        recipe.setFat(recipeObj.getInt("fat"));
        recipe.setSatFat(recipeObj.getInt("satfat"));
        recipe.setCarbs(recipeObj.getInt("carbs"));
        recipe.setFiber(recipeObj.getInt("fiber"));
        recipe.setSugar(recipeObj.getInt("sugar"));
        recipe.setProtein(recipeObj.getInt("protein"));
        recipe.setInstructions(recipeObj.getString("instructions"));
        recipe.setIngredients(ParseJSONArray(recipeObj, "ingredients"));
        recipe.setTags(ParseJSONArray(recipeObj, "tags"));
        return recipe;
    }

    public ArrayList<String> ParseJSONArray(JSONObject obj, String tag) throws JSONException {
        ArrayList<String> returnArr = new ArrayList<String>();
        JSONArray jArray = obj.getJSONArray(tag);
        for(int i = 0; i < jArray.length(); i++) {
            returnArr.add(jArray.getString(i));
        }
        return returnArr;
    }

    public String readFile(String filename) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}

