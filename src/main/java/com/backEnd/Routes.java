package com.backEnd;

import com.Global;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Routes {
/*

    private ObservableList<Route> routes = FXCollections.emptyObservableList();

    private Long count = 1L;

    public Routes() {
//        this.routes = new ArrayList<Route>();
    }

    public Routes(ObservableList<Route> routes) {
        this.routes = routes;
    }

    public ObservableList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ObservableList<Route> routes) {
        this.routes = routes;
    }

    public Route getById(Long id) {
        for (var el : routes) {
            if (el.getId() == id) return el;
        }
        return null;
    }

    public boolean add(Route route) {
        route.setId(count++);
        if (getById(route.getId()) != null) return false;
//        Global.mainList.add(route);
        return routes.addAll(route);
    }

    public boolean remove(long id) {
        if (routes == null || getById(id) == null) return false;
        return routes.remove(getById(id));
    }

    public int getLength() {
        return routes.size();
    }

    public Routes sortById() {
        Comparator<Route> comparator = Comparator.comparing(Route::getId);
        Routes routes = new Routes(this.routes);
        routes.routes.sort(comparator);
        return routes;
    }

    public Routes sortByType() {
        Comparator<Route> comparator = Comparator.comparing(Route::getTypeOfTransport);
        Routes routes = new Routes(this.routes);
        routes.routes.sort(comparator);
        return routes;
    }

    public Routes sortByLength() {
        Comparator<Route> comparator = Comparator.comparing(Route::getLength);
        Routes routes = new Routes(this.routes);
        routes.routes.sort(comparator);
        return routes;
    }

    public Routes filter_main(String typeOfTransport) {
        Routes routes = new Routes();
        for (var el : this.routes) {
            if (el.getTypeOfTransport().equals(typeOfTransport))
                routes.add(el);
        }
        return routes;
    }

    public Routes filter(String typeOfTransport) {
        var routes = new Routes();
        var length = typeOfTransport.length();
        for (var el : this.routes) {
            String str = el.getTypeOfTransport();
            if (str.substring(0, length).equals(typeOfTransport))
                routes.add(el);
        }
        return routes;
    }
*/

    private List<Route> routes;

    private Long count = 1L;

    public Routes() {
        this.routes = new ArrayList<Route>();
    }

    public Routes(List<Route> routes) {
        this.routes = routes;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Route getById(Long id) {
        for (var el : routes) {
            if (el.getId() == id) return el;
        }
        return null;
    }

    public boolean add(Route route) {
        route.setId(count++);
        if (getById(route.getId()) != null) return false;
        return routes.add(route);
    }

    public boolean remove(long id) {
        if (routes == null || getById(id) == null) return false;
        return routes.remove(getById(id));
    }

    public int getLength() {
        return routes.size();
    }

    public Routes sortById() {
        Comparator<Route> comparator = Comparator.comparing(Route::getId);
        Routes routes = new Routes(this.routes);
        routes.routes.sort(comparator);
        return routes;
    }

    public Routes sortByType() {
        Comparator<Route> comparator = Comparator.comparing(Route::getTypeOfTransport);
        Routes routes = new Routes(this.routes);
        routes.routes.sort(comparator);
        return routes;
    }

    public Routes sortByLength() {
        Comparator<Route> comparator = Comparator.comparing(Route::getLength);
        Routes routes = new Routes(this.routes);
        routes.routes.sort(comparator);
        return routes;
    }

    public void removeDownThen(int value){
        var i = 0;
        var routes = new Routes(this.getRoutes());
        for(var el : routes.getRoutes()){
            if(el.getLength() < value){
                this.remove(el.getId());
            }
        }
    }

    public Routes filter(String type) {
        var routes = new Routes();
        for(var el : this.getRoutes()){
            if(el.getTypeOfTransport().toLowerCase().startsWith(type.toLowerCase())){
                routes.add(el);
            }
        }
        return routes;
    }

//    public ObservableList<Route> getObservableList = FXCollections.observableArrayList(getRoutes());
}
