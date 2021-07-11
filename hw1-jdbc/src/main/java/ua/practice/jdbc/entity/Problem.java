package ua.practice.jdbc.entity;

public class Problem {
    private Integer id;
    private Integer fromId;
    private Integer toId;

    public Problem(Integer id, Integer fromId, Integer toId) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
    }

    public Problem(Integer fromId, Integer toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }
}
