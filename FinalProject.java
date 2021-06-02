import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

class FinalProject{
    
    public static void main(String[] args)throws FileNotFoundException{
        // Initialize variables
        Scanner sc = new Scanner(System.in);
        String userInput, selectQuestion, Solutiontype, exitCondition, solutionOption;
        boolean loop = true;
        selectQuestion = "1";
        
        solutionOption = "2";
        exitCondition = "0";
        
        // Allow users to reinput and choose between options provided
        do{
            printMenu();
            userInput = sc.nextLine();
            
            if (userInput.equals(selectQuestion)){
                loadFile(userInput);
            }
            
            else if (userInput.equals(solutionOption)){
                while(loop == true){
                    System.out.println("Which solution would you like to be view(3 for yourself, 4 for Sample)?");
                    Solutiontype = sc.nextLine();
                    if(Solutiontype.equals("3")){
                        //UserSolution();
                        System.out.println("hi");
                        loop = false;
                    }
                    else if(Solutiontype.equals("4")){
                        loadFile(Solutiontype);
                        
                        loop = false;
                    }

                    else{
                        System.out.println("You must pick a option please pick it again");
                        loop = true;
                    }
                }
            }

            else{
                System.out.println("Please type in a valid option.");
            }
        }while (!userInput.equals(exitCondition));

        sc.close();
        System.out.println("Program Terminated.");
    }

    /*
     * Description: Print out the main menu
     * 
     * @return - list of options
     * */
    public static void printMenu(){
        System.out.println("1. Select a question from a sample\n"
        .concat("2. Get solution (either from sample or solution of your new equation).\n")
        .concat("0. Quit\n")
        );
    }
    
    public static void loadFile(String Option) throws FileNotFoundException{
        String[] Equation = new String[10];
        if(Option.equals("1")){
            // Open File
            Scanner sc = new Scanner(System.in);
            System.out.println("Type the subject of chosing file and the type of problem(word or equation) (Ex: Mathequation.csv) in order to output the question:");
            String filename = sc.nextLine();
            String fileName = filename.toUpperCase();

            // Initialize a variable used to read the file line by line
            String line = "";

            // Loading the file into the program
            FileReader saleFile = new FileReader(fileName);

            try {
                // Read the file by creating Scanner instance to read the file in the java
                BufferedReader br = new BufferedReader(saleFile);

                // If there are characters in the next line, the loop will continue on
                while((line = br.readLine()) != null){
                    // Use .split to separate list number & equation 
                    Equation = line.split(",");
                    
                    SelectingQuestion(Equation, Option);
                    // Print the list & equation 
                }
                br.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }   // catch any errors
        }
        else if(Option.equals("4")){
            SelectingQuestion(Equation, Option);
        }
        
    }
    
    public static void SelectingQuestion(String[] Equation, String option){
        Scanner sc = new Scanner(System.in);
        String filepath = "Individuals.txt";
        String SelectedQuestion = new String();
        if(option.equals("1")){
            //new HashMap object
            Map<String, String> hMapData = new HashMap<String, String>();

            for(String part : Equation){
                
                //split the employee data by : to get id and name
                String empdata[] = part.split(":");
                
                String strId = empdata[0].trim();
                String strName = empdata[1].trim();
                
                //add to map
                hMapData.put(strId, strName);
            }
            
            System.out.println("String to HashMap: " + hMapData);

            System.out.println(); // to skip line looks nicer to user
            // prompt user to chose a equation in the hashmap
            System.out.println("Every Equation has a number list in front of it, please select a question you interesting in.");
            String QuestionNumber = sc.nextLine();
            SelectedQuestion = hMapData.get(QuestionNumber) + " ";    
            System.out.println("Your equation is: " + SelectedQuestion); 

            try{
                FileWriter fw = new FileWriter(filepath, false); // initlized the FileWriter and lets the it overwrite the results.csv each time
                BufferedWriter bw = new BufferedWriter(fw); // BufferedWriter initlized 
                PrintWriter pw = new PrintWriter(bw); //PrintWriter initlized
                
                pw.println(SelectedQuestion); // print the equation into individuals file.
               
                pw.flush(); //flush the PrintWriter
                pw.close(); // close the printwriter
    
                System.out.println("Record success"); // Print out record success to user once it goes into text file
                System.out.println(); // make look nicer
            }catch(Exception E){
                System.out.println("Record not success"); // Print out record not success to user once it didn't goes into text file
            }
        }
        
        else if(option.equals("3")){
            System.out.println("hi");
        }
        else if(option.equals("4")){
            
            Solution(filepath);


        }
        

    }

