package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {
    private final int width;
    private final int height;

    public Frame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.width;
        height = screenSize.height;
        setSize(width, height);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

    }

    public Frame(String title) {
        this();
        this.setTitle(title);
    }

    public void setBackground(String pathName) {
        final Image backgroundImage;
        try {
            backgroundImage = ImageIO.read(new File(pathName));
            setContentPane(new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0,getWidth(), getHeight(), null);
                }
            });
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An ERROR Occured while loading the background please restart the game");
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
