package faculty.project.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Teacher extends User {
    private Integer officeNumber;
    private String corporatePhone;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Collection<Subject> teaches = new ArrayList<>();

    public Teacher(int officeNumber, String corporatePhone){
        super();
    }

    public void add(Subject subject){
        teaches.add(subject);
        subject.setTeacher(this);
    }


}
