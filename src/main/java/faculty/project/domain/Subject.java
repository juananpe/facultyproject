package faculty.project.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Subject {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int numCredits;
    private int maxNumStudents;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Collection<Subject> preRequisites;

    @OneToOne
    private Teacher teacher;

    public Subject() {

    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject(String name, int numCredits, int maxNumStudents) {
        this.name = name;
        this.numCredits = numCredits;
        this.maxNumStudents = maxNumStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditNumber() {
        return numCredits;
    }

    public void setNumCredits(int numCredits) {
        this.numCredits = numCredits;
    }

    public int getMaxNumStudents() {
        return maxNumStudents;
    }

    public void setMaxNumStudents(int maxNumStudents) {
        this.maxNumStudents = maxNumStudents;
    }

    public Collection<Subject> getPreRequisites() {
        return preRequisites;
    }

    public void setPreRequisites(Collection<Subject> preRequisites) {
        this.preRequisites = preRequisites;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Subject{" +
            "name='" + name + '\'' +
            ", teacher=" + teacher +
            '}';
    }
}
