package controller;

import business.BOFactory;
import business.custom.ManageStudentRegistrationBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import dto.RegistrationDetailsDTO;
import dto.StudentDTO;
import entity.Program;
import entity.RegistrationDetails;
import entity.Student;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class StudentRegistrationFormController {
    public AnchorPane registrationFormContext;
    public JFXTextField txtFullName;
    public Label lblSt_Id;
    public JFXTextField txtContactNo;
    public JFXTextField txtAddress;
    public JFXComboBox<String> cmbTrainingProgram;
    public JFXComboBox<Integer> cmbDay;
    public JFXComboBox<Integer> cmbMonth;
    public JFXComboBox<Integer> cmbYear;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;
    public JFXButton btnRegister;
    public JFXButton btnCancel;
    public JFXTextField txtNIC;
    public TableView<ProgramDTO> tblPrograms;
    public TableColumn colProgramId;
    public TableColumn colProgram;
    public TableColumn colFee;
    public TableColumn colDuration;
    public JFXComboBox cmbSearchID;
    public JFXListView<String> listSearchID;
    public TextField txtSearchID;
    public TextField txtSearchName;
    public JFXListView<String> listSearchName;
    private List<ProgramDTO> allPrograms;
    ManageStudentRegistrationBO studentRegistrationBO = (ManageStudentRegistrationBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.STUDENT);

    List<Student> studentListByName;
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {
        lblSt_Id.setText(studentRegistrationBO.generateId());
        setComboBoxes();
        storeValidations();
        cmbTrainingProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setProgramDetails(newValue);
            }
        });
        colProgramId.setCellValueFactory(new PropertyValueFactory("programId"));
        colProgram.setCellValueFactory(new PropertyValueFactory("program"));
        colDuration.setCellValueFactory(new PropertyValueFactory("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory("fee"));

        txtSearchID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                loadStudentListID(newValue);
            } else {
                listSearchID.setVisible(false);
            }
        });

        listSearchID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setStudentDetials(newValue);
                listSearchID.setVisible(false);
                txtSearchID.clear();
            }

        });
        listSearchName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                for (Student student : studentListByName) {
                    if (newValue.equals(student.getFullName())) {
                        setStudentDetials(student.getSt_Id());
                        listSearchName.setVisible(false);
                        txtSearchName.clear();
                    }
                }

            }

        });

        txtSearchName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {

                loadStudentListName(newValue);
            } else {
                listSearchName.setVisible(false);
            }
        });

        for (TextField textField : map.keySet()) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                Validation.validate(map);
            });
        }
    }

    private void storeValidations() {
        map.put(txtFullName, Validation.personName);
        map.put(txtNIC, Validation.nic);
        map.put(txtContactNo, Validation.contactNo);
        map.put(txtAddress, Validation.address);


    }

    private boolean setStudentDetials(String stId) {
        StudentDTO dto = studentRegistrationBO.getStudent(stId);
        if (dto != null) {
            lblSt_Id.setText(dto.getSt_Id());
            txtFullName.setText(dto.getFullName());
            txtAddress.setText(dto.getAddress());
            txtContactNo.setText(dto.getContactNo());
            txtNIC.setText(dto.getNic());
            String[] split =dto.getDob().split("-");
            String day = split[0];
            String month = split[1];
            String year = split[2];
            cmbDay.setValue(Integer.valueOf(day));
            cmbMonth.setValue(Integer.valueOf(month));
            cmbYear.setValue(Integer.valueOf(year));
            ObservableList<ProgramDTO> programs = FXCollections.observableArrayList();
            for (RegistrationDetailsDTO registrationDetailsDTO : dto.getDetailsList()) {
                programs.add(registrationDetailsDTO.getProgram());
            }
            tblPrograms.setItems(programs);

            btnRegister.setText("Update");


            return true;
        }
        return false;
    }

    private void loadStudentListID(String id) {

        List<String> studentList = studentRegistrationBO.getStudentListID(id);
        if (studentList.isEmpty()) {
            listSearchID.setVisible(false);
        } else {
            ObservableList<String> list = FXCollections.observableArrayList();
            list.addAll(studentList);
            listSearchID.setItems(list);
            listSearchID.setVisible(true);

        }

    }

    private void loadStudentListName(String name) {

        studentListByName = studentRegistrationBO.getStudentListName(name);
        if (studentListByName.isEmpty()) {
            listSearchName.setVisible(false);
        } else {
            ObservableList<String> list = FXCollections.observableArrayList();
            for (Student student : studentListByName) {
                list.add(student.getFullName());
            }

            listSearchName.setItems(list);
            listSearchName.setVisible(true);

        }

    }

    public void setComboBoxes() {
        int start = Calendar.getInstance().get(Calendar.YEAR) - 25;
        int end = Calendar.getInstance().get(Calendar.YEAR) - 18;
        ObservableList<Integer> year = FXCollections.observableArrayList();
        for (int i = start; i <= end; i++) {
            year.add(i);
        }
        cmbYear.setItems(year);

        ObservableList<Integer> month = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        cmbMonth.setItems(month);

        ObservableList<Integer> day = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
        cmbDay.setItems(day);

        setPrograms();
    }

    public void setPrograms() {
        ObservableList<String> programList = FXCollections.observableArrayList();
        allPrograms = studentRegistrationBO.getAllPrograms();
        for (ProgramDTO program : allPrograms) {
            programList.add(program.getProgram());
        }

        cmbTrainingProgram.setItems(programList);
    }

    public void setProgramDetails(String programName) {
        for (ProgramDTO program : allPrograms) {
            if (program.getProgram().equals(programName)) {
                txtDuration.setText(program.getDuration());
                txtFee.setText(String.valueOf(program.getFee()));
            }
        }
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) registrationFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/login.fxml")))));


    }

    public void registerStudentOnAction(ActionEvent actionEvent) throws ParseException {
        if (checkAll()) {
            List<RegistrationDetailsDTO> detailsList = new ArrayList<>();
            String st_Id = lblSt_Id.getText();
            String fullName = txtFullName.getText();
            String contact = txtContactNo.getText();
            String nic = txtNIC.getText();
            String address = txtAddress.getText();
            int day = cmbDay.getValue();
            int month = cmbMonth.getValue();
            int year = cmbYear.getValue();
            String dob = day + "-" + month + "-" + year;

            StudentDTO studentDTO = new StudentDTO(st_Id, fullName, nic, dob, address, contact);

            for (ProgramDTO programDTO : tblPrograms.getItems()) {
                RegistrationDetailsDTO detail = new RegistrationDetailsDTO();
                detail.setStudent(studentDTO);
                detail.setProgram(programDTO);
                detailsList.add(detail);
            }
            studentDTO.setDetailsList(detailsList);
            if (btnRegister.getText().equals("Register")) {
                studentRegistrationBO.registerStudent(studentDTO);
            } else {

                studentRegistrationBO.updateStudent(studentDTO);
                L1:
                for (RegistrationDetailsDTO item : detailsList) {
                    for (RegistrationDetailsDTO studentProgrms : studentRegistrationBO.getStudent(lblSt_Id.getText()).getDetailsList()) {
                        if (studentProgrms.getProgram().getProgramId().equals(item.getProgram().getProgramId())) {
                            continue L1;
                        }
                    }
                    studentRegistrationBO.addStudentProgram(item);
                }

            }
            clearAllFields();
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) {
        clearAllFields();
    }

    public void clearAllFields() {
        lblSt_Id.setText(studentRegistrationBO.generateId());
        txtDuration.clear();
        txtFee.clear();
        cmbDay.getSelectionModel().clearSelection();
        cmbMonth.getSelectionModel().clearSelection();
        cmbYear.getSelectionModel().clearSelection();
        cmbTrainingProgram.getSelectionModel().clearSelection();
        btnRegister.setText("Register");
        tblPrograms.getItems().clear();
        txtSearchID.clear();
        txtSearchName.clear();
        for (JFXTextField textField : map.keySet()) {
            textField.clear();
            textField.setStyle("-fx-border-color: white;"+"-fx-border-width: 1");
        }
        btnRegister.setDisable(true);
    }

    public void addProgramOnAction(ActionEvent actionEvent) {
        if (!cmbTrainingProgram.getValue().isEmpty()){
            ObservableList<ProgramDTO> studentProgramList = tblPrograms.getItems();
            L1:for (ProgramDTO programDTO : studentProgramList) {
                if (cmbTrainingProgram.getValue().equals(programDTO.getProgram())){
                    Alert alert =new Alert(Alert.AlertType.WARNING,"This student has already registered to this program");
                    alert.show();
                    return;
                }else {
                    continue L1;
                }
            }
            for (ProgramDTO program : allPrograms) {
                if (program.getProgram().equals(cmbTrainingProgram.getValue())) {
                    studentProgramList.add(program);
                }
            }
            tblPrograms.setItems(studentProgramList);
            checkAll();
        }else{

            new Alert(Alert.AlertType.WARNING,"Please select a program").show();
            return;

        }



    }

    private boolean checkAll(){
        if (Validation.validate(map)){
            if (cmbYear.getValue() != null && cmbMonth.getValue() != null && cmbDay.getValue() != null){
                if (!tblPrograms.getItems().isEmpty()){
                    btnRegister.setDisable(false);
                    return true;
                }else{
                    Alert alert =new Alert(Alert.AlertType.WARNING,"Please select a program");
                    return false;
                }
            }else{
                Alert alert =new Alert(Alert.AlertType.WARNING,"Please enter birthday correctly");
                return false;
            }
        }
        return false;
    }
}
