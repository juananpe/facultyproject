package faculty.project.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends User {
    private Integer earnedCredits = 0;

    // @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AcademicRecord> academicRecords;
    private static final int MAXCREDITSDEGREE = 255;

    public Student(String userName, String password, String completeName, String email, String address, String phoneNumber) {
        super(userName, password, completeName, email, address, phoneNumber);

        academicRecords = new HashSet<>();
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

    public boolean isEligibleForCredits(int credits) {
        // the credits a student has already passed in previous years, cannot exceed the maximum number of credits of the degree (255).
        return getEarnedCredits() + credits <= MAXCREDITSDEGREE;
    }

    public void addEarnedCredits(int creditNumber) {
        earnedCredits += creditNumber;
    }
}
