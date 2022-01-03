package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class LoginController {
    public AnchorPane loginFormContext;


    public void moveToRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        navigate("../view/studentRegistrationForm.fxml");

    }

    public void moveToReportsOnAction(ActionEvent actionEvent) throws IOException {
        navigate("../view/reportsForm.fxml");

    }

    public void moveToTrainingProgramOnAction(ActionEvent actionEvent) throws IOException {
        navigate("../view/TrainingProgramsForm.fxml");

    }

    private void navigate(String path) throws IOException {
        Stage window = (Stage) loginFormContext.getScene().getWindow();

        window.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)))));
    }
}
