package views;

import engine.City;
import listeners.StartMenuListener;
import units.Cavalry;
import units.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class StartMenuView extends Frame implements ActionListener {

    private NewGameView newGameView;
    private StartMenuListener listener;

    public StartMenuView(){
        super("The Conqueror");
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.setBackground("temp_res/imgs/backgrounds/start_menu.jpg");
        String[] buttonNames = new String[]{"New Game", "Leadboard", "About", "Help", "Exit"};
        JPanel menuPanel = menuPanel(buttonNames);
        menuPanel.setOpaque(false);

        this.add(menuPanel);
        this.revalidate();
        this.repaint();
    }

    public JPanel menuPanel(String[] buttonsArray) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        for (String buttonName : buttonsArray) {
            newPanel.add(createButton(buttonName), gbc);
            newPanel.add(Box.createVerticalStrut(15));
        }
        return newPanel;
    }

    public JButton createButton(String name) {
        JButton newButton = new JButton(name);
        newButton.setPreferredSize(new Dimension(150,30));
        newButton.setMinimumSize(new Dimension(150,30));
        newButton.setMaximumSize(new Dimension(150,30));
        newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newButton.addActionListener(this);
        newButton.setActionCommand(name);
        return newButton;
    }

    public static void main(String[] args) {
        new StartMenuView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New Game": {
                try {
                    listener.onNewGameClicked();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                break;
            }
            case "Leadboard":{
                try {
                    listener.onLeadboardClicked();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            case "About": {
                //System.out.println("ABOUT");
//                this.setVisible(false);
                //new NewGameView();
                break;
            }
            case "Help": {
                //System.out.println("HELP");
//                this.setVisible(false);
                // new NewGameView();
                break;
            }
            case "Exit": {
                //System.out.println("Exit");
                System.exit(0);
                break;
            }
        }
    }


    public NewGameView getNewGameView() {
        return newGameView;
    }

    public void setListener(StartMenuListener listener) {
        this.listener = listener;
    }

    public void setNewGameView(NewGameView newGameView) {
        this.newGameView = newGameView;
    }


}
