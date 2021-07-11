package ua.practice.jdbc.service;

import ua.practice.jdbc.dao.LocationDao;
import ua.practice.jdbc.entity.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class LocationService {
    private final LocationDao locationDao;
    private final BufferedReader bufferedReader;

    public LocationService(Connection connection, BufferedReader bufferedReader) {
        this.locationDao = new LocationDao(connection);
        this.bufferedReader = bufferedReader;
    }

    public boolean createLocation() {
        Location location = initLocation();
        return locationDao.createLocation(location);
    }

    public Integer getCountOfLocations() {
        return locationDao.getCountOfLocations();
    }

    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    public Map<Integer, Integer> getMapOfIdToIndex() {
        return locationDao.getMapOfIdToIndex();
    }

    private Location initLocation() {
        String name = "";
        try {
            System.out.println("Input location name:");
            name = bufferedReader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return new Location(name);
    }

}
