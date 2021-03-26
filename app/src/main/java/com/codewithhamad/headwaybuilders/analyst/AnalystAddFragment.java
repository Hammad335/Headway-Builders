package com.codewithhamad.headwaybuilders.analyst;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codewithhamad.headwaybuilders.R;
import com.codewithhamad.headwaybuilders.databasehelper.DatabaseHelper;

import static android.app.Activity.RESULT_OK;

public class AnalystAddFragment extends Fragment {

    ImageView buildingImage;
    Spinner spinnerBuildingTypes;
    EditText buildingId, buildingName, buildingArea, numberOfFlats, numberOfFloors, numberOfLifts, parkingArea, shortDetails, buildingLocation;
    Button addBtn;

    private static final int PICK_IMAGE_REQUEST= 1;
    private Uri imageFilePath;
    private Bitmap bitmapImageToStore;

    DatabaseHelper databaseHelper;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_analyst_add, container, false);

        // init views
        buildingImage= view.findViewById(R.id.buildingMannualImage);
        spinnerBuildingTypes= view.findViewById(R.id.buildingTypeSpinner);
        buildingId= view.findViewById(R.id.buildingId);
        buildingName= view.findViewById(R.id.buildingName);
        buildingArea= view.findViewById(R.id.buildingArea);
        numberOfFlats= view.findViewById(R.id.numbOfFlats);
        numberOfFloors= view.findViewById(R.id.numbOfFloors);
        numberOfLifts= view.findViewById(R.id.numbOfLifts);
        parkingArea= view.findViewById(R.id.parkingArea);
        shortDetails= view.findViewById(R.id.shortDetails);
        buildingLocation= view.findViewById(R.id.location);
        addBtn= view.findViewById(R.id.addBtn);

        // on clicking imageView, navigating user to the gallery
        // and setting selected image to imageView
        buildingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage(v);
            }
        });



        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // temp vars used below
                int id;
                String name;
                int bArea,flats, floors, lifts, pArea;
                String details, location, buildingType;

                try {
                    databaseHelper = new DatabaseHelper(getContext());

                    if(buildingId.getText().length()==0){
                        buildingId.setError("Id is required.");
                    }
                    else if(buildingName.getText().length()==0){
                        buildingName.setError("Name is required.");
                    }
                    else if(buildingArea.getText().length()==0){
                        buildingArea.setError("Building Area is required.");
                    }
                    else if(parkingArea.getText().length()==0){
                        parkingArea.setError("Parking Area is required.");
                    }
                    else if(buildingLocation.getText().length()==0){
                        buildingLocation.setError("Location is required.");
                    }

                    id = Integer.parseInt(buildingId.getText().toString());
                    name= buildingName.getText().toString();
                    bArea= Integer.parseInt(buildingArea.getText().toString());

                    if(numberOfFlats.getText().length()==0)
                        flats= -1;
                    else
                        flats = Integer.parseInt(numberOfFlats.getText().toString());

                    if(numberOfFloors.getText().length()==0)
                        floors= -1;
                    else
                        floors= Integer.parseInt(numberOfFloors.getText().toString());

                    if(numberOfLifts.getText().length()==0)
                        lifts= -1;
                    else
                        lifts= Integer.parseInt(numberOfLifts.getText().toString());

                    pArea= Integer.parseInt(parkingArea.getText().toString());
                    buildingType= spinnerBuildingTypes.getSelectedItem().toString();

                    if(shortDetails.getText().length()==0)
                        details= "No Details Provided.";
                    else
                        details= shortDetails.getText().toString();

                    location= buildingLocation.getText().toString();


                    BuildingModel record= new BuildingModel(bitmapImageToStore, buildingType, id, name, bArea, flats, floors,
                            lifts, pArea, details, location);

                    databaseHelper.insertRecord(record);

                }
                catch (Exception e){
                    Toast.makeText(getContext(), "Fill the required fields properly.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void chooseImage(View objectView){

        try{

            // navigating to images/gallery
            Intent intent= new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);

        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    // setting image to imageView on Runtime after image is chosen
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        try {
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

                // image path in form of uri
                imageFilePath= data.getData();

                // converting image into bitmap
                bitmapImageToStore = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageFilePath);

                // setting image to imageView
                buildingImage.setImageBitmap(bitmapImageToStore);
            }
        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}