package edu.osu.recipe_app.ui.MyAccount;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.osu.recipe_app.MainActivity;
import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.MyAccount.UserData.User;
import edu.osu.recipe_app.ui.MyAccount.UserData.UserRepository;

public class AccountNewFragmentLandscape extends Fragment {
    private EditText mEmail;
    private EditText mPassword;
    private EditText mName;
    private User mUser;
    private Button mNewAccountButton;
    private Button mDeleteUserButton;
    private UserRepository mUserRepository;

    public AccountNewFragmentLandscape() {
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
        View v = inflater.inflate(R.layout.my_account_new_fragment_landscape, parent, false);

        mEmail = v.findViewById(R.id.emailEntry);
        mPassword = v.findViewById(R.id.passwordEntry);
        mName = v.findViewById(R.id.nameEntry);
        mNewAccountButton = (Button) v.findViewById(R.id.newAccountButton);
        //mDeleteUserButton = (Button) v.findViewById(R.id.deleteUserButton);

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

        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUser.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mNewAccountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(mUser.getEmail() != null && mUser.getPassword() != null && mUser.getName() != null){
                    if(mUserRepository.findUserByEmail(mUser.getEmail()) != null) {
                        Toast.makeText(getContext(), "Error: User already exists with that email", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "User added", Toast.LENGTH_SHORT).show();

                        mUserRepository.insertUser(mEmail.getText().toString(), mPassword.getText().toString(), mName.getText().toString());

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("CurrentUser", mUser.getName());
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getContext(), "All fields must not be blank", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        mDeleteUserButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                if(mUser.getEmail() != null){
//                    if(mUserRepository.findUserByEmail(mUser.getEmail()) != null){
//                        mUserRepository.deleteUser(mUser.getEmail());
//                        Toast.makeText(getContext(), "User deleted", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getActivity(), MainActivity.class);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(getContext(), "No user found with that email", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(getContext(), "Please enter an email first", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        return v;
    }
}
