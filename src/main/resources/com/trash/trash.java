/*    private List<Route> routes;

    public Routes() {
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
    }*/

/*

 Global.routes != null && !Global.routes.getRoutes().isEmpty()

.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (!newValue.matches("\\d*")) {
                            stopsField.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });





 */