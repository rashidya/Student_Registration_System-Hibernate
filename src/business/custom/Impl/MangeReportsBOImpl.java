package business.custom.Impl;

import business.custom.ManageReportsBO;
import dao.DAOFactory;
import dao.custom.ProgramDAO;
import dao.custom.QueryDAO;
import dao.custom.StudentDAO;
import dto.ProgramDTO;
import dto.RegistrationDetailsDTO;
import dto.StudentDTO;
import entity.Program;
import entity.RegistrationDetails;
import entity.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MangeReportsBOImpl implements ManageReportsBO {
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public List<ProgramDTO> getAllPrograms() {
        List<Program> programs = programDAO.getAllPrograms();

        List<ProgramDTO> allPrograms = new ArrayList<>();
        for (Program program : programs) {
            ProgramDTO programDTO = new ProgramDTO(program.getProgramId(), program.getProgram(), program.getDuration(), program.getFee());
            allPrograms.add(programDTO);
        }
        return allPrograms;
    }

    @Override
    public List<String> getProgramIDByName(String name) {
        return programDAO.getProgramIDByName(name);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentDAO.getAll();
        List<StudentDTO> allStudents = new ArrayList<>();
        for (Student student : students) {
            String dob = new SimpleDateFormat("dd-MM-yyyy").format(student.getDob());
            StudentDTO studentDTO = new StudentDTO(student.getSt_Id(), student.getFullName(), student.getNic(), dob, student.getAddress(), student.getContactNo());
            allStudents.add(studentDTO);
        }
        return allStudents;
    }

    @Override
    public StudentDTO getStudent(String id) {
        Student student = studentDAO.get(id);
        String dob = new SimpleDateFormat("dd-MM-yyyy").format(student.getDob());
        StudentDTO studentDTO = new StudentDTO(student.getSt_Id(), student.getFullName(), student.getNic(), dob, student.getAddress(), student.getContactNo());
        List<RegistrationDetailsDTO> list = new ArrayList<>();
        for (RegistrationDetails details : student.getDetailsList()) {
            Program program = details.getProgram();
            ProgramDTO programDTO = new ProgramDTO(program.getProgramId(), program.getProgram(), program.getDuration(), program.getFee());
            RegistrationDetailsDTO dto = new RegistrationDetailsDTO(details.getRegistrationId(), studentDTO, programDTO, details.getRegistrationDate());
            list.add(dto);
        }
        studentDTO.setDetailsList(list);

        return studentDTO;
    }

    @Override
    public List<StudentDTO> getStudentsOfAProgram(String id) throws ParseException {
        List<Student> students = queryDAO.getStudentsOfaProgram(id);
        List<StudentDTO> allStudents = new ArrayList<>();

        for (Student student : students) {
            String dob = new SimpleDateFormat("dd-MM-yyyy").format(student.getDob());

            StudentDTO studentDTO = new StudentDTO(student.getSt_Id(), student.getFullName(), student.getNic(), dob, student.getAddress(), student.getContactNo());
            allStudents.add(studentDTO);
        }

        return allStudents;
    }


}
