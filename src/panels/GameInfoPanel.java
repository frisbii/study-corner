import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameInfoPanel {
    //do we want the frame to be the same size or different?
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    public JButton ticTacToeButton;
    public JButton sudokuButton;

    // create frame for pop up
    public GameInfoPanel(){
        
         GridBagLayout layout = new GridBagLayout();
         JFrame frame = new JFrame("game info");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        JPanel infoPanel = new JPanel(layout);
        
            //Size and display the window.
            frame.add(infoPanel);
            frame.setSize(WIDTH, HEIGHT);
            frame.setVisible(true);
            frame.setResizable(false);
         
            // setting up buttons   
        ticTacToeButton = new JButton("Tic Tac Toe");
        ticTacToeButton.setFont(new Font("Calibri", Font.BOLD, 10));
        sudokuButton = new JButton("Sudoku");
        sudokuButton.setFont(new Font("Calibri", Font.BOLD, 10));

       
        layout.setConstraints(ticTacToeButton, new GridBagConstraints(
            100, 100,
            1, 1,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,40,3,3),
            0 , 0
        ));
        layout.setConstraints(sudokuButton, new GridBagConstraints(
            200, 200,
            1, 1,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(3,40,3,3),
            0 , 0
        ));

        infoPanel.add(ticTacToeButton);
        infoPanel.add(sudokuButton);
        
    
   
    }
}
