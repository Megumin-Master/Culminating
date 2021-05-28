import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Culminating extends Application implements EventHandler<ActionEvent>{
    
    Stage window;
    Scene scene1, scene2, scene3, scene4;
    Button button, goBack1, goBack2, goBack3, edit1, edit2, edit3, use1, use2, use3;
    Label[] label8;
    File math = new File("Math.txt");
    File chem = new File("Chemistry.txt");
    File physics = new File("Physics.txt");

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

        // Drop down menu
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Math", "Chemistry", "Physics");
        comboBox.setPromptText("Subjects");
        comboBox.setTranslateY(10);

        // A continue button for scene 1 after the user selects from the drop down menu
        button = new Button("Continue");
        button.setTranslateY(50);

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

        // The introduction scene
        StackPane layout1 = new StackPane();
        layout1.getChildren().addAll(label1, label2, label3, label4, button, comboBox);
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

        // Starts with scene 1
        window.setScene(scene1);
        window.show();

        // What happens when these buttons are clicked
        button.setOnAction(e -> getChoice(comboBox, window));
        goBack1.setOnAction(e -> window.setScene(scene1));
        goBack2.setOnAction(e -> window.setScene(scene1));
        goBack3.setOnAction(e -> window.setScene(scene1));

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
     * Description - Opens a notepad for the specific subject the user chose
     * 
     * @param - File file
     * @return - No return
     */
    public void sendToFile(File file) throws IOException {
        String fileName = file.getName();
        ProcessBuilder pb = new ProcessBuilder("Notepad.exe", fileName);
        pb.start();
    }

    public void makeQuestions(File file) throws IOException {
        
    }

    @Override
    public void handle(ActionEvent arg0) {
    }
}