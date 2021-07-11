package ua.practice.jdbc.dao;

import ua.practice.jdbc.entity.Solution;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao {
    private static final String CREATE_SOLUTION = "INSERT INTO solutions VALUES (?, ?)";
    private static final String GET_SOLUTIONS = "SELECT * FROM solutions";

    private final Connection connection;

    public SolutionDao(Connection connection) {
        this.connection = connection;
    }

    public void createSolution(Solution solution) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SOLUTION)) {
            preparedStatement.setInt(1, solution.getProblemId());
            preparedStatement.setInt(2, solution.getCost());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            System.out.println("solution with problem id '" + solution.getProblemId() + "' already exists");
        }
    }

    public List<Solution> getSolutions() {
        List<Solution> solutions = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_SOLUTIONS);
            while (rs.next()) {
                Integer problemId = rs.getInt(1);
                Integer problemCost = rs.getInt(2);
                solutions.add(new Solution(problemId, problemCost));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return solutions;
    }
}
