package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CityView extends Frame {

    private StatusPanel statusPanel;

    public CityView() {
        super();
        this.setLayout(null);

        ImageIcon worldMapIcon = new ImageIcon("res/img/map.png");
        JLabel worldMapLabel = new JLabel(new ImageIcon(worldMapIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));


        worldMapLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.out.println("CLICKED");
            }
        });

        worldMapLabel.setBackground(Color.DARK_GRAY);

        statusPanel = new StatusPanel();

        statusPanel.setBounds(0, 0, getWidth() - 100, 100);
        worldMapLabel.setBounds(getWidth() - 100, 0, 100, 100);

        add(statusPanel);
        add(worldMapLabel);
        setVisible(true);
    }


    public void setPlayerName(String playerName) {
        statusPanel.getPlayerNameLabel().setText(playerName);
    }

    public void setCurrentTurnCount(int currentTurnCount) {
        statusPanel.getTurnCountLabel().setText("Turn: " + currentTurnCount);
    }

    public void setTreasury(double treasury) {
        statusPanel.getGoldCountLabel().setText(treasury + "");
    }

    public void setFood(double food) {
        statusPanel.getFoodCountLabel().setText(food + "");
    }

    public static void main(String[] args) {
        new CityView();
    }
}
