package listeners;

import buildings.Building;
import engine.City;
import exceptions.NotEnoughGoldException;
import units.Army;
import units.Unit;
import views.Card;

public interface CardListener {
    void onBuildingCardClicked(Building building, String whereToBuild) throws NotEnoughGoldException;
    void onFriendlyUnitCardClicked(Card c);
    void onEnemyUnitCardClicked(Card c);
    void onArmyCardClicked(Army army);
    void onCityCardClicked(City city);
    void onNewUnitCardClicked(Unit unit);
}
