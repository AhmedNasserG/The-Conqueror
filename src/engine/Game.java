package engine;

import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import exceptions.FriendlyFireException;
import listeners.BattleListener;
import listeners.UnitListener;
import units.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Random;
// I'm unfortunately a useless comment :'(
// I'm here before anything related to the GUI. DON'T REMOVE ME !

public class Game {
    private Player player;
    private ArrayList<City> availableCities;
    private ArrayList<Distance> distances;
    private final int maxTurnCount;
    private int currentTurnCount;
    private String gameLevel;
    private String autoResolveResults;
    UnitListener unitListener;
    BattleListener battleListener;

    public Game(String playerName, String playerCity, String gameLevel) throws IOException {
        this.player = new Player(playerName);
        this.availableCities = new ArrayList<>();
        this.distances = new ArrayList<>();
        this.maxTurnCount = 50;
        this.currentTurnCount = 1;
        this.gameLevel = gameLevel;
        player.setTreasury(5000);
        loadCitiesAndDistances();

        for (City city : availableCities) {
            if (city.getName().equals(playerCity)) {
                player.getControlledCities().add(city);
            } else {
                loadArmy(city.getName(), "CSVs/" + gameLevel + "/" + city.getName().toLowerCase() + "_army.csv");
            }
        }
    }

    public void loadArmy(String cityName, String path) throws IOException {
        Army army = null;
        for (City city : availableCities) {
            if (city.getName().equals(cityName)) {
                army = new Army(cityName);
                army.setArmyName("Defending Army");
                city.setDefendingArmy(army);
            }
        }

        String currentLine;
        FileReader fileReader = new FileReader(path);
        BufferedReader br = new BufferedReader(fileReader);

        while ((currentLine = br.readLine()) != null) {
            String[] line = currentLine.split(",");
            Unit newUnit = loadUnit(line[0], Integer.parseInt(line[1]));
            if (newUnit != null) {
                newUnit.setParentArmy(army);
                army.getUnits().add(newUnit);
            }
        }
    }

    private Unit loadUnit(String type, int level) {
        switch (type) {
            case "Archer":
                return new Archer(level);
            case "Infantry":
                return new Infantry(level);
            case "Cavalry":
                return new Cavalry(level);
            default:
                return null;
        }
    }

    private void loadCitiesAndDistances() throws IOException {
        HashSet<String> cities = new HashSet<>();

        String currentLine;
        FileReader fileReader = new FileReader("CSVs/" + gameLevel + "/distances.csv");
        BufferedReader br = new BufferedReader(fileReader);

        while ((currentLine = br.readLine()) != null) {
            String[] line = currentLine.split(",");

            Distance distance = new Distance(line[0], line[1], Integer.parseInt(line[2]));

            distances.add(distance);
            cities.add(line[0]);
            cities.add(line[1]);
        }

        for (String city : cities) {
            availableCities.add(new City(city));
        }
    }

    public void targetCity(Army army, String targetName) {
        String armyMovesFrom = army.getCurrentLocation();
        int armyCurrentDistanceToTarget = 0;

        if (army.getCurrentStatus() == Status.MARCHING) {
            armyMovesFrom = army.getTarget();
            armyCurrentDistanceToTarget = army.getDistancetoTarget();
        }

        for (Distance currentDistance : distances) {
            if ((currentDistance.getFrom().equals(armyMovesFrom) && currentDistance.getTo().equals(targetName)) ||
                    (currentDistance.getFrom().equals(targetName) && currentDistance.getTo().equals(armyMovesFrom))) {
                armyCurrentDistanceToTarget += currentDistance.getDistance();
                army.setDistancetoTarget(armyCurrentDistanceToTarget);
                army.setTarget(targetName);
            }
        }
    }

