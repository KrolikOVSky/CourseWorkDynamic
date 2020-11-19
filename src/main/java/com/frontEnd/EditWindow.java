package com.frontEnd;

import com.Global;
import com.backEnd.Route;
import com.backEnd.Routes;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.text.DecimalFormat;

import static java.lang.Double.MAX_VALUE;

public class EditWindow {

    private final static Label pageNameLbl = new Label();
    private static TableView<Route> mainTable;
    private final BorderPane mainPane = new BorderPane();
    private final StackPane tableRoot = new StackPane();

    private final TableColumn<Route, Long> idColumn = new TableColumn<Route, Long>("ID");
    private final TableColumn<Route, String> nameColumn = new TableColumn<Route, String>("Name");
    private final TableColumn<Route, String> typeColumn = new TableColumn<Route, String>("Type of transport");
    private final TableColumn<Route, Integer> lengthColumn = new TableColumn<Route, Integer>("Length");
    private final TableColumn<Route, Integer> stopsColumn = new TableColumn<Route, Integer>("Count of stops");

    public EditWindow() {
//        mainPane = new BorderPane();
        mainTable = new TableView<Route>();
        HBox hBoxHeader = new HBox();

        {
            BorderPane pane = new BorderPane();
//            Label pageNameLbl = new Label();
            VBox vbox1 = new VBox();
            VBox vbox2 = new VBox();

            //Page Name Label
            {
//                Button newElement = new Button("Add Element");
//                {
//                    newElement.setOnAction(new EventHandler<ActionEvent>() {
//                        @Override
//                        public void handle(ActionEvent event) {
//                            Global.routes.add(new Route("new element", "create by button", 1234, 5678));
//                            mainTable.setItems(FXCollections.observableArrayList(Global.routes.getRoutes()));
//                        }
//                    });
//                }

                pageNameLbl.setAlignment(Pos.CENTER);
                pageNameLbl.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                pageNameLbl.setPadding(new Insets(0, 0, 10, 0));
                hBoxHeader.getChildren().addAll(pageNameLbl);
            }

            // Main Table
            {
                var temp = 182;
                System.out.println("temp = " + temp);
                idColumn.setCellValueFactory(new PropertyValueFactory<Route, Long>("id"));
                idColumn.setPrefWidth(42);
                idColumn.setStyle("-fx-text-alignment: center");

                nameColumn.setCellValueFactory(new PropertyValueFactory<Route, String>("name"));
                nameColumn.setPrefWidth(temp);

                typeColumn.setCellValueFactory(new PropertyValueFactory<Route, String>("typeOfTransport"));
                typeColumn.setPrefWidth(temp);

                lengthColumn.setCellValueFactory(new PropertyValueFactory<Route, Integer>("length"));
                lengthColumn.setPrefWidth(temp);

                stopsColumn.setCellValueFactory(new PropertyValueFactory<Route, Integer>("countOfStops"));
                stopsColumn.setPrefWidth(temp);

                mainTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        editLine(event);
                        remove(event);
                    }
                });

