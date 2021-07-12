package ua.practice.jdbc.dao;

import ua.practice.jdbc.entity.Location;

import java.sql.*;
import java.util.*;

public class LocationDao {
    private static final String CREATE_LOCATIONS = "INSERT INTO locations (name) VALUES (?);";
    private static final String GET_BY_ID = "SELECT * FROM locations WHERE id = %d";
    private static final String GET_COUNT_OF_LOCATIONS = "SELECT COUNT(id) FROM locations";
    private static final String GET_ALL_LOCATIONS = "SELECT * FROM locations";
    private final Connection connection;

    public LocationDao(Connection connection) {
        this.connection = connection;
    }

    public void createLocation(Location location) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LOCATIONS)) {
            preparedStatement.setString(1, location.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException throwable) {
            System.out.println("This location already exists");
        }
    }

    public Integer getCountOfLocations() {
        int count = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_COUNT_OF_LOCATIONS);
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL_LOCATIONS);
            while (rs.next()) {
                Integer locationId = rs.getInt(1);
                String locationName = rs.getString(2);
                locations.add(new Location(locationId, locationName));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return locations;
    }

    public Map<Integer, Integer> getMapOfIdToIndex() {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL_LOCATIONS);
            while (rs.next()) {
                Integer locationId = rs.getInt(1);
                map.put(locationId, map.size());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return map;
    }
}
