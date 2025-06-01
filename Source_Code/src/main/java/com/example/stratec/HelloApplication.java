package com.example.stratec;

import com.example.stratec.DataManipulation.ReadData;
import com.example.stratec.Model.SolarSystem;
import com.example.stratec.Service.ComputationsStageOne;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class HelloApplication extends Application {
    private static Stage primaryStage;
    private static SolarSystem solarSystem;

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        primaryStage = stage;

        // Read planetary data
        solarSystem = ReadData.readPlanetsData();

        // Load the initial scene
        switchScene("StartPage.fxml", "Welcome");
    }

    /**
     * Switch to a new scene and pass SolarSystem data
     * @param fxmlFile The FXML file to load
     * @param title The window title
     */
    public static void switchScene(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/com/example/stratec/" + fxmlFile));
            Scene scene = new Scene(loader.load(), 900, 507);

            // If switching to StageOne, pass SolarSystem data
            if (fxmlFile.equals("StageOnePage.fxml")) {
                StageOneController controller = loader.getController();
                controller.displayPlanetaryData(solarSystem);
            }

            if (fxmlFile.equals("StageTwoPage.fxml")) {
                StageTwoController controller = loader.getController();
                controller.displayEscapeCalculations(solarSystem);
            }
            if (fxmlFile.equals("StageThreePage.fxml")) {
                StageThreeController controller = loader.getController();
                controller.init(solarSystem);
            }

            if (fxmlFile.equals("StageFourPage.fxml")) {
                StageFourController controller = loader.getController();
                controller.initialize(solarSystem);
            }

            if (fxmlFile.equals("StageFourPage.fxml")) {
                StageFourController controller = loader.getController();
                controller.initialize(solarSystem);
            }
            if (fxmlFile.equals("StageFivePage.fxml")) {
                StageFiveController controller = loader.getController();
                controller.initialize(solarSystem);
            }
            if (fxmlFile.equals("StageSixPage.fxml")) {
                StageSixController controller = loader.getController();
                controller.initialize(solarSystem);
            }
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle properly in production
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
