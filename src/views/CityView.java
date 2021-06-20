package views;

import buildings.*;
import engine.City;
import listeners.CityViewListener;
import units.Archer;

import javax.swing.*;
import java.awt.*;


public class CityView extends Frame {

    private City cityToView;
    private StatusPanel statusPanel;
    private JPanel cityGrid;
    private CityViewListener listener;


    public CityView(City cityToView) {
        super();
        this.cityToView = cityToView;
        this.setLayout(null);

        statusPanel = new StatusPanel();

        ImageIcon worldMapIcon = new ImageIcon("res/img/map.png");
        JLabel worldMapLabel = new JLabel(new ImageIcon(worldMapIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        worldMapLabel.setBackground(Color.DARK_GRAY);

        cityGrid = new JPanel();
        cityGrid.setBackground(Color.lightGray);

        JPanel buildPanel = new JPanel();
        buildPanel.setBackground(Color.BLUE);

        JPanel armysPanel = new JPanel();
        armysPanel.setBackground(Color.ORANGE);


        statusPanel.setBounds(0, 0, getWidth() - 100, 100);
        worldMapLabel.setBounds(getWidth() - 100, 0, 100, 100);

        cityGrid.setBounds((getWidth() - 710) / 2, 100, 410, 410);
        cityGrid.setLayout(new GridLayout(3, 3, 5, 5));


        updateCityGrid();

        buildPanel.setBounds(0, 510, getWidth() - 300, 300);
        armysPanel.setBounds(getWidth() - 300, 100, 300, getHeight() - 100);

        add(statusPanel);
        add(worldMapLabel);
        add(cityGrid);
        add(buildPanel);
        add(armysPanel);

        setVisible(true);
    }

    public void updateCityGrid() {
        cityGrid.removeAll();
        int i = 0;
        for (Building b : cityToView.getEconomicalBuildings()) {
            Card t = new Card(b);
            t.setListener(listener);
            cityGrid.add(t);
            i++;
        }
        for (Building b : cityToView.getMilitaryBuildings()) {
            Card t = new Card(b);
            t.setListener(listener);
            cityGrid.add(t);
            i++;
        }
        for (; i < 9; i++) {
            cityGrid.add(new Card());
        }
        revalidate();
        repaint();
    }

    public void setPlayerName(String playerName) {
        statusPanel.getPlayerNameLabel().setText(playerName);
    }

    public void setCurrentTurnCount(int currentTurnCount) {
        statusPanel.getTurnCountLabel().setText("Turn: " + currentTurnCount);
    }

    public void setTreasury(double treasury) {
        statusPanel.getGoldCountLabel().setText(treasury + "");
    }

    public void setFood(double food) {
        statusPanel.getFoodCountLabel().setText(food + "");
    }

    public static void main(String[] args) {
        City city = new City("Alex");
        ArcheryRange a = new ArcheryRange();
        a.setCoolDown(false);
        city.getMilitaryBuildings().add(a);
        city.getMilitaryBuildings().add(new Barracks());
        city.getEconomicalBuildings().add(new Market());
        city.getEconomicalBuildings().add(new Farm());
        new CityView(city);
    }

    public void setListener(CityViewListener listener) {
        this.listener = listener;
        updateCityGrid();
    }


}
