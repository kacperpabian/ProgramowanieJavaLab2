package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private Button welcomeButton;

    public void generate (ActionEvent event) {

        try {
            final Node source = (Node) event.getSource();
            final Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Stuff.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Stuff");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image("file:Resources/icon.png"));
            stage.show();

        } catch (Exception e) {
            System.out.println("Błąd przy załadowywaniu drugiego okna.");
        }
    }
}
