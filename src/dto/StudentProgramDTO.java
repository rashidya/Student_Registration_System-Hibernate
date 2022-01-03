package dto;

import java.util.Date;

public class StudentProgramDTO {
    private String programId;
    private String program;
    private String duration;
    private double fee;
    private Date registrationDate;

    public StudentProgramDTO() {
    }

    public StudentProgramDTO(String programId, String program, String duration, double fee, Date registrationDate) {
        this.setProgramId(programId);
        this.setProgram(program);
        this.setDuration(duration);
        this.setFee(fee);
        this.setRegistrationDate(registrationDate);
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
