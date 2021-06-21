package views;

import buildings.*;
import engine.City;
import listeners.CityViewListener;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CityView extends Frame implements ActionListener {

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
        JButton worldMapButton = new JButton();
        worldMapButton.setActionCommand("worldMapButton");
        worldMapButton.setIcon(new ImageIcon(worldMapIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        worldMapButton.addActionListener(this);


        cityGrid = new JPanel();
        cityGrid.setBackground(Color.lightGray);

        JPanel armysPanel = new JPanel();
        armysPanel.setBackground(Color.ORANGE);


        statusPanel.setBounds(0, 0, getWidth() - 100, 100);
        worldMapButton.setBounds(getWidth() - 100, 0, 100, 100);

        cityGrid.setBounds((getWidth() - 710) / 2, 100, 410, 410);
        cityGrid.setLayout(new GridLayout(3, 3, 5, 5));


        updateCityGrid();

//        buildPanel.setBounds(0, 510, getWidth() - 300, 300);
        armysPanel.setBounds(getWidth() - 300, 100, 300, getHeight() - 100);

        add(statusPanel);
        add(worldMapButton);
        add(cityGrid);


//        add(getToBuildPanel());
        add(armysPanel);

        setVisible(true);
    }

    private JPanel getToBuildPanel() {
        JPanel buildPanel = new JPanel();
        buildPanel.setBackground(Color.BLUE);
        buildPanel.setBounds(0, getHeight() - 180, getWidth() - 300, 180);
        buildPanel.setLayout(new BoxLayout(buildPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Choose a Building to be built in your city if you want ", SwingConstants.CENTER);
        label.setBackground(Color.ORANGE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setOpaque(true);

        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(1, 5));

        addBuildingCardToBuildingPanel(cardsPanel, new Farm());
        addBuildingCardToBuildingPanel(cardsPanel, new Market());
        addBuildingCardToBuildingPanel(cardsPanel, new ArcheryRange());
        addBuildingCardToBuildingPanel(cardsPanel, new Stable());
        addBuildingCardToBuildingPanel(cardsPanel, new Barracks());

        buildPanel.add(label);
        buildPanel.add(cardsPanel);
        return buildPanel;
    }

    private void addBuildingCardToBuildingPanel(JPanel cardsPanel, Building building) {

        Card b1 = new Card(building, cityToView.getName());
        b1.setListener(listener);
        cardsPanel.add(b1);

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
        add(getToBuildPanel());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("worldMapButton")) {
            this.dispose();
            new WorldMapView();
        }
    }
}
