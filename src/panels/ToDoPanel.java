
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class ToDoPanel extends PanelBase implements ItemListener, MouseListener, MouseMotionListener {

    //sets values needed for PanelBase
    private static int TODO_WIDTH = 500;
    private static int TODO_HEIGHT = 650;
    private static Color TODO_COLOR = new Color(255, 255, 255, 180);

    //variables for the task list itself
    static ToDoPanel toDoModel;
    ToDoData data;
    JList<String> taskList;
    DefaultListModel<String> listModel;
    Graphics g;

    //buttons and panel layout
    JButton addTaskButton;
    JButton deleteTaskButton;
    JTextFieldWithPrompt newTask;
    JPanel flowPanel;

    boolean isMouseDragging;
    int dragInitial;
    boolean isPutAway;
    boolean notInList;


    //constructor contains everything graphics related in the class essentially so that it can be added to MainPanel
    public ToDoPanel() {
        super(TODO_WIDTH, TODO_HEIGHT, TODO_COLOR);

        data = new ToDoData(); //creates the ToDoData where all the data for the tsks and writing to the file, etc, is done
        flowPanel = new JPanel();
        isMouseDragging = false;

        //JList containing JCheckBox
        listModel = new DefaultListModel<>();
        for(String s : data.tasks){
            listModel.addElement(s);
        }
        taskList = new JList<String>(listModel);
        taskList.addMouseListener(this);
        taskList.addMouseMotionListener(this);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setFixedCellHeight(25); //add some spacing between tasks
        //key listener so that the "del" key can be used to remove tasks from the list when one is selected
        taskList.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                if(key == 127){
                    try {
                    if(!taskList.isSelectionEmpty()){
                        String removeMe = (String)taskList.getSelectedValue();
                        data.removeTask(removeMe);
                        listModel.removeElement(removeMe);
                    }
                }
                catch(Exception ex) {System.out.println("Exception " + ex);}
                }
            }
            public void keyReleased(KeyEvent e){
            }
            public void keyTyped(KeyEvent e){
            }
        });

        //button to add task to the to do list
        addTaskButton = new JButton("Add");
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    //add if there is enough room in the list AND the user has replaced the placeholder
                    if(!newTask.getText().equals(newTask.placeholder) && data.tasks.size() < 20){
                        if(newTask.getText().length() <= 40){
                            //only add task if it isn't on the list already
                            if(!data.tasks.contains(newTask.getText())){
                                data.addTask(newTask.getText());
                                listModel.addElement(newTask.getText());
                                newTask.setText("Enter task here...");
                            }
                            else{
                            JOptionPane.showMessageDialog(null, "Oops! Task is already in the list.");
                            }  
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Oops! Please make your task 40 characters or less.");
                        }
                    }
                }
                catch(Exception e) {System.out.println("Exception " + e);}
            }
        });
        Border emptyBorder = BorderFactory.createEmptyBorder();
        addTaskButton.setBorder(emptyBorder);

        //JTextField where the user inputs new tasks
        newTask = new JTextFieldWithPrompt(15, "Enter task here...");
        //action listener so that the enter key can also be used to enter tassk (enter is default action on JTextField)
        newTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0){
                try {
                    //same criteria for adding a task as the "add task" button above
                    if(!newTask.getText().equals(newTask.placeholder) && data.tasks.size() < 20){
                        if(newTask.getText().length() <= 40){
                            if(!data.tasks.contains(newTask.getText())){
                                data.addTask(newTask.getText());
                                listModel.addElement(newTask.getText());
                                newTask.setText("Enter task here...");
                                //brute force remove focus from textfield so that "enter task here" placeholder reappears
                                newTask.setFocusable(false);
                                //but it need to be focusable again so focus true again
                                newTask.setFocusable(true);
                            }
                            else{
                            JOptionPane.showMessageDialog(null, "Oops! Task is already in the list.");
                            }  
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Oops! Please make your task 40 characters or less.");
                            }
                    }
                }
                catch(Exception e) {System.out.println("Exception " + e);}
            }
        });

        //button to delete tasks, same criteria as the "del" key remove task
        deleteTaskButton = new JButton("Remove selected task");
        deleteTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if(!taskList.isSelectionEmpty()){
                        String removeMe = (String)taskList.getSelectedValue();
                        data.removeTask(removeMe);
                        listModel.removeElement(removeMe);
                    }
                }
                catch(Exception e) {System.out.println("Exception " + e);}
            }
        });
        deleteTaskButton.setBorder(emptyBorder);


        JLabel title = new JLabel("To Do: ");
        title.setHorizontalAlignment(JLabel.CENTER);
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(title);

        //GridBagLayout shenanigans to make things look pretty start here
        GridBagLayout toDoLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        flowPanel.setLayout(toDoLayout);

        //anchor is at the top of the page
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        toDoLayout.setConstraints(titlePanel, c);
        this.flowPanel.add(titlePanel);
        c.gridwidth = GridBagConstraints.REMAINDER;

        JPanel panel1 = new JPanel();
        panel1.add(taskList);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(newTask);
        panel2.add(addTaskButton);

        //gridbag stuff for add button pannel
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        toDoLayout.setConstraints(panel2, c);
        this.flowPanel.add(panel2);
        c.gridwidth = GridBagConstraints.REMAINDER;

        //gridbag for delete button
        JPanel panel3 = new JPanel();
        panel3.add(deleteTaskButton);
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        toDoLayout.setConstraints(panel3, c);
        this.flowPanel.add(panel3);
        c.gridwidth = GridBagConstraints.REMAINDER;

        //gridbag for to do list
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 4;
        c.gridheight = 3;
        toDoLayout.setConstraints(panel1, c);
        this.flowPanel.add(panel1);
        c.gridwidth = GridBagConstraints.REMAINDER;

        //setting background of every other panel component in the panel to have a clear background
        titlePanel.setBackground(new Color(0,0,0,0));
        panel1.setBackground(new Color(0,0,0,0));
        panel2.setBackground(new Color(0,0,0,0));
        panel3.setBackground(new Color(0,0,0,0));
        flowPanel.setBackground(new Color(0,0,0,0));

        this.add(flowPanel);
    }

    //necessary for item listener
    public void itemStateChanged(ItemEvent event){
    }

    //mouse listener events 
    public void mousePressed(MouseEvent e){
        //if mouse clicked, get index where it clicked in case we start dragging the mouse to rearrange tasks
        dragInitial = taskList.getSelectedIndex();
    }

    public void mouseEntered(MouseEvent e){
    }

    //used to rearrange tasks by clicking and dragging
    public void mouseReleased(MouseEvent e){
        if (isMouseDragging) {        
            //if mouse is dragging, the placae where we are dragging the dragInital task to is the task where the mouse ended up
            int dragTarget = taskList.getSelectedIndex();
            String dragElement = listModel.get(dragInitial);
            //move the dragInitial task to the index of dragTarget in the list
            listModel.remove(dragInitial);
            listModel.add(dragTarget, dragElement);
            try{
                data.removeTask(dragElement);
                data.addTask(dragTarget, dragElement);
            }
            catch(Exception ex){System.out.println("Error dragging: " + ex);}
            taskList.setSelectedIndex(dragTarget);
        }
        isMouseDragging = false;
    }

    public void mouseClicked(MouseEvent e){
        if(notInList) taskList.clearSelection();
    }

    //determine if the mouse is being dragged, so if it is, stuff in mouseReleased happens
    public void mouseDragged(MouseEvent e){
        isMouseDragging = true;
    }

    public void mouseMoved(MouseEvent e){
    }
    public void mouseExited(MouseEvent e){
    }

}

