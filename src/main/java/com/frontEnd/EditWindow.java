package com.frontEnd;

import com.Global;
import com.backEnd.Route;
import com.backEnd.Routes;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.util.Duration;

import static java.lang.Double.MAX_VALUE;

public class EditWindow {

    private final static Label pathCaption = new Label();
    private static TableView<Route> mainTable;
    private final BorderPane mainBoxOfElements = new BorderPane();
    private final StackPane tableRoot = new StackPane();
    private final TableColumn<Route, Long> idColumn = new TableColumn<Route, Long>("ID");
    private final TableColumn<Route, String> nameColumn = new TableColumn<Route, String>("Name");
    private final TableColumn<Route, String> typeColumn = new TableColumn<Route, String>("Type of transport");
    private final TableColumn<Route, Integer> lengthColumn = new TableColumn<Route, Integer>("Length");
    private final TableColumn<Route, Integer> stopsColumn = new TableColumn<Route, Integer>("Count of stops");

    public EditWindow() {
        mainTable = new TableView<Route>();
        HBox hBoxHeader = new HBox();

        {
            BorderPane tableWithPathCaptionBox = new BorderPane();
            VBox leftBoxOfElements = new VBox();
            VBox rightBoxOfElements = new VBox();

            // Page Name Label
            {
                pathCaption.setAlignment(Pos.CENTER);
                pathCaption.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                pathCaption.setPadding(new Insets(0, 0, 10, 0));
                hBoxHeader.getChildren().addAll(pathCaption);
            }

            // Main Table
            {
                var width = 182;
                idColumn.setCellValueFactory(new PropertyValueFactory<Route, Long>("id"));
                idColumn.setPrefWidth(42);
                idColumn.setStyle("-fx-text-alignment: center");

                nameColumn.setCellValueFactory(new PropertyValueFactory<Route, String>("name"));
                nameColumn.setPrefWidth(width);

                typeColumn.setCellValueFactory(new PropertyValueFactory<Route, String>("typeOfTransport"));
                typeColumn.setPrefWidth(width);

                lengthColumn.setCellValueFactory(new PropertyValueFactory<Route, Integer>("length"));
                lengthColumn.setPrefWidth(width);

                stopsColumn.setCellValueFactory(new PropertyValueFactory<Route, Integer>("countOfStops"));
                stopsColumn.setPrefWidth(width);

                mainTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        editLine(event);
                        remove(event);
                    }
                });
                mainTable.setPrefHeight(250);
                mainTable.getColumns().add(idColumn);
                mainTable.getColumns().add(nameColumn);
                mainTable.getColumns().add(typeColumn);
                mainTable.getColumns().add(lengthColumn);
                mainTable.getColumns().add(stopsColumn);
                tableRoot.getChildren().add(mainTable);
            }

            // Table With Caption Box
            {
                tableWithPathCaptionBox.setTop(hBoxHeader);
                tableWithPathCaptionBox.setCenter(tableRoot);
                mainBoxOfElements.setTop(tableWithPathCaptionBox);
            }

            // Left Box Of Elements
            {
                TextField inputFilter = new TextField();
                TextField inputRemoveCondition = new TextField();
                Button btnFilter = new Button();
                Button btnRemove = new Button();
                Button btnResult1 = new Button();
                Button btnResult2 = new Button();
                HBox filterBox = new HBox();
                HBox removeBox = new HBox();

                //Button Remove
                {
                    btnRemove.setText("Remove by condition");
                    btnRemove.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    btnRemove.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (!inputRemoveCondition.getText().equals("") && Global.routes != null && !Global.routes.getRoutes().isEmpty()) {
                                Global.routes.removeDownThen(Integer.parseInt(inputRemoveCondition.getText()));
                                inputRemoveCondition.setText("");
                                addToTable();
                            }
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
                            if(Global.routes != null && !Global.routes.getRoutes().isEmpty()){
                                addToTable(Global.routes.filter(inputFilter.getText()));
                            }
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
                            if (event.getCode() == KeyCode.ENTER) {
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
                            if (event.getCode() == KeyCode.ENTER) btnRemove.getOnAction().handle(new ActionEvent());
                        }
                    });
                    inputRemoveCondition.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            if (!newValue.matches("\\d*")) {
                                inputRemoveCondition.setText(newValue.replaceAll("[^\\d]", ""));
                            }
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
                            if (Global.routes != null && !Global.routes.getRoutes().isEmpty()) {
                                Global.routes.result1();
                                VBox inPane = new VBox();
                                for (var el : Global.routes.result1()) {
                                    Label lbl = new Label(el);
                                    lbl.setStyle("-fx-font-size: 20");
                                    HBox box = new HBox(lbl);
                                    inPane.getChildren().add(box);
                                }
                                inPane.setPadding(new Insets(10, 10, 10, 10));
                                Window window = new Window("Result 1", inPane);
                                window.showDialog(btnResult1);
                            }
                        }
                    });
                }

                //Button Result2
                {
                    btnResult2.setText("Result2");
                    btnResult2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    btnResult2.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (Global.routes != null && !Global.routes.getRoutes().isEmpty()) {
                                String result = String.format("Quantity of routes: %s", Global.routes.getRoutes().size());
                                Label outputText = new Label(result);
                                outputText.setStyle("-fx-font-size: 14");
                                outputText.setPadding(new Insets(10, 10, 10, 10));
                                Window window = new Window("", new BorderPane(outputText));
                                window.showDialog(btnResult2);
                            }
                        }
                    });
                }

                // Filter Box
                {
                    filterBox.setSpacing(10);
                    filterBox.setAlignment(Pos.CENTER);
                    filterBox.getChildren().setAll(inputFilter, btnFilter);
                    filterBox.setPadding(new Insets(10, 0, 0, 0));
                }

                // Remove Box
                {
                    removeBox.setSpacing(10);
                    removeBox.setAlignment(Pos.CENTER);
                    removeBox.getChildren().setAll(inputRemoveCondition, btnRemove);
                }

                leftBoxOfElements.setSpacing(10);
                leftBoxOfElements.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                leftBoxOfElements.setPrefWidth(390);
                leftBoxOfElements.setPadding(new Insets(0, 5, 0, 0));
                leftBoxOfElements.getChildren().setAll(filterBox, removeBox, btnResult1, btnResult2);
            }

            // Right Box Of Elements
            {
                Button sortingBtn = new Button("Sorting");
                BorderPane selectionBox = new BorderPane();
                Button applyBtn = new Button("Apply pop property");
                TextField getMinField = new TextField();
                TextField getMaxField = new TextField();
                Label captionOfBox = new Label("Enter interval count of stops");

                // Sorting Button
                {
                    sortingBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    sortingBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if( Global.routes != null && !Global.routes.getRoutes().isEmpty()){
                                Global.routes = Global.routes.sortByType();
                                addToTable();
                            }
                        }
                    });
                }

                // Caption of box
                {
                    captionOfBox.setStyle("-fx-font-size: 14");
                    captionOfBox.setTextAlignment(TextAlignment.CENTER);
                    captionOfBox.setAlignment(Pos.CENTER);
                    captionOfBox.setPadding(new Insets(10, 0, 10, 0));
                    captionOfBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                }

                // Min
                {
                    getMinField.setPromptText("Min");
                    getMinField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    getMinField.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            if (!newValue.matches("\\d*")) {
                                getMinField.setText(newValue.replaceAll("[^\\d]", ""));
                            }
                        }
                    });
                }

                // Max
                {
                    getMaxField.setPromptText("Max");
                    getMaxField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    getMaxField.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            if (!newValue.matches("\\d*")) {
                                getMaxField.setText(newValue.replaceAll("[^\\d]", ""));
                            }
                        }
                    });
                    getMaxField.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if(event.getCode() == KeyCode.ENTER) applyBtn.getOnAction().handle(new ActionEvent());
                        }
                    });
                }

                // Apply Button
                {
                    applyBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    applyBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if( Global.routes != null && !Global.routes.getRoutes().isEmpty() && !getMaxField.getText().equals("") && !getMinField.getText().equals("")){
                                addToTable(Global.routes.betWeenStops(Integer.parseInt(getMinField.getText()), Integer.parseInt(getMaxField.getText())));
                                getMinField.setText("");
                                getMaxField.setText("");
                            }
                        }
                    });

                }

                // Min Max Fields Box
                {
                    HBox minMaxFieldsBox = new HBox();
                    {
                        minMaxFieldsBox.getChildren().setAll(getMinField, getMaxField);
                        minMaxFieldsBox.setSpacing(10);
                        minMaxFieldsBox.setAlignment(Pos.CENTER);
                        minMaxFieldsBox.setPadding(new Insets(0, 0, 10, 0));
                    }

                    selectionBox.setTop(captionOfBox);
                    selectionBox.setCenter(minMaxFieldsBox);
                    selectionBox.setBottom(applyBtn);
                }

                rightBoxOfElements.getChildren().addAll(sortingBtn, selectionBox);
                rightBoxOfElements.setSpacing(6);
                rightBoxOfElements.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                rightBoxOfElements.setPrefWidth(390);
                rightBoxOfElements.setPadding(new Insets(10, 0, 10, 5));

            }

            // Form of adding element
            {
                var width = 153;

                HBox hBox = new HBox();
                TextField nameField = new TextField();
                TextField typeField = new TextField();
                TextField lengthField = new TextField();
                TextField stopsField = new TextField();
                Button showAll = new Button("Show All");
                Button commit = new Button("Add element to table");
                VBox vBox = new VBox();

                nameField.setPromptText("Enter name");
                nameField.setMaxSize(MAX_VALUE, MAX_VALUE);
                nameField.setPrefWidth(width);

                typeField.setPromptText("Enter type of transport");
                typeField.setMaxSize(MAX_VALUE, MAX_VALUE);
                typeField.setPrefWidth(width);

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
                stopsField.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode() == KeyCode.ENTER) commit.getOnAction().handle(new ActionEvent());
                    }
                });

                showAll.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (Global.routes != null && !Global.routes.getRoutes().isEmpty()) {
                            addToTable();
                        }
                    }
                });
                showAll.setTooltip(new Tooltip("Click to show all\nelements from database"));
                showAll.setMaxSize(MAX_VALUE, MAX_VALUE);

                commit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (Global.routes != null && !nameField.getText().equals("") && !typeField.getText().equals("") && !lengthField.getText().equals("") && !stopsField.getText().equals("")) {
                            Global.routes.add(new Route(nameField.getText(), typeField.getText(), Integer.parseInt(lengthField.getText()), Integer.parseInt(stopsField.getText())));
                            nameField.setText("");
                            typeField.setText("");
                            lengthField.setText("");
                            stopsField.setText("");
                            addToTable();
                        }
                    }
                });
                commit.setMaxSize(MAX_VALUE, MAX_VALUE);

                hBox.setSpacing(10);
                hBox.setMaxSize(MAX_VALUE, MAX_VALUE);
                hBox.getChildren().addAll(nameField, typeField, lengthField, stopsField, commit);

                vBox.setSpacing(10);
                vBox.setPadding(new Insets(10, 0, 0, 0));
                vBox.getChildren().setAll(showAll, hBox);

                mainBoxOfElements.setCenter(vBox);
            }
            mainBoxOfElements.setBottom(new BorderPane(null, null, rightBoxOfElements, null, leftBoxOfElements));
            mainBoxOfElements.setPadding(new Insets(10, 10, 10, 10));
        }
    }

    public static void addToTable() {
        setMainTable(Global.routes);
    }

    public static void addToTable(Routes routes) {
        setMainTable(routes);
    }

    public static void setMainTable(Routes routes) {
        mainTable.setItems(FXCollections.observableArrayList(routes.getRoutes()));
        pathCaption.setText(String.format("File: \"%s\"", Global.path));
    }

    public BorderPane getMainBoxOfElements() {
        return mainBoxOfElements;
    }

    public void editLine(MouseEvent mouseEvent) {
        Route currentRoute = mainTable.getSelectionModel().getSelectedItem();
        if (mouseEvent.getClickCount() == 2 && mouseEvent.getButton() == MouseButton.PRIMARY && currentRoute != null) {
            HBox inputFieldsBox = new HBox();
            VBox updateBox = new VBox();
            TextField editId = new TextField();
            TextField editName = new TextField();
            TextField editType = new TextField();
            TextField editLength = new TextField();
            TextField editStops = new TextField();
            Button commit = new Button();

            updateBox.setPadding(new Insets(0, 0, 0, 0));
            updateBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.ESCAPE) {
                        tableRoot.getChildren().clear();
                        tableRoot.getChildren().add(mainTable);
                        addToTable(Global.routes);
                    }
                }
            });

            editId.setPrefWidth(idColumn.getWidth() + 1);
            editId.setDisable(true);
            editId.setStyle("-fx-opacity: 1; -fx-text-fill: #bbbbbb");
            editId.setText(String.valueOf(currentRoute.getId()));

            editName.setPrefWidth(nameColumn.getWidth());
            editName.setText(currentRoute.getName());
            editName.setFocusTraversable(true);

            editType.setPrefWidth(typeColumn.getWidth());
            editType.setText(currentRoute.getTypeOfTransport());

            editLength.setPrefWidth(lengthColumn.getWidth());
            editLength.setText(String.valueOf(currentRoute.getLength()));
            editLength.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                        editLength.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });

            editStops.setPrefWidth(stopsColumn.getWidth());
            editStops.setText(String.valueOf(currentRoute.getCountOfStops()));
            editStops.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                        editStops.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });

            commit.setTextAlignment(TextAlignment.CENTER);
            commit.setText("Commit");
            commit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(!editName.getText().equals("") && !editType.getText().equals("") && !editLength.getText().equals("") && !editStops.getText().equals("")){
                        Global.routes.getById(Long.parseLong(editId.getText())).setName(editName.getText());
                        Global.routes.getById(Long.parseLong(editId.getText())).setTypeOfTransport(editType.getText());
                        Global.routes.getById(Long.parseLong(editId.getText())).setLength(Integer.parseInt(editLength.getText()));
                        Global.routes.getById(Long.parseLong(editId.getText())).setCountOfStops(Integer.parseInt(editStops.getText()));
                        tableRoot.getChildren().clear();
                        tableRoot.getChildren().add(mainTable);
                        addToTable();
                    }
                }
            });
            commit.setMaxWidth(MAX_VALUE);

            inputFieldsBox.getChildren().addAll(editId, editName, editType, editLength, editStops);
            inputFieldsBox.isFocused();
            updateBox.getChildren().addAll(inputFieldsBox, commit);
            tableRoot.getChildren().clear();
            tableRoot.getChildren().addAll(updateBox, mainTable);

            StackPane.setMargin(updateBox, new Insets(mouseEvent.getY(), mainBoxOfElements.getPadding().getRight(), 0, 0));
            updateBox.toFront();
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