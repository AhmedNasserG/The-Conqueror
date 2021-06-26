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
    private final int SIDE_LENGTH,CONTAINER_HEIGHT,BUTTON_WIDTH,BUTTON_HEIGHT;
    public CityPopUp(City city) {
        super(city.getName());

        SIDE_LENGTH = 420;
        CONTAINER_HEIGHT = SIDE_LENGTH/3+30;
        BUTTON_WIDTH=120;
        BUTTON_HEIGHT=35;
        this.city = city;
        this.width = (getWidth() - SIDE_LENGTH) / 2;
        this.height = (getHeight() - SIDE_LENGTH) / 2;

        setBounds(width, height, SIDE_LENGTH, SIDE_LENGTH);
        setLayout(null);

        Card cityPicture = new Card(city);
        cityPicture.setBounds(200, 10, SIDE_LENGTH / 3, SIDE_LENGTH / 3);
        //System.out.println(getWidth());

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 0, SIDE_LENGTH, CONTAINER_HEIGHT);
        backgroundPanel.setBackground(Color.ORANGE);
        backgroundPanel.setOpaque(true);

        JPanel container = new JPanel();
        container.setBounds(0, CONTAINER_HEIGHT, SIDE_LENGTH, SIDE_LENGTH - CONTAINER_HEIGHT);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));


        container.add(Box.createRigidArea(new Dimension(0, 20)));
        JPanel unitsPanel = new JPanel();
        unitsPanel.setLayout(new FlowLayout());
        unitsPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));

        JScrollPane unitsPane = new JScrollPane(unitsPanel);
        unitsPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        container.add(unitsPane);
        container.add(Box.createRigidArea(new Dimension(0, 60)));


        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.setBounds(SIDE_LENGTH / 2 - 60, 360, BUTTON_WIDTH, BUTTON_HEIGHT);


        attackButton = new JButton("Attack");
        attackButton.setActionCommand("Start Attack On City");
        attackButton.setBounds(30, 40, BUTTON_WIDTH, BUTTON_HEIGHT);

        laySiegeButton = new JButton("Lay Siege");
        laySiegeButton.setActionCommand("Lay Siege");
        laySiegeButton.setBounds(30, 80, BUTTON_WIDTH, BUTTON_HEIGHT);

        attackButton.addActionListener(this);
        laySiegeButton.addActionListener(this);

        add(attackButton);
        add(laySiegeButton);
        add(closeButton);
        add(cityPicture);
        add(backgroundPanel);
        add(container);
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
                break;
            }
            case "Lay Siege":{
                this.dispose();
                listener.onLaySiegeClicked(city);
                break;
            }
            default:
                break;

        }
    }
}
