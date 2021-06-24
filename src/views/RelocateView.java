package views;

import exceptions.MaxCapacityException;
import listeners.UnitPopUpListener;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class RelocateView extends Frame implements ActionListener {

    private UnitPopUpListener listener;
    private Army army;
    private Unit unit;
    public RelocateView(Unit unit, ArrayList<Army> armyArrayList){
        //TODO: fix the button only appears on hover
        super(unit.getUnitName());
        this.unit = unit;
        int width = (getWidth() - 420) / 2;
        int height = (getHeight() - 420) / 2;
        setBounds(width, height, 420, 420);
        setLayout(null);

        JLabel unitName = new JLabel(unit.getUnitName().toUpperCase());
        unitName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));

        unitName.setBounds(160, 1, getWidth(), 50);
        unitName.setAlignmentX(Component.CENTER_ALIGNMENT);


        // TODO: Get player armies
        JComboBox myArmies = new JComboBox(armyArrayList.toArray());
        myArmies.setBounds(135, 150, 150, 40);
        army = (Army) myArmies.getSelectedItem();

        JButton relocateButton = new JButton("Relocate");
        relocateButton.addActionListener(this);
        relocateButton.setBounds(145, 200, 100, 40);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        closeButton.setBounds(145, 350, 100, 40);


        add(myArmies);
        add(relocateButton);
        add(unitName);
        add(closeButton);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Close": {
                this.dispose();
                break;
            }
            case "Relocate": {
                try {
                    listener.onRelocateClicked(unit,army);
                } catch (MaxCapacityException maxCapacityException) {
                    maxCapacityException.printStackTrace();
                }
            }
        }
    }

    public void setListener(UnitPopUpListener listener) {
        this.listener = listener;
    }

    public static void main(String[] args) {

        ArrayList<Army> armies = new ArrayList<>();
        for(int i=0;i<30;i++){
            armies.add(new Army("Army" + i));
        }

        new RelocateView(new Archer(1),armies);


    }
}
