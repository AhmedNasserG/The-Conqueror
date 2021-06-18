package views;

import listeners.NewGameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class NewGameView extends JFrame implements ActionListener {
    //TODO: We need to unHardCode the cities part so that if we have more than those three cities.
    private JRadioButton[] newRadios;
    private JTextField nameTextField;
    private String playerName;
    private String cityName;
    private NewGameListener listener;

    public NewGameView() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);

        this.setTitle("Choosing City");
        this.setVisible(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JPanel panelName = new JPanel();
        panelName.setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel("Enter your name: ");
        nameTextField = new JTextField(15);
        panelName.add(nameLabel);
        panelName.add(nameTextField);

        JPanel panelCity = newRadioGroup(new String[]{"Cairo", "Rome", "Sparta"});
        JButton newGame = new JButton("Play");
        newGame.setSize(15, 20);
        newGame.setActionCommand("Play");
        newGame.addActionListener(this);

        add(panelName);
        add(panelCity);
        add(newGame);
        this.validate();
        this.repaint();
    }

    public JPanel newRadioGroup(String[] names) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());
        ButtonGroup cityGroup = new ButtonGroup();
        JLabel cityLabel = new JLabel("Choose your city:");
        newPanel.add(cityLabel);
        newRadios = new JRadioButton[names.length];
        for (int i = 0; i < newRadios.length; i++) {
            newRadios[i] = new JRadioButton(names[i]);
            newRadios[i].addActionListener(this);
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
        //TODO: handle if no radio is selected
        playerName = nameTextField.getText();
        if (e.getSource() instanceof JRadioButton) {
            JRadioButton selectedRadio = (JRadioButton) e.getSource();
            cityName = selectedRadio.getText();
        } else if (e.getActionCommand().equals("Play")) {
            if (validText(playerName)) {
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
        } else if (text.length() > 20) {
            JOptionPane.showMessageDialog(null, "Can you please choose a simpler name ?");
            return false;
        }
        return true;
    }
}
