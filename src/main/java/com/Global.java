package com;

import com.backEnd.Route;
import com.backEnd.Routes;
import com.frontEnd.EditWindow;
import com.frontEnd.Header;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Global {
    public static final String extension = ".mydb";
    public static String path = "";
    public static Routes routes;
    public static Stage primaryStage = new Stage();
    public static Scene mainScene = new Scene(new Pane());

    public static void setScene(Node node){
        BorderPane mainWorkSpace = new BorderPane();
        mainWorkSpace.setTop(new Header().getMainMenuBar());
        mainWorkSpace.setCenter(node);
        mainScene.setRoot(mainWorkSpace);
    }

    public static void errorReport(Exception e) {
        e.printStackTrace();
        TextArea output = new TextArea();
        {
            StringBuilder string = new StringBuilder();
            var i = 1;
            for (var el : e.getStackTrace()) {
                string.append(i).append(") ").append(el).append("\n\n");
                i++;
            }
            output.setText(string.toString());
        }
        output.setWrapText(true);

        BorderPane pane = new BorderPane();
        pane.setTop(new Label("Errors report"));
        pane.setCenter(output);
        pane.setBottom(new Label("Short description of error: " + e.getMessage()));
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setWidth(400);
        stage.setHeight(200);
//        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void fromListToFile() {
        StringBuilder content = new StringBuilder();
        for (var el : routes.getRoutes()) {
            content.append(String.format("id=%d;\n", el.getId()));
            content.append(String.format("name=%s;\n", el.getName()));
            content.append(String.format("type=%s;\n", el.getTypeOfTransport()));
            content.append(String.format("length=%d;\n", el.getLength()));
            content.append(String.format("stops=%d;\n\n", el.getCountOfStops()));
        }
        try (FileWriter writer = new FileWriter(path)) {
            writer.append(content);
            writer.flush();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void fromFileToList() {
        String[] content = new String[0];
        try {
            content = Files
                    .readString(Paths.get(path))
                    .replaceAll("\n", "")
                    .replaceAll("\r", "")
                    .split(";");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorReport(e);
        }

        routes = new Routes();//
        var j = 0;
        for (var i = 0; i < content.length / 5; i++) {
            Route route = new Route();
            route.setId(Long.parseLong(content[j].substring((content[j].indexOf("=")) + 1)));
            j++;
            route.setName(content[j].substring((content[j].indexOf("=") + 1)));
            j++;
            route.setTypeOfTransport(content[j].substring((content[j].indexOf("=") + 1)));
            j++;
            route.setLength(Integer.parseInt(content[j].substring((content[j].indexOf("=")) + 1)));
            j++;
            route.setCountOfStops(Integer.parseInt(content[j].substring((content[j].indexOf("=")) + 1)));
            j++;
            routes.add(route);
        }
        EditWindow.setMainTable(routes);
    }

    public static void newSource() {
        routes = new Routes();
        path = "";
        EditWindow.setMainTable(routes);
    }

}
