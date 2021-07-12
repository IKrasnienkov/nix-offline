package ua.practice.jdbc.service;

import ua.practice.jdbc.dao.RouteDao;
import ua.practice.jdbc.entity.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class RouteService {

    private final RouteDao routeDao;
    private final BufferedReader bufferedReader;

    public RouteService(Connection connection, BufferedReader bufferedReader) {
        this.routeDao = new RouteDao(connection);
        this.bufferedReader = bufferedReader;
    }

    public void createRoute() {
        Route route = initRoute();
        routeDao.createRoute(route);
    }

    public List<Route> getAllRoutes() {
        return routeDao.getAllRoutes();
    }

    private Route initRoute() {
        int fromId = 0;
        int toId = 0;
        int cost = 0;
        try {
            System.out.println("input start location id (from_id)");
            fromId = Integer.parseInt(bufferedReader.readLine());
            System.out.println("input finish location id (to_id)");
            toId = Integer.parseInt(bufferedReader.readLine());
            System.out.println("input cost of this route");
            cost = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return new Route(fromId, toId, cost);
    }
}
