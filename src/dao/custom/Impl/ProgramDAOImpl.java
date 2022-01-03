package dao.custom.Impl;

import dao.custom.ProgramDAO;
import entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;


public class ProgramDAOImpl implements ProgramDAO {

    @Override
    public boolean add(Program program) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(program);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Program get(String programID) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Program program = session.get(Program.class, programID);
        transaction.commit();
        session.close();
        return program;
    }

    @Override
    public boolean update(Program program) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(program);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String programID) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Program load = session.load(Program.class, programID);
        session.delete(load);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Program> getAllPrograms() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From Program";
        Query query = session.createQuery(hql);
        List<Program> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<String> getProgramIDByName(String name) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT p.programId From Program p WHERE p.program=:program";
        Query query = session.createQuery(hql);
        query.setParameter("program", name);
        List<String> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}
