package com.frontEnd;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Window {

    private Stage stage;
    private Scene scene;
    private Pane pane;

    public Window(String title, Stage stage) {
        pane = new Pane();
        this.stage = stage;
        this.scene = new Scene(pane);
        stage.setTitle(title);
        stage.setHeight(400);
        stage.setWidth(400);
        stage.setResizable(true);
        stage.setMaxHeight(800);
        stage.setMaxWidth(800);
    }

    public void show() {
        stage.show();
    }
    public void addPane(Pane pane) {
        scene.setRoot(pane);
        stage.setScene(scene);
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
