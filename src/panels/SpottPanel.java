import java.awt.event.*;
import java.io.File;

import java.awt.Image;
import javax.imageio.ImageIO;
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
        //g.setColor(Color.WHITE);
        //g.fillRect(0, 0, width, height);
        try{
            Image background = ImageIO.read(new File("./resources/images/background_bottom.png"));
            g.drawImage(background, 0, 0, 1600, 150, null);
        }
        catch(Exception e){System.out.println("Error with background: " + e);}
        spott.draw(g);
    }
}
