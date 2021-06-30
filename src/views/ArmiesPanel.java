package views;

import listeners.CardListener;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ArmiesPanel extends JPanel {
    private Color TRANSPARENT_WHITE = new Color(255,255,255,20);
    public ArmiesPanel(ArrayList<Army> defendingArmies,ArrayList<Army> controlledArmies, CardListener listener) {
        super();
        this.setLayout(new BorderLayout());
        JPanel defendingArmyPanel = new JPanel();
        defendingArmyPanel.setPreferredSize(new Dimension(defendingArmies.size()*150,160));
        defendingArmyPanel.setLayout(new GridLayout(1,0));
        JPanel controlledArmiesPanel = new JPanel();
        controlledArmiesPanel.setLayout(new GridLayout());


        for (Army army : defendingArmies) {
            Card armyCard = new Card(army);
            armyCard.setListener(listener);
            armyCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            defendingArmyPanel.add(armyCard);
        }
        for (Army army : controlledArmies) {
            Card armyCard = new Card(army);
            armyCard.setListener(listener);
            armyCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            controlledArmiesPanel.add(armyCard);
        }
        defendingArmyPanel.setBorder(BorderFactory.createTitledBorder("Defending Armies"));
        controlledArmiesPanel.setBorder(BorderFactory.createTitledBorder("Controlled Armies"));


        JScrollPane defendingArmyPane = new JScrollPane(defendingArmyPanel);
        this.add(defendingArmyPane,BorderLayout.CENTER);
        JScrollPane controlledArmiesPane = new JScrollPane(controlledArmiesPanel);
        controlledArmiesPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(controlledArmiesPane,BorderLayout.EAST);
        this.revalidate();
    }


}
