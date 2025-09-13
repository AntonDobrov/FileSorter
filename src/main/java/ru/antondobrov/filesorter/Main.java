package ru.antondobrov.filesorter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello! I'm File Sorter!");
        StackPane pane = new StackPane(label);
        Scene scene = new Scene(pane, 320, 240);
        stage.setScene(scene);
        stage.setTitle("File Sorter");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
