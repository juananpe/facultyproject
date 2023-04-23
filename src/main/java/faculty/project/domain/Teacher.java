package faculty.project.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "\"Teacher\"")
public class Teacher extends User {
    private Integer officeNumber;
    private String corporatePhone;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher")
    private List<Subject> teaches = new ArrayList<>();

    public Teacher(int officeNumber, String corporatePhone){
        super();
    }

    public Teacher(int officeNumber, String corporatePhone, String username, String password){
        super(username, password);
    }

    public Teacher() {

    }

    public List<Subject> getSubjects() {
        return teaches;
    }

    public void add(Subject subject){
        teaches.add(subject);
        subject.setTeacher(this);
    }


    public List<Subject> clearSubjects(){
        List<Subject> subjects = new ArrayList<>();
        for (Subject subject : teaches) {
            subject.setTeacher(null);
            subjects.add(subject);
        }
        teaches.clear();
        return subjects;
    }


}
