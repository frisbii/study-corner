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
        Color lightGreen = new Color (160, 228, 144);
    g.setColor(lightGreen);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    g.setColor(Color.BLACK);
    Font myFont1 = new Font(Font.MONOSPACED, Font.PLAIN, 30);
    g.setFont(myFont1);
    g.drawString("TicTacToe!", WIDTH/2 - 120, 75);
    g.drawString("Select Squares On The Grid To Place X's!", WIDTH/2 - 360 ,700 );
    int yPlus = 200;
    int xPlus = 300;
    for (int i = 0; i <= 3; i++){
         g.fillRect(200, yPlus, 500, 10);
         g.fillRect(xPlus, 100, 10, 500);
         yPlus += 100;
         xPlus += 100;
    }
    }
 
//     public void itemStateChanged(ItemEvent event){

//  }
    
}
