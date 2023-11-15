
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

//TO DO: pretty background image
public class ToDoPanel extends JPanel implements ItemListener {

    public static int FPS = 60;
    static ToDoPanel toDoModel;
    ToDoData data;
    JList taskList;
    JButton addTaskButton;
    JTextField newTask;

    //constructor contains everything graphics related in the class essentially so that it can be added to MainPanel
    public ToDoPanel() throws IOException{

        data = new ToDoData();
        //JList containing JCheckBox
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(String s : data.tasks){
            listModel.addElement(s);
        }
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        addTaskButton = new JButton("Add");
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    data.addTask(newTask.getText());
                    listModel.addElement(newTask.getText());
                    newTask.setText("Enter task here...");
                }
                catch(Exception e) {System.out.println("Exception " + e);}
            }
        });
        Border emptyBorder = BorderFactory.createEmptyBorder();
        addTaskButton.setBorder(emptyBorder);

        newTask = new JTextField(15);
        //apparently this makes it only accept 15 characters
        newTask.setText("Enter task here...");

        //TO DO: add a way to delete tasks in the program itself

        JLabel title = new JLabel("To Do: ");
        title.setHorizontalAlignment(JLabel.CENTER);

        //TO DO: figure out how GridBagLayout works to make it do properly what I want to do
        GridBagLayout toDoLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.PAGE_START;
        this.setLayout(toDoLayout);
        c.fill = GridBagConstraints.BOTH;

        c.weightx = 1;
        c.gridheight = 1;
        toDoLayout.setConstraints(title, c);
        this.add(title);
        c.gridwidth = GridBagConstraints.REMAINDER;

        //smaller borders btwn elements
        JPanel panel1 = new JPanel();
        panel1.add(taskList);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(newTask);
        panel2.add(addTaskButton);

        c.weightx = 1;
        c.gridheight = 1;
        toDoLayout.setConstraints(panel2, c);
        add(panel2);
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.weightx = 1;
        c.gridheight = 3;
        toDoLayout.setConstraints(panel1, c);
        add(panel1);
        c.gridwidth = GridBagConstraints.REMAINDER;


        //setSize(300, 500);
        setVisible(true);
    }

    public void itemStateChanged(ItemEvent event){
        Object source = event.getItemSelectable();
        try{
            if(source == addTaskButton) data.addTask(newTask.getText());
        }
        catch(Exception e) {System.out.println("Error: " + e);}
    }
}

//TO DO: finish this so I can have nice placeholder text :)))
class JTextFieldWithPrompt extends JTextField{

    String placeholder;

    public JTextFieldWithPrompt(String s){
        placeholder = s;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

    }

    public void setPlaceholder(String s){
        placeholder = s;
    }
}
