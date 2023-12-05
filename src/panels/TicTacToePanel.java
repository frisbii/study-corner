import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToePanel extends JPanel { 

    // why does the middle and bottom left not appear??
    // why do the middle and bottom right appear at the top??
    // TO DO: implement play against computer
    // for play against computer:
    // make board array 
    // need to traverse the board to get the current state
    // need to search future states
    // determine best state and play move
    // need to make the program also sleep for a second to not make it instantaneous
    // need to check the board state before and after move to determine if game is over 

   JPanel overall;
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    static final String topLeftText = "";
    static final String topLeftName = "top left button";
    static final String topMiddleText = "";
    static final String topMiddleName = "top middle button";
    static final String topRightText = "";
    static final String topRightName = "top right button";
    static final String middleLeftText = "";
    static final String middleLeftName = "middle left button";
     static final String middleMiddleText = "";
    static final String middleMiddleName = "middle middle button";
     static final String middleRightText = "";
    static final String middleRightName = "middle right button";
    static final String bottomLeftText = "";
    static final String bottomLeftName = "bottom left button";
     static final String bottomMiddleText = "";
    static final String bottomMiddleName = "bottom middle button";
     static final String bottomRightText = "";
    static final String bottomRightName = "bottom right button";
    static final String topLeftRevealText = "X";
    

    public JButton topLeft;
    public JButton topMiddle;
    public JButton topRight;
    public JButton middleLeft;
    public JButton middleMiddle;
    public JButton middleRight;
    public JButton bottomLeft;
    public JButton bottomMiddle;
    public JButton bottomRight;
    public JLabel topLeftReveal;
    public JLabel topMiddleReveal;
    public JLabel topRightReveal;
    public JLabel middleLeftReveal;
    public JLabel middleMiddleReveal;
    public JLabel middleRightReveal;
    public JLabel bottomLeftReveal;
    public JLabel bottomMiddleReveal;
    public JLabel bottomRightReveal;

    JLabel [] board = new JLabel[] {topLeftReveal, topMiddleReveal, topRightReveal, middleLeftReveal, middleMiddleReveal, middleRightReveal,
    bottomLeftReveal, bottomMiddleReveal, bottomRightReveal};
 
    Boolean [] currentState = new Boolean[9];
    JLabel [] nextState = new JLabel [9]; 


    //   public static void show(JPanel panel){
    //     JFrame frame = new JFrame("Tic-Tac-Toe");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.getContentPane().add(panel);
    //     frame.setSize(600,600);
    //     frame.setVisible(true);
    // }

    public TicTacToePanel(){
       overall = new JPanel();
       overall = createFieldsPanel();
     topLeft.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          topLeftReveal.setVisible(true);
          topLeft.setVisible(false);
        }
     }
     );

     topMiddle.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          topMiddleReveal.setVisible(true);
          topMiddle.setVisible(false);
          nextMove();
        }
     }
     );

     topRight.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          topRightReveal.setVisible(true);
          topRight.setVisible(false);
          nextMove();
        }
     }
     );

     middleLeft.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          middleLeftReveal.setVisible(true);
          middleLeft.setVisible(false);
          System.out.println("middleLeft");
          nextMove();
        }
     }
     );

     middleMiddle.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          middleMiddleReveal.setVisible(true);
          middleMiddle.setVisible(false);
          nextMove();
        }
     }
     );
     
     middleRight.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          middleRightReveal.setVisible(true);
          middleRight.setVisible(false);
          System.out.println("middle right");
          nextMove();
        }
     }
     );

     bottomLeft.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          bottomLeftReveal.setVisible(true);
          bottomLeft.setVisible(false);
          System.out.println("bottomLeft");
          nextMove();
        }
     }
     );

     bottomMiddle.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          bottomMiddleReveal.setVisible(true);
          bottomMiddle.setVisible(false);
          nextMove();
        }
     }
     );
     
     bottomRight.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          bottomRightReveal.setVisible(true);
          bottomRight.setVisible(false);
          System.out.println("bottomRight");
          nextMove();
        }
     }
     );
     

       }

       public void nextMove(){

        for (int i = 0; i < board.length; i++) {
          if (board[i].isVisible() == true){
            currentState[i] = true;
          } else {
            currentState[i] = false;
          }
          System.out.println(currentState[i]);
        }

       }

       public JPanel createFieldsPanel(){

      
       GridBagLayout layout = new GridBagLayout();

       JPanel panel = new JPanel(layout);

       Color lightPurple = new Color (156, 145, 188);
       panel.setBackground(lightPurple);

       JLabel title = new JLabel("Tic-Tac-Toe!");
       JLabel directions = new JLabel("Select Squares on the Grid to Place X's!");
       
        topLeft = new JButton(topLeftText);
        topLeft.setName(topLeftName);
         

        topLeftReveal = new JLabel(topLeftRevealText);
        topLeftReveal.setFont(new Font("Calibri", Font.BOLD, 60));

         topMiddle = new JButton(topMiddleText);
        topMiddle.setName(topMiddleName);

        topMiddleReveal = new JLabel("X");
        topMiddleReveal.setFont(new Font("Calibri", Font.BOLD, 60));

         topRight = new JButton(topRightText);
        topRight.setName(topRightName);

        topRightReveal = new JLabel("X");
        topRightReveal.setFont(new Font("Calibri", Font.BOLD, 60));
     
        middleLeft = new JButton(middleLeftText);
        middleLeft.setName(middleLeftName);

        middleLeftReveal = new JLabel("X");
        middleLeftReveal.setFont(new Font("Calibri", Font.BOLD, 60));
      
         middleMiddle = new JButton(middleMiddleText);
        middleMiddle.setName(middleMiddleName);

          middleMiddleReveal = new JLabel("X");
        middleMiddleReveal.setFont(new Font("Calibri", Font.BOLD, 60));
        
        middleRight = new JButton(middleRightText);
        middleRight.setName(middleRightName);

          middleRightReveal = new JLabel("X");
        middleRightReveal.setFont(new Font("Calibri", Font.BOLD, 60));
       
         bottomLeft = new JButton(bottomLeftText);
        bottomLeft.setName(bottomLeftName);

        bottomLeftReveal = new JLabel("X");
        bottomLeftReveal.setFont(new Font("Calibri", Font.BOLD, 60));
       
        
         bottomMiddle = new JButton(bottomMiddleText);
        bottomMiddle.setName(bottomMiddleName);

         bottomMiddleReveal = new JLabel("X");
        bottomMiddleReveal.setFont(new Font("Calibri", Font.BOLD, 60));
      
         bottomRight = new JButton(bottomRightText);
        bottomRight.setName(bottomRightName);

         bottomRightReveal = new JLabel("X");
        bottomRightReveal.setFont(new Font("Calibri", Font.BOLD, 60));

        layout.setConstraints(topLeft, new GridBagConstraints(
            30, 40,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,40,3,3),
            0 , 0
        ));

        layout.setConstraints(topLeftReveal, new GridBagConstraints(
            30, 40,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,40,3,3),
            0 , 0
        ));

        layout.setConstraints(topMiddle, new GridBagConstraints(
            40, 40,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,40,3,40),
            0 , 0
        ));

        layout.setConstraints(topMiddleReveal, new GridBagConstraints(
            40, 40,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,40,3,40),
            0 , 0
        ));

        layout.setConstraints(topRight, new GridBagConstraints(
            50, 40,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,40,20,40),
            0 , 0
        ));

         layout.setConstraints(topRightReveal, new GridBagConstraints(
            50, 40,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,40,20,40),
            0 , 0
        ));

        layout.setConstraints(middleLeft, new GridBagConstraints(
            30, 50,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,40,3,40),
            0 , 0
        ));

        layout.setConstraints(middleLeftReveal, new GridBagConstraints(
            30, 50,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,40,3,40),
            0 , 0
        ));

        layout.setConstraints(middleMiddle, new GridBagConstraints(
            40, 50,
        5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,3,3,3),
            0 , 0
        ));

        layout.setConstraints(middleMiddleReveal, new GridBagConstraints(
            40, 50,
        5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,3,3,3),
            0 , 0
        ));

        layout.setConstraints(middleRight, new GridBagConstraints(
            50, 50,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,3,3,3),
            0 , 0
        ));

         layout.setConstraints(middleRightReveal, new GridBagConstraints(
            50, 50,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,3,3,3),
            0 , 0
        ));

         layout.setConstraints(bottomLeft, new GridBagConstraints(
            30, 70,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,3,3,3),
            0 , 0
        ));

        layout.setConstraints(bottomLeftReveal, new GridBagConstraints(
            30, 70,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,3,3,3),
            0 , 0
        ));

        layout.setConstraints(bottomMiddle, new GridBagConstraints(
            40, 70,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,3,3,3),
            0 , 0
        ));

        layout.setConstraints(bottomMiddleReveal, new GridBagConstraints(
            40, 70,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,3,3,3),
            0 , 0
        ));

        layout.setConstraints(bottomRight, new GridBagConstraints(
            50, 70,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,3,3,3),
            0 , 0
        ));

        layout.setConstraints(bottomRightReveal, new GridBagConstraints(
            50, 70,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,3,3,3),
            0 , 0
        ));

        layout.setConstraints(title, new GridBagConstraints(
            40, 10,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,3,3,3),
            0 , 0
        ));

        layout.setConstraints(directions, new GridBagConstraints(
            40, 90,
            5, 5,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,3,3,3),
            0 , 0
        ));


        panel.add(title);
        panel.add(directions);
        panel.add(topLeft);
        panel.add(topMiddle);
        panel.add(topRight);
        panel.add(middleLeft);
        panel.add(middleMiddle);
        panel.add(middleRight);
        panel.add(bottomLeft);
        panel.add(bottomMiddle);
        panel.add(bottomRight);
        panel.add(topLeftReveal);
        panel.add(topMiddleReveal);
        panel.add(topRightReveal);
        panel.add(middleRightReveal);
        panel.add(middleMiddleReveal);
        panel.add(middleRightReveal);
        panel.add(bottomRightReveal);
        panel.add(bottomMiddleReveal);
        panel.add(bottomRightReveal);


        topLeftReveal.setVisible(false);
        topMiddleReveal.setVisible(false);
        topRightReveal.setVisible(false);
        middleLeftReveal.setVisible(false);
        middleMiddleReveal.setVisible(false);
        middleRightReveal.setVisible(false);
        bottomLeftReveal.setVisible(false);
        bottomMiddleReveal.setVisible(false);
        bottomRightReveal.setVisible(false);
       
       makePurple(topLeft);
       makePurple(topMiddle);
       makePurple(topRight);
       makePurple(middleLeft);
       makePurple(middleMiddle);
       makePurple(middleRight);
       makePurple(bottomLeft);
       makePurple(bottomMiddle);
       makePurple(bottomRight);

        return panel;
       }

       public static void makePurple(JButton j){
        Color lightPurple = new Color (156, 145, 188);
        j.setBackground(lightPurple);
        j.setOpaque(true);
        j.setBorderPainted(true);
        j.setBorder(BorderFactory.createLineBorder(Color.black));

       }

    // public static void main(String[] args){
    //      TicTacToePanel mainInstance = new TicTacToePanel();
    //      show(mainInstance.overall);

    // }

 

    
}
