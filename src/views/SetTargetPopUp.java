package views;

import engine.City;
import exceptions.MaxCapacityException;
import listeners.UnitPopUpListener;
import units.Archer;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class SetTargetPopUp extends Frame implements ActionListener {

    private UnitPopUpListener listener;
    private City city;
    private Army army;
    private JComboBox myCities;

    public SetTargetPopUp(ArrayList<City> availableCities, ArrayList<City> controlledCities, Army army){
        //TODO: fix the button only appears on hover !!
        super("Set Target");
        this.army = army;
        int width = (getWidth() - 420) / 2;
        int height = (getHeight() - 420) / 2;
        setBounds(width, height, 420, 420);
        setLayout(null);

        JLabel setTarget = new JLabel("SET TARGET");
        setTarget.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));

        setTarget.setBounds(160, 1, getWidth(), 50);
        setTarget.setAlignmentX(Component.CENTER_ALIGNMENT);

        ArrayList<City> newCities = new ArrayList<>();
        for(City city: availableCities) {
        if(!controlledCities.contains(city)) {
        newCities.add(city);
        }
        }

        myCities = new JComboBox(newCities.toArray());
        myCities.setBounds(135, 150, 150, 40);


        JButton setButton = new JButton("Set");
        setButton.addActionListener(this);
        setButton.setBounds(145, 200, 100, 40);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        closeButton.setBounds(145, 350, 100, 40);


        add(myCities);
        add(setButton);
        add(setTarget);
        add(closeButton);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.city = (City) myCities.getSelectedItem();

        switch (e.getActionCommand()){
            case "Close": {
                this.dispose();
                break;
            }
            case "Set": {
                    listener.onSetClicked(city, army);
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

        //new SetTargetPopUp(new Archer(1),armies);


    }
}
