package listeners;

import buildings.Building;
import engine.City;
import exceptions.NotEnoughGoldException;
import units.Army;
import units.Unit;
import views.Card;

import java.awt.event.ActionListener;

public interface CardListener extends ActionListener {
    void buildBuilding(Building building, String whereToBuild) throws NotEnoughGoldException;
    void onFriendlyUnitCardClicked(Card c);
    void onEnemyUnitCardClicked(Card c);
    void onArmyCardClicked(Army army);
    void onCityCardClicked(City city);
    void onNewUnitCardClicked(Unit unit);

}
