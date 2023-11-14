import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setVisible(true);
            frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("✨ study corner 🌇");

            MainPanel mainPanel = new MainPanel();
            frame.setContentPane(mainPanel);
        });
    }
}