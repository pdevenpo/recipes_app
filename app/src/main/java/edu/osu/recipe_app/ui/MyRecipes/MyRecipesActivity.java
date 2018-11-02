package edu.osu.recipe_app.ui.MyRecipes;

import java.io.IOException;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.MyRecipes.JSONParser.RecipeParser;

public class MyRecipesActivity extends Activity{

    //create variables
    ProgressDialog mProgressDialog;
    RecipeParser mRecipeParser;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_recipes_activity);

        // Locate the Buttons in activity_main.xml
        Button titlebutton = (Button) findViewById(R.id.titlebutton);

        // Capture button click
        titlebutton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                ShowProgressDialogue();
                mRecipeParser = new RecipeParser();
                try{
                    mRecipeParser.Parse();
                } catch (org.json.JSONException exception){

                }
            }
        });

    }

    public void ParseRecipes() throws JSONException{

    }


    public void ShowProgressDialogue(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Parsing Recipes");
        //mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();
    }
}