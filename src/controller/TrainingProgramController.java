package controller;

import business.BOFactory;
import business.custom.ManageProgramsBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.xdevapi.Schema;
import dto.ProgramDTO;
import entity.Program;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vaidation.Validation;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class TrainingProgramController {
    public AnchorPane trainingProgramsFormContext;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;
    public JFXTextField txtProgram;
    public JFXTextField txtProgramId;
    public TableView<ProgramDTO> tblPrograms;
    public TableColumn colProgramId;
    public TableColumn colProgram;
    public TableColumn colDuration;
    public TableColumn colFee;
    public JFXButton btnAddOrUpdate;

    private final ManageProgramsBO manageProgramsBO = (ManageProgramsBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PROGRAM);
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {

        setProgramTable();
        storeValidations();

        colProgramId.setCellValueFactory(new PropertyValueFactory("programId"));
        colProgram.setCellValueFactory(new PropertyValueFactory("program"));
        colDuration.setCellValueFactory(new PropertyValueFactory("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory("fee"));

        tblPrograms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setProgramDetails(newValue.getProgramId());
                btnAddOrUpdate.setText("Update");
            }
        });
        for (TextField textField : map.keySet()) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                checkAllFields();
            });
        }
    }

    private void storeValidations() {
        map.put(txtProgram, Validation.text);
        map.put(txtDuration, Validation.text);
        map.put(txtFee, Validation.decimalNumber);
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) trainingProgramsFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/login.fxml")))));
    }

    public void addOrUpdateProgramOnAction(ActionEvent actionEvent) {
        if (checkAllFields()) {
            ProgramDTO programDTO = new ProgramDTO(txtProgramId.getText(), txtProgram.getText(), txtDuration.getText(), Double.parseDouble(txtFee.getText()));
            Alert alert;
            if (btnAddOrUpdate.getText().equals("Add")) {

                if (manageProgramsBO.addProgram(programDTO)){
                    alert =new Alert(Alert.AlertType.INFORMATION,"Program Added Successfully!");

                }else{
                    alert =new Alert(Alert.AlertType.WARNING,"Something went wrong.Try Again.");
                }
                alert.show();


            } else {

                if ( manageProgramsBO.updateProgram(programDTO)){
                    alert =new Alert(Alert.AlertType.INFORMATION,"Program Updated Successfully!");

                }else{
                    alert =new Alert(Alert.AlertType.WARNING,"Something went wrong.Try Again.");
                }
                alert.show();

            }
            setProgramTable();
            clearAllFields();

        }

    }

    private boolean checkAllFields() {
        if (!txtProgramId.getText().isEmpty()){
            if (Validation.validate(map)){
                btnAddOrUpdate.setDisable(false);
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }

    public void cancelOnAction(ActionEvent actionEvent) {
        clearAllFields();
    }

    private void clearAllFields() {
        btnAddOrUpdate.setText("Add");
        btnAddOrUpdate.setDisable(true);
        txtProgramId.clear();
        txtProgramId.setEditable(true);

        for (TextField textField : map.keySet()) {
            textField.clear();
            textField.setStyle("-fx-border-color: white;"+"-fx-border-width: 1");
        }

    }

    private void setProgramTable() {
        List<ProgramDTO> programDTOList = manageProgramsBO.getAllPrograms();
        ObservableList<ProgramDTO> programList = FXCollections.observableArrayList();
        programList.addAll(programDTOList);
        tblPrograms.setItems(programList);

    }

    private void setProgramDetails(String programId) {
        ProgramDTO program = manageProgramsBO.getProgram(programId);
        txtProgramId.setText(program.getProgramId());
        txtProgram.setText(program.getProgram());
        txtDuration.setText(program.getDuration());
        txtFee.setText(String.valueOf(program.getFee()));
        txtProgramId.setEditable(false);
    }


}
