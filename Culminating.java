/*
 * Name: Benjamin Kim, David Han, Yuyang, Jeffrey
 * Date: June 4th, 2021
 * Teacher: Mr. Ho
 * Description: Culminating Assignment - Question Bank
 */

// Importing everything needed to run this program
import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
 * Author - Benjamin Kim
 * Description - Holds almost every javafx variable in the program
 */
public class Culminating extends Application implements EventHandler<ActionEvent>{
    
    static Stage window;    // Naming the Stage, which is the window, to window
    // All the scenes in this program
    Scene intro, mathScene, chemScene, physicsScene, sceneEnter, errorScene; 
    Scene previousScene1, previousScene2, previousScene3, editQuestion, useQuestion, copyQuestion, printAnswer;
    // All the buttons used in this program
    Button cont1, cont2, cont3, goBack1, goBack2, goBack3, goBack4, goBack5, goBack6, goBack7, goBack8, goBack9;
    Button edit1, edit2, edit3, use1, use2, use3, openDict, calculate;
    Label[] label8; // A label array because it is going to contain the same text in different scenes
    String[] qAndA; 
    // The text files 
    File math = new File("Math.txt");
    File mathA = new File("Math Answers.txt");
    File chem = new File("Chemistry.txt");
    File chemA = new File("Chemistry Answers.txt");
    File physics = new File("Physics.txt");
    File physicsA = new File("Physics Answers.txt");
    File dictionary = new File("Dictionary.txt");
    File readThis, readThisA;  // Blank file as it is going to be one of the files above depending on what scene came before

