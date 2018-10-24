package edu.osu.recipe_app.ui.MyAccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.UserData.User;
import edu.osu.recipe_app.ui.UserData.UserRepository;

public class AccountLoginFragment extends Fragment {

    private final String TAG = "LoginActivityTag";
    private EditText mEmail;
    private EditText mPassword;
    private User mUser;
    private Button mLoginButton;
    private Button mCreateAccountButton;
    private Button mListUsersButton;

    private UserRepository mUserRepository;


    public AccountLoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mUser = new User();
        mUserRepository = new UserRepository(this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        super.onCreateView(inflater, parent, savedInstanceState);
        View v = inflater.inflate(R.layout.my_account_login_fragment, parent, false);

        mEmail = v.findViewById(R.id.emailLoginEntry);
        mPassword = v.findViewById(R.id.passwordLoginEntry);
        mLoginButton = (Button) v.findViewById(R.id.loginButton);
        mCreateAccountButton = (Button) v.findViewById(R.id.createAccountButton);
        mListUsersButton = (Button) v.findViewById(R.id.listUsersButton);

        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUser.setEmail(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUser.setPassword(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        mCreateAccountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            startActivity(new Intent(getActivity(), AccountNewActivity.class));
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

//                if(mUser.getEmail() != null && mUser.getPassword() != null){
//                    //Toast.makeText(getContext(), "User added", Toast.LENGTH_SHORT).show();
//                    //Log in user if they currently exist in the database
//
//                    //go back to the main screen
//                    Intent intent = new Intent(getActivity(), MainActivity.class);
//                    intent.putExtra("CurrentUser", mUser.getName());
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(getContext(), "All fields must not be blank", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        mListUsersButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                List<User> listUsers = mUserRepository.listUsers();

                for(int i = 0; i < listUsers.size(); i++){
                    Log.d(TAG, "User " + i + "- Name: " + listUsers.get(i).getName() + " Email: " + listUsers.get(i).getEmail() + " Password: " + listUsers.get(i).getPassword());
                }
            }
        });

        return v;
    }
}
