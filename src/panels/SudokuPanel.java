import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

//TO DO: game win and game try again screen
//TO DO: exit upon win game

public class SudokuPanel extends JPanel{

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static int FPS = 60;

    //buttons to add numbers to the sudoku board
    JButton buttonCheck;
    JButton buttonClear;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton buttonX;
    String buttonType; //to determine which button has been pressed most recently

    JButton checkSolution;

    GamePanel sudokuTime;

    public SudokuPanel(){
        sudokuTime = new GamePanel();
        buttonType = "x";

        Color lightPurple = new Color (156, 145, 188);
        this.setBackground(lightPurple);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        this.add(sudokuTime);

        //putting all the buttons in their panel
        button1 = new JButton("1");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                buttonType = "1";
                Cell.valueToChangeTo = 1;
            }
        });
        button2 = new JButton("2");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                buttonType = "2";
                Cell.valueToChangeTo = 2;
            }
        });
        button3 = new JButton("3");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                buttonType = "3";
                Cell.valueToChangeTo = 3;
            }
        });
        button4 = new JButton("4");
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                buttonType = "4";
                Cell.valueToChangeTo = 4;
            }
        });
        buttonX = new JButton("X");
        buttonX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                buttonType = "x";
                Cell.valueToChangeTo = 0;
            }
        });

        //button to check if the sudoku is correct
        buttonCheck = new JButton("check solution");
        buttonCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(sudokuTime.isSolved()){
                    JOptionPane.showMessageDialog(null, "Congrats! You solved the puzzle!");
                    // try{
                    //         Thread.sleep(3000);
                    //     	}
                    //     catch(InterruptedException c){}
                    //         GameInfoPanel.frame.dispose();
                
                }
                else{
                    JOptionPane.showMessageDialog(null, "Not quite correct! Keep trying!");
                }
            }
        });

        buttonClear = new JButton("clear board");
        buttonClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                for(int i = 0; i < sudokuTime.gridSize; i++){
                    for(int j = 0; j < sudokuTime.gridSize; j++){
                        if(!sudokuTime.cells[i][j].isDefault) sudokuTime.cells[i][j].setValue(0);
                    }
                }
            }
        });

        //TO DO: make button panel bigger
        JPanel buttonPanel = new JPanel(new GridLayout(8,1,5,5));
        buttonPanel.add(buttonCheck);
        buttonPanel.add(buttonClear);
        JPanel clearPanel = new JPanel();
        clearPanel.setBackground(new Color(0,0,0,0));
        buttonPanel.add(clearPanel);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(buttonX);
        
        buttonPanel.setBackground(new Color(0,0,0,0));

        this.add(buttonPanel);
    }
}

class GamePanel extends JPanel{

    public static final int width = 525;
    public static final int height = 525;

    Cell[][] cells;
    boolean isSolved;
    int gridSize;

    public GamePanel(){
        gridSize = 4;
        isSolved = false;

        cells = new Cell[gridSize][gridSize];
        setValues();

        //arrange cells in game panel
        setLayout(new GridLayout(gridSize, gridSize,5,5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setBackground(Color.BLACK);
        setPreferredSize(new java.awt.Dimension(width, height));
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                this.add(cells[i][j]);
            }
        }
    }

    private void setValues(){
        WinningBoards winningBoards = new WinningBoards();
        Random r = new Random();
        int[][] boardSelected = winningBoards.boards.get(r.nextInt(winningBoards.boards.size()));

        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                cells[i][j] = new Cell(i, j);
                cells[i][j].assignInitialBoardState(boardSelected[i][j]);
                //assign "clusters" to each cell
                //integer division should result in only a 0 or a 1
                if(i/2 == 0 && j/2 == 0) {cells[i][j].cluster = 1; cells[i][j].setBackground(Color.LIGHT_GRAY);}
                else if(i/2 == 0 && j/2 == 1) cells[i][j].cluster = 2;
                else if (i/2 == 1 && j/2 == 0) cells[i][j].cluster = 3;
                else if(i/2 == 1 && j/2 == 1) {cells[i][j].cluster = 4; cells[i][j].setBackground(Color.LIGHT_GRAY);}
            }
        }

        //now randomly set some values to zero but make sure to make them not the default board state first
        int randRow;
        int randCol;
        int removeCellsCount = r.nextInt(3) + 11; //generates 11,12,13
        for(int i = 0; i < removeCellsCount; i++){
            randRow = r.nextInt(4);
            randCol = r.nextInt(4);
            if(cells[randRow][randCol].isDefault) {
                cells[randRow][randCol].isDefault = false;
                cells[randRow][randCol].setValue(0);
            }
        }

    }

    public boolean isSolved(){
        List<Integer> fourSet = new ArrayList<Integer>();
        //check that every cell has a number
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if(cells[i][j].value == 0) {System.out.println("Not completely full"); return false;}
            }
        }
        
        //check rows
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                fourSet.add(cells[i][j].value);
            }
            for(int k = 1; k < gridSize + 1; k++){
                if(!fourSet.contains(k)) {System.out.println("Rows failed " + i); return false;}
            }
            fourSet.clear();
        }
        
        //check columns
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                fourSet.add(cells[j][i].value);
            }
            for(int k = 1; k < gridSize + 1; k++){
                if(!fourSet.contains(k)) {System.out.println("Columns failed " + i); return false;}
            }
            fourSet.clear();
        }

        //check each square group of 4 squares
        for(int i = 0; i < gridSize; i++){
            //add numbers with the correct cluster to fourSet
            for(int j = 0; j < gridSize; j++){
                for(int k = 0; k < gridSize; k++){
                    if(cells[j][k].cluster == i + 1) fourSet.add(cells[j][k].value);
                }
                if(fourSet.size() == 4) break;
            }
            //check
            for(int k = 1; k < gridSize + 1; k++){
                if(!fourSet.contains(k)) {System.out.println("Cluster failed " + (i+1)); return false;}
            }
        }

        return true;
    }

}

class Cell extends JPanel implements MouseListener{

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

    public Cell (int r, int c){
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
        if(value != 0) valueText.setText(((Integer)value).toString());
        else valueText.setText(" ");
        if(valueText.getForeground() != Color.BLACK && !isDefault) valueText.setForeground(Color.BLACK);
        this.revalidate();
        this.repaint();
    }

    public void assignInitialBoardState(int value){
        isDefault = true;
        valueText.setForeground(Color.BLUE);
        setValue(value);
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

class WinningBoards{

    static List<int[][]> boards = new ArrayList<int[][]>();

    static{
        boards.add(new int[][]{{1,2,3,4}, {3,4,2,1}, {2,1,4,3}, {4,3,1,2}});
        boards.add(new int[][]{{4,2,3,1}, {1,3,2,4}, {2,4,1,3}, {3,1,4,2}});
        boards.add(new int[][]{{2,3,4,1}, {4,1,3,2}, {3,2,1,4}, {1,4,2,3}});
        boards.add(new int[][]{{3,2,1,4}, {4,1,3,2}, {1,4,2,3}, {2,3,4,1}});
    }
}