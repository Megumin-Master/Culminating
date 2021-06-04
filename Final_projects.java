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

class Final_Projects{
    
    public static void main(String[] args)throws FileNotFoundException{
        // Initialize variables
        Scanner scan = new Scanner(System.in);
        String userInput, selectQuestion, exitCondition;
        
        selectQuestion = "1";
        exitCondition = "0";
        
        // Allow users to reinput and choose between options provided
        do{
            printMenu();
            userInput = scan.nextLine();
            
            if (userInput.equals(selectQuestion)){
                loadFile(userInput);
            }

            else{
                System.out.println("Please type in a valid option.");
            }
        }while (!userInput.equals(exitCondition));

        scan.close();
        System.out.println("Program Terminated.");
    }

    /*
     * Description: Print out the main menu
     * 
     * @return - list of options
     * */
    public static void printMenu(){
        System.out.println("1. Select a question from a sample\n"
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
        else if(Option.equals("2")){
            SelectingQuestion(Equation, Option);
        }
        
    }
    
    public static void SelectingQuestion(String[] Equation, String option){
        
        Scanner sc = new Scanner(System.in);
        
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

            
            int value1 = 0, value2 = 0, value3 = 0;
            System.out.println("Do you want to Edit the Equation or Keep it same. (1 for Edit, 2 for Keep it same)");
            String EditOrNot = new String();
            EditOrNot = sc.nextLine();
            if(EditOrNot.equals("1")){
                
                System.out.println("What is your 3 new values, by looking at the equation that you have selected, please enter the value in order. Enter 0 for the values that is not being used.");
                value1 = sc.nextInt();
                value2 = sc.nextInt();
                value3 = sc.nextInt();
                
                System.out.println("Down below is the solution for the new values");
                UserSolution(value1, value2, value3, SelectedQuestion);
                
            }
            else if(EditOrNot.equals("2")){
                System.out.println("There you go, here is the solution for this sample question");
                Solution(SelectedQuestion);
            }
        }
        
        
        

    }

    public static void Solution(String SelectedQuestion){

        int counter = 0;
        Scanner sc = new Scanner(SelectedQuestion);
        String line = new String();
        String[] Operation = new String[10];
        int[] num = new int[10];

        while(sc.hasNext()){

            line = sc.next();
            Operation[counter] = line;
            
            counter ++;
        }
        num[0] = Integer.parseInt(Operation[0]);
        num[2] = Integer.parseInt(Operation[2]);
        num[4] = Integer.parseInt(Operation[4]);
        
        System.out.println(Calculation(Operation, num));
           
    }
    public static void UserSolution(int value1, int value2, int value3, String SelectedQuestion){
        int counter = 0;
        Scanner sc = new Scanner(SelectedQuestion);
        String line = new String();
        String[] Operation = new String[10];
        int[] num = new int[10];

        while(sc.hasNext()){

            line = sc.next();
            Operation[counter] = line;
            
            counter ++;
        }
            
        num[0] = value1;
        num[2] = value2;
        num[4] = value3;
            

        System.out.println(Calculation(Operation, num));
           
        
    }
    public static double Calculation(String[] Operation, int[] num){
        double counter1 = 0.0;
        double answer = 0.0;
        // SETTING UP BEDMAS FOR *
        
            if(Operation[1].equals("x")){
                if(Operation[3].equals("^")){
                    answer += num[0] * Math.pow(num[2], num[4]);
                }
                else if(Operation[3].equals("x")){
                    answer += num[0] * num[2] * num[4];
                    }
                else if(Operation[3].equals("/")){
                    answer += num[0] * num[2] / num[4];
                }
                else if(Operation[3].equals("+")){
                    answer += num[0] * num[2] + num[4];
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
                }
                else if(Operation[3].equals("/")){
                    answer += num[0] / num[2] / num[4];
                }
                else if(Operation[3].equals("+")){
                    answer += num[0] / num[2] + num[4];
                }
                else if(Operation[3].equals("-")){
                    answer += num[0] / num[2] - num[4];
                }
            }
    
            // BEDMAS FOR ADDING
            else if(Operation[1].equals("+")){
                if(Operation[3].equals("x")){
                    answer+= num[0] + num[2]*num[4];
                }
                else if(Operation[3].equals("/")){
                    answer+= num[0] + num[2] / num[4];
                }
                else if(Operation[3].equals("^")){
                    answer+= num[0] + Math.pow(num[2], num[4]);
                }
                else if(Operation[3].equals("-")){
                    answer += num[0]+ num[2] - num[4];
                }
                else{
                    answer += num[0] + num[2] + num[4];
                }
    
            }
            else if(Operation[1].equals("-")){
                if(Operation[3].equals("+")){
                    answer += num[0] - num[2] + num[4];
                }
                else if(Operation[3].equals("-")){
                    answer += num[0] - num[2] - num[4];
                }
                else if(Operation[3].equals("x")){
                    answer += num[0] - num[2] * num[4];
                }
                else if(Operation[3].equals("/")){
                    answer += num[0] - num[2] / num[4];
                }
                else if(Operation[3].equals("^")){
                    answer += num[0] - Math.pow(num[2], num[4]);
                }
                        
            }
            else if(Operation[1].equals("^")){
                if(Operation[3].equals("^")){
                    counter1 += Math.pow(num[0], num[2]); 
                    answer += Math.pow(counter1, num[4]);
                }
                else if(Operation[3].equals("x")){
                    answer += Math.pow(num[0], num[2]) * num[4];
                }
                else if(Operation[3].equals("/")){
                    answer += Math.pow(num[0], num[2]) / num[4];
                }
                else if(Operation[3].equals("-")){
                    answer += Math.pow(num[0], num[2]) - num[4];
                }
                else if(Operation[3].equals("+")){
                    answer += Math.pow(num[0], num[2]) + num[4];
                }
            }
        
        
        
        return answer;
    } 
    
    
}

