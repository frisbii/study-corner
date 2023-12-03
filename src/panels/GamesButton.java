import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;


public class GamesButton extends PanelBase implements MouseListener {

    private static int GB_WIDTH = 190;
    private static int GB_HEIGHT = 40;
    private static Color GB_BGCOLOR = new Color(171, 49, 243, 150);

    public GamesButton() {
        super(GB_WIDTH, GB_HEIGHT, GB_BGCOLOR);

        this.setLayout(new GridBagLayout());
        JLabel gb = new JLabel("open mini-games");
        gb.setFont(Fonts.generateCutiveFont(17, 1));
        gb.setForeground(Color.WHITE);
        gb.addMouseListener(this);
        this.add(gb);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO: add code to open the game picker
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
