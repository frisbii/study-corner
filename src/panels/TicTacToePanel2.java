import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Overall class to to create the panel to display the tic tac toe game
 */
public class TicTacToePanel2 extends JPanel {
   
    // Fields for the main panel
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static int FPS = 60;

    public static boolean gameDone;

    /**
     * Constructor to establish object, create pop up message, and make main panel
     */
    static TicTacToeGamePanel ticTacToeTime;

    public TicTacToePanel2(){
        gameDone = false;
        ticTacToeTime = new TicTacToeGamePanel();
     
        Color lightPurple = new Color (156, 145, 188);
        this.setBackground(lightPurple);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        this.add(ticTacToeTime);

        JOptionPane.showMessageDialog(null, "Choose squares on the grid to make your move and get three in a row!");
    }
}

/**
 * Class to create game panel for the board
 * Establishes 2d array of cells
 * creates methods to use on the grid of cells
 */
class TicTacToeGamePanel extends JPanel{

    public static final int width = 525;
    public static final int height = 525;

    /** 
     * Creating fields to store cells and check current game conditions
     */
    TicTacToeCell[][] cellsTicTac;
    int gridSize;
    int squareCol; 
    int squareRow;
    boolean won;
    boolean lost;
    boolean tie;
    boolean notThinking = true;

    public Timer responseTimer;
 
    /**
     * Constructor to set intial game conditions and construct initial grid
     */
    public TicTacToeGamePanel(){
        gridSize = 3;
        won = false;
        lost = false;
        tie = false;

        cellsTicTac = new TicTacToeCell[gridSize][gridSize];
        setValues();

        //arrange cells in game panel
        setLayout(new GridLayout(gridSize, gridSize,5,5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setBackground(Color.BLACK);
        setPreferredSize(new java.awt.Dimension(width, height));
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                this.add(cellsTicTac[i][j]);
            }
        }

        // this.responseTimer = new Timer(1000, new ActionListener() {

        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         notThinking = true;
        //         setResponse();
        //         responseTimer.stop();
                
        //     }
            
        // });
    }

    /**
     * Method establishes all cells of the grid as blank to start and reset the game
     */

    public void setValues(){
        
        //set default values of every cell to 0 
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                cellsTicTac[i][j] = new TicTacToeCell(i, j);
                cellsTicTac[i][j].setValue(0);
            }
        }
}

/**
 * Implements AI for user to play against computer
 * selects random square values to place response
 * if the square is already filled, reselect value
 */
public void setResponse(){

  squareCol = (int) (Math.random() * 3);
  squareRow = (int) (Math.random() * 3);

  while (cellsTicTac[squareRow][squareCol].value != 0){
     squareCol = (int) (Math.random() * 3);
     squareRow = (int) (Math.random() * 3);
  }
  
    cellsTicTac[squareRow][squareCol].setValue(2);
}

/**
 * Checks each value of the grid to determine how many moves are left
 * @return full if the board has more than two spaces left to place values
 */
  public boolean checkBoardFull(){
    boolean full = true;
    int spaceLeft = 0;
    for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if (cellsTicTac[i][j].value == 0){
                    spaceLeft ++;
                }
            }
        }

        if (spaceLeft <= 2){
          full = false;
        }

        return full;
  }

/**
 * Checks if any move has been placed on the board
 * If so, response is able to be placed
 * @return if the board contains any square with an X value
 */
  public boolean containsX(){
    boolean xContained = false;
    for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if (cellsTicTac[i][j].value == 1){
                    xContained = true;
                }
            }
        }

        return xContained;
  }
  

  public void isSolved(){
    // check rows
    int rowXCount = 0;
    int rowOCount = 0;
    for(int row = 0; row < gridSize; row++){
            for(int col = 0; col < cellsTicTac[row].length; col++){
                if (cellsTicTac[row][col].value == 1){
                    rowXCount++;
                }
                if (cellsTicTac[row][col].value == 2){
                    rowOCount++;
                }
                if(rowXCount == 3){
                    won = true;
                     System.out.println("won row");
                }
                if(rowOCount == 3){
                    lost = true;
                    System.out.println("lost row");
                }
            }
            rowXCount = 0;
            rowOCount = 0;
        }
    // check columns
    int colXCount = 0;
    int colOCount = 0;
    for(int col = 0; col < cellsTicTac[0].length; col++){
            for(int row = 0; row < gridSize; row++){
                if (cellsTicTac[row][col].value == 1){
                    colXCount++;
                }
                if (cellsTicTac[row][col].value == 2){
                    colOCount++;
                }
                if(colXCount == 3){
                    won = true;
                     System.out.println("won col");
                }
                if(colOCount == 3){
                    lost = true;
                    System.out.println("lost col");
                }
            }
            colXCount = 0;
            colOCount = 0;
        }
    
        // check diagonal 

    if(cellsTicTac[0][0].value == 1 && cellsTicTac[1][1].value == 1 && cellsTicTac[2][2].value == 1){
        won = true;
        System.out.print("diagonal win");
    }

    if(cellsTicTac[0][0].value == 2 && cellsTicTac[1][1].value == 2 && cellsTicTac[2][2].value == 2){
        lost = true;
        System.out.print("diagonal lose");
    }

    if(cellsTicTac[0][2].value == 1 && cellsTicTac[1][1].value == 1 && cellsTicTac[2][0].value == 1){
        won = true;
        System.out.print("diagonal win");
    }

    if(cellsTicTac[0][2].value == 2 && cellsTicTac[1][1].value == 2 && cellsTicTac[2][0].value == 2){
        lost = true;
        System.out.print("diagonal lose");
    }

    // check tie
    int fullBoard = 0;
    for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if (cellsTicTac[i][j].value == 0){
                    fullBoard ++;
                }
            }
        }
        if (fullBoard == 0 && lost == false && won == false){
           tie = true;
           System.out.println("tie");
        }
  }
}


