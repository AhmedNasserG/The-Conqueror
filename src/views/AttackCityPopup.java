package views;

import engine.City;
import listeners.WorldMapListener;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AttackCityPopup extends Frame implements ActionListener {

    private WorldMapListener listener;
    private City cityToAttack;
    private Army chosenArmy;
    private JComboBox armiesComboBox;

    public AttackCityPopup(City cityToAttack, ArrayList<Army> availableArmiesAtThisCity) {
        this.cityToAttack = cityToAttack;
        int width = (getWidth() - 420) / 2;
        int height = (getHeight() - 420) / 2;
        setBounds(width, height, 420, 420);
        setLayout(null);

        JLabel CityLabel = new JLabel(cityToAttack.getName());
        CityLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));

        CityLabel.setBounds(160, 1, getWidth(), 50);
        CityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        armiesComboBox = new JComboBox(availableArmiesAtThisCity.toArray());
        armiesComboBox.setBounds(95, 150, 205, 40);
        armiesComboBox.addActionListener(this);

        JButton manualAttackButton = new JButton("Manual Attack");
        manualAttackButton.setBounds(95, 200, 205, 40);
        manualAttackButton.addActionListener(this);

        JButton autoResolveButton = new JButton("Auto Resolve");
        autoResolveButton.setBounds(95, 250, 205, 40);
        autoResolveButton.addActionListener(this);


        JButton closeButton = new JButton("Close");
        closeButton.setBounds(145, 350, 100, 40);
        closeButton.addActionListener(this);

        add(CityLabel);
        add(armiesComboBox);
        add(manualAttackButton);
        add(autoResolveButton);
        add(closeButton);
        setVisible(true);
    }

    public static void main(String[] args) {
        ArrayList<Army> armies = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            armies.add(new Army("sadasdas"));
        }
        new AttackCityPopup(new City("Cairo"), armies);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chosenArmy = (Army) armiesComboBox.getSelectedItem();
        if (e.getActionCommand().equals("Manual Attack")) {
            if (chosenArmy != null) {
                listener.onManualAttackChosen(chosenArmy, cityToAttack);
            }
        } else if (e.getActionCommand().equals("Auto Resolve")) {
            if (chosenArmy != null) {
                listener.onAutoResolveChosen(chosenArmy, cityToAttack);
            }
        } else if (e.getActionCommand().equals("Close")) {
            this.dispose();
        }

    }

    public void setListener(WorldMapListener listener) {
        this.listener = listener;
    }
}
