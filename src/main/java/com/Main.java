package com;

import com.frontEnd.EditWindow;
import com.frontEnd.Header;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

//    public static BorderPane mainWorkSpace = new BorderPane();
    public static Stage primaryStage = new Stage();
    public static Scene mainScene = new Scene(new Pane());

    public static void setScene() {
        BorderPane mainWorkSpace = new BorderPane();
        Button btn = new Button("Click here");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainWorkSpace.setTop(new Header().getMainMenuBar());
            }
        });

        mainWorkSpace.setCenter(btn);
/*        mainWorkSpace.setTop(new Button("Top"));
        mainWorkSpace.setLeft(new Button("Left"));
        mainWorkSpace.setRight(new Button("Right"));
        mainWorkSpace.setCenter(new Button("Center"));
        mainWorkSpace.setBottom(new Button("Bottom"));*/
        mainScene.setRoot(mainWorkSpace);
    }
    
    public static void setScene(Node node){
        BorderPane mainWorkSpace = new BorderPane();
//        mainWorkSpace.setPrefSize(800, 600);
        mainWorkSpace.setTop(new Header().getMainMenuBar());
        mainWorkSpace.setCenter(node);
        mainScene.setRoot(mainWorkSpace);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        setScene(new EditWindow().getMainBoxOfElements());
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Program to work with database of transport");
        primaryStage.getIcons().add(new Image("/com/images/_icon.png"));
        primaryStage.show();
    }
}//C:\Users\Alex\IdeaProjects\CourseWorkDynamic\src\main\resources\com\images
