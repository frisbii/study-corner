import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ClockPanel extends JPanel {
    private int width = 500;
    private int height = 400;
    private Timer timer;

    private JLabel clockLabel;

    public ClockPanel() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                updateClockLabel();
            }
        });
        timer.setCoalesce(true);
        timer.setRepeats(true);
        timer.start(); 
        
        this.setLayout(new BorderLayout());
        this.clockLabel = new JLabel();
        this.clockLabel.setFont(Fonts.generateCutiveFont(70));
        this.clockLabel.setHorizontalAlignment(JLabel.CENTER);
        this.updateClockLabel();
        this.add(clockLabel, BorderLayout.CENTER);
    }

    private void updateClockLabel() {
        this.clockLabel.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }

}
