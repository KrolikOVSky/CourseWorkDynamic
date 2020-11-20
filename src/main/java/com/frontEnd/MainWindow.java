package com.frontEnd;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;

public class MainWindow {

    private Stage stage;
    private Scene scene;
    private BorderPane root;

    public MainWindow() {
        root = new BorderPane();
        stage = new Stage();

        //stage
        stage.setTitle("Main Window");
        stage.setWidth(600);
        stage.setHeight(400);
        stage.setResizable(true);


        // Menu
        MenuBar mainMenuBar = new MenuBar();
        {
            Menu fileMenu = new Menu("File");
            {
                MenuItem newItem = new MenuItem("New");
                {
                    newItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });
                }

                MenuItem openItem = new MenuItem("Open");
                {
                    openItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
                            fileChooser.setTitle("Open Database");//Заголовок диалога
                            FileChooser.ExtensionFilter extFilter =
                                    new FileChooser.ExtensionFilter("My Database Files (*.mydb)", "*.mdb");//Расширение
                            fileChooser.getExtensionFilters().addAll(
                                    extFilter,
                                    new FileChooser.ExtensionFilter("All Images", "*.*")
                            );
                            File file = fileChooser.showOpenDialog(stage);//Указываем текущую сцену CodeNote.mainStage

                        }
                    });
                }

                MenuItem saveItem = new MenuItem("Save");
                {
                    saveItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });
                }

                MenuItem saveAsItem = new MenuItem("Save as...");
                {
                    saveAsItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });
                }

                MenuItem closeItem = new MenuItem("Close");
                {
                    closeItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            stage.close();
                        }
                    });
                }

                fileMenu.getItems().addAll(newItem,
                        openItem,
                        new SeparatorMenuItem(),
                        saveItem,
                        saveAsItem,
                        new SeparatorMenuItem(),
                        closeItem);
            }

            Menu editMenu = new Menu("Edit");
            {
                MenuItem addTableItem = new MenuItem("Add table");
                {
                    addTableItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });
                }

                MenuItem delTableItem = new MenuItem("Delete table");
                {
                    delTableItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });
                }

                MenuItem addRecordItem = new MenuItem("Add new record to table");
                {
                    addRecordItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });
                }

                MenuItem delRecordItem = new MenuItem("Delete record from table");
                {
                    delRecordItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });
                }

                editMenu.getItems().addAll(addTableItem,
                        delTableItem,
                        new SeparatorMenuItem(),
                        addRecordItem,
                        delRecordItem);
            }

            Menu helpMenu = new Menu("Help");
            {
                MenuItem helpItem = new MenuItem("Help");
                {
                    helpItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });
                }

                MenuItem aboutItem = new MenuItem("About...");
                {
                    aboutItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            AboutWindow aboutWindow = new AboutWindow();
//                            aboutWindow.getStage().toFront();
//                            aboutWindow.getStage().showAndWait();
                        }
                    });
                }

                helpMenu.getItems().addAll(helpItem, new SeparatorMenuItem(), aboutItem);
            }

            mainMenuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
        }

        //Border Pane
        root.setTop(mainMenuBar);


        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }

}
