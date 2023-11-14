import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.PrintWriter;

class ToDoData{

    public ArrayList<String> tasks;

    public ToDoData() throws IOException{
        tasks = new ArrayList<String>();
        getListFromFile();
    }

    public void getListFromFile() throws IOException{
        tasks.clear();
        try{
            //figure out how to access task list to do things
            File file = new File("TaskList.txt"); //creates file if no file
            BufferedReader reader = new BufferedReader(new FileReader("TaskList.txt"));
            String currentLine = reader.readLine();
            while (currentLine != null) {
                tasks.add(currentLine);
                currentLine = reader.readLine();
              }
            reader.close();
        }
        catch (Exception e){
            System.out.println("Error writing to file: " + e);
        }
    }

    public void getFileFromList() throws IOException{
        try{
            PrintWriter out = new PrintWriter("TaskList.txt");
            //should maybe delete all the tasks in the list before re-writing??
            for(int i = 0; i < tasks.size(); i++){
                out.println(tasks.get(i));
            }
            out.close();
        }
        catch (Exception e){
            System.out.println("Error writing to file: " + e);
        }
    }

    public void addTask(String task) throws IOException{
        tasks.add(task);
        getFileFromList();
    }

}