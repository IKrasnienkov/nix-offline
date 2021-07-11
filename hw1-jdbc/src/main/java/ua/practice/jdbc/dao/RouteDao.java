package ua.practice.jdbc.dao;

import ua.practice.jdbc.entity.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteDao {
    private static final String CREATE_ROUTES = "INSERT INTO routes (from_id, to_id, cost) VALUES (?, ?, ?);";
    private static final String GET_ROUTES_BY_FROM_AND_TO_ID = "SELECT * FROM routes WHERE from_id = ? AND to_id = ?";
    private static final String GET_ALL_ROUTES = "SELECT * FROM routes";
    private final Connection connection;

    public RouteDao(Connection connection) {
        this.connection = connection;
    }

    public void createRoute(Route route) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ROUTES)) {
            preparedStatement.setInt(1, route.getFromId());
            preparedStatement.setInt(2, route.getToId());
            preparedStatement.setInt(3, route.getCost());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Optional<Route> getById(int fromId, int toId) {
        Optional<Route> routeOptional = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTES_BY_FROM_AND_TO_ID)) {
            preparedStatement.setInt(1, fromId);
            preparedStatement.setInt(1, toId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Integer routeId = rs.getInt(1);
                Integer routeFromId = rs.getInt(2);
                Integer routeToId = rs.getInt(3);
                Integer routeCost = rs.getInt(4);
                Route route = new Route(routeId, routeFromId, routeToId, routeCost);
                routeOptional = Optional.of(route);
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return routeOptional;
    }

    public List<Route> getAllRoutes() {
        ArrayList<Route> routes = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL_ROUTES);
            Route route;
            while (rs.next())
            {
                Integer routeId = rs.getInt(1);
                Integer routeFromId = rs.getInt(2);
                Integer routeToId = rs.getInt(3);
                Integer routeCost = rs.getInt(4);
                route = new Route(routeId, routeFromId, routeToId, routeCost);
                routes.add(route);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return routes;
    }
}
