package units;

import exceptions.MaxCapacityException;

import java.util.ArrayList;

public class Army {

    private Status currentStatus = Status.IDLE;
    private ArrayList<Unit> units;
    private int distancetoTarget = -1;
    private String target = "";
    private String currentLocation;
    private final int maxToHold = 10; // Cannot be changed

    public Army(String currentLocation) {
        this.currentLocation = currentLocation;
        this.units = new ArrayList<>();
    }

    public void relocateUnit(Unit unit) throws MaxCapacityException {
        units.add(unit);
        // TODO 3.1 : Additionally, it should remove the unit from the previous army and add it to the corresponding army.

    }

    public void handleAttackedUnit(Unit u){
        if(u.getCurrentSoldierCount() == 0){
            units.remove(u);
        }
    }

    public double foodNeeded(){
        double foodNeeded = 0.0;

        for(Unit u : units){
            int currentSoldierCount = u.getCurrentSoldierCount();
            switch (currentStatus) {
                case IDLE -> foodNeeded += u.getIdleUpkeep() * currentSoldierCount;
                case MARCHING -> foodNeeded += u.getMarchingUpkeep() * currentSoldierCount;
                case BESIEGING -> foodNeeded += u.getSiegeUpkeep() * currentSoldierCount;
            }
        }

        return foodNeeded;
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
