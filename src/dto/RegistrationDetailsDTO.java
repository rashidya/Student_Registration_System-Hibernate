package dto;

import entity.Program;
import entity.Student;
import org.hibernate.annotations.CreationTimestamp;


import java.util.Date;

public class RegistrationDetailsDTO {

    private int RegistrationId;
    private StudentDTO student;
    private ProgramDTO program;
    private Date registrationDate;

    public RegistrationDetailsDTO() {
    }

    public RegistrationDetailsDTO(int registrationId, StudentDTO student, ProgramDTO program, Date registrationDate) {
        setRegistrationId(registrationId);
        this.setStudent(student);
        this.setProgram(program);
        this.setRegistrationDate(registrationDate);
    }

    public int getRegistrationId() {
        return RegistrationId;
    }

    public void setRegistrationId(int registrationId) {
        RegistrationId = registrationId;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public ProgramDTO getProgram() {
        return program;
    }

    public void setProgram(ProgramDTO program) {
        this.program = program;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
