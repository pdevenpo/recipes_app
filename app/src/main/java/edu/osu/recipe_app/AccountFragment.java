package edu.osu.recipe_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AccountFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        super.onCreateView(inflater, parent, savedInstanceState);
        View v = inflater.inflate(R.layout.my_account_fragment, parent, false);

        //example from textbook

//        mTitleField = v.findViewById(R.id.crime_title);
//
//
//        mTitleField.addTextChangedListener(new TextWatcher(){
//            public void onTextChanged(CharSequence c, int start, int before, int count){
//                mCrime.setTitle(c.toString());
//            }
//            public void beforeTextChanged(CharSequence c, int start, int count, int after){
//                //this space intentionally left blank
//            }
//            public void afterTextChanged(Editable c) {
//                //this space intentionally left blank
//            }
//        });

        return v;
    }
}
