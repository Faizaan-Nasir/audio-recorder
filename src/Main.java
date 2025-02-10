import javafx.application.Application;
// import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import java.lang.Math;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) {
        VBox root=new VBox(0);
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        primaryStage.setTitle("Audio Recorder");
        primaryStage.setScene(scene);
        primaryStage.setMaxWidth(1000);
        primaryStage.setMaxHeight(600);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}