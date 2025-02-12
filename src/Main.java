import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import java.io.File;

public class Main extends Application {
    public static final String FONT_PATH = "/resources/fonts/Jersey10-Regular.ttf";
    public static final String MICROPHONE_ICON_PATH = "/resources/microphone.png";
    public static final String STYLES_PATH = "/styles/styles.css";
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 600;
    private Stage primaryStage;
    private HBox directoryContainer;
    private String currentDirectory = "c:/users/downloads";

    public void changeDirectory() {
        Button chooseDirButton = new Button("Choose Dir");
        chooseDirButton.setMinWidth(120);
        chooseDirButton.setMaxWidth(120);
        chooseDirButton.setMaxHeight(20);
        chooseDirButton.setId("choosedirbutton");

        Label directoryLabel = new Label(currentDirectory);
        directoryLabel.setId("directorylabel");

        // Add directory chooser functionality
        chooseDirButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);

            if (selectedDirectory != null) {
                currentDirectory = selectedDirectory.getAbsolutePath();
                directoryLabel.setText(currentDirectory);
            }
        });

        directoryContainer = new HBox(20, chooseDirButton, directoryLabel);
        directoryContainer.setLayoutX(30);
        directoryContainer.setLayoutY(510);
        directoryContainer.setMinWidth(900);
        directoryContainer.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        HBox.setHgrow(directoryLabel, Priority.ALWAYS);
        directoryLabel.setMaxWidth(Double.MAX_VALUE);
    }

    public void showDashboard() {
        Pane dashboardRoot = new Pane();
        Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 70);

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
        backButton.setOnAction(e -> showHome());

        HBox dashbuttonContainer = new HBox(backButton);
        dashbuttonContainer.setLayoutX(780);
        dashbuttonContainer.setLayoutY(20);
        dashbuttonContainer.setMinWidth(100);

        changeDirectory();

        Button recordingButton = new Button("recording 1");
        recordingButton.setMinWidth(130);
        recordingButton.setMaxWidth(130);
        recordingButton.setMaxHeight(25);
        recordingButton.setId("recordingbutton");

        HBox recordingButtonsContainer = new HBox(recordingButton);
        recordingButtonsContainer.setLayoutX(30);
        recordingButtonsContainer.setLayoutY(70);
        recordingButtonsContainer.setMinWidth(600);

        dashboardRoot.getChildren().addAll(dasheading, dashbuttonContainer, directoryContainer,
                recordingButtonsContainer);
        Scene dashboardScene = new Scene(dashboardRoot, 900, 600);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(dashboardScene);
        dashboardScene.getStylesheets().add(getClass().getResource(STYLES_PATH).toExternalForm());
    }

    public void startRecording() {
        Pane recordRoot = new Pane();
        Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 70);
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
        backButton.setOnAction(e -> showHome());

        HBox backbuttonContainer = new HBox(backButton);
        backbuttonContainer.setLayoutX(780);
        backbuttonContainer.setLayoutY(20);
        backbuttonContainer.setMinWidth(100);

        Button stopRecordButton = new Button("Stop Recording");
        stopRecordButton.setMinWidth(150);
        stopRecordButton.setMaxWidth(150);
        stopRecordButton.setMaxHeight(40);
        stopRecordButton.setOnAction(e -> showDashboard());

        HBox stoprecbuttonContainer = new HBox(stopRecordButton);
        stoprecbuttonContainer.setLayoutX(350);
        stoprecbuttonContainer.setLayoutY(400);
        stoprecbuttonContainer.setMinWidth(150);

        recordRoot.getChildren().addAll(gifView, backbuttonContainer, stoprecbuttonContainer);
        Scene recordScene = new Scene(recordRoot, 900, 600);
        primaryStage.setTitle("recording...");
        primaryStage.setScene(recordScene);
        recordScene.getStylesheets().add(getClass().getResource(STYLES_PATH).toExternalForm());
    }

    private void showHome() {
        setupHomeScene();
    }

    private void setupHomeScene() {
        Pane homeRoot = new Pane();
        Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 70);

        Label heading = new Label("Voice Recorder");
        heading.setMinWidth(WINDOW_WIDTH);
        heading.setAlignment(Pos.CENTER);
        heading.setLayoutX(0);
        heading.setLayoutY(200);
        heading.setId("title");

        Image micIcon = new Image(getClass().getResourceAsStream(MICROPHONE_ICON_PATH));
        ImageView micView = new ImageView(micIcon);
        micView.setFitWidth(20);
        micView.setFitHeight(25);

        Button recordButton = new Button("Record", micView);
        recordButton.setContentDisplay(ContentDisplay.RIGHT);
        recordButton.setAlignment(Pos.CENTER);
        recordButton.setMinWidth(150);
        recordButton.setMaxHeight(40);
        recordButton.setOnAction(e -> startRecording());

        Button dashboardButton = new Button("Dashboard");
        dashboardButton.setMinWidth(170);
        dashboardButton.setMaxWidth(170);
        dashboardButton.setMaxHeight(40);
        dashboardButton.setOnAction(e -> showDashboard());

        HBox buttonContainer = new HBox(20, recordButton, dashboardButton);
        buttonContainer.setLayoutY(300);
        buttonContainer.setMinWidth(WINDOW_WIDTH);
        buttonContainer.setAlignment(Pos.CENTER);

        homeRoot.getChildren().addAll(heading, buttonContainer);
        Scene scene = new Scene(homeRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource(STYLES_PATH).toExternalForm());

        primaryStage.setTitle("Audio Recorder");
        primaryStage.setScene(scene);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        primaryStage.setMaxWidth(WINDOW_WIDTH);
        primaryStage.setMaxHeight(WINDOW_HEIGHT);
        primaryStage.setMinWidth(WINDOW_WIDTH);
        primaryStage.setMinHeight(WINDOW_HEIGHT);

        showHome();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}