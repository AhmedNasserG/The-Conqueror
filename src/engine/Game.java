package engine;

import units.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Game {
    private Player player;
    private ArrayList<City> availableCities;
    private static ArrayList<Distance> distances;
    private final int maxTurnCount = 30;
    private int currentTurnCount;

    public Game(String playerName, String playerCity) throws IOException {
        player = new Player(playerName);
        availableCities = new ArrayList<>();
        distances = new ArrayList<>();
        currentTurnCount = 1;
        loadCitiesAndDistances();
        player.getControlledCities().add(new City(playerCity));

        //TODO: Carefully think about how will you initialize the army of the defending cities.
    }

    public void loadArmy(String cityName,String path) throws IOException {
        //TODO: implement this method
        if (cityName.equals(player.getControlledCities().get(0).getName())) {
            player.getControlledCities().get(0).setDefendingArmy(null);
            return;
        }


        Army army;
        for (City city : availableCities) {
            if (city.getName().equals(cityName)) {
                army = new Army(cityName);
                city.setDefendingArmy(army);
            }
        }

        ArrayList<Unit> units = new ArrayList<>();
        String currentLine;
        FileReader fileReader= new FileReader(path);
        BufferedReader br = new BufferedReader(fileReader);
        while ((currentLine = br.readLine()) != null) {
            String[] line = currentLine.split(",");
            switch (line[0]) {
                case "Archer":
                    switch (line[1]) {
                        case "1":
                            units.add(new Archer(1, 60, 0.4, 0.5, 0.6));
                            break;
                        case "2":
                            units.add(new Archer(2, 60, 0.4, 0.5, 0.6));
                            break;
                        case "3":
                            units.add(new Archer(3, 70, 0.5, 0.6, 0.7));
                            break;
                    }
                    break;
                case "Infantry":
                    switch (line[1]) {
                        case "1":
                            units.add(new Infantry(1, 50, 0.5, 0.6, 0.7));
                            break;
                        case "2":
                            units.add(new Infantry(2, 50, 0.5, 0.6, 0.7));
                            break;
                        case "3":
                            units.add(new Infantry(3, 60, 0.6, 0.7, 0.8));
                            break;
                    }
                    break;
                case "Cavalry":
                    switch (line[1]) {
                        case "1":
                            units.add(new Cavalry(1, 40, 0.6, 0.7, 0.75));
                            break;
                        case "2":
                            units.add(new Cavalry(2, 40, 0.6, 0.7, 0.75));
                            break;
                        case "3":
                            units.add(new Cavalry(3, 60, 0.7, 0.8, 0.9));
                            break;
                    }
                    break;
            }
        }


    }
//    private Unit loadUnit(String type, int level) {
//        if(type.equals("Archer")) {
//
//        }
//        return null;
//    }

    private void loadCitiesAndDistances() throws IOException {
        HashSet<String> cities = new HashSet<>();
        String currentLine;
        FileReader fileReader= new FileReader("distances.csv");
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
