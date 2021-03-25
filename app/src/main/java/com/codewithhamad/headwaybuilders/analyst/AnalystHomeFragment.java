package com.codewithhamad.headwaybuilders.analyst;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codewithhamad.headwaybuilders.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AnalystHomeFragment extends Fragment {

    RecyclerView recyclerView;
    BuildingAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_analyst_home, container, false);

        recyclerView= view.findViewById(R.id.homeFragRecView);

        ArrayList<BuildingModel> buildings= new ArrayList<>();

        buildings.add(new BuildingModel(R.drawable.one, 123,
                "Hotel", 1800, 12, 200, "It is a BBQ Hotel"));

        buildings.add(new BuildingModel(R.drawable.two, 123,
                "Hospital", 1200, 10, 100, "A Semi-govt hospital"));

        buildings.add(new BuildingModel(R.drawable.three, 123,
                "School", 4000, 8, 400, "Provides Quality Education"));

        buildings.add(new BuildingModel(R.drawable.four, 123,
                "Showroom", 2000, 14, 300, "A car showroom which provides best quality models"));


        Log.d("image", "onCreateView: " + buildings.get(2).getBuildingImage());

        adapter= new BuildingAdapter(getContext(), buildings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }
}