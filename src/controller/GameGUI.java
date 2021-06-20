package controller;

import buildings.*;
import engine.City;
import engine.Game;
import exceptions.*;
import listeners.*;
import units.*;
import views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameGUI
        implements ActionListener, NewGameListener, StartMenuListener, CityViewListener, WorldMapListener, BattleListener, UnitListener{

    private Game game;
    private final GameViews view;

    private Army a;
    private Army b;

    public GameGUI() throws IOException {
        view = new GameViews();
        game = new Game("OMAR", "Cairo");
        game.setListener(this);

    }


    public static void main(String[] args) throws IOException {


        new GameGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.getBattleView().getNextMoveBtn()){
            try {
                System.out.println("actionPerformed");
                game.autoResolve(a,b);
            } catch (FriendlyFireException friendlyFireException) {
                friendlyFireException.printStackTrace();
            }
        }
    }

    @Override
    public void onAttack(Unit attacker, Unit target) throws FriendlyFireException {
        attacker.attack(target);
    }

    @Override
    public void onBattleUpdated(Unit attacker, Unit target, String result) {
//        JLabel resultsDisplay = new JLabel();
//
//        resultsDisplay.setText(result);
//        resultsDisplay.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
//        view.getBattleView().setBattleResultsDisplay(resultsDisplay);
//        view.getBattleView().revalidate();
//        view.getBattleView().repaint();
        System.out.println("yay");
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
//        view.setBattleView(new BattleView("MANUAL ATTACK"));
//        view.getBattleView().setVisible(true);
        // TODO: hide WorldMapView
    }

    @Override
    public void onAutoResolveChosen() throws InterruptedException {
//        view.setBattleView(new BattleView("AUTO RESOLVE"));
//        view.getBattleView().setVisible(true);
        // TODO: hide WorldMapView
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
    public void onRecruitClicked(BuildingPopUp buildingPopUp) {
        try {
            game.getPlayer().recruitUnit(((MilitaryBuilding) (buildingPopUp.getBuildingToShow())));
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
    public void onCardClicked(Object object) {
        if (object instanceof Building) {
            BuildingPopUp buildingPopUp = new BuildingPopUp((Building) object);
            buildingPopUp.setListener(this);
        }
    }
}
