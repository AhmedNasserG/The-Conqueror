package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame implements ActionListener {
    JButton newGameButton;
    JButton aboutButton;
    JButton helpButton;
    JButton exitButton;

    public StartMenu() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setTitle("The Conqueror");
        this.setVisible(true);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        newGameButton = new JButton("New Game");
        aboutButton = new JButton("About");
        helpButton = new JButton("Help");
        exitButton = new JButton("Exit");

        exitButton.setBounds(10,10,30,10);

        newGameButton.addActionListener(this);
        aboutButton.addActionListener(this);
        helpButton.addActionListener(this);
        exitButton.addActionListener(this);

        menuPanel.add(newGameButton);
        menuPanel.add(aboutButton);
        menuPanel.add(helpButton);
        menuPanel.add(exitButton);

        menuPanel.setBackground(Color.CYAN);

        this.add(menuPanel);
        this.validate();
        this.repaint();
    }

    public static void main(String[] args) {
        new StartMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) {
            System.out.println("NEW GAME");
        } else if (e.getSource() == aboutButton) {
            System.out.println("ABOUT");
        } else if (e.getSource() == helpButton) {
            System.out.println("HELP");
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}
