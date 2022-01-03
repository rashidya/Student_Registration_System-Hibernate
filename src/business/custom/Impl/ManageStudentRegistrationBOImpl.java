package business.custom.Impl;

import business.custom.ManageStudentRegistrationBO;
import dao.DAOFactory;
import dao.custom.ProgramDAO;
import dao.custom.RegistrationDetailDAO;
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

public class ManageStudentRegistrationBOImpl implements ManageStudentRegistrationBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);
    RegistrationDetailDAO registrationDetailDAO = (RegistrationDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION_DETAILS);

    @Override
    public boolean registerStudent(StudentDTO dto) throws ParseException {
        List<RegistrationDetails> detailList = new ArrayList<>();

        Date dob = new SimpleDateFormat("dd-MM-yyyy").parse(dto.getDob());
        Student student = new Student(dto.getSt_Id(), dto.getFullName(), dto.getNic(), dob, dto.getAddress(), dto.getContactNo());

        for (RegistrationDetailsDTO dto1 : dto.getDetailsList()) {
            ProgramDTO programDto = dto1.getProgram();
            Program program = new Program(programDto.getProgramId(), programDto.getProgram(), programDto.getDuration(), programDto.getFee());

            RegistrationDetails detail = new RegistrationDetails();
            detail.setStudent(student);
            detail.setProgram(program);


            detailList.add(detail);
        }
        student.setDetailsList(detailList);
        return studentDAO.add(student);

    }

    @Override
    public StudentDTO getStudent(String studentID) {
        Student student = studentDAO.get(studentID);
        if (student != null) {
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
        } else {
            return null;
        }
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws ParseException {
        List<RegistrationDetails> detailList = new ArrayList<>();

        Date dob = new SimpleDateFormat("dd-MM-yyyy").parse(dto.getDob());
        Student student = new Student(dto.getSt_Id(), dto.getFullName(), dto.getNic(), dob, dto.getAddress(), dto.getContactNo());

        for (RegistrationDetailsDTO dto1 : dto.getDetailsList()) {
            ProgramDTO programDto = dto1.getProgram();
            Program program = new Program(programDto.getProgramId(), programDto.getProgram(), programDto.getDuration(), programDto.getFee());

            RegistrationDetails detail = new RegistrationDetails();
            detail.setStudent(student);
            detail.setProgram(program);


            detailList.add(detail);
        }
        student.setDetailsList(detailList);
        return studentDAO.update(student);

    }

    @Override
    public boolean deleteStudent(String studentID) {
        return studentDAO.delete(studentID);
    }

    @Override
    public List<ProgramDTO> getAllPrograms() {
        List<ProgramDTO> allPrograms = new ArrayList<>();
        List<Program> programs = programDAO.getAllPrograms();
        for (Program program : programs) {
            ProgramDTO programDTO = new ProgramDTO(program.getProgramId(), program.getProgram(), program.getDuration(), program.getFee());
            allPrograms.add(programDTO);
        }
        return allPrograms;
    }

    @Override
    public List<Student> getStudentListName(String name) {
        return studentDAO.searchName(name);
    }

    @Override
    public List<String> getStudentListID(String id) {
        return studentDAO.searchID(id);
    }

    @Override
    public String generateId() {
        String lastId = studentDAO.getLastId();

        if (lastId != null) {
            int index = Integer.parseInt(lastId.split("T")[1]);
            index++;
            return (index < 10) ? "ST000" + index : (index < 100) ? "ST00" + index : (index < 1000) ? "ST0" + index : "ST" + index;

        } else {
            return "ST0001";
        }
    }

    @Override
    public void addStudentProgram(RegistrationDetailsDTO item) throws ParseException {
        ProgramDTO programDTO = item.getProgram();
        Program program = new Program(programDTO.getProgramId(), programDTO.getProgram(), programDTO.getDuration(), programDTO.getFee());
        StudentDTO studentDTO = item.getStudent();
        Date dob = new SimpleDateFormat("dd-MM-yyyy").parse(studentDTO.getDob());
        Student student = new Student(studentDTO.getSt_Id(), studentDTO.getFullName(), studentDTO.getNic(), dob, studentDTO.getAddress(), studentDTO.getContactNo());
        RegistrationDetails detail = new RegistrationDetails();

        detail.setProgram(program);
        detail.setStudent(student);
        detail.setRegistrationDate(item.getRegistrationDate());
        registrationDetailDAO.add(detail);
    }


}