                mainTable.getColumns().add(idColumn);
                mainTable.getColumns().add(nameColumn);
                mainTable.getColumns().add(typeColumn);
                mainTable.getColumns().add(lengthColumn);
                mainTable.getColumns().add(stopsColumn);
                tableRoot.getChildren().add(mainTable);
            }

            //Pane
            {
//                pane.setTop(pageNameLbl);
                pane.setTop(hBoxHeader);
                pane.setCenter(tableRoot);
                mainPane.setTop(pane);
            }

            // Vbox1
            {
                TextField inputFilter = new TextField();
                TextField inputRemoveCondition = new TextField();
                Button btnFilter = new Button();
                Button btnRemove = new Button();
                Button btnResult1 = new Button();
                Button btnResult2 = new Button();
                HBox hBox1 = new HBox();
                HBox hBox2 = new HBox();

                //Button Remove
                {
                    btnRemove.setText("Remove by condition");
                    btnRemove.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    btnRemove.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Global.routes.removeDownThen(Integer.parseInt(inputRemoveCondition.getText()));
                        }
                    });
                }

                //Button Filter
                {
                    btnFilter.setText("Apply filter");
                    btnFilter.setPrefWidth(129);
                    btnFilter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    btnFilter.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            addToTable(Global.routes.filter(inputFilter.getText()));
                            inputFilter.setText("");
                        }
                    });

                }

                //Input Filter
                {
                    inputFilter.setPromptText("Enter filter");
                    inputFilter.setPrefWidth(250);
                    inputFilter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    inputFilter.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if(event.getCode() == KeyCode.ENTER){
                                btnFilter.getOnAction().handle(new ActionEvent());
                            }
                        }
                    });
                }

                //Remove Condition
                {
                    inputRemoveCondition.setPromptText("Enter remove condition");
                    inputRemoveCondition.setPrefWidth(250);
                    inputRemoveCondition.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    inputRemoveCondition.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if(event.getCode() == KeyCode.ENTER) btnRemove.getOnAction().handle(new ActionEvent());
                        }
                    });
                }

                //Button Result1
                {
                    btnResult1.setText("Result1");
                    btnResult1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    btnResult1.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
//                            mainTable.setItems(Global.mainList);
//                            System.out.println("Result1 clicked");
                        }
                    });

                }

                //Button Result2
                {
                    btnResult2.setText("Result2");
                    btnResult2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                }

                //HBox1
                {
                    hBox1.setSpacing(10);
                    hBox1.setAlignment(Pos.CENTER);
                    hBox1.getChildren().setAll(inputFilter, btnFilter);
                    hBox1.setPadding(new Insets(10, 0, 0, 0));
                }

                //HBox2
                {
                    hBox2.setSpacing(10);
                    hBox2.setAlignment(Pos.CENTER);
                    hBox2.getChildren().setAll(inputRemoveCondition, btnRemove);
                }

                //VBox1
                {
                    vbox1.setSpacing(10);
                    vbox1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    vbox1.setPrefWidth(390);
                    vbox1.setPadding(new Insets(0, 5, 0, 0));
                    vbox1.getChildren().setAll(hBox1, hBox2, btnResult1, btnResult2);
                }

            }

            //Vbox2
            {
                Button sortingBtn = new Button("Sorting");
                BorderPane internalPane = new BorderPane();
                Button applyBtn = new Button("Apply pop property");
                TextField min = new TextField();
                TextField max = new TextField();
                Label internalLbl = new Label("Enter interval count of stops");

                // Sorting Button
                {
                    sortingBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                }

                // Internal Label
                {
                    internalLbl.setTextAlignment(TextAlignment.CENTER);
                    internalLbl.setAlignment(Pos.CENTER);
                    internalLbl.setPadding(new Insets(10, 0, 10, 0));
                    internalLbl.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                }

                // Min
                {
                    min.setPromptText("Min");
                    min.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    min.autosize();
                }

                // Max
                {
                    max.setPromptText("Max");
                    max.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    max.autosize();
                }

                // Apply Button
                {
                    applyBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    applyBtn.autosize();
                }

                // Internal Pane
                {
                    HBox hBox = new HBox();
                    {
                        hBox.getChildren().setAll(min, max);
                        hBox.setSpacing(10);
                        hBox.setAlignment(Pos.CENTER);
                        hBox.setPadding(new Insets(0, 0, 10, 0));
                    }

                    internalPane.setTop(internalLbl);
                    internalPane.setCenter(hBox);
                    internalPane.setBottom(applyBtn);
                }

                vbox2.getChildren().addAll(sortingBtn, internalPane);
                vbox2.setSpacing(9);
                vbox2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                vbox2.setPrefWidth(390);
                vbox2.setPadding(new Insets(10, 0, 10, 5));

            }

            // Form of adding element
            {
                var width = 153;

                HBox hBox = new HBox();
                TextField nameField = new TextField();
                nameField.setPromptText("Enter name");
                nameField.setMaxSize(MAX_VALUE, MAX_VALUE);
                nameField.setPrefWidth(width);

                TextField typeField = new TextField();
                typeField.setPromptText("Enter type of transport");
                typeField.setMaxSize(MAX_VALUE, MAX_VALUE);
                typeField.setPrefWidth(width);

                TextField lengthField = new TextField();
                lengthField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (!newValue.matches("\\d*")) {
                            lengthField.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });
                lengthField.setPromptText("Enter length");
                lengthField.setMaxSize(MAX_VALUE, MAX_VALUE);
                lengthField.setPrefWidth(width);

                TextField stopsField = new TextField();
                stopsField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (!newValue.matches("\\d*")) {
                            stopsField.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });
                stopsField.setPromptText("Enter stops");
                stopsField.setMaxSize(MAX_VALUE, MAX_VALUE);
                stopsField.setPrefWidth(width);

                Button commit = new Button("Add element to table");
                commit.setMaxSize(MAX_VALUE, MAX_VALUE);
                commit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println(nameField.getText());
                        System.out.println(typeField.getText());
                        System.out.println(lengthField.getText());
                        System.out.println(stopsField.getText());
                        if (!nameField.getText().equals("") && !typeField.getText().equals("") && !lengthField.getText().equals("") && !stopsField.getText().equals("")) {
                            Global.routes.add(new Route(nameField.getText(), typeField.getText(), Integer.parseInt(lengthField.getText()), Integer.parseInt(stopsField.getText())));
                            mainTable.setItems(FXCollections.observableArrayList(Global.routes.getRoutes()));
                        }
                    }
                });

                Button showAll = new Button("Show All");
                showAll.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        addToTable();
                    }
                });
                showAll.setMaxSize(MAX_VALUE, MAX_VALUE);

                hBox.setSpacing(10);
                hBox.setMaxSize(MAX_VALUE, MAX_VALUE);
                hBox.getChildren().addAll(nameField, typeField, lengthField, stopsField, commit);

                VBox vBox = new VBox();
                vBox.setSpacing(10);
                vBox.setPadding(new Insets(10, 0, 0, 0));
                vBox.getChildren().setAll(showAll, hBox);

                mainPane.setCenter(vBox);
            }



            mainPane.setBottom(new BorderPane(null, null, vbox2, null, vbox1));
