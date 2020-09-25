package com;

import com.frontEnd.MainWindow;
import com.frontEnd.Window;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Course extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        MainWindow mainWindow = new MainWindow();
        mainWindow.show();

    }
}

