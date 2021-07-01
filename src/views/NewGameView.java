package views;

import listeners.NewGameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;


public class NewGameView extends Frame implements ActionListener {
    private JRadioButton[] newRadios;
    private JTextField nameTextField;
    private String playerName;
    private String cityName;
    private NewGameListener listener;
    private JRadioButton selectedRadio;
    private String selectedLevel = "easy";
    private ArrayList<String> levels;
    private final Font BOLD_LABEL = new Font(Font.MONOSPACED, Font.BOLD, 25);


    public NewGameView() {
        super("New Game");
        this.setVisible(true);
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
        this.setBackground("res/backgrounds/start_menu.jpg");

        JPanel allPanel = new JPanel();
        allPanel.setLayout(new BoxLayout(allPanel,BoxLayout.Y_AXIS));
        JLabel infoLabel = new JLabel("Enter Your Information");
        infoLabel.setFont(BOLD_LABEL);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelName = new JPanel();
        panelName.setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel("Enter your name: ");
        nameLabel.setFont(BOLD_LABEL);
        nameLabel.setForeground(Color.white);
        nameTextField = new JTextField(30);
        panelName.add(nameLabel);
        panelName.add(nameTextField);

        JPanel panelCity = newRadioGroup(new String[]{"Cairo", "Rome", "Sparta"});
        JPanel level = newRadioGroup(new String[]{"Easy", "Medium", "Hard"});
        levels = new ArrayList<>();
        levels.add("Easy");
        levels.add("Medium");
        levels.add("Hard");

        JButton newGame = new newButton("Play");
        newGame.setFont(BOLD_LABEL);
        newGame.setForeground(Color.white);
        newGame.setSize(25, 20);
        newGame.setActionCommand("Play");
        newGame.addActionListener(this);

        panelName.setOpaque(false);
        panelCity.setOpaque(false);
        level.setOpaque(false);
        allPanel.setOpaque(false);

        allPanel.add(Box.createVerticalStrut(500));
        allPanel.add(panelName);
        allPanel.add(panelCity);
        allPanel.add(level);
        allPanel.add(newGame);

        this.add(Box.createVerticalStrut(150));
        this.add(allPanel);
        this.validate();
        this.repaint();
    }

    public JPanel newRadioGroup(String[] names) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());
        ButtonGroup cityGroup = new ButtonGroup();
        JLabel cityLabel;
        if(names[0].equals("Cairo"))
            cityLabel = new JLabel("Choose your city:");
        else
            cityLabel = new JLabel("Select your level");

        cityLabel.setFont(BOLD_LABEL);
        cityLabel.setForeground(Color.white);
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
            String selected = selectedRadio.getText();
            if(levels.contains(selected))
            {
                selectedLevel = selected.toLowerCase();
            }
            else{
                cityName = selected;
            }
        } else if (e.getActionCommand().equals("Play")) {
            if (selectedRadio == null) {
                JOptionPane.showMessageDialog(null, "Please Choose a City");
            } else if (validText(playerName)) {
                try {
                    listener.onPlayClicked(selectedLevel);
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
