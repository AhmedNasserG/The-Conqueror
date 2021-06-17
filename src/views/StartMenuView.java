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

    public JPanel menuPanel (String[] buttonsArray ){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel,BoxLayout.Y_AXIS));
        newPanel.add(Box.createRigidArea(new Dimension(0,this.getHeight()-4*170)));
        for(String buttonName:buttonsArray )
        {

            JButton newButton = new JButton(buttonName);
            newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            newButton.addActionListener(this);
            newButton.setActionCommand(buttonName);
            newPanel.add(newButton);
            newPanel.add(Box.createVerticalStrut(15));


        }
        return newPanel;
    }
    public StartMenuView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setTitle("The Conqueror");
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        String[] buttonNames = new String[]{"New Game", "About", "Help", "Exit"};
        JPanel menuPanel = menuPanel(buttonNames);

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
        switch (e.getActionCommand()) {
            case "New Game" : {
                System.out.println("NEW GAME");
                break;
            }
            case "About" : {
                System.out.println("ABOUT");
                break;
            }
            case "Help" :{
                System.out.println("HELP");
                break;
            }
            case "Exit" :
            {
                System.exit(0);
                break;
            }
        }
    }


}
