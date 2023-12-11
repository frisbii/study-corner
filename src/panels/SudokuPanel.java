import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

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
    boolean solvedFinal; //used to send a message to GameInfoPanel to determine if the game is over and the JFrame should be closed

    SudokuGamePanel sudokuTime; //houses the grid of squares where 

    /**
     * constructs the main window the sudoku game is seen in,
     * adding all the buttons needed for the user to interact with the game properly
     */
    public SudokuPanel(){
        //creates grid within the main jpanel
        solvedFinal = false;
        sudokuTime = new SudokuGamePanel();
        buttonType = "x";

        //sets background and layout, adds SudokuGamePanel with grid board
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
                    solvedFinal = true;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Not quite correct! Keep trying!");
                }
            }
        });

        //clears every square on the board that was filled in by the user, leaves the inital puzzle state alone
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

        //adds buttons in their panel to the layout of the main sudokupanel
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

        //show instructions to user
        JOptionPane.showMessageDialog(null, "Press the number buttons and click to place a number. X clears the space you click. Try to get 1-4 in every row, column, and quadrant!");
    }
}

//houses the grid where the actual puzzle lives
//includes methods to determine whether the puzzle is solved and to assign initial values to the puzzle
class SudokuGamePanel extends JPanel{

    public static final int width = 525;
    public static final int height = 525;

    Cell[][] cells;
    boolean isSolved;
    int gridSize;

    //constructor for the puzzle board itself
    public SudokuGamePanel(){
        gridSize = 4;
        isSolved = false;

        cells = new Cell[gridSize][gridSize];
        setValues();

        //arrange cells in game panel using GridLayout because it's a grid
        setLayout(new GridLayout(gridSize, gridSize,5,5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setBackground(Color.BLACK);
        setPreferredSize(new java.awt.Dimension(width, height));
        //adds all cells into grid
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                this.add(cells[i][j]);
            }
        }
    }

    //sets initial game state values for the puzzle
    private void setValues(){
        //generates initial state by referencing solved boards and randomly removing elements from them
        WinningBoards winningBoards = new WinningBoards();
        Random r = new Random();
        //randomly detemrines which board to use
        int[][] boardSelected = winningBoards.boards.get(r.nextInt(winningBoards.boards.size()));

        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                cells[i][j] = new Cell(i, j);
                cells[i][j].assignInitialBoardState(boardSelected[i][j]);
                //assign "clusters" to each cell (the 4 groups of 2x2 squares that are important in sudoku)
                //integer division results in only a 0 or a 1
                if(i/2 == 0 && j/2 == 0) {cells[i][j].cluster = 1; cells[i][j].setBackground(Color.LIGHT_GRAY);}
                else if(i/2 == 0 && j/2 == 1) cells[i][j].cluster = 2;
                else if (i/2 == 1 && j/2 == 0) cells[i][j].cluster = 3;
                else if(i/2 == 1 && j/2 == 1) {cells[i][j].cluster = 4; cells[i][j].setBackground(Color.LIGHT_GRAY);}
            }
        }

        //now randomly set some values to zero but make sure to make them not the default board state first
        int randRow;
        int randCol;
        int removeCellsCount = r.nextInt(3) + 12; //generates 12,13,14
        for(int i = 0; i < removeCellsCount; i++){
            randRow = r.nextInt(4);
            randCol = r.nextInt(4);
            if(cells[randRow][randCol].isDefault) {
                //if the cell has not been set to zero yet, make sure it is not part of the default game state anymore and set it to zero
                cells[randRow][randCol].isDefault = false;
                cells[randRow][randCol].setValue(0);
            }
        }

    }

    //checks if the puzzle has been solved (if the current state is correct)
    public boolean isSolved(){
        List<Integer> fourSet = new ArrayList<Integer>();
        //check that every cell has a number
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if(cells[i][j].value == 0) return false;
            }
        }
        
        //check rows (fails if any number 1-4 is not in a particular row)
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                fourSet.add(cells[i][j].value);
            }
            for(int k = 1; k < gridSize + 1; k++){
                if(!fourSet.contains(k)) return false;
            }
            fourSet.clear();
        }
        
        //check columns (fails if any number 1-4 is not in a particular column)
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                fourSet.add(cells[j][i].value);
            }
            for(int k = 1; k < gridSize + 1; k++){
                if(!fourSet.contains(k)) return false;
            }
            fourSet.clear();
        }

        //check each square group of 4 squares (fails if any cluster does not contain all the numbers 1-4)
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
                if(!fourSet.contains(k)) return false;
            }
        }

        return true;
    }

}

//cell class represents a single cell with a single number in it
class Cell extends JPanel implements MouseListener{

    public static final int width = 125;
    public static final int height = 125;

    boolean isDefault; //used to determine if the cell was one of the ones filled upon generation of the game
    boolean mouseInCell; //determines if the mouse is in this particular cell

    int row;
    int column;
    int cluster;
    int value;
    JLabel valueText;

    static int valueToChangeTo; //the number value of the most recent button pressed on the main panel

    /**
     * constructs a cell, assigns its row and column, and sets the default JLabel
     * 
     * @param r   the row of the cell
     * @param c   the column of the cell
     */
    public Cell (int r, int c){
        row = r;
        column = c;
        mouseInCell = false;
        addMouseListener(this);
        isDefault = false;

        this.setMaximumSize(new java.awt.Dimension(width, height));
        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout());

        valueText = new JLabel(" ");
        valueText.setFont(Fonts.generateCutiveFont(100, 28));
        valueText.setForeground(Color.BLACK);
        this.add(valueText);
        valueText.setVisible(true);
    }

    /**
     * assigns the cell a value and makes sure the color of the text is correct 
     * based on whether or not the cell is part of the default game state
     * @param v the value assigned to the cell
     */
    public void setValue(int v){
        this.value = v;
        if(v != 0) valueText.setText(((Integer)v).toString());
        else valueText.setText(" ");
        if(valueText.getForeground() != Color.BLACK && !isDefault) valueText.setForeground(Color.BLACK);
        this.revalidate();
        this.repaint();
    }

    /**
     * assigns the cell a value and sets the cell to be part of the initial game state of the board
     * @param v the value assign to the cell
     */
    public void assignInitialBoardState(int v){
        isDefault = true;
        valueText.setForeground(Color.BLUE);
        setValue(v);
    }

    //mouse listener events to determine if the mouse is inside the cell when it is clicked
    public void mouseEntered(MouseEvent e){
        mouseInCell = true;
    }
    public void mouseExited(MouseEvent e){
        mouseInCell = false;
    }
    public void mouseClicked(MouseEvent e){
        if(mouseInCell){
            if(!isDefault) setValue(valueToChangeTo);
        }

    }
    //these two are not used in the class but needed them for implements reasons
    public void mouseReleased(MouseEvent e){
    }
    public void mousePressed(MouseEvent e){
    }

}

//static class that has a list of fully solved sudoku boards that are used to assign initial values in SudokuGamePanel class
class WinningBoards{

    static List<int[][]> boards = new ArrayList<int[][]>();

    static{
        boards.add(new int[][]{{1,2,3,4}, {3,4,2,1}, {2,1,4,3}, {4,3,1,2}});
        boards.add(new int[][]{{4,2,3,1}, {1,3,2,4}, {2,4,1,3}, {3,1,4,2}});
        boards.add(new int[][]{{2,3,4,1}, {4,1,3,2}, {3,2,1,4}, {1,4,2,3}});
        boards.add(new int[][]{{3,2,1,4}, {4,1,3,2}, {1,4,2,3}, {2,3,4,1}});
    }
}