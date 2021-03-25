package com.codewithhamad.headwaybuilders.analyst;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codewithhamad.headwaybuilders.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class BuildingDetailsFragment extends Fragment {

    private static final String TAG = "buildingDetails";
    ImageView imageView;
    TextView buildingId, buildingName, buildingArea, noOfFlats, parkingArea, details;
    BuildingModel buildingModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_building_details, container, false);

        // init views
        imageView= view.findViewById(R.id.buildngDetailsImageView);
        buildingId= view.findViewById(R.id.buildingDetailsBId);
        buildingName= view.findViewById(R.id.buildingDetailsBName);
        buildingArea= view.findViewById(R.id.buildingDetailsBArea);
        noOfFlats= view.findViewById(R.id.buildingDetailsBNoOfFlats);
        parkingArea= view.findViewById(R.id.buildingDetailsBParkingArea);
        details= view.findViewById(R.id.buildingDetailsBShortDetails);

        // receiving data from BuildingAdapter
        Bundle bundle= this.getArguments();

        if(bundle!=null) {
            String jsonItem= bundle.getString("building");
            if(jsonItem != null){
                Gson gson = new Gson();
                Type type = new TypeToken<BuildingModel>() {}.getType();
                buildingModel = gson.fromJson(jsonItem, type);

                if(buildingModel != null){

                    // setting data to views
                     imageView.setImageResource(buildingModel.getBuildingImage());
                     buildingId.setText(buildingModel.getBuildingId() + "");
                     buildingName.setText(buildingModel.getBuildingName());
                     buildingArea.setText(buildingModel.getBuildingAreaInSqFt() + "");
                     noOfFlats.setText(buildingModel.getNumbOfFlats() + "");
                     parkingArea.setText(buildingModel.getParkingAreaInSqFt() + "");
                     details.setText(buildingModel.getShortDetails());

                }

            }
        }



        return view;

    }
}