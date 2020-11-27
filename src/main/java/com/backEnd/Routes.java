package com.backEnd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Routes {

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

    public int getSize() {
        return routes.size();
    }

    public Routes sortByType() {
        Comparator<Route> comparator = Comparator.comparing(Route::getTypeOfTransport).reversed().thenComparing(Route::getLength).reversed();
        Routes routes = new Routes(this.routes);
        routes.routes.sort(comparator);
        return routes;
    }

    public Routes betWeenStops(int min, int max) {
        var routes = new Routes();
        for (var el : this.routes) {
            if (el.getCountOfStops() > min && el.getCountOfStops() < max) routes.add(el);
        }
        return routes;
    }

    public List<String> result1() {
        var routes = new Routes(this.sortByType().getRoutes());
        var typesList = new ArrayList<String>();
        var result = new ArrayList<String>();

        typesList.add(routes.getById(1L).getTypeOfTransport());
        for (var el : routes.getRoutes()) {
            if (!typesList.get(typesList.size() - 1).equals(el.getTypeOfTransport())) {
                typesList.add(el.getTypeOfTransport());
            }
        }

        for (var el : typesList) {
            var min = Integer.MAX_VALUE;
            var max = Integer.MIN_VALUE;
            for (var route : routes.getRoutes()) {
                if (el.equals(route.getTypeOfTransport())) {
                    if (route.getCountOfStops() > max) max = route.getCountOfStops();
                    if (route.getCountOfStops() < min) min = route.getCountOfStops();
                }
            }
            result.add(String.format("Type of transport: %s\t\tMax: %d\t\tMin: %d", el, max, min));
        }
        return result;
    }

    public void removeDownThen(int value) {
        this.getRoutes().removeIf(n -> (n.getLength() < value));
    }

    public Routes filter(String type) {
        var routes = new Routes();
        for (var el : this.getRoutes()) {
            if (el.getTypeOfTransport().toLowerCase().startsWith(type.toLowerCase())) {
                routes.add(el);
            }
        }
        return routes;
    }

}
