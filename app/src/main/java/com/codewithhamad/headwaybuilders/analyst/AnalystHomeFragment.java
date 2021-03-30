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
import com.codewithhamad.headwaybuilders.adapters.BuildingAdapter;
import com.codewithhamad.headwaybuilders.adapters.WorkerAdapter;
import com.codewithhamad.headwaybuilders.databasehelper.DatabaseHelper;
import com.codewithhamad.headwaybuilders.models.BuildingModel;
import com.codewithhamad.headwaybuilders.models.WorkerModel;

import java.util.ArrayList;

public class AnalystHomeFragment extends Fragment {

    private RecyclerView workersRecView, buildingsRecView;
    private BuildingAdapter buildingAdapter;
    private WorkerAdapter workerAdapter;
    private DatabaseHelper databaseHelper;
    private ArrayList<BuildingModel> buildings= new ArrayList<>();
    private ArrayList<WorkerModel> workers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_analyst_home, container, false);

        buildingsRecView= view.findViewById(R.id.homeFragBuildingsRecView);
        workersRecView= view.findViewById(R.id.homeFragWorkersRecView);

        // retrieving data from database and setting adapters
        try{
            databaseHelper= new DatabaseHelper(getContext());
            buildings= databaseHelper.getAllFromBuildingsTable();
            workers= databaseHelper.getAllFromWorkersTable();

            if(buildings != null){
                buildingAdapter = new BuildingAdapter(getContext(), buildings);
                buildingsRecView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                buildingsRecView.setAdapter(buildingAdapter);
            }

            if(workers != null){

                workerAdapter= new WorkerAdapter(getContext(), workers);
                workersRecView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
                workersRecView.setAdapter(workerAdapter);
            }

        }
        catch (Exception e){
            Log.d("check", "onCreateView: error " + e.getMessage());
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        return view;
    }


}