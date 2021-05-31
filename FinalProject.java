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
import java.util.HashMap;
import java.util.Map;

class FinalProject{
    public static void main(String[] args)throws FileNotFoundException{
        // Initialize variables
        Scanner customer = new Scanner(System.in);
        String userInput, selectQuestion, newEquation, exitCondition, solutionOption;
        selectQuestion = "1";
        newEquation = "2";
        solutionOption = "3";
        exitCondition = "0";

        // Allow users to reinput and choose between options provided
        do{
            printMenu();
            userInput = customer.nextLine();
            
            if (userInput.equals(selectQuestion)){
                loadFile();
            }
            else if (userInput.equals(newEquation)){
                newEquation();
            }
            else if (userInput.equals(solutionOption)){
                //loadFile(userInput);
            }
            else{
                System.out.println("Please type in a valid option.");
            }
        }while (!userInput.equals(exitCondition));

        customer.close();
        System.out.println("Program Terminated.");
    }

    /*
     * Description: Print out the main menu
     * 
     * @return - list of options
     * */
    public static void printMenu(){
        System.out.println("1. Select a question from a sample\n"
        .concat("2. Put down new equation into file\n")
        .concat("3. Get solution (either from sample or solution of your new equation).")
        .concat("9. Quit\n")
        );
    }
    
    public static void loadFile() throws FileNotFoundException{
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
                String[] Equation = line.split(",");
                
                SelectingQuestion(Equation);
                // Print the list & equation 


            }
            br.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }   // catch any errors
    }
    
    public static void SelectingQuestion(String[] Equation){

        Scanner sc = new Scanner(System.in);
        //new HashMap object
        Map<String, String> hMapData = new HashMap<String, String>();

        for(String part : Equation){
            
            //split the employee data by : to get id and name
            String empdata[] = part.split(":");
            
            String strId = empdata[0].trim();
            String strName = empdata[1].trim();
            
            //add to map
            hMapData.put(strId, " " + strName);
        }
        
        System.out.println("String to HashMap: " + hMapData);

        System.out.println(); // to skip line looks nicer to user
        // prompt user to chose a equation in the hashmap
        System.out.println("Every Equation has a number list in front of it, please select a question you interesting in.");
        String QuestionNumber = sc.nextLine();
        System.out.println("Your equation is: " + hMapData.get(QuestionNumber)); 

    }

    public static void newEquation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your subject of the problem(Math, chemistry, physics) and then enter type of problem(Ex: MathEquation)");
        String subject = sc.nextLine();
        String QUESTIONFILENAME = subject.toLowerCase() + "_Question.csv";
        String SOLUTIONFILENAME = subject.toLowerCase() + "_Solution.csv";

        // create a new file using the name above
        try{
            File problem = new File(QUESTIONFILENAME);
            File Answer = new File(SOLUTIONFILENAME);
            if ((problem.createNewFile()) && (Answer.createNewFile())){
                System.out.println("File created: " + problem.getName() + " & " + Answer.getName() + "\n"
                .concat("Write down your equation now!\n")
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
        System.out.println("Type your equation problem in the terminal");

        // Write the question into the file
        try {
            FileWriter fw = new FileWriter(QUESTIONFILENAME, true); // Add up idea each time. 
            fw.write(sc.nextLine());
            fw.write("\r\n");
            fw.write(sc.nextLine());
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    
    
}
