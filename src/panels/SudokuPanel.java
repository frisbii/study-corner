import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class SudokuPanel extends JPanel {

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static int FPS = 60;

    //buttons to add numbers to the sudoku board
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;

    public SudokuPanel(){

    }
    
}

class GamePanel extends JPanel{

    Cell[][] cells;
    boolean isSolved;
    int gridSize;
    int[][] numbers;

    public GamePanel(){
        gridSize = 4;
        isSolved = false;

        numbers = new int[4][4];
        setNumbers();
        System.out.println(isSolved);

    }

    private void setNumbers(){
        numbers[0][0] = 1;
        numbers[0][1] = 2;
        numbers[0][2] = 3;
        numbers[0][3] = 4;

        numbers[1][0] = 4;
        numbers[1][1] = 3;
        numbers[1][2] = 2;
        numbers[1][3] = 1;

        numbers[2][0] = 2;
        numbers[2][1] = 4;
        numbers[2][2] = 1;
        numbers[2][3] = 3;

        numbers[3][0] = 3;
        numbers[3][1] = 1;
        numbers[3][2] = 4;
        numbers[3][3] = 2;

        printNumbers();
    }

    private boolean isSolved(){
        List<Integer> fourSet = new ArrayList<Integer>();
        //check that every cell has a number
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if(numbers[i][j] == 0) return false;
            }
        }
        
        //check rows
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                fourSet.add(numbers[i][j]);
            }
            for(int k = 0; k < gridSize; k++){
                if(!fourSet.contains(k)) return false;
            }
            fourSet.clear();
        }
        
        //check columns
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                fourSet.add(numbers[j][i]);
            }
            for(int k = 0; k < gridSize; k++){
                if(!fourSet.contains(k)) return false;
            }
            fourSet.clear();
        }

        fourSet.add(numbers[0][0]);
        fourSet.add(numbers[0][1]);
        fourSet.add(numbers[1][0]);
        fourSet.add(numbers[1][1]);
        for(int k = 0; k < gridSize; k++){
                if(!fourSet.contains(k)) return false;
            }
        fourSet.clear();

        fourSet.add(numbers[0][2]);
        fourSet.add(numbers[1][2]);
        fourSet.add(numbers[0][3]);
        fourSet.add(numbers[1][3]);
        for(int k = 0; k < gridSize; k++){
                if(!fourSet.contains(k)) return false;
            }
        fourSet.clear();

        fourSet.add(numbers[2][2]);
        fourSet.add(numbers[2][3]);
        fourSet.add(numbers[3][2]);
        fourSet.add(numbers[3][3]);
        for(int k = 0; k < gridSize; k++){
                if(!fourSet.contains(k)) return false;
            }
        fourSet.clear();

        fourSet.add(numbers[2][0]);
        fourSet.add(numbers[2][1]);
        fourSet.add(numbers[3][0]);
        fourSet.add(numbers[3][1]);
        for(int k = 0; k < gridSize; k++){
                if(!fourSet.contains(k)) return false;
            }
        fourSet.clear();

        //check each square group of 4 squares
        /*for(int i = 0; i < gridSize; i++){
            //add numbers with the correct cluster to fourSet
            for(int j = 0; j < gridSize; j++){
                for(int k = 0; k < gridSize; k++){
                    if(cells[i][j].cluster == i + 1) fourSet.add(cells[i][j].value);
                }
                if(fourSet.size() == 4) break;
            }
            //check
            for(int k = 0; k < gridSize; k++){
                if(!fourSet.contains(k)) return false;
            }
        }*/


        return true;
    }

    private void printNumbers(){
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; i++){
                System.out.print(numbers[i][j] + ", ");
            }
            System.out.println();
        }
    }

}

class Cell extends JTextField{

    boolean full;
    boolean isDefault; //used to determine if the cell was one of the ones filled upon generation of the game
    int row;
    int column;
    int cluster;
    int value;

    //need  listener for what the input into the cell is going to be

}
