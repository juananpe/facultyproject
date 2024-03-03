package faculty.project.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMINISTRATIVE_OFFICER")
public class AdministrativeOfficer extends User {
    Integer officeNumber;
    String corporatePhone;

    public AdministrativeOfficer(int officeNumber, String corporatePhone) {
        super();
        this.officeNumber = officeNumber;
        this.corporatePhone = corporatePhone;
    }


    public AdministrativeOfficer() {

    }
}
