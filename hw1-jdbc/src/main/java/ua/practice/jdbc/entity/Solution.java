package ua.practice.jdbc.entity;

public class Solution {
    private Integer problemId;
    private Integer cost;

    public Solution(Integer problemId, Integer cost) {
        this.problemId = problemId;
        this.cost = cost;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "problemId=" + problemId +
                ", cost=" + cost +
                '}';
    }
}
