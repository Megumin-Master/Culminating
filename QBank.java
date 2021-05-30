/*
 * Name: Yuyang Liu
 * Date: May 29
 * Teacher: Mr.Ho
 * Description: Word Problems Input
*/

import java.io.File;
import java.util.Scanner;

public class QBank {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        // Create all possible variables used
        String choice = new String();

        // Prompt the user to choose the subject
        System.out.println("Which..");
        choice = reader.nextLine();

        // Create a new directory for the specific subject
        createDirectory(choice);
    }

    public static void createDirectory(String subject){
        System.out.println("Enter the path to create a directory for " + subject + " subject");
        Scanner sc = new Scanner(System.in);
        String path = sc.next();
        path = path + subject;
        // Creating a File object
        File file = new File(path);
        //Creating the directory
        boolean bool = file.mkdir();
        if(bool){
            System.out.println(subject + "directory created successfully");
        }
        else{
            System.out.println("Fail on creating specified directory");
        }
    }
}
