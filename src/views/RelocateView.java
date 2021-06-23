package views;

import listeners.UnitPopUpListener;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RelocateView extends Frame implements ActionListener {

    private UnitPopUpListener listener;

    public RelocateView(Army army){
        //TODO: fix the button only appears on hover
        super(army.getCurrentLocation());

        int width = (getWidth() - 420) / 2;
        int height = (getHeight() - 420) / 2;
        setBounds(width, height, 420, 420);
        setLayout(null);

        JLabel unitName = new JLabel(army.getCurrentLocation().toUpperCase());
        unitName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));

        unitName.setBounds(160, 1, getWidth(), 50);
        unitName.setAlignmentX(Component.CENTER_ALIGNMENT);



        JComboBox myArmyUnits = new JComboBox(army.getUnitsArray().toArray());
        myArmyUnits.setBounds(95, 150, 100, 40);

        // TODO: Get player armies
        JComboBox myArmies = new JComboBox(army.getUnitsArray().toArray());
        myArmies.setBounds(200, 150, 100, 40);



        JButton relocateButton = new JButton("Relocate");
        relocateButton.addActionListener(this);
        relocateButton.setBounds(145, 200, 100, 40);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        closeButton.setBounds(145, 350, 100, 40);

        add(myArmies);
        add(relocateButton);
        add(myArmyUnits);
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
            case "Relocate":{
                //TODO: What to do ?

            }
        }
    }

    public void setListener(UnitPopUpListener listener) {
        this.listener = listener;
    }

    public static void main(String[] args) {
        Army army = new Army("Cairo");
        for(int i=0;i<50;i++){
            army.getUnits().add(new Archer(1));
            army.getUnits().add(new Cavalry(2));
        }
        new RelocateView(army);


    }
}
