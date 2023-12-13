import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

/**
 * Panel on the MainPanel which contains the timer and its associated controls
 */
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

    public PopupPane popupPane;

    /**
     * Constructs the TimerPanel;
     * Sets size/position, initializes timers, adds and lays out components
     */
    public TimerPanel() {
        super(TIMER_WIDTH, TIMER_HEIGHT, TIMER_BGCOLOR);
        // Initialization tasks
        
        
        sounds = new Sounds();

        // Swing timer creation
        this.timerSetting = 60;
        this.secondsLeft = this.timerSetting;
        // Fires every second
        this.swingTimer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                // Decrement the internal count, update the timer
                secondsLeft--;
                updateTimer();
                // If finished, stop the timer and call the timerFinished method
                if (secondsLeft <= 0) {
                    stopTimer();
                    timerFinished();
                }
            }

        });

        

        // Timer button panel
        this.timerButtons = new JPanel();
        this.timerButtons.setLayout(new BoxLayout(this.timerButtons, BoxLayout.Y_AXIS));
        this.timerButtons.setOpaque(false);
        this.timerButtons.add(new TimerButton());
        this.timerButtons.add(Box.createRigidArea(new Dimension(0, 15)));
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
                resetTimer();
            }
        });
        
        // Time set spinner
        SpinnerModel model = new SpinnerNumberModel(60, 1, 6000, 1);
        this.timerSetSpinner = new JSpinner(model);
        this.timerSetSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSpinner spinner = (JSpinner) (e.getSource());
                timerSetting = (int) spinner.getValue();
                resetTimer();
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
        this.add(Box.createRigidArea(new Dimension(400, 20)), c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(this.timerLabel, c);

        c.gridy = 2;
        this.add(this.timerProgressBar, c);
        
        c.gridy = 3;
        c.insets = new Insets(-30, 0, 0, 0);
        this.add(controlButtons, c);

        // Invoked after the component is realized and dimensions are set, in order for the
        // glass pane to appear on top of the panel
        SwingUtilities.invokeLater(this::afterLoad);
    }

    /**
     * Method which initializes and adds the glass pane (for the pop-up) to the panel
     */
    public void afterLoad() {
        this.popupPane = new PopupPane();
        JFrame parent = (JFrame) javax.swing.FocusManager.getCurrentManager().getActiveWindow();
        parent.setGlassPane(this.popupPane);
    }

    /**
     * Start the timer, toggle button text, show pop-up if first run
     */
    public void startTimer() {
        if (this.secondsLeft == this.timerSetting) {
            this.popupPane.showPopup();
        }
        this.swingTimer.start();
        this.timerToggleButton.setText("Stop");
    }
    
    /**
     * Stop the timer, toggle button text
     */
    public void stopTimer() {
        this.swingTimer.stop();
        this.timerToggleButton.setText("Start");
    }

    /**
     * Set the timer, given minutes and seconds
     * @param minutes   Minutes to set the timer to
     * @param seconds   Seconds to set the timer to
     */
    public void setTimer(int minutes, int seconds) {
        this.setTimer(minutes * 60 + seconds);
    }

    /**
     * Set the timer, given seconds
     * @param seconds   Seconds to set the timer to
     */
    public void setTimer(int seconds) {
        this.timerSetting = seconds;
        // In case the timerButtons are used instead of the spinner,
        // update the spinner label
        this.timerSetSpinner.setValue(seconds);
        this.resetTimer();
    }

    /**
     * Reset the timer to its set value, toggle start button, stop timer
     */
    public void resetTimer() {
        this.secondsLeft = this.timerSetting;
        this.timerToggleButton.setEnabled(true);
        this.updateTimer();
        this.stopTimer();
    }

    /**
     * Updates the label's text, used by the swingTimer to draw changes to screen;
     * updates the progress bar
     */
    private void updateTimer() {
        this.timerLabel.setText(String.format("%d:%02d", this.secondsLeft / 60, this.secondsLeft % 60));
        this.timerProgressBar.setMaximum(this.timerSetting);
        this.timerProgressBar.setValue(this.secondsLeft);
    }

    /**
     * Called when the timer finishes; plays sounds, enables start, opens games
     */
    private void timerFinished() {
        sounds.playChimes();
        this.timerToggleButton.setEnabled(false);
        new GameInfoPanel();
    }

    /**
     * Class which creates the image buttons seen in the panel, which set the timer
     * to the labeled minute (or seconds) values.
     */
    class TimerButton extends JLabel implements MouseListener {

        private int TIMERBUTTON_SIZE = 50;

        private Image icon;
        private int durationMins;
        private int durationSecs;

        /**
         * Construct the timer button given a minute duration
         * @param d     Duration in minutes to set
         */
        public TimerButton(int d) {
            this(String.format("./resources/images/buttons/%d.png", d));
            this.durationMins = d;
        }

        /**
         * Constructs the timer button using the default image (10s test button)
         */
        public TimerButton() {
            this("./resources/images/buttons/test.png");
            this.durationSecs = 10;
        }

        /**
         * Private constructor used to construct the button using a passed path.
         * Sets the image of the button.
         * @param path  The path of the button's image file
         */
        private TimerButton(String path) {
            this.setOpaque(false);
            this.addMouseListener(this);

            try {
                this.icon = ImageIO.read(new File(path));
                this.icon = this.icon.getScaledInstance(TIMERBUTTON_SIZE, TIMERBUTTON_SIZE, Image.SCALE_SMOOTH);
            } catch (IOException e) { e.printStackTrace(); }
            this.setIcon(new ImageIcon(this.icon));

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            setTimer(durationMins, durationSecs); // Set timer when button is clicked
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

    /**
     * Class containing the code for the Spott pop-up which
     * plays when the timer is started for the first time 
     * (from a full progress bar)
     */
    class PopupPane extends JComponent {

        private Pair topLeft = new Pair(607, 761);
        private Pair center = new Pair(727, 881);
        private int size = 240;

        private Timer popupTimer;
        private int timerDelay = 20;
        
        private Image popupImage;

        private AffineTransform transform;

        /**
         * Construct the PopupPane
         */
        public PopupPane() {
            
            // Timer used for animation
            this.popupTimer = new Timer(timerDelay, new ActionListener() {
                
                int elapsedFrames;
                int numSpinFrames = 55;

                {
                    elapsedFrames = 0;
                }

                @Override
                public void actionPerformed(ActionEvent ae) {
                    elapsedFrames++;
                    transform = new AffineTransform();
                    // Phase 1: spinning, scaling up from blank
                    if (elapsedFrames < numSpinFrames) {
                        // Scaling factor used to scale and to center the translation
                        double scalingFactor = elapsedFrames * (1.0 / numSpinFrames);

                        // Translate the image to the timerPanel
                        transform.translate(center.x - (scalingFactor * size / 2.0), center.y - (scalingFactor * size / 2.0));
                        // Rotate the image as time passes
                        transform.rotate(elapsedFrames * ((24 * Math.PI) / numSpinFrames), scalingFactor * size / 2.0, scalingFactor * size / 2.0);
                        // Scale the image as time passes
                        transform.scale(scalingFactor, scalingFactor);
                    // Phase 2: linger on screen
                    } else if (elapsedFrames < 100) {
                        // Position the image in the timerPanel
                        transform = AffineTransform.getTranslateInstance(topLeft.x, topLeft.y);
                    // Phase 3: hide
                    } else {
                        setVisible(false);
                        popupTimer.stop();
                        elapsedFrames = 0;
                        transform = null;
                    }
                }

            });

            // Load the popup image
            try{
                this.popupImage = ImageIO.read(new File("./resources/images/spott/spott_popup.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            if (this.transform != null) {
                // Apply the transform set in the timer and then draw
                g2.setTransform(this.transform);
                g2.drawImage(this.popupImage, 0, 0, null);
            }
        }

        /**
         * Start the animation for the popup
         */
        public void showPopup() {
            this.popupTimer.start();
            this.setVisible(true);
        }

        
    }
    
}