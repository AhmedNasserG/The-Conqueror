package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameView extends Frame implements ActionListener {
    public EndGameView(boolean gameWon){
        super();
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JPanel textPanel = new JPanel();
        JPanel btnPanel = new JPanel();

        JButton replayBtn = new JButton("PLAY AGAIN!");
        JButton exitBtn = new JButton("EXIT");
        JLabel gameVerdictText = new JLabel();

        if(gameWon) gameVerdictText.setText("VICTORY!!!");
        else gameVerdictText.setText("DEFEAT");
        gameVerdictText.setFont(new Font("", Font.BOLD, 150));

        replayBtn.addActionListener(this);
        exitBtn.addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: will redo it again but i'll leave as is for now 3ashan ana gebt akhry
        if(e.getActionCommand().equals("PLAY AGAIN!")){
            new NewGameView();
            this.dispose();
        }
        else if(e.getActionCommand().equals("EXIT")){
            System.exit(0);
        }
    }
}
