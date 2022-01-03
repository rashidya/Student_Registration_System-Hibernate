package business.custom;

import business.SuperBO;
import dto.ProgramDTO;
import dto.StudentDTO;

import java.text.ParseException;
import java.util.List;

public interface ManageReportsBO extends SuperBO {
    List<ProgramDTO> getAllPrograms();
    List<String> getProgramIDByName(String name);
    List<StudentDTO> getAllStudents();
    StudentDTO getStudent(String id);
    List<StudentDTO> getStudentsOfAProgram(String id) throws ParseException;
}
