package edu.osu.recipe_app.ui.MyAccount;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import edu.osu.recipe_app.R;

public class AccountEditActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account_edit_activity);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.AccountEditContainer);

        String mCurrentUserEmail = "Account";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            mCurrentUserEmail = extras.getString("CurrentUserEmail");
        }


        if(fragment == null){
            fragment = new AccountEditFragment();
            Bundle args = new Bundle();
            args.putString("CurrentUserEmail", mCurrentUserEmail);
            fragment.setArguments(args);
            fm.beginTransaction()
                    .add(R.id.AccountEditContainer, fragment)
                    .commit();
        }
    }
}
