package dao.custom;

import dao.CrudDAO;
import entity.Program;
import entity.RegistrationDetails;

import java.util.List;

public interface RegistrationDetailDAO extends CrudDAO<RegistrationDetails,Integer> {






    List<RegistrationDetails> getAllPrograms(Program program);
}
