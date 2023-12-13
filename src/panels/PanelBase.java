
import java.awt.Color;

import javax.swing.JPanel;

/**
 * PanelBase is extended by most panels rendered onto the MainPanel screen.
 * Useful in the context of absolute positioning, and provides useful
 * methods for manipulating and laying out other panels.
 */
public abstract class PanelBase extends JPanel {
    
    private int width;
    private int height;

    public Color bgColor;

    /**
     * Constructs the panelBase object, setting parameters
     * used in various places throughout the code
     * 
     * @param w   width of the panel
     * @param h   height of the panel
     * @param c   color of the background rectangle
     */
    public PanelBase(int w, int h, Color c) {
        // These variables should be declared as static, final field variables
        // in each panel that extends PanelBase
        this.width = w;
        this.height = h;
        this.bgColor = c;

        this.setSize(w, h);
        this.setOpaque(false);
    }

    /**
     * Sets the x location of the panel, while keeping the
     * y location the same.
     * 
     * @param x   desired x-coordinate of the top-left corner of the panel
     */
    public void setX(int x) {
        this.setLocation(x, this.getY());
    }

    /**
     * Sets the y location of the panel, while keeping the
     * s location the same.
     * 
     * @param y   desired y-coordinate of the top-left corner of the panel
     */
    public void setY(int y) {
        this.setLocation(this.getX(), y);
    }

    /**
     * Returns the lower y coordinate of this panel
     * 
     * @return the lower y coordinate of this panel
     */
    public int getLowerY() {
        return this.getY() + this.getHeight();
    }

    /**
     * Centers this panel horizontally relative to another object's
     * left x-coordinate and width
     * 
     * @param xStart  the relative object's left x-coordinate
     * @param xWidth  the relative object's width
     */
    public void centerAlignHorizontal(int xStart, int xWidth) {
        this.setLocation((int) (xStart + (1.0/2) * (xWidth - this.width)), this.getLocation().y);
    }
    
    /**
     * Centers this panel horizontally relative to another panel
     * @param pb    the panel to which to center the target panel
     */
    public void centerAlignHorizontal(PanelBase pb) {
        this.centerAlignHorizontal(pb.getX(), pb.getWidth());
    }
    
    /**
     * Centers this panel horizontally around a given x-coordinate
     * @param xCoord    the x-coordinate around which to center the target panel
     */
    public void centerAlignHorizontal(int xCoord) {
        this.setLocation((int) (xCoord - (this.getWidth() / 2)), this.getLocation().y);
    }

    /**
     * Centers this panel horizontally relative to another object's
     * upper y-coordinate and height
     * 
     * @param yStart  the relative object's left y-coordinate
     * @param yHeight  the relative object's height
     */
    public void centerAlignVertical(int yStart, int yHeight) {
        this.setLocation(this.getLocation().x, (int) (yStart + (1.0/2) * (yHeight - this.height)));
    }


}
