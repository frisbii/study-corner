import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
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

        this.setBackground(Color.RED);
        this.setOpaque(true);

        JLabel gb = new JLabel("open games menu");
        gb.setPreferredSize(new Dimension(gbWidth, gbHeight));
        gb.setOpaque(true);
        gb.setFont(Fonts.generateCutiveFont(17, 1));
        gb.setHorizontalAlignment(SwingConstants.CENTER);

        gb.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                GameInfoPanel main = new GameInfoPanel();
                System.out.println("games button clicked");
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
            
        });

        JLabel sb = new JLabel("set");
        sb.setPreferredSize(new Dimension(40, 40));
        sb.setOpaque(true);
        sb.setFont(Fonts.generateCutiveFont(17, 1));
        sb.setHorizontalAlignment(SwingConstants.CENTER);

        this.setLayout(new FlowLayout());
        this.add(gb);
        this.add(sb);


    }
    
}

