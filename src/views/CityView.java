package views;

import buildings.*;
import engine.City;
import listeners.CityViewListener;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;


public class CityView extends Frame {

    private City cityToView;
    private ArrayList<Army> defendingArmiesAtThisCity;
    private ArrayList<Army> controlledArmiesAtThisCity;
    private StatusPanel statusPanel;
    private JPanel cityGrid;
    private CityViewListener listener;
    private JButton worldMapButton;
    private JButton initiateArmyButton;
    private ArmiesPanel armiesPanel;


    public CityView(City cityToView) {
        super();
        this.cityToView = cityToView;
        this.setLayout(null);

        worldMapButton = new JButton();
        cityGrid = new JPanel();

        ImageIcon worldMapIcon = new ImageIcon("res/img/map.png");
        worldMapButton.setActionCommand("worldMapButton");
        worldMapButton.setIcon(new ImageIcon(worldMapIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));

        cityGrid.setBackground(Color.lightGray);

        worldMapButton.setBounds(getWidth() - 100, 0, 100, 100);
        cityGrid.setBounds((getWidth() - 710) / 2, getHeight() - 410 - 180, 410, 410);
        cityGrid.setLayout(new GridLayout(3, 3, 5, 5));

        JLabel label = new JLabel(cityToView.getName(), SwingConstants.CENTER);
        label.setBackground(Color.BLUE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setOpaque(true);
        label.setBounds(0, 100, getWidth() - 300, 80);


        initiateArmyButton = new JButton("Initiate Army");
        initiateArmyButton.setBounds(getWidth() - 500, 100, 200, 80);
        add(initiateArmyButton);

        String backgroundPath = "res/img/" + cityToView.getName().toLowerCase()+"City.png";
        this.setBackground(backgroundPath);

        add(worldMapButton);
        add(cityGrid);
        add(label);
        setVisible(true);
        revalidate();
        repaint();
    }

    public void updateCityGrid() {
        cityGrid.removeAll();
        int i = 0;
        for (Building b : cityToView.getEconomicalBuildings()) {
            Card t = new Card(b);
            t.setActionCommand("PREVIEW_BUILDING");
            t.setListener(listener);
            cityGrid.add(t);
            i++;
        }
        for (Building b : cityToView.getMilitaryBuildings()) {
            Card t = new Card(b);
            t.setActionCommand("PREVIEW_BUILDING");
            t.setListener(listener);
            cityGrid.add(t);
            i++;
        }
        for (; i < 9; i++) {
            cityGrid.add(new Card());
        }
        cityGrid.revalidate();
        cityGrid.repaint();
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
        b1.setActionCommand("BUILD_BUILDING");
        cardsPanel.add(b1);

    }

    public void setStatusPanel(StatusPanel statusPanel) {
        this.statusPanel = statusPanel;
        statusPanel.setBounds(0, 0, getWidth() - 100, 100);
        add(statusPanel);
    }

    public void setListener(CityViewListener listener) {
        this.listener = listener;
        worldMapButton.addActionListener(listener);
        initiateArmyButton.addActionListener(listener);
        updateCityGrid();
        add(getToBuildPanel());
    }

    public void setControlledArmiesAtThisCity(ArrayList<Army> controlledArmiesAtThisCity) {
        this.controlledArmiesAtThisCity = controlledArmiesAtThisCity;
        defendingArmiesAtThisCity = new ArrayList<>();
        defendingArmiesAtThisCity.add(cityToView.getDefendingArmy());
        if (armiesPanel != null){
            armiesPanel.removeAll();
        }
        armiesPanel = new ArmiesPanel(defendingArmiesAtThisCity, controlledArmiesAtThisCity, listener);
        armiesPanel.setBackground(Color.ORANGE);
        armiesPanel.setBounds(getWidth() - 300, 100, 300, getHeight() - 100);
        add(armiesPanel);
        armiesPanel.revalidate();
        armiesPanel.repaint();
    }

    public ArmiesPanel getArmiesPanel() {
        return armiesPanel;
    }

}
