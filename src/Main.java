import javax.swing.JFrame;
import com.formdev.flatlaf.*;

public class Main {
    public static final int W = 1600;
    public static final int H = 900;
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            /* JFrame frame = new JFrame();
            frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            frame.pack();
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("✨ study corner 🌇");

            MainPanel mainPanel = new MainPanel();
            frame.setContentPane(mainPanel); */

            // Sets up the custom look and feel to streamline the experience
            // across devices and operating systems
            FlatLightLaf.setup();

            //Create and set up the window.
            JFrame frame = new JFrame("✨ study corner 🌇");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            //Set up the content pane.
            MainPanel mainPanel = new MainPanel();
            frame.setContentPane(mainPanel);
    
            //Size and display the window.
            frame.setSize(W, H);
            frame.setVisible(true);
            frame.setResizable(false);

            //
            AppTheme.loadThemeAssets();
        });
    }
}