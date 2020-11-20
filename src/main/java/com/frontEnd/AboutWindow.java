package com.frontEnd;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AboutWindow {

//    private BorderPane root;
//
//    private Stage stage;

    public AboutWindow() {
        /*root = new BorderPane();
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

        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.close();
            }
        });*/
        var str = String.format("Java version is %s", System.getProperties().getProperty("java.version"));
        VBox vBox = new VBox();
        Label lbl = new Label(str);
        lbl.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lbl.setAlignment(Pos.CENTER);
        lbl.setTextAlignment(TextAlignment.CENTER);
        lbl.setPadding(new Insets(10, 10, 10, 10));

        Window window = new Window("About", lbl, 100, 200);
        window.showDialog();
    }

//    public Stage getStage() {
//        return stage;
//    }
}
