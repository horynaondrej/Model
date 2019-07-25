package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    static double width = 500;
    static double height = 500;

    private Node[] nodes;

    private Cell[] cells;
    private Group edges;

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();

        Data data = new Data();

        try {
            Model model = new Model(data.get_employees(), data.get_edges());
            this.nodes = model.get_nodes();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Draw draw = new Draw(this.nodes, data.get_edges());
            this.cells = draw.get_cells();
            this.edges = draw.get_edges();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        root.getChildren().addAll(this.cells);
        root.getChildren().addAll(this.edges);

        root.setStyle("-fx-background-color: #222a30;");

        primaryStage.setTitle("Diagram");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);

    }
}
