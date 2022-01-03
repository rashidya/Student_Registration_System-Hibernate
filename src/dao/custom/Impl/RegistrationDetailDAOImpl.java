package dao.custom.Impl;

import dao.custom.RegistrationDetailDAO;
import entity.Program;
import entity.RegistrationDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class RegistrationDetailDAOImpl implements RegistrationDetailDAO {
    @Override
    public boolean add(RegistrationDetails registrationDetails) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(registrationDetails);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public RegistrationDetails get(Integer id) {
        return null;
    }

    @Override
    public boolean update(RegistrationDetails registrationDetails) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<RegistrationDetails> getAllPrograms(Program program) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From Program WHERE program = :programName";
        Query query = session.createQuery(hql);
        query.setParameter("programName", program);
        List<RegistrationDetails> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}
