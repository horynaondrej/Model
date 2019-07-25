package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * This class have information about target and source points of the edges
 * Created by Ondřej Horyna on 2019-06-12.
 */
public class Edge {

    /**
     * Class variables
     */
    private SimpleStringProperty source;
    private SimpleStringProperty target;


    /**
     * Class constructor
     * @param source
     * @param target
     */
    public Edge(String source, String target) {

        this.source = new SimpleStringProperty(source);
        this.target = new SimpleStringProperty(target);

    }

    /**
     * Set up a source of edge
     * @return String
     */
    public String get_source() {

        return this.source.get();

    }

    /**
     * This method get up a target of edge
     * @return String
     */
    public String get_target() {

        return this.target.get();

    }

}