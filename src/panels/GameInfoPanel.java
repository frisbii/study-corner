import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * GameInfoPanel class creates the menu screen when the main timer is completed or button action is taken
 * Switches to the individual game screens based on user input
 * Creates additional pop-up JFrame that closes upon user action after winning 
 */
public class GameInfoPanel implements ActionListener { 
    
    // Fields to create components and background elements of the game panel
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static Image gamePanelBG;

    private JButton ticTacToeButton;
    private JButton sudokuButton;
    private JLabel title;
    private Color purple = new Color (217,195,230);
    private static JFrame frame;

    private SudokuPanel mainSudoku;
    private TicTacToePanel2 mainTic;
    private Timer timer = new Timer(1000, this);

    // creating frame for pop up and laying out components
    public GameInfoPanel(){
        
        // Create window and layout
        GridBagLayout layout = new GridBagLayout();
        frame = new JFrame("game info");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
          
               gamePanelBG = ImageIO.read(new File("./resources/images/backgrounds/game_panel.PNG"));

        } catch (IOException e) { e.printStackTrace(); }
     

        JPanel infoPanel = new JPanel(layout) {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(gamePanelBG, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
    
        //Size and display the window.
        frame.add(infoPanel);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setResizable(false);
         
        // setting up buttons   
        ticTacToeButton = new JButton("Tic Tac Toe");
        ticTacToeButton.setFont(Fonts.generateCutiveFont(24, 5));
        sudokuButton = new JButton("Sudoku");
        sudokuButton.setFont(Fonts.generateCutiveFont(24, 5));
        ticTacToeButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        sudokuButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        ticTacToeButton.setBackground(purple);
        ticTacToeButton.setOpaque(true);
        sudokuButton.setBackground(purple);
        sudokuButton.setOpaque(true);

        title = new JLabel("Select a Game to Play!");
        title.setFont(Fonts.generateCutiveFont(32, 5));
       
        // Orienting the components on the panel to display on the frame
        layout.setConstraints(ticTacToeButton, new GridBagConstraints(
            100, 100,
            1, 1,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(200,200,25,200),
            0 , 0
        ));
        layout.setConstraints(sudokuButton, new GridBagConstraints(
            100, 200,
            1, 1,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(25,200,100,200),
            0 , 0
        ));
        layout.setConstraints(title, new GridBagConstraints(
            100, 100,
            1, 1,
            1, 1,
            GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,
            new Insets(25,185,100,15),
            0 , 0
        ));
         
       
        
        infoPanel.add(ticTacToeButton);
        infoPanel.add(sudokuButton);
        infoPanel.add(title);
        // infoPanel.setBackground();
        
        
        /**
         * If a button click action is taken on the tiTacToeButton, switch the content pane to the game
         * @param e the event occurred 
         */ 
        ticTacToeButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // creating instance of ticTacToe panel and setting the panel to show this
              mainTic = new TicTacToePanel2();
              
              frame.add(mainTic);
              frame.remove(infoPanel);
              frame.revalidate();
              frame.repaint();
            }
         }
         );

        /**
         * If a button click action is taken on the sudoku Button, switch the content pane to the game
         * @param e the event occurred 
         */ 
         sudokuButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainSudoku = new SudokuPanel();
    
                frame.add(mainSudoku);
                frame.remove(infoPanel);
                frame.revalidate();
                frame.repaint();
            }
         }
         );

         timer.start();
    }

    /**
     * whenever an action is performed, check if the event is timer based, and if so, check if the panels need to be closed
     * @param ev the event that has occurred
     */
    public void actionPerformed(ActionEvent ev){
            if(ev.getSource() == timer){
              checkClosePanel();
            }
        
        }

    /**
     * Conditional statements to check if game has been completed.
     * If this is true, exiting the window based on window actions taken
     */
    private void checkClosePanel(){
        if(!(mainSudoku == null)){
            if(mainSudoku.isVisible() && mainSudoku.solvedFinal){
                mainSudoku.solvedFinal = false;
                frame.remove(mainSudoku);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }
        else if(!(mainTic == null)){
            if(mainTic.isVisible() && TicTacToePanel2.gameDone){
                TicTacToePanel2.gameDone = false;
                frame.remove(mainTic);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }
    }

}
