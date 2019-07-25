package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Ondřej Horyna on 2019-06-12.
 */
public class Data {

    private ObservableList<Node> employees =
            FXCollections.observableArrayList();

    private ObservableList<Edge> edges =
            FXCollections.observableArrayList();

    /**
     * Class constructor
     */
    public Data() {

        employees.add(new Node("cell1", "Main", "Main class"));
        employees.add(new Node("cell2", "Data", "Provides Data"));
        employees.add(new Node("cell3", "Node", "Class represents node"));
        employees.add(new Node("cell4", "Edge", "Source with target node"));
        employees.add(new Node("cell5", "Model", "Maths nodes positions"));
        employees.add(new Node("cell6", "Queue", "First In First out"));
        employees.add(new Node("cell7", "Draw", "Drawing cells"));
        employees.add(new Node("cell8", "Cell", "Node with graphics"));


        edges.add(new Edge("cell1", "cell2"));
        edges.add(new Edge("cell2", "cell3"));
        edges.add(new Edge("cell2", "cell4"));
        edges.add(new Edge("cell1", "cell5"));
        edges.add(new Edge("cell5", "cell6"));
        edges.add(new Edge("cell1", "cell7"));
        edges.add(new Edge("cell7", "cell8"));

    }

    /**
     * Method returns employees list
     * @return
     */
    public ObservableList<Node> get_employees() {

        return this.employees;

    }

    /**
     * This method returns list with edges
     * @return
     */
    public ObservableList<Edge> get_edges() {

        return this.edges;

    }

}
