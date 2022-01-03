package dto;

import java.util.Date;
import java.util.List;

public class StudentDTO {
    private String st_Id;
    private String fullName;
    private String nic;
    private String dob;
    private String address;
    private String contactNo;

    private List<RegistrationDetailsDTO> detailsList;

    public StudentDTO() {
    }

    public StudentDTO(String st_Id, String fullName, String nic,String dob, String address, String contactNo) {
        this.st_Id = st_Id;
        this.fullName = fullName;
        this.nic = nic;
        this.dob = dob;
        this.address = address;
        this.contactNo = contactNo;
    }

    public StudentDTO(String st_Id, String fullName, String nic, String dob, String address, String contactNo, List<RegistrationDetailsDTO> detailsList) {
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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

    public List<RegistrationDetailsDTO> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<RegistrationDetailsDTO> detailsList) {
        this.detailsList = detailsList;
    }
}
