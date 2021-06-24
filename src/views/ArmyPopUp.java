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
    private final int width;
    private final int height;
    private ArrayList<Card> unitCards;
    private WorldMapListener listener;
    private JButton setTargetButton;
    private JButton initiateArmyButton;

    public ArmyPopUp(Army army, String text) {
        super(army.getCurrentLocation());
        this.army = army;
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

        JPanel container2 = new JPanel();
        container2.setBounds(0, 170, 420, 420 - 170);
        container2.setLayout(new BoxLayout(container2, BoxLayout.Y_AXIS));


        container2.add(Box.createRigidArea(new Dimension(0, 20)));
        JPanel unitsPanel = new JPanel();
        unitsPanel.setLayout(new GridLayout());
        unitsPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        unitCards = new ArrayList<Card>();
        for (Unit unit : army.getUnits()) {
            Card unitCard = new Card(unit);
            unitCard.setActionCommand("Unit Card");
            unitCards.add(unitCard);
            unitCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            unitsPanel.add(unitCard);
        }
        JScrollPane unitsPane = new JScrollPane(unitsPanel);
        unitsPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        container2.add(unitsPane);
        container2.add(Box.createRigidArea(new Dimension(0, 60)));


        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.setBounds(420 / 2 - 180 / 3, 360, 100, 30);

        setTargetButton = new JButton("Set Target");
        setTargetButton.setBounds(30, 80, 120, 35);

        if(text.equals("Defending")){
        initiateArmyButton = new JButton("Initiate Army");
        initiateArmyButton.addActionListener(this);
        initiateArmyButton.setActionCommand("Initiate Army");
        initiateArmyButton.setBounds(30, 130, 120, 35);
        initiateArmyButton.setFont(initiateArmyButton.getFont().deriveFont(12));
        add(initiateArmyButton) ;
        }


        add(setTargetButton);
        add(closeButton);
        add(armyTile);
        add(backgroundPanel);
        add(container2);

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


        new ArmyPopUp(salah,"Defending");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Close":
                this.dispose();
                break;
        }
        if(e.getActionCommand().equals("Initiate Army")){
            listener.onInitiateArmyClicked();
        }

    }

    public void setListener(WorldMapListener listener) {
        this.listener = listener;
        for(Card card: unitCards)
            card.setListener(listener);
    }
}
