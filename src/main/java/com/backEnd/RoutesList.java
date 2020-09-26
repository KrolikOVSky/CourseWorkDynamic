package com.backEnd;

import java.util.ArrayList;
import java.util.List;

public class RoutesList{

    private List<Route> routes;

    public RoutesList() {
        this.routes = new ArrayList<Route>();
    }

    public Route getId(Long id){
        for (var el : routes) {
            if (el.getId().equals(id)) return el;
        }
        return null;
    }

    public boolean add(Route route){
        if (getId(route.getId()) != null) return false;
        return routes.add(route);
    }

    public boolean remove(long id) {
        if (routes == null || getId(id) == null) return false;
        return routes.remove(getId(id));
    }

    public int getLength() {
        return routes.size();
    }
}
