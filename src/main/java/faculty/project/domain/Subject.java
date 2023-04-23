package faculty.project.domain;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "\"Subject\"")
public class Subject {
    @Id
    private String name;
    private int numCredits;
    private int maxNumStudents;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "\"Prerequisites\"")
    @JoinColumn(name = "NAME")
    private Collection<Subject> preRequisites;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher")
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


    @Override
    public String toString() {
        return "Subject{" +
            "name='" + name + '\'' +
            ", teacher=" + teacher +
            '}';
    }
}
