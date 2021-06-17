package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenuView extends JFrame implements ActionListener {
    JButton newGameButton;
    JButton aboutButton;
    JButton helpButton;
    JButton exitButton;



    public StartMenuView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setTitle("The Conqueror");
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        JPanel menuPanel = new JPanel();


        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        newGameButton = new JButton("New Game");
        aboutButton = new JButton("About");
        helpButton = new JButton("Help");
        exitButton = new JButton("Exit");


        newGameButton.addActionListener(this);
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutButton.addActionListener(this);
        aboutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        helpButton.addActionListener(this);
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(this);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        menuPanel.add(Box.createRigidArea(new Dimension(0,this.getHeight()/2-4*30)));
        menuPanel.add(newGameButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0,15)));
        menuPanel.add(aboutButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0,15)));
        menuPanel.add(helpButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0,15)));
        menuPanel.add(exitButton);



       // menuPanel.setBackground(Color.CYAN);

        this.add(menuPanel);
        this.validate();
        this.repaint();
    }

    public static void main(String[] args) {
        new StartMenuView();
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
