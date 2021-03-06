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
import android.widget.TextView;
import android.widget.Toast;

import edu.osu.recipe_app.MainActivity;
import edu.osu.recipe_app.R;
import edu.osu.recipe_app.ui.MyAccount.UserData.User;
import edu.osu.recipe_app.ui.MyAccount.UserData.UserRepository;


public class AccountEditFragment extends Fragment {

    private final String TAG = "LoginEditTag";
    private TextView mUserEmail;
    private EditText mName;
    private EditText mPassword;
    private User mUser;
    private String mDefaultEmail = "Account";

    private String mNewName;
    private String mNewPassword;

    private Button mUpdateAccountButton;


    private UserRepository mUserRepository;


    public AccountEditFragment() {

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
        View v = inflater.inflate(R.layout.my_account_edit_fragment, parent, false);
        mName = v.findViewById(R.id.nameUpdate);
        mPassword = v.findViewById(R.id.passwordUpdate);
        mUserEmail = v.findViewById(R.id.emailEdit);
        mUpdateAccountButton = v.findViewById(R.id.updateAccount);

        String email = getArguments().getString("CurrentUserEmail");
        if(email != null){
            mDefaultEmail = email;
            mUser.setEmail(mDefaultEmail);
        }
        mUserEmail.setText(mDefaultEmail);

        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mNewPassword = charSequence.toString();
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
                mNewName = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        mUpdateAccountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(mNewName != null && mNewPassword != null){
                    Toast.makeText(getContext(), "Account Updated", Toast.LENGTH_SHORT).show();

                    mUser.setName(mNewName);
                    mUser.setPassword(mNewPassword);

                    mUserRepository.updateUser(mUser);

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("CurrentUser", mUser.getName());
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Fields must not be blank", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return v;
    }
}
