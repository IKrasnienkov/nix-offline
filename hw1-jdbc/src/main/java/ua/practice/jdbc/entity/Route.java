package ua.practice.jdbc.entity;

public class Route {
    private Integer id;
    private Integer fromId;
    private Integer toId;
    private Integer cost;

    public Route(Integer id, Integer fromId, Integer toId, Integer cost) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.cost = cost;
    }

    public Route(Integer fromId, Integer toId, Integer cost) {
        this.fromId = fromId;
        this.toId = toId;
        this.cost = cost;
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Route{" +
                "fromId=" + fromId +
                ", toId=" + toId +
                ", cost=" + cost +
                '}';
    }
}
