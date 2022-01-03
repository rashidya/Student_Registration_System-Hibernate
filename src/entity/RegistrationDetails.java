package entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity()
public class RegistrationDetails {
    @Id
    @GeneratedValue
    private  int RegistrationId;
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL)
    private Program program;
    @CreationTimestamp
    private Date registrationDate;

    public RegistrationDetails() {
    }

    public RegistrationDetails(int registrationId, Student student, Program program, Date registrationDate) {
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
