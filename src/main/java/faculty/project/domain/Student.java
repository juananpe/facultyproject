package faculty.project.domain;

import java.util.Collection;

public class Student extends User {
    private int earnedCredits;
    private Collection<Subject> enrolledIn;
    private Collection<AcademicRecord> academicRecords;

    public Student(String userName, String password, String completeName, String email, String address, String phoneNumber) {
        super(userName, password, completeName, email, address, phoneNumber);
    }


    public Collection<Subject> getEnrolledIn() {
        return enrolledIn;
    }

    public void enroll(Subject subject){
        this.enrolledIn.add(subject);
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
