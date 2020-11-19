package com.backEnd;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Route {
    /*private Long id;
    private String name;
    private String typeOfTransport;
    private int length;
    private int countOfStops;

    public Route(Long id, String name, String typeOfTransport, int length, int countOfStops) {
        this.id = id;
        this.name = name;
        this.typeOfTransport = typeOfTransport;
        this.length = length;
        this.countOfStops = countOfStops;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeOfTransport(String typeOfTransport) {
        this.typeOfTransport = typeOfTransport;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setCountOfStops(int countOfStops) {
        this.countOfStops = countOfStops;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypeOfTransport() {
        return typeOfTransport;
    }

    public int getLength() {
        return length;
    }

    public int getCountOfStops() {
        return countOfStops;
    }*/
    private final SimpleLongProperty id = new SimpleLongProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty typeOfTransport = new SimpleStringProperty();
    private final SimpleIntegerProperty length = new SimpleIntegerProperty();
    private final SimpleIntegerProperty countOfStops = new SimpleIntegerProperty();

    public Route() {
        id.set(0L);
        name.set("");
        typeOfTransport.set("");
        length.set(0);
        countOfStops.set(0);
    }

    public Route(Long id) {
        this.id.set(id);
    }

    public Route(String name, String typeOfTransport, int length, int countOfStops) {
//        this.id.set(id);
        this.name.set(name);
        this.typeOfTransport.set(typeOfTransport);
        this.length.set(length);
        this.countOfStops.set(countOfStops);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getTypeOfTransport() {
        return typeOfTransport.get();
    }

    public SimpleStringProperty typeOfTransportProperty() {
        return typeOfTransport;
    }

    public void setTypeOfTransport(String typeOfTransport) {
        this.typeOfTransport.set(typeOfTransport);
    }

    public int getLength() {
        return length.get();
    }

    public SimpleIntegerProperty lengthProperty() {
        return length;
    }

    public void setLength(int length) {
        this.length.set(length);
    }

    public int getCountOfStops() {
        return countOfStops.get();
    }

    public SimpleIntegerProperty countOfStopsProperty() {
        return countOfStops;
    }

    public void setCountOfStops(int countOfStops) {
        this.countOfStops.set(countOfStops);
    }

}
