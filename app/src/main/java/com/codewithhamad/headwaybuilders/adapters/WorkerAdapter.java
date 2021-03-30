package com.codewithhamad.headwaybuilders.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithhamad.headwaybuilders.R;
import com.codewithhamad.headwaybuilders.analyst.BuildingDetailsFragment;
import com.codewithhamad.headwaybuilders.analyst.WorkerDetailsFragment;
import com.codewithhamad.headwaybuilders.models.WorkerModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> {

    Context context;
    ArrayList<WorkerModel> workers;

    public WorkerAdapter(Context context, ArrayList<WorkerModel> workers){
        this.context= context;
        this.workers= workers;
        Log.d("check", "WorkerAdapter: in constructor");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.analyst_sample_worker, parent, false);
        Log.d("check", "onCreateViewHolder:");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("check", "onBindViewHolder: started");
        WorkerModel workerModel= workers.get(position);

        if(workerModel != null) {
            holder.workerId.setText(workerModel.getWorkerId() + "");
            holder.workerName.setText(workerModel.getWorkerName());

            Log.d("check", "onBindViewHolder: " + workerModel.getWorkerName());

            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, workers.get(position).getWorkerName() + " selected", Toast.LENGTH_SHORT).show();

                    // sending data/clickedWorkerModel to WorkerDetailsFragment
                    Gson gson = new Gson();
                    String jsonItem = gson.toJson(workerModel);
                    Bundle bundle = new Bundle();
                    bundle.putString("worker", jsonItem);

                    // navigating to WorkerDetailsFragment
                    WorkerDetailsFragment workerDetailsFragment= new WorkerDetailsFragment();
                    workerDetailsFragment.setArguments(bundle);
                    AppCompatActivity appCompatActivity= (AppCompatActivity) v.getContext();
                    appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.analystContainerFrameLayout, workerDetailsFragment).commit();

                }
            });
        }
        else{
            Toast.makeText(context, "worker model is null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return workers.size();
    }

    // all the views of analyst_sample_worker layout are initialized here
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView workerId, workerName;
        CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Log.d("check", "ViewHolder: started");

            workerId= itemView.findViewById(R.id.workerIdTxt);
            workerName= itemView.findViewById(R.id.workerNameTxt);
            parent= itemView.findViewById(R.id.workerParentCardView);

            Log.d("check", "ViewHolder: end");

        }
    }
}
