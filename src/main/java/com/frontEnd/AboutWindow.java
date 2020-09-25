package com.frontEnd;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AboutWindow {

    private BorderPane root;

    private Stage stage;

    public AboutWindow() {
        root = new BorderPane();
        Button button = new Button("Close");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        root.setBottom(button);
        root.setTop((new Label(String.format("Java version is %s", System.getProperties().getProperty("java.version")))));

        stage = new Stage();
        stage.setHeight(100);
        stage.setWidth(200);
        stage.setTitle("About");
        stage.setScene(new Scene(root));
    }

    public Stage getStage() {
        return stage;
    }
}
