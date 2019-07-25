package sample;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Created by Ondřej Horyna on 2019-06-11.
 */
public class Cell extends Pane {

    /**
     * Rectangle (double width, double height)
     */
    private String key;
    private Rectangle shape;
    private Text text;

    /**
     * Class constructor
     * @param key, title
     */
    public Cell(String key, String title) {

        this.key = key;
        this.text = new Text(title);
        this.text.setFont(new Font(12));

        this.shape = new Rectangle(
                this.text.getBoundsInLocal().getWidth() + 5,
                this.text.getBoundsInLocal().getHeight() + 5
        );
        this.text.setTextAlignment(TextAlignment.CENTER);
        this.shape.setArcWidth(5);
        this.shape.setArcHeight(5);
        set_view(this.shape, this.text);
        fill_with_color();

    }

    /**
     * This method returns link to text of node
     * @return
     */
    public Text get_text() {

        return text;

    }

    /**
     * This method return link to shape of node
     * @return
     */
    public Rectangle get_shape() {

        return shape;

    }

    /**
     * Metoda pro vrácení proměnné
     * @return key
     */
    public String get_key() {

        return key;

    }

    /**
     * This method colors the node
     */
    public void fill_with_color() {

        shape.setFill(Color.LIGHTGRAY);

    }


    public void set_view(Node view, Node text) {

        getChildren().addAll(view, text);

    }

}
