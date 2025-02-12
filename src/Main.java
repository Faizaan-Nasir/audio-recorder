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
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import java.io.File;


public class Main extends Application {
    private Stage primaryStage;
    private HBox directoryContainer; 

    // DASHBOARD PAGE
    public void Dashboard() {
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
        backButton.setOnAction(e -> start(primaryStage));

        HBox dashbuttonContainer = new HBox(backButton);
        dashbuttonContainer.setLayoutX(780);
        dashbuttonContainer.setLayoutY(20);
        dashbuttonContainer.setMinWidth(100);

        Label directoryLabel = new Label("Selected Folder: None");
        directoryLabel.setId("directorylabel");
        Button chooseDirButton = new Button("Choose Dir");
        chooseDirButton.setMinWidth(120);
        chooseDirButton.setMaxWidth(120);
        chooseDirButton.setMaxHeight(20);
        chooseDirButton.setId("choosedirbutton");
        DirectoryChooser directoryChooser = new DirectoryChooser();

        directoryContainer = new HBox(20, chooseDirButton, directoryLabel);
        directoryContainer.setLayoutX(30);
        directoryContainer.setLayoutY(510); 
        directoryContainer.setMinWidth(900);
        directoryContainer.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        HBox.setHgrow(directoryLabel, Priority.ALWAYS);
        directoryLabel.setMaxWidth(Double.MAX_VALUE);
        
        FlowPane recordingButtonsContainer=new FlowPane();
        recordingButtonsContainer.setPrefWidth(600);
        recordingButtonsContainer.setVgap(20);
        recordingButtonsContainer.setHgap(20);

        chooseDirButton.setOnAction(e -> {
            directoryChooser.setTitle("Select a Folder");
            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            
            if (selectedDirectory != null) {
                directoryLabel.setText("Selected Folder: " + selectedDirectory.getAbsolutePath());
            }
            else{
                directoryLabel.setText("Selected Folder: None");
            }

            recordingButtonsContainer.getChildren().removeAll();
            File[] allFiles=selectedDirectory.listFiles();
            Button[] allButtons=new Button[allFiles.length];
            for (int i=0;i<allFiles.length;i++){
                if (allFiles[i].getName().endsWith(".wav")){
                    allButtons[i]=new Button(allFiles[i].getName());
                    allButtons[i].setMinWidth(130);
                    allButtons[i].setMaxWidth(130);
                    allButtons[i].setMaxHeight(25);
                    allButtons[i].setId("recordingbutton");
                    recordingButtonsContainer.getChildren().add(allButtons[i]);
                }
            }
        });

        recordingButtonsContainer.setLayoutX(30);
        recordingButtonsContainer.setLayoutY(70);
        recordingButtonsContainer.setMinWidth(600);
        dashboardRoot.getChildren().add(recordingButtonsContainer);
        dashboardRoot.getChildren().addAll(dasheading, dashbuttonContainer, directoryContainer);
        Scene dashboardScene = new Scene(dashboardRoot, 900, 600);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(dashboardScene);
        dashboardScene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
    }


    // RECORDING PAGE
    public void Record() {

        AudioFormat format = new AudioFormat(44100, 16, 2, true, true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        File outputFile = new File("file.wav");
        TargetDataLine targetLine;
        Button stoprecord = new Button("stop rec...");
        stoprecord.setMinWidth(150);
        stoprecord.setMaxWidth(150);
        stoprecord.setMaxHeight(40);
        try{
            targetLine = (TargetDataLine) AudioSystem.getLine(info);
            targetLine.open(format);
            targetLine.start();
            Thread recording=new Thread(()->{
                try {
                    AudioSystem.write(new AudioInputStream(targetLine), AudioFileFormat.Type.WAVE, outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            recording.start();
            stoprecord.setOnAction(e -> {
                targetLine.stop();
                targetLine.close();
                recording.interrupt();
                Dashboard();
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }

        Pane recordRoot = new Pane();
        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Jersey10-Regular.ttf"), 70);
        Image gifImage = new Image(getClass().getResource("/resources/waves.gif").toExternalForm());
        ImageView gifView = new ImageView(gifImage);
        gifView.setFitWidth(450);
        gifView.setFitHeight(450);
        gifView.setPreserveRatio(true);
        gifView.setLayoutX(225);
        gifView.setLayoutY(32);

        Button backButton = new Button("Home");
        backButton.setMinWidth(90);
        backButton.setMaxWidth(90);
        backButton.setMaxHeight(10);
        backButton.setId("backbutton");
        backButton.setOnAction(e -> start(primaryStage));

        HBox backbuttonContainer = new HBox(backButton);
        backbuttonContainer.setLayoutX(780);
        backbuttonContainer.setLayoutY(20);
        backbuttonContainer.setMinWidth(100);

        HBox stoprecbuttonContainer = new HBox(stoprecord);
        stoprecbuttonContainer.setLayoutX(350);
        stoprecbuttonContainer.setLayoutY(400);
        stoprecbuttonContainer.setMinWidth(150);

        recordRoot.getChildren().addAll(gifView, backbuttonContainer, stoprecbuttonContainer);
        Scene recordScene = new Scene(recordRoot, 900, 600);
        primaryStage.setTitle("recording...");
        primaryStage.setScene(recordScene);
        recordScene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
    }


    // Landing (HOME)
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Jersey10-Regular.ttf"), 70);
        Pane homeroot = new Pane();

        Label heading = new Label("Voice Recorder");
        heading.setMinWidth(900);
        heading.setAlignment(Pos.CENTER);
        heading.setLayoutX(0);
        heading.setLayoutY(200);
        heading.setId("title");

        Image mic_icon = new Image(getClass().getResourceAsStream("/resources/microphone.png"));
        ImageView micView = new ImageView(mic_icon);
        micView.setFitWidth(20);
        micView.setFitHeight(25);

        Button record = new Button("record ", micView);
        record.setContentDisplay(ContentDisplay.RIGHT);
        record.setAlignment(Pos.CENTER);
        record.setMinWidth(150);
        record.setMaxHeight(40);
        record.setOnAction(e -> Record());

        Button dashboard = new Button("dashboard");
        dashboard.setMinWidth(170);
        dashboard.setMaxWidth(170);
        dashboard.setMaxHeight(40);
        dashboard.setOnAction(e -> Dashboard());

        HBox buttonContainer = new HBox(20, record, dashboard);
        buttonContainer.setLayoutY(300);
        buttonContainer.setMinWidth(900);
        buttonContainer.setAlignment(Pos.CENTER);

        homeroot.getChildren().addAll(heading, buttonContainer);
        Scene scene = new Scene(homeroot, 900, 600);
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