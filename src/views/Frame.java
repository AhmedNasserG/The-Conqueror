package views;

import javax.swing.*;
import java.awt.*;

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

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
