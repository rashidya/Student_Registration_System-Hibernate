package entity;

import com.sun.istack.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Program {
    @Id
    @NotNull
    private String programId;
    private String program;
    private String duration;
    private double fee;
    @OneToMany(mappedBy = "program")
    private List<RegistrationDetails> detailsList;

    public Program() {
    }

    public Program(String programId, String program, String duration, double fee) {
        this.setProgramId(programId);
        this.setProgram(program);
        this.setDuration(duration);
        this.setFee(fee);
    }

    public Program(String programId, String program, String duration, double fee, List<RegistrationDetails> detailsList) {
        this.setProgramId(programId);
        this.setProgram(program);
        this.setDuration(duration);
        this.setFee(fee);
        this.setDetailsList(detailsList);
    }

    @Override
    public String toString() {
        return "Program{" +
                "programId='" + getProgramId() + '\'' +
                ", program='" + getProgram() + '\'' +
                ", duration='" + getDuration() + '\'' +
                ", fee=" + getFee() +
                '}';
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<RegistrationDetails> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<RegistrationDetails> detailsList) {
        this.detailsList = detailsList;
    }
}
