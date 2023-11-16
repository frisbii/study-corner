import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ClockPanel extends JPanel {
    private int width = 500;
    private int height = 400;
    private Timer timer;

    public ClockPanel() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                repaint();
            }
        });
        timer.setCoalesce(true);
        timer.setRepeats(true);
        timer.start(); 
    }

    @Override
    public void paintComponent(Graphics g) {
        String time = new SimpleDateFormat("HH:mm.ss").format(new Date());

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.setFont(Fonts.generateCutiveFont(65));
        g.drawString(time, 60, 150);
    }
}
