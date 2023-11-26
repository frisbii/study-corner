import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TimerPanel extends JPanel {
    
    private Timer swingTimer;
    private int timerSetting = 60;
    private int timerTimeLeft;

    private JLabel timer;
    private JSpinner timeSetSpinner;
    
    public TimerPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Timer initialization
        this.timerTimeLeft = this.timerSetting;
        this.swingTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (timerTimeLeft == 0) {
                    swingTimer.stop();
                } else {
                    timerTimeLeft--;
                    updateTimerLabel();
                }
            }
        });
        // -----
        
        // ----- Add components to base layout
        this.add(Box.createVerticalGlue());
        // Timer title
        JLabel timerTitle = new JLabel("time left:");
        timerTitle.setFont(Fonts.generateCutiveFont(20));
        this.add(timerTitle);
        // Timer text
        this.timer = new JLabel();
        this.timer.setFont(Fonts.generateCutiveFont(30));
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
        SpinnerModel model = new SpinnerNumberModel(60, 0, 6000, 1);
        this.timeSetSpinner = new JSpinner(model);
        this.timeSetSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSpinner spinner = (JSpinner) (e.getSource());
                timerSetting = (int) spinner.getValue();

                swingTimer.stop();
                timerTimeLeft = timerSetting;
                updateTimerLabel();
            }
        });

        JPanel setWrapper = new JPanel();
        setWrapper.add(new JLabel("set time:"));
        setWrapper.add(this.timeSetSpinner);
        this.add(setWrapper);
        

        // -----

    }

    private void updateTimerLabel() {
        timer.setText(String.format("%d:%02d", timerTimeLeft / 60, timerTimeLeft % 60));
    }
}
