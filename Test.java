/*
 * Name: Benjamin Kim, David Han, Yuyang, Jeffrey
 * Date: June 4th, 2021
 * Teacher: Mr. Ho
 * Description: Culminating Assignment - Question Bank
 */
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

public class Test extends Application implements EventHandler<ActionEvent>{
    
    static Stage window;
    Scene scene1, scene2, scene201, scene3, scene301, scene4, sceneEnter, errorScene, previousScene1, previousScene2, editQuestion;
    Button cont1, cont2, cont3, cont4, goBack1, goBack2, goBack3, goBack4, goBack5, edit1, edit2, edit3, use1, use2, use3;
    Label error, label9, questionLabel;
    Label[] label8;
    File math = new File("Math.txt");
    File chem = new File("Chemistry.txt");
    File physics = new File("Physics.txt");
    File readThis;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        window.setTitle("Question Bank");

        // Objects and their positions
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

        // Label that has a question
        label8 = new Label[3];
        for (int i = 0; i < 3; i++) {
            label8[i] = new Label("Would you like to make or use a question?");
            label8[i].setTranslateY(20);
        }

        label9 = new Label("Which number question would you like to edit?");

        error = new Label("The value you inputted is not valid. Go back and try again");

        questionLabel = new Label();    // Blank label as it can change after each run

        // Drop down menu
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Math", "Chemistry", "Physics");
        comboBox.setPromptText("Subjects");
        comboBox.setTranslateY(10);

        // A continue button for scene 1 after the user selects from the drop down menu
        cont1 = new Button("Continue");
        cont1.setTranslateY(50);

        // Go back buttons for the other scenes
        goBack1 = new Button("Go Back");
        goBack1.setTranslateX(-160);
        goBack1.setTranslateY(130);
        goBack2 = new Button("Go Back");
        goBack2.setTranslateX(-160);
        goBack2.setTranslateY(130);
        goBack3 = new Button("Go Back");
        goBack3.setTranslateX(-160);
        goBack3.setTranslateY(130);
        goBack4 = new Button("Go Back");
        goBack4.setTranslateX(-160);
        goBack4.setTranslateY(130);
        goBack5 = new Button("Go Back");
        goBack5.setTranslateX(-160);
        goBack5.setTranslateY(130);

        // Buttons that send users to make their own questions
        edit1 = new Button("Make Math Questions!");
        edit1.setTranslateX(-75);
        edit1.setTranslateY(50);
        edit2 = new Button("Make Chemistry Questions!");
        edit2.setTranslateX(-80);
        edit2.setTranslateY(50);
        edit3 = new Button("Make Physics Questions!");
        edit3.setTranslateX(-80);
        edit3.setTranslateY(50);

        // Buttons that sends the user to copy and paste existing questions
        use1 = new Button("Use Math Questions!");
        use1.setTranslateX(80);
        use1.setTranslateY(50);
        use2 = new Button("Use Chemistry Questions!");
        use2.setTranslateX(80);
        use2.setTranslateY(50);
        use3 = new Button("Use Physics Questions!");
        use3.setTranslateX(75);
        use3.setTranslateY(50);

        // Text fields
        TextField questionNum1 = new TextField();
        questionNum1.setMaxWidth(60);
        questionNum1.setTranslateY(30);
        TextField questionNum2 = new TextField();
        questionNum2.setMaxWidth(60);
        questionNum2.setTranslateY(30);
        TextField questionNum3 = new TextField();
        questionNum3.setMaxWidth(60);
        questionNum3.setTranslateY(30);

        // Continue buttons after user inputs something
        cont2 = new Button("Continue");
        cont2.setTranslateY(60);
        cont3 = new Button("Continue");
        cont3.setTranslateY(60);
        cont4 = new Button("Continue");
        cont4.setTranslateY(60);
        
        // The introduction scene
        StackPane layout1 = new StackPane();
        layout1.getChildren().addAll(label1, label2, label3, label4, cont1, comboBox);
        scene1 = new Scene(layout1, 300, 300);

