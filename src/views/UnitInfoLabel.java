package views;

import javax.swing.*;
import java.awt.*;

public class UnitInfoLabel extends JLabel {
    public UnitInfoLabel(String s){
        super(s);
        this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
