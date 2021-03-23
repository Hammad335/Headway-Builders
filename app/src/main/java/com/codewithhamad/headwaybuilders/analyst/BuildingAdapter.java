package com.codewithhamad.headwaybuilders.analyst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.codewithhamad.headwaybuilders.R;
import java.util.ArrayList;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.ViewHolder> {

    Context context;
    ArrayList<BuildingModel> buildings;

    public BuildingAdapter(Context context, ArrayList<BuildingModel> buildings){
        this.context= context;
        this.buildings= buildings;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView buildingImage, downArrow, upArrow;
        TextView buildingName, buildingShortDesc;
        RelativeLayout expandedRelLayout;
        CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            buildingImage= itemView.findViewById(R.id.analystBuildingImageView);
            downArrow= itemView.findViewById(R.id.buildingCardViewDownArrow);
            upArrow= itemView.findViewById(R.id.buildingCardViewUpArrow);
            buildingName= itemView.findViewById(R.id.analystBuildingName);
            buildingShortDesc= itemView.findViewById(R.id.buildingShortDesc);
            expandedRelLayout= itemView.findViewById(R.id.expandedRelLayout);
            parent= itemView.findViewById(R.id.analystParentCardView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.analyst_sample_building, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BuildingModel buildingModel= buildings.get(position);

        holder.buildingImage.setImageResource(buildingModel.getBuildingImage());
        holder.buildingName.setText(buildingModel.getBuildingName());
        holder.buildingShortDesc.setText(buildingModel.getShortDetails());

        // TODO: 3/24/2021 navigate user to building details fragment
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, buildings.get(position).getBuildingName() + " selected", Toast.LENGTH_SHORT).show();
            }
        });

        // expanding cardView on clicking down arrow
        holder.downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(holder.parent);
                holder.expandedRelLayout.setVisibility(View.VISIBLE);
                holder.downArrow.setVisibility(View.GONE);
            }
        });

        // contracting cardView on clicking up arrow
        holder.upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.expandedRelLayout.setVisibility(View.GONE);
                holder.downArrow.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }


}
