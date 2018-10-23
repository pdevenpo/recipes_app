package edu.osu.recipe_app.ui.MyRecipes;

import java.io.IOException;
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

public class MyRecipeActivity extends Activity{

    //create variables
    ProgressDialog mProgressDialog;
    String[] recipeNameArr = new String[10];
    String[] prepTimeArr = new String[10];
    String[] directionsArr = new String[10];
    String[] ingredientsArr = new String[10];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_recipes_activity);

        // Locate the Buttons in activity_main.xml
        Button titlebutton = (Button) findViewById(R.id.titlebutton);

        // Capture button click
        titlebutton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                // Execute Scraper AsyncTask
                new Scraper().execute();
            }
        });



    }


    private class Scraper extends AsyncTask<Void, Void, Void> {
        //Store variable names (may be database columns)
        String title;
        String time;
        String rating = "0";
        String direction;
        String ingredient;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MyRecipeActivity.this);
            mProgressDialog.setTitle("Retrieving Recipe Names");
            mProgressDialog.setMessage("Cooking...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {

                int j = 0;
                for(int i = 12494; i < 12502;i=i+2){
                    //Connect to allrecipes.com website for each recipe
                    Document document = Jsoup.connect("https://www.allrecipes.com/recipe/" + i).get();
                    //Save the title of the document pulled
                    title = document.title();
                    //Temporary fix, display food name without excess
                    title = title.substring(0,(title.length()-16));
                    //Store in array of recipe names
                    recipeNameArr[j] = title;

                    //Pull document HTML for easier scraping
                    String html = Jsoup.connect("https://www.allrecipes.com/recipe/" + i).get().html();
                    Document parsedHtml = Jsoup.parse(html);

                    //Pull the rating of the recipe
                    Elements ratings = document.select("meta[property=og:rating]");
                    rating = ratings.attr("content").toString();
                    //Store the rating
                    prepTimeArr[j] = rating;

                    //Pull the directions of the recipe
                    direction = parsedHtml.select("div.directions--section").text();
                    //Store all info without excess
                    directionsArr[j] = direction.substring(0,direction.indexOf("You"));

                    //Grab the ingredients list by iterating through div, ul, li
                    Elements div = document.select("div.recipe-container-outer");
                    Elements ul = div.select("ul");
                    Elements li = ul.select("li.checkList__line");

                    //Grab the actual ingredient
                    ingredient = li.text();
                    ingredient = ingredient.substring(0,(ingredient.length() - 57));
                    ingredientsArr[j] = ingredient;

                    //increment and repeat on next page.
                    j++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set info into TextView for testing/debugging purposes for now
            //TODO store in database
            TextView txttitle = (TextView) findViewById(R.id.titletxt);
            txttitle.setText("RECIPE NAME: " + recipeNameArr[0] + ", " + "\n" + "INGREDIENTS: " +   ingredientsArr[0] + "\n" + "RATING: " + prepTimeArr[0] + "\n" + "DIRECTIONS: " + directionsArr[0] +"\n");
            TextView txtingredient = (TextView) findViewById(R.id.ingredienttxt);
            txtingredient.setText("RECIPE NAME: " + recipeNameArr[1] + ", " + "\n" + "INGREDIENTS: " +   ingredientsArr[1] + "\n" + "RATING: " + prepTimeArr[1] + "\n" + "DIRECTIONS: " + directionsArr[1] +"\n");
            mProgressDialog.dismiss();
        }
    }



}