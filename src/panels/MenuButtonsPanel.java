import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MenuButtonsPanel extends PanelBase {

    private static int gbWidth = 210;
    private static int gbHeight = 40;
    private static int sbWidth = gbHeight;

    private static int MB_WIDTH = gbWidth + sbWidth;
    private static int MB_HEIGHT = gbHeight;
    private static Color MB_BGCOLOR = Color.WHITE;

    public MenuButtonsPanel() {
        super(MB_WIDTH, MB_HEIGHT, MB_BGCOLOR);

        JLabel gb = new JLabel("open games menu");
        gb.setPreferredSize(new Dimension(gbWidth, gbHeight));
        gb.setOpaque(true);
        gb.setFont(Fonts.generateCutiveFont(17, 1));
        gb.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(gb);

        this.setBackground(Color.RED);
        this.setOpaque(true);

    }
    
}

