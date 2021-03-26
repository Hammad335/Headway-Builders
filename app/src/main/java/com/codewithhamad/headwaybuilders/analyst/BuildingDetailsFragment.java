package com.codewithhamad.headwaybuilders.analyst;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codewithhamad.headwaybuilders.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class BuildingDetailsFragment extends Fragment {

    private static final String TAG = "buildingDetails";
    ImageView imageView, backBtn;
    TextView buildingId, buildingName, buildingType, buildingLocation, buildingArea, noOfFlats, noOfFloors, noOfLifts, parkingArea, details;
    BuildingModel buildingModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_building_details, container, false);

        // init views
        backBtn= view.findViewById(R.id.backBtn);
        imageView= view.findViewById(R.id.buildngDetailsImageView);
        buildingId= view.findViewById(R.id.buildingDetailsBId);
        buildingName= view.findViewById(R.id.buildingDetailsBName);
        buildingType= view.findViewById(R.id.buildingDetailsBType);
        buildingLocation= view.findViewById(R.id.buildingDetailsBLocation);
        buildingArea= view.findViewById(R.id.buildingDetailsBArea);
        noOfFlats= view.findViewById(R.id.buildingDetailsBNoOfFlats);
        noOfFloors= view.findViewById(R.id.buildingDetailsBNoOfFloors);
        noOfLifts= view.findViewById(R.id.buildingDetailsBNoOfLifts);
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
                     imageView.setImageBitmap(buildingModel.getBuildingImage());
                     buildingId.setText(buildingModel.getBuildingId() + "");
                     buildingName.setText(buildingModel.getBuildingName());
                     buildingType.setText(buildingModel.getBuildingType());
                     buildingLocation.setText(buildingModel.getBuildingLocation());
                     buildingArea.setText(buildingModel.getBuildingAreaInSqFt() + "");


                    if(buildingModel.getNumbOfFlats() == -1)
                        noOfFlats.setText("NULL");
                    else
                        noOfFlats.setText(buildingModel.getNumbOfFlats() + "");

                    if(buildingModel.getNumbOfFloors() == -1)
                        noOfFloors.setText("NULL");
                    else
                        noOfFloors.setText(buildingModel.getNumbOfFloors() + "");

                    if(buildingModel.getNumbOfLifts() == -1)
                        noOfLifts.setText("NULL");
                    else
                        noOfLifts.setText(buildingModel.getNumbOfLifts() + "");


                    parkingArea.setText(buildingModel.getParkingAreaInSqFt() + "");
                    details.setText(buildingModel.getShortDetails());

                }

            }
        }



        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // navigating back to the AnalystHomeFragment
                AppCompatActivity appCompatActivity= (AppCompatActivity) v.getContext();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.analystContainerFrameLayout, new AnalystHomeFragment()).commit();
            }
        });


        return view;

    }
}