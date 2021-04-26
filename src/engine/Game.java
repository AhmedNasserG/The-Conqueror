package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Game {
    private Player player;
    private ArrayList<City> availableCities;
    private ArrayList<Distance> distances;
    private final int maxTurnCount = 30;
    private int currentTurnCount;

    public Game(String playerName, String playerCity) throws IOException {
        currentTurnCount = 1;
        player = new Player(playerName);
        //TODO: Carefully think about how will you initialize the army of the defending cities.
    }

    public void loadArmy(String cityName,String path) throws IOException {
        //TODO: implement this method

    }

    private void loadCitiesAndDistances() throws IOException {
        String currentLine = "";
        FileReader fileReader= new FileReader("csv/distances.csv");
        BufferedReader br = new BufferedReader(fileReader);
        while ((currentLine = br.readLine()) != null) {
            String[] line = currentLine.split(",");
            Distance distance = new Distance(line[0], line[1], Integer.parseInt(line[2]));
            distances.add(distance);
        }
        //TODO: load cities
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
