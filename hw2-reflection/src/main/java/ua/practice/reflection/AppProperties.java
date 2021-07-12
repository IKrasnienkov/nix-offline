package ua.practice.reflection;

public class AppProperties {
    @PropertyKey("connections.name")
    public String name;

    @PropertyKey("connections.limit")
    public int maxConnections;

    @PropertyKey("connections.status")
    public boolean isConnected;

    @PropertyKey("connections.custom")
    public CustomClass fieldWithCustomClass;

    @Override
    public String toString() {
        return "AppProperties{" +
                "name='" + name + '\'' +
                ", maxConnections=" + maxConnections +
                ", isConnected=" + isConnected +
                ", fieldWithCustomClass=" + fieldWithCustomClass +
                '}';
    }
}
