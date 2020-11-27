package com.frontEnd;

import com.Global;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Window {

    private Stage stage;
    private Scene scene;
    private Pane pane;
    private Image icon;
    private String title;


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

    public Window(String title){
        this.title = title;
        this.stage = new Stage();
        this.pane = new Pane();
        this.scene = new Scene(this.pane);
        stage.setTitle(this.title);
        this.stage.initOwner(Global.primaryStage);
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
        var stage = this.stage;
        var box = new VBox();
        var close = new Button("Close");
        Effect newEffect = new GaussianBlur();
        Effect lastEffect = new Blend();//Global.primaryStage.getScene().getRoot().getEffect();
        var x = Global.primaryStage.getX() + 7;
        var y = Global.primaryStage.getY() + 30;
        var width = Global.primaryStage.getWidth() - 15;
        var height = Global.primaryStage.getHeight() - 37;

        Global.primaryStage.getScene().getRoot().setEffect(newEffect);

        close.setOnAction(event -> {
            stage.getOnCloseRequest().handle(null);
            stage.close();
        });

//        var style = "-fx-background-color: rgba(0,0,0,1)";

        box.getChildren().addAll(node, close);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        box.setPadding(new Insets(10));
        box.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ESCAPE) close.getOnAction().handle(null);
        });
//        box.setStyle(style);
//        box.setOpacity(0.5);

        this.scene.setRoot(box);
        this.scene.setUserAgentStylesheet("/com/style.css");
//        this.scene.getRoot().setStyle(style);



        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setOnCloseRequest(event -> Global.primaryStage.getScene().getRoot().setEffect(lastEffect));
        stage.setOpacity(0.7);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setX(x);
        stage.setY(y);
        stage.setScene(this.scene);

        System.out.println("X = " + x);
        System.out.println("Y = " + y);


        stage.showAndWait();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        this.stage.setScene(this.scene);
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
        this.stage.getIcons().add(this.icon);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.stage.setTitle(this.title);
    }
}
