
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.*;

public class Main {
    public static final int W = 1600;
    public static final int H = 900;
    public static void main(String[] args) {
        // Put the program onto Swing's Event Dispatching Thread (EDT) 
        // to properly schedule tasks
        SwingUtilities.invokeLater(() -> {

            // Sets up the custom look and feel to streamline the experience
            // across devices and operating systems
            FlatLightLaf.setup();

            // Create and set up the window.
            JFrame frame = new JFrame("✨ study corner 🌇");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            // Set up the content pane.
            MainPanel mainPanel = new MainPanel();
            frame.setContentPane(mainPanel);
    
            // Loads theme assets into memory, then sets theme of app components
            AppTheme.loadThemeAssets();
            
            // Size and display the window.
            frame.setSize(W, H);
            frame.setVisible(true);
            frame.setResizable(false);

        });
    }
}