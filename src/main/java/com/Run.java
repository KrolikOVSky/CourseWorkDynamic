package com;

import com.frontEnd.EditWindow;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Run extends Application {
    public static void run(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        var editWindow = new EditWindow();
        Global.setScene(editWindow.getMainBoxOfElements());
        Global.primaryStage.setScene(Global.mainScene);
        Global.primaryStage.setResizable(false);
        Global.primaryStage.setTitle("Program to work with database of transport");
        Global.primaryStage.getIcons().add(new Image("/com/images/icon.png"));


        Global.primaryStage.show();
    }
}
