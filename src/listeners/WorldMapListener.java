package listeners;

import engine.City;
import units.Army;

import java.awt.event.ActionListener;

public interface WorldMapListener extends ActionListener, CardListener {
    void onManualAttackChosen(Army playerArmy, City targetCity);
    void onAutoResolveChosen(Army playerArmy, City targetCity);
    void onCityCardClicked(City city);
    void onAttackCityClicked(City city);
    void onLaySiegeCityButton(City city);
}