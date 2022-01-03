package business.custom.Impl;


import business.custom.ManageProgramsBO;
import dao.DAOFactory;
import dao.custom.ProgramDAO;
import dao.custom.RegistrationDetailDAO;
import dto.ProgramDTO;
import entity.Program;
import entity.RegistrationDetails;


import java.util.ArrayList;
import java.util.List;

public class ManageProgramsBOImpl implements ManageProgramsBO {

    private final ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);
    RegistrationDetailDAO registrationDetailDAO = (RegistrationDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION_DETAILS);

    @Override
    public boolean addProgram(ProgramDTO dto) {
        Program program = new Program(dto.getProgramId(), dto.getProgram(), dto.getDuration(), dto.getFee());
        return programDAO.add(program);
    }

    @Override
    public ProgramDTO getProgram(String programID) {
        Program program = programDAO.get(programID);
        ProgramDTO programDTO = new ProgramDTO(program.getProgramId(), program.getProgram(), program.getDuration(), program.getFee());
        return programDTO;
    }

    @Override
    public boolean updateProgram(ProgramDTO dto) {
        Program program = new Program(dto.getProgramId(), dto.getProgram(), dto.getDuration(), dto.getFee());
        return programDAO.update(program);
    }

    @Override
    public boolean deleteProgram(String programID) {

        for (RegistrationDetails temp : registrationDetailDAO.getAllPrograms(programDAO.get(programID))) {
            registrationDetailDAO.delete(temp.getRegistrationId());
        }
        return programDAO.delete(programID);
    }

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

}
