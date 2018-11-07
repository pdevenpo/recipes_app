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
import android.widget.Toast;

import java.util.List;

import edu.osu.recipe_app.MainActivity;
import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.MyAccount.UserData.User;
import edu.osu.recipe_app.ui.MyAccount.UserData.UserRepository;

public class AccountLoginFragment extends Fragment {

    private final String TAG = "LoginActivityTag";
    private EditText mEmail;
    private EditText mPassword;
    private User mUser;
    private Button mLoginButton;
    private Button mCreateAccountButton;
    private Button mListUsersButton;
    private Button mEditAccountButton;

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
        //mListUsersButton = (Button) v.findViewById(R.id.listUsersButton);
        mEditAccountButton = v.findViewById(R.id.EditAccount);

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

        mEditAccountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(!mEmail.getText().toString().equals("")){
                    startActivity(new Intent(getActivity(), AccountEditActivity.class));
//                    if(mUserRepository.findUserByEmail(mUser.getEmail()) != null){ //this doesn't seem to be working
                        Intent intent = new Intent(getActivity(), AccountEditActivity.class);
                        intent.putExtra("CurrentUserEmail",mEmail.getText().toString());
                        startActivity(intent);
//                    } else {
//                        Toast.makeText(getContext(), "Must enter an email associated with an account", Toast.LENGTH_SHORT).show();
//
//                    }

                } else {
                    Toast.makeText(getContext(), "Email must not be blank", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(mUser.getEmail() != null && mUser.getPassword() != null){
                    User retrievedUser = mUserRepository.findUserByEmail(mUser.getEmail());
                    if(retrievedUser != null && retrievedUser.getPassword().equals(mUser.getPassword())){
                        //go back to the main screen
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("CurrentUser", retrievedUser.getName());
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "No user found with that email and password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "All fields must not be blank", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        mListUsersButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//
//                List<User> listUsers = mUserRepository.listUsers();
//
//                for(int i = 0; i < listUsers.size(); i++){
//                    Log.d(TAG, "User " + i + "- Name: " + listUsers.get(i).getName() + " Email: " + listUsers.get(i).getEmail() + " Password: " + listUsers.get(i).getPassword());
//                }
//            }
//        });

        return v;
    }
}
