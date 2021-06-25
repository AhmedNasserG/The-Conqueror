package views;

import engine.City;
import exceptions.FriendlyCityException;
import exceptions.TargetNotReachedException;
import listeners.UnitPopUpListener;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class LaySiegePopUp extends Frame implements ActionListener {

    private UnitPopUpListener listener;
    private City city;
    private JComboBox myArmies;
    private Army army;

    public LaySiegePopUp(ArrayList<Army> availableArmiesAtThisCity,City city){
        //TODO: fix the button only appears on hover !!
        super("Lay Siege");
        this.city = city;
        int width = (getWidth() - 420) / 2;
        int height = (getHeight() - 420) / 2;
        setBounds(width, height, 420, 420);
        setLayout(null);

        JLabel setTarget = new JLabel("LAY SIEGE");
        setTarget.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));

        setTarget.setBounds(160, 1, getWidth(), 50);
        setTarget.setAlignmentX(Component.CENTER_ALIGNMENT);

        myArmies = new JComboBox(availableArmiesAtThisCity.toArray());
        myArmies.setBounds(135, 150, 150, 40);
        myArmies.addActionListener(this);


        JButton layButton = new JButton("Lay Siege");
        layButton.setActionCommand("Lay");
        layButton.addActionListener(this);
        layButton.setBounds(145, 200, 130, 40);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        closeButton.setBounds(145, 350, 100, 40);


        add(myArmies);
        add(layButton);
        add(setTarget);
        add(closeButton);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.army = (Army) myArmies.getSelectedItem();

        switch (e.getActionCommand()){
            case "Close": {
                this.dispose();
                break;
            }
            case "Lay": {
                try {
                    listener.onLayClicked(city,army);
                } catch (TargetNotReachedException targetNotReachedException) {
                    targetNotReachedException.printStackTrace();
                } catch (FriendlyCityException friendlyCityException) {
                    friendlyCityException.printStackTrace();
                }
                this.dispose();
                    break;
                }
            }
        }


    public void setListener(UnitPopUpListener listener) {
        this.listener = listener;
    }

}
