package dao.custom.Impl;

import dao.custom.QueryDAO;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Student> getStudentsOfaProgram(String id) throws ParseException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT s.st_Id,s.fullName,s.address,s.contactNo,s.dob,s.nic From Student s INNER JOIN RegistrationDetails r ON r.program.programId=:id AND r.student.st_Id=s.st_Id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<Object[]> list = query.list();
        List<Student> studentList = new ArrayList<>();
        for (Object[] ob : list) {
            Student student = new Student();
            student.setSt_Id(ob[0].toString());
            student.setFullName(ob[1].toString());
            student.setAddress(ob[2].toString());
            student.setContactNo(ob[3].toString());
            student.setDob(new SimpleDateFormat("dd-MM-yyyy").parse(ob[4].toString()));
            student.setNic(ob[5].toString());
            studentList.add(student);
        }
        transaction.commit();
        session.close();
        return studentList;
    }
}
