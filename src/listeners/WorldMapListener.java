package listeners;

import engine.City;
import units.Army;

import java.awt.event.ActionListener;

public interface WorldMapListener extends ActionListener, CardListener {
    void onManualAttackChosen(String battleMode, Army playerArmy, City targetCity) throws InterruptedException;
    void onAutoResolveChosen(String battleMode, Army playerArmy, City targetCity) throws InterruptedException;
    void onCityClicked(City city) ;
    void onCityCardClicked(City city);
    void onAttackClicked();
    void onLaySiegeClicked();
    void onSetTargetClicked();
}


