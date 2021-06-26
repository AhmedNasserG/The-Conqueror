package views;

import listeners.CardListener;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ArmiesPanel extends JPanel {

    public ArmiesPanel(ArrayList<Army> defendingArmies,ArrayList<Army> controlledArmies, CardListener listener) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        //TODO: For the scroll bar to show we need to have a method to update the panel because the size of the armies changed
        JPanel defendingArmyPanel = new JPanel();
        defendingArmyPanel.setPreferredSize(new Dimension(this.getWidth(),350));
        defendingArmyPanel.setLayout(new GridLayout(0,1));
        JPanel controlledArmiesPanel = new JPanel();
        controlledArmiesPanel.setPreferredSize(new Dimension(this.getWidth(),controlledArmies.size()*150));
        controlledArmiesPanel.setLayout(new GridLayout(0,1));


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

        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setPreferredSize(new Dimension(this.getWidth(),defendingArmyPanel.getHeight()+controlledArmiesPanel.getHeight()));
        this.add(new JScrollPane(defendingArmyPanel));
        this.add(new JScrollPane(controlledArmiesPanel));

    }


}
