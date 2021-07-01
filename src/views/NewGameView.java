package views;

import listeners.NewGameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class NewGameView extends Frame implements ActionListener {
    private JRadioButton[] newRadios;
    private JTextField nameTextField;
    private String playerName;
    private String cityName;
    private NewGameListener listener;
    private JRadioButton selectedRadio;
    private final Font BOLD_LABEL = new Font(Font.MONOSPACED, Font.BOLD, 25);


    public NewGameView() {
        super("New Game");
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setBackground("temp_res/imgs/backgrounds/start_menu.jpg");

        JPanel allPanel = new JPanel();
        allPanel.setLayout(new FlowLayout());
        JLabel infoLabel = new JLabel("Enter Your Information");

        JPanel panelName = new JPanel();
        panelName.setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel("Enter your name: ");
        nameLabel.setFont(BOLD_LABEL);
        nameLabel.setForeground(Color.lightGray);
        nameTextField = new JTextField(30);
        panelName.add(nameLabel);
        panelName.add(nameTextField);

        JPanel panelCity = newRadioGroup(new String[]{"Cairo", "Rome", "Sparta"});
        JButton newGame = new newButton("Play");
        newGame.setFont(BOLD_LABEL);
        newGame.setForeground(Color.white);
        newGame.setSize(25, 20);
        newGame.setActionCommand("Play");
        newGame.addActionListener(this);

        panelName.setOpaque(false);
        panelCity.setOpaque(false);
        allPanel.setOpaque(false);

        allPanel.add(panelName);
        allPanel.add(panelCity);
        allPanel.add(newGame);

        //this.add(infoLabel,BorderLayout.NORTH);
        this.add(allPanel,BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }

    public JPanel newRadioGroup(String[] names) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());
        ButtonGroup cityGroup = new ButtonGroup();
        JLabel cityLabel = new JLabel("Choose your city:");
        cityLabel.setFont(BOLD_LABEL);
        cityLabel.setForeground(Color.lightGray);
        newPanel.add(cityLabel);
        newRadios = new JRadioButton[names.length];
        for (int i = 0; i < newRadios.length; i++) {
            newRadios[i] = new JRadioButton(names[i]);
            newRadios[i].setFont(BOLD_LABEL);
            newRadios[i].setOpaque(false);
            newRadios[i].addActionListener(this);
            newRadios[i].setForeground(Color.white);
            cityGroup.add(newRadios[i]);
            newPanel.add(newRadios[i]);
        }
        return newPanel;
    }

    public static void main(String[] args) {
        new NewGameView();
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setListener(NewGameListener listener) {
        this.listener = listener;
    }

    public void actionPerformed(ActionEvent e) {
        playerName = nameTextField.getText();
        if (e.getSource() instanceof JRadioButton) {
            selectedRadio = (JRadioButton) e.getSource();
            cityName = selectedRadio.getText();
        } else if (e.getActionCommand().equals("Play")) {
            if (selectedRadio == null) {
                JOptionPane.showMessageDialog(null, "Please Choose a City");
            } else if (validText(playerName)) {
                try {
                    listener.onPlayClicked();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public boolean validText(String text) {
        if (text.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter your name, it can't be empty");
            return false;
        } else if (text.length() > 30) {
            JOptionPane.showMessageDialog(null, "Can you please choose a simpler name ?");
            return false;
        }
        return true;
    }
}
