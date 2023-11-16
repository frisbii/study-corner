import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerPanel extends JPanel {
    private int width = 500;
    private int height = 400;
    
    public int secondsLeft;
    private Timer swingTimer;
    
    public TimerPanel() {
        this.secondsLeft = 80;

        this.swingTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                secondsLeft--;
                repaint();
            }
        });
        this.swingTimer.setCoalesce(true);
        this.swingTimer.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.setFont(Fonts.generateCutiveFont(65));
        String formattedTime = String.format("%d:%02d", secondsLeft/60, secondsLeft % 60);
        g.drawString(formattedTime, 60, 150);
    }
}
