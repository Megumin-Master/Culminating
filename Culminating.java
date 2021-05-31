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
    Scene intro, mathScene, chemScene, physicsScene, sceneEnter, errorScene, previousScene1, previousScene2, editQuestion;
    // All the buttons used in this program
    Button cont1, cont2, goBack1, goBack2, goBack3, goBack4, goBack5, goBack6, edit1, edit2, edit3, use1, use2, use3;
    Label[] label8; // A label array because it is going to contain the same text in different scenes
    // The 3 text files containing the questions
    File math = new File("Math.txt");
    File chem = new File("Chemistry.txt");
    File physics = new File("Physics.txt");
    File readThis;  // Blank file as it is going to be one of the 3 above depending on what scene came before

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
        Label questionLabel = new Label();    // Blank label as it can change after each run

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

        // Buttons that send users to make their own questions
        edit1 = new Button("Make Math Questions!"); // The edit button for math questions
        edit1.setTranslateX(-75);
        edit1.setTranslateY(50);
        edit2 = new Button("Make Chemistry Questions!");    // The edit button for chemistry questions
        edit2.setTranslateX(-80);
        edit2.setTranslateY(50);
        edit3 = new Button("Make Physics Questions!");  // The edit button for physics questions
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

        // Making a text field which is a text box. It is essentially fill in the blank. This one is for the user to choose a question
        TextField questionNum = new TextField();    
        questionNum.setMaxWidth(60);    // Sets the length of the physical box. It is 60 pixels wide here
        questionNum.setTranslateY(30);

        // Continue button for after the user inputs something
        cont2 = new Button("Continue");
        cont2.setTranslateY(60);
        
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

        // Scene where user inputs question number
        StackPane layout5 = new StackPane();
        // Uses specific variables chosen from above in a scene
        layout5.getChildren().addAll(label9, questionNum, cont2, goBack4);
        sceneEnter = new Scene(layout5, 400, 300);  // The size of the scene

        // Error scene where it tells user an error has occured
        StackPane layout6 = new StackPane();
        // Uses specific variables chosen from above in a scene
        layout6.getChildren().addAll(error, goBack5);
        errorScene = new Scene(layout6, 400, 300);  // The size of the scene

        // Scene that prints the question in the GUI
        
        StackPane layout7 = new StackPane();
        // Uses specific variables chosen from above in a scene
        layout7.getChildren().addAll(questionLabel, goBack6);
        editQuestion = new Scene(layout7, 400, 300);    // The size of the scene
        
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

        // When the user presses one of the use buttons, the program opens the notepad to the subject chosen
        // The user can copy and paste from there.
        use1.setOnAction(e -> { // If use1 is pressed...
            try {
                sendToFile(math);   // Opens Math.txt in notepad
            } catch (IOException e1) {
            }
        });
        use2.setOnAction(e -> { // If use2 is pressed...
            try {
                sendToFile(chem);   // Opens Chemistry.txt in notepad
            } catch (IOException e1) {
            }
        });
        use3.setOnAction(e -> { // If use3 is pressed...
            try {
                sendToFile(physics);    // Opens Physics.txt in notepad
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
        cont2.setOnAction(e -> {    // When cont@ is pressed...
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
    }
    
    /*
     * Author - Benjamin Kim
     * Description - Takes the user's choice from the drop down menu and sends them to that subjects scene.
     * 
     * @param - comboBox(Drop down menu), Stage primaryStage
     * @return - No return
     */
    public void getChoice(ComboBox<String> comboBox, Stage primaryStage) {
        String subject = comboBox.getValue();

        if (subject.equals("Math")) {
            primaryStage.setScene(mathScene);
        }
        else if(subject.equals("Chemistry")) {
            primaryStage.setScene(chemScene);
        }
        else if(subject.equals("Physics")) {
            primaryStage.setScene(physicsScene);
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
        String fileName = file.getName();
        ProcessBuilder pb = new ProcessBuilder("Notepad.exe", fileName);
        pb.start();
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
        Scanner scan = new Scanner(file);
        int input = userNum(textField, errorScene, editQuestion);
        int textNum = 0;
        String line = "";
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            if (Character.isDigit(line.charAt(0)) == false) {
                line = scan.nextLine();
            }
            else {
                textNum = questionNum(line);
            }
            if (input == textNum) {
                return line;
            }
        }
        return line;
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
        Scanner scan = new Scanner(file);
        int input = userNum(textField, errorScene, editQuestion);
        int textNum = 0;
        String line = "";
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            if (Character.isDigit(line.charAt(0)) == false) {
                line = scan.nextLine();
            }
            else {
                textNum = questionNum(line);
            }
            if (input == textNum) {
                return textNum;
            }
        }
        return textNum;
    }

    /*
     * Author - Benjamin Kim 
     * Description - Finds how many characters in the period is to signal the end of the question number
     * 
     * @param - String line
     * @return - int period position
     */
    public static int findPeriod(String line) throws FileNotFoundException {
        char period = '.';
        int lineLength = line.length();
        for (int i = 0; i < lineLength; i++) {
            if (Character.compare(line.charAt(i), period) == 0) {
                return i;
            }
        }
        return 0;
    }

    /* 
     * Author - Benjamin Kim
     * Description - Uses the period position to find the integer which is the question number
     * 
     * @param - String line
     * @return - integer question number
     */
    public static int questionNum(String line) throws FileNotFoundException {
        int period = findPeriod(line);
        String questionNumStr = line.substring(0, period);
        int questionNum = Integer.parseInt(questionNumStr);
        return questionNum;
    }

    /*
     * Author - Benjamin Kim
     * Description - Gets the number the user typed in the blank. Also sends user to a error scene if they input an invalid value.
     *               Sends user to editQuestion scene if everything is fine
     * 
     * @param - TextField textField, Scene errorScene, Scene editQuestion
     * @return - integer user input number
     */
    public static int userNum(TextField textField, Scene errorScene, Scene editQuestion) {
        String input = textField.getText();
        int charNum = input.length();
        int inputNum = 0;
        boolean number = true;
        char[] digit = new char[charNum];

        for(int i = 0; i < charNum; i++) {
            digit[i] = input.charAt(i);
        }
        for(int i = 0; i < charNum; i++) {
            if (!(Character.isDigit(digit[i]))) {
                number = false;
                window.setScene(errorScene);
                break;
            }
        }
        if (number == true) {
            window.setScene(editQuestion);
            inputNum = Integer.parseInt(input);
        }
        return inputNum;
    }

    @Override
    public void handle(ActionEvent arg0) {
    }
}