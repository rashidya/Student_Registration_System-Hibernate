package dto;

import java.util.List;

public class ProgramDTO {
    private String programId;
    private String program;
    private String duration;
    private double fee;
    private List<RegistrationDetailsDTO> detailsList;

    public ProgramDTO() {
    }

    public ProgramDTO(String programId, String program, String duration, double fee) {
        this.setProgramId(programId);
        this.setProgram(program);
        this.setDuration(duration);
        this.setFee(fee);
    }

    public ProgramDTO(String programId, String program, String duration, double fee, List<RegistrationDetailsDTO> detailsList) {
        this.programId = programId;
        this.program = program;
        this.duration = duration;
        this.fee = fee;
        this.detailsList = detailsList;
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

    public List<RegistrationDetailsDTO> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<RegistrationDetailsDTO> detailsList) {
        this.detailsList = detailsList;
    }
}
