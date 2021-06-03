/*
 * Name: Yuyang Liu
 * Date: May 29
 * Teacher: Mr.Ho
 * Description: Question-Bank
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Scanner;

import java.util.ArrayList;

public class QBank {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // Create all possible variables used
        int choice = 9;
        String question = null;
        String subject = new String();

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
                uploadQuestion(subject);
            }
            else if(choice == 3){
                readFile(question);
            }
        }while(choice != 0);
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

    public static boolean uploadQuestion(String subject){
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
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
        return true;
    }

    public static String readFile(String question){
        String title, subject, reader, choice;
        Scanner fileReader = null;

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
        return question;
    }

    public static String generateQuestion(String question){
        Scanner sc = new Scanner(System.in);
        int var, num;
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
        
        System.out.println("value0: " + value[0]);
        System.out.println("value1: " + value[1]);
        System.out.println("value1: " + value[2]);

        try{
            for(int i = 0; i< num; i++){
                System.out.println(value[i + 1]);
                System.out.println(orig[i]);
                if((orig[i] != null) && (!value[i + 1].equals(""))){
                    sb.setCharAt(orig[i], value[i + 1].charAt(0));
                    question = sb.toString();
                }
            }
        }
        catch(NullPointerException npe){
            System.out.println("An error occurred.");
        }
        
        return(question);
    }
}