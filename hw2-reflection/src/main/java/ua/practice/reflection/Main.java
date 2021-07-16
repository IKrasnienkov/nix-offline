package ua.practice.reflection;

public class Main {
    public static void main(String[] args) {
        AppProperties appProperties = new AppProperties();
        System.out.println(appProperties);

        Initializer.init(appProperties);
        System.out.println(appProperties);
    }
}
