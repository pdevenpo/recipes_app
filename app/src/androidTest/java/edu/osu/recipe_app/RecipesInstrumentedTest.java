package edu.osu.recipe_app;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.Recipe;
import edu.osu.recipe_app.ui.MyRecipes.RecipeDatabase.RecipeRepository;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class RecipesInstrumentedTest {
    private RecipeRepository mRecipeRepository;

    private static final String RECIPE_ID_1 = "123456";
    private static final String RECIPE_NAME_1 = "Noodles";
    private static final String RECIPE_SOURCE_1 = "Food.com";
    private static final int RECIPE_PREP_TIME_1 = 5;
    private static final int RECIPE_WAIT_TIME_1 = 10;
    private static final int RECIPE_COOK_TIME_1 = 30;
    private static final int RECIPE_SERVINGS_1 = 3;
    private static final String RECIPE_COMMENTS_1 = "N/A";
    private static final int RECIPE_CALORIES_1 = 540;
    private static final int RECIPE_FAT_1 = 5;
    private static final int RECIPE_SAT_FAT_1 = 3;
    private static final int RECIPE_CARBS_1 = 15;
    private static final int RECIPE_FIBER_1 = 3;
    private static final int RECIPE_SUGAR_1 = 2;
    private static final int RECIPE_PROTEIN_1 = 7;
    private static final String RECIPE_DIRECTIONS_1 = "Bake at 400 degrees for 30 minutes";
    private ArrayList<String> recipe_ingredients_1;
    private ArrayList<String> recipe_tags_1;
    private static final String RECIPE_CATEGORY_1 = "Breakfast";


    @Before
    public void setUpRecipeRepository(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        mRecipeRepository = new RecipeRepository(appContext);

        recipe_ingredients_1 = new ArrayList<>();
        recipe_tags_1 = new ArrayList<>();

        recipe_ingredients_1.add("noodles");
        recipe_ingredients_1.add("salt");

        recipe_tags_1.add("vegan");

        if(mRecipeRepository.getRecipe(RECIPE_NAME_1) == null){
            mRecipeRepository.insertRecipe(RECIPE_ID_1, RECIPE_NAME_1, RECIPE_SOURCE_1,
                    RECIPE_PREP_TIME_1, RECIPE_WAIT_TIME_1, RECIPE_COOK_TIME_1, RECIPE_SERVINGS_1,
                    RECIPE_COMMENTS_1, RECIPE_CALORIES_1, RECIPE_FAT_1, RECIPE_SAT_FAT_1,
                    RECIPE_CARBS_1, RECIPE_FIBER_1, RECIPE_SUGAR_1, RECIPE_PROTEIN_1,
                    RECIPE_DIRECTIONS_1, recipe_ingredients_1, recipe_tags_1, RECIPE_CATEGORY_1);
        }
    }

    @Test
    public void getRecipeNameTest(){
        Recipe r = new Recipe();

        r.setRecipeName(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_NAME_1, r.getRecipeName());
    }

    @Test
    public void retrieveRecipeNameFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_NAME_1, r.getRecipeName());
    }

    @Test
    public void retrieveRecipeIdFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_ID_1, r.getRecipeId());
    }

    @Test
    public void retrieveRecipeSourceFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_SOURCE_1, r.getRecipeSource());
    }

    @Test
    public void retrieveRecipePrepTimeFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_PREP_TIME_1, r.getRecipePrepTime());
    }

    @Test
    public void retrieveRecipeWaitTimeFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_WAIT_TIME_1, r.getRecipeWaitTime());
    }

    @Test
    public void retrieveRecipeCookTimeFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_COOK_TIME_1, r.getRecipeCookTime());
    }

    @Test
    public void retrieveRecipeServingsFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_SERVINGS_1, r.getRecipeServings());
    }

    @Test
    public void retrieveRecipeCommentsFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_COMMENTS_1, r.getRecipeComments());
    }

    @Test
    public void retrieveRecipeCaloriesFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_CALORIES_1, r.getRecipeCalories());
    }

    @Test
    public void retrieveRecipeFatFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_FAT_1, r.getRecipeFat());
    }

    @Test
    public void retrieveRecipeSatFatFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_SAT_FAT_1, r.getRecipeSatFat());
    }

    @Test
    public void retrieveRecipeCarbsFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_CARBS_1, r.getRecipeCarbs());
    }

    @Test
    public void retrieveRecipeFiberFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_FIBER_1, r.getRecipeFiber());
    }

    @Test
    public void retrieveRecipeSugarFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_SUGAR_1, r.getRecipeSugar());
    }

    @Test
    public void retrieveRecipeProteinFromDatabaseTest() {
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_PROTEIN_1, r.getRecipeProtein());
    }

    @Test
    public void retrieveRecipeDirectionsFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_DIRECTIONS_1, r.getRecipeDirections());
    }

    @Test
    public void retrieveRecipeIngredientsFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(recipe_ingredients_1, r.getRecipeIngredients());
    }

    @Test
    public void retrieveRecipeTagsFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(recipe_tags_1, r.getRecipeTags());
    }

    @Test
    public void retrieveRecipeCategoryFromDatabaseTest(){
        Recipe r = mRecipeRepository.getRecipe(RECIPE_NAME_1);

        Assert.assertEquals(RECIPE_CATEGORY_1, r.getRecipeCategory());
    }
}
