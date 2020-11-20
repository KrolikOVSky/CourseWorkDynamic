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

    public Routes sortByType() {
        Comparator<Route> comparator = Comparator.comparing(Route::getTypeOfTransport).reversed().thenComparing(Route::getLength).reversed();
        Routes routes = new Routes(this.routes);
        routes.routes.sort(comparator);
        return routes;
    }

    public Routes betWeenStops(int min, int max){
        var routes = new Routes();
        for(var el : this.routes){
            if(el.getCountOfStops() > min && el.getCountOfStops() < max) routes.add(el);
        }
        return routes;
    }

    public List<String> result1(){
        var routes = new Routes(this.sortByType().getRoutes());
        var typesList = new ArrayList<String>();
        var result = new ArrayList<String>();

        typesList.add(routes.getById(1L).getTypeOfTransport());
        for(var el : routes.getRoutes()){
            if(!typesList.get(typesList.size()-1).equals(el.getTypeOfTransport())){
                typesList.add(el.getTypeOfTransport());
            }
        }

        for(var el : typesList){
            var min = Integer.MAX_VALUE;
            var max = Integer.MIN_VALUE;
            for(var route : routes.getRoutes()){
                if(el.equals(route.getTypeOfTransport())){
                    if(route.getCountOfStops() > max) max = route.getCountOfStops();
                    if(route.getCountOfStops() < min) min = route.getCountOfStops();
                }
            }
            result.add(String.format("Type of transport: %s\t\tMax: %d\t\tMin: %d", el, max, min));
        }
//        System.out.println(result.toString());

//        for (var el : typesList) System.out.println(el);
        return result;
    }

    public void removeDownThen(int value){
        this.getRoutes().removeIf(n -> (n.getLength() < value));
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
