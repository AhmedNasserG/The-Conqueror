package views;

import buildings.Barracks;
import buildings.MilitaryBuilding;
import engine.City;
import listeners.CardListener;
import listeners.WorldMapListener;
import units.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ArmyPopUp extends Frame implements ActionListener {
    private Army army;
    private String text;
    private final int width;
    private final int height;
    private ArrayList<Card> unitCards;
    private WorldMapListener listener;
    private JButton setTargetButton;
    private JPanel unitsPanel;


    public ArmyPopUp(Army army, String text) {
        super(army.getCurrentLocation());
        this.army = army;
        this.text = text;
        this.width = (getWidth() - 420) / 2;
        this.height = (getHeight() - 420) / 2;
        setBounds(width, height, 420, 420);
        setLayout(null);
        Card armyTile = new Card(army);
        armyTile.setEnabled(false);
        armyTile.setBounds(200, 10, 400 / 3, 400 / 3);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 0, 420, 170);
        backgroundPanel.setBackground(Color.ORANGE);
        backgroundPanel.setOpaque(true);

        JPanel container = new JPanel();
        container.setBounds(0, 170, 420, 420 - 170);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));


        container.add(Box.createRigidArea(new Dimension(0, 20)));
        unitsPanel = new JPanel();
        unitsPanel.setLayout(new GridLayout());
        unitsPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        unitCards = new ArrayList<Card>();
        for (Unit unit : army.getUnits()) {
            Card unitCard = new Card(unit);
            unitCard.setActionCommand("Unit Card");
            unitCards.add(unitCard);
            unitCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            unitsPanel.add(unitCard);
            unitCard.addActionListener(this);
        }
        JScrollPane unitsPane = new JScrollPane(unitsPanel);
        unitsPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        container.add(unitsPane);
        container.add(Box.createRigidArea(new Dimension(0, 60)));


        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.setBounds(420 / 2 - 180 / 3, 360, 100, 30);

        if (!text.equals("Defending")) {
            setTargetButton = new JButton("Set Target");
            setTargetButton.addActionListener(this);
            setTargetButton.setActionCommand("Set Target");
            setTargetButton.setBounds(30, 60, 120, 35);
            add(setTargetButton);
        }

        JLabel targetLabel;
        JLabel distanceToTargetLabel;
        JLabel currentLocationLabel = new JLabel("Current Location: " + army.getCurrentLocation());
        if (army.getTarget().equals("")) {
            targetLabel = new JLabel("");
            distanceToTargetLabel = new JLabel("");
        } else {
            targetLabel = new JLabel("Target:" + army.getTarget());
            distanceToTargetLabel = new JLabel("Distance to Target: " + army.getDistancetoTarget());
        }
        currentLocationLabel.setBounds(15, 80, 200, 60);
        targetLabel.setBounds(15, 100, 200, 60);
        distanceToTargetLabel.setBounds(15, 120, 200, 60);

        add(currentLocationLabel);
        add(targetLabel);
        add(distanceToTargetLabel);
        add(closeButton);
        add(armyTile);
        add(backgroundPanel);
        add(container);

        this.setVisible(true);

    }

    public static void main(String[] args) {

        // For testing
        Army salah = new Army("Salah");
        ArrayList<Unit> units = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            units.add(new Cavalry(1));
            units.add(new Archer(3));
        }
        salah.setUnits(units);


        new ArmyPopUp(salah, "Defending");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Close":
                this.dispose();
                break;
            case "Set Target": {
                this.dispose();
                listener.onSetTargetClicked(army);
                break;
            }
                case "Unit Card":{
                    Card unitCard = (Card) e.getSource();
                    listener.onNewUnitCardClicked(unitCard.getUnit());
                    this.dispose();
                }
        }


    }

    public void setListener(WorldMapListener listener) {
        this.listener = listener;
    }

    public JPanel getUnitsPanel() {
        return unitsPanel;
    }


}
