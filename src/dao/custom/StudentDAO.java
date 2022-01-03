package dao.custom;

import dao.CrudDAO;
import entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student,String> {
    List<String> searchID(String Id);
    List<Student> searchName(String Name);

    List<Student> getAll();

    String getLastId();
}
