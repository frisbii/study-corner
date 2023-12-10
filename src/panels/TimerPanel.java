import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class TimerPanel extends PanelBase {

    private static int TIMER_WIDTH = 600;
    private static int TIMER_HEIGHT = 370;
    private static Color TIMER_BGCOLOR = new Color(255, 255, 255, 150);
    private Sounds sounds;

    public Timer swingTimer;
    public JPanel timerButtons;
    public JLabel timerLabel;
    public JProgressBar timerProgressBar;
    public JButton timerToggleButton;
    public JButton timerResetButton;
    public JSpinner timerSetSpinner;

    public int timerSetting;
    public int secondsLeft;

    public TimerPanel() {
        super(TIMER_WIDTH, TIMER_HEIGHT, TIMER_BGCOLOR);
        //sounds
        sounds = new Sounds();

        // Swing timer creation
        this.timerSetting = 60;
        this.secondsLeft = this.timerSetting;
        this.swingTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                secondsLeft--;
                updateTimer();
                if (secondsLeft <= 0) {
                    stopTimer();
                    playFinishSound();
                }
            }
        });
        

        // Timer button panel
        this.timerButtons = new JPanel();
        this.timerButtons.setLayout(new BoxLayout(this.timerButtons, BoxLayout.Y_AXIS));
        this.timerButtons.setOpaque(false);
        this.timerButtons.add(new TimerButton(5));
        this.timerButtons.add(Box.createRigidArea(new Dimension(0, 15)));
        this.timerButtons.add(new TimerButton(15));
        this.timerButtons.add(Box.createRigidArea(new Dimension(0, 15)));
        this.timerButtons.add(new TimerButton(30));


        // Timer label
        this.timerLabel = new JLabel("test");
        this.timerLabel.setFont(Fonts.generateSpaceMonoFont(70));
        

        // Timer progress bar
            // Set look and feel properties
        UIManager.put("ProgressBar.horizontalSize", new Dimension(250, 10));

        this.timerProgressBar = new JProgressBar();
        this.timerProgressBar.setMinimum(0);
        this.updateTimer();


        // Timer control buttons
        this.timerToggleButton = new JButton("Start");
        this.timerToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (swingTimer.isRunning()) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });
        this.timerResetButton = new JButton("Reset");
        this.timerResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsLeft = timerSetting;
                stopTimer();
                updateTimer();
            }
        });
        
        SpinnerModel model = new SpinnerNumberModel(60, 0, 6000, 1);
        this.timerSetSpinner = new JSpinner(model);
        this.timerSetSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSpinner spinner = (JSpinner) (e.getSource());
                timerSetting = (int) spinner.getValue();
                secondsLeft = timerSetting;
                stopTimer();
                updateTimer();
            }
        });

        JPanel controlButtons = new JPanel();
        controlButtons.setOpaque(false);
        controlButtons.setLayout(new BoxLayout(controlButtons, BoxLayout.X_AXIS));
        controlButtons.add(Box.createHorizontalGlue());
        controlButtons.add(this.timerToggleButton);
        controlButtons.add(Box.createRigidArea(new Dimension(15, 0)));
        controlButtons.add(this.timerResetButton);
        controlButtons.add(Box.createRigidArea(new Dimension(15, 0)));
        controlButtons.add(this.timerSetSpinner);
        controlButtons.add(Box.createHorizontalGlue());
        
        
        // Layout all the above components into the panel
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 4;
        this.add(this.timerButtons, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(Box.createRigidArea(new Dimension(400, 0)), c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(this.timerLabel, c);

        c.gridy = 2;
        this.add(this.timerProgressBar, c);
        
        c.gridy = 3;
        this.add(controlButtons, c);
    }

    public void startTimer() {
        this.swingTimer.start();
        this.timerToggleButton.setText("Stop");
    }
    
    public void stopTimer() {
        this.swingTimer.stop();
        this.timerToggleButton.setText("Start");
    }

    public void setTimer(int minutes, int seconds) {
        this.setTimer(minutes * 60 + seconds);
    }

    public void setTimer(int seconds) {
        this.timerSetting = seconds;
        this.timerSetSpinner.setValue(seconds);
        this.resetTimer();
    }

    public void resetTimer() {
        this.secondsLeft = this.timerSetting;
        this.updateTimer();
        this.stopTimer();
    }

    private void updateTimer() {
        this.timerLabel.setText(String.format("%d:%02d", this.secondsLeft / 60, this.secondsLeft % 60));
        this.timerProgressBar.setMaximum(this.timerSetting);
        this.timerProgressBar.setValue(this.secondsLeft);
    }

    public void playFinishSound() {
        System.out.println("Chime sounds TimerPanel");
        sounds.playChimes();
    }


    class TimerButton extends JLabel implements MouseListener {

        private int TIMERBUTTON_SIZE = 60;

        private Image icon;
        private int duration;

        public TimerButton(int d) {
            this.setOpaque(false);
            this.addMouseListener(this);

            this.duration = d;
            try {
                this.icon = ImageIO.read(new File(String.format("./resources/images/buttons/%d.png", duration)));
                this.icon = this.icon.getScaledInstance(TIMERBUTTON_SIZE, TIMERBUTTON_SIZE, Image.SCALE_SMOOTH);
            } catch (IOException e) { e.printStackTrace(); }
            this.setIcon(new ImageIcon(this.icon));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            setTimer(duration, 0);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
    
}
