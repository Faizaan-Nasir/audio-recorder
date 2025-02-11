import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
// import javafx.scene.effect.DropShadow;
// import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
// import java.lang.Math;
import javafx.scene.text.Font;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) {
        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Jersey10-Regular.ttf"), 70);
        Pane homeroot=new Pane();

        Label heading=new Label("Voice Recorder");
        heading.setMinWidth(900);
        heading.setAlignment(Pos.CENTER);
        heading.setLayoutX(0);
        heading.setLayoutY(200);
        heading.setId("title");

        Image mic_icon = new Image(getClass().getResourceAsStream("/resources/microphone.png"));
        ImageView micView = new ImageView(mic_icon);
        micView.setFitWidth(20);  
        micView.setFitHeight(25); 

        Button record=new Button("record ",micView);
        record.setContentDisplay(ContentDisplay.RIGHT);
        record.setAlignment(Pos.CENTER);
        record.setMinWidth(150);
        record.setMaxHeight(40);
                
        Button dashboard=new Button("dashboard");
        dashboard.setMinWidth(170);
        dashboard.setMaxWidth(170);
        dashboard.setMaxHeight(40);

        HBox buttonContainer=new HBox(20,record,dashboard);
        buttonContainer.setLayoutY(300);
        buttonContainer.setMinWidth(900);
        buttonContainer.setAlignment(Pos.CENTER);

        homeroot.getChildren().addAll(heading,buttonContainer);
        Scene scene = new Scene(homeroot, 900, 600);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        primaryStage.setTitle("Audio Recorder");
        primaryStage.setScene(scene);
        primaryStage.setMaxWidth(900);
        primaryStage.setMaxHeight(600);
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.show();

        dashboard.setOnAction(e -> {
            Dashboard newwin=new Dashboard(primaryStage);
            newwin.showDashboard();
            primaryStage.hide();
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}

class Dashboard{
    Stage primaryStage;
    public void showDashboard() {
        Stage dashboardStage = new Stage();
        Pane dashboardRoot = new Pane();
        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Jersey10-Regular.ttf"), 70);
    
        Label dasheading = new Label("Dashboard");
        dasheading.setMinWidth(900);
        dasheading.setLayoutX(30);
        dasheading.setLayoutY(10);
        dasheading.setId("dashboardtitle");
    
        Button backButton = new Button("Home");
        backButton.setMinWidth(90);
        backButton.setMaxWidth(90);
        backButton.setMaxHeight(10);
        backButton.setId("backbutton");
        backButton.setOnAction(e -> {
            primaryStage.show();
            dashboardStage.hide();
        });
    
        HBox dashbuttonContainer = new HBox(backButton);
        dashbuttonContainer.setLayoutX(780);
        dashbuttonContainer.setLayoutY(20);
        dashbuttonContainer.setMinWidth(100);
    
        dashboardRoot.getChildren().addAll(dasheading, dashbuttonContainer);
        Scene dashboardScene = new Scene(dashboardRoot, 900, 600);
        dashboardStage.setTitle("Dashboard");
        dashboardStage.setScene(dashboardScene);
        dashboardScene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        dashboardStage.show();
    }
    
    Dashboard(Stage primaryStage){
        this.primaryStage=primaryStage;
    }
}