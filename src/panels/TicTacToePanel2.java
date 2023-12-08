import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

// TODO: implement play against computer
// TODO: fix orientation of buttons
// TODO: write interface
public class TicTacToePanel2 extends JPanel {
   
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static int FPS = 60;

    // buttons for x vs o

    JButton xButton;
    JButton oButton;
    String buttonType;

    TicTacToeGamePanel ticTacToeTime;

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

        oButton = new JButton ("o");
        oButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                buttonType = "O";
                TicTacToeCell.valueToChangeTo = 2; 
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(2,1,0,5));
        JPanel clearPanel = new JPanel();
        clearPanel.setBackground(new Color(0,0,0,0));
        buttonPanel.add(clearPanel);
        buttonPanel.add(xButton);
        buttonPanel.add(oButton);
        
        
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
        
        //set default values of every cell to 0 before assigning random values to some
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                cellsTicTac[i][j] = new TicTacToeCell(i, j);
                cellsTicTac[i][j].setValue(0);
                
            }
        }
}
}


class TicTacToeCell extends JPanel implements MouseListener{

    public static final int width = 125;
    public static final int height = 125;

    boolean full;
    boolean isDefault; //used to determine if the cell was one of the ones filled upon generation of the game
    boolean mouseInCell;

    int row;
    int column;
    int cluster;
    int value;
    JLabel valueText;

    static int valueToChangeTo;

    public TicTacToeCell (int r, int c){
        row = r;
        column = c;
        full = false;
        mouseInCell = false;
        addMouseListener(this);
        isDefault = false;

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
        } 
         else if (value == 2) {
            valueText.setText("O");
        } else { 
            valueText.setText(" ");
        }
        this.revalidate();
        this.repaint();
    }

    public void assignInitialBoardState(int value){
        isDefault = true;
        valueText.setForeground(Color.BLUE);
        setValue(value);
        System.out.println("Initial value for cell " + row + " " + column + " is " + value);
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
        if(mouseInCell){
            if(!isDefault) setValue(valueToChangeTo);
        }

    }

}



