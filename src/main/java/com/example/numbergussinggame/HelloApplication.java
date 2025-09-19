package com.example.numbergussinggame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showGameScene();
        stage.show();
    }

    public static void showGameScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/com/example/numbergussinggame/hello-view.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
    }

    public static void showLeaderboardScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/com/example/numbergussinggame/bee.fxml"));

        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


