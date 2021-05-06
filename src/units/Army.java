package units;

import java.util.ArrayList;

public class Army {

    private Status currentStatus;
    private ArrayList<Unit> units;
    private int distancetoTarget;
    private String target;
    private String currentLocation;
    private final int maxToHold; // Cannot be changed


    public Army(String currentLocation){
        this.currentStatus = Status.IDLE;
        this.units = new ArrayList<>();
        this.distancetoTarget = -1;
        this.target = "";
        this.currentLocation = currentLocation;
        this.maxToHold = 10;
    }


    // Getters
    public Status getCurrentStatus() {
        return currentStatus;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public int getDistancetoTarget() {
        return distancetoTarget;
    }

    public String getTarget() {
        return target;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public int getMaxToHold() {
        return maxToHold;
    }


    // Setters
    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void setDistancetoTarget(int distancetoTarget) {
        this.distancetoTarget = distancetoTarget;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
}
