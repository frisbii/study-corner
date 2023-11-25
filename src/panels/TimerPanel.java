import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerPanel extends JPanel {
    
    private Timer swingTimer;
    private int timerSetting = 60;
    private int timerTimeLeft;

    public JLabel timer;
    
    public TimerPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Timer initialization
        this.timerTimeLeft = this.timerSetting;
        this.swingTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timerTimeLeft--;
                updateTimerLabel();
            }
        });
        // -----
        
        // ----- Add components to base layout
        // Timer title
        JLabel timerTitle = new JLabel("time left:");
        timerTitle.setFont(Fonts.generateCutiveFont(10));
        this.add(timerTitle);
        // Timer text
        this.timer = new JLabel();
        this.updateTimerLabel();
        this.add(this.timer);
        // Timer control buttons
        JButton startButton = new JButton("start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                swingTimer.start();
            }
        });
        JButton stopButton = new JButton("stop");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                swingTimer.stop();
            }
        });
        JButton resetButton = new JButton("reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                swingTimer.stop();
                timerTimeLeft = timerSetting;
                updateTimerLabel();
            }
        });
        JPanel controlWrapper = new JPanel();
        controlWrapper.add(startButton);
        controlWrapper.add(stopButton);
        controlWrapper.add(resetButton);
        this.add(controlWrapper);
        // Timer set buttons
        
        // -----

    }

    private void updateTimerLabel() {
        timer.setText(String.format("%d:%02d", timerTimeLeft / 60, timerTimeLeft % 60));
    }
}
