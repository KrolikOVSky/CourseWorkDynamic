package com.frontEnd;

import com.Global;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;

import java.io.File;

public class Header {

    private final MenuBar mainMenuBar;

    MenuItem openItem = new MenuItem("Open");


    public Header() {
        // Menu
        mainMenuBar = new MenuBar();
        {
            Menu fileMenu = new Menu("File");
            {
                MenuItem newItem = new MenuItem("New");
                {
                    newItem.setOnAction(event -> {
                        Global.newSource();
                    });
                    newItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
                }

//                MenuItem openItem = new MenuItem("Open");
                {
                    openItem.setOnAction(event -> {
//                            FileChooser fileChooser = new FileChooser();
//                            fileChooser.setTitle("Open Database");
//                            FileChooser.ExtensionFilter extFilter =
//                                    new FileChooser.ExtensionFilter("My Database Files (*"+Global.extension+")", "*" + Global.extension);
//                            fileChooser.getExtensionFilters().addAll(
//                                    extFilter,
//                                    new FileChooser.ExtensionFilter("All Images", "*.*")
//                            );
//
//                            File file = fileChooser.showOpenDialog(Global.primaryStage);
                        File file = new File("C:\\Users\\Alex\\Desktop\\test.mydb");
                        if (file != null) {
                            Global.path = file.getPath();
                            Global.fromFileToList();
                        }

                    });
                    KeyCombination keyCombination = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
                    openItem.setAccelerator(keyCombination);
                }

                MenuItem saveAsItem = new MenuItem("Save as...");
                {
                    saveAsItem.setOnAction(event -> {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Save Database");
                        FileChooser.ExtensionFilter extFilter =
                                new FileChooser.ExtensionFilter("My Database Files ("+Global.extension+")", Global.extension);
                        fileChooser.getExtensionFilters().add(extFilter);
                        fileChooser.setInitialFileName("my_database_file");
                        File file = fileChooser.showSaveDialog(Global.primaryStage);
                        if (file != null) {
                            Global.path = file.getPath();
                            try {
                                Global.fromListToFile();
                            } catch (Exception exception) {
                                Global.errorReport(exception);
                            }
                        }
                    });
                    saveAsItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
                }

                MenuItem saveItem = new MenuItem("Save");
                {
                    saveItem.setOnAction(event -> {
                        if (!Global.path.equals("")) {
                            Global.fromListToFile();
                        } else {
                            saveAsItem.getOnAction().handle(event);
                        }
                    });
                    saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
                }

                MenuItem closeItem = new MenuItem("Close");
                {
                    closeItem.setOnAction(event -> Global.primaryStage.close());
                }

                fileMenu.getItems().addAll(newItem,
                        openItem,
                        new SeparatorMenuItem(),
                        saveItem,
                        saveAsItem,
                        new SeparatorMenuItem(),
                        closeItem);
            }

            /*Menu editMenu = new Menu("Edit");
            {
                MenuItem addTableItem = new MenuItem("Add table");
                {
                    addTableItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
//                            Run.setScene(new EditWindow().getMainBoxOfElements());
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
            }*/

            Menu helpMenu = new Menu("Help");
            {
                MenuItem helpItem = new MenuItem("Help");
                {
                    helpItem.setOnAction(event -> {
                        System.out.println("Help shortcut clicked");
                    });
                    helpItem.setAccelerator(new KeyCodeCombination(KeyCode.F1));
                }

                MenuItem aboutItem = new MenuItem("About...");
                {
                    aboutItem.setOnAction(event -> {
                        AboutWindow aboutWindow = new AboutWindow();
                    });
                }

                helpMenu.getItems().addAll(helpItem, new SeparatorMenuItem(), aboutItem);
            }
            mainMenuBar.getMenus().add(fileMenu);
//            mainMenuBar.getMenus().add(editMenu);
            mainMenuBar.getMenus().add(helpMenu);
//            mainMenuBar.getMenus().addAll(fileMenu, /*editMenu,*/ helpMenu);
        }
    }

    public MenuBar getMainMenuBar() {
        openItem.getOnAction().handle(new ActionEvent());
        return mainMenuBar;
    }
}
