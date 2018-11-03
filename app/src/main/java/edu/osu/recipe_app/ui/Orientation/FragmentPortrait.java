package edu.osu.recipe_app.ui.Orientation;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.Favorites.FavoritesActivity;
import edu.osu.recipe_app.ui.FindStore.FindStoreActivity;
import edu.osu.recipe_app.ui.MyAccount.AccountLoginActivity;
import edu.osu.recipe_app.ui.MyRecipes.MyRecipesActivity;
import edu.osu.recipe_app.ui.Timer.TimerActivity;
import edu.osu.recipe_app.ui.TodaysPick.TodaysPickActivity;

public class FragmentPortrait extends Fragment {
    private final String s = "FragmentPortrait";


    private TextView mWelcomeMessage;
    private String mCurrentUser = "User3";
    private final String LOGIN_USER = "User2";
    private String mHeaderWelcome = "Welcome, ";


    private Button mMyRecipes;
    private Button mFindStore;
    private Button mTimer;
    private Button mTodaysPick;
    private Button mMyAccount;
    private Button mFavorites;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.portrait_fragment, container, false);

        if(savedInstanceState != null){
            mCurrentUser = savedInstanceState.getString(LOGIN_USER, "User");
        }

        String newUser = getArguments().getString("CurrentUser");
        if(newUser != null){
            mCurrentUser = newUser;
        }


        mWelcomeMessage = v.findViewById(R.id.userWelcome);
        mWelcomeMessage.setText(mHeaderWelcome + mCurrentUser + "!");


        mMyRecipes = v.findViewById(R.id.MyRecipesButton);
        mFindStore = v.findViewById(R.id.FindStoreButton);
        mTimer = v.findViewById(R.id.TimerButton);
        mTodaysPick = v.findViewById(R.id.TodaysPickButton);
        mMyAccount = v.findViewById(R.id.MyAccountButton);
        mFavorites = v.findViewById(R.id.FavoritesButton);

        mMyRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyRecipesActivity.class);
                startActivity(intent);
            }
        });
        mFindStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FindStoreActivity.class);
                startActivity(intent);
            }
        });
        mTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TimerActivity.class);
                startActivity(intent);
            }
        });
        mTodaysPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TodaysPickActivity.class);
                startActivity(intent);
            }
        });
        mMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AccountLoginActivity.class);
                startActivity(intent);
            }
        });
        mFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FavoritesActivity.class);
                startActivity(intent);
            }
        });

        return v;

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(LOGIN_USER, mCurrentUser);
    }
}
