package engine;

import units.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;


public class Game {
    private Player player;
    private ArrayList<City> availableCities;
    private ArrayList<Distance> distances;
    private final int maxTurnCount;
    private int currentTurnCount;

    public Game(String playerName, String playerCity) throws IOException {
        this.player = new Player(playerName);
        this.availableCities = new ArrayList<>();
        this.distances = new ArrayList<>();
        this.maxTurnCount = 30;
        this.currentTurnCount = 1;

        loadCitiesAndDistances();

        for (City city : availableCities) {
            if (city.getName().equals(playerCity)) {
                player.getControlledCities().add(city);
                city.setDefendingArmy(null);
            } else {
                loadArmy(city.getName(), city.getName().toLowerCase() + "_army.csv");
            }
        }
    }

    public void loadArmy(String cityName, String path) throws IOException {
        Army army = null;
        for (City city : availableCities) {
            if (city.getName().equals(cityName)) {
                army = new Army(cityName);
                city.setDefendingArmy(army);
            }
        }

        String currentLine;
        FileReader fileReader = new FileReader(path);
        BufferedReader br = new BufferedReader(fileReader);

        ArrayList<Unit> unitsArray = new ArrayList<>();
        while ((currentLine = br.readLine()) != null) {
            String[] line = currentLine.split(",");
            loadUnit(unitsArray, line[0], Integer.parseInt(line[1]));
        }

        if (army != null) {
            army.setUnits(unitsArray);
        }
    }

    private void loadUnit(ArrayList<Unit> unitsArray, String type, int level) {
        switch (type) {
            case "Archer":
                unitsArray.add(new Archer(level));
                break;
            case "Infantry":
                unitsArray.add(new Infantry(level));
                break;
            case "Cavalry":
                unitsArray.add(new Cavalry(level));
                break;
        }
    }


    private void loadCitiesAndDistances() throws IOException {
        HashSet<String> cities = new HashSet<>();

        String currentLine;
        FileReader fileReader = new FileReader("distances.csv");
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
}