//text field that has input text which disappears upon the user clicking it
class JTextFieldWithPrompt extends JTextField{

    String placeholder;

    //JTextField default constructor but with focus listener to determine when to set placeholder text
    public JTextFieldWithPrompt(){
        super();
        //determines if component has user focus (did user click on the textfield to type something into it)
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e){
                if(getText().equals(placeholder)){
                    setText("");
                }
            }
            //when the user removes focus, if there is no text, set the placeholder text
            public void focusLost(FocusEvent e){
                if(getText().isEmpty()){
                    setText(placeholder);
                }
            }
        });
    }

    /**
     * JTextField contructor that creates a textfield with the preferred with in columns and also has a focuslistener
     * @param cols columns, or width, of text field
    */
    public JTextFieldWithPrompt(int cols){
        super(cols);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e){
                if(getText().equals(placeholder)){
                    setText("");
                }
            }
            public void focusLost(FocusEvent e){
                if(getText().isEmpty()){
                    setText(placeholder);
                }
            }

        });
    }

    /**constructor that creates a default JTextField with preferred width in columns, has a focus listener
     * also sets the placeholder text
     * @param cols columns, or width, of textfield
     * @param s string that will be the placeholder text in the text field
    */
    public JTextFieldWithPrompt(int cols, String s){
        super(cols);
        placeholder = s;
        setText(placeholder);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e){
                if(getText().equals(placeholder)){
                    setText("");
                }
            }
            public void focusLost(FocusEvent e){
                if(getText().isEmpty()){
                    setText(placeholder);
                }
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    public void setPlaceholder(String s){
        placeholder = s;
    }
}
