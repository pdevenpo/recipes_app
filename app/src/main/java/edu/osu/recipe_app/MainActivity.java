package edu.osu.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_Button(View v) {
        switch (v.getId()) {

            case R.id.MyRecipesButton:
                Intent myRecipesIntent = new Intent (this, MyRecipesActivity.class);
                startActivity(myRecipesIntent);
                break;

            case R.id.FindStoreButton:
                Intent findStoreIntent = new Intent (this, FindStoreActivity.class);
                startActivity(findStoreIntent);
                break;

            case R.id.TimerButton:
                Intent timerIntent = new Intent (this, TimerActivity.class);
                startActivity(timerIntent);
                break;

            case R.id.TodaysPickButton:
                Intent todaysPickIntent = new Intent (this, TodaysPickActivity.class);
                startActivity(todaysPickIntent);
                break;

            case R.id.MyAccountButton:
                Intent myAccountIntent = new Intent (this, MyAccountActivity.class);
                startActivity(myAccountIntent);
                break;

            case R.id.FavoritesButton:
                Intent favoritesIntent = new Intent (this, FavoritesActivity.class);
                startActivity(favoritesIntent);
                break;

            default:
                break;
        }
    }
}
