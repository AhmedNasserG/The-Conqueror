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
    private final Font BOLD_LABEL = new Font(Font.MONOSPACED, Font.BOLD, 30);

    public StartMenuView(){
        super("The Conqueror");
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.setBackground("res/backgrounds/start_menu.jpg");
        String[] buttonNames = new String[]{"New Game", "Leaderboard", "About", "Help", "Exit"};
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
        newButton button = new newButton(name);
        button.setPreferredSize(new Dimension(300,50));
        button.setMinimumSize(new Dimension(300,50));
        button.setMaximumSize(new Dimension(300,50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(BOLD_LABEL);
        button.setBackgroundColor(new Color(255,255,255,100));
        button.setPressedBackgroundColor(new Color(255,255,255,160));
        button.addActionListener(this);
        button.setActionCommand(name);
        return button;
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
                new HelpView();
                break;
            }
            case "Help": {
                new HelpView();
                break;
            }
            case "Exit": {
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