        // Math Scene
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(label5, goBack1, label8[0], use1, edit1);
        scene2 = new Scene(layout2, 400, 300);

        // Chemistry Scene
        StackPane layout3 = new StackPane();
        layout3.getChildren().addAll(label6, goBack2, label8[1], use2, edit2);
        scene3 = new Scene(layout3, 400, 300);

        // Physics Scene
        StackPane layout4 = new StackPane();
        layout4.getChildren().addAll(label7, goBack3, label8[2], use3, edit3);
        scene4 = new Scene(layout4, 400, 300);

        // Scene where user inputs question number
        StackPane layout5 = new StackPane();
        layout5.getChildren().addAll(label9, questionNum1, cont2, goBack4);
        sceneEnter = new Scene(layout5, 400, 300);

        // Error scene where it tells user an error has occured
        StackPane layout6 = new StackPane();
        layout6.getChildren().addAll(error, goBack5);
        errorScene = new Scene(layout6, 400, 300);

        // Scene that prints the question in the GUI
        
        StackPane layout7 = new StackPane();
        layout7.getChildren().addAll(questionLabel);
        editQuestion = new Scene(layout7, 400, 300);
        
        // Starts with scene 1
        window.setScene(scene1);
        window.show();

        // What happens when these buttons are clicked
        cont1.setOnAction(e -> getChoice(comboBox, window));
        goBack1.setOnAction(e -> window.setScene(scene1));
        goBack2.setOnAction(e -> window.setScene(scene1));
        goBack3.setOnAction(e -> window.setScene(scene1));
        goBack4.setOnAction(e -> window.setScene(previousScene1));

        // When the user presses one of the use buttons, the program opens the notepad and the user can copy and paste from there.
        use1.setOnAction(e -> {
            try {
                sendToFile(math);
            } catch (IOException e1) {
            }
        });
        use2.setOnAction(e -> {
            try {
                sendToFile(chem);
            } catch (IOException e1) {
            }
        });
        use3.setOnAction(e -> {
            try {
                sendToFile(physics);
            } catch (IOException e1) {
            }
        });

        // When user selects Make Questions
        edit1.setOnAction(e -> {
            try {
                previousScene1 = edit1.getScene();
                window.setScene(sceneEnter);
                sendToFile(math);
                readThis = math;
            } catch (IOException e1) {
            }
        });
        edit2.setOnAction(e -> {
            try {
                previousScene1 = edit2.getScene();
                window.setScene(sceneEnter);
                sendToFile(chem);
                readThis = chem;
            } catch (IOException e1) {
            }
        });
        edit3.setOnAction(e -> {
            try {
                previousScene1 = edit3.getScene();
                window.setScene(sceneEnter);
                sendToFile(physics);
                readThis = physics;
            } catch (IOException e1) {
            }
        });

        // What happens when the user selects the continue button after they type in a number
        cont2.setOnAction(e -> {
            try {
                previousScene2 = cont2.getScene();
                int input = userNum(questionNum1, errorScene, editQuestion);
                String inputStr = Integer.toString(input);
                int textNum = num(readThis, questionNum1, errorScene, editQuestion);
                if (input == textNum) {
                    String line = line(readThis, questionNum1, errorScene, editQuestion);
                    questionLabel.setText(line);
                }
                else if (Character.isDigit(inputStr.charAt(0)) == false || input != textNum){
                    window.setScene(errorScene);
                }
            } catch (FileNotFoundException e1) {
            }
        });
        goBack5.setOnAction(e -> window.setScene(previousScene2));
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
            primaryStage.setScene(scene2);
        }
        else if(subject.equals("Chemistry")) {
            primaryStage.setScene(scene3);
        }
        else if(subject.equals("Physics")) {
            primaryStage.setScene(scene4);
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
     *               It stores that line in a string.
     * 
     * @param - File file
     * @return - String line
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
     * Description - Finds how many characters the period is to signal the end of the question number. Returns the position of the period
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
     * 
     * @param - TextField textField, Scene errorScene
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