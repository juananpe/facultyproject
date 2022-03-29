package faculty.project.domain;

public class AdministrtiveOfficer extends User{
    Integer officeNumber;
    String corporatePhone;

    public AdministrtiveOfficer(int officeNumber, String corporatePhone) {
        super();
        this.officeNumber = officeNumber;
        this.corporatePhone = corporatePhone;
    }

    public void restartSystem(){

    }
}
