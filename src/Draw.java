package sample;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.shape.Line;

import java.util.ArrayList;

/**
 * Created by Ondřej Horyna on 2019-06-11.
 */
public class Draw {

    /**
     * Class variables
     */
    private Cell[] cells;
    private Node[] nodes;
    private Group edges;
    private int number_of_edges;
    private int number_of_nodes;
    private ArrayList<Edge> data = new ArrayList<>();

    /**
     * Class Constructor
     */
    public Draw(Node[] nodes, ObservableList<Edge> edges) {

        if (nodes != null && edges != null) {
            this.nodes = nodes;
            this.number_of_nodes = nodes.length;
            set_edges(edges);
            initialize_nodes();
            relocate_nodes();
            initialize_edges();
            relocate_lines();
        }

    }

    /**
     * This method save array with edges into this class
     * @param param
     */
    public void set_edges(ObservableList<Edge> param) {

        if (param != null) {
            this.data = new ArrayList<>(param);
            this.number_of_edges = data.size();
        }

    }

    /**
     * Fill array with nodes
     */
    public void initialize_nodes() {

        this.cells = new Cell[this.number_of_nodes];

        for (int i = 0; i < this.number_of_nodes; i++) {
            this.cells[i] = new Cell(this.nodes[i].get_key(),this.nodes[i].get_name() + ",\n" + this.nodes[i].get_job());
        }

    }

    /**
     *  This method relocate the nodes via graph directions
     */
    public void relocate_nodes() {

        for (int i = 0; i < this.number_of_nodes; i++) {
            this.cells[i].get_shape().relocate(
                    this.nodes[i].get_y() - (this.cells[i].get_shape().getWidth() / 2), //x
                    this.nodes[i].get_x() - this.cells[i].get_shape().getHeight()); //y
            this.cells[i].get_text().relocate(
                    this.nodes[i].get_y() + 2 - (this.cells[i].get_shape().getWidth() / 2), //x
                    this.nodes[i].get_x() + 2 - this.cells[i].get_shape().getHeight()); //y
        }
    }

    /**
     * This method return array with relocated nodes
     */
    public Cell[] get_cells() {

        return this.cells;

    }

    /**
     * This method initialize nodes
     */
    public void initialize_edges() {

        this.edges = new Group();

        for (int i = 0; i < this.number_of_edges; i++) {
            this.edges.getChildren().add(new Line());
        }

    }

    /**
     * Method for draw lines
     */
    public void relocate_lines() {

        String source, target;
        double x_Source, y_source, x_target, y_target;

        for (int i = 0; i < this.data.size(); i++) {

            source = this.data.get(i).get_source();
            target = this.data.get(i).get_target();

            y_source = this.cells[return_index(source)].get_shape().getLayoutX() + (this.cells[return_index(source)].get_shape().getWidth() / 2); //x
            x_Source = this.cells[return_index(source)].get_shape().getLayoutY() + this.cells[return_index(source)].get_shape().getHeight(); //y

            y_target = this.cells[return_index(target)].get_shape().getLayoutX() + (this.cells[return_index(target)].get_shape().getWidth() / 2); //x
            x_target = this.cells[return_index(target)].get_shape().getLayoutY(); //y

            Line e = new Line();

                e.setStartX(y_source); //x
                e.setStartY(x_Source); //y
                e.setEndX(y_target); //x
                e.setEndY(x_target); //y

                e.setStyle("-fx-stroke: #00A388;");

            this.edges.getChildren().add(e);

        }

    }

    /**
     * This method returns index of the node
     * @param key
     * @return
     */
    public int return_index(String key) {

        int pom = -1;
        for (int i = 0; i < this.cells.length; i++) {
            if (this.cells[i].get_key().equals(key)) {
                pom = i;
            }
        }
        return pom;

    }


    /**
     * Method returns array with graphic lines
     * @return
     */
    public Group get_edges() {

        return edges;

    }
}