//            mainPane.setLeft(vbox1);
//            mainPane.setRight(vbox2);
            mainPane.setPadding(new Insets(10, 10, 10, 10));
        }
    }

    public static void addToTable() {
        mainTable.setItems(Global.getMainList());
    }

    public static void addToTable(Routes routes) {
        mainTable.setItems(FXCollections.observableArrayList(routes.getRoutes()));
    }

    public BorderPane getMainPane() {
        return mainPane;
    }

    public TableView<Route> getMainTable() {
        return mainTable;
    }

    public static void setMainTable(ObservableList<Route> observable) {
        mainTable.setItems(observable);
        pageNameLbl.setText(String.format("File: \"%s\"", Global.path));
    }

    public void editLine(MouseEvent mouseEvent) {
        Route currentRoute = mainTable.getSelectionModel().getSelectedItem();

        if (mouseEvent.getClickCount() == 2 && mouseEvent.getButton() == MouseButton.PRIMARY && currentRoute != null) {
            HBox hbox = new HBox();
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(0, 0, 0, 0));
            vbox.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.ESCAPE) {
                        tableRoot.getChildren().clear();
                        tableRoot.getChildren().add(mainTable);
                        addToTable(Global.routes);
                    }
                }
            });

            TextField editId = new TextField();
            editId.setPrefWidth(idColumn.getWidth() + 1);
            editId.setDisable(true);
            editId.setStyle("-fx-opacity: 1; -fx-text-fill: #bbbbbb");
            editId.setText(String.valueOf(currentRoute.getId()));

            TextField editName = new TextField();
            editName.setPrefWidth(nameColumn.getWidth());
            editName.setText(currentRoute.getName());
            editName.setFocusTraversable(true);

            TextField editType = new TextField();
            editType.setPrefWidth(typeColumn.getWidth());
            editType.setText(currentRoute.getTypeOfTransport());

            TextField editLength = new TextField();
            editLength.setPrefWidth(lengthColumn.getWidth());
            editLength.setText(String.valueOf(currentRoute.getLength()));

            TextField editStops = new TextField();
            editStops.setPrefWidth(stopsColumn.getWidth());
            editStops.setText(String.valueOf(currentRoute.getCountOfStops()));

            Button commit = new Button();
            commit.setTextAlignment(TextAlignment.CENTER);
            commit.setText("Commit");
            commit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Global.routes.getById(Long.parseLong(editId.getText())).setName(editName.getText());
                    Global.routes.getById(Long.parseLong(editId.getText())).setTypeOfTransport(editType.getText());
                    Global.routes.getById(Long.parseLong(editId.getText())).setLength(Integer.parseInt(editLength.getText()));
                    Global.routes.getById(Long.parseLong(editId.getText())).setCountOfStops(Integer.parseInt(editStops.getText()));
                    tableRoot.getChildren().clear();
                    tableRoot.getChildren().add(mainTable);
                    addToTable();
                }
            });
            commit.setMaxWidth(MAX_VALUE);

            hbox.getChildren().addAll(editId, editName, editType, editLength, editStops);
            hbox.isFocused();
            vbox.getChildren().addAll(hbox, commit);
            tableRoot.getChildren().clear();
            tableRoot.getChildren().addAll(vbox, mainTable);

//        if (mouseEvent.getClickCount() == 2) {
            StackPane.setMargin(vbox, new Insets(mouseEvent.getY(), mainPane.getPadding().getRight(), 0, 0));
            vbox.toFront();
        }
    }

    public void remove(MouseEvent event) {

        if (event.getButton() == MouseButton.SECONDARY) {
            Route currentRoute = mainTable.getSelectionModel().getSelectedItem();
            System.out.println(currentRoute);

            if (currentRoute != null) {
                ContextMenu contextMenu = new ContextMenu();
                MenuItem removeItem = new MenuItem(String.format("Remove %s's element", currentRoute.getId()));
                removeItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Global.routes.remove(currentRoute.getId());
                        addToTable();
                    }
                });
                contextMenu.getItems().add(removeItem);
                mainTable.setContextMenu(contextMenu);
            }
        }

    }


}
