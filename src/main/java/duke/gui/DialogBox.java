package duke.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String DUKE = "duke";
    private static final String HUMAN = "human";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String type) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        if (type == DUKE) {
            dialog.setStyle("-fx-border-color: #ffffed ;\n"
                    + "-fx-border-width: 1;\n"
                    + "-fx-border-style: solid;\n"
                    + "-fx-padding: 10 10 10 10;\n"
                    + "-fx-background-color: #ffffed ;\n"
                    + "-fx-background-radius: 5;\n"
                    + "-fx-border-radius: 5;\n"
                    + "-fx-text-fill: black");
        }
        if (text.contains("OOPS")) {
            dialog.setStyle("-fx-border-color: #ffffed ;\n"
                    + "-fx-border-width: 1;\n"
                    + "-fx-border-style: solid;\n"
                    + "-fx-padding: 10 10 10 10;\n"
                    + "-fx-background-color: #ffffed ;\n"
                    + "-fx-background-radius: 5;\n"
                    + "-fx-border-radius: 5;\n"
                    + "-fx-text-fill: red");
        }
        displayPicture.setImage(img);
    }


    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, HUMAN);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, DUKE);
        db.flip();
        return db;
    }
}
