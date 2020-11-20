package com.frontEnd;

import com.Main;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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

    public Window(String title, Parent pane) {
        this.stage = new Stage();
        this.scene = new Scene(pane);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(this.scene);
    }

    public Window(String title, Parent pane, double height, double width) {
        this.stage = new Stage();
        this.scene = new Scene(pane);
        stage.setHeight(height);
        stage.setWidth(width);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(this.scene);
    }

    public void show() {
        stage.show();
    }

    public void showDialog() {
        Stage stage = this.stage;
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void showDialog(Node node) {
        Stage stage = this.stage;
        stage.initModality(Modality.APPLICATION_MODAL);
        var newEffect = new GaussianBlur();
        var lastEffect = node.getScene().getRoot().getEffect();
        node.getScene().getRoot().setEffect(newEffect);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                node.getScene().getRoot().setEffect(lastEffect);
            }
        });
        stage.showAndWait();
    }

    public void addPane(Pane pane) {
        scene.setRoot(pane);
        stage.setScene(scene);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
