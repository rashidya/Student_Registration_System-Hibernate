package controller;

import business.BOFactory;
import business.custom.ManageReportsBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import dto.RegistrationDetailsDTO;
import dto.StudentDTO;
import dto.StudentProgramDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public class ReportsFormController {
    public AnchorPane reportsFormContext;
    public JFXButton btnBack;
    public AnchorPane studentDetailContext;
    public JFXComboBox<String> cmbSelect;
    public TableColumn colOB;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colFullName;
    public TableColumn colStId;
    public TableView<StudentDTO> tblStudent;
    public JFXTextField txtContact;
    public JFXTextField txtFullName;
    public JFXTextField txtStId;
    public JFXTextField txtDOB;
    public JFXTextField txtAddress;
    public TableView<StudentProgramDTO> tblPrograms;
    public TableColumn colProgramId;
    public TableColumn colProgram;
    public TableColumn colDuration;
    public TableColumn colFee;
    public TableColumn colRegistrationDate;
    public TableColumn colNIC;
    public JFXTextField txtNIC;
    public Label lblTotalStudents;

    ManageReportsBO manageReportsBO = (ManageReportsBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.REPORT);


    public void initialize() {
        setSelectComboBox();
        cmbSelect.setValue("ALL");
        setAllStudentTable();
        lblTotalStudents.setText(String.valueOf(tblStudent.getItems().size()));
        cmbSelect.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(cmbSelect.getItems().get(0))) {
                try {
                    setStudentTable(manageReportsBO.getProgramIDByName(newValue).get(0));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                setAllStudentTable();
            }
            lblTotalStudents.setText(String.valueOf(tblStudent.getItems().size()));

        });

        colStId.setCellValueFactory(new PropertyValueFactory<>("st_Id"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));

        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));


        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            studentDetailContext.setVisible(true);
            setStudentDetails(newValue);
        });
    }

    private void setStudentDetails(StudentDTO dto) {
        StudentDTO student = manageReportsBO.getStudent(dto.getSt_Id());
        txtStId.setText(dto.getSt_Id());
        txtFullName.setText(dto.getFullName());
        txtDOB.setText(dto.getDob());
        txtAddress.setText(dto.getAddress());
        txtContact.setText(dto.getContactNo());
        txtNIC.setText(dto.getNic());
        ObservableList<StudentProgramDTO> programsItems = tblPrograms.getItems();

        for (RegistrationDetailsDTO detailsDTO : student.getDetailsList()) {
            ProgramDTO program = detailsDTO.getProgram();
            StudentProgramDTO studentProgram = new StudentProgramDTO(program.getProgramId(), program.getProgram(), program.getDuration(), program.getFee(), detailsDTO.getRegistrationDate());
            programsItems.add(studentProgram);
        }
        tblPrograms.setItems(programsItems);
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) reportsFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/login.fxml")))));
    }

    public void BackOnAction(ActionEvent actionEvent) {
        studentDetailContext.setVisible(false);
    }

    private void setSelectComboBox() {
        List<ProgramDTO> allPrograms = manageReportsBO.getAllPrograms();
        cmbSelect.getItems().add("ALL");
        for (ProgramDTO allProgram : allPrograms) {
            cmbSelect.getItems().add(allProgram.getProgram());
        }

    }

    private void setAllStudentTable() {
        ObservableList<StudentDTO> list = tblStudent.getItems();
        list.addAll(manageReportsBO.getAllStudents());
        tblStudent.setItems(list);
    }

    private void setStudentTable(String id) throws ParseException {
        List<StudentDTO> studentsOfAProgram = manageReportsBO.getStudentsOfAProgram(id);
        tblStudent.getItems().clear();
        ObservableList<StudentDTO> students = tblStudent.getItems();
        students.addAll(studentsOfAProgram);
        tblStudent.setItems(students);
    }
}
