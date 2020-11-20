package com.frontEnd;

import com.Global;
import com.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.FileChooser;

import java.io.File;

public class Header {

    private final MenuBar mainMenuBar;

    public Header() {
        // Menu
        mainMenuBar = new MenuBar();
        {
            Menu fileMenu = new Menu("File");
            {
                MenuItem newItem = new MenuItem("New");
                {
                    newItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Global.newSource();
                        }
                    });
                }

                MenuItem openItem = new MenuItem("Open");
                {
                    openItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
//                            FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
//                            fileChooser.setTitle("Open Database");//Заголовок диалога
//                            FileChooser.ExtensionFilter extFilter =
//                                    new FileChooser.ExtensionFilter("My Database Files (*.mydb)", Global.extension);//Расширение
//                            fileChooser.getExtensionFilters().addAll(
//                                    extFilter,
//                                    new FileChooser.ExtensionFilter("All Images", "*.*")
//                            );
//
//                            File file = fileChooser.showOpenDialog(Main.primaryStage);//Указываем текущую сцену CodeNote.mainStage
                            File file = new File("C:\\Users\\Alex\\Desktop\\test.mydb");
                            if (file != null) {
                                Global.path = file.getPath();
                                Global.fromFileToList();
                            }

                        }
                    });
                }

                MenuItem saveAsItem = new MenuItem("Save as...");
                {
                    saveAsItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            FileChooser fileChooser = new FileChooser();
                            fileChooser.setTitle("Save Database");
                            FileChooser.ExtensionFilter extFilter =
                                    new FileChooser.ExtensionFilter("MyDB files (*.mydb)", Global.extension);
                            fileChooser.getExtensionFilters().add(extFilter);
                            File file = fileChooser.showSaveDialog(Main.primaryStage);
                            if (file != null) {
                                Global.path = file.getPath();
                                try {
                                    Global.fromListToFile();
                                } catch (Exception exception) {
                                    Global.errorReport(exception);
                                }
                            }
                        }
                    });
                }

                MenuItem saveItem = new MenuItem("Save");
                {
                    saveItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (!Global.path.equals("")) {
                                Global.fromListToFile();
                            } else {
                                saveAsItem.getOnAction().handle(event);
                            }
                        }
                    });
                }
                MenuItem closeItem = new MenuItem("Close");
                {
                    closeItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Main.primaryStage.close();
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

            /*Menu editMenu = new Menu("Edit");
            {
                MenuItem addTableItem = new MenuItem("Add table");
                {
                    addTableItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
//                            Main.setScene(new EditWindow().getMainBoxOfElements());
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

            mainMenuBar.getMenus().addAll(fileMenu, /*editMenu,*/ helpMenu);
        }
    }

    public MenuBar getMainMenuBar() {
        return mainMenuBar;
    }
}