    public void endTurn() {
        currentTurnCount++;
        int totalUpkeep = 0;

        for (City city : getPlayer().getControlledCities()) {
            for (EconomicBuilding building : city.getEconomicalBuildings()) {
                building.setCoolDown(false);
                if (building instanceof Farm) {
                    player.setFood(player.getFood() + building.harvest());
                } else if (building instanceof Market) {
                    player.setTreasury(player.getTreasury() + building.harvest());
                }
            }
            for (MilitaryBuilding building : city.getMilitaryBuildings()) {
                building.setCoolDown(false);
                building.setCurrentRecruit(0);
            }
            totalUpkeep += city.getDefendingArmy().foodNeeded();
        }

        for (City city : availableCities) {
            if (city.getTurnsUnderSiege() == 3) {
                city.setUnderSiege(false);
            }
            if (city.isUnderSiege()) {
                city.setTurnsUnderSiege(city.getTurnsUnderSiege() + 1);
                Army defendingArmy = city.getDefendingArmy();
                for (Unit unit : defendingArmy.getUnits()) {
                    unit.setCurrentSoldierCount(unit.getCurrentSoldierCount() - (int) (unit.getCurrentSoldierCount() * 0.1));
                }
            }
        }

        for (Army a : player.getControlledArmies()) {
            totalUpkeep += a.foodNeeded();
            if (!a.getTarget().equals("") && a.getCurrentStatus() == Status.IDLE) {
                a.setCurrentStatus(Status.MARCHING);
                a.setCurrentLocation("onRoad");
            }

            if (a.getDistancetoTarget() > 0 && !a.getTarget().equals("")) {
                a.setDistancetoTarget(a.getDistancetoTarget() - 1);
            }
            if (a.getDistancetoTarget() == 0 && !a.getTarget().equals("")) {
                a.setCurrentLocation(a.getTarget());
                a.setTarget("");
                a.setCurrentStatus(Status.IDLE);
            }
        }

        if (totalUpkeep <= player.getFood()) {
            player.setFood(player.getFood() - totalUpkeep);
        } else {
            player.setFood(0);
            for (Army a : player.getControlledArmies()) {
                for (Unit u : a.getUnits()) {
                    u.setCurrentSoldierCount(u.getCurrentSoldierCount() - (int) (u.getCurrentSoldierCount() * 0.1));
                }
            }
        }
    }

    public void occupy(Army a, String cityName) {
        City city = null;
        for (City c : availableCities) {
            if (c.getName().equals(cityName)) {
                city = c;
            }
        }
        if (city == null) {
            return;
        }
        player.getControlledCities().add(city);
        player.getControlledArmies().remove(a);
        city.setDefendingArmy(a);
        a.setArmyName("Defending Army");
        city.setUnderSiege(false);
        city.setTurnsUnderSiege(-1);
        a.setCurrentStatus(Status.IDLE);
    }

