package controller;

import buildings.*;
import engine.City;
import engine.Game;
import exceptions.*;
import listeners.*;
import units.*;
import views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class GameGUI
        implements ActionListener, NewGameListener, StartMenuListener, CityViewListener,
        WorldMapListener, BattleListener, UnitListener, CardListener {

    private Game game;
    private final GameViews view;
    private StatusPanel statusPanel;

    public GameGUI() throws IOException {
        view = new GameViews();
        view.setStartMenuView(new StartMenuView());
        view.getStartMenuView().setListener(this);

//        view = new GameViews();
//        game = new Game("OMAR", "Cairo");
//        game.setListener(this);
//        a = new Army("Cairo");
//        b = new Army("Sparta");
//
//        Archer u1 = new Archer(1, 5, 2.0, 2.0, 2.0);
//        Archer u2 = new Archer(1, 5, 2.0, 2.0, 2.0);
//        Archer u3 = new Archer(1, 5, 2.0, 2.0, 2.0);
//        Archer u4 = new Archer(1, 5, 2.0, 2.0, 2.0);
//        Archer u5 = new Archer(1, 5, 2.0, 2.0, 2.0);
//        Archer u6 = new Archer(1, 5, 2.0, 2.0, 2.0);
//        u1.setListener(this);
//        u2.setListener(this);
//        u3.setListener(this);
//        u4.setListener(this);
//        u5.setListener(this);
//        u6.setListener(this);
//
//        Army p1 = new Army("asd"), p2 = new Army("Asd");
//
//
//        ArrayList<Unit> list = new ArrayList<>();
//        list.add(u1); list.add(u2); list.add(u3);
////        for(int i = 0; i < list.size(); i++){
////            list.get(i).setListener(this);
////        }
//        a.setUnits(list);
//        game.getPlayer().getControlledArmies().add(a);
//
//        ArrayList<Unit> list2 = new ArrayList<>();
//        list2.add(u4); list2.add(u5); list2.add(u6);
////        for(int i = 0; i < list.size(); i++){
////            list.get(i).setListener(this);
////        }
//        b.setUnits(list2);
//
//        u1.setParentArmy(a);
//        u2.setParentArmy(a);
//        u3.setParentArmy(a);
//        u4.setParentArmy(b);
//        u5.setParentArmy(b);
//        u6.setParentArmy(b);
//
//        BattleView bv = new BattleView("AUTO RESOLVE", a, b, this);
//        view.setBattleView(bv);
//        bv.getNextMoveBtn().addActionListener(this);
//
//
//
//        view.getBattleView().repaint();
    }

    public static void main(String[] args) throws IOException {
        new GameGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*if (e.getSource() == view.getBattleView().getNextMoveBtn()) {
            try {
                System.out.println("actionPerformed");
                game.autoResolve(a, b);
            } catch (FriendlyFireException friendlyFireException) {
                friendlyFireException.printStackTrace();
            }
        } else */
        // TODO: Salah you should not just check it is a button because there more than a button to use this method
//        if (e.getSource() instanceof JButton) {
//            String cityName = ((JButton) e.getSource()).getText();
//            for (City city : game.getPlayer().getControlledCities()) {
//                if (cityName.equals(city.getName())) {
//                    view.setCityView(new CityView(city));
//                    view.getCityView().setListener(this);
//                    view.getWorldMapView().dispose();
//                    return;
//                }
//            }
//
//            this.onCityClicked(new City(cityName));
//        }
        if (e.getActionCommand().equals("worldMapButton")) {
            view.getCityView().dispose();
            view.setWorldMapView(new WorldMapView());
            view.getWorldMapView().setListener(this);

        } else if (e.getActionCommand().equals("End Turn")) {
            game.endTurn();
            statusPanel.updateStatusPanel();
        }
    }

    @Override
    public void onAttack(Unit attacker, Unit target) throws FriendlyFireException {
        attacker.attack(target);
    }

    @Override
    public void onBattleUpdated(Army unitParentArmy, String result1, String result2) {
        JTextArea log = view.getBattleView().getBattleLog();
        String RESULT = result1 + (game.getPlayer().getControlledArmies().contains(unitParentArmy) ? "Target" : "Player") + result2;
        log.setText((log.getText() + "\n\n" + RESULT));
//        JLabel resultsDisplay = new JLabel();
//
//        resultsDisplay.setText(result);
//        resultsDisplay.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
//        view.getBattleView().setBattleResultsDisplay(resultsDisplay);
//        view.getBattleView().revalidate();
//        view.getBattleView().repaint();
        System.out.println(RESULT);
//        System.out.println("yay");
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
        statusPanel = new StatusPanel();
        statusPanel.setGame(game);
        statusPanel.setListener(this);

//        view.setWorldMapView(new WorldMapView());
//        view.getWorldMapView().setListener(this);

        City city = game.getPlayer().getControlledCities().get(0);
        CityView cityView = new CityView(city);
        cityView.setListener(this);
        cityView.setStatusPanel(statusPanel);
        statusPanel.updateStatusPanel();
        view.setCityView(cityView);

        view.getNewGameView().dispose();

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

    @Override
    public void onCityClicked(City city) {

//        CityPopUp cityPopUp = new CityPopUp(city);

    }

    @Override
    public void onUpgradeClicked(BuildingPopUp buildingPopUp) {
        try {
            game.getPlayer().upgradeBuilding(buildingPopUp.getBuildingToShow());
            buildingPopUp.dispose();
            view.getCityView().updateCityGrid();
            statusPanel.updateStatusPanel();
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
            statusPanel.updateStatusPanel();
        } catch (BuildingInCoolDownException e) {
            JOptionPane.showMessageDialog(null, "Sorry The Building is Cooling Down, Please wait to the next turn to upgrade.");
        } catch (MaxRecruitedException e) {
            JOptionPane.showMessageDialog(null, "Sorry The Building Already Recruited The Maximum Number of Units Per Turn, Please wait to the next turn to recruit more.");
        } catch (NotEnoughGoldException e) {
            JOptionPane.showMessageDialog(null, "Sorry You Have Not Enough Gold To Recruit.");
        }
    }

    @Override
    public void onBuildingCardClicked(Building building, String whereToBuild) throws NotEnoughGoldException {
        if (whereToBuild != null) {
            try {
                game.getPlayer().build(building.getBuildingName(), whereToBuild);
                view.getCityView().updateCityGrid();
                statusPanel.updateStatusPanel();
            } catch (BuildingInCityAlreadyException e) {
                JOptionPane.showMessageDialog(null, "Sorry You Already Have " + building.getBuildingName() + " In Your City, Please Choose Another Building.");
            } catch (NotEnoughGoldException e) {
                JOptionPane.showMessageDialog(null, "Sorry You Have Not Enough Gold To Build " + building.getBuildingName() + ".");
            }
        } else {
            BuildingPopUp buildingPopUp = new BuildingPopUp(building);
            buildingPopUp.setListener(this);
        }
    }

    @Override
    public void onUnitCardClicked(Unit unit) {
        JPanel p = view.getBattleView().getUnitInfoPanel();
        Card c = new Card(unit);
        c.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(c);
        view.getBattleView().revalidate();
        view.getBattleView().repaint();
//        p.setBackground(Color.orange);
    }

    @Override
    public void onArmyCardClicked(Army army) {
        ArmyPopUp armyPopUp = new ArmyPopUp(army);
    }

}
