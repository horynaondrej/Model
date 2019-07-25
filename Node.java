package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * This class represents nodes of the graph
 * Created by Ondřej Horyna on 2019-06-11.
 */
public class Node {

    /**
     * Class variables
     */
    private String key;
    private char color;
    private SimpleStringProperty name;
    private SimpleStringProperty job;

    private int siblings;
    private int distance;

    private double x;
    private double y;

    /**
     * Class constructor
     *
     * @param key
     */
    public Node(String key, String name, String job) {
        this.key = key;
        this.color = 'B';
        this.name = new SimpleStringProperty(name);
        this.job = new SimpleStringProperty(job);
    }

    public String get_key() {
        return key;
    }

    public char get_color() {
        return color;
    }

    public void set_color(char color) {
        this.color = color;
    }

    public String get_name() {
        return name.get();
    }

    public String get_job() {
        return job.get();
    }

    public int get_distance() {
        return distance;
    }

    public void set_distance(int distance) {
        this.distance = distance;
    }

    public int get_siblings() {
        return this.siblings;
    }

    public void set_siblings(int siblings) {
        this.siblings = siblings;
    }

    public double get_x() {
        return x;
    }

    public void set_x(double x) {
        this.x = x;
    }

    public double get_y() {
        return y;
    }

    public void set_y(double points) {
        this.y = points;
    }

    @Override
    public String toString() {

        return this.name.get();

    }

}
