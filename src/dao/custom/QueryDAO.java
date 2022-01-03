package dao.custom;

import dao.SuperDAO;
import entity.Student;

import java.text.ParseException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Student> getStudentsOfaProgram(String id) throws ParseException;
}
