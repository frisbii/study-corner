import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class SpottPanel extends JPanel {
    
    // instance variables
    Spott spott;
    int delay;

    public int width;
    public int height;

    // constructor
    public SpottPanel(int h)
    {
        this.width = Main.WIDTH;
        this.height = h;
        this.setPreferredSize(new Dimension(width, height));
        this.delay = 10;
        this.spott = new Spott();
        
        // creating pauses for Spott
        ActionListener taskPerformer = new ActionListener()
        {
            public void actionPerformed(ActionEvent evt) {
                spott.update(delay);
                repaint();
            }
        };
        Timer timer = new Timer(delay, taskPerformer); // timer recurs every speed number of milliseconds
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        spott.draw(g);
    }
}
