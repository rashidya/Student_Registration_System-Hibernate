package dao.custom.Impl;

import dao.custom.StudentDAO;
import entity.RegistrationDetails;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student student) {
        for (RegistrationDetails details : student.getDetailsList()) {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.save(details);
            transaction.commit();
            session.close();

        }
        return true;
    }

    @Override
    public Student get(String studentID) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, studentID);
        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public boolean update(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String studentID) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.load(Student.class, studentID);
        session.delete(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<String> searchID(String st_ID) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "Select s.st_Id From Student s WHERE s.st_Id LIKE :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", "%" + st_ID + "%");
        List<String> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override


    public List<Student> searchName(String name) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From Student s WHERE s.fullName LIKE :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", "%" + name + "%");
        List<Student> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<Student> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From Student";
        Query query = session.createQuery(hql);
        List<Student> list = query.list();
        transaction.commit();
        session.close();
        return list;

    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createSQLQuery("SELECT st_Id FROM Student ORDER BY st_Id DESC LIMIT 1");
        List<String> list = query.list();
        transaction.commit();
        session.close();
        return list.size() == 0 ? null : list.get(0);
    }
}
