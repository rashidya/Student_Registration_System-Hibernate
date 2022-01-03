package dao;

import business.BOFactory;
import business.SuperBO;
import business.custom.Impl.ManageProgramsBOImpl;
import business.custom.Impl.ManageStudentRegistrationBOImpl;
import dao.custom.Impl.ProgramDAOImpl;
import dao.custom.Impl.QueryDAOImpl;
import dao.custom.Impl.RegistrationDetailDAOImpl;
import dao.custom.Impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDAOFactory(){
        if (daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case STUDENT:return new StudentDAOImpl();
            case PROGRAM:return new ProgramDAOImpl();
            case REGISTRATION_DETAILS:return new RegistrationDetailDAOImpl();
            case QUERY:return new QueryDAOImpl();
            default:return null;
        }
    }

    public enum DAOTypes{
        STUDENT,PROGRAM,REGISTRATION_DETAILS,QUERY
    }
}
