package listeners;

import buildings.Building;
import exceptions.NotEnoughGoldException;
import units.Army;
import units.Unit;

public interface CardListener {
    void onBuildingCardClicked(Building building, String whereToBuild) throws NotEnoughGoldException;
    void onUnitCardClicked(Unit unit);
    void onArmyCardClicked(Army army);
}
