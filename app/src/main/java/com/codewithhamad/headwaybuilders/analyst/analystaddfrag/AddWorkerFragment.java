package com.codewithhamad.headwaybuilders.analyst.analystaddfrag;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codewithhamad.headwaybuilders.R;
import com.codewithhamad.headwaybuilders.databasehelper.DatabaseHelper;
import com.codewithhamad.headwaybuilders.models.BuildingModel;

import java.util.ArrayList;

public class AddWorkerFragment extends Fragment {

    EditText workerId, buildingId, workerName, salary, job;
    Button addBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_worker, container, false);

        // init views
        workerId= view.findViewById(R.id.workerId);
        buildingId= view.findViewById(R.id.addWorkerBuildingId);
        workerName= view.findViewById(R.id.workerName);
        salary= view.findViewById(R.id.salary);
        job= view.findViewById(R.id.job);
        addBtn= view.findViewById(R.id.addWorkerAddBtn);


        // checking whether building id is valid or not
        buildingId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int building_id;

                try {
                    if (buildingId.getText().length() != 0) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

                        building_id = Integer.parseInt(buildingId.getText().toString());
                        ArrayList<BuildingModel> buildings = databaseHelper.getAllBuildingsFromDatabase();

                        if (buildings != null) {
                            boolean isValid = false;
                            for (BuildingModel b : buildings) {
                                if (building_id == b.getBuildingId()) {
                                    isValid = true;
                                    break;
                                }
                            }

                            if (isValid) {
                                buildingId.setTextColor(getResources().getColor(R.color.green));
                            } else {
                                buildingId.setTextColor(getResources().getColor(R.color.red));
                                buildingId.setError("Building does not exist.");
                            }
                        }
                    }
                }
                catch(Exception e){
                    Toast.makeText(getContext(), "Invalid input.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}