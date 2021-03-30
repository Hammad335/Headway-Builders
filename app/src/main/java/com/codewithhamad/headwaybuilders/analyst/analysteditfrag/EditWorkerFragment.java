package com.codewithhamad.headwaybuilders.analyst.analysteditfrag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.codewithhamad.headwaybuilders.R;

public class EditWorkerFragment extends Fragment {
    EditText workerId, buildingId, workerName, salary, job;
    Button updateBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_worker, container, false);

        // init views
        workerId = view.findViewById(R.id.workerId);
        buildingId = view.findViewById(R.id.addWorkerBuildingId);
        workerName = view.findViewById(R.id.workerName);
        salary = view.findViewById(R.id.salary);
        job = view.findViewById(R.id.job);
        updateBtn = view.findViewById(R.id.updtWorkerAddBtn);


        return view;


    }
}