class TicTacToeCell extends JPanel implements MouseListener{

    public static final int width = 125;
    public static final int height = 125;

    boolean mouseInCell;
    int value;
    JLabel valueText;
    boolean notClicked = true;

    static int valueToChangeTo;

    public TicTacToeCell (int r, int c){
        mouseInCell = false;
        addMouseListener(this);

        this.setMaximumSize(new java.awt.Dimension(width, height));
        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout());

        valueText = new JLabel(" ");
        valueText.setFont(Fonts.generateCutiveFont(100, 28));
        valueText.setForeground(Color.BLACK);
        this.add(valueText);
        valueText.setVisible(true);
    }

    public void setValue(int value){
        this.value = value;
        if(value == 1) {
         valueText.setText("X");
         notClicked = false;
        } 
         else if (value == 2) {
            valueText.setText("O");
            notClicked = false;
        } else { 
            valueText.setText(" ");
        }
        this.revalidate();
        this.repaint();
    }

   public void endGame(){
    TicTacToePanel2.ticTacToeTime.isSolved();

        if(TicTacToePanel2.ticTacToeTime.won){
            JOptionPane.showMessageDialog(null, "Congrats! You Won!");
                      for(int i = 0; i < TicTacToePanel2.ticTacToeTime.gridSize; i++){
                         for(int j = 0; j < TicTacToePanel2.ticTacToeTime.gridSize; j++){
                             TicTacToePanel2.ticTacToeTime.cellsTicTac[i][j].setValue(0);
                             TicTacToePanel2.ticTacToeTime.cellsTicTac[i][j].notClicked = true;
                        }
                }
             TicTacToePanel2.ticTacToeTime.won = false;
             TicTacToePanel2.gameDone = true;
                
        }
        if(TicTacToePanel2.ticTacToeTime.lost){
            JOptionPane.showMessageDialog(null, "Sorry You Lost! Try Again Next Time!");
                for(int i = 0; i < TicTacToePanel2.ticTacToeTime.gridSize; i++){
                    for(int j = 0; j < TicTacToePanel2.ticTacToeTime.gridSize; j++){
                         TicTacToePanel2.ticTacToeTime.cellsTicTac[i][j].setValue(0);
                         TicTacToePanel2.ticTacToeTime.cellsTicTac[i][j].notClicked = true;
                    }
                }
            TicTacToePanel2.ticTacToeTime.lost = false;
            System.out.println("lost the game");
            TicTacToePanel2.gameDone = true;

        } 
        if(TicTacToePanel2.ticTacToeTime.tie) {
            JOptionPane.showMessageDialog(null, "It's a Tie!");
                for(int i = 0; i < TicTacToePanel2.ticTacToeTime.gridSize; i++){
                    for(int j = 0; j < TicTacToePanel2.ticTacToeTime.gridSize; j++){
                         TicTacToePanel2.ticTacToeTime.cellsTicTac[i][j].setValue(0);
                         TicTacToePanel2.ticTacToeTime.cellsTicTac[i][j].notClicked = true;
                    }
                }
            TicTacToePanel2.ticTacToeTime.tie = false;
            TicTacToePanel2.gameDone = true;
        }
   }

    //mouse listener to determine if the cell is clicked on
    public void mousePressed(MouseEvent e){

    }
    public void mouseEntered(MouseEvent e){
        mouseInCell = true;
    }
    public void mouseExited(MouseEvent e){
        mouseInCell = false;
    }
    public void mouseReleased(MouseEvent e){

    }
    public void mouseClicked(MouseEvent e){

    TicTacToePanel2.ticTacToeTime.isSolved();
    
       if (TicTacToePanel2.ticTacToeTime.checkBoardFull()){
         if(mouseInCell && notClicked 
        // && TicTacToePanel2.ticTacToeTime.notThinking
        ){
            setValue(1);
            TicTacToePanel2.ticTacToeTime.isSolved();
           
          if (TicTacToePanel2.ticTacToeTime.containsX()){
             if (TicTacToePanel2.ticTacToeTime.won == false && TicTacToePanel2.ticTacToeTime.lost == false && TicTacToePanel2.ticTacToeTime.tie == false){
            // TicTacToePanel2.ticTacToeTime.notThinking = false;
                 TicTacToePanel2.ticTacToeTime.setResponse();
                // TicTacToePanel2.ticTacToeTime.responseTimer.start();
                // TicTacToePanel2.ticTacToeTime.isSolved();
            }
          }
        }
    }

    if (TicTacToePanel2.ticTacToeTime.checkBoardFull() == false){
         if(mouseInCell && notClicked 
        // && TicTacToePanel2.ticTacToeTime.notThinking
        ){
             setValue(1);
        }
    }
    
    endGame();  
    }
}



