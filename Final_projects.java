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
        }while (!userInput.equals(exitCondition)); // Keep let the user to prompt if the user input is not corrected

        scan.close();
        System.out.println("Program Terminated."); // if the user input "0" the program end
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
            // prompt the user to enter type of the subject and type of question and then the .csv in the end. This will open specific subject file
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
                    
                    
                    // Use the the array equation and option as a param
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
                
    }
    /*
     * Author - Jeffrey Lin
     * Description - put the equation in csv file into a hashmap so the user can selected equation by that
     * 
     * @param - String Equation array from above and the String variable of use's menu input
     * @Return  - overall goal is using the method to return the final solution
     */ 
    public static void SelectingQuestion(String[] Equation, String option){
        
        Scanner sc = new Scanner(System.in); // Initlized scanner
        
        String SelectedQuestion = new String(); 
        
        if(option.equals("1")){
            //new HashMap object
            Map<String, String> hMapData = new HashMap<String, String>();

            for(String part : Equation){
                
                //split the employee data by : to get id and name
                String empdata[] = part.split(":");
                
                String strId = empdata[0].trim();// get the index 0 to strID
                String strName = empdata[1].trim();// get the index 1 to strName
                
                //add to map
                hMapData.put(strId, strName);
            }
            
            System.out.println("String to HashMap: " + hMapData);

            System.out.println(); // to skip line looks nicer to user
            // prompt user to chose a equation in the hashmap
            System.out.println("Every Equation has a number list in front of it, please select a question you interesting in.");
            String QuestionNumber = sc.nextLine();
            SelectedQuestion = hMapData.get(QuestionNumber) + " ";    // put equation into the string variable called SelectedQuestion
            System.out.println("Your equation is: " + SelectedQuestion); // print out for user

            //Initlized double value 1,2,3
            double value1 = 0.0, value2 = 0.0, value3 = 0.0;
            System.out.println("Do you want to Edit the Equation or Keep it same. (1 for Edit, 2 for Keep it same)"); // prompt user whether they want to change some number in the equation or keep it as the same
            String EditOrNot = new String();
            EditOrNot = sc.nextLine();
            if(EditOrNot.equals("1")){ // if user input 1
                //prompt the user to input 3 new values
                System.out.println("What is your 3 new values, by looking at the equation that you have selected, please enter the value in order. Enter 0 for the values that is not being used.");
                //get the double value of user's input
                value1 = sc.nextDouble(); 
                value2 = sc.nextDouble();
                value3 = sc.nextDouble();
                
                System.out.println("Down below is the solution for the new values");
                // Method that will use the value 1,2,3 and the equation for calculating
                UserSolution(value1, value2, value3, SelectedQuestion);
                
            }
            else if(EditOrNot.equals("2")){ // if user input 2 
                System.out.println("There you go, here is the solution for this sample question");
                // Then since is keeping it as the same, no new value should be parameterize, there method "Solution" use just the string equation
                Solution(SelectedQuestion);
            }
        }
        
        
        

    }
    /*
     * Author - Jeffrey Lin
     * Description - Solution method to use scanner to read the string equation and then store the integer into int array and operation and other string to string[] array
     * 
     * @param - String equation from above method 
     * @return the Calculation method results (Basically the solution)
     */

    public static void Solution(String SelectedQuestion){

        int counter = 0;
        Scanner sc = new Scanner(SelectedQuestion); // Scanner read the string
        String line = new String();
        String[] Operation = new String[20]; // set the string array length to 20 just for making sure that it is out of range
        double[] num = new double[10]; // set the int array length to 10

        //While loop condition: whenever the sc found next character the loop continue
        while(sc.hasNext()){

            line = sc.next(); // put all the next character into string variable called line
            Operation[counter] = line; // store each time string into each index of the operation array
            
            counter ++; // +1 everytime
        }
        //////////////////////////////////////////////////////////////////////
        // Because the different length of equation the location of integer is different, if statement helps to collect the right location of the integer
        /////////////////////////////////////////////////////////////////////////
        // if the equation lenght is 3, the integer will only be two and change string double integer to double num[] array
        if(Operation[3] == null){
            num[0] = Double.parseDouble(Operation[0]);
            num[2] = Double.parseDouble(Operation[2]);
        }
        // If the the equation length is 5, the String will be change to double from the follow location
        else if(Operation[5] == null){
            num[0] = Double.parseDouble(Operation[0]);
            num[2] = Double.parseDouble(Operation[2]);
            num[4] = Double.parseDouble(Operation[4]);
        }
        // If the the equation length is 7, the String will be change to double from the follow location
        else if(Operation[7] == null){
            // if bracket, the String will be change to double from the follow location
            if(Operation[0].equals("(") && Operation[4].equals(")")){
                num[0] = Double.parseDouble(Operation[1]);
                num[2] = Double.parseDouble(Operation[3]);
                num[4] = Double.parseDouble(Operation[6]);
            }
            // if not, the String will be change to double from the follow location
            else{
                num[0] = Double.parseDouble(Operation[2]);
                num[2] = Double.parseDouble(Operation[5]);
            }
        // If the the equation length is 10, the String will be change to double from the follow location  
        }
        else if(Operation[10] == null){
            num[0] = Double.parseDouble(Operation[2]);
            num[2] = Double.parseDouble(Operation[5]);
            num[4] = Double.parseDouble(Operation[8]);
        }
        // If the the equation length is 12, the String will be change to double from the follow location
        else if(Operation[12] == null){
            if(Operation[5].equals("(") && Operation[11].equals(")")){
                num[0] = Double.parseDouble(Operation[2]);
                num[2] = Double.parseDouble(Operation[6]);
                num[4] = Double.parseDouble(Operation[9]);
            }
        }
        
        // Print the results basically
        System.out.println(Calculation(Operation, num));
           
    }
    /*
     * Author - Jeffrey Lin
     * Description: To scan the new equation of the new 3 values, base on the length of equation, will store all the neceessary integer into int array and operation to string array
     * 
     * @param - double value1, double value2, double value3, and the string of the new equation. value1,2,3 is whatever the user had Input
     * @return - return the method of "Calculation" by using the string array and int array in this methods
     */
    public static void UserSolution(double value1, double value2, double value3, String SelectedQuestion){
        int counter = 0; // Initlized int counter
        Scanner sc = new Scanner(SelectedQuestion); // scan question
        String line = new String();
        String[] Operation = new String[20]; // set the string array length to 20 just for making sure that it is out of range
        double[] num = new double[10]; // set the int array length to 10

        //While loop condition: whenever the sc found next character the loop continue
        while(sc.hasNext()){

            line = sc.next(); // put all the next character into string variable called line
            Operation[counter] = line; // store each time string into each index of the operation array
            
            counter ++; // +1 everytime
        }
        
        num[0] = value1; // int array to put value that user input into a int array
        num[2] = value2;
        num[4] = value3;
            

        System.out.println(Calculation(Operation, num));
           
        
    }
    /*
     * Author - Jeffrey Lin
     * Description: Use if statement to calculates the final solution in any condition of equations
     * 
     * @param - the String array from above method: String[] Operation, and the int array from above method: int[] num
     * @return - The answer which is the solution
     */
    public static double Calculation(String[] Operation, double[] num){
        double counter1 = 0.0;
        double answer = 0.0;
        
        // SETTING UP BEDMAS FOR *
        if(Operation[3] == null){ // if index of Operation[3] is nothing: DO FOLLOWING
            if(Operation[1].equals("x")){
                answer += num[0] * num[2];
            }
            else if(Operation[1].equals("/")){
                answer += num[0] / num[2];
            }
            else if(Operation[1].equals("-")){
                answer += num[0] - num[2];
            }
            else if(Operation[1].equals("+")){
                answer += num[0] + num[2];
            }
            else if(Operation[1].equals("^")){
                answer += Math.pow(num[0], num[2]);
            }
        }
        
        // ELSE IF Operation[5] is nothing: DO FOLLOWING
        else if(Operation[5] == null){
            //////////////////////////////////////////////////////////////////////////////
            // DOWN BELOW THE COMPOUND IF STATEMENT IS FOR THE BEDMAS OF THIS CONDITION
            //////////////////////////////////////////////////////////////////////////////

            // BEDMAS FOR *
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
                    answer += num[0] * num[2] - num[4];
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
            // BEDMAS FOR substracting 
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
            // BEDMAS FOR POWER
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
        }

        // ELSE IF Operation[7] is nothing: DO FOLLOWING
        else if(Operation[7] == null){
            // if there is bracket do what is in the bracket first
            if(Operation[0].equals("(") && Operation[4].equals(")")){
                //BEDMAS FOR ADDING
                if(Operation[2].equals("+")){
                    if(Operation[5].equals("/")){
                        answer += (num[0] + num[2]) / num[4];
                    }
                    else if(Operation[5].equals("x")){
                        answer += (num[0] + num[2]) * num[4];
                    }
                    else if(Operation[5].equals("^")){
                        counter1 += (num[0] + num[2]);
                        answer += Math.pow(counter1, num[4]);
                    }
                    else if(Operation[5].equals("-")){
                        answer += (num[0] + num[2]) - num[4];
                    }
                    else if(Operation[5].equals("+")){
                        answer += (num[0] + num[2]) + num[4];
                    }
                }
                // BEDMAS FOR *
                else if(Operation[2].equals("x")){
                    if(Operation[5].equals("-")){
                        answer += (num[0] * num[2]) - num[4];
                    }
                    else if(Operation[5].equals("+")){
                        answer += (num[0] * num[2]) + num[4];
                    }
                    else if(Operation[5].equals("x")){
                        answer += (num[0] * num[2]) * num[4];
                    }
                    else if(Operation[5].equals("/")){
                        answer += (num[0] * num[2]) / num[4];
                    }
                    else if(Operation[5].equals("^")){
                        counter1 += (num[0] * num[2]);
                        answer += Math.pow(counter1, num[4]);
                    }
                }
                // BEDMAS FOR /
                else if(Operation[2].equals("/")){
                    if(Operation[5].equals("-")){
                        answer += (num[0] / num[2]) - num[4];
                    }
                    else if(Operation[5].equals("+")){
                        answer += (num[0] / num[2]) + num[4];
                    }
                    else if(Operation[5].equals("x")){
                        answer += (num[0] / num[2]) * num[4];
                    }
                    else if(Operation[5].equals("/")){
                        answer += (num[0] / num[2]) / num[4];
                    }
                    else if(Operation[5].equals("^")){
                        counter1 += (num[0] / num[2]);
                        answer += Math.pow(counter1, num[4]);
                    }
                }
                // BEDMAS FOR - 
                else if(Operation[2].equals("-")){
                    if(Operation[5].equals("-")){
                        answer += (num[0] - num[2]) - num[4];
                    }
                    else if(Operation[5].equals("+")){
                        answer += (num[0] - num[2]) + num[4];
                    }
                    else if(Operation[5].equals("x")){
                        answer += (num[0] - num[2]) * num[4];
                    }
                    else if(Operation[5].equals("/")){
                        answer += (num[0] - num[2]) / num[4];
                    }
                    else if(Operation[5].equals("^")){
                        counter1 += (num[0] - num[2]);
                        answer += Math.pow(counter1, num[4]);
                    }
                }
                //BEDMAS FOR POWER
                else if(Operation[2].equals("^")){
                    if(Operation[5].equals("-")){
                        answer += Math.pow(num[0], num[2]) - num[4];
                    }
                    else if(Operation[5].equals("+")){
                        answer += Math.pow(num[0], num[2]) + num[4];
                    }
                    else if(Operation[5].equals("/")){
                        answer += Math.pow(num[0], num[2]) / num[4];
                    }
                    else if(Operation[5].equals("x")){
                        answer += Math.pow(num[0], num[2]) * num[4];
                    }
                    else if(Operation[5].equals("^")){
                        counter1 += Math.pow(num[0], num[2]);
                        answer += Math.pow(counter1, num[4]);
                    }
                }
            }
            // IF THERE IS NO BRACKET
            else{
                if(Operation[4].equals("x")){
                    answer += num[0] * num[2];
                }
                else if(Operation[4].equals("/")){
                    answer += num[0] / num[2];
                }
                else if(Operation[4].equals("+")){
                    answer += num[0] + num[2];
                }
                else if(Operation[4].equals("-")){
                    answer += num[0] - num[2];
                }
                else if(Operation[4].equals("^")){
                    answer += Math.pow(num[0], num[2]);
                }
            }
        }

        // To check the equation that has length of 10
        // ElSE IF the Operation[10] has nothing: DO FOLLOWING
        else if(Operation[10] == null){
            //BEDMAS FOR *
            if(Operation[4].equals("x")){
                if(Operation[7].equals("^")){
                    answer += num[0] * Math.pow(num[2], num[4]);
                }
                else if(Operation[7].equals("x")){
                    answer += num[0] * num[2] * num[4];
                    }
                else if(Operation[7].equals("/")){
                    answer += num[0] * num[2] / num[4];
                }
                else if(Operation[7].equals("+")){
                    answer += num[0] * num[2] + num[4];
                }
                else if(Operation[7].equals("-")){
                    answer += num[0] * num[2] - num[4];
                }
            }
                    
            // BEDMAS FOR /
            else if(Operation[4].equals("/")){
                if(Operation[7].equals("^")){
                    answer += num[0] / Math.pow(num[2], num[4]);
                }
                else if(Operation[7].equals("x")){
                    answer += num[0] / num[2] * num[4];
                }
                else if(Operation[7].equals("/")){
                    answer += num[0] / num[2] / num[4];
                }
                else if(Operation[7].equals("+")){
                    answer += num[0] / num[2] + num[4];
                }
                else if(Operation[7].equals("-")){
                    answer += num[0] / num[2] - num[4];
                }
            }
    
            // BEDMAS FOR ADDING
            else if(Operation[4].equals("+")){
                if(Operation[7].equals("x")){
                    answer+= num[0] + num[2]*num[4];
                }
                else if(Operation[7].equals("/")){
                    answer+= num[0] + num[2] / num[4];
                }
                else if(Operation[7].equals("^")){
                    answer+= num[0] + Math.pow(num[2], num[4]);
                }
                else if(Operation[7].equals("-")){
                    answer += num[0]+ num[2] - num[4];
                }
                else if(Operation[7].equals("+")){
                    answer += num[0] + num[2] + num[4];
                }
    
            }
            //BEDMAS FOR -
            else if(Operation[4].equals("-")){
                if(Operation[7].equals("+")){
                    answer += num[0] - num[2] + num[4];
                }
                else if(Operation[7].equals("-")){
                    answer += num[0] - num[2] - num[4];
                }
                else if(Operation[7].equals("x")){
                    answer += num[0] - num[2] * num[4];
                }
                else if(Operation[7].equals("/")){
                    answer += num[0] - num[2] / num[4];
                }
                else if(Operation[7].equals("^")){
                    answer += num[0] - Math.pow(num[2], num[4]);
                }
                        
            }
            //BEDMAS FOR power
            else if(Operation[4].equals("^")){
                if(Operation[7].equals("^")){
                    counter1 += Math.pow(num[0], num[2]); 
                    answer += Math.pow(counter1, num[4]);
                }
                else if(Operation[7].equals("x")){
                    answer += Math.pow(num[0], num[2]) * num[4];
                }
                else if(Operation[7].equals("/")){
                    answer += Math.pow(num[0], num[2]) / num[4];
                }
                else if(Operation[7].equals("-")){
                    answer += Math.pow(num[0], num[2]) - num[4];
                }
                else if(Operation[7].equals("+")){
                    answer += Math.pow(num[0], num[2]) + num[4];
                }
            }
        }
        //////////////////////////////////////////////////////////////////////////////////////
        // Check the equation that length is 12
        // ELSE IF the Operaiton[12] has nothing: DO FOLLOWING!
        /////////////////////////////////////////////////////////////////////////////////////
        else if(Operation[12] == null){
            // CHECK IF THERE IS A BRACKET, if there is do what is in there first
            if(Operation[5].equals("(") && Operation[11].equals(")")){
                // BEDMAS FOR ADDING
                if(Operation[4].equals("+")){
                    if(Operation[8].equals("/")){
                        answer += num[0] + (num[2] / num[4]);
                    }
                    else if(Operation[8].equals("x")){
                        answer += num[0] + (num[2] * num[4]);
                    }
                    else if(Operation[8].equals("^")){
                        
                        answer += num[0] + (Math.pow(num[2], num[4]));
                    }
                    else if(Operation[8].equals("-")){
                        answer += num[0] + (num[2] - num[4]);
                    }
                    else if(Operation[8].equals("+")){
                        answer += num[0] + (num[2] + num[4]);
                    }
                }
                // BEDMAS FOR *
                else if(Operation[4].equals("x")){
                    if(Operation[8].equals("-")){
                        answer += num[0] * (num[2] - num[4]);
                    }
                    else if(Operation[8].equals("+")){
                        answer += num[0] * (num[2] + num[4]);
                    }
                    else if(Operation[8].equals("x")){
                        answer += num[0] * (num[2] * num[4]);
                    }
                    else if(Operation[8].equals("/")){
                        answer += num[0] * (num[2] / num[4]);
                    }
                    else if(Operation[8].equals("^")){
                        
                        answer += num[0]*(Math.pow(num[2], num[4]));
                    }
                }
                // BEDMAS FOR /
                else if(Operation[4].equals("/")){
                    if(Operation[8].equals("-")){
                        answer += num[0] / (num[2] - num[4]);
                    }
                    else if(Operation[8].equals("+")){
                        answer += num[0] / (num[2] + num[4]);
                    }
                    else if(Operation[8].equals("x")){
                        answer += num[0] / (num[2] * num[4]);
                    }
                    else if(Operation[8].equals("/")){
                        answer += num[0] / (num[2] / num[4]);
                    }
                    else if(Operation[8].equals("^")){
                        
                        answer += num[0] / (Math.pow(num[2], num[4]));
                    }
                }
                // BEDMAS FOR -
                else if(Operation[4].equals("-")){
                    if(Operation[8].equals("-")){
                        answer += num[0] - (num[2] - num[4]);
                    }
                    else if(Operation[8].equals("+")){
                        answer += num[0] - (num[2] + num[4]);
                    }
                    else if(Operation[8].equals("x")){
                        answer += num[0] - (num[2] * num[4]);
                    }
                    else if(Operation[8].equals("/")){
                        answer += num[0] - (num[2] / num[4]);
                    }
                    else if(Operation[8].equals("^")){
                        answer += num[0] - (Math.pow(num[2], num[4]));
                    }
                }
                
            }
        }
            
        // RETURN THE ANSWER
        return answer;
    } 
    
    
}

