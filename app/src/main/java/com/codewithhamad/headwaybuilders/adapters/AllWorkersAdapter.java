package com.codewithhamad.headwaybuilders.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.codewithhamad.headwaybuilders.R;
import com.codewithhamad.headwaybuilders.models.WorkerModel;
import java.util.ArrayList;

public class AllWorkersAdapter extends RecyclerView.Adapter<AllWorkersAdapter.ViewHolder> {

    Context context;
    ArrayList<WorkerModel> workers;
    String callingActivity;

    public AllWorkersAdapter(Context context, ArrayList<WorkerModel> workers, String callingActivity){
        this.context= context;
        this.workers= workers;
        this.callingActivity= callingActivity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_worker_all_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        WorkerModel workerModel= workers.get(position);

        if(workerModel != null) {
            holder.id.setText(workerModel.getWorkerId() + "");
            holder.name.setText(workerModel.getWorkerName());
            holder.sal.setText(workerModel.getSalary() + "");
            holder.job.setText(workerModel.getJob());
            holder.buildingId.setText(workerModel.getBuildingId() + "");
        }

    }


    @Override
    public int getItemCount() {
        return workers.size();
    }

    public void setWorkers(ArrayList<WorkerModel> workers){
        this.workers= workers;
        notifyDataSetChanged();
    }

    // all the views of sample_worker_all_details layout are initialized here
    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView id, name, sal, job, buildingId;
        CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id= itemView.findViewById(R.id.txtWorkerId);
            name= itemView.findViewById(R.id.txtWorkerName);
            sal= itemView.findViewById(R.id.workerSalaryTxt);
            job= itemView.findViewById(R.id.workerJobTxt);
            buildingId= itemView.findViewById(R.id.workerBuildingId);
            parent= itemView.findViewById(R.id.workerAllDetailsParentCardView);

        }
    }
}
