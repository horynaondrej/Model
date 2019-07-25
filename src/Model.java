package sample;

import javafx.collections.ObservableList;
import java.util.ArrayList;

/**
 * Created by Ondřej Horyna on 2019-07-21.
 */
public class Model {

    /**
     * Class variables
     */
    private ArrayList<Node> employees;
    private ArrayList<Edge> edges;

    private Node[] nodes;

    private int[][] matrix;

    private double[] x_directions_array;

    private int number_of_nodes;
    private int maximum_level_number;
    private int x_scale;

    /**
     * Class constructor
     * @param param
     * @param edges
     */
    public Model(ObservableList<Node> param, ObservableList<Edge> edges)
            throws IllegalArgumentException {

        set_x_scale(1);
        set_employees(param, edges);
        set_edges(edges);
        set_nodes_size();
        set_up_matrix();
        initialize_nodes_array();
        load_edges_into_matrix();
        BFS("cell1");
        move_node_under_parent();
        find_max_level_depth();
        find_out_siblings();
        set_y_directions();
        x_directions();
        set_up_level_array();
        set_x_directions();
        //print_matrix();

    }

    /**
     * This method set employees into its data list, it also check nodes
     * with no parents
     * @param param
     * @param edges
     */
    public void set_employees(ObservableList<Node> param, ObservableList<Edge> edges) {

        this.employees = new ArrayList<>();

        for (int i = 0; i < param.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                if (param.get(i).get_key().equals(edges.get(j).get_source())
                        || param.get(i).get_key().equals(edges.get(j).get_target())) {
                    this.employees.add(param.get(i));
                    break;
                }
            }

        }

    }

    /**
     * This method fill up the nodes data array
     * @param edges
     */
    public void set_edges(ObservableList<Edge> edges) {

        this.edges = new ArrayList<>(edges);

    }

    /**
     * This method set the number of nodes
     */
    public void set_nodes_size() {

        if (this.employees != null) {
            this.number_of_nodes = this.employees.size();
        } else {
            System.out.printf("There is no data in the table!");
        }

    }

    /**
     * This method initialize matrix 2D int array
     */
    public void set_up_matrix() {

        this.matrix = new int[this.number_of_nodes][this.number_of_nodes];

    }

    /**
     * This method returns index of the node
     * @param key
     * @return
     */
    public int return_index(String key) {

        int pom = -1;
        for (int i = 0; i < this.number_of_nodes; i++) {
            if (this.nodes[i].get_key().equals(key)) {
                pom = i;
            }
        }
        return pom;

    }

    /**
     * This method cut a very long titles
     * @return Cutted job title
     */
    public String new_values_of_text(String x) {

        char new_line_char = '\n';
        char[] to_char_array = x.toCharArray();
        char[] new_values = new char[to_char_array.length];

        for (int f = 0; f < to_char_array.length; f++) {

            if (Character.isSpaceChar(to_char_array[f]) && f > 3) {
                new_values[f] = new_line_char;
            }
            else {
                new_values[f] = to_char_array[f];
            }
        }
        return String.valueOf(new_values);

    }

    /**
     * This method insert a node into the array with nodes
     */
    public void initialize_nodes_array() {

        this.nodes = new Node[this.number_of_nodes];

        for (int i = 0; i < this.number_of_nodes; i++) {
            this.nodes[i] = new Node(
                    this.employees.get(i).get_key(),
                    new_values_of_text(this.employees.get(i).get_name()),
                    new_values_of_text(this.employees.get(i).get_job()));
        }

    }

    /**
     * This method load edges into matrix
     */
    public void load_edges_into_matrix() {

        for (int i = 0; i < this.edges.size(); i++) {
            insert_edge_into_matrix(
                    this.edges.get(i).get_source(),
                    this.edges.get(i).get_target());
        }

    }

    /**
     * This method insert an edge to the matrix
     * @param start
     * @param end
     */
    public void insert_edge_into_matrix(String start, String end) {

        for (int m = 0; m < this.number_of_nodes; m++) {
            if (this.nodes[m].get_key().equals(start)) {
                for (int n = 0; n < this.number_of_nodes; n++) {
                    if (this.nodes[n].get_key().equals(end)) {
                        this.matrix[m][n] = 1;
                    }
                }
            }
        }

    }

    /**
     * This method print the whole matrix
     */
    public void print_matrix() {

        System.out.println("\nThe Matrix\n");
        for (int i = 0; i <= this.number_of_nodes; i++) {
            if (i != this.number_of_nodes) {
                System.out.print(i + " ");
            }
            else {
                System.out.println(i + " ");
            }
        }
        for (int m = 0; m < this.number_of_nodes; m++) {
            System.out.print((m + 1) + " ");
            for (int n = 0; n < this.number_of_nodes; n++) {
                System.out.print(this.matrix[m][n] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    /**
     * This method cuts canvas in y directions via number of nodes; it is used
     * only as return method
     */
    public double y_directions(int key) {

        double vertical_step;

        if (key == 0) {
            vertical_step = Main.height / 2;
        } else {
            vertical_step = Main.height / (key + 1);
        }
        return vertical_step;

    }

    /**
     * This method cuts canvas via number of nodes
     */
    public double x_directions() {

        int sum = 0;
        for (int i = 0; i < this.number_of_nodes; i++) {
            for (int j = 0; j < this.number_of_nodes; j++) {
                if (this.matrix[i][j] == 1) {
                    sum++;
                    break;
                }
            }
        }
        return Main.width / (sum + this.x_scale);

    }

    /**
     * This method set sum variable
     * @param param
     */
    public void set_x_scale(int param) {

        this.x_scale = param;

    }

    /**
     * This method increase sum of nodes
     */
    public void increase_x_scale() {

        this.x_scale++;

    }

    /**
     * This method set x position of nodes
     */
    public void set_x_directions() {

        int scale = 1;
        if (this.x_directions_array != null) {
            for (int i = 0; i < this.number_of_nodes; i++) {
                for (int j = 0; j < this.x_directions_array.length; j++) {
                    if (this.nodes[i].get_distance() == this.x_directions_array[j]) {
                        if (this.number_of_nodes < 5) {
                            scale = 2;
                        }
                        this.nodes[i].set_x((x_directions() * this.x_directions_array[j]) + (x_directions() / scale));
                        if (this.nodes[i].get_x() > Main.width) {
                            increase_x_scale();
                            set_x_directions();
                        }
                    }
                }
            }
        }

    }

    /**
     * Method for set up an array with levels of nodes
     */
    public void set_up_level_array() {

        this.maximum_level_number++;
        this.x_directions_array = new double[this.maximum_level_number];
        for (int i = 0; i < this.maximum_level_number; i++) {
            this.x_directions_array[i] = i;
        }

    }

    /**
     * Method for breath first search
     * @param firstNode
     */
    public void BFS(String firstNode) {

        Queue fr = new Queue(this.number_of_nodes);
        int s = return_index(firstNode);
        this.nodes[s].set_color('S');
        this.nodes[s].set_distance(0);
        fr.insert(s);

        while (!fr.is_empty()) {
            int i = fr.select();
            for (int j = 0; j < this.number_of_nodes; j++) {
                if (this.matrix[i][j] == 1) {
                    if (this.nodes[j].get_color() == 'B') {
                        this.nodes[j].set_color('S');
                        this.nodes[j].set_distance(this.nodes[i].get_distance() + 1);
                        fr.insert(j);
                    }
                }
            }
            this.nodes[i].set_color('C');
        }

    }

    /**
     * This method move exact node, which has more than one father
     */
    public void move_node_under_parent() {

        int max = 0;
        int node = 0;
        int count = 0;
        for (int i = 0; i < this.number_of_nodes; i++) {
            for (int j = 0; j < this.number_of_nodes; j++) {
                if (this.matrix[j][i] == 1) {
                    node = i;
                    count++;
                    if (j > max) {
                        max = j;
                    }
                }
            }
            if (count > 1) {
                this.nodes[node].set_distance(this.nodes[max].get_distance() + 1);
                move_other_node_under_parent(node);
            }
            max = 0;
            count = 0;
        }

    }

    /**
     * This method have to move node compare to previous node scheme
     */
    public void move_other_node_under_parent(int node) {

        for (int i = 0; i < this.number_of_nodes; i++) {
            if (this.matrix[node][i] == 1) {
                this.nodes[i].set_distance(this.nodes[node].get_distance() + 1);
                move_other_node_under_parent(i);
            }
        }

    }

    /**
     * Method find the max value of siblings
     */
    public void find_max_level_depth() {

        this.maximum_level_number = 0;
        for (int i = 0; i < this.number_of_nodes; i++) {
            if (this.nodes[i].get_distance() > this.maximum_level_number) {
                this.maximum_level_number = this.nodes[i].get_distance();
            }
        }
        this.maximum_level_number++;

    }

    /**
     * This method find out how many siblings the node has
     */
    public void find_out_siblings() {

        int count = 0;
        for (int i = 0; i < this.number_of_nodes; i++) {
            for (int j = 0; j < this.number_of_nodes; j++) {
                if (this.nodes[i].get_distance() == this.nodes[j].get_distance()) {
                    count++;
                }
            }
            this.nodes[i].set_siblings(count);
            count = 0;
        }

    }

    /**
     * This method draw the nodes in y directions
     */
    public void set_y_directions() {

        double points, step;
        for (int a = 0; a < this.maximum_level_number; a++) {
            for (int j = 0; j < this.number_of_nodes; j++) {
                points = step = y_directions(j);
                for (int i = 0; i < number_of_nodes; i++) {
                    if (this.nodes[i].get_distance() == a && this.nodes[i].get_siblings() == j) {
                        this.nodes[i].set_y(points);
                        points += step;
                    }
                }
            }
        }

    }

    /**
     * This method je link to the array with nodes
     * @return
     */
    public Node[] get_nodes() {

        return nodes;

    }

}
