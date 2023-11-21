import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JPanel implements Gameable, ActionListener { 

    // TO DO: be able to select where to put an x through itemlistener
    // TO DO: fix layout with gridbag layout
    // TO DO: implement play against computer


    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    static final String topLeftText = "X";
    static final String topLeftName = "top left button";
    static final String topMiddleText = "X";
    static final String topMiddleName = "top middle button";
    static final String topRightText = "X";
    static final String topRightName = "top right button";
    static final String middleLeftText = "X";
    static final String middleLeftName = "middle left button";
     static final String middleMiddleText = "X";
    static final String middleMiddleName = "middle middle button";
     static final String middleRightText = "X";
    static final String middleRightName = "middle right button";
    static final String bottomLeftText = "X";
    static final String bottomLeftName = "bottom left button";
     static final String bottomMiddleText = "X";
    static final String bottomMiddleName = "bottom middle button";
     static final String bottomRightText = "X";
    static final String bottomRightName = "bottom right button";

      public static void show(JPanel panel){
        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setSize(600,600);
        frame.setVisible(true);
    }

    public TicTacToe(){
        JLabel title = new JLabel("Tic-Tac-Toe!");
        add(title);
        JButton topLeft = new JButton(topLeftText);
        topLeft.setName(topLeftName);
        add(topLeft);
        JButton topMiddle = new JButton(topMiddleText);
        topLeft.setName(topMiddleName);
        add(topMiddle);
        JButton topRight = new JButton(topRightText);
        topLeft.setName(topRightName);
        add(topRight);
        JButton middleLeft = new JButton(middleLeftText);
        topLeft.setName(middleLeftName);
        add(middleLeft);
         JButton middleMiddle = new JButton(middleMiddleText);
        topLeft.setName(middleMiddleName);
        add(middleMiddle);
        JButton middleRight = new JButton(middleRightText);
        topLeft.setName(middleRightName);
        add(middleRight);
        JButton bottomLeft = new JButton(bottomLeftText);
        topLeft.setName(bottomLeftName);
        add(bottomLeft);
         JButton bottomMiddle = new JButton(bottomMiddleText);
        topLeft.setName(bottomMiddleName);
        add(bottomMiddle);
         JButton bottomRight = new JButton(bottomRightText);
        topLeft.setName(bottomRightName);
        add(bottomRight);

        //   this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //   topLeft = new JButton("X");
        //   topLeft.setVisible(true);
        //   topLeft.setBounds(146, 84, 85, 21);
        //   topLeft.addActionListener(this);
          
       }

    public static void main(String[] args){
         show(new TicTacToe());

    }

  

    public void paintComponent(Graphics g) {
        Color lightPurple = new Color (156, 145, 188);
    g.setColor(lightPurple);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    // g.setColor(Color.WHITE);
    // Font myFont1 = new Font(Font.MONOSPACED, Font.PLAIN, 30);
    // g.setFont(myFont1);
    // g.drawString("TicTacToe!", WIDTH/2 - 85, 75);
    // g.drawString("Select Squares On The Grid To Place X's!", WIDTH/2 - 360 ,700 );
    // int yPlus = HEIGHT/2 - 100;
    // int xPlus = WIDTH/2 - 50;
    // for (int i = 0; i < 2; i++){
    //      g.fillRect(360, yPlus, 300, 10);
    //      g.fillRect(xPlus, 200, 10, 300);
    //      xPlus += 100;
    //      yPlus += 100;
   // }
    
      
    }
 
    public void actionPerformed(ActionEvent e){

 }
    
}
