package com.codewithhamad.headwaybuilders.analyst;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codewithhamad.headwaybuilders.R;
import com.codewithhamad.headwaybuilders.databasehelper.DatabaseHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AnalystHomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private BuildingAdapter adapter;
    private DatabaseHelper databaseHelper;
    private ArrayList<BuildingModel> buildings= new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_analyst_home, container, false);

        recyclerView= view.findViewById(R.id.homeFragRecView);

        // retrieving data from database (buildings table)
        try{
            databaseHelper= new DatabaseHelper(getContext());
            buildings= databaseHelper.getAllBuildingsFromDatabase();
            if(buildings != null){
                adapter= new BuildingAdapter(getContext(), buildings);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }
        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        return view;
    }


}