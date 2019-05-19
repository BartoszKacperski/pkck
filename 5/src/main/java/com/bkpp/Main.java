package com.bkpp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class Main extends Application {

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/load.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.XMLReaderBundle"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle(fxmlLoader.getResources().getString("appTitle"));
        primaryStage.setScene(scene);
        primaryStage.show();
        stage = primaryStage;
    }

    public static Stage getPrimaryStage() {
        return stage;
    }

}
