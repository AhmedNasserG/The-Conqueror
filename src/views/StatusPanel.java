package views;

import javax.swing.*;
import java.awt.*;

class StatusPanel extends JPanel {

    private JLabel playerNameLabel;
    private JLabel turnCountLabel;
    private JLabel goldCountLabel;
    private JLabel foodCountLabel;

    public StatusPanel() {
        FlowLayout layout = new FlowLayout(FlowLayout.LEADING);
        layout.setHgap(20);
        this.setLayout(layout);
        this.setBackground(Color.CYAN);

        playerNameLabel = new JLabel();
        turnCountLabel = new JLabel();
        goldCountLabel = new JLabel();
        foodCountLabel = new JLabel();

        ImageIcon goldIcon = new ImageIcon("res/img/coin.png");
        ImageIcon foodIcon = new ImageIcon("res/img/food.png");

        goldCountLabel.setIcon(new ImageIcon(goldIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        foodCountLabel.setIcon(new ImageIcon(foodIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));

        add(playerNameLabel);
        add(turnCountLabel);
        add(goldCountLabel);
        add(foodCountLabel);

    }

    public JLabel getPlayerNameLabel() {
        return playerNameLabel;
    }

    public JLabel getTurnCountLabel() {
        return turnCountLabel;
    }

    public JLabel getGoldCountLabel() {
        return goldCountLabel;
    }

    public JLabel getFoodCountLabel() {
        return foodCountLabel;
    }
}
