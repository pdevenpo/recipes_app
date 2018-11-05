package edu.osu.recipe_app.ui.TodaysPick;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import edu.osu.recipe_app.R;

public class TodaysPickFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mDataset;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        super.onCreateView(inflater, parent, savedInstanceState);
        View v = inflater.inflate(R.layout.todays_pick_fragment, parent, false);

//        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recipe_list);
//
//        // 2. set layoutManger
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        //populate recyclerview with data
//
//        // 3. create an adapter
//         mAdapter = new MyAdapter(mDataset);
//        // 4. set adapter
//        recyclerView.setAdapter(mAdapter);
//        // 5. set item animator to DefaultAnimator
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return v;
    }



}
