/*
 * Name: Yuyang Liu
 * Date: May 29
 * Teacher: Mr.Ho
 * Description: Question-Bank
*/

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.FileWriter;

public class QBank {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // Create all possible variables used
        int choice = 0;
        String subject = new String();

        // Print the main menu and prompt the user to choose
        System.out.println("1. Check for operation symbols used for a standard formula\n"
        .concat("2. Store your new ideas into the Question Bank\n")
        .concat("3. Search for questions and practice with solving new values\n")
        .concat("0. Exit\n")    // I didn't put the do-while loop here so there isn't an option to exit
                                // Printing the menu is just to indicate function of each method
        );
        choice = sc.nextInt();

        // Verify user's option
        if(choice == 1){
            funcDictionary();
        }
        else if(choice == 2){
            createDirectory(subject);
        }
        else{
            searchQuestion(subject);
        }
    }

    /* 
     * Description: A dictionary containing particular functions' symbols
     * 
     * return - symbols validated in the program
     * */
    public static void funcDictionary(){
        String symName = new String();
        Scanner sc = new Scanner(System.in);
        System.out.println("This is the dictionary for particular functions\n"
        .concat("Enter a symbol name that you want to use\n")
        .concat("The dictionary will give you the corresponding symbols in the computer language\n")
        );
        symName = sc.nextLine();

        // Creating a My HashTable Dictionary
        Hashtable<String, String> sym_dict = new Hashtable<String, String>();

        // Using put method to specify the corresponding functions & operation symbols
        sym_dict.put("square root","√");
        sym_dict.put("arcsin","sin'");
        sym_dict.put("inverse sine","sin'");
        sym_dict.put("arccos","cos'");
        sym_dict.put("inverse cosine","cos'");
        sym_dict.put("arctan","tan'");
        sym_dict.put("inverse tangent","tan'");
        sym_dict.put("power","^");
        sym_dict.put("quotient","/");
        sym_dict.put("aliquot","//");
        sym_dict.put("remainder","%");

        // Using get() method for user to search for the symbol
        System.out.println(sym_dict.get(symName));
    }

    /*
     * Description: Create multiple directories to store the question files categorized by subject
     * 
     * @param subject - a string indicating specified subject chosen by user
     * @return - a new directory to store question files
     * */
    public static void createDirectory(String subject){
        Scanner sc = new Scanner(System.in);

        // Prompt the user to choose the subject
        System.out.println("Type in the subject's name (math, chemistry, physics...)");
        subject = sc.nextLine();

        // Prompt the user to enter the pathway for the directory
        System.out.println("Enter the path to create a directory for " + subject + " subject");
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

        uploadQuestions(subject);
    }

    /*
     * Description:
     * 
     * */
    public static void uploadQuestions(String subject){
        String title, fileName, solutionFile = new String();
        Scanner sc = new Scanner(System.in);

        // Prompt the user to input the file name
        System.out.println("Enter your word problem's title here!");
        title = sc.nextLine();
        fileName = title + "_Question.txt";
        solutionFile = title + "_Solution.txt";

        // Create new files (Questions & Solutions are separated)
        try{
            File Q = new File(fileName);
            File S = new File(solutionFile);
            if ((Q.createNewFile()) && (S.createNewFile())){
                System.out.println("File created: " + Q.getName() + " & " + S.getName() + "\n"
                .concat("Now record your brilliant ideas here!\n")
                );
            }
            else{
                System.out.println("File already exists.\n");
            }
        } catch(IOException e){
            System.out.println("An error occurred.\n");
            e.printStackTrace();
        }

        // Instructions to the question input section
        System.out.println("Type your word problem in the terminal\n"
        .concat("Remember to put the known conditions between two '#' symbols\n")
        .concat("SAMPLE INPUT: The length of the triangle is #5# centimeters and the width is #3# centimeters.\n")
        .concat("What is its area?\n")
        );

        // Write the question into the file
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(sc.nextLine());
            writer.write("\r\n");
            writer.write(sc.nextLine());
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Write the solution into the file (including variables and formulas)
        int varNum = 0;
        System.out.println("Enter the # of variables: ");
        varNum = sc.nextInt();
        System.out.println(varNum);
        String[] varArr = new String[varNum];

        for(int i = 0; i< varNum; i++){
            System.out.print("enter variabel num " + i + ": ");
            varArr[i] = sc.nextLine();
        }

    }

    /*
     * Description:
     * 
     * */
    public static void searchQuestion(String subject){

    }
}
