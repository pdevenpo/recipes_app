package edu.osu.recipe_app.ui.TodaysPick;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.osu.recipe_app.R;

public class TodaysPickFragment extends Fragment {
    private RecyclerView mTodaysPickRecyclerView;
    private RecyclerView.Adapter mTodaysPickAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mTodaysPickList;
    String[] strings = {"1", "2", "3", "4", "5", "6", "7"};

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        super.onCreateView(inflater, parent, savedInstanceState);
        View v = inflater.inflate(R.layout.todays_pick_fragment, parent, false);


        RecyclerView rv = new RecyclerView(getContext());
        rv = (RecyclerView) v.findViewById(R.id.recipe_list);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new TodaysPickAdapter(strings));
        return v;
    }

    public class TodaysPickAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
        private String[] dataSource;
        public TodaysPickAdapter(String[] dataArgs){
            dataSource = dataArgs;
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new TextView(parent.getContext());
            SimpleViewHolder viewHolder = new SimpleViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            holder.textView.setText(dataSource[position]);
        }

        @Override
        public int getItemCount() {
            return dataSource.length;
        }
    }


    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }





}
