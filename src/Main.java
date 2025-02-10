import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
// import java.lang.Math;
import javafx.scene.text.Font;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) {
        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Jersey10-Regular.ttf"), 70);
        Pane root=new Pane();

        Label heading=new Label("Voice Recorder");
        heading.setMinWidth(900);
        heading.setAlignment(Pos.CENTER);
        heading.setLayoutX(0);
        heading.setLayoutY(200);
        heading.setId("title");

        Button record=new Button("record");
        record.setMinWidth(170);
        record.setMaxHeight(45);

        Button dashboard=new Button("dashboard");
        dashboard.setMinWidth(170);
        dashboard.setMaxWidth(170);
        dashboard.setMaxHeight(45);

        HBox buttonContainer=new HBox(20,record,dashboard);
        buttonContainer.setLayoutY(300);
        buttonContainer.setMinWidth(900);
        buttonContainer.setAlignment(Pos.CENTER);

        root.getChildren().addAll(heading,buttonContainer);
        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        primaryStage.setTitle("Audio Recorder");
        primaryStage.setScene(scene);
        primaryStage.setMaxWidth(900);
        primaryStage.setMaxHeight(600);
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}