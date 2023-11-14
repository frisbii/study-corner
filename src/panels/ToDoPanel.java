
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

//figure out how to do this with extending JPanel instead of extending JFrame
public class ToDoPanel extends JPanel implements ItemListener {

    public static int FPS = 60;
    static ToDoPanel toDoModel;
    ToDoData data;
    JList taskList;
    JButton addTaskButton;
    JTextField newTask;

    /*public static void main(String [] args) throws IOException{
        toDoModel = new ToDoModel();
    } */

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
                try { //how to refresh data list after data is edited???
                    data.addTask(newTask.getText());
                    data.getFileFromList();
                    data = new ToDoData();
                }
                catch(Exception e) {System.out.println("Exception " + e);}
            }
        });
        Border emptyBorder = BorderFactory.createEmptyBorder();
        addTaskButton.setBorder(emptyBorder);
        //need to add runner back in and update FPS and every time, call update data
        newTask = new JTextField("Enter task here...");

        JLabel title = new JLabel("To Do: ");
        title.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new FlowLayout());
        this.add(title);

        //smaller borders btwn elements
        JPanel panel1 = new JPanel();
        panel1.add(taskList);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(newTask);
        panel2.add(addTaskButton);

        //this.setLayout(new GridLayout(3,1,0,0));
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
