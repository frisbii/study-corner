import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.ItemListener;
import java.awt.FontMetrics;
import java.awt.Font;

public class TicTacToe extends JPanel implements Gameable { 
// implement itemListener interface 
    // TO DO: be able to select where to put an x through itemlistener
    // TO DO: draw main graphics
    // TO DO: implement play against computer


    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    
    public TicTacToe(){
        
           this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
         
       }

    public static void main(String[] args){
        JFrame frame = new JFrame("TicTacToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TicTacToe mainInstance = new TicTacToe();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);

    }

    public void paintComponent(Graphics g) {
        Color lightPurple = new Color (156, 145, 188);
    g.setColor(lightPurple);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    g.setColor(Color.WHITE);
    Font myFont1 = new Font(Font.MONOSPACED, Font.PLAIN, 30);
    g.setFont(myFont1);
    g.drawString("TicTacToe!", WIDTH/2 - 85, 75);
    g.drawString("Select Squares On The Grid To Place X's!", WIDTH/2 - 360 ,700 );
    int yPlus = HEIGHT/2 - 100;
    int xPlus = WIDTH/2 - 50;
    for (int i = 0; i < 2; i++){
         g.fillRect(360, yPlus, 300, 10);
         g.fillRect(xPlus, 200, 10, 300);
         xPlus += 100;
         yPlus += 100;
    }
    
      
    }
 
//     public void itemStateChanged(ItemEvent event){

//  }
    
}
