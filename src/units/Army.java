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
        if (units.size() == maxToHold) {
            throw new MaxCapacityException();
        }
        unit.getParentArmy().getUnits().remove(unit);
        units.add(unit);

    }

    public void handleAttackedUnit(Unit attackedUnit) {
        if (attackedUnit.getCurrentSoldierCount() <= 0) {
            units.remove(attackedUnit);
        }
    }

    public double foodNeeded() {
        double foodNeeded = 0.0;

        for (Unit currentUnit : units) {
            int currentSoldierCount = currentUnit.getCurrentSoldierCount();
            switch (currentStatus) {
                case IDLE:
                    foodNeeded += currentUnit.getIdleUpkeep() * currentSoldierCount;
                    break;
                case MARCHING:
                    foodNeeded += currentUnit.getMarchingUpkeep() * currentSoldierCount;
                    break;
                case BESIEGING:
                    foodNeeded += currentUnit.getSiegeUpkeep() * currentSoldierCount;
                    break;
                default: break;
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
