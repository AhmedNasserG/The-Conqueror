package views;

import java.awt.event.ActionListener;
import javax.swing.*;

public class SplashScreen extends Frame {

    private static SplashScreen splashScreen;
    private static int count = 1, TIMER_PAUSE = 25, PROGBAR_MAX = 100;
    private static Timer progressBarTimer;
    ActionListener al = new ActionListener() {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            System.out.println(count);
            if (PROGBAR_MAX == count) {
                splashScreen.dispose();
                progressBarTimer.stop();
                dispose();
            }
            count++;
        }
    };

    public SplashScreen(String backgroundPath) {
        splashScreen = this;
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                setBackground(backgroundPath);
                ImageIcon icon = new ImageIcon("temp_res/imgs/loader2.gif");
                JLabel label = new JLabel(icon);
                add(label);
                setVisible(true);
                startProgressBar();
            }
        });
    }

    private void startProgressBar() {
        progressBarTimer = new Timer(TIMER_PAUSE, al);
        progressBarTimer.start();
    }

    public static void main(String[] args) {
        new SplashScreen("temp_res/imgs/backgrounds/sparta.jpg");
    }
}
