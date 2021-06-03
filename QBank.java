/*
 * Name: Yuyang Liu
 * Date: May 29
 * Teacher: Mr.Ho
 * Description: Question-Bank
*/

<<<<<<< Updated upstream
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.FileWriter;
=======
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Scanner;

import java.util.ArrayList;
>>>>>>> Stashed changes

public class QBank {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // Create all possible variables used
<<<<<<< Updated upstream
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
=======
        int choice = 9;

        // Print the main menu and prompt the user to choose
        do{
            System.out.println("1. Check for operation symbols used for a standard formula\n"
            .concat("2. Store your new ideas into the Question Bank\n")
            .concat("3. Search for questions and practice with solving new values\n")
            .concat("0. Exit\n")
            );
            choice = sc.nextInt();

            // Verify user's option
            if(choice == 1){
                funcDictionary();
            }
            else if((choice == 2) && (createFile())){
                uploadQuestion();
            }
            else if(choice == 3){
                readFile();
            }
        }while(choice != 0);
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
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

=======
    public static boolean createFile(){
        try{
            File math = new File("math.txt");
            File chem = new File("chem.txt");
            File phys = new File("phys.txt");
            
            if((math.createNewFile()) && (chem.createNewFile()) && (phys.createNewFile())){
            }
        }  
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return true;
    }

    public static void uploadQuestion(){
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        String subject = new String();
        String requestA = "Now write down your word problem.\n";
        String requestB = "The titile of the question should be in the bracket.\n";
        String requestC = "And remember to enter the known condition in Arabic numerals.\n";
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the subject of your word problem.");
        subject = sc.nextLine();      

        if(subject.equals("math")){
            try{
                fw = new FileWriter("math.txt", true);
                bw = new BufferedWriter(fw);
                pw = new PrintWriter(bw);

                System.out.println(requestA
                .concat("Ex. [Area problem]The rectangle is 5cm in length and 2cm in width, what is its area?\n")
                .concat(requestB)
                .concat(requestC)
                );

                pw.println(sc.nextLine());
                pw.println("");
                System.out.println("Successfully wrote to the file.");
                pw.flush();
            } catch(IOException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        else if(subject.equals("chemistry")){
            try{
                fw = new FileWriter("chem.txt", true);
                bw = new BufferedWriter(fw);
                pw = new PrintWriter(bw);

                System.out.println(requestA
                .concat("Ex. [mole calculation]The molar mass for magnesium is 24 g/mol, how many moles are in 2140 g of magnesium?\n")
                .concat(requestB)
                .concat(requestC)
                );

                pw.println(sc.nextLine());
                pw.println("");
                System.out.println("Successfully wrote to the file.");
                pw.flush();
            } catch(IOException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        else{
            try{
                fw = new FileWriter("phys.txt", true);
                bw = new BufferedWriter(fw);
                pw = new PrintWriter(bw);

                System.out.println(requestA
                .concat("Ex. [gravitational force problem]The mass of an object is 19 kg, the gravitational constant is 9.8 N/kg, what's the force the earth exerts on the object?\n")
                .concat(requestB)
                .concat(requestC)
                );

                pw.println(sc.nextLine());
                pw.println("");
                System.out.println("Successfully wrote to the file.");
                pw.flush();
            } 
            catch(IOException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public static void readFile(){
        String title, subject, reader, choice;
        Scanner fileReader = null;
        String question = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the subject.");
        subject = sc.nextLine();

        try{
            File math = new File("math.txt");
            File chem = new File("chem.txt");
            File phys = new File("phys.txt");
            if(subject.equals("math")){
                fileReader = new Scanner(math);
            }
            else if(subject.equals("chemistry")){ 
                fileReader = new Scanner(chem);
            }
            else if(subject.equals("physics")){
                fileReader = new Scanner(phys);
            }
            else{
                System.out.println("Does not support this subject for now.");
                for(int i = 0; i< 1; i++){
                    main(null);
                }
            } 
            
            System.out.println("Enter the title of the question (Without brakets).");
            title = sc.nextLine();
            while(fileReader.hasNextLine()){
                reader =fileReader.nextLine();
                if(reader.substring(reader.indexOf("[") + 1, reader.indexOf("]")).equals(title)){
                    question = reader;
                    System.out.println(question);
                    break;
                }
                else{
                    System.out.println("Question not found.");
                    break;
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("Wanna change the known conditions? Enter 'yes' if you do.");
        choice = sc.nextLine();
        if(choice.equals("yes")){
            generateQuestion(question);
        }
        else{
        }
    }

    public static void generateQuestion(String question){
        Scanner sc = new Scanner(System.in);
        int var, integer, num;
        int count = -1;
        String character;

        System.out.println("Enter the number of known conditions.");
        num = sc.nextInt();
        String[] value = new String[num + 1];
        Integer[] orig = new Integer[num + 1];
        String newQuestion = " ";

        for(int i = 0; i< question.length(); i++){
            try{
                if(Character.isDigit(question.charAt(i))){
                    count += 1;
                    orig[count] = i;
                }
            }
            catch(NumberFormatException nfe){
            }
        }

        StringBuilder sb = new StringBuilder(question);
        System.out.println("Enter the new value in order of precedence.");
        for(int i = 0; i< num + 1; i++){
            value[i] = sc.nextLine();
        }

        try{
            for(int i = 0; i< num; i++){
                if((orig[i] != null) && (!value[i + 1].equals(""))){
                    sb.setCharAt(orig[i], value[i + 1].charAt(0));
                    newQuestion = sb.toString();
                }
            }
        }
        catch(NullPointerException npe){
            System.out.println("An error occurred.");
        }
        
        System.out.println(newQuestion);
>>>>>>> Stashed changes
    }
}
