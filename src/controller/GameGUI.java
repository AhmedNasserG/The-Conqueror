package controller;

import buildings.*;
import engine.City;
import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import listeners.*;
import units.Unit;
import views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameGUI
        implements ActionListener, NewGameListener, StartMenuListener, CityViewListener, WorldMapListener, BattleListener {

    private Game game;
    private final GameViews view;

    public GameGUI() throws IOException, InterruptedException {
        view = new GameViews();
        view.setStartMenuView(new StartMenuView());
        view.getStartMenuView().setListener(this);
//        view.getBattleView().revalidate();
//        view.getBattleView().repaint();
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        new GameGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void onNewGameClicked() throws IOException {
        view.setNewGameView(new NewGameView());
        view.getNewGameView().setListener(this);
        view.getStartMenuView().dispose();
    }

    @Override
    public void onPlayClicked() throws IOException {
        String playerName = view.getNewGameView().getPlayerName();
        String cityName = view.getNewGameView().getCityName();
        game = new Game(playerName, cityName);
        view.getNewGameView().dispose();

        City city = game.getPlayer().getControlledCities().get(0);

        //For TEST only
        ArcheryRange a = new ArcheryRange();
        a.setCoolDown(false);
        city.getMilitaryBuildings().add(a);
        city.getMilitaryBuildings().add(new Barracks());
        city.getEconomicalBuildings().add(new Market());
        city.getEconomicalBuildings().add(new Farm());
        //---------

        CityView cityView = new CityView(city);
        cityView.setListener(this);


        // Set Status panel
        cityView.setPlayerName(game.getPlayer().getName());
        cityView.setCurrentTurnCount(game.getCurrentTurnCount());
        cityView.setFood(game.getPlayer().getFood());
        cityView.setTreasury(game.getPlayer().getTreasury());

        view.setCityView(cityView);
        view.getCityView().revalidate();
        view.getCityView().repaint();

    }

    @Override
    public void onManualAttackChosen() throws InterruptedException {
        view.setBattleView(new BattleView("MANUAL ATTACK"));
        view.getBattleView().setVisible(true);
        // TODO: hide WorldMapView
    }

    @Override
    public void onAutoResolveChosen() throws InterruptedException {
        view.setBattleView(new BattleView("AUTO RESOLVE"));
        view.getBattleView().setVisible(true);
        // TODO: hide WorldMapView
    }


    @Override
    public void onBattleUpdated(Unit attacker, Unit target, String result) {
        JLabel resultsDisplay = view.getBattleView().getBattleResultsDisplay();
        resultsDisplay.setText(result);
        resultsDisplay.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

        view.getBattleView().setBattleResultsDisplay(resultsDisplay);
        view.getBattleView().revalidate();
        view.getBattleView().repaint();
    }


    public void autoResolveBattle() {
        JPanel battlePanel = view.getBattleView().getBattlePanel();

    }

    @Override
    public void onUpgradeClicked(BuildingPopUp buildingPopUp) {
        try {
            game.getPlayer().upgradeBuilding(buildingPopUp.getBuildingToShow());
            buildingPopUp.dispose();
            view.getCityView().updateCityGrid();
        } catch (BuildingInCoolDownException e) {
            JOptionPane.showMessageDialog(null, "Sorry The Building is Cooling Down, Please wait to the next turn to upgrade.");
        } catch (MaxLevelException e) {
            JOptionPane.showMessageDialog(null, "Sorry The Building Reached Maximum Level Available.");
        } catch (NotEnoughGoldException e) {
            JOptionPane.showMessageDialog(null, "Sorry You Have Not Enough Gold To Upgrade.");
        }
    }

    @Override
    public void onRecruitClicked(BuildingPopUp buildingPopUp){
        try {
            game.getPlayer().recruitUnit(((MilitaryBuilding)(buildingPopUp.getBuildingToShow())));
            buildingPopUp.dispose();
        } catch (BuildingInCoolDownException e) {
            JOptionPane.showMessageDialog(null, "Sorry The Building is Cooling Down, Please wait to the next turn to upgrade.");
        } catch (MaxRecruitedException e) {
            JOptionPane.showMessageDialog(null, "Sorry The Building Already Recruited The Maximum Number of Units Per Turn, Please wait to the next turn to recruit more.");
        } catch (NotEnoughGoldException e) {
            JOptionPane.showMessageDialog(null, "Sorry You Have Not Enough Gold To Recruit.");
        }
    }

    @Override
    public void onTileClicked(Building building) {
        BuildingPopUp buildingPopUp = new BuildingPopUp(building);
        buildingPopUp.setListener(this);
    }
}
