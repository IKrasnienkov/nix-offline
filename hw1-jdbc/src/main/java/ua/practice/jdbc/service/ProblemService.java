package ua.practice.jdbc.service;

import ua.practice.jdbc.dao.ProblemDao;
import ua.practice.jdbc.entity.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class ProblemService {
    private final ProblemDao problemDao;
    private final BufferedReader bufferedReader;

    public ProblemService(Connection connection, BufferedReader bufferedReader) {
        this.problemDao = new ProblemDao(connection);
        this.bufferedReader = bufferedReader;
    }

    public void createProblem() {
        Problem problem = initProblem();
        problemDao.createProblem(problem);
    }

    public List<Problem> getAllProblemsWithoutSolutions() {
        return problemDao.getAllProblemsWithoutSolutions();
    }

    private Problem initProblem() {
        int fromId = 0;
        int toId = 0;
        try {
            System.out.println("input start location id (from_id)");
            fromId = Integer.parseInt(bufferedReader.readLine());
            System.out.println("input finish location id (to_id)");
            toId = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return new Problem(fromId, toId);
    }
}
