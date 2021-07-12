package ua.practice.jdbc;

import ua.practice.jdbc.service.LocationService;
import ua.practice.jdbc.service.ProblemService;
import ua.practice.jdbc.service.RouteService;
import ua.practice.jdbc.service.SolutionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

public class UI {
    private final LocationService locationService;
    private final ProblemService problemService;
    private final RouteService routeService;
    private final SolutionService solutionService;
    private final BufferedReader bufferedReader;

    public UI() {
        Connection connection = ConnectionFactory.getConnection();
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        locationService = new LocationService(connection, bufferedReader);
        problemService = new ProblemService(connection, bufferedReader);
        routeService = new RouteService(connection, bufferedReader);
        solutionService = new SolutionService(routeService, problemService, locationService, connection);
    }

    public void process() {
        String input;
        printOptions();
        try {
            while ((input = bufferedReader.readLine()) != null) {
                switch (input) {
                    case "0":
                        return;
                    case "1":
                        locationService.createLocation();
                        break;
                    case "2":
                        routeService.createRoute();
                        break;
                    case "3":
                        problemService.createProblem();
                        break;
                    case "4":
                        solutionService.createSolutions();
                        break;
                    case "5":
                        solutionService.getSolutions().forEach(System.out::println);
                        break;
                    case "6":
                        locationService.getAllLocations().forEach(System.out::println);
                        break;
                    case "7":
                        problemService.getAllProblemsWithoutSolutions().forEach(System.out::println);
                        break;
                    case "8":
                        routeService.getAllRoutes().forEach(System.out::println);
                        break;
                    default:
                        System.out.println("Incorrect input.");
                }
                printOptions();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void printOptions() {
        System.out.println("1 - create new location");
        System.out.println("2 - create new route");
        System.out.println("3 - create new problem");
        System.out.println("4 - solve problems");
        System.out.println("5 - print solutions");
        System.out.println("6 - print all locations");
        System.out.println("7 - print all problems without solutions");
        System.out.println("8 - print all routes");
        System.out.println("0 - stop");
    }
}
