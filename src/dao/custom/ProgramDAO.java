package dao.custom;

import dao.CrudDAO;
import entity.Program;

import java.util.List;

public interface ProgramDAO extends CrudDAO<Program,String> {

    List<Program> getAllPrograms();
    List<String> getProgramIDByName(String name);
}
