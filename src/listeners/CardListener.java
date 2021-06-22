package listeners;

import buildings.Building;
import engine.City;
import exceptions.NotEnoughGoldException;
import units.Army;
import units.Unit;

public interface CardListener {
    void onBuildingCardClicked(Building building, String whereToBuild) throws NotEnoughGoldException;
    void onUnitCardClicked(Unit unit);
    void onFriendlyUnitCardClicked(Unit unit);
    void onEnemyUnitCardClicked(Unit unit);
    void onArmyCardClicked(Army army);
    void onCityCardClicked(City city);
}
