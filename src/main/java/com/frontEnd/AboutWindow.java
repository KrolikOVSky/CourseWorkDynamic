package com.frontEnd;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class AboutWindow {

    public AboutWindow() {
        Window window = new Window("About");
        var str = String.format("Java version is %s", System.getProperties().getProperty("java.version"));
        VBox vBox = new VBox();
        Label lbl = new Label(str);
        lbl.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lbl.setAlignment(Pos.CENTER);
        lbl.setTextAlignment(TextAlignment.CENTER);
        lbl.setPadding(new Insets(10, 10, 10, 10));

//        Window window = new Window("About", lbl, 100, 200);
        window.showDialog(lbl);
    }
}
