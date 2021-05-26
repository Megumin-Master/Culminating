import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Culminating extends Application implements EventHandler<ActionEvent>{
    
    Stage window;
    Scene scene1, scene2, scene3, scene4;
    Button button, goBack1, goBack2, goBack3, edit1, edit2, edit3, use1, use2, use3;
    Button[] goBack, edit, use;
    Label[] label8;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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

        /*
        Label label801 = new Label("Would you like to import or export a question?");
        label801.setTranslateY(20);
        Label label802 = new Label("Would you like to import or export a question?");
        label802.setTranslateY(20);
        Label label803 = new Label("Would you like to import or export a question?");
        label803.setTranslateY(20);
        */
        // Label that has a question
        label8 = new Label[3];
        for (int i = 0; i < 3; i++) {
            label8[i] = new Label("Would you like to import or export a question?");
            label8[i].setTranslateY(20);
        }

        // A continue button for scene 1
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
        /*
        goBack = new Button[3];
        for (int i = 0; i < 3; i++) {
            goBack[i] = new Button("Go Back");
            goBack[i].setTranslateX(-160);
            goBack[i].setTranslateY(130);
        }
        */


        edit = new Button[3];
        for (int i = 0; i < 3; i++) {
            edit[i] = new Button("Import");
            edit[i].setTranslateX(-40);
            edit[i].setTranslateY(50);
        }
        /*
        use = new Button[3];
        for (int i = 0; i < 3; i++) {
            use[i] = new Button("Export");
            use[i].setTranslateX(40);
            use[i].setTranslateY(50);
        }
        */
        
        use1 = new Button("Export");
        use1.setTranslateX(40);
        use1.setTranslateY(50);
        use2 = new Button("Export");
        use2.setTranslateX(40);
        use2.setTranslateY(50);
        use3 = new Button("Export");
        use3.setTranslateX(40);
        use3.setTranslateY(50);

        // Drop down menu
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Math", "Chemistry", "Physics");
        comboBox.setPromptText("Subjects");
        comboBox.setTranslateY(10);

        // The introduction scene
        StackPane layout1 = new StackPane();
        layout1.getChildren().addAll(label1, label2, label3, label4, button, comboBox);
        scene1 = new Scene(layout1, 300, 300);

        // Math Scene
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(label5, goBack1, label8[0], use1, edit[0]);
        scene2 = new Scene(layout2, 400, 300);

        // Chemistry Scene
        StackPane layout3 = new StackPane();
        layout3.getChildren().addAll(label6, goBack2, label8[1], use2, edit[1]);
        scene3 = new Scene(layout3, 400, 300);

        // Physics Scene
        StackPane layout4 = new StackPane();
        layout4.getChildren().addAll(label7, goBack3, label8[2], use3, edit[2]);
        scene4 = new Scene(layout4, 400, 300);

        // Starts with scene 1
        window.setScene(scene1);
        window.show();

        // What happens when these buttons are clicked
        button.setOnAction(e -> getChoice(comboBox, window));
        goBack1.setOnAction(e -> window.setScene(scene1));
        goBack2.setOnAction(e -> window.setScene(scene1));
        goBack3.setOnAction(e -> window.setScene(scene1));
        use1.setOnAction(e -> sendToFile());
    }

    public void getChoice(ComboBox<String> comboBox, Stage primaryStage) {
        String subject = comboBox.getValue();
        if (subject.equals("Math")) {
            primaryStage.setScene(scene2);
            System.out.println("Welcome to the math files");
        }
        else if(subject.equals("Chemistry")) {
            primaryStage.setScene(scene3);
            System.out.println("Welcome to the chemistry files");
        }
        else if(subject.equals("Physics")) {
            primaryStage.setScene(scene4);
            System.out.println("Welcome to the physics files");
        }
        else {
            System.out.println("Select a valid subject");
        }
    }

    public void sendToFile() {
        
    }

    @Override
    public void handle(ActionEvent arg0) {
    }
}