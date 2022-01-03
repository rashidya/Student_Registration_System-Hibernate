package business.custom;

import business.SuperBO;
import dto.ProgramDTO;

import java.util.List;

public interface ManageProgramsBO extends SuperBO {
    boolean addProgram(ProgramDTO programDTO);
    ProgramDTO getProgram(String programID);
    boolean updateProgram(ProgramDTO dto);
    boolean deleteProgram(String programID);
    List<ProgramDTO> getAllPrograms();
}
