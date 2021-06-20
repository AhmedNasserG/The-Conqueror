package views;

import buildings.Barracks;
import buildings.MilitaryBuilding;
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

    public ArmyPopUp(Army army ){
        super(army.getCurrentLocation());
        this.army   = army;
        this.width = (getWidth() - 420) / 2;
        this.height = (getHeight() - 420) / 2;
        setBounds(width, height, 420, 420);
        setLayout(null);
        Card armyTile = new Card(army);
        armyTile.removeMouseListener(armyTile);
        armyTile.setBounds(420 / 2 - 200 / 3, 10, 400 / 3, 400 / 3);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 0, 420, 170);
        backgroundPanel.setBackground(Color.ORANGE);
        backgroundPanel.setOpaque(true);

        JPanel container2 = new JPanel();
        container2.setBounds(0, 170, 420, 420 - 170);
        container2.setLayout(new BoxLayout(container2, BoxLayout.Y_AXIS));



        container2.add(Box.createRigidArea(new Dimension(0, 20)));
        JPanel unitsPanel = new JPanel();
        unitsPanel.setLayout(new FlowLayout());
        unitsPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        for(Unit unit : army.getUnits()){
            Card unitCard = new Card(unit);
            unitsPanel.add(unitCard);
            // TODO: Fix the problem in the unit card not showing
            //unitsPanel.add(new JButton(unit.getUnitName()));
        }
        container2.add(new JScrollPane(unitsPanel));
        container2.add(Box.createRigidArea(new Dimension(0, 60)));


        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.setBounds(420 / 2 - 180 / 3,360,100,30);

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
        for(int i=0;i<50;i++){
               units.add(new Cavalry(1));
        }
        salah.setUnits(units);


        new ArmyPopUp(salah);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Upgrade":
                try {
                    //listener.onUpgradeClicked(this);
                } catch (Exception exception) {
                }
                break;
            case "Recruit":
                try {
                   //listener.onRecruitClicked(this);
                } catch (Exception exception) {
                }
                break;
            case "Close":
                this.dispose();
                break;
        }
    }
}
