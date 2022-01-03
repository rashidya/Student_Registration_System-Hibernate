package entity;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
public class Student {
    @Id
    private String st_Id;
    private String fullName;
    private String nic;
    private Date dob;
    private String address;
    private String contactNo;
    @OneToMany(mappedBy = "student",fetch = FetchType.EAGER)
    private List<RegistrationDetails> detailsList ;

    public Student() {
    }

    public Student(String st_Id, String fullName, String nic, Date dob, String address, String contactNo) {
        this.st_Id = st_Id;
        this.fullName = fullName;
        this.nic = nic;
        this.dob = dob;
        this.address = address;
        this.contactNo = contactNo;
    }

    public Student(String st_Id, String fullName, String nic, Date dob, String address, String contactNo, List<RegistrationDetails> detailsList) {
        this.setSt_Id(st_Id);
        this.setFullName(fullName);
        this.setNic(nic);
        this.setDob(dob);
        this.setAddress(address);
        this.setContactNo(contactNo);
        this.setDetailsList(detailsList);
    }

    @Override
    public String toString() {
        return "Student{" +
                "st_Id='" + getSt_Id() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", nic='" + getNic() + '\'' +
                ", dob='" + getDob() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", contactNo='" + getContactNo() + '\'' +
                '}';
    }

    public String getSt_Id() {
        return st_Id;
    }

    public void setSt_Id(String st_Id) {
        this.st_Id = st_Id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public List<RegistrationDetails> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<RegistrationDetails> detailsList) {
        this.detailsList = detailsList;
    }
}
