package listeners;

import buildings.Building;
import buildings.MilitaryBuilding;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import views.BuildingPopUp;

public interface BuildingPopUpListener {
    void onUpgradeClicked(BuildingPopUp buildingPopUp) throws MaxLevelException, BuildingInCoolDownException;

    void onRecruitClicked(BuildingPopUp buildingPopUp) throws MaxRecruitedException, BuildingInCoolDownException;
}
