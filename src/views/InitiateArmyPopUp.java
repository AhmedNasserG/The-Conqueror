package views;

import engine.City;
import exceptions.MaxCapacityException;
import listeners.UnitPopUpListener;
import units.Archer;
import units.Army;
import units.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class InitiateArmyPopUp extends Frame implements ActionListener {

    private UnitPopUpListener listener;
    private Unit unit;
    private City city;
    private JComboBox myArmies;
    private final Font BOLD_LABEL = new Font(Font.MONOSPACED, Font.BOLD, 22);

    public InitiateArmyPopUp(City city){
        super(city.getDefendingArmy().getCurrentLocation());
        this.city = city;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int width = (getWidth() - 420) / 2;
        int height = (getHeight() - 420) / 2;
        setBounds(width, height, 420, 420);
        setLayout(null);

        JLabel cityName = new JLabel(city.getName().toUpperCase());
        cityName.setFont(BOLD_LABEL);

        cityName.setBounds(160, 1, getWidth(), 50);
        cityName.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel chooseAUnit = new JLabel("Choose a unit");
        chooseAUnit.setBounds(135, 110, 150, 40);

        myArmies = new JComboBox(city.getDefendingArmy().getUnits().toArray());
        myArmies.setBounds(135, 150, 150, 40);


        JButton relocateButton = new JButton("Initiate");
        relocateButton.addActionListener(this);
        relocateButton.setBounds(145, 200, 100, 40);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        closeButton.setBounds(145, 350, 100, 40);

        add(chooseAUnit);
        add(myArmies);
        add(relocateButton);
        add(cityName);
        add(closeButton);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        unit = (Unit) myArmies.getSelectedItem();

        switch (e.getActionCommand()){
            case "Close": {
                this.dispose();
                break;
            }
            case "Initiate": {
                    listener.onInitiateClicked(city,unit);
                    this.dispose();
            }
        }
    }

    public void setListener(UnitPopUpListener listener) {
        this.listener = listener;
    }

    public static void main(String[] args) {

        Army army = new Army("Cairo");

        for(int i=0;i<30;i++){
            army.getUnits().add(new Archer( 2));
        }

       // new InitiateArmyPopUp(new City("Cario"),army);


    }
}
