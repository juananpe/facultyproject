package faculty.project.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Student extends User {

  private int earnedCredits = 0;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  private Collection<AcademicRecord> academicRecords;


  public Student(String userName, String password, String completeName, String email, String address, String phoneNumber) {
    super(userName, password, completeName, email, address, phoneNumber);

    academicRecords = new ArrayList<>();
  }

  public Student() {

  }


  public void enroll(Subject subject) {
    academicRecords.add(new AcademicRecord(subject, this));
  }

  public int getEarnedCredits() {
    return earnedCredits;
  }

  public void setEarnedCredits(int earnedCredits) {
    this.earnedCredits = earnedCredits;
  }

  public Collection<AcademicRecord> getAcademicRecords() {
    return academicRecords;
  }

}
