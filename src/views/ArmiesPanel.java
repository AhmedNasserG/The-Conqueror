package views;

import listeners.CardListener;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ArmiesPanel extends JPanel {
    private Color TRANSPARENT_WHITE = new Color(0,0,0,70);
    public ArmiesPanel(ArrayList<Army> defendingArmies,ArrayList<Army> controlledArmies, CardListener listener) {
        super();
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(this.getWidth(),160));
        JPanel defendingArmyPanel = new JPanel();
        defendingArmyPanel.setLayout(new GridLayout(1,0));
        JPanel controlledArmiesPanel = new JPanel();
        controlledArmiesPanel.setLayout(new GridLayout(1,0));
        defendingArmyPanel.setBackground(TRANSPARENT_WHITE);
        controlledArmiesPanel.setBackground(TRANSPARENT_WHITE);

        for (Army army : defendingArmies) {
            Card armyCard = new Card(army);
            armyCard.setOpaque(false);
            armyCard.setListener(listener);
            armyCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            defendingArmyPanel.add(armyCard);
        }
        for (Army army : controlledArmies) {
            Card armyCard = new Card(army);
            armyCard.setOpaque(false);
            armyCard.setListener(listener);
            armyCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            controlledArmiesPanel.add(armyCard);
        }
        defendingArmyPanel.setBorder(BorderFactory.createTitledBorder("Defending Armies"));
        controlledArmiesPanel.setBorder(BorderFactory.createTitledBorder("Controlled Armies"));


        JScrollPane defendingArmyPane = new JScrollPane(defendingArmyPanel);
        this.add(defendingArmyPane,BorderLayout.WEST);
        JScrollPane controlledArmiesPane = new JScrollPane(controlledArmiesPanel);
        controlledArmiesPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(controlledArmiesPane,BorderLayout.CENTER);
        this.setOpaque(false);
        this.setBackground(TRANSPARENT_WHITE);
        this.revalidate();
        this.repaint();
    }


}
