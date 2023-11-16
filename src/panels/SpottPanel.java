import java.awt.event.*;
import javax.swing.*;

public class SpottPanel extends JPanel {
    Spott spott;
    int delay;
    World world;

    public SpottPanel(int d)
    {
        spott = new Spott(new Pair(d, d), new Pair(d, d));
        
        // creating pauses for Spott
        delay = d;
        ActionListener taskPerformer = new ActionListener()
        {
            public void actionPerformed(ActionEvent evt) {
                spott.roaming(world, 1000000);
                repaint();
            }
        };
        Timer timer = new Timer(delay, taskPerformer); // timer recurs every speed number of milliseconds
        timer.start();
    }
}
