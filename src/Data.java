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

        employees.add(new Node("cell1", "Ředitel", "Jan Novák"));
        employees.add(new Node("cell2", "Manažer", "Jiří Svoboda"));
        employees.add(new Node("cell3", "Účetní", "Ondřej Nový"));
        employees.add(new Node("cell14", "Obchodní zástupce", "Michal Malý"));
        employees.add(new Node("cell5", "IT ředitel", "Jan Černý"));
        employees.add(new Node("cell6", "Zákaznický servis", "Věra Zelená"));
        employees.add(new Node("cell77", "Back Office", "Jana Králová"));
        employees.add(new Node("cell8", "EMEA Law Specialist", "Jana Králová"));
        employees.add(new Node("cell9", "Asistentka", "Jana Králová"));
        employees.add(new Node("cell10", "Kontrola", "Ondra Nový"));

        edges.add(new Edge("cell1", "cell2"));
        edges.add(new Edge("cell1", "cell5"));
        edges.add(new Edge("cell2", "cell3"));
        edges.add(new Edge("cell5", "cell6"));
        edges.add(new Edge("cell6", "cell77"));
        edges.add(new Edge("cell6", "cell8"));
        edges.add(new Edge("cell8", "cell9"));
        edges.add(new Edge("cell1", "cell10"));

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
