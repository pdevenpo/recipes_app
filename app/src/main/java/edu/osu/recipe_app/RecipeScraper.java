package edu.osu.recipe_app;

import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;


public class RecipeScraper {
    int recipeAmount = 50;
    private String[] recipeNameArray = new String[recipeAmount];

    public String[] getRecipeNameArray(){
        return recipeNameArray;
    }

    public void setRecipeNameArray(String[] recipeNameArray){
        this.recipeNameArray = recipeNameArray;
    }

    // Title AsyncTask
    public class Title extends AsyncTask<Void, Void, Void> {
        String title;
        ProgressDialog mProgressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            title = "boob";

            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                Document document = Jsoup.connect("https://www.allrecipes.com/recipe/162760/").get();
                // Get the html document title
                title = document.title();
            } catch (IOException e) {
                e.printStackTrace();
            }
            title = "boob";
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set title into TextView
                title = "tit";
//            TextView txttitle = (TextView) findViewById(R.layout.my_recipes_activity);
//            txttitle.setText(title);
//            mProgressDialog.dismiss();
        }






//    public void webScrape(){

        //create a new thread for performance reasoning
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final StringBuilder builder = new StringBuilder();
//                String str = "foodRecipeName";
//                //try catch block to avoid corrupted pages
//                try{
                    //TODO app breaks when jsoup.connect is ran, work on this. App works fine commented out
//                    //pull the document to parse
//                    Document doc = Jsoup.connect("https://www.allrecipes.com/recipe/162760/").get();
//                    //parse
//                    //store food names
//                    Elements foodNames = doc.select("h1");
//
//                    for(Element foodName:foodNames){
//                        builder.append("\n").append("Recipe: ").append(foodName.attr("h1")).append("\n");
//                        recipeNameArray[0] = foodName.attr("h1");
//                        setRecipeNameArray(recipeNameArray);
//                    }
//
//
//
//                    //throw exception of unreadable page
               // }catch (IOException e){
                    //print out error code web page responds with
                   //builder.append("Error: ").append(e.getMessage()).append("\n");
                }



//            }
//        }).start();


}
