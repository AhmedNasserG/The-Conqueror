package views;

import listeners.StartMenuListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class StartMenuView extends Frame implements ActionListener {

    private NewGameView newGameView;
    private StartMenuListener listener;

    public StartMenuView() {
        super("The Conqueror");
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        String[] buttonNames = new String[]{"New Game", "About", "Help", "Exit"};
        JPanel menuPanel = menuPanel(buttonNames);

        this.add(menuPanel);
        this.revalidate();
        this.repaint();
    }

    public JButton createButton(String name) {
        JButton newButton = new JButton(name);
        newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newButton.addActionListener(this);
        newButton.setActionCommand(name);
        return newButton;
    }

    public JPanel menuPanel(String[] buttonsArray) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        newPanel.add(Box.createRigidArea(new Dimension(0, this.getHeight() - 4 * 170)));
        for (String buttonName : buttonsArray) {
            newPanel.add(createButton(buttonName));
            newPanel.add(Box.createVerticalStrut(15));

        }
        return newPanel;
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
            case "About": {
                System.out.println("ABOUT");
//                this.setVisible(false);
                //new NewGameView();
                break;
            }
            case "Help": {
                System.out.println("HELP");
//                this.setVisible(false);
                // new NewGameView();
                break;
            }
            case "Exit": {
                System.out.println("Exit");
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
