package units;

import java.util.ArrayList;

public class Army {
    // Attribiutes
    private Status currentStatus = Status.IDLE;
    private ArrayList<Unit> units;
    private int distancetoTarget = -1;
    private String target="";
    private String currentLocation;
    private final int maxToHold = 10; // Cannot be changed

    public Army(String currentLocation){
        this.currentLocation=currentLocation;
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
