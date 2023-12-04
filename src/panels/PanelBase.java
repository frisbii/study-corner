
import java.awt.Color;

import javax.swing.JPanel;

public abstract class PanelBase extends JPanel {
    
    private int width;
    private int height;

    public Color bgColor;

    public PanelBase(int w, int h, Color c) {
        this.width = w;
        this.height = h;
        this.bgColor = c;

        this.setSize(w, h);
        this.setOpaque(false);
    }

    public int getLowerY() {
        return this.getY() + this.getHeight();
    }

    public void centerAlignHorizontal(int xStart, int xWidth) {
        this.setLocation((int) (xStart + (1.0/2) * (xWidth - this.width)), this.getLocation().y);
    }
    
    public void centerAlignHorizontal(PanelBase pb) {
        this.centerAlignHorizontal(pb.getX(), pb.getWidth());
    }
    
    public void centerAlignHorizontal(int xAxis) {
        this.setLocation((int) (xAxis - (this.getWidth() / 2)), this.getLocation().y);
    }

    public void centerAlignVertical(int yStart, int yHeight) {
        this.setLocation(this.getLocation().x, (int) (yStart + (1.0/2) * (yHeight - this.height)));
    }


}
