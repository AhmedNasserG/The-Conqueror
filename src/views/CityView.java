package views;

import buildings.*;
import engine.City;
import listeners.CityViewListener;
import units.Army;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private JPanel upperPanel;
    private Color TRANSPARENT_WHITE = new Color(255,255,255,150);

    public CityView(City cityToView) {
        super();
        this.cityToView = cityToView;
        setBackground();
        this.setLayout(new BorderLayout());

        worldMapButton = new JButton();
        cityGrid = new JPanel();

        ImageIcon worldMapIcon = new ImageIcon("res/img/map.png");
        worldMapButton.setActionCommand("worldMapButton");
        worldMapButton.setIcon(new ImageIcon(worldMapIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        worldMapButton.setContentAreaFilled(false);
        worldMapButton.setBorder(BorderFactory.createEmptyBorder());

        //cityGrid.setBackground(Color.lightGray);

        JPanel centerPanel = new JPanel(new BorderLayout());

        worldMapButton.setBounds(getWidth() - 100, 0, 100, 100);
        cityGrid.setLayout(new GridLayout(2, 3));
        cityGrid.setAlignmentX(Component.CENTER_ALIGNMENT);
        cityGrid.setBorder(BorderFactory.createTitledBorder("Your Buildings"));
        cityGrid.setOpaque(false);

        centerPanel.add(cityGrid,BorderLayout.CENTER);

        JLabel label = new JLabel(cityToView.getName(), SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setOpaque(false);


        initiateArmyButton = new JButton("Initiate Army");
        centerPanel.add(initiateArmyButton,BorderLayout.EAST);

        upperPanel = new JPanel(new FlowLayout());
        upperPanel.add(worldMapButton);
        upperPanel.setOpaque(false);



        add(upperPanel,BorderLayout.NORTH);
        add(centerPanel,BorderLayout.CENTER);

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
        for (; i < 6; i++) {
            cityGrid.add(new Card());
        }
        cityGrid.revalidate();
        cityGrid.repaint();
    }

    private JPanel getToBuildPanel() {
        JPanel buildPanel = new JPanel();
        buildPanel.setLayout(new BoxLayout(buildPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Choose a Building to be built in your city if you want ", SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setOpaque(true);

        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(1, 0));

        addBuildingCardToBuildingPanel(cardsPanel, new Farm());
        addBuildingCardToBuildingPanel(cardsPanel, new Market());
        addBuildingCardToBuildingPanel(cardsPanel, new ArcheryRange());
        addBuildingCardToBuildingPanel(cardsPanel, new Stable());
        addBuildingCardToBuildingPanel(cardsPanel, new Barracks());

        buildPanel.add(label);
        buildPanel.add(cardsPanel);
        buildPanel.setPreferredSize(new Dimension(120*5,180));
        return buildPanel;
    }

    private void addBuildingCardToBuildingPanel(JPanel cardsPanel, Building building) {

        Card card = new Card(building, cityToView.getName());
        card.setListener(listener);
        card.setActionCommand("BUILD_BUILDING");
        cardsPanel.add(card);

    }

    public void setStatusPanel(StatusPanel statusPanel) {
        this.statusPanel = statusPanel;
        upperPanel.add(statusPanel);
    }

    public void setListener(CityViewListener listener) {
        this.listener = listener;
        worldMapButton.addActionListener(listener);
        initiateArmyButton.addActionListener(listener);
        updateCityGrid();
        upperPanel.add(getToBuildPanel());
    }

    public void setControlledArmiesAtThisCity(ArrayList<Army> controlledArmiesAtThisCity) {
        this.controlledArmiesAtThisCity = controlledArmiesAtThisCity;
        defendingArmiesAtThisCity = new ArrayList<>();
        defendingArmiesAtThisCity.add(cityToView.getDefendingArmy());
        if (armiesPanel != null){
            armiesPanel.removeAll();
        }
        armiesPanel = new ArmiesPanel(defendingArmiesAtThisCity, controlledArmiesAtThisCity, listener);
        add(armiesPanel,BorderLayout.SOUTH);
        armiesPanel.revalidate();
        armiesPanel.repaint();
    }

    public void setBackground(){

        String backgroundPath = "res/img/" + cityToView.getName().toLowerCase()+"CityBackground.png";
        this.setBackground(backgroundPath);

    }

    public ArmiesPanel getArmiesPanel() {
        return armiesPanel;
    }


}
