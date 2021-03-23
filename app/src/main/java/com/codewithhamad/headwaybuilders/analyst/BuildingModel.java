package com.codewithhamad.headwaybuilders.analyst;

public class BuildingModel {

    private int buildingImage;
    private int buildingId;
    private String buildingName;
    private String buildingArea;
    private int numbOfFlats;
    private String parkingArea;
    private String shortDetails;
    private boolean isExpanded;

    public BuildingModel(int buildingImage, int buildingId, String buildingName, String area, int numbOfFlats, String parkingArea, String shortDetails) {
        this.buildingImage= buildingImage;
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.buildingArea = area;
        this.numbOfFlats = numbOfFlats;
        this.parkingArea = parkingArea;
        this.shortDetails= shortDetails;
        isExpanded= false;
    }

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

    public String getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(String buildingArea) {
        this.buildingArea = buildingArea;
    }

    public int getNumbOfFlats() {
        return numbOfFlats;
    }

    public void setNumbOfFlats(int numbOfFlats) {
        this.numbOfFlats = numbOfFlats;
    }

    public String getParkingArea() {
        return parkingArea;
    }

    public void setParkingArea(String parkingArea) {
        this.parkingArea = parkingArea;
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
}
