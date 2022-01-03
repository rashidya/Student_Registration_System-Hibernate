package business;

import business.custom.Impl.ManageProgramsBOImpl;
import business.custom.Impl.ManageStudentRegistrationBOImpl;
import business.custom.Impl.MangeReportsBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBOFactory(){
        if (boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case STUDENT:return new ManageStudentRegistrationBOImpl();
            case PROGRAM:return new ManageProgramsBOImpl();
            case REPORT:return new MangeReportsBOImpl();
            default:return null;
        }
    }

    public enum BOTypes{
        STUDENT,PROGRAM,REPORT
    }
}