    public void autoResolve(Army attacker, Army defender) throws FriendlyFireException {
        ArrayList<Unit> attackerUnits = attacker.getUnits();
        ArrayList<Unit> defenderUnits = defender.getUnits();
        Random rand = new Random();
        int turn = 0;

        int[] preAutoResolveAttackerUnitsCount = new int[3], preAutoResolveDefenderUnitsCount = new int[3],
            afterAutoResolveAttackerUnitsCount = new int[3], afterAutoResolveDefenderUnitsCount = new int[3];
        for(Unit u : attackerUnits){
            if(u instanceof Archer){
                preAutoResolveAttackerUnitsCount[0] += u.getCurrentSoldierCount();
            }
            else if(u instanceof Cavalry){
                preAutoResolveAttackerUnitsCount[1] += u.getCurrentSoldierCount();
            }
            else{
                preAutoResolveAttackerUnitsCount[2] += u.getCurrentSoldierCount();
            }
        }

        for(Unit u : defenderUnits){
            if(u instanceof Archer){
                preAutoResolveDefenderUnitsCount[0] += u.getCurrentSoldierCount();
            }
            else if(u instanceof Cavalry){
                preAutoResolveDefenderUnitsCount[1] += u.getCurrentSoldierCount();
            }
            else{
                preAutoResolveDefenderUnitsCount[2] += u.getCurrentSoldierCount();
            }
        }

        while (attackerUnits.size() > 0 && defenderUnits.size() > 0) {
            int idx1 = rand.nextInt(attackerUnits.size());
            int idx2 = rand.nextInt(defenderUnits.size());
            Unit attackUnit = attackerUnits.get(idx1);
            Unit defendUnit = defenderUnits.get(idx2);

            if (turn == 0) { // Attacker Army turn
                unitListener.onAttack(attackUnit, defendUnit);
            } else { // Defender Army turn
                unitListener.onAttack(defendUnit, attackUnit);
            }
            turn ^= 1;
        }

        for(Unit u : attackerUnits){
            if(u instanceof Archer){
                afterAutoResolveAttackerUnitsCount[0] += u.getCurrentSoldierCount();
            }
            else if(u instanceof Cavalry){
                afterAutoResolveAttackerUnitsCount[1] += u.getCurrentSoldierCount();
            }
            else{
                afterAutoResolveAttackerUnitsCount[2] += u.getCurrentSoldierCount();
            }
        }

        for(Unit u : defenderUnits){
            if(u instanceof Archer){
                afterAutoResolveDefenderUnitsCount[0] += u.getCurrentSoldierCount();
            }
            else if(u instanceof Cavalry){
                afterAutoResolveDefenderUnitsCount[1] += u.getCurrentSoldierCount();
            }
            else{
                afterAutoResolveDefenderUnitsCount[2] += u.getCurrentSoldierCount();
            }
        }

        int playerArcherLostCount = preAutoResolveAttackerUnitsCount[0] - afterAutoResolveAttackerUnitsCount[0];
        int playerCavalryLostCount = preAutoResolveAttackerUnitsCount[1] - afterAutoResolveAttackerUnitsCount[1];
        int playerInfantryLostCount = preAutoResolveAttackerUnitsCount[2] - afterAutoResolveAttackerUnitsCount[2];

        int enemyArcherLostCount = preAutoResolveDefenderUnitsCount[0] - afterAutoResolveDefenderUnitsCount[0];
        int enemyCavalryLostCount = preAutoResolveDefenderUnitsCount[1] - afterAutoResolveDefenderUnitsCount[1];
        int enemyInfantryLostCount = preAutoResolveDefenderUnitsCount[2] - afterAutoResolveDefenderUnitsCount[2];

        StringBuilder result = new StringBuilder();
        result.append("Player has lost: ").append(playerArcherLostCount).append(" Archer(s). <br/>");
        result.append("Player has lost: ").append(playerCavalryLostCount).append(" Cavalry(s). <br/>");
        result.append("Player has lost: ").append(playerInfantryLostCount).append(" Infantry(s). <br/>");

        result.append("Enemy has lost: ").append(enemyArcherLostCount).append(" Archer(s). <br/>");
        result.append("Enemy has lost: ").append(enemyCavalryLostCount).append(" Cavalry(s). <br/>");
        result.append("Enemy has lost: ").append(enemyInfantryLostCount).append(" Infantry(s). <br/>");

        battleListener.onAutoResolveEnded(result.toString());
    }

    public boolean isGameOver() {
        if (availableCities.size() == player.getControlledCities().size()) return true;
        return currentTurnCount > maxTurnCount;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<City> getAvailableCities() {
        return availableCities;
    }

    public ArrayList<Distance> getDistances() {
        return distances;
    }

    public int getMaxTurnCount() {
        return maxTurnCount;
    }

    public int getCurrentTurnCount() {
        return currentTurnCount;
    }

    public void setCurrentTurnCount(int currentTurnCount) {
        this.currentTurnCount = currentTurnCount;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setUnitListener(UnitListener unitListener) {
        this.unitListener = unitListener;
    }

    public void setBattleListener(BattleListener battleListener){
        this.battleListener = battleListener;
    }
}
