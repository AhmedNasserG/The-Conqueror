package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EndGameView extends Frame {
    private ActionListener listener;
    private JButton replayBtn;
    private JButton exitBtn;

    public EndGameView(boolean gameWon) {
        super();
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JPanel textPanel = new JPanel();
        JPanel btnPanel = new JPanel();

        replayBtn = new JButton("PLAY AGAIN!");
        exitBtn = new JButton("EXIT");
        JLabel gameVerdictText = new JLabel();

        if (gameWon) gameVerdictText.setText("VICTORY!!!");
        else gameVerdictText.setText("DEFEAT");
        gameVerdictText.setFont(new Font(Font.MONOSPACED, Font.BOLD, 150));

        replayBtn.addActionListener(listener);
        exitBtn.addActionListener(listener);

        textPanel.add(gameVerdictText);
        btnPanel.add(replayBtn);
        btnPanel.add(exitBtn);

        this.add(textPanel);
        this.add(btnPanel);

        this.setVisible(true);

        this.validate();
        this.repaint();
    }

    public static void main(String[] args) {
        new EndGameView(true);
    }

    public void setListener(ActionListener listener) {
        this.listener = listener;
        replayBtn.addActionListener(listener);
        exitBtn.addActionListener(listener);
    }
}
