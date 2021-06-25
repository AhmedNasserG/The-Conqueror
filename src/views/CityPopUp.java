package views;

import engine.City;
import listeners.WorldMapListener;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CityPopUp extends Frame implements ActionListener {
    private City city;
    private final int width;
    private final int height;
    private JButton attackButton, laySiegeButton;
    private WorldMapListener listener;
    private Army army;

    public CityPopUp(City city) {
        super(city.getName());
        this.city = city;
        this.width = (getWidth() - 420) / 2;
        this.height = (getHeight() - 420) / 2;
        setBounds(width, height, 420, 420);
        setLayout(null);
        Card cityPicture = new Card(city);
        cityPicture.setBounds(200, 10, 400 / 3, 400 / 3);

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

        JScrollPane unitsPane = new JScrollPane(unitsPanel);
        unitsPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        container2.add(unitsPane);
        container2.add(Box.createRigidArea(new Dimension(0, 60)));


        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.setBounds(420 / 2 - 180 / 3, 360, 100, 30);


        attackButton = new JButton("Attack");
        attackButton.setActionCommand("Start Attack On City");
        attackButton.setBounds(30, 40, 120, 35);

        laySiegeButton = new JButton("Lay Siege");
        attackButton.setActionCommand("Lay Siege");
        laySiegeButton.setBounds(30, 80, 120, 35);

        attackButton.addActionListener(this);
        laySiegeButton.addActionListener(this);

        add(attackButton);
        add(laySiegeButton);
        add(closeButton);
        add(cityPicture);
        add(backgroundPanel);
        add(container2);

        this.setVisible(true);

    }



    public static void main(String[] args) {
        new CityPopUp(new City("Rome"));
    }

    public void setListener(WorldMapListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Close":
                this.dispose();
                break;
            case "Start Attack On City": {
                this.dispose();
                listener.onAttackCityClicked(city);
                break;}
            case "Lay Siege":{
                this.dispose();
                listener.onLaySiegeClicked(city);
                break;}
            default:
                break;

        }
    }
}
