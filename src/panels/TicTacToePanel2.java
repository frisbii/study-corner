import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

// TODO: fix sleep
// TODO: write interface


public class TicTacToePanel2 extends JPanel {
   
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static int FPS = 60;

    // buttons for x vs o

    JButton xButton;
    String buttonType;

    static TicTacToeGamePanel ticTacToeTime;

    public TicTacToePanel2(){
        ticTacToeTime = new TicTacToeGamePanel();
        buttonType = " ";

        Color lightPurple = new Color (156, 145, 188);
        this.setBackground(lightPurple);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        this.add(ticTacToeTime);

        // adding buttons to panel
        xButton = new JButton ("x");
        xButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                buttonType = "X";
                TicTacToeCell.valueToChangeTo = 1; 
                System.out.println("x button pressed"); 
            }
        });

      

        JPanel buttonPanel = new JPanel(new GridLayout(2,1,0,5));
        JPanel clearPanel = new JPanel();
        clearPanel.setBackground(new Color(0,0,0,0));
        buttonPanel.add(clearPanel);
        buttonPanel.add(xButton);

        
        
        buttonPanel.setBackground(new Color(0,0,0,0));

        this.add(buttonPanel);
    }
}

class TicTacToeGamePanel extends JPanel{

    public static final int width = 525;
    public static final int height = 525;

    TicTacToeCell[][] cellsTicTac;
    boolean isSolved;
    int gridSize;
    int squareCol; 
    int squareRow;

    public TicTacToeGamePanel(){
        gridSize = 3;
        isSolved = false;

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
    }

    private void setValues(){
        
        //set default values of every cell to 0 
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                cellsTicTac[i][j] = new TicTacToeCell(i, j);
                cellsTicTac[i][j].setValue(0);
                
            }
        }
}

public void setResponse(){

    //  try{
    //         Thread.sleep(4000);
    //     	}
    //     catch(InterruptedException c){}
  
  squareCol = (int) (Math.random() * 3);
  squareRow = (int) (Math.random() * 3);

  while (cellsTicTac[squareRow][squareCol].value != 0){
  squareCol = (int) (Math.random() * 3);
  squareRow = (int) (Math.random() * 3);
  }
  
    cellsTicTac[squareRow][squareCol].setValue(2);
  
 
  
}

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
}


class TicTacToeCell extends JPanel implements MouseListener{

    public static final int width = 125;
    public static final int height = 125;

    boolean mouseInCell;

    int row;
    int column;
    int cluster;
    int value;
    JLabel valueText;
    boolean notClicked = true;

    static int valueToChangeTo;

    public TicTacToeCell (int r, int c){
        row = r;
        column = c;
        mouseInCell = false;
        addMouseListener(this);

        this.setMaximumSize(new java.awt.Dimension(width, height));
        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout());

        valueText = new JLabel(" ");
        Fonts.setUIFonts();
        valueText.setFont(new Font(Fonts.CUTIVE_UI.getName(), Font.PLAIN, 100));
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
       if (TicTacToePanel2.ticTacToeTime.checkBoardFull()){
        if(mouseInCell && notClicked){
            setValue(valueToChangeTo);
            
          if (TicTacToePanel2.ticTacToeTime.containsX()){
            TicTacToePanel2.ticTacToeTime.setResponse();
          }
        }
    }

    if (TicTacToePanel2.ticTacToeTime.checkBoardFull() == false){
        if(mouseInCell && notClicked){
            setValue(valueToChangeTo);
        
        }
    }
    }

}



