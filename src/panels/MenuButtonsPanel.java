import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
        gb.setBackground(Util.setAlpha(AppColors.PRIMARY, 150));
        gb.setOpaque(true);
        gb.setFont(Fonts.generateCutiveFont(17, 1));
        gb.setHorizontalAlignment(SwingConstants.CENTER);
        gb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameInfoPanel();
                System.out.println("games button clicked");
            }
        });
        
        JLabel sb = new JLabel();
        sb.setPreferredSize(new Dimension(sbWidth, sbWidth));
        sb.setBackground(Color.LIGHT_GRAY);
        sb.setOpaque(true);
        try {
            Image icon = ImageIO.read(new File("./resources/images/buttons/settings.png"));
            icon = icon.getScaledInstance(sbWidth, sbWidth, Image.SCALE_SMOOTH);
            sb.setIcon(new ImageIcon(icon));
        } catch (IOException e) { e.printStackTrace(); }
        sb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SettingsFrame();
            }
        });

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.add(gb);
        this.add(sb);


    }
    
}