    public static void Solution(String filepath){

        try{
            FileReader file = new FileReader(filepath);
            BufferedReader reader = new BufferedReader(file);
            String Operation[] = new String[5];
            int num[] = new int[5];
            double counter1 = 0.0;
            
            double answer = 0.0;
            for(int i = 0; i< 1;  i++){
                String line = reader.readLine();
                
                for(int split = 0; split < 5; split+=2){
                    num[split] = Integer.parseInt(line.split(" ")[split]);
                }
                for(int split = 1; split < 5; split+=2){
                    Operation[split]= line.split(" ")[split];
                }
            }

            // SETTING UP BEDMAS FOR *
            if(Operation[1].equals("x")){
                if(Operation[3].equals("^")){
                    answer += num[0] * Math.pow(num[2], num[4]);
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("x")){
                    answer += num[0] * num[2] * num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("/")){
                    answer += num[0] * num[2] / num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("+")){
                    answer += num[0] * num[2] + num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("-")){
                    answer += num[0] * num[2] - num[4];;
                }
            }
            
            // BEDMAS FOR /
            else if(Operation[1].equals("/")){
                if(Operation[3].equals("^")){
                    answer += num[0] / Math.pow(num[2], num[4]);
                }
                else if(Operation[3].equals("x")){
                    answer += num[0] / num[2] * num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("/")){
                    answer += num[0] / num[2] / num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("+")){
                    answer += num[0] / num[2] + num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("-")){
                    answer += num[0] / num[2] - num[4];
                    System.out.println("The Solution is: " + answer);
                }
            }

            // BEDMAS FOR ADDING
            else if(Operation[1].equals("+")){
                if(Operation[3].equals("x")){
                    answer+= num[0] + num[2]*num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("/")){
                    answer+= num[0] + num[2] / num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("^")){
                    answer+= num[0] + Math.pow(num[2], num[4]);
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("-")){
                    answer += num[0]+ num[2] - num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else{
                    answer += num[0] + num[2] + num[4];
                    System.out.println("The Solution is: " + answer);
                }

            }
            else if(Operation[1].equals("-")){
                if(Operation[3].equals("+")){
                    answer += num[0] - num[2] + num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("-")){
                    answer += num[0] - num[2] - num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("x")){
                    answer += num[0] - num[2] * num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("/")){
                    answer += num[0] - num[2] / num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("^")){
                    answer += num[0] - Math.pow(num[2], num[4]);
                    System.out.println("The Solution is: " + answer);
                }
                
            }
            else if(Operation[1].equals("^")){
                if(Operation[3].equals("^")){
                    counter1 += Math.pow(num[0], num[2]); 
                    answer += Math.pow(counter1, num[4]);
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("x")){
                    answer += Math.pow(num[0], num[2]) * num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("/")){
                    answer += Math.pow(num[0], num[2]) / num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("-")){
                    answer += Math.pow(num[0], num[2]) - num[4];
                    System.out.println("The Solution is: " + answer);
                }
                else if(Operation[3].equals("+")){
                    answer += Math.pow(num[0], num[2]) + num[4];
                    System.out.println("The Solution is: " + answer);
                }
            }
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }   // catch any errors)
    }
    
    
    public static void UserSolution(){

    }
    
}
