package faculty.project.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

@Entity
@DiscriminatorValue("TEACHER")
public class Teacher extends User {
    private String corporatePhone;
    private String officeNumber;

    @OneToMany(mappedBy = "teacher")
    private Set<Subject> teaches = new HashSet<>();

    @OneToMany(mappedBy = "signedBy")
    private Set<AcademicRecord> academicRecords = new HashSet<>();

    public Teacher(int officeNumber, String corporatePhone){
        super();
    }

    public Teacher(int officeNumber, String corporatePhone, String username, String password){
        super(username, password);
    }

    public Teacher() {

    }

    public Set<Subject> getSubjects() {
        return teaches;
    }

    public void add(Subject subject){
        teaches.add(subject);
        subject.setTeacher(this);
    }


    public Set<Subject> clearSubjects(){
        Set<Subject> subjects = new HashSet<>();
        for (Subject subject : teaches) {
            subject.setTeacher(null);
            subjects.add(subject);
        }
        teaches.clear();
        return subjects;
    }


}
