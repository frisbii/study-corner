
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ToDoModel extends JFrame implements ItemListener {

    public static int FPS = 60;
    static ToDoModel toDoModel;
    ToDoData data;
    JList taskList;
    JButton addTaskButton;
    JTextField newTask;

    public static void main(String [] args) throws IOException{
        toDoModel = new ToDoModel();
    }

    public ToDoModel() throws IOException{
        super("To Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        data = new ToDoData();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(String s : data.tasks){
            listModel.addElement(s);
        }
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        addTaskButton = new JButton("Add");
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try { //how to refresh data list after data is edited???
                    data.addTask(newTask.getText());
                    data.getFileFromList();
                    data = new ToDoData();
                }
                catch(Exception e) {System.out.println("Exception " + e);}
            }
        });
        //need to add runner back in and update FPS and every time, call update data
        newTask = new JTextField("Enter task here...");

        Border emptyBorder = BorderFactory.createEmptyBorder();

        JPanel panel1 = new JPanel();
        panel1.add(taskList);

        addTaskButton.setBorder(emptyBorder);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(newTask);
        panel2.add(addTaskButton);

        this.setLayout(new GridLayout(3,1,0,0));

        //need to make buffer borders smaller in between elements
        JLabel title = new JLabel("To Do: ");
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);
        add(panel2);
        add(panel1);

        //frame properties
        setSize(300, 500);
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
