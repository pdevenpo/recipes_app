package edu.osu.recipe_app;

import java.io.IOException;
import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyRecipeActivity extends Activity{

    // URL Address
    String url = "https://www.allrecipes.com/recipe/162760/fluffy-pancakes/";
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
                // Execute Title AsyncTask
                new Title().execute();
            }
        });



    }

    // Title AsyncTask
    private class Title extends AsyncTask<Void, Void, Void> {
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
                // Connect to the web site
                //Document document = Jsoup.connect(url).get();
                // Get the html document title
                //title = document.title();
                int j = 0;
                for(int i = 12494; i < 12502;i=i+2){
                    Document document = Jsoup.connect("https://www.allrecipes.com/recipe/" + i).get();
                    title = document.title();
                    title = title.substring(0,(title.length()-16));
                    recipeNameArr[j] = title;

                    String html = Jsoup.connect("https://www.allrecipes.com/recipe/" + i).get().html();
                    Document parsedHtml = Jsoup.parse(html);
                    //String prepTime = parsedHtml.body().text();
                    //String prepTime = parsedHtml.select("p").first();
                    //String prepTime = html.("div:contains(prep)").text();
                    //String prepTime = parsedHtml.select("div.directions--section").text();
                    Elements ratings = document.select("meta[property=og:rating]");
                    rating = ratings.attr("content").toString();
                    prepTimeArr[j] = rating;

                    direction = parsedHtml.select("div.directions--section").text();
//                    Elements directions = document.select("div.directions--section");
//                    direction = directions.attr("content");
                    directionsArr[j] = direction.substring(0,direction.indexOf("You"));

                    //grab the ingredients
                    Elements div = document.select("div.recipe-container-outer");
                    Elements ul = div.select("ul");
                    Elements li = ul.select("li.checkList__line");

                    ingredient = li.text();
                    ingredient = ingredient.substring(0,(ingredient.length() - 57));
                    ingredientsArr[j] = ingredient;


                    j++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set title into TextView
            TextView txttitle = (TextView) findViewById(R.id.titletxt);
            txttitle.setText("RECIPE NAME: " + recipeNameArr[0] + ", " + "\n" + "INGREDIENTS: " +   ingredientsArr[0] + "\n" + "RATING: " + prepTimeArr[0] + "\n" + "DIRECTIONS: " + directionsArr[0] +"\n");
            TextView txtingredient = (TextView) findViewById(R.id.ingredienttxt);
            txtingredient.setText("RECIPE NAME: " + recipeNameArr[1] + ", " + "\n" + "INGREDIENTS: " +   ingredientsArr[1] + "\n" + "RATING: " + prepTimeArr[1] + "\n" + "DIRECTIONS: " + directionsArr[1] +"\n");
            mProgressDialog.dismiss();
        }
    }



}