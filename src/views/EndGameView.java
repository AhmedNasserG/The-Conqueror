package views;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class EndGameView extends Frame {
    private ActionListener listener;
    private JButton replayBtn;
    private JButton exitBtn;

    public EndGameView(boolean gameWon) {
        super();
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JPanel textPanel = new JPanel();
        JPanel btnPanel = new JPanel();

        replayBtn = new newButton("PLAY AGAIN!");
        exitBtn = new newButton("EXIT");
        JLabel gameVerdictText = new JLabel();

        if (gameWon) {
            gameVerdictText.setText("VICTORY!!!");
            setBackground("res/backgrounds/win.jpg");
            playMusic("res/sounds/win.wav");
        }
        else {
            gameVerdictText.setText("DEFEAT");
            setBackground("res/backgrounds/lose.gif");
            playMusic("res/sounds/defeat.wav");

        }
        gameVerdictText.setFont(new Font(Font.MONOSPACED, Font.BOLD, 150));
        gameVerdictText.setForeground(Color.white);
        gameVerdictText.setOpaque(false);

        replayBtn.addActionListener(listener);
        exitBtn.addActionListener(listener);

        //textPanel.add(gameVerdictText);
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

    private void playMusic(String path) {

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
