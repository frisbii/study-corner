import javax.swing.JFrame;

public class GameInfoPanel {
    //do we want the frame to be the same size or different?
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    // create frame for pop up
    public GameInfoPanel(){
    
    
    JFrame frame = new JFrame("game info");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        
    
            //Size and display the window.
            frame.setSize(WIDTH, HEIGHT);
            frame.setVisible(true);
            frame.setResizable(false);
    }
}
