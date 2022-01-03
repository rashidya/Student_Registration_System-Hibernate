package business.custom;

import business.SuperBO;
import dto.ProgramDTO;
import dto.RegistrationDetailsDTO;
import dto.StudentDTO;
import entity.Student;

import java.text.ParseException;
import java.util.List;

public interface ManageStudentRegistrationBO extends SuperBO {
    public boolean registerStudent(StudentDTO dto) throws ParseException;
    public StudentDTO  getStudent(String studentID);
    public boolean updateStudent(StudentDTO dto) throws ParseException;
    public boolean deleteStudent(String studentID);
    List<ProgramDTO> getAllPrograms();
    List<Student> getStudentListName(String name);
    void addStudentProgram(RegistrationDetailsDTO item) throws ParseException;
    List<String> getStudentListID(String id);
    String generateId();
}
