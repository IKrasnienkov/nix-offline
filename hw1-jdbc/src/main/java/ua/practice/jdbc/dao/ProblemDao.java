package ua.practice.jdbc.dao;

import ua.practice.jdbc.entity.Problem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemDao {
    private static final String CREATE_PROBLEM = "INSERT INTO problems (from_id, to_id) VALUES (?, ?);";
    private static final String GET_PROBLEMS_WITHOUT_SOLUTIONS = "select p.id, p.from_id, p.to_id from problems p\n" +
            "left join solutions s on p.id = s.problem_id\n" +
            "where s.problem_id is null";
    private final Connection connection;

    public ProblemDao(Connection connection) {
        this.connection = connection;
    }

    public void createProblem(Problem problem) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PROBLEM)) {
            preparedStatement.setInt(1, problem.getFromId());
            preparedStatement.setInt(2, problem.getToId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<Problem> getAllProblemsWithoutSolutions() {
        ArrayList<Problem> problems = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_PROBLEMS_WITHOUT_SOLUTIONS);
            Problem problem;
            while (rs.next())
            {
                Integer problemId = rs.getInt(1);
                Integer problemFromId = rs.getInt(2);
                Integer problemToId = rs.getInt(3);
                problem = new Problem(problemId, problemFromId, problemToId);
                problems.add(problem);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return problems;
    }
}
