import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.Timer;

public class ClockPanel extends PanelBase {

    private static int CLOCK_WIDTH = 500;
    private static int CLOCK_HEIGHT = 215;
    private static Color CLOCK_COLOR = new Color(255, 255, 255, 150);

    private Timer clockTimer;
    private JLabel clockLabel;
    private JLabel dateLabel;

    public ClockPanel() {
        super(CLOCK_WIDTH, CLOCK_HEIGHT, CLOCK_COLOR);

        this.clockLabel = new JLabel();
        this.clockLabel.setFont(Fonts.generateSpaceMonoBoldFont(125));
        updateClockLabel();

        this.dateLabel = new JLabel();
        this.dateLabel.setFont(Fonts.generateCutiveFont(30, 3));
        updateDateLabel();

        // Center align the label in the panels
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.insets = new Insets(-15, 0, 0, 0);
        this.add(clockLabel, c);
        c.gridy = 1;
        c.insets = new Insets(-10, 0, 20, 0);
        this.add(dateLabel, c);

        this.clockTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                updateClockLabel();
            }
        });
        this.clockTimer.start();
    }

    private void updateClockLabel() {
        this.clockLabel.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    private void updateDateLabel() {
        this.dateLabel.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("eeee, MMMM d")).toLowerCase());
    }
}
