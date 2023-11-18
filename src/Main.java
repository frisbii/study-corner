import java.awt.Dimension;
import javax.swing.JFrame;

public class Main {
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            frame.pack();
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("✨ study corner 🌇");

            MainPanel mainPanel = new MainPanel();
            frame.setContentPane(mainPanel);
        });
    }
}