    public static void main(String[] args) {
        launch(args);  
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;  // Window is now officially the main stage
        window.setTitle("Question Bank");   // Naming the window "Question Bank"

        // Labels and all their positions according to pixels
        Label label1 = new Label("Welcome to the Question Bank!");
        label1.setTranslateY(-80);
        Label label2 = new Label("Here, you can ask, change, and use");
        label2.setTranslateY(-60);
        Label label3 = new Label("any questions you need!");
        label3.setTranslateY(-40);
        Label label4 = new Label("Start by choosing a subject");
        label4.setTranslateY(-20);
        Label label5 = new Label("Welcome to the Math files!");
        Label label6 = new Label("Welcome to the Chemistry files!");
        Label label7 = new Label("Welcome to the Physics files!");

        // Label array that makes duplicates of the same label
        label8 = new Label[3];
        for (int i = 0; i < 3; i++) {
            label8[i] = new Label("Would you like to make or use a question?");
            label8[i].setTranslateY(20);
        }
        // More labels
        Label label9 = new Label("Which number question would you like to edit?");
        Label error = new Label("The value you inputted is not valid. Go back and try again");
        Label justInCase = new Label("Enter the values in the same order as the original question");
        justInCase.setTranslateY(-20);
        Label num = new Label("Only enter numbers. Enter '0' for values not used");
        Label dict = new Label("Dictionary:");
        dict.setTranslateX(50);
        dict.setTranslateY(130);
        Label questionLabel = new Label();    // Blank label as it can change after each run
        questionLabel.setTranslateY(-40);   
        Label label10 = new Label("Which question would you like to use?");
        Label usedQuestion = new Label();   // Blank label as it can change after each run
        Label usedQuestionA = new Label();  // Blank label as it can change after each run
        usedQuestionA.setTranslateY(40);
        Label edittedQuestion = new Label(); // Blank label as it can change after each run
        edittedQuestion.setTranslateY(-40);
        Label edittedAnswer = new Label();  // Blank label as it can change after each run

        // Drop down menu
        ComboBox<String> comboBox = new ComboBox<>();   // Making a drop down menu of strings
        comboBox.getItems().addAll("Math", "Chemistry", "Physics"); // The options for the drop down menu
        comboBox.setPromptText("Subjects"); // Before user selects an option, the box says subjects so the user knows the topic
        comboBox.setTranslateY(10); // Positioning the drop down menu

        // A continue button for scene 1 after the user selects from the drop down menu
        cont1 = new Button("Continue");
        cont1.setTranslateY(50);

        // Go back buttons for the other scenes
        int x = -160;   // X coordinate that is the same among all goBack buttons
        int y = 130;    // Y coordinate that is the same among all goBack buttons
        goBack1 = new Button("Go Back");
        goBack1.setTranslateX(x);
        goBack1.setTranslateY(y);
        goBack2 = new Button("Go Back");
        goBack2.setTranslateX(x);
        goBack2.setTranslateY(y);
        goBack3 = new Button("Go Back");
        goBack3.setTranslateX(x);
        goBack3.setTranslateY(y);
        goBack4 = new Button("Go Back");
        goBack4.setTranslateX(x);
        goBack4.setTranslateY(y);
        goBack5 = new Button("Go Back");
        goBack5.setTranslateX(x);
        goBack5.setTranslateY(y);
        goBack6 = new Button("Go Back");
        goBack6.setTranslateX(x);
        goBack6.setTranslateY(y);
        goBack7 = new Button("Go Back");
        goBack7.setTranslateX(x);
        goBack7.setTranslateY(y);
        goBack8 = new Button("Go Back");
        goBack8.setTranslateX(x);
        goBack8.setTranslateY(y);
        goBack9 = new Button("Go Back");
        goBack9.setTranslateX(x);
        goBack9.setTranslateY(y);

        // Buttons that send users to make their own questions
        edit1 = new Button("Edit Math Questions!"); // The edit button for math questions
        edit1.setTranslateX(-75);
        edit1.setTranslateY(50);
        edit2 = new Button("Edit Chemistry Questions!");    // The edit button for chemistry questions
        edit2.setTranslateX(-80);
        edit2.setTranslateY(50);
        edit3 = new Button("Edit Physics Questions!");  // The edit button for physics questions
        edit3.setTranslateX(-80);
        edit3.setTranslateY(50);

        // Buttons that sends the user to copy and paste existing questions
        use1 = new Button("Use Math Questions!");   // The use button for math questions
        use1.setTranslateX(80);
        use1.setTranslateY(50);
        use2 = new Button("Use Chemistry Questions!");  // The use button for chemistry questions
        use2.setTranslateX(80);
        use2.setTranslateY(50);
        use3 = new Button("Use Physics Questions!");    // The use button for physics questions
        use3.setTranslateX(75);
        use3.setTranslateY(50);

        // Making a text field which is a text box. It is essentially fill in the blank. 
        int w = 60; // All boxes would be 60 pixels wide
        int Y = 40; // All boxes have this y coordinate
        TextField questionNum = new TextField();    // Text field where user inputs the question number they want to edit
        questionNum.setMaxWidth(w);    // Sets the length of the physical box
        questionNum.setTranslateY(30);  // Sets the position of the box
        TextField questionNum2 = new TextField();   // Text field where user inputs the question they want to use
        questionNum2.setMaxWidth(w);    // Sets length of the physical box
        questionNum2.setTranslateY(30);
        TextField value1 = new TextField("Value 1"); // Text field for the first value in any question. Has text so user knows what it is
        value1.setMaxWidth(w);  // Sets length of physical box
        value1.setTranslateX(-70); 
        value1.setTranslateY(Y);    
        TextField value2 = new TextField("Value 2"); // Text field for the first value in any question. Has text so user knows what it is
        value2.setMaxWidth(w);  // Sets length of physical box
        //value2.setTranslateX(); 
        value2.setTranslateY(Y);    
        TextField value3 = new TextField("Value 3"); // Text field for the first value in any question. Has text so user knows what it is
        value3.setMaxWidth(w);  // Sets length of physical box
        value3.setTranslateX(70); 
        value3.setTranslateY(Y);    
    
        // Continue button for after the user inputs something
        cont2 = new Button("Continue");
        cont2.setTranslateY(60);
        cont3 = new Button("Continue");
        cont3.setTranslateY(60);

        // openDict button. Opens a glossary of functions and formulas
        openDict = new Button("Open Dictionary");
        openDict.setTranslateX(135);
        openDict.setTranslateY(130);

        // Calculate button to calculate the new values
        calculate = new Button("Calculate");    // Making a new button called calculate
        calculate.setTranslateY(80);    // Positioning the button

        // The introduction scene
        StackPane layout1 = new StackPane();
        // Uses specific variables chosen from above in a scene
        layout1.getChildren().addAll(label1, label2, label3, label4, cont1, comboBox);  
        intro = new Scene(layout1, 300, 300);   // The size of the scene

        // Math Scene
        StackPane layout2 = new StackPane();
        // Uses specific variables chosen from above in a scene
        layout2.getChildren().addAll(label5, goBack1, label8[0], use1, edit1);
        mathScene = new Scene(layout2, 400, 300);   // The size of the scene

        // Chemistry Scene
        StackPane layout3 = new StackPane();
        // Uses specific variables chosen from above in a scene
        layout3.getChildren().addAll(label6, goBack2, label8[1], use2, edit2);
        chemScene = new Scene(layout3, 400, 300);   // The size of the scene

        // Physics Scene
        StackPane layout4 = new StackPane();
        // Uses specific variables chosen from above in a scene
        layout4.getChildren().addAll(label7, goBack3, label8[2], use3, edit3);
        physicsScene = new Scene(layout4, 400, 300);    // The size of the scene

        // Scene where user inputs question number they want to edit
        StackPane layout5 = new StackPane();
        // Uses specific variables chosen from above in a scene
        layout5.getChildren().addAll(label9, questionNum, cont2, goBack4);
        sceneEnter = new Scene(layout5, 400, 300);  // The size of the scene

        // Error scene where it tells user an error has occured
        StackPane layout6 = new StackPane();
        // Uses specific variables chosen from above in a scene
        layout6.getChildren().addAll(error, goBack5);
        errorScene = new Scene(layout6, 400, 300);  // The size of the scene
        
        StackPane layout7 = new StackPane();
        // Uses specific variables chosen from above in a scene
        layout7.getChildren().addAll(questionLabel, goBack6, justInCase, num, dict, openDict, value1, value2, value3, calculate);
        editQuestion = new Scene(layout7, 400, 300);    // The size of the scene

        // Scene where user can choose which question they want to use
        StackPane layout8 = new StackPane();
        layout8.getChildren().addAll(label10, questionNum2, cont3, goBack7);
        useQuestion = new Scene(layout8, 400, 300);

        // Copy Question scene where it brings user to a scene with the question they chose to use and the answer as well
        StackPane layout9 = new StackPane();
        layout9.getChildren().addAll(usedQuestion, usedQuestionA, goBack8);
        copyQuestion = new Scene(layout9, 400, 300);

        // Scene where the answer of the new values are calculated and then shown
        StackPane layout10 = new StackPane();
        layout10.getChildren().addAll(edittedQuestion, edittedAnswer, goBack9);
        printAnswer = new Scene(layout10, 400, 300);
        
        // Starts with scene 1
        window.setScene(intro); // Tells program to start with the intro scene
        window.show();  // Shows the scene

        // What happens when these buttons are clicked
        cont1.setOnAction(e -> getChoice(comboBox, window));    // Runs the getChoice method when cont1 button is pressed
        goBack1.setOnAction(e -> window.setScene(intro));       // Go Back button on math scene. Sends back to intro scene
        goBack2.setOnAction(e -> window.setScene(intro));       // Go Back button on chemistry scene. Sends back to intro scene
        goBack3.setOnAction(e -> window.setScene(intro));       // Go Back button on physics scene. Sends back to intro scene
        goBack4.setOnAction(e -> window.setScene(previousScene1));  // Go Back button on sceneEnter. Sends back to scene they came from
        goBack5.setOnAction(e -> window.setScene(previousScene2));  // Go Back button on errorScene. Sends back to sceneEnter
        goBack6.setOnAction(e -> window.setScene(previousScene2));  // Go Back button on editQuestion scene. Sends back to sceneEnter
        goBack7.setOnAction(e -> window.setScene(previousScene1));  // Go Back button on useQuestion. Sends back to scene they came from
        goBack8.setOnAction(e -> window.setScene(previousScene2));  // Go Back button on copyQuestion. Sends back to useQuestion
        goBack9.setOnAction(e -> window.setScene(previousScene3));  // Go Back button on printAnswer. Sends back to editQuestion

        // When the user presses one of the use buttons, the program opens the notepad to the subject chosen
        // The user can copy and paste from there.
        use1.setOnAction(e -> { // If use1 is pressed...
            try {
                previousScene1 = use1.getScene();   // Get the name of this scene to use in goBack buttons
                window.setScene(useQuestion);   // Goes to scene
                sendToFile(math);   // Opens Math.txt in notepad
                readThis = math;    // Stores question file
                readThisA = mathA;  // Stores answer file
            } catch (IOException e1) {
            }
        });
        use2.setOnAction(e -> { // If use2 is pressed...
            try {
                previousScene1 = use2.getScene();   // Get the name of this scene to use in goBack buttons
                window.setScene(useQuestion);   // Goes to scene
                sendToFile(chem);   // Opens Chemistry.txt in notepad
                readThis = chem;    // Stores question file
                readThisA = chemA;  // Stores answer file
            } catch (IOException e1) {
            }
        });
        use3.setOnAction(e -> { // If use3 is pressed...
            try {
                previousScene1 = use3.getScene();   // Get the name of this scene to use in goBack buttons
                window.setScene(useQuestion);   // Goes to scene
                sendToFile(physics);    // Opens Physics.txt in notepad
                readThis = physics;     // Stores question file
                readThisA = physicsA;   // Stores answer file
            } catch (IOException e1) {
            }
        });

        // When user selects on the the edit buttons, the program opens notepad once again 
        // and sends user to sceneEnter so they can choose a question to edit
        edit1.setOnAction(e -> {    // When edit1 is pressed...
            try {
                previousScene1 = edit1.getScene();  // Get the name of this scene to use in goBack buttons
                window.setScene(sceneEnter);    // Goes to sceneEnter
                sendToFile(math);   // Opens Math.txt in notepad
                readThis = math;    // Gets file name to use when running a method
            } catch (IOException e1) {
            }
        });
        edit2.setOnAction(e -> {    // When edit2 is pressed...
            try {
                previousScene1 = edit2.getScene();  // Get the name of this scene to use in goBack buttons
                window.setScene(sceneEnter);    // Goes to sceneEnter
                sendToFile(chem);   // Opens Chemistry.txt in notepad
                readThis = chem;    // Gets file name to use when running a method
            } catch (IOException e1) {
            }
        });
        edit3.setOnAction(e -> {    // When edit3 is pressed...
            try {
                previousScene1 = edit3.getScene();  // Get the name of this scene to use in goBack buttons
                window.setScene(sceneEnter);    // Goes to sceneEnter
                sendToFile(physics);    // Opens Physics.txt in notepad
                readThis = physics;     // Gets file name to use when running a method
            } catch (IOException e1) {
            }
        });

        // What happens when the user selects the continue button after they type in a number
        cont2.setOnAction(e -> {    // When cont2 is pressed...
            try {
                previousScene2 = cont2.getScene();  // Get scene to use in goBack buttons
                // Runs the userNum method and stores the integer. This is the number the user inputted in sceneEnter
                int input = userNum(questionNum, errorScene, editQuestion); 
                String inputStr = Integer.toString(input);  // Turns the integer into a string so it can be used in an if statement

                // Runs the num method and stores the integer. This is the corresponding number in the text file to int input
                int textNum = num(readThis, questionNum, errorScene, editQuestion);

                if (input == textNum) { // If the 2 numbers are equal
                    // Run the line method to get the question selected and store it as a string
                    String line = line(readThis, questionNum, errorScene, editQuestion);   
                    questionLabel.setText(line);    // Turn string into label. This is originally the empty label from earlier
                }

                else if (Character.isDigit(inputStr.charAt(0)) == false || input != textNum){   // If user input is not valid
                    window.setScene(errorScene);    // Sends the user to the errorScene
                }
            } catch (FileNotFoundException e1) {
                // Nothing in here because there is no reason a file would not be found as the files are fixed
            }
        });

        // What happens when the user selects the continue button after they type in a number when they want to use a question
        cont3.setOnAction(e -> {
            try {
                previousScene2 = cont3.getScene();  // Get scene to use in goBack buttons
                // Runs the userNum method and stores the integer. This is the number the user inputted in sceneEnter
                int input = userNum(questionNum2, errorScene, copyQuestion); 
                String inputStr = Integer.toString(input);  // Turns the integer into a string so it can be used in an if statement
    
                // Runs the num method and stores the integer. This is the corresponding number in the text file to int input
                int textNum = num(readThis, questionNum2, errorScene, copyQuestion);    // The question
                int textNum2 = num(readThisA, questionNum2, errorScene, copyQuestion);  // The asnwer
    
                if (input == textNum && input == textNum2) { // If the 2 numbers are equal
                    // Run the line method to get the question selected and store it as a string
                    String line = line(readThis, questionNum2, errorScene, copyQuestion);   // The question
                    String lineAnswer = line(readThisA, questionNum2, errorScene, copyQuestion);    // The answer
                    usedQuestion.setText("Question #" + line);    // Turn string into label. This is originally the empty question label from earlier
                    usedQuestionA.setText("Answer #" + lineAnswer);  // Turn string into label. This is originally the empty answer label from earlier
                }
    
                else if (Character.isDigit(inputStr.charAt(0)) == false || input != textNum || input != textNum2){   // If user input is not valid
                    window.setScene(errorScene);    // Sends the user to the errorScene
                }
            } catch (FileNotFoundException e1) {
                // Nothing in here because there is no reason a file would not be found as the files are fixed
            }
        });

        // What happens when openDict is pressed
        openDict.setOnAction(e -> {
            try {
                sendToFile(dictionary); // Opens a glossary
            } catch (IOException e1) {
            }
        });

        // What happens when calculate is pressed
        calculate.setOnAction( e -> {
            try {
                previousScene3 = calculate.getScene();  // Gets scene for go back button
                qAndA = answer(questionLabel, readThis, value1, value2, value3);    // Stores return value in string
                edittedQuestion.setText(qAndA[0]);  // The question label is this
                edittedAnswer.setText("Answer is: " + qAndA[1]);  // The answer label is this
                window.setScene(printAnswer);   // Goes to scene
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });
    }
    
    /*
     * Author - Benjamin Kim
     * Description - Takes the user's choice from the drop down menu and sends them to that subjects scene.
     * 
     * @param - comboBox(Drop down menu), Stage primaryStage
     * @return - No return
     */
    public void getChoice(ComboBox<String> comboBox, Stage primaryStage) {
        String subject = comboBox.getValue();   // Gets the string value of the option the user chose

        if (subject.equals("Math")) {   // If the string equals "Math"
            primaryStage.setScene(mathScene);   // Goes to math scene
        }
        else if(subject.equals("Chemistry")) {  // If the string equals "Chemistry"
            primaryStage.setScene(chemScene);   // Goes to chem scene
        }
        else if(subject.equals("Physics")) {    // if the string equals "Physics"
            primaryStage.setScene(physicsScene);    // Goes to physics scene
        }
    }

    /*
     * Author - Benjamin Kim
     * Description - Opens a notepad for the specific subject the user chose so they can view the questions
     * 
     * @param - File file
     * @return - No return
     */
    public void sendToFile(File file) throws IOException {
        String fileName = file.getName();   // Stores the name of the file in a string
        ProcessBuilder pb = new ProcessBuilder("Notepad.exe", fileName);    // Opens the file in a notepad
        pb.start(); // To make it start
    }

    /*
     * Author - Benjamin Kim
     * Description - Goes through a specified file and looks for a line that's first character is an integer. 
     *               It stores that line in a string. Same method as num, just returns different values
     * 
     * @param - File file, TextField textField, Scene errorScene, Scene editQuestion
     * @return - returns String line
     */
    public static String line(File file, TextField textField, Scene errorScene, Scene editQuestion) throws FileNotFoundException {
        Scanner scan = new Scanner(file);   // Scans the file
        int input = userNum(textField, errorScene, editQuestion);   // Gets the input of the user in sceneEnter
        int textNum = 0;    // Initializing the integer textNum which will change
        String line = "";   // Initializing the string line which will change
        while (scan.hasNextLine()) {    // If there is a next line in the file
            line = scan.nextLine();     // line equals the next line
            if (Character.isDigit(line.charAt(0)) == false) {   // If the first character of the line isn't a number
                line = scan.nextLine(); // Scan the next line and store it
            }
            else {  // If the character is a number
                textNum = questionNum(line);    // Run the questionNum method to get the number
            }
            if (input == textNum) { // If the 2 numbers match
                return line;    // return the line
            }
        }
        return line;    // Otherwise, return a blank string
    }
    /*
     * Author - Benjamin Kim
     * Description - Goes through a specified file and looks for a line that's first character is an integer. 
     *               It stores that question number in an int. Same method as line, just returns different values
     * 
     * @param - File file, TextField textField, Scene errorScene, Scene editQuestion
     * @return - returns Integer question number
     */
    public static int num(File file, TextField textField, Scene errorScene, Scene editQuestion) throws FileNotFoundException {
        Scanner scan = new Scanner(file);   // Scans the file
        int input = userNum(textField, errorScene, editQuestion);   // Gets the input of the user in sceneEnter
        int textNum = 0;    // Initializing the integer textNum which will change
        String line = "";   // Initializing the string line which will change
        while (scan.hasNextLine()) {    // If there is a next line in the file
            line = scan.nextLine();     // line equals the next line
            if (Character.isDigit(line.charAt(0)) == false) {   // If the first character of the line isn't a number
                line = scan.nextLine(); // Scan the next line and store it
            }
            else {  // If the character is a number
                textNum = questionNum(line);    // Run the questionNum method to get the number
            }
            if (input == textNum) { // If the 2 numbers match
                return textNum;    // return the number in the file
            }
        }
        return textNum;    // Otherwise, return 0
    }

    /*
     * Author - Benjamin Kim 
     * Description - Finds how many characters in the period is to signal the end of the question number
     * 
     * @param - String line
     * @return - int period position
     */
    public static int findPeriod(String line) throws FileNotFoundException {
        int lineLength = line.length(); // Gets the length of the line for the for loop
        for (int i = 0; i < lineLength; i++) {  // Runs as many time as the line length  
            if (line.charAt(i) == '.') {     // If at any point the character in the line is '.', the loop stops
                return i;   // It returns the index '.' was at
            }
        }
        return 0; // If '.' is not there, it returns 0
    }

    /* 
     * Author - Benjamin Kim
     * Description - Uses the period position to find the integer which is the question number
     * 
     * @param - String line
     * @return - integer question number
     */
    public static int questionNum(String line) throws FileNotFoundException {
        int period = findPeriod(line);  // Runs the findPeriod method from above
        String questionNumStr = line.substring(0, period);  // Gets the question number using substring as a string
        int questionNum = Integer.parseInt(questionNumStr); // Turns the string into a integer
        return questionNum; // It returns that integer
    }

    /*
     * Author - Benjamin Kim
     * Description - Gets the number the user typed in the blank. Also sends user to a error scene if they input an invalid value.
     *               Sends user to editQuestion scene if everything is fine
     * 
     * @param - TextField textField, Scene errorScene, Scene editQuestion
     * @return - integer user input number
     */
    public static int userNum(TextField textField, Scene errorScene, Scene editOrUse) {
        String input = textField.getText(); // Stores what the user inputted in a string
        int charNum = input.length();   // Gets the length of the string
        int inputNum = 0;   // Initializing the integer which will change later
        boolean number = true;  // Boolean to see if the number is valid or not
        char[] digit = new char[charNum];   // A character array for the user input. It has the same amount of values as charNum

        for(int i = 0; i < charNum; i++) {  // For loop to store values in the array
            digit[i] = input.charAt(i); 
        }
        for(int i = 0; i < charNum; i++) {  // For loop to go through the newly stored values to see if they are valid
            if (!(Character.isDigit(digit[i]))) {   // If the character is not a number
                number = false; // Number is not valid
                window.setScene(errorScene);    // Goes to the errorScene
                break;  // Exits loop
            }
        }
        if (number == true) {   // If the number stays true the entire time...
            window.setScene(editOrUse);  // Goes to scene editQuestion
            inputNum = Integer.parseInt(input); // Changes string input into an integer
        }
        return inputNum;    // returns the integer
    }
    
    /*
     * Author - Benjamin Kim
     * Description - Takes the values that the user inputted and return an answer depending on the question and file selected
     * 
     * @param - Label question, File file, TextField value1, TextField value2, TextField value3, TextField value4
     * @return - String answer
     */
    public static String[] answer(Label question, File file, TextField value1, TextField value2, TextField value3) throws FileNotFoundException {
        double answerNum = 0;   // Starting with a value of 0. It changes
        String questionStr = question.getText();    // Gets the string inside the label
        int questionNumber = questionNum(questionStr);  // Gets the question number from the string we just extracted
        String fileName = file.getName();   // Gets the string version of the file name
        
        // Getting the values from the fill in the blank
        double num1 = Double.parseDouble(value1.getText()); // Turns the user input from value 1 into a double
        double num2 = Double.parseDouble(value2.getText()); // Turns the user input from value 2 into a double
        double num3 = Double.parseDouble(value3.getText()); // Turns the user input from value 3 into a double

        double[] list = {num1, num2, num3};

        String equation = "";

        int conditions = 0;

        String newQuestion = "";

        if (fileName == "Math.txt") {   // Since the files are premade, we do not need to worry about file not found exceptions
            if (questionNumber == 1) {  // If the question is _ number, it runs _ method
                equation = (num1 + " x " + num2);
                conditions = 2;
            }
            else if (questionNumber == 2) {
                equation = "1 + 3 x 4";
                conditions = 3;
            }
            else if (questionNumber == 3) {
                equation = "2 x 3 / 4";
                conditions = 3;
            }
            else if (questionNumber == 4) {
                equation = "7 x 6 / 8";
                conditions = 3;
            }
            else if (questionNumber == 5) {
                equation = "2 ^ 5";
                conditions = 2;
            }
        }

        if (fileName == "Chemistry.txt") {
            if (questionNumber == 1) {
                equation = "2 / 5";
                conditions = 2;
            }
            else if (questionNumber == 2) {
                equation = "5 / 4";
                conditions = 2;
            } 
            else if (questionNumber == 3) {
                equation = "5 x 4 / 9";
                conditons = 3;
            }
            else if (questionNumber == 4) {
                equation = "3 x 8 / 5";
                conditions = 3;
            }
            else if (questionNumber == 5) {
                equation = "6 x 3";
                conditions = 2;
            }
        }

        if (fileName == "Physics.txt") {
            if (questionNumber == 1) {
                equation = "6 x 1";
                conditions = 2;
            }
            else if (questionNumber == 2) {
                equation = "2 / 3";
                conditions = 2;
            } 
            else if (questionNumber == 3) {
                equation = "5 / 7";
                conditions = 2;
            }
            else if (questionNumber == 4) {
                equation = "3 x 8 / 5";
                conditions = 3;
            }
            else if (questionNumber == 5) {
                equation = "9 x 7";
                conditions = 2;
            }
        }

        newQuestion = generateQuestion(questionStr, conditions, list);
        answerNum = UserSolution(num1, num2, num3, equation);

        String answer = Double.toString(answerNum); // Turns the final answer to a string to put in a label

        String[] qAndA = {newQuestion, answer};
        return qAndA;  // Returns the string
    }
    
    
    @Override
    public void handle(ActionEvent arg0) {  // Needed for javafx
    }

    public static String generateQuestion(String question, int num, double[] value){
        Scanner sc = new Scanner(System.in);
        int count = -1;
        Integer[] orig = new Integer[num];

        for(int i = 3; i< question.length(); i++){
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
        try{
            for(int i = 0; i< num; i++){
                if((orig[i] != null) && (value[i] != 0)){
                    sb.setCharAt(orig[i], (char)(value[i] + 48));
                    question = sb.toString();
                }
            }
        }
        catch(NullPointerException npe){
            return("An error occurred.");
        }
        
        return(question);
    }

    public static double UserSolution(double value1, double value2, double value3, String SelectedQuestion){
        int counter = 0;
        Scanner sc = new Scanner(SelectedQuestion);
        String line = new String();
        String[] Operation = new String[20];
        double[] num = new double[10];

        while(sc.hasNext()){

            line = sc.next();
            Operation[counter] = line;
            
            counter ++;
        }
        // System.out.println(value1);
        // System.out.println(value2);
        // System.out.println(value3);
        // int len = Operation.length;
        // System.out.println(len);   
        num[0] = value1;
        num[2] = value2;
        num[4] = value3;
            

        return(Calculation(Operation, num));
           
        
    }

    public static double Calculation(String[] Operation, double[] num){
        double counter1 = 0.0;
        double answer = 0.0;
        // SETTING UP BEDMAS FOR *

        if(Operation[3] == null){
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
        
        else if(Operation[5] == null){
            
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
        }

        else if(Operation[7] == null){
            if(Operation[0].equals("(") && Operation[4].equals(")")){
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
        else if(Operation[10] == null){
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
        else if(Operation[12] == null){
            if(Operation[5].equals("(") && Operation[11].equals(")")){
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
            
        
        
        
        return answer;
    } 
}
