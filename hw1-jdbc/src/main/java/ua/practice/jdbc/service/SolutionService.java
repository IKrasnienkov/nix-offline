package ua.practice.jdbc.service;

import ua.practice.jdbc.dao.SolutionDao;
import ua.practice.jdbc.entity.Problem;
import ua.practice.jdbc.entity.Route;
import ua.practice.jdbc.entity.Solution;
import ua.practice.jdbc.algorithm.ShortestPath;

import java.sql.Connection;
import java.util.*;

public class SolutionService {
    private final ProblemService problemService;
    private final LocationService locationService;
    private final SolutionDao solutionDao;
    private final List<Route> routes;
    private int[][] matrix;
    private int countOfLocations;
    private Map<Integer, Integer> locationIdIndexMap;

    public SolutionService(RouteService routeService, ProblemService problemService, LocationService locationService, Connection connection) {
        this.problemService = problemService;
        this.locationService = locationService;
        this.routes = routeService.getAllRoutes();
        this.solutionDao = new SolutionDao(connection);
    }

    public void createSolutions() {
        initMatrixByAllRoutes();
        List<Problem> problems = problemService.getAllProblemsWithoutSolutions();
        Solution solution;
        for (Problem problem : problems) {
            int res = ShortestPath.findShortestPath(matrix, countOfLocations,
                    locationIdIndexMap.get(problem.getFromId()), locationIdIndexMap.get(problem.getToId()));
            solution = new Solution(problem.getId(), res);
            solutionDao.createSolution(solution);
        }
    }

    private void initMatrixByAllRoutes() {
        countOfLocations = locationService.getCountOfLocations();
        matrix = new int[countOfLocations][];
        for (int i = 0; i < countOfLocations; i++) {
            matrix[i] = new int[countOfLocations];
            Arrays.fill(matrix[i], 0);
        }
        locationIdIndexMap = locationService.getMapOfIdToIndex();
        for (Route route : routes) {
            int i = locationIdIndexMap.get(route.getFromId());
            int j = locationIdIndexMap.get(route.getToId());
            int cost = route.getCost();
            matrix[i][j] = cost;
        }
    }

    public List<Solution> getSolutions(){
        return solutionDao.getSolutions();
    }
}
