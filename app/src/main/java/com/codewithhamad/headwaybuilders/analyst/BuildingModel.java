package com.codewithhamad.headwaybuilders.analyst;

import android.os.Parcel;
import android.os.Parcelable;

public class BuildingModel implements Parcelable {

    private int buildingImage;
    private int buildingId;
    private String buildingName;
    private int buildingAreaInSqFt;
    private int numbOfFlats;
    private int parkingAreaInSqFt;
    private String shortDetails;
    private boolean isExpanded;

    public BuildingModel(int buildingImage, int buildingId, String buildingName, int buildingAreaInSqFt, int numbOfFlats, int parkingAreaInSqFt, String shortDetails) {
        this.buildingImage= buildingImage;
        this.buildingId= buildingId;
        this.buildingName = buildingName;
        this.buildingAreaInSqFt = buildingAreaInSqFt;
        this.numbOfFlats = numbOfFlats;
        this.parkingAreaInSqFt = parkingAreaInSqFt;
        this.shortDetails= shortDetails;
        isExpanded= false;
    }

    protected BuildingModel(Parcel in) {
        buildingImage = in.readInt();
        buildingId = in.readInt();
        buildingName = in.readString();
        buildingAreaInSqFt = in.readInt();
        numbOfFlats = in.readInt();
        parkingAreaInSqFt = in.readInt();
        shortDetails = in.readString();
        isExpanded = in.readByte() != 0;
    }

    public static final Creator<BuildingModel> CREATOR = new Creator<BuildingModel>() {
        @Override
        public BuildingModel createFromParcel(Parcel in) {
            return new BuildingModel(in);
        }

        @Override
        public BuildingModel[] newArray(int size) {
            return new BuildingModel[size];
        }
    };

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getBuildingAreaInSqFt() {
        return buildingAreaInSqFt;
    }

    public void setBuildingAreaInSqFt(int buildingAreaInSqFt) {
        this.buildingAreaInSqFt = buildingAreaInSqFt;
    }

    public int getNumbOfFlats() {
        return numbOfFlats;
    }

    public void setNumbOfFlats(int numbOfFlats) {
        this.numbOfFlats = numbOfFlats;
    }

    public int getParkingAreaInSqFt() {
        return parkingAreaInSqFt;
    }

    public void setParkingAreaInSqFt(int parkingAreaInSqFt) {
        this.parkingAreaInSqFt = parkingAreaInSqFt;
    }

    public int getBuildingImage() {
        return buildingImage;
    }

    public void setBuildingImage(int buildingImage) {
        this.buildingImage = buildingImage;
    }

    public String getShortDetails() {
        return shortDetails;
    }

    public void setShortDetails(String shortDetails) {
        this.shortDetails = shortDetails;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(buildingImage);
        dest.writeInt(buildingId);
        dest.writeString(buildingName);
        dest.writeInt(buildingAreaInSqFt);
        dest.writeInt(numbOfFlats);
        dest.writeInt(parkingAreaInSqFt);
        dest.writeString(shortDetails);
        dest.writeByte((byte) (isExpanded ? 1 : 0));
    }
}
