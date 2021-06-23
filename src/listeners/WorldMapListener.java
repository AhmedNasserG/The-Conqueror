package listeners;

import engine.City;

import java.awt.event.ActionListener;

public interface WorldMapListener extends ActionListener, CardListener {
    void onManualAttackChosen() throws InterruptedException;
    void onAutoResolveChosen() throws InterruptedException;
    void onCityClicked(City city) ;
    void onCityCardClicked(City city);
    void onAttackClicked();
    void onLaySiegeClicked();
}


