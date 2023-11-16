import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;

public class SpottPanel extends JPanel {
    
    // instance variables
    Spott spott;
    int length;
    int height;
    int delay;

    // constructor
    public SpottPanel(int x, int y, int d)
    {
        spott = new Spott(new Pair(0, 860), new Pair(20, 0), 20);
        
        length = x;
        height = y;

        // creating pauses for Spott
        delay = d; // TODO: randomize delay to have more organic movement
        ActionListener taskPerformer = new ActionListener()
        {
            public void actionPerformed(ActionEvent evt) {
                spott.roaming(1000000);
                repaint();
            }
        };
        Timer timer = new Timer(delay, taskPerformer); // timer recurs every speed number of milliseconds
        timer.start();
    }

    // graphics
    public void drawShapes(Graphics g)
    {
        spott.tempDraw(g);
        // TODO: implement
        // again, should be different from the one in Pong.java --> we're importing our own graphics
    }

    // movement
    public void updateShapes(double time)
    {
        spott.roaming(time);
    }
}
